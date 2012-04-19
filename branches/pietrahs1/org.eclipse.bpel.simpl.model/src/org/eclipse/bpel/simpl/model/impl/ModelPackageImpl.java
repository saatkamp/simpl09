/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: ModelPackageImpl.java 1807 2011-05-12 09:27:15Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model.impl;

import javax.xml.namespace.QName;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.extensions.BPELExtensionRegistry;
import org.eclipse.bpel.model.messageproperties.MessagepropertiesPackage;
import org.eclipse.bpel.model.partnerlinktype.PartnerlinktypePackage;
import org.eclipse.bpel.simpl.model.ContainerToContainerPattern;
import org.eclipse.bpel.simpl.model.DataFormatConversionPattern;
import org.eclipse.bpel.simpl.model.DataIterationPattern;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.DataManagementPattern;
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.ModelFactory;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.simpl.model.util.DataManagementActivityDeserializer;
import org.eclipse.bpel.simpl.model.util.DataManagementActivitySerializer;
import org.eclipse.bpel.simpl.model.util.DataManagementConstants;
import org.eclipse.bpel.simpl.model.util.DataManagementPatternDeserializer;
import org.eclipse.bpel.simpl.model.util.DataManagementPatternSerializer;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass dataManagementActivityEClass = null;

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass queryDataActivityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass issueCommandActivityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass writeDataBackActivityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass transferDataActivityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dataManagementPatternEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass containerToContainerPatternEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dataIterationPatternEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dataFormatConversionPatternEClass = null;

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass retrieveDataActivityEClass = null;

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
   * @see org.eclipse.bpel.simpl.model.ModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private ModelPackageImpl() {
    super(eNS_URI, ModelFactory.eINSTANCE);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link ModelPackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead,
	 * they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the model package
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @customized
	 */
	public static ModelPackage init() {
		if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Obtain or create and register package
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BPELPackage.eINSTANCE.eClass();
		PartnerlinktypePackage.eINSTANCE.eClass();
		MessagepropertiesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theModelPackage.createPackageContents();

		// Initialize created meta-data
		theModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
		
		// Register the serializer and deserializer
		registerSerializerAndDeserializer();
		
		return theModelPackage;
	}

	
	private static void registerSerializerAndDeserializer() {
		// Initialize BPEL extension registry
		BPELExtensionRegistry extensionRegistry = BPELExtensionRegistry.getInstance();

		// Initialize our own serializers and deserializers
		DataManagementActivityDeserializer deserializer = new DataManagementActivityDeserializer();
		DataManagementActivitySerializer serializer = new DataManagementActivitySerializer();
		
		DataManagementPatternDeserializer patternDeserializer = new DataManagementPatternDeserializer();
		DataManagementPatternSerializer patternSerializer = new DataManagementPatternSerializer();

		// QueryDataActivity
		String name = QueryDataActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_QUERY_DATA_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// IssueCommandActivity
		name = IssueCommandActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_ISSUE_COMMAND_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// RetrieveDataActivity
		name = RetrieveDataActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_RETRIEVE_DATA_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
    
    // WriteDataBackActivity
    name = WriteDataBackActivity.class.getSimpleName();
    extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
        DataManagementConstants.ND_WRITE_DATA_BACK_ACTIVITY), deserializer);
    extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
        serializer);
    
		// TransferDataActivity
		name = TransferDataActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_TRANSFER_DATA_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
    // ContainerToContainer
    name = ContainerToContainerPattern.class.getSimpleName();
    extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
        DataManagementConstants.ND_CONTAINER_TO_CONTAINER_PATTERN), patternDeserializer);
    extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
        patternSerializer);
    
    // DataFormatConversionPattern
    name = DataFormatConversionPattern.class.getSimpleName();
    extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
        DataManagementConstants.ND_DATA_FORMAT_CONVERSION_PATTERN), patternDeserializer);
    extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
        patternSerializer);
    
    // DataIterationPatterm
    name = DataIterationPattern.class.getSimpleName();
    extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
        DataManagementConstants.ND_DATA_ITERATION_PATTERN), patternDeserializer);
    extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
        patternSerializer);
	}
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the data management activity
	 * @generated
	 */
	public EClass getDataManagementActivity() {
    return dataManagementActivityEClass;
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDataManagementActivity_DataResource() {
    return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(0);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDataManagementActivity_DmCommand() {
    return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(1);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getQueryDataActivity() {
    return queryDataActivityEClass;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQueryDataActivity_TargetContainer() {
    return (EAttribute)queryDataActivityEClass.getEStructuralFeatures().get(0);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIssueCommandActivity() {
    return issueCommandActivityEClass;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWriteDataBackActivity() {
    return writeDataBackActivityEClass;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWriteDataBackActivity_TargetContainer() {
    return (EAttribute)writeDataBackActivityEClass.getEStructuralFeatures().get(0);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWriteDataBackActivity_DataVariable() {
    return (EReference)writeDataBackActivityEClass.getEStructuralFeatures().get(1);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTransferDataActivity() {
    return transferDataActivityEClass;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTransferDataActivity_DataSource() {
    return (EAttribute)transferDataActivityEClass.getEStructuralFeatures().get(0);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTransferDataActivity_DataSourceCommand() {
    return (EAttribute)transferDataActivityEClass.getEStructuralFeatures().get(1);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTransferDataActivity_DataSink() {
    return (EAttribute)transferDataActivityEClass.getEStructuralFeatures().get(2);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTransferDataActivity_DataSinkContainer() {
    return (EAttribute)transferDataActivityEClass.getEStructuralFeatures().get(3);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDataManagementPattern() {
    return dataManagementPatternEClass;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getContainerToContainerPattern() {
    return containerToContainerPatternEClass;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getContainerToContainerPattern_SourceContainer() {
    return (EAttribute)containerToContainerPatternEClass.getEStructuralFeatures().get(0);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getContainerToContainerPattern_TargetContainer() {
    return (EAttribute)containerToContainerPatternEClass.getEStructuralFeatures().get(1);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDataIterationPattern() {
    return dataIterationPatternEClass;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDataIterationPattern_ContainerReferenceList() {
    return (EAttribute)dataIterationPatternEClass.getEStructuralFeatures().get(0);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDataIterationPattern_CurrentContainer() {
    return (EAttribute)dataIterationPatternEClass.getEStructuralFeatures().get(1);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataIterationPattern_Activity() {
    return (EReference)dataIterationPatternEClass.getEStructuralFeatures().get(2);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDataFormatConversionPattern() {
    return dataFormatConversionPatternEClass;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDataFormatConversionPattern_SourceContainer() {
    return (EAttribute)dataFormatConversionPatternEClass.getEStructuralFeatures().get(0);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDataFormatConversionPattern_TargetContainer() {
    return (EAttribute)dataFormatConversionPatternEClass.getEStructuralFeatures().get(1);
  }


  /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the retrieve data activity
	 * @generated
	 */
	public EClass getRetrieveDataActivity() {
    return retrieveDataActivityEClass;
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRetrieveDataActivity_TargetVariable() {
    return (EReference)retrieveDataActivityEClass.getEStructuralFeatures().get(0);
  }


  /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the model factory
	 * @generated
	 */
	public ModelFactory getModelFactory() {
    return (ModelFactory)getEFactoryInstance();
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
    dataManagementActivityEClass = createEClass(DATA_MANAGEMENT_ACTIVITY);
    createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__DATA_RESOURCE);
    createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__DM_COMMAND);

    queryDataActivityEClass = createEClass(QUERY_DATA_ACTIVITY);
    createEAttribute(queryDataActivityEClass, QUERY_DATA_ACTIVITY__TARGET_CONTAINER);

    issueCommandActivityEClass = createEClass(ISSUE_COMMAND_ACTIVITY);

    retrieveDataActivityEClass = createEClass(RETRIEVE_DATA_ACTIVITY);
    createEReference(retrieveDataActivityEClass, RETRIEVE_DATA_ACTIVITY__TARGET_VARIABLE);

    writeDataBackActivityEClass = createEClass(WRITE_DATA_BACK_ACTIVITY);
    createEAttribute(writeDataBackActivityEClass, WRITE_DATA_BACK_ACTIVITY__TARGET_CONTAINER);
    createEReference(writeDataBackActivityEClass, WRITE_DATA_BACK_ACTIVITY__DATA_VARIABLE);

    transferDataActivityEClass = createEClass(TRANSFER_DATA_ACTIVITY);
    createEAttribute(transferDataActivityEClass, TRANSFER_DATA_ACTIVITY__DATA_SOURCE);
    createEAttribute(transferDataActivityEClass, TRANSFER_DATA_ACTIVITY__DATA_SOURCE_COMMAND);
    createEAttribute(transferDataActivityEClass, TRANSFER_DATA_ACTIVITY__DATA_SINK);
    createEAttribute(transferDataActivityEClass, TRANSFER_DATA_ACTIVITY__DATA_SINK_CONTAINER);

    dataManagementPatternEClass = createEClass(DATA_MANAGEMENT_PATTERN);

    containerToContainerPatternEClass = createEClass(CONTAINER_TO_CONTAINER_PATTERN);
    createEAttribute(containerToContainerPatternEClass, CONTAINER_TO_CONTAINER_PATTERN__SOURCE_CONTAINER);
    createEAttribute(containerToContainerPatternEClass, CONTAINER_TO_CONTAINER_PATTERN__TARGET_CONTAINER);

    dataIterationPatternEClass = createEClass(DATA_ITERATION_PATTERN);
    createEAttribute(dataIterationPatternEClass, DATA_ITERATION_PATTERN__CONTAINER_REFERENCE_LIST);
    createEAttribute(dataIterationPatternEClass, DATA_ITERATION_PATTERN__CURRENT_CONTAINER);
    createEReference(dataIterationPatternEClass, DATA_ITERATION_PATTERN__ACTIVITY);

    dataFormatConversionPatternEClass = createEClass(DATA_FORMAT_CONVERSION_PATTERN);
    createEAttribute(dataFormatConversionPatternEClass, DATA_FORMAT_CONVERSION_PATTERN__SOURCE_CONTAINER);
    createEAttribute(dataFormatConversionPatternEClass, DATA_FORMAT_CONVERSION_PATTERN__TARGET_CONTAINER);
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
    BPELPackage theBPELPackage = (BPELPackage)EPackage.Registry.INSTANCE.getEPackage(BPELPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    dataManagementActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
    queryDataActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
    issueCommandActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
    retrieveDataActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
    writeDataBackActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
    transferDataActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
    dataManagementPatternEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
    containerToContainerPatternEClass.getESuperTypes().add(this.getDataManagementPattern());
    dataIterationPatternEClass.getESuperTypes().add(this.getDataManagementPattern());
    dataFormatConversionPatternEClass.getESuperTypes().add(this.getDataManagementPattern());

    // Initialize classes and features; add operations and parameters
    initEClass(dataManagementActivityEClass, DataManagementActivity.class, "DataManagementActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDataManagementActivity_DataResource(), ecorePackage.getEString(), "dataResource", null, 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDataManagementActivity_DmCommand(), ecorePackage.getEString(), "dmCommand", null, 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(queryDataActivityEClass, QueryDataActivity.class, "QueryDataActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getQueryDataActivity_TargetContainer(), ecorePackage.getEString(), "targetContainer", "", 1, 1, QueryDataActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(issueCommandActivityEClass, IssueCommandActivity.class, "IssueCommandActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(retrieveDataActivityEClass, RetrieveDataActivity.class, "RetrieveDataActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRetrieveDataActivity_TargetVariable(), theBPELPackage.getVariable(), null, "targetVariable", null, 1, 1, RetrieveDataActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(writeDataBackActivityEClass, WriteDataBackActivity.class, "WriteDataBackActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getWriteDataBackActivity_TargetContainer(), ecorePackage.getEString(), "targetContainer", "", 1, 1, WriteDataBackActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWriteDataBackActivity_DataVariable(), theBPELPackage.getVariable(), null, "dataVariable", null, 1, 1, WriteDataBackActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(transferDataActivityEClass, TransferDataActivity.class, "TransferDataActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTransferDataActivity_DataSource(), ecorePackage.getEString(), "dataSource", "", 1, 1, TransferDataActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTransferDataActivity_DataSourceCommand(), ecorePackage.getEString(), "dataSourceCommand", "", 1, 1, TransferDataActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTransferDataActivity_DataSink(), ecorePackage.getEString(), "dataSink", "", 1, 1, TransferDataActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTransferDataActivity_DataSinkContainer(), ecorePackage.getEString(), "dataSinkContainer", "", 1, 1, TransferDataActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dataManagementPatternEClass, DataManagementPattern.class, "DataManagementPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(containerToContainerPatternEClass, ContainerToContainerPattern.class, "ContainerToContainerPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getContainerToContainerPattern_SourceContainer(), ecorePackage.getEString(), "sourceContainer", "", 1, 1, ContainerToContainerPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getContainerToContainerPattern_TargetContainer(), ecorePackage.getEString(), "targetContainer", "", 1, 1, ContainerToContainerPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dataIterationPatternEClass, DataIterationPattern.class, "DataIterationPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDataIterationPattern_ContainerReferenceList(), ecorePackage.getEString(), "containerReferenceList", "", 1, 1, DataIterationPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDataIterationPattern_CurrentContainer(), ecorePackage.getEString(), "currentContainer", "", 1, 1, DataIterationPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDataIterationPattern_Activity(), theBPELPackage.getActivity(), null, "activity", null, 1, 1, DataIterationPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dataFormatConversionPatternEClass, DataFormatConversionPattern.class, "DataFormatConversionPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDataFormatConversionPattern_SourceContainer(), ecorePackage.getEString(), "sourceContainer", "", 1, 1, DataFormatConversionPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDataFormatConversionPattern_TargetContainer(), ecorePackage.getEString(), "targetContainer", "", 1, 1, DataFormatConversionPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ModelPackageImpl
