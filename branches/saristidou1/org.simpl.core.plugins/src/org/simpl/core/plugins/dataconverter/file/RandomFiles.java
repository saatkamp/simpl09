package org.simpl.core.plugins.dataconverter.file;

import java.io.File;

import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose:</b>Used by RandomFilesDataFormat and CSVDataFormat to hold a file or folder
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
public class RandomFiles {
  private File[] files = null;
  private DataSource dataSource = null;

  /**
   * @return the files
   */
  public File[] getFiles() {
    return files;
  }

  /**
   * @param files
   *          the files to set
   */
  public void setFiles(File[] files) {
    this.files = files;
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