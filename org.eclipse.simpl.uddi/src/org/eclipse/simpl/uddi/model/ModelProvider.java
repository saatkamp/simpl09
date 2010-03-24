package org.eclipse.simpl.uddi.model;

import java.util.ArrayList;
import java.util.List;

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

		DataSource ds1 = factory.createDataSource();
		ds1.setName("myDB");
		ds1.setAddress("http://localhost:3306/myDB");
		ds1.setType("database");
		ds1.setSubtype("MySQL");
		
		DataSource ds2 = factory.createDataSource();
		ds2.setName("testDB");
		ds2.setAddress("http://localhost:50000/testDB");
		ds2.setType("database");
		ds2.setSubtype("IBM DB2");
		
		datasources.add(ds1);
		datasources.add(ds2);
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
