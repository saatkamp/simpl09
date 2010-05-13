/**
 * <b>Purpose:</b> Implements a new {@link SetCommand} to set the address of the datasource in the model of a {@link DataManagementActivity} subclass.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: SetDsAddressCommand.java 1363 2010-05-13 10:09:45Z hahnml@t-online.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.TransferActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

// TODO: Auto-generated Javadoc
/**
 * The Class SetDsAddressCommand.
 */
public class SetDmActivityCommand extends SetCommand {

	/**
	 * Instantiates a new SetDsAddressCommand.
	 * 
	 * @param aTarget
	 *            the target element
	 * @param aValue
	 *            the value to set
	 * @param eReference 
	 */
	public SetDmActivityCommand(EObject aTarget, Object aValue, EReference eReference) {
		super(aTarget, aValue, eReference);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#get()
	 */
	@Override
	public Object get() {
		if (fTarget instanceof TransferActivity){
			return ((TransferActivity) fTarget).getToSource();
		}
		
		throw new IllegalArgumentException("This model object has no data management activity to get");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof TransferActivity) {
			((TransferActivity) fTarget).setToSource((DataManagementActivity) o);
			
		} else {
			throw new IllegalArgumentException(
					"This model object has no data management activity to set");
		}
	}
	
}
