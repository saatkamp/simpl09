/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
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
 *   <li>{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsAddress <em>Ds Address</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsType <em>Ds Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsKind <em>Ds Kind</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsStatement <em>Ds Statement</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsLanguage <em>Ds Language</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity()
 * @model
 * @generated
 */
public interface DataManagementActivity extends ExtensionActivity {

	/**
	 * Returns the value of the '<em><b>Ds Statement</b></em>' attribute.
	 * The default value is <code>"statement"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ds Statement</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ds Statement</em>' attribute.
	 * @see #setDsStatement(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity_DsStatement()
	 * @model default="statement"
	 * @generated
	 */
	String getDsStatement();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsStatement <em>Ds Statement</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Ds Statement</em>' attribute.
	 * @see #getDsStatement()
	 * @generated
	 */
	void setDsStatement(String value);

	/**
	 * Returns the value of the '<em><b>Ds Language</b></em>' attribute.
	 * The default value is <code>"language"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ds Language</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ds Language</em>' attribute.
	 * @see #setDsLanguage(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity_DsLanguage()
	 * @model default="language"
	 * @generated
	 */
	String getDsLanguage();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsLanguage <em>Ds Language</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Ds Language</em>' attribute.
	 * @see #getDsLanguage()
	 * @generated
	 */
	void setDsLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Ds Kind</b></em>' attribute.
	 * The default value is <code>"subtype"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ds Kind</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ds Kind</em>' attribute.
	 * @see #setDsKind(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity_DsKind()
	 * @model default="subtype"
	 * @generated
	 */
	String getDsKind();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsKind
	 * <em>Ds Kind</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ds Kind</em>' attribute.
	 * @see #getDsKind()
	 * @generated
	 */
	void setDsKind(String value);

	/**
	 * Returns the value of the '<em><b>Ds Type</b></em>' attribute.
	 * The default value is <code>"type"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ds Type</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ds Type</em>' attribute.
	 * @see #setDsType(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity_DsType()
	 * @model default="type"
	 * @generated
	 */
	String getDsType();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsType
	 * <em>Ds Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ds Type</em>' attribute.
	 * @see #getDsType()
	 * @generated
	 */
	void setDsType(String value);

	/**
	 * Returns the value of the '<em><b>Ds Address</b></em>' attribute.
	 * The default value is <code>"address"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ds Address</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ds Address</em>' attribute.
	 * @see #setDsAddress(String)
	 * @see org.eclipse.bpel.simpl.model.ModelPackage#getDataManagementActivity_DsAddress()
	 * @model default="address"
	 * @generated
	 */
	String getDsAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.simpl.model.DataManagementActivity#getDsAddress <em>Ds Address</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Ds Address</em>' attribute.
	 * @see #getDsAddress()
	 * @generated
	 */
	void setDsAddress(String value);

} // DataManagementActivity
