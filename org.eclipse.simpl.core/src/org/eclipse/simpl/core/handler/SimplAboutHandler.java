package org.eclipse.simpl.core.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.simpl.core.ui.AboutWindow;
import org.eclipse.swt.widgets.Display;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SimplAboutHandler extends AbstractHandler {
	
	/**
	 * The constructor.
	 */
	public SimplAboutHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		AboutWindow about = new AboutWindow(Display.getCurrent().getActiveShell());
		about.open();
		return null;
	}

}
