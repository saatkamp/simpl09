package org.eclipse.simpl.rrs.ui.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.simpl.rrs.model.rrs.EPR;
import org.eclipse.simpl.rrs.model.rrs.RRSFactory;
import org.eclipse.simpl.rrs.model.rrs.ReferenceParameters;
import org.eclipse.simpl.rrs.model.rrs.ReferenceProperties;
import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;
import org.eclipse.swt.SWT;
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

public class AddReferenceDialog extends TitleAreaDialog {

	private Text name;
	private Text address;
	private Text adapter;
	private Text statement;
	private EPR reference;

	public EPR getReference() {
		return reference;
	}

	public AddReferenceDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Add a new reference");
		setMessage("Please enter the data of the new reference",
				IMessageProvider.INFORMATION);
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		parent.getShell().setImage(new Image(parent.getDisplay(), getClass()
				.getResourceAsStream("/icons/add.gif")));
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);
		
		GridData gridData = new GridData();
 		gridData.horizontalAlignment = GridData.FILL;
 		gridData.grabExcessHorizontalSpace = true;
		
		Label label1 = new Label(parent, SWT.NONE);
		label1.setText("Name");
		name = new Text(parent, SWT.BORDER);
		Label label2 = new Label(parent, SWT.NONE);
		label2.setText("RRS-Address");
		address = new Text(parent, SWT.BORDER);
		address.setText(RRSUIPlugIn.getDefault().getPreferenceStore().getString("RRS_RET_ADDRESS"));
		
		Label label3 = new Label(parent, SWT.NONE);
		label3.setText("Adapter");
		adapter = new Text(parent, SWT.BORDER);
		Label label4 = new Label(parent, SWT.NONE);
		label4.setText("Statement");
		statement = new Text(parent, SWT.BORDER);
		
		name.setLayoutData(gridData);
 		address.setLayoutData(gridData);
 		adapter.setLayoutData(gridData);
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
				if (name.getText().length() != 0) {
					/*
					 * Saving the values in the EPR
					 */
					// Create a new EPR
					RRSFactory factory = RRSFactory.eINSTANCE;
					reference = factory.createEPR();
					ReferenceParameters param1 = factory.createReferenceParameters();
					ReferenceProperties prop1 = factory.createReferenceProperties();
					prop1.setResolutionSystem(adapter.getText());
					param1.setReferenceName(name.getText());
					param1.setStatement(statement.getText());
					reference.setAddress(address.getText());
					reference.setReferenceParameters(param1);
					reference.setReferenceProperties(prop1);
					close();

				} else {
					setErrorMessage("Please enter at least the name of the reference");
				}
			}
		});
	}
	
	@Override
	public boolean isHelpAvailable() {
		return false;
	}

}
