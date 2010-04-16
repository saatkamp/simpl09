/**
 * Copyright (c) 2008 IBM Corporation, University of Stuttgart (IAAS) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation, University of Stuttgart (IAAS) - initial API and implementation
 *
 * $Id$
 */
package org.eclipse.bpel.apache.ode.deploy.model.dd.impl;

import org.eclipse.bpel.apache.ode.deploy.model.dd.TPolicy;
import org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TPolicy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.impl.TPolicyImpl#getPolicyData <em>Policy Data</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.impl.TPolicyImpl#getLocalPath <em>Local Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TPolicyImpl extends EObjectImpl implements TPolicy {
	/**
	 * The default value of the '{@link #getPolicyData() <em>Policy Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicyData()
	 * @generated
	 * @ordered
	 */
	protected static final String POLICY_DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPolicyData() <em>Policy Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicyData()
	 * @generated
	 * @ordered
	 */
	protected String policyData = POLICY_DATA_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocalPath() <em>Local Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalPath()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalPath() <em>Local Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalPath()
	 * @generated
	 * @ordered
	 */
	protected String localPath = LOCAL_PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TPolicyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ddPackage.Literals.TPOLICY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPolicyData() {
		return policyData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPolicyData(String newPolicyData) {
		String oldPolicyData = policyData;
		policyData = newPolicyData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ddPackage.TPOLICY__POLICY_DATA, oldPolicyData, policyData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocalPath() {
		return localPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalPath(String newLocalPath) {
		String oldLocalPath = localPath;
		localPath = newLocalPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ddPackage.TPOLICY__LOCAL_PATH, oldLocalPath, localPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ddPackage.TPOLICY__POLICY_DATA:
				return getPolicyData();
			case ddPackage.TPOLICY__LOCAL_PATH:
				return getLocalPath();
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
			case ddPackage.TPOLICY__POLICY_DATA:
				setPolicyData((String)newValue);
				return;
			case ddPackage.TPOLICY__LOCAL_PATH:
				setLocalPath((String)newValue);
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
			case ddPackage.TPOLICY__POLICY_DATA:
				setPolicyData(POLICY_DATA_EDEFAULT);
				return;
			case ddPackage.TPOLICY__LOCAL_PATH:
				setLocalPath(LOCAL_PATH_EDEFAULT);
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
			case ddPackage.TPOLICY__POLICY_DATA:
				return POLICY_DATA_EDEFAULT == null ? policyData != null : !POLICY_DATA_EDEFAULT.equals(policyData);
			case ddPackage.TPOLICY__LOCAL_PATH:
				return LOCAL_PATH_EDEFAULT == null ? localPath != null : !LOCAL_PATH_EDEFAULT.equals(localPath);
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
		result.append(" (policyData: ");
		result.append(policyData);
		result.append(", localPath: ");
		result.append(localPath);
		result.append(')');
		return result.toString();
	}

} //TPolicyImpl
