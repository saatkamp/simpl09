/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transfer Data Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to transfer data between two datasources.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsIdentifier <em>Target Ds Identifier</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsType <em>Target Ds Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsKind <em>Target Ds Kind</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsLanguage <em>Target Ds Language</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsContainer <em>Target Ds Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity()
 * @model
 * @generated
 */
public interface TransferDataActivity extends DataManagementActivity {
  /**
   * Returns the value of the '<em><b>Target Ds Identifier</b></em>' attribute.
   * The default value is <code>"identifier"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Ds Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Ds Identifier</em>' attribute.
   * @see #setTargetDsIdentifier(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_TargetDsIdentifier()
   * @model default="identifier"
   * @generated
   */
  String getTargetDsIdentifier();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsIdentifier <em>Target Ds Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Ds Identifier</em>' attribute.
   * @see #getTargetDsIdentifier()
   * @generated
   */
  void setTargetDsIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Target Ds Type</b></em>' attribute.
   * The default value is <code>"type"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Ds Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Ds Type</em>' attribute.
   * @see #setTargetDsType(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_TargetDsType()
   * @model default="type"
   * @generated
   */
  String getTargetDsType();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsType <em>Target Ds Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Ds Type</em>' attribute.
   * @see #getTargetDsType()
   * @generated
   */
  void setTargetDsType(String value);

  /**
   * Returns the value of the '<em><b>Target Ds Kind</b></em>' attribute.
   * The default value is <code>"kind"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Ds Kind</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Ds Kind</em>' attribute.
   * @see #setTargetDsKind(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_TargetDsKind()
   * @model default="kind"
   * @generated
   */
  String getTargetDsKind();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsKind <em>Target Ds Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Ds Kind</em>' attribute.
   * @see #getTargetDsKind()
   * @generated
   */
  void setTargetDsKind(String value);

  /**
   * Returns the value of the '<em><b>Target Ds Language</b></em>' attribute.
   * The default value is <code>"language"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Ds Language</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Ds Language</em>' attribute.
   * @see #setTargetDsLanguage(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_TargetDsLanguage()
   * @model default="language"
   * @generated
   */
  String getTargetDsLanguage();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsLanguage <em>Target Ds Language</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Ds Language</em>' attribute.
   * @see #getTargetDsLanguage()
   * @generated
   */
  void setTargetDsLanguage(String value);

  /**
   * Returns the value of the '<em><b>Target Ds Container</b></em>' attribute.
   * The default value is <code>"targetDsContainer"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Ds Container</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Ds Container</em>' attribute.
   * @see #setTargetDsContainer(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getTransferDataActivity_TargetDsContainer()
   * @model default="targetDsContainer"
   * @generated
   */
  String getTargetDsContainer();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.TransferDataActivity#getTargetDsContainer <em>Target Ds Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Ds Container</em>' attribute.
   * @see #getTargetDsContainer()
   * @generated
   */
  void setTargetDsContainer(String value);

} // TransferDataActivity
