<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.resource.management.webinterface.utils.Check"%>
<%@page import="org.simpl.resource.management.webinterface.*"%>
<%@page import="org.simpl.resource.management.client.*"%>
<%@page import="org.simpl.resource.management.data.*"%>
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
  List<TypeDefinition> typeDefinitions = resourceManagement.getAllDataContainerReferenceTypes().getTypeDefinitions();
%>
<h2>SIMPL Resource Management - Data Container Reference Type Management</h2>
<a href="index.jsp">Home</a> &gt; Data Container Reference Type Management
<br/><br/>
<form name="dataContainerReferenceTypeList" action="DataContainerReferenceTypeListAction" method="post">
  <% if (typeDefinitions.size() > 0) { %>
    <table border="1" cellspacing="3" cellpadding="3">
      <tr>
        <td></td>
        <td>Name</td>
        <td>XSD Type (XML)</td>
      </tr>
      
      <% for (TypeDefinition typeDefinition : typeDefinitions) { %>
        <tr>
          <td><input type="radio" name="id" value="<%=typeDefinition.getId()%>"></input></td>
          <td><%=!Check.empty(typeDefinition.getName()) ? typeDefinition.getName() : ""%></td>
          <td><%=!Check.empty(typeDefinition.getXsdType())%></td>
        </tr>
      <% } %>
    </table>
  <% } else { %>
    No data container reference types are available, please create a data container reference type.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="dataContainerReferenceTypeListSubmit" value="New" />
  <% if (typeDefinitions.size() > 0) { %>
    <input type="submit" name="dataContainerReferenceTypeListSubmit" value="Edit" "/>
    <input type="submit" name="dataContainerReferenceTypeListSubmit" value="Delete" onclick="return confirm('Are you sure you want to delete the data container reference type?')" />
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