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
	Composite valuesCompo=null;
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	Composite actionsCompo=null;
	List columnList=null;
	List tablsList,valuesList;
	String tmpString2;
	
	String[] arrayOfParsedColumns;
	String[] arrayOfParsedValues;
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
		compos.setLayoutData(gridData);

		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(xmlFilePath);
		buttonsCompo=new Composite(compos, SWT.RIGHT_TO_LEFT);
		buttonsCompo.setLayout(gridLayoutA);
		creatButtonsOfKeyWords(parser.parseDocument());
		
		comp.setLayoutData(gridData);
		statementText = new StyledText(comp, SWT.BORDER | SWT.V_SCROLL);
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
					statementText.setText("INSERT INTO ");
				}
				//tableNameComposite.setEnabled(true);
				//tablsList.setEnabled(true);
				//valuesCompo.setEnabled(true);

			}
			else{statementText.setText("INSERT INTO ");}
		}
		else {statementText.setText("INSERT INTO ");}
		
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
		tableNameComposite=new Composite(composite, SWT.NONE);
		tableNameComposite.setEnabled(false);
		tableNameComposite.setLayout(gridLayoutx);
		if(statementText.getText().length()<8){
			tableNameComposite.setEnabled(false);
		}
		
		Label tableName =new Label(tableNameComposite, SWT.NONE);
		tableName.setText("Type the name of the Table: ");
		textTableName=new Text(tableNameComposite, SWT.BORDER);
		final Button addTable =new Button(tableNameComposite, SWT.NONE);
		addTable.setText("Add to Statement");
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
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		tablsList = new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		tablsList.setBounds(40, 20, 320, 100);
		tablsList.setLayoutData(gridData);
		tablsList.setEnabled(false);
		loadTheTablesIntoList();
		
		tablsList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
				loadColumnsOfTable();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
				loadColumnsOfTable();
			}
		});
		//**************************************************************
		
		//************************************************************
		columnList=new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL| SWT.H_SCROLL);
		columnList.setBounds(40, 20, 420, 200);
		GridData gridDatax = new GridData();
		gridDatax.horizontalAlignment = GridData.FILL;
		gridDatax.grabExcessHorizontalSpace = true;
		gridDatax.grabExcessVerticalSpace = true;
		gridDatax.verticalAlignment = GridData.FILL;
		columnList.setLayoutData(gridDatax);
		//columnList.setSize(230, 150);
		
		
		
		
		
//		columnList.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				if(columnList.getItems().length>0) 	tmpString2="\r			,";
//				else tmpString2="";
//				//statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));
//				
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				if(columnList.getItems().length>0) 	tmpString2="\r			,";
//				else tmpString2="";
//				//statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));
//
//			}
//		});
		//************************************************************
		
		//************************************************************
		
		
		
		//************************************************************
		
		//***********************************************************
		valuesCompo=new Composite(tableNameComposite, SWT.BORDER);
		valuesCompo.setEnabled(false);
		GridLayout gridLayoutY=new GridLayout();
		gridLayoutY.numColumns=1;
		valuesCompo.setLayout(gridLayoutY);
		Label valuesLabel=new Label(valuesCompo, SWT.NONE);
		valuesLabel.setText("The Values for Inserting: ");
		valuesText=new Text(valuesCompo, SWT.BORDER);
		valuesText.setSize(250, 50);
		Button addValuesIntoList=new Button(valuesCompo, SWT.NONE);
		addValuesIntoList.setText("Add Values to Statement");
		//valuesText.setText(getParsedVlauesFromStatement()); 
		addValuesIntoList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				valuesList.add(valuesText.getText());
				valuesText.setText("");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				valuesList.add(valuesText.getText());
				valuesText.setText("");
			}
		});
		valuesList=new List(valuesCompo, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		valuesList.setBounds(40, 20, 420, 200);
		GridData gridDatax2 = new GridData();
		gridDatax2.horizontalAlignment = GridData.FILL;
		gridDatax2.grabExcessHorizontalSpace = true;
		gridDatax2.grabExcessVerticalSpace = true;
		gridDatax2.verticalAlignment = GridData.FILL;
		valuesList.setLayoutData(gridDatax2);
		valuesList.setSize(170, 150);
		//***********************************************************
		
		
		//***********************************************************
		
		//***********************************************************
		
		//********************************
		actionsCompo=new Composite(tableNameComposite, SWT.NONE);
		GridLayout gridLayoutZ = new GridLayout();
		gridLayoutZ.numColumns = 2;
		
		actionsCompo.setLayout(gridLayoutZ);
//		if(statementText.getText().length()<8){
//			actionsCompo.setEnabled(false);
//		}
		
		
		Button insertColumsIntoStatment=new Button(actionsCompo, SWT.NONE);
		insertColumsIntoStatment.setToolTipText("Add Columns and the values into the Statment");
		insertColumsIntoStatment.setText("Insert to Statement");
		insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
			
			String tmpString="";
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(columnList.getItems().length>0) 	tmpString=" ,";
				else tmpString=" ";
				
				statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
				statementText.setText(statementText.getText()+" "+columnList.getItems()[columnList.getSelectionIndices()[0]]);
				for(int i=1;i<columnList.getSelectionIndices().length;i++){
					//if(columnList.isSelected(i)){
						statementText.setText(statementText.getText()+tmpString+columnList.getItems()[columnList.getSelectionIndices()[i]]);
					//}
				}
				statementText.setText(statementText.getText()+")\r");

				
				if(valuesList.getItems().length>0) 	tmpString=" ,";
				else tmpString=" ";
				statementText.setText(statementText.getText()+"	VALUES (");
				statementText.setText(statementText.getText()+" "+valuesList.getItems()[0]);
				for(int i=1;i<valuesList.getItems().length;i++){
					statementText.setText(statementText.getText()+tmpString+valuesList.getItems()[i]);
			
				}
				statementText.setText(statementText.getText()+")\r");

			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				if(columnList.getItems().length>0) 	tmpString=" ,";
				else tmpString=" ";
				statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
				statementText.setText(statementText.getText()+" "+columnList.getItems()[columnList.getSelectionIndices()[0]]);
				for(int i=1;i<columnList.getSelectionIndices().length;i++){
					//if(columnList.isSelected(i)){
						statementText.setText(statementText.getText()+tmpString+columnList.getItems()[columnList.getSelectionIndices()[i]]);
					//}
				}
				statementText.setText(statementText.getText()+")\r");

				if(valuesList.getItems().length>0) 	tmpString=" ,";
				else tmpString=" ";
				statementText.setText(statementText.getText()+"	VALUES (");
				statementText.setText(statementText.getText()+" "+valuesList.getItems()[0]);
				for(int i=1;i<valuesList.getItems().length;i++){
					statementText.setText(statementText.getText()+tmpString+valuesList.getItems()[i]);
			
				}
				statementText.setText(statementText.getText()+")\r");

			}
		});
		
		Button deleteFromColumnList =new Button(actionsCompo, SWT.NONE);
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
		//parsing the colums and values from the statement into the lists.
		parseColumnsFromStatment();
		if(arrayOfParsedColumns!=null){
			loadTheColumnsIntoList(arrayOfParsedColumns);
		}else{columnList.removeAll();}
		
		if(arrayOfParsedValues!=null){
			loadTheValuesIntoList(arrayOfParsedValues);
		}else{valuesList.removeAll();}
		
		tableNameComposite.setEnabled(true);
		tablsList.setEnabled(true);
		valuesCompo.setEnabled(true);
	}
	
	
	

	/**
	 * 
	 * @return
	 */
	private void parseColumnsFromStatment() {
		// TODO Auto-generated method stub
		ArrayList<String> parsedColumns=new ArrayList<String>();
		ArrayList<String> parsedValues=new ArrayList<String>();
		
		
		String cleandStatment=removeAllSpaces(statementText.getText());
		String[] wordsOfStatment =cleandStatment.split("\r");
		
		if((wordsOfStatment!=null)&&(wordsOfStatment.length>0)){
			//
				//if(cleandStatment.contains(")")){
					//while(!(wordsOfStatment[i].contains(")"))){
						if((wordsOfStatment[0].contains("("))){
							String[] wordsOfFirstLine =wordsOfStatment[0].split(" ");
							String tmpString = "";
							for(int i=5;i<wordsOfFirstLine.length;i++){
								if(i==wordsOfFirstLine.length-1) tmpString=tmpString+wordsOfFirstLine[i].substring(0,wordsOfFirstLine[i].length()-1);
								else tmpString=tmpString+wordsOfFirstLine[i];
							}
							String[] colums=tmpString.split(",");
							for(int i=0;i<colums.length;i++){
								parsedColumns.add(colums[i]);
							}
							
							
						}
						if(wordsOfStatment.length>2){
							if((wordsOfStatment[1].contains(" ("))){
								String[] wordsOfSecondLine =wordsOfStatment[1].split(" ");
								String tmpString = "";
								for(int i=2;i<wordsOfSecondLine.length;i++){
									if(!wordsOfSecondLine[i].equals(" ")){
										if(i==wordsOfSecondLine.length-1) tmpString=tmpString+wordsOfSecondLine[i].substring(0,wordsOfSecondLine[i].length()-1);
										else tmpString=tmpString+wordsOfSecondLine[i];
									}
									
								}
								String[] values=tmpString.split(",");
								for(int i=0;i<values.length;i++){
									parsedValues.add(values[i]);
								}

							}	
						}
				}
			//converting the List to arrays.
			arrayOfParsedColumns=new String[parsedColumns.size()];
			arrayOfParsedValues=new String[parsedValues.size()];
			for(int i=0;i<arrayOfParsedColumns.length;i++){
				arrayOfParsedColumns[i]=parsedColumns.get(i);
			}
			for(int i=0;i<arrayOfParsedValues.length;i++){
				arrayOfParsedValues[i]=parsedValues.get(i);
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
	 * load the columns of the selected table.
	 */
	private void loadColumnsOfTable() {
		
		
		String tableName=tablsList.getItem(tablsList.getSelectionIndex());
		columnList.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			columnList.add(tableName+"_Column" + loopIndex);
		}
		//*************
		
		ArrayList<String> columnsNames=null;//TODO: Column namen laden
		if(columnsNames!=null){
			for(int i=0;i<columnsNames.size();i++){
				columnList.add(columnsNames.get(i));
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
						statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());

						tableNameComposite.setEnabled(true);
						tablsList.setEnabled(true);
						valuesCompo.setEnabled(true);
//
//						if(tmpKeyWord.getMainKeyWord().equals("INSERT")){
//							statementText.setText(tmpKeyWord.getTextOfKEyWord());
//							tableNameComposite.setEnabled(true);
//							tablsList.setEnabled(true);
//							valuesCompo.setEnabled(true);
//	
//						}
//						else{ statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
//						}
						
	//					fatherComp.getShell().getData("StyledText")
	//					s.setStatementText("sdfsdf");
					}
				});
				
				buttonList.add(keyWordAsButton);
			}
		}
		
	}


}
