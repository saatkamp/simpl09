<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.resource.management.webinterface.utils.Check"%>
<%@page import="org.simpl.resource.management.webinterface.*"%>
<%@page import="org.simpl.resource.management.client.*"%>
<%@page import="org.simpl.resource.management.data.*"%>
<%
	ResourceManagement resourceManagement = ResourceManagementClient
	.getService(RMWebConfig.getInstance().getResourceManagementAddress());
	PrintWriter output = response.getWriter();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SIMPL Resource Management Web Interface</title>
</head>

<body>
<%
	DataSource rmDataSource = null;

	String name = "";
	String address = "";
	String type = "";
	String subType = "";
	String dataFormat = "";
	String user = "";
	String password = "";
	rmDataSource = resourceManagement.getConfig();
	if (rmDataSource != null) {
		name = rmDataSource.getName();
		address = rmDataSource.getAddress();
		type = rmDataSource.getType();
		subType = rmDataSource.getSubType();
		dataFormat = rmDataSource.getConnector().getDataConverter().getWorkflowDataFormat();
		user = rmDataSource.getAuthentication().getUser();
		password = rmDataSource.getAuthentication().getPassword();
	}
%>
<h2>SIMPL Resource Management - Database Address</h2>
<a href="index.jsp">Home</a> &gt;<a href="install.jsp">Installation</a> &gt; Database Address
<br />
<br />
<form name="editconfig" action="EditConfigAction" method="post" enctype="multipart/form-data">
	<table cellspacing="3" cellpadding="3" style="border: 1px solid;">
		<tr>
			<td><label>Name</label></td>
			<td><input name="name" type="text" value="<%=name%>" size="100" />
			</td>
		</tr>
		<tr>
			<td><label>Address</label></td>
			<td><input name="address" type="text" value="<%=address%>"
				size="100" /></td>
		</tr>
		<tr>
			<td><label>Type</label></td>
			<td><input name="type" type="text" value="<%=type%>" size="100" />
			</td>
		</tr>
		<tr>
			<td><label>SubType</label></td>
			<td><input name="subType" type="text" value="<%=subType%>"
				size="100" /></td>
		</tr>
		<tr>
			<td><label>DataFormat</label></td>
			<td><input name="dataFormat" type="text"
				value="<%=dataFormat%>" size="100" /></td>
		</tr>
		<tr>
			<td><label>User</label></td>
			<td><input name="user" type="text" value="<%=user%>" size="100" />
			</td>
		</tr>
		<tr>
			<td><label>Password</label></td>
			<td><input name="password" type="text" value="<%=password%>"
				size="100" /></td>
		</tr>
	</table>
	<br/> 
	<input type="submit" name="editConfigSubmit" value="Save" onclick="return confirm('Are you sure you want to save these information?')" /> 
	<input type="submit" name="editConfigSubmit" value="Cancel" />
</form>
<br />
<div>
	<b> <%=(request.getParameter("message") != null ? request
				.getParameter("message") : "")%> </b>
	</div>
</body>