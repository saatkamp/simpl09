/**
 * <b>Purpose:</b> Implements a new {@link SetCommand} to set a dataVariable in the model of a {@link RetrieveDataActivity}<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: SetDataVariableCommand.java 1757 2011-01-22 10:57:20Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SetDataVariableCommand.
 */
public class SetDataVariableCommand extends SetCommand {

	/**
	 * Instantiates a SetDataVariableCommand.
	 * 
	 * @param aTarget
	 *            the target element
	 * @param aValue
	 *            the value to set
	 */
	public SetDataVariableCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#get()
	 */
	@Override
	public Object get() {
		if (fTarget instanceof RetrieveDataActivity) {
			return ((RetrieveDataActivity) fTarget).getDataVariable();
		} else if (fTarget instanceof WriteDataBackActivity) {
      return ((WriteDataBackActivity) fTarget).getDataVariable();
    }
		
		throw new IllegalArgumentException(
				"This model object has no variable to get");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
    if (fTarget instanceof RetrieveDataActivity) {
      ((RetrieveDataActivity) fTarget).setDataVariable((Variable) o);
    } else if (fTarget instanceof WriteDataBackActivity) {
      ((WriteDataBackActivity) fTarget).setDataVariable((Variable) o);
    } else {
      throw new IllegalArgumentException("This model object has no variable to set");
    }
	}

}
