package org.simpl.rrs.webservices;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.webservices.helper.Parameter;

@WebService(name = "MetadataService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Metadata {
	@WebMethod(action = "getAllEPR")
	public List<HashMap<String, String>> getAllEPR() throws ConnectionException {
		List<HashMap<String, String>> AllEPR = new LinkedList<HashMap<String, String>>();

		AllEPR = RRS.getInstance().metadataService().getAllEPR();
		
		return AllEPR;
	}
	
	@WebMethod(action = "getEPR")
	public File getEPR(
			@WebParam(name = "name", targetNamespace = "") String name) throws ConnectionException {
				
		File EPR = null;
		EPR = RRS.getInstance().metadataService().getEPR(name);
		
		return EPR;
				
			}
}