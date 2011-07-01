package web.service.data.db;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class PostgreSQLRDBDataSourceService {
  static Logger logger = Logger.getLogger(PostgreSQLRDBDataSourceService.class);

  /**
   * Initialize the plug-in.
   */
  public PostgreSQLRDBDataSourceService() {
    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  public boolean executeStatement(DataSource dataSource, String statement){
    if (PostgreSQLRDBDataSourceService.logger.isDebugEnabled()) {
      PostgreSQLRDBDataSourceService.logger.debug("boolean executeStatement("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    boolean success = false;
    Connection conn = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());

    // replace file location with file data
    try {
      Pattern fileLocationPattern = Pattern.compile("'(.:(?:\\\\.*))'");
      Matcher matcher = fileLocationPattern.matcher(statement);
      
      if (matcher.find()) {
        String filePath = matcher.group(1);
        File file = new File(filePath);
        
        if (file.exists()) {
          statement = statement.replace("'" + filePath + "'", "?");
          FileInputStream fis = new FileInputStream(file);
          PreparedStatement ps = conn.prepareStatement(statement);
          ps.setBinaryStream(1, fis, (int)file.length());
          ps.executeUpdate();
          ps.close();
          fis.close();
          conn.commit();
          success = true;
        }
      } else {
        Statement stat = conn.createStatement();
        stat.execute(statement);
        stat.close();
        conn.commit();
        success = true;
      }
    } catch (Throwable e) {
      PostgreSQLRDBDataSourceService.logger.error("exception executing the statement: "
          + statement, e);
      PostgreSQLRDBDataSourceService.logger.debug("Connection will be rolled back.");

      try {
        conn.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    PostgreSQLRDBDataSourceService.logger.info("Statement \"" + statement + "\" send to "
        + dataSource.getAddress() + ".");
    closeConnection(conn);

    return success;
  }

  public RDBResult retrieveData(DataSource dataSource, String statement){
    if (PostgreSQLRDBDataSourceService.logger.isDebugEnabled()) {
      PostgreSQLRDBDataSourceService.logger.debug("DataObject retrieveData("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    Connection connection = openConnection(dataSource.getAddress(), dataSource
        .getAuthentication().getUser(), dataSource.getAuthentication().getPassword());

    Statement connStatement = null;
    ResultSet resultSet = null;
    RDBResult rdbResult = null;

    try {
      connection.setAutoCommit(false);
      
      connStatement = connection.createStatement();
      connStatement.setFetchSize(1000);
      
      resultSet = connStatement.executeQuery(statement);
      connection.commit();
      
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

    PostgreSQLRDBDataSourceService.logger.info("Statement \"" + statement
        + "\" executed on " + dataSource.getAddress() + ".");

    return rdbResult;
  }

  /**
   * Opens a connection.
   * 
   * @param dsAddress
   * @param user
   * @param password
   * @return
   */
  private Connection openConnection(String dsAddress, String user, String password) {
    if (PostgreSQLRDBDataSourceService.logger.isDebugEnabled()) {
      PostgreSQLRDBDataSourceService.logger.debug("Connection openConnection("
          + dsAddress + ") executed.");
    }

    Connection connect = null;

    try {
      Class.forName("org.postgresql.Driver");
      StringBuilder uri = new StringBuilder();
      uri.append("jdbc:postgresql://");
      uri.append(dsAddress);

      try {
        connect = DriverManager.getConnection(uri.toString(), user, password);
        connect.setAutoCommit(false);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        PostgreSQLRDBDataSourceService.logger.fatal(
            "exception during establishing connection to: " + uri.toString(), e);
      }

      PostgreSQLRDBDataSourceService.logger.info("Connection opened on " + dsAddress
          + ".");
      return connect;
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      PostgreSQLRDBDataSourceService.logger.fatal(
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
      if (PostgreSQLRDBDataSourceService.logger.isDebugEnabled()) {
        PostgreSQLRDBDataSourceService.logger
            .debug("boolean closeConnection() executed successfully.");
      }
      PostgreSQLRDBDataSourceService.logger.info("Connection closed.");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      if (PostgreSQLRDBDataSourceService.logger.isDebugEnabled()) {
        PostgreSQLRDBDataSourceService.logger.error(
            "boolean closeConnection() executed with failures.", e);
      }
    }

    return success;
  }
}