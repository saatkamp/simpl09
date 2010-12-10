/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.Variable;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Write Data Back Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.WriteDataBackActivityImpl#getDataVariable <em>Data Variable</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.WriteDataBackActivityImpl#getQueryTarget <em>Query Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WriteDataBackActivityImpl extends DataManagementActivityImpl implements WriteDataBackActivity {
  /**
   * The cached value of the '{@link #getDataVariable() <em>Data Variable</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataVariable()
   * @generated
   * @ordered
   */
  protected Variable dataVariable;

  /**
   * The default value of the '{@link #getQueryTarget() <em>Query Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQueryTarget()
   * @generated
   * @ordered
   */
  protected static final String QUERY_TARGET_EDEFAULT = "target";
  /**
   * The cached value of the '{@link #getQueryTarget() <em>Query Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQueryTarget()
   * @generated
   * @ordered
   */
  protected String queryTarget = QUERY_TARGET_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WriteDataBackActivityImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ModelPackage.Literals.WRITE_DATA_BACK_ACTIVITY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Variable getDataVariable() {
    if (dataVariable != null && dataVariable.eIsProxy()) {
      InternalEObject oldDataVariable = (InternalEObject)dataVariable;
      dataVariable = (Variable)eResolveProxy(oldDataVariable);
      if (dataVariable != oldDataVariable) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE, oldDataVariable, dataVariable));
      }
    }
    return dataVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Variable basicGetDataVariable() {
    return dataVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataVariable(Variable newDataVariable) {
    Variable oldDataVariable = dataVariable;
    dataVariable = newDataVariable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE, oldDataVariable, dataVariable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getQueryTarget() {
    return queryTarget;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setQueryTarget(String newQueryTarget) {
    String oldQueryTarget = queryTarget;
    queryTarget = newQueryTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.WRITE_DATA_BACK_ACTIVITY__QUERY_TARGET, oldQueryTarget, queryTarget));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE:
        if (resolve) return getDataVariable();
        return basicGetDataVariable();
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__QUERY_TARGET:
        return getQueryTarget();
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
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE:
        setDataVariable((Variable)newValue);
        return;
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__QUERY_TARGET:
        setQueryTarget((String)newValue);
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
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE:
        setDataVariable((Variable)null);
        return;
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__QUERY_TARGET:
        setQueryTarget(QUERY_TARGET_EDEFAULT);
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
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE:
        return dataVariable != null;
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__QUERY_TARGET:
        return QUERY_TARGET_EDEFAULT == null ? queryTarget != null : !QUERY_TARGET_EDEFAULT.equals(queryTarget);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (queryTarget: ");
    result.append(queryTarget);
    result.append(')');
    return result.toString();
  }

} //WriteDataBackActivityImpl