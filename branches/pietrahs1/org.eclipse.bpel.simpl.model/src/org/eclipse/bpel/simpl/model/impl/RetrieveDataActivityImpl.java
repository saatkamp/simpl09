/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.util.ReconciliationHelper;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Retrieve Data Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.RetrieveDataActivityImpl#getTargetVariable <em>Target Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class RetrieveDataActivityImpl extends DataManagementActivityImpl implements RetrieveDataActivity {
  /**
   * The cached value of the '{@link #getTargetVariable() <em>Target Variable</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetVariable()
   * @generated
   * @ordered
   */
  protected Variable targetVariable;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RetrieveDataActivityImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ModelPackage.Literals.RETRIEVE_DATA_ACTIVITY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Variable getTargetVariable() {
    if (targetVariable != null && targetVariable.eIsProxy()) {
      InternalEObject oldTargetVariable = (InternalEObject)targetVariable;
      targetVariable = (Variable)eResolveProxy(oldTargetVariable);
      if (targetVariable != oldTargetVariable) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE, oldTargetVariable, targetVariable));
      }
    }
    return targetVariable;
  }
  

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Variable basicGetTargetVariable() {
    return targetVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetVariable(Variable newTargetVariable) {
    Variable oldTargetVariable = targetVariable;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getRetrieveDataActivity_TargetVariable().getName(),
          newTargetVariable == null ? null : newTargetVariable
              .getName());
    }
    targetVariable = newTargetVariable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE, oldTargetVariable, targetVariable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ModelPackage.RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE:
        if (resolve) return getTargetVariable();
        return basicGetTargetVariable();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case ModelPackage.RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE:
        setTargetVariable((Variable)newValue);
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
      case ModelPackage.RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE:
        setTargetVariable((Variable)null);
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
      case ModelPackage.RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE:
        return targetVariable != null;
    }
    return super.eIsSet(featureID);
  }

} //RetrieveDataActivityImpl
