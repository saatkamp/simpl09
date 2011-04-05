package org.eclipse.simpl.core.auditing.ui;

import java.util.LinkedHashMap;

import org.eclipse.simpl.communication.SIMPLCoreCommunication;
import org.eclipse.simpl.core.extensions.AAdminConsoleComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataFormat;
import org.simpl.resource.management.data.DataSource;

public class AuditingLocalDsComposite extends AAdminConsoleComposite {
	// Global hinterlegte Keys der Einstellungen
	private final String AUDITING_DS_ADDRESS = "AUDITING_DS_ADDRESS";

	private Label auditingDBlabel = null;
	private Text auditingDB = null;
	private Label addressLabel = null;
	private Label typelabel = null;
	private Label subtypeLabel = null;
	private Label formatLabel = null;
	private Text addressText = null;
	private Text typeText = null;
	private Text subtypeText = null;
	private Text formatText = null;

	// Buffer-Einstellungen
	private String bAuditingDSAddress = "";
	
	// LastSaved-Einstellungen
	private String lAuditingDSAddress = "";
	
	private DataSource dataSource = new DataSource();

	@Override
	public void createComposite(Composite composite) {
    Connector connector = new Connector();
    DataFormat dataFormat = new DataFormat();
    connector.setConverterDataFormat(dataFormat);	  
	  dataSource.setConnector(connector);
    
		dataSource.setName("local");
		dataSource.setType("Database");
		dataSource.setSubType("EmbeddedDerby");
		dataSource.getConnector().getConverterDataFormat().setName("RDBDataFormat");
		Authentication auth = new Authentication();
		auth.setUser("");
		auth.setPassword("");
		dataSource.setAuthentication(auth);
		
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

		auditingDBlabel = new Label(comp, SWT.NONE);
		auditingDBlabel.setText("Name of the default data source: ");
		auditingDB = new Text(comp, SWT.BORDER);
		auditingDB.setLayoutData(gridData6);
		auditingDB.setText(dataSource.getName());
		auditingDB.setEnabled(false);
		
		addressLabel = new Label(comp, SWT.NONE);
		addressLabel.setText("Address of the data source:");
		addressText = new Text(comp, SWT.BORDER);
		addressText.setLayoutData(gridData);
		addressText.setText(this.bAuditingDSAddress);
		addressText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				bAuditingDSAddress = addressText.getText();
				dataSource.setAddress(bAuditingDSAddress);
				AuditingDSUtils.setLocalDataSource(dataSource);
			}
		});
		
		typelabel = new Label(comp, SWT.NONE);
		typelabel.setText("Type of the data source:");
		typeText = new Text(comp, SWT.BORDER);
		typeText.setLayoutData(gridData);
		typeText.setText(dataSource.getType());
		typeText.setEnabled(false);
		
		subtypeLabel = new Label(comp, SWT.NONE);
		subtypeLabel.setText("Subtype of the data source:");
		subtypeText = new Text(comp, SWT.BORDER);
		subtypeText.setLayoutData(gridData2);
		subtypeText.setText(dataSource.getSubType());
		subtypeText.setEnabled(false);

		formatLabel = new Label(comp, SWT.NONE);
		formatLabel.setText("Format of the data source:");
		formatText = new Text(comp, SWT.BORDER);
		formatText.setLayoutData(gridData3);
		formatText.setText(dataSource.getConnector().getConverterDataFormat().getName());
		formatText.setEnabled(false);
	}

	@Override
	public void saveSettings(String parentItem, String item, String settingName) {
		// Überprüfen, ob mindestens ein Wert geändert wurde
		if (haveSettingsChanged()) {
			// Settings-Liste erstellen und mit Werte füllen zum Speichern
			LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

			// Werte aus den Buffervariablen einfügen
			settings.put(this.AUDITING_DS_ADDRESS, this.bAuditingDSAddress);
			
			// Über den SIMPL Core in einer embedded DerbyDB speichern
			SIMPLCoreCommunication.getInstance().save(parentItem, item,
					settingName, settings);

			// Last-Saved Werte aktualisieren
			this.lAuditingDSAddress = this.bAuditingDSAddress;
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
			this.lAuditingDSAddress = settings.get(this.AUDITING_DS_ADDRESS);
			this.bAuditingDSAddress = this.lAuditingDSAddress;
		}else {
			this.bAuditingDSAddress = "../Tomcat-Root/simplDB";
		}
	}

	@Override
	public boolean haveSettingsChanged() {
		boolean changed = false;
		// Überprüfen, ob Auditing-Modus oder Auditing-DB geändert wurden
		if (!this.bAuditingDSAddress.equals(this.lAuditingDSAddress)) {
			changed = true;
		}
		return changed;
	}

	@Override
	public void loadSettingsFromBuffer(String settingName) {
		if (settingName.equals("default")) {
			if (getComposite() != null && !getComposite().isDisposed()) {
				addressText.setText("../Tomcat-Root/simplDB");
				// Buffer-Werte aktualisieren
				this.bAuditingDSAddress = addressText.getText();
			}
			
		} else {
			if (settingName.equals("lastSaved")) {
				if (getComposite() != null && !getComposite().isDisposed()) {
					// Last-Saved Werte in GUI-Elementen setzen
					addressText.setText(this.lAuditingDSAddress);
				}
				// Buffer-Werte aktualisieren
				this.bAuditingDSAddress = this.lAuditingDSAddress;
				
			} else {
				if (getComposite() != null && !getComposite().isDisposed()) {
					// Buffer-Werte in GUI-Elementen setzen
					addressText.setText(this.bAuditingDSAddress == null || this.bAuditingDSAddress.isEmpty() ? "../Tomcat-root/simplDB" : this.bAuditingDSAddress);
				}
			}
		}
	}

	@Override
	public String getConsoleItem() {
		// TODO Auto-generated method stub
		return "Default data source";
	}

	@Override
	public String getParentConsoleItem() {
		// TODO Auto-generated method stub
		return "Auditing";
	}
	
}
