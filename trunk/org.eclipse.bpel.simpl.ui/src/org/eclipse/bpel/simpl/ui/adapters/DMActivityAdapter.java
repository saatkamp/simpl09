package org.eclipse.bpel.simpl.ui.adapters;

import java.util.List;

import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.ui.adapters.ActivityAdapter;
import org.eclipse.gef.EditPart;
import org.eclipse.simpl.statementtest.actions.OpenStatementTestWizardAction;

/**
 * <b>Purpose:</b>Main adapter for all data management activities.<br>
 * <b>Description:</b>Adds a "Test Statement" entry to the data management activitie's
 * context menu in the process.<br>
 * <b>Copyright:</b><br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DMActivityAdapter extends ActivityAdapter {
  @SuppressWarnings("unchecked")
  @Override
  public List getEditPartActions(final EditPart editPart) {
    List actions = super.getEditPartActions(editPart);
    DataManagementActivity dmActivity = (DataManagementActivity) editPart.getModel();

    actions.add(new OpenStatementTestWizardAction(editPart, dmActivity));

    return actions;
  }
}