package org.simpl.resource.management.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Data type for a data converter that is used convert retrieved data from
 * a data source to XML.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: DataConverter.java 1822 2011-08-04 17:25:42Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataConverter", propOrder = { "id", "name", "inputDataType",
    "outputDataType", "workflowDataFormat", "directionOutputWorkflow",
    "directionWorkflowInput", "implementation"})
public class DataConverter {
  private String id;
  private String name;
  private String inputDataType;
  private String outputDataType;
  private String workflowDataFormat;
  private String directionOutputWorkflow;
  private String directionWorkflowInput;
  private String implementation;

  public DataConverter() {

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
   * @return the inputDataType
   */
  public String getInputDataType() {
    return inputDataType;
  }

  /**
   * @param inputDataType
   *          the inputDataType to set
   */
  public void setInputDataType(String inputDataType) {
    this.inputDataType = inputDataType;
  }

  /**
   * @return the outputDataType
   */
  public String getOutputDataType() {
    return outputDataType;
  }

  /**
   * @param outputDataType
   *          the outputDataType to set
   */
  public void setOutputDataType(String outputDataType) {
    this.outputDataType = outputDataType;
  }

  /**
   * @return the workflowDataFormat
   */
  public String getWorkflowDataFormat() {
    return workflowDataFormat;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setWorkflowDataFormat(String workflowDataFormat) {
    this.workflowDataFormat = workflowDataFormat;
  }

  /**
   * @return the directionOutputWorkflow
   */
  public String getDirectionOutputWorkflow() {
    return directionOutputWorkflow;
  }

  /**
   * @param directionOutputWorkflow
   *          the directionOutputWorkflow to set
   */
  public void setDirectionOutputWorkflow(String directionOutputWorkflow) {
    this.directionOutputWorkflow = directionOutputWorkflow;
  }

  /**
   * @return the directionWorkflowInput
   */
  public String getDirectionWorkflowInput() {
    return directionWorkflowInput;
  }

  /**
   * @param directionWorkflowInput
   *          the directionWorkflowInput to set
   */
  public void setDirectionWorkflowInput(String directionWorkflowInput) {
    this.directionWorkflowInput = directionWorkflowInput;
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

    string += "\r\nDataConverter {\r\n";
    string += "\tid: " + this.id + ",\r\n";
    string += "\tname: " + this.name + ",\r\n";
    string += "\tinputDataType: " + this.inputDataType + ",\r\n";
    string += "\toutputDataType: " + this.outputDataType + ",\r\n";
    string += "\tworkflowDataFormat: " + this.workflowDataFormat + ",\r\n";
    string += "\tdirectionOutputWorkflow: " + this.directionOutputWorkflow + ",\r\n";
    string += "\tdirectionWorkflowInput: " + this.directionWorkflowInput + ",\r\n";
    string += "\timplementation: " + this.implementation + ",\r\n";
    string += "}";

    return string;
  }
}