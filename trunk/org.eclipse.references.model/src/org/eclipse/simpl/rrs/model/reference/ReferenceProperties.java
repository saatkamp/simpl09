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
 * A representation of the model object '<em><b>Properties</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.ReferenceProperties#getResolutionSystem <em>Resolution System</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getReferenceProperties()
 * @model
 * @generated
 */
public interface ReferenceProperties extends EObject {
	/**
	 * Returns the value of the '<em><b>Resolution System</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolution System</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolution System</em>' containment reference.
	 * @see #setResolutionSystem(RRSAdapter)
	 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getReferenceProperties_ResolutionSystem()
	 * @model containment="true" required="true"
	 * @generated
	 */
	RRSAdapter getResolutionSystem();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.reference.ReferenceProperties#getResolutionSystem <em>Resolution System</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolution System</em>' containment reference.
	 * @see #getResolutionSystem()
	 * @generated
	 */
	void setResolutionSystem(RRSAdapter value);

} // ReferenceProperties
