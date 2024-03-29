/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Firas Zoabi <zoabifs@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.widgets;


import java.util.ArrayList;

import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Table;


// TODO: Auto-generated Javadoc
/**
 * The Class ElementsListPopUp.
 */
public class TablsListPopUp{
	
	
	ArrayList<DBTable> listOfTables;
	/** The text to search. */
	Text textToSearch;
	
	/** The list to search. */
	List listToSearch,listColumns;
	
	/** The array of elements. */
	ArrayList<String> arrayOfElements=new ArrayList<String>();
	
	ArrayList<DBTable> listOfTableObjekts;
	/** The window is open. */
	boolean windowIsOpen=false;
	
	/**
	 * Checks if is window open.
	 * 
	 * @return true, if is window open
	 */
	public boolean isWindowOpen() {
		return windowIsOpen;
	}

	/**
	 * Sets the window is open.
	 * 
	 * @param windowIsOpen
	 *            the new window is open
	 */
	public void setWindowIsOpen(boolean windowIsOpen) {
		this.windowIsOpen = windowIsOpen;
	}

	private Shell theShell;// =new Shell();
	
	/**
	 * Instantiates a new elements list pop up.
	 * 
	 * @param statementText
	 *            the statement text
	 */
	public TablsListPopUp(LiveEditStyleText statementText)
	{
		createSShell(statementText);
//		theShell.setSize(new Point(282, 184));
//		GridLayout gridLayout = new GridLayout();
//		gridLayout.numColumns = 1;
//		//theShell.setLayout(gridLayout);
//		
//		GridData gridData1 = new GridData();
//		gridData1.grabExcessHorizontalSpace = true;
//		gridData1.grabExcessVerticalSpace = false;
//		GridData gridData = new GridData();
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.grabExcessVerticalSpace = true;
//		
//		
//		
//		Text textToSearch = new Text(theShell, SWT.BORDER);
//		textToSearch.setLayoutData(gridData1);
//		List listToSearch = new List(theShell, SWT.NONE);
//		listToSearch.setLayoutData(gridData);
//		
		
		
		
	    
	}
	
	private void createSShell(final LiveEditStyleText statementText) {
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = false;
		gridData1.horizontalAlignment = GridData.FILL;
		//gridData1.verticalAlignment = GridData.CENTER;
		gridData1.grabExcessVerticalSpace = true;
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		
		GridData gridData23 = new GridData();
		gridData23.horizontalAlignment = GridData.FILL;
		gridData23.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		theShell = new Shell(Display.getDefault());
		theShell.setText("Data-Management-Activity properties");
		//theShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		theShell.setLayout(gridLayout);
		theShell.setSize(new Point(320, 254));
		
		
		
		textToSearch = new Text(theShell, SWT.BORDER);
		textToSearch.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
//				System.out.print(textToSearch.getText());
				searchListForResults(textToSearch.getText());
			}

		
			
		});	
		Button addToStatement=new Button(theShell, SWT.NONE);
		addToStatement.setText("Insert to statement");
		addToStatement.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				statementText.append(" "+listToSearch.getItems()[listToSearch.getSelectionIndex()]);	
				for(int i=0;i<listColumns.getSelectionCount();i++){
					statementText.append(" "+listColumns.getSelection()[i]);	
				}
				
				statementText.append(" "+listToSearch.getItems()[listToSearch.getSelectionIndex()]);	
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		//textToSearch.setLayoutData(gridData);
		
		listToSearch = new List(theShell, SWT.BORDER|SWT.V_SCROLL);
		//listToSearch.setItems((String[]) arrayOfElements.toArray());
		listToSearch.setLayoutData(gridData);
		listToSearch.addListener(SWT.MouseDoubleClick, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				statementText.append(" "+listToSearch.getItems()[listToSearch.getSelectionIndex()]);
				
				
			}
		});
		
		listToSearch.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadColumsOfTable(listToSearch.getItems()[listToSearch.getSelectionIndex()]);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		});
		
		
		
		listColumns = new List(theShell, SWT.BORDER|SWT.MULTI);
		listColumns.setLayoutData(gridData);
		
		listColumns.addListener(SWT.MouseDoubleClick, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				statementText.append(" "+listColumns.getItems()[listColumns.getSelectionIndex()]);
			}
		});
	
	}
	
//	private void setTextInStyleText(String string) {
//		// TODO Auto-generated method stub
//		
//	}
	
	/**
	 * add the colums of the spezified table in the colums-liste
	 */
	private void loadColumsOfTable(String searchedString) {
		DBTable tmpTableObjekt=null;
		listColumns.removeAll();
		for(int i=0;i<listOfTableObjekts.size();i++){
			
			tmpTableObjekt=listOfTableObjekts.get(i);
			if(tmpTableObjekt.getTableName().equals(searchedString)){
				for(int j=0;j<tmpTableObjekt.getListOfColumnsNames().size();j++){
					listColumns.add(tmpTableObjekt.getListOfColumnsNames().get(j));
					//listColumns.setItems((String[]) tmpTableObjekt.getListOfColumnsNames().toArray());
				}
			}
			//listColumns.add(tmpTableObjekt.);
		}
		
	}
	
	
	/**
	 * search live for results in the list
	 * @param text
	 */
	private void searchListForResults(String text) {
		//arrayOfElements= listToSearch.getItems();
		listToSearch.removeAll();
		for(int i=0;i<arrayOfElements.size();i++){
			if(arrayOfElements.get(i).toLowerCase().contains(text.toLowerCase())){
				listToSearch.add(arrayOfElements.get(i));
			}
		}
	}
	
	
	/**
	 * Open window.
	 */
	public void openWindow(){
		theShell.open();
	}
	
	/**
	 * Close window.
	 */
	public void closeWindow(){
		theShell.close();
	}
	
	/**
	 * Sets the text.
	 * 
	 * @param string
	 *            the new text
	 */
	public void setText(String string) {
		theShell.setText(string);
	}
	
	/**
	 * Load values from db.
	 */
	public void loadValuesFromDB(){
		
		
		arrayOfElements.add("aaaaa");
		arrayOfElements.add("abbb");
		arrayOfElements.add("bbbba");
		arrayOfElements.add("aaccc");
		arrayOfElements.add("cccbbb");
		arrayOfElements.add("ddccbbc");
		arrayOfElements.add("aa");
		arrayOfElements.add("sdfsdf");
		arrayOfElements.add("zzzzzz");
		arrayOfElements.add("aaa");
	}
//	public void setSize(int width,int height) {
//		theShell.setSize(width, height);
//	}

	/**
 * for adding the tables names from the DB.
 */
	public void loadTablesFromDB(DataSource dataSource) {
		
		MetaDataXMLParser metaDataXMLParser_Objekt=new MetaDataXMLParser();
		ArrayList<DBTable> listOfTables= metaDataXMLParser_Objekt.loadTablesFromDB(dataSource);
		listOfTableObjekts=listOfTables;
		
		for(int i=0;i<listOfTables.size();i++){
			arrayOfElements.add(listOfTableObjekts.get(i).getTableName());
			listToSearch.add(listOfTableObjekts.get(i).getTableName());
		}
		
//		arrayOfElements.add("aaaaa");
//		arrayOfElements.add("abbb");
//		arrayOfElements.add("bbbba");
//		arrayOfElements.add("aaccc");
//		arrayOfElements.add("cccbbb");
//		arrayOfElements.add("ddccbbc");
//		arrayOfElements.add("aa");
//		arrayOfElements.add("sdfsdf");
//		arrayOfElements.add("zzzzzz");
//		arrayOfElements.add("aaa");
	}
	
	/**
	 * for inserting the Bpel-Variables into the List.
	 */
	public void loadBPELVariables(java.util.List<String> listOfBPELVariablesAsStrings) {
		/*
		 * Hallo,
		du kannst jetzt in den PropertySections mit 
		 *VariableUtils.getUseableVariables(getProcess()) *eine Liste mit allen 
		Variablennamen, die entweder einen SimpleType (string, boolean, ...) 
		oder den ContainerReferenceType (schema+tabelle) haben, abrufen.
		Die Namen haben dabei schon die Notation, die wir vorher besprochen 
		haben, also alle SimpleType Variablennamen sind umgeben von # (z.B. 
		#simpleVariable#) und alle ContainerReferenceType Variablennamen sind 
		umgeben von [] (z.B. [containerRefType] ).

		Gru�,
		Michael
		 */
		
//		arrayOfElements.add("BPEL_Variable1");
//		arrayOfElements.add("BPEL_Variable2");
//		arrayOfElements.add("BPEL_Variable3");
//		arrayOfElements.add("BPEL_Variable4");
//		arrayOfElements.add("BPEL_Variable5");
//		arrayOfElements.add("BPEL_Variable6");
//		arrayOfElements.add("BPEL_Variable7");
//		arrayOfElements.add("BPEL_Variable8");
		
	}
	
	public ArrayList<String> parseXMLElements(){
		
		
		
		return arrayOfElements;
		
	}
	
	/**
	 * 
	 * @param findDataSourceByName
	 * @return
	 */
	public ArrayList<DBTable> getTablesFromDB(DataSource findDataSourceByName) {
		MetaDataXMLParser metaDataXMLParser_Objekt=new MetaDataXMLParser();
		ArrayList<DBTable> listOfTables= metaDataXMLParser_Objekt.loadTablesFromDB(findDataSourceByName);
		
		return listOfTables;
	}
	
	/**
	 * 
	 */
}
