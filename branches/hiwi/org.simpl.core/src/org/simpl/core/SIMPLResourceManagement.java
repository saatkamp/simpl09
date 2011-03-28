package org.simpl.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.simpl.resource.management.client.Connector;
import org.simpl.resource.management.client.ConnectorList;
import org.simpl.resource.management.client.Converter;
import org.simpl.resource.management.client.ConverterList;
import org.simpl.resource.management.client.DataSource;
import org.simpl.resource.management.client.DataSourceList;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;

/**
 * <b>Purpose:</b>Offers data from the SIMPL Resource Management about plug-ins that can
 * be used with the SIMPL Core.<br>
 * <b>Description:</b>Uses ResourceManagementClient to talk to the resource management web
 * service interface.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: SIMPLResourceManagement.java 1718 2010-11-17 18:25:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLResourceManagement {
  /**
   * List of registered connector plug-ins.
   */
  private List<String> connectorPlugins = new ArrayList<String>();

  /**
   * List of registered data format plug-ins.
   */
  private List<String> dataFormatPlugins = new ArrayList<String>();

  /**
   * List of registered converter plug-ins.
   */
  private List<String> converterPlugins = new ArrayList<String>();

  /**
   * Maps data formats to the connectors that can use it.
   */
  private HashMap<String, ArrayList<String>> dataFormatMapping = new HashMap<String, ArrayList<String>>();

  /**
   * Maps converter to the connectors that can use it.
   */
  private HashMap<String, ArrayList<String>> converterMapping = new HashMap<String, ArrayList<String>>();

  /**
   * SIMPLResourceManagement singleton instance.
   */
  private static final SIMPLResourceManagement instance = new SIMPLResourceManagement();

  /**
   * The resource management as web service
   */
  private ResourceManagement resourceManagement = null;

  /**
   * Initialize data.
   */
  private SIMPLResourceManagement() {
    this.reload();
  }

  /**
   * Returns the {@link SIMPLResourceManagement} singleton instance.
   * 
   * @return
   */
  public static SIMPLResourceManagement getInstance() {
    return SIMPLResourceManagement.instance;
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
   * Returns a list of registered ConverterPlugins. The list contains full qualified names
   * of ConverterPlugin classes.
   * 
   * @return List of ConverterPlugins
   */
  public List<String> getConverterPlugins() {
    return converterPlugins;
  }

  /**
   * Returns a map of data formats and their supported connectors, key and
   * values are full qualified class names.
   * 
   * @return data format mapping
   */
  public HashMap<String, ArrayList<String>> getDataFormatMapping() {
    return dataFormatMapping;
  }

  /**
   * Returns a map of converters and their supported connectors, key and values are full
   * qualified class names.
   * 
   * @return data format converter mapping
   */
  public HashMap<String, ArrayList<String>> getConverterMapping() {
    return converterMapping;
  }

  /**
   * Returns a data source by its data source descriptor.
   * 
   * @return
   */
  public DataSource getDataSourceByName(String dataSourceDescriptor) {
    DataSource dataSource = null;

    try {
      dataSource = this.resourceManagement.getDataSourceByName(dataSourceDescriptor);
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
   * Reloads the data from the Resource Management.
   */
  public void reload() {
    ConnectorList connectors = new ConnectorList();
    ConverterList converters = new ConverterList();
    String resourceManagementAddress = null;

    // retrieve the resource management address from the internal embedded derby simplDB
    LinkedHashMap<String, String> settings = SIMPLCore.getInstance()
        .administrationService()
        .loadSettings("RESOURCEMANAGEMENT", "SETTINGS", "lastSaved");
    resourceManagementAddress = settings.get("ADDRESS");
    
    try {
      if (resourceManagementAddress != null) {
        resourceManagement = ResourceManagementClient
            .getService(resourceManagementAddress);

        if (resourceManagement != null) {
          connectors = resourceManagement.getAllConnectors();
          converters = resourceManagement.getAllConverters();
        } else {
          connectors = new ConnectorList();
          converters = new ConverterList();
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (connectors.getConnector().size() > 0) {
      for (Connector connector : connectors.getConnector()) {
        // retrieve connector plug-ins
        this.connectorPlugins.add(connector.getImplementation());

        // retrieve data format plug-ins
        if (!this.dataFormatPlugins.contains(connector.getConverterDataFormat()
            .getImplementation())) {
          this.dataFormatPlugins.add(connector.getConverterDataFormat()
              .getImplementation());
        }

        // retrieve data format mapping
        if (this.dataFormatMapping.containsKey(connector
            .getConverterDataFormat().getImplementation())) {
          this.dataFormatMapping.get(
              connector.getConverterDataFormat().getImplementation()).add(
              connector.getImplementation());
        } else {
          this.dataFormatMapping.put(
              connector.getConverterDataFormat().getImplementation(),
              new ArrayList<String>(
                  Arrays.asList(connector.getImplementation())));
        }
      }
    }

    // retrieve converter plug-ins
    if (converters.getConverter().size() > 0) {
      for (Converter converter : converters.getConverter()) {
        this.converterPlugins.add(converter.getImplementation());

        // retrieve converter mapping
        for (Connector connector : connectors.getConnector()) {
          if (connector.getConverterDataFormat().getImplementation()
              .equals(converter.getConnectorDataFormat().getImplementation())
              || connector.getConverterDataFormat().getImplementation()
                  .equals(converter.getWorkflowDataFormat().getImplementation())) {
            if (this.converterMapping.containsKey(converter.getImplementation())) {
              this.converterMapping.get(converter.getImplementation()).add(
                  connector.getImplementation());
            } else {
              this.converterMapping.put(converter.getImplementation(),
                  new ArrayList<String>(Arrays.asList(connector.getImplementation())));
            }
          }
        }
      }
    }
  }
}