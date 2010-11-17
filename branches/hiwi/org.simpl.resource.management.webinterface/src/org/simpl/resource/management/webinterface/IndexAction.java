package org.simpl.resource.management.webinterface;

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

/**
 * <b>Purpose:</b>Receiver for actions from the index.jsp. <br>
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
public class IndexAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public IndexAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = null;
    String nextJSP = null;
    
    if (request.getParameter("indexSubmit").equals("Delete")) {
      if (this.delete(request)) {
        response.sendRedirect("index.jsp?message=Successfully deleted data source");
      } else {
        response.sendRedirect("index.jsp?message=Failed to delete data source");
      }
    } else if (request.getParameter("indexSubmit").equals("Edit")) {
      if (request.getParameter("id") == null) {
        nextJSP = "/index.jsp?message=No datasource selected";
      } else {
        nextJSP = "/form.jsp";
      }

      dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    } else if (request.getParameter("indexSubmit").equals("New")) {
      nextJSP = "/form.jsp";
      
      dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
      dispatcher.forward(request, response);
    }
  }

  private boolean delete(HttpServletRequest request) {
    boolean success = false;

    try {
      success = resourceManagementService.deleteDataSource(Integer.valueOf(request
          .getParameter("id")));
    } catch (Exception_Exception e) {
      e.printStackTrace();
    }

    return success;
  }
}