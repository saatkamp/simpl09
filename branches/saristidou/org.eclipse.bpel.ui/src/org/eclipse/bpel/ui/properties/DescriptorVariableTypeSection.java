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

import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.DescriptorVariable;
import org.eclipse.bpel.ui.adapters.AdapterNotification;
import org.eclipse.bpel.ui.commands.CompoundCommand;
import org.eclipse.bpel.ui.commands.SetDescriptorVariableKindCommand;
import org.eclipse.bpel.ui.commands.SetDescriptorVariableTypeCommand;
import org.eclipse.bpel.ui.uiextensionmodel.VariableExtension;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.BatchedMultiObjectAdapter;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.MultiObjectAdapter;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * DescriptorVariableTypeSection provides viewing and editing of the type of a BPEL variable
 * (whether that be an XSD type, WSDL message, or built-in simple type).
 */
public class DescriptorVariableTypeSection extends BPELPropertySection {

	/**
	 * Make this section use all the vertical space it can get. 
	 * 
	 */
	@Override
	public boolean shouldUseExtraSpace() { 
		return true;
	}
	
	protected DescriptorVariableTypeSelector variableTypeSelector;
	
	protected Composite parentComposite;
	

	protected boolean isMessageTypeAffected(Notification n) {
		return (n.getFeatureID(DescriptorVariable.class) == BPELPackage.VARIABLE__MESSAGE_TYPE);
	}
	
	protected boolean isTypeAffected(Notification n) {
		return (n.getFeatureID(DescriptorVariable.class) == BPELPackage.VARIABLE__TYPE);
	}
	
	protected boolean isElementAffected(Notification n) {
		return (n.getFeatureID(DescriptorVariable.class) == BPELPackage.VARIABLE__ELEMENT);
	}


	@Override
	protected MultiObjectAdapter[] createAdapters() {
		return new MultiObjectAdapter[] {
			/* model object */
			new BatchedMultiObjectAdapter() {
				
				boolean update = false;
				
				@Override
				public void notify (Notification n) {
					if (update) {
						return ;
					}
					
					int eventGroup = n.getEventType() / 100; 
					if (eventGroup == AdapterNotification.NOTIFICATION_MARKERS_CHANGED_GROUP) {
						update = true;
						return;
					}

					if (isMessageTypeAffected(n)) {
						update = true;
						return;
					}
					if (isTypeAffected(n)) {
						update = true;
						return ;
					}
					if (isElementAffected(n)) {
						update = true;
						return;
					}
					if (n.getNotifier() instanceof VariableExtension) {
						update = true;
						return ;
					}
				}
				
				@Override
				public void finish() {
					if (update) {
						updateVariableTypeSelector();
					}
					update = false;
				}
			}
		};
	}

	
	@Override
	protected void addAllAdapters() {
		super.addAllAdapters();
		VariableExtension varExt = (VariableExtension)ModelHelper.getExtension(getInput());
		if (varExt != null) fAdapters[0].addToObject(varExt);
	}
	
	
	/**
	 * @author IBM
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 15, 2007
	 */
	
	public class DescriptorVariableTypeCallback implements DescriptorVariableTypeSelector.Callback {
		
		/**
		 * @see org.eclipse.bpel.ui.properties.VariableTypeSelector.Callback#selectRadioButton(int)
		 */
		
		public void selectRadioButton(int index) {
			DescriptorVariable var = (DescriptorVariable)getInput();
			VariableExtension varExt = (VariableExtension)ModelHelper.getExtension(var);
			CompoundCommand command = new CompoundCommand();
			if ((var.getMessageType() != null) || (var.getType() != null) || (var.getXSDElement()) != null) {
				command.add(new SetDescriptorVariableTypeCommand(var, (Message)null));
			}
			if ((varExt.getVariableKind() != index)) {
				command.add(new SetDescriptorVariableKindCommand(varExt, index));
			}
			if (!command.isEmpty()) getCommandFramework().execute(wrapInShowContextCommand(command));
		}
		
		/**
		 * @see org.eclipse.bpel.ui.properties.VariableTypeSelector.Callback#selectXSDType(org.eclipse.xsd.XSDTypeDefinition)
		 */
		public void selectXSDType(XSDTypeDefinition xsdType) {
			DescriptorVariable variable = (DescriptorVariable)getInput();
	    	Command cmd = new SetDescriptorVariableTypeCommand(variable, xsdType);
			getCommandFramework().execute(wrapInShowContextCommand(cmd));
		}
		
		/** (non-Javadoc)
		 * @see org.eclipse.bpel.ui.properties.VariableTypeSelector.Callback#selectXSDElement(org.eclipse.xsd.XSDElementDeclaration)
		 */
		
		public void selectXSDElement(XSDElementDeclaration xsdElement) {
			DescriptorVariable variable = (DescriptorVariable)getInput();
	    	Command cmd = new SetDescriptorVariableTypeCommand(variable, xsdElement);
			getCommandFramework().execute(wrapInShowContextCommand(cmd));
		}
		
		/**
		 * @see org.eclipse.bpel.ui.properties.VariableTypeSelector.Callback#selectMessageType(org.eclipse.wst.wsdl.Message)
		 */
		
		public void selectMessageType(Message message) {
			DescriptorVariable variable = (DescriptorVariable)getInput();
			Command cmd = new SetDescriptorVariableTypeCommand(variable, message);
			getCommandFramework().execute(wrapInShowContextCommand(cmd));
		}
	}
	
	
	@Override
	protected void createClient(Composite parent) {
		Composite composite = parentComposite = createFlatFormComposite(parent);
		
		variableTypeSelector = new DescriptorVariableTypeSelector(composite, SWT.NONE, getBPELEditor(),
			fWidgetFactory, new DescriptorVariableTypeCallback(), true);
		FlatFormData data = new FlatFormData();
		data.top = new FlatFormAttachment(0,0);
		data.left = new FlatFormAttachment(0,0);
		data.right = new FlatFormAttachment(100,0);
		data.bottom = new FlatFormAttachment(100,0);
		variableTypeSelector.setLayoutData(data);
	}
	
	/**
	 * 
	 */
	public void updateVariableTypeSelector() {
		variableTypeSelector.setVariable((DescriptorVariable)getInput());
		updateMarkers();
	}
	
	
	@Override
	protected void basicSetInput(EObject newInput) {
		super.basicSetInput(newInput);
		updateVariableTypeSelector();
	}


	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#getUserContext()
	 */
	@Override
	public Object getUserContext() {
		return variableTypeSelector.getUserContext();
	}
	
	
	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#restoreUserContext(java.lang.Object)
	 */
	
	@Override
	public void restoreUserContext(Object userContext) {
		variableTypeSelector.restoreUserContext(userContext);
	}
	
		
	/**
	 * 
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#isValidMarker(org.eclipse.core.resources.IMarker)
	 */
	
	@SuppressWarnings("nls")
	@Override
	public boolean isValidMarker (IMarker marker) {

		String context = null;
		try {
			context = (String) marker.getAttribute("href.context");
		} catch (Exception ex) {
			return false;
		}
		
		return "name".equals (context) == false; 
	}	
	
	

	@Override
	protected void updateMarkers () {				
		variableTypeSelector.dataTypeLabel.clear();		
		for(IMarker m : getMarkers(getInput())) {
			variableTypeSelector.dataTypeLabel.addStatus(BPELUtil.adapt(m, IStatus.class));
		}		
	}
	
}
