/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.stuttgart.uni.simpl.rrs;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.stuttgart.uni.simpl.rrs.ServiceName#getValue <em>Value</em>}</li>
 *   <li>{@link de.stuttgart.uni.simpl.rrs.ServiceName#getPortName <em>Port Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.stuttgart.uni.simpl.rrs.RRSPackage#getServiceName()
 * @model extendedMetaData="name='ServiceName' kind='simple'"
 * @generated
 */
public interface ServiceName extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(QName)
	 * @see de.stuttgart.uni.simpl.rrs.RRSPackage#getServiceName_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="name=':0' kind='simple'"
	 * @generated
	 */
	QName getValue();

	/**
	 * Sets the value of the '{@link de.stuttgart.uni.simpl.rrs.ServiceName#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(QName value);

	/**
	 * Returns the value of the '<em><b>Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Name</em>' attribute.
	 * @see #setPortName(String)
	 * @see de.stuttgart.uni.simpl.rrs.RRSPackage#getServiceName_PortName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NCName"
	 *        extendedMetaData="kind='attribute' name='portName'"
	 * @generated
	 */
	String getPortName();

	/**
	 * Sets the value of the '{@link de.stuttgart.uni.simpl.rrs.ServiceName#getPortName <em>Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Name</em>' attribute.
	 * @see #getPortName()
	 * @generated
	 */
	void setPortName(String value);

} // ServiceName
