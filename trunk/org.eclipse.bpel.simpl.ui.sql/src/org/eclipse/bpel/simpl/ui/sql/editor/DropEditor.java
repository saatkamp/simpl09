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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

public class DropEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	private Label dropLabel = null;
	private List dropList = null;
	private Text dropText = null;
	private String[] statement = new String[]{"", "", ""};
	List tablsList;
	private Composite tableNameComposite=null;
	private Text textSchemaName=null;
	private Label labelSchemaName=null;
	
	
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();

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
		//Elemente eingefügt.
		compos = new Composite(comp, SWT.NONE);
		compos.setLayout(new GridLayout());
		compos.setLayoutData(gridData2);

		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(xmlFilePath);
		buttonsCompo=new Composite(compos, SWT.NONE);
		buttonsCompo.setLayout(gridLayoutA);
		creatButtonsOfKeyWords(parser.parseDocument());
		
		comp.setLayoutData(gridData);
		statementText = new StyledText(comp, SWT.BORDER| SWT.V_SCROLL| SWT.H_SCROLL);
		statementText.setLayoutData(gridData1);
		statementText.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				//Sorgt dafür, dass alle Eingaben direkt gesichert werden.
				setStatement(statementText.getText());
				
			}});
		
		setComposite(comp);
		
		if (getStatement()!=null){
			statementText.setText(getStatement());
			if(statementText.getText().length()>8){

			}
			else{statementText.setText("DROP ");}
		}
		else {statementText.setText("DROP ");}
		
		//Erzeugen des Composite inhalts zur grafischen Modellierung
		createButtonComposite(compos);
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
		
		labelSchemaName =new Label(tableNameComposite, SWT.BORDER);
		labelSchemaName.setText("The handeld Schema/Table:");
		textSchemaName=new Text(tableNameComposite, SWT.BORDER);
		final Button insertSchemaName =new Button(tableNameComposite, SWT.BORDER);
		insertSchemaName.setText("Insert in Statement");
		insertSchemaName.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String kommaString=" ";
				buttonsCompo.setEnabled(true);
				if(textSchemaName.getText().length()>0){
					statementText.setText(statementText.getText()+kommaString+textSchemaName.getText());
					kommaString="\r			,";
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String kommaString=" ";
				buttonsCompo.setEnabled(true);
				if(textSchemaName.getText().length()>0){
					statementText.setText(statementText.getText()+kommaString+textSchemaName.getText());
					kommaString="\r			,";
				}
				
			}
		});
		tableNameComposite.setEnabled(false);
		
		//************************************
		
		
		//*************************************************
		tablsList = new List(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		tablsList.setBounds(40, 20, 320, 100);
		tablsList.setEnabled(false);
		loadTheTablesIntoList();
		
		tablsList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String kommaString=" ";
				statementText.setText(statementText.getText()+kommaString+tablsList.getItem(tablsList.getSelectionIndex()));
				kommaString=",";
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				String kommaString=" ";
				statementText.setText(statementText.getText()+kommaString+tablsList.getItem(tablsList.getSelectionIndex()));
				kommaString=",";	
			}
		});
		//**************************************************************
		
		
		
		
		
		//Da createButtonComposite bei der Erstellung des Statement-Editors
		//einmal aufgerufen wird, können wir direkt beim laden einmal das
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
		
		
		
		//Nun müssen wir noch die serialisierung (grafisch -> text) umsetzen
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
			tablsList.add("Item Number " + loopIndex);
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
			final Button keyWordAsButton=new Button(buttonsCompo, SWT.NONE);
			keyWordAsButton.setText(listOfMainKeyWords.get(i).getMainKeyWord());
			//keyWordAsButton.setTextOfAction(listOfMainKeyWords.get(i).getTextOfKEyWord());
			
			keyWordAsButton.setSize(listOfMainKeyWords.get(i).getMainKeyWord().length()+20, 70);

			if(!listOfMainKeyWords.get(i).isTheMajorKey()){
				keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
			}
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
								keyWordAsButton.setEnabled(true);
							}
							
						}
						
						
					}
					
					statementText.setText(statementText.getText()+tmpKeyWord.getTextOfKEyWord());
					if(tmpKeyWord.getMainKeyWord().equals("DROP")){
						
						tablsList.setEnabled(true);
						dropText.setEnabled(true);
						dropList.setEnabled(true);
						tableNameComposite.setEnabled(true);
						statementText.setText("DROP ");

					}
//					fatherComp.getShell().getData("StyledText")
//					s.setStatementText("sdfsdf");
				}
			});
			
			buttonList.add(keyWordAsButton);

		}
		
	}

	

}
