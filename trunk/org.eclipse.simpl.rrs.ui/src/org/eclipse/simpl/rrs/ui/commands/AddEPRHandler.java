package org.eclipse.simpl.rrs.ui.commands;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.simpl.rrs.ui.client.EPR;
import org.eclipse.simpl.rrs.ui.client.ModelProvider;
import org.eclipse.simpl.rrs.ui.dialogs.AddReferenceDialog;
import org.eclipse.simpl.rrs.ui.view.ReferenceManagementView;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class AddEPRHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		AddReferenceDialog dialog = new AddReferenceDialog(window.getShell());
		dialog.open();
		if (dialog.getReference() != null) {
			ModelProvider.getInstance().getReferences().add(dialog.getReference());
			ModelProvider.getInstance().insertReference(dialog.getReference());
			
			// Updating the display in the view
			IWorkbenchPage page = window.getActivePage();
			ReferenceManagementView view = (ReferenceManagementView) page.findView(ReferenceManagementView.ID);
			view.getViewer().refresh();
		}
		
//		for (EPR reference : references){
//			System.out.println("Name: " + reference.getReferenceParameters().getReferenceName());
//			System.out.println("Address: " + reference.getAddress().getUri());
//			System.out.println("Adapter: " + reference.getReferenceProperties().getResolutionSystem().getAdapterURI());
//			System.out.println("Statement: " + reference.getReferenceParameters().getStatement());
//			System.out.println("--------------------------------------------");
//		}
		
		return null;
	}


}
