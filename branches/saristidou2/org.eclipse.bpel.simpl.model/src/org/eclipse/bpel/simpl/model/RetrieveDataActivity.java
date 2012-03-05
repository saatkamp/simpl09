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
 *   <li>{@link org.eclipse.bpel.simpl.model.RetrieveDataActivity#getDataVariable <em>Data Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getRetrieveDataActivity()
 * @model
 * @generated
 */
public interface RetrieveDataActivity extends DataManagementActivity {

	/**
	 * Returns the value of the '<em><b>Data Variable</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '' reference.
	 * @see #setDataVariable(Variable)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getRetrieveDataActivity_DataVariable()
	 * @model
	 * @generated
	 */
	Variable getDataVariable();

	/**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.RetrieveDataActivity#getDataVariable <em>Data Variable</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Variable</em>' reference.
   * @see #getDataVariable()
   * @generated
   */
	void setDataVariable(Variable value);
} // RetrieveDataActivity
