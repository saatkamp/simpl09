package org.simpl.core.dataformat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.clients.RMClient;
import org.simpl.core.connector.Connector;
import org.simpl.core.plugins.dataformat.DataFormatPlugin;

/**
 * <b>Purpose:</b>Provides access to the data formats that are loaded from data format
 * plug-ins.<br>
 * <b>Description:</b>Data format instances are retrieved by data format type, using
 * {@link #getInstance(String)}.<br>
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
   * Maps the data format to a list of supporting data format plug-in instances.
   */
  private static HashMap<String, DataFormatPlugin<Object, Object>> dataFormats = new HashMap<String, DataFormatPlugin<Object, Object>>();

  /**
   * Initially load the data format plug-ins.
   */
  static {
    DataFormatProvider.loadPlugins();
  }

  /**
   * Returns the data format instance that supports the given data format type.
   * 
   * @param dfType
   * @return
   */
  public static DataFormat<Object, Object> getInstance(String dfType) {
    return DataFormatProvider.dataFormats.get(dfType);
  }

  /**
   * Returns all data source types loaded from the plug-ins.
   * 
   * @return
   */
  public static List<String> getDataFormatTypes() {
    return new ArrayList<String>(DataFormatProvider.dataFormats.keySet());
  }

  /**
   * Returns a list of data format types that support the given connector.
   * 
   * @param connector
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  public static List<String> getSupportedDataFormatTypes(Connector connector) {
    List<String> dataFormats = new ArrayList<String>();
    HashMap<String, ArrayList<String>> dataFormatMapping = RMClient
        .getInstance().getDataFormatMapping();

    for (String dataFormatClassName : dataFormatMapping.keySet()) {
      if (dataFormatMapping.get(dataFormatClassName).contains(
          connector.getClass().getName())) {
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
   * Loads the data format plug-ins.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void loadPlugins() {
    List<String> plugins = RMClient.getInstance().getDataFormatPlugins();
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