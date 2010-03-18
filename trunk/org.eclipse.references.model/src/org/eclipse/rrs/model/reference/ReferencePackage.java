/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.rrs.model.reference;

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
 * @see org.eclipse.rrs.model.reference.ReferenceFactory
 * @model kind="package"
 * @generated
 */
public interface ReferencePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "reference";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://simpl.uni-stuttgart.de/rrs/model";

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
	ReferencePackage eINSTANCE = org.eclipse.rrs.model.reference.impl.ReferencePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.rrs.model.reference.impl.EPRImpl <em>EPR</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rrs.model.reference.impl.EPRImpl
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getEPR()
	 * @generated
	 */
	int EPR = 0;

	/**
	 * The feature id for the '<em><b>Address</b></em>' containment reference.
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
	 * The feature id for the '<em><b>Reference Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPR__REFERENCE_PARAMETERS = 2;

	/**
	 * The feature id for the '<em><b>Port Type</b></em>' containment reference.
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
	 * The feature id for the '<em><b>Policy</b></em>' containment reference.
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
	 * The meta object id for the '{@link org.eclipse.rrs.model.reference.impl.PortTypeImpl <em>Port Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rrs.model.reference.impl.PortTypeImpl
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getPortType()
	 * @generated
	 */
	int PORT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Port Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rrs.model.reference.impl.AddressImpl <em>Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rrs.model.reference.impl.AddressImpl
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getAddress()
	 * @generated
	 */
	int ADDRESS = 2;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS__URI = 0;

	/**
	 * The number of structural features of the '<em>Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDRESS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rrs.model.reference.impl.ReferencePropertiesImpl <em>Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePropertiesImpl
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getReferenceProperties()
	 * @generated
	 */
	int REFERENCE_PROPERTIES = 3;

	/**
	 * The feature id for the '<em><b>Resolution System</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTIES__RESOLUTION_SYSTEM = 0;

	/**
	 * The number of structural features of the '<em>Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PROPERTIES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rrs.model.reference.impl.ReferenceParametersImpl <em>Parameters</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rrs.model.reference.impl.ReferenceParametersImpl
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getReferenceParameters()
	 * @generated
	 */
	int REFERENCE_PARAMETERS = 4;

	/**
	 * The feature id for the '<em><b>Reference Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PARAMETERS__REFERENCE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PARAMETERS__STATEMENT = 1;

	/**
	 * The number of structural features of the '<em>Parameters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_PARAMETERS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.rrs.model.reference.impl.ServiceNameImpl <em>Service Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rrs.model.reference.impl.ServiceNameImpl
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getServiceName()
	 * @generated
	 */
	int SERVICE_NAME = 5;

	/**
	 * The feature id for the '<em><b>Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_NAME__PORT_NAME = 0;

	/**
	 * The number of structural features of the '<em>Service Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_NAME_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.rrs.model.reference.impl.PolicyImpl <em>Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rrs.model.reference.impl.PolicyImpl
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getPolicy()
	 * @generated
	 */
	int POLICY = 6;

	/**
	 * The number of structural features of the '<em>Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.rrs.model.reference.impl.RRSAdapterImpl <em>RRS Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.rrs.model.reference.impl.RRSAdapterImpl
	 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getRRSAdapter()
	 * @generated
	 */
	int RRS_ADAPTER = 7;

	/**
	 * The feature id for the '<em><b>Adapter URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RRS_ADAPTER__ADAPTER_URI = 0;

	/**
	 * The number of structural features of the '<em>RRS Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RRS_ADAPTER_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.rrs.model.reference.EPR <em>EPR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EPR</em>'.
	 * @see org.eclipse.rrs.model.reference.EPR
	 * @generated
	 */
	EClass getEPR();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rrs.model.reference.EPR#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Address</em>'.
	 * @see org.eclipse.rrs.model.reference.EPR#getAddress()
	 * @see #getEPR()
	 * @generated
	 */
	EReference getEPR_Address();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rrs.model.reference.EPR#getReferenceProperties <em>Reference Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reference Properties</em>'.
	 * @see org.eclipse.rrs.model.reference.EPR#getReferenceProperties()
	 * @see #getEPR()
	 * @generated
	 */
	EReference getEPR_ReferenceProperties();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rrs.model.reference.EPR#getReferenceParameters <em>Reference Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reference Parameters</em>'.
	 * @see org.eclipse.rrs.model.reference.EPR#getReferenceParameters()
	 * @see #getEPR()
	 * @generated
	 */
	EReference getEPR_ReferenceParameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rrs.model.reference.EPR#getPortType <em>Port Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Port Type</em>'.
	 * @see org.eclipse.rrs.model.reference.EPR#getPortType()
	 * @see #getEPR()
	 * @generated
	 */
	EReference getEPR_PortType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rrs.model.reference.EPR#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Service Name</em>'.
	 * @see org.eclipse.rrs.model.reference.EPR#getServiceName()
	 * @see #getEPR()
	 * @generated
	 */
	EReference getEPR_ServiceName();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rrs.model.reference.EPR#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Policy</em>'.
	 * @see org.eclipse.rrs.model.reference.EPR#getPolicy()
	 * @see #getEPR()
	 * @generated
	 */
	EReference getEPR_Policy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rrs.model.reference.PortType <em>Port Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Type</em>'.
	 * @see org.eclipse.rrs.model.reference.PortType
	 * @generated
	 */
	EClass getPortType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rrs.model.reference.PortType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.rrs.model.reference.PortType#getValue()
	 * @see #getPortType()
	 * @generated
	 */
	EAttribute getPortType_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rrs.model.reference.Address <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Address</em>'.
	 * @see org.eclipse.rrs.model.reference.Address
	 * @generated
	 */
	EClass getAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rrs.model.reference.Address#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.rrs.model.reference.Address#getUri()
	 * @see #getAddress()
	 * @generated
	 */
	EAttribute getAddress_Uri();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rrs.model.reference.ReferenceProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Properties</em>'.
	 * @see org.eclipse.rrs.model.reference.ReferenceProperties
	 * @generated
	 */
	EClass getReferenceProperties();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.rrs.model.reference.ReferenceProperties#getResolutionSystem <em>Resolution System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resolution System</em>'.
	 * @see org.eclipse.rrs.model.reference.ReferenceProperties#getResolutionSystem()
	 * @see #getReferenceProperties()
	 * @generated
	 */
	EReference getReferenceProperties_ResolutionSystem();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rrs.model.reference.ReferenceParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameters</em>'.
	 * @see org.eclipse.rrs.model.reference.ReferenceParameters
	 * @generated
	 */
	EClass getReferenceParameters();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rrs.model.reference.ReferenceParameters#getReferenceName <em>Reference Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference Name</em>'.
	 * @see org.eclipse.rrs.model.reference.ReferenceParameters#getReferenceName()
	 * @see #getReferenceParameters()
	 * @generated
	 */
	EAttribute getReferenceParameters_ReferenceName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rrs.model.reference.ReferenceParameters#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement</em>'.
	 * @see org.eclipse.rrs.model.reference.ReferenceParameters#getStatement()
	 * @see #getReferenceParameters()
	 * @generated
	 */
	EAttribute getReferenceParameters_Statement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rrs.model.reference.ServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Name</em>'.
	 * @see org.eclipse.rrs.model.reference.ServiceName
	 * @generated
	 */
	EClass getServiceName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rrs.model.reference.ServiceName#getPortName <em>Port Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port Name</em>'.
	 * @see org.eclipse.rrs.model.reference.ServiceName#getPortName()
	 * @see #getServiceName()
	 * @generated
	 */
	EAttribute getServiceName_PortName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rrs.model.reference.Policy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Policy</em>'.
	 * @see org.eclipse.rrs.model.reference.Policy
	 * @generated
	 */
	EClass getPolicy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.rrs.model.reference.RRSAdapter <em>RRS Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RRS Adapter</em>'.
	 * @see org.eclipse.rrs.model.reference.RRSAdapter
	 * @generated
	 */
	EClass getRRSAdapter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.rrs.model.reference.RRSAdapter#getAdapterURI <em>Adapter URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Adapter URI</em>'.
	 * @see org.eclipse.rrs.model.reference.RRSAdapter#getAdapterURI()
	 * @see #getRRSAdapter()
	 * @generated
	 */
	EAttribute getRRSAdapter_AdapterURI();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ReferenceFactory getReferenceFactory();

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
		 * The meta object literal for the '{@link org.eclipse.rrs.model.reference.impl.EPRImpl <em>EPR</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rrs.model.reference.impl.EPRImpl
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getEPR()
		 * @generated
		 */
		EClass EPR = eINSTANCE.getEPR();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPR__ADDRESS = eINSTANCE.getEPR_Address();

		/**
		 * The meta object literal for the '<em><b>Reference Properties</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPR__REFERENCE_PROPERTIES = eINSTANCE.getEPR_ReferenceProperties();

		/**
		 * The meta object literal for the '<em><b>Reference Parameters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPR__REFERENCE_PARAMETERS = eINSTANCE.getEPR_ReferenceParameters();

		/**
		 * The meta object literal for the '<em><b>Port Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPR__PORT_TYPE = eINSTANCE.getEPR_PortType();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPR__SERVICE_NAME = eINSTANCE.getEPR_ServiceName();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPR__POLICY = eINSTANCE.getEPR_Policy();

		/**
		 * The meta object literal for the '{@link org.eclipse.rrs.model.reference.impl.PortTypeImpl <em>Port Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rrs.model.reference.impl.PortTypeImpl
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getPortType()
		 * @generated
		 */
		EClass PORT_TYPE = eINSTANCE.getPortType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_TYPE__VALUE = eINSTANCE.getPortType_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.rrs.model.reference.impl.AddressImpl <em>Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rrs.model.reference.impl.AddressImpl
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getAddress()
		 * @generated
		 */
		EClass ADDRESS = eINSTANCE.getAddress();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDRESS__URI = eINSTANCE.getAddress_Uri();

		/**
		 * The meta object literal for the '{@link org.eclipse.rrs.model.reference.impl.ReferencePropertiesImpl <em>Properties</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePropertiesImpl
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getReferenceProperties()
		 * @generated
		 */
		EClass REFERENCE_PROPERTIES = eINSTANCE.getReferenceProperties();

		/**
		 * The meta object literal for the '<em><b>Resolution System</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_PROPERTIES__RESOLUTION_SYSTEM = eINSTANCE.getReferenceProperties_ResolutionSystem();

		/**
		 * The meta object literal for the '{@link org.eclipse.rrs.model.reference.impl.ReferenceParametersImpl <em>Parameters</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rrs.model.reference.impl.ReferenceParametersImpl
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getReferenceParameters()
		 * @generated
		 */
		EClass REFERENCE_PARAMETERS = eINSTANCE.getReferenceParameters();

		/**
		 * The meta object literal for the '<em><b>Reference Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_PARAMETERS__REFERENCE_NAME = eINSTANCE.getReferenceParameters_ReferenceName();

		/**
		 * The meta object literal for the '<em><b>Statement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_PARAMETERS__STATEMENT = eINSTANCE.getReferenceParameters_Statement();

		/**
		 * The meta object literal for the '{@link org.eclipse.rrs.model.reference.impl.ServiceNameImpl <em>Service Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rrs.model.reference.impl.ServiceNameImpl
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getServiceName()
		 * @generated
		 */
		EClass SERVICE_NAME = eINSTANCE.getServiceName();

		/**
		 * The meta object literal for the '<em><b>Port Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_NAME__PORT_NAME = eINSTANCE.getServiceName_PortName();

		/**
		 * The meta object literal for the '{@link org.eclipse.rrs.model.reference.impl.PolicyImpl <em>Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rrs.model.reference.impl.PolicyImpl
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getPolicy()
		 * @generated
		 */
		EClass POLICY = eINSTANCE.getPolicy();

		/**
		 * The meta object literal for the '{@link org.eclipse.rrs.model.reference.impl.RRSAdapterImpl <em>RRS Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.rrs.model.reference.impl.RRSAdapterImpl
		 * @see org.eclipse.rrs.model.reference.impl.ReferencePackageImpl#getRRSAdapter()
		 * @generated
		 */
		EClass RRS_ADAPTER = eINSTANCE.getRRSAdapter();

		/**
		 * The meta object literal for the '<em><b>Adapter URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RRS_ADAPTER__ADAPTER_URI = eINSTANCE.getRRSAdapter_AdapterURI();

	}

} //ReferencePackage
