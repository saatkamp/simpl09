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
  String implementation = "";
  String connectorDataFormat = "";
  String workflowDataFormat = "";
  
  DataTransformationService dataTransformationService = null;
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

  if (parameters.get("dataTransformationServiceListSubmit") != null
      && parameters.get("dataTransformationServiceListSubmit").equals("Edit") && parameters.get("id") != null) {
    dataTransformationService = resourceManagement.getDataTransformationServiceById(Integer.valueOf(parameters.get("id")));

    if (dataTransformationService != null) {
      id = dataTransformationService.getId();
      name = dataTransformationService.getName();
      implementation = dataTransformationService.getImplementation();
      connectorDataFormat = dataTransformationService.getConnectorDataConverter().getDataFormat();
      workflowDataFormat = dataTransformationService.getWorkflowDataConverter().getDataFormat();
    }
  }
%>
<h2>SIMPL Resource Management - Data Transformation Service Editing</h2>
<a href="index.jsp">Home</a> &gt; <a href="datatransformationservice_list.jsp">Data Transformation Service Management</a> &gt; Data Transformation Service Editing
<br/><br/>
<form name="dataTransformationServiceForm" action="DataTransformationServiceFormAction" method="post" enctype="multipart/form-data">
<table cellspacing="3" cellpadding="3" style="border: 1px solid;">
  <tr>
    <td><label>Name</label></td>
    <td><input name="name" type="text" value="<%=name%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Implementation</label></td>
    <td><input name="implementation" type="text" value="<%=implementation%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Connector Data Format</label></td>
    <td><%=FormMetaData.getInstance().getDataFormatSelect("connectorDataFormat", connectorDataFormat)%></td>
  </tr>

  <tr>
    <td><label>Workflow Data Format</label></td>
    <td><%=FormMetaData.getInstance().getDataFormatSelect("workflowDataFormat", workflowDataFormat)%></td>
  </tr>
 
</table>

<br/>
<input type="hidden" name="id" value="<%=id%>" />
<input type="submit" name="dataTransformationServiceFormSubmit" value="Save" />
<input type="submit" name="dataTransformationServiceFormSubmit" value="Cancel"/>
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