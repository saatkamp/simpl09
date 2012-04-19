package org.eclipse.bpel.simpl.ui.adapters;

import java.util.List;

import org.eclipse.bpel.ui.adapters.ActivityAdapter;
import org.eclipse.gef.EditPart;

/**
 * <b>Purpose:</b>Main adapter for all data management pattern.<br>
 * <b>Description:</b>In future it adds entries to the data management pattern's
 * context menu in the process.<br>
 * <b>Copyright:</b><br>
 * <b>Company:</b>SIMPL<br>
 * 
 */
public class DMPatternAdapter extends ActivityAdapter {
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public List getEditPartActions(final EditPart editPart) {
    List actions = super.getEditPartActions(editPart);
    // TODO add actions (if there are any)

    return actions;
  }
}
