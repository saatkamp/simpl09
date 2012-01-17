package org.simpl.core.plugins.connector.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.xml.XMLResult;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataSource;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import xtc.driver.Connection;
import xtc.driver.PutInfoObject;
import xtc.driver.XTCconnection;
import xtc.driver.XTCdriver;
import xtc.driver.XTCexception;
import xtc.driver.XTCxqueryResult;
import xtc.driver.PutInfoObject.DOCUMENT_SOURCE;
import xtc.driver.XTCxqueryResult.ResultType;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implements all methods of the {@link Connector} interface for
 * supporting XML Transaction Coordinator (XTC), a native xml database.<br>
 * <b>Description:</b>The method: 'issueCommand' is not supported.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class XMLTransactionCoordinatorConnector extends
    ConnectorPlugin<File, XMLResult> {

  static Logger logger = Logger
      .getLogger(XMLTransactionCoordinatorConnector.class);

  /**
   * Initialize the plug-in.
   */
  public XMLTransactionCoordinatorConnector() {
    this.setType("Database");
    this.setMetaDataSchemaType("tXMLDatabaseMetaData");
    this.addSubtype("XTC");
    this.addLanguage("XTC", "XQuery");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {
    // not supported
    return false;
  }

  @Override
  public XMLResult retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    if (XMLTransactionCoordinatorConnector.logger.isDebugEnabled()) {
      XMLTransactionCoordinatorConnector.logger
          .debug("DataObject retrieveData(" + dataSource.getAddress() + ", "
              + statement + ") executed.");
    }

    StringBuffer stringBuffer = null;
    XMLResult xmlResult = null;

    stringBuffer = getData(dataSource, statement);

    if (stringBuffer != null) {
      xmlResult = new XMLResult();
      xmlResult.setDataSource(dataSource);
      xmlResult.setDocument(stringBuffer);
    }

    XMLTransactionCoordinatorConnector.logger.info("Statement \"" + statement
        + "\" executed on " + dataSource.getAddress() + ".");

    return xmlResult;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, File file, String target)
      throws ConnectionException {
    if (XMLTransactionCoordinatorConnector.logger.isDebugEnabled()) {
      XMLTransactionCoordinatorConnector.logger.debug("boolean writeData("
          + dataSource.getAddress() + ", DataObject) executed.");
    }
    boolean success = storeData(dataSource, file, target);
    if (success) {
      XMLTransactionCoordinatorConnector.logger
          .info("Statement successfully executed on " + dataSource.getAddress());
    } else {
      XMLTransactionCoordinatorConnector.logger
          .info("Execution has caused an failure on " + dataSource.getAddress());
    }

    return success;
  }

  @Override
  public boolean queryData(DataSource dataSource, String statement,
      String target) throws ConnectionException {
    boolean success = false;
    // first step: retrieve specified data and create file
    // second step: store this file in the database

    if (XMLTransactionCoordinatorConnector.logger.isDebugEnabled()) {
      XMLTransactionCoordinatorConnector.logger.debug("boolean writeData("
          + dataSource.getAddress() + ", DataObject) executed.");
    }

    StringBuffer stringBuffer = null;
    Writer fileWriter = null;
    File file = null;

    // retrieve data
    stringBuffer = getData(dataSource, statement);

    // write data to file and store data
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
      XMLTransactionCoordinatorConnector.logger
          .info("Statement successfully executed on " + dataSource.getAddress());
    } else {
      XMLTransactionCoordinatorConnector.logger
          .info("Execution has caused an failure on " + dataSource.getAddress());
    }

    return success;
  }

  @SuppressWarnings("unchecked")
  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    XTCconnection connection = openConnection(dataSource);

    DataObject metaDataObject = this.getMetaDataSDO();
    DataObject collectionObject = null;
    DataObject documentObject = null;

    String actualCollection = null;

    List<DataObject> collections = new LinkedList<DataObject>();
    List<Element> elements = null;
    SAXBuilder saxBuilder = null;
    Document document = null;

    collectionObject = metaDataObject.createDataObject("collection");
    collectionObject.setString("name", connection.getCurrentDirectory());
    collections.add(collectionObject);

    while (collections.isEmpty() == false) {
      try {
        collectionObject = collections.get(0);
        actualCollection = collectionObject.getString("name");
        saxBuilder = new SAXBuilder();
        document = saxBuilder.build(new StringReader(connection
            .listDirectory(actualCollection)));
        elements = document.getRootElement().getChildren();
        for (int i = 0; i < elements.size(); i++) {
          if (elements.get(i).getName().equals("dir")) {
            DataObject newCollectionObject = collectionObject
                .createDataObject("collection");
            newCollectionObject.setString("name", elements.get(i)
                .getAttributeValue("name"));
            collections.add(newCollectionObject);
          } else if (elements.get(i).getName().equals("doc")) {
            documentObject = collectionObject.createDataObject("document");
            documentObject.setString("name",
                elements.get(i).getAttributeValue("name"));
          }
        }
        collections.remove(0);
      } catch (XTCexception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (JDOMException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return metaDataObject;
  }

  /**
   * This method is used to retrieve data from a XTC database.
   * 
   * @param dataSource
   * @param statement
   * @return StringBuffer the data
   */
  private StringBuffer getData(DataSource dataSource, String statement) {
    Connection connection = null;
    XTCxqueryResult xtcXQueryResult = null;
    StringBuffer stringBuffer = null;

    connection = openConnection(dataSource);
    try {
      connection.beginWork("getData");
      xtcXQueryResult = ((XTCconnection) connection).executeXQuery(statement,
          ResultType.STRING, true);
      if (xtcXQueryResult.getStringResult() != null) {
        stringBuffer = new StringBuffer();
        stringBuffer.append(xtcXQueryResult.getStringResult());
      }
      connection.commitWork();
      closeConnection(connection);
    } catch (XTCexception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      try {
        connection.rollbackWork();
      } catch (XTCexception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    return stringBuffer;
  }

  /**
   * This method is used to put data into a XTC database.
   * 
   * @param dataSource
   * @param file
   * @param target
   * @return
   */
  @SuppressWarnings("unchecked")
  private boolean storeData(DataSource dataSource, File file, String target) {
    boolean success = false;

    XTCconnection connection = null;
    SAXBuilder saxBuilder = null;
    Document document = null;
    PutInfoObject putInfoObject = null;

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

    connection = openConnection(dataSource);
    try {
      connection.beginWork("store Data");
      // create required collections
      changeCollection(connection, collectionName);
      putInfoObject = new PutInfoObject(file.getAbsolutePath().replace('\\',
          '/'), documentName, DOCUMENT_SOURCE.CLIENT);
      // delete old document (if there is one)
      String directory = connection.getCurrentDirectory();
      saxBuilder = new SAXBuilder();
      document = saxBuilder.build(new StringReader(connection
          .listDirectory(directory)));
      List<Element> list = document.getRootElement().getChildren();

      boolean hit = false;
      for (int j = 0; j < list.size(); j++) {
        if ((list.get(j).getName().equals("doc"))
            && (list.get(j).getAttributeValue("name").endsWith("/"
                + documentName))) {
          hit = true;
        }
      }
      if (hit) {
        connection.delete(documentName);
      }

      connection.put(putInfoObject);
      connection.commitWork();
      success = true;
    } catch (XTCexception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      try {
        connection.rollbackWork();
      } catch (XTCexception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

    } catch (JDOMException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    closeConnection(connection);
    if (file.exists()) {
      file.delete();
    }

    return success;
  }

  /**
   * This method is used to check if required collections are existent. If not,
   * they will be created.
   * 
   * @param connection
   * @param collection
   * @throws JDOMException
   * @throws IOException
   * @throws XTCexception
   */
  @SuppressWarnings("unchecked")
  private void changeCollection(XTCconnection connection, String collection)
      throws JDOMException, IOException, XTCexception {
    String[] stringArray = null;
    SAXBuilder saxBuilder = null;
    Document document = null;

    if (collection.equals("") == false) {
      if (collection.contains("/")) {
        // sub-collections > 0
        stringArray = collection.split(Pattern.quote("/"));
      } else {
        stringArray = new String[] { collection };
      }
      for (int i = 0; i < stringArray.length; i++) {
        // TODO Parsen der XML Baeume entfernen
        // Dies ist momentan noetig, da die Methode changeDirectory keine
        // Exception wirft, wenn die Directory nicht existiert
        if (!stringArray[i].equals("")) {
          saxBuilder = new SAXBuilder();
          String directory = connection.getCurrentDirectory();
          document = saxBuilder.build(new StringReader(connection
              .listDirectory(directory)));
          List<Element> list = document.getRootElement().getChildren();

          boolean hit = false;
          for (int j = 0; j < list.size(); j++) {
            if ((list.get(j).getName().equals("dir"))
                && (list.get(j).getAttributeValue("name").endsWith("/"
                    + stringArray[i]))) {
              hit = true;
              break;
            }
          }
          if (hit == false) {
            throw new XTCexception();
          }

          connection.changeDirectory(stringArray[i]);
        }
      }
    }
  }

  /**
   * Opens a connection.
   * 
   * @param dataSource
   * @return
   */
  private XTCconnection openConnection(DataSource dataSource) {
    // split address and port
    String[] stringArray = dataSource.getAddress().split(Pattern.quote(":"));
    String dsAddress = stringArray[0];
    int port = Integer.parseInt(stringArray[1]);
    String user = dataSource.getAuthentication().getUser();
    String password = dataSource.getAuthentication().getPassword();

    XTCconnection connection = null;
    try {
      connection = XTCdriver.getConnection(dsAddress, port, user, password);
    } catch (XTCexception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      XMLTransactionCoordinatorConnector.logger.fatal(
          "exception during establishing connection to: " + dsAddress, e);
    }

    XMLTransactionCoordinatorConnector.logger.info("Connection opened on "
        + dsAddress + ".");

    return connection;
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
      if (XMLTransactionCoordinatorConnector.logger.isDebugEnabled()) {
        XMLTransactionCoordinatorConnector.logger
            .debug("boolean closeConnection() executed successfully.");
      }
      XMLTransactionCoordinatorConnector.logger.info("Connection closed.");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      if (XMLTransactionCoordinatorConnector.logger.isDebugEnabled()) {
        XMLTransactionCoordinatorConnector.logger.error(
            "boolean closeConnection() executed with failures.", e);
      }
    }

    return success;
  }
}
