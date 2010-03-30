/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.QueryActivityImpl#getQueryTarget <em>Query Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class QueryActivityImpl extends DataManagementActivityImpl implements QueryActivity {
	/**
	 * The default value of the '{@link #getQueryTarget() <em>Query Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryTarget()
	 * @generated
	 * @ordered
	 */
	protected static final String QUERY_TARGET_EDEFAULT = "target";
	/**
	 * The cached value of the '{@link #getQueryTarget() <em>Query Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryTarget()
	 * @generated
	 * @ordered
	 */
	protected String queryTarget = QUERY_TARGET_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QueryActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.QUERY_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the query target
	 * @generated
	 */
	public String getQueryTarget() {
		return queryTarget;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param newQueryTarget
	 *            the new query target
	 * @customized
	 */
	public void setQueryTarget(String newQueryTarget) {
		String oldQueryTarget = queryTarget;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getQueryActivity_QueryTarget().getName(),
					newQueryTarget);
		}
		queryTarget = newQueryTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.QUERY_ACTIVITY__QUERY_TARGET, oldQueryTarget, queryTarget));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param featureID
	 *            the feature id
	 * @param resolve
	 *            the resolve
	 * @param coreType
	 *            the core type
	 * @return the object
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.QUERY_ACTIVITY__QUERY_TARGET:
				return getQueryTarget();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param featureID
	 *            the feature id
	 * @param newValue
	 *            the new value
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.QUERY_ACTIVITY__QUERY_TARGET:
				setQueryTarget((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param featureID
	 *            the feature id
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.QUERY_ACTIVITY__QUERY_TARGET:
				setQueryTarget(QUERY_TARGET_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param featureID
	 *            the feature id
	 * @return true, if successful
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.QUERY_ACTIVITY__QUERY_TARGET:
				return QUERY_TARGET_EDEFAULT == null ? queryTarget != null : !QUERY_TARGET_EDEFAULT.equals(queryTarget);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the string
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (queryTarget: ");
		result.append(queryTarget);
		result.append(')');
		return result.toString();
	}

} //QueryActivityImpl
