package org.eclipse.bpel.simpl.model.util;

import javax.xml.namespace.QName;

import org.eclipse.bpel.simpl.model.CallActivity;
import org.eclipse.bpel.simpl.model.CreateActivity;
import org.eclipse.bpel.simpl.model.DeleteActivity;
import org.eclipse.bpel.simpl.model.InsertActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.model.UpdateActivity;
import org.eclipse.bpel.simpl.model.DropActivity;
import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.extensions.BPELActivitySerializer;
import org.eclipse.bpel.model.resource.BPELWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class implements the {@link BPELActivitySerializer} interface for the serialization
 * of all SIMPL Activities.
 * 
 * @author hahnml
 *
 */
public class DataManagementActivitySerializer implements BPELActivitySerializer {

	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.model.extensions.BPELActivitySerializer#marshall(javax.xml.namespace.QName, org.eclipse.bpel.model.Activity, org.w3c.dom.Node, org.eclipse.bpel.model.Process, org.eclipse.bpel.model.resource.BPELWriter)
	 */
	@Override
	public void marshall(QName elementType, Activity activity, Node parentNode, Process process,
			BPELWriter bpelWriter) {

		Document document = parentNode.getOwnerDocument();

		/*
		 * QueryActivity
		 */
		if (activity instanceof QueryActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DataManagementConstants.ND_QUERY_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the QueryActivity Attributes
			if (((QueryActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsStatement());
			}
			
			if (((QueryActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsKind());
			}
			
			if (((QueryActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsType());
			}
			
			if (((QueryActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsAddress());
			}
			
			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * InsertActivity
		 */
		if (activity instanceof InsertActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DataManagementConstants.ND_INSERT_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));
			
			// handle the InsertActivity Attributes
			if (((InsertActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsStatement());
			}
			
			if (((InsertActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsKind());
			}
			
			if (((InsertActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsType());
			}
			
			if (((InsertActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsAddress());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * UpdateActivity
		 */
		if (activity instanceof UpdateActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DataManagementConstants.ND_UPDATE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));
			
			// handle the UpdateActivity Attributes
			if (((UpdateActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsStatement());
			}
			
			if (((UpdateActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsKind());
			}
			
			if (((UpdateActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsType());
			}
			
			if (((UpdateActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsAddress());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * DeleteActivity
		 */
		if (activity instanceof DeleteActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DataManagementConstants.ND_DELETE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));
			
			// handle the DeleteActivity Attributes
			if (((DeleteActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsStatement());
			}
			
			if (((DeleteActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsKind());
			}
			
			if (((DeleteActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsType());
			}
			
			if (((DeleteActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsAddress());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * CreateActivity
		 */
		if (activity instanceof CreateActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DataManagementConstants.ND_CREATE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));
			
			// handle the CreateActivity Attributes
			if (((CreateActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsStatement());
			}
			
			if (((CreateActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsKind());
			}
			
			if (((CreateActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsType());
			}
			
			if (((CreateActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsAddress());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * DropActivity
		 */
		if (activity instanceof DropActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DataManagementConstants.ND_DROP_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));
			
			// handle the DropActivity Attributes
			if (((DropActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((DropActivity) activity).getDsStatement());
			}
			
			if (((DropActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((DropActivity) activity).getDsKind());
			}
			
			if (((DropActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((DropActivity) activity).getDsType());
			}
			
			if (((DropActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((DropActivity) activity).getDsAddress());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * CallActivity
		 */
		if (activity instanceof CallActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DataManagementConstants.ND_CALL_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));
			
			// handle the CallActivity Attributes
			if (((CallActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((CallActivity) activity).getDsStatement());
			}
			
			if (((CallActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((CallActivity) activity).getDsKind());
			}
			
			if (((CallActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((CallActivity) activity).getDsType());
			}
			
			if (((CallActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((CallActivity) activity).getDsAddress());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
		
		/*
		 * RetrieveDataActivity
		 */
		if (activity instanceof RetrieveDataActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(), DataManagementConstants.ND_RETRIEVE_DATA_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));
			
			// handle the RetrieveDataActivity Attributes
			if (((RetrieveDataActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsStatement());
			}
			
			if (((RetrieveDataActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsKind());
			}
			
			if (((RetrieveDataActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsType());
			}
			
			if (((RetrieveDataActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsAddress());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}	
	}

}
