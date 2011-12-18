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
  <h2>SIMPL Resource Management - Home</h2>
  <h3>Resources</h3>
  <a href="datasource_list.jsp">Data Source Management</a>
  <br/>
  <a href="connector_list.jsp">Connector Management</a>
  <br/>
  <a href="dataconverter_list.jsp">Data Converter Management</a>
  <br/>
  <a href="datatransformationservice_list.jsp">Data Transformation Service Management</a>
  <br/>
  <a href="strategyplugin_list.jsp">Strategy Plug-In Management</a>
  <br/>
  <h3>Definitions</h3>
  <a href="datasourcereferencetype_list.jsp">Data Source Reference Type Management</a>
  <br/>
  <a href="datacontainerreferencetype_list.jsp">Data Container Reference Type Management</a>
  <br/>
  <a href="workflowdataformattype_list.jsp">Workflow Data Format Type Management</a>
  <br/>
  <h3>Installation</h3>
  <a href="install.jsp">Installation</a>
</body>