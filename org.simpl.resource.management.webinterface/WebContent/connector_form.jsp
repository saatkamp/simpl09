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
  String implementation = "";
  String type = "";
  String subType = "";
  String language = "";
  String workflowDataFormat = "";
  String properties = "";
  
  Connector connector = null;
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

  if (parameters.get("connectorListSubmit") != null
      && parameters.get("connectorListSubmit").equals("Edit") && parameters.get("id") != null) {
    connector = resourceManagement.getConnectorById(Integer.valueOf(parameters.get("id")));

    if (connector != null) {
      id = connector.getId();
      name = connector.getName();
      inputDataType = connector.getInputDataType();
      outputDataType = connector.getOutputDataType();
      implementation = connector.getImplementation();
      type = connector.getType();
      subType = connector.getSubType();
      language = connector.getLanguage();
      workflowDataFormat = connector.getDataConverter().getWorkflowDataFormat();
      properties = connector.getPropertiesDescription();
    }
  }
%>
<h2>SIMPL Resource Management - Connector Editing</h2>
<a href="index.jsp">Home</a> &gt; <a href="connector_list.jsp">Connector Management</a> &gt; Connector Editing
<br/><br/>
<form name="connectorForm" action="ConnectorFormAction" method="post" enctype="multipart/form-data">
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
    <td><label>Implementation</label></td>
    <td><input name="implementation" type="text" value="<%=implementation%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Type</label></td>
    <td><%=FormMetaData.getInstance().getTypeSelect(type)%></td>
  </tr>

  <tr>
    <td><label>Sub Type</label></td>
    <td><%=FormMetaData.getInstance().getSubTypeSelect(subType)%></td>
  </tr>

  <tr>
    <td><label>Language</label></td>
    <td><%=FormMetaData.getInstance().getLanguageSelect(language)%></td>
  </tr>

  <tr>
    <td><label>Workflow Data Format</label></td>
    <td><%=FormMetaData.getInstance().getDataFormatSelect("workflowDataFormat", workflowDataFormat)%></td>
  </tr>
  
  <tr>
    <td><label>Properties Description</label></td>
    <td>
      <textarea name="propertiesData" cols="77" rows="10"><%=properties%></textarea>
      <textarea name="oldPropertiesData" cols="0" rows="0" style="height:0px;width:0px;position:absolute;top:0px;left:0px;visibility:hidden"><%=properties%></textarea>
      <br/>
      <input name="properties" type="file" size="100"/>
    </td>
  </tr> 
</table>

<br/>
<div style="font-weight:bold">Note on Data Types:</div>
The <i>Input Data Type</i> and <i>Output Data Type</i> are used along with the <i>Workflow Data Format</i> when saving to automatically assign a data converter that has matching values. 

<br/><br/>
<div style="font-weight:bold">Note on Properties Description:</div>
The <i>Properties Description</i> content is generated when saving from <i>Type</i>, <i>Sub Type</i>, <i>Language</i> and <i>Workflow Data Format</i>, if it is not set explicitly. It is used when saving to automatically assign the connector to existing data sources that have a matching connector properties description.
<br/><br/>
The other way around on opening, the values for <i>Type</i>, <i>Sub Type</i> and <i>Language</i> are taken from an existing <i>Properties Description</i>. The <i>Workflow Data Format</i> is taken from the assigned data converter, if there is one.

<br/><br/>
<input type="hidden" name="id" value="<%=id%>" />
<input type="submit" name="connectorFormSubmit" value="Save" />
<input type="submit" name="connectorFormSubmit" value="Cancel"/>
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