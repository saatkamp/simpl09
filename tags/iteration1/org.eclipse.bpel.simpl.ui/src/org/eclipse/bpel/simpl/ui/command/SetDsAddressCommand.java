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

public class SetDsAddressCommand extends SetCommand {

	public SetDsAddressCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	@Override
	public Object get() {
		if (fTarget instanceof QueryActivity){
			return ((QueryActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof InsertActivity){
			return ((InsertActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof UpdateActivity){
			return ((UpdateActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof DeleteActivity){
			return ((DeleteActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof CreateActivity){
			return ((CreateActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof DropActivity){
			return ((DropActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof CallActivity){
			return ((CallActivity) fTarget).getDsAddress();
		}
		
		if (fTarget instanceof RetrieveDataActivity){
			return ((RetrieveDataActivity) fTarget).getDsAddress();
		}
		
		throw new IllegalArgumentException("This model object has no variable to get");
	}

	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryActivity) {
			((QueryActivity) fTarget).setDsAddress((String) o);
			
		} else if (fTarget instanceof InsertActivity) {
			((InsertActivity) fTarget).setDsAddress((String) o);
			
		} else if (fTarget instanceof UpdateActivity) {
			((UpdateActivity) fTarget).setDsAddress((String) o);
			
		} else if (fTarget instanceof DeleteActivity) {
			((DeleteActivity) fTarget).setDsAddress((String) o);
			
		} else if (fTarget instanceof CreateActivity) {
			((CreateActivity) fTarget).setDsAddress((String) o);
			
		} else if (fTarget instanceof DropActivity) {
			((DropActivity) fTarget).setDsAddress((String) o);
			
		} else if (fTarget instanceof CallActivity) {
			((CallActivity) fTarget).setDsAddress((String) o);
			
		} else if (fTarget instanceof RetrieveDataActivity) {
			((RetrieveDataActivity) fTarget).setDsAddress((String) o);
			
		} else {
			throw new IllegalArgumentException(
					"This model object has no variable to get");
		}
	}
	
}
