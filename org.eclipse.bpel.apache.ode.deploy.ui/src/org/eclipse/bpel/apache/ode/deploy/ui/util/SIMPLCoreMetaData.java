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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.communication.SIMPLCore;

public class SIMPLCoreMetaData {

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

	/**
	 * This variable holds all data formats of a data source subtype, which can
	 * be chosen during the modeling process and will be supported by the data
	 * sources and the SIMPL Core.
	 */
	private static HashMap<String, List<String>> dataSourceFormats = new HashMap<String, List<String>>();

	/**
	 * Inits the Constants class.
	 */
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

		// Laden alle DataFormats der Subtypen aus dem SIMPL Core.
		for (String datasource : dataSourceTypes) {
			for (String subTypeName : getDataSourceSubTypes(datasource)) {
				dataSourceFormats.put(subTypeName, simplCore
						.getDataFormat(subTypeName));
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
	 * @return
	 */
	public static List<String> getDataSourceFormats() {
		// TODO Auto-generated method stub
		return null;
	}
}
