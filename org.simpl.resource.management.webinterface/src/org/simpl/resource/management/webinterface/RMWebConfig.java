package org.simpl.resource.management.webinterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class RMWebConfig {
  /**
   * config file.
   */
  private static final String CONFIG_FILE_NAME = "rmweb-config.xml";

  /**
   * Config file location from Apache Tomcat \webapps.
   */
  private static final String CONFIG_FILE_LOCATION_1 = System.getProperty("user.dir")
      + "\\webapps\\rmweb\\WEB-INF\\conf\\" + RMWebConfig.CONFIG_FILE_NAME;

  /**
   * Config file location from Apache Tomcat \bin.
   */
  private static final String CONFIG_FILE_LOCATION_2 = System.getProperty("user.dir")
      + "\\..\\webapps\\rmweb\\WEB-INF\\conf\\" + RMWebConfig.CONFIG_FILE_NAME;

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

    try {
      in = new FileInputStream(RMWebConfig.CONFIG_FILE_LOCATION_1);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(RMWebConfig.CONFIG_FILE_LOCATION_2);
      } catch (FileNotFoundException e1) {
        try {
          in = new FileInputStream(RMWebConfig.CONFIG_FILE_NAME);
        } catch (FileNotFoundException e2) {
          System.out.println(RMWebConfig.CONFIG_FILE_LOCATION_1 + " not found");
          System.out.println(RMWebConfig.CONFIG_FILE_LOCATION_2 + " not found");
          
          e2.printStackTrace();
        }
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
    return resourceManagementElement.getChildText("address");
  }

  public String getDataSourceServiceAddress() {
    return dataSourceServiceElement.getChildText("address");
  }
}