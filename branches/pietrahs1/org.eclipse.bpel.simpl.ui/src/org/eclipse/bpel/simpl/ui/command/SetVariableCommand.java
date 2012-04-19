package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

public class SetVariableCommand extends SetCommand {

  /**
   * Instantiates a SetVariableCommand.
   * 
   * @param aTarget
   *          the target element
   * @param aValue
   *          the value to set
   */
  public SetVariableCommand(EObject aTarget, Object aValue) {
    super(aTarget, aValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.ui.commands.SetCommand#get()
   */
  @Override
  public Object get() {
    if (fTarget instanceof RetrieveDataActivity) {
      return ((RetrieveDataActivity) fTarget).getTargetVariable();
    } else if (fTarget instanceof WriteDataBackActivity) {
      return ((WriteDataBackActivity) fTarget).getDataVariable();
    }

    throw new IllegalArgumentException(
        "This model object has no variable to get");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
   */
  @Override
  public void set(Object o) {
    if (fTarget instanceof RetrieveDataActivity) {
      ((RetrieveDataActivity) fTarget).setTargetVariable((Variable) o);
    } else if (fTarget instanceof WriteDataBackActivity) {
      ((WriteDataBackActivity) fTarget).setDataVariable((Variable) o);
    } else {
      throw new IllegalArgumentException(
          "This model object has no variable to set");
    }
  }
}
