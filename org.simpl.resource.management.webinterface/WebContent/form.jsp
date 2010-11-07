<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Enumeration"%>
<%@page import="org.simpl.resource.management.webinterface.*"%>
<%@page import="org.simpl.resource.management.client.*"%>
<%@page import="org.simpl.core.webservices.client.*"%>
<%
  PrintWriter output = response.getWriter();
  ResourceManagement resourceManagement = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());
  DatasourceService dataSourceService = DatasourceServiceClient
      .getService(RMWebConfig.getInstance().getDataSourceServiceAddress());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SIMPL Resource Management Web Interface: data source</title>
</head>

<body>
<%
  String id = "";
  String name = "";
  String address = "";
  String type = "";
  String subtype = "";
  String policy = "";
  String username = "";
  String key = "";
  String password = "";
  String language = "";
  String dataformat = "";
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
  
  if (parameters.get("indexSubmit") != null
      && parameters.get("indexSubmit").equals("Edit") && parameters.get("id") != null) { // edit from index
    dataSource = resourceManagement.getDataSourceById(Integer.valueOf(parameters.get("id")));
    id = dataSource.getId();
    name = dataSource.getName();
    address = dataSource.getAddress();
    type = dataSource.getType();
    subtype = dataSource.getSubType();
    policy = dataSource.getLateBinding().getPolicy();
    username = dataSource.getAuthentication().getUser();
    password = dataSource.getAuthentication().getPassword();
    language = dataSource.getLanguage();
    dataformat = dataSource.getDataFormat();
  } else if (parameters.get("formSubmit") != null
      && parameters.get("formSubmit").equals("Save")) { // return to form after saving
    id = parameters.get("id");
    name = parameters.get("name");
    address = parameters.get("address");
    type = parameters.get("type");
    subtype = parameters.get("subtype");
    policy = parameters.get("policy");
    key = parameters.get("key");
    username = parameters.get("username");
    password = parameters.get("password");
    language = parameters.get("language");
    dataformat = parameters.get("dataformat");
  }
%>
<h2>Data Source Form</h2>

<form name="datasource" action="FormAction" method="post" enctype="multipart/form-data">
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
    <td><%=SIMPLCoreFormMetaData.getInstance().getTypeSelect(type)%></td>
  </tr>

  <tr>
    <td><label>Sub Type</label></td>
    <td><%=SIMPLCoreFormMetaData.getInstance().getSubTypeSelect(subtype)%></td>
  </tr>

  <tr>
    <td><label>Language</label></td>
    <td><%=SIMPLCoreFormMetaData.getInstance().getLanguageSelect(language)%></td>
  </tr>

  <tr>
    <td><label>Data format</label></td>
    <td><input name="dataformat" type="text" value="<%=dataformat%>"
      size="100" /></td>
  </tr>

  <tr>
    <td><label>User name</label></td>
    <td><input name="username" type="text" value="<%=username%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Password</label></td>
    <td><input name="password" type="password" value="<%=password%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Policy file</label></td>
    <td>
      <textarea name="policyData" cols="77" rows="5"><%=policy%></textarea>
      <br/>
      <input name="policy" type="file" size="100"/>
    </td>
  </tr>
</table>

<br/>
<input type="hidden" name="id" value="<%=id%>" />
<input type="submit" name="formSubmit" value="Save" />
<input type="submit" name="formSubmit" value="Cancel"/>
</form>

<br/>
<div>
  <b>
  <%= (request.getParameter("message") != null ? request.getParameter("message"): "") %>
  </b>
</div>
</body>
</html>