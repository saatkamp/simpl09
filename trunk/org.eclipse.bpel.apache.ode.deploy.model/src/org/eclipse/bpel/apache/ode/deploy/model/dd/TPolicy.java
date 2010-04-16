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
 * A representation of the model object '<em><b>TPolicy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TPolicy#getPolicy <em>Policy</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TPolicy#getLocalPath <em>Local Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTPolicy()
 * @model
 * @generated
 */
public interface TPolicy extends EObject {
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
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTPolicy_Policy()
	 * @model required="true"
	 * @generated
	 */
	String getPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TPolicy#getPolicy <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' attribute.
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(String value);

	/**
	 * Returns the value of the '<em><b>Local Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Path</em>' attribute.
	 * @see #setLocalPath(String)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTPolicy_LocalPath()
	 * @model
	 * @generated
	 */
	String getLocalPath();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TPolicy#getLocalPath <em>Local Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Path</em>' attribute.
	 * @see #getLocalPath()
	 * @generated
	 */
	void setLocalPath(String value);

} // TPolicy
