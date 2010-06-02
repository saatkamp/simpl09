/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model;

import org.eclipse.xsd.XSDTypeDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.ReferenceVariable#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.ReferenceVariable#getValueType <em>Value Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.ReferenceVariable#getReferenceType <em>Reference Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.ReferenceVariable#getPeriod <em>Period</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.ReferenceVariable#getPartnerLink <em>Partner Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.model.BPELPackage#getReferenceVariable()
 * @model
 * @generated
 */
public interface ReferenceVariable extends ExtensibleElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.bpel.model.BPELPackage#getReferenceVariable_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.model.ReferenceVariable#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' reference.
	 * @see #setValueType(XSDTypeDefinition)
	 * @see org.eclipse.bpel.model.BPELPackage#getReferenceVariable_ValueType()
	 * @model
	 * @generated
	 */
	XSDTypeDefinition getValueType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.model.ReferenceVariable#getValueType <em>Value Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' reference.
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(XSDTypeDefinition value);

	/**
	 * Returns the value of the '<em><b>Reference Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.bpel.model.ReferenceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Type</em>' attribute.
	 * @see org.eclipse.bpel.model.ReferenceType
	 * @see #setReferenceType(ReferenceType)
	 * @see org.eclipse.bpel.model.BPELPackage#getReferenceVariable_ReferenceType()
	 * @model
	 * @generated
	 */
	ReferenceType getReferenceType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.model.ReferenceVariable#getReferenceType <em>Reference Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Type</em>' attribute.
	 * @see org.eclipse.bpel.model.ReferenceType
	 * @see #getReferenceType()
	 * @generated
	 */
	void setReferenceType(ReferenceType value);

	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Period</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see #setPeriod(int)
	 * @see org.eclipse.bpel.model.BPELPackage#getReferenceVariable_Period()
	 * @model
	 * @generated
	 */
	int getPeriod();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.model.ReferenceVariable#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(int value);

	/**
	 * Returns the value of the '<em><b>Partner Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partner Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partner Link</em>' reference.
	 * @see #setPartnerLink(PartnerLink)
	 * @see org.eclipse.bpel.model.BPELPackage#getReferenceVariable_PartnerLink()
	 * @model
	 * @generated
	 */
	PartnerLink getPartnerLink();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.model.ReferenceVariable#getPartnerLink <em>Partner Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partner Link</em>' reference.
	 * @see #getPartnerLink()
	 * @generated
	 */
	void setPartnerLink(PartnerLink value);

} // ReferenceVariable
