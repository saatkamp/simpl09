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

import org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping;
import org.eclipse.bpel.apache.ode.deploy.model.dd.TPolicy;
import org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TActivity Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.impl.TActivityMappingImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.impl.TActivityMappingImpl#getDataSourceName <em>Data Source Name</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.impl.TActivityMappingImpl#getPolicy <em>Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TActivityMappingImpl extends EObjectImpl implements TActivityMapping {
	/**
	 * The default value of the '{@link #getActivity() <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVITY_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getActivity() <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected String activity = ACTIVITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataSourceName() <em>Data Source Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataSourceName()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_SOURCE_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDataSourceName() <em>Data Source Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataSourceName()
	 * @generated
	 * @ordered
	 */
	protected String dataSourceName = DATA_SOURCE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPolicy() <em>Policy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicy()
	 * @generated
	 * @ordered
	 */
	protected TPolicy policy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TActivityMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ddPackage.Literals.TACTIVITY_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivity(String newActivity) {
		String oldActivity = activity;
		activity = newActivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ddPackage.TACTIVITY_MAPPING__ACTIVITY, oldActivity, activity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataSourceName() {
		return dataSourceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataSourceName(String newDataSourceName) {
		String oldDataSourceName = dataSourceName;
		dataSourceName = newDataSourceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ddPackage.TACTIVITY_MAPPING__DATA_SOURCE_NAME, oldDataSourceName, dataSourceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPolicy getPolicy() {
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPolicy(TPolicy newPolicy, NotificationChain msgs) {
		TPolicy oldPolicy = policy;
		policy = newPolicy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ddPackage.TACTIVITY_MAPPING__POLICY, oldPolicy, newPolicy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPolicy(TPolicy newPolicy) {
		if (newPolicy != policy) {
			NotificationChain msgs = null;
			if (policy != null)
				msgs = ((InternalEObject)policy).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ddPackage.TACTIVITY_MAPPING__POLICY, null, msgs);
			if (newPolicy != null)
				msgs = ((InternalEObject)newPolicy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ddPackage.TACTIVITY_MAPPING__POLICY, null, msgs);
			msgs = basicSetPolicy(newPolicy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ddPackage.TACTIVITY_MAPPING__POLICY, newPolicy, newPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ddPackage.TACTIVITY_MAPPING__POLICY:
				return basicSetPolicy(null, msgs);
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
			case ddPackage.TACTIVITY_MAPPING__ACTIVITY:
				return getActivity();
			case ddPackage.TACTIVITY_MAPPING__DATA_SOURCE_NAME:
				return getDataSourceName();
			case ddPackage.TACTIVITY_MAPPING__POLICY:
				return getPolicy();
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
			case ddPackage.TACTIVITY_MAPPING__ACTIVITY:
				setActivity((String)newValue);
				return;
			case ddPackage.TACTIVITY_MAPPING__DATA_SOURCE_NAME:
				setDataSourceName((String)newValue);
				return;
			case ddPackage.TACTIVITY_MAPPING__POLICY:
				setPolicy((TPolicy)newValue);
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
			case ddPackage.TACTIVITY_MAPPING__ACTIVITY:
				setActivity(ACTIVITY_EDEFAULT);
				return;
			case ddPackage.TACTIVITY_MAPPING__DATA_SOURCE_NAME:
				setDataSourceName(DATA_SOURCE_NAME_EDEFAULT);
				return;
			case ddPackage.TACTIVITY_MAPPING__POLICY:
				setPolicy((TPolicy)null);
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
			case ddPackage.TACTIVITY_MAPPING__ACTIVITY:
				return ACTIVITY_EDEFAULT == null ? activity != null : !ACTIVITY_EDEFAULT.equals(activity);
			case ddPackage.TACTIVITY_MAPPING__DATA_SOURCE_NAME:
				return DATA_SOURCE_NAME_EDEFAULT == null ? dataSourceName != null : !DATA_SOURCE_NAME_EDEFAULT.equals(dataSourceName);
			case ddPackage.TACTIVITY_MAPPING__POLICY:
				return policy != null;
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
		result.append(" (activity: ");
		result.append(activity);
		result.append(", dataSourceName: ");
		result.append(dataSourceName);
		result.append(')');
		return result.toString();
	}

} //TActivityMappingImpl
