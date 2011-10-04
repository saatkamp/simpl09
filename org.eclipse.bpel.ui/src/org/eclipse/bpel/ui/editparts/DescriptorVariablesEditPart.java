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
package org.eclipse.bpel.ui.editparts;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.DescriptorVariable;
import org.eclipse.bpel.model.DescriptorVariables;
import org.eclipse.bpel.ui.factories.UIObjectFactoryProvider;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.requests.CreationFactory;


public class DescriptorVariablesEditPart extends BPELTrayCategoryEditPart {

	@Override
	protected EList<DescriptorVariable> getModelChildren() {
		return getDescriptorVariables().getChildren();
	}

	protected DescriptorVariables getDescriptorVariables() {
		return (DescriptorVariables)getModel();
	}

	@Override
	protected CreationFactory getCreationFactory() {
		return UIObjectFactoryProvider.getInstance().getFactoryFor(BPELPackage.eINSTANCE.getDescriptorVariable());
	}
	
	@Override
	protected IFigure getAddToolTip() {
	    return new Label("Add DescriptorVariable"); 
	}
	
	@Override
	protected IFigure getRemoveToolTip() {
	    return new Label("Remove DescriptorVariable"); 
	}
	
	@Override
	public void setModel(Object model) {
		// TODO Auto-generated method stub
		super.setModel(model);
	}
}
