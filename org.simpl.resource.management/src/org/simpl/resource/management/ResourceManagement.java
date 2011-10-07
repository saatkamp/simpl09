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
import org.simpl.resource.management.data.DataConverter;
import org.simpl.resource.management.data.DataConverterList;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.DataSourceList;
import org.simpl.resource.management.data.DataTransformationService;
import org.simpl.resource.management.data.DataTransformationServiceList;
import org.simpl.resource.management.data.StringList;
import org.simpl.resource.management.db.DataSourceService;
import org.xml.sax.InputSource;

/**
 * <b>Purpose:</b>The Resource Management stores data sources and other resources for the
 * SIMPL framework. This class represents the resource management and the web service
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
 * @version $Id: ResourceManagement.java 1769 2011-02-12 14:33:51Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 * @author Michael Schneidt <michael.schneidt@arcor.de>
 */
@WebService(name = "ResourceManagement")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ResourceManagement {
  /**
   * Default value for the datasources.connector_properties_description.
   */
  final static String defaultConnectorPropertiesDescription = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><connector_properties_description xmlns=\"http://org.simpl.resource.management/datasources/connector_properties_description\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd \"><type>%s</type><subType>%s</subType><language>%s</language><dataFormat>%s</dataFormat></connector_properties_description>";

  /**
   * Default value for the connectors.properties_description.
   */
  final static String defaultPropertiesDescription = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <properties_description xmlns=\"http://org.simpl.resource.management/connectors/properties_description\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://org.simpl.resource.management/connectors/properties_description connectors.xsd \"><type>%s</type><subType>%s</subType><language>%s</language><dataFormat>%s</dataFormat></properties_description>";

  // for data retrieval
  DataSourceService dataSourceService = new DataSourceService();
  String dataSourceType = null;
  DataSource rmDataSource = ResourceManagementConfig.getInstance().getDataSource();

  // dataconverters relation fields
  String[] dataconvertersFields = { "id", "name", "input_datatype", "output_datatype",
      "workflow_dataformat", "direction_output_workflow", "direction_workflow_input",
      "implementation", "xml_schema" };

  // connectors fields
  String[] connectorsFields = { "id", "name", "input_datatype", "output_datatype",
      "implementation", "properties_description" };

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
    statement += "SELECT datasources.*, " + this.getUniqueFieldNames(connectorsFields, "connectors") + ", " + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
    statement += "WHERE getDataSourceXMLProperty('type', datasources.connector_properties_description) = '" + type + "' ";
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
    statement += "SELECT datasources.*, " + this.getUniqueFieldNames(connectorsFields, "connectors") + ", " + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
    statement += "WHERE getDataSourceXMLProperty('type', connector_properties_description) = '" + type + "' ";
    statement += "AND getDataSourceXMLProperty('subType', connector_properties_description) LIKE '" + subType + "' ";
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
    statement += "SELECT datasources.*, " + this.getUniqueFieldNames(connectorsFields, "connectors") + ", " + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
    statement += "SELECT datasources.*, " + this.getUniqueFieldNames(connectorsFields, "connectors") + ", " + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
    statement += "SELECT connectors.*, " + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM connectors ";
    statement += "LEFT JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
    statement += "WHERE connectors.id = " + id;

    // retrieve connnector
    result = dataSourceService.retrieveData(rmDataSource, statement);
    connectors = this.getConnectorsFromResult(result, "");

    connectorList.getConnectors().addAll(connectors);

    if (connectors.size() > 0) {
      resultConnector = connectors.get(0);
    }

    return resultConnector;
  }

  /**
   * Returns a data converter by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataConverterById")
  public DataConverter getDataConverterById(@WebParam(name = "id") int id)
      throws Exception {
    DataConverter resultDataConverter = new DataConverter();
    DataConverterList dataConverterList = new DataConverterList();
    ArrayList<DataConverter> dataConverters = null;
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT *";
    statement += "FROM dataconverters ";
    statement += "WHERE dataconverters.id = " + id;

    // retrieve data converter
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataConverters = this.getDataConvertersFromResult(result, "");

    dataConverterList.getDataConverters().addAll(dataConverters);

    if (dataConverters.size() > 0) {
      resultDataConverter = dataConverters.get(0);
    }

    return resultDataConverter;
  }

  /**
   * Returns a data transformation service by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataTransformationServiceById")
  public DataTransformationService getDataTransformationServiceById(
      @WebParam(name = "id") int id) throws Exception {
    DataTransformationService resultDataTransformationService = new DataTransformationService();
    DataTransformationServiceList dataTransformationServiceList = new DataTransformationServiceList();
    ArrayList<DataTransformationService> dataTransformationServices = null;
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT * ";
    statement += "FROM datatransformationservices ";
    statement += "WHERE datatransformationservices.id = " + id;

    // retrieve data transformation service
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataTransformationServices = this.getDataTransformationServicesFromResult(result);

    dataTransformationServiceList.getDataTransformationServices().addAll(
        dataTransformationServices);

    if (dataTransformationServices.size() > 0) {
      resultDataTransformationService = dataTransformationServices.get(0);
    }

    return resultDataTransformationService;
  }

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
  
    statement += "SELECT datasources.*, " + this.getUniqueFieldNames(connectorsFields, "connectors") + ", " + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM datasources ";
    statement += "LEFT JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
   * Returns a list of all data source connectors.
   * 
   * @return
   * @throws Exception
   * @throws JDOMException
   * @throws IOException
   */
  @WebMethod(action = "getAllConnectors")
  public ConnectorList getAllConnectors() throws Exception {
    ConnectorList connectors = new ConnectorList();
    String statement = "";
    String result;

    statement += "SELECT connectors.*, " + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM connectors ";
    statement += "LEFT JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    connectors.getConnectors().addAll(this.getConnectorsFromResult(result, ""));

    return connectors;
  }

  /**
   * Returns a list of all data converters.
   * 
   * @return
   * @throws Exception
   * @throws JDOMException
   * @throws IOException
   */
  @WebMethod(action = "getAllDataConverters")
  public DataConverterList getAllDataConverters() throws Exception {
    DataConverterList dataConverters = new DataConverterList();
    String statement = "SELECT * FROM dataconverters ORDER BY id ASC";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    dataConverters.getDataConverters().addAll(this.getDataConvertersFromResult(result, ""));

    return dataConverters;
  }

  /**
   * Returns a list of all data transformation services.
   * 
   * @return
   * @throws Exception
   * @throws JDOMException
   * @throws IOException
   */
  @WebMethod(action = "getAllDataTransformationServices")
  public DataTransformationServiceList getAllDataTransformationServices()
      throws Exception {
    DataTransformationServiceList dataTransformationServices = new DataTransformationServiceList();
    String statement = "";
    String result;

    statement += "SELECT * ";
    statement += "FROM datatransformationservices ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    dataTransformationServices.getDataTransformationServices().addAll(this.getDataTransformationServicesFromResult(result));

    return dataTransformationServices;
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
      String type = this.getFromPropertiesDescription("type", propertyDescription);
      
      if (type != null) {
        stringList.getItems().add(type);
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

      if (typeFromResult != null && typeFromResult.equals(type)) {
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

      if (typeFromResult != null && typeFromResult.equals(subType)) {
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
   * Returns the xml schema of a workflow data format.
   * 
   * @param workflowDataFormat
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataFormatSchema")
  public String getDataFormatSchema(String dataFormat) throws Exception {
    String xmlSchema = null;
    String statement = "";
    String result = null;

    statement += "SELECT xml_schema FROM dataconverters ";
    statement += "WHERE workflow_dataformat LIKE '" + dataFormat + "'";

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
   * Returns a list of workflow data formats that are available for the given data source.
   * The result depends on the available connectors and their used data converters.
   * 
   * @param dataSource
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getSupportedDataFormats")
  public StringList getSupportedDataFormats(DataSource dataSource) throws Exception {
    StringList stringList = new StringList();
    String statement = "";
    String result = null;

    statement += "SELECT dataconverters.workflow_dataformat ";
    statement += "FROM connectors ";
    statement += "INNER JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
    statement += "WHERE getConnectorXMLProperty('type', connectors.properties_description) = '"
        + dataSource.getType() + "' ";
    statement += "AND getConnectorXMLProperty('subType', connectors.properties_description) = '"
        + dataSource.getSubType() + "' ";
    statement += "AND getConnectorXMLProperty('language', connectors.properties_description) =  '"
        + dataSource.getLanguage() + "' ";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    stringList.getItems().addAll(getColumnValuesFromResult(result, "workflow_dataformat"));
    
    // sort items
    Collections.sort(stringList.getItems());

    return stringList;
  }

  /**
   * Returns a list of workflow data formats that can be converted to and from the
   * workflow data format of the given data source.
   * 
   * @param dataSource
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getSupportedConvertDataFormats")
  public StringList getSupportedConvertDataFormats(DataSource dataSource)
      throws Exception {
    StringList stringList = new StringList();
    String statement = "";
    String result = null;

    statement += "SELECT replace(datatransformationservices.workflow_dataformat || datatransformationservices.connector_dataformat, dataconverters.workflow_dataformat, '') AS dataformat ";
    statement += "FROM datasources ";
    statement += "INNER JOIN connectors ON (datasources.connector_id = connectors.id) ";
    statement += "INNER JOIN dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
    statement += "INNER JOIN datatransformationservices ON ((datatransformationservices.direction_connector_workflow = 'true' AND datatransformationservices.connector_dataformat = dataconverters.workflow_dataformat) OR (datatransformationservices.direction_workflow_connector = 'true' AND datatransformationservices.workflow_dataformat = dataconverters.workflow_dataformat)) ";
    statement += "WHERE datasources.id = " + dataSource.getId();

    result = dataSourceService.retrieveData(rmDataSource, statement);

    stringList.getItems().addAll(getColumnValuesFromResult(result, "dataformat"));

    // remove duplicates
    HashSet<String> h = new HashSet<String>(stringList.getItems());
    stringList.getItems().clear();
    stringList.getItems().addAll(h);

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
    String propertiesDescription = dataSource.getPropertiesDescription();

    // handle empty properties description value
    if (propertiesDescription == null || propertiesDescription.equals("")) {
      propertiesDescription = "NULL";
    } else {
      propertiesDescription = "'" + propertiesDescription + "'";
    }

    // build SQL insert statement
    statement = "INSERT INTO datasources (logical_name, security_username, security_password, interface_description, properties_description, connector_properties_description) VALUES (";
    statement += "'" + dataSource.getName() + "', ";
    statement += "'" + dataSource.getAuthentication().getUser() + "', ";
    statement += "'" + dataSource.getAuthentication().getPassword() + "', ";
    statement += "'" + dataSource.getAddress() + "', ";
    statement += propertiesDescription + ", ";
    
      if (dataSource.getConnectorPropertiesDescription() == null 
          || dataSource.getConnectorPropertiesDescription().equals("")) {
        statement += "NULL";
      } else if (!dataSource.getConnectorPropertiesDescription().equals("")) {
        statement += "'" + dataSource.getConnectorPropertiesDescription() + "'";
      } else {
        statement += "'" + String.format(defaultConnectorPropertiesDescription, 
            dataSource.getType(),
            dataSource.getSubType(), 
            dataSource.getLanguage(), 
            dataSource.getConnector().getDataConverter().getWorkflowDataFormat()) + "',";
      }
    
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
    
    // handle empty properties description value
    if (propertiesDescription == null || propertiesDescription.equals("")) {
      propertiesDescription = "NULL";
    } else {
      propertiesDescription = "'" + propertiesDescription + "'";
    }
    
    // build SQL insert statement
    statement = "INSERT INTO connectors (name, input_datatype, output_datatype, implementation, properties_description) VALUES (";
    statement += "'" + connector.getName() + "', ";
    statement += "'" + connector.getInputDataType() + "', ";
    statement += "'" + connector.getOutputDataType() + "', ";
    statement += "'" + connector.getImplementation() + "', ";
    
    if (propertiesDescription.equals("NULL")) {
      statement += "'" + String.format(defaultPropertiesDescription, 
          connector.getType(), 
          connector.getSubType(), 
          connector.getLanguage(), 
          connector.getDataConverter().getWorkflowDataFormat()) + "'";
    } else {
      statement += propertiesDescription;
    }
    
    statement += ")";

    // add connector
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Adds a data converter.
   * 
   * @param dataConverter
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addDataConverter")
  public boolean addDataConverter(DataConverter dataConverter) throws Exception {
    boolean successful = false;
    String statement = null;
    String xmlSchema = dataConverter.getXmlSchema();

    // handle empty xml_schema value
    if (xmlSchema == null || xmlSchema.equals("")) {
      xmlSchema = "NULL";
    } else {
      xmlSchema = "'" + xmlSchema + "'";
    }

    // build SQL insert statement
    statement = "INSERT INTO dataconverters (name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation, xml_schema) VALUES (";
    statement += "'" + dataConverter.getName() + "', ";
    statement += "'" + dataConverter.getInputDataType() + "', ";
    statement += "'" + dataConverter.getOutputDataType() + "', ";
    statement += "'" + dataConverter.getWorkflowDataFormat() + "', ";
    statement += "'" + dataConverter.getDirectionOutputWorkflow() + "', ";
    statement += "'" + dataConverter.getDirectionWorkflowInput() + "', ";
    statement += "'" + dataConverter.getImplementation() + "', ";
    statement += xmlSchema;
    statement += ")";
    
    // add data format
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Adds a data transformation service.
   * 
   * @param DataTransformationService
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addDataTransformationService")
  public boolean addDataTransformationService(
      DataTransformationService dataTransformationService) throws Exception {
    boolean successful = false;
    String statement = null;

    // build SQL insert statement
    statement = "INSERT INTO datatransformationservices (name, connector_dataformat, workflow_dataformat, direction_connector_workflow, direction_workflow_connector, implementation) VALUES (";
    statement += "'" + dataTransformationService.getName() + "', ";
    statement += "'" + dataTransformationService.getConnectorDataFormat() + "', ";
    statement += "'" + dataTransformationService.getWorkflowDataFormat() + "', ";
    statement += "'" + dataTransformationService.getDirectionConnectorWorkflow() + "', ";
    statement += "'" + dataTransformationService.getDirectionWorkflowConnector() + "', ";
    statement += "'" + dataTransformationService.getImplementation() + "' ";
    statement += ")";

    // add data transformation service
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

    // handle empty propertiesDescription value
    if (propertiesDescription == null || propertiesDescription.equals("")) {
      propertiesDescription = "NULL";
    } else {
      propertiesDescription = "'" + propertiesDescription + "'";
    }

    // build SQL update statement
    statement = "UPDATE datasources SET ";
    statement += "logical_name='" + dataSource.getName() + "', ";
    statement += "security_username='" + dataSource.getAuthentication().getUser() + "', ";
    statement += "security_password='" + dataSource.getAuthentication().getPassword() + "', ";
    statement += "interface_description='" + dataSource.getAddress().replace("\\", "\\\\") + "', ";
    statement += "properties_description=" + propertiesDescription + ", ";
    
    if (!dataSource.getConnectorPropertiesDescription().equals("")) {
      statement += "connector_properties_description='"
          + dataSource.getConnectorPropertiesDescription() + "'";
    } else {
      statement += "connector_properties_description='"
          + String.format(defaultConnectorPropertiesDescription, 
              dataSource.getType(),
              dataSource.getSubType(), 
              dataSource.getLanguage(), 
              dataSource.getConnector().getDataConverter().getWorkflowDataFormat()) + "'";
    }
    
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
    statement += "input_datatype='" + connector.getInputDataType() + "',";
    statement += "output_datatype='" + connector.getOutputDataType() + "',";
    statement += "implementation='" + connector.getImplementation() + "',";

    if (!connector.getPropertiesDescription().equals("")) {
      statement += "properties_description='" + connector.getPropertiesDescription()
          + "' ";
    } else {
      statement += "properties_description='"
          + String.format(defaultPropertiesDescription, 
              connector.getType(), 
              connector.getSubType(), 
              connector.getLanguage(), 
              connector.getDataConverter().getWorkflowDataFormat()) + "' ";
    }

    statement += " WHERE id=" + connector.getId();

    // update connector
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Updates a data converter.
   * 
   * @param dataConverter
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateDataConverter")
  public boolean updateDataConverter(DataConverter dataConverter) throws Exception {
    boolean successful = false;
    String statement = "";
    String xmlSchema = dataConverter.getXmlSchema();

    // set xml_schema value
    if (xmlSchema == null || xmlSchema.equals("")) {
      xmlSchema = "NULL";
    } else {
      xmlSchema = "'" + xmlSchema + "'";
    }

    // build SQL update statement
    statement += "UPDATE dataconverters SET ";
    statement += "name='" + dataConverter.getName() + "', ";
    statement += "input_datatype='" + dataConverter.getInputDataType() + "', ";
    statement += "output_datatype='" + dataConverter.getOutputDataType() + "', ";
    statement += "workflow_dataformat='" + dataConverter.getWorkflowDataFormat() + "', ";
    statement += "direction_output_workflow='" + dataConverter.getDirectionOutputWorkflow() + "', ";
    statement += "direction_workflow_input='" + dataConverter.getDirectionWorkflowInput() + "', ";
    statement += "implementation='" + dataConverter.getImplementation() + "', ";
    statement += "xml_schema=" + xmlSchema;
    statement += " WHERE id=" + dataConverter.getId();

    // update data converter
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Updates a data transformation service.
   * 
   * @param dataTransformationService
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateDataTransformationService")
  public boolean updateDataTransformationService(
      DataTransformationService dataTransformationService) throws Exception {
    boolean successful = false;
    String statement = "";

    // build SQL update statement
    statement += "UPDATE datatransformationservices SET ";
    statement += "name='" + dataTransformationService.getName() + "', ";
    statement += "connector_dataformat='" + dataTransformationService.getConnectorDataFormat() + "', ";
    statement += "workflow_dataformat='"  + dataTransformationService.getWorkflowDataFormat() + "', ";
    statement += "direction_connector_workflow='" + dataTransformationService.getDirectionConnectorWorkflow() + "', ";
    statement += "direction_workflow_connector='" + dataTransformationService.getDirectionWorkflowConnector() + "', ";
    statement += "implementation='" + dataTransformationService.getImplementation() + "'";
    statement += " WHERE id=" + dataTransformationService.getId();

    // update data transformation service
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
   * Deletes a data converter.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteDataConverter")
  public boolean deleteDataConverter(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM dataconverters WHERE id = " + String.valueOf(id);

    // delete data converter
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Deletes a data transformation service.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteDataTransformationService")
  public boolean deleteDataTransformationService(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM datatransformationservices WHERE id = "
        + String.valueOf(id);

    // delete data transformation service
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
      dataSourceService.executeStatement(rmDataSource, "DROP TABLE IF EXISTS connectors");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS datatransformationservices");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS datacontainers");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS dataconverters");
      dataSourceService.executeStatement(rmDataSource,
          "DROP TABLE IF EXISTS statement_types");
      dataSourceService.executeStatement(rmDataSource, "DROP TABLE IF EXISTS languages");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS getDataSourceXMLProperty(text, xml, tid)");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS getConnectorXMLProperty(text, xml)");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS setDataSourceConnector()");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS updateDataSourceConnectors()");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS setConnectorDataConverter()");
      dataSourceService.executeStatement(rmDataSource,
          "DROP FUNCTION IF EXISTS updateConnectorDataConverters()");
      dataSourceService.executeStatement(rmDataSource, "DROP LANGUAGE plpgsql");
    }

    return successful;
  }

  /**
   * Returns a string of unique comma separated fields for a SQL statement.
   * 
   * @param array
   * @param relation
   * @return
   */
  private String getUniqueFieldNames(String[] array, String relation) {
    String out = "";
    String delim = ",";

    for (int i = 0; i < array.length; i++) {
      if (i != 0) {
        out += delim;
      }
      out += relation + "." + array[i] + " AS " + relation + "_" + array[i];
    }
    
    return out;
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
    ArrayList<Connector> connectors = this.getConnectorsFromResult(result, "connectors");
    ArrayList<DataConverter> dataConverters = this.getDataConvertersFromResult(result, "dataconverters");
    
    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (int i = 0; i < rows.size(); i++) {
      Element row = rows.get(i);
      DataSource dataSource = new DataSource();
      Connector connector = connectors.get(i);
      DataConverter dataConverter = dataConverters.get(i);
      Authentication authentication = new Authentication();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          dataSource.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("logical_name")) {
          dataSource.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("security_username")) {
          authentication.setUser(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("security_password")) {
          authentication.setPassword(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("interface_description")) {
          dataSource.setAddress(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("properties_description")) {
          dataSource.setPropertiesDescription(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("connector_properties_description")) {
          dataSource.setConnectorPropertiesDescription(column.getValue());
          dataSource.setType(this.getFromPropertiesDescription("type", column.getValue()));
          dataSource.setSubType(this.getFromPropertiesDescription("subType", column.getValue()));
          dataSource.setLanguage(this.getFromPropertiesDescription("language", column.getValue()));
        }
      }

      connector.setDataConverter(dataConverter);
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
   * @param relation
   * @return
   * @throws IOException
   * @throws JDOMException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private ArrayList<Connector> getConnectorsFromResult(String result, String relation)
      throws JDOMException, IOException {
    ArrayList<Connector> connectors = new ArrayList<Connector>();
    ArrayList<DataConverter> dataConverters = new ArrayList<DataConverter>();
    String prefix;
    
    if (relation == null || relation.equals("")) {
      prefix = "";
    } else {
      prefix = relation + "_";
    }
    
    dataConverters = this.getDataConvertersFromResult(result, "dataconverters");
    
    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (int i = 0; i < rows.size(); i++) {
      Element row = rows.get(i);
      Connector connector = new Connector();
      DataConverter dataConverter = dataConverters.get(i);
      List<Element> columns = row.getChildren("column");
      
      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals(prefix + "id")) {
          connector.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "name")) {
          connector.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "input_datatype")) {
          connector.setInputDataType(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "output_datatype")) {
          connector.setOutputDataType(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "implementation")) {
          connector.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "properties_description")) {
          connector.setPropertiesDescription(column.getValue());
          connector.setType(this.getFromPropertiesDescription("type", column.getValue()));
          connector.setSubType(this.getFromPropertiesDescription("subType", column.getValue()));
          connector.setLanguage(this.getFromPropertiesDescription("language", column.getValue()));
          
          // create a potential data converter from the properties description
          if (dataConverter == null) {
            dataConverter = new DataConverter();
            dataConverter.setWorkflowDataFormat(this.getFromPropertiesDescription("dataFormat", column.getValue()));
          }
        }
      }

      connector.setDataConverter(dataConverter);
      connectors.add(connector);
    }

    return connectors;
  }

  /**
   * Creates DataConverter objects from a RDB data format result.
   * 
   * @param result
   * @param relation
   * @return
   * @throws IOException
   * @throws JDOMException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private ArrayList<DataConverter> getDataConvertersFromResult(String result, String relation)
      throws JDOMException, IOException {
    ArrayList<DataConverter> dataConverters = new ArrayList<DataConverter>();
    String prefix;
    
    if (relation == null || relation.equals("")) {
      prefix = "";
    } else {
      prefix = relation + "_";
    }
    
    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      DataConverter dataConverter = new DataConverter();
      List<Element> columns = row.getChildren("column");
      
      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals(prefix + "id")) {
          dataConverter.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "name")) {
          dataConverter.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "input_datatype")) {
          dataConverter.setInputDataType(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "output_datatype")) {
          dataConverter.setOutputDataType(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "workflow_dataformat")) {
          dataConverter.setWorkflowDataFormat(column.getValue());
        } else if (column.getAttribute("name").getValue().equals(prefix + "direction_output_workflow")) {
          dataConverter.setDirectionOutputWorkflow(column.getValue().trim());
        } else if (column.getAttribute("name").getValue().equals(prefix + "direction_workflow_input")) {
          dataConverter.setDirectionWorkflowInput(column.getValue().trim());
        } else if (column.getAttribute("name").getValue().equals(prefix + "implementation")) {
          dataConverter.setImplementation(column.getValue().trim());
        } else if (column.getAttribute("name").getValue().equals(prefix + "xml_schema")) {
          dataConverter.setXmlSchema(column.getValue());
        }
      }

      dataConverters.add(dataConverter);
    }

    return dataConverters;
  }

  /**
   * Creates DataTransformationService objects from a RDB data format result.
   * 
   * @param result
   * @return
   * @throws IOException
   * @throws JDOMException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private ArrayList<DataTransformationService> getDataTransformationServicesFromResult(
      String result) throws JDOMException, IOException {
    ArrayList<DataTransformationService> dataTransformationServices = new ArrayList<DataTransformationService>();

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      DataTransformationService dataTransformationService = new DataTransformationService();
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          dataTransformationService.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          dataTransformationService.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("connector_dataformat")) {
          dataTransformationService.setConnectorDataFormat(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("workflow_dataformat")) {
          dataTransformationService.setWorkflowDataFormat(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("direction_connector_workflow")) {
          dataTransformationService.setDirectionConnectorWorkflow(column.getValue().trim());
        } else if (column.getAttribute("name").getValue().equals("direction_workflow_connector")) {
          dataTransformationService.setDirectionWorkflowConnector(column.getValue().trim());
        } else if (column.getAttribute("name").getValue().equals("implementation")) {
          dataTransformationService.setImplementation(column.getValue());
        }
      }

      dataTransformationServices.add(dataTransformationService);
    }

    return dataTransformationServices;
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
    
    // Workaround: sometimes the matcher returns null as string!
    if (value != null && value.equals("null")) {
      value = null;
    }

    return value;
  }
}