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
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.WriteDataBackActivityImpl#getTargetContainer <em>Target Container</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.WriteDataBackActivityImpl#getDataVariable <em>Data Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class WriteDataBackActivityImpl extends DataManagementActivityImpl implements WriteDataBackActivity {
  /**
   * The default value of the '{@link #getTargetContainer() <em>Target Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetContainer()
   * @generated
   * @ordered
   */
  protected static final String TARGET_CONTAINER_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getTargetContainer() <em>Target Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetContainer()
   * @generated
   * @ordered
   */
  protected String targetContainer = TARGET_CONTAINER_EDEFAULT;

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
  public String getTargetContainer() {
    return targetContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetContainer(String newTargetContainer) {
    String oldTargetContainer = targetContainer;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getWriteDataBackActivity_TargetContainer().getName(),
          newTargetContainer);
    }
    targetContainer = newTargetContainer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.WRITE_DATA_BACK_ACTIVITY__TARGET_CONTAINER, oldTargetContainer, targetContainer));
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
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getWriteDataBackActivity_DataVariable().getName(),
          newDataVariable == null ? null : newDataVariable
              .getName());
    }
    dataVariable = newDataVariable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE, oldDataVariable, dataVariable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__TARGET_CONTAINER:
        return getTargetContainer();
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE:
        if (resolve) return getDataVariable();
        return basicGetDataVariable();
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
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__TARGET_CONTAINER:
        setTargetContainer((String)newValue);
        return;
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE:
        setDataVariable((Variable)newValue);
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
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__TARGET_CONTAINER:
        setTargetContainer(TARGET_CONTAINER_EDEFAULT);
        return;
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE:
        setDataVariable((Variable)null);
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
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__TARGET_CONTAINER:
        return TARGET_CONTAINER_EDEFAULT == null ? targetContainer != null : !TARGET_CONTAINER_EDEFAULT.equals(targetContainer);
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE:
        return dataVariable != null;
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
    result.append(" (targetContainer: ");
    result.append(targetContainer);
    result.append(')');
    return result.toString();
  }

} //WriteDataBackActivityImpl
