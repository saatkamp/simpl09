package org.simpl.core.services.dataformat;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> Defines the methods that a data format must implement to work with the
 * data source services. <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataFormatService.java 1006 2010-03-24 17:52:54Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataFormatService {
  /**
   * Creates a DataObject of the given data.
   * 
   * @param <T> Datatype of the data
   * @param data The data
   * @return
   */
  public <T> DataObject toSDO(T data);
}