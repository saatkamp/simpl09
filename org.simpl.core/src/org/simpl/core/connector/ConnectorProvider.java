package org.simpl.core.connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.SIMPLResourceManagement;
import org.simpl.core.plugins.connector.ConnectorPlugin;

/**
 * <b>Purpose:</b> Provides access to the connectors that are loaded from connector
 * plug-ins.<br>
 * <b>Description:</b>Connector instances can be retrieved by type and sub type of a data
 * source, using the {@link #getInstance(String, String)}. <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DatasourceServiceProvider.java 973 2010-03-17 15:40:40Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ConnectorProvider {
  /**
   * Maps the connector types to a list of supporting connector instances.
   */
  private static HashMap<String, List<ConnectorPlugin<Object, Object>>> dataSourceServices = new HashMap<String, List<ConnectorPlugin<Object, Object>>>();

  /**
   * Initially load the connector plug-ins.
   */
  static {
    ConnectorProvider.loadPlugins();
  }

  /**
   * Returns the data source service instance that supports the given data source type and
   * subtype.
   * 
   * @param dsType
   * @param dsSubtype
   * @return
   */
  public static Connector<Object, Object> getInstance(String dsType, String dsSubtype) {
    ConnectorPlugin<Object, Object> dataSourceServiceInstance = null;
    List<ConnectorPlugin<Object, Object>> dataSourceServicePlugins = ConnectorProvider.dataSourceServices
        .get(dsType);
    List<String> dataSourcePluginSubtypes = null;

    // search for a plugin that supports the given subtype
    for (ConnectorPlugin<Object, Object> dataSourcePluginInstance : dataSourceServicePlugins) {
      dataSourcePluginSubtypes = dataSourcePluginInstance.getSubtypes();

      for (String subtype : dataSourcePluginSubtypes) {
        if (subtype.equals(dsSubtype)) {
          dataSourceServiceInstance = dataSourcePluginInstance;
        }
      }
    }

    return dataSourceServiceInstance;
  }

  /**
   * Loads the connector plug-ins.
   */
  @SuppressWarnings("unchecked")
  public static void loadPlugins() {
    List<String> plugins = SIMPLResourceManagement.getInstance()
        .getDataSourceServicePlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    ConnectorPlugin<Object, Object> dataSourceServiceInstance = null;
    String dataSourceType = null;

    while (pluginIterator.hasNext()) {
      try {
        dataSourceServiceInstance = (ConnectorPlugin<Object, Object>) Class.forName(
            pluginIterator.next()).newInstance();
        dataSourceType = dataSourceServiceInstance.getType();

        if (ConnectorProvider.dataSourceServices.containsKey(dataSourceType)) {
          ConnectorProvider.dataSourceServices.get(dataSourceType).add(
              dataSourceServiceInstance);
        } else {
          List<ConnectorPlugin<Object, Object>> dataSourceServicePluginList = new ArrayList<ConnectorPlugin<Object, Object>>();
          dataSourceServicePluginList.add(dataSourceServiceInstance);
          ConnectorProvider.dataSourceServices.put(dataSourceType,
              dataSourceServicePluginList);
        }
      } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * Returns all data source types loaded from the plugins.
   * 
   * @return
   */
  public static List<String> getTypes() {
    return new ArrayList<String>(ConnectorProvider.dataSourceServices.keySet());
  }

  /**
   * Returns the data source subtypes of a given data source type.
   * 
   * @param dsType
   * @return
   */
  public static List<String> getSubTypes(String dsType) {
    List<String> dataSourceSubtypes = new ArrayList<String>();

    for (ConnectorPlugin<Object, Object> dataSourceServicePlugin : ConnectorProvider.dataSourceServices
        .get(dsType)) {
      for (Object dataSourceSubtype : dataSourceServicePlugin.getSubtypes()) {
        if (!dataSourceSubtypes.contains(dataSourceSubtype)) {
          dataSourceSubtypes.add((String) dataSourceSubtype);
        }
      }
    }

    return dataSourceSubtypes;
  }

  /**
   * Returns all data source languages of a given data source subtype.
   * 
   * @param dsSubtype
   * @return
   */
  public static List<String> getLanguages(String dsSubtype) {
    List<String> dataSourceLanguages = new ArrayList<String>();

    for (String dataSourceType : ConnectorProvider.dataSourceServices.keySet()) {
      for (ConnectorPlugin<Object, Object> dataSourceServicePlugin : ConnectorProvider.dataSourceServices
          .get(dataSourceType)) {
        if (dataSourceServicePlugin.getLanguages().containsKey(dsSubtype)) {
          for (String language : dataSourceServicePlugin.getLanguages().get(dsSubtype)) {
            if (!dataSourceLanguages.contains(language)) {
              dataSourceLanguages.add(language);
            }
          }
        }
      }
    }

    return dataSourceLanguages;
  }
}