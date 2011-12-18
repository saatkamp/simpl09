<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Enumeration"%>
<%@page import="org.simpl.resource.management.webinterface.*"%>
<%@page import="org.simpl.resource.management.client.*"%>
<%@page import="org.simpl.resource.management.data.*"%>
<%
  PrintWriter output = response.getWriter();
  ResourceManagement resourceManagement = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SIMPL Resource Management Web Interface</title>
</head>

<body>
<%
  String id = "";
  String name = "";
  String address = "";
  String type = "";
  String subtype = "";
  String username = "";
  String key = "";
  String password = "";
  String language = "";
  String apitype = "";
  String drivername = "";
  String addressprefix = "";
  String workflowdataformat = "";
  String properties = "";
  String connectorProperties = "";
  
  DataSource dataSource = null;
  String param = null;
  HashMap<String, String> parameters = new HashMap<String, String>();

  if (request.getParameterMap().isEmpty()) {
    parameters = MultipartForm.getParameters(request);
  } else {
    @SuppressWarnings("rawtypes")
    Enumeration names = request.getParameterNames();

    while (names.hasMoreElements()) {
      param = names.nextElement().toString();
      parameters.put(param, request.getParameter(param));
    }
  }

  if (parameters.get("dataSourceListSubmit") != null
      && parameters.get("dataSourceListSubmit").equals("Edit") && parameters.get("id") != null) { // edit action from datasource_list.jsp
    dataSource = resourceManagement.getDataSourceById(Integer.valueOf(parameters
        .get("id")));

    if (dataSource != null) {
      id = dataSource.getId();
      name = dataSource.getName();
      address = dataSource.getAddress();
      type = dataSource.getType();
      subtype = dataSource.getSubType();
      apitype = dataSource.getAPIType();
      drivername = dataSource.getDriverName();
      addressprefix = dataSource.getAddressPrefix();
      properties = dataSource.getPropertiesDescription();
      username = dataSource.getAuthentication().getUser();
      password = dataSource.getAuthentication().getPassword();
      language = dataSource.getLanguage();
      workflowdataformat = dataSource.getConnector().getDataConverter().getWorkflowDataFormat();
      connectorProperties = dataSource.getConnectorPropertiesDescription();
    }
  }
%>
<h2>SIMPL Resource Management - Data Source Editing</h2>
<a href="index.jsp">Home</a> &gt; <a href="datasource_list.jsp">Data Source Management</a> &gt; Data Source Editing
<br/><br/>
<form name="datasourceForm" action="DataSourceFormAction" method="post" enctype="multipart/form-data">
<table cellspacing="3" cellpadding="3" style="border: 1px solid;">
  <tr>
    <td><label>Name</label></td>
    <td><input name="name" type="text" value="<%=name%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Address</label></td>
    <td><input name="address" type="text" value="<%=address%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Type</label></td>
    <td><%=FormMetaData.getInstance().getTypeSelect(type)%></td>
  </tr>

  <tr>
    <td><label>Sub Type</label></td>
    <td><%=FormMetaData.getInstance().getSubTypeSelect(subtype)%></td>
  </tr>

  <tr>
    <td><label>Language</label></td>
    <td><%=FormMetaData.getInstance().getLanguageSelect(language)%></td>
  </tr>
  
  <tr>
    <td><label>API Type</label></td>
    <td><%=FormMetaData.getInstance().getAPITypeSelect("apitype", apitype)%></td>
  </tr>
  
  <tr>
    <td><label>Driver Name*</label></td>
    <td><input name="drivername" type="text" value="<%=drivername%>" size="100" /></td>
  </tr>
  
  <tr>
    <td><label>Address Prefix*</label></td>
    <td><input name="addressprefix" type="text" value="<%=addressprefix%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Workflow Data Format</label></td>
    <td><%=FormMetaData.getInstance().getDataFormatSelect("workflowdataformat", workflowdataformat)%></td>
  </tr>

  <tr>
    <td><label>User Name</label></td>
    <td><input name="username" type="text" value="<%=username%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Password</label></td>
    <td><input name="password" type="password" value="<%=password%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Properties Description</label></td>
    <td>
      <textarea name="propertiesDescriptionData" cols="77" rows="10"><%=properties%></textarea>
      <br/>
      <input name="propertiesDescription" type="file" size="100"/>
    </td>
  </tr>
  
  <tr>
    <td><label>Connector Properties Description</label></td>
    <td>
      <textarea name="connectorPropertiesData" cols="77" rows="10"><%=connectorProperties%></textarea>
      <textarea name="oldConnectorPropertiesData" cols="0" rows="0" style="height:0px;width:0px;position:absolute;top:0px;left:0px;visibility:hidden"><%=connectorProperties%></textarea>
      <br/>
      <input name="connectorProperties" type="file" size="100"/>
    </td>
  </tr> 
</table>

<br/>
<div style="font-weight:bold">Note on Properties Description:</div>
The <i>Properties Description</i> must contain a WS-Policy expression in order to be used for late binding.
<br/><br/>
<div style="font-weight:bold">Note on Connector Properties Description:</div>
The <i>Connector Properties Description</i> content is generated from <i>Type</i>, <i>Sub Type</i>, <i>Language</i>, <i>API Type</i>, <i>Driver Name</i>, <i>Address Prefix</i> and <i>Workflow Data Format</i>, if it is not set explicitly. 
The <i>API Type</i> is used when saving, to automatically find and assign a connector that has a matching properties description. The remaining properties are used only for documentation.
<br/><br/>
* only required for relational and JDBC based databases
<br/><br/>
<input type="hidden" name="id" value="<%=id%>" />
<input type="submit" name="dataSourceFormSubmit" value="Save" />
<input type="submit" name="dataSourceFormSubmit" value="Cancel"/>
</form>

<br/>
<div>
  <b>
  <%=(request.getParameter("message") != null ? request
          .getParameter("message") : "")%>
  </b>
</div>

</body>
</html>