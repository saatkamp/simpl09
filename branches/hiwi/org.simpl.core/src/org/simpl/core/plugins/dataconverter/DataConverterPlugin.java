package org.simpl.core.plugins.dataconverter;

import java.io.IOException;
import java.io.InputStream;

import org.simpl.core.dataconverter.DataConverter;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b>This abstract class is used to create data converter plug-ins for the
 * SIMPL Core, that are be used by connectors to work with a defined data format.<br>
 * <b>Description:</b>A data converter plug-in is used to convert an outgoing data type to
 * a Service Data Object (SDO) and to transform an incoming SDO back to an incoming data
 * type.<br>
 * It has a data format name for identification and needs a XML schema that defines the
 * data format structure that is used to create a SDO based on a root schema element type.
 * Type, schema file and schema element type must be set in the plugin's constructor. <br>
 * The root schema type must have the attribute <b>dataFormat</b> that is set
 * automatically for other services being able to recognize the underlying data format of
 * a passed SDO.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataConverterPlugin<S, T> implements DataConverter<S, T> {
  /**
   * The supported data format (CSV, XML, ...).
   */
  private String dataFormat = "";

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

    dataObject = DataFactory.INSTANCE.create("http://org.simpl.core/plugins/dataconverter/dataformat/"
        + this.schemaFile.replace(".xsd", ""), this.schemaType);

    // write the data format to the data object if possible
    try {
      dataObject.setString("dataFormat", this.dataFormat);
    } catch (IllegalArgumentException e) {
      // type was not set, because the schema does not declare this attribute
    }

    return dataObject;
  }

  /**
   * Sets the supported data format.
   * 
   * @param dataFormat
   */
  public void setDataFormat(String dataFormat) {
    this.dataFormat = dataFormat;
  }

  /**
   * Sets the element type that is defined in the data format schema file.
   * 
   * @param dataFormat
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
  public String getDataFormat() {
    return this.dataFormat;
  }
}