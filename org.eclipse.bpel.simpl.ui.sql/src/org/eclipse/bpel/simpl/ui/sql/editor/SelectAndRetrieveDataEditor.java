package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.bpel.simpl.ui.sql.widgets.LiveEditStyleText;
import org.eclipse.bpel.simpl.ui.sql.xmlParser.KeyWord;
import org.eclipse.bpel.simpl.ui.sql.xmlParser.QueryKeyWordsXmlParser;
import org.eclipse.swt.SWT;
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

public class SelectAndRetrieveDataEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private LiveEditStyleText statementText = null;
	Label proceLabel;
	Button addToStatement;
	Text proceText;
	Text textTableName=null;
	Composite tableNameComposite=null;
	Text valuesText=null;
	Composite valuesCompo=null;
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	private Composite resultSETStatementCompo=null;
	//private List listOfTabels=null,listOfColumns=null;
	private Text resultSelectedTableColumns=null;
	Composite listsComposite=null;
	// Create the containing tab folder
   TabFolder tabFolder;
   String TABLE_ORIENTED="values";
	String SET_ORIENTED="select";
	int tableIndex=0,tableIndex2=0,tableIndex3=0,tableIndex4=0;
	Table tableOfColumnsAndValues,tableOfColumnsAndValues2,tableOfColumnsAndValues3,tableOfColumnsAndValues4,tableOfColumnsAndValues5;
	List tablsList,tablsList2,tablsList3,tablsList4,tablsList5;
	int folderTabsIndex=0;
	Combo comboColumnName,comboColumnName2,comboColumnName3,comboColumnName4,comboColumnName5,comboOperationName,comboOperationName2,comboOperationName3,comboOperationName4,comboDistAll,comboOperationType;
	Text textValueForColumn,textValueForColumn2,textValueForColumn3,textValueForColumn4;
	
	Combo pradiktCombo;
	
	//List of statement Changes
    ArrayList<String> listOfStatementTextChanges=new ArrayList<String>();
    
	
	public ArrayList<String> getListOfStatementTextChanges() {
		return listOfStatementTextChanges;
	}

	public void addToListOfStatementTextChanges() {
		listOfStatementTextChanges.add(statementText.getText());
	}
	
	/**
	 * UPDATE table_name
		SET column1=value, column2=value2,...
		WHERE some_column=some_value
	 */
	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreiben
	private String xmlFilePath=null;	
	public String getXmlFilePath() {
		return xmlFilePath;
	}
	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	
	
	private String mainStatmentKeyWord="";
	public String getMainStatmentKeyWord() {
		return mainStatmentKeyWord;
	}

	public void setMainStatmentKeyWord(String tmpMainStatmentKeyWord) {
		mainStatmentKeyWord = tmpMainStatmentKeyWord;
	}
	

	

	public SelectAndRetrieveDataEditor() {
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

		GridData gridData1_1 = new GridData();
		gridData1_1.horizontalAlignment = GridData.END;
		gridData1_1.grabExcessHorizontalSpace = true;
		gridData1_1.grabExcessVerticalSpace = true;
		gridData1_1.verticalAlignment = GridData.END;
		
		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(getXmlFilePath());
		buttonsCompo=new Composite(compos, SWT.RIGHT_TO_LEFT);
		buttonsCompo.setLayout(gridLayoutA);
		creatButtonsOfKeyWords(parser.parseDocument());
		
		comp.setLayoutData(gridData);
		//statementText = new StyledText(comp, SWT.BORDER| SWT.H_SCROLL| SWT.V_SCROLL);
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
			if(statementText.getText().length()>8){
				if(statementText.getText().equals("statement")){
					statementText.setText("SELECT ");
				}
			}
			else{statementText.setText("SELECT ");}
			addToListOfStatementTextChanges();
		}
		else {statementText.setText("SELECT ");}
		
		//createEditorElements(compos);
		
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
			//tablsList.setEnabled(false);
			try {
				loadTheTablesIntoList2();
			} catch (Exception e2) {
				System.out.print("ERROR: "+e2.getMessage());
			}
			
			try {
				tablsList2.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						System.out.print(tablsList2.getItem(tablsList2.getSelectionIndex()));
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							comboColumnName5.add("test");
							loadColumnsOfTableIntoCombo2(tablsList2.getItem(tablsList2.getSelectionIndex()));
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTableIntoCombo2(tablsList2.getItem(tablsList2.getSelectionIndex()));
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
//			columnList=new List(tableNameComposite, SWT.BORDER | SWT.V_SCROLL| SWT.H_SCROLL);
//			columnList.setBounds(40, 20, 420, 200);
//			GridData gridDatax = new GridData();
//			gridDatax.horizontalAlignment = GridData.FILL;
//			gridDatax.grabExcessHorizontalSpace = true;
//			gridDatax.grabExcessVerticalSpace = true;
//			gridDatax.verticalAlignment = GridData.FILL;
//			columnList.setLayoutData(gridDatax);
//			columnList.setSize(230, 150);
//				columnList.addSelectionListener(new SelectionListener() {
//					
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						if(columnList.getItem(columnList.getSelectionIndex()).length()>0)
//						valueCompo.setVisible(true);
//						
//												
//					}
//					
//					@Override
//					public void widgetDefaultSelected(SelectionEvent e) {
//						if(columnList.getItem(columnList.getSelectionIndex()).length()>0)
//						valueCompo.setVisible(true);
//					}
//				});
//			//************************************************************
			
			
			
			
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
						TableItem tableItem=new TableItem(tableOfColumnsAndValues, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName5.getText(),comboOperationName.getText(),comboDistAll.getText(),textValueForColumn.getText()});
						textValueForColumn.setText("");
					}					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(textValueForColumn.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName2.getText(),comboOperationName.getText(),comboDistAll.getText(),textValueForColumn.getText()});
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
			gridLayoutY4.numColumns=8;
			titelPartOfTableCombo.setLayout(gridLayoutY4);
			Label labelColumnName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelColumnName.setText("Column name: ");		
			comboColumnName5=new Combo(titelPartOfTableCombo, SWT.BORDER);
			comboColumnName5.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					System.out.print("test: "+comboColumnName5.getText());
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					System.out.print("test: "+comboColumnName5.getText());
					
				}
			});
			
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
			
			tableOfColumnsAndValues = new Table(tableCompo, SWT.NONE);
			
			tableOfColumnsAndValues.setHeaderVisible(true);
			tableOfColumnsAndValues.setItemCount(10);
			tableOfColumnsAndValues.setLinesVisible(true);
			
			
			
			TableColumn columnName = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			columnName.setText("Column Name");
			TableColumn columnOperation = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			columnOperation.setText("Operation");
			TableColumn columnDisctAll = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			columnDisctAll.setText("DISCT/ALL");
			TableColumn columnValues = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
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
					if(tableOfColumnsAndValues.getItemCount()>10){
						tableIndex=0;
						//tablsList.setEnabled(true);
						
						TableItem[] arrayOfTableItems =tableOfColumnsAndValues.getItems();
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
			//tablsList.setEnabled(true);
			//valuesCompo.setEnabled(true);
		}//end of if select-Part
		
		
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
					
					if(textValueForColumn2.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues2, SWT.NONE,tableIndex2++);
						tableItem.setText(new String[]{comboColumnName2.getText(),comboOperationName2.getText(),textValueForColumn2.getText()});
						textValueForColumn2.setText("");
					}					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(textValueForColumn2.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues2, SWT.NONE,tableIndex2++);
						tableItem.setText(new String[]{comboColumnName2.getText(),comboOperationName2.getText(),textValueForColumn2.getText()});
						textValueForColumn2.setText("");
					}		
				}
			});
			
			Button deleteFromColumnList =new Button(buttomsCompo, SWT.NONE);
			deleteFromColumnList.setText("Remove from table");
			deleteFromColumnList.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					tableOfColumnsAndValues2.getItem(tableOfColumnsAndValues2.getSelectionIndex()).dispose();
					if(tableIndex2>0)tableIndex2--;
					if(tableOfColumnsAndValues2.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableOfColumnsAndValues2.getItem(tableOfColumnsAndValues2.getSelectionIndex()).dispose();
					if(tableOfColumnsAndValues2.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
					if(tableIndex2>0)tableIndex2--;
				}
			});
			Composite titelPartOfTableCombo=new Composite(tableCompo, SWT.BOLD);
			GridLayout gridLayoutY4=new GridLayout();
			gridLayoutY4.numColumns=6;
			titelPartOfTableCombo.setLayout(gridLayoutY4);
			Label labelColumnName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelColumnName.setText("Column name: ");		
			comboColumnName2=new Combo(titelPartOfTableCombo, SWT.BORDER);
			Label labelOperationName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelOperationName.setText("Operation: ");
			comboOperationName2=new Combo(titelPartOfTableCombo, SWT.BORDER);
			comboOperationName2.add("IN");
			comboOperationName2.add("LIKE");
			comboOperationName2.add("<");
			comboOperationName2.add(">");
			comboOperationName2.add("=");
			comboOperationName2.add(">=");
			comboOperationName2.add("<=");
			
			Label labelValueOfColumn=new Label(titelPartOfTableCombo, SWT.NONE);
			labelValueOfColumn.setText("Value: ");
			textValueForColumn2=new Text(titelPartOfTableCombo, SWT.BORDER);
			
			tableOfColumnsAndValues2 = new Table(tableCompo, SWT.NONE);
			
			tableOfColumnsAndValues2.setHeaderVisible(true);
			tableOfColumnsAndValues2.setItemCount(10);
			tableOfColumnsAndValues2.setLinesVisible(true);
			
			
			
			TableColumn columnName = new TableColumn(tableOfColumnsAndValues2, SWT.CENTER);
			columnName.setText("Column Name");
			TableColumn columnOperation = new TableColumn(tableOfColumnsAndValues2, SWT.CENTER);
			columnOperation.setText("Operation");
			TableColumn columnValues = new TableColumn(tableOfColumnsAndValues2, SWT.CENTER);
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
				String[] tmpString=new String[2];
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(tableOfColumnsAndValues2.getItemCount()>10){
						tableIndex2=0;
						//tablsList.setEnabled(true);
						
						TableItem[] arrayOfTableItems =tableOfColumnsAndValues2.getItems();
						//arrayOfTableItems[0].getText(0)
						//if(arrayOfTableItems.length>1) 	tmpString=" ,";
						//else tmpString=" ";
						//statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
						//tmpString[0]="test_table";
						//tmpString[1]="test";
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
						
						
						DeleteContentOfTable2();
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
		
		
		//******ORDER BY***************************************************
		if(keyWordValue.toLowerCase().equals("order by"))
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
			
			tablsList3 = new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
			tablsList3.setBounds(40, 20, 320, 100);
			tablsList3.setLayoutData(gridData1);
			//tablsList.setEnabled(false);
			try {
				loadTheTablesIntoList3();
			} catch (Exception e2) {
				System.out.print("ERROR: "+e2.getMessage());
			}
			
			try {
				tablsList3.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						System.out.print(tablsList3.getItem(tablsList3.getSelectionIndex()));
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							//++++++
							loadColumnsOfTableIntoCombo3(tablsList3.getItem(tablsList3.getSelectionIndex()));
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

						System.out.print(tablsList3.getItem(tablsList3.getSelectionIndex()));

						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTableIntoCombo3(tablsList3.getItem(tablsList3.getSelectionIndex()));
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}

					
	
					
				});
			} catch (Exception e1) {
				System.out.print("ERROR: "+e1.getMessage());
			}
			//**************************************************************
			
			
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
					
					//if(textValueForColumn3.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues3, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName3.getText(),comboOperationType.getText()});
						//textValueForColumn3.setText("");
					//}					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					//if(textValueForColumn3.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues3, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName3.getText(),comboOperationType.getText()});
						//textValueForColumn3.setText("");
					//}		
				}
			});
			
			Button deleteFromColumnList =new Button(buttomsCompo, SWT.NONE);
			deleteFromColumnList.setText("Remove from table");
			deleteFromColumnList.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					tableOfColumnsAndValues3.getItem(tableOfColumnsAndValues3.getSelectionIndex()).dispose();
					tableIndex3--;
					if(tableOfColumnsAndValues3.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableOfColumnsAndValues3.getItem(tableOfColumnsAndValues3.getSelectionIndex()).dispose();
					if(tableOfColumnsAndValues3.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
					tableIndex3--;
				}
			});
			Composite titelPartOfTableCombo=new Composite(tableCompo, SWT.BOLD);
			GridLayout gridLayoutY4=new GridLayout();
			gridLayoutY4.numColumns=4;
			titelPartOfTableCombo.setLayout(gridLayoutY4);
			Label labelColumnName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelColumnName.setText("Column name: ");		
			comboColumnName3=new Combo(titelPartOfTableCombo, SWT.BORDER);
			Label labelOperationName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelOperationName.setText("Operation: ");
			comboOperationType=new Combo(titelPartOfTableCombo, SWT.BORDER);
			comboOperationType.add("ASC");
			comboOperationType.add("DESC");
			
			tableOfColumnsAndValues3 = new Table(tableCompo, SWT.NONE);
			
			tableOfColumnsAndValues3.setHeaderVisible(true);
			tableOfColumnsAndValues3.setItemCount(10);
			tableOfColumnsAndValues3.setLinesVisible(true);
			
			
			
			TableColumn columnName = new TableColumn(tableOfColumnsAndValues3, SWT.CENTER);
			columnName.setText("Column Name");
			TableColumn columnOperation = new TableColumn(tableOfColumnsAndValues3, SWT.CENTER);
			columnOperation.setText("Operation");
			
			
			//TableColumn columnType = new TableColumn(tableOfColumnsAndValues, SWT.CENTER);
			//columnType.setText("Type");
			
			columnName.setWidth(150);
			columnOperation.setWidth(150);
			
			//columnType.setWidth(70);
			
			
			
			//***********************************************************
			
			
			
			//********************************
			Button insertColumsIntoStatment=new Button(buttomsCompo, SWT.NONE);
			insertColumsIntoStatment.setToolTipText("Add Columns and the values into the Statment");
			insertColumsIntoStatment.setText("Insert to Statement");
			insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
				
				String operStat="";
				String[] tmpString=null;
				ArrayList<String> listOFTablesNames=new ArrayList<String>();
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(tableOfColumnsAndValues3.getItemCount()>10){
						tableIndex=0;
						//tablsList.setEnabled(true);
						
						TableItem[] arrayOfTableItems =tableOfColumnsAndValues3.getItems();
						//arrayOfTableItems[0].getText(0)
						//if(arrayOfTableItems.length>1) 	tmpString=" ,";
						//else tmpString=" ";
						//statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
						
						
						statementText.setText(statementText.getText()+"\r	ORDER BY ");
						tmpString=SplitTheWordInTwo(arrayOfTableItems[0].getText(0));
						listOFTablesNames.add(tmpString[0]);
						if(arrayOfTableItems[0].getText(1).length()>0) {
							
							statementText.setText(statementText.getText()+" "+arrayOfTableItems[0].getText(1)+" "+tmpString[1]);
						}
						else {
							
						}
						for(int i=1;i<arrayOfTableItems.length;i++){
							if(arrayOfTableItems[i].getText(1).length()>0) {
								tmpString=SplitTheWordInTwo(arrayOfTableItems[i].getText(0));
								listOFTablesNames.add(tmpString[0]);
								statementText.setText(statementText.getText()+","+arrayOfTableItems[i].getText(1)+" "+tmpString[1]);
								
							}
							else {
								
							}						
						}
						int listElement=1000;
						statementText.setText(statementText.getText()+" FROM ");
						for(int i=0;i<listOFTablesNames.size();i++){
							//if((listOFTablesNames.get(i).length()>0)&&(listElement==1000)) listElement=i;
								
								if(i>0){statementText.setText(statementText.getText()+","+listOFTablesNames.get(i));}
								else statementText.setText(statementText.getText()+listOFTablesNames.get(i));
							
						}
						
						addToListOfStatementTextChanges();
						
						
						
						statementText.setText(statementText.getText()+"\r");
						addToListOfStatementTextChanges();
//						for(int i=0;i<arrayOfTableItems.length;i++){
//							if(arrayOfTableItems[i].getText(0).length()>0){	
//								statementText.setText(statementText.getText()+tmpString+arrayOfTableItems[i].getText(0).split(".")[0]);
//							}
//						}
						//****************deleting all content of elements****
						for(int i=0;i<listOFTablesNames.size();i++){
							listOFTablesNames.remove(i);
						}
						comboColumnName3.removeAll();
						DeleteContentOfTable3();
						//****************************************************
						
						
					}//end of if(...count>10)
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
			tableNameComposite.setEnabled(true);
			tablsList.setEnabled(true);
			//valuesCompo.setEnabled(true);
		}//end of if orderBY -Part
		//***********************************************************************
		
		//************HAVING*****************************************************
		if(keyWordValue.toLowerCase().equals("having"))
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
			
			tablsList4 = new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
			tablsList4.setBounds(40, 20, 320, 100);
			tablsList4.setLayoutData(gridData1);
			//tablsList.setEnabled(false);
			try {
				loadTheTablesIntoList4();
			} catch (Exception e2) {
				System.out.print("ERROR: "+e2.getMessage());
			}
			
			try {
				tablsList4.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						System.out.print(tablsList4.getItem(tablsList4.getSelectionIndex()));
						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTableIntoCombo4(tablsList4.getItem(tablsList4.getSelectionIndex()));
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

						System.out.print(tablsList4.getItem(tablsList4.getSelectionIndex()));

						//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
						try {
							loadColumnsOfTableIntoCombo4(tablsList4.getItem(tablsList4.getSelectionIndex()));
						} catch (Exception e1) {
							System.out.print("ERROR: "+e1.getMessage());
						}
					}

					
	
					
				});
			} catch (Exception e1) {
				System.out.print("ERROR: "+e1.getMessage());
			}
			//**************************************************************
			
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
					
					if(textValueForColumn4.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues4, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName4.getText(),comboOperationName4.getText(),textValueForColumn4.getText()});
						textValueForColumn4.setText("");
					}					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(textValueForColumn4.getText().length()>0){
						//tablsList.setEnabled(false);
						//valueCompo.setVisible(false);
						TableItem tableItem=new TableItem(tableOfColumnsAndValues4, SWT.NONE,tableIndex++);
						tableItem.setText(new String[]{comboColumnName4.getText(),comboOperationName4.getText(),textValueForColumn4.getText()});
						textValueForColumn4.setText("");
					}		
				}
			});
			
			Button deleteFromColumnList =new Button(buttomsCompo, SWT.NONE);
			deleteFromColumnList.setText("Remove from table");
			deleteFromColumnList.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					tableOfColumnsAndValues4.getItem(tableOfColumnsAndValues4.getSelectionIndex()).dispose();
					tableIndex--;
					if(tableOfColumnsAndValues4.getItemCount()==10){
						//tablsList.setEnabled(true);
						
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableOfColumnsAndValues4.getItem(tableOfColumnsAndValues4.getSelectionIndex()).dispose();
					if(tableOfColumnsAndValues4.getItemCount()==10){
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
			comboColumnName4=new Combo(titelPartOfTableCombo, SWT.BORDER);
			Label labelOperationName=new Label(titelPartOfTableCombo, SWT.NONE);
			labelOperationName.setText("Operation: ");
			comboOperationName4=new Combo(titelPartOfTableCombo, SWT.BORDER);
			comboOperationName4.add("IN");
			comboOperationName4.add("LIKE");
			comboOperationName4.add("<");
			comboOperationName4.add(">");
			comboOperationName4.add("=");
			comboOperationName4.add(">=");
			comboOperationName4.add("<=");
			
			Label labelValueOfColumn=new Label(titelPartOfTableCombo, SWT.NONE);
			labelValueOfColumn.setText("Value: ");
			textValueForColumn4=new Text(titelPartOfTableCombo, SWT.BORDER);
			
			tableOfColumnsAndValues4 = new Table(tableCompo, SWT.NONE);
			
			tableOfColumnsAndValues4.setHeaderVisible(true);
			tableOfColumnsAndValues4.setItemCount(10);
			tableOfColumnsAndValues4.setLinesVisible(true);
			
			
			
			TableColumn columnName = new TableColumn(tableOfColumnsAndValues4, SWT.CENTER);
			columnName.setText("Column Name");
			TableColumn columnOperation = new TableColumn(tableOfColumnsAndValues4, SWT.CENTER);
			columnOperation.setText("Operation");
			TableColumn columnValues = new TableColumn(tableOfColumnsAndValues4, SWT.CENTER);
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
					if(tableOfColumnsAndValues4.getItemCount()>10){
						tableIndex=0;
						//tablsList.setEnabled(true);
						
						TableItem[] arrayOfTableItems =tableOfColumnsAndValues4.getItems();
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
						statementText.setText(statementText.getText()+"\r	HAVING ");
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
						
						
						DeleteContentOfTable4();
					}//end of if(...count>10)
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
			tableNameComposite.setEnabled(true);
			tablsList.setEnabled(true);
			//valuesCompo.setEnabled(true);
		}//end of if having-Part
		//*****************End of Having*****************************************
	    return composite;
	  }
	  
	/**  
	 * 
	 */
	private void loadTheTablesIntoList4() {
		tablsList4.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			tablsList4.add("Table" + loopIndex);
		}
	}

	/**
	   * 
	   */
	  private void loadTheTablesIntoList3() {
			// TODO Auto-generated method stub
		  tablsList3.removeAll();
			
			//zum testen***
			for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
				tablsList3.add("Table" + loopIndex);
			}
		
	  }

	/**
	   * 
	   */
	  private void loadTheTablesIntoList2() {
			// TODO Auto-generated method stub
		  tablsList2.removeAll();
			
			//zum testen***
			for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
				tablsList2.add("Table" + loopIndex);
			}
			//*************
			
//			ArrayList<String> tabelsNames=null;//TODO: tables namen laden
//			if(tabelsNames!=null){
//				for(int i=0;i<tabelsNames.size();i++){
//					tablsList2.add(tabelsNames.get(i));
//				}
//			}
	  }

	/** 
		  * splitting the word in table name and column name
		  * @param text
		  * @return
		  */
		 private String[] SplitTheWordInTwo(String text) {
			 String[] arrayOFWords=new String[2];
			 arrayOFWords[0]="";
			 arrayOFWords[1]="";
			 if(text.length()>1){	
			 	ArrayList<String> listOfLetters=new ArrayList<String>();
				
				int indexOfPoint = 1;
				for(int i=0;i<text.length()-1;i++){
					//listOfLetters.add(text.substring(i, i));
					if(text.substring(i, i+1).equals(".")) indexOfPoint=i; 
				}
				arrayOFWords[0]=text.substring(0,indexOfPoint);
				arrayOFWords[1]=text.substring(indexOfPoint+1,text.length());
			}
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
	   * for deleting the content of the colums&Values Table.
	   */
	  private void DeleteContentOfTable2() {
			for(int i=0;i<tableOfColumnsAndValues2.getItemCount();i++){
				if(tableOfColumnsAndValues2.getItem(i).getText(0).length()>0){
					tableOfColumnsAndValues2.getItem(i).dispose();
				}
			}
			
		}
	  
	  /**
	   * for deleting the content of the colums&Values Table.
	   */
	  private void DeleteContentOfTable3() {
			for(int i=0;i<tableOfColumnsAndValues3.getItemCount();i++){
				if(tableOfColumnsAndValues3.getItem(i).getText(0).length()>0){
					tableOfColumnsAndValues3.getItem(i).dispose();
					
				}
			}
			
		}
	
	  /**
	   * for deleting the content of the colums&Values Table.
	   */
	  private void DeleteContentOfTable4() {
			for(int i=0;i<tableOfColumnsAndValues4.getItemCount();i++){
				if(tableOfColumnsAndValues4.getItem(i).getText(0).length()>0){
					tableOfColumnsAndValues4.getItem(i).dispose();
				}
			}
			
		}
	  
//	/**
//	 * creating the ui Elemente of this Editor
//	 * @param composite
//	 */
//	private void createEditorElements(Composite composite) {
//		
//		GridData gridData1 = new GridData();
//		gridData1.horizontalAlignment = GridData.FILL;
//		gridData1.grabExcessHorizontalSpace = true;
//		gridData1.grabExcessVerticalSpace = true;
//		//gridData1.verticalAlignment = GridData.FILL;
//		
//		listsComposite=new Composite(composite, SWT.BORDER);
//		listsComposite.setSize(300, 250);
//		
//		//***************************************************
//		resultSETStatementCompo=new Composite(composite, SWT.NONE);
//		resultSETStatementCompo.setEnabled(false);
//		resultSelectedTableColumns=new Text(resultSETStatementCompo, SWT.NONE|SWT.H_SCROLL);
//		resultSelectedTableColumns.setSize(300, 70);
//		
//		GridLayout layout2=new GridLayout();
////		layout2.numColumns=2;
////		resultSETStatementCompo.setLayout(layout2);
//		Button insertSETInStatment=new Button(composite, SWT.NONE);
//		insertSETInStatment.setText("Add SET part to Statement");
//		insertSETInStatment.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				statementText.setText(statementText.getText()+resultSelectedTableColumns.getText());
//				addToListOfStatementTextChanges();
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				statementText.setText(statementText.getText()+resultSelectedTableColumns.getText());
//				addToListOfStatementTextChanges();
//				
//			}
//		});
//		//**********************************************
//		GridLayout layout=new GridLayout();
//		layout.numColumns=2;
//		listsComposite.setLayout(layout);
//		listsComposite.setLayoutData(gridData1);
//		
//		
//		
//		listOfTabels=new List(listsComposite, SWT.NONE|SWT.V_SCROLL);
//		listOfColumns=new List(listsComposite, SWT.NONE|SWT.V_SCROLL|SWT.MULTI);
//		loadTablesFromDS();
//		resultSelectedTableColumns.setText(parseStatment());
//		
//		Button insertTableAndColumns=new Button(listsComposite, SWT.NONE);
//		insertTableAndColumns.setText("Create SET statment part");
//		listOfTabels.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				loadColumnsOfTable();
//			}
//			
//			
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				loadColumnsOfTable();
//			}
//		});
//		
//		insertTableAndColumns.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				statementText.setText(statementText.getText()+" "+
//						getSelectedColumns()+" FROM "+listOfTabels.getItem(listOfTabels.getSelectionIndex()));
//				addToListOfStatementTextChanges();
//			}
//			
//			
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				statementText.setText(statementText.getText()+" "+
//						getSelectedColumns()+" FROM "+listOfTabels.getItem(listOfTabels.getSelectionIndex()));
//				addToListOfStatementTextChanges();
//			}
//		});
//		
//		resultSETStatementCompo.setEnabled(true);
//		listsComposite.setEnabled(true);
//	}
	
//	/**
//	 * parsing the statment and getting the SET part with the data.
//	 * @return
//	 */
//	private String parseStatment() {
//		String valuesString="";
//		if(statementText.getText().length()>0){
//			String cleanedString=RemoveAllUnnasacerelySpaces(statementText.getText());
//			String cleandStatment=removeAllSpaces(cleanedString);
//			String[] wordsOfStatment;
//			if(cleandStatment.contains("\r")){
//				String[] linesOfcleandStatment=cleandStatment.split("\r");
//				wordsOfStatment=linesOfcleandStatment[0].split(" ");
//			}
//			else{
//				wordsOfStatment=cleandStatment.split(" ");
//			}
//			
//			String[] theFROMLine;
//			String selectedTableName="",columnsOfStatement="";
//			int indexOfSELECTStatement=0,indexFROMStatment=0;
//			
//			
//			if((wordsOfStatment.length>1)){
//				
//				
//				for(int i=0;i<wordsOfStatment.length;i++){
//					
//					if(wordsOfStatment[i].contains("SELECT")&&(indexOfSELECTStatement==0)){
//						indexOfSELECTStatement=i;
//					}
//					if(wordsOfStatment[i].contains("FROM")&&(indexFROMStatment==0)){
//						indexFROMStatment=i;
//					}
//				}
//				
//				if(wordsOfStatment.length>indexFROMStatment+1){
//					selectedTableName=wordsOfStatment[indexFROMStatment+1];
////					if(listOfTabels.getItemCount()>0){
////						listOfTabels.select(listOfTabels.indexOf(selectedTableName));
////						if(listOfTabels.isSelected(listOfTabels.indexOf(selectedTableName))){
////							loadColumnsOfTable();
////						}
////					}
//				}
//				
//				if(wordsOfStatment.length>indexOfSELECTStatement+1){
//					columnsOfStatement=wordsOfStatment[indexOfSELECTStatement+1];
//					System.out.print("**"+columnsOfStatement+"**");
//					selectTheTableAndColumnsInList(selectedTableName,columnsOfStatement);
//				}
//			}
//			else{ valuesString="";}
//		}
//		
//		return valuesString;
//	}
	
	
	
	private String searchForTableNameInStatement(String[] theFROMLine) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * selecting the parsed elements from statement into the Lists
	 * @param columns
	 */
//	private void selectTheTableAndColumnsInList(String selectedTable,String columns) {
//		
//		String cleanedString=removeAllSpaces(columns);
//		String[] partsOfString= cleanedString.split(",");
//		String[] tmpPartsOfString;
//		if(listOfTabels.getItemCount()>0){
//			listOfTabels.select(listOfTabels.indexOf(selectedTable));
//			if(listOfTabels.isSelected(listOfTabels.indexOf(selectedTable))){
//				loadColumnsToCmboboxInTable();
//			}
//		}
//		if(listOfColumns.getItemCount()>0){	
//			for(int i=0;i<partsOfString.length;i++){
//				//tmpPartsOfString=partsOfString[i].split(",");
//				listOfColumns.select(listOfColumns.indexOf(partsOfString[i]));
//			}
//		}
//	}

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
	 * removes all spaces from statment
	 * @param statement
	 * @return statmentAsOneString
	 */
	private String removeAllSpaces(String statement) {
		
		String cleanedString=RemoveAllUnnasacerelySpaces(statement);
		String[] wordsOfCentence = null;
		String statmentAsOneString = "";
		
		if(cleanedString!=null){
			if(cleanedString.contains(" ")){
				wordsOfCentence=cleanedString.split(" ");
			}
			else{
				statmentAsOneString=cleanedString;
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

//	/**
//	 * load the columns of the selected table.
//	 */
//	private void loadColumnsToCmboboxInTable() {
//		
//		
//		String tableName=listOfTabels.getItem(listOfTabels.getSelectionIndex());
//		comboColumnName.removeAll();
//		
//		//zum testen***
//		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
//			comboColumnName.add(tableName+".Column" + loopIndex);
//		}
//		//*************
//		
//		ArrayList<String> columnsNames=null;//TODO: Column namen laden
//		if(columnsNames!=null){
//			for(int i=0;i<columnsNames.size();i++){
//				listOfColumns.add(columnsNames.get(i));
//			}
//		}
//	}
	
	/**
	 * for loading the colums of the table into the combo of 
	 * colums names.
	 */
	private void loadColumnsOfTableIntoCombo(String tableName) {
		//String tableName = "";
		try {
			//tableName = 
			comboColumnName2.removeAll();
		} catch (Exception e) {
			System.out.print("ERROR:"+e.getMessage());
		}
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			try {
				comboColumnName2.add(tableName+".Column" + loopIndex);
			} catch (Exception e) {
				System.out.print("ERROR:"+e.getMessage());
			}
		}
		//*************
	}
	
	/**
	 * for loading the colums of the table into the combo of 
	 * colums names.
	 */
	private void loadColumnsOfTableIntoCombo3(String tableName) {
		//String tableName = "";
		try {
			//tableName = 
			comboColumnName3.removeAll();
		} catch (Exception e) {
			System.out.print("ERROR:"+e.getMessage());
		}
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			try {
				comboColumnName3.add(tableName+".Column" + loopIndex);
			} catch (Exception e) {
				System.out.print("ERROR:"+e.getMessage());
			}
		}
		//*************
	}
	
	/**
	 * for loading the colums of the table into the combo of 
	 * colums names.
	 */
	private void loadColumnsOfTableIntoCombo4(String tableName) {
		//String tableName = "";
		try {
			//tableName = 
			comboColumnName4.removeAll();
		} catch (Exception e) {
			System.out.print("ERROR:"+e.getMessage());
		}
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			try {
				comboColumnName4.add(tableName+".Column" + loopIndex);
			} catch (Exception e) {
				System.out.print("ERROR:"+e.getMessage());
			}
		}
		//*************
	}
	
	/**
	 * for loading the colums of the table into the combo of 
	 * colums names.
	 */
	private void loadColumnsOfTableIntoCombo5(String tableName) {
		//String tableName = "";
		try {
			//tableName = 
			comboColumnName5.removeAll();
		} catch (Exception e) {
			System.out.print("ERROR:"+e.getMessage());
		}
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			try {
				comboColumnName5.add(tableName+".Column" + loopIndex);
			} catch (Exception e) {
				System.out.print("ERROR:"+e.getMessage());
			}
		}
		//*************
	}
	
	/**
	 * for loading the colums of the table into the combo of 
	 * colums names.
	 */
	private void loadColumnsOfTableIntoCombo2(String tableName) {
		//String tableName = "";
		try {
			//tableName = 
			comboColumnName5.removeAll();
		} catch (Exception e) {
			System.out.print("ERROR:"+e.getMessage());
		}
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			try {
				comboColumnName5.add(tableName+".Column" + loopIndex);
			} catch (Exception e) {
				System.out.print("ERROR:"+e.getMessage());
			}
		}
		//*************
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	private String getSelectedColumns() {
//		String selectedColumnsStatement="";
//		if(listOfColumns.getSelection().length>0){
//			for(int i=0;i<listOfColumns.getSelection().length-1;i++)
//			{
//				selectedColumnsStatement=selectedColumnsStatement+
//					listOfColumns.getSelection()[i]+",";
//			}
//			selectedColumnsStatement=selectedColumnsStatement+
//			listOfColumns.getSelection()[listOfColumns.getSelection().length-1];
//		}
//			return selectedColumnsStatement;
//	}

	/**
	 * loading the tables from data source
	 * @return
	 */
	private void loadTablesFromDS() {
		
		tablsList2.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			tablsList2.add("Table" + loopIndex);
		}
		//*************
		
//		ArrayList<String> tabelsNames=null;//TODO: tables namen laden
//		if(tabelsNames!=null){
//			for(int i=0;i<tabelsNames.size();i++){
//				listOfTabels.add(tabelsNames.get(i));
//			}
//		}
	
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
		    for(int i=buttonList.size()-1;i>=0;i--)
			{
			    TabItem three = new TabItem(tabFolder, SWT.NONE);
//				if(buttonList.get(i).getText().toLowerCase().equals("where")){
//					three.setText("Table oriented");
//				}
//				else if(buttonList.get(i).getText().toLowerCase().equals("select")){
//					three.setText("SET oriented");
//				}
//				else three.setText(buttonList.get(i).getText());

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
			
			if(!(listOfMainKeyWords.get(i).getMainKeyWord().equals("QUERY"))){

				final Button keyWordAsButton=new Button(buttonsCompo, SWT.NONE);
				keyWordAsButton.setText(listOfMainKeyWords.get(i).getMainKeyWord());
				//keyWordAsButton.setTextOfAction(listOfMainKeyWords.get(i).getTextOfKEyWord());
				keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
	
				keyWordAsButton.setSize(listOfMainKeyWords.get(i).getMainKeyWord().length()+20, 70);
	
	//			if(!listOfMainKeyWords.get(i).isTheMajorKey()){
	//				keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
	//			}
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
						
						/*
						 * in the following for statement all the buttons are only
						 * then enabled if the father button (according to the Logik in the parsed xmlFile)
						 */
						keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
						
						for(int x=0;x<buttonList.size();x++){
							//if(buttonList.get(x).getText().equals(e.text)){buttonList.get(x).setEnabled(false);}
							buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
							for(int j=0;j<tmpKeyWord.getListOfSubKeyWords().size();j++){
								//
								if(tmpKeyWord.getListOfSubKeyWords().get(j).getMainKeyWord().equals(buttonList.get(x).getText())){
									buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
								}
								
							}
							
							
						}
						statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
						addToListOfStatementTextChanges();
						
						if(tmpKeyWord.getMainKeyWord().equals("WHERE")){
							tabFolder.setSelection(1);
						}
						if(tmpKeyWord.getMainKeyWord().equals("SELECT")){
							tabFolder.setSelection(0);
						}
						if(tmpKeyWord.getMainKeyWord().equals("ORDER BY")){
							tabFolder.setSelection(2);
						}
						if(tmpKeyWord.getMainKeyWord().equals("GROUP BY")){
							tabFolder.setSelection(3);
						}
						if(tmpKeyWord.getMainKeyWord().equals("HAVING")){
							tabFolder.setSelection(4);
						}
						
						//resultSETStatementCompo.setEnabled(true);
						//listsComposite.setEnabled(true);
						
	//					if(tmpKeyWord.getMainKeyWord().equals(mainStatmentKeyWord)){
	//						statementText.setText(tmpKeyWord.getTextOfKEyWord());
	//						resultSETStatementCompo.setEnabled(true);
	//						listsComposite.setEnabled(true);	
	//					}
	//					else statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
						
	//					fatherComp.getShell().getData("StyledText")
	//					s.setStatementText("sdfsdf");
					}
				});
				
				buttonList.add(keyWordAsButton);
			}
		}
		
	}


}
