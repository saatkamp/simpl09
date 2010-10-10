package org.simpl.core.services.dataformat;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a data format service must implement to work
 * with the data source service.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataFormatService.java 1006 2010-03-24 17:52:54Z
 *          michael.schneidt@arcor.de $<br>
 * @param <S>
 *          data type from which a SDO can be created
 * @param <T>
 *          data type which can be created from a SDO
 * @link http://code.google.com/p/simpl09/
 */
public interface DataFormat<S, T> {
  /**
   * Creates a DataObject of the given data in this data format.
   * 
   * @param <S>
   *          Type of the data
   * @param data
   *          The data
   * @return
   */
  public DataObject toSDO(S data);

  /**
   * Returns the data converted to this data format from the given data object.
   * 
   * @param <T>
   *          Type of the data
   * @param data
   *          The data
   * @return
   */
  public T fromSDO(DataObject data);

  /**
   * @return Empty SDO created from the data format schema.
   */
  public DataObject getSDO();

  /**
   * @return The supported data format type.
   */
  public String getType();
}