
package org.eclipse.bpel.simpl.ui.sql.editor;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;


import org.eclipse.bpel.simpl.ui.StatementHashMap;
import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.bpel.simpl.ui.extensions.IStatementEditor;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.w3c.dom.Element;

import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;

/**
 * @author Firas.Zoabi
 * 
 *        class for creating the GUI Elements of the  StatementEditor
 *        Which depend on the readed/parsed xml Files of the Extensions
 */

public class InsertEditor_GenerateFromExtensionXML extends AStatementEditor {

	/**
	 * A list of all extensions and their child elements.
	 */
	private List<IConfigurationElement> languageExtensions = new ArrayList<IConfigurationElement>();

	/**
	 * Holds the id of the extension point which have to be managed.
	 */
	private static final String QUERY_LANGUAGE_POINT = "org.eclipse.bpel.simpl.ui.queryLanguage";
	
	boolean isInsertKeyWord=true;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	ArrayList<Button> buttonList=new ArrayList<Button>();
	Composite fatherComp;
	
	Composite com;
	Composite myComp;
	private String dataSourceType;
	public String getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	public InsertEditor_GenerateFromExtensionXML() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createComposite(Composite composite) {
		
		//setDataSourceType(dataSourceType);
		fatherComp=composite;
		// TODO Auto-generated method stub
		StatementHashMap statem = new StatementHashMap();
		
//		statem.put("INSERT", "(column1, column2, ...)");
//		statem.put("INTO", "table");
//		statem.put("VALUES", "(value1, value2, ...)");
		//com=new Composite(composite, SWT.NONE);
		myComp=new Composite(composite, SWT.NONE);
		//setStatement(statem);
		getExtensionElement();

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		myComp.setLayout(gridLayout);
//		FlowLayout layout=new FlowLayout();
//		myComp.setLayoutData(layout);
		
		getKeyWordsButtons("SQL");
		//setComp(myComp);
		
		
		
		
		setComposite(myComp);
	}
	
	
	/**
	 * Initializes the Application, which means that the list of
	 * languageExtensions will be initialized.
	 */
	public void getExtensionElement() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(QUERY_LANGUAGE_POINT);
		for (IConfigurationElement e : config) {
			languageExtensions.add(e);
			//System.out.println("Element: " + e.getChildren()[0].getAttribute("queryStatmentsLogikXmlFile"));
		}
	}
	
	/**
	 * For creating the buttons out of the xml file ,wich contains
	 * the key words of the quary language. And after creating they
	 * will be added into the composite.
	 * 
	 */
	public void getKeyWordsButtons(String language) {
		
		String SQL_ID="SQL";
		boolean ones=true;
		//IConfigurationElement element = null;
//		IStatementEditor editorClass = null;
		try {
			
			for (IConfigurationElement e : languageExtensions) {
			
			  if (e.getAttribute("language").contains(language/*getDataSourceType().equals("database")?SQL_ID:getDataSourceType()*/)) {
				  System.out.print(e.getAttribute("language")); 
			    for(IConfigurationElement elem:e.getChildren()){
			    	
					for (IConfigurationElement sub : elem.getChildren()) {
//
						if ((sub.getAttribute("type").contains("org.eclipse.bpel.simpl.model.InsertActivity"))&&(ones==true)) {			
							ones=false;
							//TODO: dann ist das die insert aktivität
//							//und es sollen die befehle von der xml datei geparrst werden !!!
//							//--> queryStatmentsLogikXmlFile
							System.out.print(elem.getAttribute("queryStatmentsLogikXmlFile"));
							parser.parseXmlFile(elem.getAttribute("queryStatmentsLogikXmlFile"));
							//System.out.print("\n"+parser.parseDocument().get(0).getListOfSubKeyWords().get(0).getMainKeyWord());
							//creatButtonsOfKeyWords(parser.parseDocument());
							//printAllOfKeyWords(parser.parseDocument(com));
							creatButtonsOfKeyWords(parser.parseDocument());
						}
					}
				}
			  }
		    }
			
//
		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
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
	private void creatButtonsOfKeyWords(final ArrayList<KeyWord> listOfMainKeyWords){
		//System.out.print("\n in creatButtonsOfKeyWords()");
		for(int i=0;i<listOfMainKeyWords.size();i++)
		{
			if((listOfMainKeyWords.get(i).getListOfSubKeyWords().size()>0)){
				creatButtonsOfKeyWords(listOfMainKeyWords.get(i).getListOfSubKeyWords());
			}
			final Button keyWordAsButton=new Button(myComp, SWT.NONE);
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
					
					//TODO: hier muss der befehle in der textBox eingefügt werden
					
//					fatherComp.getShell().getData("StyledText")
//					s.setStatementText("sdfsdf");
				}
			});
			
			buttonList.add(keyWordAsButton);

		}
		
	}
	
	private void setComp(Composite myComp2) {
		// TODO Auto-generated method stub
		com=myComp2;
	}

	/**
	 * the function for printing all of the founded 
	 * Keywords Object according to the logic xml file .
	 * 
	 * @param listOfMainKeyWords
	 */
	private void printAllOfKeyWords(ArrayList<KeyWord> listOfMainKeyWords){
		System.out.print("\n in creatButtonsOfKeyWords()");
		for(int i=0;i<listOfMainKeyWords.size();i++)
		{
			if((listOfMainKeyWords.get(i).getListOfSubKeyWords().size()>0)){
				printAllOfKeyWords(listOfMainKeyWords.get(i).getListOfSubKeyWords());
			}
			
		}
	}

	
	


}
