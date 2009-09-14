package org.eclipse.bpel.dmextension.model.util;

import java.util.Map;

import javax.wsdl.extensions.ExtensionRegistry;
import javax.xml.namespace.QName;

import org.eclipse.bpel.dmextension.model.CallProcedureActivity;
import org.eclipse.bpel.dmextension.model.CreateActivity;
import org.eclipse.bpel.dmextension.model.DataManagementActivity;
import org.eclipse.bpel.dmextension.model.DeleteActivity;
import org.eclipse.bpel.dmextension.model.InsertActivity;
import org.eclipse.bpel.dmextension.model.ModelFactory;
import org.eclipse.bpel.dmextension.model.RetrieveSetActivity;
import org.eclipse.bpel.dmextension.model.SelectActivity;
import org.eclipse.bpel.dmextension.model.UpdateActivity;
import org.eclipse.bpel.dmextension.model.WriteBackActivity;
import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.extensions.BPELActivityDeserializer;
import org.eclipse.bpel.model.resource.BPELReader;
import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataManagementActivityDeserializer implements BPELActivityDeserializer{

	@Override
	public Activity unmarshall(QName elementType, Node node, Process process, Map nsMap,
			ExtensionRegistry extReg, URI uri, BPELReader bpelReader) {

		/*
		 * DataManagementActivity
		 */
		if (DMExtensionConstants.ND_DATA_MANAGEMENT_ACTIVITY.equals(elementType.getLocalPart())) {

			Element dataManagementActivityElement = (Element) node;
			// create a DataManagementActivity model object
			DataManagementActivity activity = ModelFactory.eINSTANCE
					.createDataManagementActivity();
			// attach the DOM node to our new activity
			activity.setElement(dataManagementActivityElement);
			return activity;
		}

		/*
		 * CallProcedureActivity
		 */
		if (DMExtensionConstants.ND_CALL_PROCEDURE_ACTIVITY.equals(elementType.getLocalPart())) {

			Element callProcedureActivityElement = (Element) node;
			// create a CallProcedureActivity model object
			CallProcedureActivity activity = ModelFactory.eINSTANCE
					.createCallProcedureActivity();
			// attach the DOM node to our new activity
			activity.setElement(callProcedureActivityElement);
			return activity;
		}

		/*
		 * CreateActivity
		 */
		if (DMExtensionConstants.ND_CREATE_ACTIVITY.equals(elementType.getLocalPart())) {

			Element createActivityElement = (Element) node;
			// create a CreateActivity model object
			CreateActivity activity = ModelFactory.eINSTANCE
					.createCreateActivity();
			// attach the DOM node to our new activity
			activity.setElement(createActivityElement);
			return activity;
		}
		
		/*
		 * DeleteActivity
		 */
		if (DMExtensionConstants.ND_DELETE_ACTIVITY.equals(elementType.getLocalPart())) {

			Element deleteActivityElement = (Element) node;
			// create a DeleteActivity model object
			DeleteActivity activity = ModelFactory.eINSTANCE
					.createDeleteActivity();
			// attach the DOM node to our new activity
			activity.setElement(deleteActivityElement);
			return activity;
		}
		
		/*
		 * InsertActivity
		 */
		if (DMExtensionConstants.ND_INSERT_ACTIVITY.equals(elementType.getLocalPart())) {

			Element insertActivityElement = (Element) node;
			// create a InsertActivity model object
			InsertActivity activity = ModelFactory.eINSTANCE
					.createInsertActivity();
			// attach the DOM node to our new activity
			activity.setElement(insertActivityElement);
			return activity;
		}
		
		/*
		 * RetrieveSetActivity
		 */
		if (DMExtensionConstants.ND_RETRIEVE_SET_ACTIVITY.equals(elementType.getLocalPart())) {

			Element retrieveSetActivityElement = (Element) node;
			// create a RetrieveSetActivity model object
			RetrieveSetActivity activity = ModelFactory.eINSTANCE
					.createRetrieveSetActivity();
			// attach the DOM node to our new activity
			activity.setElement(retrieveSetActivityElement);
			return activity;
		}
		
		/*
		 * SelectActivity
		 */
		if (DMExtensionConstants.ND_SELECT_ACTIVITY.equals(elementType.getLocalPart())) {

			Element selectActivityElement = (Element) node;
			// create a SelectActivity model object
			SelectActivity activity = ModelFactory.eINSTANCE
					.createSelectActivity();
			// attach the DOM node to our new activity
			activity.setElement(selectActivityElement);
			return activity;
		}
		
		/*
		 * UpdateActivity
		 */
		if (DMExtensionConstants.ND_UPDATE_ACTIVITY.equals(elementType.getLocalPart())) {

			Element updateActivityElement = (Element) node;
			// create a UpdateActivity model object
			UpdateActivity activity = ModelFactory.eINSTANCE
					.createUpdateActivity();
			// attach the DOM node to our new activity
			activity.setElement(updateActivityElement);
			return activity;
		}
		
		/*
		 * WriteBackActivity
		 */
		if (DMExtensionConstants.ND_WRITE_BACK_ACTIVITY.equals(elementType.getLocalPart())) {

			Element writeBackActivityElement = (Element) node;
			// create a WriteBackActivity model object
			WriteBackActivity activity = ModelFactory.eINSTANCE
					.createWriteBackActivity();
			// attach the DOM node to our new activity
			activity.setElement(writeBackActivityElement);
			return activity;
		}
		
		System.out.println("Cannot handle this kind of element");
		return null;
	}
}
