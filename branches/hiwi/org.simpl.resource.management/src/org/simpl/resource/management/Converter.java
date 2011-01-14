package org.simpl.resource.management;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Represents a tuple of the resource management 'converters' table.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Converter")
@XmlType(name = "Converter", propOrder = { "id", "name", "implementation",
    "connectorDataFormatName", "connectorDataFormatImplementation",
    "workflowDataFormatName", "workflowDataFormatImplementation" })
public class Converter {
  private String id;
  private String name;
  private String implementation;
  private String connectorDataFormatName;
  private String connectorDataFormatImplementation;
  private String workflowDataFormatName;
  private String workflowDataFormatImplementation;

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

  /**
   * @return the connectorDataFormatName
   */
  public String getConnectorDataFormatName() {
    return connectorDataFormatName;
  }

  /**
   * @param connectorDataFormatName
   *          the connectorDataFormatName to set
   */
  public void setConnectorDataFormatName(String connectorDataFormatName) {
    this.connectorDataFormatName = connectorDataFormatName;
  }

  /**
   * @return the connectorDataFormatImplementation
   */
  public String getConnectorDataFormatImplementation() {
    return connectorDataFormatImplementation;
  }

  /**
   * @param connectorDataFormatImplementation
   *          the connectorDataFormatImplementation to set
   */
  public void setConnectorDataFormatImplementation(
      String connectorDataFormatImplementation) {
    this.connectorDataFormatImplementation = connectorDataFormatImplementation;
  }

  /**
   * @return the workflowDataFormatName
   */
  public String getWorkflowDataFormatName() {
    return workflowDataFormatName;
  }

  /**
   * @param workflowDataFormatName
   *          the workflowDataFormatName to set
   */
  public void setWorkflowDataFormatName(String workflowDataFormatName) {
    this.workflowDataFormatName = workflowDataFormatName;
  }

  /**
   * @return the workflowDataFormatImplementation
   */
  public String getWorkflowDataFormatImplementation() {
    return workflowDataFormatImplementation;
  }

  /**
   * @param workflowDataFormatImplementation
   *          the workflowDataFormatImplementation to set
   */
  public void setWorkflowDataFormatImplementation(String workflowDataFormatImplementation) {
    this.workflowDataFormatImplementation = workflowDataFormatImplementation;
  }

  @Override
  public String toString() {
    String string = "";
    string += "Converter {\n\r";
    string += "  id: " + this.id + ",\n\r";
    string += "  name: " + this.name + ",\n\r";
    string += "  implementation: " + this.name + ",\n\r";
    string += "  connector_dataformat_name: " + this.connectorDataFormatName + ",\n\r";
    string += "  connector_dataformat_implementation: "
        + this.connectorDataFormatImplementation + ",\n\r";
    string += "  workflow_dataformat_name: " + this.workflowDataFormatName + "\n\r";
    string += "  workflow_dataformat_implementation: "
        + this.workflowDataFormatImplementation + "\n\r";
    string += "}";

    return string;
  }
}