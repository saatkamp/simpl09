package org.simpl.core.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.simpl.core.clients.RMClient;
import org.simpl.core.connector.Connector;
import org.simpl.core.plugins.converter.ConverterPlugin;

/**
 * <b>Purpose:</b>Provides access to the converters that are loaded from converter
 * plug-ins.<br>
 * <b>Description:</b>Converter instances can be retrieved by the converter supported data
 * formats, using the {@link #getInstance(String, String)}. <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataFormatConverterProvider.java 1263 2010-05-01 22:37:34Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ConverterProvider {
  /**
   * Maps the supported data formats to a converter plug-in instance.
   */
  private static HashMap<List<String>, ConverterPlugin> converters = new HashMap<List<String>, ConverterPlugin>();

  /**
   * Initially load the converter plug-ins.
   */
  static {
    ConverterProvider.loadPlugins();
  }

  /**
   * Returns a converter instance that supports the given data formats.
   * 
   * @param fromDataFormat
   * @param toDataFormat
   * @return
   */
  public static Converter getInstance(String fromDataFormat, String toDataFormat) {
    Converter converter = null;
    Set<List<String>> formats = ConverterProvider.converters.keySet();

    for (List<String> list : formats) {
      if (list.contains(fromDataFormat) && list.contains(toDataFormat)) {
        converter = ConverterProvider.converters.get(list);
      }
    }

    return converter;
  }

  /**
   * Returns a list of data format types to which the given connector can convert the
   * given data format to.
   * 
   * @param connector
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  public static List<String> getSupportedConvertDataFormatTypes(Connector connector,
      String dataFormatType) {
    List<String> supportedConvertDataFormats = new ArrayList<String>();
    ConverterPlugin converter = null;

    HashMap<String, ArrayList<String>> dataFormatConverterMapping = RMClient
        .getInstance().getConverterMapping();

    for (String dataFormatConverterClassName : dataFormatConverterMapping.keySet()) {
      List<String> connectorClassNames = dataFormatConverterMapping
          .get(dataFormatConverterClassName);

      try {
        if (connectorClassNames.contains(connector.getClass().getName())) {
          converter = (ConverterPlugin) Class.forName(dataFormatConverterClassName)
              .newInstance();

          // there may be converters that support data formats that are not used by any connectors,
          // and thus are not loaded resulting in their instances beeing null.
          if (converter.getFromDataFormat() != null
              && converter.getToDataFormat() != null) {
            if (converter.getFromDataFormat().getType().equals(dataFormatType)) {
              supportedConvertDataFormats.add(converter.getToDataFormat().getType());
            } else if (converter.getToDataFormat().getType().equals(dataFormatType)) {
              supportedConvertDataFormats.add(converter.getFromDataFormat().getType());
            }
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
   * Loads the converter plug-ins.
   */
  public static void loadPlugins() {
    List<String> plugins = RMClient.getInstance().getConverterPlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    ConverterPlugin converterInstance;
    String toDataFormat = null;
    String fromDataFormat = null;

    while (pluginIterator.hasNext()) {
      try {
        converterInstance = (ConverterPlugin) Class.forName(pluginIterator.next())
            .newInstance();

        if (converterInstance.getToDataFormat() != null
            && converterInstance.getFromDataFormat() != null) {
          toDataFormat = converterInstance.getToDataFormat().getType();
          fromDataFormat = converterInstance.getFromDataFormat().getType();

          ConverterProvider.converters.put(Arrays.asList(toDataFormat, fromDataFormat),
              converterInstance);
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