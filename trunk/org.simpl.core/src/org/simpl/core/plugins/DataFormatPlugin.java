package org.simpl.core.plugins;

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
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataFormatPlugin implements DataFormatService {
  /**
   * Name of the data schema file.
   */
  private static final String DATA_FORMAT_SCHEMA_FILE = "DataFormat.xsd";

  /**
   * Type of the supported data format. (CSV, XML, ...)
   */
  private String dataFormatType = "default";

  /**
   * Subtypes of the supported data format. (Headline, ...)
   */
  private List<String> dataFormatSubtypes = new ArrayList<String>();

  /**
   * Creates an empty meta data object (SDO) from the data format schema.
   * 
   * @return
   */
  public DataObject create() {
    DataObject dataObject = null;
    InputStream inputStream = null;

    // Load the schema file
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

    return dataObject;
  }

  /**
   * Returns the supported data format type.
   * 
   * @return
   */
  public String getType() {
    return this.dataFormatType;
  }

  /**
   * Returns the supported data format subtypes.
   * 
   * @return
   */
  public List<String> getSubtypes() {
    return this.dataFormatSubtypes;
  }
}