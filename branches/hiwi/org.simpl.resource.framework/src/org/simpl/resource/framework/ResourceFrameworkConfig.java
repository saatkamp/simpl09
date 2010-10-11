package org.simpl.resource.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.simpl.core.webservices.client.Authentication;
import org.simpl.core.webservices.client.DataSource;

public class ResourceFrameworkConfig {
  /**
   * config file.
   */
  private static final String CONFIG_FILE_NAME = "simpl-resource-framework-config.xml";

  /**
   * Config file location from Apache ODE \webapps.
   */
  private static final String CONFIG_FILE_LOCATION_1 = System.getProperty("user.dir")
      + "\\webapps\\ode\\WEB-INF\\conf\\" + ResourceFrameworkConfig.CONFIG_FILE_NAME;

  /**
   * Config file location from Apache ODE \bin.
   */
  private static final String CONFIG_FILE_LOCATION_2 = System.getProperty("user.dir")
      + "\\..\\webapps\\ode\\WEB-INF\\conf\\" + ResourceFrameworkConfig.CONFIG_FILE_NAME;

  /**
   * Config singleton instance.
   */
  private static final ResourceFrameworkConfig instance = new ResourceFrameworkConfig();
  
  Element webServiceElement = null;
  Element dataSourceServiceElement = null;
  Element dataSourceElement = null;

  /**
   * Reads the config file into variables.
   */
  private ResourceFrameworkConfig() {
    InputStream in = null;
    Document configDoc = null;
    Element root = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    try {
      in = new FileInputStream(ResourceFrameworkConfig.CONFIG_FILE_LOCATION_1);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(ResourceFrameworkConfig.CONFIG_FILE_LOCATION_2);
      } catch (FileNotFoundException e1) {
        try {
          in = new FileInputStream(ResourceFrameworkConfig.CONFIG_FILE_NAME);
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
      webServiceElement = root.getChild("WebService");
      dataSourceServiceElement = root.getChild("DataSourceService");
      dataSourceElement = root.getChild("DataSource");
    } catch (JDOMException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static ResourceFrameworkConfig getInstance() {
    return ResourceFrameworkConfig.instance;
  }

  public String getWebServiceAddress() {
    return webServiceElement.getChildText("address");
  }

  public DataSource getDataSource() {
    DataSource dataSource = new DataSource();
    Authentication authentication = new Authentication();
    
    authentication.setUser(dataSourceElement.getChild("Authentication").getChildText("user"));
    authentication.setPassword(dataSourceElement.getChild("Authentication").getChildText("password"));
    
    dataSource.setName(dataSourceElement.getChildText("name"));
    dataSource.setAddress(dataSourceElement.getChildText("address"));
    dataSource.setType(dataSourceElement.getChildText("type"));
    dataSource.setSubType(dataSourceElement.getChildText("subType"));
    dataSource.setDataFormat(dataSourceElement.getChildText("dataFormat"));
    dataSource.setAuthentication(authentication);

    return dataSource;
  }
}