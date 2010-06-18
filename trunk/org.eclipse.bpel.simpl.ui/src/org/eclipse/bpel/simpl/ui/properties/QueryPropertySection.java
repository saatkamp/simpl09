/**
 * <b>Purpose:</b> Implements the property section for the {@link QueryActivity}. <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>, Firas Zoabi <zoabifs@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.properties;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.ui.command.SetDsAddressCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsKindCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsLanguageCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsStatementCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsTypeCommand;
import org.eclipse.bpel.simpl.ui.command.SetQueryTargetCommand;
import org.eclipse.bpel.simpl.ui.properties.util.PropertySectionUtils;
import org.eclipse.bpel.simpl.ui.properties.util.VariableUtils;
import org.eclipse.bpel.simpl.ui.widgets.DBTable;
import org.eclipse.bpel.simpl.ui.widgets.FileSysListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.LiveEditStyleText;
import org.eclipse.bpel.simpl.ui.widgets.MetaDataXMLParser;
import org.eclipse.bpel.simpl.ui.widgets.ParametersListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.TablsListPopUp;
import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


@SuppressWarnings("unused")
public class QueryPropertySection extends DMActivityPropertySection {

	/** The tabels pop window tables. */
	TablsListPopUp tabelsPopWindowTables;
	
	/** The tabels pop window bpel variables. */
	TablsListPopUp tabelsPopWindowBPELVariables;
	ParametersListPopUp bpelVariableWindow;
	FileSysListPopUp fSysElementsPopWindow;
	
	private Label typeLabel = null;
	private Text typeText = null;
	private Label statementLabel = null;
	//private Text statementText = null;
	private Button showStatementCheckBox = null;
	private Label dataSourceAddressLabel = null;
	private CCombo dataSourceAddressCombo = null;
	private Label kindLabel = null;
	private Text kindText = null;
	private Button openEditorButton = null;
	private Label languageLabel = null;
	private Text languageText = null;
	private Composite parentComposite = null;
	private Label queryTargetLabel = null;
	private CCombo queryTargetCombo = null;

	private LiveEditStyleText statementText = null;
	private Button insertBpelVariable = null;
	private Button insertTable = null;
	private Button Save = null;
	
	private QueryActivity activity;
	private Button command = null,file = null,folder = null,driver = null;
	
	/**
	 * Make this section use all the vertical space it can get.
	 * 
	 * @return true, if successful
	 */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	@Override
	protected void createClient(Composite parent) {
		// Setzen die im Editor ausgewählte Aktivität als Input.
		setInput(getPart(), getBPELEditor().getSelection());
		// Laden der Aktivität
		this.activity = getModel();

		createWidgets(parent);

		if (activity.getDsStatement() == null){
			// Setzen das Statement
			setStatement("");
		}else {
			// Setzen das Statement
			setStatement(activity.getDsStatement());
		}
		
		// Setzen die Datenquellenadresse
		dataSourceAddressCombo.setText(activity.getDsAddress());
		// Setzen die Zieleinheit des Queries.
		queryTargetCombo.setText(activity.getQueryTarget());
		// Setzen die Sprache
		languageText.setText(activity.getDsLanguage());
		
		
	}

	/**
	 * Creates the property section.
	 * 
	 * @param composite
	 *            to put the content in.
	 */
	private void createWidgets(Composite composite) {
		this.parentComposite = composite;
		GridData gridData13 = new GridData();
		gridData13.horizontalAlignment = GridData.FILL;
		gridData13.grabExcessHorizontalSpace = true;
		gridData13.verticalAlignment = GridData.CENTER;
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
		gridData31.grabExcessHorizontalSpace = false;
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
		dataSourceAddressCombo = new CCombo(composite, SWT.BORDER);
		dataSourceAddressCombo.setLayoutData(gridData12);
		// Änderungen im Modell speichern
		dataSourceAddressCombo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				getCommandFramework().execute(
						new SetDsAddressCommand(getModel(),
								dataSourceAddressCombo.getText()));

				DataSource dataSource = getDataSource();
				typeText.setText(dataSource.getType());
				kindText.setText(dataSource.getSubType());
				languageText.setText(dataSource.getLanguage());
			}
		});
		dataSourceAddressCombo.setItems(PropertySectionUtils.getAllDataSourceNames(getProcess()));
		
		dataSourceAddressLabel.setText("Data source name:");
		dataSourceAddressCombo.setEditable(false);
		dataSourceAddressCombo.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		dataSourceAddressLabel.setLayoutData(gridData31);
		dataSourceAddressLabel.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		languageLabel = new Label(composite, SWT.NONE);
		languageText = new Text(composite, SWT.BORDER);
		languageText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		languageText.setEditable(false);
		languageText.setLayoutData(gridData4);
		languageText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// Auswahl im Modell speichern
				getCommandFramework().execute(
						new SetDsLanguageCommand(getModel(), languageText.getText()));
			}
		});

		queryTargetLabel = new Label(composite, SWT.NONE);
		queryTargetLabel.setText("Target to insert the query result:");
		queryTargetLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));

		languageLabel.setText("Query language:");
		languageLabel.setVisible(true);
		languageLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		queryTargetCombo = new CCombo(composite, SWT.BORDER);
		queryTargetCombo.setLayoutData(gridData13);
		queryTargetCombo.setItems(VariableUtils.getUseableVariables(getProcess()).toArray(new String[0]));
		queryTargetCombo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				getCommandFramework().execute(
						new SetQueryTargetCommand(getModel(), queryTargetCombo
								.getText()));
			}
		});
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
				MetaDataXMLParser metaDataXMLParser_Objekt=new MetaDataXMLParser();
				ArrayList<DBTable> listOfTables= metaDataXMLParser_Objekt.loadTablesFromDB(
						PropertySectionUtils.findDataSourceByName(getProcess(), dataSourceAddressCombo.getText()));
				System.out.println("\r checkpoint \r");
				openStatementEditor(ModelPackage.eINSTANCE.getQueryActivity()
						.getInstanceClassName(), activity.getDsLanguage());
			}
		});

		
		
		
		//+++++++++++++++++++++++++++++++++++Buttons for Statmet Feld+++++++ 
		Composite statementCompo=new Composite(composite, SWT.NONE);
		statementCompo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		GridData gridData131 = new GridData();
		gridData131.horizontalSpan = 3;
		
		GridData gridData14 = new GridData();
		gridData14.horizontalSpan = 4;
		gridData14.horizontalAlignment = GridData.FILL;
		gridData14.verticalAlignment = GridData.FILL;
		gridData14.grabExcessVerticalSpace = true;
		gridData14.grabExcessHorizontalSpace = true;
		
		GridData gridData15 = new GridData();
		gridData15.horizontalSpan = 4;
		gridData15.horizontalAlignment = GridData.FILL;
		gridData15.verticalAlignment = GridData.FILL;
		gridData15.grabExcessVerticalSpace = true;
		gridData15.grabExcessHorizontalSpace = true;
		
		GridData gridData24 = new GridData();
		gridData24.horizontalAlignment = GridData.BEGINNING;
		gridData24.verticalAlignment = GridData.CENTER;
		
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		statementCompo.setLayout(gridLayout2);
		statementCompo.setLayoutData(gridData14);
		//statementCompo.setSize(new Point(150,70));
				
//		Save = new Button(statementCompo, SWT.NONE);
//		Save.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
//		Save.setText("Save");
//		Save.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				
//				//activity.setDsStatement(statementText.getText());
//				setStatement(statementText.getText());
//				saveStatementToModel();
//				 
//				tabelsPopWindowTables.closeWindow();
//				tabelsPopWindowBPELVariables.closeWindow();
//				tabelsPopWindowTables.setWindowIsOpen(false);
//				tabelsPopWindowBPELVariables.setWindowIsOpen(false);
//			}
//		});

				
		if(activity.getDsType()!=null){
			if(activity.getDsType().equals("Filesystem")){
				System.out.print("\r Filesystem");
		
				createFileSystemWidgets();
				
			}
			else if(activity.getDsType().equals("Database")){
				System.out.print("\r Database");
				
				createDBWidgets();	
			}
		}
		
		statementText = new LiveEditStyleText(composite);
		statementText.setLayoutData(gridData15);

		statementText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		
		
		
		
		
		if (activity.getDsStatement() == null){
			// Setzen das Statement
			setStatement("");
			statementText.setText("");
		}else {
			// Setzen das Statement
			statementText.setText(activity.getDsStatement());
		}

//		statementText.addModifyListener(new ModifyListener() {
//			
//			@Override
//			public void modifyText(ModifyEvent e) {
//				//setStatement(statementText.getText());
//				setStatement(statementText.getText());
//				saveStatementToModel();
//			}
//		});
		
		statementText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		//statementText.setVisible(false);
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		typeText.setEnabled(false);
		kindText.setEnabled(false);
		languageText.setEnabled(false);
	}

	/**
	 * for creating the GUI elements of DB data source as child Composite.
	 * @param parentCompo
	 * @return
	 */
	private void createDBWidgets(){
		//----------------------------------------------------------
		Composite contentCompo=new Composite(this.parentComposite, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		contentCompo.setLayout(gridLayout2);
		contentCompo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
	
		GridData gridData14 = new GridData();
		gridData14.horizontalSpan = 4;
		gridData14.horizontalAlignment = GridData.FILL;
		gridData14.verticalAlignment = GridData.FILL;
		gridData14.grabExcessVerticalSpace = true;
		gridData14.grabExcessHorizontalSpace = true;
		contentCompo.setLayoutData(gridData14);
		
		// statementCompo.setSize(new Point(150,70));
		insertBpelVariable = new Button(contentCompo, SWT.NONE);
		insertBpelVariable.setText("Insert Variable");
		insertBpelVariable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				bpelVariableWindow = new ParametersListPopUp(statementText);
				// Display display2 = Display.getDefault();
				bpelVariableWindow.setText("Insert BPEL-Variable");
				// sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
				// sShell.setLayout(gridLayout);
				java.util.List<String> listOfBPELVariablesAsStrings = VariableUtils
						.getUseableVariables(getProcess());
				bpelVariableWindow
						.loadBPELVariables(listOfBPELVariablesAsStrings);
				if (!bpelVariableWindow.isWindowOpen()) {
					bpelVariableWindow.openWindow();
					bpelVariableWindow.setWindowIsOpen(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		insertTable = new Button(contentCompo, SWT.NONE);
		insertTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tabelsPopWindowTables = new TablsListPopUp(statementText);
				tabelsPopWindowTables.setText("Select Tabel");
				tabelsPopWindowTables.loadTablesFromDB(getDataSource());

				if (!tabelsPopWindowTables.isWindowOpen()) {
					tabelsPopWindowTables.openWindow();
					tabelsPopWindowTables.setWindowIsOpen(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		Save = new Button(contentCompo, SWT.NONE);
		Save
				.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));
		Save.setText("Save");
		Save.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setStatement(statementText.getText());
				saveStatementToModel();

				tabelsPopWindowTables.closeWindow();
				tabelsPopWindowBPELVariables.closeWindow();
				tabelsPopWindowTables.setWindowIsOpen(false);
				tabelsPopWindowBPELVariables.setWindowIsOpen(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		insertTable.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		insertTable.setText("Insert Table");

		// insertBpelVariable.setLayoutData(gridData24);
		insertBpelVariable.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		//---------------------------------------------------------------
	}
	
	
	/**
	 * for creating the GUI elements of FileSystem data source as child Composite.
	 * @param parentCompo
	 * @return
	 */
	private void createFileSystemWidgets() {
		//this.parentComposite = composite;
		Composite contentCompo=new Composite(this.parentComposite, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 5;
		contentCompo.setLayout(gridLayout2);
		contentCompo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		
		GridData gridData14 = new GridData();
		gridData14.horizontalSpan = 4;
		gridData14.horizontalAlignment = GridData.FILL;
		gridData14.verticalAlignment = GridData.FILL;
		gridData14.grabExcessVerticalSpace = true;
		gridData14.grabExcessHorizontalSpace = true;
		contentCompo.setLayoutData(gridData14);
		
		
		
		command = new Button(contentCompo, SWT.NONE);
		command.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));
		command.setText("Select Command");
		command.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				fSysElementsPopWindow = new FileSysListPopUp("COMMAND",statementText);
				fSysElementsPopWindow.setText("Select Command");
				fSysElementsPopWindow.loadDataToFSysElementList("COMMAND",getDataSource());

				if (!fSysElementsPopWindow.isWindowOpen()) {
					fSysElementsPopWindow.openWindow();
					fSysElementsPopWindow.setWindowIsOpen(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		file = new Button(contentCompo, SWT.NONE);
		file.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));
		file.setText("Select File");
		file.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				fSysElementsPopWindow = new FileSysListPopUp("FILE",statementText);
				fSysElementsPopWindow.setText("Select File");
				fSysElementsPopWindow.loadDataToFSysElementList("FILE",getDataSource());

				if (!fSysElementsPopWindow.isWindowOpen()) {
					fSysElementsPopWindow.openWindow();
					fSysElementsPopWindow.setWindowIsOpen(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		folder = new Button(contentCompo, SWT.NONE);
		folder.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));
		folder.setText("Select Folder");
		folder.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				fSysElementsPopWindow = new FileSysListPopUp("FOLDER",statementText);
				fSysElementsPopWindow.setText("Select Folder");
				fSysElementsPopWindow.loadDataToFSysElementList("FOLDER",getDataSource());

				if (!fSysElementsPopWindow.isWindowOpen()) {
					fSysElementsPopWindow.openWindow();
					fSysElementsPopWindow.setWindowIsOpen(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		driver = new Button(contentCompo, SWT.NONE);
		driver.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));
		driver.setText("Select Driver");
		driver.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				fSysElementsPopWindow = new FileSysListPopUp("DRIVE",statementText);
				fSysElementsPopWindow.setText("Select Folder");
				fSysElementsPopWindow.loadDataToFSysElementList("DRIVE",getDataSource());

				if (!fSysElementsPopWindow.isWindowOpen()) {
					fSysElementsPopWindow.openWindow();
					fSysElementsPopWindow.setWindowIsOpen(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		Save = new Button(contentCompo, SWT.NONE);
		Save.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));
		Save.setText("Save");
		Save.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setStatement(statementText.getText());
				saveStatementToModel();

				tabelsPopWindowTables.closeWindow();
				tabelsPopWindowBPELVariables.closeWindow();
				tabelsPopWindowTables.setWindowIsOpen(false);
				tabelsPopWindowBPELVariables.setWindowIsOpen(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		GridData gridData15 = new GridData();
		gridData15.grabExcessHorizontalSpace = true;
		gridData15.verticalAlignment = GridData.CENTER;
		gridData15.horizontalAlignment = GridData.FILL;
}
	
	/**
	 * This method initializes typeCombo
	 * 
	 */
	private void createTypeCombo() {
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.CENTER;
		typeText = new Text(parentComposite, SWT.BORDER);
		typeText.setToolTipText("Choose the type of data source");
		typeText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		typeText.setLayoutData(gridData2);

		// Aktualisieren der KindCombo-Daten
		typeText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				// Speichern Auswahl in Modell
				getCommandFramework().execute(
						new SetDsTypeCommand(getModel(), typeText.getText()));
			}
		});
		typeText.setEditable(false);

		// Wert aus Modell setzen
		typeText.setText(this.activity.getDsType());
	}

	/**
	 * This method initializes kindCombo
	 * 
	 */
	private void createKindCombo() {
		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = GridData.FILL;
		gridData6.verticalAlignment = GridData.CENTER;
		kindText = new Text(parentComposite, SWT.BORDER);
		kindText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		kindText.setToolTipText("Choose the subtype of data source");
		kindText.setEditable(false);
		kindText.setLayoutData(gridData6);

		kindText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				// Speichern Auswahl in Modell
				getCommandFramework().execute(
						new SetDsKindCommand(getModel(), kindText.getText()));
			}
		});
				
		// Wert aus Modell setzen
		kindText.setText(this.activity.getDsKind());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#getStatement()
	 */
	@Override
	public String getStatement() {
		// TODO Auto-generated method stub
		return this.statement;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#setStatement(java.lang.String)
	 */
	@Override
	public void setStatement(String statement) {
		// TODO Auto-generated method stub
		if (statementText != null && statement != null) {
			statementText.setText(statement);
			this.statement = statement;
			//this.activity.setDsStatement(statement);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#saveStatementToModel()
	 */
	@Override
	public void saveStatementToModel() {
		getCommandFramework().execute(
				new SetDsStatementCommand(getModel(), this.statement));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {
		return PropertySectionUtils
		.findDataSourceByName(getProcess(),
				dataSourceAddressCombo.getText());
	}
}