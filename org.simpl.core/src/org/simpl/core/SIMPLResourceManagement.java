package org.simpl.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.simpl.resource.management.client.Converter;
import org.simpl.resource.management.client.ConverterList;
import org.simpl.resource.management.client.Connector;
import org.simpl.resource.management.client.ConnectorList;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;

/**
 * <b>Purpose:</b>Offers data from the SIMPL Resource Management about plugins that can be
 * used with the SIMPL Core.<br>
 * <b>Description:</b>Uses ResourceManagementClient to talk to the resource management web
 * service interface. The SIMPL Core knows data source service plug-ins, data format
 * plug-ins and data converter-plugins, while the resource management speaks of data
 * source connector and data converter plug-ins. This is because the resource management
 * was developed at a later point of time. As the resource management holds the same
 * information, the data is just mapped to the needed SIMPL Core plugin information.<br>
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
   * List of registered data source service plug-ins.
   */
  private List<String> dataSourceServicePlugins = new ArrayList<String>();

  /**
   * List of registered data format plug-ins.
   */
  private List<String> dataFormatPlugins = new ArrayList<String>();

  /**
   * List of registered data format converter plug-ins.
   */
  private List<String> dataFormatConverterPlugins = new ArrayList<String>();

  /**
   * Maps data formats to the data source services that can use it.
   */
  private HashMap<String, ArrayList<String>> dataFormatMapping = new HashMap<String, ArrayList<String>>();

  /**
   * Maps data format converter to the data source services that can use it.
   */
  private HashMap<String, ArrayList<String>> dataFormatConverterMapping = new HashMap<String, ArrayList<String>>();

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
    ConnectorList connectors = new ConnectorList();
    ConverterList dataConverters = new ConverterList();
    String resourceManagementLocation = SIMPLCoreConfig.getInstance()
        .getWebServiceAddress("ResourceManagement");

    try {
      if (resourceManagementLocation != null && !resourceManagementLocation.equals("")) {
        resourceManagement = ResourceManagementClient
            .getService(resourceManagementLocation);

        connectors = resourceManagement.getConnectors();
        dataConverters = resourceManagement.getConverters();
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (connectors.getConnectors().size() > 0) {
      for (Connector dataSourceConnector : connectors
          .getConnectors()) {
        // retrieve data source service plug-ins
        this.dataSourceServicePlugins.add(dataSourceConnector.getImplementation());

        // retrieve data format plug-ins
        if (!this.dataFormatPlugins.contains(dataSourceConnector
            .getConverterDataFormatImplementation())) {
          this.dataFormatPlugins.add(dataSourceConnector.getConverterDataFormatImplementation());
        }

        // retrieve data format mapping
        if (this.dataFormatMapping.containsKey(dataSourceConnector
            .getConverterDataFormatImplementation())) {
          this.dataFormatMapping.get(dataSourceConnector.getConverterDataFormatImplementation())
              .add(dataSourceConnector.getImplementation());
        } else {
          this.dataFormatMapping.put(dataSourceConnector.getConverterDataFormatImplementation(),
              new ArrayList<String>(Arrays.asList(dataSourceConnector.getImplementation())));
        }
      }
    }

    // retrieve data format converter plug-ins
    if (dataConverters.getConverters().size() > 0) {
      for (Converter dataConverter : dataConverters.getConverters()) {
        this.dataFormatConverterPlugins.add(dataConverter.getImplementation());

        // retrieve data format converter mapping
        for (Connector connector : connectors
            .getConnectors()) {
          if (connector.getConverterDataFormatImplementation().equals(
              dataConverter.getConnectorDataFormatImplementation())
              || connector.getConverterDataFormatImplementation().equals(
                  dataConverter.getWorkflowDataFormatImplementation())) {
            if (this.dataFormatConverterMapping.containsKey(dataConverter.getImplementation())) {
              this.dataFormatConverterMapping.get(dataConverter.getImplementation()).add(
                  connector.getImplementation());
            } else {
              this.dataFormatConverterMapping.put(dataConverter.getImplementation(),
                  new ArrayList<String>(Arrays.asList(connector.getImplementation())));
            }
          }
        }
      }
    }
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
   * Returns a list of registered DataSourcePlugins. The list contains full qualified
   * names of DataSourcePlugin classes.
   * 
   * @return List of DataSourcePlugins
   */
  public List<String> getDataSourceServicePlugins() {
    return dataSourceServicePlugins;
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
   * Returns a list of registered DataFormatPlugins. The list contains full qualified
   * names of DataFormatPlugin classes.
   * 
   * @return List of DataFormatPlugins
   */
  public List<String> getDataFormatConverterPlugins() {
    return dataFormatConverterPlugins;
  }

  /**
   * Returns a map of data formats and their supported data source services, key and values
   * are full qualified class names.
   * 
   * @return data format mapping
   */
  public HashMap<String, ArrayList<String>> getDataFormatMapping() {
    return dataFormatMapping;
  }

  /**
   * Returns a map of data format converters and their supported data source services, key
   * and values are full qualified class names.
   * 
   * @return data format converter mapping
   */
  public HashMap<String, ArrayList<String>> getDataFormatConverterMapping() {
    return dataFormatConverterMapping;
  }
}