/**
 * <copyright>
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 * </copyright>
 *
 * $Id: DataSourceReferenceVariablesImpl.java,v 1.9 2009/04/14 10:50:37 smoser Exp $
 */
package org.eclipse.bpel.model.impl;

import java.util.Collection;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.DataSourceReferenceVariable;
import org.eclipse.bpel.model.DataSourceReferenceVariables;
import org.eclipse.bpel.model.util.BPELConstants;
import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DataSourceReferenceVariables</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.impl.DataSourceReferenceVariablesImpl#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataSourceReferenceVariablesImpl extends ExtensibleElementImpl implements DataSourceReferenceVariables {
  /**
   * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChildren()
   * @generated
   * @ordered
   */
  protected EList<DataSourceReferenceVariable> children;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DataSourceReferenceVariablesImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return BPELPackage.Literals.DATA_SOURCE_REFERENCE_VARIABLES;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DataSourceReferenceVariable> getChildren() {
    if (children == null) {
      children = new EObjectContainmentEList<DataSourceReferenceVariable>(DataSourceReferenceVariable.class, this,
          BPELPackage.DATA_SOURCE_REFERENCE_VARIABLES__CHILDREN);
    }
    return children;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
    case BPELPackage.DATA_SOURCE_REFERENCE_VARIABLES__CHILDREN:
      return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case BPELPackage.DATA_SOURCE_REFERENCE_VARIABLES__CHILDREN:
      return getChildren();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case BPELPackage.DATA_SOURCE_REFERENCE_VARIABLES__CHILDREN:
      getChildren().clear();
      getChildren().addAll((Collection<? extends DataSourceReferenceVariable>) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case BPELPackage.DATA_SOURCE_REFERENCE_VARIABLES__CHILDREN:
      getChildren().clear();
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case BPELPackage.DATA_SOURCE_REFERENCE_VARIABLES__CHILDREN:
      return children != null && !children.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  @Override
  protected void adoptContent(EReference reference, Object object) {
    if (object instanceof DataSourceReferenceVariable) {
      if (getElement() == null) {
        ReconciliationHelper.getInstance().patchParentElement((DataSourceReferenceVariable) object, this,
            null, BPELConstants.ND_DATA_SOURCE_REFERENCE_VARIABLES, BPELConstants.ND_DATA_SOURCE_REFERENCE_VARIABLE);
      } else {
        ReconciliationHelper.adoptChild(this, children, (DataSourceReferenceVariable) object,
            BPELConstants.ND_DATA_SOURCE_REFERENCE_VARIABLE);
      }
    }
    super.adoptContent(reference, object);
  }

  @Override
  protected void orphanContent(EReference reference, Object obj) {
    if (obj instanceof DataSourceReferenceVariable) {
      ReconciliationHelper.orphanChild(this, (DataSourceReferenceVariable) obj);
    }
    super.orphanContent(reference, obj);
  }

} //DataSourceReferenceVariablesImpl
