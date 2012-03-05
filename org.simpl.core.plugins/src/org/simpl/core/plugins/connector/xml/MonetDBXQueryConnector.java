package org.simpl.core.plugins.connector.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.xml.XMLResult;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implements all methods of the {@link Connector} interface for
 * supporting MonetDB/XQuery, a xml database.<br>
 * <b>Description:</b>All methods are supported. For issueCommand expressions:
 * 'http://www.monetdb-xquery.org/XQuery/QuickTour/DOCMGT/index.html'<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class MonetDBXQueryConnector extends ConnectorPlugin<File, XMLResult> {

  static Logger logger = Logger.getLogger(MonetDBXQueryConnector.class);

  /**
   * Initialize the plug-in.
   */
  public MonetDBXQueryConnector() {
    this.setType("Database");
    this.setMetaDataSchemaType("tXMLDatabaseMetaData");
    this.addSubtype("MonetDBXQuery");
    this.addLanguage("MonetDBXQuery", "XQuery");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {
    if (MonetDBXQueryConnector.logger.isDebugEnabled()) {
      MonetDBXQueryConnector.logger.debug("boolean executeStatement("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    boolean success = false;
    Connection connection = openConnection(dataSource);

    try {
      Statement connStatement = connection.createStatement();
      connStatement.execute(statement);
      connStatement.close();
      success = true;
    } catch (Throwable e) {
      MonetDBXQueryConnector.logger.error("exception executing the statement: "
          + statement, e);
      MonetDBXQueryConnector.logger.debug("Connection will be rolled back.");
      success = false;
      try {
        connection.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    if (success) {
      MonetDBXQueryConnector.logger.info("Statement successfully executed on "
          + dataSource.getAddress());
    } else {
      MonetDBXQueryConnector.logger.info("Execution has caused an failure on "
          + dataSource.getAddress());
    }

    return success;
  }

  @Override
  public XMLResult retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    if (MonetDBXQueryConnector.logger.isDebugEnabled()) {
      MonetDBXQueryConnector.logger.debug("DataObject retrieveData("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    // used to hold the received data (communication is based on JDBC)
    StringBuffer stringBuffer = null;
    XMLResult xmlResult = null;

    stringBuffer = getData(dataSource, statement);

    if (stringBuffer != null) {
      // set data
      xmlResult = new XMLResult();
      xmlResult.setDataSource(dataSource);
      xmlResult.setDocument(stringBuffer);
    }

    MonetDBXQueryConnector.logger.info("Statement \"" + statement
        + "\" executed on " + dataSource.getAddress() + ".");

    return xmlResult;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, File file, String target)
      throws ConnectionException {
    if (MonetDBXQueryConnector.logger.isDebugEnabled()) {
      MonetDBXQueryConnector.logger.debug("boolean writeData("
          + dataSource.getAddress() + ", DataObject) executed.");
    }
    boolean success = storeData(dataSource, file, target);
    if (success) {
      MonetDBXQueryConnector.logger.info("Statement successfully executed on "
          + dataSource.getAddress());
    } else {
      MonetDBXQueryConnector.logger.info("Execution has caused an failure on "
          + dataSource.getAddress());
    }

    return success;
  }

  @Override
  public boolean queryData(DataSource dataSource, String statement,
      String target) throws ConnectionException {
    boolean success = false;
    // first step: retrieve specified data and create file
    // second step: store this file in the database

    if (MonetDBXQueryConnector.logger.isDebugEnabled()) {
      MonetDBXQueryConnector.logger.debug("boolean writeData("
          + dataSource.getAddress() + ", DataObject) executed.");
    }

    Connection connection = openConnection(dataSource);
    Statement connStatement = null;

    // used to hold the received data (communication is based on JDBC)
    StringBuffer stringBuffer = null;
    ResultSet resultSet = null;
    ResultSetMetaData resultSetMetaData = null;

    // used to write a file
    Writer fileWriter = null;
    File file = null;

    // extract data
    try {
      connStatement = connection.createStatement();
      resultSet = connStatement.executeQuery(statement);
      resultSetMetaData = resultSet.getMetaData();
      stringBuffer = new StringBuffer();
      while (resultSet.next()) {
        for (int j = 1; j <= resultSetMetaData.getColumnCount(); j++) {
          stringBuffer.append(resultSet.getString(j));
        }
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    closeConnection(connection);

    if (stringBuffer != null) {
      // create file
      try {
        file = new File(String.valueOf((int) (Math.random() * 10000)) + ".xml");
        fileWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        fileWriter.write(stringBuffer.toString());
      } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
        if (fileWriter != null) {
          try {
            fileWriter.close();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          success = storeData(dataSource, file, target);
        }
      }
    }

    if (success) {
      MonetDBXQueryConnector.logger.info("Statement successfully executed on "
          + dataSource.getAddress());
    } else {
      MonetDBXQueryConnector.logger.info("Execution has caused an failure on "
          + dataSource.getAddress());
    }

    return success;
  }

  @SuppressWarnings("unchecked")
  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    DataObject metaDataObject = this.getMetaDataSDO();
    DataObject collectionObject = null;
    DataObject documentObject = null;

    SAXBuilder saxBuilder = null;
    Document collections = null;
    List<Element> elements_col = null;
    try {
      saxBuilder = new SAXBuilder();
      try {
        // get collections
        collections = saxBuilder.build(new StringReader("<collections>"
            + getData(dataSource, "pf:collections()") + "</collections>"));
      } catch (JDOMException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      elements_col = collections.getRootElement().getChildren();
      for (int i = 0; i < elements_col.size(); i++) {
        // add this collection
        collectionObject = metaDataObject.createDataObject("collection");
        String collectionName = elements_col.get(i).getValue();
        collectionObject.setString("name", collectionName);
        Document documents = null;
        List<Element> elements_doc = null;
        try {
          // get documents in this collection
          documents = saxBuilder.build(new StringReader("<documents>"
              + getData(dataSource, "pf:documents(\"" + collectionName + "\")")
              + "</documents>"));
        } catch (JDOMException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        elements_doc = documents.getRootElement().getChildren();
        for (int j = 0; j < elements_doc.size(); j++) {
          // add this document
          documentObject = collectionObject.createDataObject("document");
          documentObject.setString("name", elements_doc.get(j).getValue());
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return metaDataObject;
  }

  /**
   * This method is used to retrieve data from a MonetDB/XQuery database.
   * 
   * @param dataSource
   * @param statement
   * @return
   * @throws ConnectionException
   */
  private StringBuffer getData(DataSource dataSource, String statement)
      throws ConnectionException {
    Connection connection = openConnection(dataSource);

    Statement connStatement = null;

    ResultSet resultSet = null;
    ResultSetMetaData resultSetMetaData = null;
    // used to hold the received data (communication is based on JDBC)
    StringBuffer stringBuffer = null;

    try {
      connStatement = connection.createStatement();
      resultSet = connStatement.executeQuery(statement);
      resultSetMetaData = resultSet.getMetaData();

      // create object
      stringBuffer = new StringBuffer();
      while (resultSet.next()) {
        for (int j = 1; j <= resultSetMetaData.getColumnCount(); j++) {
          stringBuffer.append(resultSet.getString(j));
        }
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    closeConnection(connection);

    return stringBuffer;
  }

  /**
   * This method is ised to put data into a MonetDB/XQuery database.
   * 
   * @param dataSource
   * @param file
   * @param target
   * @return
   * @throws ConnectionException
   */
  private boolean storeData(DataSource dataSource, File file, String target)
      throws ConnectionException {
    boolean success = false;

    // collection + document name
    // example /collection1/collection2/document1
    String documentName = null;
    String collectionName = null;
    if (target.contains("/")) {
      documentName = target.substring(target.lastIndexOf("/") + 1,
          target.length());
      collectionName = target.substring(0, target.lastIndexOf("/"));
    } else {
      documentName = target;
      collectionName = "";
    }

    Connection connection = openConnection(dataSource);
    Statement connStatement = null;

    // extract path
    String path = file.getAbsolutePath();
    path = path.replace('\\', '/');

    String createStatement = "pf:add-doc(\"" + path + "\",\"" + documentName
        + "\",\"" + collectionName + "\")";

    try {
      connStatement = connection.createStatement();

      // try to delete document
      try {
        Statement delStatement = connection.createStatement();
        String deleteStatement = "pf:del-doc('" + documentName + "')";
        delStatement.execute(deleteStatement);
      } catch (Exception e) {
        e.printStackTrace();
      }

      connStatement.executeUpdate(createStatement);
      connStatement.close();
      success = true;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      success = false;
      try {
        connection.rollback();
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    closeConnection(connection);
    if (file.exists()) {
      file.delete();
    }

    return success;
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
   * @param dsAddress
   * @param user
   * @param password
   * @return
   * @throws ConnectionException
   */
  private Connection openConnection(DataSource dataSource)
      throws ConnectionException {
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
      // XQuery support is needed
      uri.append("?language=xquery");

      try {
        connect = DriverManager.getConnection(uri.toString(), user, password);
        connect.setAutoCommit(true);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        MonetDBXQueryConnector.logger
            .fatal(
                "exception during establishing connection to: "
                    + uri.toString(), e);
      }

      MonetDBXQueryConnector.logger.info("Connection opened on " + dsAddress
          + ".");
      return connect;
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      MonetDBXQueryConnector.logger.fatal(
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
      if (MonetDBXQueryConnector.logger.isDebugEnabled()) {
        MonetDBXQueryConnector.logger
            .debug("boolean closeConnection() executed successfully.");
      }
      MonetDBXQueryConnector.logger.info("Connection closed.");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      if (MonetDBXQueryConnector.logger.isDebugEnabled()) {
        MonetDBXQueryConnector.logger.error(
            "boolean closeConnection() executed with failures.", e);
      }
    }

    return success;
  }
}