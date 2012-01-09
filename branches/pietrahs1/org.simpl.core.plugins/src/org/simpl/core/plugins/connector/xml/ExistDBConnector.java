package org.simpl.core.plugins.connector.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.xml.XMLResult;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataSource;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import org.exist.xmldb.EXistResource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implements all methods of the {@link Connector} interface for
 * supporting ExistDB, a native xml database.<br>
 * <b>Description:</b>The method: 'issueCommand' is not supported.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class ExistDBConnector extends ConnectorPlugin<File, XMLResult> {

  static Logger logger = Logger.getLogger(ExistDBConnector.class);

  public ExistDBConnector() {
    this.setType("Database");
    this.setMetaDataSchemaType("tXMLDatabaseMetaData");
    this.addSubtype("ExistDB");
    this.addLanguage("ExistDB", "XQuery");

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
    if (ExistDBConnector.logger.isDebugEnabled()) {
      ExistDBConnector.logger.debug("DataObject retrieveData("
          + dataSource.getAddress() + ", " + statement + ") executed.");
    }

    StringBuffer stringBuffer = null;
    XMLResult xmlResult = null;

    stringBuffer = getData(dataSource, statement);
    if (stringBuffer != null) {
      xmlResult = new XMLResult();
      xmlResult.setDataSource(dataSource);
      xmlResult.setDocument(stringBuffer);
    }

    ExistDBConnector.logger.info("Statement \"" + statement + "\" executed on "
        + dataSource.getAddress() + ".");

    return xmlResult;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, File file, String target)
      throws ConnectionException {
    if (ExistDBConnector.logger.isDebugEnabled()) {
      ExistDBConnector.logger.debug("boolean writeData("
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

    if (ExistDBConnector.logger.isDebugEnabled()) {
      ExistDBConnector.logger.debug("boolean writeData("
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
      ExistDBConnector.logger.info("Statement successfully executed on "
          + dataSource.getAddress());
    } else {
      ExistDBConnector.logger.info("Execution has caused an failure on "
          + dataSource.getAddress());
    }

    return success;
  }

  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    DataObject metaDataObject = this.getMetaDataSDO();
    DataObject collectionObject = null;
    try {
      Collection collection = openConnection(dataSource, "");
      collectionObject = metaDataObject.createDataObject("collection");
      try {
        collectionObject.setString("name", collection.getName());
      } catch (XMLDBException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      insertCollectionsAndDocuments(collection, collectionObject);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return metaDataObject;
  }

  /**
   * This method is used to retrieve data from a ExistDB database.
   * 
   * @param dataSource
   * @param statement
   * @return
   */
  private StringBuffer getData(DataSource dataSource, String statement) {
    Collection collection = null;
    CompiledExpression compiledExpression = null;
    ResourceSet resourceSet = null;
    ResourceIterator resourceIterator = null;
    Resource resource = null;
    StringBuffer stringBuffer = null;

    // open connection and get collection
    collection = openConnection(dataSource, "");

    // execute query
    try {
      XQueryService xqs = (XQueryService) collection.getService(
          "XQueryService", "1.0");
      xqs.setProperty("indent", "yes");

      compiledExpression = xqs.compile(statement);
      resourceSet = xqs.execute(compiledExpression);
      resourceIterator = resourceSet.getIterator();
      stringBuffer = new StringBuffer();
      while (resourceIterator.hasMoreResources()) {
        try {
          // read content
          resource = resourceIterator.nextResource();
          stringBuffer.append(resource.getContent());
        } finally {
          try {
            ((EXistResource) resource).freeResources();
          } catch (XMLDBException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (XMLDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return stringBuffer;
  }

  /**
   * This methos is used to put data into a ExistDB database.
   * 
   * @param dataSource
   * @param file
   * @param target
   * @return
   */
  private boolean storeData(DataSource dataSource, File file, String target) {
    boolean success = false;

    // collection + document name
    // example /collection1/collection2/document1
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

    Collection collection = null;
    XMLResource resource = null;

    collection = openConnection(dataSource, collectionName);
    try {
      resource = (XMLResource) collection.createResource(documentName,
          "XMLResource");
      resource.setContent(file);
      collection.storeResource(resource);
      success = true;
    } catch (XMLDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (resource != null) {
        try {
          ((EXistResource) resource).freeResources();
        } catch (XMLDBException xe) {
          xe.printStackTrace();
        }
      }
    }
    closeConnection(collection);
    if (file.exists()) {
      file.delete();
    }

    return success;
  }

  /**
   * This method is used by getMetaData(DataSource dataSource, String filter).
   * Collections and documents are added to the metaDataObject.
   * 
   * @param collection
   * @param collectionObject
   */
  private void insertCollectionsAndDocuments(Collection collection,
      DataObject collectionObject) {

    String[] collections = null;
    String[] documents = null;
    DataObject newCollectionObject = null;
    DataObject newDocumentObject = null;

    try {
      // all child collections
      collections = collection.listChildCollections();
      documents = collection.listResources();

      for (int i = 0; i < collections.length; i++) {
        // append this collection
        newCollectionObject = collectionObject.createDataObject("collection");
        newCollectionObject.setString("name", collections[i]);
        // start this method for all child collections
        insertCollectionsAndDocuments(
            collection.getChildCollection(collections[i]), newCollectionObject);
      }

      // append all documents
      for (int i = 0; i < documents.length; i++) {
        // append document
        newDocumentObject = collectionObject.createDataObject("document");
        newDocumentObject.setString("name", documents[i]);
      }

      collection.close();
    } catch (XMLDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
   * Opens a connection and a specified COLLECTION is given back.
   * 
   * @param dataSource
   * @param col
   * @return
   */
  @SuppressWarnings("rawtypes")
  private Collection openConnection(DataSource dataSource, String col) {
    String driverName = getFromPropertiesDescription("driverName",
        dataSource.getConnectorPropertiesDescription());
    String addressPrefix = getFromPropertiesDescription("addressPrefix",
        dataSource.getConnectorPropertiesDescription());
    String dsAddress = dataSource.getAddress();
    String user = dataSource.getAuthentication().getUser();
    String password = dataSource.getAuthentication().getPassword();

    StringBuilder uri = new StringBuilder();
    uri.append(addressPrefix);
    uri.append(dsAddress);

    Class c = null;
    Collection collection = null;

    try {
      c = Class.forName(driverName);
      Database database = (Database) c.newInstance();
      database.setProperty("create-database", "true");
      DatabaseManager.registerDatabase(database);

      if (col.equals("")) {
        collection = DatabaseManager.getCollection(uri.toString(), user,
            password);
      } else {
        String[] stringArray = null;
        if (col.contains("/")) {
          stringArray = col.split(Pattern.quote("/"));
        } else {
          stringArray = new String[] { col };
        }
        String subSequence = "";
        Collection parent = DatabaseManager.getCollection(uri.toString(), user,
            password);

        for (int i = 0; i < stringArray.length; i++) {
          if (!stringArray[i].equals("")) {
            subSequence += "/" + stringArray[i];
            Collection child = DatabaseManager.getCollection(uri.toString()
                + subSequence, user, password);
            // create required collection
            if (child == null) {
              CollectionManagementService mgt = (CollectionManagementService) parent
                  .getService("CollectionManagementService", "1.0");
              child = mgt.createCollection(stringArray[i]);
            }
            parent = child;
          }
        }
        collection = parent;
      }
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      ExistDBConnector.logger.fatal(
          "exception during establishing connection to: " + dsAddress, e);
    } catch (XMLDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    ExistDBConnector.logger.info("Connection opened on " + dsAddress + ".");

    return collection;
  }

  /**
   * Closes a connection.
   * 
   * @param collection
   */
  private void closeConnection(Collection collection) {
    try {
      collection.close();
      if (ExistDBConnector.logger.isDebugEnabled()) {
        ExistDBConnector.logger
            .debug("boolean closeConnection() executed successfully.");
      }
    } catch (XMLDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      if (ExistDBConnector.logger.isDebugEnabled()) {
        ExistDBConnector.logger.error(
            "boolean closeConnection() executed with failures.", e);
      }
    }
  }
}
