package org.simpl.uddi.client;
import java.util.ArrayList;

import org.uddi.api_v3.Description;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;

public class UddiBusiness implements IUddiConfig {

	private ArrayList<Description> descList = null;

	private String key = null;

	private ArrayList<Name> nameList = null;

	private ArrayList<KeyedReference> referenceList = null;

	public UddiBusiness() {

		nameList = new ArrayList<Name>();
		descList = new ArrayList<Description>();
		referenceList = new ArrayList<KeyedReference>();
	}

	public ArrayList<Description> getDescList() {
		return descList;
	}

	public void setDescList(ArrayList<Description> descList) {
		this.descList = descList;
	}

	public void addDescription(String value, String language) {
		Description description = new Description();
		description.setLang(language);
		description.setValue(value);
		this.descList.add(description);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void addName(String value, String language) {
		Name name = new Name();
		name.setLang(language);
		name.setValue(value);
		nameList.add(name);
	}

	public ArrayList<Name> getNameList() {
		return nameList;
	}

	public void setNameList(ArrayList<Name> nameList) {
		this.nameList = nameList;
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
