/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.bpel.model.ExtensionActivity;


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
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsAddress <em>Target Ds Address</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsType <em>Target Ds Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsKind <em>Target Ds Kind</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsLanguage <em>Target Ds Language</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsContainer <em>Target Ds Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity()
 * @model
 * @generated
 */
public interface TransferActivity extends DataManagementActivity {
	/**
	 * Returns the value of the '<em><b>Target Ds Address</b></em>' attribute.
	 * The default value is <code>"address"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Ds Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Ds Address</em>' attribute.
	 * @see #setTargetDsAddress(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity_TargetDsAddress()
	 * @model default="address"
	 * @generated
	 */
	String getTargetDsAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsAddress <em>Target Ds Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Ds Address</em>' attribute.
	 * @see #getTargetDsAddress()
	 * @generated
	 */
	void setTargetDsAddress(String value);

	/**
	 * Returns the value of the '<em><b>Target Ds Type</b></em>' attribute.
	 * The default value is <code>"type"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Ds Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Ds Type</em>' attribute.
	 * @see #setTargetDsType(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity_TargetDsType()
	 * @model default="type"
	 * @generated
	 */
	String getTargetDsType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsType <em>Target Ds Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Ds Type</em>' attribute.
	 * @see #getTargetDsType()
	 * @generated
	 */
	void setTargetDsType(String value);

	/**
	 * Returns the value of the '<em><b>Target Ds Kind</b></em>' attribute.
	 * The default value is <code>"kind"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Ds Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Ds Kind</em>' attribute.
	 * @see #setTargetDsKind(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity_TargetDsKind()
	 * @model default="kind"
	 * @generated
	 */
	String getTargetDsKind();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsKind <em>Target Ds Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Ds Kind</em>' attribute.
	 * @see #getTargetDsKind()
	 * @generated
	 */
	void setTargetDsKind(String value);

	/**
	 * Returns the value of the '<em><b>Target Ds Language</b></em>' attribute.
	 * The default value is <code>"language"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Ds Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Ds Language</em>' attribute.
	 * @see #setTargetDsLanguage(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity_TargetDsLanguage()
	 * @model default="language"
	 * @generated
	 */
	String getTargetDsLanguage();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsLanguage <em>Target Ds Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Ds Language</em>' attribute.
	 * @see #getTargetDsLanguage()
	 * @generated
	 */
	void setTargetDsLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Target Ds Container</b></em>' attribute.
	 * The default value is <code>"targetDsContainer"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Ds Container</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Ds Container</em>' attribute.
	 * @see #setTargetDsContainer(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferActivity_TargetDsContainer()
	 * @model default="targetDsContainer"
	 * @generated
	 */
	String getTargetDsContainer();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsContainer <em>Target Ds Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Ds Container</em>' attribute.
	 * @see #getTargetDsContainer()
	 * @generated
	 */
	void setTargetDsContainer(String value);

} // TransferActivity
