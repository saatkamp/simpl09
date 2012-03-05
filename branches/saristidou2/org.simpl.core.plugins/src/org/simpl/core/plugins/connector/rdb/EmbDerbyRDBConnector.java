package org.simpl.core.plugins.connector.rdb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.connection.JDCConnectionDriver;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.relational.RDBResult;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implements all methods of the {@link Connector} interface for
 * supporting the Apache Derby relational database in embedded mode.<br>
 * <b>Description:</b>dsAddress = Full path to embedded Derby database, for
 * example: C:\databases\myDB.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml<br>
 * @version $Id: DB2RDBDataSource.java 1014 2010-03-29 09:16:08Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class EmbDerbyRDBConnector extends
    ConnectorPlugin<List<String>, RDBResult> {
  static Logger logger = Logger.getLogger(EmbDerbyRDBConnector.class);

  /**
   * Initialize the plug-in.
   */
  public EmbDerbyRDBConnector() {
    this.setType("Database");
    this.setMetaDataSchemaType("tDatabaseMetaData");
    this.addSubtype("EmbeddedDerby");
    this.addLanguage("EmbeddedDerby", "SQL");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {
    if (EmbDerbyRDBConnector.logger.isDebugEnabled()) {
      EmbDerbyRDBConnector.logger.debug("boolean executeStatement("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    boolean success = false;
    Connection conn = openConnection(dataSource.getAddress());

    try {
      Statement stat = conn.createStatement();
      stat.execute(statement);
      conn.commit();
      success = true;
      stat.close();
    } catch (Throwable e) {
      EmbDerbyRDBConnector.logger.error("exception executing the statement: "
          + statement, e);
      EmbDerbyRDBConnector.logger.debug("Connection will be rolled back.");

      try {
        conn.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    closeConnection(conn);

    EmbDerbyRDBConnector.logger.info("Statement \"" + statement + "\" send to "
        + dataSource.getAddress() + ".");

    return success;
  }

  @Override
  public RDBResult retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    if (EmbDerbyRDBConnector.logger.isDebugEnabled()) {
      EmbDerbyRDBConnector.logger.debug("DataObject retrieveData("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    Connection connection = openConnection(dataSource.getAddress());

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
      };
      // save result sset
      rdbResult.setResultRowSet(resultSet);
      // save datasource
      rdbResult.setDataSource(dataSource);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    closeConnection(connection);

    EmbDerbyRDBConnector.logger.info("Statement \"" + statement
        + "\" executed on " + dataSource.getAddress() + ".");

    return rdbResult;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, List<String> statements,
      String target) throws ConnectionException {
    boolean success = false;
    Connection connection = openConnection(dataSource.getAddress());
    Statement connStatement = null;

    if (EmbDerbyRDBConnector.logger.isDebugEnabled()) {
      EmbDerbyRDBConnector.logger.debug("boolean writeData("
          + dataSource.getAddress() + ", DataObject) executed.");
    }

    if (statements != null && !statements.isEmpty()) {
      try {
        connStatement = connection.createStatement();

        for (String statement : statements) {
          if (target == null || target.equals("")) {
            if (statement.startsWith("UPDATE")) {
              connStatement.executeUpdate(statement);

              EmbDerbyRDBConnector.logger.info("Statement \"" + statement
                  + "\" " + "executed on " + dataSource.getAddress() + ".");
            }
          } else {
            if (statement.startsWith("INSERT")) {
              // replace dataObject's implizit schema.table name with target
              statement = statement.replaceAll("INSERT INTO .*?\\(",
                  "INSERT INTO " + target + " (");

              connStatement.executeUpdate(statement);

              EmbDerbyRDBConnector.logger.info("Statement \"" + statement
                  + "\" " + "executed on " + dataSource.getAddress() + ".");
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
        EmbDerbyRDBConnector.logger.debug("Connection will be rolled back.");

        try {
          connection.rollback();
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }

    closeConnection(connection);

    EmbDerbyRDBConnector.logger.info("Statements executed on "
        + dataSource.getAddress());
    
    return success;
  }

  @Override
  public boolean queryData(DataSource dataSource, String statement,
      String target) throws ConnectionException {
    boolean success = false;

    if (EmbDerbyRDBConnector.logger.isDebugEnabled()) {
      EmbDerbyRDBConnector.logger.debug("DataObject depositData("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    // create insert statement
    StringBuilder insertStatement = new StringBuilder();
    insertStatement.append("INSERT INTO");
    insertStatement.append(" ");
    insertStatement.append(target);
    insertStatement.append(" ");
    insertStatement.append(statement);

    Connection conn = openConnection(dataSource.getAddress());

    try {
      Statement connStatement = conn.createStatement();

      // insert data
      connStatement.execute(insertStatement.toString());

      connStatement.close();
      conn.commit();
      success = true;
    } catch (Throwable e) {
      EmbDerbyRDBConnector.logger.error("exception executing the statement: "
          + insertStatement.toString(), e);
      EmbDerbyRDBConnector.logger.debug("Connection will be rolled back.");

      try {
        conn.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    closeConnection(conn);

    EmbDerbyRDBConnector.logger.info("Statement \""
        + insertStatement.toString() + "\" " + "executed on "
        + dataSource.getAddress());

    return success;
  }

  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    Connection connection = openConnection(dataSource.getAddress());
    DataObject metaDataObject = this.getMetaDataSDO();
    DataObject schemaObject = null;
    DataObject tableObject = null;
    DataObject columnObject = null;

    try {
      DatabaseMetaData dbMetaData = connection.getMetaData();
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

      connection.commit();
      closeConnection(connection);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return metaDataObject;
  }

  /**
   * Opens a connection.
   * 
   * @param dsAddress
   * @return
   * @throws ConnectionException
   */
  private Connection openConnection(String dsAddress)
      throws ConnectionException {
    if (EmbDerbyRDBConnector.logger.isDebugEnabled()) {
      EmbDerbyRDBConnector.logger.debug("Connection openConnection("
          + dsAddress + ") executed.");
    }

    Connection connect = null;

    try {
      StringBuilder uri = new StringBuilder();
      uri.append("jdbc:derby:");
      uri.append(dsAddress);
      uri.append(";create=true");

      try {
        new JDCConnectionDriver("org.apache.derby.jdbc.EmbeddedDriver",
            uri.toString(), "none", "none").connect(uri.toString(), null);

        connect = DriverManager.getConnection("jdbc:jdc:jdcpool");
        connect.setAutoCommit(false);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        EmbDerbyRDBConnector.logger
            .fatal(
                "exception during establishing connection to: "
                    + uri.toString(), e);
      } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      EmbDerbyRDBConnector.logger.info("Connection opened on " + dsAddress
          + ".");

      return connect;
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      EmbDerbyRDBConnector.logger.fatal(
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

      if (EmbDerbyRDBConnector.logger.isDebugEnabled()) {
        EmbDerbyRDBConnector.logger
            .debug("boolean closeConnection() executed successfully.");
      }

      EmbDerbyRDBConnector.logger.info("Connection closed.");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      if (EmbDerbyRDBConnector.logger.isDebugEnabled()) {
        EmbDerbyRDBConnector.logger.error(
            "boolean closeConnection() executed with failures.", e);
      }
    }

    return success;
  }
}