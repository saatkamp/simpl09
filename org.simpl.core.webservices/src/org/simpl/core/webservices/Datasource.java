package org.simpl.core.webservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.core.SIMPLCore;
import org.simpl.core.datasource.DatasourceServiceProvider;
import org.simpl.core.datasource.exceptions.ConnectionException;
import org.simpl.core.helpers.Parameter;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;

@WebService(name = "DatasourceService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Datasource {
  DatasourceServiceProvider datasourceServiceProvider = new DatasourceServiceProvider();

  @WebMethod(action = "queryData")
  public String queryData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCore.getInstance().datasourceService(dsType, dsSubtype).queryData(
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

    success = SIMPLCore.getInstance().datasourceService(dsType, dsSubtype).depositData(
        dsAddress, statement, target);

    return success;
  }

  @WebMethod(action = "defineData")
  public boolean defineData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {
    boolean success = false;

    success = SIMPLCore.getInstance().datasourceService(dsType, dsSubtype).defineData(
        dsAddress, statement);

    return success;
  }

  @WebMethod(action = "manipulateData")
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
    success = SIMPLCore.getInstance().datasourceService(dsType, dsSubtype)
        .manipulateData(dsAddress, statement, dataObject);

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
        
    dataObject = SIMPLCore.getInstance().datasourceService(dsType, dsSubtype)
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

  @WebMethod(action = "getDatasourceTypes")
  public String getDatasourceTypes() {
    return Parameter.serialize(SIMPLCore.getInstance().getDatasourceTypes());
  }

  @WebMethod(action = "getDatasourceSubtypes")
  public String getDatasourceSubtypes(String dsType) {
    return Parameter.serialize(SIMPLCore.getInstance().getDatasourceSubtypes(dsType));
  }

  @WebMethod(action = "getDatasourceLanguages")
  public String getDatasourceLanguages(String dsSubtype) {
    return Parameter.serialize(SIMPLCore.getInstance().getDatasourceLanguages(dsSubtype));
  }
}