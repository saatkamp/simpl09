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
import org.simpl.resource.management.client.DataSourceList;
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

  /**
   * Returns all data sources.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAllDataSources")
  public DataSourceList getAllDataSources() throws Exception {
    ArrayList<DataSource> dataSources = null;
    String statement = "SELECT * FROM data_sources";
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
    ArrayList<DataSource> dataSources = null;
    String statement = null;
    String result = null;

    // build select statement
    statement = "SELECT * FROM data_sources WHERE ";
    statement += "type LIKE '" + type + "'";

    // retrieve data sources
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    DataSourceList dataSourceList = new DataSourceList();
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
    ArrayList<DataSource> dataSources = null;
    String statement = null;
    String result = null;

    // build select statement
    statement = "SELECT * FROM data_sources WHERE ";
    statement += "type LIKE '" + type + "' AND ";
    statement += "type LIKE '" + type + "'";

    // retrieve data sources
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    DataSourceList dataSourceList = new DataSourceList();
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
    ArrayList<DataSource> dataSources = null;
    String statement = null;
    String result = null;
    DataSource resultDataSource = new DataSource();

    // build select statement
    statement = "SELECT * FROM data_sources WHERE ";
    statement += "name LIKE '" + name + "'";

    // retrieve data source
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    DataSourceList dataSourceList = new DataSourceList();
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
  public DataSource getDataSourceById(@WebParam(name = "id") int id)
      throws Exception {
    String statement = null;
    String result = null;
    DataSource resultDataSource = new DataSource();

    // build select statement
    statement = "SELECT * FROM data_sources WHERE ";
    statement += "id = " + id + "'";

    // retrieve data source
    result = dataSourceService.retrieveData(rmDataSource, statement);
    resultDataSource = this.getDataSourcesFromResult(result).get(0);

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

    // build SQL create statement
    statement = "CREATE TABLE data_sources (";
    statement += "id SERIAL PRIMARY KEY, ";
    statement += "name VARCHAR(255), ";
    statement += "address VARCHAR(255), ";
    statement += "type VARCHAR(255), ";
    statement += "subtype VARCHAR(255), ";
    statement += "language VARCHAR(255), ";
    statement += "dataformat VARCHAR(255), ";
    statement += "wspolicy XML, ";
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

    // build SQL insert statement
    statement = "INSERT INTO data_sources (name, address, type, subtype, language, dataformat, wspolicy, username, password) VALUES (";
    statement += "'" + dataSource.getName() + "', ";
    statement += "'" + dataSource.getAddress() + "', ";
    statement += "'" + dataSource.getType() + "', ";
    statement += "'" + dataSource.getSubType() + "', ";
    statement += "'" + dataSource.getLanguage() + "', ";
    statement += "'" + dataSource.getDataFormat() + "', ";
    statement += "'" + dataSource.getLateBinding().getPolicy() + "', ";
    statement += "'" + dataSource.getAuthentication().getUser() + "', ";
    statement += "'" + dataSource.getAuthentication().getPassword() + "'";
    statement += ")";

    // add data source
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
    String statement = "DELETE FROM data_sources WHERE id = " + String.valueOf(id);

    // delete data source
    success = dataSourceService.executeStatement(rmDataSource, statement);

    return success;
  }

  /**
   * Parses data sources from a rdb data format result.
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
      List<Element> columns = row.getChildren("column");
      
      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          dataSource.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          dataSource.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("address")) {
          dataSource.setAddress(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("type")) {
          dataSource.setType(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("subtype")) {
          dataSource.setSubType(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("language")) {
          dataSource.setLanguage(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("dataformat")) {
          dataSource.setDataFormat(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("username")) {
          authentication.setUser(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("password")) {
          authentication.setPassword(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("wspolicy")) {
          dataSource.setDataFormat(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("id")) {
          dataSource.setName(column.getValue());
        }
      }

      dataSource.setAuthentication(authentication);
      dataSources.add(dataSource);
    }

    return dataSources;
  }
}