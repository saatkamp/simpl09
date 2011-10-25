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
  List<StrategyPlugin> strategyPlugins = resourceManagement.getAllStrategyPlugins().getStrategyPlugins();
%>
<h2>SIMPL Resource Management - Strategy Plug-In Management</h2>
<a href="index.jsp">Home</a> &gt; Strategy Plug-In Management
<br/><br/>
<form name="strategyPluginList" action="StrategyPluginListAction" method="post">
  <% if (strategyPlugins.size() > 0) { %>
    <table border="1" cellspacing="3" cellpadding="3">
      <tr>
        <td></td>
        <td>Name</td>
        <td>Implementation</td>
      </tr>
      
      <% for (StrategyPlugin strategyPlugin : strategyPlugins) { %>
        <tr>
          <td><input type="radio" name="id" value="<%=strategyPlugin.getId()%>"></input></td>
          <td><%=!Check.empty(strategyPlugin.getName()) ? strategyPlugin.getName() : ""%></td>
          <td><%=!Check.empty(strategyPlugin.getImplementation()) ? strategyPlugin.getImplementation() : ""%></td>
        </tr>
      <% } %>
    </table>
  <% } else { %>
    No strategy plug-ins are available, please create a strategy plug-in.
    <br/>
  <% } %>
  <br/>
  <input type="submit" name="strategyPluginListSubmit" value="New" />
  <% if (strategyPlugins.size() > 0) { %>
    <input type="submit" name="strategyPluginListSubmit" value="Edit" "/>
    <input type="submit" name="strategyPluginListSubmit" value="Delete" onclick="return confirm('Are you sure you want to delete the strategy plug-in?')" />
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