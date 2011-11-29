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

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.bpel.model.ContainerReferenceVariable;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.XSDUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * Adds a ContainerReferenceVariable to a process.
 * 
 */
public class AddContainerReferenceVariableCommand extends AddToListCommand {
		
	public AddContainerReferenceVariableCommand (EObject context, ContainerReferenceVariable var) {
		super(ModelHelper.getContainingScope(context), var, IBPELUIConstants.CMD_ADD_CONTAINER_REFERENCE_VARIABLE);
	}
	
	@Override
	protected EList<ContainerReferenceVariable> getList() {
		return ModelHelper.getContainerReferenceVariables( target ).getChildren();		
	}
}
