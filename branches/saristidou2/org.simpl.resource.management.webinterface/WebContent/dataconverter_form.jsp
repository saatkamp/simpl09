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
  String inputDataType = "";
  String outputDataType = "";
  String workflowDataFormat = "";
  String directionOutputWorkflow = "";
  String directionWorkflowInput = "";
  String implementation = "";
  
  DataConverter dataConverter = null;
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

  if (parameters.get("dataConverterListSubmit") != null
      && parameters.get("dataConverterListSubmit").equals("Edit") && parameters.get("id") != null) {
    dataConverter = resourceManagement.getDataConverterById(Integer.valueOf(parameters.get("id")));

    if (dataConverter != null) {
      id = dataConverter.getId();
      name = dataConverter.getName();
      inputDataType = dataConverter.getInputDataType();
      outputDataType = dataConverter.getOutputDataType();
      workflowDataFormat = dataConverter.getWorkflowDataFormat();
      directionOutputWorkflow = dataConverter.getDirectionOutputWorkflow();
      directionWorkflowInput = dataConverter.getDirectionWorkflowInput();
      implementation = dataConverter.getImplementation();
    }
  }
%>
<h2>SIMPL Resource Management - Data Converter Editing</h2>
<a href="index.jsp">Home</a> &gt; <a href="dataconverter_list.jsp">Data Converter Management</a> &gt; Data Converter Editing
<br/><br/>
<form name="dataconverterForm" action="DataConverterFormAction" method="post" enctype="multipart/form-data">
<table cellspacing="3" cellpadding="3" style="border: 1px solid;">
  <tr>
    <td><label>Name</label></td>
    <td><input name="name" type="text" value="<%=name%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Input Data Type</label></td>
    <td><input name="inputDataType" type="text" value="<%=inputDataType%>" size="100" /></td>
  </tr>
  
  <tr>
    <td><label>Output Data Type</label></td>
    <td><input name="outputDataType" type="text" value="<%=outputDataType%>" size="100" /></td>
  </tr>
  
  <tr>
    <td><label>Workflow Data Format</label></td>
    <td><%=FormMetaData.getInstance().getDataFormatSelect("workflowDataFormat", workflowDataFormat)%></td>
  </tr>

  <tr>
    <td><label>Output -&gt; Workflow</label></td>
    <td><input name="directionOutputWorkflow" type="checkbox" size="100" <%=directionOutputWorkflow.equals("true") ? "checked" : ""%>/></td>
  </tr>
  
  <tr>
    <td><label>Workflow -&gt; Input</label></td>
    <td><input name="directionWorkflowInput" type="checkbox" size="100" <%=directionWorkflowInput.equals("true") ? "checked" : ""%>/></td>
  </tr>

  <tr>
    <td><label>Implementation</label></td>
    <td><input name="implementation" type="text" value="<%=implementation%>" size="100" /></td>
  </tr>
  
</table>

<br/>
<div style="font-weight:bold">Note on Data Types:</div>
The <i>Input Data Type</i> and <i>Output Data Type</i> are used along with the <i>Workflow Data Format</i> when saving, to automatically assign the data converter to existing connectors that have matching values.

<br/><br/>
<input type="hidden" name="id" value="<%=id%>" />
<input type="submit" name="dataConverterFormSubmit" value="Save" />
<input type="submit" name="dataConverterFormSubmit" value="Cancel"/>
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