package org.eclipse.bpel.simpl.ui.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.communication.SIMPLCore;

public class Constants {

	/**
	 * This variable holds all types of data sources, which can be choosed
	 * during the modeling process and will be supported by the SIMPL Core.
	 */
	private static List<String> dataSourceTypes = new ArrayList<String>();

	/**
	 * This variable holds all subtypes of data source types, which can be
	 * choosed during the modeling process and will be supported by the SIMPL
	 * Core.
	 */
	private static HashMap<String, List<String>> dataSourceSubTypes = new HashMap<String, List<String>>();

	/**
	 * This variable holds all languages of the different data source subtypes,
	 * which can be choosed during the modeling process and will be supported by
	 * the data sources and the SIMPL Core.
	 */
	private static HashMap<String, List<String>> dataSourceSubTypeLanguages = new HashMap<String, List<String>>();

	public static void init() {
		// Holen uns eine Verbindung zum SIMPL Core.
		SIMPLCore simplCore = SIMPLCommunication.getConnection();

		// Laden alle Datenquellentypen aus dem SIMPL Core.
		dataSourceTypes = simplCore.getDatasourceTypes();

		// Laden die Subtypen aller Datenquellentypen aus dem SIMPL Core.
		for (String typeName : dataSourceTypes) {
			dataSourceSubTypes.put(typeName, simplCore
					.getDatasourceSubTypes(typeName));
		}

		// Laden alle Abfragesprachen der Subtypen aus dem SIMPL Core.
		for (String datasource : dataSourceTypes) {
			for (String subTypeName : getDataSourceSubTypes(datasource)) {
				dataSourceSubTypeLanguages.put(subTypeName, simplCore
						.getDatasourceLanguages(subTypeName));
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
}
