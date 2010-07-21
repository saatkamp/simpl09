<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.simpl.uddi.client.UddiDataSource"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.uddi.client.UddiDataSourceReader"%>
<%@page import="org.simpl.uddi.UddiWebConfig"%>
<%@page import="org.simpl.uddi.UddiDataTypeReader"%><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SIMPL jUDDI Web Interface</title>
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
	
	String language = "";
	
	String dataformat = "";

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
			language = dataSource.getAttributeValue("language");
			dataformat = dataSource.getAttributeValue("dataformat");
		}
	}
	
	if (request.getParameter("name") != null) {
		name = request.getParameter("name");
		address = request.getParameter("address");
		type = request.getParameter("type");
		subtype = request.getParameter("subtype");
		policy = request.getParameter("policy");
		key = request.getParameter("key");
		username = request.getParameter("username");
		password = request.getParameter("password");
		language = request.getParameter("language");
		dataformat = request.getParameter("dataformat");
		if (username == null || username.equals("null")) {
			username = "";
		}
		
		if (password == null || password.equals("null")) {
			password = "";
		}
		
		if (address == null || address.equals("null")) {
			address = "";
		}
		
		if (type == null || type.equals("null")) {
			type = "";
		}
		
		if (subtype == null || subtype.equals("null")) {
			subtype = "";
		}
		
		if (username == null || username.equals("null")) {
			username = "";
		}
		
		if (language == null || language.equals("null")) {
			language = "";
		}
		
		if (language == null || language.equals("null")) {
			language = "";
		}
	}

	if (request.getParameter("message") != null && !request.getParameter("message").equals("")) {
		PrintWriter output = response.getWriter();
		output.println(request.getParameter("message"));

	}
%>
<form action="UddiAction" method="post" enctype="multipart/form-data">
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

		<td><%=UddiDataTypeReader.getInstance().getTypesDropDown(type)%></td>
	</tr>

	<tr>
		<td><label>Subtype</label></td>

		<td><%=UddiDataTypeReader.getInstance().getSubTypesDropDown(subtype) %></td>
	</tr>
	
	<tr>
		<td><label>Language</label></td>

		<td><%=UddiDataTypeReader.getInstance().getLanguagesDropDown(language) %></td>
	</tr>
	
	
	<tr>
		<td><label>User name</label></td>

		<td><input name="username" type="text" value="<%=username%>"
			size="100" /></td>
	</tr>
	
	<tr>
		<td><label>Password</label></td>

		<td><input name="password" type="text" value="<%=password%>"
			size="100" /></td>
	</tr>

	<tr>
		<td><label>Policy file</label></td>

		<td><input name="policy" type="file"
			size="100" /></td>
	</tr>
	
	<tr>
		<td><label>Data format</label></td>

		<td><input name="dataformat" type="text" value="<%=dataformat%>"
			size="100" /></td>
	</tr>
</table>

<input name="key" type="hidden" value=" <%=key %>" />

<input type="submit" name="save" value="save" />

</form>
<form action="UddiAction" method="post">

<input type="submit" name="cancel" value="cancel" />
</form>

</body>
