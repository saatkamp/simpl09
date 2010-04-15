/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.simpl.rrs.model.rrs.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.simpl.rrs.model.rrs.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RRSFactoryImpl extends EFactoryImpl implements RRSFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RRSFactory init() {
		try {
			RRSFactory theRRSFactory = (RRSFactory)EPackage.Registry.INSTANCE.getEFactory("http://uni-stuttgart.de/simpl/rrs"); 
			if (theRRSFactory != null) {
				return theRRSFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RRSFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRSFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RRSPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case RRSPackage.EPR: return createEPR();
			case RRSPackage.REFERENCE_PARAMETERS: return createReferenceParameters();
			case RRSPackage.REFERENCE_PROPERTIES: return createReferenceProperties();
			case RRSPackage.SERVICE_NAME: return createServiceName();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPR createEPR() {
		EPRImpl epr = new EPRImpl();
		return epr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceParameters createReferenceParameters() {
		ReferenceParametersImpl referenceParameters = new ReferenceParametersImpl();
		return referenceParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceProperties createReferenceProperties() {
		ReferencePropertiesImpl referenceProperties = new ReferencePropertiesImpl();
		return referenceProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceName createServiceName() {
		ServiceNameImpl serviceName = new ServiceNameImpl();
		return serviceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRSPackage getRRSPackage() {
		return (RRSPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RRSPackage getPackage() {
		return RRSPackage.eINSTANCE;
	}

} //RRSFactoryImpl
