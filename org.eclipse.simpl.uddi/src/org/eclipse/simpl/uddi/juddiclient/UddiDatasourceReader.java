package org.eclipse.simpl.uddi.juddiclient;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.juddi.v3.client.transport.JAXWSTransport;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.eclipse.simpl.uddi.UDDIPlugIn;
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

public class UddiDatasourceReader implements IUddiConfig {

	UDDIInquiryPortType inquiry = null;

	private final String UDDI_INQ_WSDL = UDDIPlugIn.getDefault()
			.getPreferenceStore().getString("UDDI_INQ_ADDRESS");

	public static UddiDatasourceReader datasourceReader = null;

	private UddiDatasourceReader() {
		Transport transport = new JAXWSTransport("default");

		try {
			this.inquiry = transport.getUDDIInquiryService(UDDI_INQ_WSDL);
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

		ServiceList sd = null;

		ArrayList<UddiDataSource> datasources = new ArrayList<UddiDataSource>();

		ArrayList<ServiceInfo> serviceLists = new ArrayList<ServiceInfo>();

		ArrayList<String> keyList = new ArrayList<String>();

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
				if (this.inquiry != null) {
					ServiceDetail serviceDetail = this.inquiry
							.getServiceDetail(gsd);

					businessServices = (ArrayList<BusinessService>) serviceDetail
							.getBusinessService();
				}

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
						.getBindingTemplate().get(0).getAccessPoint()
						.getValue());
				dataSource.setDescList((ArrayList<Description>) businessService
						.getDescription());
				dataSource.setKey(businessService.getServiceKey());
				dataSource.setName(businessService.getName().get(0).getValue());
				dataSource
						.setReferenceList((ArrayList<KeyedReference>) businessService
								.getCategoryBag().getKeyedReference());
				datasources.add(dataSource);
			}

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
		FindService findService = new FindService();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "category");

		reference.setKeyValue(type);

		reference.setKeyName("type");

		bag.getKeyedReference().add(reference);

		findService.setCategoryBag(bag);

		ServiceList sd = null;

		ArrayList<UddiDataSource> datasources = new ArrayList<UddiDataSource>();

		ArrayList<ServiceInfo> serviceLists = new ArrayList<ServiceInfo>();

		ArrayList<String> keyList = new ArrayList<String>();

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
				if (this.inquiry != null) {
					ServiceDetail serviceDetail = this.inquiry
							.getServiceDetail(gsd);

					businessServices = (ArrayList<BusinessService>) serviceDetail
							.getBusinessService();
				}

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
						.getBindingTemplate().get(0).getAccessPoint()
						.getValue());
				dataSource.setDescList((ArrayList<Description>) businessService
						.getDescription());
				dataSource.setKey(businessService.getServiceKey());
				dataSource.setName(businessService.getName().get(0).getValue());
				dataSource
						.setReferenceList((ArrayList<KeyedReference>) businessService
								.getCategoryBag().getKeyedReference());
				datasources.add(dataSource);
			}

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
		FindService findService = new FindService();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "category");

		reference.setKeyValue(type);

		reference.setKeyName("subtype");

		bag.getKeyedReference().add(reference);

		findService.setCategoryBag(bag);

		ServiceList sd = null;

		ArrayList<UddiDataSource> datasources = new ArrayList<UddiDataSource>();

		ArrayList<ServiceInfo> serviceLists = new ArrayList<ServiceInfo>();

		ArrayList<String> keyList = new ArrayList<String>();

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
				if (this.inquiry != null) {
					ServiceDetail serviceDetail = this.inquiry
							.getServiceDetail(gsd);

					businessServices = (ArrayList<BusinessService>) serviceDetail
							.getBusinessService();
				}

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
						.getBindingTemplate().get(0).getAccessPoint()
						.getValue());
				dataSource.setDescList((ArrayList<Description>) businessService
						.getDescription());
				dataSource.setKey(businessService.getServiceKey());
				dataSource.setName(businessService.getName().get(0).getValue());
				dataSource
						.setReferenceList((ArrayList<KeyedReference>) businessService
								.getCategoryBag().getKeyedReference());
				datasources.add(dataSource);
			}

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
		GetServiceDetail getServiceDetail = new GetServiceDetail();

		UddiDataSource source = null;

		ServiceDetail serviceDetail = new ServiceDetail();

		getServiceDetail.getServiceKey().add(key);

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

			source = new UddiDataSource(businessService.getBusinessKey());
			source.setAddress(businessService.getBindingTemplates()
					.getBindingTemplate().get(0).getAccessPoint().getValue());
			source.setDescList((ArrayList<Description>) businessService
					.getDescription());
			source.setKey(businessService.getServiceKey());
			source.setReferenceList((ArrayList<KeyedReference>) businessService
					.getCategoryBag().getKeyedReference());
			source.setName(businessService.getName().get(0).getValue());
		}
		return source;

	}

	public static UddiDatasourceReader getInstance() {
		if (datasourceReader == null) {
			datasourceReader = new UddiDatasourceReader();
		}
		return datasourceReader;
	}
}
