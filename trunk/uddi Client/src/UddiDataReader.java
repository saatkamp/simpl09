import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.juddi.ClassUtil;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.FindBinding;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.FindTModel;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.GetBindingDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.TModel;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;


public class UddiDataReader implements IUddiConfig{
	
	public UddiDataReader() {
		try {
        	String clazz = UDDIClientContainer.getUDDIClerkManager(null).
            	getClientConfig().getUDDINode("default").getProxyTransport();
        	Class<?> transportClass = ClassUtil.forName(clazz, Transport.class);
			if (transportClass!=null) {
				Transport transport = (Transport) transportClass.
					getConstructor(String.class).newInstance("default");

				UDDIInquiryPortType inquiry = transport.getUDDIInquiryService();
				
//				GetBindingDetail gbd = new GetBindingDetail();
//				
//				gbd.getBindingKey().add(KEYPREFIX +"source_1");
//				
//				BindingDetail detail = inquiry.getBindingDetail(gbd);
//				
//				System.out.println(detail.getBindingTemplate().get(0).getCategoryBag().getKeyedReference().get(0).getKeyValue());
				
				////////////////////////////////////////////////////////////////////
				
				FindBinding findBinding = new FindBinding();
				
				CategoryBag bag = new CategoryBag();
				
				KeyedReference reference = new KeyedReference();
				
				reference.setTModelKey(KEYPREFIX + "category");
				
				reference.setKeyValue("datasource");
				
				reference.setKeyName("category");
				
				bag.getKeyedReference().add(reference);
				
				findBinding.setCategoryBag(bag);
				
				BindingDetail bd = inquiry.findBinding(findBinding);
				
				List<BindingTemplate> templateList = bd.getBindingTemplate();
				
				//////////////////////////////////////////////////////////////////
				
				for (BindingTemplate item: templateList) {
					System.out.println(item.getBindingKey());
				}
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
