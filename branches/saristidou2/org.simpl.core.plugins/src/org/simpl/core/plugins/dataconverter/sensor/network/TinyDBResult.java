package org.simpl.core.plugins.dataconverter.sensor.network;

import java.util.List;
import java.util.Vector;

import org.simpl.resource.management.data.DataSource;
import net.tinyos.tinydb.QueryResult;

/**
 * <b>Purpose: Used by TinyDBDataFormat to hold the results.</b> <br>
 * <b>Description: A list of 'QueryResults', the heading for each column and the
 * belonging data source are saved.</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class TinyDBResult {
  private List<QueryResult> queryResults = null;
  @SuppressWarnings("rawtypes")
  private Vector columnHeadings = null;
  private DataSource dataSource = null;

  /**
   * 
   * @return the queryResults
   */
  public List<QueryResult> getQueryResults() {
    return this.queryResults;
  }

  /**
   * 
   * @return the columnHeadings
   */
  @SuppressWarnings("rawtypes")
  public Vector getColumnHeadings() {
    return this.columnHeadings;
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
   * @param queryResults
   *          the results to set
   */
  public void setQueryResults(List<QueryResult> queryResults) {
    this.queryResults = queryResults;
  }
  
/**
 * 
 * @param vector
 *            the column names to set
 */
  @SuppressWarnings("rawtypes")
  public void setColumnHeadings(Vector vector) {
    this.columnHeadings = vector;
  }

  /**
   * 
   * @param dataSource
   *           the data source to set
   */
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }
}
