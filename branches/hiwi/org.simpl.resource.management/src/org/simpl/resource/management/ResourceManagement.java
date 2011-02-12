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
import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.ConnectorList;
import org.simpl.resource.management.data.Converter;
import org.simpl.resource.management.data.ConverterList;
import org.simpl.resource.management.data.DataFormat;
import org.simpl.resource.management.data.DataFormatList;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.DataSourceList;
import org.simpl.resource.management.data.LateBinding;
import org.simpl.resource.management.data.Strategy;
import org.simpl.resource.management.data.StringList;
import org.simpl.resource.management.db.DataSourceService;
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
  final static String connectorPropertiesDescription = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><connector_properties_description xmlns=\"http://org.simpl.resource.management/datasources/connector_properties_description\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd \"><type>%s</type><subType>%s</subType><language>%s</language><dataFormatName>%s</dataFormatName></connector_properties_description>";
  final static String propertiesDescription = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <properties_description xmlns=\"http://org.simpl.resource.management/connectors/properties_description\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://org.simpl.resource.management/connectors/properties_description connectors.xsd \"><type>%s</type><subType>%s</subType><language>%s</language><dataFormatName>%s</dataFormatName></properties_description>";

  DataSourceService dataSourceService = new DataSourceService();
  String dataSourceType = null;
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
    DataSourceList dataSourceList = new DataSourceList();
    String statement = "";
    String result = null;

    statement += "SELECT datasources.*, connectors.name AS connector_name, connectors.implementation AS connector_implementation, connector_properties_description ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "ORDER BY id ASC";

    // retrieve data sources
    result = dataSourceService.retrieveData(rmDataSource, statement);

    if (result != null) {
      dataSources = this.getDataSourcesFromResult(result);

      dataSourceList.getDataSources().addAll(dataSources);
    }

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
    statement += "SELECT datasources.*, connectors.name AS connector_name, connectors.implementation AS connector_implementation, connector_properties_description ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "WHERE getDataSourceXMLProperty('type', datasources.connector_properties_description) = '"
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
    statement += "SELECT datasources.*, connectors.name AS connector_name, connectors.implementation AS connector_implementation, connector_properties_description ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "WHERE getDataSourceXMLProperty('type', connector_properties_description) = '"
        + type + "' ";
    statement += "AND getDataSourceXMLProperty('subType', connector_properties_description) LIKE '"
        + subType + "' ";
    statement += "ORDER BY datasources.id ASC";

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
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT datasources.*, connectors.name AS connector_name, connectors.implementation AS connector_implementation, connector_properties_description ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "WHERE logical_name LIKE '" + name + "'";

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
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT datasources.*, connectors.name AS connector_name, connectors.implementation AS connector_implementation, connector_properties_description ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "WHERE datasources.id = " + id;

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
   * Returns a connector by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getConnectorById")
  public Connector getConnectorById(@WebParam(name = "id") int id) throws Exception {
    Connector resultConnector = new Connector();
    ConnectorList connectorList = new ConnectorList();
    ArrayList<Connector> connectors = null;
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT connectors.*, dataformats.name AS dataformat_name, dataformats.name AS dataformat_implementation, dataformats.xml_schema ";
    statement += "FROM connectors ";
    statement += "LEFT JOIN dataformats ON (connectors.converter_dataformat_id = dataformats.id) ";
    statement += "WHERE connectors.id = " + id;

    // retrieve connnector
    result = dataSourceService.retrieveData(rmDataSource, statement);
    connectors = this.getConnectorsFromResult(result);

    connectorList.getConnectors().addAll(connectors);

    if (connectors.size() > 0) {
      resultConnector = connectors.get(0);
    }

    return resultConnector;
  }

  /**
   * Returns a coconverter by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getConverterById")
  public Converter getConverterById(@WebParam(name = "id") int id) throws Exception {
    Converter resultConverter = new Converter();
    ConverterList converterList = new ConverterList();
    ArrayList<Converter> converters = null;
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT converters.*, connector_dataformats.name connector_dataformat_name, workflow_dataformats.name workflow_dataformat_name ";
    statement += "FROM converters ";
    statement += "LEFT JOIN dataformats connector_dataformats ON (converters.connector_dataformat_id = connector_dataformats.id) ";
    statement += "LEFT JOIN dataformats workflow_dataformats ON (converters.workflow_dataformat_id = workflow_dataformats.id) ";
    statement += "WHERE converters.id = " + id;

    // retrieve converter
    result = dataSourceService.retrieveData(rmDataSource, statement);
    converters = this.getConvertersFromResult(result);

    converterList.getConverters().addAll(converters);

    if (converters.size() > 0) {
      resultConverter = converters.get(0);
    }

    return resultConverter;
  }

  /**
   * Returns a list of all data source connectors.
   * 
   * @return
   * @throws Exception
   * @throws JDOMException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  @WebMethod(action = "getAllConnectors")
  public ConnectorList getAllConnectors() throws Exception {
    ConnectorList connectors = new ConnectorList();
    String statement = "";
    String result;

    statement += "SELECT connectors.id, connectors.name, connectors.implementation, connectors.properties_description, dataformats.name AS dataformat_name, dataformats.implementation AS dataformat_implementation ";
    statement += "FROM connectors ";
    statement += "LEFT JOIN dataformats ON (connectors.converter_dataformat_id = dataformats.id) ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data converters
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      Connector connector = new Connector();
      DataFormat converterDataFormat = new DataFormat();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          connector.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          connector.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("implementation")) {
          connector.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("properties_description")) {
          connector.setPropertiesDescription(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("dataformat_name")) {
          converterDataFormat.setName(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("dataformat_implementation")) {
          converterDataFormat.setImplementation(column.getValue());
        }
      }

      connector.setConverterDataFormat(converterDataFormat);
      connectors.getConnectors().add(connector);
    }

    return connectors;
  }

  /**
   * Returns a list of all data formats.
   * 
   * @return
   * @throws Exception
   * @throws JDOMException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  @WebMethod(action = "getAllDataFormats")
  public DataFormatList getAllDataFormats() throws Exception {
    DataFormatList dataFormats = new DataFormatList();
    String statement = "SELECT * FROM dataformats ORDER BY id ASC";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data formats
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      DataFormat dataFormat = new DataFormat();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          dataFormat.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          dataFormat.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("implementation")) {
          dataFormat.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("xml_schema")) {
          dataFormat.setXmlSchema(column.getValue());
        }
      }

      dataFormats.getDataFormats().add(dataFormat);
    }

    return dataFormats;
  }

  /**
   * Returns a list of all data converters.
   * 
   * @return
   * @throws Exception
   * @throws JDOMException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  @WebMethod(action = "getAllConverters")
  public ConverterList getAllConverters() throws Exception {
    ConverterList dataConverters = new ConverterList();
    String statement = "";
    String result;

    statement += "SELECT converters.id, converters.name, converters.implementation, ";
    statement += "  t2.name AS connector_dataformat_name, t2.implementation AS connector_dataformat_implementation, ";
    statement += "  t3.name AS workflow_dataformat_name, t3.implementation AS workflow_dataformat_implementation ";
    statement += "FROM converters ";
    statement += "LEFT JOIN dataformats AS t2 ON (converters.connector_dataformat_id = t2.id) ";
    statement += "LEFT JOIN dataformats AS t3 ON (converters.workflow_dataformat_id = t3.id) ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data converters
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      Converter converter = new Converter();
      DataFormat connectorDataFormat = new DataFormat();
      DataFormat workflowDataFormat = new DataFormat();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          converter.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          converter.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("implementation")) {
          converter.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("connector_dataformat_name")) {
          connectorDataFormat.setName(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("connector_dataformat_implementation")) {
          connectorDataFormat.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("workflow_dataformat_name")) {
          workflowDataFormat.setName(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("workflow_dataformat_implementation")) {
          workflowDataFormat.setImplementation(column.getValue());
        }
      }

      converter.setConnectorDataFormat(connectorDataFormat);
      converter.setWorkflowDataFormat(workflowDataFormat);
      dataConverters.getConverters().add(converter);
    }

    return dataConverters;
  }

  /**
   * Returns the names of all available languages.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAllLanguages")
  public StringList getAllLanguages() throws Exception {
    StringList languages = new StringList();

    String statement = "SELECT name FROM languages ORDER BY name ASC";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    languages.getItems().addAll(this.getColumnValuesFromResult(result, "name"));

    return languages;
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

    String statement = "SELECT properties_description FROM connectors";
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

    String statement = "SELECT properties_description FROM connectors";
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

    String statement = "SELECT properties_description FROM connectors";
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
   * Returns the xml schema the data format.
   * 
   * @param dataSource
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataFormatSchema")
  public String getDataFormatSchema(String dataFormatName) throws Exception {
    String xmlSchema = null;
    String statement = "";
    String result = null;

    statement += "SELECT xml_schema FROM dataformats ";
    statement += "WHERE name LIKE '" + dataFormatName + "'";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    xmlSchema = this.getColumnValuesFromResult(result, "xml_schema").get(0);

    return xmlSchema;
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

    statementDescription = this
        .getColumnValuesFromResult(result, "statement_description").get(0);

    return statementDescription;
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
    statement += "INNER JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "INNER JOIN converters ON (converters.connector_dataformat_id = connectors.converter_dataformat_id OR converters.workflow_dataformat_id = connectors.converter_dataformat_id) ";
    statement += "INNER JOIN dataformats ON (converters.connector_dataformat_id = dataformats.id OR converters.workflow_dataformat_id = dataformats.id) ";
    statement += "WHERE datasources.id = " + dataSource.getId();

    result = dataSourceService.retrieveData(rmDataSource, statement);

    stringList.getItems().addAll(getColumnValuesFromResult(result, "name"));

    // remove duplicates
    HashSet<String> h = new HashSet<String>(stringList.getItems());
    stringList.getItems().clear();
    stringList.getItems().addAll(h);

    // remove data format of the data source itself
    stringList.getItems().remove(
        dataSource.getConnector().getConverterDataFormat().getName());

    // sort items
    Collections.sort(stringList.getItems());

    return stringList;
  }

  /**
   * Adds a data source.
   * 
   * @param dataSource
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addDataSource")
  public boolean addDataSource(DataSource dataSource) throws Exception {
    boolean successful = false;
    String statement = null;
    String policy = dataSource.getPropertiesDescription();
    String connectorProperties = dataSource.getConnectorPropertiesDescription();

    // set empty xml policy value
    if (policy.equals("")) {
      policy = "NULL";
    } else {
      policy = "'" + policy + "'";
    }

    // set empty xml connector properties description value
    if (connectorProperties.equals("")) {
      dataSource.setConnectorPropertiesDescription("NULL");
    } else {
      dataSource.setConnectorPropertiesDescription("'" + connectorProperties + "'");
    }

    // build SQL insert statement
    statement = "INSERT INTO datasources (logical_name, interface_description, connector_properties_description, properties_description, security_username, security_password) VALUES (";
    statement += "'" + dataSource.getName() + "', ";
    statement += "'" + dataSource.getAddress() + "', ";
    statement += "'"
        + String.format(connectorPropertiesDescription, dataSource.getType(),
            dataSource.getSubType(), dataSource.getLanguage(), dataSource.getConnector()
                .getConverterDataFormat().getName()) + "', ";
    statement += "" + policy + ", ";
    statement += "'" + dataSource.getAuthentication().getUser() + "', ";
    statement += "'" + dataSource.getAuthentication().getPassword() + "'";
    statement += ")";

    // add data source
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Adds a connector.
   * 
   * @param connector
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addConnector")
  public boolean addConnector(Connector connector) throws Exception {
    boolean successful = false;
    String statement = null;
    String propertiesDescription = connector.getPropertiesDescription();

    // set empty xml schema value
    if (propertiesDescription.equals("")) {
      propertiesDescription = "NULL";
    } else {
      propertiesDescription = "'" + propertiesDescription + "'";
    }

    // build SQL insert statement
    statement = "INSERT INTO connectors (name, implementation, properties_description) VALUES (";
    statement += "'" + connector.getName() + "', ";
    statement += "'" + connector.getImplementation() + "', ";
    statement += propertiesDescription;
    statement += ")";

    // add connector
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Adds a data format.
   * 
   * @param dataFormat
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addDataFormat")
  public boolean addDataFormat(DataFormat dataFormat) throws Exception {
    boolean successful = false;
    String statement = null;
    String xmlSchema = dataFormat.getXmlSchema();

    // set empty xml schema value
    if (xmlSchema.equals("")) {
      xmlSchema = "NULL";
    } else {
      xmlSchema = "'" + xmlSchema + "'";
    }

    // build SQL insert statement
    statement = "INSERT INTO dataformats (name, implementation, xml_schema) VALUES (";
    statement += "'" + dataFormat.getName() + "', ";
    statement += "'" + dataFormat.getImplementation() + "', ";
    statement += xmlSchema;
    statement += ")";

    // add data format
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Adds a converter.
   * 
   * @param Converter
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addConverter")
  public boolean addConverter(Converter converter) throws Exception {
    boolean successful = false;
    String statement = null;

    // build SQL insert statement
    statement = "INSERT INTO converters (name, implementation, connector_dataformat_id, workflow_dataformat_id) VALUES (";
    statement += "'" + converter.getName() + "', ";
    statement += "'" + converter.getImplementation() + "', ";
    statement += "(SELECT id FROM dataformats WHERE name LIKE '"
        + converter.getConnectorDataFormat().getName() + "'), ";
    statement += "(SELECT id FROM dataformats WHERE name LIKE '"
        + converter.getWorkflowDataFormat().getName() + "')";
    statement += ")";

    // add converter
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
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
    boolean successful = false;
    String statement = null;
    String propertiesDescription = dataSource.getPropertiesDescription();

    // set empty xml policy value
    if (propertiesDescription.equals("")) {
      propertiesDescription = "NULL";
    } else {
      propertiesDescription = "'" + propertiesDescription + "'";
    }

    // build SQL update statement
    statement = "UPDATE datasources SET ";
    statement += "logical_name='" + dataSource.getName() + "',";
    statement += "interface_description='"
        + dataSource.getAddress().replace("\\", "\\\\") + "',";

    if (!dataSource.getConnectorPropertiesDescription().equals("")) {
      statement += "connector_properties_description='"
          + dataSource.getConnectorPropertiesDescription() + "', ";
    } else {
      statement += "connector_properties_description='"
          + String.format(connectorPropertiesDescription, dataSource.getType(),
              dataSource.getSubType(), dataSource.getLanguage(), dataSource
                  .getConnector().getConverterDataFormat().getName()) + "', ";
    }

    statement += "security_username='" + dataSource.getAuthentication().getUser() + "',";
    statement += "security_password='" + dataSource.getAuthentication().getPassword()
        + "',";
    statement += "properties_description=" + propertiesDescription + "";
    statement += " WHERE id=" + dataSource.getId();

    // update data source
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Updates a connector.
   * 
   * @param connector
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateConnector")
  public boolean updateConnector(Connector connector) throws Exception {
    boolean successful = false;
    String statement = "";

    // build SQL update statement
    statement += "UPDATE connectors SET ";
    statement += "name='" + connector.getName() + "',";
    statement += "implementation='" + connector.getImplementation() + "',";

    if (!connector.getPropertiesDescription().equals("")) {
      statement += "properties_description='" + connector.getPropertiesDescription()
          + "' ";
    } else {
      statement += "properties_description='"
          + String.format(propertiesDescription, connector.getType(), connector
              .getSubType(), connector.getLanguage(), connector.getConverterDataFormat()
              .getName()) + "' ";
    }

    statement += " WHERE id=" + connector.getId();

    // update connector
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Updates a data format.
   * 
   * @param dataFormat
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateDataFormat")
  public boolean updateDataFormat(DataFormat dataFormat) throws Exception {
    boolean successful = false;
    String statement = "";
    String xmlSchema = dataFormat.getXmlSchema();

    // set xml_schema value
    if (xmlSchema.equals("")) {
      xmlSchema = "NULL";
    } else {
      xmlSchema = "'" + xmlSchema + "'";
    }

    // build SQL update statement
    statement += "UPDATE dataformats SET ";
    statement += "name='" + dataFormat.getName() + "',";
    statement += "implementation='" + dataFormat.getImplementation() + "',";
    statement += "xml_schema=" + xmlSchema + "";
    statement += " WHERE id=" + dataFormat.getId();

    // update data format
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Updates a converter.
   * 
   * @param converter
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateConverter")
  public boolean updateConverter(Converter converter) throws Exception {
    boolean successful = false;
    String statement = "";

    // build SQL update statement
    statement += "UPDATE converters SET ";
    statement += "name='" + converter.getName() + "',";
    statement += "implementation='" + converter.getImplementation() + "',";
    statement += "connector_dataformat_id=(SELECT id FROM dataformats WHERE name LIKE '"
        + converter.getConnectorDataFormat().getName() + "'), ";
    statement += "workflow_dataformat_id=(SELECT id FROM dataformats WHERE name LIKE '"
        + converter.getWorkflowDataFormat().getName() + "')";
    statement += " WHERE id=" + converter.getId();

    // update connector
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Deletes a data source.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteDataSource")
  public boolean deleteDataSource(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM datasources WHERE id = " + String.valueOf(id);

    // delete data source
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Deletes a connector.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteConnector")
  public boolean deleteConnector(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM connectors WHERE id = " + String.valueOf(id);

    // delete connector
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Deletes a data format.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteDataFormat")
  public boolean deleteDataFormat(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM dataformats WHERE id = " + String.valueOf(id);

    // delete data format
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Deletes a converter.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteConverter")
  public boolean deleteConverter(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM converters WHERE id = " + String.valueOf(id);

    // delete converter
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
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
    final String sqlFile = "sql/resource_management.sql";

    boolean successful = false;
    String fileLine = null;
    String statement = "";
    ArrayList<String> statements = new ArrayList<String>();
    BufferedReader bufferedFileReader = null;
    InputStream fileStream = null;

    fileStream = this.getClass().getClassLoader().getResourceAsStream(sqlFile);
    bufferedFileReader = new BufferedReader(new InputStreamReader(fileStream));

    // TODO: optimize statement recognition and allow empty lines between function
    // declarations

    // build one-line statements
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
      successful = dataSourceService.executeStatement(rmDataSource, statements.get(i));

      if (!successful) {
        break;
      }
    }

    // drop tables on failure
    if (!successful) {
      dataSourceService
          .executeStatement(rmDataSource, "DROP TABLE IF EXISTS datasources");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS connectors_converters");
      dataSourceService.executeStatement(rmDataSource, "DROP TABLE IF EXISTS connectors");
      dataSourceService.executeStatement(rmDataSource, "DROP TABLE IF EXISTS converters");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS datacontainers");
      dataSourceService
          .executeStatement(rmDataSource, "DROP TABLE IF EXISTS dataformats");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS statement_types");
      dataSourceService.executeStatement(rmDataSource, "DROP TABLE IF EXISTS languages");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS getDataSourceXMLProperty(text, xml)");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS getConnectorXMLProperty(text, xml)");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS setDataSourceConnector()");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS updateDataSourceConnectors()");
      dataSourceService.executeStatement(rmDataSource, "DROP LANGUAGE plpgsql");
    }

    return successful;
  }

  /**
   * Dummy function in order to get unused data types generated on wsimport.
   * 
   * This is a workaround, these data types should be based in org.simpl.core.discovery,
   * but they are not added to the JAXB context by the SIMPL Core data source web service
   * client when they are not packaged with org.simpl.core.webservices. But if they are
   * packaged with org.simpl.core.webservices, the data types from
   * org.simpl.resource.management.data are not added.
   * 
   * @param lateBinding
   * @param strategy
   */
  public void dummy(LateBinding lateBinding, Strategy strategy) {
    // do nothing
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
      Connector connector = new Connector();
      DataFormat converterDataFormat = new DataFormat();
      Authentication authentication = new Authentication();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          dataSource.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("logical_name")) {
          dataSource.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("interface_description")) {
          dataSource.setAddress(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("connector_name")) {
          connector.setName(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("connector_implementation")) {
          connector.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("connector_properties_description")) {
          dataSource.setConnectorPropertiesDescription(column.getValue());
          dataSource
              .setType(this.getFromPropertiesDescription("type", column.getValue()));
          dataSource.setSubType(this.getFromPropertiesDescription("subType",
              column.getValue()));
          dataSource.setLanguage(this.getFromPropertiesDescription("language",
              column.getValue()));

          converterDataFormat.setName(this.getFromPropertiesDescription("dataFormatName",
              column.getValue()));
        } else if (column.getAttribute("name").getValue().equals("security_username")) {
          authentication.setUser(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("security_password")) {
          authentication.setPassword(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("properties_description")) {
          dataSource.setPropertiesDescription(column.getValue());
        }
      }

      connector.setConverterDataFormat(converterDataFormat);
      dataSource.setConnector(connector);
      dataSource.setAuthentication(authentication);
      dataSources.add(dataSource);
    }

    return dataSources;
  }

  /**
   * Creates Connector objects from a RDB data format result.
   * 
   * @param result
   * @return
   * @throws IOException
   * @throws JDOMException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private ArrayList<Connector> getConnectorsFromResult(String result)
      throws JDOMException, IOException {
    ArrayList<Connector> connectors = new ArrayList<Connector>();

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      Connector connector = new Connector();
      DataFormat converterDataFormat = new DataFormat();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          connector.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          connector.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("implementation")) {
          connector.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("properties_description")) {
          connector.setPropertiesDescription(column.getValue());

          connector.setType(this.getFromPropertiesDescription("type", column.getValue()));
          connector.setSubType(this.getFromPropertiesDescription("subType",
              column.getValue()));
          connector.setLanguage(this.getFromPropertiesDescription("language",
              column.getValue()));

          converterDataFormat.setName(this.getFromPropertiesDescription("dataFormatName",
              column.getValue()));
        } else if (column.getAttribute("name").getValue().equals("dataformat_name")) {
          converterDataFormat.setName(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("dataformat_implementation")) {
          converterDataFormat.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("xml_schema")) {
          converterDataFormat.setXmlSchema(column.getValue());
        }
      }

      connector.setConverterDataFormat(converterDataFormat);
      connectors.add(connector);
    }

    return connectors;
  }

  /**
   * Creates Converter objects from a RDB data format result.
   * 
   * @param result
   * @return
   * @throws IOException
   * @throws JDOMException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private ArrayList<Converter> getConvertersFromResult(String result)
      throws JDOMException, IOException {
    ArrayList<Converter> converters = new ArrayList<Converter>();

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      Converter converter = new Converter();
      DataFormat connectorDataFormat = new DataFormat();
      DataFormat workflowDataFormat = new DataFormat();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          converter.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          converter.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("implementation")) {
          converter.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("connector_dataformat_name")) {
          connectorDataFormat.setName(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("workflow_dataformat_name")) {
          workflowDataFormat.setName(column.getValue());
        }
      }

      converter.setConnectorDataFormat(connectorDataFormat);
      converter.setWorkflowDataFormat(workflowDataFormat);
      converters.add(converter);
    }

    return converters;
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