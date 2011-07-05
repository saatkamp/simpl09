package org.simpl.resource.management.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Data type for a data converter that is used convert retrieved data from a
 * data source to XML.<br>
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
@XmlType(name = "dataConverter", propOrder = { "id", "name", "dataFormat", "implementation", "xmlSchema" })
public class DataConverter {
  private String id;
  private String name;
  private String dataFormat;
  private String implementation;
  private String xmlSchema;

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
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the dataFormat
   */
  public String getDataFormat() {
    return dataFormat;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setDataFormat(String dataFormat) {
    this.dataFormat = dataFormat;
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

    string += "\r\nDataConverter {\r\n";
    string += "\tid: " + this.id + ",\r\n";
    string += "\tname: " + this.name + ",\r\n";
    string += "\tdataFormat: " + this.dataFormat + ",\r\n";
    string += "\timplementation: " + this.implementation + ",\r\n";
    string += "\txmlSchema: " + this.xmlSchema + ",\r\n";
    string += "}";

    return string;
  }
}