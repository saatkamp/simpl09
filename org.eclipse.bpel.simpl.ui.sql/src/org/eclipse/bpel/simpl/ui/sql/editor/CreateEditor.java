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
import org.eclipse.swt.widgets.Text;

import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;
import xmlParser.XMLUtils;

/**
 * Es fehlt noch die rückwärtz Parsen der Statement .
 */
public class CreateEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	private StyledText statementText = null;
	
	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreibencreatecr
	private String xmlFilePath=XMLUtils.getURLFromPath("keywords/CreateDMActivityXMLFile.xml");
	//gerade nicht in gebrauch
	
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
					listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
					statementText.setText(statementText.getText()+"\r			"+textColumnName.getText()+" "+comboColumnType.getText()+",");
				}
//				textColumnName=new Text(columnCompo, SWT.NONE);
//				comboColumnType=new Combo(columnCompo, SWT.NONE);
//				comboColumnType.setItems(defaultCombo.getItems());
//				
//				addColumnToStatement=new Button(columnCompo, SWT.NONE);
//				addColumnToStatement.setText("Ok");
//				addColumnToStatement.addSelectionListener(new SelectionListener() {
//					
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
//
//						
//					}
//					
//					@Override
//					public void widgetDefaultSelected(SelectionEvent e) {
//						listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
//					}
//				});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if((textColumnName.getText().length()>0)&&(statementText.getText().length()>0)){
					listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
					statementText.setText(statementText.getText()+"\r			"+textColumnName.getText()+" "+comboColumnType.getText()+",");
				}
//				textColumnName=new Text(columnCompo, SWT.NONE);
//				comboColumnType=new Combo(columnCompo, SWT.NONE);
//				comboColumnType.setItems(defaultCombo.getItems());
//				
//				addColumnToStatement=new Button(columnCompo, SWT.NONE);
//				addColumnToStatement.setText("Ok");
//				addColumnToStatement.addSelectionListener(new SelectionListener() {
//					
//					@Override
//					public void widgetSelected(SelectionEvent e) {
//						listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
//
//						
//					}
//					
//					@Override
//					public void widgetDefaultSelected(SelectionEvent e) {
//						listOfTheColumns.add(textColumnName.getText()+" "+comboColumnType.getText()+",");
//					}
//				});
				
				
			}
		});
		columnCompo.setVisible(false);
		//***********************************
		
		
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
		final Button creatButton=new Button(composite, SWT.BORDER);
		creatButton.setText("CREATE");
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
