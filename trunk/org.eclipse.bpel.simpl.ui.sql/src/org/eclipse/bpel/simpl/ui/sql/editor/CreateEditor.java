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
	
	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreibencreatecr
	private String xmlFilePath="/keywords/CreateDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	String kommaString="";
	Text textColumnName,textTableName;
	Combo comboColumnType;
	Button addColumnToStatement;
	ArrayList<String> listOfTheColumns=new ArrayList<String>();
	
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
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
		comp = new Composite(composite, SWT.NONE);
		comp.setLayout(new GridLayout());
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = GridData.FILL;
		GridLayout gridLayoutB = new GridLayout();
		gridData2.horizontalAlignment = 1;
		compos = new Composite(comp, SWT.NONE);
		compos.setLayout(new GridLayout());
		compos.setLayoutData(gridData2);
		
		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(xmlFilePath);
		buttonsCompo=new Composite(compos, SWT.NONE);
		buttonsCompo.setLayout(gridLayoutA);
		creatButtonsOfKeyWords(parser.parseDocument());
		buttonsCompo.setVisible(false);
		
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
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		
		composite.setLayout(gridLayout);
		
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		composite.setLayout(gridLayout);
		
		final Button creatButton=new Button(composite, SWT.BORDER);
		creatButton.setText("CREATE");
		
		
		//*************************************
		final Composite columnCompo=new Composite(composite, SWT.BORDER);
		columnCompo.setLayout(gridLayout2);
		//columnCompo.setLayoutData(gridData1);
		Label labelColumnName,labelColumnType;
		
		final Combo comboColumnType=new Combo(columnCompo, SWT.NONE);
		comboColumnType.add("INT");
		comboColumnType.add("SHORT_INT");
		comboColumnType.add("LONGINT");
		comboColumnType.add("CHAR");
		comboColumnType.add("String");
		comboColumnType.add("BOOLEAN");
		
		//Text textColumnName;
		textColumnName=new Text(columnCompo, SWT.NONE);

		
		final Button addColumn =new Button(columnCompo, SWT.BORDER);
		addColumn.setText("Add New Column");
		addColumn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if((textColumnName.getText().length()>0)&&(statementText.getText().length()>0)){
					//listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText());
					statementText.setText(statementText.getText()+"\r			"+kommaString+textColumnName.getText()+" "+comboColumnType.getText());
					columnList.add(textColumnName.getText()+" "+comboColumnType.getText());
					kommaString=",";
				}

			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if((textColumnName.getText().length()>0)&&(statementText.getText().length()>0)){
					//listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText());
					statementText.setText(statementText.getText()+"\r			"+kommaString+textColumnName.getText()+" "+comboColumnType.getText());
					columnList.add(textColumnName.getText()+" "+comboColumnType.getText());
					kommaString=",";
				}

				
				
			}
		});
		columnCompo.setVisible(false);
		//***********************************
		
		//*************************Column Composite***
		Composite columsListCompo=new Composite(composite, SWT.NONE);
		GridLayout gridLayoutx = new GridLayout();
		gridLayoutx.numColumns = 1;
		columsListCompo.setLayout(gridLayoutx);
		
		columnList=new List(columsListCompo, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		columnList.setBounds(40, 20, 420, 200);
		columnList.setEnabled(false);
		
		ArrayList<String> tempArrayListOfColumns=parseColumnsFromStatment();
		if(tempArrayListOfColumns!=null){
			loadTheColumnsIntoList(tempArrayListOfColumns);
		}
		else{columnList.removeAll();}
		
		columnList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				statementText.setText(statementText.getText()+"\r	"+columnList.getItem(columnList.getSelectionIndex()));
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				statementText.setText(statementText.getText()+"\r	"+columnList.getItem(columnList.getSelectionIndex()));

			}
		});
		Button insertColumsIntoStatment=new Button(columsListCompo, SWT.NONE);
		insertColumsIntoStatment.setToolTipText("Insert All Columns from List into Statment");
		insertColumsIntoStatment.setText("Insert Columns");
		insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
			
			String tmpString;
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(columnList.getItems().length>0) 	tmpString=",";
				else tmpString="";
				
				for(int i=0;i<columnList.getItems().length;i++){
					statementText.setText(statementText.getText()+"\r			"+ columnList.getItems()[i]+tmpString);
			
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			
				for(int i=0;i<columnList.getItems().length;i++){
					statementText.setText(statementText.getText()+"\r			"+ columnList.getItems()[i]+tmpString);
			
				}
			}
		});
		//**************************************************************

		
		
		//************************************
		final Composite tableNameComposite=new Composite(composite, SWT.BORDER);
		tableNameComposite.setLayout(gridLayout2);
		Label tableName =new Label(tableNameComposite, SWT.BORDER);
		textTableName=new Text(tableNameComposite, SWT.BORDER);
		final Button addTable =new Button(tableNameComposite, SWT.BORDER);
		addTable.setText("Add Name of handeld Element:");
		addTable.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+"\r		"+textTableName.getText()+"(");
					columnCompo.setVisible(true);
					buttonsCompo.setVisible(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+"\r		"+textTableName.getText()+"(");
					columnCompo.setVisible(true);
					buttonsCompo.setVisible(true);
				}
				
			}
		});
		tableNameComposite.setVisible(false);
		
		//************************************
		
		//*************************************
		
		creatButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				statementText.setText("CREATE SCHEMA  \r");
				creatButton.setVisible(false);
				tableNameComposite.setVisible(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				statementText.setText("CREATE SCHEMA  \r	(");
				tableNameComposite.setEnabled(true);
				creatButton.setEnabled(false);
			}
		});
		//**************************************
		
	}

	
	private ArrayList<String> parseColumnsFromStatment() {
		// TODO Auto-generated method stub
		ArrayList<String> parsedColumns=new ArrayList<String>();
		parsedColumns=null;
		
		return parsedColumns;
	}

	/**
	 * loading the tables names of data source 
	 * into the List
	 */
	private void loadTheColumnsIntoList(ArrayList<String> parsedColumsList) {
		
		columnList.removeAll();
		
		//zum testen***
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			columnList.add("Item Number " + loopIndex);
		}
		//*************
		
		
		if(parsedColumsList!=null){
			for(int i=0;i<parsedColumsList.size();i++){
				columnList.add(parsedColumsList.get(i));
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
			final Button keyWordAsButton=new Button(buttonsCompo, SWT.BORDER);
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
					
					statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
					
//					fatherComp.getShell().getData("StyledText")
//					s.setStatementText("sdfsdf");
				}
			});
			
			buttonList.add(keyWordAsButton);

		}
		
	}

}
