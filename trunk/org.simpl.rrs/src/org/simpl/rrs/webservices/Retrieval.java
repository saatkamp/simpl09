package org.simpl.rrs.webservices;

import java.io.File;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.webservices.helper.Parameter;

@WebService(name = "RetrievalService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Retrieval {
  @WebMethod(action = "retrieveData")
  public String retrieveData(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {
    Object dataObj = null;
    String data = null;
    File EPR = null;

    dataObj = RRS.getInstance().retrievalService().get(EPR);
    data = Parameter.serialize(dataObj);

    return data;
  }
  
  @WebMethod(action = "getDSAdapterTypes")
  public String getDataSourceTypes() {
    return Parameter.serialize(RRS.getInstance().getDSAdapterType());
  }

  @WebMethod(action = "getDSAdapterSubtypes")
  public String getDataSourceSubtypes(String dsType) {
    return Parameter.serialize(RRS.getInstance().getDSAdapterSubtypes(dsType));
  }

  @WebMethod(action = "getDSAdapterLanguages")
  public String getDataSourceLanguages(String dsSubtype) {
    return Parameter.serialize(RRS.getInstance().getDSAdapterLanguages(dsSubtype));
  }
}
