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
package org.eclipse.bpel.ui.adapters;

import java.util.List;

import org.eclipse.bpel.ui.actions.editpart.AbstractAction;
import org.eclipse.bpel.ui.actions.editpart.SetCompensateLinkAction;
import org.eclipse.gef.EditPart;


public class CompensateScopeAdapter extends ActivityAdapter {

	/* IEditPartActionContributor */
	
	@Override
	public List<AbstractAction> getEditPartActions(final EditPart editPart) {
		List<AbstractAction> actions = super.getEditPartActions(editPart);
		actions.add(new SetCompensateLinkAction(editPart));
		return actions;
	}	
}
