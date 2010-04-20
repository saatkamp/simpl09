package org.eclipse.simpl.uddi.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.simpl.uddi.juddiclient.UddiDataSource;
import org.eclipse.simpl.uddi.juddiclient.UddiDatasourceReader;
import org.eclipse.simpl.uddi.model.datasource.DataSource;
import org.eclipse.simpl.uddi.model.datasource.DatasourceFactory;

public class ModelProvider {
	private static ModelProvider content;
	private List<DataSource> datasources;
	

	private ModelProvider() {
		datasources = new ArrayList<DataSource>();
		// Image here some uddi access to read the Datasources and to
		// put them into the model
		DatasourceFactory factory = DatasourceFactory.eINSTANCE;

		
		ArrayList<UddiDataSource> dsList = UddiDatasourceReader.getInstance().getAllDarasources();
		
		for (UddiDataSource source: dsList) {
			DataSource ds = factory.createDataSource();
			
			ds.setName(source.getName());
			ds.setAddress(source.getAddress());
			ds.setType(source.getAttributeValue("type"));
			ds.setSubtype(source.getAttributeValue("subtype"));
			datasources.add(ds);
		}
	}

	public static synchronized ModelProvider getInstance() {
		if (content != null) {
			return content;
		}
		content = new ModelProvider();
		return content;
	}
	
	public DataSource find(String id){
		DataSource found = null;
		for (DataSource ds : datasources){
			if (ds.toString().equals(id)){
				found = ds;
				continue;
			}
		}
		return found;
	}

	public List<DataSource> getReferences() {
		return datasources;
	}
}