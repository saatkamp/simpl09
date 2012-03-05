package org.simpl.data.transformation;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a data transformation service must implement.<br>
 * <b>Description:</b>The data is passed as service data object (SDO). Because the
 * connector is passed to the data transformation service functions, it is possible to
 * adapt the conversion to specific differences that need to be taken care of, such as
 * different data types of database table columns.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataTransformation {
  /**
   * Converts the given data to the supported "to" data format.
   * 
   * @param data
   * @param connectorImpl
   * @return
   */
  public DataObject convertTo(DataObject data, String connectorImpl);

  /**
   * Converts the given SDO to the supported "from" data format.
   * 
   * @param data
   * @param connectorImpl
   * @return
   */
  public DataObject convertFrom(DataObject data, String connectorImpl);

  /**
   * Converts automatically between two data formats. The given SDO's format is recognized
   * by its dataFormat attribute to decide whether to use the
   * {@link #convertTo(DataObject)} or {@link #convertFrom(DataObject)} method.
   * 
   * @param data
   * @param connectorImpl
   * @return
   */
  public DataObject convert(DataObject data, String connectorImpl);
}