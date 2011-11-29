package org.eclipse.simpl.core.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.simpl.core.Application;
import org.eclipse.simpl.core.ui.AdminConsoleUI;
import org.eclipse.swt.widgets.Display;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SimplAdminConsoleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SimplAdminConsoleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if (Application.getInstance().isSIMPLCoreAvailable() && Application.getInstance().isDataLoaded()) {
			if (!Application.getInstance().isAdminConsoleOpen()){
				new AdminConsoleUI();
			}
		} else {
			MessageDialog
					.openError(
							Display.getCurrent().getActiveShell(),
							"SIMPL Core Connection Exception",
							"The SIMPL Core isn't available. Please check if your Apache Tomcat Server is running and reload Plug-In data.");

		}
		return null;
	}
}
