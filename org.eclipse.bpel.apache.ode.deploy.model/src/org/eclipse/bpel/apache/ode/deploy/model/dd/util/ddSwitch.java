/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation, University of Stuttgart (IAAS) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation, University of Stuttgart (IAAS) - initial API and implementation
 *******************************************************************************/

package org.eclipse.bpel.apache.ode.deploy.model.dd.util;

import java.util.List;

import org.eclipse.bpel.apache.ode.deploy.model.dd.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage
 * @generated
 */
public class ddSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ddPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ddSwitch() {
		if (modelPackage == null) {
			modelPackage = ddPackage.eINSTANCE;
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
			case ddPackage.BINDING_TYPE: {
				BindingType bindingType = (BindingType)theEObject;
				T result = caseBindingType(bindingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.MEX_INTERCEPTORS_TYPE: {
				MexInterceptorsType mexInterceptorsType = (MexInterceptorsType)theEObject;
				T result = caseMexInterceptorsType(mexInterceptorsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.PROCESS_TYPE: {
				ProcessType processType = (ProcessType)theEObject;
				T result = caseProcessType(processType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.PROPERTY_TYPE: {
				PropertyType propertyType = (PropertyType)theEObject;
				T result = casePropertyType(propertyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TDEPLOYMENT: {
				TDeployment tDeployment = (TDeployment)theEObject;
				T result = caseTDeployment(tDeployment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TENABLE_EVENT_LIST: {
				TEnableEventList tEnableEventList = (TEnableEventList)theEObject;
				T result = caseTEnableEventList(tEnableEventList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TINVOKE: {
				TInvoke tInvoke = (TInvoke)theEObject;
				T result = caseTInvoke(tInvoke);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TMEX_INTERCEPTOR: {
				TMexInterceptor tMexInterceptor = (TMexInterceptor)theEObject;
				T result = caseTMexInterceptor(tMexInterceptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TPROCESS_EVENTS: {
				TProcessEvents tProcessEvents = (TProcessEvents)theEObject;
				T result = caseTProcessEvents(tProcessEvents);
				if (result == null) result = caseTEnableEventList(tProcessEvents);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TPROVIDE: {
				TProvide tProvide = (TProvide)theEObject;
				T result = caseTProvide(tProvide);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TSCOPE_EVENTS: {
				TScopeEvents tScopeEvents = (TScopeEvents)theEObject;
				T result = caseTScopeEvents(tScopeEvents);
				if (result == null) result = caseTEnableEventList(tScopeEvents);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TSERVICE: {
				TService tService = (TService)theEObject;
				T result = caseTService(tService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TDATASOURCE: {
				TDatasource tDatasource = (TDatasource)theEObject;
				T result = caseTDatasource(tDatasource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TACTIVITY_MAPPING: {
				TActivityMapping tActivityMapping = (TActivityMapping)theEObject;
				T result = caseTActivityMapping(tActivityMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ddPackage.TPOLICY: {
				TPolicy tPolicy = (TPolicy)theEObject;
				T result = caseTPolicy(tPolicy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingType(BindingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mex Interceptors Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mex Interceptors Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMexInterceptorsType(MexInterceptorsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessType(ProcessType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyType(PropertyType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TDeployment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TDeployment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDeployment(TDeployment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TEnable Event List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TEnable Event List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTEnableEventList(TEnableEventList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TInvoke</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TInvoke</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTInvoke(TInvoke object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMex Interceptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMex Interceptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMexInterceptor(TMexInterceptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TProcess Events</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TProcess Events</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTProcessEvents(TProcessEvents object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TProvide</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TProvide</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTProvide(TProvide object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TScope Events</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TScope Events</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTScopeEvents(TScopeEvents object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TService</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TService</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTService(TService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TDatasource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TDatasource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDatasource(TDatasource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TActivity Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TActivity Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTActivityMapping(TActivityMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TPolicy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TPolicy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTPolicy(TPolicy object) {
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

} //ddSwitch
