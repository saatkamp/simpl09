package org.eclipse.simpl.resource.management.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.simpl.resource.management.data.DataProvider;
import org.eclipse.simpl.resource.management.view.ResourceManagementBrowserView;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Refreshes the view.
 * 
 * @author Michael Schneidt
 */
public class RefreshHandler extends AbstractHandler{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		DataProvider.getInstance().refresh();
		IWorkbenchPart part = HandlerUtil.getActivePart(event);
		
		if (part instanceof ResourceManagementBrowserView){
			ResourceManagementBrowserView view = (ResourceManagementBrowserView) part;
			view.getViewer().refresh();
		}
		
		return null;
	}
}