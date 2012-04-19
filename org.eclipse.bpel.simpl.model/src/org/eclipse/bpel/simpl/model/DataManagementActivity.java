/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: DataManagementActivity.java 1807 2011-05-12 09:27:15Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.bpel.model.ExtensionActivity;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Data Management Activity</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Allows to interact with datasources directly out of the process.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDataResource <em>Data Resource</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDmCommand <em>Dm Command</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity()
 * @model
 * @generated
 */
public interface DataManagementActivity extends ExtensionActivity {

	/**
   * Returns the value of the '<em><b>Data Resource</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Resource</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Resource</em>' attribute.
   * @see #setDataResource(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity_DataResource()
   * @model default=""
   * @generated
   */
  String getDataResource();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDataResource <em>Data Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Resource</em>' attribute.
   * @see #getDataResource()
   * @generated
   */
  void setDataResource(String value);

  /**
   * Returns the value of the '<em><b>Dm Command</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dm Command</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dm Command</em>' attribute.
   * @see #setDmCommand(String)
   * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity_DmCommand()
   * @model default=""
   * @generated
   */
  String getDmCommand();

  /**
   * Sets the value of the '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDmCommand <em>Dm Command</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dm Command</em>' attribute.
   * @see #getDmCommand()
   * @generated
   */
  void setDmCommand(String value);

} // DataManagementActivity
