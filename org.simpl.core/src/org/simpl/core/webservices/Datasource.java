package org.simpl.core.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.core.SIMPLCore;
import org.simpl.core.datasource.exceptions.ConnectionException;
import org.simpl.core.webservices.helpers.Parameter;

import commonj.sdo.DataObject;

@WebService(name = "DatasourceService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Datasource {
  @WebMethod(action = "queryData")
  public String queryData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType)
      throws ConnectionException {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCore.datasourceServiceProvider.getInstance(dsType).queryData(
        dsAddress, statement);
    data = Parameter.serialize(dataObject);

    return data;
  }

  @WebMethod(action = "defineData")
  public boolean defineData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType)
      throws ConnectionException {
    boolean success = false;

    success = SIMPLCore.datasourceServiceProvider.getInstance(dsType).defineData(
        dsAddress, statement);

    return success;
  }

  @WebMethod(action = "manipulateData")
  public boolean manipulateData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "data", targetNamespace = "") String data,
      @WebParam(name = "dsType", targetNamespace = "") String dsType)
      throws ConnectionException {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.datasourceServiceProvider.getInstance(dsType).manipulateData(
        dsAddress, statement, dataObject);

    return success;
  }

  /*
   * @WebMethod(action = "getSupportedDatasourceTypes") public String
   * getSupportedDatasourceTypes() { // return List<String> -> String return null; }
   * @WebMethod(action = "getDatasourceTypes") public String getDatasourceTypes() { //
   * return List<String> -> String return null; }
   * @WebMethod(action = "getDatasourceSubTypes") public String
   * getDatasourceSubTypes(String datasourceType) { // return List<String> -> String
   * return null; }
   * @WebMethod(action = "getDatasourceLanguages") public String
   * getDatasourceLanguages(String datasourceSubType) { // return List<String> -> String
   * return null; }
   */
}