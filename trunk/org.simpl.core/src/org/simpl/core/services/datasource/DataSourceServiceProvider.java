package org.simpl.core.services.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.SIMPLCore;
import org.simpl.core.plugins.datasource.DataSourcePlugin;
import org.simpl.core.services.strategy.Strategy;
import org.simpl.core.services.strategy.StrategyService;

/**
 * <b>Purpose:</b> Provides access to the data source services that are loaded
 * from data source plugins.<br>
 * <b>Description:</b> Instances of data source services are retrieved by type
 * and subtype of a data source, using the getInstance() method. <br>
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
   * Maps the data source service types to a list of supporting data source
   * plugin instances.
   */
  private HashMap<String, List<DataSourcePlugin>> dataSources = new HashMap<String, List<DataSourcePlugin>>();

  /**
   * Initialize all data source service plugins.
   */
  public DataSourceServiceProvider() {
    loadPlugins();
  }

  /**
   * Returns the data source service instance that supports the given data
   * source type and subtype.
   * 
   * @param dsType
   * @param dsSubtype
   * @return
   */
  public DataSourceService getInstance(String dsType, String dsSubtype) {
    DataSourcePlugin dataSourceServiceInstance = null;
    List<DataSourcePlugin> dataSourcePlugins = this.dataSources.get(dsType);
    List<String> dataSourcePluginSubtypes = null;

    // search for a plugin that supports the given subtype
    for (DataSourcePlugin dataSourcePluginInstance : dataSourcePlugins) {
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
   * Returns the data source service instance that matches the given wsPolicy
   * following the given strategy.
   * 
   * @param wsPolicy
   * @return
   */
  public DataSourceService getInstance(String wsPolicy, Strategy strategy) {
    StrategyService strategyService = SIMPLCore.getInstance().strategyService();
    DataSourceService dataSourceService = strategyService
        .findDataSourceService(wsPolicy, strategy);

    return dataSourceService;
  }

  /**
   * Loads instances of the data source plugins and retrieves information about
   * their supported types, subtypes and languages.
   */
  private void loadPlugins() {
    List<String> plugins = SIMPLCore.getInstance().config()
        .getDataSourcePlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    DataSourcePlugin dataSourceServiceInstance;
    String dataSourceType = null;

    while (pluginIterator.hasNext()) {
      try {
        dataSourceServiceInstance = (DataSourcePlugin) Class.forName(
            (String) pluginIterator.next()).newInstance();
        dataSourceType = dataSourceServiceInstance.getType();

        if (dataSources.containsKey(dataSourceType)) {
          dataSources.get(dataSourceType).add(dataSourceServiceInstance);
        } else {
          List<DataSourcePlugin> dataSourceServicePluginList = new ArrayList<DataSourcePlugin>();
          dataSourceServicePluginList.add(dataSourceServiceInstance);
          dataSources.put(dataSourceType, dataSourceServicePluginList);
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
  public List<String> getTypes() {
    return new ArrayList<String>(dataSources.keySet());
  }

  /**
   * Returns the data source subtypes of a given data source type.
   * 
   * @param dsType
   * @return
   */
  public List<String> getSubtypes(String dsType) {
    List<String> dataSourceSubtypes = new ArrayList<String>();

    for (DataSourcePlugin dataSourceServicePlugin : dataSources.get(dsType)) {
      for (String dataSourceSubtype : dataSourceServicePlugin.getSubtypes()) {
        if (!dataSourceSubtypes.contains(dataSourceSubtype)) {
          dataSourceSubtypes.add(dataSourceSubtype);
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
  public List<String> getLanguages(String dsSubtype) {
    List<String> dataSourceLanguages = new ArrayList<String>();

    for (String dataSourceType : dataSources.keySet()) {
      for (DataSourcePlugin dataSourceServicePlugin : dataSources
          .get(dataSourceType)) {
        if (dataSourceServicePlugin.getLanguages().containsKey(dsSubtype)) {
          for (String language : dataSourceServicePlugin.getLanguages().get(
              dsSubtype)) {
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