package org.eclipse.simpl.rrs.ui.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;
import org.eclipse.simpl.rrs.ui.client.EPR;
import org.eclipse.simpl.rrs.ui.client.ObjectFactory;
import org.eclipse.simpl.rrs.ui.client.RRSClient;
import org.eclipse.simpl.rrs.ui.client.ReferenceParameters;
import org.eclipse.simpl.rrs.ui.client.ReferenceProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class EditReferenceDialog extends TitleAreaDialog {

	private Text name;
	private Text address;
	private CCombo adapter;
	private Text dsAddress;
	private Text userName;
	private Text password;
	private Text statement;
	private EPR reference;

	public EditReferenceDialog(Shell parentShell, EPR reference) {
		super(parentShell);
		this.reference = reference;
	}

	public EPR getReference() {
		return reference;
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Edit an existing reference");
		setMessage("Please edit the data of the selected reference",
				IMessageProvider.INFORMATION);
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		parent.getShell().setImage(
				new Image(parent.getDisplay(), getClass().getResourceAsStream(
						"/icons/edit.png")));

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;

		Label label1 = new Label(parent, SWT.NONE);
		label1.setText("Name *");
		name = new Text(parent, SWT.BORDER);
		if (this.reference.getReferenceParameters() != null
				&& this.reference.getReferenceParameters().getReferenceName() != null) {
			name.setText(this.reference.getReferenceParameters()
					.getReferenceName());
		}

		Label label2 = new Label(parent, SWT.NONE);
		label2.setText("RRS-Address *");
		address = new Text(parent, SWT.BORDER);
		address.setText(RRSUIPlugIn.getDefault().getPreferenceStore()
				.getString("RRS_RET_ADDRESS"));
		address.setEnabled(false);

		Label label3 = new Label(parent, SWT.NONE);
		label3.setText("Adapter *");
		adapter = new CCombo(parent, SWT.BORDER);
		adapter.setItems(RRSClient.getClient().getAvailableRRSAdapters());
		if (this.reference.getReferenceProperties() != null
				&& this.reference.getReferenceProperties() != null
				&& this.reference.getReferenceProperties().getResolutionSystem() != null) {
			adapter.setText(this.reference.getReferenceProperties().getResolutionSystem());
		}
		
		Label label4 = new Label(parent, SWT.NONE);
		label4.setText("DS address *");
		dsAddress = new Text(parent, SWT.BORDER);
		if (this.reference.getReferenceParameters() != null
				&& this.reference.getReferenceParameters().getDsAddress() != null){
			dsAddress.setText(this.reference.getReferenceParameters().getDsAddress());
		}
		
		Label label5 = new Label(parent, SWT.NONE);
		label5.setText("Statement *");
		statement = new Text(parent, SWT.BORDER);
		if (this.reference.getReferenceParameters() != null
				&& this.reference.getReferenceParameters().getStatement() != null){
			statement.setText(this.reference.getReferenceParameters().getStatement());
		}
		
		Label label6 = new Label(parent, SWT.NONE);
		label6.setText("User");
		userName = new Text(parent, SWT.BORDER);
		if (this.reference.getReferenceParameters() != null
				&& this.reference.getReferenceParameters().getUserName() != null){
			userName.setText(this.reference.getReferenceParameters().getUserName());
		}
		
		Label label7 = new Label(parent, SWT.NONE);
		label7.setText("Password");
		password = new Text(parent, SWT.BORDER);
		if (this.reference.getReferenceParameters() != null
				&& this.reference.getReferenceParameters().getPassword() != null){
			password.setText(this.reference.getReferenceParameters().getPassword());
		}

		name.setLayoutData(gridData);
		address.setLayoutData(gridData);
		adapter.setLayoutData(gridData);
		dsAddress.setLayoutData(gridData);
		userName.setLayoutData(gridData);
		password.setLayoutData(gridData);
		statement.setLayoutData(gridData);

		return parent;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;

		Button button = new Button(parent, SWT.PUSH);
		button.setText("Save");
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!name.getText().isEmpty() && !address.getText().isEmpty()
						&& !adapter.getText().isEmpty()
						&& !dsAddress.getText().isEmpty()
						&& !statement.getText().isEmpty()) {
					/*
					 * Saving the values in the EPR
					 */
					// Create a new EPR
					ObjectFactory factory = new ObjectFactory();
					reference = factory.createEPR();
					ReferenceParameters param1 = factory
							.createReferenceParameters();
					ReferenceProperties prop1 = factory
							.createReferenceProperties();
					prop1.setResolutionSystem(adapter.getText());
					param1.setReferenceName(name.getText());
					param1.setDsAddress(dsAddress.getText());
					param1.setStatement(statement.getText());
					param1.setUserName(userName.getText());
					param1.setPassword(password.getText());
					reference.setAddress(address.getText());
					reference.setReferenceParameters(param1);
					reference.setReferenceProperties(prop1);
					close();

				} else {
					setErrorMessage("Please enter a value for every attribute of the reference");
				}
			}
		});
	}

	@Override
	public boolean isHelpAvailable() {
		return false;
	}

}
