package org.simpl.core.plugins.converter;

import org.simpl.core.connector.Connector;
import org.simpl.core.converter.Converter;
import org.simpl.core.dataformat.DataFormat;
import org.simpl.core.dataformat.DataFormatProvider;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>This abstract class is used to create converter plug-ins that are
 * created for connectors to understand data formats from other data sources.<br>
 * <b>Description:</b>Converts between two SDO data formats.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataFormatConverterPlugin.java 1192 2010-04-25 17:37:38Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class ConverterPlugin implements Converter {
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

  @Override
  public DataObject convert(DataObject dataObject,
      Connector<Object, Object> connector) {
    DataObject convertedSDO = null;

    if (dataObject.getString("dataFormatType").equals(this.getToDataFormat().getType())) {
      convertedSDO = this.convertFrom(dataObject, connector);
    } else if (dataObject.getString("dataFormatType").equals(
        this.getFromDataFormat().getType())) {
      convertedSDO = this.convertTo(dataObject, connector);
    }

    return convertedSDO;
  }
}