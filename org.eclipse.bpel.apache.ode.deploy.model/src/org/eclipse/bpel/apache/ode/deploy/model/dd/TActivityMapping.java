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
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getPolicy <em>Policy</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getStrategy <em>Strategy</em>}</li>
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
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
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
	 * @model containment="true" required="true"
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

	/**
	 * Returns the value of the '<em><b>Strategy</b></em>' attribute.
	 * The default value is <code>"firstFind"</code>.
	 * The literals are from the enumeration {@link org.eclipse.bpel.apache.ode.deploy.model.dd.StrategyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategy</em>' attribute.
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.StrategyType
	 * @see #isSetStrategy()
	 * @see #unsetStrategy()
	 * @see #setStrategy(StrategyType)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTActivityMapping_Strategy()
	 * @model default="firstFind" unsettable="true" required="true"
	 * @generated
	 */
	StrategyType getStrategy();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getStrategy <em>Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategy</em>' attribute.
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.StrategyType
	 * @see #isSetStrategy()
	 * @see #unsetStrategy()
	 * @see #getStrategy()
	 * @generated
	 */
	void setStrategy(StrategyType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getStrategy <em>Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetStrategy()
	 * @see #getStrategy()
	 * @see #setStrategy(StrategyType)
	 * @generated
	 */
	void unsetStrategy();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TActivityMapping#getStrategy <em>Strategy</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Strategy</em>' attribute is set.
	 * @see #unsetStrategy()
	 * @see #getStrategy()
	 * @see #setStrategy(StrategyType)
	 * @generated
	 */
	boolean isSetStrategy();

} // TActivityMapping
