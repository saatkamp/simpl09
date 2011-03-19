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
import org.simpl.core.SIMPLCore;
import org.simpl.core.helpers.Parameter;
import org.simpl.resource.management.client.DataSource;
import org.simpl.resource.management.client.LateBinding;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;

/**
 * <b>Purpose:</b>Web Service to access the SIMPL core data source service.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@WebService(name = "DatasourceService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Datasource {
  // Output stream to write SDO to XML string
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @WebMethod(action = "executeStatementByDataSourceObject")
  public boolean executeStatementByDataSourceObject(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceServiceInterface()
        .executeStatement(dataSource, statement, lateBinding);

    return success;
  }

  @WebMethod(action = "executeStatementByDataSourceDescriptor")
  public boolean executeStatementByDataSourceDescriptor(@WebParam(name = "dataSourceDescriptor") String dataSourceDescriptor,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceServiceInterface()
        .executeStatement(dataSourceDescriptor, statement, lateBinding);

    return success;
  }
  
  @WebMethod(action = "retrieveDataByDataSourceObject")
  public String retrieveDataByDataSourceObject(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCore.getInstance().dataSourceServiceInterface()
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
  public String retrieveDataByDataSourceDescriptor(@WebParam(name = "dataSource") String dataSourceDescriptor,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCore.getInstance().dataSourceServiceInterface()
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
  
  @WebMethod(action = "writeBackByDataSourceObject")
  public boolean writeBackByDataSourceObject(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "dataObject") String data,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.getInstance().dataSourceServiceInterface()
        .writeBack(dataSource, dataObject, lateBinding);

    return success;
  }

  @WebMethod(action = "writeBackByDataSourceDescriptor")
  public boolean writeBackByDataSourceDescriptor(@WebParam(name = "dataSourceDescriptor") String dataSourceDescriptor,
      @WebParam(name = "dataObject") String data,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.getInstance().dataSourceServiceInterface()
        .writeBack(dataSourceDescriptor, dataObject, lateBinding);

    return success;
  }
  
  @WebMethod(action = "writeDataByDataSourceObjectObject")
  public boolean writeDataByDataSourceObject(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "dataObject") String data,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.getInstance().dataSourceServiceInterface()
        .writeData(dataSource, dataObject, target, lateBinding);

    return success;
  }

  @WebMethod(action = "writeDataByDataSourceDescriptor")
  public boolean writeDataByDataSourceDescriptor(@WebParam(name = "dataSourceDescriptor") String dataSourceDescriptor,
      @WebParam(name = "dataObject") String data,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.getInstance().dataSourceServiceInterface()
        .writeData(dataSourceDescriptor, dataObject, target, lateBinding);

    return success;
  }
  
  @WebMethod(action = "depositDataByDataSourceObject")
  public boolean depositDataByDataSourceObject(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceServiceInterface()
        .depositData(dataSource, statement, target, lateBinding);

    return success;
  }
  
  @WebMethod(action = "depositDataByDataSourceDescriptor")
  public boolean depositDataByDataSourceDescriptor(@WebParam(name = "dataSourceDescriptor") String dataSourceDescriptor,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "target") String target,
      @WebParam(name = "lateBinding") LateBinding lateBinding) throws Exception {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceServiceInterface()
        .depositData(dataSourceDescriptor, statement, target, lateBinding);

    return success;
  }

  @WebMethod(action = "getMetaData")
  public String getMetaData(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "filter") String filter) throws Exception {
    DataObject dataObject = null;
    String metaData = null;

    dataObject = SIMPLCore.getInstance().dataSourceServiceInterface()
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

    inputStream = SIMPLCore.getInstance().dataSourceServiceInterface()
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