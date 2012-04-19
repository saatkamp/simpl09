/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transfer Data Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to transfer data between two datasources.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSource <em>Data Source</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSourceCommand <em>Data Source Command</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSink <em>Data Sink</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSinkContainer <em>Data Sink Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity()
 * @model
 * @generated
 */
public interface TransferDataActivity extends DataManagementActivity {
  /**
   * Returns the value of the '<em><b>Data Source</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Source</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Source</em>' attribute.
   * @see #setDataSource(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_DataSource()
   * @model default="" required="true"
   * @generated
   */
  String getDataSource();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSource <em>Data Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Source</em>' attribute.
   * @see #getDataSource()
   * @generated
   */
  void setDataSource(String value);

  /**
   * Returns the value of the '<em><b>Data Source Command</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Source Command</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Source Command</em>' attribute.
   * @see #setDataSourceCommand(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_DataSourceCommand()
   * @model default="" required="true"
   * @generated
   */
  String getDataSourceCommand();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSourceCommand <em>Data Source Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Source Command</em>' attribute.
   * @see #getDataSourceCommand()
   * @generated
   */
  void setDataSourceCommand(String value);

  /**
   * Returns the value of the '<em><b>Data Sink</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Sink</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Sink</em>' attribute.
   * @see #setDataSink(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_DataSink()
   * @model default="" required="true"
   * @generated
   */
  String getDataSink();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSink <em>Data Sink</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Sink</em>' attribute.
   * @see #getDataSink()
   * @generated
   */
  void setDataSink(String value);

  /**
   * Returns the value of the '<em><b>Data Sink Container</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Sink Container</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Sink Container</em>' attribute.
   * @see #setDataSinkContainer(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_DataSinkContainer()
   * @model default="" required="true"
   * @generated
   */
  String getDataSinkContainer();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSinkContainer <em>Data Sink Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Sink Container</em>' attribute.
   * @see #getDataSinkContainer()
   * @generated
   */
  void setDataSinkContainer(String value);

} // TransferDataActivity
