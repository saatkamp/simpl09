package org.eclipse.simpl.core.auditing.ui;

import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.simpl.communication.ResourceManagementCommunication;
import org.eclipse.simpl.communication.SIMPLCoreCommunication;
import org.simpl.resource.management.client.Authentication;
import org.simpl.resource.management.client.Connector;
import org.simpl.resource.management.client.DataFormat;
import org.simpl.resource.management.client.DataSource;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
public class AuditingDSUtils {
	
	private static DataSource localDs = null;
	
	public static String[] getRFDataSources(){
		List<String> dataSources = ResourceManagementCommunication.getInstance().getDataSourceNames();
		if (!dataSources.contains(getLocalDataSource().getName())){
			dataSources.add(0, getLocalDataSource().getName());
		}
		return dataSources.toArray(new String[0]);
	}
	
	public static DataSource getDataSourceByName(String name){
		if (name.equals(getLocalDataSource().getName())){
			return getLocalDataSource();
		}else {
			return ResourceManagementCommunication.getInstance().findDataSourceByName(name);
		}
	}

	/**
	 * @param dataSource
	 */
	public static void setLocalDataSource(DataSource dataSource) {
		localDs = dataSource;
	}
	
	public static DataSource getLocalDataSource() {
		if (localDs != null){
			return localDs;
		}else {
			//Load the address and name from DB
			localDs = new DataSource();
	    Connector connector = new Connector();
	    DataFormat dataFormat = new DataFormat();
	    connector.setConverterDataFormat(dataFormat);
			localDs.setConnector(connector);
	    
			localDs.setName("local");
			localDs.setType("Database");
			localDs.setSubType("EmbeddedDerby");
			localDs.getConnector().getConverterDataFormat().setName("RDBDataFormat");
			
			Authentication auth = new Authentication();
			auth.setUser("");
			auth.setPassword("");
			localDs.setAuthentication(auth);
			
			// Settings-Liste erstellen
			LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

			// LastSaved-Einstellungen aus SIMPL Core DB laden
			settings = SIMPLCoreCommunication.getInstance().load("AUDITING", "DEFAULTDATASOURCE",
					"lastSaved");

			if (!settings.isEmpty()) {
				localDs.setAddress(settings.get("AUDITING_DS_ADDRESS"));
				
				return localDs;
			}else {
				localDs.setAddress("../Tomcat-root/simplDB");
				return localDs;
			}
		}
	}
}
