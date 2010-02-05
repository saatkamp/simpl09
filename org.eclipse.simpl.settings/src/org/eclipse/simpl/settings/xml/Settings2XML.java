package org.eclipse.simpl.settings.xml;

import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Settings2XML {

	private final static String FILENAME = "simpl-settings";
	private final static String FILEPATH = System.getProperty("user.dir")
			+ System.getProperty("file.separator");
	private final static String FILETYPE = ".xml";

	public static boolean saveSettings(
			List<LinkedHashMap<String, String>> listOfSettings, String settingsName) {
		
		boolean saved = false;

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
			out.output(doc, new FileOutputStream(FILEPATH+FILENAME+"-"+settingsName+FILETYPE));
			
			saved = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return saved;
	}
}
