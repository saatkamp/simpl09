package org.simpl.core.plugins.dataformat.relational;

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
 * retrieveData() method but after toSDO(). The method fromSDO() is only working if the
 * table has primary keys<br>
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
    this.setSchemaFile("RelationalDataFormat.xsd");
    this.setSchemaType("tRelationalDataFormat");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object )
   */
  public DataObject toSDO(RDBResult rdbResult) {
    DataObject rdbDataObject = this.getSDO();
    DataObject dataFormatMetaDataObject = rdbDataObject
        .createDataObject("dataFormatMetaData");
    DataObject tableObject = rdbDataObject.createDataObject("table");
    DataObject rdbTableMetaDataObject = tableObject.createDataObject("rdbTableMetaData");
    DataObject columnTypeObject = null;
    DataObject rowObject = null;
    DataObject columnObject = null;

    ResultSet resultSet = rdbResult.getResultSet();
    ResultSet primaryKeys = null;
    DatabaseMetaData dbMetaData = rdbResult.getDbMetaData();

    List<String> primaryKeyList = new ArrayList<String>();
    boolean isSetTableMetaData = false;

    if (RDBDataFormat.logger.isDebugEnabled()) {
      RDBDataFormat.logger.debug("Convert data from 'RDBResult' to 'DataObject'.");
    }

    // add data format meta data
    dataFormatMetaDataObject.set("dataSource", rdbResult.getDataSource().getName());

    try {
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

      // get primary keys (the catalog attribute is set null because it doesn't reflect
      // the DB2 catalog, and no primary keys are retrieved in that case)
      primaryKeys = dbMetaData.getPrimaryKeys(null, resultSetMetaData.getSchemaName(1)
          .trim(), resultSetMetaData.getTableName(1));

      while (primaryKeys.next()) {
        primaryKeyList.add(primaryKeys.getString("COLUMN_NAME"));
      }

      // add table meta data
      rdbTableMetaDataObject.setString("schema", resultSetMetaData.getSchemaName(1)
          .trim());
      rdbTableMetaDataObject.setString("name", resultSetMetaData.getTableName(1));
      rdbTableMetaDataObject.setString("catalog", resultSetMetaData.getCatalogName(1));

      while (resultSet.next()) {
        // add row
        rowObject = tableObject.createDataObject("row");

        for (int i = 1; i < resultSetMetaData.getColumnCount() + 1; i++) {
          // add table meta data
          if (!isSetTableMetaData) {
            columnTypeObject = rdbTableMetaDataObject.createDataObject("columnType");
            columnTypeObject.set(0, resultSetMetaData.getColumnTypeName(i)
                + "("
                + this.getColumnSize(dbMetaData, resultSetMetaData.getTableName(i),
                    resultSetMetaData.getColumnName(i)) + ")");
            columnTypeObject.set("columnName", resultSetMetaData.getColumnName(i));

            if (primaryKeyList.contains(resultSetMetaData.getColumnName(i))) {
              columnTypeObject.set("isPrimaryKey", true);
            }
          }

          // add column
          columnObject = rowObject.createDataObject("column");
          columnObject.set("name", resultSetMetaData.getColumnName(i));
          columnObject.set(0, resultSet.getString(resultSetMetaData.getColumnName(i)));
        }

        isSetTableMetaData = true;
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
    List<DataObject> rows = null;
    List<DataObject> columns = null;
    List<String> primaryKeys = null;
    DataObject tableMetaData = null;
    String quote = "";
    String statement = "";
    String tableSchema = "";

    if (RDBDataFormat.logger.isDebugEnabled()) {
      RDBDataFormat.logger.debug("Convert data from 'DataObject' to 'List<String>'.");
    }

    for (DataObject table : tables) {
      tableMetaData = table.getDataObject("rdbTableMetaData");
      rows = table.getList("row");

      // get primary keys
      primaryKeys = this.getPrimaryKeys(tableMetaData);

      for (DataObject row : rows) {
        columns = row.getList("column");

        for (DataObject column : columns) {
          if (this.getColumnType(column.getString("name"), tableMetaData).startsWith(
              "VARCHAR")) {
            quote = "'";
          } else {
            quote = "";
          }

          if (tableMetaData.getString("schema").equals("")) {
            tableSchema = tableMetaData.getString("name");
          } else {
            tableSchema = tableMetaData.getString("schema") + "."
                + tableMetaData.getString("name");
          }

          // create update statement
          statement = "UPDATE " + tableSchema + " SET " + column.getString("name") + "="
              + quote + column.getString("value") + quote + " WHERE 1=1";

          for (String primaryKey : primaryKeys) {
            statement += " AND " + primaryKey + "="
                + getPrimaryKeyValue(primaryKey, columns, tableMetaData);
          }

          logger.debug("Created UPDATE statement: " + statement);
          statements.add(statement);
        }

        // create insert statement
        statement = "INSERT INTO " + tableSchema + " "
            + createInsertColumnValues(columns, tableMetaData);
        logger.debug("Created INSERT statement: " + statement);
        statements.add(statement);
      }
    }

    return statements;
  }

  /**
   * Returns the primary key columns from the table meta data.
   * 
   * @param tableMetaData
   * @return
   */
  @SuppressWarnings("unchecked")
  private List<String> getPrimaryKeys(DataObject tableMetaData) {
    List<String> primaryKeys = new ArrayList<String>();
    List<DataObject> columnTypes = tableMetaData.getList("columnType");

    for (DataObject columnType : columnTypes) {
      if (columnType.getBoolean("isPrimaryKey")) {
        primaryKeys.add(columnType.getString("columnName"));
      }
    }

    return primaryKeys;
  }

  /**
   * Returns the column type of a column from the table meta data.
   * 
   * @param column
   * @param tableMetaData
   */
  @SuppressWarnings("unchecked")
  private String getColumnType(String column, DataObject tableMetaData) {
    String columnType = "";
    List<DataObject> columnTypes = tableMetaData.getList("columnType");

    for (DataObject columnTypeObject : columnTypes) {
      if (columnTypeObject.getString("columnName").equals("column")) {
        columnType = columnTypeObject.getString(0);
        break;
      }
    }

    return columnType;
  }

  /**
   * Searches for a primary key value in the columns of a table.
   * 
   * @param primaryKey
   * @param columns
   * @param tableMetaData
   * @return
   */
  private String getPrimaryKeyValue(String primaryKey, List<DataObject> columns,
      DataObject tableMetaData) {
    String primaryKeyValue = "";
    String quote = "";

    for (DataObject column : columns) {
      if (this.getColumnType(column.getString("name"), tableMetaData).startsWith(
          "VARCHAR")) {
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
   * @param tableMetaData
   * @return
   */
  private String createInsertColumnValues(List<DataObject> columns,
      DataObject tableMetaData) {
    String insertColumns = "(";
    String quote = "";

    for (int i = 0; i < columns.size(); i++) {
      insertColumns += columns.get(i).getString("name");

      if (i < columns.size() - 1) {
        insertColumns += ", ";
      } else {
        insertColumns += ")";
      }
    }

    insertColumns += " VALUES (";

    for (int i = 0; i < columns.size(); i++) {
      if (this.getColumnType(columns.get(i).getString("name"), tableMetaData).startsWith(
          "VARCHAR")) {
        quote = "'";
      } else {
        quote = "";
      }

      insertColumns += quote + columns.get(i).getString("value") + quote;

      if (i < columns.size() - 1) {
        insertColumns += ", ";
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