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

import org.eclipse.bpel.simpl.model.CallActivity;
import org.eclipse.bpel.simpl.model.CreateActivity;
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
		
		if (fTarget instanceof InsertActivity){
			return ((InsertActivity) fTarget).getDsKind();
		}
		
		if (fTarget instanceof UpdateActivity){
			return ((UpdateActivity) fTarget).getDsKind();
		}
		
		if (fTarget instanceof DeleteActivity){
			return ((DeleteActivity) fTarget).getDsKind();
		}
		
		if (fTarget instanceof CreateActivity){
			return ((CreateActivity) fTarget).getDsKind();
		}
		
		if (fTarget instanceof DropActivity){
			return ((DropActivity) fTarget).getDsKind();
		}
		
		if (fTarget instanceof CallActivity){
			return ((CallActivity) fTarget).getDsKind();
		}
		
		if (fTarget instanceof RetrieveDataActivity){
			return ((RetrieveDataActivity) fTarget).getDsKind();
		}
		
		throw new IllegalArgumentException("This model object has no variable to get");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryActivity) {
			((QueryActivity) fTarget).setDsKind((String) o);
			
		} else if (fTarget instanceof InsertActivity) {
			((InsertActivity) fTarget).setDsKind((String) o);
			
		} else if (fTarget instanceof UpdateActivity) {
			((UpdateActivity) fTarget).setDsKind((String) o);
			
		} else if (fTarget instanceof DeleteActivity) {
			((DeleteActivity) fTarget).setDsKind((String) o);
			
		} else if (fTarget instanceof CreateActivity) {
			((CreateActivity) fTarget).setDsKind((String) o);
			
		} else if (fTarget instanceof DropActivity) {
			((DropActivity) fTarget).setDsKind((String) o);
			
		} else if (fTarget instanceof CallActivity) {
			((CallActivity) fTarget).setDsKind((String) o);
			
		} else if (fTarget instanceof RetrieveDataActivity) {
			((RetrieveDataActivity) fTarget).setDsKind((String) o);
			
		} else {
			throw new IllegalArgumentException(
					"This model object has no variable to get");
		}
	}
	
}
