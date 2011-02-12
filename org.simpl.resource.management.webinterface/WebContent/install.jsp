<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
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
<h2>SIMPL Resource Management - Installation</h2>
<a href="index.jsp">Home</a> &gt; Installation
<br/><br/>
The installation will create all necessary tables for the Resource Management on the defined PostgreSQL database in the 'simpl-resource-management-config.xml'.
<br/>
<br/>
<form name="installation" action="InstallAction" method="post" onSubmit="return confirm('Are you sure?')">
  <input type="submit" name="installSubmit" value="Install"/>
  &nbsp;<div style="display:inline">
    <b>
    <%= (request.getParameter("message") != null ? request.getParameter("message"): "") %>
    </b>
  </div>
</form>
<br/>
<div style="color:red;font-weight:bold">Attention: Do not press this button if the Resource Management is already installed and you don't want to have all resources deleted!</div>
</body>