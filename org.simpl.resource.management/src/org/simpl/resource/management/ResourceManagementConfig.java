package org.simpl.resource.management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataConverter;
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
  
  private String filePath = "./simpl-resource-management-config.xml";

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
      filePath = ResourceManagementConfig.CONFIG_FILE_LOCATION_1;
    } catch (FileNotFoundException e) {
      try {
        System.out.println("Loading " + CONFIG_FILE_NAME + " from '" + ResourceManagementConfig.CONFIG_FILE_LOCATION_2 + "'.");
        in = new FileInputStream(ResourceManagementConfig.CONFIG_FILE_LOCATION_2);
        filePath = ResourceManagementConfig.CONFIG_FILE_LOCATION_2;
      } catch (FileNotFoundException e1) {
        try {
          System.out.println("Loading " + CONFIG_FILE_NAME + " from '" + ResourceManagementConfig.CONFIG_FILE_NAME + "'.");
          in = new FileInputStream(ResourceManagementConfig.CONFIG_FILE_NAME);
          filePath = ResourceManagementConfig.CONFIG_FILE_NAME;
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
    
    dataConverter.setWorkflowDataFormat(dataSourceElement.getChildText("dataFormat"));
    
    connector.setDataConverter(dataConverter);
    dataSource.setAuthentication(authentication);
    dataSource.setConnector(connector);
    
    return dataSource;
  }
  
  //this method changes the 'simpl-resource-management-config.xml' file
  //at the end, when write operation was successful, the 'dataSourceElement'
  //variable gets a new value
  //because of that, no restart of the server in necessary (the new value will be returned directly)
	public Boolean updateConfig(DataSource rmDataSource) {
		Boolean success = false;
		InputStream in = null;
		Document configDoc = null;
		SAXBuilder saxBuilder = new SAXBuilder();

		Element localDataSourceElement = null;
		Element authentication = null;

		FileOutputStream out = null;

		try {
			in = new FileInputStream(filePath);
			configDoc = saxBuilder.build(in);
			localDataSourceElement = configDoc.getRootElement().getChild("DataSource");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//set new values
		localDataSourceElement.getChild("name").setText(rmDataSource.getName());
		localDataSourceElement.getChild("address").setText(rmDataSource.getAddress());
		localDataSourceElement.getChild("type").setText(rmDataSource.getType());
		localDataSourceElement.getChild("subType").setText(rmDataSource.getSubType());
		localDataSourceElement.getChild("dataFormat").setText(
				rmDataSource.getConnector().getDataConverter()
						.getWorkflowDataFormat());
		authentication = localDataSourceElement.getChild("Authentication");
		Authentication a = rmDataSource.getAuthentication();
		authentication.getChild("user").setText(a.getUser());
		authentication.getChild("password").setText(a.getPassword());
		//write data to to file
		
		try {
			out = new FileOutputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			XMLOutputter serializer = new XMLOutputter();
			serializer.output(configDoc, out);
			out.flush();
			out.close();
			//assign new value
			dataSourceElement = localDataSourceElement;
			success = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
  }
}