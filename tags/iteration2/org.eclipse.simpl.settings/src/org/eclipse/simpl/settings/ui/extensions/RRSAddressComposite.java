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

public class RRSAddressComposite extends ASettingsComposite {

	// Global hinterlegte Keys der Einstellungen
	private final String RRS_ADDRESS = "RRS_ADDRESS";

	private Label rrsLabel = null;
	private Text rrsText = null;

	// Default-Einstellungen
	private String dRRSAddress = "";

	// Buffer-Einstellungen
	private String bRRSAddress = "";

	// LastSaved-Einstellungen
	private String lRRSAddress = "";

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
		rrsLabel = new Label(comp, SWT.NONE);
		rrsLabel.setText("Address of the Reference Resolution System: ");
		rrsText = new Text(comp, SWT.BORDER);
		rrsText.setLayoutData(gridData6);
		rrsText.setText(this.bRRSAddress);
		rrsText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				bRRSAddress = rrsText.getText();
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
		settings.put(this.RRS_ADDRESS, this.bRRSAddress);

		// Last-Saved Werte aktualisieren
		this.lRRSAddress = this.bRRSAddress;

		return settings;
	}

	@Override
	public void setSettings(LinkedHashMap<String, String> settings,
			String settingsName) {
		if (!settings.isEmpty()) {
			if (settingsName.equals("lastSaved")) {
				// Last-Saved laden
				this.lRRSAddress = settings.get(this.RRS_ADDRESS);
				this.bRRSAddress = this.lRRSAddress;
			} else {
				// Defaults laden
				this.dRRSAddress = settings.get(this.RRS_ADDRESS);
			}

		}
	}

	@Override
	public boolean haveSettingsChanged() {
		boolean changed = false;
		// Überprüfen, ob Auditing-Modus oder Auditing-DB geändert wurden
		if (!this.bRRSAddress.equals(this.lRRSAddress)) {
			changed = true;
		}
		return changed;
	}

	@Override
	public void loadSettingsFromBuffer(String settingName) {
		if (settingName.equals("default")) {
			if (!getComposite().isDisposed()) {
				// Default Werte in GUI-Elementen setzen
				rrsText.setText(this.dRRSAddress);
				System.out.println("LOAD FROM Default: "
						+ this.dRRSAddress);
			}
			// Buffer-Werte aktualisieren
			this.bRRSAddress = this.dRRSAddress;
		} else {
			if (settingName.equals("lastSaved")) {
				if (!getComposite().isDisposed()) {
					// Last-Saved Werte in GUI-Elementen setzen
					rrsText.setText(this.lRRSAddress);
					System.out.println("LOAD FROM LastSaved: "
							+ this.lRRSAddress);
				}
				// Buffer-Werte aktualisieren
				this.bRRSAddress = this.lRRSAddress;
			} else {
				if (!getComposite().isDisposed()) {
					// Buffer-Werte in GUI-Elementen setzen
					rrsText.setText(this.bRRSAddress);
					System.out.println("LOAD FROM BUFFER: "
							+ this.bRRSAddress);
				}
			}
		}
	}

	@Override
	public String getParentSettingItem() {
		return "RRS";
	}

	@Override
	public String getSettingItem() {
		return "Address";
	}
}
