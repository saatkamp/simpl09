/**
 * CREATE TABLES
 */
CREATE TABLE dataformats (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
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
   name varchar(255) NOT NULL,
   properties_description xml DEFAULT '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type></type>
  <subType></subType>
  <language></language>
  <dataFormat></dataFormat>
</properties_description>' NOT NULL,
   FOREIGN KEY (converter_dataformat_id) references dataformats(id)
);

CREATE TABLE sources (
   id SERIAL PRIMARY KEY,
   connector_id INTEGER,
   logical_name varchar(255) UNIQUE NOT NULL,
   security_username varchar(255),
   security_password varchar(255),
   interface_description varchar(255) NOT NULL,
   properties_description xml,
   connector_properties_description xml DEFAULT '<connector_properties_description xmlns="http://org.simpl.resource.management/sources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/sources/connector_properties_description sources.xsd ">
  <type></type>
  <subType></subType>
  <language></language>
  <dataFormat></dataFormat>
</connector_properties_description>',
   FOREIGN KEY (connector_id) references connectors(id)
);

CREATE TABLE converters (
   id SERIAL PRIMARY KEY,
   connector_dataformat_id INTEGER NOT NULL,
   workflow_dataformat_id INTEGER NOT NULL,
   name varchar(255) UNIQUE NOT NULL,
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

/* retrieves the property (text) of a XML element from a xml field (xml) */
CREATE OR REPLACE FUNCTION getProperty(text, xml)
  RETURNS text AS 
  $$
    SELECT CAST ((xpath('//rmns:' || $1 || '/text()', $2, ARRAY[ARRAY['rmns','http://org.simpl.resource.management/sources/connector_properties_description']]))[1] AS text) FROM sources
  $$ 
LANGUAGE SQL;

/**
 * INSERT DATA
 */
INSERT INTO dataformats
(id, name, xml_schema)
VALUES
(1, 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
(id, name, xml_schema)
VALUES
(2, 'org.simpl.core.plugins.dataformat.relational.CSVDataFormat', '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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

INSERT INTO connectors
(id, name, properties_description, converter_dataformat_id)
VALUES
(1, 'org.simpl.core.plugins.datasource.rdb.DB2RDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>DB2</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 1);

INSERT INTO connectors
(id, name, properties_description, converter_dataformat_id)
VALUES
(2, 'org.simpl.core.plugins.datasource.rdb.DerbyRDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>Derby</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 1);

INSERT INTO connectors
(id, name, properties_description, converter_dataformat_id)
VALUES
(3, 'org.simpl.core.plugins.datasource.rdb.EmbDerbyRDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>EmbeddedDerby</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 1);

INSERT INTO connectors
(id, name, properties_description, converter_dataformat_id)
VALUES
(4, 'org.simpl.core.plugins.datasource.rdb.MySQLRDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>MySQL</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 1);

INSERT INTO connectors
(id, name, properties_description, converter_dataformat_id)
VALUES
(5, 'org.simpl.core.plugins.datasource.rdb.PostgreSQLRDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Database</type>
  <subType>PostgreSQL</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 1);

INSERT INTO connectors
(id, name, properties_description, converter_dataformat_id)
VALUES
(6, 'org.simpl.core.plugins.datasource.fs.WindowsLocalFSDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/connectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/connectors/properties_description connectors.xsd ">
  <type>Filesystem</type>
  <subType>Windows Local</subType>
  <language>Shell</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.CSVDataFormat</dataFormat>
</properties_description>', 2);

INSERT INTO converters
(id, name, connector_dataformat_id, workflow_dataformat_id)
VALUES
(1, 'org.simpl.core.plugins.dataformat.converter.CSVtoRDBDataFormatConverter', 1, 2);

INSERT INTO sources
(
    id
  , connector_id
  , logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
    5
  , 6
  , 'Windows Local'
  , NULL
  , NULL
  , 'C:\\'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/sources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/sources/connector_properties_description sources.xsd ">  <type>Filesystem</type>  <subType>Windows Local</subType>  <language>Shell</language>  <dataFormat>org.simpl.core.plugins.dataformat.relational.CSVDataFormat</dataFormat></connector_properties_description>'
);

INSERT INTO sources
(
    id
  , connector_id
  , logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
    2
  , 1
  , 'DB2'
  , ''
  , ''
  , 'localhost'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/sources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/sources/connector_properties_description sources.xsd "><type>Database</type><subType>DB2</subType><language>SQL</language><dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat></connector_properties_description>'
);

INSERT INTO sources
(
    id
  , connector_id
  , logical_name
  , security_username
  , security_password
  , interface_description
  , properties_description
  , connector_properties_description
)
VALUES
(
    4
  , 4
  , 'MySQL'
  , NULL
  , NULL
  , 'localhost'
  , NULL
  , '<connector_properties_description xmlns="http://org.simpl.resource.management/sources/connector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/sources/connector_properties_description sources.xsd ">  <type>Database</type>  <subType>MySQL</subType>  <language>SQL</language>  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat></connector_properties_description>'
);

INSERT INTO languages
(name, statement_description)
VALUES
('SQL', '<statement_description xmlns="http://org.simpl.resource.management/languages/statement_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd "><statement name="Create"><predicate>^(?i)CREATE.*</predicate></statement><statement name="Insert"><predicate>^(?i)INSERT.*</predicate></statement><statement name="Update"><predicate>^(?i)UPDATE.*</predicate></statement><statement name="Delete"><predicate>^(?i)DELETE.*</predicate></statement><statement name="Drop"><predicate>^(?i)DROP.*</predicate></statement></statement_description>');

INSERT INTO languages
(name, statement_description)
VALUES
('Shell', '<statement_description xmlns="http://org.simpl.resource.management/languages/statement_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/languages/statement_description languages.xsd "><statement name="Delete"><predicate>^(?i)del.*</predicate></statement></statement_description>');

