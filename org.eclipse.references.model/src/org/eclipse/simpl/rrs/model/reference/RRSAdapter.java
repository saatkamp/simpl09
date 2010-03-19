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
 * A representation of the model object '<em><b>RRS Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.RRSAdapter#getAdapterURI <em>Adapter URI</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getRRSAdapter()
 * @model
 * @generated
 */
public interface RRSAdapter extends EObject {
	/**
	 * Returns the value of the '<em><b>Adapter URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adapter URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adapter URI</em>' attribute.
	 * @see #setAdapterURI(String)
	 * @see org.eclipse.simpl.rrs.model.reference.ReferencePackage#getRRSAdapter_AdapterURI()
	 * @model required="true"
	 * @generated
	 */
	String getAdapterURI();

	/**
	 * Sets the value of the '{@link org.eclipse.simpl.rrs.model.reference.RRSAdapter#getAdapterURI <em>Adapter URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adapter URI</em>' attribute.
	 * @see #getAdapterURI()
	 * @generated
	 */
	void setAdapterURI(String value);

} // RRSAdapter
