package org.eclipse.simpl.settings.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.simpl.settings.ui.SettingsUI;

public class SimplSettingsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Settings", "The menu item 'Settings' was pushed");
		new SettingsUI();
		return null;
	}

}
