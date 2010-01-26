/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.simpl.model.*;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	public QueryActivity createQueryActivity() {
		QueryActivityImpl queryActivity = new QueryActivityImpl();
		return queryActivity;
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
	public UpdateActivity createUpdateActivity() {
		UpdateActivityImpl updateActivity = new UpdateActivityImpl();
		return updateActivity;
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
	public DropActivity createDropActivity() {
		DropActivityImpl dropActivity = new DropActivityImpl();
		return dropActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallActivity createCallActivity() {
		CallActivityImpl callActivity = new CallActivityImpl();
		return callActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RetrieveDataActivity createRetrieveDataActivity() {
		RetrieveDataActivityImpl retrieveDataActivity = new RetrieveDataActivityImpl();
		return retrieveDataActivity;
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