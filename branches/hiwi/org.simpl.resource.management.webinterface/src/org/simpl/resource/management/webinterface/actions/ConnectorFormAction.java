package org.simpl.resource.management.webinterface.actions;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpl.resource.management.client.Connector;
import org.simpl.resource.management.client.DataFormat;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.webinterface.FormMetaData;
import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the connector_form.jsp. <br>
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
public class ConnectorFormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public ConnectorFormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);
    
    if (parameters.get("connectorFormSubmit").equals("Save") && parameters.get("id").equals("")) {
      if (this.save(parameters)) {
        response.sendRedirect("connector_list.jsp?message=Successfully created connector");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("connector_list.jsp?message=Failed to create connector");
      }
    } else if (parameters.get("connectorFormSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response.sendRedirect("connector_list.jsp?message=Successfully updated connector");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("connector_list.jsp?message=Failed to update connector");
      }
    } else if (parameters.get("connectorFormSubmit").equals("Cancel")) {
      response.sendRedirect("connector_list.jsp");
    }
  }

  /**
   * Adds a new connector
   * 
   * @param parameters
   * @return
   */
  private boolean save(HashMap<String, String> parameters) {
    boolean success = false;
    Connector newConnector = parametersToConnector(parameters);

    try {
      success = resourceManagementService.addConnector(newConnector);
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
    Connector connector = parametersToConnector(parameters);

    try {
      success = resourceManagementService.updateConnector(connector);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Creates a connector object from form parameters.
   * 
   * @param parameters
   * @return
   */
  private Connector parametersToConnector(HashMap<String, String> parameters) {
    Connector connector = new Connector();
    DataFormat converterDataFormat = new DataFormat();
   
    // use existing connector properties data if no file is chosen
    if (!parameters.get("properties").equals("")) {
      connector.setPropertiesDescription(parameters.get("properties"));
    } else {
      if (!parameters.get("oldPropertiesData").equals(parameters.get("propertiesData"))) {
        connector.setPropertiesDescription(parameters.get("propertiesData"));
      } else {
        // if the properties description data didn't change, it will be regenerated from the form fields
        connector.setPropertiesDescription("");
      }
    }
    
    // initialize connector
    connector.setId(parameters.get("id"));
    connector.setName(parameters.get("name"));
    connector.setImplementation(parameters.get("implementation"));
    connector.setType(parameters.get("type"));
    connector.setSubType(parameters.get("subtype"));
    connector.setLanguage(parameters.get("language"));
    
    converterDataFormat.setName(parameters.get("dataformat"));
    connector.setConverterDataFormat(converterDataFormat);

    return connector;
  }
}