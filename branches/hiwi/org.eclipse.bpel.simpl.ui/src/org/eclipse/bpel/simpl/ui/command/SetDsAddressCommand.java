/**
 * <b>Purpose:</b> Implements a new {@link SetCommand} to set the address of the datasource in the model of a {@link DataManagementActivity} subclass.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SetDsAddressCommand.
 */
public class SetDsAddressCommand extends SetCommand {

	/**
	 * Instantiates a new SetDsAddressCommand.
	 * 
	 * @param aTarget
	 *            the target element
	 * @param aValue
	 *            the value to set
	 */
	public SetDsAddressCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#get()
	 */
	@Override
	public Object get() {
		if (fTarget instanceof QueryDataActivity){
			return ((QueryDataActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof IssueCommandActivity){
			return ((IssueCommandActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof RetrieveDataActivity){
			return ((RetrieveDataActivity) fTarget).getDsAddress();
		}

    if (fTarget instanceof WriteDataBackActivity){
      return ((WriteDataBackActivity) fTarget).getDsAddress();
    }
		
		if (fTarget instanceof DataManagementActivity){
			return ((DataManagementActivity) fTarget).getDsAddress();
		}
		
		throw new IllegalArgumentException("This model object has no address to get");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryDataActivity) {
			((QueryDataActivity) fTarget).setDsAddress((String) o);
			
		} else if (fTarget instanceof IssueCommandActivity) {
			((IssueCommandActivity) fTarget).setDsAddress((String) o);
		} else if (fTarget instanceof RetrieveDataActivity) {
      ((RetrieveDataActivity) fTarget).setDsAddress((String) o);
    }  else if (fTarget instanceof WriteDataBackActivity) {
			((WriteDataBackActivity) fTarget).setDsAddress((String) o);
		} else if (fTarget instanceof DataManagementActivity) {
			((DataManagementActivity) fTarget).setDsAddress((String) o);
		} else {
			throw new IllegalArgumentException(
					"This model object has no address to set");
		}
	}
	
}
