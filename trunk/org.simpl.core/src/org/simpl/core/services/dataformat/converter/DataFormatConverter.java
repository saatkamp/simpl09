package org.simpl.core.services.dataformat.converter;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a data format converter service must implement.<br>
 * <b>Description:</b> <br>
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
   * Converts the given Service Data Object (SDO) to the supported data format.
   * 
   * @param data
   * @return
   */
  public DataObject convert(DataObject data);
}