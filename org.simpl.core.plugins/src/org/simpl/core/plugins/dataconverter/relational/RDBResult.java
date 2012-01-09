package org.simpl.core.plugins.dataconverter.relational;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.simpl.resource.management.data.DataSource;

import com.sun.rowset.CachedRowSetImpl;

/**
 * <b>Purpose: Used by RDBDataFormat to hold the resultSet and the associated
 * database meta data.</b> <br>
 * <b>Description: The result set, the primary keys and a description of table
 * columns available in the catalog are saved. </b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: RDBResult.java 1815 2011-07-05 12:33:12Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDBResult {

  // the original result as a cached row set object
  private CachedRowSet resultRowSet = null;
  // the primary keys result set as a cached row set object
  private CachedRowSet primaryKeyRowSet = null;
  // a list of metadata (a cached row set object for each column)
  private List<CachedRowSet> columnRowSetList = null;

  private DataSource dataSource = null;

  public RDBResult() {
    columnRowSetList = new LinkedList<CachedRowSet>();
  }

  /**
   * 
   * @return the resultRowSet
   */
  public CachedRowSet getResultRowSet() {
    return this.resultRowSet;
  }

  /**
   * 
   * @param resultSet
   *          the result set to convert and set
   */
  public void setResultRowSet(ResultSet resultSet) {
    try {
      resultRowSet = new CachedRowSetImpl();
      resultRowSet.populate(resultSet);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 
   * @return the primaryKeyRowSet
   */
  public CachedRowSet getPrimaryKeyRowSet() {
    return this.primaryKeyRowSet;
  }

  /**
   * 
   * @param primaryKeysResultSet
   *          the result to convert and set
   */
  public void setPrimaryKeysRowSet(ResultSet primaryKeysResultSet) {
    try {
      primaryKeyRowSet = new CachedRowSetImpl();
      primaryKeyRowSet.populate(primaryKeysResultSet);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 
   * @param i
   *          the specified list entry
   * @return the columnRowSet
   */
  public CachedRowSet getColumnRowSet(int i) {
    return this.columnRowSetList.get(i);
  }

  /**
   * 
   * @param columnResultSet
   *          the result set to convert and add
   */
  public void addColumnRowSetList(ResultSet columnResultSet) {
    try {
      CachedRowSet columnRowSet = new CachedRowSetImpl();
      columnRowSet.populate(columnResultSet);
      columnRowSetList.add(columnRowSet);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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