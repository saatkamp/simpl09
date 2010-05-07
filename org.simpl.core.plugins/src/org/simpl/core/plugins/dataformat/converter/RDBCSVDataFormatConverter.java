package org.simpl.core.plugins.dataformat.converter;

import commonj.sdo.DataObject;

/**
 * <b>Purpose: Converts the Tuscany data format to the CSV data format and vice versa.</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDBCSVDataFormatConverter extends DataFormatConverterPlugin {
  /**
   * Initialize the plug-in.
   */
  public RDBCSVDataFormatConverter() {
    this.setFromDataFormat("CSV");
    this.setToDataFormat("RDB");
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.dataformat.converter.DataFormatConverter#convertFrom(commonj
   * .sdo.DataObject)
   */
  @Override
  public DataObject convertFrom(DataObject dataObject) {
    DataObject fromDataObject = this.getFromDataFormat().getSDO();

    // TODO: From CSV to RDB

    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.dataformat.converter.DataFormatConverter#convertTo(commonj
   * .sdo.DataObject)
   */
  @Override
  public DataObject convertTo(DataObject dataObject) {
    DataObject toDataObject = this.getToDataFormat().getSDO();

    // TODO: From RDB to CSV

    return null;
  }
}