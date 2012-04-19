/**
 * <b>Purpose:</b> This class implements the {@link BPELActivityDeserializer} interface for the deserialization
 * of all SIMPL Activities. <br>
 * <b>Description:</b> Provides the deserialization of all DataManagement activities.<br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: DataManagementActivityDeserializer.java 1807 2011-05-12 09:27:15Z michael.schneidt@arcor.de $ <br>
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
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.ModelFactory;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
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
public class DataManagementActivityDeserializer implements
    BPELActivityDeserializer {

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.model.extensions.BPELActivityDeserializer#unmarshall
   * (javax.xml.namespace.QName, org.w3c.dom.Node,
   * org.eclipse.bpel.model.Process, java.util.Map,
   * javax.wsdl.extensions.ExtensionRegistry, org.eclipse.emf.common.util.URI,
   * org.eclipse.bpel.model.resource.BPELReader)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Activity unmarshall(QName elementType, Node node, Process process,
      Map nsMap, ExtensionRegistry extReg, URI uri, BPELReader bpelReader) {

    /*
     * QueryDataActivity
     */
    if (DataManagementConstants.ND_QUERY_DATA_ACTIVITY.equals(elementType
        .getLocalPart())) {
      Element querydataActivityElement = (Element) node;

      // create a QueryDataActivity model object
      QueryDataActivity activity = ModelFactory.eINSTANCE
          .createQueryDataActivity();

      // attach the DOM node to our new activity
      activity.setElement(querydataActivityElement);

      // handle the DataManagementActivity attributes
      this.setCommonAttributes(activity, node);

      // handle the QueryDataActivity attributes
      String attTargetContainer = ModelPackage.eINSTANCE
          .getQueryDataActivity_TargetContainer().getName();

      if (((Element) node).getAttribute(attTargetContainer) != null) {
        activity.setTargetContainer(((Element) node)
            .getAttribute(attTargetContainer));
      }

      return activity;
    }

    /*
     * IssueCommandActivity
     */
    if (DataManagementConstants.ND_ISSUE_COMMAND_ACTIVITY.equals(elementType
        .getLocalPart())) {
      Element issueActivityElement = (Element) node;

      // create a InsertActivity model object
      IssueCommandActivity activity = ModelFactory.eINSTANCE
          .createIssueCommandActivity();

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
      RetrieveDataActivity activity = ModelFactory.eINSTANCE
          .createRetrieveDataActivity();

      // attach the DOM node to our new activity
      activity.setElement(retrieveDataActivityElement);

      // handle the DataManagementActivity attributes
      this.setCommonAttributes(activity, node);

      // handle the RetrieveDataActivity attributes
      String attTargetVariable = ModelPackage.eINSTANCE
          .getRetrieveDataActivity_TargetVariable().getName();
      String targetVariableName = ((Element) node)
          .getAttribute(attTargetVariable);

      if (targetVariableName != null) {
        activity.setTargetVariable(BPELReader.getVariable(
            process.getVariables(), targetVariableName));
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

      String attTargetContainer = ModelPackage.eINSTANCE
          .getWriteDataBackActivity_TargetContainer().getName();

      if (((Element) node).getAttribute(attTargetContainer) != null) {
        activity.setTargetContainer(((Element) node)
            .getAttribute(attTargetContainer));
      }

      return activity;
    }

    /*
     * TransferDataActivity
     */
    if (DataManagementConstants.ND_TRANSFER_DATA_ACTIVITY.equals(elementType
        .getLocalPart())) {
      Element transferDataActivityElement = (Element) node;

      // create a TransferDataActivity model object
      TransferDataActivity activity = ModelFactory.eINSTANCE
          .createTransferDataActivity();

      // attach the DOM node to our new activity
      activity.setElement(transferDataActivityElement);

      // handle the DataManagementActivity attributes
      this.setCommonAttributes(activity, node);

      // handle the TransferDataActivity attributes
      String attDataSource = ModelPackage.eINSTANCE
          .getTransferDataActivity_DataSource().getName();
      String attDataSourceCommand = ModelPackage.eINSTANCE
          .getTransferDataActivity_DataSourceCommand().getName();
      String attDataSink = ModelPackage.eINSTANCE
          .getTransferDataActivity_DataSink().getName();
      String attDataSinkContainer = ModelPackage.eINSTANCE
          .getTransferDataActivity_DataSinkContainer().getName();

      if (transferDataActivityElement.getAttribute(attDataSource) != null) {
        activity.setDataSource(transferDataActivityElement
            .getAttribute(attDataSource));
      }

      if (transferDataActivityElement.getAttribute(attDataSourceCommand) != null) {
        activity.setDataSourceCommand(transferDataActivityElement
            .getAttribute(attDataSourceCommand));
        // therefore you must not differ between TransferDataActivity and other
        // (DataManagement)Activites
        activity.setDmCommand(transferDataActivityElement
            .getAttribute(attDataSourceCommand));
      }

      if (transferDataActivityElement.getAttribute(attDataSink) != null) {
        activity.setDataSink(transferDataActivityElement
            .getAttribute(attDataSink));
      }

      if (transferDataActivityElement.getAttribute(attDataSinkContainer) != null) {
        activity.setDataSinkContainer(transferDataActivityElement
            .getAttribute(attDataSinkContainer));
      }

      return activity;
    }

    System.out.println("Cannot handle this kind of element");
    return null;
  }

  /**
   * Sets the common data management activity attributes from the DOM node on an
   * activity object.
   * 
   * @param activity
   * @param node
   */
  private void setCommonAttributes(DataManagementActivity activity, Node node) {
    String attDataResource = ModelPackage.eINSTANCE
        .getDataManagementActivity_DataResource().getName();
    String attDMCommand = ModelPackage.eINSTANCE
        .getDataManagementActivity_DmCommand().getName();

    if (((Element) node).getAttribute(attDataResource) != null) {
      activity.setDataResource(((Element) node).getAttribute(attDataResource));
    }

    if (((Element) node).getAttribute(attDMCommand) != null) {
      activity.setDmCommand(((Element) node).getAttribute(attDMCommand));
    }
  }
}
