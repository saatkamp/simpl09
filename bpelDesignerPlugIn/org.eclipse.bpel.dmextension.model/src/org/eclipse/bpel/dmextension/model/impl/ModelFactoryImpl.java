/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.dmextension.model.impl;

import org.eclipse.bpel.dmextension.model.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.example.org/DMextension"); 
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY: return createDataManagementActivity();
			case ModelPackage.UPDATE_ACTIVITY: return createUpdateActivity();
			case ModelPackage.INSERT_ACTIVITY: return createInsertActivity();
			case ModelPackage.SELECT_ACTIVITY: return createSelectActivity();
			case ModelPackage.DELETE_ACTIVITY: return createDeleteActivity();
			case ModelPackage.CREATE_ACTIVITY: return createCreateActivity();
			case ModelPackage.CALL_PROCEDURE_ACTIVITY: return createCallProcedureActivity();
			case ModelPackage.RETRIEVE_SET_ACTIVITY: return createRetrieveSetActivity();
			case ModelPackage.WRITE_BACK_ACTIVITY: return createWriteBackActivity();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataManagementActivity createDataManagementActivity() {
		DataManagementActivityImpl dataManagementActivity = new DataManagementActivityImpl();
		return dataManagementActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UpdateActivity createUpdateActivity() {
		UpdateActivityImpl updateActivity = new UpdateActivityImpl();
		return updateActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InsertActivity createInsertActivity() {
		InsertActivityImpl insertActivity = new InsertActivityImpl();
		return insertActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelectActivity createSelectActivity() {
		SelectActivityImpl selectActivity = new SelectActivityImpl();
		return selectActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeleteActivity createDeleteActivity() {
		DeleteActivityImpl deleteActivity = new DeleteActivityImpl();
		return deleteActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateActivity createCreateActivity() {
		CreateActivityImpl createActivity = new CreateActivityImpl();
		return createActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallProcedureActivity createCallProcedureActivity() {
		CallProcedureActivityImpl callProcedureActivity = new CallProcedureActivityImpl();
		return callProcedureActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RetrieveSetActivity createRetrieveSetActivity() {
		RetrieveSetActivityImpl retrieveSetActivity = new RetrieveSetActivityImpl();
		return retrieveSetActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WriteBackActivity createWriteBackActivity() {
		WriteBackActivityImpl writeBackActivity = new WriteBackActivityImpl();
		return writeBackActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} //ModelFactoryImpl
