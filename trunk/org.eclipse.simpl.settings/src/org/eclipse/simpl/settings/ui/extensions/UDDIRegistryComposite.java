package org.eclipse.simpl.settings.ui.extensions;

import java.util.LinkedHashMap;

import org.eclipse.simpl.settings.extensions.ASettingsComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class UDDIRegistryComposite extends ASettingsComposite {

	// Global hinterlegte Keys der Einstellungen
	private final String UDDI_REGISTRY_ADDRESS = "UDDI_REGISTRY_ADDRESS";

	private Label uddiRegistryLabel = null;
	private Text uddiRegistryText = null;

	// Default-Einstellungen
	private String dUDDIRegistryAddress = "";

	// Buffer-Einstellungen
	private String bUDDIRegistryAddress = "";

	// LastSaved-Einstellungen
	private String lUDDIRegistryAddress = "";

	@Override
	public void createComposite(Composite composite) {
		// TODO Auto-generated method stub
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		Composite comp = new Composite(composite, SWT.NONE);
		comp.setLayout(gridLayout);

		setComposite(comp);

		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = GridData.FILL;
		gridData6.grabExcessHorizontalSpace = true;
		gridData6.verticalAlignment = GridData.CENTER;
		uddiRegistryLabel = new Label(comp, SWT.NONE);
		uddiRegistryLabel.setText("Address of the Reference Resolution System: ");
		uddiRegistryText = new Text(comp, SWT.BORDER);
		uddiRegistryText.setLayoutData(gridData6);
		uddiRegistryText.setText(this.bUDDIRegistryAddress);
		uddiRegistryText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				bUDDIRegistryAddress = uddiRegistryText.getText();
			}
		});
	}

	@Override
	public LinkedHashMap<String, String> getSettings() {
		// Settings-Liste erstellen und mit Werte füllen zum Speichern
		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
		// Zugehörigkeit des Composites einfügen (Ober- und Unterpunkt der
		// Settings)
		settings.put("settingsEntry", getParentSettingItem());
		settings.put("settingsSubEntry", getSettingItem());

		// Werte aus den Buffervariablen einfügen
		settings.put(this.UDDI_REGISTRY_ADDRESS, this.bUDDIRegistryAddress);

		// Last-Saved Werte aktualisieren
		this.lUDDIRegistryAddress = this.bUDDIRegistryAddress;

		return settings;
	}

	@Override
	public void setSettings(LinkedHashMap<String, String> settings,
			String settingsName) {
		if (!settings.isEmpty()) {
			if (settingsName.equals("lastSaved")) {
				// Last-Saved laden
				this.lUDDIRegistryAddress = settings.get(this.UDDI_REGISTRY_ADDRESS);
				this.bUDDIRegistryAddress = this.lUDDIRegistryAddress;
			} else {
				// Defaults laden
				this.dUDDIRegistryAddress = settings.get(this.UDDI_REGISTRY_ADDRESS);
			}

		}
	}

	@Override
	public boolean haveSettingsChanged() {
		boolean changed = false;
		// Überprüfen, ob Auditing-Modus oder Auditing-DB geändert wurden
		if (!this.bUDDIRegistryAddress.equals(this.lUDDIRegistryAddress)) {
			changed = true;
		}
		return changed;
	}

	@Override
	public void loadSettingsFromBuffer(String settingName) {
		if (settingName.equals("default")) {
			if (!getComposite().isDisposed()) {
				// Default Werte in GUI-Elementen setzen
				uddiRegistryText.setText(this.dUDDIRegistryAddress);
				System.out.println("LOAD FROM Default: "
						+ this.dUDDIRegistryAddress);
			}
			// Buffer-Werte aktualisieren
			this.bUDDIRegistryAddress = this.dUDDIRegistryAddress;
		} else {
			if (settingName.equals("lastSaved")) {
				if (!getComposite().isDisposed()) {
					// Last-Saved Werte in GUI-Elementen setzen
					uddiRegistryText.setText(this.lUDDIRegistryAddress);
					System.out.println("LOAD FROM LastSaved: "
							+ this.lUDDIRegistryAddress);
				}
				// Buffer-Werte aktualisieren
				this.bUDDIRegistryAddress = this.lUDDIRegistryAddress;
			} else {
				if (!getComposite().isDisposed()) {
					// Buffer-Werte in GUI-Elementen setzen
					uddiRegistryText.setText(this.bUDDIRegistryAddress);
					System.out.println("LOAD FROM BUFFER: "
							+ this.bUDDIRegistryAddress);
				}
			}
		}
	}

	@Override
	public String getParentSettingItem() {
		return "UDDIRegistry";
	}

	@Override
	public String getSettingItem() {
		return "Address";
	}
}