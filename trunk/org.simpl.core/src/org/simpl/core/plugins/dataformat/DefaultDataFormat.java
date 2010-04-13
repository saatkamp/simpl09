package org.simpl.core.plugins.dataformat;

import commonj.sdo.DataObject;

/**
 * <b>Purpose: Default DataFormat that can be used if no data format plugin is
 * defined or available.</b> <br>
 * <b>Description: Puts all data into one DataObject data element.</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DefaultDataFormat extends DataFormatPlugin {
  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#toSDO(java.lang.Object)
   */
  @SuppressWarnings("hiding")
  @Override
  public <Object> DataObject toSDO(Object data) {

    return null;
  }
}