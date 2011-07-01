package web.service.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import web.service.data.db.Authentication;
import web.service.data.db.Connector;
import web.service.data.db.DataConverter;
import web.service.data.db.DataSource;

/**
 * <b>Purpose:</b>Config file for the "Web Service Data" Web Service.<br>
 * <b>Description:</b><br>
 * <b>Company:</b>IPVS<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 */
public class WebServiceDataConfig {
  /**
   * config file.
   */
  private static final String CONFIG_FILE_NAME = "web-service-data-config.xml";

  /**
   * Config file location from Apache Tomcat \.
   */
  private static final String CONFIG_FILE_LOCATION_1 = System.getProperty("user.dir")
      + "/webapps/axis2/WEB-INF/conf/" + WebServiceDataConfig.CONFIG_FILE_NAME;

  /**
   * Config file location from Apache Tomcat \bin.
   */
  private static final String CONFIG_FILE_LOCATION_2 = System.getProperty("user.dir")
      + "/../webapps/axis2/WEB-INF/conf/"
      + WebServiceDataConfig.CONFIG_FILE_NAME;

  /**
   * Config singleton instance.
   */
  private static final WebServiceDataConfig instance = new WebServiceDataConfig();

  Element dataSourceElement = null;

  /**
   * Reads the config file into variables.
   */
  private WebServiceDataConfig() {
    InputStream in = null;
    Document configDoc = null;
    Element root = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    try {
      System.out.println("Loading " + CONFIG_FILE_NAME + " from '" + WebServiceDataConfig.CONFIG_FILE_LOCATION_1 + "'.");
      in = new FileInputStream(WebServiceDataConfig.CONFIG_FILE_LOCATION_1);
    } catch (FileNotFoundException e) {
      try {
        System.out.println("Loading " + CONFIG_FILE_NAME + " from '" + WebServiceDataConfig.CONFIG_FILE_LOCATION_2 + "'.");
        in = new FileInputStream(WebServiceDataConfig.CONFIG_FILE_LOCATION_2);
      } catch (FileNotFoundException e1) {
        try {
          System.out.println("Loading " + CONFIG_FILE_NAME + " from '" + WebServiceDataConfig.CONFIG_FILE_NAME + "'.");
          in = new FileInputStream(WebServiceDataConfig.CONFIG_FILE_NAME);
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

  public static WebServiceDataConfig getInstance() {
    return WebServiceDataConfig.instance;
  }

  public DataSource getDataSource() {
    DataSource dataSource = new DataSource();
    Connector connector = new Connector();
    DataConverter dataConverter = new DataConverter();
    Authentication authentication = new Authentication();

    authentication.setUser(dataSourceElement.getChild("Authentication").getChildText(
        "user"));
    authentication.setPassword(dataSourceElement.getChild("Authentication").getChildText(
        "password"));

    dataSource.setName(dataSourceElement.getChildText("name"));
    dataSource.setAddress(dataSourceElement.getChildText("address"));
    dataSource.setType(dataSourceElement.getChildText("type"));
    dataSource.setSubType(dataSourceElement.getChildText("subType"));
    
    dataConverter.setDataFormat(dataSourceElement.getChildText("dataFormat"));
    
    connector.setDataConverter(dataConverter);
    dataSource.setAuthentication(authentication);
    dataSource.setConnector(connector);
    
    return dataSource;
  }
}