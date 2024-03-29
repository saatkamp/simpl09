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
package org.eclipse.bpel.ui.properties;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.ForEach;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.expressions.IEditorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;


/**
 * @author IBM
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 13, 2007
 *
 */
public class ForEachStartCounterValueSection extends ExpressionSection {

	@Override
	protected String getExpressionType() { 
		return IEditorConstants.ET_UNSIGNED_INT; 
	}	
	
	@Override
	protected Composite createNoEditorWidgets(Composite composite) {
		
		return super.createNoEditorWidgetsCreateComposite(composite,			
				Messages.ForEachStartCounterValuesSection_No_Expression_specified_1 + NL + NL +
				Messages.ForEachCounterValuesSection_Mandatory_Expression_2 ,
				
				Messages.ForEachCounterValuesSection_Create_a_New_Expression_3);
	}
	
	@Override
	protected EStructuralFeature getStructuralFeature(EObject object) {
		if (object instanceof ForEach) {
			return BPELPackage.eINSTANCE.getForEach_StartCounterValue();
		}
		return super.getStructuralFeature(object);
	}
	
	@Override
	protected void createClient(Composite parent) {
		this.title = Messages.ForEachStartCounterValuesSection_TITLE_3;
		super.createClient(parent);
	}
	
	
}
