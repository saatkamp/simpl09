/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpel.simpl.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = org.eclipse.bpel.simpl.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Data Management Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Management Activity</em>'.
	 * @generated
	 */
	DataManagementActivity createDataManagementActivity();

	/**
	 * Returns a new object of class '<em>Query Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Activity</em>'.
	 * @generated
	 */
	QueryActivity createQueryActivity();

	/**
	 * Returns a new object of class '<em>Insert Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Insert Activity</em>'.
	 * @generated
	 */
	InsertActivity createInsertActivity();

	/**
	 * Returns a new object of class '<em>Update Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Update Activity</em>'.
	 * @generated
	 */
	UpdateActivity createUpdateActivity();

	/**
	 * Returns a new object of class '<em>Delete Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delete Activity</em>'.
	 * @generated
	 */
	DeleteActivity createDeleteActivity();

	/**
	 * Returns a new object of class '<em>Create Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Create Activity</em>'.
	 * @generated
	 */
	CreateActivity createCreateActivity();

	/**
	 * Returns a new object of class '<em>Drop Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Drop Activity</em>'.
	 * @generated
	 */
	DropActivity createDropActivity();

	/**
	 * Returns a new object of class '<em>Call Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Call Activity</em>'.
	 * @generated
	 */
	CallActivity createCallActivity();

	/**
	 * Returns a new object of class '<em>Retrieve Data Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Retrieve Data Activity</em>'.
	 * @generated
	 */
	RetrieveDataActivity createRetrieveDataActivity();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
