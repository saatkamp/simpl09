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

public class SIMPLCoreAddressComposite extends ASettingsComposite {
	// Global hinterlegte Keys der Einstellungen
	private final String SIMPL_CORE_ADDRESS = "SIMPL_CORE_ADDRESS";

	private Label simplCoreLabel = null;
	private Text simplCoreText = null;

	// Default-Einstellungen
	private String dSimplCoreAddress = "";

	// Buffer-Einstellungen
	private String bSimplCoreAddress = "";

	// LastSaved-Einstellungen
	private String lSimplCoreAddress = "";

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
		simplCoreLabel = new Label(comp, SWT.NONE);
		simplCoreLabel.setText("Address of the SIMPL Core: ");
		simplCoreText = new Text(comp, SWT.BORDER);
		simplCoreText.setLayoutData(gridData6);
		simplCoreText.setText(this.bSimplCoreAddress);
		simplCoreText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				bSimplCoreAddress = simplCoreText.getText();
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
		settings.put(this.SIMPL_CORE_ADDRESS, this.bSimplCoreAddress);

		// Last-Saved Werte aktualisieren
		this.lSimplCoreAddress = this.bSimplCoreAddress;

		return settings;
	}

	@Override
	public void setSettings(LinkedHashMap<String, String> settings,
			String settingsName) {
		if (!settings.isEmpty()) {
			if (settingsName.equals("lastSaved")) {
				// Last-Saved laden
				this.lSimplCoreAddress = settings.get(this.SIMPL_CORE_ADDRESS);
				this.bSimplCoreAddress = this.lSimplCoreAddress;
			} else {
				// Defaults laden
				this.dSimplCoreAddress = settings.get(this.SIMPL_CORE_ADDRESS);
			}

		}
	}

	@Override
	public boolean haveSettingsChanged() {
		boolean changed = false;
		// Überprüfen, ob Auditing-Modus oder Auditing-DB geändert wurden
		if (!this.bSimplCoreAddress.equals(this.lSimplCoreAddress)) {
			changed = true;
		}
		return changed;
	}

	@Override
	public void loadSettingsFromBuffer(String settingName) {
		if (settingName.equals("default")) {
			if (!getComposite().isDisposed()) {
				// Default Werte in GUI-Elementen setzen
				simplCoreText.setText(this.dSimplCoreAddress);
				System.out.println("LOAD FROM Default: "
						+ this.dSimplCoreAddress);
			}
			// Buffer-Werte aktualisieren
			this.bSimplCoreAddress = this.dSimplCoreAddress;
		} else {
			if (settingName.equals("lastSaved")) {
				if (!getComposite().isDisposed()) {
					// Last-Saved Werte in GUI-Elementen setzen
					simplCoreText.setText(this.lSimplCoreAddress);
					System.out.println("LOAD FROM LastSaved: "
							+ this.lSimplCoreAddress);
				}
				// Buffer-Werte aktualisieren
				this.bSimplCoreAddress = this.lSimplCoreAddress;
			} else {
				if (!getComposite().isDisposed()) {
					// Buffer-Werte in GUI-Elementen setzen
					simplCoreText.setText(this.bSimplCoreAddress);
					System.out.println("LOAD FROM BUFFER: "
							+ this.bSimplCoreAddress);
				}
			}
		}
	}

	@Override
	public String getParentSettingItem() {
		return "SIMPL Core";
	}

	@Override
	public String getSettingItem() {
		return "Address";
	}
}