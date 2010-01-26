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

public class DeleteEditor extends AStatementEditor {

	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreiben
	private String xmlFilePath="/keywords/DeleteDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	private Composite comp = null;
	private Composite compos = null;
	
	private StyledText statementText = null;
	List tablsList;
	Composite tableNameComposite=null;
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	
	Text textTableName;
	public DeleteEditor() {
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
//		GridLayout gridLayoutB = new GridLayout();
//		gridLayoutB.numColumns = 2;
		compos.setLayoutData(gridData);
		
		
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
				// TODO Auto-generated method stub
				setStatement(statementText.getText());
			}});
		
		setComposite(comp);
		
		if (getStatement()!=null){
			statementText.setText(getStatement());
			if(statementText.getText().length()>8){
//				columsListCompo.setEnabled(true);
//				tableNameComposite.setEnabled(true);
//				buttonsCompo.setEnabled(true);
//				columnCompo.setEnabled(true);
				
			}
			else{statementText.setText("DELETE");}
		}
		else {statementText.setText("DELETE ");}
		
		
		
		CreateDELETEUIComponent(compos);
	}

	
	private void CreateDELETEUIComponent(Composite composite) {
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
		gridLayout.numColumns = 6;
		composite.setLayout(gridLayout);
		

		//****************************************************
		final Label tableNameLabel=new Label(composite, SWT.NONE);
		tableNameLabel.setText("Select the Table: ");
		//tableNameLabel.setLayoutData(gridData1);
		
		
		
		tablsList = new List(composite, SWT.BORDER | SWT.V_SCROLL| SWT.H_SCROLL);
		tablsList.setBounds(40, 20, 420, 100);
		
		parseStatment();
		loadTheTablesIntoList();
		//tablsList.setLayoutData(gridData1);
		tablsList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String kommaString=" ";
				statementText.setText(statementText.getText()+kommaString+tablsList.getItem(tablsList.getSelectionIndex()));
				kommaString="\r			,";
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				String kommaString=" ";
				statementText.setText(statementText.getText()+kommaString+tablsList.getItem(tablsList.getSelectionIndex()));
				kommaString="\r			,";
			}
		});
		//************************************************
		
		//************************************
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		tableNameComposite=new Composite(composite, SWT.BORDER);
		tableNameComposite.setLayout(gridLayout2);
		Label tableName =new Label(tableNameComposite, SWT.BORDER);
		textTableName=new Text(tableNameComposite, SWT.BORDER);
		final Button addTable =new Button(tableNameComposite, SWT.BORDER);
		addTable.setText("Add Name of handeld Element:");
		addTable.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String kommaString=" ";
				buttonsCompo.setEnabled(true);
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+kommaString+textTableName.getText());
					kommaString="\r			,";
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String kommaString=" ";
				buttonsCompo.setEnabled(true);
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+kommaString+textTableName.getText());
					kommaString="\r			,";
				}
				
			}
		});
		//tableNameComposite.setEnabled(false);
		
		//************************************
		
		
//		final Button deleteButton =new Button(composite, SWT.BORDER);
//		deleteButton.setText("DELETE");
//		deleteButton.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				
//				statementText.setText("DELETE FROM\r	:COMMENT.hier comes the table name or WHERE, SELECT...");
//				
//				tableNameLabel.setEnabled(true);
//				tablsList.setEnabled(true);
//				deleteButton.setEnabled(false);
//				tableNameLabel.setEnabled(true);
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				statementText.setText("DELETE FROM\r	");
//				
//				tableNameLabel.setEnabled(true);
//				tablsList.setEnabled(true);
//				tableNameLabel.setEnabled(true);
//				deleteButton.setEnabled(false);
//			}
//		});
		
		if(statementText.getText().length()==0){
			//tableNameLabel.setEnabled(false);
			//tablsList.setEnabled(false);
		}
		else{ //if the statment not Empty 
			//tableNameLabel.setEnabled(false);
			//tablsList.setEnabled(false);
			
			//tablsList.setSelection(GetTablesFromStatement());
		}
		
	}

	/**
	 * 
	 */
	private void parseStatment() {
		
		
		String[] statmentWords=removeAllSpaces(statementText.getText());
		if(statmentWords!=null){
			if(statmentWords.length>2){
				//String[] tempArray= new String[1];
				if(IsSringTableName(statmentWords[2])>=0){
					//tempArray[0]=statmentWords[2];
					tablsList.setSelection(IsSringTableName(statmentWords[2]));//(index)select(tempArray);//select(IsSringTableName(statmentWords[2]));
					textTableName.setText(statmentWords[2]);
				}
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
							}
							
						}
						
						
					}
					
					statementText.setText(statementText.getText()+tmpKeyWord.getTextOfKEyWord());
					if(tmpKeyWord.getTextOfKEyWord().equals("DELETE")){
						
						//tableNameLabel.setEnabled(true);
						tablsList.setEnabled(true);
						tableNameComposite.setEnabled(true);
						statementText.setText("DELETE ");
						
						
					}
//					fatherComp.getShell().getData("StyledText")
//					s.setStatementText("sdfsdf");
				}
			});
			
			buttonList.add(keyWordAsButton);

		}
		
	}


}
