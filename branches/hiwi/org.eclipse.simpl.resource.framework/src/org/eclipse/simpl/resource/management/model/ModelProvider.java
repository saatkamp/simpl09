package org.eclipse.simpl.resource.management.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.simpl.resource.management.ResourceManagementPlugIn;
import org.simpl.core.webservices.client.DataSource;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;

public class ModelProvider {
  private static ModelProvider content;
  private List<DataSource> datasources = new ArrayList<DataSource>();
  private List<String> dsNames = new ArrayList<String>();

  private ModelProvider() {
    this.refresh();
  }

  public static synchronized ModelProvider getInstance() {
    if (content != null) {
      return content;
    }

    content = new ModelProvider();

    return content;
  }

  public DataSource find(String id) {
    DataSource found = null;

    for (DataSource ds : datasources) {
      if (ds.toString().equals(id)) {
        found = ds;
        continue;
      }
    }

    return found;
  }

  public DataSource findDataSourceByName(String name) {
    boolean found = false;

    Iterator<DataSource> iterator = this.datasources.iterator();
    DataSource data = null;

    while (!found && iterator.hasNext()) {
      data = iterator.next();

      if (data.getName().equals(name)) {
        found = true;
      } else {
        data = null;
      }
    }

    return data;
  }

  public List<DataSource> getDataSources() {
    return datasources;
  }

  public List<String> getDataSourceNames() {
    return dsNames;
  }

  public void refresh() {
    dsNames.clear();
    datasources.clear();

    try {
      ResourceManagement resourceManagementService = ResourceManagementClient
          .getService(ResourceManagementPlugIn.getDefault().getPreferenceStore()
              .getString("RESOURCE_MANAGEMENT_ADDRESS"));
      List<DataSource> dataSources = null;

      if (resourceManagementService != null) {
        dataSources = resourceManagementService.getAllDataSources().getDataSources();

        if (dataSources != null) {
          datasources.addAll(dataSources);
        }
      }
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (DataSource source : datasources) {
      dsNames.add(source.getName());
    }
  }
}