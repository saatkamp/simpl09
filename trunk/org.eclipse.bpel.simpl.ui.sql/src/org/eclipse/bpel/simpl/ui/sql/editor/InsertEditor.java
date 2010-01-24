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
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

public class InsertEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	Text textTableName=null;
	Composite tableNameComposite=null;
	Text valuesText=null;
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();

	List tablsList;
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
		}
		
		creatINSERT_UIElements(compos);
	}
	
	
	
	/**
	 * 
	 * @param composite
	 */
	private void creatINSERT_UIElements(Composite composite){
		
		Button insertButton;
		
		
		
		//********************************
		GridLayout gridLayoutx = new GridLayout();
		gridLayoutx.numColumns = 3;
		tableNameComposite=new Composite(composite, SWT.BORDER);
		tableNameComposite.setLayout(gridLayoutx);
		if(statementText.getText().length()<8){
			tableNameComposite.setEnabled(false);
		}
		
		Label tableName =new Label(tableNameComposite, SWT.NONE);
		tableName.setText("Type the name of the Table: ");
		textTableName=new Text(tableNameComposite, SWT.BORDER);
		final Button addTable =new Button(tableNameComposite, SWT.BORDER);
		addTable.setText("Add Name of handeld Element:");
		addTable.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+"	"+textTableName.getText()+"\r	VALUES ");
					//columnCompo.setEnabled(true);
					//buttonsCompo.setEnabled(true);
					//columsListCompo.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+"	"+textTableName.getText()+"\r	VALUES ");
					//columnCompo.setEnabled(true);
					//buttonsCompo.setEnabled(true);
					//columsListCompo.setEnabled(true);
				}
				
			}
		});
		
		
		//************************************
		
		//*************************************************
		tablsList = new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		tablsList.setBounds(40, 20, 320, 100);
		tablsList.setEnabled(false);
		loadTheTablesIntoList();
		
		tablsList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));

			}
		});
		//**************************************************************
		
		
		//***********************************************************
		Composite valuesCompo=new Composite(composite, SWT.BORDER);
		GridLayout gridLayoutY=new GridLayout();
		gridLayoutY.numColumns=1;
		valuesCompo.setLayout(gridLayoutY);
		Label valuesLabel=new Label(valuesCompo, SWT.NONE);
		valuesLabel.setText("The Values for Inserting: ");
		valuesText=new Text(valuesCompo, SWT.NONE|SWT.V_SCROLL);
		valuesText.setSize(250, 100);
		Button addValues=new Button(valuesCompo, SWT.NONE);
		addValues.setText("Add Values to Statement");
		valuesText.setText(getParsedVlauesFromStatement());
		addValues.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				statementText.setText(statementText.getText()+" "+valuesText.getText());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				statementText.setText(statementText.getText()+" "+valuesText.getText());

			}
		});
		//***********************************************************
	}
	

	/**
	 * its for parsing the statement and getting the Values of it
	 * 
	 * @return
	 */
	private String getParsedVlauesFromStatement() {
		String valuesString="";
		if(statementText.getText().length()>0){
			String cleandStatment=removeAllSpaces(statementText.getText());
			String[] wordsOfStatment =cleandStatment.split("\r");
			
			if(wordsOfStatment.length>1){
			 valuesString=wordsOfStatment[1].substring(7,wordsOfStatment[1].length()-1);
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
						keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
						for(int j=0;j<tmpKeyWord.getListOfSubKeyWords().size();j++){
							//
							if(tmpKeyWord.getListOfSubKeyWords().get(j).getMainKeyWord().equals(buttonList.get(x).getText())){
								buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));

							}
							
						}
						
						
					}
					if(tmpKeyWord.getMainKeyWord().equals("INSERT")){
						statementText.setText(tmpKeyWord.getTextOfKEyWord());
						tableNameComposite.setEnabled(true);
						tablsList.setEnabled(true);

					}
					else{ statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
					}
					
//					fatherComp.getShell().getData("StyledText")
//					s.setStatementText("sdfsdf");
				}
			});
			
			buttonList.add(keyWordAsButton);

		}
		
	}


}
