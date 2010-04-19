import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.juddi.v3.client.transport.JAXWSTransport;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.FindBinding;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.GetBindingDetail;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;

public class UddiDatasourceReader implements IUddiConfig {

	UDDIInquiryPortType inquiry = null;

	public UddiDatasourceReader() {
				Transport transport = new JAXWSTransport("default");

				try {
					this.inquiry = transport.getUDDIInquiryService("http://localhost:8080/juddiv3/services/inquiry?wsdl");
				} catch (TransportException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}

	/**
	 * Gets all datasources
	 * 
	 * @return ArrayList of all datasources inside the jUDDI Registry
	 */
	public ArrayList<UddiDataSource> getAllDarasources() {

		FindService findService = new FindService();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "category");

		reference.setKeyValue("datasource");

		reference.setKeyName("category");

		bag.getKeyedReference().add(reference);

		findService.setCategoryBag(bag);

		ServiceList sd;

		ArrayList<UddiDataSource> datasources = new ArrayList<UddiDataSource>();

		ArrayList<ServiceInfo> serviceLists = new ArrayList<ServiceInfo>();

		ArrayList<String> keyList = new ArrayList<String>();

		try {
			sd = this.inquiry.findService(findService);

			serviceLists = (ArrayList<ServiceInfo>) sd.getServiceInfos()
					.getServiceInfo();

			for (ServiceInfo serviceInfo : serviceLists) {
				keyList.add(serviceInfo.getServiceKey());
			}
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GetServiceDetail gsd = new GetServiceDetail();
		gsd.getServiceKey().addAll(keyList);

		ArrayList<BusinessService> businessServices = new ArrayList<BusinessService>();

		try {
			ServiceDetail serviceDetail = this.inquiry.getServiceDetail(gsd);

			businessServices = (ArrayList<BusinessService>) serviceDetail
					.getBusinessService();

		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (BusinessService businessService : businessServices) {
			UddiDataSource dataSource = new UddiDataSource(businessService
					.getBusinessKey());
			dataSource.setAddress(businessService.getBindingTemplates()
					.getBindingTemplate().get(0).getAccessPoint().getValue());
			dataSource.setDescList((ArrayList<Description>) businessService
					.getDescription());
			dataSource.setKey(businessService.getServiceKey());
			dataSource.setName(businessService.getName().get(0).getValue());
			dataSource
					.setReferenceList((ArrayList<KeyedReference>) businessService
							.getCategoryBag().getKeyedReference());
			datasources.add(dataSource);
		}

		return datasources;

	}

	/**
	 * Gets all Datasources by its TypeValue
	 * 
	 * @param type
	 *            Example: filesystem
	 * @return ArrayList of datasources
	 */
	public ArrayList<UddiDataSource> getByType(String type) {
		FindBinding findBinding = new FindBinding();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "type");

		reference.setKeyValue(type);

		reference.setKeyName("type");

		// /////////////////////////////////////////////////
		// KeyedReference reference2 = new KeyedReference();
		//
		// reference2.setTModelKey(KEYPREFIX + "type");
		//
		// reference2.setKeyValue(type);
		//
		// reference2.setKeyName("subtype");
		//		
		// ////////////////////////////////////////////////

		bag.getKeyedReference().add(reference);

		findBinding.setCategoryBag(bag);

		BindingDetail bd;

		ArrayList<UddiDataSource> datasources = new ArrayList<UddiDataSource>();

		ArrayList<BindingTemplate> templateList = new ArrayList<BindingTemplate>();

		try {
			bd = this.inquiry.findBinding(findBinding);

			templateList = (ArrayList<BindingTemplate>) bd.getBindingTemplate();

			for (BindingTemplate template : templateList) {
				UddiDataSource source = new UddiDataSource(template
						.getServiceKey());
				source.setAddress(template.getAccessPoint().getValue());
				source.setDescList((ArrayList<Description>) template
						.getDescription());
				source.setKey(template.getBindingKey());
				source.setReferenceList((ArrayList<KeyedReference>) template
						.getCategoryBag().getKeyedReference());
				datasources.add(source);

			}

		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datasources;
	}

	/**
	 * Gets datasources by their subtype value
	 * 
	 * @param type
	 *            The subtype value
	 * @return ArrayList of Datasources
	 */
	public ArrayList<UddiDataSource> getBySubType(String type) {
		FindBinding findBinding = new FindBinding();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "subtype");

		reference.setKeyValue(type);

		reference.setKeyName("subtype");

		bag.getKeyedReference().add(reference);

		findBinding.setCategoryBag(bag);

		BindingDetail bd;

		ArrayList<UddiDataSource> datasources = new ArrayList<UddiDataSource>();

		ArrayList<BindingTemplate> templateList = new ArrayList<BindingTemplate>();

		try {
			bd = this.inquiry.findBinding(findBinding);

			templateList = (ArrayList<BindingTemplate>) bd.getBindingTemplate();

			for (BindingTemplate template : templateList) {
				UddiDataSource source = new UddiDataSource(template
						.getServiceKey());
				source.setAddress(template.getAccessPoint().getValue());
				source.setDescList((ArrayList<Description>) template
						.getDescription());
				source.setKey(template.getBindingKey());
				source.setReferenceList((ArrayList<KeyedReference>) template
						.getCategoryBag().getKeyedReference());
				datasources.add(source);

			}

		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datasources;
	}

	/**
	 * Gets a datasource by its Key
	 * 
	 * @param key
	 *            The DatasourceKey as KEYPREFIX + key Example:
	 *            uddi:org.apache.juddi:key
	 * @return The datasource
	 */
	public UddiDataSource getByKey(String key) {
		GetBindingDetail getbd = new GetBindingDetail();

		BindingDetail bd = new BindingDetail();

		getbd.getBindingKey().add(key);

		try {
			bd = inquiry.getBindingDetail(getbd);
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BindingTemplate template = bd.getBindingTemplate().get(0);

		UddiDataSource source = new UddiDataSource(template.getServiceKey());
		source.setAddress(template.getAccessPoint().getValue());
		source.setDescList((ArrayList<Description>) template.getDescription());
		source.setKey(template.getBindingKey());
		source.setReferenceList((ArrayList<KeyedReference>) template
				.getCategoryBag().getKeyedReference());

		return source;

	}
}
