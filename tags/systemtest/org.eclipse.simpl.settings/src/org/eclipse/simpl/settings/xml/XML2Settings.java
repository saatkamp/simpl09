package org.eclipse.simpl.settings.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class XML2Settings {

	private final static String FILENAME = "simpl-settings";
	private final static String FILEPATH = System.getProperty("user.dir")
			+ System.getProperty("file.separator");
	private final static String FILETYPE = ".xml";

	public static List<LinkedHashMap<String, String>> loadSettings(
			String settingsName) {
		List<LinkedHashMap<String, String>> listOfSettings = new ArrayList<LinkedHashMap<String, String>>();

		System.out.println("FULL_FILEPATH: " + FILEPATH + FILENAME + "-"
				+ settingsName + FILETYPE);

		if (settingsFileExists(settingsName)) {

			try {
				// Erzeugen eines JDOM-Dokuments anhand der Datei
				// simpl-settings.xml
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(FILEPATH + FILENAME + "-"
						+ settingsName + FILETYPE);
				// Lesen des Wurzelelements des JDOM-Dokuments doc
				Element settings = doc.getRootElement();

				// Erzeugen eine Liste aller Einstellungs-Einträge
				List allEntryElements = settings.getChildren("settingsEntry");
				// Arbeiten alle settingsEntry's durch
				for (Object settingsEntry : allEntryElements) {
					if (settingsEntry instanceof Element) {
						Element setEntry = (Element) settingsEntry;
						Element setSubEntry = setEntry
								.getChild("settingsSubEntry");

						// Erzeugen eine neue HashMap für das aktuell
						// gelesene settingsSubEntry-Objekt
						LinkedHashMap<String, String> setting = new LinkedHashMap<String, String>();

						// Tragen den Namen des settingsEntry's ein.
						setting.put("settingsEntry", setEntry
								.getAttributeValue("name"));
						// Tragen den Namen des settingsSubEntry's ein.
						setting.put("settingsSubEntry", setSubEntry
								.getAttributeValue("name"));

						// Erzeugen eine Liste aller Einstellungen des
						// Unter-Einstellungs-Eintrags
						List allSubEntrySettingElements = setSubEntry
								.getChildren("setting");
						// Arbeiten alle settingsSubEntry's durch
						for (Object subEntrySetting : allSubEntrySettingElements) {
							if (subEntrySetting instanceof Element) {
								Element subEntrySet = (Element) subEntrySetting;
								// Fügen jedes setting-Objekt eines
								// settingSubEntry's in die HashMap ein.
								setting.put(subEntrySet
										.getAttributeValue("name"), subEntrySet
										.getAttributeValue("value"));
							}
						}

						listOfSettings.add(setting);
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			printSettings("XML2Settings", listOfSettings);

		}
		return listOfSettings;
	}



	private static boolean settingsFileExists(String settingsName) {
		File settings = new File(FILEPATH + FILENAME + "-" + settingsName
				+ FILETYPE);
		return settings.exists();
	}

	public static void printSettings(String name, 
			List<LinkedHashMap<String, String>> listOfSettings) {
		System.out.println("NAME: "+ name);
		for (LinkedHashMap<String, String> setting : listOfSettings) {
			for (String key : setting.keySet()) {
				System.out.println("KEY: " + key + " ## " + "VALUE: "
						+ setting.get(key));
			}
			System.out.println("-------------------------------------");
		}
	}
	
	public static void printSetting(String name, LinkedHashMap<String, String> setting) {
		System.out.println("NAME: "+ name);
		for (String key : setting.keySet()) {
			System.out.println("KEY: " + key + " ## " + "VALUE: "
					+ setting.get(key));
			System.out.println("-------------------------------------");
		}
	}
}
