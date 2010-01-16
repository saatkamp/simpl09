package org.simpl.core.test;

import java.util.LinkedHashMap;

import org.simpl.core.AdministrationService;

public class AdministrationServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
		settings.put("mode", "on");
		settings.put("auditingDBaddress", "http://localhost:8080/myDB");
		
		//Speichern einiger Testeinstellungen
		AdministrationService.getInstance().saveSettings("Auditing", "General", "lastSaved", settings);
		
		//Laden der gerade gespeicherten Testeinstellungen
		printSettings(AdministrationService.getInstance().loadSettings("Auditing", "General", "lastSaved"));

//		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
//		settings.put("username", "hahnml");
//		settings.put("password", "12345");
//		
//		//Speichern einiger Testeinstellungen
//		AdministrationService.getInstance().saveSettings("Global Settings", "Authentification Data", "lastSaved", settings);
//		
//		//Laden der gerade gespeicherten Testeinstellungen
//		printSettings(AdministrationService.getInstance().loadSettings("Global Settings", "Authentification Data", "lastSaved"));
		
	}
	
	public static void printSettings(LinkedHashMap<String, String> settings){
		for (String name : settings.keySet()){
			System.out.println("Name: " + name + " | " + "Wert: " + settings.get(name));
		}
	}

}
