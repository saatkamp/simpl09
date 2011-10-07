package org.simpl.core.clients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.simpl.core.services.SIMPLCoreAdministrationService;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.DataSourceList;

/**
 * <b>Purpose:</b>Provides configurable access to the different Resource Management
 * clients.<br>
 * <b>Description:</b>Wraps the functions of the clients.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RMClient {
  private static boolean usingWebService = false;

  /**
   * RMClient singleton instance.
   */
  private static final RMClient instance = new RMClient();

  /**
   * Returns the {@link RMClient} singleton instance.
   * 
   * @return
   */
  public static RMClient getInstance() {
    // check if the Resource Management web service access if activated in the internal
    // embedded derby simplDB
    LinkedHashMap<String, String> settings = SIMPLCoreAdministrationService.getInstance()
        .getService().loadSettings("RESOURCEMANAGEMENT", "SETTINGS", "lastSaved");
    
    boolean isUsingWebService = settings.get("MODE").equals("active") ? true : false;

    if (isUsingWebService) {
      usingWebService = isUsingWebService;
    }
    
    return RMClient.instance;
  }

  public List<String> getConnectorPlugins() {
    if (isUsingWebService()) {
      return RMWebClient.getInstance().getConnectorPlugins();
    } else {
      return RMDirectClient.getInstance().getConnectorPlugins();
    }
  }

  public List<String> getDataFormatPlugins() {
    if (isUsingWebService()) {
      return RMWebClient.getInstance().getDataFormatPlugins();
    } else {
      return RMDirectClient.getInstance().getDataFormatPlugins();
    }
  }

  public List<String> getDataTransformationServices() {
    if (isUsingWebService()) {
      return RMWebClient.getInstance().getDataTransformationServices();
    } else {
      return RMDirectClient.getInstance().getDataTransformationServices();
    }
  }

  public HashMap<String, ArrayList<String>> getDataFormatMapping() {
    if (isUsingWebService()) {
      return RMWebClient.getInstance().getDataFormatMapping();
    } else {
      return RMDirectClient.getInstance().getDataFormatMapping();
    }
  }

  public HashMap<String, ArrayList<String>> getDataTransformationServiceMapping() {
    if (isUsingWebService()) {
      return RMWebClient.getInstance().getDataTransformationServiceMapping();
    } else {
      return RMDirectClient.getInstance().getDataTransformationServiceMapping();
    }
  }

  public DataSource getDataSourceByName(String dataSourceName) {
    if (isUsingWebService()) {
      return RMWebClient.getInstance().getDataSourceByName(dataSourceName);
    } else {
      return RMDirectClient.getInstance().getDataSourceByName(dataSourceName);
    }
  }

  public DataSourceList getAllDataSources() {
    if (isUsingWebService()) {
      return RMWebClient.getInstance().getAllDataSources();
    } else {
      return RMDirectClient.getInstance().getAllDataSources();
    }
  }

  public void reload() {
    if (isUsingWebService()) {
      RMWebClient.getInstance().reload();
    } else {
      RMDirectClient.getInstance().reload();
    }
  }

  /**
   * @return the usingWebService
   */
  public boolean isUsingWebService() {
    return usingWebService;
  }

  /**
   * @param useWebService
   *          the usingWebService to set
   */
  public void setUsingWebService(boolean useWebService) {
    usingWebService = useWebService;
  }
}