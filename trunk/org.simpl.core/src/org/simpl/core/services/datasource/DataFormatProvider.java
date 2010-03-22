package org.simpl.core.services.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.SIMPLCore;
import org.simpl.core.plugins.DataFormatPlugin;

/**
 * <b>Purpose:</b> Provides access to the data formats that are loaded from plugins.<br>
 * <b>Description:</b> Instances of data formats are retrieved by extension, using the
 * getInstance() method.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DatasourceServiceProvider.java 892 2010-02-18 14:21:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataFormatProvider {
  /**
   * Maps the data format to a list of supporting data format plugin instances.
   */
  private HashMap<String, List<DataFormatPlugin>> dataFormats = new HashMap<String, List<DataFormatPlugin>>();

  /**
   * Initialize all data format plugins.
   */
  public DataFormatProvider() {
    loadDataFormatPlugins();
  }

  /**
   * Returns the DataFormat instance that supports the given extension.
   * 
   * @param dType
   * @return
   */
  public DataFormat getInstance(String dType) {
    DataFormatPlugin dataFormatInstance = null;
    List<DataFormatPlugin> dataFormatPlugins = this.dataFormats.get(dType);
    String dataFormatType = null;

    // search for a plugin that supports the given type
    for (DataFormatPlugin dataFormatPluginInstance : dataFormatPlugins) {
      dataFormatType = dataFormatPluginInstance.getType();

      if (dataFormatType.equals(dType)) {
        dataFormatInstance = dataFormatPluginInstance;
      }
    }

    return dataFormatInstance;
  }

  /**
   * Loads instances of all plugins and retrievs the information about types, subtypes and
   * languages.
   */
  private void loadDataFormatPlugins() {
    List<String> plugins = SIMPLCore.getInstance().config().getDataFormatPlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    DataFormatPlugin dataFormatInstance;
    String dataFormatExtension = null;

    while (pluginIterator.hasNext()) {
      try {
        dataFormatInstance = (DataFormatPlugin) Class.forName(
            (String) pluginIterator.next()).newInstance();
        // dataFormatExtension = dataFormatInstance.getExtension("");

        if (dataFormats.containsKey(dataFormatExtension)) {
          dataFormats.get(dataFormatExtension).add(dataFormatInstance);
        } else {
          List<DataFormatPlugin> dataFormatPluginList = new ArrayList<DataFormatPlugin>();
          dataFormatPluginList.add(dataFormatInstance);
          dataFormats.put(dataFormatExtension, dataFormatPluginList);
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
  public List<String> getDataFormatTypes() {
    return new ArrayList<String>(dataFormats.keySet());
  }
}