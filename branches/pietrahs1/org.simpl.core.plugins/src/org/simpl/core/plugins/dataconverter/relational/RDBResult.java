package org.simpl.core.plugins.dataconverter.relational;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose: Used by RDBDataFormat to hold the resultSet and the associated database
 * meta data.</b> <br>
 * <b>Description: The database meta data is needed to retrieve the primary keys when
 * preparing update statements from the result set.</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: RDBResult.java 1815 2011-07-05 12:33:12Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDBResult {
  private ResultSet resultSet = null;
  private DatabaseMetaData dbMetaData = null;
  private DataSource dataSource = null;

  /**
   * @return the resultSet
   */
  public ResultSet getResultSet() {
    return resultSet;
  }

  /**
   * @param resultSet
   *          the resultSet to set
   */
  public void setResultSet(ResultSet resultSet) {
    this.resultSet = resultSet;
  }

  /**
   * @return the dbMetaData
   */
  public DatabaseMetaData getDbMetaData() {
    return dbMetaData;
  }

  /**
   * @param dbMetaData
   *          the dbMetaData to set
   */
  public void setDbMetaData(DatabaseMetaData dbMetaData) {
    this.dbMetaData = dbMetaData;
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