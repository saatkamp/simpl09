/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.dmextension.model.util;

import javax.wsdl.extensions.AttributeExtensible;
import javax.wsdl.extensions.ElementExtensible;


import org.eclipse.bpel.dmextension.model.*;
import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.ExtensionActivity;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.wst.wsdl.ExtensibleElement;
import org.eclipse.wst.wsdl.WSDLElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpel.dmextension.model.ModelPackage
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
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
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
			public Adapter caseUpdateActivity(UpdateActivity object) {
				return createUpdateActivityAdapter();
			}
			@Override
			public Adapter caseInsertActivity(InsertActivity object) {
				return createInsertActivityAdapter();
			}
			@Override
			public Adapter caseSelectActivity(SelectActivity object) {
				return createSelectActivityAdapter();
			}
			@Override
			public Adapter caseDeleteActivity(DeleteActivity object) {
				return createDeleteActivityAdapter();
			}
			@Override
			public Adapter caseCreateActivity(CreateActivity object) {
				return createCreateActivityAdapter();
			}
			@Override
			public Adapter caseCallProcedureActivity(CallProcedureActivity object) {
				return createCallProcedureActivityAdapter();
			}
			@Override
			public Adapter caseRetrieveSetActivity(RetrieveSetActivity object) {
				return createRetrieveSetActivityAdapter();
			}
			@Override
			public Adapter caseWriteBackActivity(WriteBackActivity object) {
				return createWriteBackActivityAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.DataManagementActivity <em>Data Management Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.DataManagementActivity
	 * @generated
	 */
	public Adapter createDataManagementActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.UpdateActivity <em>Update Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.UpdateActivity
	 * @generated
	 */
	public Adapter createUpdateActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.InsertActivity <em>Insert Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.InsertActivity
	 * @generated
	 */
	public Adapter createInsertActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.SelectActivity <em>Select Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.SelectActivity
	 * @generated
	 */
	public Adapter createSelectActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.DeleteActivity <em>Delete Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.DeleteActivity
	 * @generated
	 */
	public Adapter createDeleteActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.CreateActivity <em>Create Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.CreateActivity
	 * @generated
	 */
	public Adapter createCreateActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.CallProcedureActivity <em>Call Procedure Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.CallProcedureActivity
	 * @generated
	 */
	public Adapter createCallProcedureActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.RetrieveSetActivity <em>Retrieve Set Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.RetrieveSetActivity
	 * @generated
	 */
	public Adapter createRetrieveSetActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.bpel.dmextension.model.WriteBackActivity <em>Write Back Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.bpel.dmextension.model.WriteBackActivity
	 * @generated
	 */
	public Adapter createWriteBackActivityAdapter() {
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