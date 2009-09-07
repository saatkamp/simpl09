/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.dmextension.model.impl;

import org.eclipse.bpel.dmextension.model.CallProcedureActivity;
import org.eclipse.bpel.dmextension.model.CreateActivity;
import javax.xml.namespace.QName;

import org.eclipse.bpel.dmextension.model.util.DMExtensionConstants;
import org.eclipse.bpel.dmextension.model.util.DataManagementActivityDeserializer;
import org.eclipse.bpel.dmextension.model.util.DataManagementActivitySerializer;

import org.eclipse.bpel.dmextension.model.DataManagementActivity;
import org.eclipse.bpel.dmextension.model.DeleteActivity;
import org.eclipse.bpel.dmextension.model.InsertActivity;
import org.eclipse.bpel.dmextension.model.ModelFactory;
import org.eclipse.bpel.dmextension.model.ModelPackage;
import org.eclipse.bpel.dmextension.model.RetrieveSetActivity;
import org.eclipse.bpel.dmextension.model.SelectActivity;
import org.eclipse.bpel.dmextension.model.UpdateActivity;
import org.eclipse.bpel.dmextension.model.WriteBackActivity;
import org.eclipse.bpel.model.BPELPackage;

import org.eclipse.bpel.model.extensions.BPELExtensionRegistry;
import org.eclipse.bpel.model.messageproperties.MessagepropertiesPackage;

import org.eclipse.bpel.model.partnerlinktype.PartnerlinktypePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

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
	private EClass updateActivityEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass insertActivityEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selectActivityEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deleteActivityEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass createActivityEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callProcedureActivityEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass retrieveSetActivityEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass writeBackActivityEClass = null;

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
	 * @see org.eclipse.bpel.dmextension.model.ModelPackage#eNS_URI
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
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		
		// register the serializer and deserializer of the Activity
		registerSerializerAndDeserializer();
		
		return theModelPackage;
	}
	
	private static void registerSerializerAndDeserializer() {
		// Initialize BPEL extension registry
		BPELExtensionRegistry extensionRegistry = BPELExtensionRegistry
				.getInstance();
		// Initialize our own serializers and deserializers
		DataManagementActivityDeserializer deserializer = new DataManagementActivityDeserializer();
		DataManagementActivitySerializer serializer = new DataManagementActivitySerializer();
		
		// DataManagementActivity
		String name = DataManagementActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_DATA_MANAGEMENT_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
		
		// CallProcedureActivity
		name = CallProcedureActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_CALL_PROCEDURE_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
		
		// CreateActivity
		name = CreateActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_CREATE_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
		
		// DeleteActivity
		name = DeleteActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_DELETE_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
		
		// InsertActivity
		name = InsertActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_INSERT_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
		
		// RetrieveSetActivity
		name = RetrieveSetActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_RETRIEVE_SET_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
		
		// SelectActivity
		name = SelectActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_SELECT_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
		
		// UpdateActivity
		name = UpdateActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_UPDATE_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
		
		// WriteBackActivity
		name = WriteBackActivity.class.getSimpleName();
		extensionRegistry
				.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
						DMExtensionConstants.ND_WRITE_BACK_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(
				ModelPackage.eNS_URI, name), serializer);
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	public EAttribute getDataManagementActivity_Statement() {
		return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataManagementActivity_Kind() {
		return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUpdateActivity() {
		return updateActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInsertActivity() {
		return insertActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSelectActivity() {
		return selectActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeleteActivity() {
		return deleteActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreateActivity() {
		return createActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCallProcedureActivity() {
		return callProcedureActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRetrieveSetActivity() {
		return retrieveSetActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWriteBackActivity() {
		return writeBackActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__STATEMENT);
		createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__KIND);

		updateActivityEClass = createEClass(UPDATE_ACTIVITY);

		insertActivityEClass = createEClass(INSERT_ACTIVITY);

		selectActivityEClass = createEClass(SELECT_ACTIVITY);

		deleteActivityEClass = createEClass(DELETE_ACTIVITY);

		createActivityEClass = createEClass(CREATE_ACTIVITY);

		callProcedureActivityEClass = createEClass(CALL_PROCEDURE_ACTIVITY);

		retrieveSetActivityEClass = createEClass(RETRIEVE_SET_ACTIVITY);

		writeBackActivityEClass = createEClass(WRITE_BACK_ACTIVITY);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		dataManagementActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		updateActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		insertActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		selectActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		deleteActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		createActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		callProcedureActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		retrieveSetActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		writeBackActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());

		// Initialize classes and features; add operations and parameters
		initEClass(dataManagementActivityEClass, DataManagementActivity.class, "DataManagementActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataManagementActivity_Statement(), theEcorePackage.getEString(), "statement", null, 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataManagementActivity_Kind(), theEcorePackage.getEString(), "kind", null, 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(updateActivityEClass, UpdateActivity.class, "UpdateActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(insertActivityEClass, InsertActivity.class, "InsertActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(selectActivityEClass, SelectActivity.class, "SelectActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(deleteActivityEClass, DeleteActivity.class, "DeleteActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(createActivityEClass, CreateActivity.class, "CreateActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(callProcedureActivityEClass, CallProcedureActivity.class, "CallProcedureActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(retrieveSetActivityEClass, RetrieveSetActivity.class, "RetrieveSetActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(writeBackActivityEClass, WriteBackActivity.class, "WriteBackActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //ModelPackageImpl
