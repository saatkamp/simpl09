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
import org.eclipse.swt.widgets.Text;

public class DropEditor extends AStatementEditor {

	MetaDataXMLParser metaDataXMLParser_Objekt;
	ArrayList<DBTable> globalListOfTables;
	private Composite comp = null;
	private Composite compos = null;
	private LiveEditStyleText statementText = null;
	private Label dropLabel = null;
	private List dropList = null;
	private Text dropText = null;
	private String[] statement = new String[]{"", "", ""};
	List tablsList,tablsList2;
	private Composite tableNameComposite=null;
	private Text textSchemaName=null;
	private Label labelSchemaName=null;
	Label proceLabel;
	Button addToStatement;
	Text proceText;
	// Create the containing tab folder
   TabFolder tabFolder;
    
   //********************************
    List columnList=null;
    Composite actionsCompo=null;
   	String TABLE_ORIENTED="values";
	String SET_ORIENTED="select";
    int tableIndex=0; 
    Table tableOfColumnsAndValues;
    Composite valueCompo;
    Combo restrictCascadeCombo,restrictCascadeCombo2;
   //******************************** 
	
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	
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
	private String xmlFilePath="/keywords/DropDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	public DropEditor() {
		// TODO Auto-generated constructor stub
	}
	
	//Erzeugt den kompletten Inhalt des Statement-Editors.
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
		//compos ist das obere Composite, hier werden die grafischen
		//Elemente eingef�gt.
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
		parser.parseXmlFile(xmlFilePath);
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
				//Sorgt daf�r, dass alle Eingaben direkt gesichert werden.
				setStatement(statementText.getText());
				
			}});
		
		setComposite(comp);
		
		if (getStatement()!=null){
			statementText.setText(getStatement());
			if(statementText.getText().length()>8){
				if(statementText.getText().equals("statement")){
					statementText.setText("DROP ");
				}
			}
			else{statementText.setText("DROP ");}
			addToListOfStatementTextChanges();
		}
		else {statementText.setText("DROP ");}
		
		//Erzeugen des Composite inhalts zur grafischen Modellierung
		//createButtonComposite(compos);
		metaDataXMLParser_Objekt=new MetaDataXMLParser();
		globalListOfTables= metaDataXMLParser_Objekt.loadTablesFromDB(getDataSource());
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
	    GridLayout gridLayout21 = new GridLayout();
		gridLayout21.numColumns = 2;
	    composite.setLayout(gridLayout21);
	    
	    Button insertButton;
	    
	    
		
		if(keyWordValue.toLowerCase().equals("drop schema"))
		{
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
			GridLayout gridLayout = new GridLayout();
			gridLayout.numColumns = 3;
			
			
			
//			dropList = new List(composite, SWT.BORDER);
//			dropList.setLayoutData(gridData1);
//			dropList.setItems(new String[]{"SCHEMA", "TABLE"});
//			dropList.setEnabled(false);
			//************************************
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.numColumns = 3;
			tableNameComposite=new Composite(composite, SWT.BORDER);
			tableNameComposite.setLayout(gridLayout2);
			//tableNameComposite.setEnabled(false);
			
			labelSchemaName =new Label(tableNameComposite, SWT.NONE);
			labelSchemaName.setText("The handeld Schema/Table:");
			textSchemaName=new Text(tableNameComposite, SWT.BORDER);
			Label labelRestrictCascade =new Label(tableNameComposite, SWT.NONE);
			labelRestrictCascade.setText("RESTRICT/CASCADE");
			restrictCascadeCombo=new Combo(tableNameComposite, SWT.BORDER);
			restrictCascadeCombo.add("RESTRICT");
			restrictCascadeCombo.add("CASCADE");
			
			final Button insertSchemaName =new Button(tableNameComposite, SWT.NONE);
			insertSchemaName.setText("Insert in Statement");
			insertSchemaName.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					String kommaString=" ";
					//buttonsCompo.setEnabled(true);
					if(textSchemaName.getText().length()>0){
						statementText.setText(statementText.getText()+kommaString+textSchemaName.getText()+" "+restrictCascadeCombo.getText());
						kommaString="\r			,";
						tableNameComposite.setEnabled(false);
						tablsList.setEnabled(false);
						buttonsCompo.setVisible(true);
					}
				
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					String kommaString=" ";
					//buttonsCompo.setEnabled(true);
					if(textSchemaName.getText().length()>0){
						statementText.setText(statementText.getText()+kommaString+textSchemaName.getText()+" "+restrictCascadeCombo.getText());
						addToListOfStatementTextChanges();
						kommaString="\r			,";
						tableNameComposite.setEnabled(false);
						//tablsList.setEnabled(false);
						buttonsCompo.setVisible(true);
					}
					
				}
			});
			
			
			//************************************
		}
		if(keyWordValue.toLowerCase().equals("drop table"))
		{
			//*************************************************
			tablsList2 = new List(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
			tablsList2.setBounds(40, 20, 320, 100);
			//tablsList2.setEnabled(false);
			loadTheTablesIntoList2();
			Label labelRestrictCascade2 =new Label(tableNameComposite, SWT.NONE);
			labelRestrictCascade2.setText("RESTRICT/CASCADE");
			restrictCascadeCombo2=new Combo(tableNameComposite, SWT.BORDER);
			restrictCascadeCombo2.add("RESTRICT");
			restrictCascadeCombo2.add("CASCADE");
			
			
			final Button insertTableName =new Button(composite, SWT.NONE);
			insertTableName.setText("Insert table into Statement");
			insertTableName.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(tablsList2.getSelection().length>0){
						String kommaString=" ";
						for(int i=0;i<tablsList2.getSelectionCount();i++){
							statementText.setText(statementText.getText()+kommaString+tablsList2.getSelection()[i]+" "+restrictCascadeCombo2.getText());
							//addToListOfStatementTextChanges();
							kommaString=",";
						}
						tablsList2.setEnabled(false);
						tableNameComposite.setEnabled(false);
						buttonsCompo.setVisible(true);
					}
					
				
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(tablsList2.getSelection().length>0){
						String kommaString=" ";
						for(int i=0;i<tablsList2.getSelectionCount();i++){
							statementText.setText(statementText.getText()+kommaString+tablsList.getSelection()[i]+" "+restrictCascadeCombo2.getText());
							addToListOfStatementTextChanges();
							kommaString=",";
						}
						
						tablsList2.setEnabled(false);
						tableNameComposite.setEnabled(false);
						buttonsCompo.setVisible(true);
					}
					
				}
			});
			//**************************************************************
		} 
		return composite;
	}
	
	
	

	//Erzeugt die grafischen Elemente zur Statement-Modellierung
	private void createButtonComposite(Composite composite) {
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
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		
		
		
//		dropList = new List(composite, SWT.BORDER);
//		dropList.setLayoutData(gridData1);
//		dropList.setItems(new String[]{"SCHEMA", "TABLE"});
//		dropList.setEnabled(false);
		//************************************
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		tableNameComposite=new Composite(composite, SWT.BORDER);
		tableNameComposite.setLayout(gridLayout2);
		tableNameComposite.setEnabled(false);
		
		labelSchemaName =new Label(tableNameComposite, SWT.NONE);
		labelSchemaName.setText("The handeld Schema/Table:");
		textSchemaName=new Text(tableNameComposite, SWT.BORDER);
		final Button insertSchemaName =new Button(tableNameComposite, SWT.NONE);
		insertSchemaName.setText("Insert in Statement");
		insertSchemaName.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String kommaString=" ";
				//buttonsCompo.setEnabled(true);
				if(textSchemaName.getText().length()>0){
					statementText.setText(statementText.getText()+kommaString+textSchemaName.getText());
					kommaString="\r			,";
					tableNameComposite.setEnabled(false);
					tablsList.setEnabled(false);
					buttonsCompo.setVisible(true);
				}
			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String kommaString=" ";
				//buttonsCompo.setEnabled(true);
				if(textSchemaName.getText().length()>0){
					statementText.setText(statementText.getText()+kommaString+textSchemaName.getText());
					addToListOfStatementTextChanges();
					kommaString="\r			,";
					tableNameComposite.setEnabled(false);
					tablsList.setEnabled(false);
					buttonsCompo.setVisible(true);
				}
				
			}
		});
		
		
		//************************************
		
		
		//*************************************************
		tablsList = new List(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		tablsList.setBounds(40, 20, 320, 100);
		//tablsList.setEnabled(false);
		loadTheTablesIntoList();
		
		
		
		final Button insertTableName =new Button(composite, SWT.NONE);
		insertTableName.setText("Insert Tablename into Statement");
		insertTableName.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tablsList.getSelection().length>0){
					String kommaString=" ";
					for(int i=0;i<tablsList.getSelectionCount();i++){
						statementText.setText(statementText.getText()+kommaString+tablsList.getSelection()[i]);
						addToListOfStatementTextChanges();
						kommaString=",";
					}
					tablsList.setEnabled(false);
					tableNameComposite.setEnabled(false);
					buttonsCompo.setVisible(true);
				}
				
			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if(tablsList.getSelection().length>0){
					String kommaString=" ";
					for(int i=0;i<tablsList.getSelectionCount();i++){
						statementText.setText(statementText.getText()+kommaString+tablsList.getSelection()[i]);
						addToListOfStatementTextChanges();
						kommaString=",";
					}
					
					tablsList.setEnabled(false);
					tableNameComposite.setEnabled(false);
					buttonsCompo.setVisible(true);
				}
				
			}
		});
		//**************************************************************
		
		
		
		
		
		//Da createButtonComposite bei der Erstellung des Statement-Editors
		//einmal aufgerufen wird, k�nnen wir direkt beim laden einmal das
		//statement aus dem Textfeld in die grafischen Elemente laden.
		
		//Als erstes lesen wir das Statement aus dem Textfeld und teilen
		//es bei jedem Leerzeichen. Das ganze speichern wir als Array.
		//Ergebnis ist z.B. statement = {DROP, TABLE, tabelle1}
		//*****************
//		final Button dropButton=new Button(composite, SWT.BORDER);
//		dropButton.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				// TODO Auto-generated method stub
//				statementText.setText("DROP	");
//				tablsList.setEnabled(true);
//				dropText.setEnabled(true);
//				dropList.setEnabled(true);
//				dropButton.setEnabled(false);
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				// TODO Auto-generated method stub
//				statementText.setText("DROP	");
//				tablsList.setEnabled(true);
//				dropText.setEnabled(true);
//				dropList.setEnabled(true);
//				dropButton.setEnabled(false);
//			}
//		});
		//*****************
		
		statement = statementText.getText().split(" ");
		
		
//		if (statement.length > 1){
//			dropList.select(dropList.indexOf(statement[1]));
//			if (statement.length > 2){
//				dropText.setText(statement[2]);
//			}
//		}
		
		
		
		//Nun m�ssen wir noch die serialisierung (grafisch -> text) umsetzen
//		dropList.addSelectionListener(new SelectionListener(){
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				statement[1] = dropList.getItem(dropList.getSelectionIndex());
//				//Aktualisieren das Statement im Textfeld
//				statementText.setText("DROP " + statement[1] + " " + statement[2]);
//			}});
//		
//		dropText.addModifyListener(new ModifyListener(){
//
//			@Override
//			public void modifyText(ModifyEvent e) {
//				statement[2] = dropText.getText();
//				//Aktualisieren das Statement im Textfeld
//				statementText.setText("DROP " + statement[1] + " " + statement[2]);
//			}});
	}
	
	/**
	 * 
	 */
	private void parseStatment() {
		
		
		String[] statmentWords=removeAllSpaces(statementText.getText());
		if(statmentWords.length>2){
			//String[] tempArray= new String[1];
			if(IsSringTableName(statmentWords[2])>=0){
				//tempArray[0]=statmentWords[2];
				if(tablsList.getItemCount()>=0){
					tablsList.select(IsSringTableName(statmentWords[2]));//(index)select(tempArray);//select(IsSringTableName(statmentWords[2]));
				}
				textSchemaName.setText(statmentWords[2]);
			}
		}
		
		
		
		//tablsList
		
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
	private String[] removeAllSpaces(String statement) {
		String cleanedString=RemoveAllUnnasacerelySpaces(statement);
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
		return wordsOfCentence;
	}

	/**
	 * for checking if the string is one of the data source 
	 * Tables.
	 * @param string
	 * @return boolean
	 */
	private int IsSringTableName(String string) {
		//TODO: �erpr�fen 
		
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
		
//		ArrayList<String> tabelsNames=null;//TODO: tables namen laden
//		if(tabelsNames!=null){
//			for(int i=0;i<tabelsNames.size();i++){
//				tablsList.add(tabelsNames.get(i));
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
		    for(int i=0;i<listOfMainKeyWords.get(0).getListOfSubKeyWords().size();i++)
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
					three.setControl(getTabOneControl(tabFolder,listOfMainKeyWords.get(0).getListOfSubKeyWords().get(i).getMainKeyWord()));
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
			if(!(listOfMainKeyWords.get(i).getMainKeyWord().equals("DROP"))){	
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
							
							if(tmpKeyWord.getMainKeyWord().equals("DROP SCHEMA")){
								tabFolder.setSelection(0);
							}
							if(tmpKeyWord.getMainKeyWord().equals("DROP TABLE")){
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
