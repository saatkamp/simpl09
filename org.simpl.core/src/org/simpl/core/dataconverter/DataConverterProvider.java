package org.simpl.core.dataconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.clients.RMClient;
import org.simpl.core.connector.Connector;
import org.simpl.core.plugins.dataconverter.DataConverterPlugin;

/**
 * <b>Purpose:</b>Provides access to the data converters that are loaded from data
 * converter plug-ins.<br>
 * <b>Description:</b>Data converter instances are retrieved by their data format, using
 * {@link #getInstance(String)}.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataFormatProvider.java 1788 2011-04-10 12:30:51Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataConverterProvider {
  /**
   * Maps the data formats to a list of supporting data converter plug-in instances.
   */
  private static HashMap<String, DataConverterPlugin<Object, Object>> dataConverters = new HashMap<String, DataConverterPlugin<Object, Object>>();

  /**
   * Initially load the data converter plug-ins.
   */
  static {
    DataConverterProvider.loadPlugins();
  }

  /**
   * Returns the data converter instance that supports the given data format.
   * 
   * @param dataFormat
   * @return
   */
  public static DataConverter<Object, Object> getInstance(String dataFormat) {
    return DataConverterProvider.dataConverters.get(dataFormat);
  }

  /**
   * Returns a list of all data formats that are supported by the loaded data converter
   * plug-ins.
   * 
   * @return
   */
  public static List<String> getDataFormats() {
    return new ArrayList<String>(DataConverterProvider.dataConverters.keySet());
  }

  /**
   * Returns a list of data formats that can be used by a given connector.
   * 
   * @param connector
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  public static List<String> getSupportedDataFormats(Connector connector) {
    List<String> dataFormats = new ArrayList<String>();
    HashMap<String, ArrayList<String>> dataFormatMapping = RMClient.getInstance()
        .getDataFormatMapping();

    for (String dataFormatClassName : dataFormatMapping.keySet()) {
      if (dataFormatMapping.get(dataFormatClassName).contains(
          connector.getClass().getName())) {
        try {
          dataFormats.add(((DataConverterPlugin) Class.forName(dataFormatClassName)
              .newInstance()).getDataFormat());
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
    DataConverterPlugin<Object, Object> dataFormatServiceInstance;
    String dataFormat = null;

    while (pluginIterator.hasNext()) {
      try {
        dataFormatServiceInstance = (DataConverterPlugin) Class.forName(
            pluginIterator.next()).newInstance();
        dataFormat = dataFormatServiceInstance.getDataFormat();

        if (!DataConverterProvider.dataConverters.containsKey(dataFormat)) {
          DataConverterProvider.dataConverters.put(dataFormat,
              dataFormatServiceInstance);
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