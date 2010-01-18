package org.eclipse.bpel.simpl.ui.sql.editor;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CallEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	
	
	public CallEditor() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createComposite(Composite composite) {
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.verticalAlignment = GridData.FILL;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		comp = new Composite(composite, SWT.NONE);
		comp.setLayout(new GridLayout());
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = GridData.FILL;
		compos = new Composite(comp, SWT.NONE);
		compos.setLayout(new GridLayout());
		compos.setLayoutData(gridData2);
		comp.setLayoutData(gridData);
		statementText = new StyledText(comp, SWT.BORDER);
		statementText.setLayoutData(gridData1);
		statementText.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				setStatement(statementText.getText());
			}});
		
		setComposite(comp);
		
		if (getStatement()!=null){
			statementText.setText(getStatement());
		}
		CreateCallUIElements(compos);
	}

	/**
	 * 
	 * @param compos2
	 */
	private void CreateCallUIElements(Composite composite) {
		
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = GridData.CENTER;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.CENTER;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.CENTER;
//		GridLayout gridLayout = new GridLayout();
//		gridLayout.numColumns = 3;
		
		
		final Label proceLabel=new Label(composite, SWT.BORDER);
		
		proceLabel.setText("Procedure: ");
		proceLabel.setLayoutData(gridData1);
		final Text proceText =new Text(composite, SWT.BORDER);
		proceText.setSize(100, 70);
		proceText.setLayoutData(gridData1);
		final Button addToStatement =new Button(composite, SWT.BORDER);
		addToStatement.setText("Add to Statment");
		addToStatement.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			    statementText.setText(statementText.getText()+"\r	"+proceText.getText()+"(? ,? , ?, ...)");				
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				statementText.setText(statementText.getText()+"\r	"+proceText.getText()+"(? ,? , ?, ...)");
				
			}
	
		});
		
		addToStatement.setLayoutData(gridData);
		
		proceLabel.setVisible(false);
		proceText.setVisible(false);
		addToStatement.setVisible(false);
		
		final Button callButton =new Button(composite, SWT.BORDER);
		callButton.setText("CALL");
		callButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				statementText.setText("CALL");
				proceLabel.setVisible(true);
				proceText.setVisible(true);
				addToStatement.setVisible(true);
				
				callButton.setVisible(false);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statementText.setText("CALL\r");
				proceLabel.setVisible(true);
				proceText.setVisible(true);
				addToStatement.setVisible(true);
				
				callButton.setVisible(false);
			}
		});
	}

}
