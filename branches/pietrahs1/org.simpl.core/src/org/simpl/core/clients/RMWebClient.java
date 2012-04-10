package org.simpl.core.clients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.simpl.core.services.SIMPLCoreAdministrationService;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.ConnectorList;
import org.simpl.resource.management.data.DataContainer;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.DataSourceList;
import org.simpl.resource.management.data.DataTransformationService;
import org.simpl.resource.management.data.DataTransformationServiceList;

/**
 * <b>Purpose:</b>Provides access to and holds data from the SIMPL Resource Management
 * about plug-ins that can be used with the SIMPL Core. <br>
 * <b>Description:</b>Uses the ResourceManagementClient to talk to the resource management
 * web service interface. The web service address of the Resource Management is retrieved
 * from the SIMPLCore embedded Derby database.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: SIMPLResourceManagement.java 1718 2010-11-17 18:25:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RMWebClient {
  /**
   * List of registered connector plug-ins.
   */
  private List<String> connectorPlugins = new ArrayList<String>();

  /**
   * List of registered data format plug-ins.
   */
  private List<String> dataFormatPlugins = new ArrayList<String>();

  /**
   * List of registered data transformation services.
   */
  private List<String> dataTransformationServices = new ArrayList<String>();

  /**
   * Maps data formats to the connectors that can use it.
   */
  private HashMap<String, ArrayList<String>> dataFormatMapping = new HashMap<String, ArrayList<String>>();

  /**
   * Maps data transformation services to the connectors that can use it.
   */
  private HashMap<String, ArrayList<String>> dataTransformationServiceMapping = new HashMap<String, ArrayList<String>>();

  /**
   * The data format schemas.
   */
  private HashMap<String, String> dataFormatSchemas = new HashMap<String, String>();
  
  /**
   * The resource management web service.
   */
  private ResourceManagement resourceManagement = null;

  /**
   * RMWebClient singleton instance.
   */
  private static final RMWebClient instance = new RMWebClient();

  /**
   * Initialize data.
   */
  private RMWebClient() {
    this.reload();
  }

  /**
   * Returns the {@link RMWebClient} singleton instance.
   * 
   * @return
   */
  public static RMWebClient getInstance() {
    return RMWebClient.instance;
  }

  /**
   * Returns a list of registered ConnectorPlugins. The list contains full qualified names
   * of ConnectorPlugin classes.
   * 
   * @return List of ConnectorPlugins
   */
  public List<String> getConnectorPlugins() {
    return connectorPlugins;
  }

  /**
   * Returns a list of registered DataFormatPlugins. The list contains full qualified
   * names of DataFormatPlugin classes.
   * 
   * @return List of DataFormatPlugins
   */
  public List<String> getDataFormatPlugins() {
    return dataFormatPlugins;
  }

  /**
   * Returns a list of registered DataTransformationServices. The list contains full qualified names
   * of DataTransformationService classes.
   * 
   * @return List of DataTransformationServices
   */
  public List<String> getDataTransformationServices() {
    return dataTransformationServices;
  }

  /**
   * Returns a map of data formats and their supported connectors, key and values are full
   * qualified class names.
   * 
   * @return data format mapping
   */
  public HashMap<String, ArrayList<String>> getDataFormatMapping() {
    return dataFormatMapping;
  }

  /**
   * Returns the schema of a data format.
   * 
   * @param dataFormat
   * @return data format schema
   */
  public String getDataFormatSchema(String dataFormat) {
    String dataFormatSchema = null;
    
    if (!dataFormatSchemas.containsKey(dataFormat)) {
      try {
        dataFormatSchema = resourceManagement.getDataFormatSchema(dataFormat);
        dataFormatSchemas.put(dataFormat, dataFormatSchema);
      } catch (Exception_Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }  
    } else {
      dataFormatSchema = dataFormatSchemas.get(dataFormat);
    }
    
    return dataFormatSchema;
  }
  
  /**
   * Returns a map of data transformation services and their supported connectors, key and values are full
   * qualified class names.
   * 
   * @return data transformation service mapping
   */
  public HashMap<String, ArrayList<String>> getDataTransformationServiceMapping() {
    return dataTransformationServiceMapping;
  }

  /**
   * Returns a data source by its name.
   * 
   * @return
   */
  public DataSource getDataSourceByName(String dataSourceName) {
    DataSource dataSource = null;

    try {
      dataSource = this.resourceManagement.getDataSourceByName(dataSourceName);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSource;
  }

  /**
   * Returns all data sources.
   * 
   * @return
   */
  public DataSourceList getAllDataSources() {
    DataSourceList dataSources = null;

    try {
      dataSources = this.resourceManagement.getAllDataSources();
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataSources;
  }

  /**
   * Returns a data container by its name.
   * 
   * @return
   */
  public DataContainer getDataContainerByName(String name) {
    DataContainer dataContainer = null;

    try {
      dataContainer = this.resourceManagement.getDataContainerByName(name);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dataContainer;
  }

  /**
   * Returns a data container reference type by a data source id.
   * 
   * @return
   */
  public String getDataContainerReferenceTypeByDataSourceId(String id,
      boolean basetype) {
    String result = null;

    try {
      result = this.resourceManagement
          .getDataContainerReferenceTypeByDataSourceId(id, basetype);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return result;
  }
  
  /**
   * Reloads the data from the Resource Management.
   */
  public void reload() {
    ConnectorList connectors = new ConnectorList();
    DataTransformationServiceList dataTransformationServices = new DataTransformationServiceList();
    String resourceManagementAddress = null;

    // retrieve the resource management address from the internal embedded derby simplDB
    LinkedHashMap<String, String> settings = SIMPLCoreAdministrationService.getInstance()
        .getService().loadSettings("RESOURCEMANAGEMENT", "SETTINGS", "lastSaved");
    resourceManagementAddress = settings.get("ADDRESS");

    try {
      if (resourceManagementAddress != null) {
        resourceManagement = ResourceManagementClient
            .getService(resourceManagementAddress);

        if (resourceManagement != null) {
          connectors = resourceManagement.getAllConnectors();
          dataTransformationServices = resourceManagement.getAllDataTransformationServices();
        } else {
          connectors = new ConnectorList();
          dataTransformationServices = new DataTransformationServiceList();
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (connectors.getConnectors().size() > 0) {
      for (Connector connector : connectors.getConnectors()) {
        // retrieve connector plug-ins
        this.connectorPlugins.add(connector.getImplementation());

        // retrieve data format plug-ins
        if (!this.dataFormatPlugins.contains(connector.getDataConverter()
            .getImplementation())) {
          this.dataFormatPlugins.add(connector.getDataConverter()
              .getImplementation());
        }

        // retrieve data format mapping
        if (this.dataFormatMapping.containsKey(connector.getDataConverter()
            .getImplementation())) {
          this.dataFormatMapping.get(
              connector.getDataConverter().getImplementation()).add(
              connector.getImplementation());
        } else {
          this.dataFormatMapping.put(connector.getDataConverter()
              .getImplementation(),
              new ArrayList<String>(Arrays.asList(connector.getImplementation())));
        }
      }
    }

    // retrieve converter plug-ins
    if (dataTransformationServices.getDataTransformationServices().size() > 0) {
      for (DataTransformationService dataTransformationService : dataTransformationServices.getDataTransformationServices()) {
        this.dataTransformationServices.add(dataTransformationService.getImplementation());

        // retrieve data transformation service mapping
        for (Connector connector : connectors.getConnectors()) {
          if (connector.getDataConverter().getWorkflowDataFormat().equals(dataTransformationService.getInputDataFormat()) && dataTransformationService.getDirectionOutputInput().equals("true")
              || connector.getDataConverter().getWorkflowDataFormat().equals(dataTransformationService.getOutputDataFormat()) && dataTransformationService.getDirectionInputOutput().equals("true")) {
            if (this.dataTransformationServiceMapping.containsKey(dataTransformationService.getImplementation())) {
              this.dataTransformationServiceMapping.get(dataTransformationService.getImplementation()).add(
                  connector.getImplementation());
            } else {
              this.dataTransformationServiceMapping.put(dataTransformationService.getImplementation(),
                  new ArrayList<String>(Arrays.asList(connector.getImplementation())));
            }            
          }
        }
      }
    }
    
    // clear data format schemas
    dataFormatSchemas.clear();
  }
}