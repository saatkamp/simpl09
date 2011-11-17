package org.simpl.resource.management.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Data type for a data source with all relevant information that is needed
 * to set up a connection.<br>
 * <b>Description:</b>Type and subType are used by the SIMPL Core to determine a data
 * source service that can respond to the data source over the address. Further it holds a
 * properties description that defines non-functional properties used for a late binding,
 * a connector properties description that can be used to automatically find and assign a
 * working connector, the necessary authentication information for the data source, and
 * the assigned connector.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSource", propOrder = { "id", "name", "address", "type", "subType",
    "language", "propertiesDescription", "connectorPropertiesDescription",
    "authentication", "connector" })
public class DataSource {
  private String id;
  private String name;
  private String address;
  private String type;
  private String subType;
  private String language;
  private String propertiesDescription;
  private String connectorPropertiesDescription;
  private Authentication authentication = new Authentication();
  private Connector connector = new Connector();

  public DataSource() {
    
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
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address
   *          the address to set
   */
  public void setAddress(String address) {
    this.address = address;
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
   * @return the connectorPropertiesDescription
   */
  public String getConnectorPropertiesDescription() {
    return connectorPropertiesDescription;
  }

  /**
   * @param connectorPropertiesDescription
   *          the connectorPropertiesDescription to set
   */
  public void setConnectorPropertiesDescription(String connectorPropertiesDescription) {
    this.connectorPropertiesDescription = connectorPropertiesDescription;
  }

  /**
   * @return the connector
   */
  public Connector getConnector() {
    return connector;
  }

  /**
   * @param connector
   *          the connector to set
   */
  public void setConnector(Connector connector) {
    this.connector = connector;
  }

  /**
   * @return the authentication
   */
  public Authentication getAuthentication() {
    return authentication;
  }

  /**
   * @param authentication
   *          the authentication to set
   */
  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }

  @Override
  public String toString() {
    String string = "";

    string += "\r\nDataSource {\r\n";
    string += "\tid: " + this.id + ",\r\n";
    string += "\tname: " + this.name + ",\r\n";
    string += "\taddress: " + this.address + ",\r\n";
    string += "\ttype: " + this.type + ",\r\n";
    string += "\tsubtype: " + this.subType + ",\r\n";
    string += "\tlanguage: " + this.language + ",\r\n";
    string += "\tpropertiesDescription: " + this.propertiesDescription + ",\r\n";
    string += "\tconnectorPropertiesDescription: " + this.connectorPropertiesDescription
        + ",\r\n";
    string += "\tconnector: " + this.connector + "\r\n";
    string += "}";

    return string;
  }
}