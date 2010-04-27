package org.eclipse.bpel.apache.ode.deploy.ui.pages.dialogs;

import org.eclipse.bpel.apache.ode.deploy.model.dd.TDatasource;
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

public class EditDataSourceDialog extends TitleAreaDialog {

	private Text name;
	private Text address;
	private Text type;
	private Text subtype;
	private Text language;
	private Text user;
	private Text password;
	private TDatasource datasource;

	public EditDataSourceDialog(Shell parentShell, TDatasource datasource) {
		super(parentShell);
		this.datasource = datasource;
	}

	public TDatasource getDatasource() {
		return datasource;
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
		name = new Text(parent, SWT.BORDER);
		if (this.datasource.getDataSourceName() != null) {
			name.setText(this.datasource.getDataSourceName());
		}

		Label label2 = new Label(parent, SWT.NONE);
		label2.setText("Address");
		address = new Text(parent, SWT.BORDER);
		if (this.datasource.getAddress() != null) {
			address.setText(this.datasource.getAddress());
		}

		Label label3 = new Label(parent, SWT.NONE);
		label3.setText("Type");
		type = new Text(parent, SWT.BORDER);
		if (this.datasource.getType() != null) {
			type.setText(this.datasource.getType());
		}
		
		Label label4 = new Label(parent, SWT.NONE);
		label4.setText("Subtype");
		subtype = new Text(parent, SWT.BORDER);
		if (this.datasource.getSubtype() != null) {
			subtype.setText(this.datasource.getSubtype());
		}
		
		Label label5 = new Label(parent, SWT.NONE);
		label5.setText("Language");
		language = new Text(parent, SWT.BORDER);
		if (this.datasource.getLanguage() != null) {
			address.setText(this.datasource.getLanguage());
		}
		
		Label label6 = new Label(parent, SWT.NONE);
		label6.setText("User name");
		user = new Text(parent, SWT.BORDER);
		if (this.datasource.getUserName() != null) {
			user.setText(this.datasource.getUserName());
		}
		
		Label label7 = new Label(parent, SWT.NONE);
		label7.setText("Password");
		password = new Text(parent, SWT.BORDER);
		if (this.datasource.getPassword() != null){
			password.setText(this.datasource.getPassword());
		}
		
		name.setLayoutData(gridData);
		address.setLayoutData(gridData);
		type.setLayoutData(gridData);
		subtype.setLayoutData(gridData);
		language.setLayoutData(gridData);
		user.setLayoutData(gridData);
		password.setLayoutData(gridData);

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
						&& !type.getText().isEmpty()&& !subtype.getText().isEmpty()
						&& !language.getText().isEmpty()
						&& !user.getText().isEmpty()&& !password.getText().isEmpty()) {
					/*
					 * Saving the values in the TDatasource
					 */
					// Create a new TDatasource
					ddFactory factory = ddFactory.eINSTANCE;
					datasource = factory.createTDatasource();
					datasource.setDataSourceName(name.getText());
					datasource.setAddress(address.getText());
					datasource.setType(type.getText());
					datasource.setSubtype(subtype.getText());
					datasource.setLanguage(language.getText());
					datasource.setUserName(user.getText());
					datasource.setPassword(password.getText());
					close();

				} else {
					setErrorMessage("Please enter a value for every paramater of the data source");
				}
			}
		});
	}

	@Override
	public boolean isHelpAvailable() {
		return false;
	}

}
