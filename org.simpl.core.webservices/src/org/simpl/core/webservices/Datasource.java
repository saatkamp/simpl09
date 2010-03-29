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
import org.simpl.core.plugins.datasource.DataSourcePlugin;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;

/**
 * Web Service of the data source service.
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@WebService(name = "DatasourceService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Datasource {
  @WebMethod(action = "retrieveData")
  public String retrieveData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCore.getInstance().dataSourceService(dsType, dsSubtype).retrieveData(
        dsAddress, statement);
    data = Parameter.serialize(dataObject);

    return data;
  }

  @WebMethod(action = "depositData")
  public boolean depositData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype,
      @WebParam(name = "target", targetNamespace = "") String target)
      throws ConnectionException {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceService(dsType, dsSubtype).depositData(
        dsAddress, statement, target);

    return success;
  }

  @WebMethod(action = "executeStatement")
  public boolean executeStatement(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceService(dsType, dsSubtype).executeStatement(
        dsAddress, statement);

    return success;
  }

  @WebMethod(action = "writeBack")
  public boolean manipulateData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "data", targetNamespace = "") String data,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.getInstance().dataSourceService(dsType, dsSubtype)
        .writeBack(dsAddress, statement, dataObject);

    return success;
  }

  @WebMethod(action = "getMetaData")
  public String getMetaData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {
    DataObject dataObject = null;
    String metaData = null;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    dataObject = SIMPLCore.getInstance().dataSourceService(dsType, dsSubtype)
        .getMetaData(dsAddress);

    try {
      XMLHelper.INSTANCE.save(dataObject, "commonj.sdo", "dataObject", outputStream);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    metaData = new String(outputStream.toByteArray());

    return metaData;
  }

  @WebMethod(action = "getMetaDataSchema")
  public String getMetaDataSchema(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {
    InputStream inputStream = null;
    String metaDataSchema = "";
    StringWriter writer = new StringWriter();    
    
    inputStream = ((DataSourcePlugin)SIMPLCore.getInstance().dataSourceService(dsType, dsSubtype)).getMetaDataSchema();
    
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
  
  @WebMethod(action = "getDataSourceTypes")
  public String getDataSourceTypes() {
    return Parameter.serialize(SIMPLCore.getInstance().getDataSourceTypes());
  }

  @WebMethod(action = "getDataSourceSubtypes")
  public String getDataSourceSubtypes(String dsType) {
    return Parameter.serialize(SIMPLCore.getInstance().getDataSourceSubtypes(dsType));
  }

  @WebMethod(action = "getDataSourceLanguages")
  public String getDataSourceLanguages(String dsSubtype) {
    return Parameter.serialize(SIMPLCore.getInstance().getDataSourceLanguages(dsSubtype));
  }
  
  @WebMethod(action = "getDataFormatTypes")
  public String getDataFormatTypes() {
    return Parameter.serialize(SIMPLCore.getInstance().getDataFormatTypes());
  }
  
  @WebMethod(action = "getDataFormatSubtypes")
  public String getDataFormatSubtypes(String dfType) {
    return Parameter.serialize(SIMPLCore.getInstance().getDataFormatSubtypes(dfType));
  }
}