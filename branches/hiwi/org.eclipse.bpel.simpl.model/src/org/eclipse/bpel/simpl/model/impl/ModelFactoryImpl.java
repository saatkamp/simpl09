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
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.simpl.model.*;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.ModelFactory;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
   * @generated
   */
	public static ModelFactory init() {
    try {
      ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.example.org/simpl"); 
      if (theModelFactory != null) {
        return theModelFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ModelFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ModelFactoryImpl() {
    super();
  }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param eClass
	 *            the e class
	 * @return the e object
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case ModelPackage.DATA_MANAGEMENT_ACTIVITY: return createDataManagementActivity();
      case ModelPackage.QUERY_DATA_ACTIVITY: return createQueryDataActivity();
      case ModelPackage.ISSUE_COMMAND_ACTIVITY: return createIssueCommandActivity();
      case ModelPackage.RETRIEVE_DATA_ACTIVITY: return createRetrieveDataActivity();
      case ModelPackage.WRITE_DATA_BACK_ACTIVITY: return createWriteDataBackActivity();
      case ModelPackage.TRANSFER_DATA_ACTIVITY: return createTransferDataActivity();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the data management activity
	 * @generated
	 */
	public DataManagementActivity createDataManagementActivity() {
    DataManagementActivityImpl dataManagementActivity = new DataManagementActivityImpl();
    return dataManagementActivity;
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QueryDataActivity createQueryDataActivity() {
    QueryDataActivityImpl queryDataActivity = new QueryDataActivityImpl();
    return queryDataActivity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IssueCommandActivity createIssueCommandActivity() {
    IssueCommandActivityImpl issueCommandActivity = new IssueCommandActivityImpl();
    return issueCommandActivity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WriteDataBackActivity createWriteDataBackActivity() {
    WriteDataBackActivityImpl writeDataBackActivity = new WriteDataBackActivityImpl();
    return writeDataBackActivity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TransferDataActivity createTransferDataActivity() {
    TransferDataActivityImpl transferDataActivity = new TransferDataActivityImpl();
    return transferDataActivity;
  }

  /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the retrieve data activity
	 * @generated
	 */
	public RetrieveDataActivity createRetrieveDataActivity() {
    RetrieveDataActivityImpl retrieveDataActivity = new RetrieveDataActivityImpl();
    return retrieveDataActivity;
  }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the model package
	 * @generated
	 */
	public ModelPackage getModelPackage() {
    return (ModelPackage)getEPackage();
  }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the package
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
    return ModelPackage.eINSTANCE;
  }

} //ModelFactoryImpl
