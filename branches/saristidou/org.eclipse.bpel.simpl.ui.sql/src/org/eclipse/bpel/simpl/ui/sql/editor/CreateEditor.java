package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.bpel.simpl.ui.sql.xmlParser.KeyWord;
import org.eclipse.bpel.simpl.ui.sql.xmlParser.QueryKeyWordsXmlParser;
import org.eclipse.bpel.simpl.ui.widgets.DBTable;
import org.eclipse.bpel.simpl.ui.widgets.LiveEditStyleText;
import org.eclipse.bpel.simpl.ui.widgets.MetaDataXMLParser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
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



/**
 * Es fehlt noch die rückwärtz Parsen der Statement .
 */
public class CreateEditor extends AStatementEditor {
	MetaDataXMLParser metaDataXMLParser_Objekt;
	ArrayList<DBTable> globalListOfTables;
	
	private Composite comp = null;
	private Composite compos = null;
	private LiveEditStyleText statementText = null;
	Label proceLabel;
	Button addToStatement;
	Text proceText;
	List columnList;
	// Create the containing tab folder
   TabFolder tabFolder;
   
	private Composite tableNameComposite=null;
	private Composite columnCompo=null;
	 List listOfColumn;
	 ArrayList<String> dSourceTables;
	 private String[] theKeyWords = new String[]{"CREATE","INTO","FROM", "WHERE"};//TODO: welche KeyWods bzw. befehle 
																			   //sind noch denkbar für jeden StatmentEditor
		public ArrayList<String> getdSourceTables() {
			return dSourceTables;
		}
	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreibencreatecr
	private String xmlFilePath="/keywords/CreateDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	String kommaString="",tmpString2;
	Text textColumnName,textTableName;
	Combo comboColumnType;
	Button addColumnToStatement;
	ArrayList<String> listOfTheColumns=new ArrayList<String>();
	
 	private ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	private Composite columsListCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	
	Label labelSchamName,labelAuthuresation;
	Text txtSchemaName,txtAuthorisation,txtDefault,txtColumnName,txtTableNAme;
	Combo comboType,comboTables;
	Button chbxPrimaryK,chbxUniqe,chbxNull,chbxDefault,chbxForeignKey;
	Table tableOfColums;
	int tableIndex=0;
	
	String PRIMARY_KEY="PRIMARY-KEY",UNIQE="UNIQE",NULL="NULL",DEFAULT="DEFAULT",FOREIGN_KEY="FOREIGEN-KEY",none="";
	
	
	//List of statement Changes
    ArrayList<String> listOfStatementTextChanges=new ArrayList<String>();
    
	
	public ArrayList<String> getListOfStatementTextChanges() {
		return listOfStatementTextChanges;
	}

	public void addToListOfStatementTextChanges() {
		listOfStatementTextChanges.add(statementText.getText());
	}
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
		//gridData.horizontalAlignment = 6;
		comp = new Composite(composite, SWT.NONE);
		comp.setLayout(new GridLayout());
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = GridData.FILL;
		
		GridData gridData1_1 = new GridData();
		gridData1_1.horizontalAlignment = GridData.END;
		gridData1_1.grabExcessHorizontalSpace = true;
		gridData1_1.grabExcessVerticalSpace = true;
		gridData1_1.verticalAlignment = GridData.END;
		
		GridLayout gridLayoutB = new GridLayout();
		gridLayoutB.numColumns=1;
		
		
		compos = new Composite(comp, SWT.NONE);
		compos.setLayout(gridLayoutB);
		compos.setLayoutData(gridData1);
		
		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(xmlFilePath);
		
		buttonsCompo=new Composite(compos, SWT.RIGHT_TO_LEFT);
		buttonsCompo.setLayout(gridLayoutA);
		//buttonsCompo.setLayoutData(gridData1);
		creatButtonsOfKeyWords(parser.parseDocument());
		//buttonsCompo.setEnabled(false);
		
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
					statementText.setText("CREATE ");
				}
			}
			else{statementText.setText("CREATE ");}
			
			addToListOfStatementTextChanges();
		}
		else {statementText.setText("CREATE ");}
		
		metaDataXMLParser_Objekt=new MetaDataXMLParser();
		globalListOfTables= metaDataXMLParser_Objekt.loadTablesFromDB(getDataSource());
		createTabTable(parser.parseDocument());
		//CreateCREATEEditorUI(compos);
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
	    //composite.setLayout(new FillLayout(SWT.VERTICAL));
	    
	    Button insertButton;
	    
	    
		
		if(keyWordValue.toLowerCase().equals("table"))
		{
			GridLayout gridLayout8 = new GridLayout();
			gridLayout8.numColumns=1;
			composite.setLayout(gridLayout8);
			
			Composite titelCompo=new Composite(composite, SWT.BOLD);
			GridLayout gridLayout5 = new GridLayout();
			gridLayout5.numColumns=2;
			titelCompo.setLayout(gridLayout5);
			
			Composite columsCompo=new Composite(composite, SWT.NONE);
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.numColumns=11;
			columsCompo.setLayout(gridLayout2);
			
			
			
			
			Label lblTableName=new Label(titelCompo, SWT.NONE);
			lblTableName.setText("Table name:");
			txtTableNAme=new Text(titelCompo, SWT.BORDER);
			
			
		
			
			
			
			
			Label lblColumnName=new Label(columsCompo, SWT.NONE);
			lblColumnName.setText("Column name: ");
			txtColumnName=new Text(columsCompo, SWT.BORDER);
			Label lblType=new Label(columsCompo, SWT.NONE);
			lblType.setText("Type:");
			comboType=new Combo(columsCompo, SWT.NONE);
			comboType.add("INT");
			comboType.add("BIT");
			comboType.add("BIGINT");
			comboType.add("DECIMAL");
			comboType.add("FLOAT");
			comboType.add("TIME");
			comboType.add("DATE");
			comboType.add("CHAR");
			comboType.add("VARCHAR");
			comboType.add("TEXT");
			
			chbxPrimaryK=new Button(columsCompo, SWT.CHECK);
			chbxPrimaryK.setText("PRIMARY-KEY");
			chbxPrimaryK.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					if(chbxPrimaryK.getSelection()){
						chbxUniqe.setSelection(true);
						chbxNull.setSelection(false); 
						
					}
					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
					
				}
			});
			chbxUniqe =new Button(columsCompo, SWT.CHECK);
			chbxUniqe.setText("UNIQE");
			chbxNull=new Button(columsCompo, SWT.CHECK);
			chbxNull.setText("NULL");
			chbxNull.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(chbxNull.getSelection()) {
						chbxPrimaryK.setSelection(false);
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
			
			Composite foreignKEyCompo=new Composite(columsCompo, SWT.BORDER);
			GridLayout gridLayout3 = new GridLayout();
			gridLayout3.numColumns=2;
			foreignKEyCompo.setLayout(gridLayout3);
			chbxForeignKey =new Button(foreignKEyCompo, SWT.CHECK);
			chbxForeignKey.setText("FOREIGN-KEY:");
			comboTables=new Combo(foreignKEyCompo, SWT.NONE);
			loadTableInComboTables();
			
			Composite defaultCompo=new Composite(columsCompo, SWT.BORDER);
			GridLayout gridLayout4 = new GridLayout();
			gridLayout4.numColumns=2;
			defaultCompo.setLayout(gridLayout4);
			chbxDefault=new Button(defaultCompo, SWT.CHECK);
			chbxDefault.setText("DEFAULT:");
			txtDefault=new Text(defaultCompo, SWT.BORDER);
			
			
			
			
			Composite columsCompoMain=new Composite(composite, SWT.PUSH);
			GridLayout gridLayout7 = new GridLayout();
			gridLayout7.numColumns=1;
			columsCompoMain.setLayout(gridLayout7);
			GridData gridData1_1 = new GridData();
			//gridData1_1.horizontalAlignment = GridData.END;
			gridData1_1.grabExcessHorizontalSpace = true;
			//gridData1_1.grabExcessVerticalSpace = true;
			//gridData1_1.verticalAlignment = GridData.END;
			
			columsCompoMain.setLayoutData(gridData1_1);
			
			tableOfColums=new Table(columsCompoMain, SWT.BORDER);
			tableOfColums.setHeaderVisible(true);
			tableOfColums.setItemCount(10);
			tableOfColums.setLinesVisible(true);
			
			
			TableColumn columnName=new TableColumn(tableOfColums, SWT.NONE);
			columnName.setText("Attribute name");
			columnName.setWidth(150);
			TableColumn columnType=new TableColumn(tableOfColums, SWT.NONE);
			columnType.setText("Type");
			columnType.setWidth(130);
			TableColumn columnPrimaryK=new TableColumn(tableOfColums, SWT.NONE);
			columnPrimaryK.setText("PRIMARY-KEY/NONE");
			columnPrimaryK.setWidth(90);
			TableColumn columnUniqe=new TableColumn(tableOfColums, SWT.NONE);
			columnUniqe.setText("UNIQE/NONE");
			columnUniqe.setWidth(60);
			TableColumn columnNull=new TableColumn(tableOfColums, SWT.NONE);
			columnNull.setText("NULL/NONE");
			columnNull.setWidth(60);
			TableColumn columnForeignK=new TableColumn(tableOfColums, SWT.NONE);
			columnForeignK.setText("FOREIGN-KEY/NONE");
			columnForeignK.setWidth(150);
			TableColumn columnDefault=new TableColumn(tableOfColums, SWT.NONE);
			columnDefault.setText("DEFAULT/NONE");
			columnDefault.setWidth(170);
			
			
			Composite buttonCompo=new Composite(composite, SWT.BOLD);
			GridLayout gridLayout6 = new GridLayout();
			gridLayout6.numColumns=3;
			buttonCompo.setLayout(gridLayout6);
	
			Button insertToTable=new Button(buttonCompo, SWT.NONE);
			insertToTable.setText("Insert to table");
			insertToTable.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					//boolean isPrimaryKey=false;
					if(txtColumnName.getText().length()>0){
						TableItem newTableItem=new TableItem(tableOfColums, SWT.NONE,tableIndex++);
						newTableItem.setText(0, txtColumnName.getText());
						newTableItem.setText(1, comboType.getText());
						if(chbxPrimaryK.getSelection()){
							newTableItem.setText(2, PRIMARY_KEY);
						}
						else newTableItem.setText(2, none);
						if(chbxUniqe.getSelection()) newTableItem.setText(3, UNIQE);
						else newTableItem.setText(3, none);
						if(chbxNull.getSelection()) newTableItem.setText(4, NULL);
						else newTableItem.setText(4, none);
						if(chbxForeignKey.getSelection()) newTableItem.setText(5, comboTables.getText());
						else newTableItem.setText(5, none);
						if(chbxDefault.getSelection()) newTableItem.setText(6, DEFAULT);
						else newTableItem.setText(6, none);
						txtColumnName.setText("");
					}
					
					
					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
					
				}
			});
			
			Button deleteItemFromTable=new Button(buttonCompo, SWT.NONE);
			deleteItemFromTable.setText("Delete selected item");
			deleteItemFromTable.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					tableOfColums.getItem(tableOfColums.getSelectionIndex()).dispose();
					if(tableIndex>0) tableIndex--;
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			Button insertToStatement=new Button(buttonCompo, SWT.NONE);
			insertToStatement.setText("Create statement");
			insertToStatement.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(tableOfColums.getItem(0).getText(0).length()>0){
						statementText.setText("CREATE TABLE "+txtTableNAme.getText()+" ("+tableOfColums.getItem(0).getText(0)+" "
								+tableOfColums.getItem(0).getText(1)+" "+tableOfColums.getItem(0).getText(2)+" "+tableOfColums.getItem(0).getText(3)+
								" "+tableOfColums.getItem(0).getText(4)+" "+tableOfColums.getItem(0).getText(5));
					}
					int last=0;
					for(int i=1;i<tableOfColums.getItemCount()-1;i++){
						if(tableOfColums.getItem(i).getText(0).length()>0){
							statementText.setText(statementText.getText()+"\r		,"+tableOfColums.getItem(i).getText(0)+" "
									+tableOfColums.getItem(i).getText(1)+" "+tableOfColums.getItem(i).getText(2)+" "+
									tableOfColums.getItem(i).getText(3)+" "+tableOfColums.getItem(i).getText(4)+" "
									+tableOfColums.getItem(i).getText(5));
							last=i;
						}
						
					}
					last++;
					
					if(tableOfColums.getItem(last).getText(0).length()>0){
						statementText.setText(statementText.getText()+"\r		"+tableOfColums.getItem(last).getText(0)+" "
								+tableOfColums.getItem(last).getText(1)+" "+tableOfColums.getItem(last).getText(2)+" "+
								tableOfColums.getItem(last).getText(3)+" "+tableOfColums.getItem(last).getText(4)+" "
								+tableOfColums.getItem(last).getText(5)+")");
					}
					
					addToListOfStatementTextChanges();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
					
				}
			});
			
		}
		
		if(keyWordValue.toLowerCase().equals("schema"))
		{
			GridLayout gridLayout8 = new GridLayout();
			gridLayout8.numColumns=1;
			composite.setLayout(gridLayout8);
			
			Composite mainCompo=new Composite(composite, SWT.NONE);
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.numColumns=4;
			mainCompo.setLayout(gridLayout1);
			
			labelSchamName=new Label(mainCompo, SWT.NONE);
			labelSchamName.setText("Schema name: ");
			txtSchemaName=new Text(mainCompo, SWT.BORDER);
			labelAuthuresation=new Label(mainCompo, SWT.NONE);
			labelAuthuresation.setText("Authorisation: ");
			txtAuthorisation=new Text(mainCompo, SWT.BORDER);
			Button creatStatement=new Button(mainCompo, SWT.NONE);
			creatStatement.setText("Create statement");
			creatStatement.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {					
					statementText.setText("CREATE SCHEMA "+txtSchemaName.getText()+" "+txtAuthorisation.getText()+"\r");		
					addToListOfStatementTextChanges();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
		}
		
		return composite;
	 }

	   /**
	   * for loading the tables names in the combo
	   */
	  private void loadTableInComboTables() {
		
		
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
		
		
		
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 5;
		//composite.setLayout(gridLayout2);
		
//		final Button creatButton=new Button(composite, SWT.BORDER);
//		creatButton.setText("CREATE");
		
		
		//*************************************
		
		//columnCompo.setLayoutData(gridData1);
		
		
		//***********************************
		
		

		
		
		
		tableNameComposite=new Composite(composite, SWT.BACKGROUND);
		tableNameComposite.setLayout(gridLayout2);
		if(statementText.getText().length()<8){
			tableNameComposite.setEnabled(false);
		}
		
		Label tableName =new Label(tableNameComposite, SWT.NONE);
		tableName.setText("Type or Select Table name:");
		textTableName=new Text(tableNameComposite, SWT.BORDER);
		final Button addTable =new Button(tableNameComposite, SWT.NONE);
		addTable.setText("Add to Statement");
		addTable.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+"	"+textTableName.getText()+"		(\r");
					addToListOfStatementTextChanges();
					columnCompo.setEnabled(true);
					//buttonsCompo.setEnabled(true);
					columsListCompo.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+"	"+textTableName.getText()+"		(\r");
					addToListOfStatementTextChanges();
					columnCompo.setEnabled(true);
					//buttonsCompo.setEnabled(true);
					columsListCompo.setEnabled(true);
				}
				
			}
		});
		//************************************
		
		
		
		//*************************Column Composite***
		columsListCompo=new Composite(composite, SWT.NONE);
		GridLayout gridLayoutx = new GridLayout();
		gridLayoutx.numColumns = 1;
		
		columsListCompo.setLayout(gridLayoutx);
		
		//************************************
		columnCompo=new Composite(columsListCompo, SWT.BORDER);
		columnCompo.setLayout(gridLayout2);
		if(statementText.getText().length()<8){
			columsListCompo.setEnabled(false);
		}
		
		Label tableName1 =new Label(tableNameComposite, SWT.NONE);
		tableName1.setText("Name:");
		Label tableName4 =new Label(tableNameComposite, SWT.NONE);
		tableName4.setText("NON/PRIMARY KEY");
		Label tableName2 =new Label(tableNameComposite, SWT.NONE);
		tableName2.setText("Type:");
		Label tableName3 =new Label(tableNameComposite, SWT.NONE);
		tableName3.setText("NOT/NULL:");
		
		Label tableName5 =new Label(tableNameComposite, SWT.NONE);
		tableName5.setText("");
		
		
		Label labelColumnName,labelColumnType;
		
		//Text textColumnName;
		textColumnName=new Text(columnCompo, SWT.BORDER);
		
		final Combo comboPrimaryKey=new Combo(columnCompo, SWT.NONE);
		comboPrimaryKey.add("PRIMARY_KEY");
		comboPrimaryKey.add("NON PRIMARY_KEY");
		
		final Combo comboColumnType=new Combo(columnCompo, SWT.NONE);
		comboColumnType.add("INT");
		comboColumnType.add("SHORT_INT");
		comboColumnType.add("LONGINT");
		comboColumnType.add("CHAR");
		comboColumnType.add("String");
		comboColumnType.add("BOOLEAN");
		
		final Combo comboColumnNULL=new Combo(columnCompo, SWT.NONE);
		comboColumnNULL.add("NULL");
		comboColumnNULL.add("NOT NULL");
		
		
		
		
		

		
		final Button addColumn =new Button(columnCompo, SWT.NONE);
		addColumn.setText("Add New Column");
		addColumn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if((textColumnName.getText().length()>0)&&(statementText.getText().length()>0)){
					//listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText());
					statementText.setText(statementText.getText()+kommaString+textColumnName.getText()+" "
							+comboColumnType.getText()+" "+comboColumnNULL.getText());
					
					columnList.add(comboColumnType.getText()+" "+comboColumnNULL.getText());
					kommaString="\r			,";
					
					if(comboPrimaryKey.getText().equals("PRIMARY_KEY")){
						statementText.setText(statementText.getText()+kommaString+comboPrimaryKey.getText()
								+" "+textColumnName.getText());
						columnList.add(comboPrimaryKey.getText()+" "+textColumnName.getText());
						kommaString="\r			,";
					}
					addToListOfStatementTextChanges();
				}

			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if((textColumnName.getText().length()>0)&&(statementText.getText().length()>0)){
					//listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText());
					statementText.setText(statementText.getText()+kommaString+textColumnName.getText()+" "
							+comboColumnType.getText()+" "+comboColumnNULL.getText());
					
					columnList.add(textColumnName.getText()+" "+comboColumnType.getText()
							+" "+comboColumnNULL.getText());
					kommaString="\r			,";
					
					if(comboPrimaryKey.getText().equals("PRIMARY_KEY")){
						statementText.setText(statementText.getText()+kommaString+comboPrimaryKey.getText()
								+" "+textColumnName.getText());
						columnList.add(kommaString+comboPrimaryKey.getText()+" "+textColumnName.getText());
						kommaString="\r			,";
					}
					addToListOfStatementTextChanges();
				}

				
				
			}
		});
		
		//********************************
		
		if(statementText.getText().length()<8){
			columsListCompo.setEnabled(false);
		}
		
		
		columnList=new List(columsListCompo, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL| SWT.H_SCROLL);
		columnList.setBounds(40, 20, 420, 200);
		GridData gridDatax = new GridData();
		gridDatax.horizontalAlignment = GridData.FILL;
		gridDatax.grabExcessHorizontalSpace = true;
		gridDatax.grabExcessVerticalSpace = true;
		gridDatax.verticalAlignment = GridData.FILL;
		columnList.setLayoutData(gridDatax);
		columnList.setSize(170, 150);
		
		String[] tempArrayOfColumns=parseColumnsFromStatment();
		if(tempArrayOfColumns!=null){
			loadTheColumnsIntoList(tempArrayOfColumns);
		}
		else{columnList.removeAll();}
		
		columnList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(columnList.getItems().length>0) 	tmpString2="\r			,";
				else tmpString2="";
				statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));
				addToListOfStatementTextChanges();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if(columnList.getItems().length>0) 	tmpString2="\r			,";
				else tmpString2="";
				statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));
				addToListOfStatementTextChanges();
			}
		});
		Button insertColumsIntoStatment=new Button(columsListCompo, SWT.NONE);
		insertColumsIntoStatment.setToolTipText("Insert All Columns from List into Statment");
		insertColumsIntoStatment.setText("Insert Columns");
		insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
			
			String tmpString;
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(columnList.getItems().length>0) 	tmpString="\r			,";
				else tmpString="\r			";
				
				for(int i=0;i<columnList.getItems().length;i++){
					statementText.setText(statementText.getText()+ columnList.getItems()[i]+tmpString);
					addToListOfStatementTextChanges();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				if(columnList.getItems().length>0) 	tmpString="\r			,";
				else tmpString="\r			";
			
				for(int i=0;i<columnList.getItems().length;i++){
					statementText.setText(statementText.getText()+ columnList.getItems()[i]+tmpString);
			
				}
				addToListOfStatementTextChanges();
			}
			
		});
		Button deleteFromColumnList =new Button(columsListCompo, SWT.NONE);
		deleteFromColumnList.setText("Remove from List");
		deleteFromColumnList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				columnList.remove(columnList.getSelectionIndex());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				columnList.remove(columnList.getSelectionIndex());
			}
		});
		
		//**************************************************************
		
		tableNameComposite.setEnabled(false);
		columnCompo.setEnabled(false);
		columsListCompo.setEnabled(false);
		
		//*************************************
		
//		creatButton.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				statementText.setText("CREATE	");
//				creatButton.setEnabled(false);
//				tableNameComposite.setEnabled(true);
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				
//				statementText.setText("CREATE 	");
//				tableNameComposite.setEnabled(true);
//				creatButton.setEnabled(false);
//			}
//		});
		//**************************************
		
		//statementText.setText("CREATE ");
		tableNameComposite.setEnabled(true);
		columnCompo.setEnabled(true);
		columsListCompo.setEnabled(true);
	}

	/**
	 * 
	 * @return
	 */
	private String[] parseColumnsFromStatment() {
		// TODO Auto-generated method stub
		ArrayList<String> parsedColumns=new ArrayList<String>();
		
		
		String cleandStatment=removeAllSpaces(statementText.getText());
		String[] wordsOfStatment =cleandStatment.split("\r");
		
		if((wordsOfStatment!=null)&&(wordsOfStatment.length>0)){
			for(int i=1;i<wordsOfStatment.length;i++){
				try {
					if(cleandStatment.contains(")")){
						//while(!(wordsOfStatment[i].contains(")"))){
							if(!(wordsOfStatment[i].contains(")"))	) parsedColumns.add(wordsOfStatment[i]);
						//}
					}
					else{
						if(!(wordsOfStatment[i].contains(")"))	) parsedColumns.add(wordsOfStatment[i]);
					}
				} catch (Exception e) {
					System.out.print("ERROR: "+e.getMessage());
				}
//				if(cleandStatment.contains(",")){
//					wordsOfStatment[i]=wordsOfStatment[i].substring(1);
//				}
			}
	
			if(wordsOfStatment.length>1){
				if(wordsOfStatment[wordsOfStatment.length-1].contains(")")) parsedColumns.add(wordsOfStatment[wordsOfStatment.length-1]);
			}
			String[] wordsOfStatment2=new String[parsedColumns.size()];
			for(int i=0;i<wordsOfStatment2.length;i++){
				try {
					wordsOfStatment2[i]=parsedColumns.get(i);
					if(wordsOfStatment2[i].contains(",")){
						wordsOfStatment2[i]=wordsOfStatment2[i].substring(1);
					}
				} catch (Exception e) {
					System.out.print("ERROR: "+e.getMessage());
				}
			}
			
			
			if((wordsOfStatment2.length>1)&&(wordsOfStatment2!=null)){
				//hier i remove the "("  ")" from the columns lines.
				if(wordsOfStatment2[wordsOfStatment2.length-1].contains(")")){
					try {
						wordsOfStatment2[wordsOfStatment2.length-1]=wordsOfStatment2[wordsOfStatment2.length-1].
							substring(wordsOfStatment2[wordsOfStatment2.length-1].length()-2,
												wordsOfStatment2[wordsOfStatment2.length-1].length()-1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.print("ERROR: "+e.getMessage());
					}
				}
			
			
			if(wordsOfStatment2[0].contains("("))
				wordsOfStatment2[0]=wordsOfStatment2[0].substring(1);
			
			}
			
		
			return wordsOfStatment2;
		}	
		
		return null;
	}

	/**
	 * loading the tables names of data source 
	 * into the List
	 */
	private void loadTheColumnsIntoList(String[] parsedColums) {
		
		columnList.removeAll();
		
//		//zum testen***
//		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
//			columnList.add("Item Number " + loopIndex);
//		}
//		//*************
		
		
		if(parsedColums!=null){
			for(int i=0;i<parsedColums.length;i++){
				columnList.add(parsedColums[i]);
			}
		}
		
		
	}
	
	//parsing the statement 
	//NOT In Use
	/**
	 * for changing the UI according to the loaded Statement.
	 * it like parsing the text from the loaded statement
	 */
	private void EditTheUIFromStatment() {
		// TODO Auto-generated method stub
		//dSourceTables
		String[] tempColumesNames = null;
		String cleandStatment=removeAllSpaces(statementText.getText());
		String[] wordsOfStatment =cleandStatment.split("\r");
		String arrayAsString="";
		for(int i=0;i<wordsOfStatment.length;i++){
			//if(wordsOfStatment[i].equals("INSERT INTO"))
			if(IsSringTableName(wordsOfStatment[i])){
				
			}
			else{
				if(!(IsStringKeyWord(wordsOfStatment[i]))){
			
					try {
						tempColumesNames=ParseStringIntoColumsNames(wordsOfStatment[i]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.print("ERROR: "+e.getMessage());
					}
					//ArrayList<Integer> indicesOfSelection=new ArrayList<Integer>();
					for(int j=0;j<tempColumesNames.length;j++){
						try {
							arrayAsString=ConvertArrayToString(listOfColumn.getItems());
							if(arrayAsString.contains(tempColumesNames[j])){
								//indicesOfSelection.add(j);
								listOfColumn.select(j);
								
							}
						} catch (Exception e) {
							System.out.print("ERROR: "+e.getMessage());
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
		
		String  cleanedString=RemoveAllUnnasacerelySpaces(statement);
		String[] wordsOfCentence = null;
		String statmentAsOneString = "";
		
		if(cleanedString!=null){
			if(cleanedString.contains(" ")){
				wordsOfCentence=cleanedString.split(" ");
			}
			if(wordsOfCentence!=null){
				for(int i=0;i<wordsOfCentence.length;i++){
					statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
				}
			}
			
			
			while(statmentAsOneString.contains(" ")){
				wordsOfCentence=cleanedString.split(" ");
				for(int i=0;i<wordsOfCentence.length;i++){
					statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
					
				}
				
			}
		}
		return statmentAsOneString;
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
				
					//three.setControl(creatInsert_UIElements(tabFolder,TABLE_ORIENTED));
					three.setControl(getTabOneControl(tabFolder,buttonList.get(i).getText()));
				
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
			if(!(listOfMainKeyWords.get(i).getMainKeyWord().equals("CREATE"))){	
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
							
							if(tmpKeyWord.getMainKeyWord().equals("TABLE")){
								tabFolder.setSelection(0);
							}
							if(tmpKeyWord.getMainKeyWord().equals("SCHEMA")){
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
