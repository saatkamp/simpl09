package org.simpl.rrs.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.RRS;
import org.simpl.rrs.model.EPR;
import org.simpl.rrs.webservices.helper.Parameter;

@WebService(name = "RRSRetrievalService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class RRSRetrieval {
	@WebMethod(action = "get")
	public String get(
			@WebParam(name = "EPR", targetNamespace = "") EPR epr){
		Object dataObj = null;
		String data = null;

		dataObj = RRS.getInstance().retrievalService().get(epr);
		data = Parameter.serialize(dataObj);

		return data;
	}
}
