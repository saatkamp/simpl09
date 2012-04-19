/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: ModelSwitch.java 1963 2011-10-26 11:22:17Z michael.schneidt@arcor.de $ <br>
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
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
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
      case ModelPackage.QUERY_DATA_ACTIVITY: {
        QueryDataActivity queryDataActivity = (QueryDataActivity)theEObject;
        T result = caseQueryDataActivity(queryDataActivity);
        if (result == null) result = caseDataManagementActivity(queryDataActivity);
        if (result == null) result = caseExtensionActivity(queryDataActivity);
        if (result == null) result = caseActivity(queryDataActivity);
        if (result == null) result = caseBPEL_ExtensibleElement(queryDataActivity);
        if (result == null) result = caseExtensibleElement(queryDataActivity);
        if (result == null) result = caseWSDLElement(queryDataActivity);
        if (result == null) result = caseIElementExtensible(queryDataActivity);
        if (result == null) result = caseIAttributeExtensible(queryDataActivity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.ISSUE_COMMAND_ACTIVITY: {
        IssueCommandActivity issueCommandActivity = (IssueCommandActivity)theEObject;
        T result = caseIssueCommandActivity(issueCommandActivity);
        if (result == null) result = caseDataManagementActivity(issueCommandActivity);
        if (result == null) result = caseExtensionActivity(issueCommandActivity);
        if (result == null) result = caseActivity(issueCommandActivity);
        if (result == null) result = caseBPEL_ExtensibleElement(issueCommandActivity);
        if (result == null) result = caseExtensibleElement(issueCommandActivity);
        if (result == null) result = caseWSDLElement(issueCommandActivity);
        if (result == null) result = caseIElementExtensible(issueCommandActivity);
        if (result == null) result = caseIAttributeExtensible(issueCommandActivity);
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
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY: {
        WriteDataBackActivity writeDataBackActivity = (WriteDataBackActivity)theEObject;
        T result = caseWriteDataBackActivity(writeDataBackActivity);
        if (result == null) result = caseDataManagementActivity(writeDataBackActivity);
        if (result == null) result = caseExtensionActivity(writeDataBackActivity);
        if (result == null) result = caseActivity(writeDataBackActivity);
        if (result == null) result = caseBPEL_ExtensibleElement(writeDataBackActivity);
        if (result == null) result = caseExtensibleElement(writeDataBackActivity);
        if (result == null) result = caseWSDLElement(writeDataBackActivity);
        if (result == null) result = caseIElementExtensible(writeDataBackActivity);
        if (result == null) result = caseIAttributeExtensible(writeDataBackActivity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.TRANSFER_DATA_ACTIVITY: {
        TransferDataActivity transferDataActivity = (TransferDataActivity)theEObject;
        T result = caseTransferDataActivity(transferDataActivity);
        if (result == null) result = caseDataManagementActivity(transferDataActivity);
        if (result == null) result = caseExtensionActivity(transferDataActivity);
        if (result == null) result = caseActivity(transferDataActivity);
        if (result == null) result = caseBPEL_ExtensibleElement(transferDataActivity);
        if (result == null) result = caseExtensibleElement(transferDataActivity);
        if (result == null) result = caseWSDLElement(transferDataActivity);
        if (result == null) result = caseIElementExtensible(transferDataActivity);
        if (result == null) result = caseIAttributeExtensible(transferDataActivity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.DATA_MANAGEMENT_PATTERN: {
        DataManagementPattern dataManagementPattern = (DataManagementPattern)theEObject;
        T result = caseDataManagementPattern(dataManagementPattern);
        if (result == null) result = caseExtensionActivity(dataManagementPattern);
        if (result == null) result = caseActivity(dataManagementPattern);
        if (result == null) result = caseBPEL_ExtensibleElement(dataManagementPattern);
        if (result == null) result = caseExtensibleElement(dataManagementPattern);
        if (result == null) result = caseWSDLElement(dataManagementPattern);
        if (result == null) result = caseIElementExtensible(dataManagementPattern);
        if (result == null) result = caseIAttributeExtensible(dataManagementPattern);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.CONTAINER_TO_CONTAINER_PATTERN: {
        ContainerToContainerPattern containerToContainerPattern = (ContainerToContainerPattern)theEObject;
        T result = caseContainerToContainerPattern(containerToContainerPattern);
        if (result == null) result = caseDataManagementPattern(containerToContainerPattern);
        if (result == null) result = caseExtensionActivity(containerToContainerPattern);
        if (result == null) result = caseActivity(containerToContainerPattern);
        if (result == null) result = caseBPEL_ExtensibleElement(containerToContainerPattern);
        if (result == null) result = caseExtensibleElement(containerToContainerPattern);
        if (result == null) result = caseWSDLElement(containerToContainerPattern);
        if (result == null) result = caseIElementExtensible(containerToContainerPattern);
        if (result == null) result = caseIAttributeExtensible(containerToContainerPattern);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.DATA_ITERATION_PATTERN: {
        DataIterationPattern dataIterationPattern = (DataIterationPattern)theEObject;
        T result = caseDataIterationPattern(dataIterationPattern);
        if (result == null) result = caseDataManagementPattern(dataIterationPattern);
        if (result == null) result = caseExtensionActivity(dataIterationPattern);
        if (result == null) result = caseActivity(dataIterationPattern);
        if (result == null) result = caseBPEL_ExtensibleElement(dataIterationPattern);
        if (result == null) result = caseExtensibleElement(dataIterationPattern);
        if (result == null) result = caseWSDLElement(dataIterationPattern);
        if (result == null) result = caseIElementExtensible(dataIterationPattern);
        if (result == null) result = caseIAttributeExtensible(dataIterationPattern);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.DATA_FORMAT_CONVERSION_PATTERN: {
        DataFormatConversionPattern dataFormatConversionPattern = (DataFormatConversionPattern)theEObject;
        T result = caseDataFormatConversionPattern(dataFormatConversionPattern);
        if (result == null) result = caseDataManagementPattern(dataFormatConversionPattern);
        if (result == null) result = caseExtensionActivity(dataFormatConversionPattern);
        if (result == null) result = caseActivity(dataFormatConversionPattern);
        if (result == null) result = caseBPEL_ExtensibleElement(dataFormatConversionPattern);
        if (result == null) result = caseExtensibleElement(dataFormatConversionPattern);
        if (result == null) result = caseWSDLElement(dataFormatConversionPattern);
        if (result == null) result = caseIElementExtensible(dataFormatConversionPattern);
        if (result == null) result = caseIAttributeExtensible(dataFormatConversionPattern);
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
   * Returns the result of interpreting the object as an instance of '<em>Query Data Activity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Query Data Activity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseQueryDataActivity(QueryDataActivity object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Issue Command Activity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Issue Command Activity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIssueCommandActivity(IssueCommandActivity object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Write Data Back Activity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Write Data Back Activity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWriteDataBackActivity(WriteDataBackActivity object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Transfer Data Activity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Transfer Data Activity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTransferDataActivity(TransferDataActivity object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Management Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Management Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataManagementPattern(DataManagementPattern object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Container To Container Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Container To Container Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseContainerToContainerPattern(ContainerToContainerPattern object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Iteration Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Iteration Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataIterationPattern(DataIterationPattern object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Format Conversion Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Format Conversion Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataFormatConversionPattern(DataFormatConversionPattern object) {
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
