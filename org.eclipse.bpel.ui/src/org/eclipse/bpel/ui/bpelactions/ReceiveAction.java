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
package org.eclipse.bpel.ui.bpelactions;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.terms.BPELTerms;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.adapters.BPELUIAdapterFactory;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;

public class ReceiveAction extends AbstractBPELAction {

    @Override
	public EClass getModelType() {
        return BPELPackage.eINSTANCE.getReceive();
    }

    @Override
	public String getLabel() {
    	return BPELTerms.getString("Receive"); //$NON-NLS-1$
    }

    @Override
	public String getDescription() {
        return Messages.ReceiveAction_Receive_HTML_Description_1; 
    }

    @Override
	public AdapterFactory getAdapterFactory() {
        return BPELUIAdapterFactory.getInstance();
	}
}
