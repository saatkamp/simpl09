/**
 * CREATE TABLES
 */
CREATE TABLE dataformats (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   implementation varchar(255) UNIQUE NOT NULL,
   xml_schema xml,
   UNIQUE (id)
);

CREATE TABLE languages (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   statement_description xml DEFAULT '<statement_description
  xmlns="http://org.simpl.resource.management/languages/statement_description"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd ">
  <statement name="">
    <predicate>predicate</predicate>
  </statement>
</statement_description>',
   UNIQUE (id)
);

CREATE TABLE connectors (
   id SERIAL PRIMARY KEY,
   converter_dataformat_id INTEGER,
   name varchar(255) UNIQUE NOT NULL,
   implementation varchar(255) UNIQUE NOT NULL,
   properties_description xml DEFAULT '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type></type>
  <subType></subType>
  <language></language>
  <dataFormatName></dataFormatName>
</properties_description>' NOT NULL,
   FOREIGN KEY (converter_dataformat_id) references dataformats(id)
);

CREATE TABLE datasources (
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
  <dataFormatName></dataFormatName>
</connector_properties_description>',
   FOREIGN KEY (connector_id) references connectors(id)
);

CREATE TABLE converters (
   id SERIAL PRIMARY KEY,
   connector_dataformat_id INTEGER NOT NULL,
   workflow_dataformat_id INTEGER NOT NULL,
   name varchar(255) UNIQUE NOT NULL,
   implementation varchar(255) UNIQUE NOT NULL,
   UNIQUE (connector_dataformat_id, workflow_dataformat_id),
   FOREIGN KEY (connector_dataformat_id) references dataformats(id),
   FOREIGN KEY (workflow_dataformat_id) references dataformats(id)
);

CREATE TABLE datacontainers (
   id SERIAL PRIMARY KEY,
   datasource_id INTEGER,
   logical_name varchar(255) NOT NULL,
   local_identifier xml NOT NULL
);


/**
 * CREATE FUNCTIONS
 */
/* retrieves the property (text) of a XML element from a datasources table xml field (xml) */
CREATE OR REPLACE FUNCTION getDataSourceXMLProperty(text, xml)
  RETURNS text AS 
  $$
    SELECT CAST ((xpath('//rmns:' || $1 || '/text()', $2, ARRAY[ARRAY['rmns','http://org.simpl.resource.management/datasources/connector_properties_description']]))[1] AS text) FROM datasources
  $$ 
LANGUAGE SQL;

/* retrieves the property (text) of a XML element from a connectors table xml field (xml) */
CREATE OR REPLACE FUNCTION getConnectorXMLProperty(text, xml)
  RETURNS text AS 
  $$
    SELECT CAST ((xpath('//rmns:' || $1 || '/text()', $2, ARRAY[ARRAY['rmns','http://org.simpl.resource.management/connectors/properties_description']]))[1] AS text) FROM connectors
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
	    SELECT INTO matching_connector_id id 
        FROM connectors 
        WHERE getConnectorXMLProperty(''type'', connectors.properties_description) = getDataSourceXMLProperty(''type'', NEW.connector_properties_description) 
          AND getConnectorXMLProperty(''subType'', connectors.properties_description) = getDataSourceXMLProperty(''subType'', NEW.connector_properties_description) 
          AND getConnectorXMLProperty(''language'', connectors.properties_description) = getDataSourceXMLProperty(''language'', NEW.connector_properties_description) 
          AND getConnectorXMLProperty(''dataFormatName'', connectors.properties_description) = getDataSourceXMLProperty(''dataFormatName'', NEW.connector_properties_description) 
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

/* tries to find a connector for all data sources without an assigned connector when a new connector is inserted, based on their connector_properties_description */
CREATE OR REPLACE FUNCTION updateDataSourceConnectors() RETURNS trigger AS '
  DECLARE
    matching_connector_id INTEGER;
    datasource_record RECORD;
    connector_type VARCHAR;
    connector_sub_type VARCHAR;
    connector_language VARCHAR;
    connector_data_format_name VARCHAR;
    datasource_type VARCHAR;
    datasource_sub_type VARCHAR;
    datasource_language VARCHAR;
    datasource_data_format_name VARCHAR;
  BEGIN
    RAISE NOTICE ''[Trigger] update_data_source_connectors activated'';
    IF NEW.properties_description IS NOT NULL THEN
	    connector_type := getConnectorXMLProperty(''type'', NEW.properties_description);
		  connector_sub_type := getConnectorXMLProperty(''subType'', NEW.properties_description);
		  connector_language := getConnectorXMLProperty(''language'', NEW.properties_description);
		  connector_data_format_name := getConnectorXMLProperty(''dataFormatName'', NEW.properties_description);
      RAISE NOTICE ''[Trigger] new or updated connector: %, %, %, %'',connector_type, connector_sub_type, connector_language, connector_data_format_name;
	    FOR datasource_record IN SELECT * FROM datasources ORDER BY id LOOP
	      datasource_type := getDataSourceXMLProperty(''type'', datasource_record.connector_properties_description);
	      datasource_sub_type := getDataSourceXMLProperty(''subType'', datasource_record.connector_properties_description);
	      datasource_language := getDataSourceXMLProperty(''language'', datasource_record.connector_properties_description);
        datasource_data_format_name := getDataSourceXMLProperty(''dataFormatName'', datasource_record.connector_properties_description);
	      IF connector_type = datasource_type 
          AND connector_sub_type = datasource_sub_type 
          AND connector_language = datasource_language 
          AND connector_data_format_name = datasource_data_format_name 
          AND (datasource_record.connector_id IS NULL OR (datasource_record.connector_id <> NEW.id)) THEN
          UPDATE datasources SET connector_id = NEW.id WHERE datasources.id = datasource_record.id;
	        RAISE NOTICE ''[Trigger] updated connector_id (id = %) on datasource (id = %)'', NEW.id, datasource_record.id;
	      END IF;
        /* remove connector from data source if the connector changed and does not fit anymore*/
        IF ((connector_type <> datasource_type 
	        OR connector_sub_type <> datasource_sub_type 
	        OR connector_language <> datasource_language 
	        OR connector_data_format_name <> datasource_data_format_name) 
	        AND (datasource_record.connector_id = NEW.id)) THEN
          UPDATE datasources SET connector_id = NULL WHERE datasources.id = datasource_record.id;
          RAISE NOTICE ''[Trigger] updated connector_id (id = %) on datasource (id = %)'', NEW.id, datasource_record.id;
        END IF;
	    END LOOP;
    END IF;
    RETURN NEW;
  END;' 
LANGUAGE plpgsql;

CREATE TRIGGER set_data_source_connector BEFORE INSERT OR UPDATE ON datasources FOR EACH ROW EXECUTE PROCEDURE setDataSourceConnector();

CREATE TRIGGER update_data_source_connectors AFTER INSERT OR UPDATE ON connectors FOR EACH ROW EXECUTE PROCEDURE updateDataSourceConnectors();

/**
 * INSERT DATA
 */
INSERT INTO dataformats
(id, name, implementation, xml_schema)
VALUES
(1, 'RDBDataFormat', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsd:schema targetNamespace="http://org.simpl.core/plugins/dataformat/RelationalDataFormat"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:das="http:///org.apache.tuscany.das.rdb/das"
  xmlns="http://org.simpl.core/plugins/dataformat/RelationalDataFormat"
  xmlns:sdo="commonj.sdo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <xsd:annotation>
    <xsd:documentation>Defines the SDO structure of relational data such as from databases or CSV files.</xsd:documentation>
  </xsd:annotation>
  <xsd:element name="RelationalDataFormat" type="tRelationalDataFormat"></xsd:element>
  <xsd:complexType name="tRelationalDataFormat">
    <xsd:sequence>
            <xsd:element name="dataFormatMetaData" type="tDataFormatMetaData" maxOccurs="1" minOccurs="0">
      </xsd:element>
            <xsd:element name="table" type="tTable" maxOccurs="unbounded"
        minOccurs="0">
      </xsd:element>
    </xsd:sequence>
    <xsd:attribute name="dataFormatType" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tTable">
    <xsd:sequence>
      <xsd:element name="rdbTableMetaData" type="tRDBTableMetaData"
        maxOccurs="1" minOccurs="0">
      </xsd:element>
            <xsd:element name="csvTableMetaData" type="tCSVTableMetaData" maxOccurs="1" minOccurs="0"></xsd:element>
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
        <xsd:element name="columnType" type="tColumnType" maxOccurs="unbounded" minOccurs="0"></xsd:element>
      </xsd:sequence>
      <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
        <xsd:attribute name="schema" type="xsd:string"></xsd:attribute>
        <xsd:attribute name="catalog" type="xsd:string"></xsd:attribute>
    </xsd:complexType>
  <xsd:complexType name="tColumnType">
    <xsd:simpleContent>
      <xsd:extension base="xsd:string">
        <xsd:attribute name="isPrimaryKey" type="xsd:boolean"
          use="optional">
        </xsd:attribute>
        <xsd:attribute name="columnName" type="xsd:string" use="required"></xsd:attribute>
      </xsd:extension>
    </xsd:simpleContent>
  </xsd:complexType>
  <xsd:complexType name="tDataFormatMetaData">
      <xsd:sequence>
        <xsd:element name="dataSource" type="xsd:string" maxOccurs="1" minOccurs="0"></xsd:element>
      </xsd:sequence>
    </xsd:complexType>
</xsd:schema>');

INSERT INTO dataformats
(id, name, implementation, xml_schema)
VALUES
(2, 'CSVDataFormat', 'org.simpl.core.plugins.dataformat.relational.CSVDataFormat', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsd:schema targetNamespace="http://org.simpl.core/plugins/dataformat/RelationalDataFormat"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:das="http:///org.apache.tuscany.das.rdb/das"
  xmlns="http://org.simpl.core/plugins/dataformat/RelationalDataFormat"
  xmlns:sdo="commonj.sdo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <xsd:annotation>
    <xsd:documentation>Defines the SDO structure of relational data such as from databases or CSV files.</xsd:documentation>
  </xsd:annotation>
  <xsd:element name="RelationalDataFormat" type="tRelationalDataFormat"></xsd:element>
  <xsd:complexType name="tRelationalDataFormat">
    <xsd:sequence>
            <xsd:element name="dataFormatMetaData" type="tDataFormatMetaData" maxOccurs="1" minOccurs="0">
      </xsd:element>
            <xsd:element name="table" type="tTable" maxOccurs="unbounded"
        minOccurs="0">
      </xsd:element>
    </xsd:sequence>
    <xsd:attribute name="dataFormatType" type="xsd:string"></xsd:attribute>
  </xsd:complexType>
  <xsd:complexType name="tTable">
    <xsd:sequence>
      <xsd:element name="rdbTableMetaData" type="tRDBTableMetaData"
        maxOccurs="1" minOccurs="0">
      </xsd:element>
            <xsd:element name="csvTableMetaData" type="tCSVTableMetaData" maxOccurs="1" minOccurs="0"></xsd:element>
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
        <xsd:element name="columnType" type="tColumnType" maxOccurs="unbounded" minOccurs="0"></xsd:element>
      </xsd:sequence>
      <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
        <xsd:attribute name="schema" type="xsd:string"></xsd:attribute>
        <xsd:attribute name="catalog" type="xsd:string"></xsd:attribute>
    </xsd:complexType>
  <xsd:complexType name="tColumnType">
    <xsd:simpleContent>
      <xsd:extension base="xsd:string">
        <xsd:attribute name="isPrimaryKey" type="xsd:boolean"
          use="optional">
        </xsd:attribute>
        <xsd:attribute name="columnName" type="xsd:string" use="required"></xsd:attribute>
      </xsd:extension>
    </xsd:simpleContent>
  </xsd:complexType>
  <xsd:complexType name="tDataFormatMetaData">
      <xsd:sequence>
        <xsd:element name="dataSource" type="xsd:string" maxOccurs="1" minOccurs="0"></xsd:element>
      </xsd:sequence>
    </xsd:complexType>
</xsd:schema>');

INSERT INTO dataformats
(id, name, implementation, xml_schema)
VALUES
(3, 'RandomFileDataFormat', 'org.simpl.core.plugins.dataformat.file.RandomFileDataFormat', '<?xml version="1.0" standalone="yes"?>
<xsd:schema targetNamespace="http://org.simpl.core/plugins/dataformat/file/RandomFileDataFormat" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://org.simpl.core/plugins/dataformat/file/RandomFileDataFormat" xmlns:Q1="http://org.simpl.core/services/datasource/metadata/DataSourceMetaData">
    <xsd:annotation>
      <xsd:documentation></xsd:documentation></xsd:annotation>
    <xsd:complexType name="tRandomFileDataFormat">
      <xsd:sequence>
        <xsd:element name="file" type="tFile" maxOccurs="unbounded"
          minOccurs="0">
        </xsd:element>
      </xsd:sequence>
      <xsd:attribute name="dataFormatType" type="xsd:string"></xsd:attribute>
        <xsd:attribute name="folder" type="xsd:string"></xsd:attribute>
    </xsd:complexType>
    <xsd:element name="RandomFileDataFormat" type="tRandomFileDataFormat"></xsd:element>
    <xsd:complexType name="tFile">
      <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
      <xsd:attribute name="content" type="xsd:string"></xsd:attribute>
    </xsd:complexType>
</xsd:schema>');

INSERT INTO connectors
(id, name, implementation, properties_description, converter_dataformat_id)
VALUES
(1, 'DB2RDBConnector', 'org.simpl.core.plugins.connector.rdb.DB2RDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>DB2</subType>
  <language>SQL</language>
  <dataFormatName>RDBDataFormat</dataFormatName>
</properties_description>', 1);

INSERT INTO connectors
(id, name, implementation, properties_description, converter_dataformat_id)
VALUES
(2, 'DerbyRDBConnector', 'org.simpl.core.plugins.connector.rdb.DerbyRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>Derby</subType>
  <language>SQL</language>
  <dataFormatName>RDBDataFormat</dataFormatName>
</properties_description>', 1);

INSERT INTO connectors
(id, name, implementation, properties_description, converter_dataformat_id)
VALUES
(3, 'EmbDerbyRDBConnector', 'org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>EmbeddedDerby</subType>
  <language>SQL</language>
  <dataFormatName>RDBDataFormat</dataFormatName>
</properties_description>', 1);

INSERT INTO connectors
(id, name, implementation, properties_description, converter_dataformat_id)
VALUES
(4, 'MySQLRDBConnector', 'org.simpl.core.plugins.connector.rdb.MySQLRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>MySQL</subType>
  <language>SQL</language>
  <dataFormatName>RDBDataFormat</dataFormatName>
</properties_description>', 1);

INSERT INTO connectors
(id, name, implementation, properties_description, converter_dataformat_id)
VALUES
(5, 'PostgreSQLRDBConnector', 'org.simpl.core.plugins.connector.rdb.PostgreSQLRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>PostgreSQL</subType>
  <language>SQL</language>
  <dataFormatName>RDBDataFormat</dataFormatName>
</properties_description>', 1);

INSERT INTO connectors
(id, name, implementation, properties_description, converter_dataformat_id)
VALUES
(6, 'WindowsLocalFSConnector', 'org.simpl.core.plugins.connector.fs.WindowsLocalFSConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Filesystem</type>
  <subType>Windows Local</subType>
  <language>Shell</language>
  <dataFormatName>RandomFileDataFormat</dataFormatName>
</properties_description>', 3);

INSERT INTO converters
(id, name, implementation, connector_dataformat_id, workflow_dataformat_id)
VALUES
(1, 'CSVtoRDBConverter', 'org.simpl.core.plugins.converter.CSVtoRDBConverter', 1, 2);

INSERT INTO converters
(id, name, implementation, connector_dataformat_id, workflow_dataformat_id)
VALUES
(2, 'RandomFiletoRDBConverter', 'org.simpl.core.plugins.converter.RandomFiletoRDBConverter', 3, 1);


INSERT INTO datasources
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
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Filesystem</type><subType>Windows Local</subType><language>Shell</language><dataFormatName>RandomFileDataFormat</dataFormatName></connector_properties_description>'
);

INSERT INTO datasources
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
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>MySQL</subType><language>SQL</language><dataFormatName>RDBDataFormat</dataFormatName></connector_properties_description>'
);

INSERT INTO datasources
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
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>DB2</subType><language>SQL</language><dataFormatName>RDBDataFormat</dataFormatName></connector_properties_description>'
);

INSERT INTO datasources
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
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>EmbeddedDerby</subType><language>SQL</language><dataFormatName>RDBDataFormat</dataFormatName></connector_properties_description>'
);

INSERT INTO languages
(name, statement_description)
VALUES
('SQL', '<statement_description xmlns="http://org.simpl.resource.management/languages/statement_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd "><statement name="Create"><predicate>^(?i)CREATE.*</predicate></statement><statement name="Insert"><predicate>^(?i)INSERT.*</predicate></statement><statement name="Update"><predicate>^(?i)UPDATE.*</predicate></statement><statement name="Delete"><predicate>^(?i)DELETE.*</predicate></statement><statement name="Drop"><predicate>^(?i)DROP.*</predicate></statement></statement_description>');

INSERT INTO languages
(name, statement_description)
VALUES
('Shell', '<statement_description xmlns="http://org.simpl.resource.management/languages/statement_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd "><statement name="Delete"><predicate>^(?i)del.*</predicate></statement></statement_description>');

