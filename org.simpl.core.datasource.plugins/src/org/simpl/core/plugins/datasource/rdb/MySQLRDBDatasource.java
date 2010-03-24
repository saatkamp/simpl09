package org.simpl.core.plugins.datasource.rdb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.core.plugins.DataSourcePlugin;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <p>
 * Implements all methods of the {@link IDatasourceService} interface for supporting the
 * MySQL relational database.
 * </p>
 * 
 * dsAddress = //MyDbComputerNameOrIP:3306/myDatabaseName, for example
 * //localhost:3306/simplDB.
 * 
 * @author hahnml
 */
public class MySQLRDBDatasource extends DataSourcePlugin {
  static Logger logger = Logger.getLogger(MySQLRDBDatasource.class);

  public MySQLRDBDatasource() {
    this.setDatasourceType("Database");
    this.setDatasourceMetaDataType("tDatabaseMetaData");
    this.addDatasourceSubtype("MySQL");
    this.addDatasourceLanguage("MySQL", "SQL");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public Connection openConnection(String dsAddress) throws ConnectionException {
    // TODO Umändern in DataSource Connection
    // Testweise wird hier nur eine embedded Derby Datenbank verwendet
    if (logger.isDebugEnabled()) {
      logger.debug("Connection openConnection(" + dsAddress + ") executed.");
    }
    Connection connect = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      StringBuilder uri = new StringBuilder();
      uri.append("jdbc:mysql:");
      uri.append(dsAddress);
      try {
        // TODO Hier müssen noch die im SIMPL Core hinterlegten
        // Authentification-Informationen geladen werden
        connect = DriverManager.getConnection(uri.toString(), "test", "test");
        connect.setAutoCommit(false);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        logger.fatal("exception during establishing connection to: " + uri.toString(), e);
      }

      logger.info("Connection opened on " + dsAddress + ".");
      return connect;
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      logger.fatal("exception during loading the JDBC driver", e);
    }
    return connect;
  }

  @Override
  public boolean closeConnection(Connection connection) throws ConnectionException {
    // TODO Auto-generated method stub
    boolean success = false;

    try {
      connection.close();
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

  @Override
  public DataObject queryData(String dsAddress, String statement)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger
          .debug("DataObject queryData(" + dsAddress + ", " + statement + ") executed.");
    }
    DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
    Command read = das.createCommand(statement);
    DataObject root = read.executeQuery();

    logger.info("Statement '" + statement + "' executed on " + dsAddress + ".");
    return root;
  }

  @Override
  public boolean defineData(String dsAddress, String statement)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("boolean defineData(" + dsAddress + ", " + statement + ") executed.");
    }
    boolean success = false;
    Connection conn = openConnection(dsAddress);
    try {
      Statement stat = conn.createStatement();
      stat.execute(statement);
      conn.commit();
      success = true;
      stat.close();
    } catch (Throwable e) {
      logger.error("exception executing the statement: " + statement, e);
    }

    logger.info("Statement '" + statement + "' send to " + dsAddress + ".");
    closeConnection(conn);
    return success;
  }

  @Override
  public boolean manipulateData(String dsAddress, String statement, DataObject data)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("boolean manipulateData(" + dsAddress + ", " + statement
          + ") executed.");
    }

    // TODO Hier muss noch der Fall mit einem DataObject abgedeckt werden.
    boolean success = false;
    Connection conn = openConnection(dsAddress);
    try {
      Statement stat = conn.createStatement();
      stat.execute(statement);
      conn.commit();
      stat.close();
      success = true;
    } catch (Throwable e) {
      logger.error("exception executing the statement: " + statement, e);
    }

    logger.info("Statement '" + statement + "' send to " + dsAddress + ".");
    closeConnection(conn);
    return success;
  }

  // Test
  public boolean manipulateDataWithSDO(String dsAddress, DataObject data)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("boolean manipulateDataWithSDO(" + dsAddress + ", " + data
          + ") executed.");
    }
    DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
    das.applyChanges(data);
    logger.info("DataObject " + data + "was send back to datasource " + dsAddress);
    return false;
  }

  @Override
  public boolean depositData(String dsAddress, String statement, String target)
      throws ConnectionException {
    boolean success = false;

    if (logger.isDebugEnabled()) {
      logger.debug("DataObject depositData(" + dsAddress + ", " + statement
          + ") executed.");
    }

    // Beispiel: CREATE TABLE TAB SELECT n FROM foo;
    // Dies erzeugt aus den Query-Daten eine Neue Tabelle TAB mit den gequerieten Daten.
    StringBuilder createTableStatement = new StringBuilder();
    createTableStatement.append("CREATE TABLE");
    createTableStatement.append(" ");
    createTableStatement.append(target);
    createTableStatement.append(" ");
    createTableStatement.append(statement);

    Connection conn = openConnection(dsAddress);
    try {
      Statement createState = conn.createStatement();

      // Neue Tabelle aus dem Query erzeugen
      createState.execute(createTableStatement.toString());

      conn.commit();
      createState.close();
      closeConnection(conn);

      success = true;
    } catch (Throwable e) {
      logger.error("exception executing the statement: "
          + createTableStatement.toString(), e);
    }

    logger.info("Statement '" + createTableStatement.toString() + "' " + "executed on "
        + dsAddress);

    return success;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#getMetaData(java.lang.String)
   */
  @Override
  public DataObject getMetaData(String dsAddress) throws ConnectionException {
    Connection conn = openConnection(dsAddress);
    DataObject metaDataObject = this.createDatasourceMetaDataObject().getRootObject();
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
}