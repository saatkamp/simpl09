package org.simpl.core.plugins.dataformat.rdb;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * <b>Purpose: Used by RDBDataFormat to hold the resultSet and the associated database
 * meta data.</b> <br>
 * <b>Description: The database meta data is needed zo retrieve the primary keys when
 * preparing update statments from the result set.</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDBResult {
  private ResultSet resultSet = null;
  private DatabaseMetaData dbMetaData = null;
  private String target = null;

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
   * @return the target
   */
  public String getTarget() {
    return target;
  }

  /**
   * @param target
   *          the target to set
   */
  public void setTarget(String target) {
    this.target = target;
  }
}