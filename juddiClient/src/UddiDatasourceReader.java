import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.juddi.ClassUtil;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.FindBinding;
import org.uddi.api_v3.GetBindingDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;

public class UddiDatasourceReader implements IUddiConfig {

	UDDIInquiryPortType inquiry = null;

	public UddiDatasourceReader() {
		try {
			String clazz = UDDIClientContainer.getUDDIClerkManager(null)
					.getClientConfig().getUDDINode("default")
					.getProxyTransport();
			Class<?> transportClass = ClassUtil.forName(clazz, Transport.class);
			if (transportClass != null) {
				Transport transport = (Transport) transportClass
						.getConstructor(String.class).newInstance("default");

				this.inquiry = transport.getUDDIInquiryService();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets all datasources
	 * @return
	 * ArrayList of all datasources inside the jUDDI Registry
	 */
	public ArrayList<UddiDataSource> getAllDarasources() {

		FindBinding findBinding = new FindBinding();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "category");

		reference.setKeyValue("datasource");

		reference.setKeyName("category");

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
	 * Gets all Datasources by its TypeValue
	 * @param type
	 * Example: filesystem
	 * @return
	 * ArrayList of datasources
	 */
	public ArrayList<UddiDataSource> getByType(String type) {
		FindBinding findBinding = new FindBinding();

		CategoryBag bag = new CategoryBag();

		KeyedReference reference = new KeyedReference();

		reference.setTModelKey(KEYPREFIX + "type");

		reference.setKeyValue(type);

		reference.setKeyName("type");

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
	 * @param type
	 * The subtype value
	 * @return
	 * ArrayList of Datasources
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
	 * @param key
	 * The DatasourceKey as KEYPREFIX + key
	 * Example:
	 * uddi:org.apache.juddi:key
	 * @return
	 * The datasource
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
		
		UddiDataSource source = new UddiDataSource(template
				.getServiceKey());
		source.setAddress(template.getAccessPoint().getValue());
		source.setDescList((ArrayList<Description>) template
				.getDescription());
		source.setKey(template.getBindingKey());
		source.setReferenceList((ArrayList<KeyedReference>) template
				.getCategoryBag().getKeyedReference());
		
		return source;

	}
}
