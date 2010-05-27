package org.simpl.uddi.client;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.juddi.v3.client.transport.JAXWSTransport;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;

public class UddiDataSourceReader implements IUddiConfig {

	UDDIInquiryPortType inquiry = null;

	String address = "";

	Transport transport = null;

	public static UddiDataSourceReader datasourceReader = null;

	private UddiDataSourceReader(String address) {
		this.address = address;
		this.transport = new JAXWSTransport("default");

		try {
			inquiry = this.transport.getUDDIInquiryService(this.address
					+ "/services/inquiry?wsdl");
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void refresh() {
		try {
			inquiry = this.transport.getUDDIInquiryService(this.address
					+ "/services/inquiry?wsdl");
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
	public ArrayList<UddiDataSource> getAllDatasources() {

		FindService findService = new FindService();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "category");

		reference.setKeyValue("datasource");

		reference.setKeyName("category");

		bag.getKeyedReference().add(reference);

		findService.setCategoryBag(bag);

		return getDataSources(findService);

	}

	/**
	 * Gets all Datasources by its TypeValue
	 * 
	 * @param type
	 *            Example: filesystem
	 * @return ArrayList of datasources
	 */
	public ArrayList<UddiDataSource> getByType(String type) {
		FindService findService = new FindService();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "category");

		reference.setKeyValue(type);

		reference.setKeyName("type");

		bag.getKeyedReference().add(reference);

		findService.setCategoryBag(bag);

		return getDataSources(findService);
	}

	/**
	 * Gets datasources by their subtype value
	 * 
	 * @param type
	 *            The subtype value
	 * @return ArrayList of Datasources
	 */
	public ArrayList<UddiDataSource> getBySubType(String type) {
		FindService findService = new FindService();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "category");

		reference.setKeyValue(type);

		reference.setKeyName("subtype");

		bag.getKeyedReference().add(reference);

		findService.setCategoryBag(bag);

		return getDataSources(findService);
	}

	/**
	 * Gets a datasource by its name value
	 * 
	 * @param name
	 *            of the searched data source
	 * @return The data source with the given name.
	 */
	public UddiDataSource getByName(String name) {
		return getByKey(IUddiConfig.KEYPREFIX + name);
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
		GetServiceDetail getServiceDetail = new GetServiceDetail();

		UddiDataSource source = null;

		ServiceDetail serviceDetail = new ServiceDetail();

		getServiceDetail.getServiceKey().add(key.toLowerCase());

		try {
			serviceDetail = inquiry.getServiceDetail(getServiceDetail);
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (serviceDetail.getBusinessService() != null) {
			BusinessService businessService = serviceDetail
					.getBusinessService().get(0);

			source = new UddiDataSource(businessService.getBusinessKey(),
					businessService.getServiceKey());
			source.setAddress(businessService.getBindingTemplates()
					.getBindingTemplate().get(0).getAccessPoint().getValue());
			source.setDescList((ArrayList<Description>) businessService
					.getDescription());
			source.setReferenceList((ArrayList<KeyedReference>) businessService
					.getCategoryBag().getKeyedReference());
			source.setName(businessService.getName().get(0).getValue());
		}
		return source;

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String trimKey(String key) {
		String[] keyStringList = key.split(":");

		return keyStringList[2];
	}

	private ArrayList<UddiDataSource> getDataSources(FindService findService) {
		ArrayList<ServiceInfo> serviceLists = new ArrayList<ServiceInfo>();

		ArrayList<String> keyList = new ArrayList<String>();

		ServiceList sd = null;

		ArrayList<UddiDataSource> datasources = new ArrayList<UddiDataSource>();

		try {
			if (this.inquiry != null) {
				sd = this.inquiry.findService(findService);
			}

			if (sd != null && sd.getServiceInfos() != null) {
				serviceLists = (ArrayList<ServiceInfo>) sd.getServiceInfos()
						.getServiceInfo();

				for (ServiceInfo serviceInfo : serviceLists) {
					keyList.add(serviceInfo.getServiceKey());
				}
			}

		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (sd != null && sd.getServiceInfos() != null) {
			GetServiceDetail gsd = new GetServiceDetail();
			gsd.getServiceKey().addAll(keyList);

			ArrayList<BusinessService> businessServices = new ArrayList<BusinessService>();

			try {
				ServiceDetail serviceDetail = this.inquiry
						.getServiceDetail(gsd);

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
						.getBusinessKey(), businessService.getServiceKey());
				dataSource.setAddress(businessService.getBindingTemplates()
						.getBindingTemplate().get(0).getAccessPoint()
						.getValue());
				dataSource.setDescList((ArrayList<Description>) businessService
						.getDescription());
				dataSource.setName(businessService.getName().get(0).getValue());
				dataSource
						.setReferenceList((ArrayList<KeyedReference>) businessService
								.getCategoryBag().getKeyedReference());
				datasources.add(dataSource);
			}
		}

		return datasources;
	}

	public static UddiDataSourceReader getInstance(String addr) {
		if (datasourceReader == null) {
			datasourceReader = new UddiDataSourceReader(addr);
		} else {
			if (!datasourceReader.getAddress().equals(addr)) {
				datasourceReader.setAddress(addr);
				datasourceReader.refresh();
			}
		}

		return datasourceReader;
	}
}
