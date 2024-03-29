/**
 * <b>Purpose:</b> Implements a new {@link SetCommand} to set the type of the datasource in the model of a {@link DataManagementActivity} subclass.<br><br>
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

import org.eclipse.bpel.simpl.model.CallActivity;
import org.eclipse.bpel.simpl.model.CreateActivity;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.DeleteActivity;
import org.eclipse.bpel.simpl.model.DropActivity;
import org.eclipse.bpel.simpl.model.InsertActivity;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.UpdateActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SetDsTypeCommand.
 */
public class SetDsTypeCommand extends SetCommand {

	/**
	 * Instantiates a new SetDsTypeCommand.
	 * 
	 * @param aTarget
	 *            the target element
	 * @param aValue
	 *            the value to set
	 */
	public SetDsTypeCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.bpel.ui.commands.SetCommand#get()
	 */
	@Override
	public Object get() {
		if (fTarget instanceof QueryActivity) {
			return ((QueryActivity) fTarget).getDsType();
		}

		if (fTarget instanceof InsertActivity) {
			return ((InsertActivity) fTarget).getDsType();
		}

		if (fTarget instanceof UpdateActivity) {
			return ((UpdateActivity) fTarget).getDsType();
		}

		if (fTarget instanceof DeleteActivity) {
			return ((DeleteActivity) fTarget).getDsType();
		}

		if (fTarget instanceof CreateActivity) {
			return ((CreateActivity) fTarget).getDsType();
		}

		if (fTarget instanceof DropActivity) {
			return ((DropActivity) fTarget).getDsType();
		}

		if (fTarget instanceof CallActivity) {
			return ((CallActivity) fTarget).getDsType();
		}

		if (fTarget instanceof RetrieveDataActivity) {
			return ((RetrieveDataActivity) fTarget).getDsType();
		}

		if (fTarget instanceof DataManagementActivity) {
			return ((DataManagementActivity) fTarget).getDsType();
		}

		throw new IllegalArgumentException(
				"This model object has no type to get");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryActivity) {
			((QueryActivity) fTarget).setDsType((String) o);

		} else if (fTarget instanceof InsertActivity) {
			((InsertActivity) fTarget).setDsType((String) o);

		} else if (fTarget instanceof UpdateActivity) {
			((UpdateActivity) fTarget).setDsType((String) o);

		} else if (fTarget instanceof DeleteActivity) {
			((DeleteActivity) fTarget).setDsType((String) o);

		} else if (fTarget instanceof CreateActivity) {
			((CreateActivity) fTarget).setDsType((String) o);

		} else if (fTarget instanceof DropActivity) {
			((DropActivity) fTarget).setDsType((String) o);

		} else if (fTarget instanceof CallActivity) {
			((CallActivity) fTarget).setDsType((String) o);

		} else if (fTarget instanceof RetrieveDataActivity) {
			((RetrieveDataActivity) fTarget).setDsType((String) o);
			
		} else if (fTarget instanceof DataManagementActivity) {
			((DataManagementActivity) fTarget).setDsType((String) o);
			
		} else {
			throw new IllegalArgumentException(
					"This model object has no type to set");
		}
	}

}
