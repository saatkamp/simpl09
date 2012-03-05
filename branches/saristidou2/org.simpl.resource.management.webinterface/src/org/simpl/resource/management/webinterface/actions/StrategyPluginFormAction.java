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
import org.simpl.resource.management.data.StrategyPlugin;
import org.simpl.resource.management.webinterface.FormMetaData;
import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;

/**
 * <b>Purpose:</b>Receiver for actions from the strategyplugin_form.jsp. <br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: StrategyPluginFormAction.java 1967 2011-10-30 12:54:52Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@SuppressWarnings("serial")
public class StrategyPluginFormAction extends HttpServlet {
  private ResourceManagement resourceManagementService = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());

  public StrategyPluginFormAction() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<String, String> parameters = MultipartForm.getParameters(request);
    
    if (parameters.get("strategyPluginFormSubmit").equals("Save") && parameters.get("id").equals("")) {
      if (this.save(parameters)) {
        response.sendRedirect("strategyplugin_list.jsp?message=Successfully created strategy plug-in");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("strategyplugin_list.jsp?message=Failed to create strategy plug-in");
      }
    } else if (parameters.get("strategyPluginFormSubmit").equals("Save")) {
      if (this.update(parameters)) {
        response.sendRedirect("strategyplugin_list.jsp?message=Successfully updated strategy plug-in");
        FormMetaData.refresh();
      } else {
        response.sendRedirect("strategyplugin_list.jsp?message=Failed to update strategy plug-in");
      }
    } else if (parameters.get("strategyPluginFormSubmit").equals("Cancel")) {
      response.sendRedirect("strategyplugin_list.jsp");
    }
  }

  /**
   * Adds a new strategy plug-in
   * 
   * @param parameters
   * @return
   */
  private boolean save(HashMap<String, String> parameters) {
    boolean success = false;
    StrategyPlugin newStrategyPlugin = parametersToStrategyPlugin(parameters);

    try {
      success = resourceManagementService.addStrategyPlugin(newStrategyPlugin);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Updates a strategy plug-in.
   * 
   * @param parameters
   * @return
   */
  private boolean update(HashMap<String, String> parameters) {
    boolean success = false;
    StrategyPlugin strategyPlugin = parametersToStrategyPlugin(parameters);

    try {
      success = resourceManagementService.updateStrategyPlugin(strategyPlugin);
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return success;
  }

  /**
   * Creates a strategy plug-in object from form parameters.
   * 
   * @param parameters
   * @return
   */
  private StrategyPlugin parametersToStrategyPlugin(HashMap<String, String> parameters) {
    StrategyPlugin strategyplugin = new StrategyPlugin();
    
    // initialize strategy plug-in
    strategyplugin.setId(parameters.get("id"));
    strategyplugin.setName(parameters.get("name"));
    strategyplugin.setImplementation(parameters.get("implementation"));
    
    return strategyplugin;
  }
}