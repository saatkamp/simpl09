package org.simpl.core.webservices;

import java.util.LinkedHashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.core.SIMPLCore;
import org.simpl.core.webservices.helpers.Parameter;

@WebService(name = "AdministrationService", targetNamespace = "", wsdlLocation = "AdministrationService.wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Administration {
  @WebMethod(action = "saveSettings")
  @SuppressWarnings("unchecked")
  public boolean saveSettings(
      @WebParam(name = "schema", targetNamespace = "") String schema,
      @WebParam(name = "table", targetNamespace = "") String table,
      @WebParam(name = "settingName", targetNamespace = "") String settingName,
      String settings) {
    boolean success = false;
    LinkedHashMap<String, String> settingsHashMap = null;

    settingsHashMap = (LinkedHashMap<String, String>) Parameter.deserialize(settings);
    success = SIMPLCore.administrationService.saveSettings(schema, table, settingName,
        settingsHashMap);

    return success;
  }

  @WebMethod(action = "loadSettings")
  public LinkedHashMap<String, String> loadSettings(
      @WebParam(name = "schema", targetNamespace = "") String schema,
      @WebParam(name = "table", targetNamespace = "") String table,
      @WebParam(name = "settingName", targetNamespace = "") String settingName) {
    LinkedHashMap<String, String> settings = null;

    settings = SIMPLCore.administrationService.loadSettings(schema, table, settingName);

    return settings;
  }
}