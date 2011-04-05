package org.simpl.core.plugins.dataformat.file;

import java.io.File;

import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose:</b>Used by RandomFileDataFormat and CSVDataFormat to hold a file or folder
 * and meta data like the data source the file was retrieved from.<br>
 * <b>Description:</b>The meta data is used to give additional information about the
 * retrieved data besides the actual data.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RandomFile {
  private File file = null;
  private DataSource dataSource = null;

  /**
   * @return the file
   */
  public File getFile() {
    return file;
  }

  /**
   * @param file
   *          the file to set
   */
  public void setFile(File file) {
    this.file = file;
  }

  /**
   * @return the dataSource
   */
  public DataSource getDataSource() {
    return dataSource;
  }

  /**
   * @param dataSource
   *          the dataSource to set
   */
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }
}