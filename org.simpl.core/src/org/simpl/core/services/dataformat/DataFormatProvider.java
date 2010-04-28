package org.simpl.core.services.dataformat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.core.SIMPLCore;
import org.simpl.core.plugins.dataformat.DataFormatPlugin;

/**
 * <b>Purpose:</b>Provides access to the data format services that are created
 * from data format plug-ins.<br>
 * <b>Description:</b> Instances of data format services are retrieved by the
 * data format type, using {@link #getInstance(String)}.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DatasourceServiceProvider.java 892 2010-02-18 14:21:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@SuppressWarnings("unchecked")
public class DataFormatProvider {
  /**
   * Maps the data format to a list of supporting data format plugin instances.
   */
  private static HashMap<String, DataFormatPlugin> dataFormats = new HashMap<String, DataFormatPlugin>();

  /**
   * Initialize all data format plugins.
   */
  static {
    loadPlugins();
  }

  /**
   * Returns the DataFormat instance that supports the given data format type.
   * 
   * @param dfType
   * @param dfSubtype
   * @return
   */
  public static DataFormat<Object> getInstance(String dfType) {
    return dataFormats.get(dfType);
  }

  /**
   * Loads instances of the data format plugins and retrieves information about
   * their supported type.
   */
  private static void loadPlugins() {
    List<String> plugins = SIMPLCore.getInstance().config()
        .getDataFormatPlugins();
    Iterator<String> pluginIterator = plugins.iterator();
    DataFormatPlugin dataFormatServiceInstance;
    String dataFormatType = null;

    while (pluginIterator.hasNext()) {
      try {
        dataFormatServiceInstance = (DataFormatPlugin) Class.forName(
            (String) pluginIterator.next()).newInstance();
        dataFormatType = dataFormatServiceInstance.getType();

        if (!dataFormats.containsKey(dataFormatType)) {
          dataFormats.put(dataFormatType, dataFormatServiceInstance);
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
    return new ArrayList<String>(dataFormats.keySet());
  }
}