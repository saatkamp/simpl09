CREATE TABLE datasourceconnectors (
   id SERIAL PRIMARY KEY,
   name varchar(255) NOT NULL,
   properties_description xml NOT NULL,
   dataconverter_dataformat varchar(255)
);

CREATE TABLE datasources (
   id SERIAL PRIMARY KEY,
   datasourceconnector_id INTEGER,
   datacontainer_id INTEGER,
   logical_name varchar(255) UNIQUE NOT NULL,
   security_username varchar(255),
   security_password varchar(255),
   interface_description varchar(255) NOT NULL,
   "type" varchar(255),
   subtype varchar(255),
   "language" varchar(255),
   dataformat varchar(255),
   properties_description xml,
   datasourceconnector_properties_description xml,
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
   logical_name varchar(255) NOT NULL,
   local_identifier xml NOT NULL
);

INSERT INTO datasourceconnectors
(id, name, dataconverter_dataformat, properties_description)
VALUES
(1, 'org.simpl.core.plugins.datasource.rdb.DB2RDBDataSourceService', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat', '<emtpy/>');

INSERT INTO datasourceconnectors
(id, name, dataconverter_dataformat, properties_description)
VALUES
(2, 'org.simpl.core.plugins.datasource.rdb.DerbyRDBDataSourceService', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat', '<empty/>');

INSERT INTO datasourceconnectors
(id, name, dataconverter_dataformat, properties_description)
VALUES
(3, 'org.simpl.core.plugins.datasource.rdb.EmbDerbyRDBDataSourceService', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat', '<empty/>');

INSERT INTO datasourceconnectors
(id, name, dataconverter_dataformat, properties_description)
VALUES
(4, 'org.simpl.core.plugins.datasource.rdb.MySQLRDBDataSourceService', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat', '<empty/>');

INSERT INTO datasourceconnectors
(id, name, dataconverter_dataformat, properties_description)
VALUES
(5, 'org.simpl.core.plugins.datasource.rdb.PostgreSQLRDBDataSourceService', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat', '<empty/>');

INSERT INTO datasourceconnectors
(id, name, dataconverter_dataformat, properties_description)
VALUES
(6, 'org.simpl.core.plugins.datasource.fs.WindowsLocalFSDataSourceService', 'org.simpl.core.plugins.dataformat.relational.CSVDataFormat', '<empty/>');

INSERT INTO dataconverters
(id, name, datasourceconnector_dataformat, workflow_dataformat)
VALUES
(1, 'org.simpl.core.plugins.dataformat.converter.CSVtoRDBDataFormatConverter', 'org.simpl.core.plugins.dataformat.relational.CSVDataFormat', 'org.simpl.core.plugins.dataformat.relational.RDBDataFormat');

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