package org.simpl.core;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.simpl.resource.management.client.DataConverter;
import org.simpl.resource.management.client.DataConverterList;
import org.simpl.resource.management.client.DataSourceConnector;
import org.simpl.resource.management.client.DataSourceConnectorList;
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
 * @version $Id$<br>
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
    DataSourceConnectorList dataSourceConnectors = new DataSourceConnectorList();
    DataConverterList dataConverters = new DataConverterList();
    String resourceManagementLocation = SIMPLCoreConfig.getInstance()
        .getWebServiceAddress("ResourceManagement");

    try {
      if (resourceManagementLocation != null && !resourceManagementLocation.equals("")) {
        // check if service is online and available
        HttpURLConnection con = (HttpURLConnection) new URL(resourceManagementLocation)
            .openConnection();
        con.setRequestMethod("HEAD");
        
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
          resourceManagement = ResourceManagementClient.getService(resourceManagementLocation);
          
          dataSourceConnectors = resourceManagement.getDataSourceConnectors();
          dataConverters = resourceManagement.getDataConverters();
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    if (dataSourceConnectors.getDataSourceConnectors().size() > 0) {
      for (DataSourceConnector dataSourceConnector : dataSourceConnectors
          .getDataSourceConnectors()) {
        // retrieve data source service plug-ins
        this.dataSourceServicePlugins.add(dataSourceConnector.getName());

        // retrieve data format plug-ins
        if (!this.dataFormatPlugins.contains(dataSourceConnector
            .getDataConverterDataFormat())) {
          this.dataFormatPlugins.add(dataSourceConnector.getDataConverterDataFormat());
        }

        // retrieve data format mapping
        if (this.dataFormatMapping.containsKey(dataSourceConnector
            .getDataConverterDataFormat())) {
          this.dataFormatMapping.get(dataSourceConnector.getDataConverterDataFormat())
              .add(dataSourceConnector.getName());
        } else {
          this.dataFormatMapping.put(dataSourceConnector.getDataConverterDataFormat(),
              new ArrayList<String>(Arrays.asList(dataSourceConnector.getName())));
        }
      }
    }
    
    // retrieve data format converter plug-ins
    if (dataConverters.getDataConverters().size() > 0) {
      for (DataConverter dataConverter : dataConverters.getDataConverters()) {
        this.dataFormatConverterPlugins.add(dataConverter.getName());

        // retrieve data format converter mapping
        for (DataSourceConnector dataSourceConnector : dataSourceConnectors
            .getDataSourceConnectors()) {
          if (dataSourceConnector.getDataConverterDataFormat().equals(
              dataConverter.getDataSourceConnectorDataFormat())
              || dataSourceConnector.getDataConverterDataFormat().equals(
                  dataConverter.getWorkflowDataFormat())) {
            if (this.dataFormatConverterMapping.containsKey(dataConverter.getName())) {
              this.dataFormatConverterMapping.get(dataConverter.getName()).add(
                  dataSourceConnector.getName());
            } else {
              this.dataFormatConverterMapping.put(dataConverter.getName(),
                  new ArrayList<String>(Arrays.asList(dataSourceConnector.getName())));
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
   * Returns a map of data format and their supported data source services, key and values
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