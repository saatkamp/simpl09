<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.simpl.resource.management.webinterface.utils.Check"%>
<%@page import="org.simpl.resource.management.webinterface.*"%>
<%@page import="org.simpl.resource.management.client.*"%>
<%@page import="org.simpl.resource.management.data.*"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
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
  List<DataConverter> dataConverters = resourceManagement.getAllDataConverters().getDataConverters();
%>
<h2>SIMPL Resource Management - Data Converter Management</h2>
<a href="index.jsp">Home</a> &gt; Data Converter  Management
<br/><br/>
<form name="dataConverterList" action="DataConverterListAction" method="post">
  <% if (dataConverters.size() > 0) { %>
    <table border="1" cellspacing="3" cellpadding="3">
      <tr>
        <td></td>
        <td>Name</td>
        <td>Connector Input Data Type</td>
        <td>Connector Output Data Type</td>
        <td>Workflow Data Format</td>
        <td>Workflow -&gt; Input</td>
        <td>Output -&gt; Workflow</td>
        <td>Implementation</td>
      </tr>
      
      <% for (DataConverter dataConverter : dataConverters) { %>
        <tr>
          <td><input type="radio" name="id" value="<%=dataConverter.getId()%>"></input></td>
          <td><%=!Check.empty(dataConverter.getName()) ? dataConverter.getName() : ""%></td>
          <td><%=!Check.empty(dataConverter.getConnectorInputDataType()) ? StringEscapeUtils.escapeHtml(dataConverter.getConnectorInputDataType()) : ""%></td>
          <td><%=!Check.empty(dataConverter.getConnectorOutputDataType()) ? StringEscapeUtils.escapeHtml(dataConverter.getConnectorOutputDataType()) : ""%></td>
          <td><%=!Check.empty(dataConverter.getWorkflowDataFormat()) ? dataConverter.getWorkflowDataFormat() : ""%></td>
          <td><%=!Check.empty(dataConverter.getDirectionWorkflowInput()) ? dataConverter.getDirectionWorkflowInput() : "false"%></td>
          <td><%=!Check.empty(dataConverter.getDirectionOutputWorkflow()) ? dataConverter.getDirectionOutputWorkflow() : "false"%></td>
          <td><%=!Check.empty(dataConverter.getImplementation()) ? dataConverter.getImplementation() : ""%></td>
        </tr>
      <% } %>
    </table>
  <% } else { %>
    No data converters are available, please create a data converter.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="dataConverterListSubmit" value="New" />
  <% if (dataConverters.size() > 0) { %>
    <input type="submit" name="dataConverterListSubmit" value="Edit" "/>
    <input type="submit" name="dataConverterListSubmit" value="Delete" onclick="return confirm('Are you sure you want to delete the data converter?')" />
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