package org.eclipse.simpl.statementtest.actions;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.simpl.statementtest.StatementTestPlugin;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <b>Purpose:</b>Eclipse action for the context menu in the BPEL editor on multiple data
 * management activity selection to test their statements.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * TODO: zum Laufen bringen im BPEL Editor, notfalls direkt in
 * org.eclipse.bpel.ui.BPELEditor
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: OpenStatementTestWizardSelectionAction.java 51 2010-06-29 18:21:35Z hiwi
 *          $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class OpenStatementTestWizardSelectionAction extends SelectionAction {
  /**
   * @param part
   */
  public OpenStatementTestWizardSelectionAction(IWorkbenchPart part) {
    super(part);
    setText("Test Statements");
    setToolTipText("Test Statements");
    setImageDescriptor(StatementTestPlugin.getImageDescriptor("icons/flask16.png"));
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
   */
  @Override
  protected boolean calculateEnabled() {
    return true;
  }
}