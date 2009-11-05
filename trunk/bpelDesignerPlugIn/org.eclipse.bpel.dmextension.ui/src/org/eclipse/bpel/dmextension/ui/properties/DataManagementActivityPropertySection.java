package org.eclipse.bpel.dmextension.ui.properties;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.bpel.ui.properties.BPELPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DataManagementActivityPropertySection extends BPELPropertySection implements Observer{

	private Label typeLabel = null;
	private CCombo typeCombo = null;
	private Label macroLabel = null;
	private Label microLabel = null;
	private Label statementLabel = null;
	private CCombo macroCombo = null;
	private CCombo microCombo = null;
	private Text statementText = null;
	private Button showStatementCheckBox = null;
	private Label dataSourceAddressLabel = null;
	private Text dataSourceAddressText = null;
	private Label kindLabel = null;
	private CCombo kindCombo = null;
	private Display display = null;
	private Composite parentComposite = null;
	
	private DataClass data;
	
	public DataManagementActivityPropertySection(){
		data = DataClass.getInstance();
		data.addObserver(this);
		System.out.println("asdasd");
	}

	
	/**
	 * Make this section use all the vertical space it can get.
	 * 
	 */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	@Override
	protected void createClient(Composite parent) {
		createWidgets(parent);
	}

	private void createWidgets(Composite composite) {
		this.parentComposite = composite;
		GridData gridData61 = new GridData();
		gridData61.widthHint = 150;
		GridData gridData5 = new GridData();
		gridData5.widthHint = 150;
		GridData gridData51 = new GridData();
		gridData51.horizontalAlignment = GridData.FILL;
		gridData51.verticalAlignment = GridData.CENTER;
		GridData gridData41 = new GridData();
		gridData41.horizontalAlignment = GridData.FILL;
		gridData41.verticalAlignment = GridData.CENTER;
		GridData gridData31 = new GridData();
		gridData31.grabExcessHorizontalSpace = true;
		GridData gridData21 = new GridData();
		gridData21.grabExcessHorizontalSpace = true;
		gridData21.verticalAlignment = GridData.CENTER;
		gridData21.horizontalAlignment = GridData.FILL;
		GridData gridData12 = new GridData();
		gridData12.grabExcessHorizontalSpace = true;
		gridData12.verticalAlignment = GridData.CENTER;
		gridData12.horizontalAlignment = GridData.FILL;
		GridData gridData11 = new GridData();
		gridData11.horizontalAlignment = GridData.FILL;
		gridData11.grabExcessHorizontalSpace = false;
		gridData11.horizontalIndent = 1;
		gridData11.heightHint = -1;
		gridData11.horizontalSpan = 2;
		gridData11.grabExcessVerticalSpace = true;
		gridData11.verticalAlignment = GridData.CENTER;
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = false;
		gridData1.verticalAlignment = GridData.END;
		gridData1.horizontalSpan = 3;
		gridData1.horizontalAlignment = GridData.FILL;
		GridData gridData = new GridData();
		gridData.heightHint = -1;
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.END;
		gridData.horizontalIndent = 0;
		gridData.grabExcessVerticalSpace = true;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		parentComposite.setLayout(gridLayout);
		parentComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		parentComposite.setSize(new Point(582, 294));
		typeLabel = new Label(parentComposite, SWT.NONE);
		typeLabel.setText("Type of data source:");
		typeLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		typeLabel.setLayoutData(gridData51);
		createTypeCombo();
		Label filler2 = new Label(parentComposite, SWT.NONE);
		Label filler5 = new Label(parentComposite, SWT.NONE);
		kindLabel = new Label(parentComposite, SWT.NONE);
		kindLabel.setText("Kind of data source:");
		kindLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		createKindCombo();
		dataSourceAddressLabel = new Label(parentComposite, SWT.NONE);
		dataSourceAddressText = new Text(parentComposite, SWT.BORDER);
		dataSourceAddressText.setLayoutData(gridData12);
		macroLabel = new Label(parentComposite, SWT.NONE);
		macroLabel.setText("Table:");
		macroLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		macroLabel.setLayoutData(gridData41);
		createMacroCombo();
		dataSourceAddressLabel.setText("Address of the data source:");
		dataSourceAddressLabel.setLayoutData(gridData31);
		dataSourceAddressLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		preSelectButton = new Button(parentComposite, SWT.NONE);
		preSelectButton.setText("Specify pre-select statement");
		preSelectButton.setToolTipText("Click to set a pre-select to generate a table of data to work with");
		preSelectButton.setLayoutData(gridData5);
		preSelectButton.setVisible(false);
		preSelectButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				new InnerSelect(display);
			}});
		Label filler41 = new Label(parentComposite, SWT.NONE);
		microLabel = new Label(parentComposite, SWT.NONE);
		microLabel.setText("Column:");
		microLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		microLabel.setLayoutData(gridData21);
		createMicroCombo();
		multiColumnButton = new Button(parentComposite, SWT.NONE);
		multiColumnButton.setText("Choose multi columns");
		multiColumnButton.setLayoutData(gridData61);
		multiColumnButton.setVisible(false);
		multiColumnButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				new MultiColumn(display);
			}});
		Label filler3 = new Label(parentComposite, SWT.NONE);
		showStatementCheckBox = new Button(parentComposite, SWT.CHECK);
		showStatementCheckBox.setText("Show resulting statement");
		showStatementCheckBox.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		showStatementCheckBox.setLayoutData(gridData11);
		Label filler6 = new Label(parentComposite, SWT.NONE);
		showStatementCheckBox.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (showStatementCheckBox.getSelection()){
					statementLabel.setVisible(true);
					statementText.setVisible(true);
				}else {
					statementLabel.setVisible(false);
					statementText.setVisible(false);
				}
			}});
		Label filler1 = new Label(parentComposite, SWT.NONE);
		statementLabel = new Label(parentComposite, SWT.NONE);
		statementLabel.setText("Resulting statement:");
		statementLabel.setVisible(false);
		statementLabel.setLayoutData(gridData);
		statementLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		statementText = new Text(parentComposite, SWT.BORDER);
		statementText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		statementText.setVisible(false);
		statementText.setLayoutData(gridData1);
	}

	

	/**
	 * This method initializes typeCombo	
	 *
	 */
	private void createTypeCombo() {
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.CENTER;
		typeCombo = new CCombo(parentComposite, SWT.BORDER);
		typeCombo.setToolTipText("Choose the type of data source");
		typeCombo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		typeCombo.setLayoutData(gridData2);
		// In globale Konstanten-Klasse Werte definieren und dann hier referenzieren.
		typeCombo.setItems(Constants.getDataSourceTypes());
		// Listener für die automatische Änderung des Dialog-Inhalts anlegen
		typeCombo.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				// Abfrage, ob "file system" ausgewählt wurde
				
				switch (typeCombo.getSelectionIndex()){
					case 0: //Dateisysteme
						//Ändern der Labels
						macroLabel.setText("Folder:");
						macroLabel.setToolTipText("Choose a folder of the file system");
						microLabel.setText("File:");
						microLabel.setToolTipText("Choose a file of the selected folder");
						kindCombo.setItems(Constants.getFileSystemKinds());
						
						//Beispiel Dateien
						//Beispiele für Spalten, Dateien, ...
						macroCombo.setItems(new String[]{"data", "root", "..."});
						microCombo.setItems(new String[]{"sim_daten.csv", "parameter.xml", "..."});
						
						break;
					case 1: //Datenbanken
						//Ändern der Labels
						macroLabel.setText("Table:");
						macroLabel.setToolTipText("Choose a table of the database");
						microLabel.setText("Column:");
						microLabel.setToolTipText("Choose a column of the selected table");
						kindCombo.setItems(Constants.getDatabasekinds());
						
						//Beispiel Tabellen, der Eintrag "Use SELECT" wird später standardmäßig dabei sein
						//um ein Pre-Select ausführen zu können, dessen Ergebnis dann als Quelltabelle verwendet werden kann.
						macroCombo.setItems(new String[]{"KUNDEN", "...", "Use SELECT"});
						//Beispiele für Spalten, Dateien, ...
						microCombo.setItems(new String[]{"*", "ID", "NAME", "Multi column"});
						
						break;
					case 2: //Sensornetze
						//Ändern der Labels
						macroLabel.setText("Table:");
						macroLabel.setToolTipText("Choose a table of the sensor net database");
						microLabel.setText("Attribute:");
						microLabel.setToolTipText("Choose an attribute of the selected table");
						kindCombo.setItems(Constants.getSensornetkinds());
						
						//Beispiel Tabellen, der Eintrag "Use SELECT" wird später standardmäßig dabei sein
						//um ein Pre-Select ausführen zu können, dessen Ergebnis dann als Quelltabelle verwendet werden kann.
						//Hier bleibt ebenfalls "sensors" dabei, da dass die Standardtabelle des Systems ist!
						//Testen ob hier ein pre-select verwendet werden kann
						macroCombo.setItems(new String[]{"sensors", "buffer_temperature", "Use SELECT"});
						//Beispiele für Spalten, Dateien, ...
						microCombo.setItems(new String[]{"ampere", "volt", "temp", "Multi column"});
						
						break;
					default:
						break;
				}
			}});
		//Listener für die Änderung des Statement Textfelds registrieren
		typeCombo.addSelectionListener(statementChangeListener);
		typeCombo.setEditable(false);
	}

	/**
	 * This method initializes macroCombo	
	 *
	 */
	private void createMacroCombo() {
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.verticalAlignment = GridData.CENTER;
		macroCombo = new CCombo(parentComposite, SWT.BORDER);
		macroCombo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		macroCombo.setLayoutData(gridData3);
		macroCombo.setEditable(false);
		//Listener für die Änderung des Statement Textfelds registrieren
		macroCombo.addSelectionListener(statementChangeListener);
		
		macroCombo.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (macroCombo.getItem(macroCombo.getSelectionIndex()).contains("Use SELECT")){
					preSelectButton.setVisible(true);
				}else {
					preSelectButton.setVisible(false);
				}
			}});
	}

	/**
	 * This method initializes microCombo	
	 *
	 */
	private void createMicroCombo() {
		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = GridData.FILL;
		gridData4.widthHint = -1;
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.verticalAlignment = GridData.CENTER;
		microCombo = new CCombo(parentComposite, SWT.BORDER);
		microCombo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		microCombo.setLayoutData(gridData4);
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
	}


	/**
	 * This method initializes kindCombo	
	 *
	 */
	private void createKindCombo() {
		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = GridData.FILL;
		gridData6.verticalAlignment = GridData.CENTER;
		kindCombo = new CCombo(parentComposite, SWT.BORDER);
		kindCombo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		kindCombo.setToolTipText("Choose the kind of data source");
		kindCombo.setEditable(false);
		kindCombo.setLayoutData(gridData6);
		
	}
	
	/**
	 * Processes the statement, which is formed out of the selected items in the combo boxes.
	 * @return The correct formed user selected statement.
	 */
	public void processStatement(){
		String statement = "";
		DataClass.getInstance().processStatement();
		statement = DataClass.getInstance().getStatement();
//		if (macroCombo.getSelectionIndex()!=-1 && macroCombo.getItem(macroCombo.getSelectionIndex()).contains("Use SELECT")){
//			if (microCombo.getSelectionIndex()!=-1 && microCombo.getItem(microCombo.getSelectionIndex()).contains("Multi column")){
//				statement = "SELECT" + " " + multiColumn + " " + "FROM" + " " + preSelect;
//			}else {
//				statement = "SELECT" + " " + microCombo.getText() + " " + "FROM" + " " + preSelect;
//			}
//		}else {
//			if (microCombo.getSelectionIndex()!=-1 && microCombo.getItem(microCombo.getSelectionIndex()).contains("Multi column")){
//				statement = "SELECT" + " " + multiColumn + " " + "FROM" + " " + macroCombo.getText();
//			}else {
//				statement = "SELECT" + " " + microCombo.getText() + " " + "FROM" + " " + macroCombo.getText();
//			}
//		}
		statementText.setText(statement);
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
			if (macroCombo.getSelectionIndex()!=-1){
				DataClass.getInstance().setMacroSource(macroCombo.getItem(macroCombo.getSelectionIndex()));
			}
			if (microCombo.getSelectionIndex()!=-1){
				DataClass.getInstance().setMicroSource(microCombo.getItem(microCombo.getSelectionIndex()));
			}
			processStatement();
		}
	};  //  @jve:decl-index=0:
	private Button preSelectButton = null;
	private Button multiColumnButton = null;

	@Override
	public void update(Observable object, Object arg) {
		// TODO Auto-generated method stub
		if ((object == data) && "statement".equals(arg)){
			processStatement();
		}
	}
}
