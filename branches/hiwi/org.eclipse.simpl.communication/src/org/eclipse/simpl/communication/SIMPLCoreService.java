package org.eclipse.simpl.communication;

import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;

import org.simpl.core.helpers.Parameter;
import org.simpl.core.webservices.client.AdministrationService;
import org.simpl.core.webservices.client.AdministrationServiceClient;
import org.simpl.core.webservices.client.DatasourceService;
import org.simpl.core.webservices.client.DatasourceServiceClient;
import org.simpl.core.webservices.client.Exception_Exception;
import org.simpl.resource.management.client.DataSource;

/**
 * SIMPL Core Service that gives access to all SIMPL Core web services.
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 */
@SuppressWarnings("unchecked")
public class SIMPLCoreService {
  AdministrationService administrationService = null;
  DatasourceService datasourceService = null;

  private final static String DSS_WSDL_ADDRESS = CommunicationPlugIn.getDefault()
      .getPreferenceStore().getString("SIMPL_CORE_DSS_ADDRESS");

  private final static String AS_WSDL_ADDRESS = CommunicationPlugIn.getDefault()
      .getPreferenceStore().getString("SIMPL_CORE_AS_ADDRESS");

  public DatasourceService getDatasourceService() {
    if (datasourceService == null) {
      datasourceService = DatasourceServiceClient.getService(DSS_WSDL_ADDRESS);
    }
    return datasourceService;
  }

  public AdministrationService getAdministrationService() {
    if (administrationService == null) {
      administrationService = AdministrationServiceClient.getService(AS_WSDL_ADDRESS);
    }
    return administrationService;
  }

  public boolean save(String schema, String table, String settingName,
      LinkedHashMap<String, String> settings) {
    boolean success = false;

    if (getAdministrationService() != null) {
      success = getAdministrationService().saveSettings(schema, table, settingName,
          Parameter.serialize(settings));
    }

    return success;
  }

  public LinkedHashMap<String, String> load(String schema, String table,
      String settingName) {
    LinkedHashMap<String, String> settings = null;

    if (getAdministrationService() != null) {
      settings = (LinkedHashMap<String, String>) Parameter
          .deserialize(getAdministrationService()
              .loadSettings(schema, table, settingName));
    }

    return settings;
  }

  public String getMetaData(DataSource dataSource, String filter)
      throws Exception_Exception {
    String metaData = null;

    if (getDatasourceService() != null) {
      metaData = getDatasourceService().getMetaData(dataSource, filter);
    }

    return metaData;
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

  public String getResourceManagementAddress() {
    LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
    
    if (getAdministrationService() != null) {
      settings = this.load("RESOURCEMANAGEMENT", "SETTINGS", "lastSaved");
    }
    
    return settings.get("ADDRESS");
  }
}