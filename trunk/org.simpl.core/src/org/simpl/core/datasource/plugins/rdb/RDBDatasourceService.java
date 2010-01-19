package org.simpl.core.datasource.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.core.datasource.DatasourceServicePlugin;
import org.simpl.core.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <p>
 * Implements all methods of the {@link IDatasourceService} interface for relational
 * databases.
 * </p>
 * 
 * @author hahnml
 */
public class RDBDatasourceService extends DatasourceServicePlugin {
  static Logger logger = Logger.getLogger(RDBDatasourceService.class);

  public RDBDatasourceService() {
    this.datasourceType = "database";
    this.datasourceSubtypes.add("DB2");
    this.datasourceSubtypes.add("MySQL");
    this.datasourceSubtypes.add("Derby");
    this.datasourceLanguages.put("DB2", Arrays.asList(new String[] {"SQL"}));
    this.datasourceLanguages.put("MySQL", Arrays.asList(new String[] {"SQL"}));
    this.datasourceLanguages.put("Derby", Arrays.asList(new String[] {"SQL"}));
    
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
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      StringBuilder uri = new StringBuilder();
      uri.append("jdbc:derby:");
      uri.append(dsAddress);
      uri.append(";create=true");
      try {
        connect = DriverManager.getConnection(uri.toString());
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
}
