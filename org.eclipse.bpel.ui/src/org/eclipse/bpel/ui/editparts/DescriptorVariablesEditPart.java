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
package org.eclipse.bpel.ui.editparts;

import java.util.List;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.DescriptorVariable;
import org.eclipse.bpel.model.DescriptorVariables;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.model.util.XSDImportResolver;
import org.eclipse.bpel.model.util.XSDUtil;
import org.eclipse.bpel.ui.commands.SetDescriptorVariableTypeCommand;
import org.eclipse.bpel.ui.factories.UIObjectFactoryProvider;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xml.type.internal.QName;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;


public class DescriptorVariablesEditPart extends BPELTrayCategoryEditPart {
  private static String SIMPL_NAMESPACE = "http://www.example.org/simpl";
  private static String SIMPL_PREFIX = "simpl";
  private static String DATA_TYPE = "LogicalDataSourceDescriptorType";
  
	@Override
	protected EList<DescriptorVariable> getModelChildren() {
		return getDescriptorVariables().getChildren();
	}

	protected DescriptorVariables getDescriptorVariables() {
		return (DescriptorVariables)getModel();
	}

	@Override
	protected CreationFactory getCreationFactory() {
		return UIObjectFactoryProvider.getInstance().getFactoryFor(BPELPackage.eINSTANCE.getDescriptorVariable());
	}
	
	@Override
	protected IFigure getAddToolTip() {
	    return new Label("Add DescriptorVariable"); 
	}
	
	@Override
	protected IFigure getRemoveToolTip() {
	    return new Label("Remove DescriptorVariable"); 
	}
	
  /**
   * Sets the variable data type of a descriptor variable when it is added.
   */
  @Override
	protected Object addEntry() {
    DescriptorVariable variable = (DescriptorVariable)super.addEntry();
    XSDImportResolver resolver = new XSDImportResolver();
    org.eclipse.bpel.model.Process process = BPELUtils.getProcess(this.getDescriptorVariables());
    EList<Import> imports = process.getImports();
    List<Object> definitions = null;
    
    for (Import import1 : imports) {
      if (import1.getNamespace().equals(SIMPL_NAMESPACE)) {
        definitions = resolver.resolve(import1, XSDImportResolver.RESOLVE_SCHEMA);
        break;
      }
    }
    
    if (!definitions.isEmpty()) {
      XSDTypeDefinition newType = XSDUtil.resolveTypeDefinition((XSDSchema)definitions.get(0), new QName(SIMPL_NAMESPACE, DATA_TYPE, SIMPL_PREFIX));
      SetDescriptorVariableTypeCommand command = new SetDescriptorVariableTypeCommand(variable, newType);
      command.execute();
    }
    
    return variable;
  }
	
	@Override
	public void setModel(Object model) {
		// TODO Auto-generated method stub
		super.setModel(model);
	}
}
