package org.eclipse.bpel.apache.ode.deploy.ui.pages.dialogs;

import org.eclipse.bpel.apache.ode.deploy.model.dd.ProcessType;
import org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping;
import org.eclipse.bpel.apache.ode.deploy.model.dd.ddFactory;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
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

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
public class EditMappingDialog extends TitleAreaDialog {


	private TActivityMapping mapping;
	private ProcessType processType;

	public EditMappingDialog(Shell parentShell, ProcessType pt, TActivityMapping mapping) {
		super(parentShell);
		this.mapping = mapping;
		this.processType = pt;
	}

	public TActivityMapping getMapping() {
		return mapping;
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Edit an existing data source");
		setMessage("Please edit the data of the selected data source",
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
		label1.setText("Name");
//		name = new Text(parent, SWT.BORDER);
//		if (this.datasource.getDataSourceName() != null) {
//			name.setText(this.datasource.getDataSourceName());
//		}

		Label label2 = new Label(parent, SWT.NONE);
		label2.setText("Address");
//		address = new Text(parent, SWT.BORDER);
//		if (this.datasource.getAddress() != null) {
//			address.setText(this.datasource.getAddress());
//		}

		Label label3 = new Label(parent, SWT.NONE);
		label3.setText("User name");
//		user = new Text(parent, SWT.BORDER);
//		if (this.datasource.getUserName() != null) {
//			user.setText(this.datasource.getUserName());
//		}
		
		Label label4 = new Label(parent, SWT.NONE);
		label4.setText("Password");
//		password = new Text(parent, SWT.BORDER);
//		if (this.datasource.getPassword() != null){
//			password.setText(this.datasource.getPassword());
//		}

//		name.setLayoutData(gridData);
//		address.setLayoutData(gridData);
//		user.setLayoutData(gridData);
//		password.setLayoutData(gridData);

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
//				if (name.getText().length() != 0) {
//					/*
//					 * Saving the values in the TDatasource
//					 */
//					// Create a new TDatasource
//					ddFactory factory = ddFactory.eINSTANCE;
//					datasource = factory.createTDatasource();
//					datasource.setDataSourceName(name.getText());
//					datasource.setAddress(address.getText());
//					datasource.setUserName(user.getText());
//					datasource.setPassword(password.getText());
					close();

//				} else {
//					setErrorMessage("Please enter at least the name of the data source");
//				}
			}
		});
	}

	@Override
	public boolean isHelpAvailable() {
		return false;
	}

}