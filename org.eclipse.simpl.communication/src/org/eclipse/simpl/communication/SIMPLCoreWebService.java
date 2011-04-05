package org.eclipse.simpl.communication;

import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;

import org.simpl.core.helpers.Parameter;
import org.simpl.core.webservices.client.Exception_Exception;
import org.simpl.core.webservices.client.SIMPLCoreAdministrationService;
import org.simpl.core.webservices.client.SIMPLCoreAdministrationServiceClient;
import org.simpl.core.webservices.client.SIMPLCoreService;
import org.simpl.core.webservices.client.SIMPLCoreServiceClient;
import org.simpl.resource.management.data.DataSource;

/**
 * Class that gives access to all SIMPL Core web services.
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 */
@SuppressWarnings("unchecked")
public class SIMPLCoreWebService {
  SIMPLCoreAdministrationService simplCoreAdministrationService = null;
  SIMPLCoreService simplCoreService = null;

  private final static String DSS_WSDL_ADDRESS = CommunicationPlugIn.getDefault()
      .getPreferenceStore().getString("SIMPL_CORE_DSS_ADDRESS");

  private final static String AS_WSDL_ADDRESS = CommunicationPlugIn.getDefault()
      .getPreferenceStore().getString("SIMPL_CORE_AS_ADDRESS");

  public SIMPLCoreService getSIMPLCoreService() {
    if (simplCoreService == null) {
      simplCoreService = SIMPLCoreServiceClient.getService(DSS_WSDL_ADDRESS);
    }
    return simplCoreService;
  }

  public SIMPLCoreAdministrationService getSIMPLCoreAdministrationService() {
    if (simplCoreAdministrationService == null) {
      simplCoreAdministrationService = SIMPLCoreAdministrationServiceClient
          .getService(AS_WSDL_ADDRESS);
    }
    return simplCoreAdministrationService;
  }

  public boolean save(String schema, String table, String settingName,
      LinkedHashMap<String, String> settings) {
    boolean success = false;

    if (getSIMPLCoreAdministrationService() != null) {
      success = getSIMPLCoreAdministrationService().saveSettings(schema, table, settingName,
          Parameter.serialize(settings));
    }

    return success;
  }

  public LinkedHashMap<String, String> load(String schema, String table,
      String settingName) {
    LinkedHashMap<String, String> settings = null;

    if (getSIMPLCoreAdministrationService() != null) {
      settings = (LinkedHashMap<String, String>) Parameter
          .deserialize(getSIMPLCoreAdministrationService()
              .loadSettings(schema, table, settingName));
    }

    return settings;
  }

  public String getMetaData(DataSource dataSource, String filter)
      throws Exception_Exception {
    String metaData = null;

    if (getSIMPLCoreService() != null) {
      metaData = getSIMPLCoreService().getMetaData(dataSource, filter);
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

    if (getSIMPLCoreAdministrationService() != null) {
      settings = this.load("RESOURCEMANAGEMENT", "SETTINGS", "lastSaved");
    }

    return settings.get("ADDRESS");
  }
}