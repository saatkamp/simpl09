package org.simpl.core.webservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.io.IOUtils;
import org.simpl.core.helpers.Parameter;
import org.simpl.core.services.SIMPLCoreService;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;

/**
 * <b>Purpose:</b>Web Service to access the SIMPL core.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@WebService(name = "SIMPLCoreService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SIMPLCore {
  // Output stream to write SDO to XML string
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @WebMethod(action = "issueCommandByDataSourceObject")
  public boolean issueCommandByDataSourceObject(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCoreService.getInstance().getService()
        .issueCommand(dataSource, statement, lateBinding);

    return success;
  }

  @WebMethod(action = "executeStatementByDataSourceDescriptor")
  public boolean issueCommandByDataSourceDescriptor(
      @WebParam(name = "dataSourceDescriptor") String dataSourceDescriptor,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCoreService.getInstance().getService()
        .issueCommand(dataSourceDescriptor, statement, lateBinding);

    return success;
  }

  @WebMethod(action = "retrieveDataByDataSourceObject")
  public String retrieveDataByDataSourceObject(
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

  @WebMethod(action = "retrieveDataByDataSourceDescriptor")
  public String retrieveDataByDataSourceDescriptor(
      @WebParam(name = "dataSource") String dataSourceDescriptor,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCoreService.getInstance().getService()
        .retrieveData(dataSourceDescriptor, statement, lateBinding);

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

  @WebMethod(action = "writeDataBackByDataSourceObjectObject")
  public boolean writeDataBackByDataSourceObject(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "dataObject") String data,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCoreService.getInstance().getService()
        .writeDataBack(dataSource, dataObject, target, lateBinding);

    return success;
  }

  @WebMethod(action = "writeDataBackByDataSourceDescriptor")
  public boolean writeDataBackByDataSourceDescriptor(
      @WebParam(name = "dataSourceDescriptor") String dataSourceDescriptor,
      @WebParam(name = "dataObject") String data,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCoreService.getInstance().getService()
        .writeDataBack(dataSourceDescriptor, dataObject, target, lateBinding);

    return success;
  }

  @WebMethod(action = "queryDataByDataSourceObject")
  public boolean queryDataByDataSourceObject(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCoreService.getInstance().getService()
        .queryData(dataSource, statement, target, lateBinding);

    return success;
  }

  @WebMethod(action = "queryDataByDataSourceDescriptor")
  public boolean queryDataByDataSourceDescriptor(
      @WebParam(name = "dataSourceDescriptor") String dataSourceDescriptor,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCoreService.getInstance().getService()
        .queryData(dataSourceDescriptor, statement, target, lateBinding);

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