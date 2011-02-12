package org.simpl.core.services.dataformat.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.simpl.core.SIMPLResourceManagement;
import org.simpl.core.plugins.dataformat.converter.DataFormatConverterPlugin;
import org.simpl.core.services.datasource.DataSourceService;

/**
 * <b>Purpose:</b>Provides access to the data format converter services that are created
 * from data format converter plug-ins.<br>
 * <b>Description:</b>Instances of data format converter services are retrieved by the
 * data format converter supported data formats.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataFormatConverterProvider.java 1263 2010-05-01 22:37:34Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataFormatConverterProvider {
  /**
   * Maps the supported data formats to a supported data format converter plugin instance.
   */
  private static HashMap<List<String>, DataFormatConverterPlugin> dataFormatConverter = new HashMap<List<String>, DataFormatConverterPlugin>();

  /**
   * Initialize all data format converter plugins.
   */
  static {
    DataFormatConverterProvider.loadPlugins();
  }

  /**
   * Returns the data format converter instance that supports the given data formats.
   * 
   * @param fromDataFormat
   * @param toDataFormat
   * @return
   */
  public static DataFormatConverter getInstance(String fromDataFormat, String toDataFormat) {
    DataFormatConverter converter = null;
    Set<List<String>> formats = DataFormatConverterProvider.dataFormatConverter.keySet();

    for (List<String> list : formats) {
      if (list.contains(fromDataFormat) && list.contains(toDataFormat)) {
        converter = DataFormatConverterProvider.dataFormatConverter.get(list);
      }
    }

    return converter;
  }

  /**
   * Returns a list of data format types that support the given data source service and
   * can be converted from the given data format type.
   * 
   * @param dataSourceService
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  public static List<String> getSupportedConvertDataFormatTypes(
      DataSourceService dataSourceService, String dataFormatType) {
    List<String> supportedConvertDataFormats = new ArrayList<String>();
    DataFormatConverterPlugin dataFormatConverter = null;

    HashMap<String, ArrayList<String>> dataFormatConverterMapping = SIMPLResourceManagement
        .getInstance().getDataFormatConverterMapping();

    for (String dataFormatConverterClassName : dataFormatConverterMapping.keySet()) {
      List<String> dataSourceServiceClassNames = dataFormatConverterMapping
          .get(dataFormatConverterClassName);

      try {
        if (dataSourceServiceClassNames.contains(dataSourceService.getClass().getName())) {
          dataFormatConverter = (DataFormatConverterPlugin) Class.forName(
              dataFormatConverterClassName).newInstance();

          if (dataFormatConverter.getFromDataFormat().getType().equals(dataFormatType)) {
            supportedConvertDataFormats.add(dataFormatConverter.getToDataFormat()
                .getType());
          } else if (dataFormatConverter.getToDataFormat().getType()
              .equals(dataFormatType)) {
            supportedConvertDataFormats.add(dataFormatConverter.getFromDataFormat()
                .getType());
          }
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

    return supportedConvertDataFormats;
  }

  /**
   * Loads instances of the data format converter plug-ins and retrieves information about
   * their supported data source type and subtype.
   */
  public static void loadPlugins() {
    List<String> plugins = SIMPLResourceManagement.getInstance().getDataFormatConverterPlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    DataFormatConverterPlugin dataFormatConverterInstance;
    String toDataFormat = null;
    String fromDataFormat = null;

    while (pluginIterator.hasNext()) {
      try {
        dataFormatConverterInstance = (DataFormatConverterPlugin) Class.forName(
            pluginIterator.next()).newInstance();

        toDataFormat = dataFormatConverterInstance.getToDataFormat().getType();
        fromDataFormat = dataFormatConverterInstance.getFromDataFormat().getType();

        DataFormatConverterProvider.dataFormatConverter.put(
            Arrays.asList(toDataFormat, fromDataFormat), dataFormatConverterInstance);
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