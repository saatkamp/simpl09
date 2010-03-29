package widgets;


import java.util.ArrayList;

import org.eclipse.bpel.model.Exit;
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
import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.communication.SIMPLCore;

public class ElementsListPopUp{
	Text textToSearch;
	List listToSearch;
	ArrayList<String> arrayOfElements=new ArrayList<String>();
	boolean windowIsOpen=false;
	
	public boolean isWindowOpen() {
		return windowIsOpen;
	}

	public void setWindowIsOpen(boolean windowIsOpen) {
		this.windowIsOpen = windowIsOpen;
	}

	private Shell theShell;// =new Shell();
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
	
	
	public void openWindow(){
		theShell.open();
	}
	
	public void closeWindow(){
		theShell.close();
	}
	
	public void setText(String string) {
		theShell.setText(string);
	}
	
	/**
	 * 
	 */
	public void loadValuesFromDB(){
		SIMPLCore simplCore = SIMPLCommunication.getConnection();
		
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
	 * for adding the tables names from the DB
	 */
	public void loadTablesFromDB() {
		// TODO Auto-generated method stub
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
	 * for inserting the Bpel-Variables into the List
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
	
	/**
	 * 
	 */
}
