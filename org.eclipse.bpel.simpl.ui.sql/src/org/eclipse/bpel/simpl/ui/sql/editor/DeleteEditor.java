package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

public class DeleteEditor extends AStatementEditor {

	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreiben
	private String xmlFilePath="E:\\Studium_Dateien\\StuproA\\Workspace2\\org.eclipse.bpel.simpl.ui.sql\\src\\xmlParser\\DeleteDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	private Composite comp = null;
	private Composite compos = null;
	private Composite buttonsCompo=null;
	private StyledText statementText = null;
	List tablsList;
	ArrayList<Button> buttonList=new ArrayList<Button>();
	
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	
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
		compos.setLayoutData(gridData2);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		
		//parser.parseXmlFile(xmlFilePath);
		//buttonsCompo=new Composite(compos, SWT.NONE);
		//buttonsCompo.setLayoutData(gridLayout);
		//creatButtonsOfKeyWords(compos,parser.parseDocument());
		
		
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
		

		
		final Label tableNameLabel=new Label(composite, SWT.NONE);
		tableNameLabel.setText("Select the Table: ");
		//tableNameLabel.setLayoutData(gridData1);
		
		
		
		tablsList = new List(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		tablsList.setBounds(40, 20, 320, 100);
		for (int loopIndex = 0; loopIndex < 9; loopIndex++) {
			tablsList.add("Item Number " + loopIndex);
		}
		loadTheTablesIntoList();
		//tablsList.setLayoutData(gridData1);
		tablsList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				statementText.setText(statementText.getText()+"\r	"+tablsList.getItem(tablsList.getSelectionIndex()));
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				statementText.setText(statementText.getText()+"\r	"+tablsList.getItem(tablsList.getSelectionIndex()));

			}
		});
		
		
		
		
		final Button deleteButton =new Button(composite, SWT.BORDER);
		deleteButton.setText("DELETE FROM");
		deleteButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				statementText.setText("DELETE FROM\r	:COMMENT.hier comes the table name or WHERE, SELECT...");
				
				tableNameLabel.setEnabled(true);
				tablsList.setEnabled(true);
				deleteButton.setEnabled(false);
				tableNameLabel.setEnabled(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statementText.setText("DELETE FROM\r	:COMMENT.hier comes the table name or WHERE, SELECT...");
				
				tableNameLabel.setEnabled(true);
				tablsList.setEnabled(true);
				tableNameLabel.setEnabled(true);
				deleteButton.setEnabled(false);
			}
		});
		
		if(statementText.getText().length()==0){
			tableNameLabel.setEnabled(false);
			tablsList.setEnabled(false);
		}
		else{ //if the statment not Empty 
			tableNameLabel.setEnabled(false);
			tablsList.setEnabled(false);
			
			//tablsList.setSelection(GetTablesFromStatement());
		}
		
	}

	/**
	 * loading the tables names of data source 
	 * into the List
	 */
	private void loadTheTablesIntoList() {
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
	public void creatButtonsOfKeyWords(Composite buttonsComposite,final ArrayList<KeyWord> listOfMainKeyWords){
		//System.out.print("\n in creatButtonsOfKeyWords()");
		
		for(int i=0;i<listOfMainKeyWords.size();i++)
		{
			if((listOfMainKeyWords.get(i).getListOfSubKeyWords().size()>0)){
				creatButtonsOfKeyWords(buttonsComposite,listOfMainKeyWords.get(i).getListOfSubKeyWords());
			}
			final Button keyWordAsButton=new Button(buttonsComposite, SWT.NONE);
			keyWordAsButton.setText(listOfMainKeyWords.get(i).getMainKeyWord());
			keyWordAsButton.setSize(20, 10);
			if(!listOfMainKeyWords.get(i).isTheMajorKey()){
				keyWordAsButton.setEnabled(false);
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
					keyWordAsButton.setEnabled(false);
					
					for(int x=0;x<buttonList.size();x++){
						//if(buttonList.get(x).getText().equals(e.text)){buttonList.get(x).setEnabled(false);}
						buttonList.get(x).setEnabled(false);
						for(int j=0;j<tmpKeyWord.getListOfSubKeyWords().size();j++){
							//
							if(tmpKeyWord.getListOfSubKeyWords().get(j).getMainKeyWord().equals(buttonList.get(x).getText())){
								buttonList.get(x).setEnabled(true);
							}
							
						}
						
						
					}
					
					statementText.setText(statementText.getText()+"\r"+keyWordAsButton.getText());
					
//					fatherComp.getShell().getData("StyledText")
//					s.setStatementText("sdfsdf");
				}
			});
			
			buttonList.add(keyWordAsButton);

		}
		
	}


}
