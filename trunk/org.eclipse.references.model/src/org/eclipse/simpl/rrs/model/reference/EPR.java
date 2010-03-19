/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.simpl.rrs.model.reference;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EPR</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.EPR#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.EPR#getReferenceProperties <em>Reference Properties</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.EPR#getReferenceParameters <em>Reference Parameters</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.EPR#getPortType <em>Port Type</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.EPR#getServiceName <em>Service Name</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.EPR#getPolicy <em>Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getEPR()
 * @model
 * @generated
 */
public interface EPR extends EObject {
	/**
	 * Returns the value of the '<em><b>Address</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' containment reference.
	 * @see #setAddress(Address)
	 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getEPR_Address()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Address getAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.reference.EPR#getAddress <em>Address</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' containment reference.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(Address value);

	/**
	 * Returns the value of the '<em><b>Reference Properties</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Properties</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Properties</em>' containment reference.
	 * @see #setReferenceProperties(ReferenceProperties)
	 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getEPR_ReferenceProperties()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ReferenceProperties getReferenceProperties();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.reference.EPR#getReferenceProperties <em>Reference Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Properties</em>' containment reference.
	 * @see #getReferenceProperties()
	 * @generated
	 */
	void setReferenceProperties(ReferenceProperties value);

	/**
	 * Returns the value of the '<em><b>Reference Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Parameters</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Parameters</em>' containment reference.
	 * @see #setReferenceParameters(ReferenceParameters)
	 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getEPR_ReferenceParameters()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ReferenceParameters getReferenceParameters();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.reference.EPR#getReferenceParameters <em>Reference Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Parameters</em>' containment reference.
	 * @see #getReferenceParameters()
	 * @generated
	 */
	void setReferenceParameters(ReferenceParameters value);

	/**
	 * Returns the value of the '<em><b>Port Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Type</em>' containment reference.
	 * @see #setPortType(PortType)
	 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getEPR_PortType()
	 * @model containment="true"
	 * @generated
	 */
	PortType getPortType();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.reference.EPR#getPortType <em>Port Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Type</em>' containment reference.
	 * @see #getPortType()
	 * @generated
	 */
	void setPortType(PortType value);

	/**
	 * Returns the value of the '<em><b>Service Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Name</em>' containment reference.
	 * @see #setServiceName(ServiceName)
	 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getEPR_ServiceName()
	 * @model containment="true"
	 * @generated
	 */
	ServiceName getServiceName();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.reference.EPR#getServiceName <em>Service Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Name</em>' containment reference.
	 * @see #getServiceName()
	 * @generated
	 */
	void setServiceName(ServiceName value);

	/**
	 * Returns the value of the '<em><b>Policy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy</em>' containment reference.
	 * @see #setPolicy(Policy)
	 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getEPR_Policy()
	 * @model containment="true"
	 * @generated
	 */
	Policy getPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.reference.EPR#getPolicy <em>Policy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' containment reference.
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(Policy value);

} // EPR
