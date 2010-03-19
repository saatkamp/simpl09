/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.simpl.rrs.model.reference.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.simpl.rrs.model.reference.Address;
import org.eclipse.simpl.rrs.model.reference.EPR;
import org.eclipse.simpl.rrs.model.reference.Policy;
import org.eclipse.simpl.rrs.model.reference.PortType;
import org.eclipse.simpl.rrs.model.reference.ReferencePackage;
import org.eclipse.simpl.rrs.model.reference.ReferenceParameters;
import org.eclipse.simpl.rrs.model.reference.ReferenceProperties;
import org.eclipse.simpl.rrs.model.reference.ServiceName;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EPR</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.impl.EPRImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.impl.EPRImpl#getReferenceProperties <em>Reference Properties</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.impl.EPRImpl#getReferenceParameters <em>Reference Parameters</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.impl.EPRImpl#getPortType <em>Port Type</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.impl.EPRImpl#getServiceName <em>Service Name</em>}</li>
 *   <li>{@link org.eclipse.simpl.rrs.model.reference.impl.EPRImpl#getPolicy <em>Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EPRImpl extends EObjectImpl implements EPR {
	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected Address address;

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
	 * The cached value of the '{@link #getReferenceParameters() <em>Reference Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceParameters()
	 * @generated
	 * @ordered
	 */
	protected ReferenceParameters referenceParameters;

	/**
	 * The cached value of the '{@link #getPortType() <em>Port Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortType()
	 * @generated
	 * @ordered
	 */
	protected PortType portType;

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
	 * The cached value of the '{@link #getPolicy() <em>Policy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicy()
	 * @generated
	 * @ordered
	 */
	protected Policy policy;

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
		return ReferencePackage.Literals.EPR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAddress(Address newAddress, NotificationChain msgs) {
		Address oldAddress = address;
		address = newAddress;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__ADDRESS, oldAddress, newAddress);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddress(Address newAddress) {
		if (newAddress != address) {
			NotificationChain msgs = null;
			if (address != null)
				msgs = ((InternalEObject)address).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__ADDRESS, null, msgs);
			if (newAddress != null)
				msgs = ((InternalEObject)newAddress).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__ADDRESS, null, msgs);
			msgs = basicSetAddress(newAddress, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__ADDRESS, newAddress, newAddress));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__REFERENCE_PROPERTIES, oldReferenceProperties, newReferenceProperties);
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
				msgs = ((InternalEObject)referenceProperties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__REFERENCE_PROPERTIES, null, msgs);
			if (newReferenceProperties != null)
				msgs = ((InternalEObject)newReferenceProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__REFERENCE_PROPERTIES, null, msgs);
			msgs = basicSetReferenceProperties(newReferenceProperties, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__REFERENCE_PROPERTIES, newReferenceProperties, newReferenceProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceParameters getReferenceParameters() {
		return referenceParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferenceParameters(ReferenceParameters newReferenceParameters, NotificationChain msgs) {
		ReferenceParameters oldReferenceParameters = referenceParameters;
		referenceParameters = newReferenceParameters;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__REFERENCE_PARAMETERS, oldReferenceParameters, newReferenceParameters);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceParameters(ReferenceParameters newReferenceParameters) {
		if (newReferenceParameters != referenceParameters) {
			NotificationChain msgs = null;
			if (referenceParameters != null)
				msgs = ((InternalEObject)referenceParameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__REFERENCE_PARAMETERS, null, msgs);
			if (newReferenceParameters != null)
				msgs = ((InternalEObject)newReferenceParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__REFERENCE_PARAMETERS, null, msgs);
			msgs = basicSetReferenceParameters(newReferenceParameters, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__REFERENCE_PARAMETERS, newReferenceParameters, newReferenceParameters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortType getPortType() {
		return portType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPortType(PortType newPortType, NotificationChain msgs) {
		PortType oldPortType = portType;
		portType = newPortType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__PORT_TYPE, oldPortType, newPortType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortType(PortType newPortType) {
		if (newPortType != portType) {
			NotificationChain msgs = null;
			if (portType != null)
				msgs = ((InternalEObject)portType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__PORT_TYPE, null, msgs);
			if (newPortType != null)
				msgs = ((InternalEObject)newPortType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__PORT_TYPE, null, msgs);
			msgs = basicSetPortType(newPortType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__PORT_TYPE, newPortType, newPortType));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__SERVICE_NAME, oldServiceName, newServiceName);
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
				msgs = ((InternalEObject)serviceName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__SERVICE_NAME, null, msgs);
			if (newServiceName != null)
				msgs = ((InternalEObject)newServiceName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__SERVICE_NAME, null, msgs);
			msgs = basicSetServiceName(newServiceName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__SERVICE_NAME, newServiceName, newServiceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Policy getPolicy() {
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPolicy(Policy newPolicy, NotificationChain msgs) {
		Policy oldPolicy = policy;
		policy = newPolicy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__POLICY, oldPolicy, newPolicy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPolicy(Policy newPolicy) {
		if (newPolicy != policy) {
			NotificationChain msgs = null;
			if (policy != null)
				msgs = ((InternalEObject)policy).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__POLICY, null, msgs);
			if (newPolicy != null)
				msgs = ((InternalEObject)newPolicy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReferencePackage.EPR__POLICY, null, msgs);
			msgs = basicSetPolicy(newPolicy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReferencePackage.EPR__POLICY, newPolicy, newPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReferencePackage.EPR__ADDRESS:
				return basicSetAddress(null, msgs);
			case ReferencePackage.EPR__REFERENCE_PROPERTIES:
				return basicSetReferenceProperties(null, msgs);
			case ReferencePackage.EPR__REFERENCE_PARAMETERS:
				return basicSetReferenceParameters(null, msgs);
			case ReferencePackage.EPR__PORT_TYPE:
				return basicSetPortType(null, msgs);
			case ReferencePackage.EPR__SERVICE_NAME:
				return basicSetServiceName(null, msgs);
			case ReferencePackage.EPR__POLICY:
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
			case ReferencePackage.EPR__ADDRESS:
				return getAddress();
			case ReferencePackage.EPR__REFERENCE_PROPERTIES:
				return getReferenceProperties();
			case ReferencePackage.EPR__REFERENCE_PARAMETERS:
				return getReferenceParameters();
			case ReferencePackage.EPR__PORT_TYPE:
				return getPortType();
			case ReferencePackage.EPR__SERVICE_NAME:
				return getServiceName();
			case ReferencePackage.EPR__POLICY:
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
			case ReferencePackage.EPR__ADDRESS:
				setAddress((Address)newValue);
				return;
			case ReferencePackage.EPR__REFERENCE_PROPERTIES:
				setReferenceProperties((ReferenceProperties)newValue);
				return;
			case ReferencePackage.EPR__REFERENCE_PARAMETERS:
				setReferenceParameters((ReferenceParameters)newValue);
				return;
			case ReferencePackage.EPR__PORT_TYPE:
				setPortType((PortType)newValue);
				return;
			case ReferencePackage.EPR__SERVICE_NAME:
				setServiceName((ServiceName)newValue);
				return;
			case ReferencePackage.EPR__POLICY:
				setPolicy((Policy)newValue);
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
			case ReferencePackage.EPR__ADDRESS:
				setAddress((Address)null);
				return;
			case ReferencePackage.EPR__REFERENCE_PROPERTIES:
				setReferenceProperties((ReferenceProperties)null);
				return;
			case ReferencePackage.EPR__REFERENCE_PARAMETERS:
				setReferenceParameters((ReferenceParameters)null);
				return;
			case ReferencePackage.EPR__PORT_TYPE:
				setPortType((PortType)null);
				return;
			case ReferencePackage.EPR__SERVICE_NAME:
				setServiceName((ServiceName)null);
				return;
			case ReferencePackage.EPR__POLICY:
				setPolicy((Policy)null);
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
			case ReferencePackage.EPR__ADDRESS:
				return address != null;
			case ReferencePackage.EPR__REFERENCE_PROPERTIES:
				return referenceProperties != null;
			case ReferencePackage.EPR__REFERENCE_PARAMETERS:
				return referenceParameters != null;
			case ReferencePackage.EPR__PORT_TYPE:
				return portType != null;
			case ReferencePackage.EPR__SERVICE_NAME:
				return serviceName != null;
			case ReferencePackage.EPR__POLICY:
				return policy != null;
		}
		return super.eIsSet(featureID);
	}

} //EPRImpl
