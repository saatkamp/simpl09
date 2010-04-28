<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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

	String name = "";

	String address = "";

	String type = "";

	String subtype = "";

	String policy = "";
	
	String username = "";
	
	String key = "";
	
	String password = "";

	if (request.getParameter("uddi") != null) {
		UddiDataSource dataSource = datasourceReader.getByKey(request
				.getParameter("uddi"));

		if (request.getParameter("new") == null
				&& request.getParameter("uddi") != null) {
			name = dataSource.getName();
			address = dataSource.getAddress();
			type = dataSource.getAttributeValue("type");
			subtype = dataSource.getAttributeValue("subtype");
			policy = dataSource.getAttributeValue("wspolicy");
			username = dataSource.getAttributeValue("username");
			password = dataSource.getAttributeValue("password");
		}
	}
	
	if (request.getParameter("name") != null) {
		name = request.getParameter("name");
		address = request.getParameter("address");
		type = request.getParameter("type");
		subtype = request.getParameter("subtype");
		policy = request.getParameter("policy");
		key = request.getParameter("key");
	}

	if (request.getParameter("message") != null && !request.getParameter("message").equals("")) {
		PrintWriter output = response.getWriter();
		output.println(request.getParameter("message"));

	}
%>
<form action="UddiAction" method="post">
<table>
	<tr>
		<td><label>Name</label></td>
		<td><input name="name" type="text" value="<%=name%>" size="100" /></td>
	</tr>

	<tr>
		<td><label>Address</label></td>

		<td><input name="address" type="text" value="<%=address%>"
			size="100" /></td>
	</tr>

	<tr>
		<td><label>Type</label></td>

		<td><input name="type" type="text" value="<%=type%>" size="100" /></td>
	</tr>

	<tr>
		<td><label>Subtype</label></td>

		<td><input name="subtype" type="text" value="<%=subtype%>"
			size="100" /></td>
	</tr>
	
	<tr>
		<td><label>Username</label></td>

		<td><input name="username" type="text" value="<%=username%>"
			size="100" /></td>
	</tr>
	
	<tr>
		<td><label>Password</label></td>

		<td><input name="password" type="password" value="<%=password%>"
			size="100" /></td>
	</tr>

	<tr>
		<td><label>Policy File</label></td>

		<td><input name="policy" type="text" value="<%=policy%>"
			size="100" /></td>
	</tr>
</table>

<input name="key" type="hidden" value=" <%=key %>" />

<input type="submit" name="save" value="save" /></form>

</body>
