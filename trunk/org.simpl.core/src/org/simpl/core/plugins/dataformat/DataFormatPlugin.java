package org.simpl.core.plugins.dataformat;

import java.io.IOException;
import java.io.InputStream;

import org.simpl.core.services.dataformat.DataFormat;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b>This abstract class is used to create data format plug-ins for
 * the SIMPL Core, that can be used by data source plug-ins to work with a
 * defined data format.<br>
 * <b>Description:</b>A data format plug-in is used to map outgoing data of a
 * format to a Service Data Object (SDO) and to transform incoming data as SDO
 * back to the format.<br>
 * It has a type for identification and needs a XML schema that defines the data
 * format structure, that is used to create a SDO based on a root schema element
 * type. Type, schema file and schema element type must be set in the plugin's
 * constructor. <br>
 * The root schema type must have an attribute called <b>type</b>, that is set
 * automatically, for other services being able to recognize the underlying
 * format of a passed SDO.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataFormatPlugin.java 1006 2010-03-24 17:52:54Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataFormatPlugin<S, T> implements DataFormat<S, T> {
  /**
   * Type of the supported data format (CSV, XML, ...).
   */
  private String type = "tDataFormat";

  /**
   * Name of the data format schema file.
   */
  private String schemaFile = "TuscanyDataFormat.xsd";

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
        "http://org.simpl.core/plugins/dataformat/" + this.getType() + "DataFormat", this.schemaType);

    // write the format type to the data object if possible
    try {
      dataObject.setString("formatType", this.type);
    } catch (IllegalArgumentException e) {
      // type is not set, because the schema does not declare this attribute
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
   * @return The supported data format type.
   */
  public String getType() {
    return this.type;
  }
}