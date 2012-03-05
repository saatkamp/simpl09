/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Source Reference Variables</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.DataSourceReferenceVariables#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.model.BPELPackage#getDataSourceReferenceVariables()
 * @model
 * @generated
 */
public interface DataSourceReferenceVariables extends ExtensibleElement {
  /**
   * Returns the value of the '<em><b>Children</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.bpel.model.DataSourceReferenceVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Children</em>' containment reference list.
   * @see org.eclipse.bpel.model.BPELPackage#getDataSourceReferenceVariables_Children()
   * @model containment="true"
   * @generated
   */
  EList<DataSourceReferenceVariable> getChildren();

} // DataSourceReferenceVariables
