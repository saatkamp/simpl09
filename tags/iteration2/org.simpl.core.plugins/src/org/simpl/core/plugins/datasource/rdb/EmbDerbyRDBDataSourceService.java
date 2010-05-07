package org.simpl.core.plugins.datasource.rdb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.plugins.dataformat.rdb.RDBResult;
import org.simpl.core.plugins.datasource.DataSourceServicePlugin;
import org.simpl.core.services.connection.JDCConnectionDriver;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <p>
 * Implements all methods of the {@link IDatasourceService} interface for supporting the
 * Apache Derby relational database in embedded mode.
 * </p>
 * 
 * dsAddress = Full path to embedded Derby database, for example: C:\databases\myDB.
 * 
 * @author hahnml<br>
 * @version $Id: EmbDerbyRDBDataSource.java 1087 2010-04-13 17:12:27Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class EmbDerbyRDBDataSourceService extends DataSourceServicePlugin<List<String>, RDBResult> {
  static Logger logger = Logger.getLogger(EmbDerbyRDBDataSourceService.class);

  /**
   * Initialize the plug-in.
   */
  public EmbDerbyRDBDataSourceService() {
    this.setType("Database");
    this.setMetaDataSchemaType("tDatabaseMetaData");
    this.addSubtype("EmbeddedDerby");
    this.addLanguage("EmbeddedDerby", "SQL");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean executeStatement(DataSource dataSource, String statement)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("boolean executeStatement(" + dataSource.getAddress() + ", "
          + statement + ") executed.");
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
      logger.error("exception executing the statement: " + statement, e);
    }

    logger.info("Statement \"" + statement + "\" send to " + dataSource.getAddress() + ".");
    closeConnection(conn);

    return success;
  }

  @Override
  public RDBResult retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("DataObject retrieveData(" + dataSource.getAddress() + ", "
          + statement + ") executed.");
    }

    Connection connection = openConnection(dataSource.getAddress());

    Statement connStatement = null;
    ResultSet resultSet = null;
    RDBResult rdbResult = new RDBResult();

    try {     
      connStatement = connection.createStatement();
      resultSet = connStatement.executeQuery(statement);

      rdbResult.setDbMetaData(connection.getMetaData());
      rdbResult.setResultSet(resultSet);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    logger.info("Statement \"" + statement + "\" executed on " + dataSource.getAddress()
        + ".");

    return rdbResult;
  }

  @Override
  public boolean writeBack(DataSource dataSource, List<String> statements)
      throws ConnectionException {
    boolean success = true;
    Connection connection = openConnection(dataSource.getAddress());
    Statement connStatement;

    if (logger.isDebugEnabled()) {
      logger.debug("boolean writeBack(" + dataSource.getAddress()
          + ", DataObject) executed.");
    }

    try {
      connStatement = connection.createStatement();

      for (String statement : statements) {
        if (statement.startsWith("UPDATE")) {
          if (connStatement.executeUpdate(statement) != 1) {
            success = success && false;
          }

          logger.info("Statement \"" + statement + "\" " + "executed on "
              + dataSource.getAddress() + (success ? " was successful" : " failed"));
        }
      }

      connStatement.close();
      connection.commit();
      closeConnection(connection);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#writeData(org.simpl.core.services
   * .datasource.DataSource, commonj.sdo.DataObject, java.lang.String)
   */
  @Override
  public boolean writeData(DataSource dataSource, List<String> statements, String target)
      throws ConnectionException {
    boolean success = true;
    Connection connection = openConnection(dataSource.getAddress());
    Statement connStatement = null;

    if (logger.isDebugEnabled()) {
      logger.debug("boolean writeData(" + dataSource.getAddress()
          + ", DataObject) executed.");
    }

    if (statements != null && !statements.isEmpty()) {
      try {
        connStatement = connection.createStatement();

        for (String statement : statements) {
          if (statement.startsWith("INSERT")) {
            // replace dataObject's implizit schema.table name with target
            if (target != null) {
              statement = statement.replaceAll("INSERT INTO .*?\\(", "INSERT INTO "
                  + target + " (");
            }

            if (connStatement.executeUpdate(statement) != 1) {
              success = success && false;
            }

            logger.info("Statement \"" + statement + "\" " + "executed on "
                + dataSource.getAddress() + (success ? " was successful" : " failed"));
          }
        }

        connStatement.close();
        connection.commit();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    closeConnection(connection);

    return success;
  }

  @Override
  public boolean depositData(DataSource dataSource, String statement, String target)
      throws ConnectionException {
    boolean success = false;

    if (logger.isDebugEnabled()) {
      logger.debug("DataObject depositData(" + dataSource.getAddress() + ", " + statement
          + ") executed.");
    }

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

    Connection conn = openConnection(dataSource.getAddress());

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
      logger.error("exception executing the statement: "
          + createTableStatement.toString(), e);
    }

    logger.info("Statement \"" + createTableStatement.toString() + "\" " + "& \""
        + insertStatement.toString() + "\" " + "executed on " + dataSource.getAddress());

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
  private Connection openConnection(String dsAddress) throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("Connection openConnection(" + dsAddress + ") executed.");
    }

    Connection connect = null;

    try {
      StringBuilder uri = new StringBuilder();
      uri.append("jdbc:derby:");
      uri.append(dsAddress);
      uri.append(";create=true");

      try {
        new JDCConnectionDriver("org.apache.derby.jdbc.EmbeddedDriver", uri.toString(),
            "none", "none").connect(uri.toString(), null);

        connect = DriverManager.getConnection("jdbc:jdc:jdcpool");
        connect.setAutoCommit(false);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        logger.fatal("exception during establishing connection to: " + uri.toString(), e);
      } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      logger.info("Connection opened on " + dsAddress + ".");

      return connect;
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      logger.fatal("exception during loading the JDBC driver", e);
    }
    return connect;
  }

  /**
   * Closes a connection.
   * @param connection
   * @return
   */
  private boolean closeConnection(Connection connection) {
    boolean success = false;

    try {
      ((java.sql.Connection) connection).close();
      success = true;

      if (logger.isDebugEnabled()) {
        logger.debug("boolean closeConnection() executed successfully.");
      }

      logger.info("Connection closed.");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      if (logger.isDebugEnabled()) {
        logger.error("boolean closeConnection() executed with failures.", e);
      }
    }

    return success;
  }
}