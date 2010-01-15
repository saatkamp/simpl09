package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

public class SelectEditor extends AStatementEditor {

	
	public SelectEditor() {
		// TODO Auto-generated constructor stub
	}
	
	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	private List listOfTabells=null;
	private Font keyWordFont = new Font(comp.getDisplay(), "Courier New", 16, SWT.NORMAL);
	private String createdStatment=null;
	
	List  listOfTables=new List(comp,SWT.NONE);
	List  listOfColoms=new List(comp,SWT.NONE);
	
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
	}


	/**
	 * for creating the UI in the Editor
	 */
	private void BuildUIElements() {
		// TODO Auto-generated method stub
		CreatButtons();
		CreatListOfTables();
		LoadTablesIntoList();
		
	}

	/**
	 * creating the List of tables names and Spalten Names .
	 */
	private void CreatListOfTables() {
		// TODO Auto-generated method stub
		
		listOfTables.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent event) {
		          //int[] selectedItems = listOfTables.getSelectionIndices();
		          //for (int loopIndex = 1; loopIndex < selectedItems.length; loopIndex++){
		        	  
		        	  //AddEneterisToStatmentString("\r"+","+listOfTables.getItem(selectedItems[loopIndex]));  
		          //}
		    	  LoadColumsAccordingToTable(listOfTables.getItem(listOfTables.getSelectionIndex()));
		    	  AddEneterisToStatmentString("\r"+listOfTables.getItem(listOfTables.getSelectionIndex()));
		          
		        }

		      public void widgetDefaultSelected(SelectionEvent event) {
		          //int[] selectedItems = listOfTables.getSelectionIndices();
		          //AddEneterisToStatmentString("\r"+listOfTables.getItem(selectedItems[0])); 
		          //for (int loopIndex = 1; loopIndex < selectedItems.length; loopIndex++)
		        	  //AddEneterisToStatmentString("\r"+","+listOfTables.getItem(selectedItems[loopIndex]));  
		    	  	
		        //}
		    	  LoadColumsAccordingToTable(listOfTables.getItem(listOfTables.getSelectionIndex()));
		    	  AddEneterisToStatmentString("\r"+listOfTables.getItem(listOfTables.getSelectionIndex()));
		        }
		    });
		    
	}
	
	/**
	 * 
	 */
	private void CreatListOfColoms(){
		listOfColoms.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent event) {
		          int[] selectedItems = listOfColoms.getSelectionIndices();
		          for (int loopIndex = 1; loopIndex < selectedItems.length; loopIndex++){
		        	  AddEneterisToStatmentString("\r"+","+listOfColoms.getItem(selectedItems[loopIndex]));  
		          }
		          
		        }

		      public void widgetDefaultSelected(SelectionEvent event) {
		          int[] selectedItems = listOfColoms.getSelectionIndices();
		          AddEneterisToStatmentString("\r"+listOfColoms.getItem(selectedItems[0])); 
		          for (int loopIndex = 1; loopIndex < selectedItems.length; loopIndex++)
		        	  AddEneterisToStatmentString("\r"+","+listOfColoms.getItem(selectedItems[loopIndex]));  

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
		Button selectButton =new Button(comp, SWT.NONE);
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
		
		Button fromButton =new Button(comp, SWT.NONE);
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
	
	/**
	 * load the tables from DataSource
	 */
	private void LoadTablesIntoList(){
		ArrayList<String> dSourceTables =new ArrayList<String>()/*=Data Source Tables*/; //TODO: get tables from DSource
		for(int i=0;i<dSourceTables.size();i++){
			listOfTables.add(dSourceTables.get(i));
		}
	}
	
	/**
	 * Load the coloms titels of the selected Table
	 * in the coloms List 
	 * @param tableName 
	 */
	private void LoadColumsAccordingToTable(String tableName){
		ArrayList<String> dSourceColomsOfTable =new ArrayList<String>()/*=Data Source coloms*/; //TODO: get Coloms of table tableName from Source
		for(int i=0;i<dSourceColomsOfTable.size();i++){
			listOfColoms.add(dSourceColomsOfTable.get(i));
		}
		
	}

}
