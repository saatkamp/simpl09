<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Enumeration"%>
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
  String id = "";
  String name = "";
  String dataformat = "";
  String implementation = "";
  String xmlSchema = "";
  
  DataConverter dataConverter = null;
  String param = null;
  HashMap<String, String> parameters = new HashMap<String, String>();

  if (request.getParameterMap().isEmpty()) {
    parameters = MultipartForm.getParameters(request);
  } else {
    @SuppressWarnings("rawtypes")
    Enumeration names = request.getParameterNames();

    while (names.hasMoreElements()) {
      param = names.nextElement().toString();
      parameters.put(param, request.getParameter(param));
    }
  }

  if (parameters.get("dataConverterListSubmit") != null
      && parameters.get("dataConverterListSubmit").equals("Edit") && parameters.get("id") != null) {
    dataConverter = resourceManagement.getDataConverterById(Integer.valueOf(parameters.get("id")));

    if (dataConverter != null) {
      id = dataConverter.getId();
      name = dataConverter.getName();
      dataformat = dataConverter.getDataFormat();
      implementation = dataConverter.getImplementation();
      xmlSchema = dataConverter.getXmlSchema();
    }
  }
%>
<h2>SIMPL Resource Management - Data Converter Editing</h2>
<a href="index.jsp">Home</a> &gt; <a href="dataconverter_list.jsp">Data Converter Management</a> &gt; Data Converter Editing
<br/><br/>
<form name="dataconverterForm" action="DataConverterFormAction" method="post" enctype="multipart/form-data">
<table cellspacing="3" cellpadding="3" style="border: 1px solid;">
  <tr>
    <td><label>Name</label></td>
    <td><input name="name" type="text" value="<%=name%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Data Format</label></td>
    <td><input name="dataformat" type="text" value="<%=dataformat%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>Implementation</label></td>
    <td><input name="implementation" type="text" value="<%=implementation%>" size="100" /></td>
  </tr>
  
  <tr>
    <td><label>XML Schema</label></td>
    <td>
      <textarea name="xmlSchemaData" cols="77" rows="10"><%=xmlSchema%></textarea>
      <textarea name="oldXmlSchemaData" cols="0" rows="0" style="height:0px;width:0px;position:absolute;top:0px;left:0px;visibility:hidden"><%=xmlSchema%></textarea>
      <br/>
      <input name="xmlSchema" type="file" size="100"/>
    </td>
  </tr> 
</table>

<br/>
<input type="hidden" name="id" value="<%=id%>" />
<input type="submit" name="dataConverterFormSubmit" value="Save" />
<input type="submit" name="dataConverterFormSubmit" value="Cancel"/>
</form>

<br/>
<div>
  <b>
  <%=(request.getParameter("message") != null ? request
          .getParameter("message") : "")%>
  </b>
</div>

</body>
</html>