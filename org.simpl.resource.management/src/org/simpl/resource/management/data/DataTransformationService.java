package org.simpl.resource.management.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Data type for a data converter that is used to convert between data
 * formats.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: DataTransformationService.java 1815 2011-07-05 12:33:12Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataTransformationService", propOrder = { "id", "name",
    "connectorDataFormat", "workflowDataFormat", "directionConnectorWorkflow",
    "directionWorkflowConnector", "implementation" })
public class DataTransformationService {
  private String id;
  private String name;
  private String connectorDataFormat;
  private String workflowDataFormat;
  private String directionConnectorWorkflow;
  private String directionWorkflowConnector;
  private String implementation;

  public DataTransformationService() {

  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the connectorDataFormat
   */
  public String getConnectorDataFormat() {
    return connectorDataFormat;
  }

  /**
   * @param connectorDataFormat
   *          the connectorDataFormat to set
   */
  public void setConnectorDataFormat(String connectorDataFormat) {
    this.connectorDataFormat = connectorDataFormat;
  }

  /**
   * @return the workflowDataFormat
   */
  public String getWorkflowDataFormat() {
    return workflowDataFormat;
  }

  /**
   * @param workflowDataFormat
   *          the workflowDataFormat to set
   */
  public void setWorkflowDataFormat(String workflowDataFormat) {
    this.workflowDataFormat = workflowDataFormat;
  }

  /**
   * @return the directionConnectorWorkflow
   */
  public String getDirectionConnectorWorkflow() {
    return directionConnectorWorkflow;
  }

  /**
   * @param directionConnectorWorkflow
   *          the directionConnectorWorkflow to set
   */
  public void setDirectionConnectorWorkflow(String directionConnectorWorkflow) {
    this.directionConnectorWorkflow = directionConnectorWorkflow;
  }

  /**
   * @return the directionWorkflowConnector
   */
  public String getDirectionWorkflowConnector() {
    return directionWorkflowConnector;
  }

  /**
   * @param directionWorkflowConnector
   *          the directionWorkflowConnector to set
   */
  public void setDirectionWorkflowConnector(String directionWorkflowConnector) {
    this.directionWorkflowConnector = directionWorkflowConnector;
  }

  /**
   * @return the implementation
   */
  public String getImplementation() {
    return implementation;
  }

  /**
   * @param implementation
   *          the implementation to set
   */
  public void setImplementation(String implementation) {
    this.implementation = implementation;
  }

  @Override
  public String toString() {
    String string = "";
    string += "\r\nData Transformation Service {\r\n";
    string += "\tid: " + this.id + ",\r\n";
    string += "\tname: " + this.name + ",\r\n";
    string += "\tconnectorDataFormat: " + this.connectorDataFormat + ",\r\n";
    string += "\tworkflowDataFormat: " + this.workflowDataFormat + ",\r\n";
    string += "\tdirectionConnectorWorkflow: " + this.directionConnectorWorkflow
        + ",\r\n";
    string += "\tdirectionWorkflowConnector: " + this.directionWorkflowConnector
        + ",\r\n";
    string += "\timplementation: " + this.name + ",\r\n";
    string += "}";

    return string;
  }
}