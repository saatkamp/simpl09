package org.simpl.resource.management;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataFormat;
import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose:</b>Config file for the Resource Management.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ResourceManagementConfig {
  /**
   * config file.
   */
  private static final String CONFIG_FILE_NAME = "simpl-resource-management-config.xml";

  /**
   * Config file location from Apache Tomcat \.
   */
  private static final String CONFIG_FILE_LOCATION_1 = System.getProperty("user.dir")
      + "/webapps/axis2/WEB-INF/conf/" + ResourceManagementConfig.CONFIG_FILE_NAME;

  /**
   * Config file location from Apache Tomcat \bin.
   */
  private static final String CONFIG_FILE_LOCATION_2 = System.getProperty("user.dir")
      + "/../webapps/axis2/WEB-INF/conf/"
      + ResourceManagementConfig.CONFIG_FILE_NAME;

  /**
   * Config singleton instance.
   */
  private static final ResourceManagementConfig instance = new ResourceManagementConfig();

  Element dataSourceElement = null;

  /**
   * Reads the config file into variables.
   */
  private ResourceManagementConfig() {
    InputStream in = null;
    Document configDoc = null;
    Element root = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    try {
      System.out.println("Loading " + CONFIG_FILE_NAME + " from '" + ResourceManagementConfig.CONFIG_FILE_LOCATION_1 + "'.");
      in = new FileInputStream(ResourceManagementConfig.CONFIG_FILE_LOCATION_1);
    } catch (FileNotFoundException e) {
      try {
        System.out.println("Loading " + CONFIG_FILE_NAME + " from '" + ResourceManagementConfig.CONFIG_FILE_LOCATION_2 + "'.");
        in = new FileInputStream(ResourceManagementConfig.CONFIG_FILE_LOCATION_2);
      } catch (FileNotFoundException e1) {
        try {
          System.out.println("Loading " + CONFIG_FILE_NAME + " from '" + ResourceManagementConfig.CONFIG_FILE_NAME + "'.");
          in = new FileInputStream(ResourceManagementConfig.CONFIG_FILE_NAME);
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
      dataSourceElement = root.getChild("DataSource");
    } catch (JDOMException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static ResourceManagementConfig getInstance() {
    return ResourceManagementConfig.instance;
  }

  public DataSource getDataSource() {
    DataSource dataSource = new DataSource();
    Connector connector = new Connector();
    DataFormat converterDataFormat = new DataFormat();
    Authentication authentication = new Authentication();

    authentication.setUser(dataSourceElement.getChild("Authentication").getChildText(
        "user"));
    authentication.setPassword(dataSourceElement.getChild("Authentication").getChildText(
        "password"));

    dataSource.setName(dataSourceElement.getChildText("name"));
    dataSource.setAddress(dataSourceElement.getChildText("address"));
    dataSource.setType(dataSourceElement.getChildText("type"));
    dataSource.setSubType(dataSourceElement.getChildText("subType"));
    
    converterDataFormat.setName(dataSourceElement.getChildText("dataFormat"));
    
    connector.setConverterDataFormat(converterDataFormat);
    dataSource.setAuthentication(authentication);
    dataSource.setConnector(connector);
    
    return dataSource;
  }
}