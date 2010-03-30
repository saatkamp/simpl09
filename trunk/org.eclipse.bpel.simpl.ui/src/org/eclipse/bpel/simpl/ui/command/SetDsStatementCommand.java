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
		if (fTarget instanceof QueryActivity){
			return ((QueryActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof InsertActivity){
			return ((InsertActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof UpdateActivity){
			return ((UpdateActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof DeleteActivity){
			return ((DeleteActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof CreateActivity){
			return ((CreateActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof DropActivity){
			return ((DropActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof CallActivity){
			return ((CallActivity) fTarget).getDsStatement();
		}
		
		if (fTarget instanceof RetrieveDataActivity){
			return ((RetrieveDataActivity) fTarget).getDsStatement();
		}
		
		throw new IllegalArgumentException("This model object has no variable to get");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.commands.SetCommand#set(java.lang.Object)
	 */
	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryActivity) {
			((QueryActivity) fTarget).setDsStatement((String) o);
			
		} else if (fTarget instanceof InsertActivity) {
			((InsertActivity) fTarget).setDsStatement((String) o);
			
		} else if (fTarget instanceof UpdateActivity) {
			((UpdateActivity) fTarget).setDsStatement((String) o);
			
		} else if (fTarget instanceof DeleteActivity) {
			((DeleteActivity) fTarget).setDsStatement((String) o);
			
		} else if (fTarget instanceof CreateActivity) {
			((CreateActivity) fTarget).setDsStatement((String) o);
			
		} else if (fTarget instanceof DropActivity) {
			((DropActivity) fTarget).setDsStatement((String) o);
			
		} else if (fTarget instanceof CallActivity) {
			((CallActivity) fTarget).setDsStatement((String) o);
			
		} else if (fTarget instanceof RetrieveDataActivity) {
			((RetrieveDataActivity) fTarget).setDsStatement((String) o);
			
		} else {
			throw new IllegalArgumentException(
					"This model object has no variable to get");
		}
	}
	
}
