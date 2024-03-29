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
package org.eclipse.bpel.ui.commands;

import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.emf.ecore.EObject;


/** 
 * Sets the namespace half of the faultName property of a Catch activity.
 */
public class SetFaultNamespaceCommand extends SetCommand {

	public String getDefaultLabel() { return IBPELUIConstants.CMD_EDIT_FAULTNAMESPACE; }

	public SetFaultNamespaceCommand(EObject target, String newFaultNS)  {
		super(target, newFaultNS);
	}

	@Override
	public Object get() {
		return ModelHelper.getFaultNamespace(fTarget);
	}
	@Override
	public void set(Object o) {
		ModelHelper.setFaultNamespace(fTarget, (String)o);
	}
}
