package org.simpl.resource.management.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Data type for a connector of a data source that is used to connect to
 * the data source.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: Connector.java 1822 2011-08-04 17:25:42Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "connector", propOrder = { "id", "name", "implementation",
    "inputDataType", "outputDataType", "type", "subType", "language", "apiType",
    "propertiesDescription", "dataConverter" })
public class Connector {
  private String id;
  private String name;
  private String implementation;
  private String inputDataType;
  private String outputDataType;
  private String type;
  private String subType;
  private String language;
  private String apiType;
  private String propertiesDescription;
  private DataConverter dataConverter = new DataConverter();

  public Connector() {

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
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the subType
   */
  public String getSubType() {
    return subType;
  }

  /**
   * @param subType
   *          the subType to set
   */
  public void setSubType(String subType) {
    this.subType = subType;
  }

  /**
   * @return the language
   */
  public String getLanguage() {
    return language;
  }

  /**
   * @param language
   *          the language to set
   */
  public void setLanguage(String language) {
    this.language = language;
  }
  
  /**
   * @return the api type
   */
  public String getAPIType() {
    return apiType;
  }

  /**
   * @param language
   *          the api type to set
   */
  public void setAPIType(String apiType) {
    this.apiType = apiType;
  }

  /**
   * @return the propertiesDescription
   */
  public String getPropertiesDescription() {
    return propertiesDescription;
  }

  /**
   * @param propertiesDescription
   *          the propertiesDescription to set
   */
  public void setPropertiesDescription(String propertiesDescription) {
    this.propertiesDescription = propertiesDescription;
  }

  /**
   * @return the dataConverter
   */
  public DataConverter getDataConverter() {
    return dataConverter;
  }

  /**
   * @param dataConverter
   *          the dataConverter to set
   */
  public void setDataConverter(DataConverter dataConverter) {
    this.dataConverter = dataConverter;
  }

  @Override
  public String toString() {
    String string = "";

    string += "\r\nConnector {\r\n";
    string += "\tid: " + this.id + ",\r\n";
    string += "\tname: " + this.name + ",\r\n";
    string += "\tinputDataType: " + this.inputDataType + ",\r\n";
    string += "\toutputDataType: " + this.outputDataType + ",\r\n";
    string += "\timplementation: " + this.implementation + ",\r\n";
    string += "\tpropertiesDescription: " + this.propertiesDescription + ",\r\n";
    string += "\tdataConverter: " + this.dataConverter + "\r\n";
    string += "}";

    return string;
  }
}