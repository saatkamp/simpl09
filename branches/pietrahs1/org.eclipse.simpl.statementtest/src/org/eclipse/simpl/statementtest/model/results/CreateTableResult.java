package org.eclipse.simpl.statementtest.model.results;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Purpose:</b>Result that holds all information about a created table.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: RDBResult.java 78 2010-07-24 23:54:40Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class CreateTableResult extends Result {
  String table = null;
  List<String> columns = new ArrayList<String>();
  List<String> primaryKeys = new ArrayList<String>();

  /**
   * Initializes the table result.
   * 
   * @param table
   * @param columns
   * @param primaryKeys
   */
  public CreateTableResult(String table, List<String> columns, List<String> primaryKeys) {
    this.table = table;
    this.columns = columns;
    this.primaryKeys = primaryKeys;
  }

  /**
   * @return the table
   */
  public String getTable() {
    return table;
  }

  /**
   * @return List of columns.
   */
  public List<String> getColumns() {
    return columns;
  }

  /**
   * @return List of primary keys.
   */
  public List<String> getPrimaryKeys() {
    return primaryKeys;
  }
}