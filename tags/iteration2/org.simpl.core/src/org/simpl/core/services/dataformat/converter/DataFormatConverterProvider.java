package org.simpl.core.services.dataformat.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.simpl.core.SIMPLCore;
import org.simpl.core.plugins.dataformat.converter.DataFormatConverterPlugin;

/**
 * <b>Purpose:</b>Provides access to the data format converter services that are created
 * from data format converter plug-ins.<br>
 * <b>Description:</b> Instances of data format converter services are retrieved by the
 * data format converter supported data formats.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
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
    loadPlugins();
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
    Set<List<String>> formats = dataFormatConverter.keySet();

    for (List<String> list : formats) {
      if (list.contains(fromDataFormat) && list.contains(toDataFormat)) {
        converter = dataFormatConverter.get(list);
      }
    }

    return converter;
  }

  /**
   * Loads instances of the data format converter plug-ins and retrieves information about
   * their supported data source type and subtype.
   */
  private static void loadPlugins() {
    List<String> plugins = SIMPLCore.getInstance().config()
        .getDataFormatConverterPlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    DataFormatConverterPlugin dataFormatConverterInstance;
    String toDataFormat = null;
    String fromDataFormat = null;

    while (pluginIterator.hasNext()) {
      try {
        dataFormatConverterInstance = (DataFormatConverterPlugin) Class.forName(
            (String) pluginIterator.next()).newInstance();

        toDataFormat = dataFormatConverterInstance.getToDataFormat().getType();
        fromDataFormat = dataFormatConverterInstance.getFromDataFormat().getType();

        dataFormatConverter.put(Arrays.asList(toDataFormat, fromDataFormat),
            dataFormatConverterInstance);
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
   * Returns all data formats that can be converted between the given data format.
   * 
   * @return
   */
  public static List<String> getConvertDataFormats(String dataFormat) {
    Set<List<String>> dataFormatPairs = dataFormatConverter.keySet();
    List<String> dataFormats = new ArrayList<String>();

    for (List<String> dataFormatPair : dataFormatPairs) {
      if (dataFormatPair.contains(dataFormat)) {
        for (String format : dataFormatPair) {
          if (!format.equals(dataFormat)) {
            dataFormats.add(format);
          }
        }
      }
    }

    return dataFormats;
  }
}