package org.simpl.core.datasource;

public abstract class DatasourceServicePlugin implements DatasourceService {
  String datasourceType; // Database, Filesystem, ...
  String datasourceSubtype; // DB2, MySQL
}