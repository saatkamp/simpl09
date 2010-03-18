/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.rrs.model.reference.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.rrs.model.reference.RRSAdapter;
import org.eclipse.rrs.model.reference.ReferencePackage;
import org.eclipse.rrs.model.reference.ReferenceProperties;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Properties</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.rrs.model.reference.impl.ReferencePropertiesImpl#getResolutionSystem <em>Resolution System</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferencePropertiesImpl extends EObjectImpl implements ReferenceProperties {
	/**
	 * The cached value of the '{@link #getResolutionSystem() <em>Resolution System</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionSystem()
	 * @generated
	 * @ordered
	 */
	protected RRSAdapter resolutionSystem;

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
		return ReferencePackage.Literals.REFERENCE_PROPERTIES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRSAdapter getResolutionSystem() {
		return resolutionSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResolutionSystem(RRSAdapter newResolutionSystem, NotificationChain msgs) {
		RRSAdapter oldResolutionSystem = resolutionSystem;
		resolutionSystem = newResolutionSystem;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM, oldResolutionSystem, newResolutionSystem);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolutionSystem(RRSAdapter newResolutionSystem) {
		if (newResolutionSystem != resolutionSystem) {
			NotificationChain msgs = null;
			if (resolutionSystem != null)
				msgs = ((InternalEObject)resolutionSystem).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM, null, msgs);
			if (newResolutionSystem != null)
				msgs = ((InternalEObject)newResolutionSystem).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM, null, msgs);
			msgs = basicSetResolutionSystem(newResolutionSystem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM, newResolutionSystem, newResolutionSystem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
				return basicSetResolutionSystem(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
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
			case ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
				setResolutionSystem((RRSAdapter)newValue);
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
			case ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
				setResolutionSystem((RRSAdapter)null);
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
			case ReferencePackage.REFERENCE_PROPERTIES__RESOLUTION_SYSTEM:
				return resolutionSystem != null;
		}
		return super.eIsSet(featureID);
	}

} //ReferencePropertiesImpl
