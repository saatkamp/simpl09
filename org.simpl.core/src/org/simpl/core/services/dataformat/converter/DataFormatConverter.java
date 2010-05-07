package org.simpl.core.services.dataformat.converter;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a data format converter service must implement.<br>
 * <b>Description:</b>The data is passed as service data object (SDO).<br>
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
   * Converts the given data to the supported "to" data format.
   * 
   * @param data
   * @return
   */
  public DataObject convertTo(DataObject data);
  
  /**
   * Converts the given SDO to the supported "from" data format.
   *
   * @param data
   * @return
   */
  public DataObject convertFrom(DataObject data);
}