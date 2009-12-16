package org.eclipse.bpel.simpl.ui.properties;

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

/**
 * A BPELPropertySection for SIMPL Data-Management-Activities.
 * 
 * @author hahnml
 *
 */
public class DMActivityPropertySection extends BPELPropertySection{

	private Label typeLabel = null;
	private CCombo typeCombo = null;
	private Label statementLabel = null;
	private Text statementText = null;
	private Button showStatementCheckBox = null;
	private Label dataSourceAddressLabel = null;
	private Text dataSourceAddressText = null;
	private Label kindLabel = null;
	private CCombo kindCombo = null;
	private Button openEditorButton = null;
	private Composite parentComposite = null;

	
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

	/**
	 * Creates the property section.
	 * 
	 * @param composite to put the content in.
	 */
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
		typeLabel = new Label(composite, SWT.NONE);
		typeLabel.setText("Type of data source:");
		typeLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		typeLabel.setLayoutData(gridData51);
		createTypeCombo();
		Label filler2 = new Label(composite, SWT.NONE);
		Label filler5 = new Label(composite, SWT.NONE);
		kindLabel = new Label(composite, SWT.NONE);
		kindLabel.setText("Subtype of data source:");
		kindLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		createKindCombo();
		dataSourceAddressLabel = new Label(composite, SWT.NONE);
		dataSourceAddressText = new Text(composite, SWT.BORDER);
		dataSourceAddressText.setLayoutData(gridData12);
		Label filler4 = new Label(composite, SWT.NONE);
		dataSourceAddressLabel.setText("Address of the data source:");
		dataSourceAddressLabel.setLayoutData(gridData31);
		dataSourceAddressLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		openEditorButton = new Button(composite, SWT.NONE);
		openEditorButton.setText("Open Editor");
		openEditorButton.setLayoutData(gridData21);
		openEditorButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				openStatementEditor();
			}});
		
		Label filler3 = new Label(composite, SWT.NONE);
		Label filler41 = new Label(composite, SWT.NONE);
		showStatementCheckBox = new Button(composite, SWT.CHECK);
		showStatementCheckBox.setText("Show resulting statement");
		showStatementCheckBox.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		showStatementCheckBox.setLayoutData(gridData11);
		Label filler6 = new Label(composite, SWT.NONE);
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
		Label filler1 = new Label(composite, SWT.NONE);
		statementLabel = new Label(composite, SWT.NONE);
		statementLabel.setText("Resulting statement:");
		statementLabel.setVisible(false);
		statementLabel.setLayoutData(gridData);
		statementLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		statementText = new Text(composite, SWT.BORDER);
		statementText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		statementText.setVisible(false);
		statementText.setLayoutData(gridData1);
		statementText.setEditable(false);
		
		//TODO: Statement-Wert aus Modell lesen und in Textfeld schreiben
		
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
		//Besser wäre die Typen direkt aus dem SIMPL Core anzufragen !!!
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
						kindCombo.setItems(Constants.getFileSystemKinds());
						
						break;
					case 1: //Datenbanken
						//Ändern der Labels
						kindCombo.setItems(Constants.getDatabasekinds());
			
						break;
					case 2: //Sensornetze
						kindCombo.setItems(Constants.getSensornetkinds());
				
						break;
					default:
						break;
				}
			}});
		typeCombo.setEditable(false);
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
		kindCombo.setToolTipText("Choose the subtype of data source");
		kindCombo.setEditable(false);
		kindCombo.setLayoutData(gridData6);
		
	}
	
	/**
	 * Opens a statement editor shell.
	 */
	private void openStatementEditor(){
		//dynamisch machen, language resultiert aus Datenquellenauswahl und die Aktivität
		// muss irgendwie festgestellt werden
		System.out.println("OBJECT: " + getBPELEditor().getSelection().toString());
		new StatementEditor(this, "SQL", getBPELEditor().getSelection().toString());
	}
	
	public void setStatement(String statement){
		statementText.setText(statement);
	}
	
	public String getStatement(){
		return statementText.getText();
	}
}
