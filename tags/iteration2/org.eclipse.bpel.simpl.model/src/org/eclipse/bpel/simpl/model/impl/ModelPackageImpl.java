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
package org.eclipse.bpel.simpl.model.impl;

import javax.xml.namespace.QName;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.extensions.BPELExtensionRegistry;
import org.eclipse.bpel.model.messageproperties.MessagepropertiesPackage;
import org.eclipse.bpel.model.partnerlinktype.PartnerlinktypePackage;
import org.eclipse.bpel.simpl.model.CallActivity;
import org.eclipse.bpel.simpl.model.CreateActivity;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.DeleteActivity;
import org.eclipse.bpel.simpl.model.DropActivity;
import org.eclipse.bpel.simpl.model.InsertActivity;
import org.eclipse.bpel.simpl.model.ModelFactory;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.model.ReferenceType;
import org.eclipse.bpel.simpl.model.ReferenceVariable;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.UpdateActivity;
import org.eclipse.bpel.simpl.model.util.DataManagementActivityDeserializer;
import org.eclipse.bpel.simpl.model.util.DataManagementActivitySerializer;
import org.eclipse.bpel.simpl.model.util.DataManagementConstants;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.xsd.XSDPackage;

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
	private EClass queryActivityEClass = null;

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
	private EClass updateActivityEClass = null;

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
	private EClass dropActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass retrieveDataActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum referenceTypeEEnum = null;

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

		// QueryActivity
		String name = QueryActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_QUERY_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// InsertActivity
		name = InsertActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_INSERT_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// UpdateActivity
		name = UpdateActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_UPDATE_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// DeleteActivity
		name = DeleteActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_DELETE_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// CreateActivity
		name = CreateActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_CREATE_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// DropActivity
		name = DropActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_DROP_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// CallActivity
		name = CallActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_CALL_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);
		
		// RetrieveDataActivity
		name = RetrieveDataActivity.class.getSimpleName();
		extensionRegistry.registerActivityDeserializer(new QName(ModelPackage.eNS_URI,
				DataManagementConstants.ND_RETRIEVE_DATA_ACTIVITY), deserializer);
		extensionRegistry.registerActivitySerializer(new QName(ModelPackage.eNS_URI, name),
				serializer);

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the data management activity_ ds statement
	 * @generated
	 */
	public EAttribute getDataManagementActivity_DsStatement() {
		return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the data management activity_ ds language
	 * @generated
	 */
	public EAttribute getDataManagementActivity_DsLanguage() {
		return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the data management activity_ ds kind
	 * @generated
	 */
	public EAttribute getDataManagementActivity_DsKind() {
		return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the data management activity_ ds type
	 * @generated
	 */
	public EAttribute getDataManagementActivity_DsType() {
		return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the data management activity_ ds address
	 * @generated
	 */
	public EAttribute getDataManagementActivity_DsAddress() {
		return (EAttribute)dataManagementActivityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the query activity
	 * @generated
	 */
	public EClass getQueryActivity() {
		return queryActivityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the query activity_ query target
	 * @generated
	 */
	public EAttribute getQueryActivity_QueryTarget() {
		return (EAttribute)queryActivityEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the insert activity
	 * @generated
	 */
	public EClass getInsertActivity() {
		return insertActivityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the update activity
	 * @generated
	 */
	public EClass getUpdateActivity() {
		return updateActivityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the delete activity
	 * @generated
	 */
	public EClass getDeleteActivity() {
		return deleteActivityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the creates the activity
	 * @generated
	 */
	public EClass getCreateActivity() {
		return createActivityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the drop activity
	 * @generated
	 */
	public EClass getDropActivity() {
		return dropActivityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the call activity
	 * @generated
	 */
	public EClass getCallActivity() {
		return callActivityEClass;
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the retrieve data activity_ data variable
	 * @generated
	 */
	public EReference getRetrieveDataActivity_DataVariable() {
		return (EReference)retrieveDataActivityEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the reference variable
	 * @generated
	 */
	public EClass getReferenceVariable() {
		return referenceVariableEClass;
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the reference variable_ value type
	 * @generated
	 */
	public EReference getReferenceVariable_ValueType() {
		return (EReference)referenceVariableEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the reference variable_ reference type
	 * @generated
	 */
	public EAttribute getReferenceVariable_ReferenceType() {
		return (EAttribute)referenceVariableEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the reference variable_ period
	 * @generated
	 */
	public EAttribute getReferenceVariable_Period() {
		return (EAttribute)referenceVariableEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the reference variable_ external
	 * @generated
	 */
	public EReference getReferenceVariable_External() {
		return (EReference)referenceVariableEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the reference type
	 * @generated
	 */
	public EEnum getReferenceType() {
		return referenceTypeEEnum;
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
		createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS);
		createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__DS_TYPE);
		createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__DS_KIND);
		createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT);
		createEAttribute(dataManagementActivityEClass, DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE);

		queryActivityEClass = createEClass(QUERY_ACTIVITY);
		createEAttribute(queryActivityEClass, QUERY_ACTIVITY__QUERY_TARGET);

		insertActivityEClass = createEClass(INSERT_ACTIVITY);

		updateActivityEClass = createEClass(UPDATE_ACTIVITY);

		deleteActivityEClass = createEClass(DELETE_ACTIVITY);

		createActivityEClass = createEClass(CREATE_ACTIVITY);

		dropActivityEClass = createEClass(DROP_ACTIVITY);

		callActivityEClass = createEClass(CALL_ACTIVITY);

		retrieveDataActivityEClass = createEClass(RETRIEVE_DATA_ACTIVITY);
		createEReference(retrieveDataActivityEClass, RETRIEVE_DATA_ACTIVITY__DATA_VARIABLE);

		referenceVariableEClass = createEClass(REFERENCE_VARIABLE);
		createEReference(referenceVariableEClass, REFERENCE_VARIABLE__VALUE_TYPE);
		createEAttribute(referenceVariableEClass, REFERENCE_VARIABLE__REFERENCE_TYPE);
		createEAttribute(referenceVariableEClass, REFERENCE_VARIABLE__PERIOD);
		createEReference(referenceVariableEClass, REFERENCE_VARIABLE__EXTERNAL);

		// Create enums
		referenceTypeEEnum = createEEnum(REFERENCE_TYPE);
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
		XSDPackage theXSDPackage = (XSDPackage)EPackage.Registry.INSTANCE.getEPackage(XSDPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		dataManagementActivityEClass.getESuperTypes().add(theBPELPackage.getExtensionActivity());
		queryActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
		insertActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
		updateActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
		deleteActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
		createActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
		dropActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
		callActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
		retrieveDataActivityEClass.getESuperTypes().add(this.getDataManagementActivity());
		referenceVariableEClass.getESuperTypes().add(theBPELPackage.getVariable());

		// Initialize classes and features; add operations and parameters
		initEClass(dataManagementActivityEClass, DataManagementActivity.class, "DataManagementActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataManagementActivity_DsAddress(), ecorePackage.getEString(), "dsAddress", "address", 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataManagementActivity_DsType(), ecorePackage.getEString(), "dsType", "type", 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataManagementActivity_DsKind(), ecorePackage.getEString(), "dsKind", "subtype", 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataManagementActivity_DsStatement(), ecorePackage.getEString(), "dsStatement", "statement", 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataManagementActivity_DsLanguage(), ecorePackage.getEString(), "dsLanguage", "language", 0, 1, DataManagementActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(queryActivityEClass, QueryActivity.class, "QueryActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQueryActivity_QueryTarget(), ecorePackage.getEString(), "queryTarget", "target", 0, 1, QueryActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(insertActivityEClass, InsertActivity.class, "InsertActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(updateActivityEClass, UpdateActivity.class, "UpdateActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(deleteActivityEClass, DeleteActivity.class, "DeleteActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(createActivityEClass, CreateActivity.class, "CreateActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dropActivityEClass, DropActivity.class, "DropActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(callActivityEClass, CallActivity.class, "CallActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(retrieveDataActivityEClass, RetrieveDataActivity.class, "RetrieveDataActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRetrieveDataActivity_DataVariable(), theBPELPackage.getVariable(), null, "dataVariable", null, 0, 1, RetrieveDataActivity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceVariableEClass, ReferenceVariable.class, "ReferenceVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceVariable_ValueType(), theXSDPackage.getXSDTypeDefinition(), null, "valueType", null, 0, 1, ReferenceVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceVariable_ReferenceType(), this.getReferenceType(), "referenceType", "", 1, 1, ReferenceVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceVariable_Period(), ecorePackage.getEInt(), "period", null, 0, 1, ReferenceVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferenceVariable_External(), theBPELPackage.getPartnerLink(), null, "external", null, 0, 1, ReferenceVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(referenceTypeEEnum, ReferenceType.class, "ReferenceType");
		addEEnumLiteral(referenceTypeEEnum, ReferenceType.ON_INSTANTIATION);
		addEEnumLiteral(referenceTypeEEnum, ReferenceType.FRESH);

		// Create resource
		createResource(eNS_URI);
	}

} //ModelPackageImpl
