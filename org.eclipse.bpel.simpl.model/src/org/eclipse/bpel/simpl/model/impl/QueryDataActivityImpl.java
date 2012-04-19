/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query Data Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.QueryDataActivityImpl#getTargetContainer <em>Target Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class QueryDataActivityImpl extends DataManagementActivityImpl implements QueryDataActivity {
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected QueryDataActivityImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ModelPackage.Literals.QUERY_DATA_ACTIVITY;
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
          .getQueryDataActivity_TargetContainer().getName(),
          newTargetContainer);
    }
    targetContainer = newTargetContainer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.QUERY_DATA_ACTIVITY__TARGET_CONTAINER, oldTargetContainer, targetContainer));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ModelPackage.QUERY_DATA_ACTIVITY__TARGET_CONTAINER:
        return getTargetContainer();
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
      case ModelPackage.QUERY_DATA_ACTIVITY__TARGET_CONTAINER:
        setTargetContainer((String)newValue);
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
      case ModelPackage.QUERY_DATA_ACTIVITY__TARGET_CONTAINER:
        setTargetContainer(TARGET_CONTAINER_EDEFAULT);
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
      case ModelPackage.QUERY_DATA_ACTIVITY__TARGET_CONTAINER:
        return TARGET_CONTAINER_EDEFAULT == null ? targetContainer != null : !TARGET_CONTAINER_EDEFAULT.equals(targetContainer);
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

} //QueryDataActivityImpl
