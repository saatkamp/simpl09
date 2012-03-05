package org.simpl.core.webservices;

import java.util.LinkedHashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.core.helpers.Parameter;
import org.simpl.core.services.SIMPLCoreAdministrationService;


/**
 * <b>Purpose:</b>Web Service to access the SIMPL Core administration.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: SIMPLCoreAdministration.java 1815 2011-07-05 12:33:12Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@WebService(name = "SIMPLCoreAdministrationService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SIMPLCoreAdministration {
  @WebMethod(action = "saveSettings")
  @SuppressWarnings("unchecked")
  public boolean saveSettings(@WebParam(name = "schema") String schema,
      @WebParam(name = "table") String table,
      @WebParam(name = "settingName") String settingName,
      @WebParam(name = "settings") String settings) {
    boolean success = false;
    LinkedHashMap<String, String> settingsHashMap = null;

    settingsHashMap = (LinkedHashMap<String, String>) Parameter.deserialize(settings);
    success = SIMPLCoreAdministrationService.getInstance().getService()
        .saveSettings(schema, table, settingName, settingsHashMap);

    return success;
  }

  @WebMethod(action = "loadSettings")
  public String loadSettings(@WebParam(name = "schema") String schema,
      @WebParam(name = "table") String table,
      @WebParam(name = "settingName") String settingName) {
    LinkedHashMap<String, String> settings = null;

    settings = SIMPLCoreAdministrationService.getInstance().getService()
        .loadSettings(schema, table, settingName);
    System.out.println("SAVE SETTINGS: " + settings.size());
    return Parameter.serialize(settings);
  }
}