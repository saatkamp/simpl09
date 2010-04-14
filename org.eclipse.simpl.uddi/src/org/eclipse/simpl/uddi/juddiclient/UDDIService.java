package org.eclipse.simpl.uddi.juddiclient;
import java.util.ArrayList;

import org.uddi.api_v3.Description;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;

public class UDDIService implements IUddiConfig {

	private ArrayList<Name> nameList = null;

	private ArrayList<Description> descList = null;

	private String key = null;

	private String businessKey = null;

	private ArrayList<KeyedReference> referenceList = null;

	public UDDIService(String businessKey) {
		this.businessKey = businessKey;

		this.descList = new ArrayList<Description>();
		this.nameList = new ArrayList<Name>();
		this.referenceList = new ArrayList<KeyedReference>();

	}

	public ArrayList<Name> getNameList() {
		return this.nameList;
	}

	public void addName(String value, String language) {
		Name name = new Name();
		name.setLang(language);
		name.setValue(value);
		nameList.add(name);

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
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
