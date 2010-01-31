package org.eclipse.simpl.settings.extensions;

import org.eclipse.swt.widgets.Composite;

public interface ISettingsComposite {

	public Composite getComposite();

	public void setComposite(Composite composite);

	public void createComposite(Composite composite);

	public void saveSettings(String parentItem, String item, String settingName);

	public void loadSettings(String parentItem, String item);

	public boolean haveSettingsChanged();

	public void loadSettingsFromBuffer(String settingName);
	
	public String getParentConsoleItem();
	
	public String getConsoleItem();
}
