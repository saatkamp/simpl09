/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model.impl;

import java.util.Collection;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.ReferenceVariable;
import org.eclipse.bpel.model.ReferenceVariables;
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
 * An implementation of the model object '<em><b>Reference Variables</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.impl.ReferenceVariablesImpl#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceVariablesImpl extends ExtensibleElementImpl implements
    ReferenceVariables {
  /**
   * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChildren()
   * @generated
   * @ordered
   */
  protected EList<ReferenceVariable> children;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ReferenceVariablesImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return BPELPackage.Literals.REFERENCE_VARIABLES;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ReferenceVariable> getChildren() {
    if (children == null) {
      children = new EObjectContainmentEList<ReferenceVariable>(ReferenceVariable.class,
          this, BPELPackage.REFERENCE_VARIABLES__CHILDREN);
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
    case BPELPackage.REFERENCE_VARIABLES__CHILDREN:
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
    case BPELPackage.REFERENCE_VARIABLES__CHILDREN:
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
    case BPELPackage.REFERENCE_VARIABLES__CHILDREN:
      getChildren().clear();
      getChildren().addAll((Collection<? extends ReferenceVariable>) newValue);
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
    case BPELPackage.REFERENCE_VARIABLES__CHILDREN:
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
    case BPELPackage.REFERENCE_VARIABLES__CHILDREN:
      return children != null && !children.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  @Override
  protected void adoptContent(EReference reference, Object object) {
    if (object instanceof ReferenceVariable) {
      if (getElement() == null) {
        ReconciliationHelper.getInstance().patchParentElement((ReferenceVariable) object, this, 
            null, BPELConstants.ND_REFERENCE_VARIABLES,
            BPELConstants.ND_REFERENCE_VARIABLE);
      } else {
        ReconciliationHelper.adoptChild(this, children, (ReferenceVariable) object,
            BPELConstants.ND_REFERENCE_VARIABLE);
      }
    }
    super.adoptContent(reference, object);
  }

  @Override
  protected void orphanContent(EReference reference, Object obj) {
    if (obj instanceof ReferenceVariable) {
      ReconciliationHelper.orphanChild(this, (ReferenceVariable) obj);
    }
    super.orphanContent(reference, obj);
  }

} //ReferenceVariablesImpl
