package org.eclipse.simpl.references.ui;

import java.util.List;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import org.eclipse.simpl.extensions.IAdminConsoleComposite;
import org.eclipse.swt.widgets.Composite;

public class ReferenceComposite implements IAdminConsoleComposite {

	private Composite composite = null;
	private Table referenceTable = null;
	private Button openButton = null;
	private Button newButton = null;
	private Button deleteButton = null;
	private Label referenceLabel = null;

	@Override
	public void createComposite(Composite composite) {
		// TODO Auto-generated method stub
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.END;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.verticalAlignment = GridData.CENTER;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.END;
		gridData2.verticalAlignment = GridData.CENTER;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.END;
		gridData1.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 3;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		Composite comp = new Composite(composite, SWT.NONE);
		comp.setLayout(gridLayout);
		
		setComposite(comp);
		referenceLabel = new Label(comp, SWT.NONE);
		referenceLabel.setText("Table of references:");
		Label filler = new Label(comp, SWT.NONE);
		Label filler1 = new Label(comp, SWT.NONE);
		referenceTable = new Table(comp, SWT.BORDER | SWT.FULL_SELECTION);
		referenceTable.setHeaderVisible(true);
		referenceTable.setLayoutData(gridData);
		referenceTable.setLinesVisible(true);
		TableColumn tc1 = new TableColumn(referenceTable, SWT.LEFT);
	    TableColumn tc2 = new TableColumn(referenceTable, SWT.LEFT);
	    TableColumn tc3 = new TableColumn(referenceTable, SWT.LEFT);
	    TableColumn tc4 = new TableColumn(referenceTable, SWT.LEFT);
	    tc1.setText("Name");
	    tc2.setText("Datasource address");
	    tc3.setText("Query statement");
	    tc4.setText("Write statement");
	    tc1.setWidth(100);
	    tc2.setWidth(150);
	    tc3.setWidth(200);
	    tc4.setWidth(200);
	    TableItem item1 = new TableItem(referenceTable, SWT.NONE);
	    item1.setText(new String[] { "startValue", "http://localhost:8080", 
	    		"SELECT constants FROM simdata WHERE id = '1'" , 
	    		"UPDATE simdata SET constants='123' WHERE id = '1'"});
		openButton = new Button(comp, SWT.NONE);
		openButton.setText("Open");
		openButton.setLayoutData(gridData3);
		newButton = new Button(comp, SWT.NONE);
		newButton.setText("New");
		newButton.setLayoutData(gridData2);
		deleteButton = new Button(comp, SWT.NONE);
		deleteButton.setText("Delete");
		deleteButton.setLayoutData(gridData1);
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
	
	@Override
	public Composite getComposite() {
		// TODO Auto-generated method stub
		return this.composite;
	}

	@Override
	public void setComposite(Composite composite) {
		// TODO Auto-generated method stub
		this.composite = composite;
	}

}
