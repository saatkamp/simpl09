package org.simpl.core.plugins.dataformat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.simpl.core.services.dataformat.DataFormatService;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b> ... <br>
 * <b>Description:</b> service data object (SDO) <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataFormatPlugin.java 1006 2010-03-24 17:52:54Z michael.schneidt@arcor.de
 *          $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataFormatPlugin<T> implements DataFormatService<T> {
  /**
   * Name of the data format schema file.
   */
  private String dataFormatSchemaFile = "DefaultDataFormat.xsd";

  /**
   * Type of the supporting data format (CSV, XML, ...).
   */
  private String dataFormatType = "DefaultFormat";

  /**
   * The data format schema type defined in the data format schema file that is used to
   * create the data object.
   */
  private String dataFormatSchemaType = "dataObject";

  /**
   * Subtypes of the supporting data format (CSVWithHeadline, XMLForSimulation, ..). TODO:
   * bessere Beispiele finden
   */
  private List<String> dataFormatSubtypes = new ArrayList<String>();

  /**
   * Data object created from the data format schema file for holding the data.
   */
  private DataObject dataObject = null;

  /**
   * Initializes the data format in creating the data object from the schema file.
   */
  public void init() {
    this.dataObject = createDataObject();
  }

  /**
   * @return Empty data object following the data format schema
   */
  public DataObject getDataObjectFromSchema() {
    return this.dataObject;
  }

  /**
   * Sets the supporting data format type.
   * 
   * @param dfType
   */
  public void setType(String dfType) {
    this.dataFormatType = dfType;
  }

  /**
   * Sets the element type that is defined in the data format schema file.
   * 
   * @param type
   */
  public void setSchemaType(String dfSchemaType) {
    this.dataFormatSchemaType = dfSchemaType;
  }

  /**
   * Sets the name/location of the data format schema file.
   * 
   * @param dfSchemaFile
   */
  public void setSchemaFile(String dfSchemaFile) {
    this.dataFormatSchemaFile = dfSchemaFile;
  }

  /**
   * @return List of supporting data format subtypes.
   */
  public List<String> getSubtypes() {
    return this.dataFormatSubtypes;
  }

  /**
   * Adds a supporting data format subtype.
   * 
   * @param dfSubtype
   */
  public void addSubtype(String dfSubtype) {
    if (!this.dataFormatSubtypes.contains(dfSubtype)) {
      this.dataFormatSubtypes.add(dfSubtype);
    }
  }

  /**
   * @return The supporting data format type.
   */
  public String getType() {
    return this.dataFormatType;
  }

  /**
   * @return Emty data object created from the data format schema.
   */
  private DataObject createDataObject() {
    DataObject dataObject = null;
    InputStream inputStream = null;

    // load the schema file
    if (this.dataFormatSchemaFile.equals("DefaultDataFormat.xsd")) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/plugins/dataformat/" + this.dataFormatSchemaFile);
    } else {
      inputStream = getClass().getResourceAsStream(this.dataFormatSchemaFile);
    }

    if (inputStream == null) {
      System.out.println("The file '" + this.dataFormatSchemaFile
          + "' could not be found.");
    }

    XSDHelper.INSTANCE.define(inputStream, null);

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    dataObject = DataFactory.INSTANCE.create(
        "http://org.simpl.core/plugins/dataformat/DataFormat", this.dataFormatSchemaType);

    return dataObject;
  }
}