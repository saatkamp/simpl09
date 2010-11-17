package org.simpl.resource.management;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.simpl.core.webservices.client.Authentication;
import org.simpl.core.webservices.client.DataSource;
import org.simpl.core.webservices.client.DatasourceService;
import org.simpl.core.webservices.client.DatasourceServiceClient;
import org.simpl.core.webservices.client.LateBinding;
import org.xml.sax.InputSource;

/**
 * <b>Purpose:</b>The Resource Management stores data sources and other resources for the
 * SIMPL framework. This class represents the resource management and its web service
 * interface.<br>
 * <b>Description:</b>The resources are stored in a PostgreSQL database that is actually
 * accessed via the SIMPL Core data source web service. The PostgreSQL data source and the
 * SIMPL Core data source web service are setup in the
 * WEB-INF\lib\resource-management-config.xml file.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 * @author Michael Schneidt <michael.schneidt@arcor.de>
 */
@WebService(name = "ResourceManagement")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ResourceManagement {
  DatasourceService dataSourceService = DatasourceServiceClient
      .getService(ResourceManagementConfig.getInstance().getDataSourceServiceAddress());
  DataSource rmDataSource = ResourceManagementConfig.getInstance().getDataSource();

  Document configDoc = null;
  Element root = null;
  List<Element> rows = null;
  SAXBuilder saxBuilder = new SAXBuilder();

  /**
   * Returns all data sources.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAllDataSources")
  public DataSourceList getAllDataSources() throws Exception {
    ArrayList<DataSource> dataSources = null;
    String statement = "SELECT * FROM datasources ORDER BY id ASC";
    String result = null;
    
    // retrieve data sources
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    DataSourceList dataSourceList = new DataSourceList();
    dataSourceList.getDataSources().addAll(dataSources);

    return dataSourceList;
  }

  /**
   * Returns all data sources filtered by type.
   * 
   * @param type
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataSourcesByType")
  public DataSourceList getDataSourcesByType(@WebParam(name = "type") String type)
      throws Exception {
    DataSourceList dataSourceList = new DataSourceList();
    ArrayList<DataSource> dataSources = null;
    String statement = null;
    String result = null;

    // build select statement
    statement = "SELECT * FROM datasources WHERE ";
    statement += "type LIKE '" + type + "'";

    // retrieve data sources
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    dataSourceList.getDataSources().addAll(dataSources);

    return dataSourceList;
  }

  /**
   * Returns all data sources filtered by type and subtype.
   * 
   * @param type
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataSourcesByTypeAndSubtype")
  public DataSourceList getDataSourcesByTypeAndSubtype(
      @WebParam(name = "type") String type, @WebParam(name = "subType") String subType)
      throws Exception {
    DataSourceList dataSourceList = new DataSourceList();
    ArrayList<DataSource> dataSources = null;
    String statement = null;
    String result = null;

    // build select statement
    statement = "SELECT * FROM datasources WHERE ";
    statement += "type LIKE '" + type + "' AND ";
    statement += "subtype LIKE '" + subType + "' ORDER BY id ASC";

    // retrieve data sources
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    dataSourceList.getDataSources().addAll(dataSources);

    return dataSourceList;
  }

  /**
   * Returns all data sources filtered by name.
   * 
   * @param type
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataSourcesByName")
  public DataSource getDataSourceByName(@WebParam(name = "name") String name)
      throws Exception {
    DataSource resultDataSource = new DataSource();
    DataSourceList dataSourceList = new DataSourceList();
    ArrayList<DataSource> dataSources = null;
    String statement = null;
    String result = null;

    // build select statement
    statement = "SELECT * FROM datasources WHERE ";
    statement += "logical_name LIKE '" + name + "'";

    // retrieve data source
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    dataSourceList.getDataSources().addAll(dataSources);

    if (dataSources.size() > 0) {
      resultDataSource = dataSources.get(0);
    }

    return resultDataSource;
  }

  /**
   * Returns a data source by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataSourceById")
  public DataSource getDataSourceById(@WebParam(name = "id") int id) throws Exception {
    DataSource resultDataSource = new DataSource();
    DataSourceList dataSourceList = new DataSourceList();
    ArrayList<DataSource> dataSources = null;
    String statement = null;
    String result = null;

    // build select statement
    statement = "SELECT * FROM datasources WHERE ";
    statement += "id = " + id;

    // retrieve data source
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    dataSourceList.getDataSources().addAll(dataSources);

    if (dataSources.size() > 0) {
      resultDataSource = dataSources.get(0);
    }

    return resultDataSource;
  }

  /**
   * Creates the tables for the Resource Management in the configured PostgreSQL data
   * source.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "createResourceManagementTables")
  public boolean createResourceManagementTables() throws Exception {
    boolean success = false;
    String statement = null;

    // TODO resource_management.sql ausführen
    // Datei einlesen -> Nach ; splitten -> Zeilenumbrüche entfernen -> executeStatement

    // build SQL create statement
    statement = "CREATE TABLE datasources (";
    statement += "id SERIAL PRIMARY KEY, ";
    statement += "name VARCHAR(255), ";
    statement += "address VARCHAR(255), ";
    statement += "type VARCHAR(255), ";
    statement += "subtype VARCHAR(255), ";
    statement += "language VARCHAR(255), ";
    statement += "dataformat VARCHAR(255), ";
    statement += "policy XML, ";
    statement += "username VARCHAR(255), ";
    statement += "password VARCHAR(255)";
    statement += ")";

    success = dataSourceService.executeStatement(rmDataSource, statement);

    return success;
  }

  /**
   * Adds a data source to the Resource Management.
   * 
   * @param dataSource
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addDataSource")
  public boolean addDataSource(DataSource dataSource) throws Exception {
    boolean success = false;
    String statement = null;
    String policy = dataSource.getLateBinding().getPolicy();

    // set empty xml policy value
    if (policy.equals("")) {
      policy = "NULL";
    } else {
      policy = "'" + policy + "'";
    }

    // build SQL insert statement
    statement = "INSERT INTO datasources (logical_name, interface_description, type, subtype, language, dataformat, properties_description, security_username, security_password) VALUES (";
    statement += "'" + dataSource.getName() + "', ";
    statement += "'" + dataSource.getAddress() + "', ";
    statement += "'" + dataSource.getType() + "', ";
    statement += "'" + dataSource.getSubType() + "', ";
    statement += "'" + dataSource.getLanguage() + "', ";
    statement += "'" + dataSource.getDataFormat() + "', ";
    statement += "" + policy + ", ";
    statement += "'" + dataSource.getAuthentication().getUser() + "', ";
    statement += "'" + dataSource.getAuthentication().getPassword() + "'";
    statement += ")";

    // add data source
    success = dataSourceService.executeStatement(rmDataSource, statement);

    return success;
  }

  /**
   * Updates a data source.
   * 
   * @param dataSource
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateDataSource")
  public boolean updateDataSource(DataSource dataSource) throws Exception {
    boolean success = false;
    String statement = null;
    String policy = dataSource.getLateBinding().getPolicy();

    // set empty xml policy value
    if (policy.equals("")) {
      policy = "NULL";
    } else {
      policy = "'" + policy + "'";
    }

    // build SQL update statement
    statement = "UPDATE datasources SET ";
    statement += "logical_name='" + dataSource.getName() + "',";
    statement += "interface_description='"
        + dataSource.getAddress().replace("\\", "\\\\") + "',";
    statement += "type='" + dataSource.getType() + "',";
    statement += "subtype='" + dataSource.getSubType() + "',";
    statement += "language='" + dataSource.getLanguage() + "',";
    statement += "dataformat='" + dataSource.getDataFormat() + "',";
    statement += "security_username='" + dataSource.getAuthentication().getUser() + "',";
    statement += "security_password='" + dataSource.getAuthentication().getPassword()
        + "',";
    statement += "properties_description=" + policy + "";
    statement += " WHERE id=" + dataSource.getId();

    // update data source
    success = dataSourceService.executeStatement(rmDataSource, statement);

    return success;
  }

  /**
   * Deletes a data source from the Resource Management.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteDataSource")
  public boolean deleteDataSource(int id) throws Exception {
    boolean success = false;
    String statement = "DELETE FROM datasources WHERE id = " + String.valueOf(id);

    // delete data source
    success = dataSourceService.executeStatement(rmDataSource, statement);

    return success;
  }

  /**
   * Returns a list of data source connectors.
   * 
   * @return
   * @throws Exception
   * @throws JDOMException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  @WebMethod(action = "getDataSourceConnectors")
  public DataSourceConnectorList getDataSourceConnectors() throws Exception {
    DataSourceConnectorList dataSourceConnectors = new DataSourceConnectorList();
    String statement = "SELECT * FROM datasourceconnectors";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data converters
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      DataSourceConnector dataSourceConnector = new DataSourceConnector();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          dataSourceConnector.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          dataSourceConnector.setName(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("properties_description")) {
          dataSourceConnector.setPropertiesDescription(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("dataconverter_dataformat")) {
          dataSourceConnector.setDataConverterDataFormat(column.getValue());
        }
      }

      dataSourceConnectors.getDataSourceConnectors().add(dataSourceConnector);
    }

    return dataSourceConnectors;
//    DataSourceConnectorList dataSourceConnectorList = new DataSourceConnectorList();
//    String statement = "SELECT * FROM datasourceconnectors";
//    DataObject result = (DataObject) dataSourceService.retrieveData(rmDataSource,
//        statement);
//
//    List<DataObject> tables = result.getList("table");
//    List<DataObject> rows = null;
//    List<DataObject> columns = null;
//
//    rows = tables.get(0).getList("row");
//
//    for (DataObject row : rows) {
//      columns = row.getList("column");
//
//      DataSourceConnector dataSourceConnector = new DataSourceConnector();
//
//      for (DataObject column : columns) {
//        column.getString("name");
//
//        if (column.getString("name").equals("id")) {
//          dataSourceConnector.setId(String.valueOf(column.getInt(0)));
//        } else if (column.getString("name").equals("name")) {
//          dataSourceConnector.setName(column.getString(0));
//        } else if (column.getString("name").equals("properties_description")) {
//          dataSourceConnector.setPropertiesDescription(column.getString(0));
//        } else if (column.getString("name").equals("dataconverter_dataformat")) {
//          dataSourceConnector.setDataConverterDataFormat(column.getString(0));
//        }
//      }
//
//      dataSourceConnectorList.getDataSourceConnectors().add(dataSourceConnector);
//    }
  }

  /**
   * Returns a list of data converters.
   * 
   * @return
   * @throws Exception
   * @throws JDOMException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  @WebMethod(action = "getDataConverters")
  public DataConverterList getDataConverters() throws Exception {
    DataConverterList dataConverters = new DataConverterList();
    String statement = "SELECT * FROM dataconverters";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data converters
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      DataConverter dataConverter = new DataConverter();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          dataConverter.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          dataConverter.setName(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("datasourceconnector_dataformat")) {
          dataConverter.setDataSourceConnectorDataFormat(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("workflow_dataformat")) {
          dataConverter.setWorkflowDataFormat(column.getValue());
        }
      }

      dataConverters.getDataConverters().add(dataConverter);
    }

    return dataConverters;
    
//    DataConverterList dataConverters = new DataConverterList();
//    String statement = "SELECT * FROM dataconverters";
//    String result = dataSourceService.retrieveData(rmDataSource,
//        statement);
//
//    List<DataObject> tables = result.getList("table");
//    List<DataObject> rows = null;
//    List<DataObject> columns = null;
//
//    rows = tables.get(0).getList("row");
//
//    for (DataObject row : rows) {
//      columns = row.getList("column");
//
//      DataConverter dataConverter = new DataConverter();
//
//      for (DataObject column : columns) {
//        column.getString("name");
//
//        if (column.getString("name").equals("id")) {
//          dataConverter.setId(String.valueOf(column.getInt(0)));
//        } else if (column.getString("name").equals("name")) {
//          dataConverter.setName(column.getString(0));
//        } else if (column.getString("name").equals("datasourceconnector_dataformat")) {
//          dataConverter.setDataSourceConnectorDataFormat(column.getString(0));
//        } else if (column.getString("name").equals("workflow_dataformat")) {
//          dataConverter.setWorkflowDataFormat(column.getString(0));
//        }
//      }
//
//      dataConverters.getDataConverters().add(dataConverter);
//    }
  }

  /**
   * Creates DataSource objects from a RDB data format result.
   * 
   * @param result
   * @return
   * @throws IOException
   * @throws JDOMException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private ArrayList<DataSource> getDataSourcesFromResult(String result)
      throws JDOMException, IOException {
    ArrayList<DataSource> dataSources = new ArrayList<DataSource>();

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      DataSource dataSource = new DataSource();
      Authentication authentication = new Authentication();
      LateBinding lateBinding = new LateBinding();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          dataSource.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("logical_name")) {
          dataSource.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("interface_description")) {
          dataSource.setAddress(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("type")) {
          dataSource.setType(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("subtype")) {
          dataSource.setSubType(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("language")) {
          dataSource.setLanguage(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("dataformat")) {
          dataSource.setDataFormat(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("security_username")) {
          authentication.setUser(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("security_password")) {
          authentication.setPassword(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("properties_description")) {
          lateBinding.setPolicy(column.getValue());
        }
      }

      dataSource.setAuthentication(authentication);
      dataSource.setLateBinding(lateBinding);
      dataSources.add(dataSource);
    }

    return dataSources;
//    ArrayList<DataSource> dataSources = new ArrayList<DataSource>();
//
//    List<DataObject> tables = result.getList("table");
//    List<DataObject> rows = null;
//    List<DataObject> columns = null;
//
//    rows = tables.get(0).getList("row");
//
//    for (DataObject row : rows) {
//      columns = row.getList("column");
//
//      DataSource dataSource = new DataSource();
//      Authentication authentication = new Authentication();
//      LateBinding lateBinding = new LateBinding();
//
//      for (DataObject column : columns) {
//        column.getString("name");
//
//        if (column.getString("name").equals("id")) {
//          dataSource.setId(String.valueOf(column.getInt(0)));
//        } else if (column.getString("name").equals("logical_name")) {
//          dataSource.setName(column.getString(0));
//        } else if (column.getString("name").equals("interface_description")) {
//          dataSource.setAddress(column.getString(0));
//        } else if (column.getString("name").equals("type")) {
//          dataSource.setType(column.getString(0));
//        } else if (column.getString("name").equals("subtype")) {
//          dataSource.setSubType(column.getString(0));
//        } else if (column.getString("name").equals("language")) {
//          dataSource.setLanguage(column.getString(0));
//        } else if (column.getString("name").equals("dataformat")) {
//          dataSource.setDataFormat(column.getString(0));
//        } else if (column.getString("name").equals("security_username")) {
//          authentication.setUser(column.getString(0));
//        } else if (column.getString("name").equals("security_password")) {
//          authentication.setPassword(column.getString(0));
//        } else if (column.getString("name").equals(
//            "properties_description")) {
//          lateBinding.setPolicy(column.getString(0));
//        }
//      }
//
//      dataSource.setAuthentication(authentication);
//      dataSource.setLateBinding(lateBinding);
//      dataSources.add(dataSource);
//    }
  }
}