package org.simpl.data.transformation;

import org.simpl.core.connector.Connector;
import org.simpl.core.dataconverter.DataConverter;
import org.simpl.core.dataconverter.DataConverterProvider;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>This abstract class is used to create data transformation services that
 * are created for connectors to understand data formats from other data sources. The data
 * format of a connector is defined by a data converter plug-in.<br>
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
public abstract class DataTransformationService implements DataTransformation {
  /**
   * The data format to convert from.
   */
  private String fromDataFormat = "";

  /**
   * The data format to convert to.
   */
  private String toDataFormat = "";

  /**
   * @return the fromDataConverter
   */
  public DataConverter<Object, Object> getFromDataConverter() {
    return DataConverterProvider.getInstance(fromDataFormat);
  }

  /**
   * @param fromDataFormat
   *          the fromDataFormat to set
   */
  public void setFromDataFormat(String fromDataFormat) {
    this.fromDataFormat = fromDataFormat;
  }

  /**
   * @return the toDataConverter
   */
  public DataConverter<Object, Object> getToDataConverter() {
    return DataConverterProvider.getInstance(toDataFormat);
  }

  /**
   * @param toDataFormat
   *          the toDataFormat to set
   */
  public void setToDataFormat(String toDataFormat) {
    this.toDataFormat = toDataFormat;
  }

  @Override
  public DataObject convert(DataObject dataObject, Connector<Object, Object> connector) {
    DataObject convertedSDO = null;

    if (dataObject.getString("dataFormat").equals(
        this.getToDataConverter().getDataFormat())) {
      convertedSDO = this.convertFrom(dataObject, connector);
    } else if (dataObject.getString("dataFormat").equals(
        this.getFromDataConverter().getDataFormat())) {
      convertedSDO = this.convertTo(dataObject, connector);
    }

    return convertedSDO;
  }
}