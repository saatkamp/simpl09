package org.eclipse.simpl.settings.xml;

import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Settings2XML {

	private final static String FILENAME = "simpl-settings.xml";
	private final static String FILEPATH = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + FILENAME;

	public static boolean saveSettings(
			List<LinkedHashMap<String, String>> listOfSettings) {
		
		boolean saved = false;

		// List<LinkedHashMap<String, String>> listOfSettings = new
		// ArrayList<LinkedHashMap<String, String>>();
		// // Test
		// // Settings-Liste erstellen und mit Werten füllen zum Speichern
		// LinkedHashMap<String, String> settings = new LinkedHashMap<String,
		// String>();
		// settings.put("settingsEntry", "SIMPL Core");
		// settings.put("settingsSubEntry", "Address");
		// settings.put("simplCoreAddress", "http://localhost:8080/simpl");
		// LinkedHashMap<String, String> setti = new LinkedHashMap<String,
		// String>();
		// setti.put("settingsEntry", "SIMPL Coreas");
		// setti.put("settingsSubEntry", "xyzasdasd");
		// setti.put("asdasdasdasd", "asdasdasd");
		// setti.put("asdasdasasrterdasd", "asdaertersdasd");
		// setti.put("eqweqweqw", "asdasdeteteetasd");
		// listOfSettings.add(settings);
		// listOfSettings.add(setti);

		try {
			Document doc = new Document(new Element("settings"));
			Element root = doc.getRootElement();

			for (LinkedHashMap<String, String> setting : listOfSettings) {
				// Erzeugen ein neues settingsEntry-Element
				Element settingsEntry = new Element("settingsEntry");
				settingsEntry
						.setAttribute("name", setting.get("settingsEntry"));
				// Erzeugen ein neues settingsSubEntry-Element
				Element settingsSubEntry = new Element("settingsSubEntry");
				// Setzen das name-Attribut auf seinen Wert
				settingsSubEntry.setAttribute("name", setting
						.get("settingsSubEntry"));
				for (String key : setting.keySet()) {
					if (!key.equals("settingsEntry")
							& !key.equals("settingsSubEntry")) {
						// Erzeugen ein neues setting-Element
						Element set = new Element("setting");
						// Setzen die Attribute name und value
						set.setAttribute("name", key);
						set.setAttribute("value", setting.get(key));
						// Fügen das setting-Element dem
						// settingsSubEntry-Element hinzu
						settingsSubEntry.addContent(set);
					}
				}
				// Fügen das settingsSubEntry-Element dem settingsEntry-Element
				// hinzu
				settingsEntry.addContent(settingsSubEntry);
				// Fügen das settingsEntry-Element dem Dokument hinzu
				root.addContent(settingsEntry);
			}

			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			out.output(doc, new FileOutputStream(FILEPATH));
			
			saved = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return saved;
	}
}
