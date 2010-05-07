/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model;


// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to query data on a data source. The queried data will be stored on the data source.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.QueryActivity#getQueryTarget <em>Query Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getQueryActivity()
 * @model
 * @generated
 */
public interface QueryActivity extends DataManagementActivity {

	/**
	 * Returns the value of the '<em><b>Query Target</b></em>' attribute. The
	 * default value is <code>"target"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query Target</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '' attribute.
	 * @see #setQueryTarget(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getQueryActivity_QueryTarget()
	 * @model default="target"
	 * @generated
	 */
	String getQueryTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.QueryActivity#getQueryTarget <em>Query Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query Target</em>' attribute.
	 * @see #getQueryTarget()
	 * @generated
	 */
	void setQueryTarget(String value);
} // QueryActivity
