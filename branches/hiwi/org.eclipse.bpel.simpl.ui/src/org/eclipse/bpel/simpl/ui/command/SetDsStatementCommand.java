/**
 * <b>Purpose:</b> Implements a new {@link SetCommand} to set the statement to execute in the model of a {@link DataManagementActivity} subclass.<br> <br>
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
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SetDsStatementCommand.
 */
public class SetDsStatementCommand extends SetCommand {

	/**
	 * Instantiates a new SetDsStatementCommand.
	 * 
	 * @param aTarget
	 *            the target element
	 * @param aValue
	 *            the value to set
	 */
	public SetDsStatementCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#get()
	 */
	@Override
	public Object get() {
		if (fTarget instanceof QueryDataActivity){
			return ((QueryDataActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof IssueCommandActivity){
			return ((IssueCommandActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof RetrieveDataActivity){
			return ((RetrieveDataActivity) fTarget).getDsStatement();
		}

    if (fTarget instanceof WriteDataBackActivity){
      return ((WriteDataBackActivity) fTarget).getDsStatement();
    }
		
		if (fTarget instanceof TransferDataActivity){
			return ((TransferDataActivity) fTarget).getDsStatement();
		}
		
    if (fTarget instanceof DataManagementActivity) {
      return ((DataManagementActivity) fTarget).getDsType();
    }
		
		throw new IllegalArgumentException("This model object has no statement to get");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryDataActivity) {
			((QueryDataActivity) fTarget).setDsStatement((String) o);
			
		} else if (fTarget instanceof IssueCommandActivity) {
			((IssueCommandActivity) fTarget).setDsStatement((String) o);
		} else if (fTarget instanceof RetrieveDataActivity) {
			((RetrieveDataActivity) fTarget).setDsStatement((String) o);
		} else if (fTarget instanceof WriteDataBackActivity) {
      ((WriteDataBackActivity) fTarget).setDsStatement((String) o);
    }  else if (fTarget instanceof TransferDataActivity) {
			((TransferDataActivity) fTarget).setDsStatement((String) o);
		} else {
			throw new IllegalArgumentException(
					"This model object has no statement to set");
		}
	}
	
}
