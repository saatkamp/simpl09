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
 * <b>Purpose:</b> Reads the SIMPL Core configuration from the configuration file and the
 * Resource Management and provides functions to access the configuration settings. This
 * includes information about all registered plug-ins.<br>
 * <b>Description:</b> Expects the configuration file (simpl-core-config.xml) in the
 * WEB-INF/conf folder relative to the deployed SIMPL Core. If the Resource Management is
 * declared in the configuration file and is online, the information from the Resource
 * Management will overwrite the information from the configuration file.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: SIMPLCoreConfig.java 1673 2010-10-10 17:24:35Z michael.schneidt@arcor.de
 *          $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCoreConfig {
  /**
   * SIMPL config file.
   */
  private static final String CONFIG_FILE_NAME = "simpl-core-config.xml";

  /**
   * List of registered data source service plug-ins.
   */
  private List<String> dataSourceServicePlugins = new ArrayList<String>();

  /**
   * List of registered data format plug-ins.
   */
  private List<String> dataFormatPlugins = new ArrayList<String>();

  /**
   * List of registered data format converter plug-ins.
   */
  private List<String> dataFormatConverterPlugins = new ArrayList<String>();

  /**
   * Maps data formats to the data source services that can use it.
   */
  private HashMap<String, ArrayList<String>> dataFormatMapping = new HashMap<String, ArrayList<String>>();

  /**
   * Maps data format converter to the data source services that can use it.
   */
  private HashMap<String, ArrayList<String>> dataFormatConverterMapping = new HashMap<String, ArrayList<String>>();

  /**
   * Maps the web services to their addresses.
   */
  private HashMap<String, String> webServicesMapping = new HashMap<String, String>();

  /**
   * SIMPLCoreConfig singleton instance.
   */
  private static final SIMPLCoreConfig instance = new SIMPLCoreConfig();

  /**
   * Reads the SIMPL config file and retrieves all config information.
   */
  @SuppressWarnings("unchecked")
  private SIMPLCoreConfig() {
    InputStream in = null;
    Document configDoc = null;

    Element root = null;
    SAXBuilder saxBuilder = new SAXBuilder();
    List<Element> dataSourceServicePluginElements = null;
    List<Element> dataFormatPluginElements = null;
    List<Element> dataFormatConverterPluginElements = null;
    List<Element> dataFormatElements = null;
    List<Element> dataFormatConverterElements = null;
    Element webServicesElement = null;

    // determine path to the simpl-core-config.xml
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    String configFilePath = loader.getResource(
        SIMPLCore.class.getName().replace('.', '/') + ".class").getPath();
    configFilePath = configFilePath.replace("file:/", "").substring(0,
        configFilePath.indexOf("WEB-INF") + 1)
        + "/conf/";

    try {
      in = new FileInputStream(configFilePath + CONFIG_FILE_NAME);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(SIMPLCoreConfig.CONFIG_FILE_NAME);
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
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
      webServicesElement = root.getChild("WebServices");

      // retrieve data source service plug-ins
      if (dataSourceServicePluginElements.size() > 0) {
        for (Element dataSourceServicePluginElement : dataSourceServicePluginElements) {
          dataSourceServicePlugins.add(dataSourceServicePluginElement
              .getAttributeValue("className"));
        }
      }

      // retrieve data format plug-ins
      if (dataFormatPluginElements.size() > 0) {
        for (Element dataFormatPluginElement : dataFormatPluginElements) {
          dataFormatPlugins.add(dataFormatPluginElement.getAttributeValue("className"));
        }
      }

      // retrieve data format converter plug-ins
      if (dataFormatConverterPluginElements.size() > 0) {
        for (Element dataFormatConverterPluginElement : dataFormatConverterPluginElements) {
          dataFormatConverterPlugins.add(dataFormatConverterPluginElement
              .getAttributeValue("className"));
        }
      }

      // retrieve data format mapping
      if (dataFormatElements.size() > 0) {
        for (Element dataFormatElement : dataFormatElements) {
          String dataFormatClassName = getElementbyId(root,
              dataFormatElement.getAttributeValue("ref")).getAttributeValue("className");
          ArrayList<String> dataSourceServiceClassNames = new ArrayList<String>();
          String dataSourceServiceClassName = null;

          for (Element dataSourceServiceElement : (List<Element>) dataFormatElement
              .getChildren()) {
            dataSourceServiceClassName = getElementbyId(root,
                dataSourceServiceElement.getValue()).getAttributeValue("className");
            dataSourceServiceClassNames.add(dataSourceServiceClassName);
          }

          dataFormatMapping.put(dataFormatClassName, dataSourceServiceClassNames);
        }
      }

      // retrieve data format converter mapping
      if (dataFormatConverterElements.size() > 0) {
        for (Element dataFormatConverterElement : dataFormatConverterElements) {
          String dataFormatConverterClassName = getElementbyId(root,
              dataFormatConverterElement.getAttributeValue("ref")).getAttributeValue(
              "className");
          ArrayList<String> dataSourceServiceClassNames = new ArrayList<String>();
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
      }

      // retrieve web services
      if (webServicesElement != null) {
        for (Element webService : (List<Element>) webServicesElement.getChildren()) {
          webServicesMapping.put(webService.getName(), webService.getValue());
        }
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
   * Returns the SIMPLCoreConfig singleton instance.
   * 
   * @return
   */
  public static SIMPLCoreConfig getInstance() {
    return SIMPLCoreConfig.instance;
  }

  /**
   * Returns a list of registered DataSourcePlugins. The list contains full qualified
   * names of DataSourcePlugin classes.
   * 
   * @return List of DataSourcePlugins
   */
  public List<String> getDataSourceServicePlugins() {
    List<String> dataSourceServicePlugins = SIMPLResourceManagement.getInstance()
        .getDataSourceServicePlugins();

    if (dataSourceServicePlugins.size() == 0) {
      dataSourceServicePlugins = this.dataSourceServicePlugins;
    }

    return dataSourceServicePlugins;
  }

  /**
   * Returns a list of registered DataFormatPlugins. The list contains full qualified
   * names of DataFormatPlugin classes.
   * 
   * @return List of DataFormatPlugins
   */
  public List<String> getDataFormatPlugins() {
    List<String> dataFormatPlugins = SIMPLResourceManagement.getInstance()
        .getDataFormatPlugins();

    if (dataFormatPlugins.size() == 0) {
      dataFormatPlugins = this.dataFormatPlugins;
    }

    return dataFormatPlugins;
  }

  /**
   * Returns a list of registered DataFormatPlugins. The list contains full qualified
   * names of DataConverterPlugin classes.
   * 
   * @return List of DataFormatPlugins
   */
  public List<String> getDataFormatConverterPlugins() {
    List<String> dataFormatConverterPlugins = SIMPLResourceManagement.getInstance()
        .getDataFormatConverterPlugins();

    if (dataFormatConverterPlugins.size() == 0) {
      dataFormatConverterPlugins = this.dataFormatConverterPlugins;
    }

    return dataFormatConverterPlugins;
  }

  /**
   * Returns a map of data format and their supported data source services, key and values
   * are full qualified class names.
   * 
   * @return data format mapping
   */
  public HashMap<String, ArrayList<String>> getDataFormatMapping() {
    HashMap<String, ArrayList<String>> dataFormatMapping = SIMPLResourceManagement
        .getInstance().getDataFormatMapping();

    if (dataFormatMapping.size() == 0) {
      dataFormatMapping = this.dataFormatMapping;
    }

    return dataFormatMapping;
  }

  /**
   * Returns a map of data format converters and their supported data source services, key
   * and values are full qualified class names.
   * 
   * @return data format converter mapping
   */
  public HashMap<String, ArrayList<String>> getDataFormatConverterMapping() {
    HashMap<String, ArrayList<String>> dataFormatConverterMapping = SIMPLResourceManagement
        .getInstance().getDataFormatConverterMapping();

    if (dataFormatConverterMapping.size() == 0) {
      dataFormatConverterMapping = this.dataFormatConverterMapping;
    }

    return dataFormatConverterMapping;
  }

  /**
   * Returns the address of a SIMPL Core needed web service.
   * 
   * @param webService
   *          Web service name
   * @return
   */
  public String getWebServiceAddress(String webService) {
    return webServicesMapping.get(webService);
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