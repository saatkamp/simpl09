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

import org.eclipse.bpel.simpl.model.*;
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.example.org/simpl"); 
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param eClass
	 *            the e class
	 * @return the e object
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY: return createDataManagementActivity();
			case ModelPackage.QUERY_ACTIVITY: return createQueryActivity();
			case ModelPackage.INSERT_ACTIVITY: return createInsertActivity();
			case ModelPackage.UPDATE_ACTIVITY: return createUpdateActivity();
			case ModelPackage.DELETE_ACTIVITY: return createDeleteActivity();
			case ModelPackage.CREATE_ACTIVITY: return createCreateActivity();
			case ModelPackage.DROP_ACTIVITY: return createDropActivity();
			case ModelPackage.CALL_ACTIVITY: return createCallActivity();
			case ModelPackage.RETRIEVE_DATA_ACTIVITY: return createRetrieveDataActivity();
			case ModelPackage.REFERENCE_VARIABLE: return createReferenceVariable();
			case ModelPackage.TRANSFER_ACTIVITY: return createTransferActivity();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param eDataType
	 *            the e data type
	 * @param initialValue
	 *            the initial value
	 * @return the object
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.REFERENCE_TYPE:
				return createReferenceTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param eDataType
	 *            the e data type
	 * @param instanceValue
	 *            the instance value
	 * @return the string
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.REFERENCE_TYPE:
				return convertReferenceTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the data management activity
	 * @generated
	 */
	public DataManagementActivity createDataManagementActivity() {
		DataManagementActivityImpl dataManagementActivity = new DataManagementActivityImpl();
		return dataManagementActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the query activity
	 * @generated
	 */
	public QueryActivity createQueryActivity() {
		QueryActivityImpl queryActivity = new QueryActivityImpl();
		return queryActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the insert activity
	 * @generated
	 */
	public InsertActivity createInsertActivity() {
		InsertActivityImpl insertActivity = new InsertActivityImpl();
		return insertActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the update activity
	 * @generated
	 */
	public UpdateActivity createUpdateActivity() {
		UpdateActivityImpl updateActivity = new UpdateActivityImpl();
		return updateActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the delete activity
	 * @generated
	 */
	public DeleteActivity createDeleteActivity() {
		DeleteActivityImpl deleteActivity = new DeleteActivityImpl();
		return deleteActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the creates the activity
	 * @generated
	 */
	public CreateActivity createCreateActivity() {
		CreateActivityImpl createActivity = new CreateActivityImpl();
		return createActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the drop activity
	 * @generated
	 */
	public DropActivity createDropActivity() {
		DropActivityImpl dropActivity = new DropActivityImpl();
		return dropActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the call activity
	 * @generated
	 */
	public CallActivity createCallActivity() {
		CallActivityImpl callActivity = new CallActivityImpl();
		return callActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the retrieve data activity
	 * @generated
	 */
	public RetrieveDataActivity createRetrieveDataActivity() {
		RetrieveDataActivityImpl retrieveDataActivity = new RetrieveDataActivityImpl();
		return retrieveDataActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the reference variable
	 * @generated
	 */
	public ReferenceVariable createReferenceVariable() {
		ReferenceVariableImpl referenceVariable = new ReferenceVariableImpl();
		return referenceVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransferActivity createTransferActivity() {
		TransferActivityImpl transferActivity = new TransferActivityImpl();
		return transferActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param eDataType
	 *            the e data type
	 * @param initialValue
	 *            the initial value
	 * @return the reference type
	 * @generated
	 */
	public ReferenceType createReferenceTypeFromString(EDataType eDataType, String initialValue) {
		ReferenceType result = ReferenceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param eDataType
	 *            the e data type
	 * @param instanceValue
	 *            the instance value
	 * @return the string
	 * @generated
	 */
	public String convertReferenceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the model package
	 * @generated
	 */
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the package
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} //ModelFactoryImpl
