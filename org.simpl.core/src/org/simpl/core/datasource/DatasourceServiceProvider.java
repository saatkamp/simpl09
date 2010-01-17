package org.simpl.core.datasource;

import java.util.ArrayList;
import java.util.List;

public class DatasourceServiceProvider {
  public DatasourceService getInstance(String dsType) { // type = type + subtype (RDBDERBY)
    DatasourceService datasource = null;

    try {
      datasource = (DatasourceService) Class.forName(
          "org.simpl.core.datasource.plugins." + dsType.toLowerCase() + "." + dsType + "DatasourceService").newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return datasource;
  }

  public List<String> getDatasourceLanguages(String datasourceSubType) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<String> getDatasourceSubTypes(String datasourceType) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<String> getDatasourceTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  public ArrayList<String> getSupportedDatasourceTypes() {
    // TODO Auto-generated method stub
    return null;
  }

}
