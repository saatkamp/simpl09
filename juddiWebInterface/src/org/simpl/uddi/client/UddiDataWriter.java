package org.simpl.uddi.client;


import java.rmi.RemoteException;

import org.apache.juddi.v3.client.transport.JAXWSTransport;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.AccessPoint;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BindingTemplates;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.DeleteService;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.SaveBusiness;
import org.uddi.api_v3.SaveService;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

public class UddiDataWriter implements IUddiConfig {

	private static UDDISecurityPortType security = null;

	private static UDDIPublicationPortType publish = null;

	private AuthToken userAuthToken = null;
	
	private static UddiDataWriter  dataWriter = null;
	
	private String username = "";
	
	private String password = "";
	
	private String address = "";

	private UddiDataWriter(String address, String username, String password) {
		
		this.address = address;
		
		this.username = username;
		
		this.password = password;

		Transport transport = new JAXWSTransport("default");

		try {
		  UddiDataWriter.security = transport
					.getUDDISecurityService(this.address + "/services/security?wsdl");
		  UddiDataWriter.publish = transport.getUDDIPublishService(this.address + "/services/publish?wsdl");
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GetAuthToken getAuthToken = new GetAuthToken();
		getAuthToken.setUserID(this.username);
		getAuthToken.setCred(this.password);

		try {
			this.userAuthToken = UddiDataWriter.security.getAuthToken(getAuthToken);
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Writes the Datasource to the jUDDI Registry
	 * 
	 * @param source
	 *            The UddiDataSource object
	 */
	public void writeDatasource(UddiDataSource source) {
		BusinessService service = new BusinessService();

		BindingTemplate template = new BindingTemplate();

		service.getDescription().addAll(source.getDescList());

		service.setServiceKey(source.getKey());
				
		service.setBusinessKey(source.getBusinessKey());
		Name name = new Name();
		name.setValue(source.getName());
		service.getName().add(name);
		template.setBindingKey(source.getKey()+ "_binding");
		AccessPoint accessPoint = new AccessPoint();
		accessPoint.setUseType(ACCESS_POINT_TYPE);
		accessPoint.setValue(source.getAddress());

		template.setAccessPoint(accessPoint);
		
		template.setServiceKey(source.getKey());

		CategoryBag bag = new CategoryBag();
		bag.getKeyedReference().addAll(source.getReferenceList());

		service.setCategoryBag(bag);

		BindingTemplates templates = new BindingTemplates();

		templates.getBindingTemplate().add(template);

		service.setBindingTemplates(templates);

		SaveService saveService = new SaveService();

		saveService.setAuthInfo(userAuthToken.getAuthInfo());

		saveService.getBusinessService().add(service);
		try {
		  UddiDataWriter.publish.saveService(saveService);
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Writes a Business to the jUDDI Registry
	 * 
	 * @param uddiBusiness
	 *            The UddiBusiness object
	 */
	public void writeBusiness(UddiBusiness uddiBusiness) {
		BusinessEntity business = new BusinessEntity();
		business.setBusinessKey(uddiBusiness.getKey());
		business.getDescription().addAll(uddiBusiness.getDescList());
		business.getName().addAll(uddiBusiness.getNameList());

		// Nicht alle Business haben Attribute
		if (!uddiBusiness.getReferenceList().isEmpty()) {
			CategoryBag bag = new CategoryBag();
			bag.getKeyedReference().addAll(uddiBusiness.getReferenceList());

			business.setCategoryBag(bag);
		}

		SaveBusiness saveBusiness = new SaveBusiness();
		saveBusiness.getBusinessEntity().add(business);
		saveBusiness.setAuthInfo(userAuthToken.getAuthInfo());

		try {
		  UddiDataWriter.publish.saveBusiness(saveBusiness);
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteDatasource(String key) {
		DeleteService deleteService = new DeleteService();
		deleteService.setAuthInfo(this.userAuthToken.getAuthInfo());
		deleteService.getServiceKey().add(key);
		try {
			publish.deleteService(deleteService);
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static UddiDataWriter getInstance(String address, String username, String password) {
		if (dataWriter == null) {
			dataWriter = new UddiDataWriter(address, username, password);			
		}
		
		return dataWriter;
	}
}
