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
package org.eclipse.bpel.ui.details.providers;

import java.util.List;

import org.eclipse.bpel.model.ReferenceVariable;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.emf.ecore.EObject;

/**
 * Content provider for variables.
 * 
 * Provides all the Variables visible in a given context.
 */

public class VariableContentProvider extends AbstractContentProvider {

	private boolean showRefVars = false;
	
	public VariableContentProvider(boolean showReferenceVariables) {
		super();
		this.showRefVars = showReferenceVariables;
	}

	@Override
	public void collectElements(Object input, List list) {
		Variable[] vars = BPELUtil.getVisibleVariables((EObject) input);
		ReferenceVariable[] refVars = BPELUtil
				.getVisibleReferenceVariables((EObject) input);
		for (int i = 0; i < vars.length; i++) {
			list.add(vars[i]);
		}
		if (this.showRefVars){
			for (int i = 0; i < refVars.length; i++) {
				list.add(refVars[i]);
			}	
		}
	}
}
