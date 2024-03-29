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

import org.eclipse.wst.wsdl.Fault;

/** 
 * Sets the fault name and namespace for the target model object, to match those of the
 * given WSDL Fault object.  This is supported for Throw, Catch and Reply.
 */
public class SetWSDLFaultCommand extends SetCommand {

	public String getDefaultLabel() { return IBPELUIConstants.CMD_EDIT_FAULTNAME; }

	public SetWSDLFaultCommand(Object target, Fault newFault)  {
		super((EObject)target, newFault);
	}

	@Override
	public Object get() {
		return ModelHelper.getWSDLFault(fTarget);
	}
	@Override
	public void set(Object o) {
		ModelHelper.setWSDLFault(fTarget, (Fault)o);
	}
}
