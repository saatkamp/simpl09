package org.eclipse.simpl.communication;

import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;

import org.simpl.core.helpers.Parameter;
import org.simpl.core.webservices.client.AdministrationService;
import org.simpl.core.webservices.client.AdministrationServiceClient;
import org.simpl.core.webservices.client.DataSource;
import org.simpl.core.webservices.client.DatasourceService;
import org.simpl.core.webservices.client.DatasourceServiceClient;
import org.simpl.core.webservices.client.Exception_Exception;

/**
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 * 
 */
@SuppressWarnings("unchecked")
public class SIMPLCore {
  AdministrationService administrationService = null;
  DatasourceService datasourceService = null;

  private final static String DSS_WSDL_ADDRESS = CommunicationPlugIn.getDefault()
      .getPreferenceStore().getString("SIMPL_CORE_DSS_ADDRESS");

  private final static String AS_WSDL_ADDRESS = CommunicationPlugIn.getDefault()
      .getPreferenceStore().getString("SIMPL_CORE_AS_ADDRESS");

  private DatasourceService getDatasourceService() {
    if (datasourceService == null) {
      datasourceService = DatasourceServiceClient.getService(DSS_WSDL_ADDRESS);
    }
    return datasourceService;
  }

  private AdministrationService getAdministrationService() {
    if (administrationService == null) {
      administrationService = AdministrationServiceClient.getService(AS_WSDL_ADDRESS);
    }
    return administrationService;
  }

  public boolean save(String schema, String table, String settingName,
      LinkedHashMap<String, String> settings) {
    boolean success = false;

    success = getAdministrationService().saveSettings(schema, table, settingName,
        Parameter.serialize(settings));

    return success;
  }

  public LinkedHashMap<String, String> load(String schema, String table,
      String settingName) {
    LinkedHashMap<String, String> settings = null;

    settings = (LinkedHashMap<String, String>) Parameter
        .deserialize(getAdministrationService().loadSettings(schema, table, settingName));

    return settings;
  }

  public String getMetaData(DataSource dataSource, String filter)
      throws Exception_Exception {

    String metaData = getDatasourceService().getMetaData(dataSource, filter);

    return metaData;
  }

  public List<String> getDatasourceTypes() {
    List<String> dsTypes = (List<String>) Parameter.deserialize(getDatasourceService()
        .getDataSourceTypes());

    return dsTypes;
  }

  public List<String> getDatasourceSubTypes(String dsType) {
    List<String> dsSubTypes = (List<String>) Parameter.deserialize(getDatasourceService()
        .getDataSourceSubTypes(dsType));

    return dsSubTypes;
  }

  public List<String> getDatasourceLanguages(String dsSubtype) {
    List<String> dsSubTypeLanguages = (List<String>) Parameter
        .deserialize(getDatasourceService().getDataSourceLanguages(dsSubtype));

    return dsSubTypeLanguages;
  }

  /**
   * @param subTypeName
   * @return
   */
  public List<String> getSupportedDataFormats(DataSource dataSource) {
    try {
      List<String> dsDataFormat = (List<String>) Parameter
          .deserialize(getDatasourceService().getSupportedDataFormatTypes(dataSource));

      return dsDataFormat;
    } catch (Exception e) {
      return null;
    }
  }

  public String getDataFormatSchema(DataSource dataSource) {
    String dataFormatSchema = "";

    try {
      dataFormatSchema = getDatasourceService().getDataFormatSchema(dataSource);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataFormatSchema;
  }

  public boolean isSIMPLCoreAvailable() {
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