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
  List<DataTransformationService> dataTransformationServices = resourceManagement.getAllDataTransformationServices().getDataTransformationServices();
%>
<h2>SIMPL Resource Management - Data Transformation Service Management</h2>
<a href="index.jsp">Home</a> &gt; Data Transformation Service Management
<br/><br/>
<form name="dataTransformationServiceList" action="DataTransformationServiceListAction" method="post">
  <% if (dataTransformationServices.size() > 0) { %>
    <table border="1" cellspacing="3" cellpadding="3">
      <tr>
        <td></td>
        <td>Name</td>
        <td>Connector Data Format</td>
        <td>Workflow Data Format</td>
        <td>Connector -&gt; Workflow</td>
        <td>Workflow -&gt; Connector</td>
        <td>Implementation</td>
      </tr>
      
      <% for (DataTransformationService dataTransformationService : dataTransformationServices) { %>
        <tr>
          <td><input type="radio" name="id" value="<%=dataTransformationService.getId()%>"></input></td>
          <td><%=!Check.empty(dataTransformationService.getName()) ? dataTransformationService.getName() : ""%></td>
          <td><%=!Check.empty(dataTransformationService.getConnectorDataFormat()) ? dataTransformationService.getConnectorDataFormat() : ""%></td>
          <td><%=!Check.empty(dataTransformationService.getWorkflowDataFormat()) ? dataTransformationService.getWorkflowDataFormat() : ""%></td>
          <td><%=!Check.empty(dataTransformationService.getDirectionConnectorWorkflow()) ? dataTransformationService.getDirectionConnectorWorkflow() : ""%></td>
          <td><%=!Check.empty(dataTransformationService.getDirectionWorkflowConnector()) ? dataTransformationService.getDirectionWorkflowConnector() : ""%></td>
          <td><%=!Check.empty(dataTransformationService.getImplementation()) ? dataTransformationService.getImplementation() : ""%></td>
        </tr>
      <% } %>
    </table>
  <% } else { %>
    No data transformation services are available, please create a data transformation service.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="dataTransformationServiceListSubmit" value="New" />
  <% if (dataTransformationServices.size() > 0) { %>
    <input type="submit" name="dataTransformationServiceListSubmit" value="Edit" "/>
    <input type="submit" name="dataTransformationServiceListSubmit" value="Delete" onclick="return confirm('Are you sure you want to delete the data transformation service?')" />
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