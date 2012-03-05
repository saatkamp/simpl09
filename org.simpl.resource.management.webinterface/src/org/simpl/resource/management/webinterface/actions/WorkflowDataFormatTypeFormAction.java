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
import org.simpl.resource.management.data.TypeDefinition;
import org.simpl.resource.management.webinterface.FormMetaData;
import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the datasourcereferencetype_form.jsp. <br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: DataSourceReferenceTypeFormAction.java 1967 2011-10-30 12:54:52Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@SuppressWarnings("serial")
public class WorkflowDataFormatTypeFormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public WorkflowDataFormatTypeFormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);
    
    if (parameters.get("workflowDataFormatTypeFormSubmit").equals("Save") && parameters.get("id").equals("")) {
      if (this.save(parameters)) {
        response.sendRedirect("workflowdataformattype_list.jsp?message=Successfully created workflow data format type");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("workflowdataformattype_list.jsp?message=Failed to create workflow data format type");
      }
    } else if (parameters.get("workflowDataFormatTypeFormSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response.sendRedirect("workflowdataformattype_list.jsp?message=Successfully updated workflow data format type");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("workflowdataformattype_list.jsp?message=Failed to update workflow data format type");
      }
    } else if (parameters.get("workflowDataFormatTypeFormSubmit").equals("Cancel")) {
      response.sendRedirect("workflowdataformattype_list.jsp");
    }
  }

  /**
   * Adds a new data source reference type
   * 
   * @param parameters
   * @return
   */
  private boolean save(HashMap<String, String> parameters) {
    boolean success = false;
    TypeDefinition newTypeDefinition = parametersToTypeDefinition(parameters);

    try {
      success = resourceManagementService.addWorkflowDataFormatType(newTypeDefinition);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Updates a data source reference type.
   * 
   * @param parameters
   * @return
   */
  private boolean update(HashMap<String, String> parameters) {
    boolean success = false;
    TypeDefinition typeDefinition = parametersToTypeDefinition(parameters);

    try {
      success = resourceManagementService.updateWorkflowDataFormatType(typeDefinition);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Creates a data source reference type object from form parameters.
   * 
   * @param parameters
   * @return
   */
  private TypeDefinition parametersToTypeDefinition(HashMap<String, String> parameters) {
    TypeDefinition typeDefinition = new TypeDefinition();

    // initialize data converter
    typeDefinition.setId(parameters.get("id"));
    typeDefinition.setName(parameters.get("name"));

    // use existing XSD type data if no file is chosen
    if (!parameters.get("xsdType").equals("")) {
      typeDefinition.setXsdType(parameters.get("xsdType").replace("'", "\\'"));
    } else {
      typeDefinition.setXsdType(parameters.get("xsdTypeData").replace("'", "\\'"));
    }

    return typeDefinition;
  }
}