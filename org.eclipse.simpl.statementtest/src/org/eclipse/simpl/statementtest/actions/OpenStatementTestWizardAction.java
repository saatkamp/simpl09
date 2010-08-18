package org.eclipse.simpl.statementtest.actions;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.ui.actions.editpart.AbstractAction;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.simpl.statementtest.StatementTestPlugin;
import org.eclipse.simpl.statementtest.ui.wizards.WizardLauncher;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * <b>Purpose:</b>Eclipse action for the context menu in the BPEL editor on a selected
 * data management activity to test its statement.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: OpenStatementTestWizardAction.java 51 2010-06-29 18:21:35Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class OpenStatementTestWizardAction extends AbstractAction {
  DataManagementActivity selectedDMActivity = null;

  /**
   * @param anEditPart
   */
  public OpenStatementTestWizardAction(EditPart anEditPart,
      DataManagementActivity dmActivity) {
    super(anEditPart);
    this.selectedDMActivity = dmActivity;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.bpel.ui.actions.editpart.IEditPartAction#getIcon()
   */
  @Override
  public ImageDescriptor getIcon() {
    return StatementTestPlugin.getImageDescriptor("icons/flask16.png");
  }

  public Image getIconImg() {
    Image iconImage = new Image(Display.getDefault(), StatementTestPlugin
        .getImageDescriptor("icons/flask16.png").getImageData());

    return iconImage;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.bpel.ui.actions.editpart.IEditPartAction#getToolTip()
   */
  @Override
  public String getToolTip() {
    return "Test Statement";
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.bpel.ui.actions.editpart.IEditPartAction#onButtonPressed()
   */
  @Override
  public boolean onButtonPressed() {
    // access the active BPEL process to catch the necessary information that is used to
    // later get a list of available data sources
    Process process = null;

    if (editPart.getParent().getParent().getModel() instanceof org.eclipse.bpel.model.Process) {
      process = (org.eclipse.bpel.model.Process) editPart.getParent().getParent()
          .getModel();
    }

    WizardLauncher.launch(selectedDMActivity, process);

    return true;
  }

  @Override
  public ImageDescriptor getDisabledIcon() {
    return ImageDescriptor.getMissingImageDescriptor();
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}