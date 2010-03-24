package org.simpl.core.services.dataformat;

import java.io.File;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> Defines the methods that a data format must implement to work
 * with the data source services. <br>
 * <b>Description:</b> TODO: Beschreibung, auch von allen Funktionen.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataFormatService {
  public String getType();
  
  public DataObject SDO(File file);
}