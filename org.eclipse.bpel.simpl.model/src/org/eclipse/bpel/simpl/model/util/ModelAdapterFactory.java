/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: ModelAdapterFactory.java 1963 2011-10-26 11:22:17Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model.util;

import javax.wsdl.extensions.AttributeExtensible;
import javax.wsdl.extensions.ElementExtensible;

import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.ExtensionActivity;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.wsdl.ExtensibleElement;
import org.eclipse.wst.wsdl.WSDLElement;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpel.simpl.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl {
	/**
   * The cached model package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static ModelPackage modelPackage;

	/**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ModelAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = ModelPackage.eINSTANCE;
    }
  }

	/**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
	@Override
	public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

	/**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelSwitch<Adapter> modelSwitch =
		new ModelSwitch<Adapter>() {
      @Override
      public Adapter caseDataManagementActivity(DataManagementActivity object) {
        return createDataManagementActivityAdapter();
      }
      @Override
      public Adapter caseQueryDataActivity(QueryDataActivity object) {
        return createQueryDataActivityAdapter();
      }
      @Override
      public Adapter caseIssueCommandActivity(IssueCommandActivity object) {
        return createIssueCommandActivityAdapter();
      }
      @Override
      public Adapter caseRetrieveDataActivity(RetrieveDataActivity object) {
        return createRetrieveDataActivityAdapter();
      }
      @Override
      public Adapter caseWriteDataBackActivity(WriteDataBackActivity object) {
        return createWriteDataBackActivityAdapter();
      }
      @Override
      public Adapter caseTransferDataActivity(TransferDataActivity object) {
        return createTransferDataActivityAdapter();
      }
      @Override
      public Adapter caseWSDLElement(WSDLElement object) {
        return createWSDLElementAdapter();
      }
      @Override
      public Adapter caseIElementExtensible(ElementExtensible object) {
        return createIElementExtensibleAdapter();
      }
      @Override
      public Adapter caseIAttributeExtensible(AttributeExtensible object) {
        return createIAttributeExtensibleAdapter();
      }
      @Override
      public Adapter caseExtensibleElement(ExtensibleElement object) {
        return createExtensibleElementAdapter();
      }
      @Override
      public Adapter caseBPEL_ExtensibleElement(org.eclipse.bpel.model.ExtensibleElement object) {
        return createBPEL_ExtensibleElementAdapter();
      }
      @Override
      public Adapter caseActivity(Activity object) {
        return createActivityAdapter();
      }
      @Override
      public Adapter caseExtensionActivity(ExtensionActivity object) {
        return createExtensionActivityAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

	/**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
	@Override
	public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject)target);
  }


	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.simpl.model.DataManagementActivity <em>Data Management Activity</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.simpl.model.DataManagementActivity
   * @generated
   */
	public Adapter createDataManagementActivityAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.simpl.model.QueryDataActivity <em>Query Data Activity</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.simpl.model.QueryDataActivity
   * @generated
   */
  public Adapter createQueryDataActivityAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.simpl.model.IssueCommandActivity <em>Issue Command Activity</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.simpl.model.IssueCommandActivity
   * @generated
   */
  public Adapter createIssueCommandActivityAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.simpl.model.WriteDataBackActivity <em>Write Data Back Activity</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.simpl.model.WriteDataBackActivity
   * @generated
   */
  public Adapter createWriteDataBackActivityAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.simpl.model.TransferDataActivity <em>Transfer Data Activity</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.simpl.model.TransferDataActivity
   * @generated
   */
  public Adapter createTransferDataActivityAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.simpl.model.RetrieveDataActivity <em>Retrieve Data Activity</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.simpl.model.RetrieveDataActivity
   * @generated
   */
	public Adapter createRetrieveDataActivityAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.wst.wsdl.WSDLElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.wst.wsdl.WSDLElement
   * @generated
   */
	public Adapter createWSDLElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link javax.wsdl.extensions.ElementExtensible <em>IElement Extensible</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see javax.wsdl.extensions.ElementExtensible
   * @generated
   */
	public Adapter createIElementExtensibleAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link javax.wsdl.extensions.AttributeExtensible <em>IAttribute Extensible</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see javax.wsdl.extensions.AttributeExtensible
   * @generated
   */
	public Adapter createIAttributeExtensibleAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.wst.wsdl.ExtensibleElement <em>Extensible Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.wst.wsdl.ExtensibleElement
   * @generated
   */
	public Adapter createExtensibleElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.model.ExtensibleElement <em>Extensible Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.model.ExtensibleElement
   * @generated
   */
	public Adapter createBPEL_ExtensibleElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.model.Activity <em>Activity</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.model.Activity
   * @generated
   */
	public Adapter createActivityAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.bpel.model.ExtensionActivity <em>Extension Activity</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.bpel.model.ExtensionActivity
   * @generated
   */
	public Adapter createExtensionActivityAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
	public Adapter createEObjectAdapter() {
    return null;
  }

} //ModelAdapterFactory
