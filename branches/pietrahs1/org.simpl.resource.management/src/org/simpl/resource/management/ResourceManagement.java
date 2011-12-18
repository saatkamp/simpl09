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
import org.simpl.resource.management.data.TypeDefinition;
import org.simpl.resource.management.data.TypeDefinitionList;
import org.simpl.resource.management.data.DataConverter;
import org.simpl.resource.management.data.DataConverterList;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.DataSourceList;
import org.simpl.resource.management.data.DataTransformationService;
import org.simpl.resource.management.data.DataTransformationServiceList;
import org.simpl.resource.management.data.StrategyPlugin;
import org.simpl.resource.management.data.StrategyPluginList;
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
  final static String defaultConnectorPropertiesDescription = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><connector_properties_description xmlns=\"http://org.simpl.resource.management/datasources/connector_properties_description\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://org.simpl.resource.management/datasources/connector_properties_description datasources.xsd \"><type>%s</type><subType>%s</subType><language>%s</language><apiType>%s</apiType><driverName>%s</driverName><addressPrefix>%s</addressPrefix><dataFormat>%s</dataFormat></connector_properties_description>";

  /**
   * Default value for the connectors.properties_description.
   */
  final static String defaultPropertiesDescription = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <properties_description xmlns=\"http://org.simpl.resource.management/connectors/properties_description\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://org.simpl.resource.management/connectors/properties_description connectors.xsd \"><type>%s</type><subType>%s</subType><language>%s</language><apiType>%s</apiType><dataFormat>%s</dataFormat></properties_description>";

  // for data retrieval
  DataSourceService dataSourceService = new DataSourceService();
  String dataSourceType = null;
  DataSource rmDataSource = ResourceManagementConfig.getInstance().getDataSource();

  // dataconverters relation fields
  String[] dataconvertersFields = { "id", "name", "input_datatype", "output_datatype",
      "workflow_dataformat", "direction_output_workflow", "direction_workflow_input",
      "implementation"};

  // connectors fields
  String[] connectorsFields = { "id", "name", "input_datatype", "output_datatype",
      "implementation", "properties_description" };

  /**
   * The information for accessing the PostgreSQL database will be returned
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getConfig")
  public DataSource getConfig() throws Exception {
    return this.rmDataSource;
  }

  /**
   * The user has entered new information about accessing the PostgreSQL database these
   * new information will now be stored
   * 
   * @param rmDataSource
   * @return
   * @throws Exception
   */
  @WebMethod(action = "setConfig")
  public Boolean setConfig(@WebParam(name = "rmDataSource") DataSource rmDataSource) throws Exception {
    Boolean success = false;
    success = ResourceManagementConfig.getInstance().updateConfig(rmDataSource);
    this.rmDataSource = rmDataSource;
    
    return success;
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
    statement += "SELECT datasources.*, "
        + this.getUniqueFieldNames(connectorsFields, "connectors") + ", "
        + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM simpl_resources.datasources ";
    statement += "LEFT JOIN simpl_resources.connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
    statement += "SELECT datasources.*, "
        + this.getUniqueFieldNames(connectorsFields, "connectors") + ", "
        + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM simpl_resources.datasources ";
    statement += "LEFT JOIN simpl_resources.connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
    statement += "SELECT datasources.*, "
        + this.getUniqueFieldNames(connectorsFields, "connectors") + ", "
        + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM simpl_resources.datasources ";
    statement += "LEFT JOIN simpl_resources.connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
    statement += "SELECT datasources.*, "
        + this.getUniqueFieldNames(connectorsFields, "connectors") + ", "
        + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM simpl_resources.datasources ";
    statement += "LEFT JOIN simpl_resources.connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
    statement += "SELECT connectors.*, "
        + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM simpl_resources.connectors ";
    statement += "LEFT JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
    statement += "FROM simpl_resources.dataconverters ";
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
    statement += "FROM simpl_resources.datatransformationservices ";
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
   * Returns a strategy plug-in by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getStrategyPluginById")
  public StrategyPlugin getStrategyPluginById(@WebParam(name = "id") int id)
      throws Exception {
    StrategyPlugin resultStrategyPlugin = new StrategyPlugin();
    StrategyPluginList strategyPluginList = new StrategyPluginList();
    ArrayList<StrategyPlugin> strategyPlugins = null;
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT * ";
    statement += "FROM simpl_resources.strategyplugins ";
    statement += "WHERE strategyplugins.id = " + id;

    // retrieve strategy plug-ins
    result = dataSourceService.retrieveData(rmDataSource, statement);
    strategyPlugins = this.getStrategyPluginsFromResult(result);

    strategyPluginList.getStrategyPlugins().addAll(strategyPlugins);

    if (strategyPlugins.size() > 0) {
      resultStrategyPlugin = strategyPlugins.get(0);
    }

    return resultStrategyPlugin;
  }
  
  /**
   * Returns a data container reference type by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataContainerReferenceTypeById")
  public TypeDefinition getDataContainerReferenceTypeById(@WebParam(name = "id") int id)
      throws Exception {
    TypeDefinition resultDataContainerReferenceType = new TypeDefinition();
    TypeDefinitionList dataContainerReferenceTypeList = new TypeDefinitionList();
    ArrayList<TypeDefinition> dataContainerReferenceTypes = null;
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT * ";
    statement += "FROM simpl_definitions.datacontainer_reference_types ";
    statement += "WHERE datacontainer_reference_types.id = " + id;

    // retrieve data container reference types
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataContainerReferenceTypes = this.getTypeDefinitionsFromResult(result);

    dataContainerReferenceTypeList.getTypeDefinitions().addAll(
        dataContainerReferenceTypes);

    if (dataContainerReferenceTypes.size() > 0) {
      resultDataContainerReferenceType = dataContainerReferenceTypes.get(0);
    }

    return resultDataContainerReferenceType;
  }

  /**
   * Returns a data container reference type by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getDataSourceReferenceTypeById")
  public TypeDefinition getDataSourceReferenceTypeById(@WebParam(name = "id") int id)
      throws Exception {
    TypeDefinition resultDataSourceReferenceType = new TypeDefinition();
    TypeDefinitionList dataSourceReferenceTypeList = new TypeDefinitionList();
    ArrayList<TypeDefinition> dataSourceReferenceTypes = null;
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT * ";
    statement += "FROM simpl_definitions.datasource_reference_types ";
    statement += "WHERE datasource_reference_types.id = " + id;

    // retrieve data source reference types
    result = dataSourceService.retrieveData(rmDataSource, statement);
    dataSourceReferenceTypes = this.getTypeDefinitionsFromResult(result);

    dataSourceReferenceTypeList.getTypeDefinitions().addAll(dataSourceReferenceTypes);

    if (dataSourceReferenceTypes.size() > 0) {
      resultDataSourceReferenceType = dataSourceReferenceTypes.get(0);
    }

    return resultDataSourceReferenceType;
  }

  /**
   * Returns a workflow data format type by id.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getWorkflowDataFormatTypeById")
  public TypeDefinition getWorkflowDataFormatTypeById(@WebParam(name = "id") int id)
      throws Exception {
    TypeDefinition resultWorkflowDataFormatType = new TypeDefinition();
    TypeDefinitionList workflowDataFormatTypeList = new TypeDefinitionList();
    ArrayList<TypeDefinition> workflowDataFormatTypes = null;
    String statement = "";
    String result = null;

    // build select statement
    statement += "SELECT * ";
    statement += "FROM simpl_definitions.workflow_dataformat_types ";
    statement += "WHERE workflow_dataformat_types.id = " + id;

    // retrieve workflow data format types
    result = dataSourceService.retrieveData(rmDataSource, statement);
    workflowDataFormatTypes = this.getTypeDefinitionsFromResult(result);

    workflowDataFormatTypeList.getTypeDefinitions().addAll(
        workflowDataFormatTypes);

    if (workflowDataFormatTypes.size() > 0) {
      resultWorkflowDataFormatType = workflowDataFormatTypes.get(0);
    }

    return resultWorkflowDataFormatType;
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

    statement += "SELECT datasources.*, "
        + this.getUniqueFieldNames(connectorsFields, "connectors") + ", "
        + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM simpl_resources.datasources ";
    statement += "LEFT JOIN simpl_resources.connectors ON (datasources.connector_id = connectors.id) ";
    statement += "LEFT JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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

    statement += "SELECT connectors.*, "
        + this.getUniqueFieldNames(dataconvertersFields, "dataconverters") + " ";
    statement += "FROM simpl_resources.connectors ";
    statement += "LEFT JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
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
    String statement = "";

    statement += "SELECT * FROM simpl_resources.dataconverters ";
    statement += "ORDER BY id ASC";

    String result = dataSourceService.retrieveData(rmDataSource, statement);

    dataConverters.getDataConverters().addAll(
        this.getDataConvertersFromResult(result, ""));

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
    String result = null;

    statement += "SELECT * ";
    statement += "FROM simpl_resources.datatransformationservices ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    dataTransformationServices.getDataTransformationServices().addAll(
        this.getDataTransformationServicesFromResult(result));

    return dataTransformationServices;
  }

  /**
   * Returns a list of strategies.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAllStrategyPlugins")
  public StrategyPluginList getAllStrategyPlugins() throws Exception {
    StrategyPluginList strategyList = new StrategyPluginList();
    ArrayList<StrategyPlugin> strategiePlugins = null;
    String statement = "";
    String result = null;

    statement += "SELECT * ";
    statement += "FROM simpl_resources.strategyplugins ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    // extract strategy plug-ins from result
    strategiePlugins = this.getStrategyPluginsFromResult(result);

    strategyList.getStrategyPlugins().addAll(strategiePlugins);

    return strategyList;
  }
  
  /**
   * Returns a list of data container reference types.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAllDataContainerReferenceTypes")
  public TypeDefinitionList getAllDataContainerReferenceTypes() throws Exception {
    TypeDefinitionList typeDefinitionList = new TypeDefinitionList();
    ArrayList<TypeDefinition> typeDefinitions = null;
    String statement = "";
    String result = null;

    statement += "SELECT * ";
    statement += "FROM simpl_definitions.datacontainer_reference_types ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    // extract type definitions from result
    typeDefinitions = this.getTypeDefinitionsFromResult(result);

    typeDefinitionList.getTypeDefinitions().addAll(typeDefinitions);

    return typeDefinitionList;
  }

  /**
   * Returns a list of data source reference types.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAllDataSourceReferenceTypes")
  public TypeDefinitionList getAllDataSourceReferenceTypes() throws Exception {
    TypeDefinitionList typeDefinitionList = new TypeDefinitionList();
    ArrayList<TypeDefinition> typeDefinitions = null;

    String statement = "";
    String result = null;

    statement += "SELECT * ";
    statement += "FROM simpl_definitions.datasource_reference_types ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    // extract type definitions from result
    typeDefinitions = this.getTypeDefinitionsFromResult(result);

    typeDefinitionList.getTypeDefinitions().addAll(typeDefinitions);

    return typeDefinitionList;
  }
  
  /**
   * Returns a list of workflow data format types.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAllWorkflowDataFormatTypes")
  public TypeDefinitionList getAllWorkflowDataFormatTypes() throws Exception {
    TypeDefinitionList typeDefinitionList = new TypeDefinitionList();
    ArrayList<TypeDefinition> typeDefinitions = null;
    String statement = "";
    String result = null;

    statement += "SELECT * ";
    statement += "FROM simpl_definitions.workflow_dataformat_types ";
    statement += "ORDER BY id ASC";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    // extract type definitions from result
    typeDefinitions = this.getTypeDefinitionsFromResult(result);

    typeDefinitionList.getTypeDefinitions().addAll(typeDefinitions);

    return typeDefinitionList;
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

    String statement = "SELECT name FROM simpl_definitions.languages ORDER BY name ASC";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    languages.getItems().addAll(this.getColumnValuesFromResult(result, "name"));

    return languages;
  }

  /**
   * Returns a xml schema including all type definitions.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAllTypeDefinitionsSchema")
  public String getAllTypeDefinitionsSchema() throws Exception {
    String schema = null;
    String statement = null;
    String result = null;
    StringList typeDefinitions = new StringList();

    // datacontainer_reference_types
    statement = "SELECT * FROM simpl_definitions.datacontainer_reference_types ORDER BY name ASC";
    result = dataSourceService.retrieveData(rmDataSource, statement);
    typeDefinitions.getItems().addAll(this.getColumnValuesFromResult(result, "xsd_type"));

    // datasource_reference_types
    statement = "SELECT * FROM simpl_definitions.datasource_reference_types ORDER BY name ASC";
    result = dataSourceService.retrieveData(rmDataSource, statement);
    typeDefinitions.getItems().addAll(this.getColumnValuesFromResult(result, "xsd_type"));
    
    // datasource_reference_types
    statement = "SELECT * FROM simpl_definitions.workflow_dataformat_types ORDER BY name ASC";
    result = dataSourceService.retrieveData(rmDataSource, statement);
    typeDefinitions.getItems().addAll(this.getColumnValuesFromResult(result, "xsd_type"));

    // build XSD schema
    String schemaOpenTag = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema targetNamespace=\"http://www.example.org/simpl\" xmlns:simpl=\"http://www.example.org/simpl\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
    StringBuffer schemaContent = new StringBuffer();
    String schemaCloseTag = "</xsd:schema>";

    for (String complexType : typeDefinitions.getItems()) {
      schemaContent.append(complexType);
    }
    schema = schemaOpenTag + schemaContent.toString() + schemaCloseTag;

    return schema;
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

    String statement = "SELECT properties_description FROM simpl_resources.connectors";
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

    String statement = "SELECT properties_description FROM simpl_resources.connectors";
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

    String statement = "SELECT properties_description FROM simpl_resources.connectors";
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
   * Returns a list of API types types.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getAPITypes")
  public StringList getAPITypes() throws Exception {
    StringList stringList = new StringList();
    ArrayList<String> propertiesDescriptions = null;

    String statement = "SELECT properties_description FROM simpl_resources.connectors";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    // extract properties description from result
    propertiesDescriptions = this.getColumnValuesFromResult(result,
        "properties_description");

    // extract types from the properties description
    for (String propertyDescription : propertiesDescriptions) {
      String apiType = this.getFromPropertiesDescription("apiType", propertyDescription);

      if (apiType != null) {
        stringList.getItems().add(apiType);
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
   * Returns the names of all available workflow data format types.
   * 
   * @return
   * @throws Exception
   */
  @WebMethod(action = "getWorkflowDataFormatTypeNames")
  public StringList getWorkflowDataFormatTypeNames() throws Exception {
    StringList names = new StringList();

    String statement = "SELECT name FROM simpl_definitions.workflow_dataformat_types ORDER BY name ASC";
    String result = dataSourceService.retrieveData(rmDataSource, statement);

    names.getItems().addAll(this.getColumnValuesFromResult(result, "name"));

    return names;
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
    String complexType = null;
	String xmlSchema = null;
    String statement = "";
    String result = null;

    //two types are needed: the specified data format and the associated base type
    statement += "SELECT xsd_type FROM simpl_definitions.workflow_dataformat_types ";
    statement += "WHERE name LIKE '" + dataFormat + "' OR name LIKE 'DataFormat'" ;

    result = dataSourceService.retrieveData(rmDataSource, statement);

    
    complexType = this.getColumnValuesFromResult(result, "xsd_type").get(0) + this.getColumnValuesFromResult(result, "xsd_type").get(1);
    
    // build XSD schema
    String schemaOpenTag = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsd:schema targetNamespace=\"http://www.example.org/simpl\" xmlns:simpl=\"http://www.example.org/simpl\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
    String schemaCloseTag = "</xsd:schema>";

    xmlSchema = schemaOpenTag + complexType + schemaCloseTag;

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
    statement += "FROM simpl_definitions.languages ";
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
    statement += "FROM simpl_resources.connectors ";
    statement += "INNER JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
    statement += "WHERE getConnectorXMLProperty('type', connectors.properties_description) = '"
        + dataSource.getType() + "' ";
    statement += "AND getConnectorXMLProperty('subType', connectors.properties_description) = '"
        + dataSource.getSubType() + "' ";
    statement += "AND getConnectorXMLProperty('language', connectors.properties_description) =  '"
        + dataSource.getLanguage() + "' ";

    result = dataSourceService.retrieveData(rmDataSource, statement);

    stringList.getItems()
        .addAll(getColumnValuesFromResult(result, "workflow_dataformat"));

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
    statement += "FROM simpl_resources.datasources ";
    statement += "INNER JOIN simpl_resources.connectors ON (datasources.connector_id = connectors.id) ";
    statement += "INNER JOIN simpl_resources.dataconverters ON (connectors.dataconverter_id = dataconverters.id) ";
    statement += "INNER JOIN simpl_resources.datatransformationservices ON ((datatransformationservices.direction_connector_workflow = 'true' AND datatransformationservices.connector_dataformat = dataconverters.workflow_dataformat) OR (datatransformationservices.direction_workflow_connector = 'true' AND datatransformationservices.workflow_dataformat = dataconverters.workflow_dataformat)) ";
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
    String connectorPropertiesDescription = dataSource
        .getConnectorPropertiesDescription();

    // handle empty properties description value
    if (propertiesDescription == null || propertiesDescription.equals("")) {
      propertiesDescription = "NULL";
    } else {
      propertiesDescription = "'" + propertiesDescription + "'";
    }

    // handle empty connector properties description value
    if (connectorPropertiesDescription == null
        || connectorPropertiesDescription.equals("")) {
      connectorPropertiesDescription = "NULL";
    } else {
      connectorPropertiesDescription = "'" + connectorPropertiesDescription + "'";
    }

    // build SQL insert statement
    statement = "INSERT INTO simpl_resources.datasources (logical_name, security_username, security_password, interface_description, properties_description, connector_properties_description) VALUES (";
    statement += "'" + dataSource.getName() + "', ";
    statement += "'" + dataSource.getAuthentication().getUser() + "', ";
    statement += "'" + dataSource.getAuthentication().getPassword() + "', ";
    statement += "'" + dataSource.getAddress() + "', ";
    statement += propertiesDescription + ", ";

    if (connectorPropertiesDescription.equals("NULL")) {
      statement += "'"
          + String.format(defaultConnectorPropertiesDescription, dataSource.getType(),
              dataSource.getSubType(), dataSource.getLanguage(), dataSource.getAPIType(), 
              dataSource.getDriverName(), dataSource.getAddressPrefix(), dataSource
              .getConnector().getDataConverter().getWorkflowDataFormat()) + "'";
    } else {
      statement += "'" + dataSource.getConnectorPropertiesDescription() + "'";
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
    statement = "INSERT INTO simpl_resources.connectors (name, input_datatype, output_datatype, implementation, properties_description) VALUES (";
    statement += "'" + connector.getName() + "', ";
    statement += "'" + connector.getInputDataType() + "', ";
    statement += "'" + connector.getOutputDataType() + "', ";
    statement += "'" + connector.getImplementation() + "', ";

    if (propertiesDescription.equals("NULL")) {
      statement += "'"
          + String.format(defaultPropertiesDescription, connector.getType(), connector
              .getSubType(), connector.getLanguage(), connector.getAPIType(), connector
              .getDataConverter().getWorkflowDataFormat()) + "'";
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
//    String xmlSchema = dataConverter.getXmlSchema();
//
//    // handle empty xml_schema value
//    if (xmlSchema == null || xmlSchema.equals("")) {
//      xmlSchema = "NULL";
//    } else {
//      xmlSchema = "'" + xmlSchema + "'";
//    }

    // build SQL insert statement
    statement = "INSERT INTO simpl_resources.dataconverters (name, input_datatype, output_datatype, workflow_dataformat, direction_output_workflow, direction_workflow_input, implementation) VALUES (";
    statement += "'" + dataConverter.getName() + "', ";
    statement += "'" + dataConverter.getInputDataType() + "', ";
    statement += "'" + dataConverter.getOutputDataType() + "', ";
    statement += "'" + dataConverter.getWorkflowDataFormat() + "', ";
    statement += "'" + dataConverter.getDirectionOutputWorkflow() + "', ";
    statement += "'" + dataConverter.getDirectionWorkflowInput() + "', ";
    statement += "'" + dataConverter.getImplementation() + "'";
    statement += ")";

    // add data format
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Adds a data transformation service.
   * 
   * @param dataTransformationService
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addDataTransformationService")
  public boolean addDataTransformationService(
      DataTransformationService dataTransformationService) throws Exception {
    boolean successful = false;
    String statement = null;

    // build SQL insert statement
    statement = "INSERT INTO simpl_resources.datatransformationservices (name, connector_dataformat, workflow_dataformat, direction_connector_workflow, direction_workflow_connector, implementation) VALUES (";
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
   * Adds a strategy plug-in.
   * 
   * @param strategyPlugin
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addStrategyPlugin")
  public boolean addStrategyPlugin(StrategyPlugin strategyPlugin) throws Exception {
    boolean successful = false;
    String statement = null;

    // build SQL insert statement
    statement = "INSERT INTO simpl_resources.strategyplugins (name, implementation) VALUES (";
    statement += "'" + strategyPlugin.getName() + "', ";
    statement += "'" + strategyPlugin.getImplementation() + "' ";
    statement += ")";

    // add strategy plug-in
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }
  
  /**
   * Adds a data container reference type.
   * 
   * @param typeDefinition
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addDataContainerReferenceType")
  public boolean addDataContainerReferenceType(TypeDefinition typeDefinition)
      throws Exception {
    boolean successful = false;
    String statement = null;

    // build SQL insert statement
    statement = "INSERT INTO simpl_definitions.datacontainer_reference_types (name, xsd_type) VALUES (";
    statement += "'" + typeDefinition.getName() + "', ";
    statement += "'" + typeDefinition.getXsdType() + "' ";
    statement += ")";

    // add type definition
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Adds a data source reference type.
   * 
   * @param TypeDefinition
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addDataSourceReferenceType")
  public boolean addDataSourceReferenceType(TypeDefinition typeDefinition)
      throws Exception {
    boolean successful = false;
    String statement = null;

    // build SQL insert statement
    statement = "INSERT INTO simpl_definitions.datasource_reference_types (name, xsd_type) VALUES (";
    statement += "'" + typeDefinition.getName() + "', ";
    statement += "'" + typeDefinition.getXsdType() + "' ";
    statement += ")";

    // add type definition
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }
  
  /**
   * Adds a workflow data format type.
   * 
   * @param typeDefinition
   * @return
   * @throws Exception
   */
  @WebMethod(action = "addWorkflowDataFormatType")
  public boolean addWorkflowDataFormatType(TypeDefinition typeDefinition)
      throws Exception {
    boolean successful = false;
    String statement = null;

    // build SQL insert statement
    statement = "INSERT INTO simpl_definitions.workflow_dataformat_types (name, xsd_type) VALUES (";
    statement += "'" + typeDefinition.getName() + "', ";
    statement += "'" + typeDefinition.getXsdType() + "'";
    statement += ")";

    // add type definition
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
    statement = "UPDATE simpl_resources.datasources SET ";
    statement += "logical_name='" + dataSource.getName() + "', ";
    statement += "security_username='" + dataSource.getAuthentication().getUser() + "', ";
    statement += "security_password='" + dataSource.getAuthentication().getPassword()
        + "', ";
    statement += "interface_description='"
        + dataSource.getAddress().replace("\\", "\\\\") + "', ";
    statement += "properties_description=" + propertiesDescription + ", ";

    if (!dataSource.getConnectorPropertiesDescription().equals("")) {
      statement += "connector_properties_description='"
          + dataSource.getConnectorPropertiesDescription() + "'";
    } else {
      statement += "connector_properties_description='"
          + String.format(defaultConnectorPropertiesDescription, dataSource.getType(),
              dataSource.getSubType(), dataSource.getLanguage(), dataSource.getAPIType(), 
              dataSource.getDriverName(), dataSource.getAddressPrefix(), dataSource
              .getConnector().getDataConverter().getWorkflowDataFormat()) + "'";
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
    statement += "UPDATE simpl_resources.connectors SET ";
    statement += "name='" + connector.getName() + "',";
    statement += "input_datatype='" + connector.getInputDataType() + "',";
    statement += "output_datatype='" + connector.getOutputDataType() + "',";
    statement += "implementation='" + connector.getImplementation() + "',";

    if (!connector.getPropertiesDescription().equals("")) {
      statement += "properties_description='" + connector.getPropertiesDescription()
          + "' ";
    } else {
      statement += "properties_description='"
          + String.format(defaultPropertiesDescription, connector.getType(), connector
              .getSubType(), connector.getLanguage(), connector.getAPIType(), connector
              .getDataConverter().getWorkflowDataFormat()) + "' ";
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
//    String xmlSchema = dataConverter.getXmlSchema();
//
//    // set xml_schema value
//    if (xmlSchema == null || xmlSchema.equals("")) {
//      xmlSchema = "NULL";
//    } else {
//      xmlSchema = "'" + xmlSchema + "'";
//    }

    // build SQL update statement
    statement += "UPDATE simpl_resources.dataconverters SET ";
    statement += "name='" + dataConverter.getName() + "', ";
    statement += "input_datatype='" + dataConverter.getInputDataType() + "', ";
    statement += "output_datatype='" + dataConverter.getOutputDataType() + "', ";
    statement += "workflow_dataformat='" + dataConverter.getWorkflowDataFormat() + "', ";
    statement += "direction_output_workflow='"
        + dataConverter.getDirectionOutputWorkflow() + "', ";
    statement += "direction_workflow_input='" + dataConverter.getDirectionWorkflowInput()
        + "', ";
    statement += "implementation='" + dataConverter.getImplementation() + "'";
//    statement += "xml_schema=" + xmlSchema;
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
    statement += "UPDATE simpl_resources.datatransformationservices SET ";
    statement += "name='" + dataTransformationService.getName() + "', ";
    statement += "connector_dataformat='"
        + dataTransformationService.getConnectorDataFormat() + "', ";
    statement += "workflow_dataformat='"
        + dataTransformationService.getWorkflowDataFormat() + "', ";
    statement += "direction_connector_workflow='"
        + dataTransformationService.getDirectionConnectorWorkflow() + "', ";
    statement += "direction_workflow_connector='"
        + dataTransformationService.getDirectionWorkflowConnector() + "', ";
    statement += "implementation='" + dataTransformationService.getImplementation() + "'";
    statement += " WHERE id=" + dataTransformationService.getId();

    // update data transformation service
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Updates a strategy plug-in.
   * 
   * @param strategyPlugin
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateStrategyPlugin")
  public boolean updateStrategyPlugin(StrategyPlugin strategyPlugin) throws Exception {
    boolean successful = false;
    String statement = "";

    // build SQL update statement
    statement += "UPDATE simpl_resources.strategyplugins SET ";
    statement += "name='" + strategyPlugin.getName() + "', ";
    statement += "implementation='" + strategyPlugin.getImplementation() + "'";
    statement += " WHERE id=" + strategyPlugin.getId();

    // update data transformation service
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }
  
  /**
   * Updates a data container reference type.
   * 
   * @param typeDefinition
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateDataContainerReferenceType")
  public boolean updateDataContainerReferenceType(TypeDefinition typeDefinition)
      throws Exception {
    boolean successful = false;
    String statement = "";
    String xsdType = typeDefinition.getXsdType();

    // set xsd_type value
    if (xsdType == null || xsdType.equals("")) {
      xsdType = "NULL";
    } else {
      xsdType = "'" + xsdType + "'";
    }

    // build SQL update statement
    statement += "UPDATE simpl_definitions.datacontainer_reference_types SET ";
    statement += "name='" + typeDefinition.getName() + "', ";
    statement += "xsd_type=" + xsdType + "";
    statement += " WHERE id=" + typeDefinition.getId();

    // update data container reference type
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Updates a data source reference type.
   * 
   * @param typeDefinition
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateDataSourceReferenceType")
  public boolean updateDataSourceReferenceType(TypeDefinition typeDefinition)
      throws Exception {
    boolean successful = false;
    String statement = "";
    String xsdType = typeDefinition.getXsdType();

    // set xsd_type value
    if (xsdType == null || xsdType.equals("")) {
      xsdType = "NULL";
    } else {
      xsdType = "'" + xsdType + "'";
    }

    // build SQL update statement
    statement += "UPDATE simpl_definitions.datasource_reference_types SET ";
    statement += "name='" + typeDefinition.getName() + "', ";
    statement += "xsd_type=" + xsdType + "";
    statement += " WHERE id=" + typeDefinition.getId();

    // update data source reference type
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }
  
  /**
   * Updates a workflow data format type.
   * 
   * @param typeDefinition
   * @return
   * @throws Exception
   */
  @WebMethod(action = "updateWorkflowDataFormatType")
  public boolean updateWorkflowDataFormatType(TypeDefinition typeDefinition)
      throws Exception {
    boolean successful = false;
    String statement = "";
    String xsdType = typeDefinition.getXsdType();

    // set xsd_type value
    if (xsdType == null || xsdType.equals("")) {
      xsdType = "NULL";
    } else {
      xsdType = "'" + xsdType + "'";
    }

    // build SQL update statement
    statement += "UPDATE simpl_definitions.workflow_dataformat_types SET ";
    statement += "name='" + typeDefinition.getName() + "', ";
    statement += "xsd_type=" + xsdType + "";
    statement += " WHERE id=" + typeDefinition.getId();

    // update data container reference type
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
    String statement = "DELETE FROM simpl_resources.datasources WHERE id = "
        + String.valueOf(id);

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
    String statement = "DELETE FROM simpl_resources.connectors WHERE id = "
        + String.valueOf(id);

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
    String statement = "DELETE FROM simpl_resources.dataconverters WHERE id = "
        + String.valueOf(id);

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
    String statement = "DELETE FROM simpl_resources.datatransformationservices WHERE id = "
        + String.valueOf(id);

    // delete data transformation service
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Deletes a strategy plug-in.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteStrategyPlugin")
  public boolean deleteStrategyPlugin(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM simpl_resources.strategyplugins WHERE id = "
        + String.valueOf(id);

    // delete strategy plug-in
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }
  
  /**
   * Deletes a data container reference type.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteDataContainerReferenceType")
  public boolean deleteDataContainerReferenceType(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM simpl_definitions.datacontainer_reference_types WHERE id = "
        + String.valueOf(id);

    // delete data container reference type
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Deletes a data source reference type.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteDataSourceReferenceType")
  public boolean deleteDataSourceReferenceType(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM simpl_definitions.datasource_reference_types WHERE id = "
        + String.valueOf(id);

    // delete data source reference type
    successful = dataSourceService.executeStatement(rmDataSource, statement);

    return successful;
  }

  /**
   * Deletes a workflow data format type.
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @WebMethod(action = "deleteWorkflowDataFormatType")
  public boolean deleteWorkflowDataFormatType(int id) throws Exception {
    boolean successful = false;
    String statement = "DELETE FROM simpl_definitions.workflow_dataformat_types WHERE id = "
        + String.valueOf(id);

    // delete data container reference type
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

    // clean DB on failure
    if (!successful) {
      dataSourceService.executeStatement(rmDataSource,
          "DROP SCHEMA simpl_resources CASCADE");
      dataSourceService.executeStatement(rmDataSource,
          "DROP SCHEMA simpl_definitions CASCADE");
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
    ArrayList<DataConverter> dataConverters = this.getDataConvertersFromResult(result,
        "dataconverters");

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
        } else if (column.getAttribute("name").getValue()
            .equals("properties_description")) {
          dataSource.setPropertiesDescription(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals("connector_properties_description")) {
          dataSource.setConnectorPropertiesDescription(column.getValue());
          dataSource
              .setType(this.getFromPropertiesDescription("type", column.getValue()));
          dataSource.setSubType(this.getFromPropertiesDescription("subType",
              column.getValue()));
          dataSource.setLanguage(this.getFromPropertiesDescription("language",
              column.getValue()));
          dataSource.setAPIType(this.getFromPropertiesDescription("apiType",
                  column.getValue()));
          dataSource.setDriverName(this.getFromPropertiesDescription("driverName",
                  column.getValue()));
          dataSource.setAddressPrefix(this.getFromPropertiesDescription("addressPrefix",
                  column.getValue()));
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
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "input_datatype")) {
          connector.setInputDataType(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "output_datatype")) {
          connector.setOutputDataType(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "implementation")) {
          connector.setImplementation(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "properties_description")) {
          connector.setPropertiesDescription(column.getValue());
          connector.setType(this.getFromPropertiesDescription("type", column.getValue()));
          connector.setSubType(this.getFromPropertiesDescription("subType",
              column.getValue()));
          connector.setLanguage(this.getFromPropertiesDescription("language",
              column.getValue()));
          connector.setAPIType(this.getFromPropertiesDescription("apiType",
                  column.getValue()));
          
          // create a potential data converter from the properties description
          if (dataConverter == null) {
            dataConverter = new DataConverter();
            dataConverter.setWorkflowDataFormat(this.getFromPropertiesDescription(
                "dataFormat", column.getValue()));
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
  private ArrayList<DataConverter> getDataConvertersFromResult(String result,
      String relation) throws JDOMException, IOException {
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
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "input_datatype")) {
          dataConverter.setInputDataType(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "output_datatype")) {
          dataConverter.setOutputDataType(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "workflow_dataformat")) {
          dataConverter.setWorkflowDataFormat(column.getValue());
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "direction_output_workflow")) {
          dataConverter.setDirectionOutputWorkflow(column.getValue().trim());
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "direction_workflow_input")) {
          dataConverter.setDirectionWorkflowInput(column.getValue().trim());
        } else if (column.getAttribute("name").getValue()
            .equals(prefix + "implementation")) {
          dataConverter.setImplementation(column.getValue().trim());
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
        } else if (column.getAttribute("name").getValue()
            .equals("direction_connector_workflow")) {
          dataTransformationService.setDirectionConnectorWorkflow(column.getValue()
              .trim());
        } else if (column.getAttribute("name").getValue()
            .equals("direction_workflow_connector")) {
          dataTransformationService.setDirectionWorkflowConnector(column.getValue()
              .trim());
        } else if (column.getAttribute("name").getValue().equals("implementation")) {
          dataTransformationService.setImplementation(column.getValue());
        }
      }

      dataTransformationServices.add(dataTransformationService);
    }

    return dataTransformationServices;
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
  private ArrayList<StrategyPlugin> getStrategyPluginsFromResult(String result)
      throws JDOMException, IOException {
    ArrayList<StrategyPlugin> strategies = new ArrayList<StrategyPlugin>();

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      StrategyPlugin strategy = new StrategyPlugin();

      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          strategy.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          strategy.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("implementation")) {
          strategy.setImplementation(column.getValue());
        }
      }

      strategies.add(strategy);
    }

    return strategies;
  }

  /**
   * Creates TypeDefinition objects from a RDB data format result.
   * 
   * @param result
   * @return
   * @throws IOException
   * @throws JDOMException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private ArrayList<TypeDefinition> getTypeDefinitionsFromResult(String result)
      throws JDOMException, IOException {
    ArrayList<TypeDefinition> typeDefinitions = new ArrayList<TypeDefinition>();

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      TypeDefinition typeDefinition = new TypeDefinition();

      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("id")) {
          typeDefinition.setId(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("name")) {
          typeDefinition.setName(column.getValue());
        } else if (column.getAttribute("name").getValue().equals("xsd_type")) {
          typeDefinition.setXsdType(column.getValue());
        }
      }

      typeDefinitions.add(typeDefinition);
    }

    return typeDefinitions;
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