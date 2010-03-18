/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.rrs.model.reference.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.rrs.model.reference.Address;
import org.eclipse.rrs.model.reference.Policy;
import org.eclipse.rrs.model.reference.PortType;
import org.eclipse.rrs.model.reference.RRSAdapter;
import org.eclipse.rrs.model.reference.ReferenceFactory;
import org.eclipse.rrs.model.reference.ReferencePackage;
import org.eclipse.rrs.model.reference.ReferenceParameters;
import org.eclipse.rrs.model.reference.ReferenceProperties;
import org.eclipse.rrs.model.reference.ServiceName;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReferencePackageImpl extends EPackageImpl implements ReferencePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eprEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addressEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referencePropertiesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceParametersEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass policyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rrsAdapterEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.rrs.model.reference.ReferencePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ReferencePackageImpl() {
		super(eNS_URI, ReferenceFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ReferencePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ReferencePackage init() {
		if (isInited) return (ReferencePackage)EPackage.Registry.INSTANCE.getEPackage(ReferencePackage.eNS_URI);

		// Obtain or create and register package
		ReferencePackageImpl theReferencePackage = (ReferencePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ReferencePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ReferencePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theReferencePackage.createPackageContents();

		// Initialize created meta-data
		theReferencePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theReferencePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ReferencePackage.eNS_URI, theReferencePackage);
		return theReferencePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEPR() {
		return eprEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEPR_Address() {
		return (EReference)eprEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEPR_ReferenceProperties() {
		return (EReference)eprEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEPR_ReferenceParameters() {
		return (EReference)eprEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEPR_PortType() {
		return (EReference)eprEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEPR_ServiceName() {
		return (EReference)eprEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEPR_Policy() {
		return (EReference)eprEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortType() {
		return portTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortType_Value() {
		return (EAttribute)portTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddress() {
		return addressEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddress_Uri() {
		return (EAttribute)addressEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceProperties() {
		return referencePropertiesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceProperties_ResolutionSystem() {
		return (EReference)referencePropertiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceParameters() {
		return referenceParametersEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceParameters_ReferenceName() {
		return (EAttribute)referenceParametersEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceParameters_Statement() {
		return (EAttribute)referenceParametersEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceName() {
		return serviceNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceName_PortName() {
		return (EAttribute)serviceNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolicy() {
		return policyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRRSAdapter() {
		return rrsAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRRSAdapter_AdapterURI() {
		return (EAttribute)rrsAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceFactory getReferenceFactory() {
		return (ReferenceFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		eprEClass = createEClass(EPR);
		createEReference(eprEClass, EPR__ADDRESS);
		createEReference(eprEClass, EPR__REFERENCE_PROPERTIES);
		createEReference(eprEClass, EPR__REFERENCE_PARAMETERS);
		createEReference(eprEClass, EPR__PORT_TYPE);
		createEReference(eprEClass, EPR__SERVICE_NAME);
		createEReference(eprEClass, EPR__POLICY);

		portTypeEClass = createEClass(PORT_TYPE);
		createEAttribute(portTypeEClass, PORT_TYPE__VALUE);

		addressEClass = createEClass(ADDRESS);
		createEAttribute(addressEClass, ADDRESS__URI);

		referencePropertiesEClass = createEClass(REFERENCE_PROPERTIES);
		createEReference(referencePropertiesEClass, REFERENCE_PROPERTIES__RESOLUTION_SYSTEM);

		referenceParametersEClass = createEClass(REFERENCE_PARAMETERS);
		createEAttribute(referenceParametersEClass, REFERENCE_PARAMETERS__REFERENCE_NAME);
		createEAttribute(referenceParametersEClass, REFERENCE_PARAMETERS__STATEMENT);

		serviceNameEClass = createEClass(SERVICE_NAME);
		createEAttribute(serviceNameEClass, SERVICE_NAME__PORT_NAME);

		policyEClass = createEClass(POLICY);

		rrsAdapterEClass = createEClass(RRS_ADAPTER);
		createEAttribute(rrsAdapterEClass, RRS_ADAPTER__ADAPTER_URI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(eprEClass, org.eclipse.rrs.model.reference.EPR.class, "EPR", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEPR_Address(), this.getAddress(), null, "address", null, 1, 1, org.eclipse.rrs.model.reference.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEPR_ReferenceProperties(), this.getReferenceProperties(), null, "referenceProperties", null, 1, 1, org.eclipse.rrs.model.reference.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEPR_ReferenceParameters(), this.getReferenceParameters(), null, "referenceParameters", null, 1, 1, org.eclipse.rrs.model.reference.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEPR_PortType(), this.getPortType(), null, "portType", null, 0, 1, org.eclipse.rrs.model.reference.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEPR_ServiceName(), this.getServiceName(), null, "serviceName", null, 0, 1, org.eclipse.rrs.model.reference.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEPR_Policy(), this.getPolicy(), null, "policy", null, 0, 1, org.eclipse.rrs.model.reference.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portTypeEClass, PortType.class, "PortType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortType_Value(), ecorePackage.getEString(), "value", null, 1, 1, PortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addressEClass, Address.class, "Address", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAddress_Uri(), ecorePackage.getEString(), "uri", null, 1, 1, Address.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referencePropertiesEClass, ReferenceProperties.class, "ReferenceProperties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceProperties_ResolutionSystem(), this.getRRSAdapter(), null, "resolutionSystem", null, 1, 1, ReferenceProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceParametersEClass, ReferenceParameters.class, "ReferenceParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReferenceParameters_ReferenceName(), ecorePackage.getEString(), "referenceName", null, 1, 1, ReferenceParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceParameters_Statement(), ecorePackage.getEString(), "statement", null, 1, 1, ReferenceParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceNameEClass, ServiceName.class, "ServiceName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getServiceName_PortName(), ecorePackage.getEString(), "PortName", null, 0, 1, ServiceName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(policyEClass, Policy.class, "Policy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rrsAdapterEClass, RRSAdapter.class, "RRSAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRRSAdapter_AdapterURI(), ecorePackage.getEString(), "adapterURI", null, 1, 1, RRSAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (eprEClass, 
		   source, 
		   new String[] {
		   });
	}

} //ReferencePackageImpl
