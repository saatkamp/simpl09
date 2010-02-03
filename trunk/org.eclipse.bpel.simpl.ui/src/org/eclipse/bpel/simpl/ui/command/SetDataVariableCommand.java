package org.eclipse.bpel.simpl.ui.command;

import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.emf.ecore.EObject;

public class SetDataVariableCommand extends SetCommand {

	public SetDataVariableCommand(EObject aTarget, Object aValue) {
		super(aTarget, aValue);
	}

	@Override
	public Object get() {
		if (fTarget instanceof RetrieveDataActivity) {
			// TODO: Abklären, ob hier nur der Name (String) oder das
			// tatsächliche Objekt (Variable) übergeben werden muss.
			return ((RetrieveDataActivity) fTarget).getDataVariable();
		}

		throw new IllegalArgumentException(
				"This model object has no variable to get");
	}

	@Override
	public void set(Object o) {
		if (fTarget instanceof RetrieveDataActivity) {
			// TODO: Abklären, ob hier nur der Name (String) oder das
			// tatsächliche Objekt (Variable) übergeben werden muss.
			((RetrieveDataActivity) fTarget).setDataVariable((Variable) o);
		} else {
			throw new IllegalArgumentException(
					"This model object has no variable to get");
		}
	}

}
