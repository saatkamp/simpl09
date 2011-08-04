package org.simpl.core.datatransformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.simpl.core.clients.RMClient;
import org.simpl.core.connector.Connector;
import org.simpl.data.transformation.DataTransformationService;

/**
 * <b>Purpose:</b>Provides access to the data transformation services that are registered
 * in the resource management.<br>
 * <b>Description:</b>Data transformation service instances can be retrieved by their
 * supporting data formats, using the {@link #getInstance(String, String)}. <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataTransformationServiceProvider {
  /**
   * Maps the supported data formats to a converter plug-in instance.
   */
  private static HashMap<List<String>, DataTransformationService> dataTransformationServices = new HashMap<List<String>, DataTransformationService>();

  /**
   * Initially load the converter plug-ins.
   */
  static {
    DataTransformationServiceProvider.loadServices();
  }

  /**
   * Returns a converter instance that supports the given data formats.
   * 
   * @param fromDataFormat
   * @param toDataFormat
   * @return
   */
  public static DataTransformationService getInstance(String fromDataFormat,
      String toDataFormat) {
    DataTransformationService dataTransformationService = null;
    Set<List<String>> formats = DataTransformationServiceProvider.dataTransformationServices
        .keySet();

    for (List<String> list : formats) {
      if (list.contains(fromDataFormat) && list.contains(toDataFormat)) {
        dataTransformationService = DataTransformationServiceProvider.dataTransformationServices
            .get(list);
      }
    }

    return dataTransformationService;
  }

  /**
   * Returns a list of data format types to which the given connector can convert the
   * given data format to.
   * 
   * @param connector
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  public static List<String> getSupportedConvertDataFormats(Connector connector,
      String dataFormat) {
    List<String> supportedConvertDataFormats = new ArrayList<String>();
    DataTransformationService dataTransformationService = null;

    HashMap<String, ArrayList<String>> dataFormatConverterMapping = RMClient
        .getInstance().getDataTransformationServiceMapping();

    for (String dataFormatConverterClassName : dataFormatConverterMapping.keySet()) {
      List<String> connectorClassNames = dataFormatConverterMapping
          .get(dataFormatConverterClassName);

      try {
        if (connectorClassNames.contains(connector.getClass().getName())) {
          dataTransformationService = (DataTransformationService) Class.forName(
              dataFormatConverterClassName).newInstance();

          // there may be converters that support data formats that are not used by any
          // connectors, and thus are not loaded resulting in their instances beeing null.
          if (dataTransformationService.getFromDataConverter() != null
              && dataTransformationService.getToDataConverter() != null) {
            if (dataTransformationService.getFromDataConverter().getDataFormat()
                .equals(dataFormat)) {
              supportedConvertDataFormats.add(dataTransformationService
                  .getToDataConverter().getDataFormat());
            } else if (dataTransformationService.getToDataConverter().getDataFormat()
                .equals(dataFormat)) {
              supportedConvertDataFormats.add(dataTransformationService
                  .getFromDataConverter().getDataFormat());
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
  public static void loadServices() {
    List<String> plugins = RMClient.getInstance().getDataTransformationServices();
    Iterator<String> pluginIterator = plugins.iterator();
    DataTransformationService dataTransformationServiceInstance;
    String toDataFormat = null;
    String fromDataFormat = null;

    while (pluginIterator.hasNext()) {
      try {
        dataTransformationServiceInstance = (DataTransformationService) Class.forName(
            pluginIterator.next()).newInstance();

        if (dataTransformationServiceInstance.getToDataConverter() != null
            && dataTransformationServiceInstance.getFromDataConverter() != null) {
          toDataFormat = dataTransformationServiceInstance.getToDataConverter()
              .getDataFormat();
          fromDataFormat = dataTransformationServiceInstance.getFromDataConverter()
              .getDataFormat();

          DataTransformationServiceProvider.dataTransformationServices.put(
              Arrays.asList(toDataFormat, fromDataFormat),
              dataTransformationServiceInstance);
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