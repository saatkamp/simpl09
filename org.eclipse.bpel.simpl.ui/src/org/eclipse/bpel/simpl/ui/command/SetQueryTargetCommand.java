package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

public class SetQueryTargetCommand extends SetCommand {

	public SetQueryTargetCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	@Override
	public Object get() {
		if (fTarget instanceof QueryActivity){
			return ((QueryActivity) fTarget).getDsAddress();
		}
		
		throw new IllegalArgumentException("This model object has no variable to get");
	}

	@Override
	public void set(Object o) {
		if (fTarget instanceof QueryActivity) {
			((QueryActivity) fTarget).setDsAddress((String) o);
		} else {
			throw new IllegalArgumentException(
					"This model object has no variable to get");
		}
	}
	
}
