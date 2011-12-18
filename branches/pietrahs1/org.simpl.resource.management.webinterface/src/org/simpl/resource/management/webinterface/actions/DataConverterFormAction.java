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
import org.simpl.resource.management.webinterface.FormMetaData;
import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the dataconverter_form.jsp. <br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: DataConverterFormAction.java 1822 2011-08-04 17:25:42Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@SuppressWarnings("serial")
public class DataConverterFormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public DataConverterFormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);
    
    if (parameters.get("dataConverterFormSubmit").equals("Save") && parameters.get("id").equals("")) {
      if (this.add(parameters)) {
        response.sendRedirect("dataconverter_list.jsp?message=Successfully created data converter");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("dataconverter_list.jsp?message=Failed to create data converter");
      }
    } else if (parameters.get("dataConverterFormSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response.sendRedirect("dataconverter_list.jsp?message=Successfully updated data converter");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("dataconverter_list.jsp?message=Failed to update data converter");
      }
    } else if (parameters.get("dataConverterFormSubmit").equals("Cancel")) {
      response.sendRedirect("dataconverter_list.jsp");
    }
  }

  /**
   * Adds a new data converter.
   * 
   * @param parameters
   * @return
   */
  private boolean add(HashMap<String, String> parameters) {
    boolean success = false;
    DataConverter newDataConverter = parametersToDataConverter(parameters);

    try {
      success = resourceManagementService.addDataConverter(newDataConverter);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Updates a data converter.
   * 
   * @param parameters
   * @return
   */
  private boolean update(HashMap<String, String> parameters) {
    boolean success = false;
    DataConverter dataConverter = parametersToDataConverter(parameters);

    try {
      success = resourceManagementService.updateDataConverter(dataConverter);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Creates a data converter object from form parameters.
   * 
   * @param parameters
   * @return
   */
  private DataConverter parametersToDataConverter(HashMap<String, String> parameters) {
    DataConverter dataConverter = new DataConverter();

    // initialize data converter
    dataConverter.setId(parameters.get("id"));
    dataConverter.setName(parameters.get("name"));
    dataConverter.setInputDataType(parameters.get("inputDataType"));
    dataConverter.setOutputDataType(parameters.get("outputDataType"));
    dataConverter.setWorkflowDataFormat(parameters.get("workflowDataFormat"));
    dataConverter.setDirectionOutputWorkflow(parameters.get("directionOutputWorkflow") != null ? "true" : "false");
    dataConverter.setDirectionWorkflowInput(parameters.get("directionWorkflowInput") != null ? "true" : "false");
    dataConverter.setImplementation(parameters.get("implementation"));
    
    return dataConverter;
  }
}