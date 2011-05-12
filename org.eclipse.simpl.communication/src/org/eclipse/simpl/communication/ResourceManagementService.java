package org.eclipse.simpl.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.data.DataSource;

/**
 * Resource Management web service access.
 * 
 * @author Michael Schneidt
 */
public class ResourceManagementService {
  HashMap<String, DataSource> dataSourcesMap = new HashMap<String, DataSource>();

  ResourceManagementService() {
    List<DataSource> dataSources = null;
    ResourceManagement resourceManagementService = this.getService();

    try {
      if (resourceManagementService != null) {
        dataSources = resourceManagementService.getAllDataSources().getDataSources();
      } else {
        MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
            "Resource Management is not available.", "Could not load resources.");
      }
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (dataSources != null) {
      for (DataSource dataSource : dataSources) {
        dataSourcesMap.put(dataSource.getName(), dataSource);
      }
    }
  }

  public DataSource findDataSourceById(String id) {
    DataSource result = null;
    
    for (DataSource dataSource : this.getDataSources()) {
      if (dataSource.getId().equals(id)) {
        result = dataSource;
      }
    }

    return result;
  }

  public DataSource findDataSourceByName(String name) {
    DataSource result = null;
    
    for (DataSource dataSource : this.getDataSources()) {
      if (dataSource.getName().equals(name)) {
        result = dataSource;
      }
    }

    return result;
  }

  public List<DataSource> getDataSources() {
    return new ArrayList<DataSource>(dataSourcesMap.values());
  }

  public List<String> getDataSourceNames() {
    return new ArrayList<String>(dataSourcesMap.keySet());
  }

  public List<String> getDataSourceLanguages(String subType) {
    List<String> dataSourceLanguages = new ArrayList<String>();

    try {
      dataSourceLanguages = this.getService().getDataSourceLanguages(subType).getItems();
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSourceLanguages;
  }

  public List<String> getDataSourceTypes() {
    List<String> dataSourceTypes = new ArrayList<String>();

    try {
      dataSourceTypes = this.getService().getDataSourceTypes().getItems();
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSourceTypes;
  }

  public List<String> getDataSourceSubTypes(String type) {
    List<String> dataSourceSubTypes = new ArrayList<String>();

    try {
      dataSourceSubTypes = this.getService().getDataSourceSubTypes(type).getItems();
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSourceSubTypes;
  }

  public List<String> getSupportedDataFormats(DataSource dataSource) {
    List<String> dataFormats = new ArrayList<String>();

    try {
      dataFormats = this.getService().getSupportedDataFormatTypes(dataSource).getItems();
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataFormats;
  }

  public List<String> getSupportedConvertDataFormats(DataSource dataSource) {
    List<String> dataFormats = new ArrayList<String>();

    try {
      dataFormats = this.getService().getSupportedConvertDataFormatTypes(dataSource)
          .getItems();
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataFormats;
  }

  public ResourceManagement getService() {
    String address = CommunicationPlugIn.getDefault().getPreferenceStore()
        .getString("SIMPL_RESOURCE_MANAGEMENT_ADDRESS");

    return ResourceManagementClient.getService(address);
  }
}