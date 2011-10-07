package org.eclipse.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.simpl.communication.ResourceManagementCommunication;
import org.simpl.resource.management.data.DataSource;

/**
 * Provides the data for the view.
 * 
 * @author Michael Schneidt
 */
public class DataProvider {
  private static DataProvider content;
  private List<DataSource> datasources = new ArrayList<DataSource>();
  private List<String> dsNames = new ArrayList<String>();

  private DataProvider() {
    this.refresh();
  }

  public static synchronized DataProvider getInstance() {
    if (content != null) {
      return content;
    }

    content = new DataProvider();

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

    List<DataSource> dataSources = null;

    ResourceManagementCommunication.reload();    
    dataSources = ResourceManagementCommunication.getInstance().getDataSources();

    if (dataSources != null) {
      datasources.addAll(dataSources);
    }

    for (DataSource source : datasources) {
      dsNames.add(source.getName());
    }
  }
}