package org.simpl.core.services.dataformat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.SIMPLCoreConfig;
import org.simpl.core.plugins.dataformat.DataFormatPlugin;
import org.simpl.core.services.datasource.DataSourceService;

/**
 * <b>Purpose:</b>Provides access to the data format services that are created from data
 * format plug-ins.<br>
 * <b>Description:</b> Instances of data format services are retrieved by the data format
 * type, using {@link #getInstance(String)}.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
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
  private static HashMap<String, DataFormatPlugin<Object, Object>> dataFormats = new HashMap<String, DataFormatPlugin<Object, Object>>();

  /**
   * Initialize all data format plugins.
   */
  static {
    DataFormatProvider.loadPlugins();
  }

  /**
   * Returns the DataFormat instance that supports the given data format type.
   * 
   * @param dfType
   * @return
   */
  public static DataFormat<Object, Object> getInstance(String dfType) {
    return DataFormatProvider.dataFormats.get(dfType);
  }

  /**
   * Returns all data source types loaded from the plugins.
   * 
   * @return
   */
  public static List<String> getDataFormatTypes() {
    return new ArrayList<String>(DataFormatProvider.dataFormats.keySet());
  }

  /**
   * Returns a list of data format types that support the given data source service.
   * 
   * @param dataSourceService
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  public static List<String> getSupportedDataFormatTypes(
      DataSourceService dataSourceService) {
    List<String> dataFormats = new ArrayList<String>();
    HashMap<String, ArrayList<String>> dataFormatMapping = SIMPLCoreConfig.getInstance()
        .getDataFormatMapping();

    for (String dataFormatClassName : dataFormatMapping.keySet()) {
      if (dataFormatMapping.get(dataFormatClassName).contains(
          dataSourceService.getClass().getName())) {
        try {
          dataFormats.add(((DataFormatPlugin) Class.forName(dataFormatClassName)
              .newInstance()).getType());
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

    return dataFormats;
  }

  /**
   * Loads instances of the data format plugins and retrieves information about their
   * supported type.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private static void loadPlugins() {
    List<String> plugins = SIMPLCoreConfig.getInstance().getDataFormatPlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    DataFormatPlugin<Object, Object> dataFormatServiceInstance;
    String dataFormatType = null;

    while (pluginIterator.hasNext()) {
      try {
        dataFormatServiceInstance = (DataFormatPlugin) Class.forName(
            pluginIterator.next()).newInstance();
        dataFormatType = dataFormatServiceInstance.getType();

        if (!DataFormatProvider.dataFormats.containsKey(dataFormatType)) {
          DataFormatProvider.dataFormats.put(dataFormatType, dataFormatServiceInstance);
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
}