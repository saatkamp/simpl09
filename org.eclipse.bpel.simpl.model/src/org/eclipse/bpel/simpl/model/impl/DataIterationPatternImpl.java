/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.DataIterationPattern;
import org.eclipse.bpel.simpl.model.ModelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Iteration Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataIterationPatternImpl#getContainerReferenceList <em>Container Reference List</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataIterationPatternImpl#getCurrentContainer <em>Current Container</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataIterationPatternImpl#getActivity <em>Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class DataIterationPatternImpl extends DataManagementPatternImpl implements DataIterationPattern {
  /**
   * The default value of the '{@link #getContainerReferenceList() <em>Container Reference List</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContainerReferenceList()
   * @generated
   * @ordered
   */
  protected static final String CONTAINER_REFERENCE_LIST_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getContainerReferenceList() <em>Container Reference List</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContainerReferenceList()
   * @generated
   * @ordered
   */
  protected String containerReferenceList = CONTAINER_REFERENCE_LIST_EDEFAULT;

  /**
   * The default value of the '{@link #getCurrentContainer() <em>Current Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCurrentContainer()
   * @generated
   * @ordered
   */
  protected static final String CURRENT_CONTAINER_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getCurrentContainer() <em>Current Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCurrentContainer()
   * @generated
   * @ordered
   */
  protected String currentContainer = CURRENT_CONTAINER_EDEFAULT;

  /**
   * The cached value of the '{@link #getActivity() <em>Activity</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActivity()
   * @generated
   * @ordered
   */
  protected Activity activity;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DataIterationPatternImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ModelPackage.Literals.DATA_ITERATION_PATTERN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getContainerReferenceList() {
    return containerReferenceList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainerReferenceList(String newContainerReferenceList) {
    String oldContainerReferenceList = containerReferenceList;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getDataIterationPattern_ContainerReferenceList().getName(),
          newContainerReferenceList);
    }
    containerReferenceList = newContainerReferenceList;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_ITERATION_PATTERN__CONTAINER_REFERENCE_LIST, oldContainerReferenceList, containerReferenceList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCurrentContainer() {
    return currentContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCurrentContainer(String newCurrentContainer) {
    String oldCurrentContainer = currentContainer;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getDataIterationPattern_CurrentContainer().getName(),
          newCurrentContainer);
    }
    currentContainer = newCurrentContainer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_ITERATION_PATTERN__CURRENT_CONTAINER, oldCurrentContainer, currentContainer));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Activity getActivity() {
    return activity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetActivity(Activity newActivity, NotificationChain msgs) {
    Activity oldActivity = activity;
    if (!isReconciling) {
      ReconciliationHelper.replaceChild(this, oldActivity, newActivity);
    }
    activity = newActivity;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY, oldActivity, newActivity);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActivity(Activity newActivity) {
    if (newActivity != activity) {
      NotificationChain msgs = null;
      if (activity != null)
        msgs = ((InternalEObject)activity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY, null, msgs);
      if (newActivity != null)
        msgs = ((InternalEObject)newActivity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY, null, msgs);
      msgs = basicSetActivity(newActivity, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY, newActivity, newActivity));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY:
        return basicSetActivity(null, msgs);
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
      case ModelPackage.DATA_ITERATION_PATTERN__CONTAINER_REFERENCE_LIST:
        return getContainerReferenceList();
      case ModelPackage.DATA_ITERATION_PATTERN__CURRENT_CONTAINER:
        return getCurrentContainer();
      case ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY:
        return getActivity();
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
      case ModelPackage.DATA_ITERATION_PATTERN__CONTAINER_REFERENCE_LIST:
        setContainerReferenceList((String)newValue);
        return;
      case ModelPackage.DATA_ITERATION_PATTERN__CURRENT_CONTAINER:
        setCurrentContainer((String)newValue);
        return;
      case ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY:
        setActivity((Activity)newValue);
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
      case ModelPackage.DATA_ITERATION_PATTERN__CONTAINER_REFERENCE_LIST:
        setContainerReferenceList(CONTAINER_REFERENCE_LIST_EDEFAULT);
        return;
      case ModelPackage.DATA_ITERATION_PATTERN__CURRENT_CONTAINER:
        setCurrentContainer(CURRENT_CONTAINER_EDEFAULT);
        return;
      case ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY:
        setActivity((Activity)null);
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
      case ModelPackage.DATA_ITERATION_PATTERN__CONTAINER_REFERENCE_LIST:
        return CONTAINER_REFERENCE_LIST_EDEFAULT == null ? containerReferenceList != null : !CONTAINER_REFERENCE_LIST_EDEFAULT.equals(containerReferenceList);
      case ModelPackage.DATA_ITERATION_PATTERN__CURRENT_CONTAINER:
        return CURRENT_CONTAINER_EDEFAULT == null ? currentContainer != null : !CURRENT_CONTAINER_EDEFAULT.equals(currentContainer);
      case ModelPackage.DATA_ITERATION_PATTERN__ACTIVITY:
        return activity != null;
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
    result.append(" (containerReferenceList: ");
    result.append(containerReferenceList);
    result.append(", currentContainer: ");
    result.append(currentContainer);
    result.append(')');
    return result.toString();
  }
} //DataIterationPatternImpl
