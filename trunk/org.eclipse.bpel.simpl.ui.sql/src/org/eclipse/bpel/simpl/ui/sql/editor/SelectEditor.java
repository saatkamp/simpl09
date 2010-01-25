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
import org.eclipse.swt.widgets.List;

import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

public class SelectEditor extends SelectAndRetrieveDataEditor {

//	private Composite comp = null;
//	private Composite compos = null;
//	private StyledText statementText = null;
//	
//	ArrayList<Button> buttonList=new ArrayList<Button>();
//	private Composite buttonsCompo=null;
//	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
//    List listOfColumn;
//    ArrayList<String> dSourceTables;
//	private String[] theKeyWords = new String[]{"SELECT", "FROM", "WHERE"};//TODO: welche KeyWods bzw. befehle 
//																		   //sind noch denkbar für jeden StatmentEditor
//	public ArrayList<String> getdSourceTables() {
//		return dSourceTables;
//	}
//	/*
//	 * The XML file wich contais the statment KeyWords
//	 */
//	
//	//gerade nicht in gebrauch
//	
	public SelectEditor() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createComposite(Composite composite) {
		
		setXmlFilePath("/keywords/SelectDMActivityXMLFile.xml");
		setMainStatmentKeyWord("SELECT");
		super.createComposite(composite);
		
//		GridData gridData1 = new GridData();
//		gridData1.horizontalAlignment = GridData.FILL;
//		gridData1.grabExcessHorizontalSpace = true;
//		gridData1.grabExcessVerticalSpace = true;
//		gridData1.verticalAlignment = GridData.FILL;
//		GridData gridData = new GridData();
//		gridData.horizontalAlignment = GridData.FILL;
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.grabExcessVerticalSpace = true;
//		gridData.verticalAlignment = GridData.FILL;
//		comp = new Composite(composite, SWT.NONE);
//		comp.setLayout(new GridLayout());
//		GridData gridData2 = new GridData();
//		gridData2.horizontalAlignment = GridData.FILL;
//		gridData2.grabExcessHorizontalSpace = true;
//		gridData2.grabExcessVerticalSpace = true;
//		gridData2.verticalAlignment = GridData.FILL;
//		compos = new Composite(comp, SWT.NONE);
//		compos.setLayout(new GridLayout());
//		compos.setLayoutData(gridData2);
//
//		GridLayout gridLayoutA = new GridLayout();
//		gridLayoutA.numColumns = 6;
//		parser.parseXmlFile(getXmlFilePath());
//		buttonsCompo=new Composite(compos, SWT.NONE);
//		buttonsCompo.setLayout(gridLayoutA);
//		creatButtonsOfKeyWords(parser.parseDocument());
//		
//		comp.setLayoutData(gridData);
//		statementText = new StyledText(comp, SWT.BORDER);
//		statementText.setLayoutData(gridData1);
//		statementText.addModifyListener(new ModifyListener(){
//
//			@Override
//			public void modifyText(ModifyEvent e) {
//				// TODO Auto-generated method stub
//				setStatement(statementText.getText());
//			}});
//		
//		setComposite(comp);
//		
//		if (getStatement()!=null){
//			statementText.setText(getStatement());
//		}
//		CreatSELECTUIElemente(compos);
	}
	
//	/**
//	 * 
//	 * @param composite
//	 */
//	private void CreatSELECTUIElemente(Composite composite){
//		listOfColumn=new List(composite, SWT.NONE);
//	}
//	
//	/**
//	 * Load the coloms titels of the selected Table
//	 * in the coloms List 
//	 * @param tableName 
//	 */
//	private void LoadColumsAccordingToTable(String tableName){
//		ArrayList<String> dSourceColomsOfTable =new ArrayList<String>()/*=Data Source coloms*/; //TODO: get Coloms of table tableName from Source
//		for(int i=0;i<dSourceColomsOfTable.size();i++){
//			listOfColumn.add(dSourceColomsOfTable.get(i));
//		}
//		
//	}
//	
//	//parsing the statement 
//	
//	/**
//	 * for changing the UI according to the loaded Statement.
//	 * it like parsing the text from the loaded statement
//	 */
//	private void EditTheUIFromStatment() {
//		// TODO Auto-generated method stub
//		//dSourceTables
//		String[] tempColumesNames;
//		String cleandStatment=removeAllSpaces(getStatement());
//		String[] wordsOfStatment =cleandStatment.split("\r");
//		String arrayAsString="";
//		for(int i=0;i<wordsOfStatment.length;i++){
//			//if(wordsOfStatment[i].equals("INSERT INTO"))
//			if(IsSringTableName(wordsOfStatment[i])){
//				
//			}
//			else{
//				if(!(IsStringKeyWord(wordsOfStatment[i]))){
//			
//					tempColumesNames=ParseStringIntoColumsNames(wordsOfStatment[i]);
//					//ArrayList<Integer> indicesOfSelection=new ArrayList<Integer>();
//					for(int j=0;j<tempColumesNames.length;j++){
//						arrayAsString=ConvertArrayToString(listOfColumn.getItems());
//						if(arrayAsString.contains(tempColumesNames[j])){
//							//indicesOfSelection.add(j);
//							listOfColumn.select(j);
//							
//						}
//					}
//					//listOfColumn.select(indicesOfSelection.toArray());
//				}
//			}
//		}
//		//TODO: ....
//		
//	}
//	
//	/**
//	 * splits the string/sentence into the "," separated Columns names.
//	 * @param string
//	 * @return arrayOfColums
//	 */
//	private String[] ParseStringIntoColumsNames(String string) {
//		
//		String[] arrayOfColums=string.split(",");
//		return arrayOfColums;
//	}
//
//
//	/**
//	 * this function is for checking if the string is one of
//	 * the used KeyWords  
//	 * @param string
//	 * @return 
//	 */
//	private boolean IsStringKeyWord(String string) {
//		if(ConvertArrayToString(theKeyWords).contains(string)) return true;
//		return false;
//	}
//
//
//	/**
//	 * Convert an array of words into one String, wich the words
//	 * are separaed with " "
//	 * @param items
//	 * @return resultString
//	 */
//	private String ConvertArrayToString(String[] items) {
//		String resultString="";
//		for(int i=0;i<items.length;i++){
//			resultString=resultString+" "+items[i];
//		}
//		
//		return resultString;
//	}
//
//
//	/**
//	 * for checking if the string is one of the data source 
//	 * Tables.
//	 * @param string
//	 * @return boolean
//	 */
//	private boolean IsSringTableName(String string) {
//		//TODO: üerprüfen 
//		
//		for(int i=0;i<dSourceTables.size();i++){
//			if(dSourceTables.get(i).equals(string)) return true;
//		}
//		
//		return false;
//	}
//
//
//	/**
//	 * removes all spaces from statment
//	 * @param statement
//	 * @return statmentAsOneString
//	 */
//	private String removeAllSpaces(String statement) {
//		String[] wordsOfCentence = null;
//		String statmentAsOneString = "";
//		if(statement.contains(" ")){
//			wordsOfCentence=statement.split(" ");
//		}
//		for(int i=0;i<wordsOfCentence.length;i++){
//			statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
//		}
//		
//		while(statmentAsOneString.contains(" ")){
//			wordsOfCentence=statement.split(" ");
//			for(int i=0;i<wordsOfCentence.length;i++){
//				statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
//			}
//		}
//		
//		return statmentAsOneString;
//	}
//
//	
//	/**
//	 * For creating the buttons out of the xml file ,wich contains
//	 * the key words of the quary language. And after creating they
//	 * will be added into the composite.
//	 * 
//	 * in this function we creat the buttons for the parsed KeyWords.
//	 * 
//	 * @param listOfMainKeyWords
//	 */
//	public void creatButtonsOfKeyWords(final ArrayList<KeyWord> listOfMainKeyWords){
//		//System.out.print("\n in creatButtonsOfKeyWords()");
//		
//		for(int i=0;i<listOfMainKeyWords.size();i++)
//		{
//			if((listOfMainKeyWords.get(i).getListOfSubKeyWords().size()>0)){
//				creatButtonsOfKeyWords(listOfMainKeyWords.get(i).getListOfSubKeyWords());
//			}
//			final Button keyWordAsButton=new Button(buttonsCompo, SWT.NONE);
//			keyWordAsButton.setText(listOfMainKeyWords.get(i).getMainKeyWord());
//			//keyWordAsButton.setTextOfAction(listOfMainKeyWords.get(i).getTextOfKEyWord());
//			
//			keyWordAsButton.setSize(listOfMainKeyWords.get(i).getMainKeyWord().length()+20, 70);
//
//			if(!listOfMainKeyWords.get(i).isTheMajorKey()){
//				keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
//			}
//			//else isInsertKeyWord=false;
//			
//			final KeyWord tmpKeyWord=listOfMainKeyWords.get(i);
//			keyWordAsButton.addSelectionListener(new SelectionListener() {
//				@Override
//				public void widgetDefaultSelected(SelectionEvent e) {
//					// TODO Auto-generated method stub
//					widgetSelected(e);
//				}
//
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					// TODO hier muss der statement befehle in der textBox eingetragen werden.
//					
//					/*
//					 * in the following for statement all the buttons are only
//					 * then enabled if the father button (according to the Logik in the parsed xmlFile)
//					 */
//					keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
//					
//					for(int x=0;x<buttonList.size();x++){
//						//if(buttonList.get(x).getText().equals(e.text)){buttonList.get(x).setEnabled(false);}
//						buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
//						for(int j=0;j<tmpKeyWord.getListOfSubKeyWords().size();j++){
//							//
//							if(tmpKeyWord.getListOfSubKeyWords().get(j).getMainKeyWord().equals(buttonList.get(x).getText())){
//								buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
//							}
//							
//						}
//						
//						
//					}
//					
//					statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
//					
////					fatherComp.getShell().getData("StyledText")
////					s.setStatementText("sdfsdf");
//				}
//			});
//			
//			buttonList.add(keyWordAsButton);
//
//		}
//		
//	}


}
