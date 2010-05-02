package org.simpl.rrs.webservices;

import java.io.File;
import java.sql.SQLException;
import java.util.LinkedHashMap;

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
	@SuppressWarnings("unchecked")
	public boolean Delete(
			@WebParam(name = "EPR", targetNamespace = "") LinkedHashMap<String, String> EPR)
			throws ConnectionException {

		boolean success = false;
		success = RRS.getInstance().managementService().Delete(EPR);

		return success;
	}

	@WebMethod(action = "Insert")
	@SuppressWarnings("unchecked")
	public File Insert(
			@WebParam(name = "EPR", targetNamespace = "") LinkedHashMap<String, String> EPR)
			throws ConnectionException, SQLException {
		
			File XMLEPR = null;
			XMLEPR = RRS.getInstance().managementService().Insert(EPR);

		return XMLEPR;
	}

	@WebMethod(action = "Update")
	@SuppressWarnings("unchecked")
	public boolean Update(
			@WebParam(name = "EPR", targetNamespace = "") LinkedHashMap<String, String> EPR)
			throws ConnectionException, SQLException {

		boolean success = false;
		success = RRS.getInstance().managementService().Update(EPR);

		return success;
	}

	@WebMethod(action = "getDSAdapterTypes")
	public String getDataSourceTypes() {
		return Parameter.serialize(RRS.getInstance().getDSAdapterType());
	}

	@WebMethod(action = "getDSAdapterSubtypes")
	public String getDataSourceSubtypes(String dsType) {
		return Parameter.serialize(RRS.getInstance().getDSAdapterSubtypes(
				dsType));
	}

	@WebMethod(action = "getDSAdapterLanguages")
	public String getDataSourceLanguages(String dsSubtype) {
		return Parameter.serialize(RRS.getInstance().getDSAdapterLanguages(
				dsSubtype));
	}
}
