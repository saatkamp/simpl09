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

package org.eclipse.bpel.apache.ode.deploy.model.dd;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TInvoke</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TInvoke#getService <em>Service</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TInvoke#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TInvoke#getPartnerLink <em>Partner Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTInvoke()
 * @model extendedMetaData="name='tInvoke' kind='elementOnly'"
 * @generated
 */
public interface TInvoke extends EObject {
	/**
	 * Returns the value of the '<em><b>Service</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service</em>' containment reference.
	 * @see #setService(TService)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTInvoke_Service()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='service' namespace='##targetNamespace'"
	 * @generated
	 */
	TService getService();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TInvoke#getService <em>Service</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service</em>' containment reference.
	 * @see #getService()
	 * @generated
	 */
	void setService(TService value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(BindingType)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTInvoke_Binding()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='binding' namespace='##targetNamespace'"
	 * @generated
	 */
	BindingType getBinding();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TInvoke#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(BindingType value);

	/**
	 * Returns the value of the '<em><b>Partner Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partner Link</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partner Link</em>' attribute.
	 * @see #setPartnerLink(String)
	 * @see org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage#getTInvoke_PartnerLink()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='partnerLink'"
	 * @generated
	 */
	String getPartnerLink();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.apache.ode.deploy.model.dd.TInvoke#getPartnerLink <em>Partner Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partner Link</em>' attribute.
	 * @see #getPartnerLink()
	 * @generated
	 */
	void setPartnerLink(String value);

} // TInvoke
