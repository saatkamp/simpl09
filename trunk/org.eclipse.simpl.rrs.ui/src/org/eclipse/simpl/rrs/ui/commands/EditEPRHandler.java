package org.eclipse.simpl.rrs.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.simpl.rrs.ui.client.EPR;
import org.eclipse.simpl.rrs.ui.client.ModelProvider;
import org.eclipse.simpl.rrs.ui.client.RRSClient;
import org.eclipse.simpl.rrs.ui.dialogs.EditReferenceDialog;
import org.eclipse.simpl.rrs.ui.view.ReferenceManagementView;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class EditEPRHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (RRSClient.getClient().isRRSAvailable()) {

			IWorkbenchWindow window = HandlerUtil
					.getActiveWorkbenchWindow(event);
			IWorkbenchPage page = window.getActivePage();
			ReferenceManagementView view = (ReferenceManagementView) page
					.findView(ReferenceManagementView.ID);
			ISelection selection = view.getSite().getSelectionProvider()
					.getSelection();

			if (selection != null && selection instanceof IStructuredSelection) {

				IStructuredSelection sel = (IStructuredSelection) selection;

				if (sel.size() == 1) {
					EPR reference = (EPR) sel.getFirstElement();
					ModelProvider.getInstance().getReferences().remove(
							reference);

					EditReferenceDialog dialog = new EditReferenceDialog(window
							.getShell(), reference);
					dialog.open();

					ModelProvider.getInstance().getReferences().add(
							dialog.getReference());

					ModelProvider.getInstance().updateReference(
							dialog.getReference());
				}
				view.getViewer().refresh();

				// for (EPR reference : references){
				// System.out.println("Name: " +
				// reference.getReferenceParameters().getReferenceName());
				// System.out.println("Address: " +
				// reference.getAddress().getUri());
				// System.out.println("Adapter: " +
				// reference.getReferenceProperties().getResolutionSystem().getAdapterURI());
				// System.out.println("Statement: " +
				// reference.getReferenceParameters().getStatement());
				// System.out.println("--------------------------------------------");
				// }
			}
		} else {
			MessageDialog
			.openError(
					Display.getCurrent().getActiveShell(),
					"RRS Connection Exception",
					"The RRS isn't available. Please check that the RRS works properly and that the specified addresses in the SIMPL preferences are correct.");
		}
		return null;
	}
}
