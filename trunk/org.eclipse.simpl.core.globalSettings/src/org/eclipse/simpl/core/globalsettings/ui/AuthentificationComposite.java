package org.eclipse.simpl.core.globalsettings.ui;

import java.util.List;

import org.eclipse.simpl.core.extensions.AAdminConsoleComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class AuthentificationComposite extends AAdminConsoleComposite {

	private Label userLabel = null;
	private Label passwordLabel = null;
	private Text userText = null;
	private Text passwordText = null;
	
	@Override
	public void createComposite(Composite composite) {
		// TODO Auto-generated method stub
		GridData gridData4 = new GridData();
		gridData4.widthHint = 100;
		GridData gridData3 = new GridData();
		gridData3.widthHint = 100;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.BEGINNING;
		gridData1.verticalAlignment = GridData.CENTER;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		Composite comp = new Composite(composite, SWT.NONE);
		comp.setLayout(gridLayout);
		
		setComposite(comp);
		
		userLabel = new Label(comp, SWT.NONE);
		userLabel.setText("User:");
		userLabel.setLayoutData(gridData);
		userText = new Text(comp, SWT.BORDER);
		userText.setLayoutData(gridData3);
		passwordLabel = new Label(comp, SWT.NONE);
		passwordLabel.setText("Password:");
		passwordLabel.setLayoutData(gridData1);
		passwordText = new Text(comp, SWT.BORDER);
		passwordText.setEchoChar('*');
		passwordText.setLayoutData(gridData4);
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
