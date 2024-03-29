package org.simpl.core.plugins.dataformat.rdb;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.plugins.dataformat.DataFormatPlugin;
import org.simpl.core.plugins.datasource.rdb.DB2RDBDataSourceService;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Used to create a SDO from RDB result and vice versa.<br>
 * <b>Description:</b>Converts the data from a ResultSet to a DataObject. When converting
 * back from a DataObject, SQL statements are created, that can be executed on a RDB
 * database to create or update the data.<br>
 * Because the result set and data base meta data objects need a connection to be able to
 * retrieve data from, the connection is not closed by the data source service
 * retrieveData() method but after toSDO().<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * TODO: create list of quoted column data types, currently only VARCHAR is supported
 * 
 * @author schneimi<br>
 * @version $Id: TuscanyDataFormat.java 1224 2010-04-28 14:17:34Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDBDataFormat extends DataFormatPlugin<RDBResult, List<String>> {
  static Logger logger = Logger.getLogger(DB2RDBDataSourceService.class);

  public RDBDataFormat() {
    this.setType("RDB");
    this.setSchemaFile("RDBDataFormat.xsd");
    this.setSchemaType("tRDBDataFormat");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object )
   */
  public DataObject toSDO(RDBResult result) {
    DataObject rdbDataObject = this.getSDO();
    DataObject tableObject = null;
    DataObject columnObject = null;

    ResultSet resultSet = result.getResultSet();
    ResultSet primaryKey = null;
    DatabaseMetaData dbMetaData = result.getDbMetaData();

    List<String> primaryKeyList = new ArrayList<String>();

    if (RDBDataFormat.logger.isDebugEnabled()) {
      RDBDataFormat.logger.debug("Convert data from 'RDBResult' to 'DataObject'.");
    }

    try {
      while (resultSet.next()) {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i < resultSetMetaData.getColumnCount() + 1; i++) {
          // add table
          if (tableObject == null) {
            tableObject = rdbDataObject.createDataObject("table");
            tableObject.set("name", resultSetMetaData.getTableName(i));
            tableObject.set("catalog", resultSetMetaData.getCatalogName(i));
            tableObject.set("schema", resultSetMetaData.getSchemaName(i));
          }

          // add primary keys
          primaryKeyList.clear();
          primaryKey = dbMetaData.getPrimaryKeys(resultSetMetaData.getCatalogName(i),
              resultSetMetaData.getSchemaName(i), resultSetMetaData.getTableName(i));

          while (primaryKey.next()) {
            for (int j = 1; j < primaryKey.getFetchSize() + 1; j++) {
              primaryKeyList.add(primaryKey.getString("COLUMN_NAME"));
            }
          }

          tableObject.set("primaryKey", primaryKeyList);

          // add columns
          columnObject = tableObject.createDataObject("column");
          columnObject.set("name", resultSetMetaData.getColumnName(i));

          columnObject.set("type", resultSetMetaData.getColumnTypeName(i)
              + "("
              + this.getColumnSize(dbMetaData, resultSetMetaData.getTableName(i),
                  resultSetMetaData.getColumnName(i)) + ")");
          columnObject.set("value", resultSet.getObject(resultSetMetaData
              .getColumnName(i)));
        }

        tableObject = null;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // disconnect from the data source
    try {
      dbMetaData.getConnection().commit();
      dbMetaData.getConnection().close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return rdbDataObject;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
   * .DataObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<String> fromSDO(DataObject dataObject) {
    List<String> statements = new ArrayList<String>();
    List<DataObject> tables = dataObject.getList("table");
    List<DataObject> columns = null;
    List<String> primaryKeys = null;
    String quote = "";
    String statement = "";
    String tableSchema = "";

    if (RDBDataFormat.logger.isDebugEnabled()) {
      RDBDataFormat.logger.debug("Convert data from 'DataObject' to 'List<String>'.");
    }

    for (DataObject table : tables) {
      columns = table.getList("column");
      primaryKeys = table.getList("primaryKey");

      for (DataObject column : columns) {
        if (column.getString("type").startsWith("VARCHAR")) {
          quote = "'";
        } else {
          quote = "";
        }

        if (table.getString("schema").equals("")) {
          tableSchema = table.getString("name");
        } else {
          tableSchema = table.getString("schema") + "." + table.getString("name");
        }

        // create update statement
        statement = "UPDATE " + tableSchema + " SET " + column.getString("name") + "="
            + quote + column.getString("value") + quote + " WHERE 1=1";

        for (String primaryKey : primaryKeys) {
          statement += " AND " + primaryKey + "="
              + findPrimaryKeyValue(primaryKey, columns);
        }

        logger.debug("Created UPDATE statement: " + statement);
        statements.add(statement);
      }

      // create insert statement
      statement = "INSERT INTO " + tableSchema + " " + createInsertColumnValues(columns);
      logger.debug("Created INSERT statement: " + statement);
      statements.add(statement);
    }

    return statements;
  }

  /**
   * Searches for a primary key value in the columns of a table.
   * 
   * @param primaryKey
   * @param columns
   * @return
   */
  private String findPrimaryKeyValue(String primaryKey, List<DataObject> columns) {
    String primaryKeyValue = "";
    String quote = "";

    for (DataObject column : columns) {
      if (column.getString("type").startsWith("VARCHAR")) {
        quote = "'";
      } else {
        quote = "";
      }

      if (column.getString("name").equals(primaryKey)) {
        primaryKeyValue = quote + column.getString("value") + quote;
        break;
      }
    }

    return primaryKeyValue;
  }

  /**
   * Creates a comma sparated columns and values statement for an insert statement.
   * 
   * @param columns
   * @return
   */
  private String createInsertColumnValues(List<DataObject> columns) {
    String insertColumns = "(";
    String quote = "";

    for (int i = 0; i < columns.size(); i++) {
      insertColumns += columns.get(i).getString("name");

      if (i < columns.size() - 1) {
        insertColumns += ",";
      } else {
        insertColumns += ")";
      }
    }

    insertColumns += " VALUES (";

    for (int i = 0; i < columns.size(); i++) {
      if (columns.get(i).getString("type").startsWith("VARCHAR")) {
        quote = "'";
      } else {
        quote = "";
      }

      insertColumns += quote + columns.get(i).getString("value") + quote;

      if (i < columns.size() - 1) {
        insertColumns += ",";
      } else {
        insertColumns += ")";
      }
    }

    return insertColumns;
  }

  /**
   * Retrieves the size of a table's column from the database meta data.
   * 
   * @param dbMetaData
   * @param tableName
   * @param columnName
   * @return
   */
  private int getColumnSize(DatabaseMetaData dbMetaData, String tableName,
      String columnName) {
    int size = 0;

    ResultSet columns = null;

    try {
      columns = dbMetaData.getColumns(null, null, tableName, null);

      while (columns.next()) {
        if (columns.getString("COLUMN_NAME").equals(columnName)) {
          size = columns.getInt("COLUMN_SIZE");
        }
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return size;
  }
}