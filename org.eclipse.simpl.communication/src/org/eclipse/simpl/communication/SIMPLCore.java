package org.eclipse.simpl.communication;

import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.simpl.communication.client.AdministrationService;
import org.eclipse.simpl.communication.client.AdministrationService_Service;
import org.eclipse.simpl.communication.client.ConnectionException_Exception;
import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.simpl.communication.client.DatasourceService;
import org.eclipse.simpl.communication.client.DatasourceService_Service;
import org.eclipse.simpl.communication.client.Parameter;

/**
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 * 
 */
@SuppressWarnings("unchecked")
public class SIMPLCore {
	AdministrationService administrationService = null;
	DatasourceService datasourceService = null;

	private final static String DSS_WSDL_ADDRESS = CommunicationPlugIn
			.getDefault().getPreferenceStore().getString(
					"SIMPL_CORE_DSS_ADDRESS");

	private DatasourceService getDatasourceService() {
		if (datasourceService == null) {
			datasourceService = new DatasourceService_Service()
					.getDatasourceServicePort();
		}
		return datasourceService;
	}

	private AdministrationService getAdministrationService() {
		if (administrationService == null) {
			administrationService = new AdministrationService_Service()
					.getAdministrationServicePort();
		}
		return administrationService;
	}

	public boolean save(String schema, String table, String settingName,
			LinkedHashMap<String, String> settings) {
		boolean success = false;

		success = getAdministrationService().saveSettings(schema, table,
				settingName, Parameter.serialize(settings));

		return success;
	}

	public LinkedHashMap<String, String> load(String schema, String table,
			String settingName) {
		LinkedHashMap<String, String> settings = null;

		settings = (LinkedHashMap<String, String>) Parameter
				.deserialize(getAdministrationService().loadSettings(schema,
						table, settingName));

		return settings;
	}

	public String getMetaData(DataSource dataSource, String filter)
			throws ConnectionException_Exception {

		String metaData = getDatasourceService()
				.getMetaData(dataSource, filter);

		return metaData;
	}

	public List<String> getDatasourceTypes() {
		List<String> dsTypes = (List<String>) Parameter
				.deserialize(getDatasourceService().getDataSourceTypes());

		return dsTypes;
	}

	public List<String> getDatasourceSubTypes(String dsType) {
		List<String> dsSubTypes = (List<String>) Parameter
				.deserialize(getDatasourceService().getDataSourceSubTypes(
						dsType));

		return dsSubTypes;
	}

	public List<String> getDatasourceLanguages(String dsSubtype) {
		List<String> dsSubTypeLanguages = (List<String>) Parameter
				.deserialize(getDatasourceService().getDataSourceLanguages(
						dsSubtype));

		return dsSubTypeLanguages;
	}

	/**
	 * @param subTypeName
	 * @return
	 */
	public List<String> getSupportedDataFormats(DataSource dataSource) {
		try {
			List<String> dsDataFormat = (List<String>) Parameter
					.deserialize(getDatasourceService()
							.getSupportedDataFormatTypes(dataSource));

			return dsDataFormat;
		} catch (Exception e) {
			return null;
		}
	}

	public String getDataFormatSchema(DataSource dataSource) {
		try {
			return getDatasourceService().getDataFormatSchema(dataSource);
		} catch (ConnectionException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
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