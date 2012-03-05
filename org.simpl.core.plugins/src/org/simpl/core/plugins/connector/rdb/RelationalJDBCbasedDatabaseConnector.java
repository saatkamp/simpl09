package org.simpl.core.plugins.connector.rdb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.relational.RDBResult;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implements all methods of the {@link Connector} interface for
 * supporting relational and JDBC based databases.<br>
 * <b>Description:</b>dsAddress = path to database<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class RelationalJDBCbasedDatabaseConnector extends
    ConnectorPlugin<List<String>, RDBResult> {
  static Logger logger = Logger
      .getLogger(RelationalJDBCbasedDatabaseConnector.class);

  /**
   * Initialize the plug-in
   */
  public RelationalJDBCbasedDatabaseConnector() {
    this.setType("Database");
    this.setMetaDataSchemaType("tDatabaseMetaData");
    this.addSubtype("Relational");
    this.addLanguage("Relational", "SQL");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {
    if (RelationalJDBCbasedDatabaseConnector.logger.isDebugEnabled()) {
      RelationalJDBCbasedDatabaseConnector.logger
          .debug("boolean executeStatement(" + dataSource.getAddress() + ", "
              + statement + ") executed.");
    }

    boolean success = false;
    Connection connection = openConnection(dataSource);

    try {
      Statement connStatement = connection.createStatement();
      connStatement.execute(statement);
      connStatement.close();
      connection.commit();
      success = true;
    } catch (Throwable e) {
      RelationalJDBCbasedDatabaseConnector.logger.error(
          "exception executing the statement: " + statement, e);
      RelationalJDBCbasedDatabaseConnector.logger
          .debug("Connection will be rolled back.");

      try {
        connection.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    closeConnection(connection);

    RelationalJDBCbasedDatabaseConnector.logger.info("Statement \"" + statement
        + "\" send to " + dataSource.getAddress() + ".");

    return success;
  }

  @Override
  public RDBResult retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    if (RelationalJDBCbasedDatabaseConnector.logger.isDebugEnabled()) {
      RelationalJDBCbasedDatabaseConnector.logger
          .debug("DataObject retrieveData(" + dataSource.getAddress() + ", "
              + statement + ") executed.");
    }

    Connection connection = openConnection(dataSource);

    Statement connStatement = null;

    ResultSet resultSet = null;
    ResultSetMetaData resultSetMetaData = null;
    DatabaseMetaData databaseMetaData = null;

    RDBResult rdbResult = null;

    try {
      connStatement = connection.createStatement();
      resultSet = connStatement.executeQuery(statement);
      resultSetMetaData = resultSet.getMetaData();
      databaseMetaData = connection.getMetaData();

      // save primary keys
      rdbResult = new RDBResult();
      rdbResult.setPrimaryKeysRowSet(databaseMetaData.getPrimaryKeys(null,
          resultSetMetaData.getSchemaName(1).trim(),
          resultSetMetaData.getTableName(1)));
      // save description of table columns
      for (int i = 1; i < resultSetMetaData.getColumnCount() + 1; i++) {
        rdbResult.addColumnRowSetList(databaseMetaData.getColumns(null, null,
            resultSetMetaData.getTableName(i), null));
      }
      ;
      // save result sset
      rdbResult.setResultRowSet(resultSet);
      // save datasource
      rdbResult.setDataSource(dataSource);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    closeConnection(connection);

    RelationalJDBCbasedDatabaseConnector.logger.info("Statement \"" + statement
        + "\" executed on " + dataSource.getAddress() + ".");

    return rdbResult;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, List<String> statements,
      String target) throws ConnectionException {
    boolean success = false;

    Connection connection = openConnection(dataSource);
    Statement connStatement = null;

    if (RelationalJDBCbasedDatabaseConnector.logger.isDebugEnabled()) {
      RelationalJDBCbasedDatabaseConnector.logger.debug("boolean writeData("
          + dataSource.getAddress() + ", DataObject) executed.");
    }

    if (statements != null && !statements.isEmpty()) {
      try {
        connStatement = connection.createStatement();

        for (String statement : statements) {
          if (target == null || target.equals("")) {
            if (statement.startsWith("UPDATE")) {
              connStatement.executeUpdate(statement);

              RelationalJDBCbasedDatabaseConnector.logger.info("Statement \""
                  + statement + "\" " + "executed on "
                  + dataSource.getAddress() + ".");
            }
          } else {
            if (statement.startsWith("INSERT")) {
              // replace dataObject's including schema.table name
              // with target name
              statement = statement.replaceAll("INSERT INTO .*?\\(",
                  "INSERT INTO " + target + " (");

              connStatement.executeUpdate(statement);

              RelationalJDBCbasedDatabaseConnector.logger.info("Statement \""
                  + statement + "\" " + "executed on "
                  + dataSource.getAddress() + ".");
            }
          }
        }
        connStatement.close();
        connection.commit();
        success = true;
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        RelationalJDBCbasedDatabaseConnector.logger
            .debug("Connection will be rolled back.");

        try {
          connection.rollback();
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }

    closeConnection(connection);

    RelationalJDBCbasedDatabaseConnector.logger.info("Statements executed on "
        + dataSource.getAddress());

    return success;
  }

  @Override
  public boolean queryData(DataSource dataSource, String statement,
      String target) throws ConnectionException {
    boolean success = false;

    if (RelationalJDBCbasedDatabaseConnector.logger.isDebugEnabled()) {
      RelationalJDBCbasedDatabaseConnector.logger
          .debug("DataObject depositData(" + dataSource.getAddress() + ", "
              + statement + ") executed.");
    }

    // create insert statement
    StringBuilder insertStatement = new StringBuilder();
    insertStatement.append("INSERT INTO");
    insertStatement.append(" ");
    insertStatement.append(target);
    insertStatement.append(" ");
    insertStatement.append(statement);

    Connection connection = openConnection(dataSource);

    try {
      Statement connStatement = connection.createStatement();

      // insert data
      connStatement.execute(insertStatement.toString());

      connStatement.close();
      connection.commit();
      success = true;
    } catch (Throwable e) {
      RelationalJDBCbasedDatabaseConnector.logger
          .error(
              "exception executing the statement: "
                  + insertStatement.toString(), e);
      RelationalJDBCbasedDatabaseConnector.logger
          .debug("Connection will be rolled back.");

      try {
        connection.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    closeConnection(connection);

    RelationalJDBCbasedDatabaseConnector.logger.info("Statement \""
        + insertStatement.toString() + "\" " + "executed on "
        + dataSource.getAddress());

    return success;
  }

  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    Connection conn = openConnection(dataSource);

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

        ResultSet tables = dbMetaData.getTables(null, schemas.getString(1),
            null, null);

        while (tables.next()) {
          tableObject = schemaObject.createDataObject("table");
          tableObject.setString("name", tables.getString(3));
        }

        ResultSet columns = dbMetaData.getColumns(null, schemas.getString(1),
            null, null);

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

  private String getFromPropertiesDescription(String property,
      String propertiesDescription) {
    String value = null;
    Pattern pattern = Pattern.compile("<" + property + ">(.*?)</" + property
        + ">");
    Matcher matcher = pattern.matcher(propertiesDescription);

    if (matcher.find()) {
      value = matcher.group(1);
    }

    // Workaround: sometimes the matcher returns null as string!
    if (value != null && value.equals("null")) {
      value = null;
    }

    return value;
  }

  /**
   * Opens a connection.
   * 
   * @param dataSource
   * @return
   * @throws ConnectionException
   */
  private Connection openConnection(DataSource dataSource)
      throws ConnectionException {
    if (RelationalJDBCbasedDatabaseConnector.logger.isDebugEnabled()) {
      RelationalJDBCbasedDatabaseConnector.logger
          .debug("Connection openConnection(" + dataSource.getAddress()
              + ") executed.");
    }

    Connection connect = null;

    try {
      String driverName = getFromPropertiesDescription("driverName",
          dataSource.getConnectorPropertiesDescription());
      String addressPrefix = getFromPropertiesDescription("addressPrefix",
          dataSource.getConnectorPropertiesDescription());
      String dsAddress = dataSource.getAddress();
      String user = dataSource.getAuthentication().getUser();
      String password = dataSource.getAuthentication().getPassword();

      Class.forName(driverName);
      StringBuilder uri = new StringBuilder();
      uri.append(addressPrefix);
      uri.append(dsAddress);

      try {
        if (!user.equals("")) {
          connect = DriverManager.getConnection(uri.toString(), user, password);
          connect.setAutoCommit(false);
        } else {
          connect = DriverManager.getConnection(dsAddress);
          connect.setAutoCommit(false);
        }
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        RelationalJDBCbasedDatabaseConnector.logger.fatal(
            "exception during establishing connection to: "
                + dataSource.getAddress(), e);
      }

      RelationalJDBCbasedDatabaseConnector.logger.info("Connection opened on "
          + dsAddress + ".");
      return connect;
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      RelationalJDBCbasedDatabaseConnector.logger.fatal(
          "exception during loading the JDBC driver", e);
    }

    return connect;
  }

  /**
   * Closes a connection.
   * 
   * @param connection
   * @return
   */
  private boolean closeConnection(Connection connection) {
    boolean success = false;

    try {
      (connection).close();
      success = true;
      if (RelationalJDBCbasedDatabaseConnector.logger.isDebugEnabled()) {
        RelationalJDBCbasedDatabaseConnector.logger
            .debug("boolean closeConnection() executed successfully.");
      }
      RelationalJDBCbasedDatabaseConnector.logger.info("Connection closed.");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      if (RelationalJDBCbasedDatabaseConnector.logger.isDebugEnabled()) {
        RelationalJDBCbasedDatabaseConnector.logger.error(
            "boolean closeConnection() executed with failures.", e);
      }
    }

    return success;
  }

}
