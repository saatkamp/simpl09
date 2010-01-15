package org.eclipse.bpel.simpl.ui.sql.editor;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class UpdateEditor extends AStatementEditor {

	
	
	
	public UpdateEditor() {
		// TODO Auto-generated constructor stub
	}
	
	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	private List listOfTabells=null;
	private Font keyWordFont = new Font(comp.getDisplay(), "Courier New", 16, SWT.NORMAL);
	private String createdStatment=null;
	
	public void createComposite(Composite composite) {
		System.out.print("checkpoint");
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
		
		compos = new Composite(comp, SWT.BORDER);
		//compos.setLayout(new GridLayout());
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		compos.setLayoutData(gridLayout);
		
		comp.setLayoutData(gridData);
		statementText = new StyledText(comp, SWT.BORDER);
		statementText.setLayoutData(gridData1);
		statementText.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				setStatement(statementText.getText());
			}});
		
		BuildUIElements();
		
		setComposite(comp);
		
		if (getStatement()!=null){
			statementText.setText(getStatement());
		}
	}


	/**
	 * for creating the UI in the Editor
	 */
	private void BuildUIElements() {
		// TODO Auto-generated method stub
		CreatButtons();
		CreatListOfTables();
		
	}

	/**
	 * creating the List of tables names and Spalten Names .
	 */
	private void CreatListOfTables() {
		// TODO Auto-generated method stub
		final List  listOfTables=new List(compos,SWT.NONE);
		listOfTables.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent event) {
		          int[] selectedItems = listOfTables.getSelectionIndices();
		          for (int loopIndex = 1; loopIndex < selectedItems.length; loopIndex++){
		        	  AddEneterisToStatmentString("\r"+","+listOfTables.getItem(selectedItems[loopIndex]));  
		          }
		          
		        }

		      public void widgetDefaultSelected(SelectionEvent event) {
		          int[] selectedItems = listOfTables.getSelectionIndices();
		          AddEneterisToStatmentString("\r"+listOfTables.getItem(selectedItems[0])); 
		          for (int loopIndex = 1; loopIndex < selectedItems.length; loopIndex++)
		        	  AddEneterisToStatmentString("\r"+","+listOfTables.getItem(selectedItems[loopIndex]));  

		        }
		    });
		    
	}
	
	private void AddEneterisToStatmentString(String entery){
		createdStatment=createdStatment+entery;
		statementText.setText(createdStatment);
	}

	/**
	 * 
	 */
	private void CreatButtons() {
		// TODO Auto-generated method stub
		Button selectButton =new Button(compos, SWT.NONE);
		selectButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				AddEneterisToStatmentString("SELECT\r");
				//statementText.setFont(keyWordFont);
				//statementText.setText("SELECT\r");
			}
		});
		
		Button fromButton =new Button(compos, SWT.NONE);
		fromButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				//statementText.setFont(keyWordFont);
				AddEneterisToStatmentString("FROM\r");
			}
		});
		
	}

	/**
	 * for changing the UI according to the loaded Statment.
	 * it like parsing the text from the loaded statment
	 */
	private void EditTheUIFromStatment() {
		// TODO Auto-generated method stub
		
	}
}
