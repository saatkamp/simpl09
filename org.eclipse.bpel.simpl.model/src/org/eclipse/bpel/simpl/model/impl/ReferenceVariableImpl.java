/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.PartnerLink;

import org.eclipse.bpel.model.impl.VariableImpl;
import org.eclipse.bpel.model.util.ReconciliationHelper;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.ReferenceType;
import org.eclipse.bpel.simpl.model.ReferenceVariable;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xsd.XSDTypeDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.ReferenceVariableImpl#getValueType <em>Value Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.ReferenceVariableImpl#getReferenceType <em>Reference Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.ReferenceVariableImpl#getPeriod <em>Period</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.ReferenceVariableImpl#getExternal <em>External</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceVariableImpl extends VariableImpl implements ReferenceVariable {
	/**
	 * The cached value of the '{@link #getValueType() <em>Value Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueType()
	 * @generated
	 * @ordered
	 */
	protected XSDTypeDefinition valueType;

	/**
	 * The default value of the '{@link #getReferenceType() <em>Reference Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceType()
	 * @generated
	 * @ordered
	 */
	protected static final ReferenceType REFERENCE_TYPE_EDEFAULT = ReferenceType.ON_INSTANTIATION;

	/**
	 * The cached value of the '{@link #getReferenceType() <em>Reference Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceType()
	 * @generated
	 * @ordered
	 */
	protected ReferenceType referenceType = REFERENCE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPeriod() <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriod()
	 * @generated
	 * @ordered
	 */
	protected static final int PERIOD_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPeriod() <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriod()
	 * @generated
	 * @ordered
	 */
	protected int period = PERIOD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExternal() <em>External</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternal()
	 * @generated
	 * @ordered
	 */
	protected PartnerLink external;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.REFERENCE_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XSDTypeDefinition getValueType() {
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValueType(XSDTypeDefinition newValueType, NotificationChain msgs) {
		XSDTypeDefinition oldValueType = valueType;
		valueType = newValueType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE, oldValueType, newValueType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueType(XSDTypeDefinition newValueType) {
		if (newValueType != valueType) {
			NotificationChain msgs = null;
			if (valueType != null)
				msgs = ((InternalEObject)valueType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE, null, msgs);
			if (newValueType != null)
				msgs = ((InternalEObject)newValueType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE, null, msgs);
			msgs = basicSetValueType(newValueType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE, newValueType, newValueType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceType getReferenceType() {
		return referenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceType(ReferenceType newReferenceType) {
		ReferenceType oldReferenceType = referenceType;
		referenceType = newReferenceType == null ? REFERENCE_TYPE_EDEFAULT : newReferenceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REFERENCE_VARIABLE__REFERENCE_TYPE, oldReferenceType, referenceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPeriod(int newPeriod) {
		int oldPeriod = period;
		period = newPeriod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REFERENCE_VARIABLE__PERIOD, oldPeriod, period));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartnerLink getExternal() {
		return external;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExternal(PartnerLink newExternal, NotificationChain msgs) {
		PartnerLink oldExternal = external;
		external = newExternal;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.REFERENCE_VARIABLE__EXTERNAL, oldExternal, newExternal);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternal(PartnerLink newExternal) {
		if (newExternal != external) {
			NotificationChain msgs = null;
			if (external != null)
				msgs = ((InternalEObject)external).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.REFERENCE_VARIABLE__EXTERNAL, null, msgs);
			if (newExternal != null)
				msgs = ((InternalEObject)newExternal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.REFERENCE_VARIABLE__EXTERNAL, null, msgs);
			msgs = basicSetExternal(newExternal, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REFERENCE_VARIABLE__EXTERNAL, newExternal, newExternal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE:
				return basicSetValueType(null, msgs);
			case ModelPackage.REFERENCE_VARIABLE__EXTERNAL:
				return basicSetExternal(null, msgs);
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
			case ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE:
				return getValueType();
			case ModelPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
				return getReferenceType();
			case ModelPackage.REFERENCE_VARIABLE__PERIOD:
				return getPeriod();
			case ModelPackage.REFERENCE_VARIABLE__EXTERNAL:
				return getExternal();
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
			case ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE:
				setValueType((XSDTypeDefinition)newValue);
				return;
			case ModelPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
				setReferenceType((ReferenceType)newValue);
				return;
			case ModelPackage.REFERENCE_VARIABLE__PERIOD:
				setPeriod((Integer)newValue);
				return;
			case ModelPackage.REFERENCE_VARIABLE__EXTERNAL:
				setExternal((PartnerLink)newValue);
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
			case ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE:
				setValueType((XSDTypeDefinition)null);
				return;
			case ModelPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
				setReferenceType(REFERENCE_TYPE_EDEFAULT);
				return;
			case ModelPackage.REFERENCE_VARIABLE__PERIOD:
				setPeriod(PERIOD_EDEFAULT);
				return;
			case ModelPackage.REFERENCE_VARIABLE__EXTERNAL:
				setExternal((PartnerLink)null);
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
			case ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE:
				return valueType != null;
			case ModelPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
				return referenceType != REFERENCE_TYPE_EDEFAULT;
			case ModelPackage.REFERENCE_VARIABLE__PERIOD:
				return period != PERIOD_EDEFAULT;
			case ModelPackage.REFERENCE_VARIABLE__EXTERNAL:
				return external != null;
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
		result.append(" (referenceType: ");
		result.append(referenceType);
		result.append(", period: ");
		result.append(period);
		result.append(')');
		return result.toString();
	}

} //ReferenceVariableImpl
