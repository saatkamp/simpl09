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
package widgets;


import java.util.ArrayList;

import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.communication.SIMPLCore;
import org.eclipse.simpl.communication.client.ConnectionException_Exception;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

// TODO: Auto-generated Javadoc
/**
 * The Class ElementsListPopUp.
 */
public class ElementsListPopUp{
	
	/** The text to search. */
	Text textToSearch;
	
	/** The list to search. */
	List listToSearch;
	
	/** The array of elements. */
	ArrayList<String> arrayOfElements=new ArrayList<String>();
	
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
	public ElementsListPopUp(LiveEditStyleText statementText)
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
		
		textToSearch.setLayoutData(gridData1);
		listToSearch = new List(theShell, SWT.BORDER);
		listToSearch.setLayoutData(gridData);
		listToSearch.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				statementText.append(listToSearch.getItems()[listToSearch.getSelectionIndex()]);
				
			}
			
			

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statementText.append(listToSearch.getItems()[listToSearch.getSelectionIndex()]);
				
			}
		});
		
	}
	
//	private void setTextInStyleText(String string) {
//		// TODO Auto-generated method stub
//		
//	}
	
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
	public void loadTablesFromDB(String dsAddress,String dsType,String dsSubtype) {
		
		
		SIMPLCore simplCore=SIMPLCommunication.getConnection();
		try {
			simplCore.getMetaData(dsAddress, dsType, dsSubtype);
			//TODO: es muss noch der SDO objekt von der simplCore geholt werden .
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ArrayList<String> tablesInDB=new ArrayList<String>();
		
		
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
	
	/**
	 * for inserting the Bpel-Variables into the List.
	 */
	public void loadBPELVariables() {
		arrayOfElements.add("BPEL_Variable1");
		arrayOfElements.add("BPEL_Variable2");
		arrayOfElements.add("BPEL_Variable3");
		arrayOfElements.add("BPEL_Variable4");
		arrayOfElements.add("BPEL_Variable5");
		arrayOfElements.add("BPEL_Variable6");
		arrayOfElements.add("BPEL_Variable7");
		arrayOfElements.add("BPEL_Variable8");
		
	}
	
	public ArrayList<String> parseXMLElements(){
		
		
		
		return arrayOfElements;
		
	}
	
	/**
	 * 
	 */
}
