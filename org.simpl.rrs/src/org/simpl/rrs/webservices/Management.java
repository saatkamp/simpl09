package org.simpl.rrs.webservices;

import java.io.File;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.rrs.EPR;
import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.webservices.helper.Parameter;

@WebService(name = "ManagementService", targetNamespace = "")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Management {
	@WebMethod(action = "Delete")
	@SuppressWarnings("unchecked")
	public boolean Delete(
			@WebParam(name = "EPR", targetNamespace = "") EPR epr)
			throws ConnectionException {

		boolean success = false;
		success = RRS.getInstance().managementService().Delete(epr);

		return success;
	}

	@WebMethod(action = "Insert")
	@SuppressWarnings("unchecked")
	public File Insert(
			@WebParam(name = "EPR", targetNamespace = "") EPR epr)
			throws ConnectionException, SQLException {
		
			File XMLEPR = null;
			XMLEPR = RRS.getInstance().managementService().Insert(epr);

		return XMLEPR;
	}

	@WebMethod(action = "Update")
	@SuppressWarnings("unchecked")
	public boolean Update(
			@WebParam(name = "EPR", targetNamespace = "") EPR epr)
			throws ConnectionException, SQLException {

		boolean success = false;
		success = RRS.getInstance().managementService().Update(epr);

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
