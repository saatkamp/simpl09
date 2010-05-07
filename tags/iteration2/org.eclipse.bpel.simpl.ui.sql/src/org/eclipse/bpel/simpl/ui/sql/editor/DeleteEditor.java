package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import widgets.LiveEditStyleText;
import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

public class DeleteEditor extends AStatementEditor {

	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreiben
	private String xmlFilePath="/keywords/DeleteDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	private Composite comp = null;
	private Composite compos = null;
	
	Label proceLabel;
	Button addToStatement;
	Text proceText;
	
	private LiveEditStyleText statementText = null;
	
	
	Composite tableNameComposite=null;
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	
	//********************************
    List tablsList,columnList=null;
    Composite actionsCompo=null;
   	String TABLE_ORIENTED="values";
	String SET_ORIENTED="select";
    int tableIndex=0; 
    Table tableOfColumnsAndValues;
    Composite valueCompo;
   //******************************** 
    
    //for select editor
    Combo comboColumnName,comboOperationName,comboDistAll,pradiktCombo;
	Text textValueForColumn;
	// Create the containing tab folder
	TabFolder tabFolder;
	
	//List of statement Changes
    ArrayList<String> listOfStatementTextChanges=new ArrayList<String>();
    
	
	public ArrayList<String> getListOfStatementTextChanges() {
		return listOfStatementTextChanges;
	}

	public void addToListOfStatementTextChanges() {
		listOfStatementTextChanges.add(statementText.getText());
	}
	
	Text textTableName;
	public DeleteEditor() {
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
		compos.setLayoutData(gridData1);
		
		GridData gridData1_1 = new GridData();
		gridData1_1.horizontalAlignment = GridData.END;
		gridData1_1.grabExcessHorizontalSpace = true;
		gridData1_1.grabExcessVerticalSpace = true;
		gridData1_1.verticalAlignment = GridData.END;
		
		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(xmlFilePath);
		buttonsCompo=new Composite(compos, SWT.RIGHT_TO_LEFT);
		buttonsCompo.setLayout(gridLayoutA);
		creatButtonsOfKeyWords(parser.parseDocument());
		
		
		comp.setLayoutData(gridData);
		Composite statementCompo=new Composite(comp, SWT.NONE);
			
		statementCompo.setLayout(new GridLayout());
		statementCompo.setLayoutData(gridData2);
		GridLayout gridLayoutA2 = new GridLayout();
		gridLayoutA2.numColumns = 1;
		statementCompo.setLayout(gridLayoutA2);
			statementText=new LiveEditStyleText(statementCompo);
			statementText.setEditable(false);
			//+++++++++++++undoButton++++++++++++++++++++++++++++++++++
			Button undoButton=new Button(statementCompo, SWT.LEFT);
			//undoButton.setLayoutData(gridData1_1);
			undoButton.setText("UNDO");
			undoButton.setToolTipText("UNDO Statement: delete last changes in the Statement.");
			undoButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(listOfStatementTextChanges.size()>=2){
						statementText.setText(listOfStatementTextChanges.get(listOfStatementTextChanges.size()-2));
						listOfStatementTextChanges.remove(listOfStatementTextChanges.size()-1);
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(listOfStatementTextChanges.size()>=2){
						statementText.setText(listOfStatementTextChanges.get(listOfStatementTextChanges.size()-2));
						listOfStatementTextChanges.remove(listOfStatementTextChanges.size()-1);
					}
				}
			});
			//+++++++++++++++++end undoButton+++++++++++++++++++++++++
		statementText.setLayoutData(gridData2);
		statementText.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				setStatement(statementText.getText());
			}});
		
		setComposite(comp);
		
		if (getStatement()!=null){
			statementText.setText(getStatement());
			if(statementText.getText().length()>8){
				if(statementText.getText().equals("statement")){
					statementText.setText("DELETE FROM ");
				}
				
			}
			else{statementText.setText("DELETE FROM ");}
			addToListOfStatementTextChanges();
		}
		else {statementText.setText("DELETE FROM ");}
		
		
		
		//CreateDELETEUIComponent(compos);
		createTabTable(parser.parseDocument());
	}


	/**
	   * Gets the control for tab one
	   * 
	   * @param tabFolder the parent tab folder
	   * @return Control
	   */
	  private Control getTabOneControl(final TabFolder tabFolder,final String keyWordValue) {
	    // Create a composite and add four buttons to it
	    Composite composite = new Composite(tabFolder, SWT.NONE);
	    composite.setLayout(new FillLayout(SWT.VERTICAL));
	    
	    Button insertButton;
	    
	    
		
	    if(keyWordValue.toLowerCase().equals("where"))
		{
		    GridLayout gridLayoutx = new GridLayout();
			gridLayoutx.numColumns = 4;
			Composite tableNameComposite = new Composite(composite, SWT.PUSH);
			//tableNameComposite.setEnabled(false);
			tableNameComposite.setLayout(gridLayoutx);
	
			//*************************************************
			GridData gridData1 = new GridData();
			//gridData1.horizontalAlignment = GridData.FILL;
			gridData1.grabExcessHorizontalSpace = true;
			gridData1.grabExcessVerticalSpace = true;
			gridData1.verticalAlignment = GridData.FILL;
			
			tablsList = new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
			tablsList.setBounds(40, 20, 320, 100);
			tablsList.setLayoutData(gridData1);
			//tablsList.setEnabled(false);
			try {
				loadTheTablesIntoList();
			} catch (Exception e2) {
				System.out.print("ERROR: "+e2.getMessage());
			}
			
			try {
				tablsList.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						System.out.print(tablsList.getItem(tablsList.getSelectionIndex()));
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTableIntoCombo(tablsList.getItem(tablsList.getSelectionIndex()));
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

						System.out.print(tablsList.getItem(tablsList.getSelectionIndex()));

						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTableIntoCombo(tablsList.getItem(tablsList.getSelectionIndex()));
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}

					
	
					
				});
			} catch (Exception e1) {
				System.out.print("ERROR: "+e1.getMessage());
			}
			//**************************************************************
			
			//************************************************************
			
			//************************************************************
//			
			
			
			
			//***********************************************************
			GridData gridDatax1 = new GridData();
			Composite tableCompo=new Composite(tableNameComposite, SWT.NONE);
			GridLayout gridLayoutY1=new GridLayout();
			gridLayoutY1.numColumns=1;
			tableCompo.setLayout(gridLayoutY1);
			
			
			Composite buttomsCompo=new Composite(tableNameComposite, SWT.BOLD);
			GridLayout gridLayoutY3=new GridLayout();
			gridLayoutY3.numColumns=1;
			buttomsCompo.setLayout(gridLayoutY3);
			
			Button insertItemInTable=new Button(buttomsCompo, SWT.NONE);
			insertItemInTable.setText("Insert to table");
			insertItemInTable.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					if(textValueForColumn.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName.getText(),comboOperationName.getText(),textValueForColumn.getText()});
						textValueForColumn.setText("");
					}					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(textValueForColumn.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName.getText(),comboOperationName.getText(),textValueForColumn.getText()});
						textValueForColumn.setText("");
					}		
				}
			});
			
			Button deleteFromColumnList =new Button(buttomsCompo, SWT.NONE);
			deleteFromColumnList.setText("Remove from table");
			deleteFromColumnList.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					tableOfColumnsAndValues.getItem(tableOfColumnsAndValues.getSelectionIndex()).dispose();
					tableIndex--;
					if(tableOfColumnsAndValues.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableOfColumnsAndValues.getItem(tableOfColumnsAndValues.getSelectionIndex()).dispose();
					if(tableOfColumnsAndValues.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
					tableIndex--;
				}
			});
			Composite titelPartOfTableCombo=new Composite(tableCompo, SWT.BOLD);
			GridLayout gridLayoutY4=new GridLayout();
			gridLayoutY4.numColumns=6;
			titelPartOfTableCombo.setLayout(gridLayoutY4);
			Label labelColumnName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelColumnName.setText("Column name: ");		
			comboColumnName=new Combo(titelPartOfTableCombo, SWT.BORDER);
			Label labelOperationName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelOperationName.setText("Operation: ");
			comboOperationName=new Combo(titelPartOfTableCombo, SWT.BORDER);
			comboOperationName.add("IN");
			comboOperationName.add("LIKE");
			comboOperationName.add("<");
			comboOperationName.add(">");
			comboOperationName.add("=");
			comboOperationName.add(">=");
			comboOperationName.add("<=");
			
			Label labelValueOfColumn=new Label(titelPartOfTableCombo, SWT.NONE);
			labelValueOfColumn.setText("Value: ");
			textValueForColumn=new Text(titelPartOfTableCombo, SWT.BORDER);
			
			tableOfColumnsAndValues = new Table(tableCompo, SWT.NONE);
			
			tableOfColumnsAndValues.setHeaderVisible(true);
			tableOfColumnsAndValues.setItemCount(10);
			tableOfColumnsAndValues.setLinesVisible(true);
			
			
			
			TableColumn columnName = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			columnName.setText("Column Name");
			TableColumn columnOperation = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			columnOperation.setText("Operation");
			TableColumn columnValues = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			columnValues.setText("Value");
			
			//TableColumn columnType = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			//columnType.setText("Type");
			
			columnName.setWidth(150);
			columnOperation.setWidth(150);
			columnValues.setWidth(150);
			//columnType.setWidth(70);
			
			Composite pradikatCompo=new Composite(tableCompo, SWT.NONE);
			GridLayout gridLayoutY5=new GridLayout();
			gridLayoutY5.numColumns=2;
			pradikatCompo.setLayout(gridLayoutY5);
			Label labelPradikat=new Label(pradikatCompo, SWT.NONE);
			labelPradikat.setText("Pradikat: ");
			pradiktCombo=new Combo(pradikatCompo,SWT.NONE);
			pradiktCombo.add("OR");
			pradiktCombo.add("AND");
			//***********************************************************
			
			
			
			//********************************
			Button insertColumsIntoStatment=new Button(buttomsCompo, SWT.NONE);
			insertColumsIntoStatment.setToolTipText("Add Columns and the values into the Statment");
			insertColumsIntoStatment.setText("Insert to Statement");
			insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
				
				String operStat="";
				String[] tmpString=null;
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(tableOfColumnsAndValues.getItemCount()>10){
						tableIndex=0;
						//tablsList.setEnabled(true);
						
						TableItem[] arrayOfTableItems =tableOfColumnsAndValues.getItems();
						//arrayOfTableItems[0].getText(0)
						//if(arrayOfTableItems.length>1) 	tmpString=" ,";
						//else tmpString=" ";
						//statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
						
						statementText.setText(statementText.getText()+"\r	FROM ");
						tmpString=SplitTheWordInTwo(arrayOfTableItems[0].getText(0));
						statementText.setText(statementText.getText()+" "+tmpString[0]);
						for(int i=1;i<arrayOfTableItems.length;i++){
							if(arrayOfTableItems[i].getText(0).length()>0){
								tmpString=SplitTheWordInTwo(arrayOfTableItems[i].getText(0));
								statementText.setText(statementText.getText()+","+tmpString[0]);
							}
						}
						statementText.setText(statementText.getText()+"\r	WHERE ");
						tmpString=SplitTheWordInTwo(arrayOfTableItems[0].getText(0));
						if(arrayOfTableItems[0].getText(1).length()>0) {
							
							statementText.setText(statementText.getText()+" "+tmpString[1]+" "+arrayOfTableItems[0].getText(1)+" "+arrayOfTableItems[0].getText(2));
						}
						else {
							
						}
						
						statementText.setText(statementText.getText()+arrayOfTableItems[0].getText(2)+" "+operStat);

						for(int i=1;i<arrayOfTableItems.length;i++){
							if(arrayOfTableItems[i].getText(1).length()>0) {
								if(pradiktCombo.getText().length()>0) statementText.setText(statementText.getText()+pradiktCombo.getText());
								else statementText.setText(statementText.getText()+"OR");
								
								tmpString=SplitTheWordInTwo(arrayOfTableItems[i].getText(0));
								statementText.setText(statementText.getText()+" "+tmpString[1]+" "+arrayOfTableItems[i].getText(1)+" "+arrayOfTableItems[i].getText(2));
								
							}
							else {
								
							}						}
						addToListOfStatementTextChanges();
						
						
						
						statementText.setText(statementText.getText()+"\r");
						addToListOfStatementTextChanges();
//						for(int i=0;i<arrayOfTableItems.length;i++){
//							if(arrayOfTableItems[i].getText(0).length()>0){	
//								statementText.setText(statementText.getText()+tmpString+arrayOfTableItems[i].getText(0).split(".")[0]);
//							}
//						}
						
						
						DeleteContentOfTable();
					}//end of if(...count>10)
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
			tableNameComposite.setEnabled(true);
			tablsList.setEnabled(true);
			//valuesCompo.setEnabled(true);
		}//end of if where-Part
	    //**********End of where**********************************************

		if(keyWordValue.toLowerCase().equals("from"))
		{
		}
		
		return composite;
	}
	
	  /**
		 * for loading the colums of the table into the combo of 
		 * colums names.
		 */
		private void loadColumnsOfTableIntoCombo(String tableName) {
			//String tableName = "";
			try {
				//tableName = 
				comboColumnName.removeAll();
			} catch (Exception e) {
				System.out.print("ERROR:"+e.getMessage());
			}
			
			//zum testen***
			for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
				try {
					comboColumnName.add(tableName+".Column" + loopIndex);
				} catch (Exception e) {
					System.out.print("ERROR:"+e.getMessage());
				}
			}
			//*************
		}
		//**********************************
	  
	private void CreateDELETEUIComponent(Composite composite) {
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
		
//		GridLayout gridLayout = new GridLayout();
//		gridLayout.numColumns = 6;
//		composite.setLayout(gridLayout);
		

		//****************************************************
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		tableNameComposite=new Composite(composite, SWT.NONE);
		tableNameComposite.setLayout(gridLayout2);
		
		final Label tableNameLabel=new Label(tableNameComposite, SWT.NONE);
		tableNameLabel.setText("Select the Table: ");
		//tableNameLabel.setLayoutData(gridData1);
		
		
		
		tablsList = new List(tableNameComposite, SWT.BORDER | SWT.V_SCROLL| SWT.H_SCROLL);
		tablsList.setBounds(40, 20, 420, 100);
		
		parseStatment();
		loadTheTablesIntoList();
		//tablsList.setLayoutData(gridData1);
		tablsList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String kommaString=" ";
				statementText.setText(statementText.getText()+kommaString+tablsList.getItem(tablsList.getSelectionIndex()));
				addToListOfStatementTextChanges();
				kommaString="\r			,";
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				String kommaString=" ";
				statementText.setText(statementText.getText()+kommaString+tablsList.getItem(tablsList.getSelectionIndex()));
				addToListOfStatementTextChanges();
				kommaString="\r			,";
			}
		});
		//************************************************
		
		//************************************
		
		Label tableName =new Label(tableNameComposite, SWT.BORDER);
		textTableName=new Text(tableNameComposite, SWT.BORDER);
		final Button addTable =new Button(tableNameComposite, SWT.NONE);
		addTable.setText("Add Name of handeld Element:");
		addTable.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String kommaString=" ";
				buttonsCompo.setEnabled(true);
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+kommaString+textTableName.getText());
					addToListOfStatementTextChanges();
					kommaString="\r			,";
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String kommaString=" ";
				buttonsCompo.setEnabled(true);
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+kommaString+textTableName.getText());
					addToListOfStatementTextChanges();
					kommaString="\r			,";
				}
				
			}
		});
		//tableNameComposite.setEnabled(false);
		
		//************************************
		
		
//		final Button deleteButton =new Button(composite, SWT.BORDER);
//		deleteButton.setText("DELETE");
//		deleteButton.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				
//				statementText.setText("DELETE FROM\r	:COMMENT.hier comes the table name or WHERE, SELECT...");
//				
//				tableNameLabel.setEnabled(true);
//				tablsList.setEnabled(true);
//				deleteButton.setEnabled(false);
//				tableNameLabel.setEnabled(true);
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				statementText.setText("DELETE FROM\r	");
//				
//				tableNameLabel.setEnabled(true);
//				tablsList.setEnabled(true);
//				tableNameLabel.setEnabled(true);
//				deleteButton.setEnabled(false);
//			}
//		});
		
		if(statementText.getText().length()==0){
			//tableNameLabel.setEnabled(false);
			//tablsList.setEnabled(false);
		}
		else{ //if the statment not Empty 
			//tableNameLabel.setEnabled(false);
			//tablsList.setEnabled(false);
			
			//tablsList.setSelection(GetTablesFromStatement());
		}
		
		tablsList.setEnabled(true);
		tableNameComposite.setEnabled(true);
		
		loadTheTablesIntoList();
		parseStatment();
	}
	
	/** 
	  * splitting the word in table name and column name
	  * @param text
	  * @return
	  */
	 private String[] SplitTheWordInTwo(String text) {
		ArrayList<String> listOfLetters=new ArrayList<String>();
		String[] arrayOFWords=new String[2];
		int indexOfPoint = 1;
		for(int i=0;i<text.length()-1;i++){
			//listOfLetters.add(text.substring(i, i));
			if(text.substring(i, i+1).equals(".")) indexOfPoint=i; 
		}
		arrayOFWords[0]=text.substring(0,indexOfPoint-1);
		arrayOFWords[1]=text.substring(indexOfPoint+1,text.length());
		
		return arrayOFWords;
	  }
	  
	  /**
	   * for deleting the content of the colums&Values Table.
	   */
	  private void DeleteContentOfTable() {
			for(int i=0;i<tableOfColumnsAndValues.getItemCount();i++){
				if(tableOfColumnsAndValues.getItem(i).getText(0).length()>0){
					tableOfColumnsAndValues.getItem(i).dispose();
				}
			}
			
			
		}

	/**
	 * Removing all extra / unnasecerely Spaces in the String .
	 * @param theString
	 * @return cleanedString
	 */
	private String RemoveAllUnnasacerelySpaces(String theString){
		String cleanedString=theString;
		if((cleanedString!=null)&&(cleanedString.length()>1)){
			for(int i=0;i<cleanedString.length();i++){
				cleanedString=cleanedString.replace("  ", " ");
			}
			
		}
		return cleanedString;
	}
	
	/**
	 * loading the tables names of data source 
	 * into the List
	 */
	private void loadTheColumnsIntoList(String[] parsedColums) {
		columnList.removeAll();
		if(parsedColums!=null){
			for(int i=0;i<parsedColums.length;i++){
				columnList.add(parsedColums[i]);
			}
		}
	}
	
	/**
	 * 
	 */
	private void parseStatment() {
		
		String cleanedString=RemoveAllUnnasacerelySpaces(statementText.getText());
		String[] statmentWords=removeAllSpaces(cleanedString);
		if(statmentWords!=null){
			if(statmentWords.length>2){
				//String[] tempArray= new String[1];
				if(IsSringTableName(statmentWords[2])>=0){
					//tempArray[0]=statmentWords[2];
					tablsList.select(IsSringTableName(statmentWords[2]));//(index)select(tempArray);//select(IsSringTableName(statmentWords[2]));
					textTableName.setText(statmentWords[2]);
				}
			}
		}
		
		
		
		//tablsList
		
	}

	/**
	 * removes all spaces from statment
	 * @param statement
	 * @return statmentAsOneString
	 */
	private String[] removeAllSpaces(String statement) {
		String[] wordsOfCentence = null;
		String statmentAsOneString = "";
		
		if(statement!=null){
			if(statement.contains(" ")){
				wordsOfCentence=statement.split(" ");
			}
			if(wordsOfCentence!=null){
				for(int i=0;i<wordsOfCentence.length;i++){
					statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
				}
			}
			
			
			while(statmentAsOneString.contains(" ")){
				wordsOfCentence=statement.split(" ");
				for(int i=0;i<wordsOfCentence.length;i++){
					statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
					
				}
				
			}
		}
		return wordsOfCentence;
	}

	/**
	 * for checking if the string is one of the data source 
	 * Tables.
	 * @param string
	 * @return boolean
	 */
	private int IsSringTableName(String string) {
		//TODO: üerprüfen 
		
		for(int i=0;i<tablsList.getItemCount();i++){
			if(tablsList.getItem(i).equals(string)) return i;
		}
		
		return -1;
	}
	
	/**
	 * loading the tables names of data source 
	 * into the List
	 */
	private void loadTheTablesIntoList() {
		tablsList.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			tablsList.add("Table" + loopIndex);
		}
		//*************
		
		ArrayList<String> tabelsNames=null;//TODO: tables namen laden
		if(tabelsNames!=null){
			for(int i=0;i<tabelsNames.size();i++){
				tablsList.add(tabelsNames.get(i));
			}
		}
		
	}
	
	
	/**
	*
	*
	*/
	private void createTabTable(final ArrayList<KeyWord> listOfMainKeyWords){
		
		
		Label helpSympol=new Label(buttonsCompo, SWT.NONE);
		helpSympol.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/helpSymbole.png")));
		helpSympol.setToolTipText("The orange marks for showing you\r in wich order the Keywords \r can be used");
		//** creating the Tab Panel +++++++++++++++++
		/**
		   * Creates the contents
		   * 
		   * @param shell the parent shell
		   */
		  
			tabFolder = new TabFolder(compos, SWT.NONE);

		    // Create each tab and set its text, tool tip text,
		    // image, and control
		   
		   

		    // Select the third tab (index is zero-based)
		    

		    // Add an event listener to write the selected tab to stdout
		    tabFolder.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					tabFolder.getItem(tabFolder.getSelectionIndex()).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
					
					for(int i=0;i<tabFolder.getItems().length;i++)
					{
						if(i!=tabFolder.getSelectionIndex()){
						tabFolder.getItem(tabFolder.getSelectionIndex()).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
						}
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tabFolder.getItem(tabFolder.getSelectionIndex()).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
					for(int i=0;i<tabFolder.getItems().length;i++)
					{
						if(i!=tabFolder.getSelectionIndex()){
						tabFolder.getItem(tabFolder.getSelectionIndex()).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
						}
					}
				}
			});
		    for(int i=0;i<buttonList.size();i++)
			{
			    TabItem three = new TabItem(tabFolder, SWT.NONE);
//				if(listOfMainKeyWords.get(0).getListOfSubKeyWords().get(i).getMainKeyWord().toLowerCase().equals("values")){
//					three.setText("Table oriented");
//				}
//				else if(listOfMainKeyWords.get(0).getListOfSubKeyWords().get(i).getMainKeyWord().toLowerCase().equals("select")){
//					three.setText("SET oriented");
//				}
//				else three.setText(listOfMainKeyWords.get(0).getListOfSubKeyWords().get(i).getMainKeyWord());

					//three.setToolTipText("This is tab three");    
				//if(tmpKeyWord.getMainKeyWord().equals("VALUES")){
					//three.setControl(creatInsert_UIElements(tabFolder,TABLE_ORIENTED));
					three.setControl(getTabOneControl(tabFolder,buttonList.get(i).getText()));
				//}
			}
		 
		
		//+++++++++++++++++++++++++++++++++++++++++++
	}
	
	/**
	 * For creating the buttons out of the xml file ,wich contains
	 * the key words of the quary language. And after creating they
	 * will be added into the composite.
	 * 
	 * in this function we creat the buttons for the parsed KeyWords.
	 * 
	 * @param listOfMainKeyWords
	 */
	public void creatButtonsOfKeyWords(final ArrayList<KeyWord> listOfMainKeyWords){
		//System.out.print("\n in creatButtonsOfKeyWords()");
		
		for(int i=0;i<listOfMainKeyWords.size();i++)
		{
			if((listOfMainKeyWords.get(i).getListOfSubKeyWords().size()>0)){
				creatButtonsOfKeyWords(listOfMainKeyWords.get(i).getListOfSubKeyWords());
			}
			if(!(listOfMainKeyWords.get(i).getMainKeyWord().equals("INSERT"))){	
				final Button keyWordAsButton=new Button(buttonsCompo, SWT.NONE);
				keyWordAsButton.setText(listOfMainKeyWords.get(i).getMainKeyWord());
				//keyWordAsButton.setTextOfAction(listOfMainKeyWords.get(i).getTextOfKEyWord());
				keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));

				
				keyWordAsButton.setSize(listOfMainKeyWords.get(i).getMainKeyWord().length()+20, 70);
	
//				if(!listOfMainKeyWords.get(i).isTheMajorKey()){
//					keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
//				}
				//else isInsertKeyWord=false;
				
				final KeyWord tmpKeyWord=listOfMainKeyWords.get(i);
				keyWordAsButton.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						widgetSelected(e);
					}
	
					@Override
					public void widgetSelected(SelectionEvent e) {
						// TODO hier muss der statement befehle in der textBox eingetragen werden.
						
						
						
						try {
							statementText.setText(statementText.getText()+tmpKeyWord.getTextOfKEyWord());
							addToListOfStatementTextChanges();
							
							if(tmpKeyWord.getMainKeyWord().equals("WHERE")){
								tabFolder.setSelection(0);
							}
							if(tmpKeyWord.getMainKeyWord().equals("FROM")){
								tabFolder.setSelection(1);
							}
							
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
						
						
	//					fatherComp.getShell().getData("StyledText")
	//					s.setStatementText("sdfsdf");
					}
				});
				
				buttonList.add(keyWordAsButton);
			}
		}
		
	}


}
