package org.eclipse.bpel.dmextension.model.util;

import javax.xml.namespace.QName;

import org.eclipse.bpel.dmextension.model.CallProcedureActivity;
import org.eclipse.bpel.dmextension.model.CreateActivity;
import org.eclipse.bpel.dmextension.model.DataManagementActivity;
import org.eclipse.bpel.dmextension.model.DeleteActivity;
import org.eclipse.bpel.dmextension.model.InsertActivity;
import org.eclipse.bpel.dmextension.model.ModelPackage;
import org.eclipse.bpel.dmextension.model.RetrieveSetActivity;
import org.eclipse.bpel.dmextension.model.SelectActivity;
import org.eclipse.bpel.dmextension.model.UpdateActivity;
import org.eclipse.bpel.dmextension.model.WriteBackActivity;
import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.extensions.BPELActivitySerializer;
import org.eclipse.bpel.model.resource.BPELWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DataManagementActivitySerializer implements BPELActivitySerializer {

	
	@Override
	public void marshall(QName elementType, Activity activity, Node parentNode, Process process,
			BPELWriter bpelWriter) {

		Document document = parentNode.getOwnerDocument();

		/*
		 * DataManagementActivity
		 */
		if (activity instanceof DataManagementActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_DATA_MANAGEMENT_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the DataManagement Attributes
			if (((DataManagementActivity) activity).getKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_Kind().getName();
				activityElement.setAttribute(attName,
						((DataManagementActivity) activity).getKind());
			}
			
			if (((DataManagementActivity) activity).getStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_Statement().getName();
				activityElement.setAttribute(attName,
						((DataManagementActivity) activity).getStatement());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * CallProcedureActivity
		 */
		if (activity instanceof CallProcedureActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_CALL_PROCEDURE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * CreateActivity
		 */
		if (activity instanceof CreateActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_CREATE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * DeleteActivity
		 */
		if (activity instanceof DeleteActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_DELETE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * InsertActivity
		 */
		if (activity instanceof InsertActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_INSERT_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * RetrieveSetActivity
		 */
		if (activity instanceof RetrieveSetActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_RETRIEVE_SET_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * SelectActivity
		 */
		if (activity instanceof SelectActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_SELECT_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * UpdateActivity
		 */
		if (activity instanceof UpdateActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_UPDATE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * WriteBackActivity
		 */
		if (activity instanceof WriteBackActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DMExtensionConstants.ND_WRITE_BACK_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
	}

}
