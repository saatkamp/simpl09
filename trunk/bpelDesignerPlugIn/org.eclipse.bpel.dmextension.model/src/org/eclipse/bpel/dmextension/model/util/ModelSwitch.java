/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.dmextension.model.util;

import java.util.List;

import javax.wsdl.extensions.AttributeExtensible;
import javax.wsdl.extensions.ElementExtensible;


import org.eclipse.bpel.dmextension.model.*;
import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.ExtensionActivity;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.wst.wsdl.ExtensibleElement;
import org.eclipse.wst.wsdl.WSDLElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpel.dmextension.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY: {
				DataManagementActivity dataManagementActivity = (DataManagementActivity)theEObject;
				T result = caseDataManagementActivity(dataManagementActivity);
				if (result == null) result = caseExtensionActivity(dataManagementActivity);
				if (result == null) result = caseActivity(dataManagementActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(dataManagementActivity);
				if (result == null) result = caseExtensibleElement(dataManagementActivity);
				if (result == null) result = caseWSDLElement(dataManagementActivity);
				if (result == null) result = caseIElementExtensible(dataManagementActivity);
				if (result == null) result = caseIAttributeExtensible(dataManagementActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.UPDATE_ACTIVITY: {
				UpdateActivity updateActivity = (UpdateActivity)theEObject;
				T result = caseUpdateActivity(updateActivity);
				if (result == null) result = caseExtensionActivity(updateActivity);
				if (result == null) result = caseActivity(updateActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(updateActivity);
				if (result == null) result = caseExtensibleElement(updateActivity);
				if (result == null) result = caseWSDLElement(updateActivity);
				if (result == null) result = caseIElementExtensible(updateActivity);
				if (result == null) result = caseIAttributeExtensible(updateActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.INSERT_ACTIVITY: {
				InsertActivity insertActivity = (InsertActivity)theEObject;
				T result = caseInsertActivity(insertActivity);
				if (result == null) result = caseExtensionActivity(insertActivity);
				if (result == null) result = caseActivity(insertActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(insertActivity);
				if (result == null) result = caseExtensibleElement(insertActivity);
				if (result == null) result = caseWSDLElement(insertActivity);
				if (result == null) result = caseIElementExtensible(insertActivity);
				if (result == null) result = caseIAttributeExtensible(insertActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SELECT_ACTIVITY: {
				SelectActivity selectActivity = (SelectActivity)theEObject;
				T result = caseSelectActivity(selectActivity);
				if (result == null) result = caseExtensionActivity(selectActivity);
				if (result == null) result = caseActivity(selectActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(selectActivity);
				if (result == null) result = caseExtensibleElement(selectActivity);
				if (result == null) result = caseWSDLElement(selectActivity);
				if (result == null) result = caseIElementExtensible(selectActivity);
				if (result == null) result = caseIAttributeExtensible(selectActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DELETE_ACTIVITY: {
				DeleteActivity deleteActivity = (DeleteActivity)theEObject;
				T result = caseDeleteActivity(deleteActivity);
				if (result == null) result = caseExtensionActivity(deleteActivity);
				if (result == null) result = caseActivity(deleteActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(deleteActivity);
				if (result == null) result = caseExtensibleElement(deleteActivity);
				if (result == null) result = caseWSDLElement(deleteActivity);
				if (result == null) result = caseIElementExtensible(deleteActivity);
				if (result == null) result = caseIAttributeExtensible(deleteActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CREATE_ACTIVITY: {
				CreateActivity createActivity = (CreateActivity)theEObject;
				T result = caseCreateActivity(createActivity);
				if (result == null) result = caseExtensionActivity(createActivity);
				if (result == null) result = caseActivity(createActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(createActivity);
				if (result == null) result = caseExtensibleElement(createActivity);
				if (result == null) result = caseWSDLElement(createActivity);
				if (result == null) result = caseIElementExtensible(createActivity);
				if (result == null) result = caseIAttributeExtensible(createActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CALL_PROCEDURE_ACTIVITY: {
				CallProcedureActivity callProcedureActivity = (CallProcedureActivity)theEObject;
				T result = caseCallProcedureActivity(callProcedureActivity);
				if (result == null) result = caseExtensionActivity(callProcedureActivity);
				if (result == null) result = caseActivity(callProcedureActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(callProcedureActivity);
				if (result == null) result = caseExtensibleElement(callProcedureActivity);
				if (result == null) result = caseWSDLElement(callProcedureActivity);
				if (result == null) result = caseIElementExtensible(callProcedureActivity);
				if (result == null) result = caseIAttributeExtensible(callProcedureActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.RETRIEVE_SET_ACTIVITY: {
				RetrieveSetActivity retrieveSetActivity = (RetrieveSetActivity)theEObject;
				T result = caseRetrieveSetActivity(retrieveSetActivity);
				if (result == null) result = caseExtensionActivity(retrieveSetActivity);
				if (result == null) result = caseActivity(retrieveSetActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(retrieveSetActivity);
				if (result == null) result = caseExtensibleElement(retrieveSetActivity);
				if (result == null) result = caseWSDLElement(retrieveSetActivity);
				if (result == null) result = caseIElementExtensible(retrieveSetActivity);
				if (result == null) result = caseIAttributeExtensible(retrieveSetActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WRITE_BACK_ACTIVITY: {
				WriteBackActivity writeBackActivity = (WriteBackActivity)theEObject;
				T result = caseWriteBackActivity(writeBackActivity);
				if (result == null) result = caseExtensionActivity(writeBackActivity);
				if (result == null) result = caseActivity(writeBackActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(writeBackActivity);
				if (result == null) result = caseExtensibleElement(writeBackActivity);
				if (result == null) result = caseWSDLElement(writeBackActivity);
				if (result == null) result = caseIElementExtensible(writeBackActivity);
				if (result == null) result = caseIAttributeExtensible(writeBackActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Management Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Management Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataManagementActivity(DataManagementActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Update Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Update Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUpdateActivity(UpdateActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Insert Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Insert Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInsertActivity(InsertActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Select Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Select Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSelectActivity(SelectActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delete Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delete Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeleteActivity(DeleteActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Create Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Create Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreateActivity(CreateActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Call Procedure Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Call Procedure Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCallProcedureActivity(CallProcedureActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Retrieve Set Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Retrieve Set Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRetrieveSetActivity(RetrieveSetActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Write Back Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Write Back Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWriteBackActivity(WriteBackActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWSDLElement(WSDLElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IElement Extensible</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IElement Extensible</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIElementExtensible(ElementExtensible object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IAttribute Extensible</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IAttribute Extensible</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIAttributeExtensible(AttributeExtensible object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensibleElement(ExtensibleElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBPEL_ExtensibleElement(org.eclipse.bpel.model.ExtensibleElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivity(Activity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extension Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extension Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensionActivity(ExtensionActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
