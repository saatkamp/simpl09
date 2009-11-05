package org.eclipse.bpel.dmextension.ui.properties;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;

public class MultiColumn {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private List columnList = null;
	private Button okButton = null;
	private String multiColumn = "";  //  @jve:decl-index=0:

	public MultiColumn(Display display) {
		createSShell(display);
		sShell.open();
	}

	public MultiColumn() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method initializes sShell
	 */
	private void createSShell(Display display) {
		
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.END;
		gridData1.widthHint = 60;
		gridData1.verticalAlignment = GridData.CENTER;
		GridData gridData = new GridData();
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.FILL;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		sShell = new Shell(display, SWT.TITLE);
		sShell.setText("Multi column selection");
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(147, 276));
		columnList = new List(sShell, SWT.BORDER | SWT.H_SCROLL | SWT.MULTI | SWT.V_SCROLL);
		columnList.setToolTipText("Choose the columns for the statement");
		columnList.setLayoutData(gridData);
		
		//Beispiel Daten
		columnList.setItems(new String[]{"ID" , "NAME" , "PHONE" , "STREET", "ZIP"});
		
		columnList.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				String[] columns = columnList.getSelection();
				multiColumn = "";
				for (String column : columns){
					multiColumn += column + ", ";
				}
				multiColumn = multiColumn.substring(0, multiColumn.lastIndexOf(','));
			}});
		
		Label filler1 = new Label(sShell, SWT.NONE);
		Label filler = new Label(sShell, SWT.NONE);
		okButton = new Button(sShell, SWT.NONE);
		okButton.setText("OK");
		okButton.setLayoutData(gridData1);
		okButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				DataClass.getInstance().setMultiColumn(multiColumn);
				DataClass.getInstance().processStatement();
				sShell.close();
			}});
	}
}
