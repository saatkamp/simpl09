/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: RetrieveDataActivity.java 1734 2010-12-10 17:05:18Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.bpel.model.Variable;


// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Retrieve Data Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to load data into the process space to work with it.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.RetrieveDataActivity#getTargetVariable <em>Target Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getRetrieveDataActivity()
 * @model
 * @generated
 */
public interface RetrieveDataActivity extends DataManagementActivity {

	/**
   * Returns the value of the '<em><b>Target Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Variable</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Variable</em>' reference.
   * @see #setTargetVariable(Variable)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getRetrieveDataActivity_TargetVariable()
   * @model required="true"
   * @generated
   */
  Variable getTargetVariable();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.RetrieveDataActivity#getTargetVariable <em>Target Variable</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Variable</em>' reference.
   * @see #getTargetVariable()
   * @generated
   */
  void setTargetVariable(Variable value);
} // RetrieveDataActivity
