package org.simpl.core.services.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>The connector of a data source that is used to connect to the data
 * source.<br>
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
    "propertiesDescription", "converterDataFormat" })
public class Connector {
  private String id;
  private String name;
  private String implementation;
  private String propertiesDescription;
  private DataFormat converterDataFormat;

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
   * @return the converterDataFormat
   */
  public DataFormat getConverterDataFormat() {
    return converterDataFormat;
  }

  /**
   * @param converterDataFormat
   *          the converterDataFormat to set
   */
  public void setConverterDataFormat(DataFormat converterDataFormat) {
    this.converterDataFormat = converterDataFormat;
  }

  @Override
  public String toString() {
    String string = "";

    string += "Connector {\n\r";
    string += "  id: " + this.id + ",\n\r";
    string += "  name: " + this.name + ",\n\r";
    string += "  implementation: " + this.implementation + ",\n\r";
    string += "  propertiesDescription: " + this.propertiesDescription + ",\n\r";
    string += "  converterDataFormat: " + this.converterDataFormat + "\n\r";
    string += "}";

    return string;
  }
}