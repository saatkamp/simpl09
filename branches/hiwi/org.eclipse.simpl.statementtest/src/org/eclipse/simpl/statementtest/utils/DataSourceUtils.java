package org.eclipse.simpl.statementtest.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.simpl.communication.ResourceManagementCommunication;
import org.eclipse.simpl.statementtest.types.DataSourceTypes;
import org.simpl.resource.management.data.DataSource;
import org.eclipse.bpel.model.Process;

/**
 * <b>Purpose:</b>Util functions for data source retrieval.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: DataSourceUtils.java 51 2010-06-29 18:21:35Z hiwi $ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class DataSourceUtils {
  private static final String RESOURCE_MANAGEMENT_PREFIX = "rm";


  @SuppressWarnings("unused")
  private static List<String> getRMDatasourceNames() {
    List<String> dataSourceNames = new ArrayList<String>();

    List<DataSource> dataSources = ResourceManagementCommunication.getInstance().getDataSources();

    if (dataSources != null) {
      for (DataSource dat : dataSources) {
        dataSourceNames.add(DataSourceUtils.RESOURCE_MANAGEMENT_PREFIX + ":"
            + dat.getName());
      }
    }
    
    return dataSourceNames;
  }

  public static String[] getAllDataSourceNames(Process process) {
    List<String> datasources = new ArrayList<String>();

    // TODO: Uncommented because rm: identifiers are not supported anymore
    //datasources.addAll(getRMDatasourceNames());
    datasources.addAll(VariableUtils.getDescriptorVariablesFromProcess(process));
    
    return datasources.toArray(new String[0]);
  }

  public static DataSource findDataSourceByName(Process process, String nameWithPrefix) {
    DataSource data = null;

    String[] name = nameWithPrefix.split(":");

    if (name[0].equals(DataSourceUtils.RESOURCE_MANAGEMENT_PREFIX)) {
      data = ResourceManagementCommunication.getInstance().findDataSourceByName(name[1]);
    } else {
      String dataSourceName = VariableUtils.getDescriptorElementValue(process, nameWithPrefix, "name");
      data = ResourceManagementCommunication.getInstance().findDataSourceByName(dataSourceName);
    }
    
    return data;
  }
  
  public static String[] filterDataSourceNamesByType(Process process, String[] dataSourceNames, String type) {
    List<String> filteredDataSources = new ArrayList<String>();
    
    for (int i = 0; i < dataSourceNames.length; i++) {
      DataSource dataSource = DataSourceUtils.findDataSourceByName(process, dataSourceNames[i]);
      String dataSourceType = "";      
      
      if (dataSource != null) {
       dataSourceType = dataSource.getType();
      }
      // filter data sources of the same type as the data source set in the
      // activity
      if (dataSourceType.equals(type)) {
        filteredDataSources.add(dataSourceNames[i]);
        // if no data source is selected in the activity
      } else if (type.equals("")
          && (dataSourceType.equals(DataSourceTypes.DATABASE) || dataSourceType
              .equals(DataSourceTypes.FILESYSTEM))) {
        filteredDataSources.add(dataSourceNames[i]);
      }
    }

    return filteredDataSources.toArray(new String[filteredDataSources.size()]);
  }
  
  public static void refresh() {
    ResourceManagementCommunication.reload();
  }
}