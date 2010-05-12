/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transfer Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to transfer data between two datasources.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferActivity#getFromSource <em>From Source</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferActivity#getToSource <em>To Source</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferActivity#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity()
 * @model
 * @generated
 */
public interface TransferActivity extends DataManagementActivity {
	/**
	 * Returns the value of the '<em><b>From Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Source</em>' reference.
	 * @see #setFromSource(DataManagementActivity)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity_FromSource()
	 * @model
	 * @generated
	 */
	DataManagementActivity getFromSource();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferActivity#getFromSource <em>From Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Source</em>' reference.
	 * @see #getFromSource()
	 * @generated
	 */
	void setFromSource(DataManagementActivity value);

	/**
	 * Returns the value of the '<em><b>To Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Source</em>' reference.
	 * @see #setToSource(DataManagementActivity)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity_ToSource()
	 * @model
	 * @generated
	 */
	DataManagementActivity getToSource();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferActivity#getToSource <em>To Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Source</em>' reference.
	 * @see #getToSource()
	 * @generated
	 */
	void setToSource(DataManagementActivity value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity_Target()
	 * @model
	 * @generated
	 */
	String getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(String value);

} // TransferActivity
