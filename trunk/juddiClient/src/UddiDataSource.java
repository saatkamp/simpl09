import java.util.ArrayList;

import org.uddi.api_v3.Description;
import org.uddi.api_v3.KeyedReference;

public class UddiDataSource implements IUddiConfig {

	private ArrayList<Description> descList = null;

	private String address = null;

	private String key = null;

	private String serviceKey = null;

	private ArrayList<KeyedReference> referenceList = null;

	public UddiDataSource(String serviceKey) {
		this.serviceKey = serviceKey;

		descList = new ArrayList<Description>();
		referenceList = new ArrayList<KeyedReference>();

		addAttribute("category", "datasource", KEYPREFIX + "category");
	}

	public ArrayList<Description> getDescList() {
		return descList;
	}

	public void setDescList(ArrayList<Description> descList) {
		this.descList = descList;
	}

	public void addDescription(String description, String language) {
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
		this.key = key;
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
