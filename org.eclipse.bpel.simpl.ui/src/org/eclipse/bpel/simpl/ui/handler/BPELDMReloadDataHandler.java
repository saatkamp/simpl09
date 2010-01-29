package org.eclipse.bpel.simpl.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class BPELDMReloadDataHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//Laden die Daten des BPEL-DM Plug-Ins neu
		org.eclipse.bpel.simpl.ui.properties.Constants.init();
		
		return null;
	}

}
