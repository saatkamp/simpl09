package org.eclipse.simpl.core.auditing;

import java.util.List;

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
				if (auditingCheckBox.getSelection()){
					auditingCheckBox.setText("active");
				} else{
					auditingCheckBox.setText("inactive");
				}
			}

			public void widgetDefaultSelected(SelectionEvent event) {
				auditingCheckBox.setText("inactive");
			}
		});
		
	}

	@Override
	public List<String> loadDefaultProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> loadProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveProperties(List<String> properties) {
		// TODO Auto-generated method stub
		
	}

}
