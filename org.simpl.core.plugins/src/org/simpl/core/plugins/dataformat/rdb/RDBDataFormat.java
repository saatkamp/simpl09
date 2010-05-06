package org.simpl.core.plugins.dataformat.rdb;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.simpl.core.plugins.dataformat.DataFormatPlugin;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b>Converts the data from a ResultSet to a DataObject. When converting
 * back from a DataObject, SQL statements are created, that can be executed on a RDB
 * database to create or update the data.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: TuscanyDataFormat.java 1224 2010-04-28 14:17:34Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDBDataFormat extends DataFormatPlugin<RDBResult, List<String>> {
  public RDBDataFormat() {
    this.setType("RDB");
    this.setSchemaFile("RDBDataFormat.xsd");
    this.setSchemaType("tRDBDataFormat");
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
          columnObject.set("type", resultSetMetaData.getColumnTypeName(i));
          columnObject.set("value", resultSet.getObject(resultSetMetaData
              .getColumnName(i)));
        }

        tableObject = null;
      }
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

    for (DataObject table : tables) {
      columns = table.getList("column");
      primaryKeys = table.getList("primaryKey");

      for (DataObject column : columns) {
        if (column.getString("type").equals("VARCHAR")) {
          quote = "'";
        } else {
          quote = "";
        }

        // create update statement
        statement = "UPDATE " + table.getString("schema") + "." + table.getString("name")
            + " SET " + column.getString("name") + "=" + quote
            + column.getString("value") + quote + " WHERE 1=1";

        for (String primaryKey : primaryKeys) {
          statement += " AND " + primaryKey + "="
              + findPrimaryKeyValue(primaryKey, columns);
        }

        statements.add(statement);
      }
      
      // create insert statement
      statement = "INSERT INTO " + table.getString("schema") + "."
          + table.getString("name") + "_1 " + createInsertColumnValues(columns);
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
      if (column.getString("type").equals("VARCHAR")) {
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
      if (columns.get(i).getString("type").equals("VARCHAR")) {
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
}