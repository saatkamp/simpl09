package org.simpl.core.plugins.connector.rdb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataformat.relational.RDBResult;
import org.simpl.resource.management.client.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implements all methods of the {@link Connector} interface for
 * supporting the Apache Derby relational database in Client-Server mode.<br>
 * <b>Description:</b>dsAddress = Full path to embedded Derby database, for example:
 * C:\databases\myDB.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml<br>
 * @version $Id: DB2RDBDataSource.java 1014 2010-03-29 09:16:08Z michael.schneidt@arcor.de
 *          $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DerbyRDBConnector extends ConnectorPlugin<List<String>, RDBResult> {
  static Logger logger = Logger.getLogger(DerbyRDBConnector.class);

  /**
   * Initialize the plug-in.
   */
  public DerbyRDBConnector() {
    this.setType("Database");
    this.setMetaDataSchemaType("tDatabaseMetaData");
    this.addSubtype("Derby");
    this.addLanguage("Derby", "SQL");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {
    if (DerbyRDBConnector.logger.isDebugEnabled()) {
      DerbyRDBConnector.logger.debug("boolean executeStatement("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    boolean success = false;
    Connection conn = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());

    try {
      Statement stat = conn.createStatement();
      stat.execute(statement);
      conn.commit();
      success = true;
      stat.close();
    } catch (Throwable e) {
      DerbyRDBConnector.logger
          .error("exception executing the statement: " + statement, e);
      DerbyRDBConnector.logger.debug("Connection will be rolled back.");

      try {
        conn.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    DerbyRDBConnector.logger.info("Statement \"" + statement + "\" send to "
        + dataSource.getAddress() + ".");
    closeConnection(conn);

    return success;
  }

  @Override
  public RDBResult retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    if (DerbyRDBConnector.logger.isDebugEnabled()) {
      DerbyRDBConnector.logger.debug("DataObject retrieveData(" + dataSource.getAddress()
          + ", " + statement + ") executed.");
    }

    Connection connection = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());
    Statement connStatement = null;
    ResultSet resultSet = null;
    RDBResult rdbResult = null;

    try {
      connStatement = connection.createStatement();
      resultSet = connStatement.executeQuery(statement);

      rdbResult = new RDBResult();
      rdbResult.setDbMetaData(connection.getMetaData());
      rdbResult.setResultSet(resultSet);
      rdbResult.setDataSource(dataSource);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (rdbResult == null) {
      try {
        connStatement.close();
        connection.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    DerbyRDBConnector.logger.info("Statement \"" + statement + "\" executed on "
        + dataSource.getAddress() + ".");

    return rdbResult;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, List<String> statements,
      String target) throws ConnectionException {
    boolean success = false;

    Connection connection = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());
    Statement connStatement = null;

    if (DerbyRDBConnector.logger.isDebugEnabled()) {
      DerbyRDBConnector.logger.debug("boolean writeData(" + dataSource.getAddress()
          + ", DataObject) executed.");
    }

    if (statements != null && !statements.isEmpty()) {
      try {
        connStatement = connection.createStatement();

        for (String statement : statements) {
          if (target == null || target.equals("")) {
            if (statement.startsWith("UPDATE")) {
              connStatement.executeUpdate(statement);

              DerbyRDBConnector.logger.info("Statement \"" + statement + "\" "
                  + "executed on " + dataSource.getAddress()
                  + (success ? " was successful" : " failed"));
            }
          } else {
            if (statement.startsWith("INSERT")) {
              // replace dataObject's implizit schema.table name with target
              statement = statement.replaceAll("INSERT INTO .*?\\(", "INSERT INTO "
                  + target + " (");

              connStatement.executeUpdate(statement);

              DerbyRDBConnector.logger.info("Statement \"" + statement + "\" "
                  + "executed on " + dataSource.getAddress()
                  + (success ? " was successful" : " failed"));
            }
          }
        }

        // all statements executed without SQLException
        success = true;

        connStatement.close();
        connection.commit();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        DerbyRDBConnector.logger.debug("Connection will be rolled back.");

        try {
          connection.rollback();
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }

    closeConnection(connection);

    return success;
  }

  @Override
  public boolean queryData(DataSource dataSource, String statement, String target)
      throws ConnectionException {
    boolean success = false;

    if (DerbyRDBConnector.logger.isDebugEnabled()) {
      DerbyRDBConnector.logger.debug("DataObject depositData(" + dataSource.getAddress()
          + ", " + statement + ") executed.");
    }

    // TODO Nochmal überprüfen, ob das für Client-Server Derby auch gilt
    // Hier wird ein seit SQL2003 exisiterender erweiterter CREATE TABLE Befehl
    // genutzt.
    // Beispiel: CREATE TABLE TAB AS SELECT * FROM T1 WITH DATA;
    // Dies erzeugt aus den Query-Daten eine Neue Tabelle TAB mit den
    // gequerieten Daten.
    StringBuilder createTableStatement = new StringBuilder();
    createTableStatement.append("CREATE TABLE");
    createTableStatement.append(" ");
    createTableStatement.append(target);
    createTableStatement.append(" AS ");
    createTableStatement.append(statement);
    createTableStatement.append(" ");
    createTableStatement.append("WITH NO DATA");

    StringBuilder insertStatement = new StringBuilder();
    insertStatement.append("INSERT INTO");
    insertStatement.append(" ");
    insertStatement.append(target);
    insertStatement.append(" ");
    insertStatement.append(statement);

    Connection conn = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());

    try {
      Statement createState = conn.createStatement();
      Statement insertState = conn.createStatement();

      // Neue Tabelle aus dem Query erzeugen
      createState.execute(createTableStatement.toString());

      // Query-Daten in die neue Tabelle einfügen
      insertState.execute(insertStatement.toString());

      conn.commit();
      createState.close();
      insertState.close();
      closeConnection(conn);

      success = true;
    } catch (Throwable e) {
      DerbyRDBConnector.logger.error("exception executing the statement: "
          + createTableStatement.toString(), e);
      DerbyRDBConnector.logger.debug("Connection will be rolled back.");
      try {
        conn.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    DerbyRDBConnector.logger.info("Statement \"" + createTableStatement.toString()
        + "\" " + "& \"" + insertStatement.toString() + "\" " + "executed on "
        + dataSource.getAddress());

    return success;
  }

  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    Connection conn = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());

    DataObject metaDataObject = this.getMetaDataSDO();
    DataObject schemaObject = null;
    DataObject tableObject = null;
    DataObject columnObject = null;

    try {
      DatabaseMetaData dbMetaData = conn.getMetaData();
      ResultSet schemas = dbMetaData.getSchemas();

      while (schemas.next()) {
        schemaObject = metaDataObject.createDataObject("schema");
        schemaObject.setString("name", schemas.getString(1));

        ResultSet tables = dbMetaData.getTables(null, schemas.getString(1), null, null);

        while (tables.next()) {
          tableObject = schemaObject.createDataObject("table");
          tableObject.setString("name", tables.getString(3));
        }

        ResultSet columns = dbMetaData.getColumns(null, schemas.getString(1), null, null);

        while (columns.next()) {
          columnObject = tableObject.createDataObject("column");
          columnObject.setString("name", columns.getString("COLUMN_NAME"));
          columnObject.setString("type", columns.getString("TYPE_NAME"));
        }
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return metaDataObject;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean createTarget(DataSource dataSource, DataObject dataObject, String target)
      throws ConnectionException {
    boolean createdTarget = false;

    if (MySQLRDBConnector.logger.isDebugEnabled()) {
      MySQLRDBConnector.logger.debug("createTarget '" + target + "' on '"
          + dataSource.getAddress() + "'.");
    }

    // test if target already exists
    try {
      createdTarget = this.issueCommand(dataSource, "SELECT * FROM " + target);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (!createdTarget) {
      List<DataObject> tables = dataObject.getList("table");
      DataObject tableMetaData = tables.get(0).getDataObject("rdbTableMetaData");
      List<DataObject> rows = tables.get(0).getList("row");
      List<DataObject> columns = rows.get(0).getList("column");
      List<String> primaryKeys = this.getPrimaryKeys(tableMetaData);
      String createTargetStatement = null;

      // build create statement
      createTargetStatement = "CREATE TABLE " + target + " (";

      // create table with columns
      for (int i = 0; i < columns.size(); i++) {
        if (i > 0) {
          createTargetStatement += ",";
        }

        createTargetStatement += columns.get(i).getString("name") + " "
            + this.getColumnType(columns.get(i).getString("name"), tableMetaData);
      }

      // add primary keys
      if (!primaryKeys.isEmpty()) {
        createTargetStatement += ", PRIMARY KEY (";

        for (int j = 0; j < primaryKeys.size(); j++) {
          createTargetStatement += primaryKeys.get(j);

          if (j < primaryKeys.size() - 1) {
            createTargetStatement += ",";
          }
        }

        createTargetStatement += ")";
      }

      createTargetStatement += ")";
      createdTarget = this.issueCommand(dataSource, createTargetStatement);
    }

    return createdTarget;
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
      if (columnTypeObject.getString("columnName").equals(column)) {
        columnType = columnTypeObject.getString(0);

        break;
      }
    }

    return columnType;
  }

  /**
   * Opens a connection.
   * 
   * @param dsAddress
   * @param user
   * @param password
   * @return
   * @throws ConnectionException
   */
  private Connection openConnection(String dsAddress, String user, String password)
      throws ConnectionException {
    if (DerbyRDBConnector.logger.isDebugEnabled()) {
      DerbyRDBConnector.logger.debug("Connection openConnection(" + dsAddress
          + ") executed.");
    }

    Connection connect = null;

    try {
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      StringBuilder uri = new StringBuilder();
      // jdbc:derby:sampleDB", "dba", "password");
      uri.append("jdbc:derby://");
      uri.append(dsAddress);

      try {
        connect = DriverManager.getConnection(uri.toString(), user, password);
        connect.setAutoCommit(false);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        DerbyRDBConnector.logger.fatal("exception during establishing connection to: "
            + uri.toString(), e);
      }

      DerbyRDBConnector.logger.info("Connection opened on " + dsAddress + ".");

      return connect;
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      DerbyRDBConnector.logger.fatal("exception during loading the JDBC driver", e);
    }

    return connect;
  }

  /**
   * Closes a connection.
   * 
   * @param connection
   * @return
   * @throws ConnectionException
   */
  private boolean closeConnection(Connection connection) throws ConnectionException {
    boolean success = false;

    try {
      (connection).close();
      success = true;

      if (DerbyRDBConnector.logger.isDebugEnabled()) {
        DerbyRDBConnector.logger
            .debug("boolean closeConnection() executed successfully.");
      }

      DerbyRDBConnector.logger.info("Connection closed.");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      if (DerbyRDBConnector.logger.isDebugEnabled()) {
        DerbyRDBConnector.logger.error(
            "boolean closeConnection() executed with failures.", e);
      }
    }

    return success;
  }
}