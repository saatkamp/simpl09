/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.bpel.model.Variable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Write Data Back Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to write data back to a data source.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity#getDataVariable <em>Data Variable</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity#getWriteTarget <em>Write Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getWriteDataBackActivity()
 * @model
 * @generated
 */
public interface WriteDataBackActivity extends DataManagementActivity {
  /**
   * Returns the value of the '<em><b>Data Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Variable</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Variable</em>' reference.
   * @see #setDataVariable(Variable)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getWriteDataBackActivity_DataVariable()
   * @model
   * @generated
   */
  Variable getDataVariable();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity#getDataVariable <em>Data Variable</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Variable</em>' reference.
   * @see #getDataVariable()
   * @generated
   */
  void setDataVariable(Variable value);

  /**
   * Returns the value of the '<em><b>Write Target</b></em>' attribute.
   * The default value is <code>"target"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Write Target</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Write Target</em>' attribute.
   * @see #setWriteTarget(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getWriteDataBackActivity_WriteTarget()
   * @model default="target"
   * @generated
   */
  String getWriteTarget();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity#getWriteTarget <em>Write Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Write Target</em>' attribute.
   * @see #getWriteTarget()
   * @generated
   */
  void setWriteTarget(String value);

} // WriteDataBackActivity
