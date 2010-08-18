package org.eclipse.simpl.statementtest.ui.wizards;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * <b>Purpose:</b>Launcher for the wizard that is used from the triggered action from the
 * test button in the property sections as well as from the context menu.<br>
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
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    StatementTestWizard statementTestWizard = null;
    WizardDialog dialog = null;

    // data source type filtering with message dialogs
    if (dmActivity.getDsType().equals("Database")
        || dmActivity.getDsType().equals("Filesystem")) {
      statementTestWizard = new StatementTestWizard(dmActivity, process);
      statementTestWizard.setHelpAvailable(false);

      // create wizard
      dialog = new WizardDialog(window.getShell(), statementTestWizard);

      // hide help controls
      WizardDialog.setDialogHelpAvailable(false);
      dialog.setHelpAvailable(false);

      // open wizard
      dialog.create();
      dialog.open();
    } else if (dmActivity.getDsType().equals("type")) {
      MessageDialog.openInformation(window.getShell(), "No data source assigned.",
          "Please assign a data source in the activity 'Properties' section.");

    } else {
      MessageDialog.openInformation(window.getShell(), dmActivity.getDsType()
          + " is not supported.", "Statement tests for the data source type '"
          + dmActivity.getDsType() + "' are not supported yet.");
    }
  }
}