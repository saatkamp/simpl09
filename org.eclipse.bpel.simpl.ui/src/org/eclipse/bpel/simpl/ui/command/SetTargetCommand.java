package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.simpl.model.TransferActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
public class SetTargetCommand extends SetCommand {

	/**
	 * Instantiates a new SetQueryTargetCommand.
	 * 
	 * @param aTarget
	 *            the target element
	 * @param aValue
	 *            the value to set
	 */
	public SetTargetCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#get()
	 */
	@Override
	public Object get() {
		if (fTarget instanceof TransferActivity){
			return ((TransferActivity) fTarget).getTarget();
		}
		
		throw new IllegalArgumentException("This model object has no target to get");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof TransferActivity) {
			((TransferActivity) fTarget).setTarget((String) o);
		} else {
			throw new IllegalArgumentException(
					"This model object has no target to set");
		}
	}
	
}