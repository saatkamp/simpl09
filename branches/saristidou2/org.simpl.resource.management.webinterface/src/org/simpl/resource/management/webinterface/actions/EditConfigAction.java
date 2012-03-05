package org.simpl.resource.management.webinterface.actions;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simpl.resource.management.webinterface.MultipartForm;
import org.simpl.resource.management.webinterface.RMWebConfig;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.ResourceManagementClient;
import org.simpl.resource.management.data.Authentication;
import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataConverter;
import org.simpl.resource.management.data.DataSource;

@SuppressWarnings("serial")
public class EditConfigAction extends HttpServlet {

	private ResourceManagement resourceManagementService = ResourceManagementClient
			.getService(RMWebConfig.getInstance()
					.getResourceManagementAddress());

	public EditConfigAction() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> parameters = MultipartForm.getParameters(request);
		if (parameters.get("editConfigSubmit").equals("Save")) {
			if (this.setConfig(parameters)) {
				response.sendRedirect("install.jsp?message=Successfully updated config file");
			} else {
				response.sendRedirect("install.jsp?message=Failed to update config file");
			}
		} else if (parameters.get("editConfigSubmit").equals("Cancel")) {
			response.sendRedirect("install.jsp?");
		}
	}

	private boolean setConfig(HashMap<String, String> parameters) {
		boolean success = false;
		//create new data source with the actual information
		DataSource dataSource = new DataSource();
		Connector connector = new Connector();
		DataConverter dataConverter = new DataConverter();
		Authentication authentication = new Authentication();

		authentication.setUser(parameters.get("user"));
		authentication.setPassword(parameters.get("password"));

		dataSource.setName(parameters.get("name"));
		dataSource.setAddress(parameters.get("address"));
		dataSource.setType(parameters.get("type"));
		dataSource.setSubType(parameters.get("subType"));

		dataConverter.setWorkflowDataFormat(parameters.get("dataFormat"));
		connector.setDataConverter(dataConverter);
		dataSource.setAuthentication(authentication);
		dataSource.setConnector(connector);
		//new information must be stored
		//the resource management is responsible for writing the file
		try {
			success = resourceManagementService.setConfig(dataSource);
		} catch (Exception_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;
	}
}
