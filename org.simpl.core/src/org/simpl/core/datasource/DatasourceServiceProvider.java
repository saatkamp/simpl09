package org.simpl.core.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.SIMPLCore;
import org.simpl.core.datasource.plugins.DatasourceServicePlugin;

/**
 * <b>Purpose:</b> Provides access to the datasource services that are loaded from
 * plugins.<br>
 * <b>Description:</b> Instances of datasource services are retrieved by type and subtype
 * of a datasource, using the getInstance() method. <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DatasourceServiceProvider {
  /**
   * Maps the datasource service types to a list of supporting datasource service plugin
   * instances.
   */
  private HashMap<String, List<DatasourceServicePlugin>> datasourceServices = new HashMap<String, List<DatasourceServicePlugin>>();

  /**
   * Initialize all datasource service plugins.
   */
  public DatasourceServiceProvider() {
    loadDatasourcePlugins();
  }

  /**
   * Returns the datasource service instance that suppots the given datasource type and
   * subtype.
   * 
   * @param dsType
   * @param dsSubtype
   * @return
   */
  public DatasourceService getInstance(String dsType, String dsSubtype) {
    DatasourceServicePlugin datasourceServiceInstance = null;
    List<DatasourceServicePlugin> datasourceServicePlugins = this.datasourceServices
        .get(dsType);
    List<String> datasourceServiceSubtypes = null;

    // search for a plugin that supports the given subtype
    for (DatasourceServicePlugin datasourceServicePluginInstance : datasourceServicePlugins) {
      datasourceServiceSubtypes = datasourceServicePluginInstance.getDatasourceSubtypes();

      for (String subtype : datasourceServiceSubtypes) {
        if (subtype.equals(dsSubtype)) {
          datasourceServiceInstance = datasourceServicePluginInstance;
        }
      }
    }

    return datasourceServiceInstance;
  }

  /**
   * Loads instances of all plugins and retrievs the information about types, subtypes and
   * languages.
   */
  private void loadDatasourcePlugins() {
    List<String> plugins = SIMPLCore.getInstance().config().getDataSourceServicePlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    DatasourceServicePlugin datasourceServiceInstance;
    String datasourceType = null;

    while (pluginIterator.hasNext()) {
      try {
        datasourceServiceInstance = (DatasourceServicePlugin) Class.forName(
            (String) pluginIterator.next()).newInstance();
        datasourceType = datasourceServiceInstance.getDatasourceType();

        if (datasourceServices.containsKey(datasourceType)) {
          datasourceServices.get(datasourceType).add(datasourceServiceInstance);
        } else {
          List<DatasourceServicePlugin> datasourceServicePluginList = new ArrayList<DatasourceServicePlugin>();
          datasourceServicePluginList.add(datasourceServiceInstance);
          datasourceServices.put(datasourceType, datasourceServicePluginList);
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
   * Returns all datasource types loaded from the plugins.
   * 
   * @return
   */
  public List<String> getDatasourceTypes() {
    return new ArrayList<String>(datasourceServices.keySet());
  }

  /**
   * Returns the datasource subtypes of a given datasource type.
   * 
   * @param dsType
   * @return
   */
  public List<String> getDatasourceSubtypes(String dsType) {
    List<String> datasourceSubtypes = new ArrayList<String>();

    for (DatasourceServicePlugin datasourceServicePlugin : datasourceServices.get(dsType)) {
      for (String datasourceSubtype : datasourceServicePlugin.getDatasourceSubtypes()) {
        if (!datasourceSubtypes.contains(datasourceSubtype)) {
          datasourceSubtypes.add(datasourceSubtype);
        }
      }
    }

    return datasourceSubtypes;
  }

  /**
   * Returns all datasource languages of a given datasource subtype.
   * 
   * @param dsSubtype
   * @return
   */
  public List<String> getDatasourceLanguages(String dsSubtype) {
    List<String> datasourceLanguages = new ArrayList<String>();

    for (String datasourceType : datasourceServices.keySet()) {
      for (DatasourceServicePlugin datasourceServicePlugin : datasourceServices
          .get(datasourceType)) {
        if (datasourceServicePlugin.getDatasourceLanguages().containsKey(dsSubtype)) {
          for (String language : datasourceServicePlugin.getDatasourceLanguages().get(
              dsSubtype)) {
            if (!datasourceLanguages.contains(language)) {
              datasourceLanguages.add(language);
            }
          }
        }
      }
    }

    return datasourceLanguages;
  }
}