/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container To Container Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to copy the data from one container to another.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.ContainerToContainerPattern#getSourceContainer <em>Source Container</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.ContainerToContainerPattern#getTargetContainer <em>Target Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getContainerToContainerPattern()
 * @model
 * @generated
 */
public interface ContainerToContainerPattern extends DataManagementPattern {
  /**
   * Returns the value of the '<em><b>Source Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source Container</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source Container</em>' attribute.
   * @see #setSourceContainer(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getContainerToContainerPattern_SourceContainer()
   * @model required="true"
   * @generated
   */
  String getSourceContainer();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.ContainerToContainerPattern#getSourceContainer <em>Source Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Container</em>' attribute.
   * @see #getSourceContainer()
   * @generated
   */
  void setSourceContainer(String value);

  /**
   * Returns the value of the '<em><b>Target Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Container</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Container</em>' attribute.
   * @see #setTargetContainer(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getContainerToContainerPattern_TargetContainer()
   * @model required="true"
   * @generated
   */
  String getTargetContainer();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.ContainerToContainerPattern#getTargetContainer <em>Target Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Container</em>' attribute.
   * @see #getTargetContainer()
   * @generated
   */
  void setTargetContainer(String value);

} // ContainerToContainerPattern
