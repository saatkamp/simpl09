package org.simpl.resource.management.webinterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * <b>Purpose:</b>Offers access to the rmweb-config.xml config file and its data.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RMWebConfig {
  /**
   * Config file.
   */
  private static final String CONFIG_FILE_NAME = "rmweb-config.xml";

  /**
   * Config singleton instance.
   */
  private static final RMWebConfig instance = new RMWebConfig();

  Element dataSourceServiceElement = null;
  Element resourceManagementElement = null;

  /**
   * Reads the config file into variables.
   */
  private RMWebConfig() {
    InputStream in = null;
    Document configDoc = null;
    Element root = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // determine path to the rmweb-config.xml
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    String configFilePath = loader.getResource(
        RMWebConfig.class.getName().replace('.', '/') + ".class").getPath();

    configFilePath = configFilePath.substring(1, configFilePath.indexOf("WEB-INF") + 7)
        + "/conf/";

    try {
      in = new FileInputStream(configFilePath + CONFIG_FILE_NAME);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(RMWebConfig.CONFIG_FILE_NAME);
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
    }

    // retrieve information from the config file
    if (in != null) {
      try {
        // read xml config file
        configDoc = saxBuilder.build(in);
        root = configDoc.getRootElement();

        // read elements
        dataSourceServiceElement = root.getChild("DataSourceService");
        resourceManagementElement = root.getChild("ResourceManagement");
      } catch (JDOMException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static RMWebConfig getInstance() {
    return RMWebConfig.instance;
  }

  public String getResourceManagementAddress() {
    String address = null;

    if (resourceManagementElement != null) {
      address = resourceManagementElement.getChildText("address");
    }

    return address;
  }

  public String getDataSourceServiceAddress() {
    String address = null;

    if (dataSourceServiceElement != null) {
      address = dataSourceServiceElement.getChildText("address");
    }

    return address;
  }
}