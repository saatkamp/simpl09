package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

public class SetDataSinkContainerCommand extends SetCommand {
  /**
   * Instantiates a new SetDataSinkContainerCommand.
   * 
   * @param aTarget
   *          the target element
   * @param aValue
   *          the value to set
   */
  public SetDataSinkContainerCommand(EObject aTarget, Object aValue) {
    super(aTarget, aValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.ui.commands.SetCommand#get()
   */
  @Override
  public Object get() {
    if (fTarget instanceof TransferDataActivity) {
      return ((TransferDataActivity) fTarget).getDataSinkContainer();
    }

    throw new IllegalArgumentException(
        "This model object has no data sink container to get");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
   */
  @Override
  public void set(Object o) {
    if (fTarget instanceof TransferDataActivity) {
      ((TransferDataActivity) fTarget).setDataSinkContainer((String) o);
    } else {
      throw new IllegalArgumentException(
          "This model object has no data sink container to set");
    }
  }
}
