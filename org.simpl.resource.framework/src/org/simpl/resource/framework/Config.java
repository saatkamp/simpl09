package org.simpl.resource.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.simpl.resource.framework.simpl.core.client.Authentication;
import org.simpl.resource.framework.simpl.core.client.DataSource;

public class Config {
  /**
   * config file.
   */
  private static final String CONFIG_FILE_NAME = "resource-framework-config.xml";

  /**
   * Config file location from Apache ODE \webapps.
   */
  private static final String CONFIG_FILE_LOCATION_1 = System.getProperty("user.dir")
      + "\\webapps\\ode\\WEB-INF\\conf\\" + Config.CONFIG_FILE_NAME;

  /**
   * Config file location from Apache ODE \bin.
   */
  private static final String CONFIG_FILE_LOCATION_2 = System.getProperty("user.dir")
      + "\\..\\webapps\\ode\\WEB-INF\\conf\\" + Config.CONFIG_FILE_NAME;

  /**
   * Config singleton instance.
   */
  private static final Config instance = new Config();
  
  Element dataSourceServiceElement = null;
  Element dataSourceElement = null;

  /**
   * Reads the config file into variables.
   */
  private Config() {
    InputStream in = null;
    Document configDoc = null;
    Element root = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    try {
      in = new FileInputStream(Config.CONFIG_FILE_LOCATION_1);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(Config.CONFIG_FILE_LOCATION_2);
      } catch (FileNotFoundException e1) {
        try {
          in = new FileInputStream(Config.CONFIG_FILE_NAME);
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

  public static Config getInstance() {
    return Config.instance;
  }

  public String getDataSourceServiceAddress() {
    return dataSourceServiceElement.getChildText("address");
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