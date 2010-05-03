package org.simpl.rrs.webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.EPR;
import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.webservices.helper.Parameter;

@WebService(serviceName = "RRSMetadataService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Metadata {
	@WebMethod(action = "getAllEPR")
	public EPR[] getAllEPR() throws ConnectionException {
		
		EPR[] AllEPR = null;

		AllEPR = RRS.getInstance().metadataService().getAllEPR();
		
		return AllEPR;
	}
	
	@WebMethod(action = "getEPR")
	public String getEPR(
			@WebParam(name = "name", targetNamespace = "") String name) throws ConnectionException {
				
		File xmlepr = null;
		xmlepr = RRS.getInstance().metadataService().getEPR(name);
		
		String output = null;
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(xmlepr);
			byte[] byteInput = new byte[fis.available()];
			output = new String(byteInput);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
				
			}
}