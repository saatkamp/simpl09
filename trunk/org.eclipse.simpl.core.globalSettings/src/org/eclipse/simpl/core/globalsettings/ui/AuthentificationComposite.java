package org.eclipse.simpl.core.globalsettings.ui;

import java.util.LinkedHashMap;

import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.core.extensions.AAdminConsoleComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class AuthentificationComposite extends AAdminConsoleComposite {

	// Global hinterlegte Keys der Einstellungen
	private final String USER = "username";
	private final String PASSWORD = "password";

	private Label userLabel = null;
	private Label passwordLabel = null;
	private Text userText = null;
	private Text passwordText = null;

	// Default-Einstellungen
	private String dUser = null;
	private String dPassword = null;

	// Buffer-Einstellungen
	private String bUser = null;
	private String bPassword = null;

	// LastSaved-Einstellungen
	private String lUser = null;
	private String lPassword = null;

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
		userText.setText(this.bUser);
		userText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				bUser = userText.getText();
			}
		});

		passwordLabel = new Label(comp, SWT.NONE);
		passwordLabel.setText("Password:");
		passwordLabel.setLayoutData(gridData1);
		passwordText = new Text(comp, SWT.BORDER);
		passwordText.setEchoChar('*');
		passwordText.setLayoutData(gridData4);
		passwordText.setText(this.bPassword);
		passwordText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				bPassword = passwordText.getText();
			}
		});
	}

	@Override
	public void saveSettings(String parentItem, String item, String settingName) {
		// Überprüfen, ob mindestens ein Wert geändert wurde
		if (haveSettingsChanged()) {

			if (getComposite() != null) {
				// Settings-Liste erstellen und mit Werte füllen zum Speichern
				LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

				// Werte aus den Buffervariablen in die HashMap einfügen
				settings.put(this.USER, this.bUser);

				// TODO: Passwort bitte nicht für immer als Klartext speichern
				// ;)
				settings.put(this.PASSWORD, this.bPassword);

				// Über den SIMPL Core in einer embedded DerbyDB speichern
				SIMPLCommunication.getConnection().save(parentItem, item,
						settingName, settings);
			}
		}
	}

	@Override
	public void loadSettings(String parentItem, String item, String settingName) {
		// Settings-Liste erstellen und mit geladenen Werten füllen
		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
		// Über den SIMPL Core aus einer embedded DerbyDB laden
		settings = SIMPLCommunication.getConnection().load(parentItem, item,
				settingName);
		// Überprüfen, ob das Composite schon erzeugt wurde
		if (getComposite() != null) {
			if (settings.isEmpty()) {
				// Defaults aus Code laden
				userText.setText(this.dUser);
				passwordText.setText(this.dPassword);
			} else {
				// Geladene Werte in GUI-Elementen setzen
				userText.setText(settings.get(this.USER));
				passwordText.setText(settings.get(this.PASSWORD));
			}
			this.lUser = userText.getText();
			this.lPassword = passwordText.getText();
			this.bUser = userText.getText();
			this.bPassword = passwordText.getText();
		} else {
			this.lUser = settings.get(this.USER);
			this.lPassword = settings.get(this.PASSWORD);
			this.bUser = this.lUser;
			this.bPassword = this.lPassword;
		}
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
	public void loadSettingsFromBuffer(String settingName) {
		userText.setText(this.bUser);
		passwordText.setText(this.bPassword);
		System.out.println("LOAD FROM BUFFER: " + this.bUser + " | "
				+ this.bPassword);
	}

	@Override
	public String getConsoleItem() {
		// TODO Auto-generated method stub
		return "AuthentificationData";
	}

	@Override
	public String getParentConsoleItem() {
		// TODO Auto-generated method stub
		return "GlobalSettings";
	}

}
