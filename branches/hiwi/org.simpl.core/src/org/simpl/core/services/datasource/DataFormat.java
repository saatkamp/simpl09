package org.simpl.core.services.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>A data format that is used convert retrieved data source data to XML.<br>
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
@XmlRootElement(name = "DataFormat")
@XmlType(name = "DataFormat", propOrder = { "id", "name", "implementation", "xmlSchema" })
public class DataFormat {
  private String id;
  private String name;
  private String implementation;
  private String xmlSchema;

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
   * @return the xmlSchema
   */
  public String getXmlSchema() {
    return xmlSchema;
  }

  /**
   * @param xmlSchema
   *          the xmlSchema to set
   */
  public void setXmlSchema(String xmlSchema) {
    this.xmlSchema = xmlSchema;
  }

  @Override
  public String toString() {
    String string = "";

    string += "DataFormat {\n\r";
    string += "  id: " + this.id + ",\n\r";
    string += "  name: " + this.name + ",\n\r";
    string += "  implementation: " + this.implementation + ",\n\r";
    string += "  xmlSchema: " + this.xmlSchema + ",\n\r";
    string += "}";

    return string;
  }
}