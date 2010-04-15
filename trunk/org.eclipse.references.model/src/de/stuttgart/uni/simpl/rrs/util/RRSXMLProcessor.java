/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.stuttgart.uni.simpl.rrs.util;

import de.stuttgart.uni.simpl.rrs.RRSPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RRSXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRSXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		RRSPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the RRSResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new RRSResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new RRSResourceFactoryImpl());
		}
		return registrations;
	}

} //RRSXMLProcessor
