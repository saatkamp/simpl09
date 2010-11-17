package org.eclipse.simpl.statementtest.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.simpl.communication.CommunicationPlugIn;
import org.eclipse.simpl.statementtest.StatementTestPlugin;
import org.eclipse.simpl.statementtest.execution.DatabaseStatementExecution;
import org.eclipse.simpl.statementtest.execution.FilesystemStatementExecution;
import org.eclipse.simpl.statementtest.model.StatementTest;
import org.eclipse.simpl.statementtest.model.StatementTestViewModel;
import org.eclipse.simpl.statementtest.model.variables.ContainerVariable;
import org.eclipse.simpl.statementtest.model.variables.ParameterVariable;
import org.eclipse.simpl.statementtest.ui.wizards.pages.DataSourceSelectionPage;
import org.eclipse.simpl.statementtest.ui.wizards.pages.ParameterAdjustmentPage;
import org.eclipse.simpl.statementtest.ui.wizards.pages.StatementScreeningPage;
import org.eclipse.simpl.statementtest.ui.wizards.pages.StatementTestWizardPage;
import org.eclipse.simpl.statementtest.utils.StringUtils;
import org.eclipse.simpl.statementtest.utils.VariableUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.simpl.core.webservices.client.DatasourceService;
import org.simpl.core.webservices.client.DatasourceServiceClient;

/**
 * <b>Purpose:</b>Wizard to create a new statement test.<br>
 * <b>Description:</b>Guides through the creation of a statement test that consists of
 * data source selection, parameter adjustment and a final statement screening.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: StatementTestWizard.java 89 2010-08-18 16:27:23Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StatementTestWizard extends Wizard {
  final static String WIZARD_TITLE = "New Statement Test";
  final static String WIZARD_ICON = "icons/database.png";
  final static String DATA_SOURCE_SELECTION_PAGE_ICON = "icons/database.png";
  final static String PARAMETER_ADJUSTMENT_PAGE_ICON = "icons/table.png";
  final static String STATEMENT_SCREENING_PAGE_ICON = "icons/exclamation.png";

  /**
   * The wizard pages.
   */
  private StatementTestWizardPage dataSourcePage, parameterAdjustmentPage,
      statementScreeningPage;

  /**
   * Instance of the data source service.
   */
  DatasourceService dataSourceService = null;

  /**
   * The statement test.
   */
  private StatementTest statementTest = null;

  /**
   * Initialize the wizard with the selected activity and the process.
   * 
   * @param activity
   * @param process
   */
  public StatementTestWizard(DataManagementActivity activity, Process process) {
    this.setHelpAvailable(false);
    this.setNeedsProgressMonitor(false);
    this.setWindowTitle(WIZARD_TITLE);
    this.setDefaultPageImageDescriptor(StatementTestPlugin
        .getImageDescriptor(WIZARD_ICON));

    this.statementTest = new StatementTest(activity, process);
    this.statementTest.setStatement(activity.getDsStatement());

    // set variables
    List<String> parameterVariableNames = VariableUtils
        .getParameterVariablesFromStatement(activity.getDsStatement(), process);
    List<String> containerVariablenames = VariableUtils
        .getContainerVariablesFromStatement(activity.getDsStatement(), process);

    for (String variableName : parameterVariableNames) {
      Variable variable = VariableUtils.getVariableByName(variableName, process);
      ParameterVariable parameterVariable = new ParameterVariable(variable);
      this.statementTest.getParameterVariables().put(variable, parameterVariable);
    }

    for (String variableName : containerVariablenames) {
      Variable variable = VariableUtils.getVariableByName(variableName, process);
      ContainerVariable containerVariable = new ContainerVariable(variable);
      this.statementTest.getContainerVariables().put(variable, containerVariable);
    }
  }

  @Override
  public void addPages() {
    dataSourcePage = new DataSourceSelectionPage("dataSourcePage", StatementTestPlugin
        .getImageDescriptor(DATA_SOURCE_SELECTION_PAGE_ICON));
    parameterAdjustmentPage = new ParameterAdjustmentPage("parameterAdjustmentPage",
        StatementTestPlugin.getImageDescriptor(PARAMETER_ADJUSTMENT_PAGE_ICON));
    statementScreeningPage = new StatementScreeningPage("statementScreeningPage",
        StatementTestPlugin.getImageDescriptor(STATEMENT_SCREENING_PAGE_ICON));

    addPage(dataSourcePage);
    addPage(parameterAdjustmentPage);
    addPage(statementScreeningPage);
  }

  @Override
  public boolean canFinish() {
    boolean canFinish = false;

    if (this.getContainer().getCurrentPage() == statementScreeningPage) {
      canFinish = true;
    } else {
      canFinish = false;
    }

    return canFinish;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    boolean isSimplCoreAvailable = true;
    boolean retry = true;

    // display wait cursor
    this.getShell().setCursor(new Cursor(this.getShell().getDisplay(), SWT.CURSOR_WAIT));

    try {
      // initialize the data source service client
      while (retry) {
        try {
          this.dataSourceService = DatasourceServiceClient.getService(CommunicationPlugIn.getDefault()
              .getPreferenceStore().getString("SIMPL_CORE_DSS_ADDRESS"));
          isSimplCoreAvailable = true;
          retry = false;
        } catch (WebServiceException e) {
          isSimplCoreAvailable = false;

          // retry dialog
          retry = MessageDialog.openQuestion(getShell(), "Execution failed.",
              "Could not connect to the SIMPL Core, do you want to retry?");
        }
      }

      // execute the generated statement
      if (isSimplCoreAvailable) {
        this.executeActivityStatement();
      } else {
        statementTest.log("Could not connect to the SIMPL Core.");
      }

      // open the statement test view
      IViewPart view = StatementTestPlugin.getDefault().getWorkbench()
          .getActiveWorkbenchWindow().getActivePage().showView(
              "org.eclipse.simpl.statementtest.ui.views.StatementTestView");

      // connect the view to the model
      StatementTestViewModel.getInstance().setView(view);

      // add the statement test to the view model
      StatementTestViewModel.getInstance().addStatementTest(this.statementTest);
    } catch (PartInitException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return true;
  }

  @Override
  public IWizardPage getNextPage(IWizardPage page) {
    String statement = this.statementTest.getActivity().getDsStatement();
    Process process = this.statementTest.getProcess();

    // generate the statement on the statement screening page before showing or
    // skip the parameter adjustment page if no variables are used within the statement
    if (page instanceof ParameterAdjustmentPage
        || (page instanceof DataSourceSelectionPage
            && VariableUtils.getContainerVariablesFromStatement(statement, process)
                .size() == 0 && VariableUtils.getParameterVariablesFromStatement(
            statement, process).size() == 0)) {
      ((StatementScreeningPage) this.statementScreeningPage).updateStatement();
      ((StatementScreeningPage) this.statementScreeningPage).updateDataSource();

      return this.statementScreeningPage;
    }

    return super.getNextPage(page);
  }

  /**
   * @return the statementTest
   */
  public StatementTest getStatementTest() {
    return statementTest;
  }

  /**
   * Executes the statement depending on the data source type and activity type.
   */
  private void executeActivityStatement() {
    DatabaseStatementExecution databaseActivityExecution = new DatabaseStatementExecution(
        this.statementTest, this.dataSourceService);
    FilesystemStatementExecution filesystemActivityExecution = new FilesystemStatementExecution(
        this.statementTest, this.dataSourceService);
    String activityType = statementTest.getActivity().eClass().getName();
    String dataSourceType = statementTest.getDataSource().getType();

    if (this.statementTest.getGeneratedStatement() != null) {
      // statement test log entries
      this.statementTest.log("= Statement test =");
      this.statementTest.log(this.statementTest.getActivity().eClass().getName() + ": "
          + this.statementTest.getActivity().getName());
      this.statementTest.log("Statement: " + this.statementTest.getGeneratedStatement());
      this.statementTest.log("Data source: "
          + this.statementTest.getDataSource().getName());

      // result options log entry
      List<String> resultOptions = new ArrayList<String>();

      if (this.statementTest.isLimitedResult()) {
        resultOptions.add("Limited");
      }

      if (this.statementTest.isExclusiveResult()) {
        resultOptions.add("Exclusive");
      }

      if (this.statementTest.isEnhancedResult()) {
        resultOptions.add("Enhanced");
      }

      if (!resultOptions.isEmpty()) {
        this.statementTest.log("Result Options: "
            + StringUtils.implode(resultOptions, ", "));
      }

      // execute database activity
      if (dataSourceType.equals("Database")) {
        if (activityType.equals("QueryActivity")
            || activityType.equals("TransferActivity")
            || activityType.equals("RetrieveDataActivity")) {
          databaseActivityExecution.executeQueryActivityStatement();
        } else if (activityType.equals("InsertActivity")) {
          databaseActivityExecution.executeInsertActivityStatement();
        } else if (activityType.equals("DeleteActivity")) {
          databaseActivityExecution.executeDeleteActivityStatement();
        } else if (activityType.equals("UpdateActivity")) {
          databaseActivityExecution.executeUpdateActivityStatement();
        } else if (activityType.equals("CreateActivity")) {
          databaseActivityExecution.executeCreateActivityStatement();
        } else if (activityType.equals("DropActivity")) {
          databaseActivityExecution.executeDropActivityStatement();
        }
      }

      // execute filesystem activity
      if (dataSourceType.equals("Filesystem")) {
        if (activityType.equals("QueryActivity")
            || activityType.equals("TransferActivity")
            || activityType.equals("RetrieveActivity")) {
          filesystemActivityExecution.executeQueryActivityStatement();
        } else if (activityType.equals("DeleteActivity")) {
          filesystemActivityExecution.executeDeleteActivityStatement();
        }
      }
    } else {
      this.statementTest.log("No statement is set on the activity.");
    }
  }
}