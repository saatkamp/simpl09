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
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;

/**
 * Web Service to access the SIMPL core data source service.
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@WebService(name = "DatasourceService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Datasource {
  // Output stream to write SDO to XML string
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @WebMethod(action = "executeStatement")
  public boolean executeStatement(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement) throws ConnectionException {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceService().executeStatement(dataSource,
        statement);

    return success;
  }

  @WebMethod(action = "retrieveData")
  public String retrieveData(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement) throws ConnectionException {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCore.getInstance().dataSourceService().retrieveData(dataSource,
        statement);

    try {
      XMLDocument xmlDocument = XMLHelper.INSTANCE.createDocument(dataObject,
          "commonj.sdo", "dataObject");
      xmlDocument.setEncoding("UTF-8");
      XMLHelper.INSTANCE.save(xmlDocument, outputStream, null);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    data = new String(outputStream.toByteArray());

    return data;
  }

  @WebMethod(action = "writeBack")
  public boolean writeBack(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "dataObject") String data) throws ConnectionException {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.getInstance().dataSourceService().writeBack(dataSource,
        dataObject);

    return success;
  }

  @WebMethod(action = "writeData")
  public boolean writeData(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "dataObject") String data, @WebParam(name = "target") String target)
      throws ConnectionException {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.getInstance().dataSourceService().writeData(dataSource,
        dataObject, target);

    return success;
  }

  @WebMethod(action = "depositData")
  public boolean depositData(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "target") String target) throws ConnectionException {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceService().depositData(dataSource,
        statement, target);

    return success;
  }

  @WebMethod(action = "getMetaData")
  public String getMetaData(@WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "filter") String filter) throws ConnectionException {
    DataObject dataObject = null;
    String metaData = null;

    dataObject = SIMPLCore.getInstance().dataSourceService().getMetaData(dataSource,
        filter);

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
      throws ConnectionException {
    InputStream inputStream = null;
    String metaDataSchema = "";
    StringWriter writer = new StringWriter();

    inputStream = SIMPLCore.getInstance().dataSourceService().getMetadataSchemaFile(
        dataSource);

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

  @WebMethod(action = "getDataFormatSchema")
  public String getDataFormatSchema(@WebParam(name = "dataSource") DataSource dataSource)
      throws ConnectionException {
    InputStream inputStream = null;
    String dataFormatSchema = "";
    StringWriter writer = new StringWriter();

    inputStream = SIMPLCore.getInstance().dataSourceService().getDataFormatSchemaFile(
        dataSource);

    // convert inputStream to String
    try {
      IOUtils.copy(inputStream, writer);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    dataFormatSchema = writer.toString();

    return dataFormatSchema;
  }

  @WebMethod(action = "getDataSourceTypes")
  public String getDataSourceTypes() {
    return Parameter.serialize(SIMPLCore.getInstance().dataSourceService()
        .getDataSourceTypes());
  }

  @WebMethod(action = "getDataSourceSubTypes")
  public String getDataSourceSubTypes(String dsType) {
    return Parameter.serialize(SIMPLCore.getInstance().dataSourceService()
        .getDataSourceSubTypes(dsType));
  }

  @WebMethod(action = "getDataSourceLanguages")
  public String getDataSourceLanguages(String dsSubtype) {
    return Parameter.serialize(SIMPLCore.getInstance().dataSourceService()
        .getDataSourceLanguages(dsSubtype));
  }

  @WebMethod(action = "getSupportedDataFormatTypes")
  public String getSupportedDataFormatTypes(
      @WebParam(name = "dataSource") DataSource dataSource) {
    return Parameter.serialize(SIMPLCore.getInstance().dataSourceService()
        .getSupportedDataFormatTypes(dataSource));
  }

  @WebMethod(action = "getSupportedConvertDataFormatTypes")
  public String getSupportedConvertDataFormatTypes(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "dataFormatType") String dataFormatType) {
    return Parameter.serialize(SIMPLCore.getInstance().dataSourceService()
        .getSupportedConvertDataFormatTypes(dataSource));
  }
}