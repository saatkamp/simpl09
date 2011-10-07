/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model;

import org.eclipse.wst.wsdl.Message;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Descriptor Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.DescriptorVariable#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.DescriptorVariable#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.DescriptorVariable#getXSDElement <em>XSD Element</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.DescriptorVariable#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.DescriptorVariable#getFrom <em>From</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.model.BPELPackage#getDescriptorVariable()
 * @model
 * @generated
 */
public interface DescriptorVariable extends ExtensibleElement {

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
   * @see org.eclipse.bpel.model.BPELPackage#getDescriptorVariable_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.model.DescriptorVariable#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Message Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Type</em>' reference.
   * @see #setMessageType(Message)
   * @see org.eclipse.bpel.model.BPELPackage#getDescriptorVariable_MessageType()
   * @model
   * @generated
   */
  Message getMessageType();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.model.DescriptorVariable#getMessageType <em>Message Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message Type</em>' reference.
   * @see #getMessageType()
   * @generated
   */
  void setMessageType(Message value);

  /**
   * Returns the value of the '<em><b>XSD Element</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>XSD Element</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>XSD Element</em>' reference.
   * @see #setXSDElement(XSDElementDeclaration)
   * @see org.eclipse.bpel.model.BPELPackage#getDescriptorVariable_XSDElement()
   * @model
   * @generated
   */
  XSDElementDeclaration getXSDElement();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.model.DescriptorVariable#getXSDElement <em>XSD Element</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>XSD Element</em>' reference.
   * @see #getXSDElement()
   * @generated
   */
  void setXSDElement(XSDElementDeclaration value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(XSDTypeDefinition)
   * @see org.eclipse.bpel.model.BPELPackage#getDescriptorVariable_Type()
   * @model
   * @generated
   */
  XSDTypeDefinition getType();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.model.DescriptorVariable#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(XSDTypeDefinition value);

  /**
   * Returns the value of the '<em><b>From</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>From</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>From</em>' containment reference.
   * @see #setFrom(From)
   * @see org.eclipse.bpel.model.BPELPackage#getDescriptorVariable_From()
   * @model containment="true"
   * @generated
   */
  From getFrom();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.model.DescriptorVariable#getFrom <em>From</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>From</em>' containment reference.
   * @see #getFrom()
   * @generated
   */
  void setFrom(From value);

} // DescriptorVariable
