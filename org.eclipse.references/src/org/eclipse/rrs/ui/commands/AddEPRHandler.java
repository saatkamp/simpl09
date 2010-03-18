package org.eclipse.rrs.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.rrs.model.ModelProvider;
import org.eclipse.rrs.ui.dialogs.AddReferenceDialog;
import org.eclipse.rrs.ui.view.ReferenceManagementView;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class AddEPRHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		ModelProvider references = ModelProvider.getInstance();
		AddReferenceDialog dialog = new AddReferenceDialog(window.getShell());
		dialog.open();
		if (dialog.getReference() != null) {
			references.getReferences().add(dialog.getReference());
			// Updating the display in the view
			IWorkbenchPage page = window.getActivePage();
			ReferenceManagementView view = (ReferenceManagementView) page.findView(ReferenceManagementView.ID);
			view.getViewer().refresh();
		}
		return null;
	}


}
