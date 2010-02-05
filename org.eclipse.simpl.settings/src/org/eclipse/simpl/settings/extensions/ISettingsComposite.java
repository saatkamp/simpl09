package org.eclipse.simpl.settings.extensions;

import java.util.LinkedHashMap;

import org.eclipse.swt.widgets.Composite;

public interface ISettingsComposite {

	public Composite getComposite();

	public void setComposite(Composite composite);

	public void createComposite(Composite composite);
	
	public LinkedHashMap<String, String> getSettings();
	
	public void setSettings(LinkedHashMap<String, String> settings, String settingsName);

	public boolean haveSettingsChanged();

	public void loadSettingsFromBuffer(String settingName);
	
	public String getParentSettingItem();
	
	public String getSettingItem();
}
