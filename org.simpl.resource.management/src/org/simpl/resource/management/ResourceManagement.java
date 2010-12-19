package org.simpl.resource.management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * accessed via an additionally deployed SIMPL Core data source web service. The
 * PostgreSQL data source and the SIMPL Core data source web service are setup in the
 * WEB-INF\lib\resource-management-config.xml file.<br>
 * The PostgreSQL database is setup via the createResourceManagementTables() method, that
 * executes the statements from the resource_management.sql on the database.<br>
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
  // TODO: use xpath in statements and fill in the default xml in the database
  final static String propertiesDescription = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><datasourceconnector_properties_description xmlns=\"http://org.simpl.resource.management/datasources/datasourceconnector_properties_description\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://org.simpl.resource.management/datasources/datasourceconnector_properties_description datasources.xsd \"><type>%s</type><subType>%s</subType><language>%s</language><dataFormat>%s</dataFormat></datasourceconnector_properties_description>";

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
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT datasources.* ";
    statement += "FROM datasources ";
    statement += "WHERE getProperty('type', datasources.datasourceconnector_properties_description) = '"
        + type + "' ";
    statement += "ORDER BY datasources.id ASC";

    // retrieve data sources
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSources = this.getDataSourcesFromResult(result);

    dataSourceList.getDataSources().addAll(dataSources);

    return dataSourceList;
  }

  /**
   * Returns all data sources filtered by type and sub type.
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
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT * ";
    statement += "FROM datasources ";
    statement += "WHERE getProperty('type', datasourceconnector_properties_description) = '"
        + type + "' ";
    statement += "AND ";
    statement += "getProperty('subType', datasourceconnector_properties_description) LIKE '"
        + subType + "' ";
    statement += "ORDER BY id ASC";

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
   * The SQL statements are retrieved from the resource_management.sql file that is placed
   * inside the resource management web service jar.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "createResourceManagementTables")
  public boolean createResourceManagementTables() throws Exception {
    final String sqlFile = "resource_management.sql";

    boolean success = false;
    String fileLine = null;
    String statement = "";
    ArrayList<String> statements = new ArrayList<String>();
    BufferedReader bufferedFileReader = null;
    InputStream fileStream = null;

    fileStream = this.getClass().getClassLoader().getResourceAsStream(sqlFile);
    bufferedFileReader = new BufferedReader(new InputStreamReader(fileStream));

    // build one line statements
    while ((fileLine = bufferedFileReader.readLine()) != null) {
      if (!fileLine.equals("")) {
        statement += fileLine;
      } else {
        statements.add(statement);
        statement = "";
      }
    }

    // execute statements
    for (int i = 0; i < statements.size(); i++) {
      success = dataSourceService.executeStatement(rmDataSource, statements.get(i));

      if (!success) {
        break;
      }
    }

    // drop tables on failure
    if (!success) {
      dataSourceService
          .executeStatement(rmDataSource, "DROP TABLE IF EXISTS datasources");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS datasourceconnectors_dataconverters");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS datasourceconnectors");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS dataconverters");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS datacontainers");
      dataSourceService
          .executeStatement(rmDataSource, "DROP TABLE IF EXISTS dataformats");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS statement_types");
      dataSourceService.executeStatement(rmDataSource, "DROP TABLE IF EXISTS languages");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS getProperty(text, xml)");
    }

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
    statement = "INSERT INTO datasources (logical_name, interface_description, datasourceconnector_properties_description, properties_description, security_username, security_password) VALUES (";
    statement += "'" + dataSource.getName() + "', ";
    statement += "'" + dataSource.getAddress() + "', ";
    statement += "'"
        + String
            .format(propertiesDescription, dataSource.getType(), dataSource.getSubType(),
                dataSource.getLanguage(), dataSource.getDataFormat()) + "', ";
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
    statement += "datasourceconnector_properties_description='"
        + String
            .format(propertiesDescription, dataSource.getType(), dataSource.getSubType(),
                dataSource.getLanguage(), dataSource.getDataFormat()) + "', ";
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
    String statement = "SELECT datasourceconnectors.id, datasourceconnectors.name, datasourceconnectors.properties_description, dataformats.name AS dataformat FROM datasourceconnectors INNER JOIN dataformats ON (datasourceconnectors.dataconverter_dataformat_id = dataformats.id)";
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
        } else if (column.getAttribute("name").getValue().equals("dataformat")) {
          dataSourceConnector.setDataConverterDataFormat(column.getValue());
        }
      }

      dataSourceConnectors.getDataSourceConnectors().add(dataSourceConnector);
    }

    return dataSourceConnectors;
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
  }

  /**
   * Returns a list of data source types.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataSourceTypes")
  public StringList getDataSourceTypes() throws Exception {
    StringList stringList = new StringList();
    ArrayList<String> propertiesDescriptions = null;

    String statement = "SELECT properties_description FROM datasourceconnectors";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    // extract properties description from result
    propertiesDescriptions = this.getColumnValuesFromResult(result,
        "properties_description");

    // extract types from the properties description
    for (String propertyDescription : propertiesDescriptions) {
      stringList.getItems().add(
          this.getFromPropertiesDescription("type", propertyDescription));
    }

    // remove duplicates
    HashSet<String> h = new HashSet<String>(stringList.getItems());
    stringList.getItems().clear();
    stringList.getItems().addAll(h);

    // sort items
    Collections.sort(stringList.getItems());

    return stringList;
  }

  /**
   * Returns a list of data source sub types of the given type.
   * 
   * @param type
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataSourceSubTypes")
  public StringList getDataSourceSubTypes(String type) throws Exception {
    StringList stringList = new StringList();
    ArrayList<String> propertiesDescriptions = null;

    String statement = "SELECT properties_description FROM datasourceconnectors";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    // extract properties description from result
    propertiesDescriptions = this.getColumnValuesFromResult(result,
        "properties_description");

    // extract sub types from the properties description
    for (String propertyDescription : propertiesDescriptions) {
      String typeFromResult = this.getFromPropertiesDescription("type",
          propertyDescription);

      if (typeFromResult.equals(type)) {
        stringList.getItems().add(
            this.getFromPropertiesDescription("subType", propertyDescription));
      }
    }

    // remove duplicates
    HashSet<String> h = new HashSet<String>(stringList.getItems());
    stringList.getItems().clear();
    stringList.getItems().addAll(h);

    // sort items
    Collections.sort(stringList.getItems());

    return stringList;
  }

  /**
   * Returns a list of data source languages of the given sub type.
   * 
   * @param subType
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataSourceLanguages")
  public StringList getDataSourceLanguages(String subType) throws Exception {
    StringList stringList = new StringList();
    ArrayList<String> propertiesDescriptions = null;

    String statement = "SELECT properties_description FROM datasourceconnectors";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    // extract properties description from result
    propertiesDescriptions = this.getColumnValuesFromResult(result,
        "properties_description");

    // extract sub types from the properties description
    for (String propertyDescription : propertiesDescriptions) {
      String typeFromResult = this.getFromPropertiesDescription("subType",
          propertyDescription);

      if (typeFromResult.equals(subType)) {
        stringList.getItems().add(
            this.getFromPropertiesDescription("language", propertyDescription));
      }
    }

    // remove duplicates
    HashSet<String> h = new HashSet<String>(stringList.getItems());
    stringList.getItems().clear();
    stringList.getItems().addAll(h);

    // sort items
    Collections.sort(stringList.getItems());

    return stringList;
  }

  /**
   * Returns the statement description of a language.
   * 
   * @param language
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getLanguageStatementDescription")
  public String getLanguageStatementDescription(String language) throws Exception {
    String statementDescription = null;
    String statement = "";
    String result = null;
    
    statement += "SELECT CAST(statement_description AS TEXT) ";
    statement += "FROM languages ";
    statement += "WHERE name LIKE '%" + language + "%'";
    
    result = dataSourceService.retrieveData(rmDataSource, statement);
    
    statementDescription = this.getColumnValuesFromResult(result, "statement_description").get(0);
    
    return statementDescription;
  }

  /**
   * Returns the names of all available languages.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getLanguages")
  public StringList getLanguages() throws Exception {
    StringList languages = new StringList();
    
    String statement = "SELECT name FROM languages";
    String result = dataSourceService.retrieveData(rmDataSource, statement);
    
    languages.getItems().addAll(this.getColumnValuesFromResult(result, "name"));
    
    return languages;
  }
  
  /**
   * Returns a list of data format types that can be converted to and from the data format
   * type of the given data source.
   * 
   * @param dataSource
   * @param dataFormatType
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getSupportedConvertDataFormatTypes")
  public StringList getSupportedConvertDataFormatTypes(DataSource dataSource)
      throws Exception {
    StringList stringList = new StringList();
    String statement = "";
    String result = null;

    statement += "SELECT dataformats.name ";
    statement += "FROM datasources ";
    statement += "INNER JOIN datasourceconnectors ON (datasources.datasourceconnector_id = datasourceconnectors.id) ";
    statement += "INNER JOIN dataconverters ON (dataconverters.datasourceconnector_dataformat_id = datasourceconnectors.dataconverter_dataformat_id OR dataconverters.workflow_dataformat_id = datasourceconnectors.dataconverter_dataformat_id) ";
    statement += "INNER JOIN dataformats ON (dataconverters.datasourceconnector_dataformat_id = dataformats.id OR dataconverters.workflow_dataformat_id = dataformats.id) ";
    statement += "WHERE datasources.id = " + dataSource.getId();

    result = dataSourceService.retrieveData(rmDataSource, statement);

    stringList.getItems().addAll(getColumnValuesFromResult(result, "name"));

    // remove duplicates
    HashSet<String> h = new HashSet<String>(stringList.getItems());
    stringList.getItems().clear();
    stringList.getItems().addAll(h);

    // remove data format of the data source itself
    stringList.getItems().remove(dataSource.getDataFormat());

    // sort items
    Collections.sort(stringList.getItems());

    return stringList;
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

    // transform the document to a list of data source objects
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
        } else if (column.getAttribute("name").getValue()
            .equals("datasourceconnector_properties_description")) {
          dataSource
              .setType(this.getFromPropertiesDescription("type", column.getValue()));
          dataSource.setSubType(this.getFromPropertiesDescription("subType",
              column.getValue()));
          dataSource.setLanguage(this.getFromPropertiesDescription("language",
              column.getValue()));
          dataSource.setDataFormat(this.getFromPropertiesDescription("dataFormat",
              column.getValue()));
        } else if (column.getAttribute("name").getValue().equals("security_username")) {
          authentication.setUser(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("security_password")) {
          authentication.setPassword(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("properties_description")) {
          lateBinding.setPolicy(column.getValue());
        }
      }

      dataSource.setAuthentication(authentication);
      dataSource.setLateBinding(lateBinding);
      dataSources.add(dataSource);
    }

    return dataSources;
  }

  /**
   * Returns a list of all items from the given column.
   * 
   * @param result
   * @param columnName
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private ArrayList<String> getColumnValuesFromResult(String result, String columnName)
      throws Exception {
    ArrayList<String> values = new ArrayList<String>();

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals(columnName)) {
          values.add(column.getValue());
        }
      }
    }

    return values;
  }

  /**
   * Returns a property from the given properties description.
   * 
   * @param property
   *          Property name
   * @param propertiesDescription
   *          XML data
   * @return
   */
  private String getFromPropertiesDescription(String property,
      String propertiesDescription) {
    String value = null;
    Pattern pattern = Pattern.compile("<" + property + ">(.*?)</" + property + ">");
    Matcher matcher = pattern.matcher(propertiesDescription);

    if (matcher.find()) {
      value = matcher.group(1);
    }

    return value;
  }
}