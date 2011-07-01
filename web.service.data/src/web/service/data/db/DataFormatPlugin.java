package web.service.data.db;

import java.io.IOException;
import java.io.InputStream;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

public abstract class DataFormatPlugin {
  /**
   * Type of the supported data format (CSV, XML, ...).
   */
  private String type = "";

  /**
   * Name of the data format schema file.
   */
  private String schemaFile = "";

  /**
   * The data format schema type defined in the data format schema file that is used to
   * create the data object.
   */
  private String schemaType = "";

  /**
   * @return Empty SDO created from the data format schema.
   */
  public DataObject getSDO() {
    DataObject dataObject = null;
    InputStream inputStream = null;

    // load the schema file
    inputStream = getClass().getResourceAsStream(this.schemaFile);

    if (inputStream == null) {
      System.out.println("The file '" + this.schemaFile + "' could not be found.");
    }

    XSDHelper.INSTANCE.define(inputStream, null);

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    dataObject = DataFactory.INSTANCE.create("http://web.services.data/db/"
        + this.schemaFile.replace(".xsd", ""), this.schemaType);

    // write the data format type to the data object if possible
    try {
      dataObject.setString("dataFormatType", this.type);
    } catch (IllegalArgumentException e) {
      // type was not set, because the schema does not declare this attribute
    }

    return dataObject;
  }

  /**
   * Sets the supported data format type.
   * 
   * @param dfType
   */
  public void setType(String dfType) {
    this.type = dfType;
  }

  /**
   * Sets the element type that is defined in the data format schema file.
   * 
   * @param type
   */
  public void setSchemaType(String dfSchemaType) {
    this.schemaType = dfSchemaType;
  }

  /**
   * Sets the name and location of the data format schema file.
   * 
   * @param dfSchemaFile
   */
  public void setSchemaFile(String dfSchemaFile) {
    this.schemaFile = dfSchemaFile;
  }

  /**
   * @return The schema file as InputStream
   */
  public InputStream getSchemaFile() {
    InputStream inputStream = null;

    // Load the schema from jar file
    inputStream = getClass().getResourceAsStream(this.schemaFile);

    if (inputStream == null) {
      System.out.println("The file '" + schemaFile + "' could not be found.");
    }

    return inputStream;
  }

  /**
   * @return The supported data format type.
   */
  public String getType() {
    return this.type;
  }
}