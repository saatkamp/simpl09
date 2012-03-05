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
  List<TypeDefinition> typeDefinitions = resourceManagement.getAllWorkflowDataFormatTypes().getTypeDefinitions();
%>
<h2>SIMPL Resource Management - Workflow Data Format Type Management</h2>
<a href="index.jsp">Home</a> &gt; Workflow Data Format Type Management
<br/><br/>
<form name="workflowDataFormatTypeList" action="WorkflowDataFormatTypeListAction" method="post">
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
    No workflow data format types are available, please create a workflow data format type.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="workflowDataFormatTypeListSubmit" value="New" />
  <% if (typeDefinitions.size() > 0) { %>
    <input type="submit" name="workflowDataFormatTypeListSubmit" value="Edit" "/>
    <input type="submit" name="workflowDataFormatTypeListSubmit" value="Delete" onclick="return confirm('Are you sure you want to delete the workflow data format type?')" />
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