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
public class TuscanyCSVDataFormatConverter extends DataFormatConverterPlugin {
  /**
   * Initialize the plugin.
   */
  public TuscanyCSVDataFormatConverter() {
    this.setFromDataFormat("Tuscany");
    this.setToDataFormat("CSV");
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
    
    // TODO: From CSV to Tuscany
    
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

    // TODO: From Tuscany to CSV
    
    return null;
  }
}