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
			return ((QueryActivity) fTarget).getQueryTarget();
		}
		
		throw new IllegalArgumentException("This model object has no variable to get");
	}

	@Override
	public void set(Object o) {
		//FIXME: Die Änderung wird zwar ins Modell übertragen, aber nicht
		//im BPEL Designer Source-View durchgeführt bzw. angezeigt!
		if (fTarget instanceof QueryActivity) {
			((QueryActivity) fTarget).setQueryTarget((String) o);
		} else {
			throw new IllegalArgumentException(
					"This model object has no variable to get");
		}
	}
	
}
