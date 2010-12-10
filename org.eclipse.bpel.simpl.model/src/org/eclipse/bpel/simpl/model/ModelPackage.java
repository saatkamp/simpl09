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
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.IssueActivityImpl <em>Issue Activity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.IssueActivityImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getIssueActivity()
   * @generated
   */
  int ISSUE_ACTIVITY = 2;

  /**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

  /**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

  /**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

  /**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

  /**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

  /**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

  /**
   * The feature id for the '<em><b>Ds Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

  /**
   * The feature id for the '<em><b>Ds Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

  /**
   * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

  /**
   * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

  /**
   * The feature id for the '<em><b>Ds Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

  /**
   * The number of structural features of the '<em>Issue Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISSUE_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

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
   * The feature id for the '<em><b>Ds Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

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
   * The feature id for the '<em><b>Query Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY__QUERY_TARGET = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Write Data Back Activity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITE_DATA_BACK_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 2;


	/**
   * The meta object id for the '{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl <em>Transfer Activity</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.eclipse.bpel.simpl.model.impl.TransferActivityImpl
   * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getTransferActivity()
   * @generated
   */
	int TRANSFER_ACTIVITY = 5;

	/**
   * The feature id for the '<em><b>Documentation Element</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__DOCUMENTATION_ELEMENT = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION_ELEMENT;

	/**
   * The feature id for the '<em><b>Element</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__ELEMENT = DATA_MANAGEMENT_ACTIVITY__ELEMENT;

	/**
   * The feature id for the '<em><b>EExtensibility Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__EEXTENSIBILITY_ELEMENTS = DATA_MANAGEMENT_ACTIVITY__EEXTENSIBILITY_ELEMENTS;

	/**
   * The feature id for the '<em><b>Documentation</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__DOCUMENTATION = DATA_MANAGEMENT_ACTIVITY__DOCUMENTATION;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__NAME = DATA_MANAGEMENT_ACTIVITY__NAME;

	/**
   * The feature id for the '<em><b>Suppress Join Failure</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__SUPPRESS_JOIN_FAILURE = DATA_MANAGEMENT_ACTIVITY__SUPPRESS_JOIN_FAILURE;

	/**
   * The feature id for the '<em><b>Targets</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__TARGETS = DATA_MANAGEMENT_ACTIVITY__TARGETS;

	/**
   * The feature id for the '<em><b>Sources</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__SOURCES = DATA_MANAGEMENT_ACTIVITY__SOURCES;

	/**
   * The feature id for the '<em><b>Ds Address</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS;

	/**
   * The feature id for the '<em><b>Ds Type</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__DS_TYPE = DATA_MANAGEMENT_ACTIVITY__DS_TYPE;

	/**
   * The feature id for the '<em><b>Ds Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__DS_KIND = DATA_MANAGEMENT_ACTIVITY__DS_KIND;

	/**
   * The feature id for the '<em><b>Ds Statement</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__DS_STATEMENT = DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT;

	/**
   * The feature id for the '<em><b>Ds Language</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE;

	/**
   * The feature id for the '<em><b>Target Ds Address</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__TARGET_DS_ADDRESS = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 0;

	/**
   * The feature id for the '<em><b>Target Ds Type</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__TARGET_DS_TYPE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 1;

	/**
   * The feature id for the '<em><b>Target Ds Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__TARGET_DS_KIND = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 2;

	/**
   * The feature id for the '<em><b>Target Ds Language</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__TARGET_DS_LANGUAGE = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 3;

	/**
   * The feature id for the '<em><b>Target Ds Container</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY__TARGET_DS_CONTAINER = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 4;

	/**
   * The number of structural features of the '<em>Transfer Activity</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int TRANSFER_ACTIVITY_FEATURE_COUNT = DATA_MANAGEMENT_ACTIVITY_FEATURE_COUNT + 5;

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
   * Returns the meta object for class '{@link org.eclipse.bpel.simpl.model.IssueActivity <em>Issue Activity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Issue Activity</em>'.
   * @see org.eclipse.bpel.simpl.model.IssueActivity
   * @generated
   */
  EClass getIssueActivity();

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
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity#getQueryTarget <em>Query Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Query Target</em>'.
   * @see org.eclipse.bpel.simpl.model.WriteDataBackActivity#getQueryTarget()
   * @see #getWriteDataBackActivity()
   * @generated
   */
  EAttribute getWriteDataBackActivity_QueryTarget();

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
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsAddress <em>Target Ds Address</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Address</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsAddress()
   * @see #getTransferActivity()
   * @generated
   */
	EAttribute getTransferActivity_TargetDsAddress();

	/**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsType <em>Target Ds Type</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Type</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsType()
   * @see #getTransferActivity()
   * @generated
   */
	EAttribute getTransferActivity_TargetDsType();

	/**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsKind <em>Target Ds Kind</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Kind</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsKind()
   * @see #getTransferActivity()
   * @generated
   */
	EAttribute getTransferActivity_TargetDsKind();

	/**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsLanguage <em>Target Ds Language</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Language</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsLanguage()
   * @see #getTransferActivity()
   * @generated
   */
	EAttribute getTransferActivity_TargetDsLanguage();

	/**
   * Returns the meta object for the attribute '{@link org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsContainer <em>Target Ds Container</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Ds Container</em>'.
   * @see org.eclipse.bpel.simpl.model.TransferActivity#getTargetDsContainer()
   * @see #getTransferActivity()
   * @generated
   */
	EAttribute getTransferActivity_TargetDsContainer();

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
     * The meta object literal for the '{@link org.eclipse.bpel.simpl.model.impl.IssueActivityImpl <em>Issue Activity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.bpel.simpl.model.impl.IssueActivityImpl
     * @see org.eclipse.bpel.simpl.model.impl.ModelPackageImpl#getIssueActivity()
     * @generated
     */
    EClass ISSUE_ACTIVITY = eINSTANCE.getIssueActivity();

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
     * The meta object literal for the '<em><b>Query Target</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WRITE_DATA_BACK_ACTIVITY__QUERY_TARGET = eINSTANCE.getWriteDataBackActivity_QueryTarget();

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
     * The meta object literal for the '<em><b>Target Ds Address</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TRANSFER_ACTIVITY__TARGET_DS_ADDRESS = eINSTANCE.getTransferActivity_TargetDsAddress();

		/**
     * The meta object literal for the '<em><b>Target Ds Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TRANSFER_ACTIVITY__TARGET_DS_TYPE = eINSTANCE.getTransferActivity_TargetDsType();

		/**
     * The meta object literal for the '<em><b>Target Ds Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TRANSFER_ACTIVITY__TARGET_DS_KIND = eINSTANCE.getTransferActivity_TargetDsKind();

		/**
     * The meta object literal for the '<em><b>Target Ds Language</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TRANSFER_ACTIVITY__TARGET_DS_LANGUAGE = eINSTANCE.getTransferActivity_TargetDsLanguage();

		/**
     * The meta object literal for the '<em><b>Target Ds Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		EAttribute TRANSFER_ACTIVITY__TARGET_DS_CONTAINER = eINSTANCE.getTransferActivity_TargetDsContainer();

	}

} //ModelPackage
