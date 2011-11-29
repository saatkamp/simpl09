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
import org.eclipse.bpel.model.ContainerReferenceVariable;
import org.eclipse.bpel.ui.adapters.AdapterNotification;
import org.eclipse.bpel.ui.commands.CompoundCommand;
import org.eclipse.bpel.ui.commands.SetContainerReferenceVariableKindCommand;
import org.eclipse.bpel.ui.commands.SetContainerReferenceVariableTypeCommand;
import org.eclipse.bpel.ui.details.providers.XSDTypeOrElementContentProvider;
import org.eclipse.bpel.ui.properties.ContainerReferenceVariableTypeSelector.Callback;
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
 * ContainerReferenceVariableTypeSection provides viewing and editing of the type of a BPEL variable
 * (whether that be an XSD type, WSDL message, or built-in simple type).
 */
public class ContainerReferenceVariableTypeSection extends BPELPropertySection {

	/**
	 * Make this section use all the vertical space it can get. 
	 * 
	 */
	@Override
	public boolean shouldUseExtraSpace() { 
		return true;
	}
	
	protected ContainerReferenceVariableTypeSelector variableTypeSelector;
	
	protected Composite parentComposite;
	

	protected boolean isMessageTypeAffected(Notification n) {
		return (n.getFeatureID(ContainerReferenceVariable.class) == BPELPackage.VARIABLE__MESSAGE_TYPE);
	}
	
	protected boolean isTypeAffected(Notification n) {
		return (n.getFeatureID(ContainerReferenceVariable.class) == BPELPackage.VARIABLE__TYPE);
	}
	
	protected boolean isElementAffected(Notification n) {
		return (n.getFeatureID(ContainerReferenceVariable.class) == BPELPackage.VARIABLE__ELEMENT);
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
	
	public class ContainerReferenceVariableTypeCallback implements ContainerReferenceVariableTypeSelector.Callback {
		
		/**
		 * @see org.eclipse.bpel.ui.properties.VariableTypeSelector.Callback#selectRadioButton(int)
		 */
		
		public void selectRadioButton(int index) {
			ContainerReferenceVariable var = (ContainerReferenceVariable)getInput();
			VariableExtension varExt = (VariableExtension)ModelHelper.getExtension(var);
			CompoundCommand command = new CompoundCommand();
			if ((var.getMessageType() != null) || (var.getType() != null) || (var.getXSDElement()) != null) {
				command.add(new SetContainerReferenceVariableTypeCommand(var, (Message)null));
			}
			if ((varExt.getVariableKind() != index)) {
				command.add(new SetContainerReferenceVariableKindCommand(varExt, index));
			}
			if (!command.isEmpty()) getCommandFramework().execute(wrapInShowContextCommand(command));
		}
		
		/**
		 * @see org.eclipse.bpel.ui.properties.VariableTypeSelector.Callback#selectXSDType(org.eclipse.xsd.XSDTypeDefinition)
		 */
		public void selectXSDType(XSDTypeDefinition xsdType) {
			ContainerReferenceVariable variable = (ContainerReferenceVariable)getInput();
	    	Command cmd = new SetContainerReferenceVariableTypeCommand(variable, xsdType);
			getCommandFramework().execute(wrapInShowContextCommand(cmd));
		}
		
		/** (non-Javadoc)
		 * @see org.eclipse.bpel.ui.properties.VariableTypeSelector.Callback#selectXSDElement(org.eclipse.xsd.XSDElementDeclaration)
		 */
		
		public void selectXSDElement(XSDElementDeclaration xsdElement) {
			ContainerReferenceVariable variable = (ContainerReferenceVariable)getInput();
	    	Command cmd = new SetContainerReferenceVariableTypeCommand(variable, xsdElement);
			getCommandFramework().execute(wrapInShowContextCommand(cmd));
		}
		
		/**
		 * @see org.eclipse.bpel.ui.properties.VariableTypeSelector.Callback#selectMessageType(org.eclipse.wst.wsdl.Message)
		 */
		
		public void selectMessageType(Message message) {
			ContainerReferenceVariable variable = (ContainerReferenceVariable)getInput();
			Command cmd = new SetContainerReferenceVariableTypeCommand(variable, message);
			getCommandFramework().execute(wrapInShowContextCommand(cmd));
		}
	}
	
	
	@Override
	protected void createClient(Composite parent) {
		Composite composite = parentComposite = createFlatFormComposite(parent);
		Callback callback = new ContainerReferenceVariableTypeCallback();
		
		variableTypeSelector = new ContainerReferenceVariableTypeSelector(composite, SWT.NONE, getBPELEditor(),
			fWidgetFactory, callback, true);
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
		variableTypeSelector.setVariable((ContainerReferenceVariable)getInput());
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