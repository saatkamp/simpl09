/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model.impl;

import javax.xml.namespace.QName;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.ReferenceType;
import org.eclipse.bpel.model.ReferenceVariable;
import org.eclipse.bpel.model.util.BPELConstants;
import org.eclipse.bpel.model.util.ReconciliationHelper;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.wst.wsdl.WSDLElement;
import org.eclipse.xsd.XSDTypeDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.impl.ReferenceVariableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ReferenceVariableImpl#getValueType <em>Value Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ReferenceVariableImpl#getReferenceType <em>Reference Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ReferenceVariableImpl#getPeriod <em>Period</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ReferenceVariableImpl#getPartnerLink <em>Partner Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceVariableImpl extends ExtensibleElementImpl implements
		ReferenceVariable {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getPartnerLink() <em>Partner Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartnerLink()
	 * @generated
	 * @ordered
	 */
	protected PartnerLink partnerLink;

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
		return BPELPackage.Literals.REFERENCE_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @customized
	 */
	public void setName(String newName) {
		String oldName = name;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, BPELConstants.AT_NAME,
					newName);
			ReconciliationHelper.updateVariableName((WSDLElement) eContainer(),
					newName);
		}
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BPELPackage.REFERENCE_VARIABLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XSDTypeDefinition getValueType() {
		if (valueType != null && valueType.eIsProxy()) {
			InternalEObject oldValueType = (InternalEObject) valueType;
			valueType = (XSDTypeDefinition) eResolveProxy(oldValueType);
			if (valueType != oldValueType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							BPELPackage.REFERENCE_VARIABLE__VALUE_TYPE,
							oldValueType, valueType));
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
			ReconciliationHelper.replaceAttribute(this, "valueType",
					newValueType == null ? null : new QName(newValueType
							.getTargetNamespace(), newValueType.getName()));
		}
		valueType = newValueType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BPELPackage.REFERENCE_VARIABLE__VALUE_TYPE, oldValueType,
					valueType));
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
	 * @customized
	 */
	public void setReferenceType(ReferenceType newReferenceType) {
		ReferenceType oldReferenceType = referenceType;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
					BPELConstants.AT_REFERENCE_TYPE,
					newReferenceType == null ? REFERENCE_TYPE_EDEFAULT.getName()
							: newReferenceType.getName());
		}
		referenceType = newReferenceType == null ? REFERENCE_TYPE_EDEFAULT
				: newReferenceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BPELPackage.REFERENCE_VARIABLE__REFERENCE_TYPE,
					oldReferenceType, referenceType));
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
	 * @customized
	 */
	public void setPeriod(int newPeriod) {
		int oldPeriod = period;
		period = newPeriod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BPELPackage.REFERENCE_VARIABLE__PERIOD, oldPeriod, period));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartnerLink getPartnerLink() {
		if (partnerLink != null && partnerLink.eIsProxy()) {
			InternalEObject oldPartnerLink = (InternalEObject) partnerLink;
			partnerLink = (PartnerLink) eResolveProxy(oldPartnerLink);
			if (partnerLink != oldPartnerLink) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							BPELPackage.REFERENCE_VARIABLE__PARTNER_LINK,
							oldPartnerLink, partnerLink));
			}
		}
		return partnerLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartnerLink basicGetPartnerLink() {
		return partnerLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @customized
	 */
	public void setPartnerLink(PartnerLink newPartnerLink) {
		PartnerLink oldPartnerLink = partnerLink;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this,
					BPELConstants.AT_PARTNER_LINK,
					newPartnerLink == null ? null : newPartnerLink.getName());
		}
		partnerLink = newPartnerLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BPELPackage.REFERENCE_VARIABLE__PARTNER_LINK,
					oldPartnerLink, partnerLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BPELPackage.REFERENCE_VARIABLE__NAME:
			return getName();
		case BPELPackage.REFERENCE_VARIABLE__VALUE_TYPE:
			if (resolve)
				return getValueType();
			return basicGetValueType();
		case BPELPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
			return getReferenceType();
		case BPELPackage.REFERENCE_VARIABLE__PERIOD:
			return getPeriod();
		case BPELPackage.REFERENCE_VARIABLE__PARTNER_LINK:
			if (resolve)
				return getPartnerLink();
			return basicGetPartnerLink();
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
		case BPELPackage.REFERENCE_VARIABLE__NAME:
			setName((String) newValue);
			return;
		case BPELPackage.REFERENCE_VARIABLE__VALUE_TYPE:
			setValueType((XSDTypeDefinition) newValue);
			return;
		case BPELPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
			setReferenceType((ReferenceType) newValue);
			return;
		case BPELPackage.REFERENCE_VARIABLE__PERIOD:
			setPeriod((Integer) newValue);
			return;
		case BPELPackage.REFERENCE_VARIABLE__PARTNER_LINK:
			setPartnerLink((PartnerLink) newValue);
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
		case BPELPackage.REFERENCE_VARIABLE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BPELPackage.REFERENCE_VARIABLE__VALUE_TYPE:
			setValueType((XSDTypeDefinition) null);
			return;
		case BPELPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
			setReferenceType(REFERENCE_TYPE_EDEFAULT);
			return;
		case BPELPackage.REFERENCE_VARIABLE__PERIOD:
			setPeriod(PERIOD_EDEFAULT);
			return;
		case BPELPackage.REFERENCE_VARIABLE__PARTNER_LINK:
			setPartnerLink((PartnerLink) null);
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
		case BPELPackage.REFERENCE_VARIABLE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case BPELPackage.REFERENCE_VARIABLE__VALUE_TYPE:
			return valueType != null;
		case BPELPackage.REFERENCE_VARIABLE__REFERENCE_TYPE:
			return referenceType != REFERENCE_TYPE_EDEFAULT;
		case BPELPackage.REFERENCE_VARIABLE__PERIOD:
			return period != PERIOD_EDEFAULT;
		case BPELPackage.REFERENCE_VARIABLE__PARTNER_LINK:
			return partnerLink != null;
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", referenceType: "); //$NON-NLS-1$
		result.append(referenceType);
		result.append(", period: "); //$NON-NLS-1$
		result.append(period);
		result.append(')');
		return result.toString();
	}

} //ReferenceVariableImpl
