/**
 * <b>Purpose:</b> This class buffers all the data which is needed in this Plug-In and provided by the SIMPL Core.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: SIMPLCoreMetaData.java 1202 2010-04-27 16:31:01Z hahnml@t-online.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.apache.ode.deploy.ui.util;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.bpel.apache.ode.deploy.model.dd.TDatasource;
import org.eclipse.simpl.communication.CommunicationPlugIn;
import org.eclipse.simpl.communication.ResourceManagementCommunication;
import org.eclipse.simpl.communication.ResourceManagementService;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataFormat;
import org.simpl.resource.management.data.DataSource;

public class ResourceManagementMetaData {

	/**
	 * This variable holds all types of data sources, which can be chosen during
	 * the modeling process and will be supported by the SIMPL Core.
	 */
	private static List<String> dataSourceTypes = new ArrayList<String>();

	/**
	 * This variable holds all subtypes of data source types, which can be
	 * chosen during the modeling process and will be supported by the SIMPL
	 * Core.
	 */
	private static HashMap<String, List<String>> dataSourceSubTypes = new HashMap<String, List<String>>();

	/**
	 * This variable holds all languages of the different data source subtypes,
	 * which can be chosen during the modeling process and will be supported by
	 * the data sources and the SIMPL Core.
	 */
	private static HashMap<String, List<String>> dataSourceSubTypeLanguages = new HashMap<String, List<String>>();

	private final static String DSS_WSDL_ADDRESS = CommunicationPlugIn.getDefault().getPreferenceStore().getString("SIMPL_CORE_DSS_ADDRESS");
	
	/**
	 * Inits the Constants class.
	 */
	public static void init() {
		// Holen uns eine Verbindung zum SIMPL Core.
		ResourceManagementService simplCore = ResourceManagementCommunication.getInstance();

		// Laden alle Datenquellentypen aus dem SIMPL Core.
		dataSourceTypes = simplCore.getDataSourceTypes();

		// Laden die Subtypen aller Datenquellentypen aus dem SIMPL Core.
		for (String typeName : dataSourceTypes) {
			dataSourceSubTypes.put(typeName, simplCore
					.getDataSourceSubTypes(typeName));
		}

		// Laden alle Abfragesprachen der Subtypen aus dem SIMPL Core.
		for (String datasource : dataSourceTypes) {
			for (String subTypeName : getDataSourceSubTypes(datasource)) {
				dataSourceSubTypeLanguages.put(subTypeName, simplCore
						.getDataSourceLanguages(subTypeName));
			}
		}
	}

	/**
	 * Returns the list of the data source types.
	 * 
	 * @return A list with all supported data source types.
	 */
	public static List<String> getDataSourceTypes() {
		return dataSourceTypes;
	}

	/**
	 * Returns the list of all data source sub types.
	 * 
	 * @param datasource
	 *            to get sub types of
	 * @return A list with all supported data source sub types of the given data
	 *         source type.
	 */
	public static List<String> getDataSourceSubTypes(String datasource) {
		return dataSourceSubTypes.get(datasource);
	}

	/**
	 * Returns the list of all query languages the given data source sub type
	 * supports.
	 * 
	 * @param datasourceSubType
	 *            to get query languages off.
	 * @return A list of all query languages the given data source sub type
	 *         supports.
	 */
	public static List<String> getDatasourceLanguages(String datasourceSubType) {
		return dataSourceSubTypeLanguages.get(datasourceSubType);
	}

	/**
	 * @return the supported data formats for a given data source.
	 */
	public static List<String> getDataSourceFormats(TDatasource dataSource) {
		ResourceManagementService resourceManagement = ResourceManagementCommunication.getInstance();
		
		return resourceManagement.getSupportedDataFormats(tDs2ds(dataSource));
	}
	
	private static DataSource tDs2ds (TDatasource data){
		DataSource dataSource = new DataSource();
		Connector connector = new Connector();
		DataFormat dataFormat = new DataFormat(); 
		
		if (data != null){
			dataSource.setName(data.getDataSourceName());
			dataSource.setAddress(data.getAddress());
			dataSource.setType(data.getType());
			dataSource.setSubType(data.getSubtype());
			dataSource.setLanguage(data.getLanguage());
			dataFormat.setName(data.getFormat());
			
			connector.setConverterDataFormat(dataFormat);
			dataSource.setConnector(connector);
		}
		
		return dataSource;
	}
	
	public static boolean isSIMPLCoreAvailable() {
		boolean isAvailable = false;
		URL url;
		try {
			url = new URL(DSS_WSDL_ADDRESS);
			URLConnection connection = url.openConnection();
			connection.connect();
			isAvailable = true;
		} catch (Exception e) {
			isAvailable = false;
		}
		return isAvailable;
	}
}
