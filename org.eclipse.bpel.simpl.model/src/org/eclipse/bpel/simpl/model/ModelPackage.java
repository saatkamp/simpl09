/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: ModelPackage.java 1963 2011-10-26 11:22:17Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
   * The feature id for the '<em><b>Data Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Dm Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_ACTIVITY__DM_COMMAND = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Data Management Activity</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 2;

	/**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.QueryDataActivityImpl <em>Query Data Activity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.QueryDataActivityImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getQueryDataActivity()
   * @generated
   */
  int QUERY_DATA_ACTIVITY = 1;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

  /**
   * The feature id for the '<em><b>Data Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DATA_RESOURCE = DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE;

  /**
   * The feature id for the '<em><b>Dm Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DM_COMMAND = DATA_MANAGEMENT_ACTIVITY__DM_COMMAND;

  /**
   * The feature id for the '<em><b>Target Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__TARGET_CONTAINER = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Query Data Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.IssueCommandActivityImpl <em>Issue Command Activity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.IssueCommandActivityImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getIssueCommandActivity()
   * @generated
   */
  int ISSUE_COMMAND_ACTIVITY = 2;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

  /**
   * The feature id for the '<em><b>Data Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DATA_RESOURCE = DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE;

  /**
   * The feature id for the '<em><b>Dm Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DM_COMMAND = DATA_MANAGEMENT_ACTIVITY__DM_COMMAND;

  /**
   * The number of structural features of the '<em>Issue Command Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.WriteDataBackActivityImpl <em>Write Data Back Activity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.WriteDataBackActivityImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getWriteDataBackActivity()
   * @generated
   */
  int WRITE_DATA_BACK_ACTIVITY = 4;

  /**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.RetrieveDataActivityImpl <em>Retrieve Data Activity</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.RetrieveDataActivityImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getRetrieveDataActivity()
   * @generated
   */
	int RETRIEVE_DATA_ACTIVITY = 3;

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
   * The feature id for the '<em><b>Data Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RETRIEVE_DATA_ACTIVITY__DATA_RESOURCE = DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE;

  /**
   * The feature id for the '<em><b>Dm Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RETRIEVE_DATA_ACTIVITY__DM_COMMAND = DATA_MANAGEMENT_ACTIVITY__DM_COMMAND;

  /**
   * The feature id for the '<em><b>Target Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Retrieve Data Activity</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int RETRIEVE_DATA_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

  /**
   * The feature id for the '<em><b>Data Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DATA_RESOURCE = DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE;

  /**
   * The feature id for the '<em><b>Dm Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DM_COMMAND = DATA_MANAGEMENT_ACTIVITY__DM_COMMAND;

  /**
   * The feature id for the '<em><b>Target Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__TARGET_CONTAINER = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Data Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Write Data Back Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 2;


	/**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.TransferDataActivityImpl <em>Transfer Data Activity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.TransferDataActivityImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getTransferDataActivity()
   * @generated
   */
  int TRANSFER_DATA_ACTIVITY = 5;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

  /**
   * The feature id for the '<em><b>Data Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DATA_RESOURCE = DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE;

  /**
   * The feature id for the '<em><b>Dm Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DM_COMMAND = DATA_MANAGEMENT_ACTIVITY__DM_COMMAND;

  /**
   * The feature id for the '<em><b>Data Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DATA_SOURCE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Data Source Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DATA_SOURCE_COMMAND = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Data Sink</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DATA_SINK = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Data Sink Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DATA_SINK_CONTAINER = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Transfer Data Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.DataManagementPatternImpl <em>Data Management Pattern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.DataManagementPatternImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDataManagementPattern()
   * @generated
   */
  int DATA_MANAGEMENT_PATTERN = 6;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN__DOCUMENTATION_ELEMENT = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN__ELEMENT = BPELPackage.EXTENSION_ACTIVITY__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN__EEXTENSIBILITY_ELEMENTS = BPELPackage.EXTENSION_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN__DOCUMENTATION = BPELPackage.EXTENSION_ACTIVITY__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN__NAME = BPELPackage.EXTENSION_ACTIVITY__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN__SUPPRESS_JOIN_FAILURE = BPELPackage.EXTENSION_ACTIVITY__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN__TARGETS = BPELPackage.EXTENSION_ACTIVITY__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN__SOURCES = BPELPackage.EXTENSION_ACTIVITY__SOURCES;

  /**
   * The number of structural features of the '<em>Data Management Pattern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_PATTERN_FEATURE_COUNT = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.ContainerToContainerPatternImpl <em>Container To Container Pattern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.ContainerToContainerPatternImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getContainerToContainerPattern()
   * @generated
   */
  int CONTAINER_TO_CONTAINER_PATTERN = 7;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_PATTERN__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__ELEMENT = DATA_MANAGEMENT_PATTERN__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_PATTERN__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__DOCUMENTATION = DATA_MANAGEMENT_PATTERN__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__NAME = DATA_MANAGEMENT_PATTERN__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_PATTERN__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__TARGETS = DATA_MANAGEMENT_PATTERN__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__SOURCES = DATA_MANAGEMENT_PATTERN__SOURCES;

  /**
   * The feature id for the '<em><b>Source Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__SOURCE_CONTAINER = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Target Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN__TARGET_CONTAINER = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Container To Container Pattern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_TO_CONTAINER_PATTERN_FEATURE_COUNT = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.DataIterationPatternImpl <em>Data Iteration Pattern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.DataIterationPatternImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDataIterationPattern()
   * @generated
   */
  int DATA_ITERATION_PATTERN = 8;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_PATTERN__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__ELEMENT = DATA_MANAGEMENT_PATTERN__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_PATTERN__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__DOCUMENTATION = DATA_MANAGEMENT_PATTERN__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__NAME = DATA_MANAGEMENT_PATTERN__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_PATTERN__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__TARGETS = DATA_MANAGEMENT_PATTERN__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__SOURCES = DATA_MANAGEMENT_PATTERN__SOURCES;

  /**
   * The feature id for the '<em><b>Container Reference List</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__CONTAINER_REFERENCE_LIST = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Current Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__CURRENT_CONTAINER = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Activity</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN__ACTIVITY = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Data Iteration Pattern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_ITERATION_PATTERN_FEATURE_COUNT = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.DataFormatConversionPatternImpl <em>Data Format Conversion Pattern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.DataFormatConversionPatternImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDataFormatConversionPattern()
   * @generated
   */
  int DATA_FORMAT_CONVERSION_PATTERN = 9;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_PATTERN__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__ELEMENT = DATA_MANAGEMENT_PATTERN__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_PATTERN__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__DOCUMENTATION = DATA_MANAGEMENT_PATTERN__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__NAME = DATA_MANAGEMENT_PATTERN__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_PATTERN__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__TARGETS = DATA_MANAGEMENT_PATTERN__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__SOURCES = DATA_MANAGEMENT_PATTERN__SOURCES;

  /**
   * The feature id for the '<em><b>Source Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__SOURCE_CONTAINER = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Target Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN__TARGET_CONTAINER = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Data Format Conversion Pattern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_FORMAT_CONVERSION_PATTERN_FEATURE_COUNT = DATA_MANAGEMENT_PATTERN_FEATURE_COUNT + 2;

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
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDataResource <em>Data Resource</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Resource</em>'.
   * @see org.eclipse.bpel.simpl.model.DataManagementActivity#getDataResource()
   * @see #getDataManagementActivity()
   * @generated
   */
  EAttribute getDataManagementActivity_DataResource();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDmCommand <em>Dm Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Dm Command</em>'.
   * @see org.eclipse.bpel.simpl.model.DataManagementActivity#getDmCommand()
   * @see #getDataManagementActivity()
   * @generated
   */
  EAttribute getDataManagementActivity_DmCommand();

  /**
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.QueryDataActivity <em>Query Data Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Query Data Activity</em>'.
   * @see org.eclipse.bpel.simpl.model.QueryDataActivity
   * @generated
   */
  EClass getQueryDataActivity();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.QueryDataActivity#getTargetContainer <em>Target Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Container</em>'.
   * @see org.eclipse.bpel.simpl.model.QueryDataActivity#getTargetContainer()
   * @see #getQueryDataActivity()
   * @generated
   */
  EAttribute getQueryDataActivity_TargetContainer();

  /**
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.IssueCommandActivity <em>Issue Command Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Issue Command Activity</em>'.
   * @see org.eclipse.bpel.simpl.model.IssueCommandActivity
   * @generated
   */
  EClass getIssueCommandActivity();

  /**
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity <em>Write Data Back Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Write Data Back Activity</em>'.
   * @see org.eclipse.bpel.simpl.model.WriteDataBackActivity
   * @generated
   */
  EClass getWriteDataBackActivity();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity#getTargetContainer <em>Target Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Container</em>'.
   * @see org.eclipse.bpel.simpl.model.WriteDataBackActivity#getTargetContainer()
   * @see #getWriteDataBackActivity()
   * @generated
   */
  EAttribute getWriteDataBackActivity_TargetContainer();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity#getDataVariable <em>Data Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Data Variable</em>'.
   * @see org.eclipse.bpel.simpl.model.WriteDataBackActivity#getDataVariable()
   * @see #getWriteDataBackActivity()
   * @generated
   */
  EReference getWriteDataBackActivity_DataVariable();

  /**
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.TransferDataActivity <em>Transfer Data Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Transfer Data Activity</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity
   * @generated
   */
  EClass getTransferDataActivity();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSource <em>Data Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Source</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSource()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_DataSource();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSourceCommand <em>Data Source Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Source Command</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSourceCommand()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_DataSourceCommand();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSink <em>Data Sink</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Sink</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSink()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_DataSink();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSinkContainer <em>Data Sink Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Sink Container</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getDataSinkContainer()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_DataSinkContainer();

  /**
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.DataManagementPattern <em>Data Management Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Management Pattern</em>'.
   * @see org.eclipse.bpel.simpl.model.DataManagementPattern
   * @generated
   */
  EClass getDataManagementPattern();

  /**
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.ContainerToContainerPattern <em>Container To Container Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Container To Container Pattern</em>'.
   * @see org.eclipse.bpel.simpl.model.ContainerToContainerPattern
   * @generated
   */
  EClass getContainerToContainerPattern();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.ContainerToContainerPattern#getSourceContainer <em>Source Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source Container</em>'.
   * @see org.eclipse.bpel.simpl.model.ContainerToContainerPattern#getSourceContainer()
   * @see #getContainerToContainerPattern()
   * @generated
   */
  EAttribute getContainerToContainerPattern_SourceContainer();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.ContainerToContainerPattern#getTargetContainer <em>Target Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Container</em>'.
   * @see org.eclipse.bpel.simpl.model.ContainerToContainerPattern#getTargetContainer()
   * @see #getContainerToContainerPattern()
   * @generated
   */
  EAttribute getContainerToContainerPattern_TargetContainer();

  /**
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.DataIterationPattern <em>Data Iteration Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Iteration Pattern</em>'.
   * @see org.eclipse.bpel.simpl.model.DataIterationPattern
   * @generated
   */
  EClass getDataIterationPattern();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getContainerReferenceList <em>Container Reference List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Container Reference List</em>'.
   * @see org.eclipse.bpel.simpl.model.DataIterationPattern#getContainerReferenceList()
   * @see #getDataIterationPattern()
   * @generated
   */
  EAttribute getDataIterationPattern_ContainerReferenceList();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getCurrentContainer <em>Current Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Current Container</em>'.
   * @see org.eclipse.bpel.simpl.model.DataIterationPattern#getCurrentContainer()
   * @see #getDataIterationPattern()
   * @generated
   */
  EAttribute getDataIterationPattern_CurrentContainer();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getActivity <em>Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Activity</em>'.
   * @see org.eclipse.bpel.simpl.model.DataIterationPattern#getActivity()
   * @see #getDataIterationPattern()
   * @generated
   */
  EReference getDataIterationPattern_Activity();

  /**
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.DataFormatConversionPattern <em>Data Format Conversion Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Format Conversion Pattern</em>'.
   * @see org.eclipse.bpel.simpl.model.DataFormatConversionPattern
   * @generated
   */
  EClass getDataFormatConversionPattern();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataFormatConversionPattern#getSourceContainer <em>Source Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source Container</em>'.
   * @see org.eclipse.bpel.simpl.model.DataFormatConversionPattern#getSourceContainer()
   * @see #getDataFormatConversionPattern()
   * @generated
   */
  EAttribute getDataFormatConversionPattern_SourceContainer();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataFormatConversionPattern#getTargetContainer <em>Target Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Container</em>'.
   * @see org.eclipse.bpel.simpl.model.DataFormatConversionPattern#getTargetContainer()
   * @see #getDataFormatConversionPattern()
   * @generated
   */
  EAttribute getDataFormatConversionPattern_TargetContainer();

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
   * Returns the meta object for the reference '{@link org.eclipse.bpel.simpl.model.RetrieveDataActivity#getTargetVariable <em>Target Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Variable</em>'.
   * @see org.eclipse.bpel.simpl.model.RetrieveDataActivity#getTargetVariable()
   * @see #getRetrieveDataActivity()
   * @generated
   */
  EReference getRetrieveDataActivity_TargetVariable();

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
     * The meta object literal for the '<em><b>Data Resource</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE = eINSTANCE.getDataManagementActivity_DataResource();

    /**
     * The meta object literal for the '<em><b>Dm Command</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_MANAGEMENT_ACTIVITY__DM_COMMAND = eINSTANCE.getDataManagementActivity_DmCommand();

    /**
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.QueryDataActivityImpl <em>Query Data Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.QueryDataActivityImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getQueryDataActivity()
     * @generated
     */
    EClass QUERY_DATA_ACTIVITY = eINSTANCE.getQueryDataActivity();

    /**
     * The meta object literal for the '<em><b>Target Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute QUERY_DATA_ACTIVITY__TARGET_CONTAINER = eINSTANCE.getQueryDataActivity_TargetContainer();

    /**
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.IssueCommandActivityImpl <em>Issue Command Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.IssueCommandActivityImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getIssueCommandActivity()
     * @generated
     */
    EClass ISSUE_COMMAND_ACTIVITY = eINSTANCE.getIssueCommandActivity();

    /**
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.WriteDataBackActivityImpl <em>Write Data Back Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.WriteDataBackActivityImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getWriteDataBackActivity()
     * @generated
     */
    EClass WRITE_DATA_BACK_ACTIVITY = eINSTANCE.getWriteDataBackActivity();

    /**
     * The meta object literal for the '<em><b>Target Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WRITE_DATA_BACK_ACTIVITY__TARGET_CONTAINER = eINSTANCE.getWriteDataBackActivity_TargetContainer();

    /**
     * The meta object literal for the '<em><b>Data Variable</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE = eINSTANCE.getWriteDataBackActivity_DataVariable();

    /**
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.TransferDataActivityImpl <em>Transfer Data Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.TransferDataActivityImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getTransferDataActivity()
     * @generated
     */
    EClass TRANSFER_DATA_ACTIVITY = eINSTANCE.getTransferDataActivity();

    /**
     * The meta object literal for the '<em><b>Data Source</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__DATA_SOURCE = eINSTANCE.getTransferDataActivity_DataSource();

    /**
     * The meta object literal for the '<em><b>Data Source Command</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__DATA_SOURCE_COMMAND = eINSTANCE.getTransferDataActivity_DataSourceCommand();

    /**
     * The meta object literal for the '<em><b>Data Sink</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__DATA_SINK = eINSTANCE.getTransferDataActivity_DataSink();

    /**
     * The meta object literal for the '<em><b>Data Sink Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__DATA_SINK_CONTAINER = eINSTANCE.getTransferDataActivity_DataSinkContainer();

    /**
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.DataManagementPatternImpl <em>Data Management Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.DataManagementPatternImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDataManagementPattern()
     * @generated
     */
    EClass DATA_MANAGEMENT_PATTERN = eINSTANCE.getDataManagementPattern();

    /**
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.ContainerToContainerPatternImpl <em>Container To Container Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.ContainerToContainerPatternImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getContainerToContainerPattern()
     * @generated
     */
    EClass CONTAINER_TO_CONTAINER_PATTERN = eINSTANCE.getContainerToContainerPattern();

    /**
     * The meta object literal for the '<em><b>Source Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTAINER_TO_CONTAINER_PATTERN__SOURCE_CONTAINER = eINSTANCE.getContainerToContainerPattern_SourceContainer();

    /**
     * The meta object literal for the '<em><b>Target Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTAINER_TO_CONTAINER_PATTERN__TARGET_CONTAINER = eINSTANCE.getContainerToContainerPattern_TargetContainer();

    /**
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.DataIterationPatternImpl <em>Data Iteration Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.DataIterationPatternImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDataIterationPattern()
     * @generated
     */
    EClass DATA_ITERATION_PATTERN = eINSTANCE.getDataIterationPattern();

    /**
     * The meta object literal for the '<em><b>Container Reference List</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_ITERATION_PATTERN__CONTAINER_REFERENCE_LIST = eINSTANCE.getDataIterationPattern_ContainerReferenceList();

    /**
     * The meta object literal for the '<em><b>Current Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_ITERATION_PATTERN__CURRENT_CONTAINER = eINSTANCE.getDataIterationPattern_CurrentContainer();

    /**
     * The meta object literal for the '<em><b>Activity</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_ITERATION_PATTERN__ACTIVITY = eINSTANCE.getDataIterationPattern_Activity();

    /**
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.DataFormatConversionPatternImpl <em>Data Format Conversion Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.DataFormatConversionPatternImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getDataFormatConversionPattern()
     * @generated
     */
    EClass DATA_FORMAT_CONVERSION_PATTERN = eINSTANCE.getDataFormatConversionPattern();

    /**
     * The meta object literal for the '<em><b>Source Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_FORMAT_CONVERSION_PATTERN__SOURCE_CONTAINER = eINSTANCE.getDataFormatConversionPattern_SourceContainer();

    /**
     * The meta object literal for the '<em><b>Target Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_FORMAT_CONVERSION_PATTERN__TARGET_CONTAINER = eINSTANCE.getDataFormatConversionPattern_TargetContainer();

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
     * The meta object literal for the '<em><b>Target Variable</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE = eINSTANCE.getRetrieveDataActivity_TargetVariable();

	}

} //ModelPackage
