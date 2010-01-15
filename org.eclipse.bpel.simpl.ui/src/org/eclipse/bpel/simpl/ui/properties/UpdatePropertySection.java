package org.eclipse.bpel.simpl.ui.properties;

import java.util.List;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.UpdateActivity;
import org.eclipse.bpel.simpl.ui.command.SetDsAddressCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsKindCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsStatementCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsTypeCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

public class UpdatePropertySection extends DMActivityPropertySection {

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
	private Label languageLabel = null;
	private CCombo languageCombo = null;
	private Composite parentComposite = null;
	private String language = null;

	private UpdateActivity activity;
	
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
		//Setzen die im Editor ausgewählte Aktivität als Input.
		setInput(getPart(), getBPELEditor().getSelection());
		//Laden der Aktivität
		this.activity = getModel();
		
		createWidgets(parent);
		
		//Setzen das Statement
		setStatement(activity.getDsStatement());
		//Setzen die Datenquellenadresse
		dataSourceAddressText.setText(activity.getDsAddress());
		
		//Type und Kind werden in den entsprechenden createXXXCombo()-Methoden
		//geladen und in den ComboBoxes selektiert.
	}

	/**
	 * Creates the property section.
	 * 
	 * @param composite
	 *            to put the content in.
	 */
	private void createWidgets(Composite composite) {
		this.parentComposite = composite;
		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = GridData.FILL;
		gridData4.verticalAlignment = GridData.CENTER;
		GridData gridData21 = new GridData();
		gridData21.horizontalAlignment = GridData.FILL;
		gridData21.verticalAlignment = GridData.CENTER;
		GridData gridData51 = new GridData();
		gridData51.horizontalAlignment = GridData.FILL;
		gridData51.verticalAlignment = GridData.CENTER;
		GridData gridData31 = new GridData();
		gridData31.grabExcessHorizontalSpace = true;
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
		parentComposite.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		parentComposite.setSize(new Point(582, 294));
		typeLabel = new Label(composite, SWT.NONE);
		typeLabel.setText("Type of data source:");
		typeLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		typeLabel.setLayoutData(gridData51);
		createTypeCombo();
		Label filler2 = new Label(composite, SWT.NONE);
		Label filler5 = new Label(composite, SWT.NONE);
		kindLabel = new Label(composite, SWT.NONE);
		kindLabel.setText("Subtype of data source:");
		kindLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		createKindCombo();
		dataSourceAddressLabel = new Label(composite, SWT.NONE);
		dataSourceAddressText = new Text(composite, SWT.BORDER);
		dataSourceAddressText.setLayoutData(gridData12);
		//Änderungen im Modell speichern
		dataSourceAddressText.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				getCommandFramework().execute(
						new SetDsAddressCommand(getModel(), dataSourceAddressText.getText()));
			}});
		dataSourceAddressLabel.setText("Address of the data source:");
		dataSourceAddressLabel.setLayoutData(gridData31);
		dataSourceAddressLabel.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		languageLabel = new Label(composite, SWT.NONE);
		languageCombo = new CCombo(composite, SWT.BORDER);
		languageCombo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		languageCombo.setVisible(false);
		languageCombo.setLayoutData(gridData4);
		languageCombo.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				language = languageCombo.getItem(languageCombo.getSelectionIndex());
			}
		});
		
		Label filler411 = new Label(composite, SWT.NONE);
		Label filler42 = new Label(composite, SWT.NONE);
		languageLabel.setText("Query language:");
		languageLabel.setVisible(false);
		languageLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		Label filler43 = new Label(composite, SWT.NONE);
		openEditorButton = new Button(composite, SWT.NONE);
		openEditorButton.setText("Open Editor");
		openEditorButton.setLayoutData(gridData21);
		openEditorButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				openStatementEditor(ModelPackage.eINSTANCE.getUpdateActivity()
						.getInstanceClassName(), language);
			}
		});

		Label filler3 = new Label(composite, SWT.NONE);
		Label filler41 = new Label(composite, SWT.NONE);
		showStatementCheckBox = new Button(composite, SWT.CHECK);
		showStatementCheckBox.setText("Show resulting statement");
		showStatementCheckBox.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		showStatementCheckBox.setLayoutData(gridData11);
		Label filler6 = new Label(composite, SWT.NONE);
		showStatementCheckBox.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (showStatementCheckBox.getSelection()) {
					statementLabel.setVisible(true);
					statementText.setVisible(true);
				} else {
					statementLabel.setVisible(false);
					statementText.setVisible(false);
				}
			}
		});
		Label filler1 = new Label(composite, SWT.NONE);
		statementLabel = new Label(composite, SWT.NONE);
		statementLabel.setText("Resulting statement:");
		statementLabel.setVisible(false);
		statementLabel.setLayoutData(gridData);
		statementLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		statementText = new Text(composite, SWT.BORDER);
		statementText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		statementText.setVisible(false);
		statementText.setLayoutData(gridData1);
		statementText.setEditable(false);
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
		typeCombo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		typeCombo.setLayoutData(gridData2);

		// Initialisieren der typeCombo-Daten
		typeCombo.setItems(Constants.getDataSourceTypes()
				.toArray(new String[0]));
		// Aktualisieren der KindCombo-Daten
		typeCombo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				kindCombo.setItems(Constants.getDataSourceSubTypes(
						typeCombo.getItem(typeCombo.getSelectionIndex()))
						.toArray(new String[0]));
				//Speichern Auswahl in Modell
				getCommandFramework().execute(
						new SetDsTypeCommand(getModel(), typeCombo.getItem(typeCombo.getSelectionIndex())));
			}
		});
		typeCombo.setEditable(false);
	
		//Wert aus Modell selektieren
		typeCombo.select(typeCombo.indexOf(this.activity.getDsType()));
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
		kindCombo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		kindCombo.setToolTipText("Choose the subtype of data source");
		kindCombo.setEditable(false);
		kindCombo.setLayoutData(gridData6);
		
		// Initilisieren der kindCombo-Daten
		if (typeCombo.getSelectionIndex()>0){
			kindCombo.setItems(Constants.getDataSourceSubTypes(
					typeCombo.getItem(typeCombo.getSelectionIndex()))
					.toArray(new String[0]));
		}
		
		kindCombo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				List<String> languages = Constants
						.getDatasourceLanguages(kindCombo.getItem(kindCombo
								.getSelectionIndex()));

				if (languages.size()>1) {
					languageCombo.setVisible(true);
					languageLabel.setVisible(true);
					languageCombo.setItems(languages.toArray(new String[0]));
				} else {
					languageCombo.setVisible(false);
					languageLabel.setVisible(false);
					language = languages.get(0);
				}
				//Speichern Auswahl in Modell
				getCommandFramework().execute(
						new SetDsKindCommand(getModel(), kindCombo.getItem(kindCombo
								.getSelectionIndex())));
			}
		});
		
		//Wert aus Modell selektieren
		kindCombo.select(kindCombo.indexOf(this.activity.getDsKind()));
	}
	
	@Override
	public String getStatement() {
		// TODO Auto-generated method stub
		return this.statement;
	}

	@Override
	public void setStatement(String statement) {
		// TODO Auto-generated method stub
		this.statement = statement;
		statementText.setText(statement);
	}

	@Override
	public void saveStatementToModel() {
		getCommandFramework().execute(
				new SetDsStatementCommand(getModel(), this.statement));
	}
}
