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
 *   <li>{@link org.eclipse.bpel.simpl.model.QueryDataActivity#getQueryTarget <em>Query Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getQueryDataActivity()
 * @model
 * @generated
 */
public interface QueryDataActivity extends DataManagementActivity {
  /**
   * Returns the value of the '<em><b>Query Target</b></em>' attribute.
   * The default value is <code>"target"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Query Target</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Query Target</em>' attribute.
   * @see #setQueryTarget(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getQueryDataActivity_QueryTarget()
   * @model default="target"
   * @generated
   */
  String getQueryTarget();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.QueryDataActivity#getQueryTarget <em>Query Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Query Target</em>' attribute.
   * @see #getQueryTarget()
   * @generated
   */
  void setQueryTarget(String value);

} // QueryDataActivity
