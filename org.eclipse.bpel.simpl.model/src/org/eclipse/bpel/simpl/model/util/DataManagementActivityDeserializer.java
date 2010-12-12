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
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.IssueActivity;
import org.eclipse.bpel.simpl.model.ModelFactory;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

// TODO: Auto-generated Javadoc
/**
 * The Class DataManagementActivityDeserializer.
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 */
public class DataManagementActivityDeserializer implements BPELActivityDeserializer {

  /*
   * (non-Javadoc)
   * @see org.eclipse.bpel.model.extensions.BPELActivityDeserializer#unmarshall
   * (javax.xml.namespace.QName, org.w3c.dom.Node, org.eclipse.bpel.model.Process,
   * java.util.Map, javax.wsdl.extensions.ExtensionRegistry,
   * org.eclipse.emf.common.util.URI, org.eclipse.bpel.model.resource.BPELReader)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Activity unmarshall(QName elementType, Node node, Process process, Map nsMap,
      ExtensionRegistry extReg, URI uri, BPELReader bpelReader) {

    /*
     * QueryActivity
     */
    if (DataManagementConstants.ND_QUERY_ACTIVITY.equals(elementType.getLocalPart())) {
      Element queryActivityElement = (Element) node;

      // create a QueryActivity model object
      QueryActivity activity = ModelFactory.eINSTANCE.createQueryActivity();

      // attach the DOM node to our new activity
      activity.setElement(queryActivityElement);

      // handle the DataManagementActivity attributes
      this.setCommonAttributes(activity, node);

      // handle the QueryActivity attributes
      String attQueryTarget = ModelPackage.eINSTANCE.getQueryActivity_QueryTarget()
          .getName();

      if (((Element) node).getAttribute(attQueryTarget) != null) {
        activity.setQueryTarget(((Element) node).getAttribute(attQueryTarget));
      }

      return activity;
    }

    /*
     * IssueActivity
     */
    if (DataManagementConstants.ND_ISSUE_ACTIVITY.equals(elementType.getLocalPart())) {
      Element issueActivityElement = (Element) node;

      // create a InsertActivity model object
      IssueActivity activity = ModelFactory.eINSTANCE.createIssueActivity();

      // attach the DOM node to our new activity
      activity.setElement(issueActivityElement);

      // handle the DataManagementActivity attributes
      this.setCommonAttributes(activity, node);

      return activity;
    }

    /*
     * RetrieveDataActivity
     */
    if (DataManagementConstants.ND_RETRIEVE_DATA_ACTIVITY.equals(elementType
        .getLocalPart())) {
      Element retrieveDataActivityElement = (Element) node;

      // create a RetrieveDataActivity model object
      RetrieveDataActivity activity = ModelFactory.eINSTANCE.createRetrieveDataActivity();

      // attach the DOM node to our new activity
      activity.setElement(retrieveDataActivityElement);

      // handle the DataManagementActivity attributes
      this.setCommonAttributes(activity, node);

      // handle the RetrieveDataActivity attributes
      String attDataVariable = ModelPackage.eINSTANCE
          .getRetrieveDataActivity_DataVariable().getName();
      String dataVariableName = ((Element) node).getAttribute(attDataVariable);

      if (dataVariableName != null) {
        activity.setDataVariable(BPELReader.getVariable(process.getVariables(),
            dataVariableName));
      }

      return activity;
    }

    /*
     * WriteDataBackDataActivity
     */
    if (DataManagementConstants.ND_WRITE_DATA_BACK_ACTIVITY.equals(elementType
        .getLocalPart())) {
      Element writeDataBackActivityElement = (Element) node;

      // create a WriteDataBackActivity model object
      WriteDataBackActivity activity = ModelFactory.eINSTANCE
          .createWriteDataBackActivity();

      // attach the DOM node to our new activity
      activity.setElement(writeDataBackActivityElement);

      // handle the DataManagementActivity attributes
      this.setCommonAttributes(activity, node);

      // handle the WriteDataBackDataActivity attributes
      String attDataVariable = ModelPackage.eINSTANCE
          .getWriteDataBackActivity_DataVariable().getName();
      String dataVariableName = ((Element) node).getAttribute(attDataVariable);

      if (dataVariableName != null) {
        activity.setDataVariable(BPELReader.getVariable(process.getVariables(),
            dataVariableName));
      }

      return activity;
    }

    /*
     * TransferActivity
     */
    if (DataManagementConstants.ND_TRANSFER_ACTIVITY.equals(elementType.getLocalPart())) {
      Element transferActivityElement = (Element) node;

      // create a TransferActivity model object
      TransferActivity activity = ModelFactory.eINSTANCE.createTransferActivity();

      // attach the DOM node to our new activity
      activity.setElement(transferActivityElement);

      // handle the DataManagementActivity attributes
      this.setCommonAttributes(activity, node);

      // handle the TransferActivity attributes
      String attTargetContainer = ModelPackage.eINSTANCE
          .getTransferActivity_TargetDsContainer().getName();
      String attTargetKind = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind()
          .getName();
      String attTargetType = ModelPackage.eINSTANCE.getDataManagementActivity_DsType()
          .getName();
      String attTargetAddress = ModelPackage.eINSTANCE
          .getDataManagementActivity_DsAddress().getName();
      String attTargetLanguage = ModelPackage.eINSTANCE
          .getDataManagementActivity_DsLanguage().getName();

      if (transferActivityElement.getAttribute(attTargetContainer) != null) {
        activity.setTargetDsContainer(transferActivityElement
            .getAttribute(attTargetContainer));
      }

      if (transferActivityElement.getAttribute(attTargetKind) != null) {
        activity.setTargetDsKind(transferActivityElement.getAttribute(attTargetKind));
      }

      if (transferActivityElement.getAttribute(attTargetType) != null) {
        activity.setTargetDsType(transferActivityElement.getAttribute(attTargetType));
      }

      if (transferActivityElement.getAttribute(attTargetAddress) != null) {
        activity.setTargetDsAddress(transferActivityElement
            .getAttribute(attTargetAddress));
      }

      if (transferActivityElement.getAttribute(attTargetLanguage) != null) {
        activity.setTargetDsLanguage(transferActivityElement
            .getAttribute(attTargetLanguage));
      }

      return activity;
    }

    System.out.println("Cannot handle this kind of element");
    return null;
  }

  /**
   * Sets the common data management activity attributes from the DOM node on an activity
   * object.
   * 
   * @param activity
   * @param node
   */
  private void setCommonAttributes(DataManagementActivity activity, Node node) {
    String attStatement = ModelPackage.eINSTANCE.getDataManagementActivity_DsStatement()
        .getName();
    String attKind = ModelPackage.eINSTANCE.getDataManagementActivity_DsKind().getName();
    String attType = ModelPackage.eINSTANCE.getDataManagementActivity_DsType().getName();
    String attAddress = ModelPackage.eINSTANCE.getDataManagementActivity_DsAddress()
        .getName();
    String attLanguage = ModelPackage.eINSTANCE.getDataManagementActivity_DsLanguage()
        .getName();

    if (((Element) node).getAttribute(attStatement) != null) {
      activity.setDsStatement(((Element) node).getAttribute(attStatement));
    }

    if (((Element) node).getAttribute(attKind) != null) {
      activity.setDsKind(((Element) node).getAttribute(attKind));
    }

    if (((Element) node).getAttribute(attType) != null) {
      activity.setDsType(((Element) node).getAttribute(attType));
    }

    if (((Element) node).getAttribute(attAddress) != null) {
      activity.setDsAddress(((Element) node).getAttribute(attAddress));
    }

    if (((Element) node).getAttribute(attLanguage) != null) {
      activity.setDsLanguage(((Element) node).getAttribute(attLanguage));
    }
  }
}
