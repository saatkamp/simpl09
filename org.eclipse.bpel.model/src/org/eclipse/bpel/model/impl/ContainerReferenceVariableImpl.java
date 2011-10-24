/**
 * <copyright>
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 * </copyright>
 *
 * $Id: ContainerReferenceVariableImpl.java,v 1.15 2009/04/14 10:50:37 smoser Exp $
 */
package org.eclipse.bpel.model.impl;

import javax.xml.namespace.QName;

import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.ContainerReferenceVariable;
import org.eclipse.bpel.model.From;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.Variables;
import org.eclipse.bpel.model.util.BPELConstants;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.WSDLElement;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;
import org.w3c.dom.Element;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>ContainerReferenceVariable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.impl.ContainerReferenceVariableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ContainerReferenceVariableImpl#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ContainerReferenceVariableImpl#getXSDElement <em>XSD Element</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ContainerReferenceVariableImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.model.impl.ContainerReferenceVariableImpl#getFrom <em>From</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */

@SuppressWarnings("restriction")
public class ContainerReferenceVariableImpl extends ExtensibleElementImpl implements
    ContainerReferenceVariable {
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getMessageType() <em>Message Type</em>}' reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getMessageType()
   * @generated
   * @ordered
   */
  protected Message messageType;

  /**
   * The cached value of the '{@link #getXSDElement() <em>XSD Element</em>}' reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getXSDElement()
   * @generated
   * @ordered
   */
  protected XSDElementDeclaration xsdElement;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected XSDTypeDefinition type;

  /**
   * The cached value of the '{@link #getFrom() <em>From</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getFrom()
   * @generated
   * @ordered
   */
  protected From from;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected ContainerReferenceVariableImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return BPELPackage.Literals.CONTAINER_REFERENCE_VARIABLE;
  }

  /**
   * Finds the variable clone of this container variable.
   * 
   * @return the variable clone
   */
  protected Variable findCloneVariable() {
    org.eclipse.bpel.model.Process process = BPELUtils.getProcess(this);
    Variables variables = null;
    Variable cloneVariable = null;

    if (process != null) {
      variables = process.getVariables();

      for (Variable var : variables.getChildren()) {
        if (var.getName().equals(this.getName())) {
          cloneVariable = var;
          break;
        }
      }
    }

    return cloneVariable;
  }

  /**
   * Overridden to synchronize the variable clone.
   */
  @Override
  protected void reconcile(Element changedElement) {
    super.reconcile(changedElement);

    // calling own setters to update the variable clone
    this.setMessageType(this.getMessageType());
    this.setXSDElement(this.getXSDElement());
    this.setType(this.getType());
    this.setFrom(this.getFrom());
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   */
  public void setName(String newName) {
    String oldName = name;
    if (!isReconciling) {
      if (this.findCloneVariable() != null) {
        this.findCloneVariable().setName(newName);
      }

      ReconciliationHelper.replaceAttribute(this, BPELConstants.AT_NAME, newName);
      ReconciliationHelper.updateVariableName((WSDLElement) eContainer(), newName);
    }

    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          BPELPackage.CONTAINER_REFERENCE_VARIABLE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public Message getMessageType() {
    if (messageType != null && messageType.eIsProxy()) {
      InternalEObject oldMessageType = (InternalEObject) messageType;
      messageType = (Message) eResolveProxy(oldMessageType);
      if (messageType != oldMessageType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              BPELPackage.CONTAINER_REFERENCE_VARIABLE__MESSAGE_TYPE, oldMessageType,
              messageType));
      }
    }
    return messageType;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public Message basicGetMessageType() {
    return messageType;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   */
  public void setMessageType(Message newMessageType) {
    Message oldMessageType = messageType;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, BPELConstants.AT_MESSAGE_TYPE,
          newMessageType == null ? null : newMessageType.getQName());
    }
    messageType = newMessageType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          BPELPackage.CONTAINER_REFERENCE_VARIABLE__MESSAGE_TYPE, oldMessageType,
          messageType));

    if (this.findCloneVariable() != null) {
      this.findCloneVariable().setMessageType(messageType);
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public XSDElementDeclaration getXSDElement() {
    if (xsdElement != null && xsdElement.eIsProxy()) {
      InternalEObject oldXSDElement = (InternalEObject) xsdElement;
      xsdElement = (XSDElementDeclaration) eResolveProxy(oldXSDElement);
      if (xsdElement != oldXSDElement) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              BPELPackage.CONTAINER_REFERENCE_VARIABLE__XSD_ELEMENT, oldXSDElement,
              xsdElement));
      }
    }
    return xsdElement;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public XSDElementDeclaration basicGetXSDElement() {
    return xsdElement;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   */
  public void setXSDElement(XSDElementDeclaration newXSDElement) {
    XSDElementDeclaration oldXSDElement = xsdElement;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(this, BPELConstants.AT_ELEMENT,
          newXSDElement == null ? null : new QName(newXSDElement.getTargetNamespace(),
              newXSDElement.getName()));
    }
    xsdElement = newXSDElement;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          BPELPackage.CONTAINER_REFERENCE_VARIABLE__XSD_ELEMENT, oldXSDElement,
          xsdElement));

    if (this.findCloneVariable() != null) {
      this.findCloneVariable().setXSDElement(xsdElement);
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public XSDTypeDefinition getType() {
    if (type != null && type.eIsProxy()) {
      InternalEObject oldType = (InternalEObject) type;
      type = (XSDTypeDefinition) eResolveProxy(oldType);
      if (type != oldType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              BPELPackage.CONTAINER_REFERENCE_VARIABLE__TYPE, oldType, type));
      }
    }
    return type;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public XSDTypeDefinition basicGetType() {
    return type;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   */
  public void setType(XSDTypeDefinition newType) {
    XSDTypeDefinition oldType = type;
    if (!isReconciling) {
      ReconciliationHelper.replaceAttribute(
          this,
          BPELConstants.AT_TYPE,
          newType == null ? null : new QName(newType.getTargetNamespace(), newType
              .getName()));
    }
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          BPELPackage.CONTAINER_REFERENCE_VARIABLE__TYPE, oldType, type));

    if (this.findCloneVariable() != null) {
      this.findCloneVariable().setType(type);
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public From getFrom() {
    return from;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  public NotificationChain basicSetFrom(From newFrom, NotificationChain msgs) {
    From oldFrom = from;
    if (!isReconciling) {
      ReconciliationHelper.replaceChild(this, oldFrom, newFrom);
    }
    from = newFrom;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM, oldFrom, newFrom);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   */
  public void setFrom(From newFrom) {
    if (newFrom != from) {
      NotificationChain msgs = null;
      if (from != null)
        msgs = ((InternalEObject) from).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
            - BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM, null, msgs);
      if (newFrom != null)
        msgs = ((InternalEObject) newFrom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
            - BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM, null, msgs);
      msgs = basicSetFrom(newFrom, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM, newFrom, newFrom));

    if (this.findCloneVariable() != null) {
      Element fromCloneElement = null;
      From fromClone = null;

      if (newFrom != null) {
        fromCloneElement = (Element) newFrom.getElement().cloneNode(true);
        fromClone = BPELFactory.eINSTANCE.createFrom();
        fromClone.setElement(fromCloneElement);
      }

      this.findCloneVariable().setFrom(fromClone);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM:
      return basicSetFrom(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__NAME:
      return getName();
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__MESSAGE_TYPE:
      if (resolve)
        return getMessageType();
      return basicGetMessageType();
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__XSD_ELEMENT:
      if (resolve)
        return getXSDElement();
      return basicGetXSDElement();
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__TYPE:
      if (resolve)
        return getType();
      return basicGetType();
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM:
      return getFrom();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__NAME:
      setName((String) newValue);
      return;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__MESSAGE_TYPE:
      setMessageType((Message) newValue);
      return;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__XSD_ELEMENT:
      setXSDElement((XSDElementDeclaration) newValue);
      return;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__TYPE:
      setType((XSDTypeDefinition) newValue);
      return;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM:
      setFrom((From) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__NAME:
      setName(NAME_EDEFAULT);
      return;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__MESSAGE_TYPE:
      setMessageType((Message) null);
      return;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__XSD_ELEMENT:
      setXSDElement((XSDElementDeclaration) null);
      return;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__TYPE:
      setType((XSDTypeDefinition) null);
      return;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM:
      setFrom((From) null);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__NAME:
      return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__MESSAGE_TYPE:
      return messageType != null;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__XSD_ELEMENT:
      return xsdElement != null;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__TYPE:
      return type != null;
    case BPELPackage.CONTAINER_REFERENCE_VARIABLE__FROM:
      return from != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: "); //$NON-NLS-1$
    result.append(name);
    result.append(')');
    return result.toString();
  }
} // ContainerReferenceVariableImpl
