package org.simpl.resource.management.webinterface;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpl.core.webservices.client.Authentication;
import org.simpl.core.webservices.client.DataSource;
import org.simpl.core.webservices.client.LateBinding;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;

/**
 * <b>Purpose:</b>Receiver for actions from the form.jsp. <br>
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
public class FormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public FormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);
    
    if (parameters.get("formSubmit").equals("Save") && parameters.get("id").equals("")) {
      if (this.save(parameters)) {
        response.sendRedirect("index.jsp?message=Successfully created data source.");
      } else {
        response.sendRedirect("index.jsp?message=Failed to create data source.");
      }
    } else if (parameters.get("formSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response.sendRedirect("index.jsp?message=Successfully updated data source.");
      } else {
        response.sendRedirect("index.jsp?message=Failed to update data source.");
      }
    } else if (parameters.get("formSubmit").equals("Cancel")) {
      response.sendRedirect("index.jsp");
    }
  }

  /**
   * Adds a new data source.
   * 
   * @param parameters
   * @return
   */
  private boolean save(HashMap<String, String> parameters) {
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
   * Deletes a data source.
   * 
   * @param parameters
   * @return
   */
  private DataSource parametersToDataSource(HashMap<String, String> parameters) {
    DataSource dataSource = new DataSource();

    // initialize authentication and late binding
    Authentication auth = new Authentication();
    LateBinding lateBinding = new LateBinding();

    auth.setPassword(parameters.get("password"));
    auth.setUser(parameters.get("username"));
    
    // use existing policy data if no file is chosen
    if (!parameters.get("policy").equals("")) {
      lateBinding.setPolicy(parameters.get("policy"));
    } else {
      lateBinding.setPolicy(parameters.get("policyData"));
    }

    // use existing connector properties data if no file is chosen
    if (!parameters.get("connectorProperties").equals("")) {
      dataSource.setConnectorPropertiesDescription(parameters.get("connectorProperties"));
    } else {
      if (!parameters.get("oldConnectorPropertiesData").equals(parameters.get("connectorPropertiesData"))) {
        dataSource.setConnectorPropertiesDescription(parameters.get("connectorPropertiesData"));
      } else {
        // if the properties description data didn't change, the properties are regenerated from the form fields
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
    dataSource.setDataFormatName(parameters.get("dataformat"));
    dataSource.setAuthentication(auth);
    dataSource.setLateBinding(lateBinding);

    return dataSource;
  }
}