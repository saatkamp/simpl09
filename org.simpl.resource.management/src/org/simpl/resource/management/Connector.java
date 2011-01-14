package org.simpl.resource.management;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Represents a tuple of the resource management 'connectors' table.<br>
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
@XmlRootElement(name = "Connector")
@XmlType(name = "Connector", propOrder = { "id", "name", "implementation", 
    "converterDataFormatName", "converterDataFormatImplementation", "propertiesDescription" })
public class Connector {
  private String id;
  private String name;
  private String implementation;
  private String converterDataFormatName;
  private String converterDataFormatImplementation;
  private String propertiesDescription;

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
   * @return the converterDataFormatName
   */
  public String getConverterDataFormatName() {
    return converterDataFormatName;
  }

  /**
   * @param converterDataFormatName
   *          the converterDataFormatName to set
   */
  public void setConverterDataFormatName(String converterDataFormatName) {
    this.converterDataFormatName = converterDataFormatName;
  }

  /**
   * @return the converterDataFormatImplementation
   */
  public String getConverterDataFormatImplementation() {
    return converterDataFormatImplementation;
  }

  /**
   * @param converterDataFormatImplementation
   *          the converterDataFormatImplementation to set
   */
  public void setConverterDataFormatImplementation(
      String converterDataFormatImplementation) {
    this.converterDataFormatImplementation = converterDataFormatImplementation;
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

  @Override
  public String toString() {
    String string = "";

    string += "Connector {\n\r";
    string += "  id: " + this.id + ",\n\r";
    string += "  name: " + this.name + ",\n\r";
    string += "  implementation: " + this.implementation + ",\n\r";
    string += "  converter_dataformat_name: " + this.converterDataFormatName + ",\n\r";
    string += "  converter_dataformat_implementation: " + this.converterDataFormatImplementation + ",\n\r";
    string += "  properties_description: " + this.propertiesDescription + "\n\r";
    string += "}";

    return string;
  }
}