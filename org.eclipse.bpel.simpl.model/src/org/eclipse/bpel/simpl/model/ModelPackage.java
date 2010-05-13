/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

// TODO: Auto-generated Javadoc
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
 * @see org.eclipse.bpel.simpl.model.ModelFactory
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
	String eNS_URI = "http://www.example.org/simpl";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "simpl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.eclipse.bpel.simpl.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl <em>Data Management Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDataManagementActivity()
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
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__DS_TYPE = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__DS_KIND = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Data Management Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.QueryActivityImpl <em>Query Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.QueryActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getQueryActivity()
	 * @generated
	 */
	int QUERY_ACTIVITY = 1;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Query Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY__QUERY_TARGET = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Query Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.InsertActivityImpl <em>Insert Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.InsertActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getInsertActivity()
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
	int INSERT_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
	 * The number of structural features of the '<em>Insert Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.UpdateActivityImpl <em>Update Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.UpdateActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getUpdateActivity()
	 * @generated
	 */
	int UPDATE_ACTIVITY = 3;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
	 * The number of structural features of the '<em>Update Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.DeleteActivityImpl <em>Delete Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.DeleteActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDeleteActivity()
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
	int DELETE_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
	 * The number of structural features of the '<em>Delete Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.CreateActivityImpl <em>Create Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.CreateActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getCreateActivity()
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
	int CREATE_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
	 * The number of structural features of the '<em>Create Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.DropActivityImpl <em>Drop Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.DropActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDropActivity()
	 * @generated
	 */
	int DROP_ACTIVITY = 6;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
	 * The number of structural features of the '<em>Drop Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DROP_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.CallActivityImpl <em>Call Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.CallActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getCallActivity()
	 * @generated
	 */
	int CALL_ACTIVITY = 7;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
	 * The number of structural features of the '<em>Call Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.RetrieveDataActivityImpl <em>Retrieve Data Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.RetrieveDataActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getRetrieveDataActivity()
	 * @generated
	 */
	int RETRIEVE_DATA_ACTIVITY = 8;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>Ds Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
	 * The feature id for the '<em><b>Ds Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
	 * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
	 * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
	 * The feature id for the '<em><b>Ds Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Data Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Retrieve Data Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_DATA_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl <em>Transfer Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.bpel.simpl.model.impl.TransferActivityImpl
	 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getTransferActivity()
	 * @generated
	 */
	int TRANSFER_ACTIVITY = 9;

	/**
	 * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

	/**
	 * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

	/**
	 * The feature id for the '<em><b>From Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__FROM_SOURCE = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__TO_SOURCE = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY__TARGET = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Transfer Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 3;

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.DataManagementActivity <em>Data Management Activity</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Data Management Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.DataManagementActivity
	 * @generated
	 */
	EClass getDataManagementActivity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsStatement <em>Ds Statement</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ds Statement</em>'.
	 * @see org.eclipse.bpel.simpl.model.DataManagementActivity#getDsStatement()
	 * @see #getDataManagementActivity()
	 * @generated
	 */
	EAttribute getDataManagementActivity_DsStatement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsLanguage <em>Ds Language</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ds Language</em>'.
	 * @see org.eclipse.bpel.simpl.model.DataManagementActivity#getDsLanguage()
	 * @see #getDataManagementActivity()
	 * @generated
	 */
	EAttribute getDataManagementActivity_DsLanguage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsKind <em>Ds Kind</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ds Kind</em>'.
	 * @see org.eclipse.bpel.simpl.model.DataManagementActivity#getDsKind()
	 * @see #getDataManagementActivity()
	 * @generated
	 */
	EAttribute getDataManagementActivity_DsKind();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsType <em>Ds Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ds Type</em>'.
	 * @see org.eclipse.bpel.simpl.model.DataManagementActivity#getDsType()
	 * @see #getDataManagementActivity()
	 * @generated
	 */
	EAttribute getDataManagementActivity_DsType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsAddress <em>Ds Address</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ds Address</em>'.
	 * @see org.eclipse.bpel.simpl.model.DataManagementActivity#getDsAddress()
	 * @see #getDataManagementActivity()
	 * @generated
	 */
	EAttribute getDataManagementActivity_DsAddress();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.QueryActivity <em>Query Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.QueryActivity
	 * @generated
	 */
	EClass getQueryActivity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.QueryActivity#getQueryTarget <em>Query Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query Target</em>'.
	 * @see org.eclipse.bpel.simpl.model.QueryActivity#getQueryTarget()
	 * @see #getQueryActivity()
	 * @generated
	 */
	EAttribute getQueryActivity_QueryTarget();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.InsertActivity <em>Insert Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Insert Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.InsertActivity
	 * @generated
	 */
	EClass getInsertActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.UpdateActivity <em>Update Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Update Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.UpdateActivity
	 * @generated
	 */
	EClass getUpdateActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.DeleteActivity <em>Delete Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delete Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.DeleteActivity
	 * @generated
	 */
	EClass getDeleteActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.CreateActivity <em>Create Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Create Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.CreateActivity
	 * @generated
	 */
	EClass getCreateActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.DropActivity <em>Drop Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drop Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.DropActivity
	 * @generated
	 */
	EClass getDropActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.CallActivity <em>Call Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.CallActivity
	 * @generated
	 */
	EClass getCallActivity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.RetrieveDataActivity <em>Retrieve Data Activity</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Retrieve Data Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.RetrieveDataActivity
	 * @generated
	 */
	EClass getRetrieveDataActivity();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.bpel.simpl.model.RetrieveDataActivity#getDataVariable <em>Data Variable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Variable</em>'.
	 * @see org.eclipse.bpel.simpl.model.RetrieveDataActivity#getDataVariable()
	 * @see #getRetrieveDataActivity()
	 * @generated
	 */
	EReference getRetrieveDataActivity_DataVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.TransferActivity <em>Transfer Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transfer Activity</em>'.
	 * @see org.eclipse.bpel.simpl.model.TransferActivity
	 * @generated
	 */
	EClass getTransferActivity();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpel.simpl.model.TransferActivity#getFromSource <em>From Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From Source</em>'.
	 * @see org.eclipse.bpel.simpl.model.TransferActivity#getFromSource()
	 * @see #getTransferActivity()
	 * @generated
	 */
	EReference getTransferActivity_FromSource();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.bpel.simpl.model.TransferActivity#getToSource <em>To Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>To Source</em>'.
	 * @see org.eclipse.bpel.simpl.model.TransferActivity#getToSource()
	 * @see #getTransferActivity()
	 * @generated
	 */
	EReference getTransferActivity_ToSource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see org.eclipse.bpel.simpl.model.TransferActivity#getTarget()
	 * @see #getTransferActivity()
	 * @generated
	 */
	EAttribute getTransferActivity_Target();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->.
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl <em>Data Management Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDataManagementActivity()
		 * @generated
		 */
		EClass DATA_MANAGEMENT_ACTIVITY = eINSTANCE.getDataManagementActivity();

		/**
		 * The meta object literal for the '<em><b>Ds Statement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT = eINSTANCE.getDataManagementActivity_DsStatement();

		/**
		 * The meta object literal for the '<em><b>Ds Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE = eINSTANCE.getDataManagementActivity_DsLanguage();

		/**
		 * The meta object literal for the '<em><b>Ds Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_MANAGEMENT_ACTIVITY__DS_KIND = eINSTANCE.getDataManagementActivity_DsKind();

		/**
		 * The meta object literal for the '<em><b>Ds Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_MANAGEMENT_ACTIVITY__DS_TYPE = eINSTANCE.getDataManagementActivity_DsType();

		/**
		 * The meta object literal for the '<em><b>Ds Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS = eINSTANCE.getDataManagementActivity_DsAddress();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.QueryActivityImpl <em>Query Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.QueryActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getQueryActivity()
		 * @generated
		 */
		EClass QUERY_ACTIVITY = eINSTANCE.getQueryActivity();

		/**
		 * The meta object literal for the '<em><b>Query Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_ACTIVITY__QUERY_TARGET = eINSTANCE.getQueryActivity_QueryTarget();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.InsertActivityImpl <em>Insert Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.InsertActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getInsertActivity()
		 * @generated
		 */
		EClass INSERT_ACTIVITY = eINSTANCE.getInsertActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.UpdateActivityImpl <em>Update Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.UpdateActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getUpdateActivity()
		 * @generated
		 */
		EClass UPDATE_ACTIVITY = eINSTANCE.getUpdateActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.DeleteActivityImpl <em>Delete Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.DeleteActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDeleteActivity()
		 * @generated
		 */
		EClass DELETE_ACTIVITY = eINSTANCE.getDeleteActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.CreateActivityImpl <em>Create Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.CreateActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getCreateActivity()
		 * @generated
		 */
		EClass CREATE_ACTIVITY = eINSTANCE.getCreateActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.DropActivityImpl <em>Drop Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.DropActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDropActivity()
		 * @generated
		 */
		EClass DROP_ACTIVITY = eINSTANCE.getDropActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.CallActivityImpl <em>Call Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.CallActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getCallActivity()
		 * @generated
		 */
		EClass CALL_ACTIVITY = eINSTANCE.getCallActivity();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.RetrieveDataActivityImpl <em>Retrieve Data Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.RetrieveDataActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getRetrieveDataActivity()
		 * @generated
		 */
		EClass RETRIEVE_DATA_ACTIVITY = eINSTANCE.getRetrieveDataActivity();

		/**
		 * The meta object literal for the '<em><b>Data Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE = eINSTANCE.getRetrieveDataActivity_DataVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl <em>Transfer Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.bpel.simpl.model.impl.TransferActivityImpl
		 * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getTransferActivity()
		 * @generated
		 */
		EClass TRANSFER_ACTIVITY = eINSTANCE.getTransferActivity();

		/**
		 * The meta object literal for the '<em><b>From Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFER_ACTIVITY__FROM_SOURCE = eINSTANCE.getTransferActivity_FromSource();

		/**
		 * The meta object literal for the '<em><b>To Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFER_ACTIVITY__TO_SOURCE = eINSTANCE.getTransferActivity_ToSource();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFER_ACTIVITY__TARGET = eINSTANCE.getTransferActivity_Target();

	}

} //ModelPackage
