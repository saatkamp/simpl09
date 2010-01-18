package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Es fehlt noch die r�ckw�rtz Parsen der Statement .
 */
public class CreateEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	
	Text textColumnName;
	Combo comboColumnType;
	Button addColumnToStatement;
	ArrayList<String> listOfTheColumns=new ArrayList<String>();
	
	public CreateEditor() {
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
		
		CreateCREATEEditorUI(compos);
	}

	private void CreateCREATEEditorUI(Composite composite) {
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = GridData.CENTER;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.BEGINNING;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.CENTER;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.CENTER;
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		composite.setLayout(gridLayout);
		
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		composite.setLayout(gridLayout);
		final Composite columnCompo=new Composite(composite, SWT.BORDER);
		columnCompo.setLayout(gridLayout2);
		//columnCompo.setLayoutData(gridData1);
		Label labelColumnName,labelColumnType;
		
		final Combo comboColumnType=new Combo(columnCompo, SWT.NONE);
		comboColumnType.add("INT");
		comboColumnType.add("SHORT_INT");
		comboColumnType.add("LONGINT");
		comboColumnType.add("CHAR");
		comboColumnType.add("String");
		comboColumnType.add("BOOLEAN");
		textColumnName=new Text(columnCompo, SWT.NONE);
		
		
		final Button addColumn =new Button(composite, SWT.BORDER);
		addColumn.setText("Add New Column");
		addColumn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
				statementText.setText(statementText.getText()+"\r		"+textColumnName.getText()+" "+comboColumnType.getText()+",");
//				textColumnName=new Text(columnCompo, SWT.NONE);
//				comboColumnType=new Combo(columnCompo, SWT.NONE);
//				comboColumnType.setItems(defaultCombo.getItems());
//				
//				addColumnToStatement=new Button(columnCompo, SWT.NONE);
//				addColumnToStatement.setText("Ok");
//				addColumnToStatement.addSelectionListener(new SelectionListener() {
//					
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
//
//						
//					}
//					
//					@Override
//					public void widgetDefaultSelected(SelectionEvent e) {
//						listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
//					}
//				});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
				statementText.setText(statementText.getText()+"\r		"+textColumnName.getText()+" "+comboColumnType.getText()+",");

//				textColumnName=new Text(columnCompo, SWT.NONE);
//				comboColumnType=new Combo(columnCompo, SWT.NONE);
//				comboColumnType.setItems(defaultCombo.getItems());
//				
//				addColumnToStatement=new Button(columnCompo, SWT.NONE);
//				addColumnToStatement.setText("Ok");
//				addColumnToStatement.addSelectionListener(new SelectionListener() {
//					
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
//
//						
//					}
//					
//					@Override
//					public void widgetDefaultSelected(SelectionEvent e) {
//						listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
//					}
//				});
				
				
			}
		});
		addColumn.setEnabled(false);
		
		final Button creatButton=new Button(composite, SWT.BORDER);
		creatButton.setText("CREATE");
		creatButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				statementText.setText("CREATE TABLE  :COMMENT.type the table name hier___ \r	(");
				addColumn.setEnabled(true);
				creatButton.setEnabled(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				statementText.setText("CREATE TABLE  :COMMENT.type the table name hier___ \r	(");
				addColumn.setEnabled(true);
				creatButton.setEnabled(false);
			}
		});
		
	}

}
