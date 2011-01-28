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
  <title>SIMPL Resource Management Web Interface</title>
</head>

<body>
<%
  List<Converter> converters = resourceManagement.getAllConverters().getConverters();
%>
<h2>SIMPL Resource Management - Converter Management</h2>
<a href="index.jsp">Home</a> &gt; Converter Management
<br/><br/>
<form name="converterList" action="ConverterListAction" method="post">
  <% if (converters.size() > 0) { %>
    <table border="1" cellspacing="3" cellpadding="3">
      <tr>
        <td></td>
        <td>Name</td>
        <td>Implementation</td>
        <td>Connector Data Format</td>
        <td>Workflow Data Format</td>
      </tr>
      
      <% for (Converter converter : converters) { %>
        <tr>
          <td><input type="radio" name="id" value="<%=converter.getId()%>"></input></td>
          <td><%=!converter.getName().equals("") ? converter.getName() : ""%></td>
          <td><%=!converter.getImplementation().equals("") ? converter.getImplementation() : ""%></td>
          <td><%=!converter.getConnectorDataFormat().getName().equals("") ? converter.getConnectorDataFormat().getName() : ""%></td>
          <td><%=!converter.getWorkflowDataFormat().getName().equals("") ? converter.getWorkflowDataFormat().getName() : ""%></td>
        </tr>
      <% } %>
    </table>
  <% } else { %>
    No converters available, please create a converter.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="converterListSubmit" value="New" />
  <% if (converters.size() > 0) { %>
    <input type="submit" name="converterListSubmit" value="Edit" "/>
    <input type="submit" name="converterListSubmit" value="Delete" onclick="return confirm('Are you sure to delete the converter?')" />
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