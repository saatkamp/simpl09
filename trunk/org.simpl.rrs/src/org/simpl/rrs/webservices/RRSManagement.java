package org.simpl.rrs.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.RRS;

import de.uni_stuttgart.simpl.rrs.EPR;

@WebService(name = "RRSManagementService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class RRSManagement {
	@WebMethod(action = "delete")
	public boolean delete(
			@WebParam(name = "epr", targetNamespace = "") EPR epr){

		boolean success = false;
		success = RRS.getInstance().managementService().Delete(epr);

		return success;
	}

	@WebMethod(action = "insert")
	public boolean insert(
			@WebParam(name = "epr", targetNamespace = "") EPR epr){
		
			return RRS.getInstance().managementService().Insert(epr);
	}

	@WebMethod(action = "update")
	public boolean update(
			@WebParam(name = "epr", targetNamespace = "") EPR epr){
		
		return RRS.getInstance().managementService().Update(epr);
	}
}
