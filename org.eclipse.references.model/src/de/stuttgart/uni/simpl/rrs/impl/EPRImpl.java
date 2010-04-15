/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.stuttgart.uni.simpl.rrs.impl;

import de.stuttgart.uni.simpl.rrs.EPR;
import de.stuttgart.uni.simpl.rrs.RRSPackage;
import de.stuttgart.uni.simpl.rrs.ReferenceProperties;
import de.stuttgart.uni.simpl.rrs.ServiceName;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EPR</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.stuttgart.uni.simpl.rrs.impl.EPRImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link de.stuttgart.uni.simpl.rrs.impl.EPRImpl#getReferenceProperties <em>Reference Properties</em>}</li>
 *   <li>{@link de.stuttgart.uni.simpl.rrs.impl.EPRImpl#getReferenceParameters <em>Reference Parameters</em>}</li>
 *   <li>{@link de.stuttgart.uni.simpl.rrs.impl.EPRImpl#getPortType <em>Port Type</em>}</li>
 *   <li>{@link de.stuttgart.uni.simpl.rrs.impl.EPRImpl#getServiceName <em>Service Name</em>}</li>
 *   <li>{@link de.stuttgart.uni.simpl.rrs.impl.EPRImpl#getPolicy <em>Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EPRImpl extends EObjectImpl implements EPR {
	/**
	 * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected String address = ADDRESS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferenceProperties() <em>Reference Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceProperties()
	 * @generated
	 * @ordered
	 */
	protected ReferenceProperties referenceProperties;

	/**
	 * The default value of the '{@link #getReferenceParameters() <em>Reference Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceParameters()
	 * @generated
	 * @ordered
	 */
	protected static final String REFERENCE_PARAMETERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferenceParameters() <em>Reference Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceParameters()
	 * @generated
	 * @ordered
	 */
	protected String referenceParameters = REFERENCE_PARAMETERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPortType() <em>Port Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortType()
	 * @generated
	 * @ordered
	 */
	protected static final QName PORT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortType() <em>Port Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortType()
	 * @generated
	 * @ordered
	 */
	protected QName portType = PORT_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getServiceName() <em>Service Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceName()
	 * @generated
	 * @ordered
	 */
	protected ServiceName serviceName;

	/**
	 * The default value of the '{@link #getPolicy() <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String POLICY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPolicy() <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicy()
	 * @generated
	 * @ordered
	 */
	protected String policy = POLICY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EPRImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RRSPackage.Literals.EPR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddress(String newAddress) {
		String oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RRSPackage.EPR__ADDRESS, oldAddress, address));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceProperties getReferenceProperties() {
		return referenceProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferenceProperties(ReferenceProperties newReferenceProperties, NotificationChain msgs) {
		ReferenceProperties oldReferenceProperties = referenceProperties;
		referenceProperties = newReferenceProperties;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RRSPackage.EPR__REFERENCE_PROPERTIES, oldReferenceProperties, newReferenceProperties);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceProperties(ReferenceProperties newReferenceProperties) {
		if (newReferenceProperties != referenceProperties) {
			NotificationChain msgs = null;
			if (referenceProperties != null)
				msgs = ((InternalEObject)referenceProperties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RRSPackage.EPR__REFERENCE_PROPERTIES, null, msgs);
			if (newReferenceProperties != null)
				msgs = ((InternalEObject)newReferenceProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RRSPackage.EPR__REFERENCE_PROPERTIES, null, msgs);
			msgs = basicSetReferenceProperties(newReferenceProperties, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RRSPackage.EPR__REFERENCE_PROPERTIES, newReferenceProperties, newReferenceProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReferenceParameters() {
		return referenceParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceParameters(String newReferenceParameters) {
		String oldReferenceParameters = referenceParameters;
		referenceParameters = newReferenceParameters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RRSPackage.EPR__REFERENCE_PARAMETERS, oldReferenceParameters, referenceParameters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getPortType() {
		return portType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortType(QName newPortType) {
		QName oldPortType = portType;
		portType = newPortType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RRSPackage.EPR__PORT_TYPE, oldPortType, portType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceName getServiceName() {
		return serviceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServiceName(ServiceName newServiceName, NotificationChain msgs) {
		ServiceName oldServiceName = serviceName;
		serviceName = newServiceName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RRSPackage.EPR__SERVICE_NAME, oldServiceName, newServiceName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceName(ServiceName newServiceName) {
		if (newServiceName != serviceName) {
			NotificationChain msgs = null;
			if (serviceName != null)
				msgs = ((InternalEObject)serviceName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RRSPackage.EPR__SERVICE_NAME, null, msgs);
			if (newServiceName != null)
				msgs = ((InternalEObject)newServiceName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RRSPackage.EPR__SERVICE_NAME, null, msgs);
			msgs = basicSetServiceName(newServiceName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RRSPackage.EPR__SERVICE_NAME, newServiceName, newServiceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPolicy(String newPolicy) {
		String oldPolicy = policy;
		policy = newPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RRSPackage.EPR__POLICY, oldPolicy, policy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RRSPackage.EPR__REFERENCE_PROPERTIES:
				return basicSetReferenceProperties(null, msgs);
			case RRSPackage.EPR__SERVICE_NAME:
				return basicSetServiceName(null, msgs);
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
			case RRSPackage.EPR__ADDRESS:
				return getAddress();
			case RRSPackage.EPR__REFERENCE_PROPERTIES:
				return getReferenceProperties();
			case RRSPackage.EPR__REFERENCE_PARAMETERS:
				return getReferenceParameters();
			case RRSPackage.EPR__PORT_TYPE:
				return getPortType();
			case RRSPackage.EPR__SERVICE_NAME:
				return getServiceName();
			case RRSPackage.EPR__POLICY:
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
			case RRSPackage.EPR__ADDRESS:
				setAddress((String)newValue);
				return;
			case RRSPackage.EPR__REFERENCE_PROPERTIES:
				setReferenceProperties((ReferenceProperties)newValue);
				return;
			case RRSPackage.EPR__REFERENCE_PARAMETERS:
				setReferenceParameters((String)newValue);
				return;
			case RRSPackage.EPR__PORT_TYPE:
				setPortType((QName)newValue);
				return;
			case RRSPackage.EPR__SERVICE_NAME:
				setServiceName((ServiceName)newValue);
				return;
			case RRSPackage.EPR__POLICY:
				setPolicy((String)newValue);
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
			case RRSPackage.EPR__ADDRESS:
				setAddress(ADDRESS_EDEFAULT);
				return;
			case RRSPackage.EPR__REFERENCE_PROPERTIES:
				setReferenceProperties((ReferenceProperties)null);
				return;
			case RRSPackage.EPR__REFERENCE_PARAMETERS:
				setReferenceParameters(REFERENCE_PARAMETERS_EDEFAULT);
				return;
			case RRSPackage.EPR__PORT_TYPE:
				setPortType(PORT_TYPE_EDEFAULT);
				return;
			case RRSPackage.EPR__SERVICE_NAME:
				setServiceName((ServiceName)null);
				return;
			case RRSPackage.EPR__POLICY:
				setPolicy(POLICY_EDEFAULT);
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
			case RRSPackage.EPR__ADDRESS:
				return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
			case RRSPackage.EPR__REFERENCE_PROPERTIES:
				return referenceProperties != null;
			case RRSPackage.EPR__REFERENCE_PARAMETERS:
				return REFERENCE_PARAMETERS_EDEFAULT == null ? referenceParameters != null : !REFERENCE_PARAMETERS_EDEFAULT.equals(referenceParameters);
			case RRSPackage.EPR__PORT_TYPE:
				return PORT_TYPE_EDEFAULT == null ? portType != null : !PORT_TYPE_EDEFAULT.equals(portType);
			case RRSPackage.EPR__SERVICE_NAME:
				return serviceName != null;
			case RRSPackage.EPR__POLICY:
				return POLICY_EDEFAULT == null ? policy != null : !POLICY_EDEFAULT.equals(policy);
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
		result.append(" (address: ");
		result.append(address);
		result.append(", referenceParameters: ");
		result.append(referenceParameters);
		result.append(", portType: ");
		result.append(portType);
		result.append(", policy: ");
		result.append(policy);
		result.append(')');
		return result.toString();
	}

} //EPRImpl
