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
  String dataSourceId = "";
  String dataSourceName = "";
  String logicalName = "";
  String localIdentifier = "";
  
  DataContainer dataContainer = null;
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

  if (parameters.get("dataContainerListSubmit") != null
      && parameters.get("dataContainerListSubmit").equals("Edit") && parameters.get("id") != null) {
    dataContainer = resourceManagement.getDataContainerById(Integer.valueOf(parameters.get("id")));
    
    if (dataContainer != null) {
      id = dataContainer.getId();
      dataSourceId = dataContainer.getDataSourceId();
      dataSourceName = dataContainer.getDataSourceName();
      logicalName = dataContainer.getLogicalName();
      localIdentifier = dataContainer.getLocalIdentifier();
    }
  }
%>
<h2>SIMPL Resource Management - Data Container Editing</h2>
<a href="index.jsp">Home</a> &gt; <a href="datacontainer_list.jsp">Data Container Management</a> &gt; Data Container Editing
<br/><br/>
<form name="DataContainerForm" action="DataContainerFormAction" method="post" enctype="multipart/form-data">
<table cellspacing="3" cellpadding="3" style="border: 1px solid;">
  <tr>
    <td><label>Logical Name</label></td>
    <td><input name="logicalName" type="text" value="<%=logicalName%>" size="100" /></td>
  </tr>
  
  <tr>
    <td><label>Data Source</label></td>
    <td><%=FormMetaData.getInstance().getDataSourceSelect("dataSourceName", dataSourceName)%></td>
  </tr>

  <tr>
    <td><label>Local Identifier (XML)</label></td>
    <td>
      <textarea name="localIdentifierData" cols="77" rows="10"><%=localIdentifier%></textarea>
      <br/>
      <input name="localIdentifier" type="file" size="100"/>
    </td>
  </tr>
</table>

<br/>
<input type="hidden" name="id" value="<%=id%>" />
<input type="submit" name="dataContainerFormSubmit" value="Save" />
<input type="submit" name="dataContainerFormSubmit" value="Cancel"/>
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