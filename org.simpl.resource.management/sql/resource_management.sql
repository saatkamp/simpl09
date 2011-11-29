/**
 * CREATE SCHEMAS
 */
CREATE SCHEMA simpl_resources;
CREATE SCHEMA simpl_definitions;

/**
 * CREATE TABLES
 */
CREATE TABLE simpl_resources.dataconverters (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   input_datatype varchar(255) NOT NULL,
   output_datatype varchar(255) NOT NULL,
   workflow_dataformat varchar(255) UNIQUE NOT NULL,
   direction_output_workflow char(5) DEFAULT 'true',
   direction_workflow_input char(5) DEFAULT 'true',
   implementation varchar(255) UNIQUE NOT NULL,
   xml_schema xml
);

CREATE TABLE simpl_resources.connectors (
   id SERIAL PRIMARY KEY,
   dataconverter_id INTEGER,
   name varchar(255) UNIQUE NOT NULL,
   input_datatype varchar(255) NOT NULL,
   output_datatype varchar(255) NOT NULL,
   implementation varchar(255) UNIQUE NOT NULL,
   properties_description xml DEFAULT '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type></type>
  <subType></subType>
  <language></language>
  <dataFormat></dataFormat>
</properties_description>' NOT NULL,
   FOREIGN KEY (dataconverter_id) references simpl_resources.dataconverters(id)
);

CREATE TABLE simpl_resources.datasources (
   id SERIAL PRIMARY KEY,
   connector_id INTEGER,
   logical_name varchar(255) UNIQUE NOT NULL,
   security_username varchar(255),
   security_password varchar(255),
   interface_description varchar(255) NOT NULL,
   properties_description xml,
   connector_properties_description xml DEFAULT '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd ">
  <type></type>
  <subType></subType>
  <language></language>
  <dataFormat></dataFormat>
</connector_properties_description>',
   FOREIGN KEY (connector_id) references simpl_resources.connectors(id)
);

CREATE TABLE simpl_resources.datatransformationservices (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   connector_dataformat varchar(255) NOT NULL,
   workflow_dataformat varchar(255) NOT NULL,
   direction_connector_workflow char(5) DEFAULT 'true',
   direction_workflow_connector char(5) DEFAULT 'true',
   implementation varchar(255) UNIQUE NOT NULL
);

CREATE TABLE simpl_resources.strategyplugins (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   implementation varchar(255) UNIQUE NOT NULL
);

CREATE TABLE simpl_definitions.languages (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   statement_description xml DEFAULT '<statement_description
  xmlns="http://org.simpl.resource.management/languages/statement_description"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd ">
  <statement name="">
    <predicate>predicate</predicate>
  </statement>
</statement_description>'
);

CREATE TABLE simpl_definitions.datacontainer_reference_types (
   id SERIAL PRIMARY KEY,
   name varchar(255) NOT NULL,
   xsd_type xml NOT NULL
);

CREATE TABLE simpl_definitions.datasource_reference_types (
   id SERIAL PRIMARY KEY,
   name varchar(255) NOT NULL,
   xsd_type xml NOT NULL
);

/**
 * CREATE FUNCTIONS
 */
/* retrieves the property (text) of a XML element from a datasources table xml field (xml) */
CREATE OR REPLACE FUNCTION getDataSourceXMLProperty(text, xml)
  RETURNS text AS 
  $$
    SELECT CAST ((xpath('//rmns:' || $1 || '/text()', $2, ARRAY[ARRAY['rmns','http://org.simpl.resource.management/datasources/connector_properties_description']]))[1] AS text) FROM simpl_resources.datasources
  $$ 
LANGUAGE SQL;

/* retrieves the property (text) of a XML element from a connectors table xml field (xml) */
CREATE OR REPLACE FUNCTION getConnectorXMLProperty(text, xml)
  RETURNS text AS 
  $$
    SELECT CAST ((xpath('//rmns:' || $1 || '/text()', $2, ARRAY[ARRAY['rmns','http://org.simpl.resource.management/connectors/properties_description']]))[1] AS text) FROM simpl_resources.connectors
  $$ 
LANGUAGE SQL;

CREATE LANGUAGE plpgsql;

/* tries to find a connector for a new inserted or updated data source, based on its connector_properties_description */
CREATE OR REPLACE FUNCTION setDataSourceConnector() RETURNS trigger AS '
  DECLARE
    matching_connector_id INTEGER;
  BEGIN
    RAISE NOTICE ''[Trigger] set_data_source_connector activated'';
    IF NEW.connector_properties_description IS NOT NULL THEN 
		  RAISE NOTICE ''[Trigger] type: %'', getDataSourceXMLProperty(''type'', NEW.connector_properties_description);
			RAISE NOTICE ''[Trigger] subType: %'', getDataSourceXMLProperty(''subType'', NEW.connector_properties_description);
			RAISE NOTICE ''[Trigger] language: %'', getDataSourceXMLProperty(''language'', NEW.connector_properties_description);
			RAISE NOTICE ''[Trigger] dataFormat: %'', getDataSourceXMLProperty(''dataFormat'', NEW.connector_properties_description);
      SELECT INTO matching_connector_id id 
        FROM simpl_resources.connectors 
        WHERE getConnectorXMLProperty(''type'', simpl_resources.connectors.properties_description) = getDataSourceXMLProperty(''type'', NEW.connector_properties_description) 
          AND getConnectorXMLProperty(''subType'', simpl_resources.connectors.properties_description) = getDataSourceXMLProperty(''subType'', NEW.connector_properties_description) 
          AND getConnectorXMLProperty(''language'', simpl_resources.connectors.properties_description) = getDataSourceXMLProperty(''language'', NEW.connector_properties_description) 
          AND getConnectorXMLProperty(''dataFormat'', simpl_resources.connectors.properties_description) = getDataSourceXMLProperty(''dataFormat'', NEW.connector_properties_description) 
        LIMIT 1;
      IF matching_connector_id IS NOT NULL THEN
        /* avoid a second update if this function is triggered by an UPDATE from updateDataSourceConnectors() */
        IF NEW.connector_id IS NULL OR (NEW.connector_id <> matching_connector_id) THEN
          NEW.connector_id := matching_connector_id;
          RAISE NOTICE ''[Trigger] found matching connector (id = %) for datasource.'', matching_connector_id;
        END IF;
      ELSE 
        IF matching_connector_id IS NULL THEN
          NEW.connector_id := NULL;
          RAISE NOTICE ''[Trigger] no matching connector found.'';  
        END IF;
      END IF;
    END IF;  
    RETURN NEW;
  END;' 
LANGUAGE plpgsql;

/* tries to find a connector for all data sources without an assigned connector when a connector is inserted or updated, based on the data sources connector_properties_description */
CREATE OR REPLACE FUNCTION updateDataSourceConnectors() RETURNS trigger AS '
  DECLARE
    matching_connector_id INTEGER;
    datasource_record RECORD;
    connector_type VARCHAR;
    connector_sub_type VARCHAR;
    connector_language VARCHAR;
    connector_dataformat VARCHAR;
    datasource_type VARCHAR;
    datasource_sub_type VARCHAR;
    datasource_language VARCHAR;
    datasource_dataformat VARCHAR;
  BEGIN
    RAISE NOTICE ''[Trigger] update_data_source_connectors activated'';
    IF NEW.properties_description IS NOT NULL THEN
	    connector_type := getConnectorXMLProperty(''type'', NEW.properties_description);
		  connector_sub_type := getConnectorXMLProperty(''subType'', NEW.properties_description);
		  connector_language := getConnectorXMLProperty(''language'', NEW.properties_description);
		  connector_dataformat := getConnectorXMLProperty(''dataFormat'', NEW.properties_description);
      RAISE NOTICE ''[Trigger] new or updated connector: %, %, %, %'',connector_type, connector_sub_type, connector_language, connector_dataformat;
	    FOR datasource_record IN SELECT * FROM simpl_resources.datasources ORDER BY id LOOP
	      datasource_type := getDataSourceXMLProperty(''type'', datasource_record.connector_properties_description);
	      datasource_sub_type := getDataSourceXMLProperty(''subType'', datasource_record.connector_properties_description);
	      datasource_language := getDataSourceXMLProperty(''language'', datasource_record.connector_properties_description);
        datasource_dataformat := getDataSourceXMLProperty(''dataFormat'', datasource_record.connector_properties_description);
	      IF connector_type = datasource_type 
          AND connector_sub_type = datasource_sub_type 
          AND connector_language = datasource_language 
          AND connector_dataformat = datasource_dataformat 
          AND (datasource_record.connector_id IS NULL OR (datasource_record.connector_id <> NEW.id)) THEN
          UPDATE simpl_resources.datasources SET connector_id = NEW.id WHERE simpl_resources.datasources.id = datasource_record.id;
	        RAISE NOTICE ''[Trigger] updated connector_id (id = %) on datasource (id = %)'', NEW.id, datasource_record.id;
	      END IF;
        /* remove connector from data source if the connector changed and does not fit anymore */
        IF ((connector_type <> datasource_type 
	        OR connector_sub_type <> datasource_sub_type 
	        OR connector_language <> datasource_language 
	        OR connector_dataformat <> datasource_dataformat) 
	        AND (datasource_record.connector_id = NEW.id)) THEN
          UPDATE simpl_resources.datasources SET connector_id = NULL WHERE simpl_resources.datasources.id = datasource_record.id;
          RAISE NOTICE ''[Trigger] updated connector_id (id = %) on datasource (id = %)'', NEW.id, datasource_record.id;
        END IF;
	    END LOOP;
    END IF;
    RETURN NEW;
  END;' 
LANGUAGE plpgsql;

/* tries to find a data converter for a new inserted or updated connector, based on its properties_description:workflow data format and data types. */
CREATE OR REPLACE FUNCTION setConnectorDataConverter() RETURNS trigger AS '
  DECLARE
    matching_dataconverter_id INTEGER;
  BEGIN
    RAISE NOTICE ''[Trigger] set_connector_data_converter activated'';
    IF NEW.properties_description IS NOT NULL THEN 
      RAISE NOTICE ''[Trigger] input_datatype: %'', NEW.input_datatype;
      RAISE NOTICE ''[Trigger] output_datatype: %'', NEW.output_datatype;
      RAISE NOTICE ''[Trigger] dataFormat: %'', getconnectorXMLProperty(''dataFormat'', NEW.properties_description);
      SELECT INTO matching_dataconverter_id id 
        FROM simpl_resources.dataconverters 
        WHERE input_datatype = NEW.input_datatype 
          AND output_datatype = NEW.output_datatype 
          AND workflow_dataformat = getConnectorXMLProperty(''dataFormat'', NEW.properties_description)  
        LIMIT 1;
      IF matching_dataconverter_id IS NOT NULL THEN
        /* avoid a second update if this function is triggered by an UPDATE from updateConnectorDataConverters() */
        IF NEW.dataconverter_id IS NULL OR (NEW.dataconverter_id <> matching_dataconverter_id) THEN
          NEW.dataconverter_id := matching_dataconverter_id;
          RAISE NOTICE ''[Trigger] found matching dataconverter (id = %) for connector.'', matching_dataconverter_id;
        END IF;
      ELSE 
        IF matching_dataconverter_id IS NULL THEN
          NEW.dataconverter_id := NULL;
          RAISE NOTICE ''[Trigger] no matching dataconverter found.'';  
        END IF;
      END IF;
    END IF;  
    RETURN NEW;
  END;' 
LANGUAGE plpgsql;

/* tries to find a data converter for all connectors without an assigned data converter when a data converter is inserted or updated, based on the connectors properties_description */
CREATE OR REPLACE FUNCTION updateConnectorDataConverters() RETURNS trigger AS '
  DECLARE
    matching_dataconverter_id INTEGER;
    connector_record RECORD;
    dataconverter_input_datatype VARCHAR;
    dataconverter_output_datatype VARCHAR;
    dataconverter_workflow_dataformat VARCHAR;
    connector_input_datatype VARCHAR;
    connector_output_datatype VARCHAR;
    connector_workflow_dataformat VARCHAR;
  BEGIN
    RAISE NOTICE ''[Trigger] update_connector_data_converters activated'';
    RAISE NOTICE ''[Trigger] input_datatype: %'', NEW.input_datatype;
    RAISE NOTICE ''[Trigger] output_datatype: %'', NEW.output_datatype;
    RAISE NOTICE ''[Trigger] workflow_dataformat: %'', NEW.workflow_dataformat;
    IF NEW.input_datatype IS NOT NULL AND NEW.output_datatype IS NOT NULL AND NEW.workflow_dataformat IS NOT NULL THEN
      dataconverter_input_datatype := NEW.input_datatype;
      dataconverter_output_datatype := NEW.output_datatype;
      dataconverter_workflow_dataformat := NEW.workflow_dataformat;
      RAISE NOTICE ''[Trigger] new or updated dataconverter: %, %, %'',dataconverter_input_datatype, dataconverter_output_datatype, dataconverter_workflow_dataformat;
      FOR connector_record IN SELECT * FROM simpl_resources.connectors ORDER BY id LOOP
        connector_input_datatype := connector_record.input_datatype;
        connector_output_datatype := connector_record.output_datatype;
        connector_workflow_dataformat := getConnectorXMLProperty(''dataFormat'', connector_record.properties_description);
        IF dataconverter_input_datatype = connector_input_datatype 
          AND dataconverter_output_datatype = connector_output_datatype
          AND dataconverter_workflow_dataformat = connector_workflow_dataformat  
          AND (connector_record.dataconverter_id IS NULL OR (connector_record.dataconverter_id <> NEW.id)) THEN
          UPDATE simpl_resources.connectors SET dataconverter_id = NEW.id WHERE simpl_resources.connectors.id = connector_record.id;
          RAISE NOTICE ''[Trigger] updated dataconverter_id (id = %) on connector (id = %)'', NEW.id, connector_record.id;
        END IF;
        /* remove dataconverter from connector if the dataconverter changed and does not fit anymore */
        IF ((dataconverter_input_datatype <> connector_input_datatype 
          OR dataconverter_output_datatype = connector_output_datatype
          OR dataconverter_workflow_dataformat = connector_workflow_dataformat) 
          AND (connector_record.dataconverter_id = NEW.id)) THEN
          UPDATE simpl_resources.connectors SET dataconverter_id = NULL WHERE simpl_resources.connectors.id = connector_record.id;
          RAISE NOTICE ''[Trigger] updated dataconverter_id (id = %) on connector (id = %)'', NEW.id, connector_record.id;
        END IF;
      END LOOP;
    END IF;
    RETURN NEW;
  END;' 
LANGUAGE plpgsql;

CREATE TRIGGER set_data_source_connector BEFORE INSERT OR UPDATE ON simpl_resources.datasources FOR EACH ROW EXECUTE PROCEDURE setDataSourceConnector();

CREATE TRIGGER update_data_source_connectors AFTER INSERT OR UPDATE ON simpl_resources.connectors FOR EACH ROW EXECUTE PROCEDURE updateDataSourceConnectors();

CREATE TRIGGER set_connector_data_converter BEFORE INSERT OR UPDATE ON simpl_resources.connectors FOR EACH ROW EXECUTE PROCEDURE setConnectorDataConverter();

CREATE TRIGGER update_connector_data_converters AFTER INSERT OR UPDATE ON simpl_resources.dataconverters FOR EACH ROW EXECUTE PROCEDURE updateConnectorDataConverters();

/**
 * INSERT DATA
 */
INSERT INTO simpl_resources.dataconverters
(id, name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation, xml_schema)
VALUES
(1, 'RDBDataConverter', 'List<String>', 'RDBResult', 'RDBDataFormat', 'true', 'true', 'org.simpl.core.plugins.dataconverter.relational.RDBDataConverter', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsd:schema targetNamespace="http://org.simpl.core/plugins/dataconverter/dataformat/RelationalDataFormat"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
  xmlns="http://org.simpl.core/plugins/dataconverter/dataformat/RelationalDataFormat"
  xmlns:sdo="commonj.sdo" 
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <xsd:annotation>
    <xsd:documentation>Defines the SDO structure of relational data such as from databases or CSV files.</xsd:documentation>
  </xsd:annotation>
  <xsd:element name="RelationalDataFormat" type="tRelationalDataFormat"></xsd:element>
  <xsd:complexType name="tRelationalDataFormat">
    <xsd:sequence>
      <xsd:element name="dataFormatMetaData" type="tDataFormatMetaData"
        maxOccurs="1" minOccurs="0">
      </xsd:element>
      <xsd:element name="table" type="tTable" maxOccurs="unbounded"
        minOccurs="0">
      </xsd:element>
    </xsd:sequence>
    <xsd:attribute name="dataFormat" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tTable">
    <xsd:sequence>
      <xsd:element name="rdbTableMetaData" type="tRDBTableMetaData"
        maxOccurs="1" minOccurs="0">
      </xsd:element>
      <xsd:element name="csvTableMetaData" type="tCSVTableMetaData"
        maxOccurs="1" minOccurs="0"></xsd:element>
      <xsd:element name="row" type="tRow" maxOccurs="unbounded"
        minOccurs="0">
      </xsd:element>
    </xsd:sequence>
  </xsd:complexType>
  <xsd:complexType name="tColumn">
    <xsd:simpleContent>
      <xsd:extension base="xsd:string">
        <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
      </xsd:extension>
    </xsd:simpleContent>
  </xsd:complexType>
  <xsd:complexType name="tRow">
    <xsd:sequence>
      <xsd:element name="column" type="tColumn" maxOccurs="unbounded"
        minOccurs="1"></xsd:element>
    </xsd:sequence>
  </xsd:complexType>
  <xsd:complexType name="tCSVTableMetaData">
    <xsd:attribute name="filename" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="separator" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="quoteChar" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="escapeChar" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="strictQuotes" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tRDBTableMetaData">
    <xsd:sequence>
      <xsd:element name="columnType" type="tColumnType" maxOccurs="unbounded"
        minOccurs="0"></xsd:element>
    </xsd:sequence>
    <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="schema" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="catalog" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tColumnType">
    <xsd:simpleContent>
      <xsd:extension base="xsd:string">
        <xsd:attribute name="isPrimaryKey" type="xsd:boolean" use="optional">
        </xsd:attribute>
        <xsd:attribute name="columnName" type="xsd:string" use="required"></xsd:attribute>
      </xsd:extension>
    </xsd:simpleContent>
  </xsd:complexType>
  <xsd:complexType name="tDataFormatMetaData">
    <xsd:sequence>
      <xsd:element name="dataSource" type="xsd:string" maxOccurs="1"
        minOccurs="0"></xsd:element>
    </xsd:sequence>
  </xsd:complexType>
</xsd:schema>');

INSERT INTO simpl_resources.dataconverters
(id, name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation, xml_schema)
VALUES
(2, 'CSVDataConverter', 'File', 'RandomFiles', 'CSVDataFormat', 'true', 'true', 'org.simpl.core.plugins.dataconverter.relational.CSVDataConverter', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsd:schema targetNamespace="http://org.simpl.core/plugins/dataconverter/dataformat/RelationalDataFormat"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
  xmlns="http://org.simpl.core/plugins/dataconverter/dataformat/RelationalDataFormat"
  xmlns:sdo="commonj.sdo" 
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <xsd:annotation>
    <xsd:documentation>Defines the SDO structure of relational data such as from databases or CSV files.</xsd:documentation>
  </xsd:annotation>
  <xsd:element name="RelationalDataFormat" type="tRelationalDataFormat"></xsd:element>
  <xsd:complexType name="tRelationalDataFormat">
    <xsd:sequence>
      <xsd:element name="dataFormatMetaData" type="tDataFormatMetaData"
        maxOccurs="1" minOccurs="0">
      </xsd:element>
      <xsd:element name="table" type="tTable" maxOccurs="unbounded"
        minOccurs="0">
      </xsd:element>
    </xsd:sequence>
    <xsd:attribute name="dataFormat" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tTable">
    <xsd:sequence>
      <xsd:element name="rdbTableMetaData" type="tRDBTableMetaData"
        maxOccurs="1" minOccurs="0">
      </xsd:element>
      <xsd:element name="csvTableMetaData" type="tCSVTableMetaData"
        maxOccurs="1" minOccurs="0"></xsd:element>
      <xsd:element name="row" type="tRow" maxOccurs="unbounded"
        minOccurs="0">
      </xsd:element>
    </xsd:sequence>
  </xsd:complexType>
  <xsd:complexType name="tColumn">
    <xsd:simpleContent>
      <xsd:extension base="xsd:string">
        <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
      </xsd:extension>
    </xsd:simpleContent>
  </xsd:complexType>
  <xsd:complexType name="tRow">
    <xsd:sequence>
      <xsd:element name="column" type="tColumn" maxOccurs="unbounded"
        minOccurs="1"></xsd:element>
    </xsd:sequence>
  </xsd:complexType>
  <xsd:complexType name="tCSVTableMetaData">
    <xsd:attribute name="filename" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="separator" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="quoteChar" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="escapeChar" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="strictQuotes" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tRDBTableMetaData">
    <xsd:sequence>
      <xsd:element name="columnType" type="tColumnType" maxOccurs="unbounded"
        minOccurs="0"></xsd:element>
    </xsd:sequence>
    <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="schema" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="catalog" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tColumnType">
    <xsd:simpleContent>
      <xsd:extension base="xsd:string">
        <xsd:attribute name="isPrimaryKey" type="xsd:boolean" use="optional">
        </xsd:attribute>
        <xsd:attribute name="columnName" type="xsd:string" use="required"></xsd:attribute>
      </xsd:extension>
    </xsd:simpleContent>
  </xsd:complexType>
  <xsd:complexType name="tDataFormatMetaData">
    <xsd:sequence>
      <xsd:element name="dataSource" type="xsd:string" maxOccurs="1"
        minOccurs="0"></xsd:element>
    </xsd:sequence>
  </xsd:complexType>
</xsd:schema>');

INSERT INTO simpl_resources.dataconverters
(id, name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation, xml_schema)
VALUES
(3, 'RandomFilesDataConverter', 'File', 'RandomFiles', 'RandomFilesDataFormat', 'true', 'true', 'org.simpl.core.plugins.dataconverter.file.RandomFilesDataConverter', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsd:schema targetNamespace="http://org.simpl.core/plugins/dataconverter/dataformat/RandomFilesDataFormat"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
  xmlns="http://org.simpl.core/plugins/dataconverter/dataformat/RandomFilesDataFormat"
  xmlns:sdo="commonj.sdo" 
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <xsd:annotation>
    <xsd:documentation>Defines the SDO structure of data from files.</xsd:documentation>
  </xsd:annotation>
  <xsd:element name="RandomFilesDataFormat" type="tRandomFilesDataFormat"></xsd:element>
  <xsd:complexType name="tRandomFilesDataFormat">
    <xsd:sequence>
      <xsd:element name="file" type="tFile" maxOccurs="unbounded"
        minOccurs="0">
      </xsd:element>
    </xsd:sequence>
    <xsd:attribute name="dataFormat" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="folder" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tFile">
    <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
    <xsd:attribute name="content" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
</xsd:schema>');

/* Workaround: the getConnectorXMLProperty and thus the setConnectorDataConverter trigger does not work properly if not at least one connector exists. This is why one dummy connector is created before inserting the real connectors. */
INSERT INTO simpl_resources.connectors
(id, dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(2, 0, 'DUMMY', '', '', '', '<dummy></dummy>');

INSERT INTO simpl_resources.connectors
(id, dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(1, 1, 'DB2RDBConnector', 'List<String>', 'RDBResult', 'org.simpl.core.plugins.connector.rdb.DB2RDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>DB2</subType>
  <language>SQL</language>
  <dataFormat>RDBDataFormat</dataFormat>
</properties_description>');

/* Workaround: the DUMMY connector gets deleted after one connector is insert. */
DELETE FROM simpl_resources.connectors WHERE name = 'DUMMY';

INSERT INTO simpl_resources.connectors
(id, dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(2, 1, 'DerbyRDBConnector', 'List<String>', 'RDBResult', 'org.simpl.core.plugins.connector.rdb.DerbyRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>Derby</subType>
  <language>SQL</language>
  <dataFormat>RDBDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(id, dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(3, 1, 'EmbDerbyRDBConnector', 'List<String>', 'RDBResult', 'org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>EmbeddedDerby</subType>
  <language>SQL</language>
  <dataFormat>RDBDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(id, dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(4, 1, 'MySQLRDBConnector', 'List<String>', 'RDBResult', 'org.simpl.core.plugins.connector.rdb.MySQLRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>MySQL</subType>
  <language>SQL</language>
  <dataFormat>RDBDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(id, dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(5, 1, 'PostgreSQLRDBConnector', 'List<String>', 'RDBResult', 'org.simpl.core.plugins.connector.rdb.PostgreSQLRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>PostgreSQL</subType>
  <language>SQL</language>
  <dataFormat>RDBDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(id, dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(6, 3, 'WindowsLocalFSConnector', 'File', 'RandomFiles', 'org.simpl.core.plugins.connector.fs.WindowsLocalFSConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Filesystem</type>
  <subType>Windows Local</subType>
  <language>Shell</language>
  <dataFormat>RandomFilesDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(id, dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(7, 3, 'SSHConnector', 'File', 'RandomFiles', 'org.simpl.core.plugins.connector.ssh.SSHConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
	<type>Filesystem</type>
	<subType>SSH Server</subType>
	<language>Shell</language>
	<dataFormat>RandomFilesDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.datatransformationservices
(id, name, connector_dataformat, workflow_dataformat, direction_connector_workflow, direction_workflow_connector, implementation)
VALUES
(1, 'CSVToRDBDataTransformationService', 'CSVDataFormat', 'RDBDataFormat', 'true', 'true', 'org.simpl.data.transformation.services.CSVToRDBDataTransformationService');

INSERT INTO simpl_resources.datatransformationservices
(id, name, connector_dataformat, workflow_dataformat, direction_connector_workflow, direction_workflow_connector, implementation)
VALUES
(2, 'RandomFilesToRDBDataTransformationService', 'RandomFilesDataFormat', 'RDBDataFormat', 'true', 'true', 'org.simpl.data.transformation.services.RandomFilesToRDBDataTransformationService');

/* Workaround: the getDataSourceXMLProperty and thus the setDataSourceConnector trigger does not work properly if not at least one connector exists. This is why one dummy datasource is created before inserting the real datasources. */
INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
  'DUMMY'
  , ''
  , ''
  , ''
  , NULL
  , NULL
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
  'Windows Local'
  , ''
  , ''
  , ''
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Filesystem</type><subType>Windows Local</subType><language>Shell</language><dataFormat>RandomFilesDataFormat</dataFormat></connector_properties_description>'
);

/* Workaround: the DUMMY database gets deleted after one datasource is insert. */
DELETE FROM simpl_resources.datasources WHERE logical_name = 'DUMMY';

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
  'MySQL'
  , 'simpl'
  , 'simpl'
  , 'localhost/simpl'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>MySQL</subType><language>SQL</language><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
  'PostgresSQL'
  , 'postgres'
  , 'postgres'
  , 'localhost:5432/postgres'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>PostgreSQL</subType><language>SQL</language><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
);


INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
  'DB2 TESTDAT'
  , 'hiwi'
  , 'hiwi'
  , 'localhost:50000/TESTDAT'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>DB2</subType><language>SQL</language><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
  'SIMPL Embedded Derby'
  , ''
  , ''
  , 'C:\\Tomcat 6.0\\simplDB'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>EmbeddedDerby</subType><language>SQL</language><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
);

INSERT INTO simpl_definitions.languages
(name, statement_description)
VALUES
('SQL', '<statement_description xmlns="http://org.simpl.resource.management/languages/statement_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd "><statement name="Create"><predicate>^(?i)CREATE.*</predicate></statement><statement name="Insert"><predicate>^(?i)INSERT.*</predicate></statement><statement name="Update"><predicate>^(?i)UPDATE.*</predicate></statement><statement name="Delete"><predicate>^(?i)DELETE.*</predicate></statement><statement name="Drop"><predicate>^(?i)DROP.*</predicate></statement></statement_description>');

INSERT INTO simpl_definitions.languages
(name, statement_description)
VALUES
('Shell', '<statement_description xmlns="http://org.simpl.resource.management/languages/statement_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd "><statement name="Delete"><predicate>^(?i)del.*</predicate></statement></statement_description>');

INSERT INTO simpl_resources.strategyplugins
(id, name, implementation)
VALUES
(1, 'FIRST_FIND', 'org.simpl.resource.discovery.strategy.FirstFindStrategy');

INSERT INTO simpl_definitions.datacontainer_reference_types
(name, xsd_type)
VALUES
('DataContainerReferenceType', '<xsd:complexType name="DataContainerReferenceType">
</xsd:complexType>');

INSERT INTO simpl_definitions.datacontainer_reference_types
(name, xsd_type)
VALUES
('RelationalDatabaseDataContainerReferenceType', '<xsd:complexType name="RelationalDatabaseDataContainerReferenceType">
    <xsd:complexContent>
      <xsd:extension base="simpl:DataContainerReferenceType">
        <xsd:sequence>
          <xsd:element name="schema" type="xsd:string" maxOccurs="1"
            minOccurs="0">
          </xsd:element>
          <xsd:element name="table" type="xsd:string" maxOccurs="1"
            minOccurs="1">
          </xsd:element>
        </xsd:sequence>
        <xsd:attribute name="stringPattern" type="xsd:string" use="required" fixed="schema.table">
        </xsd:attribute>
      </xsd:extension>
    </xsd:complexContent>
  </xsd:complexType>');

INSERT INTO simpl_definitions.datasource_reference_types
(name, xsd_type)
VALUES
('DataSourceReferenceType', '<xsd:complexType name="DataSourceReferenceType">
    <xsd:sequence>
      <xsd:element name="name" type="xsd:string"></xsd:element>
      <xsd:element name="requirements" type="xsd:string"></xsd:element>
      <xsd:element name="strategy" type="xsd:string"></xsd:element>
    </xsd:sequence>
  </xsd:complexType>');

