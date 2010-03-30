/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.simpl.rrs.model.rrs;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EPR</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.simpl.rrs.model.rrs.EPR#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.rrs.EPR#getReferenceProperties <em>Reference Properties</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.rrs.EPR#getReferenceParameters <em>Reference Parameters</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.rrs.EPR#getPortType <em>Port Type</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.rrs.EPR#getServiceName <em>Service Name</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.rrs.EPR#getPolicy <em>Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getEPR()
 * @model extendedMetaData="name='EPR' kind='elementOnly'"
 * @generated
 */
public interface EPR extends EObject {
	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getEPR_Address()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='element' name='address' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

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
	 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getEPR_ReferenceProperties()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='referenceProperties' namespace='##targetNamespace'"
	 * @generated
	 */
	ReferenceProperties getReferenceProperties();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getReferenceProperties <em>Reference Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Properties</em>' containment reference.
	 * @see #getReferenceProperties()
	 * @generated
	 */
	void setReferenceProperties(ReferenceProperties value);

	/**
	 * Returns the value of the '<em><b>Reference Parameters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Parameters</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Parameters</em>' attribute.
	 * @see #setReferenceParameters(String)
	 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getEPR_ReferenceParameters()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='referenceParameters' namespace='##targetNamespace'"
	 * @generated
	 */
	String getReferenceParameters();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getReferenceParameters <em>Reference Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Parameters</em>' attribute.
	 * @see #getReferenceParameters()
	 * @generated
	 */
	void setReferenceParameters(String value);

	/**
	 * Returns the value of the '<em><b>Port Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Type</em>' attribute.
	 * @see #setPortType(QName)
	 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getEPR_PortType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='portType' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getPortType();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getPortType <em>Port Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Type</em>' attribute.
	 * @see #getPortType()
	 * @generated
	 */
	void setPortType(QName value);

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
	 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getEPR_ServiceName()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='serviceName' namespace='##targetNamespace'"
	 * @generated
	 */
	ServiceName getServiceName();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getServiceName <em>Service Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Name</em>' containment reference.
	 * @see #getServiceName()
	 * @generated
	 */
	void setServiceName(ServiceName value);

	/**
	 * Returns the value of the '<em><b>Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy</em>' attribute.
	 * @see #setPolicy(String)
	 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getEPR_Policy()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='policy' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getPolicy <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' attribute.
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(String value);

} // EPR
