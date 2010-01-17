package org.simpl.core;

import java.util.LinkedHashMap;

import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>
 * <br>
 * <b>Description:</b>
 * <br>
 * <b>Copyright:</b>     <br>
 * <b>Company:</b>       SIMPL<br>
 *
 * @author      Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version     $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class Test {

  /**
   * @param args
   * @throws ConnectionException 
   */
  public static void main(String[] args) throws ConnectionException {
    LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
    settings.put("setting1", "value1");
    settings.put("setting2", "value2");
    
    System.out.println("saveSettings: " + SIMPLCore.administrationService.saveSettings("test", "tabelle1", "settings", settings));
    System.out.println("loadSettings: " + SIMPLCore.administrationService.loadSettings("test", "tabelle1", "settings"));

    
    DatasourceService ds = SIMPLCore.datasourceServiceProvider.getInstance("RDB");
    DataObject sdo = ds.queryData("simplDB", "SELECT * FROM test.tabelle1");
    
    System.out.println(sdo);
  }

}
