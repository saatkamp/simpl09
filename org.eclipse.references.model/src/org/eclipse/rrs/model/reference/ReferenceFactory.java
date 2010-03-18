/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.rrs.model.reference;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rrs.model.reference.ReferencePackage
 * @generated
 */
public interface ReferenceFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReferenceFactory eINSTANCE = org.eclipse.rrs.model.reference.impl.ReferenceFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>EPR</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EPR</em>'.
	 * @generated
	 */
	EPR createEPR();

	/**
	 * Returns a new object of class '<em>Port Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Type</em>'.
	 * @generated
	 */
	PortType createPortType();

	/**
	 * Returns a new object of class '<em>Address</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Address</em>'.
	 * @generated
	 */
	Address createAddress();

	/**
	 * Returns a new object of class '<em>Properties</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Properties</em>'.
	 * @generated
	 */
	ReferenceProperties createReferenceProperties();

	/**
	 * Returns a new object of class '<em>Parameters</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameters</em>'.
	 * @generated
	 */
	ReferenceParameters createReferenceParameters();

	/**
	 * Returns a new object of class '<em>Service Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Name</em>'.
	 * @generated
	 */
	ServiceName createServiceName();

	/**
	 * Returns a new object of class '<em>Policy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Policy</em>'.
	 * @generated
	 */
	Policy createPolicy();

	/**
	 * Returns a new object of class '<em>RRS Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>RRS Adapter</em>'.
	 * @generated
	 */
	RRSAdapter createRRSAdapter();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ReferencePackage getReferencePackage();

} //ReferenceFactory
