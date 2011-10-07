/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.actions.editpart;

import org.eclipse.bpel.model.ContainerVariable;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.commands.SetContainerVariableCommand;
import org.eclipse.bpel.ui.dialogs.ContainerVariableSelectorDialog;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;


/**
 * Action for setting a container variable.
 */
public class SetContainerVariableAction extends AbstractAction {
	
	/**
	 * The type can be REQUEST or RESPONSE.
	 */
	public SetContainerVariableAction(EditPart editPart) {
		super(editPart);
	}

	public Image getIconImg() {
		return BPELUIPlugin.INSTANCE.getImage(IBPELUIConstants.ICON_CONTAINER_VARIABLE_16);
	}

	public ImageDescriptor getIcon() {
		return BPELUIPlugin.INSTANCE.getImageDescriptor(IBPELUIConstants.ICON_CONTAINER_VARIABLE_16);
	}

	public boolean onButtonPressed() {
		Shell shell = viewer.getControl().getShell();
		EObject model = (EObject)editPart.getModel();
		ContainerVariableSelectorDialog dialog = new ContainerVariableSelectorDialog(shell, model);
		dialog.setTitle(getDialogTitle());
		if (dialog.open() == Window.OK) {
			ContainerVariable variable = dialog.getContainerVariable();
			Command command = new SetContainerVariableCommand(model, variable);
			viewer.getEditDomain().getCommandStack().execute(command);
		}
		return true;
	}

	protected String getDialogTitle() {
		return "Set ContainerVariable"; 
	}
	
	public String getToolTip() {
		return "Set ContainerVariable";
	}
	
	@Override
	public ImageDescriptor getDisabledIcon() { return ImageDescriptor.getMissingImageDescriptor(); }
	@Override
	public boolean isEnabled() { return true; }	
}