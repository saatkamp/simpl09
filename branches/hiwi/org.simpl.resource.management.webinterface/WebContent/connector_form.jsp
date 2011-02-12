<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Enumeration"%>
<%@page import="org.simpl.resource.management.webinterface.*"%>
<%@page import="org.simpl.resource.management.client.*"%>
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
  String dataformat = "";
  String properties = "";
  String type = "";
  String subtype = "";
  String language = "";
  
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
      implementation = connector.getImplementation();
      type = connector.getType();
      subtype = connector.getSubType();
      language = connector.getLanguage();
      properties = connector.getPropertiesDescription();
      dataformat = connector.getConverterDataFormat().getName();
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
    <td><label>Implementation</label></td>
    <td><input name="implementation" type="text" value="<%=implementation%>" size="100" /></td>
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
    <td><label>Data Format</label></td>
    <td><%=FormMetaData.getInstance().getDataFormatSelect("dataformat", dataformat)%></td>
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