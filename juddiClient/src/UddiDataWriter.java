
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
	
	public static UddiDataWriter dataWriter = null;

	private UddiDataWriter() {

		Transport transport = new JAXWSTransport("default");

		try {
		  UddiDataWriter.security = transport
					.getUDDISecurityService("http://localhost:8080/juddiv3/services/security?wsdl");
		  UddiDataWriter.publish = transport.getUDDIPublishService("http://localhost:8080/juddiv3/services/publish?wsdl");
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GetAuthToken getAuthToken = new GetAuthToken();
		getAuthToken.setUserID(USERNAME);
		getAuthToken.setCred(USERPASSWORD);

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
		template.setBindingKey(source.getKey() + "_binding");
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

	// /**
	// * Writes the Business Service to the jUDDI Registry
	// * @param uddiService
	// * The UDDIService object
	// */
	// public void writeService(UDDIService uddiService) {
	// BusinessService service = new BusinessService();
	//
	// service.getDescription().addAll(uddiService.getDescList());
	// service.setServiceKey(uddiService.getKey());
	// service.setBusinessKey(uddiService.getBusinessKey());
	// service.getName().addAll(uddiService.getNameList());
	//
	// // Nicht alle Services haben Attribute
	// if (!uddiService.getReferenceList().isEmpty()) {
	// CategoryBag bag = new CategoryBag();
	// bag.getKeyedReference().addAll(uddiService.getReferenceList());
	//
	// service.setCategoryBag(bag);
	// }
	//
	// SaveService saveService = new SaveService();
	// saveService.getBusinessService().add(service);
	// saveService.setAuthInfo(this.userAuthToken.getAuthInfo());
	//
	// try {
	// this.publish.saveService(saveService);
	// } catch (DispositionReportFaultMessage e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

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
	
	public static UddiDataWriter getInstance () {
		if (dataWriter == null) {
			dataWriter = new UddiDataWriter();
		}
		return dataWriter;
	}
}
