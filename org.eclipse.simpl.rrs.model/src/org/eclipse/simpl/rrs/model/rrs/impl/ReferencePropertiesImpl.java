/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.simpl.rrs.model.rrs.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.simpl.rrs.model.rrs.RRSPackage;
import org.eclipse.simpl.rrs.model.rrs.ReferenceProperties;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Properties</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.simpl.rrs.model.rrs.impl.ReferencePropertiesImpl#getResolutionSystem <em>Resolution System</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferencePropertiesImpl extends EObjectImpl implements ReferenceProperties {
	/**
	 * The default value of the '{@link #getResolutionSystem() <em>Resolution System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionSystem()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOLUTION_SYSTEM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolutionSystem() <em>Resolution System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionSystem()
	 * @generated
	 * @ordered
	 */
	protected String resolutionSystem = RESOLUTION_SYSTEM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferencePropertiesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RRSPackage.Literals.REFERENCE_PROPERTIES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResolutionSystem() {
		return resolutionSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolutionSystem(String newResolutionSystem) {
		String oldResolutionSystem = resolutionSystem;
		resolutionSystem = newResolutionSystem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RRSPackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM, oldResolutionSystem, resolutionSystem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RRSPackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
				return getResolutionSystem();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RRSPackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
				setResolutionSystem((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RRSPackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
				setResolutionSystem(RESOLUTION_SYSTEM_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RRSPackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
				return RESOLUTION_SYSTEM_EDEFAULT == null ? resolutionSystem != null : !RESOLUTION_SYSTEM_EDEFAULT.equals(resolutionSystem);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (resolutionSystem: ");
		result.append(resolutionSystem);
		result.append(')');
		return result.toString();
	}

} //ReferencePropertiesImpl
