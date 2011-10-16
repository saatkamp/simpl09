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

import java.util.Collections;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.ui.adapters.ILabeledElement;
import org.eclipse.bpel.ui.adapters.INamedElement;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.emf.ecore.EObject;


/**
 * Sets a unique name/display name to the model object. 
 */
public class SetUniqueNameCommand extends org.eclipse.gef.commands.CompoundCommand {

	protected Process process;
	protected Object model;

	public SetUniqueNameCommand(Process process, Object model) {
		this.model = model;
		this.process = process;
		ILabeledElement element = BPELUtil.adapt(model, ILabeledElement.class);
		String name = (element != null) ? element.getTypeLabel(model) : ""; //$NON-NLS-1$
		
		// HACK: renames container and descriptor variables to their user-friendly names
    if (name.startsWith("Container Variable")) {
      name = name.replace("Container Variable", "Data Container Reference");
    } else if (name.startsWith("Descriptor Variable")) {
      name = name.replace("Descriptor Variable", "Data Source Reference");
    }
		
		String uniqueModelName = BPELUtil.getUniqueModelName(process, name, Collections.singletonList(model));
		if (BPELUtil.adapt(model, INamedElement.class) != null) {
			add(new SetNameCommand((EObject)model, uniqueModelName));
		}
	}
}
