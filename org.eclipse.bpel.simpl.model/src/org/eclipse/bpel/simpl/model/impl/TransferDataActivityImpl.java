/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.TransferDataActivity;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transfer Data Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferDataActivityImpl#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferDataActivityImpl#getDataSourceCommand <em>Data Source Command</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferDataActivityImpl#getDataSink <em>Data Sink</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferDataActivityImpl#getDataSinkContainer <em>Data Sink Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class TransferDataActivityImpl extends DataManagementActivityImpl implements TransferDataActivity {
  /**
   * The default value of the '{@link #getDataSource() <em>Data Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSource()
   * @generated
   * @ordered
   */
  protected static final String DATA_SOURCE_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getDataSource() <em>Data Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSource()
   * @generated
   * @ordered
   */
  protected String dataSource = DATA_SOURCE_EDEFAULT;

  /**
   * The default value of the '{@link #getDataSourceCommand() <em>Data Source Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSourceCommand()
   * @generated
   * @ordered
   */
  protected static final String DATA_SOURCE_COMMAND_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getDataSourceCommand() <em>Data Source Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSourceCommand()
   * @generated
   * @ordered
   */
  protected String dataSourceCommand = DATA_SOURCE_COMMAND_EDEFAULT;

  /**
   * The default value of the '{@link #getDataSink() <em>Data Sink</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSink()
   * @generated
   * @ordered
   */
  protected static final String DATA_SINK_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getDataSink() <em>Data Sink</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSink()
   * @generated
   * @ordered
   */
  protected String dataSink = DATA_SINK_EDEFAULT;

  /**
   * The default value of the '{@link #getDataSinkContainer() <em>Data Sink Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSinkContainer()
   * @generated
   * @ordered
   */
  protected static final String DATA_SINK_CONTAINER_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getDataSinkContainer() <em>Data Sink Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSinkContainer()
   * @generated
   * @ordered
   */
  protected String dataSinkContainer = DATA_SINK_CONTAINER_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TransferDataActivityImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ModelPackage.Literals.TRANSFER_DATA_ACTIVITY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDataSource() {
    return dataSource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataSource(String newDataSource) {
    String oldDataSource = dataSource;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getTransferDataActivity_DataSource().getName(),
          newDataSource);
    }
    dataSource = newDataSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE, oldDataSource, dataSource));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDataSourceCommand() {
    return dataSourceCommand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataSourceCommand(String newDataSourceCommand) {
    String oldDataSourceCommand = dataSourceCommand;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getTransferDataActivity_DataSourceCommand().getName(),
          newDataSourceCommand);
    }
    dataSourceCommand = newDataSourceCommand;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE_COMMAND, oldDataSourceCommand, dataSourceCommand));
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDataSink() {
    return dataSink;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataSink(String newDataSink) {
    String oldDataSink = dataSink;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getTransferDataActivity_DataSink().getName(),
          newDataSink);
    }
    dataSink = newDataSink;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK, oldDataSink, dataSink));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDataSinkContainer() {
    return dataSinkContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataSinkContainer(String newDataSinkContainer) {
    String oldDataSinkContainer = dataSinkContainer;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
          .getTransferDataActivity_DataSinkContainer().getName(),
          newDataSinkContainer);
    }
    dataSinkContainer = newDataSinkContainer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK_CONTAINER, oldDataSinkContainer, dataSinkContainer));
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE:
        return getDataSource();
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE_COMMAND:
        return getDataSourceCommand();
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK:
        return getDataSink();
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK_CONTAINER:
        return getDataSinkContainer();
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
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE:
        setDataSource((String)newValue);
        return;
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE_COMMAND:
        setDataSourceCommand((String)newValue);
        return;
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK:
        setDataSink((String)newValue);
        return;
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK_CONTAINER:
        setDataSinkContainer((String)newValue);
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
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE:
        setDataSource(DATA_SOURCE_EDEFAULT);
        return;
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE_COMMAND:
        setDataSourceCommand(DATA_SOURCE_COMMAND_EDEFAULT);
        return;
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK:
        setDataSink(DATA_SINK_EDEFAULT);
        return;
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK_CONTAINER:
        setDataSinkContainer(DATA_SINK_CONTAINER_EDEFAULT);
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
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE:
        return DATA_SOURCE_EDEFAULT == null ? dataSource != null : !DATA_SOURCE_EDEFAULT.equals(dataSource);
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SOURCE_COMMAND:
        return DATA_SOURCE_COMMAND_EDEFAULT == null ? dataSourceCommand != null : !DATA_SOURCE_COMMAND_EDEFAULT.equals(dataSourceCommand);
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK:
        return DATA_SINK_EDEFAULT == null ? dataSink != null : !DATA_SINK_EDEFAULT.equals(dataSink);
      case ModelPackage.TRANSFER_DATA_ACTIVITY__DATA_SINK_CONTAINER:
        return DATA_SINK_CONTAINER_EDEFAULT == null ? dataSinkContainer != null : !DATA_SINK_CONTAINER_EDEFAULT.equals(dataSinkContainer);
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
    result.append(" (dataSource: ");
    result.append(dataSource);
    result.append(", dataSourceCommand: ");
    result.append(dataSourceCommand);
    result.append(", dataSink: ");
    result.append(dataSink);
    result.append(", dataSinkContainer: ");
    result.append(dataSinkContainer);
    result.append(')');
    return result.toString();
  }

} //TransferDataActivityImpl
