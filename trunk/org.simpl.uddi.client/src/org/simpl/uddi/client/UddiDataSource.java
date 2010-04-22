package org.simpl.uddi.client;
import java.util.ArrayList;

import org.uddi.api_v3.Description;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;

public class UddiDataSource implements IUddiConfig {

	private ArrayList<Description> descList = null;

	private String address = null;

	private String serviceKey = null;

	private ArrayList<KeyedReference> referenceList = null;

	private Name name = null;

	private String businessKey = null;

	public UddiDataSource(String businessKey) {
		this.businessKey = businessKey;

		descList = new ArrayList<Description>();
		referenceList = new ArrayList<KeyedReference>();
		this.name = new Name();
		this.businessKey = businessKey;

		addAttribute("category", "datasource", KEYPREFIX + "category");

	}

	public String getName() {
		return name.getValue();
	}

	public void setName(String name) {
		this.name.setValue(name);
		this.name.setLang("");
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public ArrayList<Description> getDescList() {
		return descList;
	}

	public void setDescList(ArrayList<Description> descList) {
		this.descList = descList;
	}

	/**
	 * Adds an description Element to the Datasource
	 * 
	 * @param description
	 *            Description of the Source
	 * @param language
	 *            Language of the Description
	 */
	public void addDescription(String description, String language) {
		Description desc = new Description();
		desc.setLang(language);
		desc.setValue(description);
		this.descList.add(desc);
	}

	/**
	 * Returns the Adress of the Datasource
	 * 
	 * @return The datasource adress
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the Datasource Adress
	 * 
	 * @param address
	 *            Datasource Adress
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the Datasource Key
	 * 
	 * @return Datasource Key
	 */
	public String getKey() {
		return serviceKey;
	}

	/**
	 * Sets the Datasource Key
	 * 
	 * @param key
	 *            Key in form of a UDDIPRÄFIX + unique key Example:
	 *            uddi:org.apache.juddi:key
	 */
	public void setKey(String key) {
		this.serviceKey = key;
	}

	public ArrayList<KeyedReference> getReferenceList() {
		return referenceList;
	}

	public void setReferenceList(ArrayList<KeyedReference> referenceList) {
		this.referenceList = referenceList;
	}

	/**
	 * Adds an Attribute in form of a keyed reference to the Datasource.
	 * Attributes can be everything.
	 * 
	 * @param name
	 *            Name of the Attribute
	 * @param value
	 *            Attribute Value
	 * @param type
	 *            TModelKey of the Attribute
	 */
	public void addAttribute(String name, String value, String type) {
		KeyedReference reference = new KeyedReference();
		reference.setKeyName(name);
		reference.setKeyValue(value);
		reference.setTModelKey(type);
		referenceList.add(reference);
	}

	/**
	 * Get the Value of the entered attribute name
	 * 
	 * @param attributeName
	 *            attribute name
	 * @return attribute value
	 */
	public String getAttributeValue(String attributeName) {
		String value = null;
		for (KeyedReference reference : referenceList) {
			if (reference.getKeyName().equals(attributeName)) {
				value = reference.getKeyValue();
			}
		}

		return value;
	}
	
	public String getWsPolicy() {
		return getAttributeValue("wspolicy");
	}
	
	public String getType() {
		return getAttributeValue("type");
	}
	
	public String getSubtype() {
		return getAttributeValue("subtype");
	}
}
