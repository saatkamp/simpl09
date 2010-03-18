/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.rrs.model.reference.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.rrs.model.reference.RRSAdapter;
import org.eclipse.rrs.model.reference.ReferencePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RRS Adapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.rrs.model.reference.impl.RRSAdapterImpl#getAdapterURI <em>Adapter URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RRSAdapterImpl extends EObjectImpl implements RRSAdapter {
	/**
	 * The default value of the '{@link #getAdapterURI() <em>Adapter URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdapterURI()
	 * @generated
	 * @ordered
	 */
	protected static final String ADAPTER_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdapterURI() <em>Adapter URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdapterURI()
	 * @generated
	 * @ordered
	 */
	protected String adapterURI = ADAPTER_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RRSAdapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReferencePackage.Literals.RRS_ADAPTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAdapterURI() {
		return adapterURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdapterURI(String newAdapterURI) {
		String oldAdapterURI = adapterURI;
		adapterURI = newAdapterURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.RRS_ADAPTER__ADAPTER_URI, oldAdapterURI, adapterURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReferencePackage.RRS_ADAPTER__ADAPTER_URI:
				return getAdapterURI();
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
			case ReferencePackage.RRS_ADAPTER__ADAPTER_URI:
				setAdapterURI((String)newValue);
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
			case ReferencePackage.RRS_ADAPTER__ADAPTER_URI:
				setAdapterURI(ADAPTER_URI_EDEFAULT);
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
			case ReferencePackage.RRS_ADAPTER__ADAPTER_URI:
				return ADAPTER_URI_EDEFAULT == null ? adapterURI != null : !ADAPTER_URI_EDEFAULT.equals(adapterURI);
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
		result.append(" (adapterURI: ");
		result.append(adapterURI);
		result.append(')');
		return result.toString();
	}

} //RRSAdapterImpl
