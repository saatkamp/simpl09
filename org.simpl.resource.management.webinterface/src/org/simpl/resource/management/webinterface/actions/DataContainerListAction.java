package org.simpl.resource.management.webinterface.actions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the datacontainer_list.jsp. <br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 */
@SuppressWarnings("serial")
public class DataContainerListAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public DataContainerListAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = null;
    String nextJSP = null;

    if (request.getParameter("dataContainerListSubmit").equals("Delete")) {
      if (this.delete(request)) {
        response
            .sendRedirect("datacontainer_list.jsp?message=Successfully deleted data container");
      } else {
        response
            .sendRedirect("daracontainer_list.jsp?message=Failed to delete data container");
      }
    } else if (request.getParameter("dataContainerListSubmit")
        .equals("Edit")) {
      if (request.getParameter("id") == null) {
        nextJSP = "/datacontainer_list.jsp?message=Please select a data container";
      } else {
        nextJSP = "/datacontainer_form.jsp";
      }

      dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    } else if (request.getParameter("dataContainerListSubmit").equals("New")) {
      nextJSP = "/datacontainer_form.jsp";

      dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    }
  }

  private boolean delete(HttpServletRequest request) {
    boolean success = false;

    try {
      success = resourceManagementService.deleteDataContainer(Integer
          .valueOf(request.getParameter("id")));
    } catch (Exception_Exception e) {
      e.printStackTrace();
    }

    return success;
  }
}