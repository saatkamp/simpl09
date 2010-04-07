import java.rmi.RemoteException;
import java.util.ArrayList;

import org.uddi.api_v3.*;
import org.apache.juddi.ClassUtil;
import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDISecurityPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.apache.juddi.v3_service.JUDDIApiPortType;

public class UddiDataSource implements IUddiConfig{
	private String name = null;
	
	private ArrayList<Description> descList= null;
	
	private String address = null;
	
	private String key = null;
	
	private static UDDISecurityPortType security = null;
	
	private static JUDDIApiPortType juddiApi = null;
	
	private static UDDIPublicationPortType publish = null;
	
	private AuthToken userAuthToken = null;
	
	private String serviceKey = null;
	
	private ArrayList<KeyedReference> referenceList= null;

	
	
	
	public UddiDataSource(String serviceKey) {
        		this.serviceKey = serviceKey;
				
				descList = new ArrayList<Description>();
				referenceList = new ArrayList<KeyedReference>();
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Description> getDescList() {
		return descList;
	}

	public void setDescList(ArrayList<Description> descList) {
		this.descList = descList;
	}
	
	public void addDescription (String description, String language) {
		Description desc = new Description();
		desc.setLang(language);
		desc.setValue(description);
		this.descList.add(desc);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = KEYPREFIX + "" + key;
	}
	
	
	public String getServiceKey() {
		return serviceKey;
	}



	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	


	public ArrayList<KeyedReference> getReferenceList() {
		return referenceList;
	}



	public void setReferenceList(ArrayList<KeyedReference> referenceList) {
		this.referenceList = referenceList;
	}



	public void addAttribute(String name, String value, String type) {
		KeyedReference reference = new KeyedReference();
		reference.setKeyName(name);
		reference.setKeyValue(value);
		reference.setTModelKey(type);
		referenceList.add(reference);
	}
}
