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
  String xsdType = "";
  
  TypeDefinition typeDefinition = null;
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

  if (parameters.get("dataContainerReferenceTypeListSubmit") != null
      && parameters.get("dataContainerReferenceTypeListSubmit").equals("Edit") && parameters.get("id") != null) {
    typeDefinition = resourceManagement.getDataContainerReferenceTypeById(Integer.valueOf(parameters.get("id")));

    if (typeDefinition != null) {
      id = typeDefinition.getId();
      name = typeDefinition.getName();
      xsdType = typeDefinition.getXsdType();
    }
  }
%>
<h2>SIMPL Resource Management - Data Container Reference Type Editing</h2>
<a href="index.jsp">Home</a> &gt; <a href="datacontainerreferencetype_list.jsp">Data Container Reference Type Management</a> &gt; Data Container Reference Type Editing
<br/><br/>
<form name="dataContainerReferenceTypeForm" action="DataContainerReferenceTypeFormAction" method="post" enctype="multipart/form-data">
<table cellspacing="3" cellpadding="3" style="border: 1px solid;">
  <tr>
    <td><label>Name</label></td>
    <td><input name="name" type="text" value="<%=name%>" size="100" /></td>
  </tr>

  <tr>
    <td><label>XSD Type</label></td>
    <td>
      <textarea name="xsdTypeData" cols="77" rows="10"><%=xsdType%></textarea>
      <br/>
      <input name="xsdType" type="file" size="100"/>
    </td>
  </tr>
</table>

<br/>
<input type="hidden" name="id" value="<%=id%>" />
<input type="submit" name="dataContainerReferenceTypeFormSubmit" value="Save" />
<input type="submit" name="dataContainerReferenceTypeFormSubmit" value="Cancel"/>
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