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
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataTransformationService", propOrder = { "id", "name", "implementation",
    "connectorDataConverter", "workflowDataConverter" })
public class DataTransformationService {
  private String id;
  private String name;
  private String implementation;
  private DataConverter connectorDataConverter = new DataConverter();
  private DataConverter workflowDataConverter = new DataConverter();

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
   * @return the connectorDataConverter
   */
  public DataConverter getConnectorDataConverter() {
    return connectorDataConverter;
  }

  /**
   * @param connectorDataConverter
   *          the connectorDataConverter to set
   */
  public void setConnectorDataConverter(DataConverter connectorDataConverter) {
    this.connectorDataConverter = connectorDataConverter;
  }

  /**
   * @return the workflowDataConverter
   */
  public DataConverter getWorkflowDataConverter() {
    return workflowDataConverter;
  }

  /**
   * @param workflowDataConverter
   *          the workflowDataConverter to set
   */
  public void setWorkflowDataConverter(DataConverter workflowDataConverter) {
    this.workflowDataConverter = workflowDataConverter;
  }

  @Override
  public String toString() {
    String string = "";
    string += "\r\nConverter {\r\n";
    string += "\tid: " + this.id + ",\r\n";
    string += "\tname: " + this.name + ",\r\n";
    string += "\timplementation: " + this.name + ",\r\n";
    string += "\tconnectorDataformat: " + this.connectorDataConverter + ",\r\n";
    string += "\tworkflowDataformat: " + this.workflowDataConverter + "\r\n";
    string += "}";

    return string;
  }
}