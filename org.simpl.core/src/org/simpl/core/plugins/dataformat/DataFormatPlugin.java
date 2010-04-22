package org.simpl.core.plugins.dataformat;

import java.io.IOException;
import java.io.InputStream;

import org.simpl.core.services.dataformat.DataFormatService;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b>This abstract class is used to create data format plugins for
 * the SIMPL Core, that are used by the data source service to support different
 * data formats of different data sources. A data format is used by a data
 * source service to map outgoing data to and incoming data from a Service Data
 * Object (SDO) using Apache Tuscany.<br>
 * <b>Description:</b>The type or rather name of data format is set with
 * {@link #setType(String)}. The structure of the SDO is defined by a XML-schema
 * file, that is set with {@link #setSchemaFile(String)}, and supposed to be
 * deployed in a .jar file along with the extending plugin class. In order to
 * create an empty SDO from the schema that can be filled with data, a root
 * schema type has to be set with #setSchemaType(String). All settings have to
 * be done in the plugin's constructor, the created SDO is returned by
 * {@link #getDataObject()}.<br>
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
   * Type of the supporting data format (CSV, XML, ...).
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
   * Sets the supporting data format type.
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
   * @return The supporting data format type.
   */
  public String getType() {
    return this.type;
  }
}