package org.eclipse.simpl.uddi.juddiclient;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.juddi.ClassUtil;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;


public class UddiServiceReader implements IUddiConfig{
	UDDIInquiryPortType inquiry = null;
	
	public UddiServiceReader () {
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
	
//	public UDDIService getByKey(String serviceKey) {
//		GetServiceDetail getsd = new GetServiceDetail();
//		
//		ServiceDetail sd = new ServiceDetail();
//		
//		getsd.getServiceKey().add(serviceKey);
//		
//		try {
//			sd = inquiry.getServiceDetail(getsd);
//		} catch (DispositionReportFaultMessage e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		BusinessService service = sd.getBusinessService().get(0);
//		
//		UDDIService uddiService = new UDDIService(service.getBusinessKey());
//		
//		uddiService.setDescList((ArrayList<Description>)service.getDescription());
//		
////		uddiService.setReferenceList(service.getCategoryBag().getKeyedReference())
//	}

}
