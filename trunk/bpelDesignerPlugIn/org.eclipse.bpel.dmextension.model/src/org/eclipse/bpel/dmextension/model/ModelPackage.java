/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.dmextension.model;

import org.eclipse.bpel.model.BPELPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.eclipse.bpel.dmextension.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/DMextension";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "DMextension";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.DataManagementActivityImpl <em>Data Management Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.DataManagementActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getDataManagementActivity()
	 * @generated
	 */
	int DATA_MANAGEMENT_ACTIVITY = 0;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__STATEMENT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__KIND = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Data Management Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.UpdateActivityImpl <em>Update Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.UpdateActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getUpdateActivity()
	 * @generated
	 */
	int UPDATE_ACTIVITY = 1;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The number of structural features of the '<em>Update Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.InsertActivityImpl <em>Insert Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.InsertActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getInsertActivity()
	 * @generated
	 */
	int INSERT_ACTIVITY = 2;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The number of structural features of the '<em>Insert Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.SelectActivityImpl <em>Select Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.SelectActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getSelectActivity()
	 * @generated
	 */
	int SELECT_ACTIVITY = 3;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The number of structural features of the '<em>Select Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.DeleteActivityImpl <em>Delete Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.DeleteActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getDeleteActivity()
	 * @generated
	 */
	int DELETE_ACTIVITY = 4;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The number of structural features of the '<em>Delete Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.CreateActivityImpl <em>Create Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.CreateActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getCreateActivity()
	 * @generated
	 */
	int CREATE_ACTIVITY = 5;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The number of structural features of the '<em>Create Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.CallProcedureActivityImpl <em>Call Procedure Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.CallProcedureActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getCallProcedureActivity()
	 * @generated
	 */
	int CALL_PROCEDURE_ACTIVITY = 6;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The number of structural features of the '<em>Call Procedure Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PROCEDURE_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.RetrieveSetActivityImpl <em>Retrieve Set Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.RetrieveSetActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getRetrieveSetActivity()
	 * @generated
	 */
	int RETRIEVE_SET_ACTIVITY = 7;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The number of structural features of the '<em>Retrieve Set Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_SET_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.dmextension.model.impl.WriteBackActivityImpl <em>Write Back Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.dmextension.model.impl.WriteBackActivityImpl
	 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getWriteBackActivity()
	 * @generated
	 */
	int WRITE_BACK_ACTIVITY = 8;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The number of structural features of the '<em>Write Back Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BACK_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.DataManagementActivity <em>Data Management Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Management Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.DataManagementActivity
	 * @generated
	 */
	EClass getDataManagementActivity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.dmextension.model.DataManagementActivity#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement</em>'.
	 * @see org.eclipse.bpel.dmextension.model.DataManagementActivity#getStatement()
	 * @see #getDataManagementActivity()
	 * @generated
	 */
	EAttribute getDataManagementActivity_Statement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.dmextension.model.DataManagementActivity#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.bpel.dmextension.model.DataManagementActivity#getKind()
	 * @see #getDataManagementActivity()
	 * @generated
	 */
	EAttribute getDataManagementActivity_Kind();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.UpdateActivity <em>Update Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Update Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.UpdateActivity
	 * @generated
	 */
	EClass getUpdateActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.InsertActivity <em>Insert Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Insert Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.InsertActivity
	 * @generated
	 */
	EClass getInsertActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.SelectActivity <em>Select Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Select Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.SelectActivity
	 * @generated
	 */
	EClass getSelectActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.DeleteActivity <em>Delete Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delete Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.DeleteActivity
	 * @generated
	 */
	EClass getDeleteActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.CreateActivity <em>Create Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Create Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.CreateActivity
	 * @generated
	 */
	EClass getCreateActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.CallProcedureActivity <em>Call Procedure Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Procedure Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.CallProcedureActivity
	 * @generated
	 */
	EClass getCallProcedureActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.RetrieveSetActivity <em>Retrieve Set Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Retrieve Set Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.RetrieveSetActivity
	 * @generated
	 */
	EClass getRetrieveSetActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.dmextension.model.WriteBackActivity <em>Write Back Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Write Back Activity</em>'.
	 * @see org.eclipse.bpel.dmextension.model.WriteBackActivity
	 * @generated
	 */
	EClass getWriteBackActivity();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.DataManagementActivityImpl <em>Data Management Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.DataManagementActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getDataManagementActivity()
		 * @generated
		 */
		EClass DATA_MANAGEMENT_ACTIVITY = eINSTANCE.getDataManagementActivity();

		/**
		 * The meta object literal for the '<em><b>Statement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_MANAGEMENT_ACTIVITY__STATEMENT = eINSTANCE.getDataManagementActivity_Statement();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_MANAGEMENT_ACTIVITY__KIND = eINSTANCE.getDataManagementActivity_Kind();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.UpdateActivityImpl <em>Update Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.UpdateActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getUpdateActivity()
		 * @generated
		 */
		EClass UPDATE_ACTIVITY = eINSTANCE.getUpdateActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.InsertActivityImpl <em>Insert Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.InsertActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getInsertActivity()
		 * @generated
		 */
		EClass INSERT_ACTIVITY = eINSTANCE.getInsertActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.SelectActivityImpl <em>Select Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.SelectActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getSelectActivity()
		 * @generated
		 */
		EClass SELECT_ACTIVITY = eINSTANCE.getSelectActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.DeleteActivityImpl <em>Delete Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.DeleteActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getDeleteActivity()
		 * @generated
		 */
		EClass DELETE_ACTIVITY = eINSTANCE.getDeleteActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.CreateActivityImpl <em>Create Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.CreateActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getCreateActivity()
		 * @generated
		 */
		EClass CREATE_ACTIVITY = eINSTANCE.getCreateActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.CallProcedureActivityImpl <em>Call Procedure Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.CallProcedureActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getCallProcedureActivity()
		 * @generated
		 */
		EClass CALL_PROCEDURE_ACTIVITY = eINSTANCE.getCallProcedureActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.RetrieveSetActivityImpl <em>Retrieve Set Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.RetrieveSetActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getRetrieveSetActivity()
		 * @generated
		 */
		EClass RETRIEVE_SET_ACTIVITY = eINSTANCE.getRetrieveSetActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.dmextension.model.impl.WriteBackActivityImpl <em>Write Back Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.dmextension.model.impl.WriteBackActivityImpl
		 * @see org.eclipse.bpel.dmextension.model.impl.ModelPackageImpl#getWriteBackActivity()
		 * @generated
		 */
		EClass WRITE_BACK_ACTIVITY = eINSTANCE.getWriteBackActivity();

	}

} //ModelPackage
