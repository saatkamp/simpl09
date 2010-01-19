package org.simpl.core.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class DatasourceServicePlugin implements DatasourceService {
  protected String datasourceServiceId = "DEFAULT";
  protected String datasourceType; // Database, Filesystem, ...
  protected List<String> datasourceSubtypes = new ArrayList<String>(); // DB2, MySQL, ...
  protected HashMap<String, List<String>> datasourceLanguages = new HashMap<String, List<String>>(); // SQL, XQuery, ...
  
  public String getDatasourceType() {
    return datasourceType;
  }

  public List<String> getDatasourceSubtypes() {
    return datasourceSubtypes;
  }

  public HashMap<String, List<String>> getDatasourceLanguages() {
    return datasourceLanguages;
  }
}