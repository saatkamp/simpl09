package org.eclipse.simpl.resource.management.adminconsole;

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

public class ResourceManagementSettingsComposite extends AAdminConsoleComposite {
	// Global hinterlegte Keys der Einstellungen
	private final String ADDRESS = "ADDRESS";

	private Label addressLabel = null;
	private Text addressText = null;

	// Default-Einstellungen
	private String dAddress = "http://localhost:8080/axis2/services/ResourceManagementService.ResourceManagementPort?wsdl";

	// Buffer-Einstellungen
	private String bAddress = "";

	// LastSaved-Einstellungen
	private String lAddress = "";

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

		addressLabel = new Label(comp, SWT.NONE);
		addressLabel.setText("Address of the Resource Management:");
		addressText = new Text(comp, SWT.BORDER);
		addressText.setLayoutData(gridData);
		addressText.setText(this.bAddress);
    addressText.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent e) {
        bAddress = addressText.getText();
      }
    });
	}

	/**
	 * @param dataSource
	 */
	protected void setInfoTextFields(String address) {
		addressText.setText(address);
	}

	@Override
	public void saveSettings(String parentItem, String item, String settingName) {
		// Überprüfen, ob mindestens ein Wert geändert wurde
		if (haveSettingsChanged()) {
			// Settings-Liste erstellen und mit Werte füllen zum Speichern
			LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

			// Werte aus den Buffervariablen einfügen
			settings.put(this.ADDRESS, this.bAddress);

			// Über den SIMPL Core in einer embedded DerbyDB speichern
			SIMPLCoreCommunication.getInstance().save(parentItem, item,
					settingName, settings);

			// Last-Saved Werte aktualisieren
			this.lAddress = this.bAddress;
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
			this.lAddress = settings.get(this.ADDRESS);
			this.bAddress = this.lAddress;
		} else {
			this.bAddress = "http://localhost:8080/axis2/services/ResourceManagementService.ResourceManagementPort?wsdl";
		}

		// Default-Einstellungen aus SIMPL Core DB laden
		settings = SIMPLCoreCommunication.getInstance().load(parentItem, item,
				"default");

		if (!settings.isEmpty()) {
			this.dAddress = settings.get(this.ADDRESS);
		}
	}

	@Override
	public boolean haveSettingsChanged() {
		boolean changed = false;
		// Überprüfen, ob Werte geändert wurden
		if (!this.bAddress.equals(this.lAddress)) {
			changed = true;
		}
		return changed;
	}

	@Override
	public void loadSettingsFromBuffer(String settingName) {
		if (settingName.equals("default")) {
			if (getComposite() != null && !getComposite().isDisposed()) {

				// Default-Werte in GUI-Elementen setzen
				addressText.setText(this.dAddress);
			}
			
			// Buffer-Werte aktualisieren
			this.bAddress = this.dAddress;
		} else {
			if (settingName.equals("lastSaved")) {
				if (getComposite() != null && !getComposite().isDisposed()) {
					// Last-Saved Werte in GUI-Elementen setzen
					addressText.setText(this.lAddress);
				}
				// Buffer-Werte aktualisieren
				this.bAddress = this.lAddress;
			} else {
				if (getComposite() != null && !getComposite().isDisposed()) {
					// Buffer-Werte in GUI-Elementen setzen
					addressText.setText(this.bAddress);
				}
			}
		}
	}

	@Override
	public String getConsoleItem() {
		// TODO Auto-generated method stub
		return "Settings";
	}

	@Override
	public String getParentConsoleItem() {
		// TODO Auto-generated method stub
		return "ResourceManagement";
	}
}