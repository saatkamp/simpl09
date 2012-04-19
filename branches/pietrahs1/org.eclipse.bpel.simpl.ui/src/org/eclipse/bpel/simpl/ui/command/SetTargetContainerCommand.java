package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

public class SetTargetContainerCommand extends SetCommand {
  /**
   * Instantiates a new SetTargetContainerCommand.
   * 
   * @param aTarget
   *          the target element
   * @param aValue
   *          the value to set
   */
  public SetTargetContainerCommand(EObject aTarget, Object aValue) {
    super(aTarget, aValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.ui.commands.SetCommand#get()
   */
  @Override
  public Object get() {
    if (fTarget instanceof QueryDataActivity) {
      return ((QueryDataActivity) fTarget).getTargetContainer();
    }

    if (fTarget instanceof WriteDataBackActivity) {
      return ((WriteDataBackActivity) fTarget).getTargetContainer();
    }

    throw new IllegalArgumentException(
        "This model object has no target container to get");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
   */
  @Override
  public void set(Object o) {
    if (fTarget instanceof QueryDataActivity) {
      ((QueryDataActivity) fTarget).setTargetContainer((String) o);
    } else if (fTarget instanceof WriteDataBackActivity) {
      ((WriteDataBackActivity) fTarget).setTargetContainer((String) o);
    } else {
      throw new IllegalArgumentException(
          "This model object has no target container to set");
    }
  }
}
