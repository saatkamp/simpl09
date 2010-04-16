package org.eclipse.bpel.apache.ode.deploy.ui.pages.dialogs;

import org.eclipse.bpel.apache.ode.deploy.model.dd.ProcessType;
import org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping;
import org.eclipse.bpel.apache.ode.deploy.model.dd.ddFactory;
import org.eclipse.bpel.apache.ode.deploy.ui.util.DeployUtils;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class AddMappingDialog extends TitleAreaDialog {

	private TActivityMapping mapping;
	private ProcessType processType;

	private CCombo availableActivities;
	private CCombo dataSource;
	private Text policyAdress;
	private Button buttonSelectFile;

	public TActivityMapping getMapping() {
		return mapping;
	}

	public AddMappingDialog(Shell parentShell, ProcessType pt) {
		super(parentShell);
		this.processType = pt;
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Add a new activity mapping");
		setMessage("Please enter the data of the new mapping",
				IMessageProvider.INFORMATION);
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		parent.getShell().setImage(
				new Image(parent.getDisplay(), getClass().getResourceAsStream(
						"/icons/add.gif")));

		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		parent.setLayout(layout);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;

		Label label1 = new Label(parent, SWT.NONE);
		label1.setText("Activity");
		availableActivities = new CCombo(parent, SWT.BORDER);
		Label filler1 = new Label(parent, SWT.NONE);
		// Load the available SIMPL activities
		availableActivities.setItems(DeployUtils.getDMActivityNames(processType
				.getModel()));

		Label label2 = new Label(parent, SWT.NONE);
		label2.setText("DataSource");
		dataSource = new CCombo(parent, SWT.BORDER);
		Label filler2 = new Label(parent, SWT.NONE);
		// Load the available data sources
		dataSource.setItems(DeployUtils.getProcessDataSourceNames(processType));

		Label label3 = new Label(parent, SWT.NONE);
		label3.setText("Policy path");
		policyAdress = new Text(parent, SWT.BORDER);

		buttonSelectFile = new Button(parent, SWT.PUSH);
		buttonSelectFile.setText("...");
		buttonSelectFile.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog fileDialog = new FileDialog(Display.getCurrent()
						.getActiveShell());

				fileDialog.setFilterExtensions(new String[] { "*.xml", "*.*" });
				fileDialog.setFilterNames(new String[] {
						"eXtensible Markup Language", "Any" });

				String firstFile = fileDialog.open();

				if (firstFile != null) {
					policyAdress.setText(fileDialog.getFilterPath()
							+ System.getProperty("file.separator")
							+ fileDialog.getFileName());
				}
			}
		});

		availableActivities.setLayoutData(gridData);
		dataSource.setLayoutData(gridData);
		policyAdress.setLayoutData(gridData);

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
				/*
				 * Saving the values in the TDatasource
				 */
				// Create a new TDatasource
				ddFactory factory = ddFactory.eINSTANCE;
				mapping = factory.createTActivityMapping();

				String activityName = availableActivities.getText();
				// String activityName =
				// availableActivities.getItem(availableActivities.getSelectionIndex());
				mapping.setActivity(DeployUtils.findActivityByName(
						activityName, processType.getModel()));

				String dataSourceName = dataSource.getText();
				// String dataSourceName =
				// dataSource.getItem(dataSource.getSelectionIndex());
				mapping.setDatasource(DeployUtils.findDataSourceByName(
						dataSourceName, processType));

				mapping.setPolicy(DeployUtils.queryPolicyByPath(policyAdress
						.getText()));
				close();
			}
		});
	}

	@Override
	public boolean isHelpAvailable() {
		return false;
	}
}