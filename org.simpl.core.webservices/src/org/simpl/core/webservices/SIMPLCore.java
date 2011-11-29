package org.simpl.core.webservices;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.io.IOUtils;
import org.simpl.core.clients.RMClient;
import org.simpl.core.services.SIMPLCoreService;
import org.simpl.resource.management.ResourceManagement;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b>Web Service to access the SIMPL core.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: SIMPLCore.java 1822 2011-08-04 17:25:42Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@WebService(name = "SIMPLCoreService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SIMPLCore {
  // Output stream to write SDO to XML string
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @WebMethod(action = "issueCommandByDataSource")
  public boolean issueCommandByDataSource(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCoreService.getInstance().getService()
        .issueCommand(dataSource, statement, lateBinding);

    return success;
  }

  @WebMethod(action = "executeStatementByDataSourceName")
  public boolean issueCommandByDataSourceName(
      @WebParam(name = "dataSourceName") String dataSourceName,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCoreService.getInstance().getService()
        .issueCommand(dataSourceName, statement, lateBinding);

    return success;
  }

  @WebMethod(action = "retrieveDataByDataSource")
  public String retrieveDataByDataSource(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCoreService.getInstance().getService()
        .retrieveData(dataSource, statement, lateBinding);

    if (dataObject != null) {
      try {
        XMLDocument xmlDocument = XMLHelper.INSTANCE.createDocument(dataObject,
            "commonj.sdo", "dataObject");
        xmlDocument.setEncoding("UTF-8");
        XMLHelper.INSTANCE.save(xmlDocument, outputStream, null);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    data = new String(outputStream.toByteArray());

    return data;
  }

  @WebMethod(action = "retrieveDataByDataSourceName")
  public String retrieveDataByDataSourceName(
      @WebParam(name = "dataSourceName") String dataSourceName,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCoreService.getInstance().getService()
        .retrieveData(dataSourceName, statement, lateBinding);

    if (dataObject != null) {
      try {
        XMLDocument xmlDocument = XMLHelper.INSTANCE.createDocument(dataObject,
            "commonj.sdo", "dataObject");
        xmlDocument.setEncoding("UTF-8");
        XMLHelper.INSTANCE.save(xmlDocument, outputStream, null);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    data = new String(outputStream.toByteArray());

    return data;
  }

  @WebMethod(action = "writeDataBackByDataSource")
  public boolean writeDataBackByDataSource(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "dataObject") String data,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;
    DataObject dataObject = null;
    
    // retrieve the schema file and define it for SDO
    ResourceManagement resourceManagement = new ResourceManagement();
    
    String schema = resourceManagement.getDataFormatSchema(
        dataSource.getConnector().getDataConverter().getWorkflowDataFormat());
    InputStream schemaInputStream = new ByteArrayInputStream(schema.getBytes());
    XSDHelper.INSTANCE.define(schemaInputStream, null);

    // convert xml string to SDO
    XMLDocument xmlDoc = XMLHelper.INSTANCE.load(data);
    dataObject = xmlDoc.getRootObject();

    // write data back
    success = SIMPLCoreService.getInstance().getService()
        .writeDataBack(dataSource, dataObject, target, lateBinding);

    return success;
  }

  @WebMethod(action = "writeDataBackByDataSourceName")
  public boolean writeDataBackByDataSourceName(
      @WebParam(name = "dataSourceName") String dataSourceName,
      @WebParam(name = "dataObject") String data,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;
    DataSource dataSource = RMClient.getInstance().getDataSourceByName(dataSourceName);

    success = writeDataBackByDataSource(dataSource, data, target, lateBinding);

    return success;
  }

  @WebMethod(action = "queryDataByDataSource")
  public boolean queryDataByDataSource(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCoreService.getInstance().getService()
        .queryData(dataSource, statement, target, lateBinding);

    return success;
  }

  @WebMethod(action = "queryDataByDataSourceName")
  public boolean queryDataByDataSourceName(
      @WebParam(name = "dataSourceName") String dataSourceName,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCoreService.getInstance().getService()
        .queryData(dataSourceName, statement, target, lateBinding);

    return success;
  }

  @WebMethod(action = "getMetaData")
  public String getMetaData(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "filter") String filter) throws Exception {
    DataObject dataObject = null;
    String metaData = null;

    dataObject = SIMPLCoreService.getInstance().getService()
        .getMetaData(dataSource, filter);

    try {
      XMLDocument xmlDocument = XMLHelper.INSTANCE.createDocument(dataObject,
          "commonj.sdo", "dataObject");
      xmlDocument.setEncoding("UTF-8");
      XMLHelper.INSTANCE.save(xmlDocument, outputStream, null);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    metaData = new String(outputStream.toByteArray());

    return metaData;
  }

  @WebMethod(action = "getMetaDataSchema")
  public String getMetaDataSchema(@WebParam(name = "dataSource") DataSource dataSource)
      throws Exception {
    InputStream inputStream = null;
    String metaDataSchema = "";
    StringWriter writer = new StringWriter();

    inputStream = SIMPLCoreService.getInstance().getService()
        .getMetaDataSchemaFile(dataSource);

    // convert inputStream to String
    try {
      IOUtils.copy(inputStream, writer);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    metaDataSchema = writer.toString();

    return metaDataSchema;
  }
}