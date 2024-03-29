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
package org.eclipse.bpel.simpl.model.util;

import java.util.List;

import javax.wsdl.extensions.AttributeExtensible;
import javax.wsdl.extensions.ElementExtensible;

import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.ExtensionActivity;
import org.eclipse.bpel.simpl.model.*;
import org.eclipse.bpel.simpl.model.CallActivity;
import org.eclipse.bpel.simpl.model.CreateActivity;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.DeleteActivity;
import org.eclipse.bpel.simpl.model.DropActivity;
import org.eclipse.bpel.simpl.model.InsertActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferActivity;
import org.eclipse.bpel.simpl.model.UpdateActivity;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.wsdl.ExtensibleElement;
import org.eclipse.wst.wsdl.WSDLElement;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see org.eclipse.bpel.simpl.model.ModelPackage
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
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
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
			case ModelPackage.QUERY_ACTIVITY: {
				QueryActivity queryActivity = (QueryActivity)theEObject;
				T result = caseQueryActivity(queryActivity);
				if (result == null) result = caseDataManagementActivity(queryActivity);
				if (result == null) result = caseExtensionActivity(queryActivity);
				if (result == null) result = caseActivity(queryActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(queryActivity);
				if (result == null) result = caseExtensibleElement(queryActivity);
				if (result == null) result = caseWSDLElement(queryActivity);
				if (result == null) result = caseIElementExtensible(queryActivity);
				if (result == null) result = caseIAttributeExtensible(queryActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.INSERT_ACTIVITY: {
				InsertActivity insertActivity = (InsertActivity)theEObject;
				T result = caseInsertActivity(insertActivity);
				if (result == null) result = caseDataManagementActivity(insertActivity);
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
			case ModelPackage.UPDATE_ACTIVITY: {
				UpdateActivity updateActivity = (UpdateActivity)theEObject;
				T result = caseUpdateActivity(updateActivity);
				if (result == null) result = caseDataManagementActivity(updateActivity);
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
			case ModelPackage.DELETE_ACTIVITY: {
				DeleteActivity deleteActivity = (DeleteActivity)theEObject;
				T result = caseDeleteActivity(deleteActivity);
				if (result == null) result = caseDataManagementActivity(deleteActivity);
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
				if (result == null) result = caseDataManagementActivity(createActivity);
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
			case ModelPackage.DROP_ACTIVITY: {
				DropActivity dropActivity = (DropActivity)theEObject;
				T result = caseDropActivity(dropActivity);
				if (result == null) result = caseDataManagementActivity(dropActivity);
				if (result == null) result = caseExtensionActivity(dropActivity);
				if (result == null) result = caseActivity(dropActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(dropActivity);
				if (result == null) result = caseExtensibleElement(dropActivity);
				if (result == null) result = caseWSDLElement(dropActivity);
				if (result == null) result = caseIElementExtensible(dropActivity);
				if (result == null) result = caseIAttributeExtensible(dropActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CALL_ACTIVITY: {
				CallActivity callActivity = (CallActivity)theEObject;
				T result = caseCallActivity(callActivity);
				if (result == null) result = caseDataManagementActivity(callActivity);
				if (result == null) result = caseExtensionActivity(callActivity);
				if (result == null) result = caseActivity(callActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(callActivity);
				if (result == null) result = caseExtensibleElement(callActivity);
				if (result == null) result = caseWSDLElement(callActivity);
				if (result == null) result = caseIElementExtensible(callActivity);
				if (result == null) result = caseIAttributeExtensible(callActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.RETRIEVE_DATA_ACTIVITY: {
				RetrieveDataActivity retrieveDataActivity = (RetrieveDataActivity)theEObject;
				T result = caseRetrieveDataActivity(retrieveDataActivity);
				if (result == null) result = caseDataManagementActivity(retrieveDataActivity);
				if (result == null) result = caseExtensionActivity(retrieveDataActivity);
				if (result == null) result = caseActivity(retrieveDataActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(retrieveDataActivity);
				if (result == null) result = caseExtensibleElement(retrieveDataActivity);
				if (result == null) result = caseWSDLElement(retrieveDataActivity);
				if (result == null) result = caseIElementExtensible(retrieveDataActivity);
				if (result == null) result = caseIAttributeExtensible(retrieveDataActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRANSFER_ACTIVITY: {
				TransferActivity transferActivity = (TransferActivity)theEObject;
				T result = caseTransferActivity(transferActivity);
				if (result == null) result = caseDataManagementActivity(transferActivity);
				if (result == null) result = caseExtensionActivity(transferActivity);
				if (result == null) result = caseActivity(transferActivity);
				if (result == null) result = caseBPEL_ExtensibleElement(transferActivity);
				if (result == null) result = caseExtensibleElement(transferActivity);
				if (result == null) result = caseWSDLElement(transferActivity);
				if (result == null) result = caseIElementExtensible(transferActivity);
				if (result == null) result = caseIAttributeExtensible(transferActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Management Activity</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Management Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataManagementActivity(DataManagementActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query Activity</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQueryActivity(QueryActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Insert Activity</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Insert Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInsertActivity(InsertActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Update Activity</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Update Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUpdateActivity(UpdateActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delete Activity</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
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
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Create Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreateActivity(CreateActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Drop Activity</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Drop Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDropActivity(DropActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Call Activity</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Call Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCallActivity(CallActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Retrieve Data Activity</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Retrieve Data Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRetrieveDataActivity(RetrieveDataActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transfer Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transfer Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransferActivity(TransferActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
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
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
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
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
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
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
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
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
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
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
