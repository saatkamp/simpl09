package org.eclipse.simpl.core.globalsettings.ui;

import java.util.LinkedHashMap;

import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.core.extensions.AAdminConsoleComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class AuthentificationComposite extends AAdminConsoleComposite {

	private Label userLabel = null;
	private Label passwordLabel = null;
	private Text userText = null;
	private Text passwordText = null;

	// Default-Einstellungen
	private String dUser = null;
	private String dPassword = null;

	// LastSaved-Einstellungen
	private String lUser = "";
	private String lPassword = "";

	// Buffer für Einstellungen
	private String bUser = "";
	private String bPassword = "";

	@Override
	public void createComposite(Composite composite) {
		// TODO Auto-generated method stub
		GridData gridData4 = new GridData();
		gridData4.widthHint = 100;
		GridData gridData3 = new GridData();
		gridData3.widthHint = 100;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.BEGINNING;
		gridData1.verticalAlignment = GridData.CENTER;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite comp = new Composite(composite, SWT.NONE);
		comp.setLayout(gridLayout);

		setComposite(comp);

		userLabel = new Label(comp, SWT.NONE);
		userLabel.setText("User:");
		userLabel.setLayoutData(gridData);
		userText = new Text(comp, SWT.BORDER);
		userText.setLayoutData(gridData3);
		passwordLabel = new Label(comp, SWT.NONE);
		passwordLabel.setText("Password:");
		passwordLabel.setLayoutData(gridData1);
		passwordText = new Text(comp, SWT.BORDER);
		passwordText.setEchoChar('*');
		passwordText.setLayoutData(gridData4);
	}

	@Override
	public void saveSettings(String parentItem, String item, String settingName) {
		// Überprüfen, ob mindestens ein Wert geändert wurde
		if (haveSettingsChanged()) {
			// Settings-Liste erstellen und mit Werte füllen zum Speichern
			LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

			// Werte aus den GUI-Elementen in die HashMap einfügen
			settings.put("username", userText.getText());

			// TODO: Passwort bitte nicht für immer als Klartext speichern ;)
			settings.put("password", passwordText.getText());

			// Über den SIMPL Core in einer embedded DerbyDB speichern
			SIMPLCommunication.getConnection().save(parentItem, item,
					settingName, settings);
			
			this.lUser = userText.getText();
			this.lPassword = passwordText.getText();
		}
	}

	@Override
	public void loadSettings(String parentItem, String item, String settingName) {
		// Settings-Liste erstellen und mit geladenen Werten füllen
		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
		// Über den SIMPL Core aus einer embedded DerbyDB laden
		settings = SIMPLCommunication.getConnection().load(parentItem, item,
				settingName);
		if (settings.isEmpty()) {
			// Defaults aus Code laden
			userText.setText(this.dUser);
			passwordText.setText(this.dPassword);
		} else {
			// Geladene Werte in GUI-Elementen setzen
			userText.setText(settings.get("username"));
			passwordText.setText(settings.get("password"));
		}
		
		this.lUser = userText.getText();
		this.lPassword = passwordText.getText();
		this.bUser = userText.getText();
		this.bPassword = passwordText.getText();
	}

	@Override
	public boolean haveSettingsChanged() {
		boolean changed = false;
		// Überprüfen, ob Auditing-Modus oder Auditing-DB geändert wurden
		if (!this.bUser.equals(this.lUser)
				|| !this.bPassword.equals(this.lPassword)) {
			changed = true;
		}
		return changed;
	}

	@Override
	public void loadSettingsFromBuffer() {
		userText.setText(this.bUser);
		passwordText.setText(this.bPassword);
		System.out.println("LOAD FROM BUFFER: " + this.bUser + " | " + this.bPassword);
	}

	@Override
	public void saveSettingsToBuffer() {
		this.bUser = userText.getText();
		this.bPassword = passwordText.getText();
		System.out.println("SAVE TO BUFFER: " + this.bUser + " | " + this.bPassword);
	}

}
