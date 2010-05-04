package org.eclipse.simpl.rrs.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.simpl.rrs.ui.client.ModelProvider;
import org.eclipse.simpl.rrs.ui.view.ReferenceManagementView;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
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
		ModelProvider.getInstance().refresh();
		IWorkbenchPart part = HandlerUtil.getActivePart(event);
		if (part instanceof ReferenceManagementView){
			ReferenceManagementView view = (ReferenceManagementView) part;
			view.getViewer().refresh();
		}
		return null;
	}

}
