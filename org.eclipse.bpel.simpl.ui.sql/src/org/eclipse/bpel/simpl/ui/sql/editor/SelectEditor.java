package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

import javax.print.attribute.TextSyntax;

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
	private int counter=0;
	ArrayList<String> dSourceTables;
	private String[] theKeyWords = new String[]{"SELECT", "FROM", "WHERE"};//TODO: welche KeyWods bzw. befehle 
																		   //sind noch denkbar für jeden StatmentEditor
	public ArrayList<String> getdSourceTables() {
		return dSourceTables;
	}


	public void setdSourceTables(ArrayList<String> dSourceTables) {
		this.dSourceTables = dSourceTables;
	}

	List  listOfTables;
	List  listOfColumn;
	
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
		
		BuildUIElements(compos);
	}


	/**
	 * for creating the UI in the Editor
	 */
	private void BuildUIElements(Composite composite) {
		// TODO Auto-generated method stub
		CreatButtons(composite);
		CreatListOfTables(composite);
		CreatListOfColoms(composite);
		
		LoadTablesIntoList();
		
	}

	/**
	 * creating the List of tables names and Column Names .
	 */
	private void CreatListOfTables(Composite composite) {
		// TODO Auto-generated method stub
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		
		listOfTables=new List(composite,SWT.BORDER);
		listOfTables.setLayoutData(gridData2);
		
		listOfTables.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent event) {
		          //int[] selectedItems = listOfTables.getSelectionIndices();
		          //for (int loopIndex = 1; loopIndex < selectedItems.length; loopIndex++){
		        	  
		        	  //AddEneterisToStatmentString("\r"+","+listOfTables.getItem(selectedItems[loopIndex]));  
		          //}
		    	  LoadColumsAccordingToTable(listOfTables.getItem(listOfTables.getSelectionIndex()));
		    	  AddEneterisToStatmentString("\r	"+listOfTables.getItem(listOfTables.getSelectionIndex()));
		          
		        }

		      public void widgetDefaultSelected(SelectionEvent event) {
		          //int[] selectedItems = listOfTables.getSelectionIndices();
		          //AddEneterisToStatmentString("\r"+listOfTables.getItem(selectedItems[0])); 
		          //for (int loopIndex = 1; loopIndex < selectedItems.length; loopIndex++)
		        	  //AddEneterisToStatmentString("\r"+","+listOfTables.getItem(selectedItems[loopIndex]));  
		    	  	
		        //}
		    	  LoadColumsAccordingToTable(listOfTables.getItem(listOfTables.getSelectionIndex()));
		    	  AddEneterisToStatmentString("\r	"+listOfTables.getItem(listOfTables.getSelectionIndex()));
		        }
		    });
		    
	}
	
	/**
	 * creates the List widget for the columns of a Table
	 */
	private void CreatListOfColoms(Composite composite){
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.CENTER;
		
		listOfColumn=new List(composite,SWT.BORDER);
		listOfColumn.setLayoutData(gridData1);
		
		listOfColumn.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent event) {
		    	  counter++;
		    	  if(counter>1){ //every second column title should have "," before it. 
		    		  AddEneterisToStatmentString(","+listOfColumn.getItem(listOfTables.getSelectionIndex()));
		    	  }
		    	  else{ //the first colom Titel shold be written in new line
		    		  AddEneterisToStatmentString("\r"+listOfColumn.getItem(listOfTables.getSelectionIndex()));

		    	  }
		          
		        }

		      public void widgetDefaultSelected(SelectionEvent event) {
		          counter++;
		    	  if(counter>1){ //every second column title should have "," before it. 
		    		  AddEneterisToStatmentString(","+listOfColumn.getItem(listOfTables.getSelectionIndex()));
		    	  }
		    	  else{ //the first colom Titel shold be written in new line
		    		  AddEneterisToStatmentString("\r"+listOfColumn.getItem(listOfTables.getSelectionIndex()));

		    	  }
		        }
		    });
	}
	
	
	
	private void AddEneterisToStatmentString(String entery){
		createdStatment=createdStatment+entery;
		statementText.setText(createdStatment);
	}

	/**
	 * creat the buttons for the KeyWords
	 */
	private void CreatButtons(Composite composite) {
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
	 * load the tables from DataSource
	 */
	private void LoadTablesIntoList(){
		dSourceTables =new ArrayList<String>()/*=Data Source Tables*/; //TODO: get tables from DSource
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
			listOfColumn.add(dSourceColomsOfTable.get(i));
		}
		
	}
	
	//parsing the statement 
	
	/**
	 * for changing the UI according to the loaded Statement.
	 * it like parsing the text from the loaded statement
	 */
	private void EditTheUIFromStatment() {
		// TODO Auto-generated method stub
		//dSourceTables
		String[] tempColumesNames;
		String cleandStatment=removeAllSpaces(getStatement());
		String[] wordsOfStatment =cleandStatment.split("\r");
		String arrayAsString="";
		for(int i=0;i<wordsOfStatment.length;i++){
			//if(wordsOfStatment[i].equals("INSERT INTO"))
			if(IsSringTableName(wordsOfStatment[i])){
				
			}
			else{
				if(!(IsStringKeyWord(wordsOfStatment[i]))){
			
					tempColumesNames=ParseStringIntoColumsNames(wordsOfStatment[i]);
					//ArrayList<Integer> indicesOfSelection=new ArrayList<Integer>();
					for(int j=0;j<tempColumesNames.length;j++){
						arrayAsString=ConvertArrayToString(listOfColumn.getItems());
						if(arrayAsString.contains(tempColumesNames[j])){
							//indicesOfSelection.add(j);
							listOfColumn.select(j);
							
						}
					}
					//listOfColumn.select(indicesOfSelection.toArray());
				}
			}
		}
		//TODO: ....
		
	}
	
	/**
	 * splits the string/sentence into the "," separated Columns names.
	 * @param string
	 * @return arrayOfColums
	 */
	private String[] ParseStringIntoColumsNames(String string) {
		
		String[] arrayOfColums=string.split(",");
		return arrayOfColums;
	}


	/**
	 * this function is for checking if the string is one of
	 * the used KeyWords  
	 * @param string
	 * @return 
	 */
	private boolean IsStringKeyWord(String string) {
		if(ConvertArrayToString(theKeyWords).contains(string)) return true;
		return false;
	}


	/**
	 * Convert an array of words into one String, wich the words
	 * are separaed with " "
	 * @param items
	 * @return resultString
	 */
	private String ConvertArrayToString(String[] items) {
		String resultString="";
		for(int i=0;i<items.length;i++){
			resultString=resultString+" "+items[i];
		}
		
		return resultString;
	}


	/**
	 * for checking if the string is one of the data source 
	 * Tables.
	 * @param string
	 * @return boolean
	 */
	private boolean IsSringTableName(String string) {
		//TODO: üerprüfen 
		
		for(int i=0;i<dSourceTables.size();i++){
			if(dSourceTables.get(i).equals(string)) return true;
		}
		
		return false;
	}


	/**
	 * removes all spaces from statment
	 * @param statement
	 * @return statmentAsOneString
	 */
	private String removeAllSpaces(String statement) {
		String[] wordsOfCentence = null;
		String statmentAsOneString = "";
		if(statement.contains(" ")){
			wordsOfCentence=statement.split(" ");
		}
		for(int i=0;i<wordsOfCentence.length;i++){
			statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
		}
		
		while(statmentAsOneString.contains(" ")){
			wordsOfCentence=statement.split(" ");
			for(int i=0;i<wordsOfCentence.length;i++){
				statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
			}
		}
		
		return statmentAsOneString;
	}

}
