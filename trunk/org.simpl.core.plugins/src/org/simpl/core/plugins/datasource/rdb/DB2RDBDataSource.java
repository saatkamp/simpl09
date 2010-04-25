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
import org.simpl.core.plugins.datasource.DataSourcePlugin;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.auth.Authentication;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <p>
 * Implements all methods of the {@link IDatasourceService} interface for
 * supporting the IBM DB2 relational database.
 * </p>
 * 
 * dsAddress = //server:port/database or //server/database, for example
 * //localhost:50000/testdb.
 * 
 * @author hahnml
 * @version $Id: DB2RDBDataSource.java 1014 2010-03-29 09:16:08Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DB2RDBDataSource extends DataSourcePlugin {
  static Logger logger = Logger.getLogger(DB2RDBDataSource.class);

  public DB2RDBDataSource() {
    this.setType("Database");
    this.setMetaDataSchemaType("tDatabaseMetaData");
    this.addSubtype("DB2");
    this.addLanguage("DB2", "SQL");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  private Connection openConnection(String dsAddress, String user,
      String password) throws ConnectionException {
    // TODO Umändern in DataSource Connection
    // Testweise wird hier nur eine embedded Derby Datenbank verwendet
    if (logger.isDebugEnabled()) {
      logger.debug("Connection openConnection(" + dsAddress + ") executed.");
    }

    java.sql.Connection connect = null;

    try {
      Class.forName("com.ibm.db2.jcc.DB2Driver");
      StringBuilder uri = new StringBuilder();
      uri.append("jdbc:db2://");
      uri.append(dsAddress);

      try {
        connect = (java.sql.Connection) DriverManager.getConnection(uri
            .toString(), user, password);
        connect.setAutoCommit(false);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        logger.fatal("exception during establishing connection to: "
            + uri.toString(), e);
      }

      logger.info("Connection opened on " + dsAddress + ".");
      return connect;
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      logger.fatal("exception during loading the JDBC driver", e);
    }

    return connect;
  }

  private boolean closeConnection(Connection connection)
      throws ConnectionException {
    // TODO Auto-generated method stub
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

  @Override
  public DataObject retrieveData(DataSource dataSource, Authentication auth,
      String statement) throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("DataObject retrieveData(" + dataSource.getAddress() + ", "
          + statement + ") executed.");
    }

    DAS das = DAS.FACTORY.createDAS(openConnection(dataSource.getAddress(), "",
        ""));
    Command read = das.createCommand(statement);
    DataObject root = read.executeQuery();

    logger.info("Statement '" + statement + "' executed on "
        + dataSource.getAddress() + ".");

    return root;
  }

  @Override
  public boolean executeStatement(DataSource dataSource, Authentication auth,
      String statement) throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("boolean executeStatement(" + dataSource.getAddress() + ", "
          + statement + ") executed.");
    }

    boolean success = false;
    Connection conn = openConnection(dataSource.getAddress(), auth.getUser(),
        auth.getPassword());

    try {
      Statement stat = conn.createStatement();
      stat.execute(statement);
      conn.commit();
      success = true;
      stat.close();
    } catch (Throwable e) {
      logger.error("exception executing the statement: " + statement, e);
    }

    logger.info("Statement '" + statement + "' send to "
        + dataSource.getAddress() + ".");
    closeConnection(conn);
    return success;
  }

  @Override
  public boolean writeBack(DataSource dataSource, Authentication auth,
      DataObject data) throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("boolean writeBack(" + dataSource.getAddress()
          + ", DataObject) executed.");
    }

    // TODO Hier muss noch der Fall mit einem DataObject abgedeckt werden.
    boolean success = false;
    /*
     * Connection conn = openConnection(dsAddress); try { Statement stat =
     * conn.createStatement(); stat.execute(statement); conn.commit();
     * stat.close(); success = true; } catch (Throwable e) {
     * logger.error("exception executing the statement: " + statement, e); }
     * logger.info("Statement '" + statement + "' send to " + dsAddress + ".");
     * closeConnection(conn);
     */
    return success;
  }

  @Override
  public boolean depositData(DataSource dataSource, Authentication auth,
      String statement, String target) throws ConnectionException {
    boolean success = false;

    if (logger.isDebugEnabled()) {
      logger.debug("DataObject depositData(" + dataSource.getAddress() + ", "
          + statement + ") executed.");
    }

    // Beispiel: CREATE TABLE TAB AS (SELECT * FROM T1 WITH NO DATA);
    // Dies erzeugt aus den Query-Daten eine Neue Tabelle TAB mit den
    // gequerieten Daten.
    StringBuilder createTableStatement = new StringBuilder();
    createTableStatement.append("CREATE TABLE");
    createTableStatement.append(" ");
    createTableStatement.append(target);
    createTableStatement.append(" AS (");
    createTableStatement.append(statement);
    createTableStatement.append(" ");
    createTableStatement.append(") WITH NO DATA");

    StringBuilder insertStatement = new StringBuilder();
    insertStatement.append("INSERT INTO");
    insertStatement.append(" ");
    insertStatement.append(target);
    insertStatement.append(" ");
    insertStatement.append(statement);

    Connection conn = openConnection(dataSource.getAddress(), auth.getUser(),
        auth.getPassword());

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

    logger.info("Statement '" + createTableStatement.toString() + "' " + "& '"
        + insertStatement.toString() + "'" + "executed on "
        + dataSource.getAddress());

    return success;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.simpl.core.datasource.DatasourceService#getMetaData(java.lang.String)
   */
  @Override
  public DataObject getMetaData(DataSource dataSource, Authentication auth,
      String filter) throws ConnectionException {
    Connection conn = openConnection(dataSource.getAddress(), auth.getUser(),
        auth.getPassword());

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
}