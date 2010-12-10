/**
 * <b>Purpose:</b> Implements a new {@link SetCommand} to set the kind of datasource in the model of a {@link DataManagementActivity} subclass.<br> <br>
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
import org.eclipse.bpel.simpl.model.IssueActivity;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SetDsKindCommand.
 */
public class SetDsKindCommand extends SetCommand {

	/**
	 * Instantiates a new SetDsKindCommand.
	 * 
	 * @param aTarget
	 *            the target element
	 * @param aValue
	 *            the value to set
	 */
	public SetDsKindCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#get()
	 */
	@Override
	public Object get() {
		if (fTarget instanceof QueryActivity){
			return ((QueryActivity) fTarget).getDsKind();
		}
		
		if (fTarget instanceof IssueActivity){
			return ((IssueActivity) fTarget).getDsKind();
		}
	
		if (fTarget instanceof RetrieveDataActivity){
			return ((RetrieveDataActivity) fTarget).getDsKind();
		}
	
	  if (fTarget instanceof WriteDataBackActivity){
      return ((WriteDataBackActivity) fTarget).getDsKind();
    }
		
		if (fTarget instanceof DataManagementActivity){
			return ((DataManagementActivity) fTarget).getDsKind();
		}
		
		throw new IllegalArgumentException("This model object has no kind to get");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryActivity) {
			((QueryActivity) fTarget).setDsKind((String) o);
			
		} else if (fTarget instanceof IssueActivity) {
			((IssueActivity) fTarget).setDsKind((String) o);
		} else if (fTarget instanceof RetrieveDataActivity) {
      ((RetrieveDataActivity) fTarget).setDsKind((String) o);
    } else if (fTarget instanceof WriteDataBackActivity) {
			((WriteDataBackActivity) fTarget).setDsKind((String) o);
		} else if (fTarget instanceof DataManagementActivity) {
			((DataManagementActivity) fTarget).setDsKind((String) o);
		} else {
			throw new IllegalArgumentException(
					"This model object has no kind to set");
		}
	}
	
}
