package org.simpl.core.services.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.SIMPLCore;
import org.simpl.core.plugins.datasource.DataSourceServicePlugin;

/**
 * <b>Purpose:</b> Provides access to the data source services that are loaded from data
 * source plug-ins.<br>
 * <b>Description:</b> Instances of data source services are retrieved by type and subtype
 * of a data source, using the getInstance() method. <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DatasourceServiceProvider.java 973 2010-03-17 15:40:40Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataSourceServiceProvider {
  /**
   * Maps the data source service types to a list of supporting data source plugin
   * instances.
   */
  private static HashMap<String, List<DataSourceServicePlugin<Object, Object>>> dataSourceServices = new HashMap<String, List<DataSourceServicePlugin<Object, Object>>>();

  /**
   * Initialize all data source service plugins.
   */
  static {
    loadPlugins();
  }

  /**
   * Returns the data source service instance that supports the given data source type and
   * subtype.
   * 
   * @param dsType
   * @param dsSubtype
   * @return
   */
  public static DataSourceService<Object, Object> getInstance(String dsType, String dsSubtype) {
    DataSourceServicePlugin<Object, Object> dataSourceServiceInstance = null;
    List<DataSourceServicePlugin<Object, Object>> dataSourceServicePlugins = dataSourceServices.get(dsType);
    List<String> dataSourcePluginSubtypes = null;

    // search for a plugin that supports the given subtype
    for (DataSourceServicePlugin<Object, Object> dataSourcePluginInstance : dataSourceServicePlugins) {
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
   * Loads instances of the data source plugins and retrieves information about their
   * supported types, subtypes and languages.
   */
  @SuppressWarnings("unchecked")
  private static void loadPlugins() {
    List<String> plugins = SIMPLCore.getInstance().config().getDataSourceServicePlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    DataSourceServicePlugin<Object, Object> dataSourceServiceInstance;
    String dataSourceType = null;

    while (pluginIterator.hasNext()) {
      try {
        dataSourceServiceInstance = (DataSourceServicePlugin<Object, Object>) Class.forName(
            (String) pluginIterator.next()).newInstance();
        dataSourceType = dataSourceServiceInstance.getType();

        if (dataSourceServices.containsKey(dataSourceType)) {
          dataSourceServices.get(dataSourceType).add(dataSourceServiceInstance);
        } else {
          List<DataSourceServicePlugin<Object, Object>> dataSourceServicePluginList = new ArrayList<DataSourceServicePlugin<Object, Object>>();
          dataSourceServicePluginList.add(dataSourceServiceInstance);
          dataSourceServices.put(dataSourceType, dataSourceServicePluginList);
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
    return new ArrayList<String>(dataSourceServices.keySet());
  }

  /**
   * Returns the data source subtypes of a given data source type.
   * 
   * @param dsType
   * @return
   */
  public static List<String> getSubTypes(String dsType) {
    List<String> dataSourceSubtypes = new ArrayList<String>();

    for (DataSourceServicePlugin<Object, Object> dataSourceServicePlugin : dataSourceServices.get(dsType)) {
      for (Object dataSourceSubtype : dataSourceServicePlugin.getSubtypes()) {
        if (!dataSourceSubtypes.contains(dataSourceSubtype)) {
          dataSourceSubtypes.add((String)dataSourceSubtype);
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

    for (String dataSourceType : dataSourceServices.keySet()) {
      for (DataSourceServicePlugin<Object, Object> dataSourceServicePlugin : dataSourceServices
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