package org.eclipse.bpel.simpl.ui.adapters;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.ui.adapters.ContainerActivityAdapter;
import org.eclipse.bpel.ui.adapters.IContainer;
import org.eclipse.bpel.ui.adapters.delegates.ActivityContainer;
import org.eclipse.bpel.ui.editparts.SequenceEditPart;
import org.eclipse.gef.EditPart;

/**
 * The Class DataIterationPatternAdapter.
 * 
 */
public class DataIterationPatternAdapter extends ContainerActivityAdapter {

  @SuppressWarnings("rawtypes")
  @Override
  protected IContainer createContainerDelegate() {
    // TODO Auto-generated method stub
    return new ActivityContainer(
        ModelPackage.eINSTANCE.getDataIterationPattern_Activity());
  }

  @Override
  public EditPart createEditPart(EditPart context, Object model) {
    EditPart result = new SequenceEditPart();
    result.setModel(model);
    return result;
  }
}
