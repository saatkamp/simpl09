package org.simpl.resource.management.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataContainer", propOrder = { "id", "dataSourceId",
    "dataSourceName", "logicalName", "localIdentifier" })
public class DataContainer {
  private String id;
  private String dataSourceId;
  private String dataSourceName;
  private String logicalName;
  private String localIdentifier;

  public DataContainer() {

  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * 
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the dataSourceId
   */
  public String getDataSourceId() {
    return dataSourceId;
  }

  /**
   * 
   * @param dataSourceId
   *          the dataSourceId to set
   */
  public void setDataSourceId(String dataSourceId) {
    this.dataSourceId = dataSourceId;
  }

  /**
   * @return the dataSourceName
   */
  public String getDataSourceName() {
    return dataSourceName;
  }

  /**
   * 
   * @param dataSourceName
   *          the dataSourceName to set
   */
  public void setDataSourceName(String dataSourceName) {
    this.dataSourceName = dataSourceName;
  }
  
  /**
   * @return the logicalName
   */
  public String getLogicalName() {
    return logicalName;
  }


  /**
   * 
   * @param logicalName
   *          the logicalName to set
   */
  public void setLogicalName(String logicalName) {
    this.logicalName = logicalName;
  }

  /**
   * @return the localIdentifier
   */
  public String getLocalIdentifier() {
    return localIdentifier;
  }

  /**
   * 
   * @param localIdentifier
   *          the localIdentifier to set
   */
  public void setLocalIdentifier(String localIdentifier) {
    this.localIdentifier = localIdentifier;
  }

  @Override
  public String toString() {
    String string = "";

    string += "\r\nDataContainer {\r\n";
    string += "\tid: " + this.id + ",\r\n";
    string += "\tdaraSourceId: " + this.dataSourceId + ",\r\n";
    string += "\tdaraSourceName: " + this.dataSourceName + ",\r\n";
    string += "\tlogicalName: " + this.logicalName + ",\r\n";
    string += "\tlocalIdentifier: " + this.localIdentifier + "\r\n";
    string += "}";

    return string;
  }
}
