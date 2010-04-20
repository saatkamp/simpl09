package org.simpl.rrs.webservices;

import java.io.File;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.webservices.helper.Parameter;

@WebService(name = "ManagementService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Management {
  @WebMethod(action = "Delete")
  public String Delete(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {

	  File EPR = null;
      String report = null;
	  report = RRS.getInstance().managementService().Delete(EPR);

    
    return report;
  }
  
  @WebMethod(action = "Insert")
  public String Insert(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {

	  File EPR = null;
      String report = null;
	  //report = RRS.getInstance().managementService().Insert(EPR);

    
    return report;
  }
  
  @WebMethod(action = "Update")
  public String Update(
      @WebParam(name = "dsAddress", targetNamespace = "") String dsAddress,
      @WebParam(name = "statement", targetNamespace = "") String statement,
      @WebParam(name = "dsType", targetNamespace = "") String dsType,
      @WebParam(name = "dsSubtype", targetNamespace = "") String dsSubtype)
      throws ConnectionException {

	  File EPR = null;
      String report = null;
	  //report = RRS.getInstance().managementService().Update(EPR);

    
    return report;
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
