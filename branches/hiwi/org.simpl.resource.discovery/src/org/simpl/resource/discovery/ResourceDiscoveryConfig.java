package org.simpl.resource.discovery;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * <b>Purpose:</b>Config file for the Resource Discovery.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ResourceDiscoveryConfig {
  /**
   * config file.
   */
  private static final String CONFIG_FILE_NAME = "simpl-resource-discovery-config.xml";

  /**
   * Config file location from Apache Tomcat \.
   */
  private static final String CONFIG_FILE_LOCATION_1 = System.getProperty("user.dir")
      + "\\webapps\\axis2\\WEB-INF\\conf\\" + ResourceDiscoveryConfig.CONFIG_FILE_NAME;

  /**
   * Config file location from Apache Tomcat \bin.
   */
  private static final String CONFIG_FILE_LOCATION_2 = System.getProperty("user.dir")
      + "\\..\\webapps\\axis2\\WEB-INF\\conf\\"
      + ResourceDiscoveryConfig.CONFIG_FILE_NAME;

  /**
   * Config singleton instance.
   */
  private static final ResourceDiscoveryConfig instance = new ResourceDiscoveryConfig();

  Element resourceManagementElement = null;

  /**
   * Reads the config file into variables.
   */
  private ResourceDiscoveryConfig() {
    InputStream in = null;
    Document configDoc = null;
    Element root = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    try {
      in = new FileInputStream(ResourceDiscoveryConfig.CONFIG_FILE_LOCATION_1);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(ResourceDiscoveryConfig.CONFIG_FILE_LOCATION_2);
      } catch (FileNotFoundException e1) {
        try {
          in = new FileInputStream(ResourceDiscoveryConfig.CONFIG_FILE_NAME);
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
      resourceManagementElement = root.getChild("ResourceManagement");
    } catch (JDOMException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static ResourceDiscoveryConfig getInstance() {
    return ResourceDiscoveryConfig.instance;
  }

  public String getAddress() {
    return resourceManagementElement.getChild("address").getText();
  }
}