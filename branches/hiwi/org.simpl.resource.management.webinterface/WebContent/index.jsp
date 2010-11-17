<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.resource.management.webinterface.*"%>
<%@page import="org.simpl.resource.management.client.*"%>
<%@page import="org.simpl.core.webservices.client.*"%>
<%
  PrintWriter output = response.getWriter();
  ResourceManagement resourceManagement = ResourceManagementClient
      .getService(RMWebConfig.getInstance().getResourceManagementAddress());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SIMPL Resource Management Web Interface: data source list</title>
</head>

<body>
<%
  List<DataSource> dataSources = resourceManagement.getAllDataSources()
      .getDataSources();
%>
<h2>SIMPL Resource Management: Data Source List</h2>

<form name="datasources" action="IndexAction" method="post">
  <table border="1" cellspacing="3" cellpadding="3">
    <tr>
      <td></td>
      <td>Name</td>
      <td>Address</td>
      <td>Type</td>
      <td>Sub type</td>
      <td>Language</td>
      <td>Data format</td>
      <td>User</td>
      <td>Password</td>
      <td>Policy</td>
    </tr>
    
    <% for (DataSource source : dataSources) { %>
      <tr>
        <td><input type="radio" name="id" value="<%=source.getId()%>"></input></td>
        <td><%=source.getName()%></td>
        <td><%=source.getAddress()%></td>
        <td><%=source.getType()%></td>
        <td><%=source.getSubType()%></td>
        <td><%=source.getLanguage()%></td>
        <td><%=source.getDataFormat()%></td>
        <td><%=source.getAuthentication().getUser()%></td>
        <td><%=source.getAuthentication().getPassword().equals("") != null ? source.getAuthentication().getPassword().replaceAll(".", "*") : ""%></td>
        <td><%=!source.getLateBinding().getPolicy().equals("")%></td>
      </tr>
    <% } %>
  </table>
  
  <br/>
  <input type="submit" name="indexSubmit" value="New" />
  <% if (dataSources.size() > 0) { %>
    <input type="submit" name="indexSubmit" value="Edit" "/>
    <input type="submit" name="indexSubmit" value="Delete" onclick="return confirm('Are you sure to delete the data source?')" />
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