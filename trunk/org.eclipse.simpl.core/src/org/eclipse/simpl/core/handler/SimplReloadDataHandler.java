package org.eclipse.simpl.core.handler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.simpl.core.Application;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;


public class SimplReloadDataHandler extends AbstractHandler {

	private boolean success = false;
	
	public SimplReloadDataHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
			org.eclipse.simpl.core.Application.getInstance().initApplication();
			Application.getInstance().setDataLoaded(true);
			
			success = true;
		} catch (Exception e) {
			success = false;
		}
		
		final IHandlerService handlerService = (IHandlerService) PlatformUI
        .getWorkbench().getService(IHandlerService.class);
		
		try {
			handlerService.executeCommand("org.eclipse.bpel.apache.ode.deployl.ui.reloadSIMPLDataCommand", null);
		} catch (Exception e) {
			success = false;
		}

		if (success){
			MessageDialog
			.openInformation(
					Display.getCurrent().getActiveShell(),
					"SIMPL Core Data loaded",
					"The SIMPL Core data was loaded successfully.");
		}else {
			MessageDialog
			.openError(
					Display.getCurrent().getActiveShell(),
					"SIMPL Core Connection Exception",
					"The SIMPL Core isn't available. Please check if your Apache Tomcat Server is running and reload Plug-In data.");
		}
		
		return null;
	}

}
