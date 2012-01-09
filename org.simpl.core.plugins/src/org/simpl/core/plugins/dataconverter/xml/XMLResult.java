package org.simpl.core.plugins.dataconverter.xml;

import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose: Used by XMLDataFormat to hold the results.</b> <br>
 * <b>Description: A xml-fragment or xml-document is saved as StringBuffer object.</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class XMLResult {
  private StringBuffer document = null;
  private DataSource dataSource = null;

  /**
   * 
   * @return the document
   */
  public StringBuffer getDocument() {
    return this.document;
  }

  /**
   * 
   * @param document
   *          the document to set
   */
  public void setDocument(StringBuffer document) {
    this.document = document;
  }

  /**
   * 
   * @return the dataSource
   */
  public DataSource getDataSource() {
    return this.dataSource;
  }

  /**
   * 
   * @param dataSource
   *          the dataSource to set
   */
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }
}
