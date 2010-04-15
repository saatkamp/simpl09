package org.simpl.core.services.dataformat;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> Defines the methods that a data format must implement to work with the
 * data format services. <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataFormatService.java 1006 2010-03-24 17:52:54Z
 *          michael.schneidt@arcor.de $<br>
 * @param <T>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataFormatService<T> {
  /**
   * Creates a DataObject of the given data in this data format.
   * 
   * @param <T>
   *          Type of the data
   * @param data
   *          The data
   * @return
   */
  public DataObject toSDO(T data);

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
}