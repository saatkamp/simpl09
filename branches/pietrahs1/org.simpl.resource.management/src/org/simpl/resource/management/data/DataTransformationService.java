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
    "inputDataFormat", "outputDataFormat", "directionInputOutput",
    "directionOutputInput", "implementation" })
public class DataTransformationService {
  private String id;
  private String name;
  private String inputDataFormat;
  private String outputDataFormat;
  private String directionInputOutput;
  private String directionOutputInput;
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
   * @return the inputDataFormat
   */
  public String getInputDataFormat() {
    return inputDataFormat;
  }

  /**
   * @param inputDataFormat
   *          the inputDataFormat to set
   */
  public void setInputDataFormat(String inputDataFormat) {
    this.inputDataFormat = inputDataFormat;
  }

  /**
   * @return the outputDataFormat
   */
  public String getOutputDataFormat() {
    return outputDataFormat;
  }

  /**
   * @param outputDataFormat
   *          the outputDataFormat to set
   */
  public void setOutputDataFormat(String outputDataFormat) {
    this.outputDataFormat = outputDataFormat;
  }

  /**
   * @return the directionInputOutput
   */
  public String getDirectionInputOutput() {
    return directionInputOutput;
  }

  /**
   * @param directionInputOutput
   *          the directionInputOutput to set
   */
  public void setDirectionInputOutput(String directionInputOutput) {
    this.directionInputOutput = directionInputOutput;
  }

  /**
   * @return the directionOutputInput
   */
  public String getDirectionOutputInput() {
    return directionOutputInput;
  }

  /**
   * @param directionOutputInput
   *          the directionOutputInput to set
   */
  public void setDirectionOutputInput(String directionOutputInput) {
    this.directionOutputInput = directionOutputInput;
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
    string += "\tinputDataFormat: " + this.inputDataFormat + ",\r\n";
    string += "\toutputDataFormat: " + this.outputDataFormat + ",\r\n";
    string += "\tdirectionInputOutput: " + this.directionInputOutput
        + ",\r\n";
    string += "\tdirectionOutputInput: " + this.directionOutputInput
        + ",\r\n";
    string += "\timplementation: " + this.name + ",\r\n";
    string += "}";

    return string;
  }
}