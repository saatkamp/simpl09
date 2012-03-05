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
import org.simpl.resource.management.data.DataContainer;
import org.simpl.resource.management.webinterface.FormMetaData;
import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the datacontainer_form.jsp. <br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 */
@SuppressWarnings("serial")
public class DataContainerFormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public DataContainerFormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);

    if (parameters.get("dataContainerFormSubmit").equals("Save")
        && parameters.get("id").equals("")) {
      if (this.save(parameters)) {
        response
            .sendRedirect("datacontainer_list.jsp?message=Successfully created data container");
        FormMetaData.refresh();
      } else {
        response
            .sendRedirect("datacontainer_list.jsp?message=Failed to create data container");
      }
    } else if (parameters.get("dataContainerFormSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response
            .sendRedirect("datacontainer_list.jsp?message=Successfully updated data container");
        FormMetaData.refresh();
      } else {
        response
            .sendRedirect("datacontainer_list.jsp?message=Failed to update data container");
      }
    } else if (parameters.get("dataContainerFormSubmit").equals("Cancel")) {
      response.sendRedirect("datacontainer_list.jsp");
    }
  }

  /**
   * Adds a new data container.
   * 
   * @param parameters
   * @return
   */
  private boolean save(HashMap<String, String> parameters) {
    boolean success = false;
    DataContainer dataContainer = parametersToDataContainer(parameters);

    try {
      success = resourceManagementService.addDataContainer(dataContainer);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Updates a data container.
   * 
   * @param parameters
   * @return
   */
  private boolean update(HashMap<String, String> parameters) {
    boolean success = false;
    DataContainer dataContainer = parametersToDataContainer(parameters);

    try {
      success = resourceManagementService.updateDataContainer(dataContainer);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Creates a data container object from form parameters.
   * 
   * @param parameters
   * @return
   */
  private DataContainer parametersToDataContainer(
      HashMap<String, String> parameters) {
    DataContainer dataContainer = new DataContainer();

    // initialize data converter
    dataContainer.setId(parameters.get("id"));
    dataContainer.setLogicalName(parameters.get("logicalName"));

    // set data source id/name
    dataContainer.setDataSourceId(parameters.get("dataSourceId"));
    dataContainer.setDataSourceName(parameters.get("dataSourceName"));

    // use existing XML data if no file is chosen
    if (!parameters.get("localIdentifier").equals("")) {
      dataContainer.setLocalIdentifier(parameters.get("localIdentifier")
          .replace("'", "\\'"));
    } else {
      dataContainer.setLocalIdentifier(parameters.get("localIdentifierData")
          .replace("'", "\\'"));
    }

    return dataContainer;
  }
}