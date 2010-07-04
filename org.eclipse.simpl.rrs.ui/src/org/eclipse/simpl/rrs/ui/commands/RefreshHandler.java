package org.eclipse.simpl.rrs.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.simpl.rrs.ui.client.ModelProvider;
import org.eclipse.simpl.rrs.ui.client.RRSClient;
import org.eclipse.simpl.rrs.ui.view.ReferenceManagementView;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class RefreshHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (RRSClient.getClient().isRRSAvailable()) {
			ModelProvider.getInstance().refresh();
			IWorkbenchPart part = HandlerUtil.getActivePart(event);
			if (part instanceof ReferenceManagementView) {
				ReferenceManagementView view = (ReferenceManagementView) part;
				view.getViewer().refresh();
			}
		}else {
			MessageDialog
			.openError(
					Display.getCurrent().getActiveShell(),
					"RRS Connection Exception",
					"The RRS isn't available. Please check that the RRS works properly and that the specified addresses in the SIMPL preferences are correct.");
		}
		return null;
	}

}
