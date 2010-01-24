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
import org.eclipse.swt.widgets.Text;

import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

public class UpdateEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	private Composite resultSETStatementCompo=null;
	private List listOfTabels=null,listOfColumns=null;
	private Text resultSelectedTableColumns=null;
	Composite listsComposite=null;
	
	/**
	 * UPDATE table_name
		SET column1=value, column2=value2,...
		WHERE some_column=some_value
	 */
	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreiben
	private String xmlFilePath="/keywords/UpdateDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	public UpdateEditor() {
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

		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(xmlFilePath);
		buttonsCompo=new Composite(compos, SWT.NONE);
		buttonsCompo.setLayout(gridLayoutA);
		creatButtonsOfKeyWords(parser.parseDocument());
		
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
			if(statementText.getText().length()>8){
				//resultSETStatementCompo.setEnabled(true);
				//listsComposite.setEnabled(true);
			}
			else{statementText.setText("UPDATE ");}
		}
		else {statementText.setText("UPDATE ");}
		
		createUPDATEEditorElements(compos);
		
	}
	
	/**
	 * creating the ui Elemente of this Editor
	 * @param composite
	 */
	private void createUPDATEEditorElements(Composite composite) {
		
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		//gridData1.verticalAlignment = GridData.FILL;
		
		listsComposite=new Composite(composite, SWT.BORDER|SWT.H_SCROLL);
		listsComposite.setSize(300, 250);
		
		//***************************************************
		resultSETStatementCompo=new Composite(composite, SWT.NONE);
		resultSETStatementCompo.setEnabled(false);
		resultSelectedTableColumns=new Text(resultSETStatementCompo, SWT.NONE|SWT.H_SCROLL);
		resultSelectedTableColumns.setSize(300, 100);
		resultSelectedTableColumns.setText(parseStatment());
		GridLayout layout2=new GridLayout();
//		layout2.numColumns=2;
//		resultSETStatementCompo.setLayout(layout2);
		Button insertSETInStatment=new Button(composite, SWT.NONE);
		insertSETInStatment.setText("Add SET part to Statement");
		insertSETInStatment.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				statementText.setText(statementText.getText()+resultSelectedTableColumns.getText());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statementText.setText(statementText.getText()+resultSelectedTableColumns.getText());

				
			}
		});
		//**********************************************
		GridLayout layout=new GridLayout();
		layout.numColumns=2;
		listsComposite.setLayout(layout);
		listsComposite.setLayoutData(gridData1);
		
		listOfTabels=new List(listsComposite, SWT.NONE|SWT.V_SCROLL);
		listOfColumns=new List(listsComposite, SWT.NONE|SWT.V_SCROLL|SWT.MULTI);
		loadTablesFromDS();
		
		Button insertTableAndColumns=new Button(listsComposite, SWT.NONE);
		insertTableAndColumns.setText("Create SET statment part");
		listOfTabels.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadColumnsOfTable();
			}
			
			

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				loadColumnsOfTable();
			}
		});
		
		insertTableAndColumns.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				statementText.setText(statementText.getText()+listOfTabels.getItem(listOfTabels.getSelectionIndex())+"\r	SET"+
						getSelectedColumns());
			}
			
			

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statementText.setText(statementText.getText()+listOfTabels.getItem(listOfTabels.getSelectionIndex())+"\r	SET"+
						getSelectedColumns());
			}
		});
		
		
	}
	
	/**
	 * parsing the statment and getting the SET part with the data.
	 * @return
	 */
	private String parseStatment() {
		String valuesString="";
		if(statementText.getText().length()>0){
			String cleandStatment=removeAllSpaces(statementText.getText());
			String[] wordsOfStatment =cleandStatment.split("\r");
			
			if((wordsOfStatment.length>1)){
				for(int i=0;i<wordsOfStatment.length;i++){
					if((wordsOfStatment[i].contains("SET"))){
						valuesString=valuesString+wordsOfStatment[i].substring(4,wordsOfStatment[i].length()-1);
						
					}
				}
			}
			else{ valuesString="";}
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
	 * load the columns of the selected table.
	 */
	private void loadColumnsOfTable() {
		
		String tableName=listOfTabels.getItem(listOfTabels.getSelectionIndex());
		listOfColumns.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			listOfColumns.add(tableName+"_Column" + loopIndex);
		}
		//*************
		
		ArrayList<String> columnsNames=null;//TODO: Column namen laden
		if(columnsNames!=null){
			for(int i=0;i<columnsNames.size();i++){
				listOfColumns.add(columnsNames.get(i));
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private String getSelectedColumns() {
		String selectedColumnsStatement="";
		if(listOfColumns.getSelection().length>0){
			for(int i=0;i<listOfColumns.getSelection().length-1;i++)
			{
				selectedColumnsStatement=selectedColumnsStatement+
					listOfColumns.getSelection()[i]+"=X,";
			}
			selectedColumnsStatement=selectedColumnsStatement+
			listOfColumns.getSelection()[listOfColumns.getSelection().length-1]+"=X";
		}
			return selectedColumnsStatement;
	}

	/**
	 * loading the tables from data source
	 * @return
	 */
	private void loadTablesFromDS() {
		
		listOfTabels.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			listOfTabels.add("Table" + loopIndex);
		}
		//*************
		
		ArrayList<String> tabelsNames=null;//TODO: tables namen laden
		if(tabelsNames!=null){
			for(int i=0;i<tabelsNames.size();i++){
				listOfTabels.add(tabelsNames.get(i));
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
							}
							
						}
						
						
					}
					
					if(tmpKeyWord.getMainKeyWord().equals("UPDATE")){
						statementText.setText(tmpKeyWord.getTextOfKEyWord());
						resultSETStatementCompo.setEnabled(true);
						listsComposite.setEnabled(true);	
					}
					else statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
					
//					fatherComp.getShell().getData("StyledText")
//					s.setStatementText("sdfsdf");
				}
			});
			
			buttonList.add(keyWordAsButton);

		}
		
	}


}
