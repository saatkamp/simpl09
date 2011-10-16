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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.ContainerVariable;
import org.eclipse.bpel.model.ContainerVariables;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.impl.BPELFactoryImpl;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.model.util.XSDImportResolver;
import org.eclipse.bpel.model.util.XSDUtil;
import org.eclipse.bpel.ui.commands.AddVariableCommand;
import org.eclipse.bpel.ui.commands.SetContainerVariableTypeCommand;
import org.eclipse.bpel.ui.factories.UIObjectFactoryProvider;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xml.type.internal.QName;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;

public class ContainerVariablesEditPart extends BPELTrayCategoryEditPart {
  public static String DATA_TYPE = "DataContainerReferenceType";
  private static String SIMPL_NAMESPACE = "http://www.example.org/simpl";
  private static String SIMPL_PREFIX = "simpl";
  private static HashMap<GraphicalEditPart, GraphicalEditPart> cloneEditParts = new HashMap<GraphicalEditPart, GraphicalEditPart>();
  
  @Override
  protected EList<ContainerVariable> getModelChildren() {
    return getContainerVariables().getChildren();
  }

  protected ContainerVariables getContainerVariables() {
    return (ContainerVariables) getModel();
  }

  @Override
  protected CreationFactory getCreationFactory() {
    return UIObjectFactoryProvider.getInstance().getFactoryFor(
        BPELPackage.eINSTANCE.getContainerVariable());
  }

  @Override
  protected IFigure getAddToolTip() {
    return new Label("Add Data Container Reference");
  }

  @Override
  protected IFigure getRemoveToolTip() {
    return new Label("Remove Data Container Reference");
  }

  /**
   * Overridden to set the type and create a clone variable on creation of a container
   * variable.
   * 
   * The data type is automatically set and the container variable is cloned to a normal
   * variable. The clone is used by the BPEL engine, but is not visible in the GUI.
   */
  @Override
  protected Object addEntry() {
    // add container variable
    ContainerVariable variable = (ContainerVariable) super.addEntry();
    XSDImportResolver resolver = new XSDImportResolver();
    org.eclipse.bpel.model.Process process = BPELUtils.getProcess(this
        .getContainerVariables());
    EList<Import> imports = process.getImports();
    List<Object> definitions = null;

    for (Import import1 : imports) {
      if (import1.getNamespace().equals(SIMPL_NAMESPACE)) {
        definitions = resolver.resolve(import1, XSDImportResolver.RESOLVE_SCHEMA);
        break;
      }
    }

    // set the type of the container variable
    if (!definitions.isEmpty()) {
      XSDTypeDefinition newType = XSDUtil.resolveTypeDefinition((XSDSchema) definitions
          .get(0), new QName(SIMPL_NAMESPACE, DATA_TYPE, SIMPL_PREFIX));
      SetContainerVariableTypeCommand command = new SetContainerVariableTypeCommand(
          variable, newType);
      command.execute();
    }
    
    // create variable clone of the container variable
    Variable cloneVariable = BPELFactoryImpl.init().createVariable();
    cloneVariable.setName(variable.getName());
    cloneVariable.setMessageType(variable.getMessageType());
    cloneVariable.setType(variable.getType());
    cloneVariable.setXSDElement(variable.getXSDElement());
    cloneVariable.setFrom(variable.getFrom());
    AddVariableCommand command = new AddVariableCommand(process, cloneVariable);
    command.execute();

    // hide the clone variable in the GUI for the moment (this does not hide it on reopen, for that see
    // VariableEditPart and VariablesEditPart)
    GraphicalEditPart editPartOfClone = (GraphicalEditPart) this.getViewer()
        .getEditPartRegistry().get(cloneVariable);
    editPartOfClone.getFigure().setFocusTraversable(false);
    editPartOfClone.getFigure().setRequestFocusEnabled(false);
    editPartOfClone.getFigure().setEnabled(false);
    editPartOfClone.getFigure().setPreferredSize(new Dimension(0, 0));
    editPartOfClone.getFigure().setVisible(false);

    // register edit parts for removeEntry
    GraphicalEditPart editPartOfVariable = (GraphicalEditPart) this.getViewer()
        .getEditPartRegistry().get(variable);
    cloneEditParts.put(editPartOfVariable, editPartOfClone);
    
    return variable;
  }

  /**
   * Overridden to remove the clone variable when a container variable is removed.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  protected void removeEntry() {
    List selectedParts = getViewer().getSelectedEditParts();
    if (selectedParts.size() == 0) return; // nothing to be done
    
    // we can only delete edit parts that are children of this edit part
    List<EditPart> condemned = new ArrayList<EditPart>();
    for (Iterator<EditPart> iter = selectedParts.iterator(); iter.hasNext();) {
      EditPart part = iter.next();
      if (part.getParent() == ContainerVariablesEditPart.this) {
        condemned.add(part);
      }
    }
    if (condemned.isEmpty()) return; // nothing to be done
    
    // remove container variables
    super.removeEntry();
    
    // remove clones
    GroupRequest request = new GroupRequest(RequestConstants.REQ_DELETE);
    CompoundCommand deletions = new CompoundCommand();
    for (Iterator<EditPart> iter = condemned.iterator(); iter.hasNext();) {
      EditPart part = iter.next();
      part = cloneEditParts.get(part);
      deletions.add(part.getCommand(request));
    }
    getCommandStack().execute(deletions);
  }
  
  @Override
  public void setModel(Object model) {
    // TODO Auto-generated method stub
    super.setModel(model);
  }
}
