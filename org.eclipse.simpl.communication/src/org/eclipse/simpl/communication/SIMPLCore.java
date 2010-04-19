package org.eclipse.simpl.communication;

import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.simpl.communication.client.AdministrationService;
import org.eclipse.simpl.communication.client.AdministrationService_Service;
import org.eclipse.simpl.communication.client.ConnectionException_Exception;
import org.eclipse.simpl.communication.client.DatasourceService;
import org.eclipse.simpl.communication.client.DatasourceService_Service;
import org.eclipse.simpl.communication.client.Parameter;

/**
 * Die ganze Klasse ist nur ein Stub für den echten SIMPL Core bzw. für den Teil den
 * unsere Eclipse Plug-Ins benötigen. D.h. diese Klasse simuliert die gesamte
 * Funktionalität des SIMPL Core, die von Eclipse aus benötigt wird. Die hier
 * implementierte Funktionalität befindet sich später im SIMPL Core (Administration- &
 * StorageService).
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 * 
 */
@SuppressWarnings("unchecked")
public class SIMPLCore {
  AdministrationService administrationService = new AdministrationService_Service()
      .getAdministrationServicePort();
  DatasourceService datasourceService = new DatasourceService_Service()
      .getDatasourceServicePort();

  public boolean save(String schema, String table, String settingName,
      LinkedHashMap<String, String> settings) {
    boolean success = false;

    success = administrationService.saveSettings(schema, table, settingName, Parameter
        .serialize(settings));

    return success;
  }

  public LinkedHashMap<String, String> load(String schema, String table,
      String settingName) {
    LinkedHashMap<String, String> settings = null;

    settings = (LinkedHashMap<String, String>) Parameter
        .deserialize(administrationService.loadSettings(schema, table, settingName));

    return settings;
  }

  public String getMetaData(String dsAddress, String dsType, String dsSubtype, String filter) throws ConnectionException_Exception {
    String metaData = datasourceService.getMetaData(dsAddress, dsType, dsSubtype, filter);

    return metaData;
  }
  
  public List<String> getDatasourceTypes() {
    List<String> dsTypes = (List<String>) Parameter.deserialize(datasourceService
        .getDataSourceTypes());

    return dsTypes;
  }

  public List<String> getDatasourceSubTypes(String dsType) {
    List<String> dsSubTypes = (List<String>) Parameter.deserialize(datasourceService
        .getDataSourceSubtypes(dsType));

    return dsSubTypes;
  }

  public List<String> getDatasourceLanguages(String dsSubtype) {
    List<String> dsSubTypeLanguages = (List<String>) Parameter
        .deserialize(datasourceService.getDataSourceLanguages(dsSubtype));

    return dsSubTypeLanguages;
  }
  
  // public boolean saveAll(List<String> schema, List<String> table, String settingName,
  // List<LinkedHashMap<String, String>> settings) {
  // // Code der Operation
  //
  // // STUB
  // for (LinkedHashMap<String, String> map : settings) {
  // System.out.println("-------------------------------");
  // System.out.println("Schema: " + schema);
  // System.out.println("Tabelle: " + table);
  // System.out.println("Name der Einstellungen: " + settingName);
  // for (String key : map.keySet()) {
  // System.out.println(key + " : " + map.get(key));
  // }
  // System.out.println("-------------------------------");
  // }
  //
  // // STUB
  //
  // return true;// Hier wird dann in einem boolean zurückgegeben, ob das
  // // Speichern der Einstellungen geklappt hat.
  // }
  //
  // public List<LinkedHashMap<String, String>> loadAll(List<String> schema,
  // List<String> table, String settingName) {
  // // Code der Operation
  //
  // // STUB
  // List<LinkedHashMap<String, String>> settings = new ArrayList<LinkedHashMap<String,
  // String>>();
  // LinkedHashMap<String, String> globalSettings = new LinkedHashMap<String, String>();
  // LinkedHashMap<String, String> auditingSettings = new LinkedHashMap<String, String>();
  // // Global Settings
  // globalSettings.put("user", "admin");
  // globalSettings.put("password", "12345");
  // // Auditing
  // auditingSettings.put("mode", "off");
  // auditingSettings.put("auditingDsAddress", "http://localhost:8080/myDB");
  // //Zur Liste hinzufügen
  // settings.add(globalSettings);
  // settings.add(auditingSettings);
  // // STUB
  //
  // return settings;// Einstellungen als HashMap
  // }
}