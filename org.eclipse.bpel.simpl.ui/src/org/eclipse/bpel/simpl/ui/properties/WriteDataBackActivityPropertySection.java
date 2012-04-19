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

import java.util.List;

import org.eclipse.bpel.common.ui.assist.FieldAssistAdapter;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.model.util.XSDImportResolver;
import org.eclipse.bpel.model.util.XSDUtil;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.simpl.ui.command.SetDMCommandCommand;
import org.eclipse.bpel.simpl.ui.command.SetDataResourceCommand;
import org.eclipse.bpel.simpl.ui.command.SetTargetContainerCommand;
import org.eclipse.bpel.simpl.ui.command.SetVariableCommand;
import org.eclipse.bpel.simpl.ui.properties.util.PropertySectionUtils;
import org.eclipse.bpel.simpl.ui.properties.util.VariableUtils;
import org.eclipse.bpel.simpl.ui.widgets.FileSysListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.ParametersListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.SchemaListPopUp;
import org.eclipse.bpel.simpl.ui.widgets.TablsListPopUp;
import org.eclipse.bpel.ui.details.providers.BaseTypeVariableFilter;
import org.eclipse.bpel.ui.details.providers.ModelLabelProvider;
import org.eclipse.bpel.ui.details.providers.VariableContentProvider;
import org.eclipse.bpel.ui.proposal.providers.ModelContentProposalProvider;
import org.eclipse.bpel.ui.util.BatchedMultiObjectAdapter;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.MultiObjectAdapter;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xml.type.internal.QName;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;
import org.simpl.resource.management.data.DataSource;

/**
 * The Class WriteDataBackVariableSection.
 */
public class WriteDataBackActivityPropertySection extends ADataManagementActivityPropertySection {

  private BaseTypeVariableFilter fInputVariableFilter = new BaseTypeVariableFilter();
  
  /** simpl.xsd */
  public static String DATA_TYPE = "tDataFormat";
  private static String SIMPL_NAMESPACE = "http://www.example.org/simpl";
  private static String SIMPL_PREFIX = "simpl";

  /** The tabels pop window tables. */
  SchemaListPopUp schemaPopWindow;
  ParametersListPopUp bpelVariableWindow;
  FileSysListPopUp fSysElementsPopWindow;

  /** The tabels pop window bpel variables. */
  TablsListPopUp tabelsPopWindowBPELVariables;
  private Label typeLabel = null;
  private Text typeText = null;
  private Label dataSourceIdentifierLabel = null;
  private CCombo dataSourceIdentifierCombo = null;
  private Label kindLabel = null;
  private Text kindText = null;
  //private Button openEditorButton = null;
  private Label languageLabel = null;
  private Text languageText = null;
  private Label writeTargetLabel = null;
  private CCombo writeTargetCombo = null;
  
  private Button inputVariableButton;
  @SuppressWarnings("unused")
  private Label inputVariableLabel;
  private Text inputVariableText;
  private Composite parentComposite;

   
  private WriteDataBackActivity activity;
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

    if (activity.getDmCommand() == null) {
      // Setzen das Statement
      setStatement("");
    } else {
      // Setzen das Statement
      setStatement(activity.getDmCommand());
    }

    setValues();
  }

  /**
   * Creates the property section.
   * 
   * @param composite
   *            to put the content in.
   */
  @SuppressWarnings("unused")
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
    //numColumns=5 => to move the 'Data Sources' Box to the right position
    gridLayout.numColumns = 5;
    parentComposite.setLayout(gridLayout);
    parentComposite.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));
    parentComposite.setSize(new Point(582, 350));
    typeLabel = new Label(composite, SWT.NONE);
    typeLabel.setText("Type of data resource:");
    typeLabel.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));
    typeLabel.setLayoutData(gridData51);
    createTypeCombo();
    Label filler1_3 = new Label(composite, SWT.NONE);
    Label filler1_4 = new Label(composite, SWT.NONE);
    Label filler1_5 = new Label(composite, SWT.NONE);
    kindLabel = new Label(composite, SWT.NONE);
    kindLabel.setText("Subtype of data resource:");
    kindLabel.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));
    createKindCombo();
    dataSourceIdentifierLabel = new Label(composite, SWT.NONE);
    dataSourceIdentifierCombo = new CCombo(composite, SWT.BORDER);
    dataSourceIdentifierCombo.setLayoutData(gridData12);
    // Änderungen im Modell speichern
    dataSourceIdentifierCombo.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        getCommandFramework().execute(
            new SetDataResourceCommand(getModel(),
                dataSourceIdentifierCombo.getText()));

        setValues();
      }
    });
    dataSourceIdentifierCombo.setItems(PropertySectionUtils
        .getAllDataSourceIdentifiers(getProcess()));

    dataSourceIdentifierLabel.setText("Data resource:");
    dataSourceIdentifierCombo.setEditable(false);
    dataSourceIdentifierCombo.setBackground(Display.getCurrent()
        .getSystemColor(SWT.COLOR_WHITE));
    dataSourceIdentifierLabel.setLayoutData(gridData31);
    dataSourceIdentifierLabel.setBackground(Display.getCurrent()
        .getSystemColor(SWT.COLOR_WHITE));
    Label filler2_5 = new Label(composite, SWT.NONE);
    languageLabel = new Label(composite, SWT.NONE);
    languageText = new Text(composite, SWT.BORDER);
    languageText.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));
    languageText.setEditable(false);
    languageText.setLayoutData(gridData4);
    languageLabel.setText("Query language:");
    languageLabel.setVisible(true);
    languageLabel.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));


    writeTargetLabel = new Label(composite, SWT.NONE);
    writeTargetLabel.setText("Target container to write the data:");
    writeTargetLabel.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));

    languageLabel.setText("Query language of data resource:");
    languageLabel.setVisible(true);
    languageLabel.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));
    writeTargetCombo = new CCombo(composite, SWT.BORDER);
    writeTargetCombo.setLayoutData(gridData130);
    writeTargetCombo.setItems(VariableUtils.getUseableVariables(getProcess()).toArray(new String[0]));
    writeTargetCombo.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent e) {
        getCommandFramework().execute(
            new SetTargetContainerCommand(getModel(), writeTargetCombo
                .getText()));
      }
    });
    Label filler3_5 = new Label(composite, SWT.NONE);
	typeText.setEnabled(false);
	kindText.setEnabled(false);
	languageText.setEnabled(false);
    Label filler4_1 = new Label(composite, SWT.NONE);
    Label filler4_2 = new Label(composite, SWT.NONE);

    //label is created here, in order to get a good alignment
    Label InputVariableWidgetLabel = new Label(composite, SWT.NONE);
    InputVariableWidgetLabel.setText("Data variable:");
    InputVariableWidgetLabel.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));
    createInputVariableWidgets();
    setBaseTypeVariableFilterType();
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
    typeText.setToolTipText("Choose the type of data resource");
    typeText.setBackground(Display.getCurrent().getSystemColor(
        SWT.COLOR_WHITE));
    typeText.setLayoutData(gridData2);
    typeText.setEditable(false);
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
    kindText.setToolTipText("Choose the subtype of data resource");
    kindText.setEditable(false);
    kindText.setLayoutData(gridData6);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.bpel.simpl.ui.properties.DataManagementActivityPropertySection#getStatement
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
   * org.eclipse.bpel.simpl.ui.properties.DataManagementActivityPropertySection#setStatement
   * (java.lang.String)
   */
  @Override
  public void setStatement(String statement) {
    this.statement = statement;
  }

  /*
   * (non-Javadoc)
   * 
   * @seeorg.eclipse.bpel.simpl.ui.properties.DataManagementActivityPropertySection#
   * saveStatementToModel()
   */
  @Override
  public void saveStatementToModel() {
    getCommandFramework().execute(
        new SetDMCommandCommand(getModel(), this.statement));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.bpel.simpl.ui.properties.DataManagementActivityPropertySection#getDataSource
   * ()
   */
  @Override
  public DataSource getDataSource() {
    return PropertySectionUtils.findDataSourceByIdentifier(getProcess(),
        dataSourceIdentifierCombo.getText());
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

  @SuppressWarnings("deprecation")
  private void createInputVariableWidgets() {
    Composite inputVariableComp = new Composite(parentComposite, SWT.NONE);

    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 2;
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
//    compare line 294
//    inputVariableLabel = fWidgetFactory.createLabel(inputVariableComp,
//        "Data variable:");
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
            SetVariableCommand cmd = new SetVariableCommand(
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

    SetVariableCommand cmd = new SetVariableCommand(getInput(),
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
    } else {
      inputVariableText.setText(EMPTY_STRING);
    }
  }
  
  /**
   * this method is used to set the type of the filter 
   */
  private void setBaseTypeVariableFilterType () {
    XSDImportResolver resolver = new XSDImportResolver();
    org.eclipse.bpel.model.Process process = BPELUtils.getProcess(getModel());
    EList<Import> imports = process.getImports();
    List<Object> definitions = null;

    for (Import import1 : imports) {
      if (import1.getNamespace() != null
          && import1.getNamespace().equals(SIMPL_NAMESPACE)) {
        definitions = resolver.resolve(import1,
            XSDImportResolver.RESOLVE_SCHEMA);
        break;
      }
    }

    if (!definitions.isEmpty()) {
      XSDTypeDefinition newType = XSDUtil.resolveTypeDefinition(
          (XSDSchema) definitions.get(0), new QName(SIMPL_NAMESPACE, DATA_TYPE,
              SIMPL_PREFIX));
      if (newType != null) {
        fInputVariableFilter.clear();
        fInputVariableFilter.setType(newType);
      }
    }
  }
  
  private void setValues() {
    dataSourceIdentifierCombo.setText(activity.getDataResource() != null ? activity.getDataResource() : "");
    writeTargetCombo.setText(activity.getTargetContainer());
    
    dataSource = getDataSource();
    
    if (dataSource != null) {
      typeText.setText(dataSource.getType());
      kindText.setText(dataSource.getSubType());
      languageText.setText(dataSource.getLanguage());
    } else {
      typeText.setText("");
      kindText.setText("");
      languageText.setText("");
    }
  }
}