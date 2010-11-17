package org.simpl.resource.management;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Represents a tuple of the resource management datasourceconnectors
 * table.<br>
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
@XmlRootElement(name = "DataSourceConnector")
@XmlType(name = "DataSourceConnector", propOrder = { "id", "name",
    "dataConverterDataFormat", "propertiesDescription" })
public class DataSourceConnector {
  private String id;
  private String name;
  private String dataConverterDataFormat;
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
   * @return the dataConverterDataFormat
   */
  public String getDataConverterDataFormat() {
    return dataConverterDataFormat;
  }

  /**
   * @param dataConverterDataFormat
   *          the dataConverterDataFormat to set
   */
  public void setDataConverterDataFormat(String dataConverterDataFormat) {
    this.dataConverterDataFormat = dataConverterDataFormat;
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

    string += "DataSourceConnector {\n\r";
    string += "  id: " + this.id + ",\n\r";
    string += "  name: " + this.name + ",\n\r";
    string += "  dataconverter_dataformat: " + this.dataConverterDataFormat + ",\n\r";
    string += "  properties_description: " + this.propertiesDescription + "\n\r";
    string += "}";
    
    return string;
  }
}