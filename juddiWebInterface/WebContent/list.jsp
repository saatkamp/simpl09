<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>

<%@page import="org.simpl.uddi.client.UddiDataSource"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.uddi.client.UddiDataSourceReader"%>


<%@page import="org.simpl.uddi.client.UddiBusinessReader"%>
<%@page import="org.simpl.uddi.UddiWebConfig"%>
<%@page import="org.simpl.uddi.CreateDatabase"%>
<meta
	http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<title>SIMPL jUDDI Web Interface: List of data sources</title>
</head>
<body>
<%
	UddiWebConfig uddiWebConfig = UddiWebConfig.getInstance();
	PrintWriter output = response.getWriter();
	UddiDataSourceReader datasourceReader = UddiDataSourceReader
			.getInstance(uddiWebConfig.getAddress());
	ArrayList<UddiDataSource> dataSources = datasourceReader
			.getAllDatasources();
	if (request.getParameter("message") != null) {
		//PrintWriter output = response.getWriter();
		output.println(request.getParameter("message"));

	}
%>
<form action="UddiAction" method="post">
<table border="1">
	<tr>
		<td>Datasource name</td>
		<td>Address</td>
		<td>Type</td>
		<td>Subtype</td>
		<td>Language</td>
		<td>Data format</td>
		<td>User name</td>
		<td>Password</td>
	</tr>
	<%
		for (UddiDataSource source : dataSources) {
	%>
	<tr>
		<td><%=source.getName()%></td>
		<td><%=source.getAddress()%></td>
		<td><%=source.getAttributeValue("type")%></td>
		<td><%=source.getAttributeValue("subtype")%></td>
		<td><%=source.getAttributeValue("language")%></td>
		<td><%=source.getAttributeValue("dataformat")%></td>
		<td><%=source.getAttributeValue("username")%></td>
		<td><%=source.getAttributeValue("password")%></td>
		<td><input type="radio" name="uddi" value="<%=source.getKey()%>"></input></td>
	</tr>
	<%
		}
	%>
</table>
<input type="submit" name="new" value="New" /> <%
 	if (dataSources.size() > 0) {
 %> <input type="submit" name="edit" value="Edit" /> <input
	type="submit" name="delete" value="Delete" /></form>
<%
	}
%>
</body>