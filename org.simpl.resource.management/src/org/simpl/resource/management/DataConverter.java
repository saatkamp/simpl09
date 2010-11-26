package org.simpl.resource.management;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Represents a tuple of the resource management dataconverters table.<br>
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
@XmlRootElement(name = "DataConverter")
@XmlType(name = "DataConverter", propOrder = { "id", "name",
    "dataSourceConnectorDataFormat", "workflowDataFormat" })
public class DataConverter {
  private String id;
  private String name;
  private String dataSourceConnectorDataFormat;
  private String workflowDataFormat;

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
   * @return the dataSourceConnectorDataFormat
   */
  public String getDataSourceConnectorDataFormat() {
    return dataSourceConnectorDataFormat;
  }

  /**
   * @param dataSourceConnectorDataFormat
   *          the dataSourceConnectorDataFormat to set
   */
  public void setDataSourceConnectorDataFormat(String dataSourceConnectorDataFormat) {
    this.dataSourceConnectorDataFormat = dataSourceConnectorDataFormat;
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

  @Override
  public String toString() {
    String string = "";
    string += "DataConverter {\n\r";
    string += "  id: " + this.id + ",\n\r";
    string += "  name: " + this.name + ",\n\r";
    string += "  datasourceconnector_dataformat: " + this.dataSourceConnectorDataFormat
        + ",\n\r";
    string += "  workflow_dataformat: " + this.workflowDataFormat + "\n\r";
    string += "}";

    return string;
  }
}