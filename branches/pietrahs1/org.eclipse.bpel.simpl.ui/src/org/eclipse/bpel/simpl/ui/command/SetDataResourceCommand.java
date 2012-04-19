package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

public class SetDataResourceCommand extends SetCommand {
  /**
   * Instantiates a new SetDataResourceCommand.
   * 
   * @param aTarget
   *          the target element
   * @param aValue
   *          the value to set
   */
  public SetDataResourceCommand(EObject aTarget, Object aValue) {
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
      return ((QueryDataActivity) fTarget).getDataResource();
    }

    if (fTarget instanceof IssueCommandActivity) {
      return ((IssueCommandActivity) fTarget).getDataResource();
    }

    if (fTarget instanceof RetrieveDataActivity) {
      return ((RetrieveDataActivity) fTarget).getDataResource();
    }

    if (fTarget instanceof WriteDataBackActivity) {
      return ((WriteDataBackActivity) fTarget).getDataResource();
    }

    if (fTarget instanceof DataManagementActivity) {
      return ((DataManagementActivity) fTarget).getDataResource();
    }

    throw new IllegalArgumentException(
        "This model object has no data resource to get");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
   */
  @Override
  public void set(Object o) {
    if (fTarget instanceof QueryDataActivity) {
      ((QueryDataActivity) fTarget).setDataResource((String) o);

    } else if (fTarget instanceof IssueCommandActivity) {
      ((IssueCommandActivity) fTarget).setDataResource((String) o);
    } else if (fTarget instanceof RetrieveDataActivity) {
      ((RetrieveDataActivity) fTarget).setDataResource((String) o);
    } else if (fTarget instanceof WriteDataBackActivity) {
      ((WriteDataBackActivity) fTarget).setDataResource((String) o);
    } else if (fTarget instanceof DataManagementActivity) {
      ((DataManagementActivity) fTarget).setDataResource((String) o);
    } else {
      throw new IllegalArgumentException(
          "This model object has no data resource to set");
    }
  }
}
