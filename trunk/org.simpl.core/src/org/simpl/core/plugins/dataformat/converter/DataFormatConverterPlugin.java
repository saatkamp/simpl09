package org.simpl.core.plugins.dataformat.converter;

import org.simpl.core.services.dataformat.DataFormat;
import org.simpl.core.services.dataformat.DataFormatProvider;
import org.simpl.core.services.dataformat.converter.DataFormatConverter;

/**
 * <b>Purpose:</b>This abstract class is used to create data format converter plug-ins
 * that can be created for data sources to understand data formats from other data
 * sources.<br>
 * <b>Description:</b>Converts between two formats.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataFormatConverterPlugin.java 1192 2010-04-25 17:37:38Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataFormatConverterPlugin implements DataFormatConverter {
  /**
   * The data format to convert from.
   */
  private String fromDataFormat = "Tuscany";

  /**
   * The data format to convert to.
   */
  private String toDataFormat = "CSV";

  /**
   * @return the fromDataFormat
   */
  public DataFormat<Object> getFromDataFormat() {
    return DataFormatProvider.getInstance(fromDataFormat);
  }

  /**
   * @param fromDataFormat
   *          the fromDataFormat to set
   */
  public void setFromDataFormat(String fromDataFormat) {
    this.fromDataFormat = fromDataFormat;
  }

  /**
   * @return the toDataFormat
   */
  public DataFormat<Object> getToDataFormat() {
    return DataFormatProvider.getInstance(toDataFormat);
  }

  /**
   * @param toDataFormat
   *          the toDataFormat to set
   */
  public void setToDataFormat(String toDataFormat) {
    this.toDataFormat = toDataFormat;
  }
}