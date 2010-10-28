package org.eclipse.simpl.resource.framework.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.simpl.resource.framework.ResourceFrameworkPlugIn;
import org.simpl.core.webservices.client.DataSource;
import org.simpl.resource.framework.client.Exception_Exception;
import org.simpl.resource.framework.client.ResourceFrameworkClient;

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
	
	public DataSource findDataSourceByName(String name){
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
      datasources.addAll(ResourceFrameworkClient
          .getService(
              ResourceFrameworkPlugIn.getDefault().getPreferenceStore()
                  .getString("RESOURCE_FRAMEWORK_ADDRESS")).getAllDataSources()
          .getDataSources());
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
		
		for (DataSource source : datasources) {
			dsNames.add(source.getName());
		}
	}
}