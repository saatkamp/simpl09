/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.ContainerToContainerPattern;
import org.eclipse.bpel.simpl.model.ModelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container To Container Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.ContainerToContainerPatternImpl#getSourceContainer <em>Source Container</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.ContainerToContainerPatternImpl#getTargetContainer <em>Target Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class ContainerToContainerPatternImpl extends DataManagementPatternImpl implements ContainerToContainerPattern {
  /**
   * The default value of the '{@link #getSourceContainer() <em>Source Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceContainer()
   * @generated
   * @ordered
   */
  protected static final String SOURCE_CONTAINER_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getSourceContainer() <em>Source Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceContainer()
   * @generated
   * @ordered
   */
  protected String sourceContainer = SOURCE_CONTAINER_EDEFAULT;

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
  protected ContainerToContainerPatternImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ModelPackage.Literals.CONTAINER_TO_CONTAINER_PATTERN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSourceContainer() {
    return sourceContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceContainer(String newSourceContainer) {
    String oldSourceContainer = sourceContainer;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getContainerToContainerPattern_SourceContainer().getName(),
          newSourceContainer);
    }
    sourceContainer = newSourceContainer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__SOURCE_CONTAINER, oldSourceContainer, sourceContainer));
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
          .getContainerToContainerPattern_TargetContainer().getName(),
          newTargetContainer);
    }
    sourceContainer = newTargetContainer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__TARGET_CONTAINER, oldTargetContainer, targetContainer));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__SOURCE_CONTAINER:
        return getSourceContainer();
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__TARGET_CONTAINER:
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
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__SOURCE_CONTAINER:
        setSourceContainer((String)newValue);
        return;
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__TARGET_CONTAINER:
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
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__SOURCE_CONTAINER:
        setSourceContainer(SOURCE_CONTAINER_EDEFAULT);
        return;
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__TARGET_CONTAINER:
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
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__SOURCE_CONTAINER:
        return SOURCE_CONTAINER_EDEFAULT == null ? sourceContainer != null : !SOURCE_CONTAINER_EDEFAULT.equals(sourceContainer);
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN__TARGET_CONTAINER:
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
    result.append(" (sourceContainer: ");
    result.append(sourceContainer);
    result.append(", targetContainer: ");
    result.append(targetContainer);
    result.append(')');
    return result.toString();
  }

} //ContainerToContainerPatternImpl
