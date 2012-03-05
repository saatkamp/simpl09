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
  List<DataContainer> dataContainers = resourceManagement.getAllDataContainers().getDataContainers();
%>
<h2>SIMPL Resource Management - Data Container Management</h2>
<a href="index.jsp">Home</a> &gt; Data Container Management
<br/><br/>
<form name="dataContainerList" action="DataContainerListAction" method="post">
  <% if (dataContainers.size() > 0) { %>
    <table border="1" cellspacing="3" cellpadding="3">
      <tr>
        <td></td>
        <td>Logical Name</td>
        <td>Data Source Name</td>
        <td>Local Identifier (XML)</td>
      </tr>
      
      <% for (DataContainer dataContainer : dataContainers) { %>
        <tr>
          <td><input type="radio" name="id" value="<%=dataContainer.getId()%>"></input></td>
          <td><%=!Check.empty(dataContainer.getLogicalName()) ? dataContainer.getLogicalName() : ""%></td>
          <td><%=!Check.empty(dataContainer.getDataSourceName()) ? dataContainer.getDataSourceName() : ""%></td>
          <td><%=!Check.empty(dataContainer.getLocalIdentifier())%></td>
        </tr>
      <% } %>
    </table>
  <% } else { %>
    No data containers are available, please create a data container.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="dataContainerListSubmit" value="New" />
  <% if (dataContainers.size() > 0) { %>
    <input type="submit" name="dataContainerListSubmit" value="Edit" "/>
    <input type="submit" name="dataContainerListSubmit" value="Delete" onclick="return confirm('Are you sure you want to delete the data container?')" />
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