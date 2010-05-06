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
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.core.plugins.datasource.DataSourceServicePlugin;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <p>
 * Implements all methods of the {@link IDatasourceService} interface for supporting the
 * Apache Derby relational database in Client-Server mode.
 * </p>
 * 
 * dsAddress = Full path to embedded Derby database, for example: C:\databases\myDB.
 * 
 * @author hahnml
 */
public class DerbyRDBDataSourceService extends DataSourceServicePlugin {
  static Logger logger = Logger.getLogger(DerbyRDBDataSourceService.class);

  /**
   * Initialize the plug-in.
   */
  public DerbyRDBDataSourceService() {
    this.setType("Database");
    this.setMetaDataSchemaType("tDatabaseMetaData");
    this.addSubtype("Derby");
    this.addLanguage("Derby", "SQL");
    this.setDataFormat("RDB");

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
    Connection conn = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());

    try {
      Statement stat = conn.createStatement();
      stat.execute(statement);
      conn.commit();
      success = true;
      stat.close();
    } catch (Throwable e) {
      logger.error("exception executing the statement: " + statement, e);
    }

    logger.info("Statement '" + statement + "' send to " + dataSource.getAddress() + ".");
    closeConnection(conn);

    return success;
  }

  @Override
  public DataObject retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("DataObject retrieveData(" + dataSource.getAddress() + ", "
          + statement + ") executed.");
    }

    DAS das = DAS.FACTORY.createDAS(openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword()));
    Command read = das.createCommand(statement);
    DataObject root = read.executeQuery();

    logger.info("Statement '" + statement + "' executed on " + dataSource.getAddress()
        + ".");

    // convert SDO to the tuscany data format
    if (root != null) {
      root = this.getDataFormat().toSDO(root);
    }

    return root;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean writeBack(DataSource dataSource, DataObject data)
      throws ConnectionException {
    boolean success = true;
    List<String> statements = null;
    Connection connection = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());
    Statement connStatement;

    if (logger.isDebugEnabled()) {
      logger.debug("boolean writeBack(" + dataSource.getAddress()
          + ", DataObject) executed.");
    }

    // to data format
    statements = (List<String>) this.getDataFormat().fromSDO(data);

    try {
      connStatement = connection.createStatement();

      for (String statement : statements) {
        if (statement.startsWith("UPDATE")) {
          if (connStatement.executeUpdate(statement) != 1) {
            success = success && false;
          }

          logger.info("Statement '" + statement + "' " + "executed on "
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
  @SuppressWarnings("unchecked")
  @Override
  public boolean writeData(DataSource dataSource, DataObject data, String target)
      throws ConnectionException {
    boolean success = true;
    List<String> statements = null;
    Connection connection = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());
    Statement connStatement = null;

    // from data format
    statements = (List<String>) this.getDataFormat().fromSDO(data);

    if (logger.isDebugEnabled()) {
      logger.debug("boolean writeData(" + dataSource.getAddress()
          + ", DataObject) executed.");
    }

    statements = (List<String>) this.getDataFormat().fromSDO(data);
    if (statements != null && !statements.isEmpty()) {
      if (target != null) {
        // create target        
        this.createTarget(connection, target, data);
      }
      
      try {
        connStatement = connection.createStatement();

        for (String statement : statements) {
          if (statement.startsWith("INSERT")) {
            if (connStatement.executeUpdate(statement) != 1) {
              success = success && false;
            }

            logger.info("Statement '" + statement + "' " + "executed on "
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
      logger.error("exception executing the statement: "
          + createTableStatement.toString(), e);
    }

    logger.info("Statement '" + createTableStatement.toString() + "' " + "& '"
        + insertStatement.toString() + "'" + "executed on " + dataSource.getAddress());

    return success;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#getMetaData(java.lang.String)
   */
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
  
  private Connection openConnection(String dsAddress, String user, String password)
      throws ConnectionException {
    // TODO Umändern in DataSource Connection
    // Testweise wird hier nur eine embedded Derby Datenbank verwendet
    if (logger.isDebugEnabled()) {
      logger.debug("Connection openConnection(" + dsAddress + ") executed.");
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

  private boolean closeConnection(Connection connection) throws ConnectionException {
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

  /**
   * Creates a target table for data to write.
   * 
   * @param connection
   * @param target
   * @param data
   * @return
   */
  @SuppressWarnings("unchecked")
  private boolean createTarget(Connection connection, String target, DataObject data) {
    boolean created = false;

    List<DataObject> tables = data.getList("table");
    List<DataObject> columns = null;
    List<String> primaryKeys = null;
    String createStatement = "";

    // build a create statement
    for (DataObject table : tables) {
      columns = (List<DataObject>) table.getList("column");
      primaryKeys = (List<String>) table.getList("primaryKey");

      createStatement = "CREATE TABLE " + target + " (";

      // create table with columns
      for (DataObject column : columns) {
        createStatement += column.getString("name") + " " + column.getString("type")
            + ",";
      }

      // add primary keys
      createStatement += " PRIMARY KEY (";

      for (int i = 0; i < primaryKeys.size(); i++) {
        createStatement += primaryKeys.get(i);

        if (i < primaryKeys.size() - 1) {
          createStatement += ",";
        }
      }

      createStatement += "))";
    }

    // execute the create statement
    try {
      Statement statement = connection.createStatement();
      created = statement.execute(createStatement);
      statement.close();
      connection.commit();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    if (logger.isDebugEnabled()) {
      logger.debug("createTarget executed: " + createStatement);
    }

    return created;
  }
}