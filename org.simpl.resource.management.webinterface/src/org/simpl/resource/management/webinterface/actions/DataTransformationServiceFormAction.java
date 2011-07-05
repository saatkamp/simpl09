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
import org.simpl.resource.management.data.DataConverter;
import org.simpl.resource.management.data.DataTransformationService;
import org.simpl.resource.management.webinterface.FormMetaData;
import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the datatransformationservice_form.jsp. <br>
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
public class DataTransformationServiceFormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public DataTransformationServiceFormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);
    
    if (parameters.get("dataTransformationServiceFormSubmit").equals("Save") && parameters.get("id").equals("")) {
      if (this.save(parameters)) {
        response.sendRedirect("datatransformationservice_list.jsp?message=Successfully created data transformation service");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("datatransformationservice_list.jsp?message=Failed to create data transformation service");
      }
    } else if (parameters.get("dataTransformationServiceFormSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response.sendRedirect("datatransformationservice_list.jsp?message=Successfully updated data transformation service");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("datatransformationservice_list.jsp?message=Failed to update data transformation service");
      }
    } else if (parameters.get("dataTransformationServiceFormSubmit").equals("Cancel")) {
      response.sendRedirect("datatransformationservice_list.jsp");
    }
  }

  /**
   * Adds a new data transformation service
   * 
   * @param parameters
   * @return
   */
  private boolean save(HashMap<String, String> parameters) {
    boolean success = false;
    DataTransformationService newDataTransformationService = parametersToDataTransformationService(parameters);

    try {
      success = resourceManagementService.addDataTransformationService(newDataTransformationService);
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
    DataTransformationService dataTransformationService = parametersToDataTransformationService(parameters);

    try {
      success = resourceManagementService.updateDataTransformationService(dataTransformationService);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Creates a data transformation service object from form parameters.
   * 
   * @param parameters
   * @return
   */
  private DataTransformationService parametersToDataTransformationService(HashMap<String, String> parameters) {
    DataTransformationService datatransformationservice = new DataTransformationService();
    DataConverter connectorDataConverter = new DataConverter();
    DataConverter workflowDataConverter = new DataConverter();
    
    // initialize data transformation service
    datatransformationservice.setId(parameters.get("id"));
    datatransformationservice.setName(parameters.get("name"));
    datatransformationservice.setImplementation(parameters.get("implementation"));
    connectorDataConverter.setDataFormat(parameters.get("connectorDataFormat"));
    workflowDataConverter.setDataFormat(parameters.get("workflowDataFormat"));   
    datatransformationservice.setConnectorDataConverter(connectorDataConverter);
    datatransformationservice.setWorkflowDataConverter(workflowDataConverter);
    
    return datatransformationservice;
  }
}