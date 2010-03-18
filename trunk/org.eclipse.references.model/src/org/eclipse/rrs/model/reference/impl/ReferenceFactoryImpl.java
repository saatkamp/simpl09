/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.rrs.model.reference.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.rrs.model.reference.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReferenceFactoryImpl extends EFactoryImpl implements ReferenceFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferenceFactory init() {
		try {
			ReferenceFactory theReferenceFactory = (ReferenceFactory)EPackage.Registry.INSTANCE.getEFactory("http://simpl.uni-stuttgart.de/rrs/model"); 
			if (theReferenceFactory != null) {
				return theReferenceFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ReferenceFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ReferencePackage.EPR: return createEPR();
			case ReferencePackage.PORT_TYPE: return createPortType();
			case ReferencePackage.ADDRESS: return createAddress();
			case ReferencePackage.REFERENCE_PROPERTIES: return createReferenceProperties();
			case ReferencePackage.REFERENCE_PARAMETERS: return createReferenceParameters();
			case ReferencePackage.SERVICE_NAME: return createServiceName();
			case ReferencePackage.POLICY: return createPolicy();
			case ReferencePackage.RRS_ADAPTER: return createRRSAdapter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPR createEPR() {
		EPRImpl epr = new EPRImpl();
		return epr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortType createPortType() {
		PortTypeImpl portType = new PortTypeImpl();
		return portType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Address createAddress() {
		AddressImpl address = new AddressImpl();
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceProperties createReferenceProperties() {
		ReferencePropertiesImpl referenceProperties = new ReferencePropertiesImpl();
		return referenceProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceParameters createReferenceParameters() {
		ReferenceParametersImpl referenceParameters = new ReferenceParametersImpl();
		return referenceParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceName createServiceName() {
		ServiceNameImpl serviceName = new ServiceNameImpl();
		return serviceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Policy createPolicy() {
		PolicyImpl policy = new PolicyImpl();
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRSAdapter createRRSAdapter() {
		RRSAdapterImpl rrsAdapter = new RRSAdapterImpl();
		return rrsAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencePackage getReferencePackage() {
		return (ReferencePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ReferencePackage getPackage() {
		return ReferencePackage.eINSTANCE;
	}

} //ReferenceFactoryImpl
