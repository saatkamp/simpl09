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
 * $Id: DescriptorVariablesImpl.java,v 1.9 2009/04/14 10:50:37 smoser Exp $
 */
package org.eclipse.bpel.model.impl;

import java.util.Collection;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.DescriptorVariable;
import org.eclipse.bpel.model.DescriptorVariables;
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
 * An implementation of the model object '<em><b>DescriptorVariables</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.impl.DescriptorVariablesImpl#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DescriptorVariablesImpl extends ExtensibleElementImpl implements DescriptorVariables {
  /**
   * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChildren()
   * @generated
   * @ordered
   */
  protected EList<DescriptorVariable> children;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DescriptorVariablesImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return BPELPackage.Literals.DESCRIPTOR_VARIABLES;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DescriptorVariable> getChildren() {
    if (children == null) {
      children = new EObjectContainmentEList<DescriptorVariable>(DescriptorVariable.class, this,
          BPELPackage.DESCRIPTOR_VARIABLES__CHILDREN);
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
    case BPELPackage.DESCRIPTOR_VARIABLES__CHILDREN:
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
    case BPELPackage.DESCRIPTOR_VARIABLES__CHILDREN:
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
    case BPELPackage.DESCRIPTOR_VARIABLES__CHILDREN:
      getChildren().clear();
      getChildren().addAll((Collection<? extends DescriptorVariable>) newValue);
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
    case BPELPackage.DESCRIPTOR_VARIABLES__CHILDREN:
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
    case BPELPackage.DESCRIPTOR_VARIABLES__CHILDREN:
      return children != null && !children.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  @Override
  protected void adoptContent(EReference reference, Object object) {
    if (object instanceof DescriptorVariable) {
      if (getElement() == null) {
        ReconciliationHelper.getInstance().patchParentElement((DescriptorVariable) object, this,
            null, BPELConstants.ND_DESCRIPTOR_VARIABLES, BPELConstants.ND_DESCRIPTOR_VARIABLE);
      } else {
        ReconciliationHelper.adoptChild(this, children, (DescriptorVariable) object,
            BPELConstants.ND_DESCRIPTOR_VARIABLE);
      }
    }
    super.adoptContent(reference, object);
  }

  @Override
  protected void orphanContent(EReference reference, Object obj) {
    if (obj instanceof DescriptorVariable) {
      ReconciliationHelper.orphanChild(this, (DescriptorVariable) obj);
    }
    super.orphanContent(reference, obj);
  }

} //DescriptorVariablesImpl
