/**
 * <b>Purpose:</b> This class implements the {@link BPELActivityDeserializer} interface for the deserialization
 * of all SIMPL Activities. <br>
 * <b>Description:</b> Provides the deserialization of all DataManagement activities.<br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model.util;

import java.util.Map;

import javax.wsdl.extensions.ExtensionRegistry;
import javax.xml.namespace.QName;

import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.extensions.BPELActivityDeserializer;
import org.eclipse.bpel.model.resource.BPELReader;
import org.eclipse.bpel.simpl.model.CallActivity;
import org.eclipse.bpel.simpl.model.CreateActivity;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.DeleteActivity;
import org.eclipse.bpel.simpl.model.DropActivity;
import org.eclipse.bpel.simpl.model.InsertActivity;
import org.eclipse.bpel.simpl.model.ModelFactory;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferActivity;
import org.eclipse.bpel.simpl.model.UpdateActivity;
import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// TODO: Auto-generated Javadoc
/**
 * The Class DataManagementActivityDeserializer.
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 */
public class DataManagementActivityDeserializer implements
		BPELActivityDeserializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.model.extensions.BPELActivityDeserializer#unmarshall
	 * (javax.xml.namespace.QName, org.w3c.dom.Node,
	 * org.eclipse.bpel.model.Process, java.util.Map,
	 * javax.wsdl.extensions.ExtensionRegistry, org.eclipse.emf.common.util.URI,
	 * org.eclipse.bpel.model.resource.BPELReader)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Activity unmarshall(QName elementType, Node node, Process process,
			Map nsMap, ExtensionRegistry extReg, URI uri, BPELReader bpelReader) {

		String attStatement = ModelPackage.eINSTANCE
				.getDataManagementActivity_DsStatement().getName();
		String attKind = ModelPackage.eINSTANCE
				.getDataManagementActivity_DsKind().getName();
		String attType = ModelPackage.eINSTANCE
				.getDataManagementActivity_DsType().getName();
		String attAddress = ModelPackage.eINSTANCE
				.getDataManagementActivity_DsAddress().getName();
		String attLanguage = ModelPackage.eINSTANCE
				.getDataManagementActivity_DsLanguage().getName();

		/*
		 * QueryActivity
		 */
		if (DataManagementConstants.ND_QUERY_ACTIVITY.equals(elementType
				.getLocalPart())) {

			Element queryActivityElement = (Element) node;
			// create a QueryActivity model object
			QueryActivity activity = ModelFactory.eINSTANCE
					.createQueryActivity();
			// attach the DOM node to our new activity
			activity.setElement(queryActivityElement);

			// handle the QueryActivity attributes
			String attQueryTarget = ModelPackage.eINSTANCE
					.getQueryActivity_QueryTarget().getName();

			if (((Element) node).getAttribute(attStatement) != null) {
				activity.setDsStatement(((Element) node)
						.getAttribute(attStatement));
			}
			if (((Element) node).getAttribute(attKind) != null) {
				activity.setDsKind(((Element) node).getAttribute(attKind));
			}
			if (((Element) node).getAttribute(attType) != null) {
				activity.setDsType(((Element) node).getAttribute(attType));
			}
			if (((Element) node).getAttribute(attAddress) != null) {
				activity
						.setDsAddress(((Element) node).getAttribute(attAddress));
			}
			if (((Element) node).getAttribute(attLanguage) != null) {
				activity.setDsLanguage(((Element) node)
						.getAttribute(attLanguage));
			}
			if (((Element) node).getAttribute(attQueryTarget) != null) {
				activity.setQueryTarget(((Element) node)
						.getAttribute(attQueryTarget));
			}

			return activity;
		}

		/*
		 * InsertActivity
		 */
		if (DataManagementConstants.ND_INSERT_ACTIVITY.equals(elementType
				.getLocalPart())) {

			Element insertActivityElement = (Element) node;
			// create a InsertActivity model object
			InsertActivity activity = ModelFactory.eINSTANCE
					.createInsertActivity();
			// attach the DOM node to our new activity
			activity.setElement(insertActivityElement);

			// handle the InsertActivity attributes
			if (((Element) node).getAttribute(attStatement) != null) {
				activity.setDsStatement(((Element) node)
						.getAttribute(attStatement));
			}
			if (((Element) node).getAttribute(attKind) != null) {
				activity.setDsKind(((Element) node).getAttribute(attKind));
			}
			if (((Element) node).getAttribute(attType) != null) {
				activity.setDsType(((Element) node).getAttribute(attType));
			}
			if (((Element) node).getAttribute(attAddress) != null) {
				activity
						.setDsAddress(((Element) node).getAttribute(attAddress));
			}
			if (((Element) node).getAttribute(attLanguage) != null) {
				activity.setDsLanguage(((Element) node)
						.getAttribute(attLanguage));
			}

			return activity;
		}

		/*
		 * UpdateActivity
		 */
		if (DataManagementConstants.ND_UPDATE_ACTIVITY.equals(elementType
				.getLocalPart())) {

			Element updateActivityElement = (Element) node;
			// create a UpdateActivity model object
			UpdateActivity activity = ModelFactory.eINSTANCE
					.createUpdateActivity();
			// attach the DOM node to our new activity
			activity.setElement(updateActivityElement);

			// handle the UpdateActivity attributes
			if (((Element) node).getAttribute(attStatement) != null) {
				activity.setDsStatement(((Element) node)
						.getAttribute(attStatement));
			}
			if (((Element) node).getAttribute(attKind) != null) {
				activity.setDsKind(((Element) node).getAttribute(attKind));
			}
			if (((Element) node).getAttribute(attType) != null) {
				activity.setDsType(((Element) node).getAttribute(attType));
			}
			if (((Element) node).getAttribute(attAddress) != null) {
				activity
						.setDsAddress(((Element) node).getAttribute(attAddress));
			}
			if (((Element) node).getAttribute(attLanguage) != null) {
				activity.setDsLanguage(((Element) node)
						.getAttribute(attLanguage));
			}

			return activity;
		}

		/*
		 * DeleteActivity
		 */
		if (DataManagementConstants.ND_DELETE_ACTIVITY.equals(elementType
				.getLocalPart())) {

			Element deleteActivityElement = (Element) node;
			// create a DeleteActivity model object
			DeleteActivity activity = ModelFactory.eINSTANCE
					.createDeleteActivity();
			// attach the DOM node to our new activity
			activity.setElement(deleteActivityElement);

			// handle the DeleteActivity attributes
			if (((Element) node).getAttribute(attStatement) != null) {
				activity.setDsStatement(((Element) node)
						.getAttribute(attStatement));
			}
			if (((Element) node).getAttribute(attKind) != null) {
				activity.setDsKind(((Element) node).getAttribute(attKind));
			}
			if (((Element) node).getAttribute(attType) != null) {
				activity.setDsType(((Element) node).getAttribute(attType));
			}
			if (((Element) node).getAttribute(attAddress) != null) {
				activity
						.setDsAddress(((Element) node).getAttribute(attAddress));
			}
			if (((Element) node).getAttribute(attLanguage) != null) {
				activity.setDsLanguage(((Element) node)
						.getAttribute(attLanguage));
			}

			return activity;
		}

		/*
		 * CreateActivity
		 */
		if (DataManagementConstants.ND_CREATE_ACTIVITY.equals(elementType
				.getLocalPart())) {

			Element createActivityElement = (Element) node;
			// create a CreateActivity model object
			CreateActivity activity = ModelFactory.eINSTANCE
					.createCreateActivity();
			// attach the DOM node to our new activity
			activity.setElement(createActivityElement);

			// handle the CreateActivity attributes
			if (((Element) node).getAttribute(attStatement) != null) {
				activity.setDsStatement(((Element) node)
						.getAttribute(attStatement));
			}
			if (((Element) node).getAttribute(attKind) != null) {
				activity.setDsKind(((Element) node).getAttribute(attKind));
			}
			if (((Element) node).getAttribute(attType) != null) {
				activity.setDsType(((Element) node).getAttribute(attType));
			}
			if (((Element) node).getAttribute(attAddress) != null) {
				activity
						.setDsAddress(((Element) node).getAttribute(attAddress));
			}
			if (((Element) node).getAttribute(attLanguage) != null) {
				activity.setDsLanguage(((Element) node)
						.getAttribute(attLanguage));
			}

			return activity;
		}

		/*
		 * DropActivity
		 */
		if (DataManagementConstants.ND_DROP_ACTIVITY.equals(elementType
				.getLocalPart())) {

			Element dropActivityElement = (Element) node;
			// create a DropActivity model object
			DropActivity activity = ModelFactory.eINSTANCE.createDropActivity();
			// attach the DOM node to our new activity
			activity.setElement(dropActivityElement);

			// handle the DropActivity attributes
			if (((Element) node).getAttribute(attStatement) != null) {
				activity.setDsStatement(((Element) node)
						.getAttribute(attStatement));
			}
			if (((Element) node).getAttribute(attKind) != null) {
				activity.setDsKind(((Element) node).getAttribute(attKind));
			}
			if (((Element) node).getAttribute(attType) != null) {
				activity.setDsType(((Element) node).getAttribute(attType));
			}
			if (((Element) node).getAttribute(attAddress) != null) {
				activity
						.setDsAddress(((Element) node).getAttribute(attAddress));
			}
			if (((Element) node).getAttribute(attLanguage) != null) {
				activity.setDsLanguage(((Element) node)
						.getAttribute(attLanguage));
			}

			return activity;
		}

		/*
		 * CallActivity
		 */
		if (DataManagementConstants.ND_CALL_ACTIVITY.equals(elementType
				.getLocalPart())) {

			Element callActivityElement = (Element) node;
			// create a CallActivity model object
			CallActivity activity = ModelFactory.eINSTANCE.createCallActivity();
			// attach the DOM node to our new activity
			activity.setElement(callActivityElement);

			// handle the CallActivity attributes
			if (((Element) node).getAttribute(attStatement) != null) {
				activity.setDsStatement(((Element) node)
						.getAttribute(attStatement));
			}
			if (((Element) node).getAttribute(attKind) != null) {
				activity.setDsKind(((Element) node).getAttribute(attKind));
			}
			if (((Element) node).getAttribute(attType) != null) {
				activity.setDsType(((Element) node).getAttribute(attType));
			}
			if (((Element) node).getAttribute(attAddress) != null) {
				activity
						.setDsAddress(((Element) node).getAttribute(attAddress));
			}
			if (((Element) node).getAttribute(attLanguage) != null) {
				activity.setDsLanguage(((Element) node)
						.getAttribute(attLanguage));
			}

			return activity;
		}

		/*
		 * RetrieveDataActivity
		 */
		if (DataManagementConstants.ND_RETRIEVE_DATA_ACTIVITY
				.equals(elementType.getLocalPart())) {

			Element retrieveDataActivityElement = (Element) node;
			// create a RetrieveDataActivity model object
			RetrieveDataActivity activity = ModelFactory.eINSTANCE
					.createRetrieveDataActivity();
			// attach the DOM node to our new activity
			activity.setElement(retrieveDataActivityElement);

			// handle the RetrieveDataActivity attributes
			String attDataVariable = ModelPackage.eINSTANCE
					.getRetrieveDataActivity_DataVariable().getName();

			if (((Element) node).getAttribute(attStatement) != null) {
				activity.setDsStatement(((Element) node)
						.getAttribute(attStatement));
			}
			if (((Element) node).getAttribute(attKind) != null) {
				activity.setDsKind(((Element) node).getAttribute(attKind));
			}
			if (((Element) node).getAttribute(attType) != null) {
				activity.setDsType(((Element) node).getAttribute(attType));
			}
			if (((Element) node).getAttribute(attAddress) != null) {
				activity
						.setDsAddress(((Element) node).getAttribute(attAddress));
			}
			if (((Element) node).getAttribute(attLanguage) != null) {
				activity.setDsLanguage(((Element) node)
						.getAttribute(attLanguage));
			}
			if (((Element) node).getAttribute(attDataVariable) != null) {
				activity.setDataVariable(BPELReader.getVariable(activity,
						attDataVariable));
			}

			return activity;
		}

		/*
		 * TransferActivity
		 */
		if (DataManagementConstants.ND_TRANSFER_ACTIVITY.equals(elementType
				.getLocalPart())) {

			String attTargetContainer = ModelPackage.eINSTANCE
					.getTransferActivity_TargetDsContainer().getName();
			String attTargetKind = ModelPackage.eINSTANCE
					.getDataManagementActivity_DsKind().getName();
			String attTargetType = ModelPackage.eINSTANCE
					.getDataManagementActivity_DsType().getName();
			String attTargetAddress = ModelPackage.eINSTANCE
					.getDataManagementActivity_DsAddress().getName();
			String attTargetLanguage = ModelPackage.eINSTANCE
					.getDataManagementActivity_DsLanguage().getName();

			Element transferActivityElement = (Element) node;

			// create a TransferActivity model object
			TransferActivity activity = ModelFactory.eINSTANCE
					.createTransferActivity();
			// attach the DOM node to our new activity
			activity.setElement(transferActivityElement);

			// handle the TransferActivity attributes
			if (transferActivityElement.getAttribute(attStatement) != null) {
				activity.setDsStatement(transferActivityElement.getAttribute(attStatement));
			}
			if (transferActivityElement.getAttribute(attKind) != null) {
				activity.setDsKind(transferActivityElement.getAttribute(attKind));
			}
			if (transferActivityElement.getAttribute(attType) != null) {
				activity.setDsType(transferActivityElement.getAttribute(attType));
			}
			if (transferActivityElement.getAttribute(attAddress) != null) {
				activity.setDsAddress(transferActivityElement.getAttribute(attAddress));
			}
			if (transferActivityElement.getAttribute(attLanguage) != null) {
				activity.setDsLanguage(transferActivityElement.getAttribute(attLanguage));
			}
			if (transferActivityElement.getAttribute(attTargetContainer) != null) {
				activity.setTargetDsContainer(transferActivityElement.getAttribute(attTargetContainer));
			}
			if (transferActivityElement.getAttribute(attTargetKind) != null) {
				activity.setTargetDsKind(transferActivityElement.getAttribute(attTargetKind));
			}
			if (transferActivityElement.getAttribute(attTargetType) != null) {
				activity.setTargetDsType(transferActivityElement.getAttribute(attTargetType));
			}
			if (transferActivityElement.getAttribute(attTargetAddress) != null) {
				activity.setTargetDsAddress(transferActivityElement.getAttribute(attTargetAddress));
			}
			if (transferActivityElement.getAttribute(attTargetLanguage) != null) {
				activity.setTargetDsLanguage(transferActivityElement.getAttribute(attTargetLanguage));
			}
			
			return activity;
		}

		System.out.println("Cannot handle this kind of element");
		return null;
	}
}
