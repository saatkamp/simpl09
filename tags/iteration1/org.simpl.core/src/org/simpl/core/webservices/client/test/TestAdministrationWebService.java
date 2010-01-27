package org.simpl.core.webservices.client.test;

import java.util.LinkedHashMap;

import org.simpl.core.webservices.client.AdministrationService;
import org.simpl.core.webservices.client.AdministrationService_Service;
import org.simpl.core.webservices.helpers.Parameter;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 */
public class TestAdministrationWebService {

  /**
   * @param args
   */
  public static void main(String[] args) {
    AdministrationService_Service administrationService = new AdministrationService_Service();
    AdministrationService administrationServicePort = administrationService.getAdministrationServicePort();
    
    LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
    settings.put("setting1", "value1");
    settings.put("setting2", "value2");
    
    System.out.println("settings: " + settings);
    System.out.println("serialize: " + Parameter.serialize(settings));
    System.out.println("saveSettings: " + administrationServicePort.saveSettings("test", "tabelle1", "settings", Parameter.serialize(settings)));
    System.out.println("loadSettings: " + Parameter.deserialize(administrationServicePort.loadSettings("test", "tabelle1", "settings")));
  }
}
