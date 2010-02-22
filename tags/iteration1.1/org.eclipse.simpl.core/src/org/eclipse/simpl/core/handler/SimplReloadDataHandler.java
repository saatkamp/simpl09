package org.eclipse.simpl.core.handler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.simpl.core.Application;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;


public class SimplReloadDataHandler extends AbstractHandler {

	
	public SimplReloadDataHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//Laden die Daten des SIMPL Core Plug-Ins neu
		try {
			org.eclipse.simpl.core.Application.getInstance().initApplication();
			Application.getInstance().setDataLoaded(true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		final IHandlerService handlerService = (IHandlerService) PlatformUI
        .getWorkbench().getService(IHandlerService.class);
		
		try {
			handlerService.executeCommand("org.eclipse.bpel.simpl.ui.reloadDataCommand", null);
		} catch (NotDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotHandledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
