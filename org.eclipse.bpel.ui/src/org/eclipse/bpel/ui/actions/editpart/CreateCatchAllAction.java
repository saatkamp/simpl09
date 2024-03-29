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

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.CatchAll;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.commands.CompoundCommand;
import org.eclipse.bpel.ui.commands.InsertInContainerCommand;
import org.eclipse.bpel.ui.factories.UIObjectFactoryProvider;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * CreateCatchAllAction
 * 
 * @author IBM Original contribution.  
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 */
public class CreateCatchAllAction extends AbstractAction {

	/**
	 * Create a brand new CreateCatchAllAction object.
	 * 
	 * @param anEditPart the edit part to create the action on.
	 */
	
	public CreateCatchAllAction(EditPart anEditPart) {
		super(anEditPart);
	}
	
	
	/**
	 * @see org.eclipse.bpel.ui.actions.editpart.IEditPartAction#getIcon()
	 */
	public ImageDescriptor getIcon() {
		return BPELUIPlugin.INSTANCE.getImageDescriptor(IBPELUIConstants.ICON_ACTION_CATCHALL);
	}

	/**
	 * @see org.eclipse.bpel.ui.actions.editpart.IEditPartAction#onButtonPressed()
	 */
	public boolean onButtonPressed() {
		CompoundCommand command = new CompoundCommand();
		final CatchAll child = (CatchAll)UIObjectFactoryProvider.getInstance().getFactoryFor(
			BPELPackage.eINSTANCE.getCatchAll()).createInstance();
		command.add(new InsertInContainerCommand((EObject)modelObject, child, null));
		BPELEditor bpelEditor = ModelHelper.getBPELEditor(modelObject);
		bpelEditor.getCommandStack().execute(command);
		return true;
	}

	/**
	 * @see org.eclipse.bpel.ui.actions.editpart.IEditPartAction#getToolTip()
	 */
	public String getToolTip() {
		return Messages.CreateCatchAllAction_Add_Catch_All_1; 
	}
}