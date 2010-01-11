package org.eclipse.simpl.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.simpl.communication.SIMPLCommunication;

public class BufferedData {

	List<LinkedHashMap<String, String>> defaultSettings = new ArrayList<LinkedHashMap<String, String>>();

	List<LinkedHashMap<String, String>> lastSavedSettings = new ArrayList<LinkedHashMap<String, String>>();

	List<LinkedHashMap<String, String>> bufferedSettings = new ArrayList<LinkedHashMap<String, String>>();

	private static BufferedData instance = null;

	public static BufferedData getInstance() {
		if (instance == null) {
			instance = new BufferedData();
		}
		return instance;
	}

	public void init() {
		// Alle Admin-Konsolen Punkte laden
		List<Tuple> treeItems = Application.getInstance().getTreeItems();
		List<String> treeSubItems = null;
		for (Tuple treeIt : treeItems) {
			// Alle Admin-Konsolen Unterpunkte eines Punktes laden
			treeSubItems = Application.getInstance().getTreeSubItems(
					treeIt.getName());
			for (String subItem : treeSubItems) {
				// Für jeden Unterpunkt der Admin-Konsole die Einstellungen aus
				// dem SIMPL Core laden.
				LinkedHashMap<String, String> lastSetting = new LinkedHashMap<String, String>();
				LinkedHashMap<String, String> defaultSetting = new LinkedHashMap<String, String>();
				lastSetting.put("schema", treeIt.getName());
				lastSetting.put("table", subItem);
				lastSetting.putAll(SIMPLCommunication.getConnection().load(
						treeIt.getName(), subItem, "lastSaved"));
				lastSavedSettings.add(lastSetting);
				bufferedSettings.add(lastSetting);

				defaultSetting.put("schema", treeIt.getName());
				defaultSetting.put("table", subItem);
				defaultSetting.putAll(SIMPLCommunication.getConnection().load(
						treeIt.getName(), subItem, "default"));
			}
		}
	}

	public void setSettings(String treeItem, String treeSubItem,
			String settingName, LinkedHashMap<String, String> settings) {
		
	}

	public LinkedHashMap<String, String> getSettings(String treeItem,
			String treeSubItem, String settingName) {
		return null;
	}

	public boolean haveSettingsChanged(String treeItem, String treeSubItem,
			String settingName, LinkedHashMap<String, String> settings) {

		return false;
	}
}
