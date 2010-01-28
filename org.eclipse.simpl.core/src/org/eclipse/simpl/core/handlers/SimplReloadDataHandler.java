package org.eclipse.simpl.core.handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;


public class SimplReloadDataHandler extends AbstractHandler {

	
	public SimplReloadDataHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//Laden die Daten des SIMPL Core Plug-Ins neu
		org.eclipse.simpl.core.Application.getInstance().initApplication();
		
		//Laden die Daten des BPEL-DM Plug-Ins neu
		org.eclipse.bpel.simpl.ui.properties.Constants.init();

		return null;
	}

}
