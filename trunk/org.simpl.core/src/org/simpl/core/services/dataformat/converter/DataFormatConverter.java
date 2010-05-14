package org.simpl.core.services.dataformat.converter;

import org.simpl.core.services.datasource.DataSourceService;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a data format converter service must implement.<br>
 * <b>Description:</b>The data is passed as service data object (SDO). Because the data
 * source service is passed to the converter functions, it is possible to adapt the
 * conversion to specific differences that need to be taken care of, such as different
 * data types of database table columns.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataFormatService.java 1006 2010-03-24 17:52:54Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataFormatConverter {
  /**
   * Converts the given data to the supported "to" data format if the data source service
   * is registered for this converter in the SIMPL config file.
   * 
   * @param data
   * @param dataSourceService
   * @return
   */
  public DataObject convertTo(DataObject data,
      DataSourceService<Object, Object> dataSourceService);

  /**
   * Converts the given SDO to the supported "from" data format if the data source service
   * is registered for this converter in the SIMPL config file
   * 
   * @param data
   * @param dataSourceService
   * @return
   */
  public DataObject convertFrom(DataObject data,
      DataSourceService<Object, Object> dataSourceService);

  /**
   * Converts automatically between the supported data formats if the data source service
   * is registered for this converter in the SIMPL config file. The given SDO's format is
   * recognized by its dataFormatType attribute to decide wheather to use the
   * {@link #convertTo(DataObject)} or {@link #convertFrom(DataObject)} method.
   * 
   * @param dataObject
   * @param dataSourceService
   * @return
   */
  public DataObject convert(DataObject dataObject,
      DataSourceService<Object, Object> dataSourceService);
}