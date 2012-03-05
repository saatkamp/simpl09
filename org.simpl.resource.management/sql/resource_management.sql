/**
 * CREATE SCHEMAS
 */
CREATE SCHEMA simpl_resources;
CREATE SCHEMA simpl_definitions;

/**
 * CREATE TABLES
 */

CREATE TABLE simpl_definitions.datasource_reference_types (
   id SERIAL PRIMARY KEY,
   name varchar(255) NOT NULL,
   xsd_type xml NOT NULL
);

CREATE TABLE simpl_definitions.datacontainer_reference_types (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   xsd_type xml NOT NULL
);

CREATE TABLE simpl_definitions.workflow_dataformat_types (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   xsd_type xml NOT NULL
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

CREATE TABLE simpl_resources.dataconverters (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   input_datatype varchar(255) NOT NULL,
   output_datatype varchar(255) NOT NULL,
   workflow_dataformat varchar(255) NOT NULL,
   direction_output_workflow char(5) DEFAULT 'true',
   direction_workflow_input char(5) DEFAULT 'true',
   implementation varchar(255) UNIQUE NOT NULL,
   FOREIGN KEY (workflow_dataformat) references simpl_definitions.workflow_dataformat_types(name)
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
  <apiType></apiType>
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
  <apiType></apiType>
  <driverName></driverName>
  <addressPrefix></addressPrefix>
  <dataFormat></dataFormat>
</connector_properties_description>',
   datacontainer_reference_type varchar(255),
   FOREIGN KEY (connector_id) references simpl_resources.connectors(id),
   FOREIGN KEY (datacontainer_reference_type) references simpl_definitions.datacontainer_reference_types(name)
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

CREATE TABLE simpl_resources.datacontainers (
   id SERIAL PRIMARY KEY,
   datasource_id INTEGER,
   logical_name varchar(255) NOT NULL,
   local_identifier xml NOT NULL
);

CREATE TABLE simpl_resources.strategyplugins (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   implementation varchar(255) UNIQUE NOT NULL
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
      RAISE NOTICE ''[Trigger] apiType: %'', getDataSourceXMLProperty(''apiType'', NEW.connector_properties_description);
      SELECT INTO matching_connector_id id 
        FROM simpl_resources.connectors 
        WHERE getConnectorXMLProperty(''apiType'', simpl_resources.connectors.properties_description) = getDataSourceXMLProperty(''apiType'', NEW.connector_properties_description) 
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
    connector_apiType VARCHAR;
    datasource_apiType VARCHAR;
  BEGIN
    RAISE NOTICE ''[Trigger] update_data_source_connectors activated'';
    IF NEW.properties_description IS NOT NULL THEN
      connector_apiType := getConnectorXMLProperty(''apiType'', NEW.properties_description);
      RAISE NOTICE ''[Trigger] new or updated connector: %'', connector_apiType;
      FOR datasource_record IN SELECT * FROM simpl_resources.datasources ORDER BY id LOOP
        datasource_apiType := getDataSourceXMLProperty(''apiType'', datasource_record.connector_properties_description);
        IF connector_apiType = datasource_apiType 
          AND (datasource_record.connector_id IS NULL OR (datasource_record.connector_id <> NEW.id)) THEN
          UPDATE simpl_resources.datasources SET connector_id = NEW.id WHERE simpl_resources.datasources.id = datasource_record.id;
          RAISE NOTICE ''[Trigger] updated connector_id (id = %) on datasource (id = %)'', NEW.id, datasource_record.id;
        END IF;
        /* remove connector from data source if the connector changed and does not fit anymore */
        IF ((connector_apiType <> datasource_apiType)  
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

INSERT INTO simpl_definitions.datacontainer_reference_types
(name, xsd_type)
VALUES
('DataContainerReferenceType', '<xsd:complexType name="DataContainerReferenceType">
</xsd:complexType>');

INSERT INTO simpl_definitions.datacontainer_reference_types
(name, xsd_type)
VALUES
('LocalDataContainerReferenceType', '<xsd:complexType name="LocalDataContainerReferenceType">
  <xsd:complexContent>
    <xsd:extension base="simpl:DataContainerReferenceType">
      <xsd:sequence>
        <xsd:element name="dataSourceReferenceVariable" type="xsd:string"></xsd:element>
      </xsd:sequence>
    </xsd:extension>
  </xsd:complexContent>
</xsd:complexType>');

INSERT INTO simpl_definitions.datacontainer_reference_types
(name, xsd_type)
VALUES
('LogicalDataContainerReferenceType', '<xsd:complexType name="LogicalDataContainerReferenceType">
  <xsd:complexContent>
    <xsd:extension base="simpl:DataContainerReferenceType">
      <xsd:sequence>
        <xsd:element name="logicalIdentifier" type="xsd:string" maxOccurs="1"
          minOccurs="1">
        </xsd:element>
      </xsd:sequence>
      <xsd:attribute name="stringPattern" type="xsd:string"
        use="required" fixed="logicalIdentifier">
      </xsd:attribute>
    </xsd:extension>
  </xsd:complexContent>
</xsd:complexType>');

INSERT INTO simpl_definitions.datacontainer_reference_types
(name, xsd_type)
VALUES
('RelationalDatabaseDataContainerReferenceType', '<xsd:complexType name="RelationalDatabaseDataContainerReferenceType">
    <xsd:complexContent>
      <xsd:extension base="simpl:LocalDataContainerReferenceType">
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
  
INSERT INTO simpl_definitions.datacontainer_reference_types
(name, xsd_type)
VALUES
('XMLDatabaseDataContainerReferenceType', '<xsd:complexType name="XMLDatabaseDataContainerReferenceType">
    <xsd:complexContent>
      <xsd:extension base="simpl:LocalDataContainerReferenceType">
        <xsd:sequence>
          <xsd:element name="collectionName" type="xsd:string" maxOccurs="1"
            minOccurs="0">
          </xsd:element>
          <xsd:element name="documentName" type="xsd:string" maxOccurs="1"
            minOccurs="1">
          </xsd:element>
        </xsd:sequence>
        <xsd:attribute name="stringPattern" type="xsd:string" use="required" fixed="collectionName/documentName">
        </xsd:attribute>
      </xsd:extension>
    </xsd:complexContent>
  </xsd:complexType>');

INSERT INTO simpl_definitions.workflow_dataformat_types
(name, xsd_type)
VALUES
('DataFormat', '<xsd:complexType name="tDataFormat">
  <xsd:attribute name="dataFormat" type="xsd:string"></xsd:attribute>
</xsd:complexType>')

INSERT INTO simpl_definitions.workflow_dataformat_types
(name, xsd_type)
VALUES
('RDBDataFormat', '<xsd:complexType name="tRelationalDataFormat">
  <xsd:complexContent>
    <xsd:extension base="simpl:tDataFormat">
      <xsd:sequence>
        <xsd:element name="dataFormatMetaData" maxOccurs="1"
          minOccurs="0">
          <xsd:complexType>
            <xsd:sequence>
              <xsd:element name="dataSource" type="xsd:string"
                maxOccurs="1" minOccurs="0"></xsd:element>
            </xsd:sequence>
          </xsd:complexType>
        </xsd:element>
        <xsd:element name="table" maxOccurs="unbounded"
          minOccurs="0">
          <xsd:complexType>
            <xsd:sequence>
              <xsd:element name="rdbTableMetaData" maxOccurs="1"
                minOccurs="0">
                <xsd:complexType>
                  <xsd:sequence>
                    <xsd:element name="columnType" maxOccurs="unbounded"
                      minOccurs="0">
                      <xsd:complexType>
                        <xsd:simpleContent>
                          <xsd:extension base="xsd:string">
                            <xsd:attribute name="isPrimaryKey" type="xsd:boolean"
                              use="optional">
                            </xsd:attribute>
                            <xsd:attribute name="columnName" type="xsd:string"
                              use="required"></xsd:attribute>
                          </xsd:extension>
                        </xsd:simpleContent>
                      </xsd:complexType>
                    </xsd:element>
                  </xsd:sequence>
                  <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="schema" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="catalog" type="xsd:string"></xsd:attribute>
                </xsd:complexType>
              </xsd:element>
              <xsd:element name="csvTableMetaData" maxOccurs="1"
                minOccurs="0">
                <xsd:complexType>
                  <xsd:attribute name="filename" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="separator" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="quoteChar" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="escapeChar" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="strictQuotes" type="xsd:string"></xsd:attribute>
                </xsd:complexType>
              </xsd:element>
              <xsd:element name="row" maxOccurs="unbounded"
                minOccurs="0">
                <xsd:complexType>
                  <xsd:sequence>
                    <xsd:element name="column" maxOccurs="unbounded"
                      minOccurs="1">
                      <xsd:complexType>
                        <xsd:simpleContent>
                          <xsd:extension base="xsd:string">
                            <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
                          </xsd:extension>
                        </xsd:simpleContent>
                      </xsd:complexType>
                    </xsd:element>
                  </xsd:sequence>
                </xsd:complexType>
              </xsd:element>
            </xsd:sequence>
          </xsd:complexType>
        </xsd:element>
      </xsd:sequence>
    </xsd:extension>
  </xsd:complexContent>
</xsd:complexType>');

INSERT INTO simpl_definitions.workflow_dataformat_types
(name, xsd_type)
VALUES
('CSVDataFormat', '<xsd:complexType name="tRelationalDataFormat">
  <xsd:complexContent>
    <xsd:extension base="simpl:tDataFormat">
      <xsd:sequence>
        <xsd:element name="dataFormatMetaData" maxOccurs="1"
          minOccurs="0">
          <xsd:complexType>
            <xsd:sequence>
              <xsd:element name="dataSource" type="xsd:string"
                maxOccurs="1" minOccurs="0"></xsd:element>
            </xsd:sequence>
          </xsd:complexType>
        </xsd:element>
        <xsd:element name="table" maxOccurs="unbounded"
          minOccurs="0">
          <xsd:complexType>
            <xsd:sequence>
              <xsd:element name="rdbTableMetaData" maxOccurs="1"
                minOccurs="0">
                <xsd:complexType>
                  <xsd:sequence>
                    <xsd:element name="columnType" maxOccurs="unbounded"
                      minOccurs="0">
                      <xsd:complexType>
                        <xsd:simpleContent>
                          <xsd:extension base="xsd:string">
                            <xsd:attribute name="isPrimaryKey" type="xsd:boolean"
                              use="optional">
                            </xsd:attribute>
                            <xsd:attribute name="columnName" type="xsd:string"
                              use="required"></xsd:attribute>
                          </xsd:extension>
                        </xsd:simpleContent>
                      </xsd:complexType>
                    </xsd:element>
                  </xsd:sequence>
                  <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="schema" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="catalog" type="xsd:string"></xsd:attribute>
                </xsd:complexType>
              </xsd:element>
              <xsd:element name="csvTableMetaData" maxOccurs="1"
                minOccurs="0">
                <xsd:complexType>
                  <xsd:attribute name="filename" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="separator" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="quoteChar" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="escapeChar" type="xsd:string"></xsd:attribute>
                  <xsd:attribute name="strictQuotes" type="xsd:string"></xsd:attribute>
                </xsd:complexType>
              </xsd:element>
              <xsd:element name="row" maxOccurs="unbounded"
                minOccurs="0">
                <xsd:complexType>
                  <xsd:sequence>
                    <xsd:element name="column" maxOccurs="unbounded"
                      minOccurs="1">
                      <xsd:complexType>
                        <xsd:simpleContent>
                          <xsd:extension base="xsd:string">
                            <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
                          </xsd:extension>
                        </xsd:simpleContent>
                      </xsd:complexType>
                    </xsd:element>
                  </xsd:sequence>
                </xsd:complexType>
              </xsd:element>
            </xsd:sequence>
          </xsd:complexType>
        </xsd:element>
      </xsd:sequence>
    </xsd:extension>
  </xsd:complexContent>
</xsd:complexType>');

INSERT INTO simpl_definitions.workflow_dataformat_types
(name, xsd_type)
VALUES
('RandomFilesDataFormat', '<xsd:complexType name="tRandomFilesDataFormat">
  <xsd:complexContent>
    <xsd:extension base="simpl:tDataFormat">
      <xsd:sequence>
        <xsd:element name="file" maxOccurs="unbounded"
          minOccurs="0">
          <xsd:complexType>
            <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
            <xsd:attribute name="content" type="xsd:string"></xsd:attribute>
          </xsd:complexType>
        </xsd:element>
      </xsd:sequence>
      <xsd:attribute name="folder" type="xsd:string"></xsd:attribute>
    </xsd:extension>
  </xsd:complexContent>
</xsd:complexType>');

INSERT INTO simpl_definitions.workflow_dataformat_types
(name, xsd_type)
VALUES
('XMLDataFormat', '<xsd:complexType name="tXMLDataFormat">
    <xsd:complexContent>
      <xsd:extension base="simpl:tDataFormat">
        <xsd:sequence>
          <xsd:element name="dataFormatMetaData" maxOccurs="1"
            minOccurs="0">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="dataSource" type="xsd:string"
                  maxOccurs="1" minOccurs="0"></xsd:element>
              </xsd:sequence>
            </xsd:complexType>
          </xsd:element>
          <xsd:element name="documentHeader" type="xsd:string"
            minOccurs="0" />
          <xsd:element name="documentContent" type="xsd:anyType" />
        </xsd:sequence>
      </xsd:extension>
    </xsd:complexContent>
  </xsd:complexType>');

INSERT INTO simpl_definitions.workflow_dataformat_types
(name, xsd_type)
VALUES
('TinyDBDataFormat', '<xsd:complexType name="tTinyDBDataFormat">
  <xsd:complexContent>
    <xsd:extension base="simpl:tDataFormat">
      <xsd:sequence>
        <xsd:element name="dataFormatMetaData" maxOccurs="1"
          minOccurs="0">
          <xsd:complexType>
            <xsd:sequence>
              <xsd:element name="dataSource" type="xsd:string"
                maxOccurs="1" minOccurs="0"></xsd:element>
            </xsd:sequence>
          </xsd:complexType>
        </xsd:element>
        <xsd:element name="table" maxOccurs="unbounded"
          minOccurs="0">
          <xsd:complexType>
            <xsd:sequence>
              <xsd:element name="row" maxOccurs="unbounded"
                minOccurs="0">
                <xsd:complexType>
                  <xsd:sequence>
                    <xsd:element name="column" maxOccurs="unbounded"
                      minOccurs="1">
                      <xsd:complexType>
                        <xsd:simpleContent>
                          <xsd:extension base="xsd:string">
                            <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
                          </xsd:extension>
                        </xsd:simpleContent>
                      </xsd:complexType>
                    </xsd:element>
                  </xsd:sequence>
                </xsd:complexType>
              </xsd:element>
            </xsd:sequence>
          </xsd:complexType>
        </xsd:element>
      </xsd:sequence>
    </xsd:extension>
  </xsd:complexContent>
</xsd:complexType>');

INSERT INTO simpl_definitions.languages
(name, statement_description)
VALUES
('SQL', '<statement_description xmlns="http://org.simpl.resource.management/languages/statement_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd "><statement name="Create"><predicate>^(?i)CREATE.*</predicate></statement><statement name="Insert"><predicate>^(?i)INSERT.*</predicate></statement><statement name="Update"><predicate>^(?i)UPDATE.*</predicate></statement><statement name="Delete"><predicate>^(?i)DELETE.*</predicate></statement><statement name="Drop"><predicate>^(?i)DROP.*</predicate></statement></statement_description>');

INSERT INTO simpl_definitions.languages
(name, statement_description)
VALUES
('Shell', '<statement_description xmlns="http://org.simpl.resource.management/languages/statement_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd "><statement name="Delete"><predicate>^(?i)del.*</predicate></statement></statement_description>');


INSERT INTO simpl_resources.dataconverters
(name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation)
VALUES
('RDBDataConverter', 'List<String>', 'RDBResult', 'RDBDataFormat', 'true', 'true', 'org.simpl.core.plugins.dataconverter.relational.RDBDataConverter');

INSERT INTO simpl_resources.dataconverters
(name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation)
VALUES
('CSVDataConverter', 'File', 'RandomFiles', 'CSVDataFormat', 'true', 'true', 'org.simpl.core.plugins.dataconverter.relational.CSVDataConverter');

INSERT INTO simpl_resources.dataconverters
(name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation)
VALUES
('RandomFilesDataConverter', 'File', 'RandomFiles', 'RandomFilesDataFormat', 'true', 'true', 'org.simpl.core.plugins.dataconverter.file.RandomFilesDataConverter');

INSERT INTO simpl_resources.dataconverters
(name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation)
VALUES
('XMLDataConverter', 'File', 'XMLResult', 'XMLDataFormat', 'true', 'true', 'org.simpl.core.plugins.dataconverter.xml.XMLResultDataConverter');

INSERT INTO simpl_resources.dataconverters
(name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation)
VALUES
('TinyDBDataConverter', 'String', 'TinyDBResult', 'TinyDBDataFormat', 'false', 'true', 'org.simpl.core.plugins.dataconverter.sensor.network.TinyDBResultConverter');


/* Workaround: the getConnectorXMLProperty and thus the setConnectorDataConverter trigger does not work properly if not at least one connector exists. This is why one dummy connector is created before inserting the real connectors. */
INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(0, 'DUMMY', '', '', '', '<dummy></dummy>');

INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(1, 'RelationalJDBCbasedDatabaseConnector', 'List<String>', 'RDBResult', 'org.simpl.core.plugins.connector.rdb.RelationalJDBCbasedDatabaseConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>Relational</subType>
  <language>SQL</language>
  <apiType>JDBC</apiType>
  <dataFormat>RDBDataFormat</dataFormat>
</properties_description>');

/* Workaround: the DUMMY connector gets deleted after one connector is insert. */
DELETE FROM simpl_resources.connectors WHERE name = 'DUMMY';

INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(1, 'EmbDerbyRDBConnector', 'List<String>', 'RDBResult', 'org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>EmbeddedDerby</subType>
  <language>SQL</language>
  <apiType>JDBC_JDC</apiType>
  <dataFormat>RDBDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(3, 'WindowsLocalFSConnector', 'File', 'RandomFiles', 'org.simpl.core.plugins.connector.fs.WindowsLocalFSConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Filesystem</type>
  <subType>Windows Local</subType>
  <language>Shell</language>
  <apiType>CommandLine</apiType>
  <dataFormat>RandomFilesDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(3, 'SSHConnector', 'File', 'RandomFiles', 'org.simpl.core.plugins.connector.ssh.SSHConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Filesystem</type>
  <subType>SSH Server</subType>
  <language>Shell</language>
  <apiType>SSH</apiType>
  <dataFormat>RandomFilesDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(4, 'MonetDBXQueryConnector', 'File', 'XMLResult', 'org.simpl.core.plugins.connector.xml.MonetDBXQueryConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>MonetDBXQuery</subType>
  <language>XQuery</language>
  <apiType>JDBC_XML</apiType>
  <dataFormat>XMLDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(4, 'ExistDBConnector', 'File', 'XMLResult', 'org.simpl.core.plugins.connector.xml.ExistDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>ExistDB</subType>
  <language>XQuery</language>
  <apiType>XML:DB</apiType>
  <dataFormat>XMLDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(4, 'XTCConnector', 'File', 'XMLResult', 'org.simpl.core.plugins.connector.xml.XMLTransactionCoordinatorConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>XTC</subType>
  <language>XQuery</language>
  <apiType>XTC</apiType>
  <dataFormat>XMLDataFormat</dataFormat>
</properties_description>');

INSERT INTO simpl_resources.connectors
(dataconverter_id, name, input_datatype, output_datatype, implementation, properties_description)
VALUES
(5, 'TinyDBConnector', 'String', 'TinyDBResult', 'org.simpl.core.plugins.connector.sensor.network.TinyDBConnector', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>TinyDB</subType>
  <language>TinySQL</language>
  <apiType>TinyDB</apiType>
  <dataFormat>TinyDBDataFormat</dataFormat>
</properties_description>');

/* Workaround: the getDataSourceXMLProperty and thus the setDataSourceConnector trigger does not work properly if not at least one connector exists. This is why one dummy datasource is created before inserting the real datasources. */
INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'DUMMY'
  , ''
  , ''
  , ''
  , NULL
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
  , datacontainer_reference_type
)
VALUES
(
  'Windows Local'
  , ''
  , ''
  , ''
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Filesystem</type><subType>Windows Local</subType><language>Shell</language><apiType>CommandLine</apiType><driverName></driverName><addressPrefix></addressPrefix><dataFormat>RandomFilesDataFormat</dataFormat></connector_properties_description>'
  , NULL
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
  , datacontainer_reference_type
)
VALUES
(
  'MySQL'
  , 'simpl'
  , 'simpl'
  , 'localhost/simpl'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>Relational</subType><language>SQL</language><apiType>JDBC</apiType><driverName>com.mysql.jdbc.Driver</driverName><addressPrefix>jdbc:mysql://</addressPrefix><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
  , 'RelationalDatabaseDataContainerReferenceType'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'PostgresSQL'
  , 'postgres'
  , 'postgres'
  , 'localhost:5432/postgres'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>Relational</subType><language>SQL</language><apiType>JDBC</apiType><driverName>org.postgresql.Driver</driverName><addressPrefix>jdbc:postgresql://</addressPrefix><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
  , 'RelationalDatabaseDataContainerReferenceType'
);


INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'DB2 TESTDAT'
  , 'hiwi'
  , 'hiwi'
  , 'localhost:50000/TESTDAT'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>Relational</subType><language>SQL</language><apiType>JDBC</apiType><driverName>com.ibm.db2.jcc.DB2Driver</driverName><addressPrefix>jdbc:db2://</addressPrefix><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
  , 'RelationalDatabaseDataContainerReferenceType'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'SIMPL Embedded Derby'
  , ''
  , ''
  , 'C:\\Tomcat 6.0\\simplDB'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>Relational</subType><language>SQL</language><apiType>JDBC</apiType><driverName>org.apache.derby.jdbc.EmbeddedDriver</driverName><addressPrefix>jdbc:derby:</addressPrefix><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
  , 'RelationalDatabaseDataContainerReferenceType'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'MyDerby'
  , ''
  , ''
  , 'C:\\MyDerby\\testdb'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>Relational</subType><language>SQL</language><apiType>JDBC</apiType><driverName>org.apache.derby.jdbc.EmbeddedDriver</driverName><addressPrefix>jdbc:derby:</addressPrefix><dataFormat>RDBDataFormat</dataFormat></connector_properties_description>'
  , 'RelationalDatabaseDataContainerReferenceType'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'MonetDBXQuery'
  , 'monetdb'
  , 'monetdb'
  , 'localhost:50000/demo'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>MonetDBXQuery</subType><language>XQuery</language><apiType>JDBC_XML</apiType><driverName>nl.cwi.monetdb.jdbc.MonetDriver</driverName><addressPrefix>jdbc:monetdb://</addressPrefix><dataFormat>XMLDataFormat</dataFormat></connector_properties_description>'
  , 'XMLDatabaseDataContainerReferenceType'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'ExistDB'
  , 'admin'
  , 'admin'
  , 'localhost:5433/exist/xmlrpc/db'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>ExistDB</subType><language>XQuery</language><apiType>XML:DB</apiType><driverName>org.exist.xmldb.DatabaseImpl</driverName><addressPrefix>xmldb:exist://</addressPrefix><dataFormat>XMLDataFormat</dataFormat></connector_properties_description>'
  , 'XMLDatabaseDataContainerReferenceType'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'XTC'
  , 'xtc'
  , 'xtc'
  , 'localhost:24203'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>XTC</subType><language>XQuery</language><apiType>XTC</apiType><driverName></driverName><addressPrefix></addressPrefix><dataFormat>XMLDataFormat</dataFormat></connector_properties_description>'
  , 'XMLDatabaseDataContainerReferenceType'
);

INSERT INTO simpl_resources.datasources
(
  logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
  , datacontainer_reference_type
)
VALUES
(
  'TinyDB'
  , ''
  , ''
  , ''
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/datasources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd "><type>Database</type><subType>TinyDB</subType><language>TinySQL</language><apiType>TinyDB</apiType><driverName></driverName><addressPrefix></addressPrefix><dataFormat>TinyDBDataFormat</dataFormat></connector_properties_description>'
  , NULL
  );

INSERT INTO simpl_resources.datatransformationservices
(name, connector_dataformat, workflow_dataformat, direction_connector_workflow, direction_workflow_connector, implementation)
VALUES
('CSVToRDBDataTransformationService', 'CSVDataFormat', 'RDBDataFormat', 'true', 'true', 'org.simpl.data.transformation.services.CSVToRDBDataTransformationService');

INSERT INTO simpl_resources.datatransformationservices
(name, connector_dataformat, workflow_dataformat, direction_connector_workflow, direction_workflow_connector, implementation)
VALUES
('RandomFilesToRDBDataTransformationService', 'RandomFilesDataFormat', 'RDBDataFormat', 'true', 'true', 'org.simpl.data.transformation.services.RandomFilesToRDBDataTransformationService');

INSERT INTO simpl_resources.strategyplugins
(id, name, implementation)
VALUES
(1, 'FIRST_FIND', 'org.simpl.resource.discovery.strategy.FirstFindStrategy');



