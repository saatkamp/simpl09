/**
 * Copyright (c) 2008 IBM Corporation, University of Stuttgart (IAAS) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation, University of Stuttgart (IAAS) - initial API and implementation
 *
 * $Id$
 */
package org.eclipse.bpel.apache.ode.deploy.model.dd;

import org.eclipse.bpel.model.ExtensionActivity;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TActivity Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getPolicy <em>Policy</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getDatasource <em>Datasource</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping()
 * @model
 * @generated
 */
public interface TActivityMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' containment reference.
	 * @see #setActivity(ExtensionActivity)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping_Activity()
	 * @model containment="true"
	 * @generated
	 */
	ExtensionActivity getActivity();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getActivity <em>Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' containment reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(ExtensionActivity value);

	/**
	 * Returns the value of the '<em><b>Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy</em>' attribute.
	 * @see #setPolicy(String)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping_Policy()
	 * @model required="true"
	 * @generated
	 */
	String getPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getPolicy <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' attribute.
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(String value);

	/**
	 * Returns the value of the '<em><b>Datasource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Datasource</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Datasource</em>' containment reference.
	 * @see #setDatasource(TDatasource)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping_Datasource()
	 * @model containment="true"
	 * @generated
	 */
	TDatasource getDatasource();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getDatasource <em>Datasource</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Datasource</em>' containment reference.
	 * @see #getDatasource()
	 * @generated
	 */
	void setDatasource(TDatasource value);

} // TActivityMapping
