/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.properties;

//TODO: Diese Implementierung abändern und per Extension-Point 
//in die RetrieveDataPropertySection einbinden.

import java.util.ArrayList;

import org.eclipse.bpel.common.ui.assist.FieldAssistAdapter;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.ui.command.SetDataVariableCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsAddressCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsKindCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsLanguageCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsStatementCommand;
import org.eclipse.bpel.simpl.ui.command.SetDsTypeCommand;
import org.eclipse.bpel.simpl.ui.properties.util.PropertySectionUtils;
import org.eclipse.bpel.simpl.ui.properties.util.VariableUtils;
import org.eclipse.bpel.simpl.ui.widgets.DBTable;
import org.eclipse.bpel.simpl.ui.widgets.LiveEditStyleText;
import org.eclipse.bpel.simpl.ui.widgets.MetaDataXMLParser;
import org.eclipse.bpel.simpl.ui.widgets.ParametersListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.TablsListPopUp;
import org.eclipse.bpel.ui.details.providers.ModelLabelProvider;
import org.eclipse.bpel.ui.details.providers.VariableContentProvider;
import org.eclipse.bpel.ui.details.providers.VariableFilter;
import org.eclipse.bpel.ui.proposal.providers.ModelContentProposalProvider;
import org.eclipse.bpel.ui.util.BatchedMultiObjectAdapter;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.MultiObjectAdapter;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * The Class RetrieveDataVariableSection.
 */
public class RetrieveDataVariableSection extends DMActivityPropertySection {

	private VariableFilter fInputVariableFilter = new VariableFilter();

	/** The tabels pop window tables. */
	TablsListPopUp tabelsPopWindowTables;
	ParametersListPopUp bpelVariableWindow;
	
	/** The tabels pop window bpel variables. */
	TablsListPopUp tabelsPopWindowBPELVariables;
	private Label typeLabel = null;
	private Text typeText = null;
	private Label statementLabel = null;
	// private Text statementText = null;
	private Label dataSourceAddressLabel = null;
	private CCombo dataSourceAddressCombo = null;
	private Label kindLabel = null;
	private Text kindText = null;
	private Button openEditorButton = null;
	private Label languageLabel = null;
	private Text languageText = null;
	private Label queryTargetLabel = null;
	private Text queryTargetText = null;

	private LiveEditStyleText statementText = null;

	private Button insertBpelVariable = null;
	private Button insertTable = null;
	private Button Save = null;

	private RetrieveDataActivity activity;

	private DataSource dataSource = null;

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

		// Setzen das Statement
		setStatement(activity.getDsStatement());
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
		GridData gridData130 = new GridData();
		gridData130.horizontalAlignment = GridData.FILL;
		gridData130.grabExcessHorizontalSpace = true;
		gridData130.verticalAlignment = GridData.CENTER;
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
						new SetDsLanguageCommand(getModel(), languageText
								.getText()));
			}
		});

		languageLabel.setText("Query language:");
		languageLabel.setVisible(true);
		languageLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));

		createInputVariableWidgets();

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

				openStatementEditor(ModelPackage.eINSTANCE
						.getRetrieveDataActivity().getInstanceClassName(),
						activity.getDsLanguage());
			}
		});

		// +++++++++++++++++++++++++++++++++++Buttons for Statmet Feld+++++++
		Composite statementCompo = new Composite(composite, SWT.NONE);
		statementCompo.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		GridData gridData13 = new GridData();
		gridData13.horizontalSpan = 3;

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
		// statementCompo.setSize(new Point(150,70));
		insertBpelVariable = new Button(statementCompo, SWT.NONE);
		insertBpelVariable.setText("Insert Variable");
		insertBpelVariable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				bpelVariableWindow = new ParametersListPopUp(statementText);
				// Display display2 = Display.getDefault();
				bpelVariableWindow.setText("Insert BPEL-Variable");
				// sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
				// sShell.setLayout(gridLayout);
				java.util.List<String> listOfBPELVariablesAsStrings=VariableUtils.getUseableVariables(getProcess());
				bpelVariableWindow.loadBPELVariables(listOfBPELVariablesAsStrings);
				if (!bpelVariableWindow.isWindowOpen()) {
					bpelVariableWindow.openWindow();
					bpelVariableWindow.setWindowIsOpen(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		insertTable = new Button(statementCompo, SWT.NONE);
		insertTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				// Display tablesDisplay =new Display();
				// Composite tablesComp=new
				// Composite(tablesDisplay.getCurrent(), SWT.NONE);
				tabelsPopWindowTables = new TablsListPopUp(statementText);
				// Display display2 = Display.getDefault();
				tabelsPopWindowTables.setText("Select Tabel");
				// sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
				// sShell.setLayout(gridLayout);
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

		Save = new Button(statementCompo, SWT.NONE);
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
				setStatement(statementText.getText());
				saveStatementToModel();
				
				tabelsPopWindowTables.closeWindow();
				tabelsPopWindowBPELVariables.closeWindow();
				tabelsPopWindowTables.setWindowIsOpen(false);
				tabelsPopWindowBPELVariables.setWindowIsOpen(false);
			}
		});

		insertTable.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		insertTable.setText("Insert Table");

		// insertBpelVariable.setLayoutData(gridData24);
		insertBpelVariable.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		statementText = new LiveEditStyleText(statementCompo,this);
		statementText.setLayoutData(gridData15);

		statementText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		
//		statementText.addModifyListener(new ModifyListener() {
//			
//			@Override
//			public void modifyText(ModifyEvent e) {
//				setStatement(statementText.getText());
//				saveStatementToModel();
//			}
//		});
		// statementText.setVisible(false);
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++

		typeText.setEnabled(false);
		kindText.setEnabled(false);
		languageText.setEnabled(false);
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
		// TODO Auto-generated method stub
		this.statement = statement;
		if (statementText != null && statement != null) {
			statementText.setText(statement);
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

	private IControlContentAdapter fTextContentAdapter = new TextContentAdapter() {
		@Override
		public void insertControlContents(Control control, String text,
				int cursorPosition) {
			if (text != null) {
				super.insertControlContents(control, text, cursorPosition);
			}
		}

		@Override
		public void setControlContents(Control control, String text,
				int cursorPosition) {
			if (text != null) {
				super.setControlContents(control, text, cursorPosition);
			}
		}
	};
	private Button inputVariableButton;
	private Label inputVariableLabel;
	private Text inputVariableText;
	private Composite parentComposite;

	@Override
	protected void addAllAdapters() {
		// model object
		super.addAllAdapters();
	}

	@Override
	protected void basicSetInput(EObject newInput) {
		super.basicSetInput(newInput);
	}

	@Override
	protected MultiObjectAdapter[] createAdapters() {
		return new MultiObjectAdapter[] {
		/* model object */
		new BatchedMultiObjectAdapter() {

			@Override
			public void finish() {
				refresh();
			}

			@Override
			public void notify(Notification n) {
			}
		} };
	}

	private void createInputVariableWidgets() {
		Composite inputVariableComp = new Composite(parentComposite, SWT.NONE);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		inputVariableComp.setLayout(gridLayout);
		inputVariableComp.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		GridData gridData12 = new GridData();
		gridData12.horizontalSpan = 2;
		gridData12.grabExcessHorizontalSpace = true;
		gridData12.verticalAlignment = GridData.CENTER;
		gridData12.horizontalAlignment = GridData.FILL;
		inputVariableComp.setLayoutData(gridData12);

		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = GridData.FILL;
		gridData6.verticalAlignment = GridData.CENTER;
		gridData6.grabExcessHorizontalSpace = true;

		inputVariableLabel = fWidgetFactory.createLabel(inputVariableComp,
				"Data variable:");
		inputVariableText = fWidgetFactory.createText(inputVariableComp,
				EMPTY_STRING);
		inputVariableButton = fWidgetFactory.createButton(inputVariableComp,
				EMPTY_STRING, SWT.ARROW | SWT.DOWN | SWT.CENTER);

		VariableContentProvider provider = new VariableContentProvider(false);
		ModelContentProposalProvider proposalProvider;
		proposalProvider = new ModelContentProposalProvider(
				new ModelContentProposalProvider.ValueProvider() {
					@Override
					public Object value() {
						return getInput();
					}
				}, provider, fInputVariableFilter);

		final FieldAssistAdapter contentAssist = new FieldAssistAdapter(
				inputVariableText, fTextContentAdapter, proposalProvider, null,
				null, true);
		// 
		contentAssist.setLabelProvider(new ModelLabelProvider());
		contentAssist.setPopupSize(new Point(300, 100));
		contentAssist.setFilterStyle(ContentProposalAdapter.FILTER_CUMULATIVE);
		contentAssist
				.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		contentAssist
				.addContentProposalListener(new IContentProposalListener() {

					public void proposalAccepted(IContentProposal chosenProposal) {
						if (chosenProposal.getContent() == null) {
							return;
						}
						Variable variable = null;
						try {
							variable = (Variable) ((Adapter) chosenProposal)
									.getTarget();
						} catch (Throwable t) {
							return;
						}
						SetDataVariableCommand cmd = new SetDataVariableCommand(
								getInput(), variable);
						getCommandFramework().execute(cmd);
					}
				});

		// End of Content Assist for variable
		inputVariableButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				contentAssist.openProposals();
			}
		});

		inputVariableText.addListener(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				if (event.keyCode == SWT.CR) {
					findAndSetVariable(inputVariableText.getText());
				}
			}
		});

		inputVariableText.setLayoutData(gridData6);
	}

	private void findAndSetVariable(String text) {

		text = text.trim();
		EObject model = getInput();

		SetDataVariableCommand cmd = new SetDataVariableCommand(getInput(),
				null);
		if (text.length() > 0) {
			Variable variable = (Variable) ModelHelper
					.findElementByName(ModelHelper.getContainingScope(model),
							text, Variable.class);
			if (variable == null) {

				return;
			}

			cmd.setNewValue(variable);
		}

		getCommandFramework().execute(cmd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		super.refresh();
		updateInputVariableWidgets();
	}

	private void updateInputVariableWidgets() {
		Variable inputVar = activity.getDataVariable();

		if (inputVar != null) {
			inputVariableText.setText(inputVar.getName());

			// Figure out the type of the variable XSDTypeDefinition.
			fInputVariableFilter.clear();
			Object type = inputVar.getType();
			if (type != null && type instanceof XSDTypeDefinition) {
				fInputVariableFilter.setType((XSDTypeDefinition) type);
			}
		} else {
			inputVariableText.setText(EMPTY_STRING);
		}
	}
}
