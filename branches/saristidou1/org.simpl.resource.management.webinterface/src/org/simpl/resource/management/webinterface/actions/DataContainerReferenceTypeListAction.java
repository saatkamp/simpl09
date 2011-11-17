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
 * <b>Purpose:</b>Receiver for actions from the datacontainerreferencetype_list.jsp. <br>
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
public class DataContainerReferenceTypeListAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public DataContainerReferenceTypeListAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = null;
    String nextJSP = null;

    if (request.getParameter("dataContainerReferenceTypeListSubmit").equals("Delete")) {
      if (this.delete(request)) {
        response
            .sendRedirect("datacontainerreferencetype_list.jsp?message=Successfully deleted data container reference type");
      } else {
        response
            .sendRedirect("datacontainerreferencetype_list.jsp?message=Failed to delete data container reference type");
      }
    } else if (request.getParameter("dataContainerReferenceTypeListSubmit")
        .equals("Edit")) {
      if (request.getParameter("id") == null) {
        nextJSP = "/datacontainerreferencetype_list.jsp?message=Please select a data container reference type";
      } else {
        nextJSP = "/datacontainerreferencetype_form.jsp";
      }

      dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    } else if (request.getParameter("dataContainerReferenceTypeListSubmit").equals("New")) {
      nextJSP = "/datacontainerreferencetype_form.jsp";

      dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    }
  }

  private boolean delete(HttpServletRequest request) {
    boolean success = false;

    try {
      success = resourceManagementService.deleteDataContainerReferenceType(Integer
          .valueOf(request.getParameter("id")));
    } catch (Exception_Exception e) {
      e.printStackTrace();
    }

    return success;
  }
}