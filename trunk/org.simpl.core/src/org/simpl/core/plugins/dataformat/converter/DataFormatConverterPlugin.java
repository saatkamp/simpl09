package org.simpl.core.plugins.dataformat.converter;

import org.simpl.core.services.dataformat.DataFormat;
import org.simpl.core.services.dataformat.DataFormatProvider;
import org.simpl.core.services.dataformat.converter.DataFormatConverter;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>This abstract class is used to create data format converter plug-ins
 * that can be created for data source services to understand data formats from other data
 * sources.<br>
 * <b>Description:</b>Converts between two SDO data formats.<br>
 * <b>Copyright:</b><br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataFormatConverterPlugin.java 1192 2010-04-25 17:37:38Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataFormatConverterPlugin implements DataFormatConverter {
  /**
   * The data format type to convert from.
   */
  private String fromDataFormatType = "";

  /**
   * The data format type to convert to.
   */
  private String toDataFormatType = "";

  /**
   * @return the fromDataFormatType
   */
  public DataFormat<Object, Object> getFromDataFormat() {
    return DataFormatProvider.getInstance(fromDataFormatType);
  }

  /**
   * @param fromDataFormatType
   *          the fromDataFormatType to set
   */
  public void setFromDataFormat(String fromDataFormat) {
    this.fromDataFormatType = fromDataFormat;
  }

  /**
   * @return the toDataFormatType
   */
  public DataFormat<Object, Object> getToDataFormat() {
    return DataFormatProvider.getInstance(toDataFormatType);
  }

  /**
   * @param toDataFormatType
   *          the toDataFormatType to set
   */
  public void setToDataFormat(String toDataFormat) {
    this.toDataFormatType = toDataFormat;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.dataformat.converter.DataFormatConverter#convert(commonj.
   * sdo.DataObject)
   */
  @Override
  public DataObject convert(DataObject dataObject) {
    DataObject convertedSDO = null;

    if (dataObject.getString("dataFormatType").equals(this.getToDataFormat().getType())) {
      convertedSDO = this.convertFrom(dataObject);
    } else if (dataObject.getString("dataFormatType").equals(this.getFromDataFormat().getType())) {
      convertedSDO = this.convertTo(dataObject);
    }

    return convertedSDO;
  }
}