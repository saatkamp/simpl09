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
 * <b>Purpose:</b>Receiver for actions from the datasourcereferencetype_list.jsp. <br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: DataSourceReferenceTypeListAction.java 1967 2011-10-30 12:54:52Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@SuppressWarnings("serial")
public class WorkflowDataFormatTypeListAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public WorkflowDataFormatTypeListAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = null;
    String nextJSP = null;

    if (request.getParameter("workflowDataFormatTypeListSubmit").equals("Delete")) {
      if (this.delete(request)) {
        response
            .sendRedirect("workflowdataformattype_list.jsp?message=Successfully deleted workflow data format type");
      } else {
        response
            .sendRedirect("workflowdataformattype_list.jsp?message=Failed to delete workflow data format type");
      }
    } else if (request.getParameter("workflowDataFormatTypeListSubmit")
        .equals("Edit")) {
      if (request.getParameter("id") == null) {
        nextJSP = "/workflowdataformattype_list.jsp?message=Please select a workflow data format type";
      } else {
        nextJSP = "/workflowdataformattype_form.jsp";
      }

      dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    } else if (request.getParameter("workflowDataFormatTypeListSubmit").equals("New")) {
      nextJSP = "/workflowdataformattype_form.jsp";

      dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    }
  }

  private boolean delete(HttpServletRequest request) {
    boolean success = false;

    try {
      success = resourceManagementService.deleteWorkflowDataFormatType(Integer
          .valueOf(request.getParameter("id")));
    } catch (Exception_Exception e) {
      e.printStackTrace();
    }

    return success;
  }
}