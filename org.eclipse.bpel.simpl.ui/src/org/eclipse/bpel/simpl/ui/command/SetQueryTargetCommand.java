/**
 * <b>Purpose:</b> Implements a new {@link SetCommand} to set the target of the query in the model of a {@link QueryActivity}.<br>
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

import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SetQueryTargetCommand.
 */
public class SetQueryTargetCommand extends SetCommand {

	/**
	 * Instantiates a new SetQueryTargetCommand.
	 * 
	 * @param aTarget
	 *            the target element
	 * @param aValue
	 *            the value to set
	 */
	public SetQueryTargetCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#get()
	 */
	@Override
	public Object get() {
		if (fTarget instanceof QueryActivity){
			return ((QueryActivity) fTarget).getQueryTarget();
		}
		
		throw new IllegalArgumentException("This model object has no variable to get");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryActivity) {
			((QueryActivity) fTarget).setQueryTarget((String) o);
		} else {
			throw new IllegalArgumentException(
					"This model object has no query target to get");
		}
	}
	
}
