package org.simpl.core.plugins.dataformat;

import java.io.IOException;
import java.io.InputStream;

import org.simpl.core.services.dataformat.DataFormatService;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b>This abstract class is used to create data format plugins for
 * the SIMPL Core, that are used by a data source service to support different
 * data formats of different data sources.<br>
 * <b>Description:</b>A data format plugin is used to map outgoing data of a
 * format to a Service Data Object (SDO) and to transform incoming data as SDO
 * back to the format. It has a type for identification and needs a XML schema
 * that defines the data format structure, that is used to create a SDO based on
 * a schema element type. Type, schema file and schema element type must be set
 * in the plugin's constructor.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataFormatPlugin.java 1006 2010-03-24 17:52:54Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataFormatPlugin<T> implements DataFormatService<T> {
  /**
   * Type of the supported data format (CSV, XML, ...).
   */
  private String type = "DefaultFormat";

  /**
   * Name of the data format schema file.
   */
  private String schemaFile = "DefaultDataFormat.xsd";

  /**
   * The data format schema type defined in the data format schema file that is
   * used to create the data object.
   */
  private String schemaType = "tDataObject";

  /**
   * @return Empty SDO created from the data format schema.
   */
  public DataObject getSDO() {
    DataObject dataObject = null;
    InputStream inputStream = null;

    // load the schema file
    if (this.schemaFile.equals("DefaultDataFormat.xsd")) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/plugins/dataformat/" + this.schemaFile);
    } else {
      inputStream = getClass().getResourceAsStream(this.schemaFile);
    }

    if (inputStream == null) {
      System.out.println("The file '" + this.schemaFile
          + "' could not be found.");
    }

    XSDHelper.INSTANCE.define(inputStream, null);

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    dataObject = DataFactory.INSTANCE.create(
        "http://org.simpl.core/plugins/dataformat/DataFormat", this.schemaType);

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
   * @return The supported data format type.
   */
  public String getType() {
    return this.type;
  }
}