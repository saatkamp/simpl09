package org.simpl.core.services.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>A data converter to convert between data formats.<br>
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
    "connectorDataFormat", "workflowDataFormat" })
public class Converter {
  private String id;
  private String name;
  private String implementation;
  private DataFormat connectorDataFormat = new DataFormat();
  private DataFormat workflowDataFormat = new DataFormat();

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
   * @return the connectorDataFormat
   */
  public DataFormat getConnectorDataFormat() {
    return connectorDataFormat;
  }

  /**
   * @param connectorDataFormat
   *          the connectorDataFormat to set
   */
  public void setConnectorDataFormat(DataFormat connectorDataFormat) {
    this.connectorDataFormat = connectorDataFormat;
  }

  /**
   * @return the workflowDataFormat
   */
  public DataFormat getWorkflowDataFormat() {
    return workflowDataFormat;
  }

  /**
   * @param workflowDataFormat
   *          the workflowDataFormat to set
   */
  public void setWorkflowDataFormat(DataFormat workflowDataFormat) {
    this.workflowDataFormat = workflowDataFormat;
  }

  @Override
  public String toString() {
    String string = "";
    string += "Converter {\n\r";
    string += "  id: " + this.id + ",\n\r";
    string += "  name: " + this.name + ",\n\r";
    string += "  implementation: " + this.name + ",\n\r";
    string += "  connectorDataformat: " + this.connectorDataFormat + ",\n\r";
    string += "  workflowDataformat: " + this.workflowDataFormat + "\n\r";
    string += "}";

    return string;
  }
}