package org.simpl.core.clients;

import java.util.LinkedHashMap;

import org.simpl.core.services.SIMPLCoreAdministrationService;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Provides configurable access to the different Data Transformation
 * Service clients.<br>
 * <b>Description:</b>Wraps the functions of the clients.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DTSClient {
  private static boolean usingWebService = false;

  /**
   * RDClient singleton instance.
   */
  private static final DTSClient instance = new DTSClient();

  /**
   * Returns the {@link DTSClient} singleton instance.
   * 
   * @return
   */
  public static DTSClient getInstance() {
    // check if the Data Transformation Service web service access if activated in the internal
    // embedded derby simplDB
    LinkedHashMap<String, String> settings = SIMPLCoreAdministrationService.getInstance()
        .getService().loadSettings("DATATRANSFORMATIONSERVICE", "SETTINGS", "lastSaved");
    
    boolean isUsingWebService = settings.get("MODE").equals("active") ? true : false;

    if (isUsingWebService) {
      usingWebService = isUsingWebService;
    }
    
    return DTSClient.instance;
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

  public DataObject convert(String fromDataFormat, String toDataFormat, DataObject data, String connectorImpl) {
    if (isUsingWebService()) {
      return DTSWebClient.getInstance().convert(fromDataFormat, toDataFormat, data, connectorImpl);
    } else {
      return DTSDirectClient.getInstance().convert(fromDataFormat, toDataFormat, data, connectorImpl);
    }
  }
}