<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>

<%@page import="org.simpl.uddi.client.UddiDataSource"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.uddi.client.UddiWebConfig"%>
<%@page import="org.simpl.uddi.client.UddiDataSourceReader"%><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simpl jUDDI Webinterface</title>
</head>

<body>
<%
	UddiWebConfig uddiWebConfig = UddiWebConfig.getInstance();
	UddiDataSourceReader datasourceReader = UddiDataSourceReader
			.getInstance(uddiWebConfig.getAddress());
	ArrayList<UddiDataSource> dataSources = datasourceReader
			.getAllDatasources();
	if (request.getParameter("message") != null) {
		PrintWriter output = response.getWriter();
		output.println(request.getParameter("message"));
		
	}
%>
<form action="UddiAction" method="post">
<table border="1">
	<tr>
		<td>Datasource Name</td>
		<td>Adress</td>
		<td>Type</td>
		<td>Subtype</td>
	</tr>
	<%
		for (UddiDataSource source : dataSources) {
	%>
	<tr>
		<td><%=source.getName()%></td>
		<td><%=source.getAddress()%></td>
		<td><%=source.getAttributeValue("type")%></td>
		<td><%=source.getAttributeValue("subtype")%></td>
		<td><input type="radio" name="uddi" value="<%=source.getKey()%>"></input></td>
	</tr>
	<%
		}
	%>
</table>
<input type="submit" name="new" value="New" />
<%
	if (dataSources.size() > 0) {
%> <input type="submit" name="edit"
	value="Edit" /> <input type="submit" name="delete" value="Delete" /></form>
<%
	}
%>

</body>
