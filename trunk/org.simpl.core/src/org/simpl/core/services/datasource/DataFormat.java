package org.simpl.core.services.datasource;

import java.io.File;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> Defines the methods that a data format must implement to work
 * with the datasource services. <br>
 * <b>Description:</b> TODO: Beschreibung, auch von allen Funktionen.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataFormat {
  public String getType();
  
  public DataObject SDO(File file);
}