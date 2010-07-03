package org.eclipse.simpl.uddi.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.simpl.communication.client.Authentication;
import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.simpl.uddi.UDDIPlugIn;
import org.simpl.uddi.client.UddiDataSource;
import org.simpl.uddi.client.UddiDataSourceReader;

public class ModelProvider {
	private static ModelProvider content;
	private List<DataSource> datasources;
	private List<String> dsNames = new ArrayList<String>();

	private ModelProvider() {
		datasources = new ArrayList<DataSource>();

		ArrayList<UddiDataSource> dsList = UddiDataSourceReader.getInstance(UDDIPlugIn.getDefault().getPreferenceStore().getString("UDDI_ADDRESS")).getAllDatasources();
		
		for (UddiDataSource source: dsList) {

			DataSource ds = new DataSource();

			ds.setName(source.getName());
			ds.setAddress(source.getAddress());
			ds.setType(source.getType());
			ds.setSubType(source.getSubtype());
			ds.setLanguage(source.getLanguage());
			ds.setDataFormat(source.getDataFormat());
			Authentication auth = new Authentication();
			auth.setUser(source.getUsername());
			auth.setPassword(source.getPassword());
			ds.setAuthentication(auth);

			datasources.add(ds);
			dsNames.add(source.getName());
		}
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
		datasources.clear();
		dsNames.clear();
		
		ArrayList<UddiDataSource> dsList = UddiDataSourceReader.getInstance(UDDIPlugIn.getDefault().getPreferenceStore().getString("UDDI_ADDRESS")).getAllDatasources();
		
		for (UddiDataSource source : dsList) {
			DataSource ds = new DataSource();

			ds.setName(source.getName());
			ds.setAddress(source.getAddress());
			ds.setType(source.getType());
			ds.setSubType(source.getSubtype());
			ds.setLanguage(source.getLanguage());
			ds.setDataFormat(source.getDataFormat());
			Authentication auth = new Authentication();
			auth.setUser(source.getUsername());
			auth.setPassword(source.getPassword());
			ds.setAuthentication(auth);

			datasources.add(ds);
			dsNames.add(source.getName());
		}
	}
}
