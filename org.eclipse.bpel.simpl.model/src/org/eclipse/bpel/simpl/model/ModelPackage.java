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
   * The feature id for the '<em><b>Ds Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_MANAGEMENT_ACTIVITY__DS_IDENTIFIER = BPELPackage.EXTENSION_ACTIVITY_FEATURE_COUNT + 0;

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
   * The feature id for the '<em><b>Ds Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DS_IDENTIFIER = DATA_MANAGEMENT_ACTIVITY__DS_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Ds Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

  /**
   * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

  /**
   * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

  /**
   * The feature id for the '<em><b>Ds Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

  /**
   * The feature id for the '<em><b>Query Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_DATA_ACTIVITY__QUERY_TARGET = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

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
   * The feature id for the '<em><b>Ds Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DS_IDENTIFIER = DATA_MANAGEMENT_ACTIVITY__DS_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Ds Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

  /**
   * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

  /**
   * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

  /**
   * The feature id for the '<em><b>Ds Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_COMMAND_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

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
   * The feature id for the '<em><b>Ds Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RETRIEVE_DATA_ACTIVITY__DS_IDENTIFIER = DATA_MANAGEMENT_ACTIVITY__DS_IDENTIFIER;

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
   * The feature id for the '<em><b>Ds Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DS_IDENTIFIER = DATA_MANAGEMENT_ACTIVITY__DS_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Ds Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

  /**
   * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

  /**
   * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

  /**
   * The feature id for the '<em><b>Ds Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

  /**
   * The feature id for the '<em><b>Data Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Write Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__WRITE_TARGET = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

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
   * The feature id for the '<em><b>Ds Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DS_IDENTIFIER = DATA_MANAGEMENT_ACTIVITY__DS_IDENTIFIER;

  /**
   * The feature id for the '<em><b>Ds Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

  /**
   * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

  /**
   * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

  /**
   * The feature id for the '<em><b>Ds Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

  /**
   * The feature id for the '<em><b>Target Ds Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__TARGET_DS_IDENTIFIER = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Target Ds Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__TARGET_DS_TYPE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target Ds Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__TARGET_DS_KIND = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Target Ds Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__TARGET_DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Target Ds Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY__TARGET_DS_CONTAINER = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Transfer Data Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSFER_DATA_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 5;

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
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsIdentifier <em>Ds Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ds Identifier</em>'.
   * @see org.eclipse.bpel.simpl.model.DataManagementActivity#getDsIdentifier()
   * @see #getDataManagementActivity()
   * @generated
   */
  EAttribute getDataManagementActivity_DsIdentifier();

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
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.QueryDataActivity <em>Query Data Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Query Data Activity</em>'.
   * @see org.eclipse.bpel.simpl.model.QueryDataActivity
   * @generated
   */
  EClass getQueryDataActivity();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.QueryDataActivity#getQueryTarget <em>Query Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Query Target</em>'.
   * @see org.eclipse.bpel.simpl.model.QueryDataActivity#getQueryTarget()
   * @see #getQueryDataActivity()
   * @generated
   */
  EAttribute getQueryDataActivity_QueryTarget();

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
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity#getWriteTarget <em>Write Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Write Target</em>'.
   * @see org.eclipse.bpel.simpl.model.WriteDataBackActivity#getWriteTarget()
   * @see #getWriteDataBackActivity()
   * @generated
   */
  EAttribute getWriteDataBackActivity_WriteTarget();

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
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsIdentifier <em>Target Ds Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Identifier</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsIdentifier()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_TargetDsIdentifier();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsType <em>Target Ds Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Type</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsType()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_TargetDsType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsKind <em>Target Ds Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Kind</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsKind()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_TargetDsKind();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsLanguage <em>Target Ds Language</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Language</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsLanguage()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_TargetDsLanguage();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsContainer <em>Target Ds Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Container</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsContainer()
   * @see #getTransferDataActivity()
   * @generated
   */
  EAttribute getTransferDataActivity_TargetDsContainer();

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
     * The meta object literal for the '<em><b>Ds Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_MANAGEMENT_ACTIVITY__DS_IDENTIFIER = eINSTANCE.getDataManagementActivity_DsIdentifier();

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
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.QueryDataActivityImpl <em>Query Data Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.QueryDataActivityImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getQueryDataActivity()
     * @generated
     */
    EClass QUERY_DATA_ACTIVITY = eINSTANCE.getQueryDataActivity();

    /**
     * The meta object literal for the '<em><b>Query Target</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute QUERY_DATA_ACTIVITY__QUERY_TARGET = eINSTANCE.getQueryDataActivity_QueryTarget();

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
     * The meta object literal for the '<em><b>Data Variable</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE = eINSTANCE.getWriteDataBackActivity_DataVariable();

    /**
     * The meta object literal for the '<em><b>Write Target</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WRITE_DATA_BACK_ACTIVITY__WRITE_TARGET = eINSTANCE.getWriteDataBackActivity_WriteTarget();

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
     * The meta object literal for the '<em><b>Target Ds Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__TARGET_DS_IDENTIFIER = eINSTANCE.getTransferDataActivity_TargetDsIdentifier();

    /**
     * The meta object literal for the '<em><b>Target Ds Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__TARGET_DS_TYPE = eINSTANCE.getTransferDataActivity_TargetDsType();

    /**
     * The meta object literal for the '<em><b>Target Ds Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__TARGET_DS_KIND = eINSTANCE.getTransferDataActivity_TargetDsKind();

    /**
     * The meta object literal for the '<em><b>Target Ds Language</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__TARGET_DS_LANGUAGE = eINSTANCE.getTransferDataActivity_TargetDsLanguage();

    /**
     * The meta object literal for the '<em><b>Target Ds Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TRANSFER_DATA_ACTIVITY__TARGET_DS_CONTAINER = eINSTANCE.getTransferDataActivity_TargetDsContainer();

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

	}

} //ModelPackage
