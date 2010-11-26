CREATE TABLE datasourceconnectors (
   id SERIAL PRIMARY KEY,
   name varchar(255) NOT NULL,
   properties_description xml DEFAULT '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/datasourceconnectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasourceconnectors/properties_description datasourceconnectors.xsd ">
  <type></type>
  <subType></subType>
  <language></language>
  <dataFormat></dataFormat>
</properties_description>' NOT NULL,
   dataconverter_dataformat varchar(255)
);

CREATE TABLE datasources (
   id SERIAL PRIMARY KEY,
   datasourceconnector_id INTEGER,
   logical_name varchar(255) UNIQUE NOT NULL,
   security_username varchar(255),
   security_password varchar(255),
   interface_description varchar(255) NOT NULL,
   properties_description xml,
   datasourceconnector_properties_description xml DEFAULT '<datasourceconnector_properties_description xmlns="http://org.simpl.resource.management/datasources/datasourceconnector_properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasources/datasourceconnector_properties_description datasources.xsd ">
  <type></type>
  <subType></subType>
  <language></language>
  <dataFormat></dataFormat>
</datasourceconnector_properties_description>',
   FOREIGN KEY (datasourceconnector_id) references datasourceconnectors(id)
);

CREATE TABLE dataconverters (
   id SERIAL PRIMARY KEY,
   name varchar(255) UNIQUE NOT NULL,
   datasourceconnector_dataformat varchar(255) NOT NULL,
   workflow_dataformat varchar(255) NOT NULL,
   UNIQUE (datasourceconnector_dataformat, workflow_dataformat)
);

CREATE TABLE datasourceconnectors_dataconverters (
   datasourceconnector_id INTEGER NOT NULL,
   dataconverter_id INTEGER NOT NULL,
   FOREIGN KEY (datasourceconnector_id) references datasourceconnectors(id),
   FOREIGN KEY (dataconverter_id) references dataconverters(id),
   PRIMARY KEY (datasourceconnector_id, dataconverter_id)
);

CREATE TABLE datacontainers (
   id SERIAL PRIMARY KEY,
   datasource_id INTEGER,
   logical_name varchar(255) NOT NULL,
   local_identifier xml NOT NULL
);

INSERT INTO datasourceconnectors
(id, name, properties_description, dataconverter_dataformat)
VALUES
(1, 'org.simpl.core.plugins.datasource.rdb.DB2RDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/datasourceconnectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasourceconnectors/properties_description datasourceconnectors.xsd ">
  <type>Database</type>
  <subType>DB2</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat');

INSERT INTO datasourceconnectors
(id, name, properties_description, dataconverter_dataformat)
VALUES
(2, 'org.simpl.core.plugins.datasource.rdb.DerbyRDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/datasourceconnectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasourceconnectors/properties_description datasourceconnectors.xsd ">
  <type>Database</type>
  <subType>Derby</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat');

INSERT INTO datasourceconnectors
(id, name, properties_description, dataconverter_dataformat)
VALUES
(3, 'org.simpl.core.plugins.datasource.rdb.EmbDerbyRDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/datasourceconnectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasourceconnectors/properties_description datasourceconnectors.xsd ">
  <type>Database</type>
  <subType>EmbeddedDerby</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat');

INSERT INTO datasourceconnectors
(id, name, properties_description, dataconverter_dataformat)
VALUES
(4, 'org.simpl.core.plugins.datasource.rdb.MySQLRDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/datasourceconnectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasourceconnectors/properties_description datasourceconnectors.xsd ">
  <type>Database</type>
  <subType>MySQL</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat');

INSERT INTO datasourceconnectors
(id, name, properties_description, dataconverter_dataformat)
VALUES
(5, 'org.simpl.core.plugins.datasource.rdb.PostgreSQLRDBDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/datasourceconnectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasourceconnectors/properties_description datasourceconnectors.xsd ">
  <type>Database</type>
  <subType>PostgreSQL</subType>
  <language>SQL</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.RDBDataFormat</dataFormat>
</properties_description>', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat');

INSERT INTO datasourceconnectors
(id, name, properties_description, dataconverter_dataformat)
VALUES
(6, 'org.simpl.core.plugins.datasource.fs.WindowsLocalFSDataSourceService', '<?xml version="1.0" encoding="UTF-8"?>
<properties_description xmlns="http://org.simpl.resource.management/datasourceconnectors/properties_description" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://org.simpl.resource.management/datasourceconnectors/properties_description datasourceconnectors.xsd ">
  <type>Filesystem</type>
  <subType>Windows Local</subType>
  <language>Shell</language>
  <dataFormat>org.simpl.core.plugins.dataformat.relational.CSVDataFormat</dataFormat>
</properties_description>', 'org.simpl.core.plugins.dataformat.relational.CSVDataFormat');

INSERT INTO dataconverters
(id, name, datasourceconnector_dataformat, workflow_dataformat)
VALUES
(1, 'org.simpl.core.plugins.dataformat.converter.CSVtoRDBDataFormatConverter', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat', 'org.simpl.core.plugins.dataformat.relational.CSVDataFormat');

INSERT INTO datasourceconnectors_dataconverters
(datasourceconnector_id, dataconverter_id)
VALUES
(1, 1);

INSERT INTO datasourceconnectors_dataconverters
(datasourceconnector_id, dataconverter_id)
VALUES
(2, 1);

INSERT INTO datasourceconnectors_dataconverters
(datasourceconnector_id, dataconverter_id)
VALUES
(3, 1);

INSERT INTO datasourceconnectors_dataconverters
(datasourceconnector_id, dataconverter_id)
VALUES
(4, 1);

INSERT INTO datasourceconnectors_dataconverters
(datasourceconnector_id, dataconverter_id)
VALUES
(5, 1);

INSERT INTO datasourceconnectors_dataconverters
(datasourceconnector_id, dataconverter_id)
VALUES
(6, 1);