/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query Data Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to query data on a data source. The queried data will be stored on the data source.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.QueryDataActivity#getTargetContainer <em>Target Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getQueryDataActivity()
 * @model
 * @generated
 */
public interface QueryDataActivity extends DataManagementActivity {
  /**
   * Returns the value of the '<em><b>Target Container</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Container</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Container</em>' attribute.
   * @see #setTargetContainer(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getQueryDataActivity_TargetContainer()
   * @model default="" required="true"
   * @generated
   */
  String getTargetContainer();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.QueryDataActivity#getTargetContainer <em>Target Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Container</em>' attribute.
   * @see #getTargetContainer()
   * @generated
   */
  void setTargetContainer(String value);

} // QueryDataActivity
