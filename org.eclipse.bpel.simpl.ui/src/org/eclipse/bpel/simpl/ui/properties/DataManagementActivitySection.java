package org.eclipse.bpel.simpl.ui.properties;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.TransferActivity;
import org.eclipse.bpel.simpl.ui.command.SetDsAddressCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsKindCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsLanguageCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsStatementCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsTypeCommand;
import org.eclipse.bpel.simpl.ui.properties.util.PropertySectionUtils;
import org.eclipse.bpel.simpl.ui.properties.util.VariableUtils;
import org.eclipse.bpel.simpl.ui.widgets.DBTable;
import org.eclipse.bpel.simpl.ui.widgets.FileSysListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.LiveEditStyleText;
import org.eclipse.bpel.simpl.ui.widgets.MetaDataXMLParser;
import org.eclipse.bpel.simpl.ui.widgets.ParametersListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.SchemaListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.TablsListPopUp;
import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class DataManagementActivitySection extends DMActivityPropertySection {

	/** The tabels pop window tables. */
	SchemaListPopUp schemaPopWindow;
	ParametersListPopUp bpelVariableWindow;
	FileSysListPopUp fSysElementsPopWindow;

	/** The tabels pop window bpel variables. */
	TablsListPopUp tabelsPopWindowBPELVariables;
	private Label typeLabel = null;
	private Text typeText = null;
	private Label dataSourceAddressLabel = null;
	private CCombo dataSourceAddressCombo = null;
	private Label kindLabel = null;
	private Text kindText = null;
	private Button openEditorButton = null;
	private Label languageLabel = null;
	private Text languageText = null;
	private Composite parentComposite = null;

	private DataManagementActivity activity;

	private LiveEditStyleText statementText = null;
	private Button insertParameterVariable = null;
	private Button insertContainerVariable = null;
	private Button insertTable = null;
	private Button Save = null;
	private Button command = null, file = null; //, folder = null, driver = null;

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

		if (activity.getDsStatement() == null) {
			// Setzen das Statement
			setStatement("");
		} else {
			// Setzen das Statement
			setStatement(activity.getDsStatement());
		}

		// Setzen die Datenquellenadresse
		dataSourceAddressCombo.setText(activity.getDsAddress());
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
		parentComposite.setSize(new Point(582, 350));
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
						new SetDsAddressCommand(activity,
								dataSourceAddressCombo.getText()));

				DataSource dataSource = getDataSource();
				if (dataSource != null) {
					typeText.setText(dataSource.getType());
					kindText.setText(dataSource.getSubType());
					languageText.setText(dataSource.getLanguage());
				}
			}
		});
		dataSourceAddressCombo.setItems(PropertySectionUtils
				.getAllDataSourceNames(getProcess()));

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
						new SetDsLanguageCommand(activity, languageText
								.getText()));

				if (languageText.getText().isEmpty()) {
					openEditorButton.setEnabled(false);
				} else {
					openEditorButton.setEnabled(true);
				}
			}
		});

		Label filler411 = new Label(composite, SWT.NONE);
		Label filler42 = new Label(composite, SWT.NONE);
		languageLabel.setText("Query language:");
		languageLabel.setVisible(true);
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
				MetaDataXMLParser metaDataXMLParser_Objekt=new MetaDataXMLParser();
				ArrayList<DBTable> listOfTables= metaDataXMLParser_Objekt.loadTablesFromDB(
						PropertySectionUtils.findDataSourceByName(getProcess(), dataSourceAddressCombo.getText()));
				if (getModel() instanceof TransferActivity) {
					// The fromSource DataManagementActivity of the transfer
					// activity needs a query statement editor
					openStatementEditor(ModelPackage.eINSTANCE
							.getQueryActivity().getName(), activity
							.getDsLanguage(), activity.getName());
				} else {
					openStatementEditor(getModel().eClass().getName(), activity
							.getDsLanguage(), activity.getName());
				}
			}
		});

		Label filler = new Label(composite, SWT.NONE);
		Label filler6 = new Label(composite, SWT.NONE);

		GridData gridData14 = new GridData();
		gridData14.horizontalSpan = 4;
		gridData14.horizontalAlignment = GridData.FILL;
		gridData14.verticalAlignment = GridData.FILL;
		gridData14.grabExcessVerticalSpace = true;
		gridData14.grabExcessHorizontalSpace = true;

		if (activity.getDsType() != null) {
			if (activity.getDsType().equals("Filesystem")) {
				System.out.print("\r Filesystem");

				createFileSystemWidgets();

			} else if (activity.getDsType().equals("Database")) {
				System.out.print("\r Database");

				createDBWidgets();
			}
		}

		GridData gridData15 = new GridData();
		gridData15.horizontalSpan = 4;
		gridData15.horizontalAlignment = GridData.FILL;
		gridData15.verticalAlignment = GridData.FILL;
		gridData15.grabExcessVerticalSpace = true;
		gridData15.grabExcessHorizontalSpace = true;

		insertParameterVariable = new Button(composite, SWT.NONE);
		insertParameterVariable.setText("Insert Parameter Variable");
		insertParameterVariable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				bpelVariableWindow = new ParametersListPopUp(statementText);

				bpelVariableWindow.setText("Insert Parameter Variable");

				java.util.List<String> listOfBPELVariablesAsStrings = VariableUtils
						.getUseableVariables(getProcess(),
								VariableUtils.PARAMETER_VAR);
				bpelVariableWindow
						.loadBPELVariables(listOfBPELVariablesAsStrings);
				if (!bpelVariableWindow.isWindowOpen()) {
					bpelVariableWindow.openWindow();
					bpelVariableWindow.setWindowIsOpen(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		insertContainerVariable = new Button(composite, SWT.NONE);
		insertContainerVariable.setText("Insert Container Reference Variable");
		insertContainerVariable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				bpelVariableWindow = new ParametersListPopUp(statementText);

				bpelVariableWindow
						.setText("Insert Container Reference Variable");

				java.util.List<String> listOfBPELVariablesAsStrings = VariableUtils
						.getUseableVariables(getProcess(),
								VariableUtils.CONTAINER_VAR);
				bpelVariableWindow
						.loadBPELVariables(listOfBPELVariablesAsStrings);
				if (!bpelVariableWindow.isWindowOpen()) {
					bpelVariableWindow.openWindow();
					bpelVariableWindow.setWindowIsOpen(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		Save = new Button(composite, SWT.NONE);
		Save.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));
		Save.setText("Save");
		Save.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setStatement(statementText.getText());
				saveStatementToModel();

//				if (tabelsPopWindowTables != null){
//					tabelsPopWindowTables.closeWindow();
//					tabelsPopWindowTables.setWindowIsOpen(false);
//				}
//				if (tabelsPopWindowBPELVariables != null){
//					tabelsPopWindowBPELVariables.closeWindow();
//					tabelsPopWindowBPELVariables.setWindowIsOpen(false);
//				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		insertParameterVariable.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		insertContainerVariable.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));

		Label filler1 = new Label(composite, SWT.NONE);

		Label statementLabel = new Label(composite, SWT.NONE);
		statementLabel.setText("Data management operation:");
		statementLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		statementText = new LiveEditStyleText(composite);
		statementText.setLayoutData(gridData15);

		statementText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));

		typeText.setEnabled(false);
		kindText.setEnabled(false);
		languageText.setEnabled(false);
	}

	/**
	 * for creating the GUI elements of DB data source as child Composite.
	 * 
	 * @param parentCompo
	 * @return
	 */
	private void createDBWidgets() {
		// ----------------------------------------------------------
		Composite contentCompo = new Composite(this.parentComposite, SWT.NONE);
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

		insertTable = new Button(contentCompo, SWT.NONE);
		insertTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				schemaPopWindow = new SchemaListPopUp(statementText);
				schemaPopWindow.setText("Select Table Name");
				schemaPopWindow.loadSchemasFromDB(getDataSource());

				if (!schemaPopWindow.isWindowOpen()) {
					schemaPopWindow.openWindow();
					schemaPopWindow.setWindowIsOpen(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		insertTable.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		insertTable.setText("Insert Table Name");
	}

	/**
	 * for creating the GUI elements of FileSystem data source as child
	 * Composite.
	 * 
	 * @param parentCompo
	 * @return
	 */
	private void createFileSystemWidgets() {
		// this.parentComposite = composite;
		Composite contentCompo = new Composite(this.parentComposite, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 4;
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
				fSysElementsPopWindow = new FileSysListPopUp("COMMAND",
						statementText);
				fSysElementsPopWindow.setText("Select Command");
				fSysElementsPopWindow.loadDataToFSysElementList("COMMAND",
						getDataSource());

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
		file
				.setBackground(Display.getCurrent().getSystemColor(
						SWT.COLOR_WHITE));
		file.setText("Select File");
		file.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(parentComposite.getShell(), SWT.OPEN);
			    dialog
			        .setFilterNames(new String[] { "Comma Separated Values", "All Files (*.*)" });
			    dialog.setFilterExtensions(new String[] { "*.csv", "*.*" });                   
			    dialog.setFilterPath(getDataSource().getAddress());
			    dialog.setFileName("");
			    
			    dialog.open();
			    
			    statementText.append(" " + dialog.getFilterPath() + dialog.getFileName());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
//		folder = new Button(contentCompo, SWT.NONE);
//		folder.setBackground(Display.getCurrent().getSystemColor(
//				SWT.COLOR_WHITE));
//		folder.setText("Select Folder");
//		folder.addSelectionListener(new SelectionListener() {
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				fSysElementsPopWindow = new FileSysListPopUp("FOLDER",
//						statementText);
//				fSysElementsPopWindow.setText("Select Folder");
//				fSysElementsPopWindow.loadDataToFSysElementList("FOLDER",
//						getDataSource());
//
//				if (!fSysElementsPopWindow.isWindowOpen()) {
//					fSysElementsPopWindow.openWindow();
//					fSysElementsPopWindow.setWindowIsOpen(true);
//				}
//			}
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//
//			}
//		});
//		driver = new Button(contentCompo, SWT.NONE);
//		driver.setBackground(Display.getCurrent().getSystemColor(
//				SWT.COLOR_WHITE));
//		driver.setText("Select Driver");
//		driver.addSelectionListener(new SelectionListener() {
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				fSysElementsPopWindow = new FileSysListPopUp("DRIVE",
//						statementText);
//				fSysElementsPopWindow.setText("Select Folder");
//				fSysElementsPopWindow.loadDataToFSysElementList("DRIVE",
//						getDataSource());
//
//				if (!fSysElementsPopWindow.isWindowOpen()) {
//					fSysElementsPopWindow.openWindow();
//					fSysElementsPopWindow.setWindowIsOpen(true);
//				}
//			}
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//
//			}
//		});

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
						new SetDsTypeCommand(activity, typeText.getText()));
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
						new SetDsKindCommand(activity, kindText.getText()));
			}
		});

		// Wert aus Modell setzen
		kindText.setText(this.activity.getDsKind());
	}

	@Override
	public void refresh() {
		super.refresh();

		// Setzen die im Editor ausgewählte Aktivität als Input.
		setInput(getPart(), getBPELEditor().getSelection());

		this.activity = getModel();

		if (activity.getDsStatement() == null) {
			// Setzen das Statement
			setStatement("");
		} else {
			// Setzen das Statement
			setStatement(activity.getDsStatement());
		}

		// Setzen die Datenquellenadresse
		dataSourceAddressCombo.setText(activity.getDsAddress());

		// Wert aus Modell setzen
		typeText.setText(this.activity.getDsType());

		// Wert aus Modell setzen
		kindText.setText(this.activity.getDsKind());

		// Setzen die Sprache
		languageText.setText(activity.getDsLanguage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#getStatement
	 * ()
	 */
	@Override
	public String getStatement() {
		// TODO Auto-generated method stub
		return this.statement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#setStatement
	 * (java.lang.String)
	 */
	@Override
	public void setStatement(String statement) {
		if (statementText != null && statement != null) {
			statementText.setText(statement);
			this.statement = statement;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#
	 * saveStatementToModel()
	 */
	@Override
	public void saveStatementToModel() {
		getCommandFramework().execute(
				new SetDsStatementCommand(activity, this.statement));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#getDataSource
	 * ()
	 */
	@Override
	public DataSource getDataSource() {
		return PropertySectionUtils.findDataSourceByName(getProcess(),
				dataSourceAddressCombo.getText());
	}
}