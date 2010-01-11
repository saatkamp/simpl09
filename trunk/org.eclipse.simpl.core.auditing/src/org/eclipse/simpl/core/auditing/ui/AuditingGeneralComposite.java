package org.eclipse.simpl.core.auditing.ui;

import java.util.LinkedHashMap;
import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.core.extensions.AAdminConsoleComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AuditingGeneralComposite extends AAdminConsoleComposite {

	private Label auditingLabel = null;
	private Button auditingCheckBox = null;
	private Label auditingDBlabel = null;
	private Text auditingDBtext = null;

	// Default-Einstellungen
	private String dMode = "inactive";
	private String dAuditingDBAddress = "";

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
		auditingCheckBox.setText("inactive");
		Label filler3 = new Label(comp, SWT.NONE);
		auditingDBlabel = new Label(comp, SWT.NONE);
		auditingDBlabel.setText("Address of the Auditing database: ");
		auditingDBtext = new Text(comp, SWT.BORDER);
		auditingDBtext.setLayoutData(gridData6);
		auditingCheckBox.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent event) {
				if (auditingCheckBox.getSelection()) {
					auditingCheckBox.setText("active");
				} else {
					auditingCheckBox.setText("inactive");
				}
			}

			public void widgetDefaultSelected(SelectionEvent event) {
				auditingCheckBox.setText("inactive");
			}
		});
	}

	@Override
	public void saveSettings(String parentItem, String item, String settingName) {
		// Überprüfen, ob mindestens ein Wert geändert wurde

		// Settings-Liste erstellen und mit Werte füllen zum Speichern
		LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

		// Werte aus den GUI-Elementen in die HashMap einfügen
		if (auditingCheckBox.getSelection()) {
			settings.put("mode", auditingCheckBox.getText());
		}
		settings.put("auditingDsAddress", auditingDBtext.getText());

		// Über den SIMPL Core in einer embedded DerbyDB speichern
		SIMPLCommunication.getConnection().save(parentItem, item, settingName,
				settings);

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
			if (this.dMode.equals("active")) {
				auditingCheckBox.setSelection(true);
				auditingCheckBox.setText("active");
			} else {
				auditingCheckBox.setSelection(false);
				auditingCheckBox.setText("inactive");
			}
			auditingDBtext.setText(this.dAuditingDBAddress);
		} else {
			// Geladene Werte in GUI-Elementen setzen
			if (settings.get("mode").contains("active")) {
				auditingCheckBox.setSelection(true);
				auditingCheckBox.setText("active");
			} else {
				auditingCheckBox.setSelection(false);
				auditingCheckBox.setText("inactive");
			}
			auditingDBtext.setText(settings.get("auditingDsAddress"));
		}
	}
}
