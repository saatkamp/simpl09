package org.simpl.resource.management.webinterface.actions;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpl.core.webservices.client.Converter;
import org.simpl.core.webservices.client.DataFormat;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.webinterface.FormMetaData;
import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the converter_form.jsp. <br>
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
public class ConverterFormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public ConverterFormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);
    
    if (parameters.get("converterFormSubmit").equals("Save") && parameters.get("id").equals("")) {
      if (this.save(parameters)) {
        response.sendRedirect("converter_list.jsp?message=Successfully created converter");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("converter_list.jsp?message=Failed to create converter");
      }
    } else if (parameters.get("converterFormSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response.sendRedirect("converter_list.jsp?message=Successfully updated converter");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("converter_list.jsp?message=Failed to update converter");
      }
    } else if (parameters.get("converterFormSubmit").equals("Cancel")) {
      response.sendRedirect("converter_list.jsp");
    }
  }

  /**
   * Adds a new converter
   * 
   * @param parameters
   * @return
   */
  private boolean save(HashMap<String, String> parameters) {
    boolean success = false;
    Converter newConverter = parametersToConverter(parameters);

    try {
      success = resourceManagementService.addConverter(newConverter);
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
    Converter converter = parametersToConverter(parameters);

    try {
      success = resourceManagementService.updateConverter(converter);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Creates a converter object from form parameters.
   * 
   * @param parameters
   * @return
   */
  private Converter parametersToConverter(HashMap<String, String> parameters) {
    Converter converter = new Converter();
    DataFormat connectorDataFormat = new DataFormat();
    DataFormat workflowDataFormat = new DataFormat();
    
    // initialize converter
    converter.setId(parameters.get("id"));
    converter.setName(parameters.get("name"));
    converter.setImplementation(parameters.get("implementation"));
    connectorDataFormat.setName(parameters.get("connectorDataFormat"));
    workflowDataFormat.setName(parameters.get("workflowDataFormat"));   
    converter.setConnectorDataFormat(connectorDataFormat);
    converter.setWorkflowDataFormat(workflowDataFormat);
    
    return converter;
  }
}