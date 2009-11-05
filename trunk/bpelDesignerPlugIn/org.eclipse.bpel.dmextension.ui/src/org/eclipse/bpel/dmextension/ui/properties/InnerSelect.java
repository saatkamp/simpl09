package org.eclipse.bpel.dmextension.ui.properties;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;

public class InnerSelect {

	private static Shell sShell = null; // @jve:decl-index=0:visual-constraint="10,10"
	private Label macroLabel = null;
	private Label microLabel = null;
	private Label dataSourceAddressLabel = null;
	private Text dataSourceAddressText = null;
	private CCombo microCombo = null;
	private CCombo macroCombo = null;
	private Button showStatementCheckBox = null;
	private Label statementLabel = null;
	private Text statementText = null;
	private String multiColumn = "";
	
	private Button discardButton = null;
	private Button okButton = null;

	public InnerSelect(Display display) {
		createSShell(display);
		sShell.open();
	}
	
	public InnerSelect() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell(Display display) {
		GridData gridData31 = new GridData();
		gridData31.widthHint = 60;
		gridData31.horizontalAlignment = GridData.END;
		GridData gridData4 = new GridData();
		gridData4.widthHint = 60;
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.horizontalAlignment = GridData.END;
		GridData gridData21 = new GridData();
		gridData21.horizontalSpan = 5;
		gridData21.verticalAlignment = GridData.CENTER;
		gridData21.horizontalAlignment = GridData.FILL;
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.widthHint = 100;
		gridData3.heightHint = -1;
		gridData3.verticalAlignment = GridData.CENTER;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.widthHint = 100;
		gridData2.verticalAlignment = GridData.CENTER;
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.CENTER;
		gridData1.horizontalSpan = 3;
		gridData1.horizontalAlignment = GridData.FILL;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 2;
		gridData.verticalAlignment = GridData.BEGINNING;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		
		sShell = new Shell(display, SWT.TITLE);
		sShell.setText("Pre SELECT data");
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(553, 208));
		macroLabel = new Label(sShell, SWT.NONE);
		macroLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_BACKGROUND));
		macroLabel.setText("Table:");
		macroCombo = new CCombo(sShell, SWT.BORDER);
		macroCombo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		macroCombo.setLayoutData(gridData3);
		macroCombo.setEditable(false);
		//Listener für die Änderung des Statement Textfelds registrieren
		macroCombo.addSelectionListener(statementChangeListener);
		
		//Beispiel Tabellen
		macroCombo.setItems(new String[]{"KUNDEN", "ADRESSEN", "..."});
		
		dataSourceAddressLabel = new Label(sShell, SWT.NONE);
		dataSourceAddressLabel.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		dataSourceAddressLabel.setText("Address of the data source:");
		dataSourceAddressText = new Text(sShell, SWT.BORDER);
		dataSourceAddressText.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		dataSourceAddressText.setLayoutData(gridData1);
		microLabel = new Label(sShell, SWT.NONE);
		microLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_BACKGROUND));
		microLabel.setText("Column:");
		microCombo = new CCombo(sShell, SWT.BORDER);
		microCombo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		microCombo.setLayoutData(gridData2);
		microCombo.setEditable(false);
		//Listener für die Änderung des Statement Textfelds registrieren
		microCombo.addSelectionListener(statementChangeListener);
		
		microCombo.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (microCombo.getItem(microCombo.getSelectionIndex()).contains("Multi column")){
					multiColumnButton.setVisible(true);
				}else {
					multiColumnButton.setVisible(false);
				}
			}});
		
		//Beispiel Spalten
		microCombo.setItems(new String[]{"ID", "NAME", "Multi column"});
		
		multiColumnButton = new Button(sShell, SWT.NONE);
		multiColumnButton.setVisible(false);
		multiColumnButton.setText("Choose multi columns");
		multiColumnButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				new MultiColumn();
			}});
		
		Label filler1 = new Label(sShell, SWT.NONE);
		Label filler18 = new Label(sShell, SWT.NONE);
		Label filler9 = new Label(sShell, SWT.NONE);
		showStatementCheckBox = new Button(sShell, SWT.CHECK);
		showStatementCheckBox.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		showStatementCheckBox.setLayoutData(gridData);
		showStatementCheckBox.setText("Show resulting statement");
		showStatementCheckBox.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (showStatementCheckBox.getSelection()) {
					statementLabel.setVisible(true);
					statementText.setVisible(true);
				} else {
					statementLabel.setVisible(false);
					statementText.setVisible(false);
				}
			}
		});
		Label filler3 = new Label(sShell, SWT.NONE);
		Label filler4 = new Label(sShell, SWT.NONE);
		Label filler17 = new Label(sShell, SWT.NONE);
		Label filler8 = new Label(sShell, SWT.NONE);
		statementLabel = new Label(sShell, SWT.NONE);
		statementLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_BACKGROUND));
		statementLabel.setText("Resulting statement:");
		statementLabel.setVisible(false);
		statementText = new Text(sShell, SWT.BORDER);
		statementText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		statementText.setLayoutData(gridData21);
		statementText.setVisible(false);
		Label filler5 = new Label(sShell, SWT.NONE);
		Label filler6 = new Label(sShell, SWT.NONE);
		Label filler15 = new Label(sShell, SWT.NONE);
		Label filler10 = new Label(sShell, SWT.NONE);
		discardButton = new Button(sShell, SWT.NONE);
		discardButton.setText("Discard");
		discardButton.setLayoutData(gridData4);
		discardButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				DataClass.getInstance().setInnerSelect("");
				DataClass.getInstance().processStatement();
				sShell.close();
			}});
		
		okButton = new Button(sShell, SWT.NONE);
		okButton.setText("OK");
		okButton.setLayoutData(gridData31);
		okButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				DataClass.getInstance().setInnerSelect("(" + processStatement() + ")");
				DataClass.getInstance().processStatement();
				sShell.close();
			}});
	}
	
	/**
	 * Processes the statement, which is formed out of the selected items in the combo boxes.
	 * @return The correct formed user selected statement.
	 */
	public String processStatement(){
		// Das statement setzt sich nach der Standard SQL-Select Syntax zusammen.
		String statement = "";
		if (microCombo.getSelectionIndex()!=-1 && microCombo.getItem(microCombo.getSelectionIndex()).contains("Multi column")){
			statement = "SELECT" + " " + multiColumn + " " + "FROM" + " " + macroCombo.getText();
		}else {
			statement = "SELECT" + " " + microCombo.getText() + " " + "FROM" + " " + macroCombo.getText();
		}
		return statement;
	}
	
	/**
	 * This variable is used to register the same SelectionListener on 
	 * all combo boxes. The listener itself is used to detect changes for the
	 * resulting statement and for updating it. 
	 */
	private SelectionListener statementChangeListener = new SelectionListener(){
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			statementText.setText(processStatement());
		}
	};  //  @jve:decl-index=0:
	private Button multiColumnButton = null;
}
