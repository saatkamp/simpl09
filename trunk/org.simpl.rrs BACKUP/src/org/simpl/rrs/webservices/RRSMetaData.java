package org.simpl.rrs.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.RRS;
import org.simpl.rrs.webservices.client.EPR;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
@WebService(name = "RRSMetaDataService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class RRSMetaData {
	@WebMethod(action = "getEPR")
	public EPR getEPR(
			@WebParam(name = "eprName", targetNamespace = "") String eprName) {
		EPR response = RRS.getInstance().metadataService().getEPR(eprName);

		return response;
	}
	
	@WebMethod(action = "getAllEPR")
	public EPR[] getAllEPR() {
		EPR[] response = RRS.getInstance().metadataService().getAllEPR();

		return response;
	}
	
	@WebMethod(action = "getAvailableAdapters")
	public String[] getAvailableAdapters() {
		String[] response = RRS.getInstance().metadataService().getAvailableAdapters();

		return response;
	}
}