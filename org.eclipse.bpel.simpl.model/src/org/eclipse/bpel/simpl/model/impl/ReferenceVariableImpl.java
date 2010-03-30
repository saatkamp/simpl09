/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import javax.xml.namespace.QName;

import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.impl.VariableImpl;
import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.ReferenceType;
import org.eclipse.bpel.simpl.model.ReferenceVariable;
import org.eclipse.emf.common.notify.Notification;
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
@SuppressWarnings("restriction")
public class ReferenceVariableImpl extends VariableImpl implements ReferenceVariable {
	/**
	 * The cached value of the '{@link #getValueType() <em>Value Type</em>}' reference.
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
	 * The cached value of the '{@link #getExternal() <em>External</em>}' reference.
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
		if (valueType != null && valueType.eIsProxy()) {
			InternalEObject oldValueType = (InternalEObject)valueType;
			valueType = (XSDTypeDefinition)eResolveProxy(oldValueType);
			if (valueType != oldValueType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE, oldValueType, valueType));
			}
		}
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XSDTypeDefinition basicGetValueType() {
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @customized
	 */
	public void setValueType(XSDTypeDefinition newValueType) {
		XSDTypeDefinition oldValueType = valueType;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE.getReferenceVariable_ReferenceType().getName(),
					newValueType == null ? null : new QName(newValueType
							.getTargetNamespace(), newValueType.getName()));
		}
		valueType = newValueType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REFERENCE_VARIABLE__VALUE_TYPE, oldValueType, valueType));
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
		if (external != null && external.eIsProxy()) {
			InternalEObject oldExternal = (InternalEObject)external;
			external = (PartnerLink)eResolveProxy(oldExternal);
			if (external != oldExternal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.REFERENCE_VARIABLE__EXTERNAL, oldExternal, external));
			}
		}
		return external;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartnerLink basicGetExternal() {
		return external;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternal(PartnerLink newExternal) {
		PartnerLink oldExternal = external;
		external = newExternal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REFERENCE_VARIABLE__EXTERNAL, oldExternal, external));
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
				if (resolve) return getValueType();
				return basicGetValueType();
			case ModelPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
				return getReferenceType();
			case ModelPackage.REFERENCE_VARIABLE__PERIOD:
				return getPeriod();
			case ModelPackage.REFERENCE_VARIABLE__EXTERNAL:
				if (resolve) return getExternal();
				return basicGetExternal();
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
