<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.resource.management.webinterface.utils.Check"%>
<%@page import="org.simpl.resource.management.webinterface.*"%>
<%@page import="org.simpl.resource.management.client.*"%>
<%@page import="org.simpl.resource.management.data.*"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
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
  List<Connector> connectors = resourceManagement.getAllConnectors().getConnectors();
%>
<h2>SIMPL Resource Management - Connector Management</h2>
<a href="index.jsp">Home</a> &gt; Connector Management
<br/><br/>
<form name="connectorList" action="ConnectorListAction" method="post">
  <% if (connectors.size() > 0) { %>
    <table border="1" cellspacing="3" cellpadding="3">
      <tr>
        <td></td>
        <td>Name</td>
        <td>Input Data Type</td>
        <td>Output Data Type</td>
        <td>Implementation</td>
        <td>Type</td>
        <td>Sub Type</td>
        <td>Language</td>
        <td>Workflow Data Format</td>
        <td>API Type</td>
        <td>Properties (XML)</td>
        <td>Assigned Data Converter</td>
      </tr>
      
      <% for (Connector connector : connectors) { %>
        <tr>
          <td><input type="radio" name="id" value="<%=connector.getId()%>"></input></td>
          <td><%=!Check.empty(connector.getName()) ? connector.getName() : ""%></td>
          <td><%=!Check.empty(connector.getInputDataType()) ? StringEscapeUtils.escapeHtml(connector.getInputDataType()) : ""%></td>
          <td><%=!Check.empty(connector.getOutputDataType()) ? StringEscapeUtils.escapeHtml(connector.getOutputDataType()) : ""%></td>
          <td><%=!Check.empty(connector.getImplementation()) ? connector.getImplementation() : ""%></td>
          <td><%=!Check.empty(connector.getType()) ? connector.getType() : ""%></td>
          <td><%=!Check.empty(connector.getSubType()) ? connector.getSubType() : ""%></td>
          <td><%=!Check.empty(connector.getLanguage()) ? connector.getLanguage() : ""%></td>
          <td><%=!Check.empty(connector.getDataConverter().getWorkflowDataFormat()) ? connector.getDataConverter().getWorkflowDataFormat() : ""%></td>
          <td><%=!Check.empty(connector.getAPIType()) ? connector.getAPIType() : ""%></td>
          <td><%=!Check.empty(connector.getPropertiesDescription())%></td>
          <td><%=!Check.empty(connector.getDataConverter().getName()) ? connector.getDataConverter().getName() : ""%></td>
        </tr>
      <% } %>
    </table>
  <% } else { %>
    No connectors are available, please create a connector.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="connectorListSubmit" value="New" />
  <% if (connectors.size() > 0) { %>
    <input type="submit" name="connectorListSubmit" value="Edit" "/>
    <input type="submit" name="connectorListSubmit" value="Delete" onclick="return confirm('Are you sure you want to delete the connector?')" />
  <% } %>
</form>
  
<br/>
<div>
  <b>
  <%= (request.getParameter("message") != null ? request.getParameter("message"): "") %>
  </b>
</div>

</body>
</html>