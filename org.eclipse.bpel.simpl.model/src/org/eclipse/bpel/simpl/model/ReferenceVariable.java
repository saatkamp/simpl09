/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.Variable;

import org.eclipse.xsd.XSDTypeDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.ReferenceVariable#getValueType <em>Value Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.ReferenceVariable#getReferenceType <em>Reference Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.ReferenceVariable#getPeriod <em>Period</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.ReferenceVariable#getExternal <em>External</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getReferenceVariable()
 * @model
 * @generated
 */
public interface ReferenceVariable extends Variable {
	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' containment reference.
	 * @see #setValueType(XSDTypeDefinition)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getReferenceVariable_ValueType()
	 * @model containment="true"
	 * @generated
	 */
	XSDTypeDefinition getValueType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.ReferenceVariable#getValueType <em>Value Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' containment reference.
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(XSDTypeDefinition value);

	/**
	 * Returns the value of the '<em><b>Reference Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link org.eclipse.bpel.simpl.model.ReferenceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Type</em>' attribute.
	 * @see org.eclipse.bpel.simpl.model.ReferenceType
	 * @see #setReferenceType(ReferenceType)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getReferenceVariable_ReferenceType()
	 * @model default="" required="true"
	 * @generated
	 */
	ReferenceType getReferenceType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.ReferenceVariable#getReferenceType <em>Reference Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Type</em>' attribute.
	 * @see org.eclipse.bpel.simpl.model.ReferenceType
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
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getReferenceVariable_Period()
	 * @model
	 * @generated
	 */
	int getPeriod();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.ReferenceVariable#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(int value);

	/**
	 * Returns the value of the '<em><b>External</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External</em>' containment reference.
	 * @see #setExternal(PartnerLink)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getReferenceVariable_External()
	 * @model containment="true"
	 * @generated
	 */
	PartnerLink getExternal();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.ReferenceVariable#getExternal <em>External</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External</em>' containment reference.
	 * @see #getExternal()
	 * @generated
	 */
	void setExternal(PartnerLink value);

} // ReferenceVariable
