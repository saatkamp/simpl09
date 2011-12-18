<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.resource.management.webinterface.utils.Check"%>
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
  List<DataSource> dataSources = resourceManagement.getAllDataSources().getDataSources();
%>
<h2>SIMPL Resource Management - Data Source Management</h2>
<a href="index.jsp">Home</a> &gt; Data Source Management
<br/><br/>
<form name="datasourceList" action="DataSourceListAction" method="post">
  <% if (dataSources.size() > 0) { %>
    <table border="1" cellspacing="3" cellpadding="3">
      <tr>
        <td></td>
        <td>Name</td>
        <td>Address</td>
        <td>Type</td>
        <td>Sub Type</td>
        <td>Language</td>
        <td>apiType</td>
        <td>driverName</td>
        <td>addressPrefix</td>
        <td>Data Format</td>
        <td>User</td>
        <td>Password</td>
        <td>Properties (XML)</td>
        <td>Connector Properties (XML)</td>
        <td>Assigned Connector</td>
      </tr>
      
      <% for (DataSource source : dataSources) { %>
        <tr>
          <td><input type="radio" name="id" value="<%=source.getId()%>"></input></td>
          <td><%=!Check.empty(source.getName()) ? source.getName() : ""%></td>
          <td><%=!Check.empty(source.getAddress()) ? source.getAddress() : ""%></td>
          <td><%=!Check.empty(source.getType()) ? source.getType() : ""%></td>
          <td><%=!Check.empty(source.getSubType()) ? source.getSubType() : ""%></td>
          <td><%=!Check.empty(source.getLanguage()) ? source.getLanguage() : ""%></td>
          <td><%=!Check.empty(source.getAPIType()) ? source.getAPIType() : ""%></td>
          <td><%=!Check.empty(source.getDriverName()) ? source.getDriverName() : ""%></td>
          <td><%=!Check.empty(source.getAddressPrefix()) ? source.getAddressPrefix() : ""%></td>
          <td><%=!Check.empty(source.getConnector().getDataConverter().getWorkflowDataFormat()) ? source.getConnector().getDataConverter().getWorkflowDataFormat() : ""%></td>
          <td><%=!Check.empty(source.getAuthentication().getUser()) ? source.getAuthentication().getUser() : ""%></td>
          <td><%=!Check.empty(source.getAuthentication().getPassword()) ? source.getAuthentication().getPassword().replaceAll(".", "*") : ""%></td>
          <td><%=!Check.empty(source.getPropertiesDescription())%></td>
          <td><%=!Check.empty(source.getConnectorPropertiesDescription())%></td>
          <td><%=!Check.empty(source.getConnector().getName()) ? source.getConnector().getName() : ""%></td>
        </tr>
      <% } %>
    </table>
  <% } else { %>
    No data sources are available, please create a data source.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="dataSourceListSubmit" value="New" />
  <% if (dataSources.size() > 0) { %>
    <input type="submit" name="dataSourceListSubmit" value="Edit" "/>
    <input type="submit" name="dataSourceListSubmit" value="Delete" onclick="return confirm('Are you sure you want to delete the data source?')" />
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