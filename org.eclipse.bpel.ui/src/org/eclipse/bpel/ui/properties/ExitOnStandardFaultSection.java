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

import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.details.RadioButtonIValue;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * ExitOnStandardFaultSection provides viewing and editing of the
 * exitOnStandardFault attribute of Process elements.
 */

@SuppressWarnings({ "boxing", "nls" })

public class ExitOnStandardFaultSection extends BPELPropertySection {
	
	
	protected Button yesRadio, noRadio ;

	EditController fRadioController;
	
	protected void createWidgets(Composite composite) {
		FlatFormData data;
		
		Label suppressLabel = fWidgetFactory.createLabel(composite, Messages.ExitOnStandardFault_1); 
		
		yesRadio = fWidgetFactory.createButton(composite, Messages.ExitOnStandardFault_Yes_2, SWT.RADIO); 
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(suppressLabel, STANDARD_LABEL_WIDTH_LRG));
		data.top = new FlatFormAttachment(0, 0);
		yesRadio.setLayoutData(data);	
		yesRadio.setData(RadioButtonIValue.VALUE, Boolean.TRUE);

		noRadio = fWidgetFactory.createButton(composite, Messages.ExitOnStandardFault_No_3, SWT.RADIO); 
		data = new FlatFormData();
		data.left = new FlatFormAttachment(yesRadio, IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(0, 0);
		noRadio.setLayoutData(data);
		noRadio.setData(RadioButtonIValue.VALUE, Boolean.FALSE);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(yesRadio, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(yesRadio, 0, SWT.CENTER);
		suppressLabel.setLayoutData(data);
	}
	
	protected void createControllers () {
		fRadioController = createEditController();
		fRadioController.setFeature(BPELPackage.eINSTANCE.getProcess_ExitOnStandardFault() );		
		fRadioController.setViewIValue(new RadioButtonIValue ( yesRadio , noRadio )) ;		
		fRadioController.startListeningTo(yesRadio,noRadio);
	}
	
	@Override
	protected void createClient(Composite parent) {
		Composite composite = createFlatFormComposite(parent);
		createWidgets(composite);
		createControllers();
	}

	

	@Override
	protected void basicSetInput (EObject newInput) {		
		super.basicSetInput(newInput);
		fRadioController.setInput(newInput);		
	}
	
}
