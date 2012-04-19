/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.impl.ExtensionActivityImpl;
import org.eclipse.bpel.model.util.ReconciliationHelper;

import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Management Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl#getDataResource <em>Data Resource</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl#getDmCommand <em>Dm Command</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class DataManagementActivityImpl extends ExtensionActivityImpl implements DataManagementActivity {
  /**
   * The default value of the '{@link #getDataResource() <em>Data Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataResource()
   * @generated
   * @ordered
   */
  protected static final String DATA_RESOURCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDataResource() <em>Data Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataResource()
   * @generated
   * @ordered
   */
  protected String dataResource = DATA_RESOURCE_EDEFAULT;

  /**
   * The default value of the '{@link #getDmCommand() <em>Dm Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDmCommand()
   * @generated
   * @ordered
   */
  protected static final String DM_COMMAND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDmCommand() <em>Dm Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDmCommand()
   * @generated
   * @ordered
   */
  protected String dmCommand = DM_COMMAND_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DataManagementActivityImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ModelPackage.Literals.DATA_MANAGEMENT_ACTIVITY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDataResource() {
    return dataResource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataResource(String newDataResource) {
    String oldDataResource = dataResource;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getDataManagementActivity_DataResource().getName(),
          newDataResource);
    }
    dataResource = newDataResource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE, oldDataResource, dataResource));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDmCommand() {
    return dmCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDmCommand(String newDmCommand) {
    String oldDmCommand = dmCommand;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getDataManagementActivity_DmCommand().getName(),
          newDmCommand);
    }
    dmCommand = newDmCommand;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MANAGEMENT_ACTIVITY__DM_COMMAND, oldDmCommand, dmCommand));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE:
        return getDataResource();
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DM_COMMAND:
        return getDmCommand();
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
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE:
        setDataResource((String)newValue);
        return;
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DM_COMMAND:
        setDmCommand((String)newValue);
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
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE:
        setDataResource(DATA_RESOURCE_EDEFAULT);
        return;
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DM_COMMAND:
        setDmCommand(DM_COMMAND_EDEFAULT);
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
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE:
        return DATA_RESOURCE_EDEFAULT == null ? dataResource != null : !DATA_RESOURCE_EDEFAULT.equals(dataResource);
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DM_COMMAND:
        return DM_COMMAND_EDEFAULT == null ? dmCommand != null : !DM_COMMAND_EDEFAULT.equals(dmCommand);
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
    result.append(" (dataResource: ");
    result.append(dataResource);
    result.append(", dmCommand: ");
    result.append(dmCommand);
    result.append(')');
    return result.toString();
  }

} //DataManagementActivityImpl
