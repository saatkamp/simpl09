package org.simpl.uddi.client;

import java.rmi.RemoteException;

import org.apache.juddi.v3.client.transport.JAXWSTransport;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.GetBusinessDetail;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;

public class UddiBusinessReader implements IUddiConfig{
UDDIInquiryPortType inquiry = null;
	
	String address = "";
	
	Transport transport = null;
	
	public static UddiBusinessReader businessReader = null;

	public static UddiDataSourceReader datasourceReader = null;

	private UddiBusinessReader(String address) {
		this.address = address;
		this.transport = new JAXWSTransport("default");

		try {
			inquiry = this.transport
					.getUDDIInquiryService(this.address + "/services/inquiry?wsdl");
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean simplBusinessExsists () {
		boolean exsists = false;
		GetBusinessDetail getBusinessDetail = new GetBusinessDetail();
		getBusinessDetail.getBusinessKey().add(KEYPREFIX + "simpl");
		
		BusinessDetail businessDetail = new BusinessDetail();
		
		try {
			if (inquiry.getBusinessDetail(getBusinessDetail) != null) {
				businessDetail = inquiry.getBusinessDetail(getBusinessDetail);
			}
			
		} catch (DispositionReportFaultMessage e) {
						
		} catch (RemoteException e) {
			
		} catch (javax.xml.ws.soap.SOAPFaultException e) {
			businessDetail = null;
		}
		
		if (businessDetail != null) {
			exsists = true;
		}
		
		return exsists;
	}
	
	public static UddiBusinessReader getInstance(String address) {
		if (businessReader == null) {
			businessReader = new UddiBusinessReader(address);
		}
		return businessReader;
	}
}
