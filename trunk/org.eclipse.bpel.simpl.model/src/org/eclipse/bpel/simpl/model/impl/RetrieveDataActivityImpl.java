/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Retrieve Data Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.RetrieveDataActivityImpl#getDataVariable <em>Data Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RetrieveDataActivityImpl extends DataManagementActivityImpl implements RetrieveDataActivity {
	/**
	 * The cached value of the '{@link #getDataVariable() <em>Data Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataVariable()
	 * @generated
	 * @ordered
	 */
	protected Variable dataVariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RetrieveDataActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.RETRIEVE_DATA_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variable getDataVariable() {
		if (dataVariable != null && dataVariable.eIsProxy()) {
			InternalEObject oldDataVariable = (InternalEObject)dataVariable;
			dataVariable = (Variable)eResolveProxy(oldDataVariable);
			if (dataVariable != oldDataVariable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE, oldDataVariable, dataVariable));
			}
		}
		return dataVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variable basicGetDataVariable() {
		return dataVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataVariable(Variable newDataVariable) {
		Variable oldDataVariable = dataVariable;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getRetrieveDataActivity_DataVariable().getName(),
					newDataVariable.getName());
		}
		dataVariable = newDataVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE, oldDataVariable, dataVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE:
				if (resolve) return getDataVariable();
				return basicGetDataVariable();
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
			case ModelPackage.RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE:
				setDataVariable((Variable)newValue);
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
			case ModelPackage.RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE:
				setDataVariable((Variable)null);
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
			case ModelPackage.RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE:
				return dataVariable != null;
		}
		return super.eIsSet(featureID);
	}

} //RetrieveDataActivityImpl
