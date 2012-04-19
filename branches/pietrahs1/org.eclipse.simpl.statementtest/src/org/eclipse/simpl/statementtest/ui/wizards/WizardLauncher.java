package org.eclipse.simpl.statementtest.ui.wizards;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.simpl.statementtest.types.DMActivityTypes;
import org.eclipse.simpl.statementtest.types.DataSourceTypes;
import org.eclipse.simpl.statementtest.utils.DataSourceUtils;
import org.eclipse.simpl.statementtest.utils.IssueRecognition;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose:</b>Launcher for the wizard that is used from the triggered action
 * from the test button in the property sections as well as from the context
 * menu.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: WizardLauncher.java 89 2010-08-18 16:27:23Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class WizardLauncher {
  public static void launch(DataManagementActivity dmActivity, Process process) {
    IWorkbenchWindow window = PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow();
    StatementTestWizard statementTestWizard = null;
    WizardDialog dialog = null;
    String issue = null;

    DataSource activityDataSource = null;
    String dmCommand = null;

    if ((dmActivity.getDataResource() != null && dmActivity.getDataResource()
        .equals(""))
        || (dmActivity instanceof TransferDataActivity && ((TransferDataActivity) dmActivity)
            .getDataSource().equals(""))) {
      MessageDialog.openInformation(window.getShell(),
          "Activity has no data source.",
          "Please select a data source in the activity details.");
    } else {
      if (dmActivity instanceof TransferDataActivity) {
        activityDataSource = DataSourceUtils.findDataSourceByName(process,
            ((TransferDataActivity) dmActivity).getDataSource());
        dmCommand = ((TransferDataActivity) dmActivity).getDataSourceCommand();
      } else {
        activityDataSource = DataSourceUtils.findDataSourceByName(process,
            dmActivity.getDataResource());
        dmCommand = dmActivity.getDmCommand();
      }
      // data source type filtering with message dialogs
      if (activityDataSource == null) {
        MessageDialog.openInformation(window.getShell(),
            "Activity has no valid data source.",
            "The data source of the activity couldn't be resolved.");
      } else if (dmCommand == null || dmCommand.equals("")) {
        MessageDialog.openInformation(window.getShell(),
            "No valid DM Command to execute.",
            "Please create a valid DM Command.");
      } else if (activityDataSource.getType().equals(DataSourceTypes.DATABASE)
          || activityDataSource.getType().equals(DataSourceTypes.FILESYSTEM)) {
        statementTestWizard = new StatementTestWizard(dmActivity, process,
            activityDataSource, dmCommand);
        statementTestWizard.setHelpAvailable(false);

        // create wizard
        dialog = new WizardDialog(window.getShell(), statementTestWizard);

        // hide help controls
        WizardDialog.setDialogHelpAvailable(false);
        dialog.setHelpAvailable(false);

        // recognize the issue of the statement
        if (dmActivity.eClass().getName()
            .equals(DMActivityTypes.ISSUE_COMMAND_ACTIVITY)) {
          issue = IssueRecognition.getInstance().recognizeIssue(
              activityDataSource.getLanguage(), dmActivity.getDmCommand());

          if (issue != null) {
            statementTestWizard.getStatementTest().setIssue(issue);
          }
        }

        // open wizard
        dialog.create();
        dialog.open();
      } else {
        MessageDialog.openInformation(
            window.getShell(),
            activityDataSource.getType() + " is not supported.",
            "Statement tests for the data source type '"
                + activityDataSource.getType() + "' are not supported yet.");
      }
    }
  }
}