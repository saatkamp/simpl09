package org.eclipse.bpel.simpl.ui.sql.editor;

import java.awt.Checkbox;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

/**
 * Es fehlt noch die rückwärtz Parsen der Statement .
 */
public class CreateEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	List columnList;
	
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
		statementText = new StyledText(comp, SWT.BORDER|SWT.V_SCROLL| SWT.H_SCROLL);
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
		}
		else {statementText.setText("CREATE ");}
		
		CreateCREATEEditorUI(compos);
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
		
		
		Label labelColumnName,labelColumnType;
		
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
		
		final Combo comboPrimaryKey=new Combo(columnCompo, SWT.NONE);
		comboPrimaryKey.add("PRIMARY_KEY");
		comboPrimaryKey.add("NON PRIMARY_KEY");
		
		
		//Text textColumnName;
		textColumnName=new Text(columnCompo, SWT.BORDER);

		
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
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if(columnList.getItems().length>0) 	tmpString2="\r			,";
				else tmpString2="";
				statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));

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
			
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				if(columnList.getItems().length>0) 	tmpString="\r			,";
				else tmpString="\r			";
			
				for(int i=0;i<columnList.getItems().length;i++){
					statementText.setText(statementText.getText()+ columnList.getItems()[i]+tmpString);
			
				}
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
				try {
					creatButtonsOfKeyWords(listOfMainKeyWords.get(i).getListOfSubKeyWords());
				} catch (Exception e) {
					System.out.print("ERROR: "+e.getMessage());
				}
			}
			
			if(!(listOfMainKeyWords.get(i).getMainKeyWord().equals("CREATE"))){
				
			    final Button keyWordAsButton=new Button(buttonsCompo, SWT.NONE);
			    keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
			    keyWordAsButton.setText(listOfMainKeyWords.get(i).getMainKeyWord());
				//keyWordAsButton.setTextOfAction(listOfMainKeyWords.get(i).getTextOfKEyWord());
				
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
							buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
							for(int j=0;j<tmpKeyWord.getListOfSubKeyWords().size();j++){
								//
								if(tmpKeyWord.getListOfSubKeyWords().get(j).getMainKeyWord().equals(buttonList.get(x).getText())){
									buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
								}
								
							}
							
							
						}
						
						statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
//						if(tmpKeyWord.getMainKeyWord().equals("CREATE")){
//							
//							statementText.setText("CREATE ");
//							tableNameComposite.setEnabled(true);
//							columnCompo.setEnabled(true);
//							columsListCompo.setEnabled(true);
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
