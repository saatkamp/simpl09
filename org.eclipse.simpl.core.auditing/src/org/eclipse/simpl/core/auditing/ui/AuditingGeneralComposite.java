package org.eclipse.simpl.core.auditing.ui;

import java.util.LinkedHashMap;

import org.eclipse.simpl.communication.SIMPLCoreCommunication;
import org.eclipse.simpl.core.extensions.AAdminConsoleComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.simpl.resource.management.data.DataSource;

public class AuditingGeneralComposite extends AAdminConsoleComposite {
	// Global hinterlegte Keys der Einstellungen
	private final String MODE = "MODE";
	private final String AUDITING_DS_NAME = "AUDITING_DS_NAME";
	private final String AUDITING_DS_ADDRESS = "AUDITING_DS_ADDRESS";
	private final String DS_TYPE = "DS_TYPE";
	private final String DS_SUBTYPE = "DS_SUBTYPE";
	private final String DS_FORMAT = "DS_FORMAT";
	private final String DS_USER = "DS_USER";
	private final String DS_PASSWORD = "DS_PASSWORD";

	private Label auditingLabel = null;
	private Button auditingCheckBox = null;
	private Label auditingDBlabel = null;
	private CCombo auditingDB = null;
	private Label addressLabel = null;
	private Label typelabel = null;
	private Label subtypeLabel = null;
	private Label formatLabel = null;
	private Label userLabel = null;
	private Label passwordLabel = null;
	private Text addressText = null;
	private Text typeText = null;
	private Text subtypeText = null;
	private Text formatText = null;
	private Text userNameText = null;
	private Text passwordText = null;

	// Default-Einstellungen
	private String dMode = "inactive";
	private String dAuditingDSName = "";
	private String dAuditingDSAddress = "";
	private String dTypeOfDS = "";
	private String dSubtypeOfDS = "";
	private String dFormatOfDS = "";
	private String dUserNameOfDS = "";
	private String dPasswordOfDS = "";

	// Buffer-Einstellungen
	private String bMode = "inactive";
	private String bAuditingDSName = "";
	private String bAuditingDSAddress = "";
	private String bTypeOfDS = "";
	private String bSubtypeOfDS = "";
	private String bFormatOfDS = "";
	private String bUserNameOfDS = "";
	private String bPasswordOfDS = "";

	private DataSource dataSource = null;

	// LastSaved-Einstellungen
	private String lMode = "";
	private String lAuditingDSName = "";
	private String lAuditingDSAddress = "";
	private String lTypeOfDS = "";
	private String lSubtypeOfDS = "";
	private String lFormatOfDS = "";
	private String lUserNameOfDS = "";
	private String lPasswordOfDS = "";

	@Override
	public void createComposite(Composite composite) {

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite comp = new Composite(composite, SWT.NONE);
		GridData gridDataComp = new GridData();
		gridDataComp.horizontalAlignment = GridData.FILL;
		gridDataComp.grabExcessHorizontalSpace = true;
		gridDataComp.verticalAlignment = GridData.CENTER;

		comp.setLayout(gridLayout);
		comp.setLayoutData(gridDataComp);

		setComposite(comp);

		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = GridData.FILL;
		gridData6.grabExcessHorizontalSpace = true;
		gridData6.verticalAlignment = GridData.CENTER;
		GridData gridData5 = new GridData();
		gridData5.horizontalAlignment = GridData.FILL;
		gridData5.grabExcessHorizontalSpace = true;
		gridData5.verticalAlignment = GridData.CENTER;
		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = GridData.FILL;
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.verticalAlignment = GridData.CENTER;
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.verticalAlignment = GridData.CENTER;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = GridData.CENTER;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.CENTER;
		auditingLabel = new Label(comp, SWT.NONE);
		auditingLabel.setText("Activate or deactivate the Auditing:");
		auditingCheckBox = new Button(comp, SWT.CHECK);
		auditingCheckBox.setText(this.bMode);
		if (this.bMode.equals("active")) {
			auditingCheckBox.setSelection(true);
		} else {
			auditingCheckBox.setSelection(false);
		}

		auditingDBlabel = new Label(comp, SWT.NONE);
		auditingDBlabel.setText("Choose a data source: ");
		auditingDB = new CCombo(comp, SWT.BORDER);
		auditingDB.setLayoutData(gridData6);
		auditingDB.setText(this.bAuditingDSName);
		auditingDB.setEditable(false);
		auditingDB.setItems(AuditingDSUtils.getRFDataSources());
		auditingDB.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				bAuditingDSName = auditingDB.getText();
				dataSource = AuditingDSUtils
						.getDataSourceByName(bAuditingDSName);
				setInfoTextFields(dataSource);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
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
		addressLabel = new Label(comp, SWT.NONE);
		addressLabel.setText("Address of the data source:");
		addressText = new Text(comp, SWT.BORDER);
		addressText.setLayoutData(gridData);
		addressText.setText(this.bAuditingDSAddress);
		addressText.setEnabled(false);

		typelabel = new Label(comp, SWT.NONE);
		typelabel.setText("Type of the data source:");
		typeText = new Text(comp, SWT.BORDER);
		typeText.setLayoutData(gridData);
		typeText.setText(this.bTypeOfDS);
		typeText.setEnabled(false);

		subtypeLabel = new Label(comp, SWT.NONE);
		subtypeLabel.setText("Subtype of the data source:");
		subtypeText = new Text(comp, SWT.BORDER);
		subtypeText.setLayoutData(gridData2);
		subtypeText.setText(this.bSubtypeOfDS);
		subtypeText.setEnabled(false);

		formatLabel = new Label(comp, SWT.NONE);
		formatLabel.setText("Format of the data source:");
		formatText = new Text(comp, SWT.BORDER);
		formatText.setLayoutData(gridData3);
		formatText.setText(this.bFormatOfDS);
		formatText.setEnabled(false);

		userLabel = new Label(comp, SWT.NONE);
		userLabel.setText("User name:");
		userNameText = new Text(comp, SWT.BORDER);
		userNameText.setLayoutData(gridData4);
		userNameText.setText(this.bUserNameOfDS);
		userNameText.setEnabled(false);

		passwordLabel = new Label(comp, SWT.NONE);
		passwordLabel.setText("Password:");
		passwordText = new Text(comp, SWT.BORDER | SWT.PASSWORD);
		passwordText.setLayoutData(gridData5);
		passwordText.setText(this.bPasswordOfDS);
		passwordText.setEnabled(false);
	}

	/**
	 * @param dataSource
	 */
	protected void setInfoTextFields(DataSource dataSource) {
		addressText.setText(dataSource.getAddress());
		typeText.setText(dataSource.getType());
		subtypeText.setText(dataSource.getSubType());
		formatText.setText(dataSource.getConnector().getConverterDataFormat().getName());
		userNameText.setText(dataSource.getAuthentication().getUser());
		passwordText.setText(dataSource.getAuthentication().getPassword());
		bAuditingDSAddress = addressText.getText();
		bTypeOfDS = typeText.getText();
		bSubtypeOfDS = subtypeText.getText();
		bFormatOfDS = formatText.getText();
		bUserNameOfDS = userNameText.getText();
		bPasswordOfDS = passwordText.getText();
	}

	@Override
	public void saveSettings(String parentItem, String item, String settingName) {
		// Überprüfen, ob mindestens ein Wert geändert wurde
		if (haveSettingsChanged()) {
			// Settings-Liste erstellen und mit Werte füllen zum Speichern
			LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

			// Werte aus den Buffervariablen einfügen
			settings.put(this.MODE, this.bMode);
			settings.put(this.AUDITING_DS_NAME, this.bAuditingDSName);
			settings.put(this.AUDITING_DS_ADDRESS, this.bAuditingDSAddress);
			settings.put(this.DS_TYPE, this.bTypeOfDS);
			settings.put(this.DS_SUBTYPE, this.bSubtypeOfDS);
			settings.put(this.DS_FORMAT, this.bFormatOfDS);
			settings.put(this.DS_USER, this.bUserNameOfDS);
			settings.put(this.DS_PASSWORD, this.bPasswordOfDS);

			// Über den SIMPL Core in einer embedded DerbyDB speichern
			SIMPLCoreCommunication.getInstance().save(parentItem, item,
					settingName, settings);

			// Last-Saved Werte aktualisieren
			this.lAuditingDSName = this.bAuditingDSName;
			this.lAuditingDSAddress = this.bAuditingDSAddress;
			this.lMode = this.bMode;
			this.lTypeOfDS = this.bTypeOfDS;
			this.lSubtypeOfDS = this.bSubtypeOfDS;
			this.lFormatOfDS = this.bFormatOfDS;
			this.lUserNameOfDS = this.bUserNameOfDS;
			this.lPasswordOfDS = this.bPasswordOfDS;
		}
	}

	@Override
	public void loadSettings(String parentItem, String item) {
		// Settings-Liste erstellen
		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

		// LastSaved-Einstellungen aus SIMPL Core DB laden
		settings = SIMPLCoreCommunication.getInstance().load(parentItem, item,
				"lastSaved");

		if (!settings.isEmpty()) {
			this.lMode = settings.get(this.MODE).isEmpty() ? "inactive"
					: settings.get(this.MODE);
			this.lAuditingDSName = settings.get(this.AUDITING_DS_NAME);
			this.lAuditingDSAddress = settings.get(this.AUDITING_DS_ADDRESS);
			this.lTypeOfDS = settings.get(this.DS_TYPE);
			this.lSubtypeOfDS = settings.get(this.DS_SUBTYPE);
			this.lFormatOfDS = settings.get(this.DS_FORMAT);
			this.lUserNameOfDS = settings.get(this.DS_USER);
			this.lPasswordOfDS = settings.get(this.DS_PASSWORD);

			this.bMode = this.lMode;
			this.bAuditingDSName = this.lAuditingDSName;
			this.bAuditingDSAddress = this.lAuditingDSAddress;
			this.bTypeOfDS = this.lTypeOfDS;
			this.bSubtypeOfDS = this.lSubtypeOfDS;
			this.bFormatOfDS = this.lFormatOfDS;
			this.bUserNameOfDS = this.lUserNameOfDS;
			this.bPasswordOfDS = this.lPasswordOfDS;
		} else {
			DataSource data = AuditingDSUtils.getLocalDataSource();
			this.bAuditingDSName = data.getName();
			this.bAuditingDSAddress = data.getAddress();
			this.bTypeOfDS = data.getType();
			this.bSubtypeOfDS = data.getSubType();
			
			if (data.getConnector() != null) {
			  this.bFormatOfDS = data.getConnector().getConverterDataFormat().getName();
			} else {
			  this.bFormatOfDS = "";
			}
			
			this.bUserNameOfDS = "";
			this.bPasswordOfDS = "";
		}

		// Default-Einstellungen aus SIMPL Core DB laden
		settings = SIMPLCoreCommunication.getInstance().load(parentItem, item,
				"default");

		if (!settings.isEmpty()) {
			this.dMode = settings.get(this.MODE);
			this.dAuditingDSName = settings.get(this.AUDITING_DS_NAME);
			this.dAuditingDSAddress = settings.get(this.AUDITING_DS_ADDRESS);
			this.dTypeOfDS = settings.get(this.DS_TYPE);
			this.dSubtypeOfDS = settings.get(this.DS_SUBTYPE);
			this.dFormatOfDS = settings.get(this.DS_FORMAT);
			this.dUserNameOfDS = settings.get(this.DS_USER);
			this.dPasswordOfDS = settings.get(this.DS_PASSWORD);
		}
	}

	@Override
	public boolean haveSettingsChanged() {
		boolean changed = false;
		// Überprüfen, ob Auditing-Modus oder Auditing-DB geändert wurden
		if (!this.bMode.equals(this.lMode)
				|| !this.bAuditingDSAddress.equals(this.lAuditingDSAddress)
				|| !this.bTypeOfDS.equals(this.lTypeOfDS)
				|| !this.bSubtypeOfDS.equals(this.lSubtypeOfDS)
				|| !this.bFormatOfDS.equals(this.lFormatOfDS)
				|| !this.bUserNameOfDS.equals(this.lUserNameOfDS)
				|| !this.bPasswordOfDS.equals(this.lPasswordOfDS)) {
			changed = true;
		}
		return changed;
	}

	@Override
	public void loadSettingsFromBuffer(String settingName) {
		if (settingName.equals("default")) {
			if (getComposite() != null && !getComposite().isDisposed()) {

				// Default-Werte in GUI-Elementen setzen
				if (this.dMode.equals("active")) {
					auditingCheckBox.setSelection(true);
					auditingCheckBox.setText("active");
				} else {
					auditingCheckBox.setSelection(false);
					auditingCheckBox.setText("inactive");
				}
				auditingDB.setText(this.dAuditingDSName);
				addressText.setText(this.dAuditingDSAddress);
				typeText.setText(this.dTypeOfDS);
				subtypeText.setText(this.dSubtypeOfDS);
				formatText.setText(this.dFormatOfDS);
				userNameText.setText(this.dUserNameOfDS);
				passwordText.setText(this.dPasswordOfDS);
			}
			// Buffer-Werte aktualisieren
			this.bAuditingDSName = this.dAuditingDSName;
			this.bAuditingDSAddress = this.dAuditingDSAddress;
			this.bMode = this.dMode;
			this.bTypeOfDS = this.dTypeOfDS;
			this.bSubtypeOfDS = this.dSubtypeOfDS;
			this.bFormatOfDS = this.dFormatOfDS;
			this.bUserNameOfDS = this.dUserNameOfDS;
			this.bPasswordOfDS = this.dPasswordOfDS;
		} else {
			if (settingName.equals("lastSaved")) {
				if (getComposite() != null && !getComposite().isDisposed()) {
					// Last-Saved Werte in GUI-Elementen setzen
					if (this.lMode.equals("active")) {
						auditingCheckBox.setSelection(true);
						auditingCheckBox.setText("active");
					} else {
						auditingCheckBox.setSelection(false);
						auditingCheckBox.setText("inactive");
					}
					auditingDB.setText(this.lAuditingDSName);
					addressText.setText(this.lAuditingDSAddress);
					typeText.setText(this.lTypeOfDS);
					subtypeText.setText(this.lSubtypeOfDS);
					formatText.setText(this.lFormatOfDS);
					userNameText.setText(this.lUserNameOfDS);
					passwordText.setText(this.lPasswordOfDS);
				}
				// Buffer-Werte aktualisieren
				this.bAuditingDSName = this.lAuditingDSName;
				this.bAuditingDSAddress = this.lAuditingDSAddress;
				this.bMode = this.lMode;
				this.bTypeOfDS = this.lTypeOfDS;
				this.bSubtypeOfDS = this.lSubtypeOfDS;
				this.bFormatOfDS = this.lFormatOfDS;
				this.bUserNameOfDS = this.lUserNameOfDS;
				this.bPasswordOfDS = this.lPasswordOfDS;
			} else {
				if (getComposite() != null && !getComposite().isDisposed()) {
					// Buffer-Werte in GUI-Elementen setzen
					if (this.bMode.equals("active")) {
						auditingCheckBox.setSelection(true);
						auditingCheckBox.setText("active");
					} else {
						auditingCheckBox.setSelection(false);
						auditingCheckBox.setText("inactive");
					}

					if (this.bAuditingDSName.isEmpty()) {
						DataSource data = AuditingDSUtils.getLocalDataSource();
						this.bAuditingDSName = data.getName();
						this.bAuditingDSAddress = data.getAddress();
						this.bTypeOfDS = data.getType();
						this.bSubtypeOfDS = data.getSubType();
						this.bFormatOfDS = data.getConnector().getConverterDataFormat().getName();
						this.bUserNameOfDS = "";
						this.bPasswordOfDS = "";
					}

					auditingDB.setText(this.bAuditingDSName);
					if (this.bAuditingDSName.equals("local")) {
						this.bAuditingDSAddress = AuditingDSUtils
								.getLocalDataSource().getAddress();
					}
					addressText.setText(this.bAuditingDSAddress);
					typeText.setText(this.bTypeOfDS);
					subtypeText.setText(this.bSubtypeOfDS);
					formatText.setText(this.bFormatOfDS);
					userNameText.setText(this.bUserNameOfDS);
					passwordText.setText(this.bPasswordOfDS);
				}
			}
		}
	}

	@Override
	public String getConsoleItem() {
		// TODO Auto-generated method stub
		return "General";
	}

	@Override
	public String getParentConsoleItem() {
		// TODO Auto-generated method stub
		return "Auditing";
	}
}
