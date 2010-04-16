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
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getDataSourceName <em>Data Source Name</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getPolicy <em>Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping()
 * @model
 * @generated
 */
public interface TActivityMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' attribute.
	 * @see #setActivity(String)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping_Activity()
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getActivity();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getActivity <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' attribute.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(String value);

	/**
	 * Returns the value of the '<em><b>Data Source Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Source Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Source Name</em>' attribute.
	 * @see #setDataSourceName(String)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping_DataSourceName()
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getDataSourceName();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getDataSourceName <em>Data Source Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Source Name</em>' attribute.
	 * @see #getDataSourceName()
	 * @generated
	 */
	void setDataSourceName(String value);

	/**
	 * Returns the value of the '<em><b>Policy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy</em>' containment reference.
	 * @see #setPolicy(TPolicy)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping_Policy()
	 * @model containment="true"
	 * @generated
	 */
	TPolicy getPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getPolicy <em>Policy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' containment reference.
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(TPolicy value);

} // TActivityMapping
