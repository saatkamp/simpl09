package org.simpl.resource.management.webinterface.actions;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataConverter;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.webinterface.FormMetaData;
import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the datasource_form.jsp. <br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@SuppressWarnings("serial")
public class DataSourceFormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public DataSourceFormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);
    
    if (parameters.get("dataSourceFormSubmit").equals("Save") && parameters.get("id").equals("")) {
      if (this.add(parameters)) {
        response.sendRedirect("datasource_list.jsp?message=Successfully created data source");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("datasource_list.jsp?message=Failed to create data source");
      }
    } else if (parameters.get("dataSourceFormSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response.sendRedirect("datasource_list.jsp?message=Successfully updated data source");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("datasource_list.jsp?message=Failed to update data source");
      }
    } else if (parameters.get("dataSourceFormSubmit").equals("Cancel")) {
      response.sendRedirect("datasource_list.jsp");
    }
  }

  /**
   * Adds a new data source.
   * 
   * @param parameters
   * @return
   */
  private boolean add(HashMap<String, String> parameters) {
    boolean success = false;
    DataSource newDataSource = parametersToDataSource(parameters);

    try {
      success = resourceManagementService.addDataSource(newDataSource);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Updates a data source.
   * 
   * @param parameters
   * @return
   */
  private boolean update(HashMap<String, String> parameters) {
    boolean success = false;
    DataSource dataSource = parametersToDataSource(parameters);

    try {
      success = resourceManagementService.updateDataSource(dataSource);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Creates a data source object from form parameters.
   * 
   * @param parameters
   * @return
   */
  private DataSource parametersToDataSource(HashMap<String, String> parameters) {
    DataSource dataSource = new DataSource();
    Connector connector = new Connector();
    DataConverter dataConverter = new DataConverter();
    
    // initialize authentication and late binding
    Authentication auth = new Authentication();

    auth.setPassword(parameters.get("password"));
    auth.setUser(parameters.get("username"));
    
    // use existing propertiesDescription data if no file is chosen
    if (!parameters.get("propertiesDescription").equals("")) {
      dataSource.setPropertiesDescription(parameters.get("propertiesDescription"));
    } else {
      dataSource.setPropertiesDescription(parameters.get("propertiesDescriptionData"));
    }

    // use existing connector properties data if no file is chosen
    if (!parameters.get("connectorProperties").equals("")) {
      dataSource.setConnectorPropertiesDescription(parameters.get("connectorProperties"));
    } else {
      if (!parameters.get("oldConnectorPropertiesData").equals(parameters.get("connectorPropertiesData"))) {
        dataSource.setConnectorPropertiesDescription(parameters.get("connectorPropertiesData"));
      } else {
        // if the properties description data didn't change, it will be regenerated from the form fields
        dataSource.setConnectorPropertiesDescription("");
      }
    }
    
    // initialize data source
    dataSource.setId(parameters.get("id"));
    dataSource.setName(parameters.get("name"));
    dataSource.setAddress(parameters.get("address"));
    dataSource.setType(parameters.get("type"));
    dataSource.setSubType(parameters.get("subtype"));
    dataSource.setLanguage(parameters.get("language"));
    
    dataConverter.setDataFormat(parameters.get("dataformat"));
    connector.setDataConverter(dataConverter);
    
    dataSource.setConnector(connector);
    dataSource.setAuthentication(auth);

    return dataSource;
  }
}