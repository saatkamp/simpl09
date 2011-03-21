package org.eclipse.simpl.communication;

import java.util.ArrayList;
import java.util.List;

import org.simpl.resource.management.client.DataSource;
import org.simpl.resource.management.client.DataSourceList;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;

/**
 * Resource Management web service access.
 * 
 * @author Michael Schneidt
 */
public class ResourceManagementService {
  ResourceManagement service = null;

  public DataSource findDataSourceById(String id) {
    DataSource dataSource = null;

    try {
      if (this.getService() != null) {
        dataSource = this.getService().getDataSourceById(Integer.parseInt(id));
      }
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSource;
  }

  public DataSource findDataSourceByName(String name) {
    DataSource dataSource = null;

    try {
      if (this.getService() != null) {
        dataSource = this.getService().getDataSourceByName(name);
      }
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSource;
  }

  public List<DataSource> getDataSources() {
    List<DataSource> dataSources = null;
    DataSourceList dataSourceList = null;

    try {
      if (this.getService() != null) {
        dataSourceList = this.getService().getAllDataSources();
        dataSources = dataSourceList.getDataSource();
      }
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSources;
  }

  public List<String> getDataSourceNames() {
    DataSourceList dataSources = null;
    List<String> dataSourceNames = new ArrayList<String>();

    try {
      if (this.getService() != null) {
        dataSources = this.getService().getAllDataSources();
      }

      for (DataSource source : dataSources.getDataSource()) {
        dataSourceNames.add(source.getName());
      }
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSourceNames;
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
      dataFormats = this.getService().getSupportedDataFormatTypes(dataSource)
          .getItems();
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
    service = ResourceManagementClient.getService(SIMPLCoreCommunication.getInstance()
        .getResourceManagementAddress());

    return service;
  }
}