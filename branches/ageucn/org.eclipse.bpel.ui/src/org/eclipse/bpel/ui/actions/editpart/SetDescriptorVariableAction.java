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

import org.eclipse.bpel.model.DescriptorVariable;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.commands.SetDescriptorVariableCommand;
import org.eclipse.bpel.ui.dialogs.DescriptorVariableSelectorDialog;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;


/**
 * Action for setting a descriptor variable.
 */
public class SetDescriptorVariableAction extends AbstractAction {
	
	/**
	 * The type can be REQUEST or RESPONSE.
	 */
	public SetDescriptorVariableAction(EditPart editPart) {
		super(editPart);
	}

	public Image getIconImg() {
		return BPELUIPlugin.INSTANCE.getImage(IBPELUIConstants.ICON_DESCRIPTOR_VARIABLE_16);
	}

	public ImageDescriptor getIcon() {
		return BPELUIPlugin.INSTANCE.getImageDescriptor(IBPELUIConstants.ICON_DESCRIPTOR_VARIABLE_16);
	}

	public boolean onButtonPressed() {
		Shell shell = viewer.getControl().getShell();
		EObject model = (EObject)editPart.getModel();
		DescriptorVariableSelectorDialog dialog = new DescriptorVariableSelectorDialog(shell, model);
		dialog.setTitle(getDialogTitle());
		if (dialog.open() == Window.OK) {
			DescriptorVariable variable = dialog.getDescriptorVariable();
			Command command = new SetDescriptorVariableCommand(model, variable);
			viewer.getEditDomain().getCommandStack().execute(command);
		}
		return true;
	}

	protected String getDialogTitle() {
		return "Set DescriptorVariable"; 
	}
	
	public String getToolTip() {
		return "Set DescriptorVariable";
	}
	
	@Override
	public ImageDescriptor getDisabledIcon() { return ImageDescriptor.getMissingImageDescriptor(); }
	@Override
	public boolean isEnabled() { return true; }	
}