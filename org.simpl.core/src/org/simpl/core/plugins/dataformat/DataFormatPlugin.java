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
 * <b>Description:</b> ... <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataFormatPlugin.java 1006 2010-03-24 17:52:54Z michael.schneidt@arcor.de
 *          $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataFormatPlugin implements DataFormatService {
  /**
   * Name of the data format schema file.
   */
  private static final String DATA_FORMAT_SCHEMA_FILE = "DataFormat.xsd";

  /**
   * Type of the supporting data format (CSV, XML, ...).
   */
  private String dataFormatType = "Default";

  /**
   * Subtypes of the supporting data format (CSVWithHeadline, XMLForSimulation, ..). TODO: bessere Beispiele finden
   */
  private List<String> dataFormatSubtypes = new ArrayList<String>();

  /**
   * DataObject created from the data format schema file.
   */
  private DataObject data = null;
  
  /**
   * Creates an empty meta data object (SDO) from the data format schema.
   */
  public DataFormatPlugin() {
    DataObject dataObject = null;
    InputStream inputStream = null;

    // load the schema file
    inputStream = getClass().getResourceAsStream(DATA_FORMAT_SCHEMA_FILE);

    if (inputStream == null) {
      System.out
          .println("The file '" + DATA_FORMAT_SCHEMA_FILE + "' could not be found.");
    }

    XSDHelper.INSTANCE.define(inputStream, null);

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    dataObject = DataFactory.INSTANCE.create(
        "http://org.simpl.core/src/org/simpl/core/plugins/dataformat/"
            + DATA_FORMAT_SCHEMA_FILE, this.dataFormatType);

    this.setData(dataObject);
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
   * Returns the supporting data format subtypes.
   * 
   * @return list of data format subtypes
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
   * @return the supporting data format type.
   */
  public String getType() {
    return this.dataFormatType;
  }

  /**
   * @param data the data to set
   */
  public void setData(DataObject data) {
    this.data = data;
  }

  /**
   * @return the data
   */
  public DataObject getData() {
    return data;
  }
}