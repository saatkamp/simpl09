package org.simpl.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * <b>Purpose:</b> Reads the SIMPLCore configuration from the configuration file and
 * provides functions to access the configuration settings. This includes information
 * about all registered plug-ins.<br>
 * <b>Description:</b> Searches for the configuration file in the current work directory
 * and in the Apache ODE <i>conf</i> directory whose relative location depends on the
 * execution path of Apache Tomcat.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLConfig {
  /**
   * SIMPL config file.
   */
  private static final String CONFIG_FILE_NAME = "simpl-core-config.xml";

  /**
   * Config file location within Apache ODE.
   */
  private static final String CONFIG_FILE_LOCATION_1 = System.getProperty("user.dir")
      + "\\webapps\\ode\\WEB-INF\\conf\\" + SIMPLConfig.CONFIG_FILE_NAME;

  /**
   * Config file location within Apache ODE.
   */
  private static final String CONFIG_FILE_LOCATION_2 = System.getProperty("user.dir")
      + "\\..\\webapps\\ode\\WEB-INF\\conf\\" + SIMPLConfig.CONFIG_FILE_NAME;

  /**
   * List of registered data source service plug-ins.
   */
  List<String> dataSourceServicePlugins = new ArrayList<String>();

  /**
   * List of registered data format plug-ins.
   */
  List<String> dataFormatPlugins = new ArrayList<String>();

  /**
   * List of registered data format converter plug-ins.
   */
  List<String> dataFormatConverterPlugins = new ArrayList<String>();

  /**
   * Maps data formats to the data source services that can use it.
   */
  HashMap<String, List<String>> dataFormatMapping = new HashMap<String, List<String>>();

  /**
   * Maps data format converter to the data source services that can use it.
   */
  HashMap<String, List<String>> dataFormatConverterMapping = new HashMap<String, List<String>>();

  /**
   * Reads the SIMPL config file and retrieves all config information.
   */
  @SuppressWarnings("unchecked")
  public SIMPLConfig() {
    InputStream in = null;
    Document configDoc = null;

    Element root = null;
    SAXBuilder saxBuilder = new SAXBuilder();
    List<Element> dataSourceServicePluginElements = null;
    List<Element> dataFormatPluginElements = null;
    List<Element> dataFormatConverterPluginElements = null;
    List<Element> dataFormatElements = null;
    List<Element> dataFormatConverterElements = null;

    try {
      in = new FileInputStream(SIMPLConfig.CONFIG_FILE_LOCATION_1);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(SIMPLConfig.CONFIG_FILE_LOCATION_2);
      } catch (FileNotFoundException e1) {
        try {
          in = new FileInputStream(SIMPLConfig.CONFIG_FILE_NAME);
        } catch (FileNotFoundException e2) {
          // TODO Auto-generated catch block
          e2.printStackTrace();
        }
      }
    }

    // retrieve information from the config file
    try {
      // read xml config file
      configDoc = saxBuilder.build(in);
      root = configDoc.getRootElement();

      // read elements
      dataSourceServicePluginElements = root.getChildren("DataSourceServicePlugin");
      dataFormatPluginElements = root.getChildren("DataFormatPlugin");
      dataFormatConverterPluginElements = root.getChildren("DataFormatConverterPlugin");
      dataFormatElements = root.getChildren("DataFormat");
      dataFormatConverterElements = root.getChildren("DataFormatConverter");

      // retrieve data source service plug-ins
      for (Element dataSourceServicePluginElement : dataSourceServicePluginElements) {
        dataSourceServicePlugins.add(dataSourceServicePluginElement
            .getAttributeValue("className"));
      }

      // retrieve data format plug-ins
      for (Element dataFormatPluginElement : dataFormatPluginElements) {
        dataFormatPlugins.add(dataFormatPluginElement.getAttributeValue("className"));
      }

      // retrieve data format converter plug-ins
      for (Element dataFormatConverterPluginElement : dataFormatConverterPluginElements) {
        dataFormatConverterPlugins.add(dataFormatConverterPluginElement
            .getAttributeValue("className"));
      }

      // retrieve data format mapping
      for (Element dataFormatElement : dataFormatElements) {
        String dataFormatClassName = getElementbyId(root,
            dataFormatElement.getAttributeValue("ref")).getAttributeValue("className");
        List<String> dataSourceServiceClassNames = new ArrayList<String>();
        String dataSourceServiceClassName = null;

        for (Element dataSourceServiceElement : (List<Element>) dataFormatElement
            .getChildren()) {
          dataSourceServiceClassName = getElementbyId(root,
              dataSourceServiceElement.getValue()).getAttributeValue("className");
          dataSourceServiceClassNames.add(dataSourceServiceClassName);
        }

        dataFormatMapping.put(dataFormatClassName, dataSourceServiceClassNames);
      }

      // retrieve data format converter mapping
      for (Element dataFormatConverterElement : dataFormatConverterElements) {
        String dataFormatConverterClassName = getElementbyId(root,
            dataFormatConverterElement.getAttributeValue("ref")).getAttributeValue(
            "className");
        List<String> dataSourceServiceClassNames = new ArrayList<String>();
        String dataSourceServiceClassName = null;

        for (Element dataSourceServiceElement : (List<Element>) dataFormatConverterElement
            .getChildren()) {
          dataSourceServiceClassName = getElementbyId(root,
              dataSourceServiceElement.getValue()).getAttributeValue("className");
          dataSourceServiceClassNames.add(dataSourceServiceClassName);
        }

        dataFormatConverterMapping.put(dataFormatConverterClassName,
            dataSourceServiceClassNames);
      }
    } catch (JDOMException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Returns a list of registered DataSourcePlugins. The list contains full qualified
   * names of DataSourcePlugin classes.
   * 
   * @return List of DataSourcePlugins
   */
  public List<String> getDataSourceServicePlugins() {
    return dataSourceServicePlugins;
  }

  /**
   * Returns a list of registered DataFormatPlugins. The list contains full qualified
   * names of DataFormatPlugin classes.
   * 
   * @return List of DataFormatPlugins
   */
  public List<String> getDataFormatPlugins() {
    return dataFormatPlugins;
  }

  /**
   * Returns a list of registered DataFormatPlugins. The list contains full qualified
   * names of DataFormatPlugin classes.
   * 
   * @return List of DataFormatPlugins
   */
  public List<String> getDataFormatConverterPlugins() {
    return dataFormatConverterPlugins;
  }

  /**
   * Returns a map of data format and their supported data source services, key and values
   * are full qualified class names.
   * 
   * @return data format mapping
   */
  public HashMap<String, List<String>> getDataFormatMapping() {
    return dataFormatMapping;
  }

  /**
   * Returns a map of data format converters and their supported data source services, key
   * and values are full qualified class names.
   * 
   * @return data format converter mapping
   */
  public HashMap<String, List<String>> getDataFormatConverterMapping() {
    return dataFormatConverterMapping;
  }

  /**
   * Gets an element by its id attribute from the given root element.
   * 
   * @param root
   * @param id
   * @return
   */
  @SuppressWarnings("unchecked")
  private Element getElementbyId(Element root, String id) {
    Element idElement = null;

    for (Element element : (List<Element>) root.getChildren()) {
      if (element.getAttributeValue("id") != null
          && element.getAttributeValue("id").equals(id)) {
        idElement = element;
        break;
      }
    }

    return idElement;
  }
}