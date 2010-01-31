package org.eclipse.simpl.settings.ui.test;

import java.util.LinkedHashMap;

import org.eclipse.simpl.settings.extensions.ASettingsComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TestComposite extends ASettingsComposite {
	// Global hinterlegte Keys der Einstellungen
	private final String MODE = "MODE";
	private final String AUDITING_DS_ADDRESS = "AUDITING_DS_ADDRESS";

	private Label auditingLabel = null;
	private Button auditingCheckBox = null;
	private Label auditingDBlabel = null;
	private Text auditingDBtext = null;

	// Default-Einstellungen
	private String dMode = "";
	private String dAuditingDSAddress = "";

	// Buffer-Einstellungen
	private String bMode = "";
	private String bAuditingDSAddress = "";

	// LastSaved-Einstellungen
	private String lMode = "";
	private String lAuditingDSAddress = "";

	@Override
	public void createComposite(Composite composite) {
		// TODO Auto-generated method stub
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		Composite comp = new Composite(composite, SWT.NONE);
		comp.setLayout(gridLayout);

		setComposite(comp);

		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 2;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.horizontalSpan = 4;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.FILL;
		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = GridData.FILL;
		gridData6.grabExcessHorizontalSpace = true;
		gridData6.verticalAlignment = GridData.CENTER;
		auditingLabel = new Label(comp, SWT.NONE);
		auditingLabel.setText("Activate or deactivate the Auditing:");
		auditingCheckBox = new Button(comp, SWT.CHECK);
		auditingCheckBox.setText(this.bMode);
		if (this.bMode.contains("active")) {
			auditingCheckBox.setSelection(true);
		} else {
			auditingCheckBox.setSelection(false);
		}
		Label filler3 = new Label(comp, SWT.NONE);
		auditingDBlabel = new Label(comp, SWT.NONE);
		auditingDBlabel.setText("Address of the Auditing database: ");
		auditingDBtext = new Text(comp, SWT.BORDER);
		auditingDBtext.setLayoutData(gridData6);
		auditingDBtext.setText(this.bAuditingDSAddress);
		auditingDBtext.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				bAuditingDSAddress = auditingDBtext.getText();
			}
		});
		auditingCheckBox.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent event) {
				if (auditingCheckBox.getSelection()) {
					auditingCheckBox.setText("active");
				} else {
					auditingCheckBox.setText("inactive");
				}
				bMode = auditingCheckBox.getText();
			}

			public void widgetDefaultSelected(SelectionEvent event) {
				widgetSelected(event);
			}
		});
	}

	@Override
	public void saveSettings(String parentItem, String item, String settingName) {
		// Überprüfen, ob mindestens ein Wert geändert wurde
		if (haveSettingsChanged()) {
			// Settings-Liste erstellen und mit Werte füllen zum Speichern
			LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

			// Werte aus den Buffervariablen einfügen
			settings.put(this.MODE, this.bMode);
			settings.put(this.AUDITING_DS_ADDRESS, this.bAuditingDSAddress);

			//TODO: Speichern

			// Last-Saved Werte aktualisieren
			this.lAuditingDSAddress = this.bAuditingDSAddress;
			this.lMode = this.bMode;
		}
	}

	@Override
	public void loadSettings(String parentItem, String item) {
		// Settings-Liste erstellen
		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

		//TODO: Laden
		settings.put(this.MODE, "active");
		settings.put(this.AUDITING_DS_ADDRESS, "");

		if (!settings.isEmpty()) {
			this.lMode = settings.get(this.MODE);
			this.lAuditingDSAddress = settings.get(this.AUDITING_DS_ADDRESS);
			this.bMode = this.lMode;
			this.bAuditingDSAddress = this.lAuditingDSAddress;
		}

		//TODO: Defaults-Laden

		if (!settings.isEmpty()) {
			this.dMode = settings.get(this.MODE);
			this.dAuditingDSAddress = settings.get(this.AUDITING_DS_ADDRESS);
		}
	}

	@Override
	public boolean haveSettingsChanged() {
		boolean changed = false;
		// Überprüfen, ob Auditing-Modus oder Auditing-DB geändert wurden
		if (!this.bMode.equals(this.lMode)
				|| !this.bAuditingDSAddress.equals(this.lAuditingDSAddress)) {
			changed = true;
		}
		return changed;
	}

	@Override
	public void loadSettingsFromBuffer(String settingName) {
		if (settingName.equals("default")) {
			if (!getComposite().isDisposed()) {

				// Default-Werte in GUI-Elementen setzen
				if (this.dMode.equals("active")) {
					auditingCheckBox.setSelection(true);
					auditingCheckBox.setText("active");
				} else {
					auditingCheckBox.setSelection(false);
					auditingCheckBox.setText("inactive");
				}
				auditingDBtext.setText(this.dAuditingDSAddress);
				System.out.println("LOAD FROM Default: " + this.dMode + " | "
						+ this.dAuditingDSAddress);
			}
			// Buffer-Werte aktualisieren
			this.bAuditingDSAddress = this.dAuditingDSAddress;
			this.bMode = this.dMode;
		} else {
			if (settingName.equals("lastSaved")) {
				if (!getComposite().isDisposed()) {
					// Last-Saved Werte in GUI-Elementen setzen
					if (this.lMode.equals("active")) {
						auditingCheckBox.setSelection(true);
						auditingCheckBox.setText("active");
					} else {
						auditingCheckBox.setSelection(false);
						auditingCheckBox.setText("inactive");
					}
					auditingDBtext.setText(this.lAuditingDSAddress);
					System.out.println("LOAD FROM LastSaved: " + this.lMode
							+ " | " + this.lAuditingDSAddress);
				}
				// Buffer-Werte aktualisieren
				this.bAuditingDSAddress = this.lAuditingDSAddress;
				this.bMode = this.lMode;
			} else {
				if (!getComposite().isDisposed()) {
					// Buffer-Werte in GUI-Elementen setzen
					if (this.bMode.equals("active")) {
						auditingCheckBox.setSelection(true);
						auditingCheckBox.setText("active");
					} else {
						auditingCheckBox.setSelection(false);
						auditingCheckBox.setText("inactive");
					}
					auditingDBtext.setText(this.bAuditingDSAddress);
					System.out.println("LOAD FROM BUFFER: " + this.bMode
							+ " | " + this.bAuditingDSAddress);
				}
			}
		}
	}

	@Override
	public String getConsoleItem() {
		// TODO Auto-generated method stub
		return "Test";
	}

	@Override
	public String getParentConsoleItem() {
		// TODO Auto-generated method stub
		return "test";
	}
}