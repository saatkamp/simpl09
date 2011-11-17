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

import org.eclipse.emf.ecore.EFactory;

// TODO: Auto-generated Javadoc
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
	 * Returns a new object of class '<em>Data Management Activity</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class ''.
	 * @generated
	 */
	DataManagementActivity createDataManagementActivity();

	/**
   * Returns a new object of class '<em>Query Data Activity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Query Data Activity</em>'.
   * @generated
   */
  QueryDataActivity createQueryDataActivity();

  /**
   * Returns a new object of class '<em>Issue Command Activity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Issue Command Activity</em>'.
   * @generated
   */
  IssueCommandActivity createIssueCommandActivity();

  /**
   * Returns a new object of class '<em>Write Data Back Activity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Write Data Back Activity</em>'.
   * @generated
   */
  WriteDataBackActivity createWriteDataBackActivity();

  /**
   * Returns a new object of class '<em>Transfer Data Activity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Transfer Data Activity</em>'.
   * @generated
   */
  TransferDataActivity createTransferDataActivity();

  /**
	 * Returns a new object of class '<em>Retrieve Data Activity</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class ''.
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
