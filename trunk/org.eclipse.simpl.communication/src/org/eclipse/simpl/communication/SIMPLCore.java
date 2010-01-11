package org.eclipse.simpl.communication;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Die ganze Klasse ist nur ein Stub für den echten SIMPL Core bzw. für den Teil
 * den unsere Eclipse Plug-Ins benötigen. D.h. diese Klasse simuliert die
 * gesamte Funktionalität des SIMPL Core, die von Eclipse aus benötigt wird. Die
 * hier implementierte Funktionalität befindet sich später im SIMPL Core
 * (Administration- & StorageService).
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 * 
 */
public class SIMPLCore {
	
	private LinkedHashMap<String, String> auditingSettings = new LinkedHashMap<String, String>();

	private LinkedHashMap<String, String> globalSettings = new LinkedHashMap<String, String>();
	
	private LinkedHashMap<String, String> auditingDefaultSettings = new LinkedHashMap<String, String>();

	private LinkedHashMap<String, String> globalDefaultSettings = new LinkedHashMap<String, String>();
	
	public SIMPLCore() {
		// Global Settings
		globalDefaultSettings.put("username", "admin");
		globalDefaultSettings.put("password", "12345");
		
		// Auditing
		auditingDefaultSettings.put("mode", "inactive");
		auditingDefaultSettings.put("auditingDsAddress", "http://localhost:8080/myDB");
	}
	
	public boolean save(String schema, String table, String settingName,
			LinkedHashMap<String, String> settings) {
//		//Weiterleiten an AdministrationService
//		return AdministrationService.getInstance().saveSettings(schema, table, settingName, settings);
		// Code der Operation

		// STUB
		System.out.println("Schema: " + schema);
		System.out.println("Tabelle: " + table);
		System.out.println("Name der Einstellungen: " + settingName);
		for (String key : settings.keySet()) {
			System.out.println(key + " : " + settings.get(key));
		}
		if (schema.contains("Auditing")){
			if (settingName.contains("lastSaved")){
				auditingSettings = settings;
			}else{
				auditingDefaultSettings = settings;
			}
		}else{
			if (settingName.contains("lastSaved")){
				globalSettings = settings;
			}else{
				globalDefaultSettings = settings;
			}
		}
		
		// STUB

		return true;// Hier wird dann in einem boolean zurückgegeben, ob das
					// Speichern der Einstellungen geklappt hat.
	}

	public LinkedHashMap<String, String> load(String schema, String table,
			String settingName) {
//		//Weiterleiten an AdministrationService
//		return AdministrationService.getInstance().loadSettings(schema, table, settingName);
		// Code der Operation

		// STUB
		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
		if (schema.contains("Auditing")){
			if (settingName.contains("lastSaved") && !auditingSettings.isEmpty()){
				settings = auditingSettings;
			}else{
				settings = auditingDefaultSettings;
			}
		}else{
			if (settingName.contains("lastSaved") && !globalSettings.isEmpty()){
				settings = globalSettings;
			}else{
				settings = globalDefaultSettings;
			}
		}
		// STUB

		return settings;// Einstellungen als HashMap
	}

//	public boolean saveAll(List<String> schema, List<String> table, String settingName,
//			List<LinkedHashMap<String, String>> settings) {
//		// Code der Operation
//
//		// STUB
//		for (LinkedHashMap<String, String> map : settings) {
//			System.out.println("-------------------------------");
//			System.out.println("Schema: " + schema);
//			System.out.println("Tabelle: " + table);
//			System.out.println("Name der Einstellungen: " + settingName);
//			for (String key : map.keySet()) {
//				System.out.println(key + " : " + map.get(key));
//			}
//			System.out.println("-------------------------------");
//		}
//
//		// STUB
//
//		return true;// Hier wird dann in einem boolean zurückgegeben, ob das
//					// Speichern der Einstellungen geklappt hat.
//	}
//
//	public List<LinkedHashMap<String, String>> loadAll(List<String> schema,
//			List<String> table, String settingName) {
//		// Code der Operation
//
//		// STUB
//		List<LinkedHashMap<String, String>> settings = new ArrayList<LinkedHashMap<String, String>>();
//		LinkedHashMap<String, String> globalSettings = new LinkedHashMap<String, String>();
//		LinkedHashMap<String, String> auditingSettings = new LinkedHashMap<String, String>();
//		// Global Settings
//		globalSettings.put("user", "admin");
//		globalSettings.put("password", "12345");
//		// Auditing
//		auditingSettings.put("mode", "off");
//		auditingSettings.put("auditingDsAddress", "http://localhost:8080/myDB");
//		//Zur Liste hinzufügen
//		settings.add(globalSettings);
//		settings.add(auditingSettings);
//		// STUB
//
//		return settings;// Einstellungen als HashMap
//	}

	public List<String> getDatasourceTypes() {
		List<String> dsTypes = new ArrayList<String>();

		// STUB
		dsTypes.add("filesystem");
		dsTypes.add("database");
		dsTypes.add("sensornet");
		// STUB

		return dsTypes;
	}

	public List<String> getDatasourceSubTypes(String datasourceType) {
		List<String> dsSubTypes = new ArrayList<String>();

		// STUB
		if (datasourceType.contains("filesystem")) {
			dsSubTypes.add("ext3");
			dsSubTypes.add("ntfs");
			dsSubTypes.add("fat32");
		} else {
			if (datasourceType.contains("database")) {
				dsSubTypes.add("DB2");
				dsSubTypes.add("MySQL");
			} else {
				if (datasourceType.contains("sensornet")) {
					dsSubTypes.add("TinyDB");
				}
			}
		}
		// STUB

		return dsSubTypes;
	}

	public List<String> getDatasourceLanguages(String datasourceSubType) {
		List<String> dsSubTypeLanguages = new ArrayList<String>();

		// STUB
		if (datasourceSubType.contains("DB2")) {
			dsSubTypeLanguages.add("XQuery");
			dsSubTypeLanguages.add("SQL");
		} else {
			if (datasourceSubType.contains("ext3")
					|| datasourceSubType.contains("ntfs")
					|| datasourceSubType.contains("fat32")) {
				dsSubTypeLanguages.add("OSCall");
			} else {
				if (datasourceSubType.contains("TinyDB")) {
					dsSubTypeLanguages.add("TinySQL");
				} else {
					if (datasourceSubType.contains("MySQL")) {
						dsSubTypeLanguages.add("SQL");
					}
				}
			}
		}
		// STUB

		return dsSubTypeLanguages;
	}
}
