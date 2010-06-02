/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Firas Zoabi <zoabifs@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: ElementsListPopUp.java 1202 2010-04-27 16:31:01Z hahnml@t-online.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.widgets;


import java.util.ArrayList;

import org.eclipse.bpel.simpl.ui.properties.util.VariableUtils;
import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.communication.SIMPLCore;
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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



// TODO: Auto-generated Javadoc
/**
 * The Class ElementsListPopUp.
 */
public class ParametersListPopUp{
	
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
	public ParametersListPopUp(LiveEditStyleText statementText)
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
		gridLayout.numColumns = 1;
		theShell = new Shell(Display.getDefault());
		theShell.setText("Data-Management-Activity properties");
		//theShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		theShell.setLayout(gridLayout);
		theShell.setSize(new Point(320, 254));
		
		
		
		textToSearch = new Text(theShell, SWT.BORDER);
		textToSearch.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				System.out.print(textToSearch.getText());
				searchListForResults(textToSearch.getText());
			}		
		});	
		
		Button addToStatement=new Button(theShell, SWT.NONE);
		addToStatement.setText("Insert to statement");
		addToStatement.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				statementText.append(" "+listToSearch.getItems()[listToSearch.getSelectionIndex()]);
				//loadColumsOfTable(listToSearch.getItems()[listToSearch.getSelectionIndex()]);
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statementText.append(" "+listToSearch.getItems()[listToSearch.getSelectionIndex()]);
				//loadColumsOfTable(listToSearch.getItems()[listToSearch.getSelectionIndex()]);
			}
		});
		
		textToSearch.setLayoutData(gridData1);
		
		listToSearch = new List(theShell, SWT.BORDER);
		//listToSearch.setItems((String[]) arrayOfElements.toArray());
		listToSearch.setLayoutData(gridData);
		listToSearch.addListener(SWT.MouseDoubleClick, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				statementText.append(" "+listToSearch.getItems()[listToSearch.getSelectionIndex()]);
			}
		});
		
//		listColumns = new List(theShell, SWT.BORDER);
//		listColumns.setLayoutData(gridData);
//		listColumns.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				statementText.append(listColumns.getItems()[listColumns.getSelectionIndex()]);
//				
//			}
//			
//			
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				statementText.append(listColumns.getItems()[listColumns.getSelectionIndex()]);
//				
//			}
//		});
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
				listColumns.setItems((String[]) tmpTableObjekt.getListOfColumnsNames().toArray());
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
			if(arrayOfElements.get(i).toLowerCase().contains(text)){
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
		
		for(int i=0;i<listOfTables.size();i++){
			arrayOfElements.add(listOfTables.get(i).getTableName());
		}
		
		
	}
	
	/**
	 * for inserting the Bpel-Variables into the List.
	 * @param listOfBPELVariablesAsStrings 
	 */
	public void loadBPELVariables(java.util.List<String> listOfBPELVariablesAsStrings) {
		
		for(int i=0;i<listOfBPELVariablesAsStrings.size();i++){
			arrayOfElements.add(listOfBPELVariablesAsStrings.get(i));
			listToSearch.add(listOfBPELVariablesAsStrings.get(i));
		}
		
		//******zum testen*******
//		arrayOfElements.add("BPEL_Variable1");
//		arrayOfElements.add("BPEL_Variable2");
//		arrayOfElements.add("BPEL_Variable3");
//		arrayOfElements.add("BPEL_Variable4");
//		arrayOfElements.add("BPEL_Variable5");
//		arrayOfElements.add("BPEL_Variable6");
//		arrayOfElements.add("BPEL_Variable7");
//		arrayOfElements.add("BPEL_Variable8");
		//**********************
	}
	
	public ArrayList<String> parseXMLElements(){
		
		
		
		return arrayOfElements;
		
	}
	
	/**
	 * 
	 */
}
