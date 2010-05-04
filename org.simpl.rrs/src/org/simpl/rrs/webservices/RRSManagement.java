package org.simpl.rrs.webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.model.EPR;
import org.simpl.rrs.webservices.helper.Parameter;

@WebService(serviceName = "RRSManagementService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class RRSManagement {
	@WebMethod(action = "delete")
	public boolean delete(
			@WebParam(name = "epr", targetNamespace = "") EPR epr)
			throws ConnectionException {

		boolean success = false;
		success = RRS.getInstance().managementService().Delete(epr);

		return success;
	}

	@WebMethod(action = "insert")
	public String insert(
			@WebParam(name = "epr", targetNamespace = "") EPR epr)
			throws ConnectionException {
		
			File xmlepr = null;
			try {
				xmlepr = RRS.getInstance().managementService().Insert(epr);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	@WebMethod(action = "update")
	public boolean update(
			@WebParam(name = "epr", targetNamespace = "") EPR epr)
			throws ConnectionException {

		boolean success = false;
		try {
			success = RRS.getInstance().managementService().Update(epr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;
	}

	@WebMethod(action = "getDSAdapterTypes")
	public String getDSAdapterTypes() {
		return Parameter.serialize(RRS.getInstance().getDSAdapterType());
	}

	@WebMethod(action = "getDSAdapterSubtypes")
	public String getDSAdapterSubtypes(String dsType) {
		return Parameter.serialize(RRS.getInstance().getDSAdapterSubtypes(
				dsType));
	}

	@WebMethod(action = "getDSAdapterLanguages")
	public String getDSAdapterLanguages(String dsSubtype) {
		return Parameter.serialize(RRS.getInstance().getDSAdapterLanguages(
				dsSubtype));
	}
}
