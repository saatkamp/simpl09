package org.simpl.core.dataconverter;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a data converter plug-in must implement to work
 * with a connector.<br>
 * <b>Description:</b>The data converter plug-in can convert am arbitrary data type to and
 * from SDO that is defined by a XML schema file.<br>
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
public interface DataConverter<S, T> {
  /**
   * Creates a DataObject from the given <S> data type.
   * 
   * @param <S>
   *          The data type
   * @param data
   *          The data
   * @return
   */
  public DataObject toSDO(S data);

  /**
   * Converts the given data to the <T> data type.
   * 
   * @param <T>
   *          The data type
   * @param data
   *          The data
   * @return
   */
  public T fromSDO(DataObject data);

  /**
   * @return Empty SDO created from the data converter XML schema.
   */
  public DataObject getSDO();

  /**
   * @return The data converter data format.
   */
  public String getDataFormat();
}