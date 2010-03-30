/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.simpl.rrs.model.rrs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.simpl.rrs.model.rrs.RRSFactory
 * @model kind="package"
 * @generated
 */
public interface RRSPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "rrs";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/simpl/rrs";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "rrs";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RRSPackage eINSTANCE = org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.simpl.rrs.model.rrs.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.simpl.rrs.model.rrs.impl.DocumentRootImpl
	 * @see org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Epr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EPR = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.simpl.rrs.model.rrs.impl.EPRImpl <em>EPR</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.simpl.rrs.model.rrs.impl.EPRImpl
	 * @see org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl#getEPR()
	 * @generated
	 */
	int EPR = 1;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPR__ADDRESS = 0;

	/**
	 * The feature id for the '<em><b>Reference Properties</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPR__REFERENCE_PROPERTIES = 1;

	/**
	 * The feature id for the '<em><b>Reference Parameters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPR__REFERENCE_PARAMETERS = 2;

	/**
	 * The feature id for the '<em><b>Port Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPR__PORT_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPR__SERVICE_NAME = 4;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPR__POLICY = 5;

	/**
	 * The number of structural features of the '<em>EPR</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPR_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.simpl.rrs.model.rrs.impl.ReferencePropertiesImpl <em>Reference Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.simpl.rrs.model.rrs.impl.ReferencePropertiesImpl
	 * @see org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl#getReferenceProperties()
	 * @generated
	 */
	int REFERENCE_PROPERTIES = 2;

	/**
	 * The feature id for the '<em><b>Resolution System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTIES__RESOLUTION_SYSTEM = 0;

	/**
	 * The number of structural features of the '<em>Reference Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTIES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.simpl.rrs.model.rrs.impl.ServiceNameImpl <em>Service Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.simpl.rrs.model.rrs.impl.ServiceNameImpl
	 * @see org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl#getServiceName()
	 * @generated
	 */
	int SERVICE_NAME = 3;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_NAME__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_NAME__PORT_NAME = 1;

	/**
	 * The number of structural features of the '<em>Service Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_NAME_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.eclipse.simpl.rrs.model.rrs.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.simpl.rrs.model.rrs.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.simpl.rrs.model.rrs.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.simpl.rrs.model.rrs.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.simpl.rrs.model.rrs.DocumentRoot#getEpr <em>Epr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Epr</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.DocumentRoot#getEpr()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Epr();

	/**
	 * Returns the meta object for class '{@link org.eclipse.simpl.rrs.model.rrs.EPR <em>EPR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EPR</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.EPR
	 * @generated
	 */
	EClass getEPR();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.EPR#getAddress()
	 * @see #getEPR()
	 * @generated
	 */
	EAttribute getEPR_Address();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getReferenceProperties <em>Reference Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reference Properties</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.EPR#getReferenceProperties()
	 * @see #getEPR()
	 * @generated
	 */
	EReference getEPR_ReferenceProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getReferenceParameters <em>Reference Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference Parameters</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.EPR#getReferenceParameters()
	 * @see #getEPR()
	 * @generated
	 */
	EAttribute getEPR_ReferenceParameters();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getPortType <em>Port Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port Type</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.EPR#getPortType()
	 * @see #getEPR()
	 * @generated
	 */
	EAttribute getEPR_PortType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Service Name</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.EPR#getServiceName()
	 * @see #getEPR()
	 * @generated
	 */
	EReference getEPR_ServiceName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.simpl.rrs.model.rrs.EPR#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Policy</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.EPR#getPolicy()
	 * @see #getEPR()
	 * @generated
	 */
	EAttribute getEPR_Policy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.simpl.rrs.model.rrs.ReferenceProperties <em>Reference Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Properties</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.ReferenceProperties
	 * @generated
	 */
	EClass getReferenceProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.simpl.rrs.model.rrs.ReferenceProperties#getResolutionSystem <em>Resolution System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolution System</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.ReferenceProperties#getResolutionSystem()
	 * @see #getReferenceProperties()
	 * @generated
	 */
	EAttribute getReferenceProperties_ResolutionSystem();

	/**
	 * Returns the meta object for class '{@link org.eclipse.simpl.rrs.model.rrs.ServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Name</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.ServiceName
	 * @generated
	 */
	EClass getServiceName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.simpl.rrs.model.rrs.ServiceName#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.ServiceName#getValue()
	 * @see #getServiceName()
	 * @generated
	 */
	EAttribute getServiceName_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.simpl.rrs.model.rrs.ServiceName#getPortName <em>Port Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port Name</em>'.
	 * @see org.eclipse.simpl.rrs.model.rrs.ServiceName#getPortName()
	 * @see #getServiceName()
	 * @generated
	 */
	EAttribute getServiceName_PortName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RRSFactory getRRSFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.simpl.rrs.model.rrs.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.simpl.rrs.model.rrs.impl.DocumentRootImpl
		 * @see org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Epr</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EPR = eINSTANCE.getDocumentRoot_Epr();

		/**
		 * The meta object literal for the '{@link org.eclipse.simpl.rrs.model.rrs.impl.EPRImpl <em>EPR</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.simpl.rrs.model.rrs.impl.EPRImpl
		 * @see org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl#getEPR()
		 * @generated
		 */
		EClass EPR = eINSTANCE.getEPR();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EPR__ADDRESS = eINSTANCE.getEPR_Address();

		/**
		 * The meta object literal for the '<em><b>Reference Properties</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPR__REFERENCE_PROPERTIES = eINSTANCE.getEPR_ReferenceProperties();

		/**
		 * The meta object literal for the '<em><b>Reference Parameters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EPR__REFERENCE_PARAMETERS = eINSTANCE.getEPR_ReferenceParameters();

		/**
		 * The meta object literal for the '<em><b>Port Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EPR__PORT_TYPE = eINSTANCE.getEPR_PortType();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPR__SERVICE_NAME = eINSTANCE.getEPR_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EPR__POLICY = eINSTANCE.getEPR_Policy();

		/**
		 * The meta object literal for the '{@link org.eclipse.simpl.rrs.model.rrs.impl.ReferencePropertiesImpl <em>Reference Properties</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.simpl.rrs.model.rrs.impl.ReferencePropertiesImpl
		 * @see org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl#getReferenceProperties()
		 * @generated
		 */
		EClass REFERENCE_PROPERTIES = eINSTANCE.getReferenceProperties();

		/**
		 * The meta object literal for the '<em><b>Resolution System</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_PROPERTIES__RESOLUTION_SYSTEM = eINSTANCE.getReferenceProperties_ResolutionSystem();

		/**
		 * The meta object literal for the '{@link org.eclipse.simpl.rrs.model.rrs.impl.ServiceNameImpl <em>Service Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.simpl.rrs.model.rrs.impl.ServiceNameImpl
		 * @see org.eclipse.simpl.rrs.model.rrs.impl.RRSPackageImpl#getServiceName()
		 * @generated
		 */
		EClass SERVICE_NAME = eINSTANCE.getServiceName();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_NAME__VALUE = eINSTANCE.getServiceName_Value();

		/**
		 * The meta object literal for the '<em><b>Port Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_NAME__PORT_NAME = eINSTANCE.getServiceName_PortName();

	}

} //RRSPackage
