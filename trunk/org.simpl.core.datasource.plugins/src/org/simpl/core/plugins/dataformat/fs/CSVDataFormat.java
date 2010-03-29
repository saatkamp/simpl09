package org.simpl.core.plugins.dataformat.fs;

import org.simpl.core.plugins.dataformat.DataFormatPlugin;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>
 * <br>
 * <b>Description:</b>
 * <br>
 * <b>Copyright:</b>     <br>
 * <b>Company:</b>       SIMPL<br>
 *
 * @author      Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version     $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class CSVDataFormat extends DataFormatPlugin {
  public CSVDataFormat() {
    this.setType("CSV");
    this.addSubtype("Standard");
  }

  /* (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object)
   */
  @Override
  public <File> DataObject toSDO(File file) {
    // TODO Auto-generated method stub
    return null;
  }
}