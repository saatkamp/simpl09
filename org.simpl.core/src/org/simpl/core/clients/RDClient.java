package org.simpl.core.clients;

import java.util.LinkedHashMap;

import org.simpl.core.services.SIMPLCoreAdministrationService;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

/**
 * <b>Purpose:</b>Provides configurable access to the different Resource Discovery
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
public class RDClient {
  private static boolean usingWebService = false;

  /**
   * RDClient singleton instance.
   */
  private static final RDClient instance = new RDClient();

  /**
   * Returns the {@link RDClient} singleton instance.
   * 
   * @return
   */
  public static RDClient getInstance() {
    // check if the Resource Discovery web service access if activated in the internal
    // embedded derby simplDB
    LinkedHashMap<String, String> settings = SIMPLCoreAdministrationService.getInstance()
        .getService().loadSettings("RESOURCEDISCOVERY", "SETTINGS", "lastSaved");
    
    boolean isUsingWebService = settings.get("MODE").equals("active") ? true : false;

    if (isUsingWebService) {
      usingWebService = isUsingWebService;
    }
    
    return RDClient.instance;
  }

  public DataSource findDataSource(LateBinding lateBinding) {
    if (isUsingWebService()) {
      return RDWebClient.getInstance().findDataSource(lateBinding);
    } else {
      return RDDirectClient.getInstance().findDataSource(lateBinding);
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