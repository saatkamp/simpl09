/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.simpl.rrs.model.rrs.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.simpl.rrs.model.rrs.DocumentRoot;
import org.eclipse.simpl.rrs.model.rrs.RRSFactory;
import org.eclipse.simpl.rrs.model.rrs.RRSPackage;
import org.eclipse.simpl.rrs.model.rrs.ReferenceParameters;
import org.eclipse.simpl.rrs.model.rrs.ReferenceProperties;
import org.eclipse.simpl.rrs.model.rrs.ServiceName;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RRSPackageImpl extends EPackageImpl implements RRSPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

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
	private EClass referenceParametersEClass = null;

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
	private EClass serviceNameEClass = null;

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
	 * @see org.eclipse.simpl.rrs.model.rrs.RRSPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RRSPackageImpl() {
		super(eNS_URI, RRSFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RRSPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RRSPackage init() {
		if (isInited) return (RRSPackage)EPackage.Registry.INSTANCE.getEPackage(RRSPackage.eNS_URI);

		// Obtain or create and register package
		RRSPackageImpl theRRSPackage = (RRSPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RRSPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RRSPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theRRSPackage.createPackageContents();

		// Initialize created meta-data
		theRRSPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRRSPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RRSPackage.eNS_URI, theRRSPackage);
		return theRRSPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Epr() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getEPR_Address() {
		return (EAttribute)eprEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getEPR_PortType() {
		return (EAttribute)eprEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getEPR_Policy() {
		return (EAttribute)eprEClass.getEStructuralFeatures().get(5);
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
	public EClass getReferenceProperties() {
		return referencePropertiesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceProperties_ResolutionSystem() {
		return (EAttribute)referencePropertiesEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getServiceName_Value() {
		return (EAttribute)serviceNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceName_PortName() {
		return (EAttribute)serviceNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRSFactory getRRSFactory() {
		return (RRSFactory)getEFactoryInstance();
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
		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__EPR);

		eprEClass = createEClass(EPR);
		createEAttribute(eprEClass, EPR__ADDRESS);
		createEReference(eprEClass, EPR__REFERENCE_PROPERTIES);
		createEReference(eprEClass, EPR__REFERENCE_PARAMETERS);
		createEAttribute(eprEClass, EPR__PORT_TYPE);
		createEReference(eprEClass, EPR__SERVICE_NAME);
		createEAttribute(eprEClass, EPR__POLICY);

		referenceParametersEClass = createEClass(REFERENCE_PARAMETERS);
		createEAttribute(referenceParametersEClass, REFERENCE_PARAMETERS__REFERENCE_NAME);
		createEAttribute(referenceParametersEClass, REFERENCE_PARAMETERS__STATEMENT);

		referencePropertiesEClass = createEClass(REFERENCE_PROPERTIES);
		createEAttribute(referencePropertiesEClass, REFERENCE_PROPERTIES__RESOLUTION_SYSTEM);

		serviceNameEClass = createEClass(SERVICE_NAME);
		createEAttribute(serviceNameEClass, SERVICE_NAME__VALUE);
		createEAttribute(serviceNameEClass, SERVICE_NAME__PORT_NAME);
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

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Epr(), theXMLTypePackage.getAnySimpleType(), "epr", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(eprEClass, org.eclipse.simpl.rrs.model.rrs.EPR.class, "EPR", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEPR_Address(), theXMLTypePackage.getAnyURI(), "address", null, 1, 1, org.eclipse.simpl.rrs.model.rrs.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEPR_ReferenceProperties(), this.getReferenceProperties(), null, "referenceProperties", null, 1, 1, org.eclipse.simpl.rrs.model.rrs.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEPR_ReferenceParameters(), this.getReferenceParameters(), null, "referenceParameters", null, 1, 1, org.eclipse.simpl.rrs.model.rrs.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEPR_PortType(), theXMLTypePackage.getQName(), "portType", null, 0, 1, org.eclipse.simpl.rrs.model.rrs.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEPR_ServiceName(), this.getServiceName(), null, "serviceName", null, 0, 1, org.eclipse.simpl.rrs.model.rrs.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEPR_Policy(), theXMLTypePackage.getString(), "policy", null, 0, 1, org.eclipse.simpl.rrs.model.rrs.EPR.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceParametersEClass, ReferenceParameters.class, "ReferenceParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReferenceParameters_ReferenceName(), theXMLTypePackage.getString(), "referenceName", null, 1, 1, ReferenceParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceParameters_Statement(), theXMLTypePackage.getString(), "statement", null, 1, 1, ReferenceParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referencePropertiesEClass, ReferenceProperties.class, "ReferenceProperties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReferenceProperties_ResolutionSystem(), theXMLTypePackage.getString(), "resolutionSystem", null, 0, 1, ReferenceProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceNameEClass, ServiceName.class, "ServiceName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getServiceName_Value(), theXMLTypePackage.getQName(), "value", null, 0, 1, ServiceName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceName_PortName(), theXMLTypePackage.getNCName(), "portName", null, 0, 1, ServiceName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_Epr(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "epr",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (eprEClass, 
		   source, 
		   new String[] {
			 "name", "EPR",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEPR_Address(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "address",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getEPR_ReferenceProperties(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "referenceProperties",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getEPR_ReferenceParameters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "referenceParameters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getEPR_PortType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "portType",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getEPR_ServiceName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "serviceName",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getEPR_Policy(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "policy",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (referenceParametersEClass, 
		   source, 
		   new String[] {
			 "name", "ReferenceParameters",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getReferenceParameters_ReferenceName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "referenceName",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getReferenceParameters_Statement(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "statement",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (referencePropertiesEClass, 
		   source, 
		   new String[] {
			 "name", "ReferenceProperties",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getReferenceProperties_ResolutionSystem(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resolutionSystem",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (serviceNameEClass, 
		   source, 
		   new String[] {
			 "name", "ServiceName",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getServiceName_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getServiceName_PortName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "portName"
		   });
	}

} //RRSPackageImpl
