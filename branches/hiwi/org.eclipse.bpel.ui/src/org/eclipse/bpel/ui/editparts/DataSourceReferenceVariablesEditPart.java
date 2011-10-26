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
import java.util.Iterator;
import java.util.List;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.DataSourceReferenceVariable;
import org.eclipse.bpel.model.DataSourceReferenceVariables;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.impl.BPELFactoryImpl;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.model.util.WSDLImportResolver;
import org.eclipse.bpel.model.util.XSDUtil;
import org.eclipse.bpel.ui.commands.AddVariableCommand;
import org.eclipse.bpel.ui.commands.SetDataSourceReferenceVariableTypeCommand;
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


public class DataSourceReferenceVariablesEditPart extends BPELTrayCategoryEditPart {
  public static String DATA_TYPE = "DataSourceReferenceType";
  private static String SIMPL_NAMESPACE = "http://www.example.org/simpl";
  private static String SIMPL_PREFIX = "simpl";
    
	@Override
	protected EList<DataSourceReferenceVariable> getModelChildren() {
		return getDataSourceReferenceVariables().getChildren();
	}

	protected DataSourceReferenceVariables getDataSourceReferenceVariables() {
		return (DataSourceReferenceVariables)getModel();
	}

	@Override
	protected CreationFactory getCreationFactory() {
		return UIObjectFactoryProvider.getInstance().getFactoryFor(BPELPackage.eINSTANCE.getDataSourceReferenceVariable());
	}
	
	@Override
	protected IFigure getAddToolTip() {
	    return new Label("Add Data Source Reference"); 
	}
	
	@Override
	protected IFigure getRemoveToolTip() {
	    return new Label("Remove Data Source Reference"); 
	}
	
  /**
   * Overridden to set the type and create a clone variable on creation of a data source reference variable.
   * 
   * The data type is automatically chosen and the data source reference variable is cloned to a
   * normal variable. The clone is used by the BPEL engine because but is not visible in
   * the GUI.
   */
  @Override
  protected Object addEntry() {
    // add data source reference variable
    DataSourceReferenceVariable variable = (DataSourceReferenceVariable) super.addEntry();
    WSDLImportResolver resolver = new WSDLImportResolver();
    org.eclipse.bpel.model.Process process = BPELUtils.getProcess(this
        .getDataSourceReferenceVariables());
    EList<Import> imports = process.getImports();
    List<Object> definitions = null;

    for (Import import1 : imports) {
      if (import1.getNamespace() != null && import1.getNamespace().equals(SIMPL_NAMESPACE)) {
        definitions = resolver.resolve(import1, WSDLImportResolver.RESOLVE_SCHEMA);
        break;
      }
    }

    // set the type of the data source reference variable
    if (!definitions.isEmpty()) {
      XSDTypeDefinition newType = XSDUtil.resolveTypeDefinition((XSDSchema) definitions
          .get(0), new QName(SIMPL_NAMESPACE, DATA_TYPE, SIMPL_PREFIX));
      SetDataSourceReferenceVariableTypeCommand command = new SetDataSourceReferenceVariableTypeCommand(
          variable, newType);
      command.execute();
    }

    // create variable clone of the data source reference variable
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

    return variable;
  }

  /**
   * Override to remove the clone variable when a data source reference variable is removed.
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
      if (part.getParent() == DataSourceReferenceVariablesEditPart.this) {
        condemned.add(part);
      }
    }
    if (condemned.isEmpty()) return; // nothing to be done
    
    // remove selected data source reference variables
    super.removeEntry();
    
    Process process = BPELUtils.getProcess(this.getDataSourceReferenceVariables());
    GroupRequest request = new GroupRequest(RequestConstants.REQ_DELETE);
    CompoundCommand deletions = new CompoundCommand();

    // remove clone variables
    for (Iterator<EditPart> editPartsIter = condemned.iterator(); editPartsIter.hasNext();) {
      EditPart part = editPartsIter.next();
      DataSourceReferenceVariable dataSourceReferenceVariable = (DataSourceReferenceVariable) part.getModel();
      EList<Variable> variables = process.getVariables().getChildren();
      GraphicalEditPart editPartOfClone = null;

      for (Iterator<Variable> variablesIter = variables.iterator(); variablesIter
          .hasNext();) {
        Variable cloneVariable = variablesIter.next();

        if (cloneVariable.getName().equals(dataSourceReferenceVariable.getName())) {
          editPartOfClone = (GraphicalEditPart) this.getViewer().getEditPartRegistry()
              .get(cloneVariable);
          deletions.add(editPartOfClone.getCommand(request));
        }
      }
    }
    getCommandStack().execute(deletions);
  }
  
	@Override
	public void setModel(Object model) {
		// TODO Auto-generated method stub
		super.setModel(model);
	}
}
