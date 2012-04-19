/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.bpel.model.Activity;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Iteration Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to iterate a reference list and to execute a specified operation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getContainerReferenceList <em>Container Reference List</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getCurrentContainer <em>Current Container</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getActivity <em>Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataIterationPattern()
 * @model
 * @generated
 */
public interface DataIterationPattern extends DataManagementPattern {
  /**
   * Returns the value of the '<em><b>Container Reference List</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Container Reference List</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Container Reference List</em>' attribute.
   * @see #setContainerReferenceList(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataIterationPattern_ContainerReferenceList()
   * @model required="true"
   * @generated
   */
  String getContainerReferenceList();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getContainerReferenceList <em>Container Reference List</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Container Reference List</em>' attribute.
   * @see #getContainerReferenceList()
   * @generated
   */
  void setContainerReferenceList(String value);

  /**
   * Returns the value of the '<em><b>Current Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Current Container</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Current Container</em>' attribute.
   * @see #setCurrentContainer(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataIterationPattern_CurrentContainer()
   * @model required="true"
   * @generated
   */
  String getCurrentContainer();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getCurrentContainer <em>Current Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Current Container</em>' attribute.
   * @see #getCurrentContainer()
   * @generated
   */
  void setCurrentContainer(String value);

  /**
   * Returns the value of the '<em><b>Activity</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Activity</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Activity</em>' containment reference.
   * @see #setActivity(Activity)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataIterationPattern_Activity()
   * @model containment="true" required="true"
   * @generated
   */
  Activity getActivity();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.DataIterationPattern#getActivity <em>Activity</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Activity</em>' containment reference.
   * @see #getActivity()
   * @generated
   */
  void setActivity(Activity value);

} // DataIterationPattern
