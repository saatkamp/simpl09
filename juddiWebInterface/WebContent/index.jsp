<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="org.simpl.uddi.UddiWebConfig"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.PrintWriter"%><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SIMPL jUDDI Web Interface</title>
</head>
<body>
<%
PrintWriter output = response.getWriter();
if (request.getParameter("message") != null) {
	//PrintWriter output = response.getWriter();
	output.println(request.getParameter("message"));

}
String address = "";

String username = "";

String password = "";

String dsAddress = "";
	try {
		UddiWebConfig uddiWebConfig = UddiWebConfig.getInstance();

		address = uddiWebConfig.getAddress();

		username = uddiWebConfig.getUsername();
		
		password = uddiWebConfig.getPassword();
		
		dsAddress = uddiWebConfig.getDsAddress();
		
		
	} catch (FileNotFoundException e) {
		//Todo Config datei erstellen
	}
		
		
		
%>
	

<form action="UddiAction" method="post">
<table>

	<tr>
		<td><label>Address</label></td>

		<td><input name="address" type="text" value="<%=address%>"
			size="100" /></td>
	</tr>


	<tr>
		<td><label>User name</label></td>

		<td><input name="username" type="text" value="<%=username%>"
			size="100" /></td>
	</tr>
	
	<tr>
		<td><label>Password</label></td>

		<td><input name="password" type="password" value="<%=password%>"
			size="100" /></td>
	</tr>
	
	<tr>
		<td><label>DataSourceService Address</label></td>

		<td><input name="ds-address" type="text" value="<%=dsAddress%>"
			size="100" /></td>
	</tr>

</table>


<input type="submit" name="saveconfig" value="save" />
<input type="submit" name="checkuddi" value="CheckUddi" />
</form>

<% if (request.getParameter("message") == null || !request.getParameter("message").equals("Uddi Registry not found") ){ %>
<a href="./list.jsp">Show data sources</a>
<%} %>

</body>