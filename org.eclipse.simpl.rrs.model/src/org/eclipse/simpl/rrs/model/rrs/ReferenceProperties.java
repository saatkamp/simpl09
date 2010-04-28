/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.simpl.rrs.model.rrs;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Properties</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.simpl.rrs.model.rrs.ReferenceProperties#getResolutionSystem <em>Resolution System</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getReferenceProperties()
 * @model extendedMetaData="name='ReferenceProperties' kind='elementOnly'"
 * @generated
 */
public interface ReferenceProperties extends EObject {
	/**
	 * Returns the value of the '<em><b>Resolution System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolution System</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolution System</em>' attribute.
	 * @see #setResolutionSystem(String)
	 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#getReferenceProperties_ResolutionSystem()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='resolutionSystem' namespace='##targetNamespace'"
	 * @generated
	 */
	String getResolutionSystem();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.rrs.ReferenceProperties#getResolutionSystem <em>Resolution System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolution System</em>' attribute.
	 * @see #getResolutionSystem()
	 * @generated
	 */
	void setResolutionSystem(String value);

} // ReferenceProperties
