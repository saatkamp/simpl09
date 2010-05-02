package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
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
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import widgets.LiveEditStyleText;
import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

public class InsertEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private LiveEditStyleText statementText = null;
	Text textTableName=null;
	Composite tableNameComposite=null;
	Text valuesText=null;
	Composite valuesCompo=null;
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	Composite compoOfTabFolder;
	List valuesList;
	
	String tmpString2;
	
	String[] arrayOfParsedColumns;
	String[] arrayOfParsedValues;
	
	
	// Create the containing tab folder
   TabFolder tabFolder;
    
   //********************************
    List tablsList,tablsList2,columnList=null;
    Composite actionsCompo=null;
   	String TABLE_ORIENTED="values";
	String SET_ORIENTED="select";
    int tableIndex=0; 
    Table tableOfColumnsAndValues,tableOfColumnsAndValues2;
    Composite valueCompo;
   //******************************** 
    
    //for select editor
    Combo comboColumnName,comboOperationName,comboDistAll;
	Text textValueForColumn;
	
	
    
    //List of statement Changes
    ArrayList<String> listOfStatementTextChanges=new ArrayList<String>();
    
	
	public ArrayList<String> getListOfStatementTextChanges() {
		return listOfStatementTextChanges;
	}

	public void addToListOfStatementTextChanges() {
		listOfStatementTextChanges.add(statementText.getText());
	}

	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreiben
	private String xmlFilePath="/keywords/InsertDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	public InsertEditor() {
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
		gridData2.grabExcessVerticalSpace =false;
		gridData2.verticalAlignment = GridData.FILL;
		GridData gridData1_1 = new GridData();
		gridData1_1.horizontalAlignment = GridData.END;
		gridData1_1.grabExcessHorizontalSpace = true;
		gridData1_1.grabExcessVerticalSpace = true;
		gridData1_1.verticalAlignment = GridData.END;
		compos = new Composite(comp, SWT.NONE);
		compos.setLayout(new GridLayout());
		compos.setLayoutData(gridData1);
		
		
		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(xmlFilePath);
		buttonsCompo=new Composite(compos, SWT.RIGHT_TO_LEFT);
		buttonsCompo.setLayout(gridLayoutA);
		creatButtonsOfKeyWords(parser.parseDocument());
		
		
		comp.setLayoutData(gridData);
		 
		Composite statementCompo=new Composite(comp, SWT.NONE);
		//TODO: hier war der versuch einen Undo Button neben statementFeld ....
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
		statementText.setEditable(false);
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
					statementText.setText("INSERT INTO ");
					
				}
				//tableNameComposite.setEnabled(true);
				//tablsList.setEnabled(true);
				//valuesCompo.setEnabled(true);

			}
			else{statementText.setText("INSERT INTO ");
				
			}
			addToListOfStatementTextChanges();
		}
		else {statementText.setText("INSERT INTO ");
			
		}
		
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
	    
	    
		
		if(keyWordValue.toLowerCase().equals("values"))
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
			columnList=new List(tableNameComposite, SWT.BORDER | SWT.V_SCROLL| SWT.H_SCROLL);
			try {
				tablsList.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTable();
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTable();
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
			
			
			columnList.setBounds(40, 20, 420, 200);
			GridData gridDatax = new GridData();
			gridDatax.horizontalAlignment = GridData.FILL;
			gridDatax.grabExcessHorizontalSpace = true;
			gridDatax.grabExcessVerticalSpace = true;
			gridDatax.verticalAlignment = GridData.FILL;
			columnList.setLayoutData(gridDatax);
			columnList.setSize(230, 150);
				columnList.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						if(columnList.getItem(columnList.getSelectionIndex()).length()>0)
						valueCompo.setVisible(true);
						
												
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						if(columnList.getItem(columnList.getSelectionIndex()).length()>0)
						valueCompo.setVisible(true);
					}
				});
			//************************************************************
			
			
			
			
			//***********************************************************
			GridData gridDatax1 = new GridData();
			Composite tableCompo=new Composite(tableNameComposite, SWT.NONE);
			GridLayout gridLayoutY1=new GridLayout();
			gridLayoutY1.numColumns=1;
			tableCompo.setLayout(gridLayoutY1);
			
			valueCompo=new Composite(tableCompo, SWT.NONE);
			GridLayout gridLayoutY2=new GridLayout();
			gridLayoutY2.numColumns=3;
			valueCompo.setLayout(gridLayoutY2);
			Label labelValueToInsert =new Label(valueCompo, SWT.NONE);
			labelValueToInsert.setText("Type the value to Insert: ");
			final Text valueForSelectedColumn=new Text(valueCompo, SWT.BORDER);
			valueForSelectedColumn.setSize(50, 20);
			
			Composite buttomsCompo=new Composite(tableNameComposite, SWT.BOLD);
			GridLayout gridLayoutY3=new GridLayout();
			gridLayoutY3.numColumns=1;
			buttomsCompo.setLayout(gridLayoutY3);
			
			Button insertItemInTable=new Button(buttomsCompo, SWT.NONE);
			insertItemInTable.setText("Insert to table");
			insertItemInTable.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					if(valueForSelectedColumn.getText().length()>0){
						tablsList.setEnabled(false);
						valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{columnList.getItem(columnList.getSelectionIndex()),valueForSelectedColumn.getText()});
						valueForSelectedColumn.setText("");
					}					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(valueForSelectedColumn.getText().length()>0){
						tablsList.setEnabled(false);
						valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{columnList.getItem(columnList.getSelectionIndex()),valueForSelectedColumn.getText()});
						valueForSelectedColumn.setText("");
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
						tablsList.setEnabled(true);
						
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableOfColumnsAndValues.getItem(tableOfColumnsAndValues.getSelectionIndex()).dispose();
					if(tableOfColumnsAndValues.getItemCount()==10){
						tablsList.setEnabled(true);
						
					}
					tableIndex--;
				}
			});
			
			tableOfColumnsAndValues = new Table(tableCompo, SWT.NONE);
			
			tableOfColumnsAndValues.setHeaderVisible(true);
			tableOfColumnsAndValues.setItemCount(10);
			tableOfColumnsAndValues.setLinesVisible(true);
			
			
			
			TableColumn columnName = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			columnName.setText("Column Name");
			TableColumn columnValues = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			columnValues.setText("Value");
			
			//TableColumn columnType = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			//columnType.setText("Type");
			
			columnName.setWidth(70);
			columnValues.setWidth(70);
			//columnType.setWidth(70);
			//***********************************************************
			
			
			
			//********************************
			actionsCompo=new Composite(tableNameComposite, SWT.NONE);
			GridLayout gridLayoutZ = new GridLayout();
			gridLayoutZ.numColumns = 2;
			
			actionsCompo.setLayout(gridLayoutZ);
	//			if(statementText.getText().length()<8){
	//				actionsCompo.setEnabled(false);
	//			}
			
			
			Button insertColumsIntoStatment=new Button(buttomsCompo, SWT.NONE);
			insertColumsIntoStatment.setToolTipText("Add Columns and the values into the Statment");
			insertColumsIntoStatment.setText("Insert to Statement");
			insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
				
				String tmpString="";
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(tableOfColumnsAndValues.getItemCount()>10){
						tableIndex=0;
						tablsList.setEnabled(true);
						
						TableItem[] arrayOfTableItems =tableOfColumnsAndValues.getItems();
						//arrayOfTableItems[0].getText(0)
						if(arrayOfTableItems.length>0) 	tmpString=" ,";
						else tmpString=" ";
						statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
						if(arrayOfTableItems[0].getText(0).length()>0){
							
							statementText.setText(statementText.getText()+" "+arrayOfTableItems[0].getText(0));
							}
						for(int i=1;i<arrayOfTableItems.length;i++){
							if(arrayOfTableItems[i].getText(0).length()>0){	
								statementText.setText(statementText.getText()+tmpString+arrayOfTableItems[i].getText(0));
							}
						}
						statementText.setText(statementText.getText()+")\r");
						addToListOfStatementTextChanges();
						
						if(arrayOfTableItems.length>0) 	tmpString=" ,";
						else tmpString=" ";
						statementText.setText(statementText.getText()+"	VALUES (");
						if(arrayOfTableItems[0].getText(1).length()>0){
							statementText.setText(statementText.getText()+" "+arrayOfTableItems[0].getText(1));
						}
						addToListOfStatementTextChanges();
						for(int i=1;i<arrayOfTableItems.length;i++){
							try {
								if(arrayOfTableItems[i].getText(1).length()>0){
									statementText.setText(statementText.getText()+tmpString+arrayOfTableItems[i].getText(1));
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								System.out.print("ERROR:"+e1.getMessage());
							}
				
						}
						statementText.setText(statementText.getText()+")\r");
						
						addToListOfStatementTextChanges();
						DeleteContentOfTable();
					}//end of if(...count>10)
				}
				
				

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
			
			
			
			//**************************************************************
			//parsing the colums and values from the statement into the lists.
			try {
				parseColumnsFromStatment();
			} catch (Exception e1) {
				System.out.print("ERROR:"+e1.getMessage());
			}
			if(arrayOfParsedColumns!=null){
				try {
					loadTheColumnsIntoList(arrayOfParsedColumns);
				} catch (Exception e1) {
					System.out.print("ERROR:"+e1.getMessage());
				}
			}else{columnList.removeAll();}
			
			if(arrayOfParsedValues!=null){
				try {
					loadTheColumnsIntoList(arrayOfParsedValues);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.print("ERROR:"+e1.getMessage());
				}
			}else{valuesList.removeAll();}
			
			tableNameComposite.setEnabled(true);
			tablsList.setEnabled(true);
			//valuesCompo.setEnabled(true);
		}//end of if values-Part
		
		
		
		//******************************************************************
		if(keyWordValue.toLowerCase().equals("select"))
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
			
			tablsList2 = new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
			tablsList2.setBounds(40, 20, 320, 100);
			tablsList2.setLayoutData(gridData1);
			tablsList2.setEnabled(false);
			try {
				loadTheTablesIntoList2();
			} catch (Exception e2) {
				System.out.print("ERROR: "+e2.getMessage());
			}
			
			try {
				tablsList2.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTableIntoCombo();
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}
					
					

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTableIntoCombo();
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
			
			
			
			
			
			
			//***********************************************************
			GridData gridDatax1 = new GridData();
			Composite tableCompo=new Composite(tableNameComposite, SWT.NONE);
			GridLayout gridLayoutY1=new GridLayout();
			gridLayoutY1.numColumns=1;
			tableCompo.setLayout(gridLayoutY1);
			
//			valueCompo=new Composite(tableCompo, SWT.NONE);
//			GridLayout gridLayoutY2=new GridLayout();
//			gridLayoutY2.numColumns=3;
//			valueCompo.setLayout(gridLayoutY2);
//			Label labelValueToInsert =new Label(valueCompo, SWT.NONE);
//			labelValueToInsert.setText("Type the value to Insert: ");
//			final Text valueForSelectedColumn=new Text(valueCompo, SWT.BORDER);
//			valueForSelectedColumn.setSize(50, 20);
			
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
						TableItem tableItem=new TableItem(tableOfColumnsAndValues2, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName.getText(),comboOperationName.getText(),comboDistAll.getText(),textValueForColumn.getText()});
						textValueForColumn.setText("");
					}					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(textValueForColumn.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues2, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName.getText(),comboOperationName.getText(),comboDistAll.getText(),textValueForColumn.getText()});
						textValueForColumn.setText("");
					}		
				}
			});
			
			Button deleteFromColumnList =new Button(buttomsCompo, SWT.NONE);
			deleteFromColumnList.setText("Remove from table");
			deleteFromColumnList.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					tableOfColumnsAndValues2.getItem(tableOfColumnsAndValues2.getSelectionIndex()).dispose();
					tableIndex--;
					if(tableOfColumnsAndValues2.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableOfColumnsAndValues.getItem(tableOfColumnsAndValues2.getSelectionIndex()).dispose();
					if(tableOfColumnsAndValues2.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
					tableIndex--;
				}
			});
			Composite titelPartOfTableCombo=new Composite(tableCompo, SWT.BOLD);
			GridLayout gridLayoutY4=new GridLayout();
			gridLayoutY4.numColumns=8;
			titelPartOfTableCombo.setLayout(gridLayoutY4);
			Label labelColumnName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelColumnName.setText("Column name: ");		
			comboColumnName=new Combo(titelPartOfTableCombo, SWT.BORDER);
			Label labelOperationName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelOperationName.setText("Operation: ");
			comboOperationName=new Combo(titelPartOfTableCombo, SWT.BORDER);
			comboOperationName.add("AVG");
			comboOperationName.add("MAX");
			comboOperationName.add("MIN");
			comboOperationName.add("SUM");
			comboOperationName.add("COUNT");
			Label labelDistAllName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelDistAllName.setText("DIST/ALL: ");
			comboDistAll=new Combo(titelPartOfTableCombo, SWT.BORDER);
			comboDistAll.add("DISTINCT");
			comboDistAll.add("ALL");
			Label labelValueOfColumn=new Label(titelPartOfTableCombo, SWT.NONE);
			labelValueOfColumn.setText("Value: ");
			textValueForColumn=new Text(titelPartOfTableCombo, SWT.BORDER);
			
			tableOfColumnsAndValues2 = new Table(tableCompo, SWT.NONE);
			
			tableOfColumnsAndValues2.setHeaderVisible(true);
			tableOfColumnsAndValues2.setItemCount(10);
			tableOfColumnsAndValues2.setLinesVisible(true);
			
			
			
			TableColumn columnName = new TableColumn(tableOfColumnsAndValues2, SWT.CENTER);
			columnName.setText("Column Name");
			TableColumn columnOperation = new TableColumn(tableOfColumnsAndValues2, SWT.CENTER);
			columnOperation.setText("Operation");
			TableColumn columnDisctAll = new TableColumn(tableOfColumnsAndValues2, SWT.CENTER);
			columnDisctAll.setText("DISCT/ALL");
			TableColumn columnValues = new TableColumn(tableOfColumnsAndValues2, SWT.CENTER);
			columnValues.setText("Value");
			
			//TableColumn columnType = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			//columnType.setText("Type");
			
			columnName.setWidth(150);
			columnOperation.setWidth(150);
			columnDisctAll.setWidth(150);
			columnValues.setWidth(150);
			//columnType.setWidth(70);
			//***********************************************************
			
			
			
			//********************************
			Button insertColumsIntoStatment=new Button(buttomsCompo, SWT.NONE);
			insertColumsIntoStatment.setToolTipText("Add Columns and the values into the Statment");
			insertColumsIntoStatment.setText("Insert to Statement");
			insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
				
				String operStat="";
				String isDistict="";
				String[] tmpString=null;
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(tableOfColumnsAndValues2.getItemCount()>10){
						tableIndex=0;
						//tablsList.setEnabled(true);
						
						TableItem[] arrayOfTableItems =tableOfColumnsAndValues2.getItems();
						//arrayOfTableItems[0].getText(0)
						//if(arrayOfTableItems.length>1) 	tmpString=" ,";
						//else tmpString=" ";
						//statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
						
						statementText.setText(statementText.getText()+"\r	SELECT ");
						tmpString=SplitTheWordInTwo(arrayOfTableItems[0].getText(0));
						if(arrayOfTableItems[0].getText(1).length()>0) {
							
							System.out.print(tmpString[0]);
							operStat=arrayOfTableItems[0].getText(1)+"("+tmpString[1]+")";
						}
						else operStat=tmpString[1];
						
						statementText.setText(statementText.getText()+arrayOfTableItems[0].getText(2)+" "+operStat);

						for(int i=1;i<arrayOfTableItems.length;i++){
							if(arrayOfTableItems[i].getText(0).length()>0){	
								tmpString=SplitTheWordInTwo(arrayOfTableItems[i].getText(0));
								if(arrayOfTableItems[i].getText(1).length()>0){
									
									operStat=arrayOfTableItems[i].getText(1)+"("+tmpString[1]+")";
								}
								else operStat=tmpString[1];
								
								statementText.setText(statementText.getText()+arrayOfTableItems[i].getText(2)+" "+operStat);							}
						}
						addToListOfStatementTextChanges();
						
						statementText.setText(statementText.getText()+" FROM ");
						tmpString=SplitTheWordInTwo(arrayOfTableItems[0].getText(0));
						statementText.setText(statementText.getText()+" "+tmpString[0]);
						for(int i=1;i<arrayOfTableItems.length;i++){
							if(arrayOfTableItems[i].getText(0).length()>0){
								tmpString=SplitTheWordInTwo(arrayOfTableItems[i].getText(0));
								statementText.setText(statementText.getText()+","+tmpString[0]);
							}
						}
						
						statementText.setText(statementText.getText()+")\r");
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
			tablsList2.setEnabled(true);
			//valuesCompo.setEnabled(true);
		}//end of if select-Part
		
		
	    return composite;
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
	 * 
	 * @param composite
	 * @return 
	 */
//	private Composite creatInsert_UIElements(TabFolder tabFolder,String actionName){
//		
//		Composite composite = new Composite(tabFolder, SWT.NONE);
//		GridData gridData = new GridData();
//		gridData.horizontalAlignment = GridData.FILL;
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.grabExcessVerticalSpace = true;
//		gridData.verticalAlignment = GridData.FILL;
//		composite.setLayout(new GridLayout());
//		composite.setLayoutData(gridData);
//		
//		if(actionName.equals(TABLE_ORIENTED)){
//			
//			Button insertButton;
//			
//			
//			//
//			//********************************
//			GridLayout gridLayoutx = new GridLayout();
//			gridLayoutx.numColumns = 3;
//			tableNameComposite=new Composite(composite, SWT.NONE);
//			tableNameComposite.setEnabled(false);
//			tableNameComposite.setLayout(gridLayoutx);
//			if(statementText.getText().length()<8){
//				tableNameComposite.setEnabled(false);
//			}
//			
//			Label tableName =new Label(tableNameComposite, SWT.NONE);
//			tableName.setText("Type the name of the Table: ");
//			textTableName=new Text(tableNameComposite, SWT.BORDER);
//			final Button addTable =new Button(tableNameComposite, SWT.NONE);
//			addTable.setText("Add to Statement");
//			addTable.addSelectionListener(new SelectionListener() {
//				
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					// TODO Auto-generated method stub
//					
//					try {
//						if(textTableName.getText().length()>0){
//							statementText.setText(statementText.getText()+"	"+textTableName.getText()+"\r	VALUES ");
//							//columnCompo.setEnabled(true);
//							//buttonsCompo.setEnabled(true);
//							//columsListCompo.setEnabled(true);
//						}
//					} catch (Exception e1) {
//						System.out.print("ERROR: "+e1.getMessage());
//					}
//				}
//				
//				@Override
//				public void widgetDefaultSelected(SelectionEvent e) {
//					// TODO Auto-generated method stub
//					
//					if(textTableName.getText().length()>0){
//						statementText.setText(statementText.getText()+"	"+textTableName.getText()+"\r	VALUES ");
//						//columnCompo.setEnabled(true);
//						//buttonsCompo.setEnabled(true);
//						//columsListCompo.setEnabled(true);
//					}
//					
//				}
//			});
//			//************************************
//			
//			
//			
//			
//			//*************************************************
//			GridData gridData1 = new GridData();
//			gridData1.horizontalAlignment = GridData.FILL;
//			gridData1.grabExcessHorizontalSpace = true;
//			gridData1.grabExcessVerticalSpace = true;
//			gridData1.verticalAlignment = GridData.FILL;
//			
//			tablsList = new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
//			tablsList.setBounds(40, 20, 320, 100);
//			tablsList.setLayoutData(gridData1);
//			tablsList.setEnabled(false);
//			try {
//				loadTheTablesIntoList();
//			} catch (Exception e2) {
//				System.out.print("ERROR: "+e2.getMessage());
//			}
//			
//			try {
//				tablsList.addSelectionListener(new SelectionListener() {
//					
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						
//						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
//						try {
//							loadColumnsOfTable();
//						} catch (Exception e1) {
//							System.out.print("ERROR: "+e1.getMessage());
//						}
//					}
//					
//					@Override
//					public void widgetDefaultSelected(SelectionEvent e) {
//						
//						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
//						try {
//							loadColumnsOfTable();
//						} catch (Exception e1) {
//							System.out.print("ERROR: "+e1.getMessage());
//						}
//					}
//				});
//			} catch (Exception e1) {
//				System.out.print("ERROR: "+e1.getMessage());
//			}
//			//**************************************************************
//			
//			//************************************************************
//			columnList=new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL| SWT.H_SCROLL);
//			columnList.setBounds(40, 20, 420, 200);
//			GridData gridDatax = new GridData();
//			gridDatax.horizontalAlignment = GridData.FILL;
//			gridDatax.grabExcessHorizontalSpace = true;
//			gridDatax.grabExcessVerticalSpace = true;
//			gridDatax.verticalAlignment = GridData.FILL;
//			columnList.setLayoutData(gridDatax);
//			//columnList.setSize(230, 150);
//			
//			
//			
//			
//			
//	//		columnList.addSelectionListener(new SelectionListener() {
//	//			
//	//			@Override
//	//			public void widgetSelected(SelectionEvent e) {
//	//				if(columnList.getItems().length>0) 	tmpString2="\r			,";
//	//				else tmpString2="";
//	//				//statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));
//	//				
//	//			}
//	//			
//	//			@Override
//	//			public void widgetDefaultSelected(SelectionEvent e) {
//	//				if(columnList.getItems().length>0) 	tmpString2="\r			,";
//	//				else tmpString2="";
//	//				//statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));
//	//
//	//			}
//	//		});
//			//************************************************************
//			
//			//************************************************************
//			
//			
//			
//			//************************************************************
//			
//			//***********************************************************
//			valuesCompo=new Composite(tableNameComposite, SWT.BORDER);
//			valuesCompo.setEnabled(false);
//			GridLayout gridLayoutY=new GridLayout();
//			gridLayoutY.numColumns=1;
//			valuesCompo.setLayout(gridLayoutY);
//			Label valuesLabel=new Label(valuesCompo, SWT.NONE);
//			valuesLabel.setText("The Values for Inserting: ");
//			valuesText=new Text(valuesCompo, SWT.BORDER);
//			valuesText.setSize(250, 50);
//			Button addValuesIntoList=new Button(valuesCompo, SWT.NONE);
//			addValuesIntoList.setText("Add Values to Statement");
//			//valuesText.setText(getParsedVlauesFromStatement()); 
//			addValuesIntoList.addSelectionListener(new SelectionListener() {
//				
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					valuesList.add(valuesText.getText());
//					valuesText.setText("");
//				}
//				
//				@Override
//				public void widgetDefaultSelected(SelectionEvent e) {
//					valuesList.add(valuesText.getText());
//					valuesText.setText("");
//				}
//			});
//			valuesList=new List(valuesCompo, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
//			valuesList.setBounds(40, 20, 420, 200);
//			GridData gridDatax2 = new GridData();
//			gridDatax2.horizontalAlignment = GridData.FILL;
//			gridDatax2.grabExcessHorizontalSpace = true;
//			gridDatax2.grabExcessVerticalSpace = true;
//			gridDatax2.verticalAlignment = GridData.FILL;
//			valuesList.setLayoutData(gridDatax2);
//			valuesList.setSize(170, 150);
//			//***********************************************************
//			
//			
//			//***********************************************************
//			
//			//***********************************************************
//			
//			//********************************
//			actionsCompo=new Composite(tableNameComposite, SWT.NONE);
//			GridLayout gridLayoutZ = new GridLayout();
//			gridLayoutZ.numColumns = 2;
//			
//			actionsCompo.setLayout(gridLayoutZ);
//	//		if(statementText.getText().length()<8){
//	//			actionsCompo.setEnabled(false);
//	//		}
//			
//			
//			Button insertColumsIntoStatment=new Button(actionsCompo, SWT.NONE);
//			insertColumsIntoStatment.setToolTipText("Add Columns and the values into the Statment");
//			insertColumsIntoStatment.setText("Insert to Statement");
//			insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
//				
//				String tmpString="";
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					
//					if(columnList.getItems().length>0) 	tmpString=" ,";
//					else tmpString=" ";
//					
//					statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
//					statementText.setText(statementText.getText()+" "+columnList.getItems()[columnList.getSelectionIndices()[0]]);
//					for(int i=1;i<columnList.getSelectionIndices().length;i++){
//						//if(columnList.isSelected(i)){
//							statementText.setText(statementText.getText()+tmpString+columnList.getItems()[columnList.getSelectionIndices()[i]]);
//						//}
//					}
//					statementText.setText(statementText.getText()+")\r");
//	
//					
//					if(valuesList.getItems().length>0) 	tmpString=" ,";
//					else tmpString=" ";
//					statementText.setText(statementText.getText()+"	VALUES (");
//					statementText.setText(statementText.getText()+" "+valuesList.getItems()[0]);
//					for(int i=1;i<valuesList.getItems().length;i++){
//						statementText.setText(statementText.getText()+tmpString+valuesList.getItems()[i]);
//				
//					}
//					statementText.setText(statementText.getText()+")\r");
//	
//				}
//				
//				@Override
//				public void widgetDefaultSelected(SelectionEvent e) {
//					
//					if(columnList.getItems().length>0) 	tmpString=" ,";
//					else tmpString=" ";
//					statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
//					statementText.setText(statementText.getText()+" "+columnList.getItems()[columnList.getSelectionIndices()[0]]);
//					for(int i=1;i<columnList.getSelectionIndices().length;i++){
//						//if(columnList.isSelected(i)){
//							statementText.setText(statementText.getText()+tmpString+columnList.getItems()[columnList.getSelectionIndices()[i]]);
//						//}
//					}
//					statementText.setText(statementText.getText()+")\r");
//	
//					if(valuesList.getItems().length>0) 	tmpString=" ,";
//					else tmpString=" ";
//					statementText.setText(statementText.getText()+"	VALUES (");
//					statementText.setText(statementText.getText()+" "+valuesList.getItems()[0]);
//					for(int i=1;i<valuesList.getItems().length;i++){
//						try {
//							statementText.setText(statementText.getText()+tmpString+valuesList.getItems()[i]);
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							System.out.print("ERROR:"+e1.getMessage());
//						}
//				
//					}
//					statementText.setText(statementText.getText()+")\r");
//	
//				}
//			});
//			
//			Button deleteFromColumnList =new Button(actionsCompo, SWT.NONE);
//			deleteFromColumnList.setText("Remove from List");
//			deleteFromColumnList.addSelectionListener(new SelectionListener() {
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					// TODO Auto-generated method stub
//					columnList.remove(columnList.getSelectionIndex());
//				}
//				
//				@Override
//				public void widgetDefaultSelected(SelectionEvent e) {
//					// TODO Auto-generated method stub
//					columnList.remove(columnList.getSelectionIndex());
//				}
//			});
//			
//			//**************************************************************
//			//parsing the colums and values from the statement into the lists.
//			try {
//				parseColumnsFromStatment();
//			} catch (Exception e1) {
//				System.out.print("ERROR:"+e1.getMessage());
//			}
//			if(arrayOfParsedColumns!=null){
//				try {
//					loadTheColumnsIntoList(arrayOfParsedColumns);
//				} catch (Exception e1) {
//					System.out.print("ERROR:"+e1.getMessage());
//				}
//			}else{columnList.removeAll();}
//			
//			if(arrayOfParsedValues!=null){
//				try {
//					loadTheValuesIntoList(arrayOfParsedValues);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					System.out.print("ERROR:"+e1.getMessage());
//				}
//			}else{valuesList.removeAll();}
//			
//			tableNameComposite.setEnabled(true);
//			tablsList.setEnabled(true);
//			valuesCompo.setEnabled(true);
//			
//			
//			
//		}//end of if()
//		if(actionName.equals(SET_ORIENTED)){
//			
//		}
//		
//		return composite;
//	}
	
	
	

	/**
	 * 
	 * @return
	 */
	private void parseColumnsFromStatment() {
		// TODO Auto-generated method stub
		ArrayList<String> parsedColumns=new ArrayList<String>();
		ArrayList<String> parsedValues=new ArrayList<String>();
		
		
		String[] wordsOfStatment = null;
		try {
			String cleandStatment=removeAllSpaces(statementText.getText());
			wordsOfStatment = cleandStatment.split("\r");
		} catch (Exception e) {
			System.out.print("ERROR:"+e.getMessage());
		}
		
		if((wordsOfStatment!=null)&&(wordsOfStatment.length>0)){
			//
				//if(cleandStatment.contains(")")){
					//while(!(wordsOfStatment[i].contains(")"))){
						if((wordsOfStatment[0].contains("("))){
							String[] wordsOfFirstLine =wordsOfStatment[0].split(" ");
							String tmpString = "";
							for(int i=5;i<wordsOfFirstLine.length;i++){
								try {
									if(i==wordsOfFirstLine.length-1) tmpString=tmpString+wordsOfFirstLine[i].substring(0,wordsOfFirstLine[i].length()-1);
									else tmpString=tmpString+wordsOfFirstLine[i];
								} catch (Exception e) {
									System.out.print("ERROR:"+e.getMessage());
								}
							}
							String[] colums=tmpString.split(",");
							for(int i=0;i<colums.length;i++){
								try {
									parsedColumns.add(colums[i]);
								} catch (Exception e) {
									System.out.print("ERROR:"+e.getMessage());
								}
							}
							
							
						}
						if(wordsOfStatment.length>2){
							if((wordsOfStatment[1].contains(" ("))){
								String[] wordsOfSecondLine = null;
								try {
									wordsOfSecondLine = wordsOfStatment[1].split(" ");
								} catch (Exception e) {
									System.out.print("ERROR:"+e.getMessage());
								}
								String tmpString = "";
								for(int i=2;i<wordsOfSecondLine.length;i++){
									if(!wordsOfSecondLine[i].equals(" ")){
										if(i==wordsOfSecondLine.length-1) tmpString=tmpString+wordsOfSecondLine[i].substring(0,wordsOfSecondLine[i].length()-1);
										else tmpString=tmpString+wordsOfSecondLine[i];
									}
									
								}
								String[] values = null;
								try {
									values = tmpString.split(",");
								} catch (Exception e) {
									System.out.print("ERROR:"+e.getMessage());
								}
								for(int i=0;i<values.length;i++){
									try {
										parsedValues.add(values[i]);
									} catch (Exception e) {
										System.out.print("ERROR:"+e.getMessage());
									}
								}

							}	
						}
				}
			//converting the List to arrays.
			arrayOfParsedColumns=new String[parsedColumns.size()];
			arrayOfParsedValues=new String[parsedValues.size()];
			for(int i=0;i<arrayOfParsedColumns.length;i++){
				try {
					arrayOfParsedColumns[i]=parsedColumns.get(i);
				} catch (Exception e) {
					System.out.print("ERROR:"+e.getMessage());
				}
			}
			for(int i=0;i<arrayOfParsedValues.length;i++){
				try {
					arrayOfParsedValues[i]=parsedValues.get(i);
				} catch (Exception e) {
					System.out.print("ERROR:"+e.getMessage());
				}
			}
			
			if(wordsOfStatment.length>1){
				if(wordsOfStatment[wordsOfStatment.length-1].contains(")")) parsedColumns.add(wordsOfStatment[wordsOfStatment.length-1]);
			}
//			String[] wordsOfStatment2=new String[parsedColumns.size()];
//			for(int i=0;i<wordsOfStatment2.length;i++){
//				wordsOfStatment2[i]=parsedColumns.get(i);
//				if(wordsOfStatment2[i].contains(",")){
//					wordsOfStatment2[i]=wordsOfStatment2[i].substring(1);
//				}
//			}
//			
//			
//			if((wordsOfStatment2.length>1)&&(wordsOfStatment2!=null)){
//				//hier i remove the "("  ")" from the columns lines.
//				if(wordsOfStatment2[wordsOfStatment2.length-1].contains(")")){
//					wordsOfStatment2[wordsOfStatment2.length-1]=wordsOfStatment2[wordsOfStatment2.length-1].
//						substring(wordsOfStatment2[wordsOfStatment2.length-1].length()-2,
//											wordsOfStatment2[wordsOfStatment2.length-1].length()-1);
//				}
//			
//			
//			if(wordsOfStatment2[0].contains("("))
//				wordsOfStatment2[0]=wordsOfStatment2[0].substring(1);
//			
//			}
//			
//		
//			return wordsOfStatment2;
		//}	
		
		
	}
	
	/**
	 * for loading the colums of the table into the combo of 
	 * colums names.
	 */
	private void loadColumnsOfTableIntoCombo() {
		String tableName = "";
		try {
			tableName = tablsList2.getItem(tablsList2.getSelectionIndex());
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
	
	
	/**
	 * load the columns of the selected table.
	 */
	private void loadColumnsOfTable() {
		
		
		String tableName = null;
		try {
			tableName = tablsList.getItem(tablsList.getSelectionIndex());
			columnList.removeAll();
		} catch (Exception e) {
			System.out.print("ERROR:"+e.getMessage());
		}
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			try {
				columnList.add(tableName+".Column" + loopIndex);
			} catch (Exception e) {
				System.out.print("ERROR:"+e.getMessage());
			}
		}
		//*************
		
		ArrayList<String> columnsNames=null;//TODO: Column namen laden
		if(columnsNames!=null){
			for(int i=0;i<columnsNames.size();i++){
				try {
					columnList.add(columnsNames.get(i));
				} catch (Exception e) {
					System.out.print("ERROR:"+e.getMessage());
				}
			}
		}
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
	 * loading the values  of data source 
	 * into the values List
	 */
	private void loadTheValuesIntoList(String[] parsedValues) {
		valuesList.removeAll();
		if(parsedValues!=null){
			for(int i=0;i<parsedValues.length;i++){
				valuesList.add(parsedValues[i]);
			}
		}
	}

	/**
	 * its for parsing the statement and getting the Values of it
	 * 
	 * @return
	 */
	private String getParsedVlauesFromStatement() {
		String valuesString="";
		try {
			if((statementText.getText().length()>0)&&(statementText.getText()!=null)){
				String cleandStatment=removeAllSpaces(statementText.getText());
				String[] wordsOfStatment =cleandStatment.split("\r");
				if(wordsOfStatment.length>1){
					if(wordsOfStatment[1].length()>7){
					 valuesString=wordsOfStatment[1].substring(7,wordsOfStatment[1].length()-1);
					}
					else{ valuesString="";}
				}
			}
		} catch (Exception e) {
			System.out.print("ERROR:"+e.getMessage());
		}
		
		return valuesString;
	}

	/**
	 * removes all spaces from statment
	 * @param statement
	 * @return statmentAsOneString
	 */
	private String removeAllSpaces(String statement) {
		String[] wordsOfCentence = null;
		String statmentAsOneString = "";
		
		if(statement!=null){
			if(statement.contains(" ")){
				wordsOfCentence=statement.split(" ");
			}
			if(wordsOfCentence!=null){
				for(int i=0;i<wordsOfCentence.length;i++){
					statmentAsOneString=statmentAsOneString+wordsOfCentence[i]+" ";
				}
			}
			
			
//			while(statmentAsOneString.contains(" ")){
//				wordsOfCentence=statement.split(" ");
//				for(int i=0;i<wordsOfCentence.length;i++){
//					statmentAsOneString=statmentAsOneString+" "+wordsOfCentence[i];
//					
//				}
//				
//			}
		}
		return statmentAsOneString;
	}
	
	/**
	 * loading the tables names of data source 
	 * into the List
	 */
	private void loadTheTablesIntoList() {
		
		tablsList2.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			tablsList2.add("Table" + loopIndex);
		}
		//*************
		
//		ArrayList<String> tabelsNames=null;//TODO: tables namen laden
//		if(tabelsNames!=null){
//			for(int i=0;i<tabelsNames.size();i++){
//				tablsList2.add(tabelsNames.get(i));
//			}
//		}
		
		
	}
	
	/**
	 * loading the tables names of data source 
	 * into the List
	 */
	private void loadTheTablesIntoList2() {
		
		tablsList2.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			tablsList2.add("Table" + loopIndex);
		}
		//*************
		
//		ArrayList<String> tabelsNames=null;//TODO: tables namen laden
//		if(tabelsNames!=null){
//			for(int i=0;i<tabelsNames.size();i++){
//				tablsList2.add(tabelsNames.get(i));
//			}
//		}
		
		
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
		   compoOfTabFolder=new Composite(compos, SWT.NONE);
			tabFolder = new TabFolder(compoOfTabFolder, SWT.NONE);
			
		    // Create each tab and set its text, tool tip text,
		    // image, and control
		   
		   

		    // Select the third tab (index is zero-based)
		    

		    // Add an event listener to write the selected tab to stdout
//		    tabFolder.addSelectionListener(new SelectionListener() {
//				
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					tabFolder.getItem(tabFolder.getSelectionIndex()).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
//					
//					for(int i=0;i<tabFolder.getItems().length;i++)
//					{
//						if(i!=tabFolder.getSelectionIndex()){
//						tabFolder.getItem(tabFolder.getSelectionIndex()).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
//						}
//					}
//				}
//				
//				@Override
//				public void widgetDefaultSelected(SelectionEvent e) {
//					tabFolder.getItem(tabFolder.getSelectionIndex()).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
//					for(int i=0;i<tabFolder.getItems().length;i++)
//					{
//						if(i!=tabFolder.getSelectionIndex()){
//						tabFolder.getItem(tabFolder.getSelectionIndex()).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
//						}
//					}
//				}
//			});
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
				if(!(buttonList.get(i).getText().equals("FROM"))){
					//three.setControl(creatInsert_UIElements(tabFolder,TABLE_ORIENTED));
					three.setControl(getTabOneControl(tabFolder,buttonList.get(i).getText()));
				}
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
							
							if(tmpKeyWord.getMainKeyWord().equals("VALUES")){
								tabFolder.setSelection(0);
							}
							if(tmpKeyWord.getMainKeyWord().equals("SELECT")){
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
