package org.eclipse.simpl.core.extensions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

public abstract class AAdminConsoleComposite implements IAdminConsoleComposite {

	private Composite composite = null;
	
//	private List<LinkedHashMap<String, String>> settings = new ArrayList<LinkedHashMap<String, String>>();

	@Override
	public Composite getComposite() {
		// TODO Auto-generated method stub
		return this.composite;
	}

	@Override
	public void setComposite(Composite composite) {
		// TODO Auto-generated method stub
		this.composite = composite;
	}

//	public LinkedHashMap<String, String> getSettings(String settingName){
//		LinkedHashMap<String, String> set = new LinkedHashMap<String, String>();
//		for (LinkedHashMap<String, String> map : this.settings){
//			if (map.get("settingName").contains(settingName)){
//				map.remove("settingName");
//				set=map;
//			}
//		}
//		return set;
//	}
//	
//	public void setSettings(String settingName, LinkedHashMap<String, String> settings){
//		boolean found = false;
//		int index = 0;
//		//Überprüfen, ob die Einstellung schon vorhanden ist
//		while (index < this.settings.size() && !found){
//			index++;
//			if (this.settings.get(index).get("settingName").contains(settingName)){
//				found = true;
//			}
//		}
//		if (found){
//			//Vorhandene Einstellungen löschen
//			this.settings.remove(index);
//		}
//		//Neue Einstellung hinzufügen
//		LinkedHashMap<String, String> newSettings = new LinkedHashMap<String, String>();
//		newSettings.put("settingName", settingName);
//		newSettings.putAll(settings);
//		this.settings.add(newSettings);
//	}
}
