package org.simpl.rrs.dsadapter.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.simpl.rrs.dsadapter.DSAdapter;

public abstract class DSAdapterPlugin implements DSAdapter {
	
	private String dataSourceType = "Database";
	
	private List<String> dataSourceSubtypes = new ArrayList<String>();
	
	private HashMap<String, List<String>> dataSourceLanguages = new HashMap<String, List<String>>();
	
	public String getType() {
		return this.dataSourceType;
	}
	
	public List<String> getSubtypes() {
		return this.dataSourceSubtypes;
	}
	
	public HashMap<String, List<String>> getLanguages() {
		return this.dataSourceLanguages;
	}
	
	public void setType(String dsType) {
		this.dataSourceType = dsType;
	}
	
	  public void addSubtype(String dsSubtype) {
		    if (!this.dataSourceSubtypes.contains(dsSubtype)) {
		      this.dataSourceSubtypes.add(dsSubtype);
		    }
		  }
	  
	  public void addLanguage(String dsSubtype, String dsLanguage) {
		    List<String> languages = null;

		    if (dataSourceLanguages.containsKey(dsSubtype)) {
		      languages = dataSourceLanguages.get(dsSubtype);
		    } else {
		      languages = new ArrayList<String>();
		    }

		    if (!languages.contains(dsLanguage)) {
		      languages.add(dsLanguage);
		      this.dataSourceLanguages.put(dsSubtype, languages);
		    }
		  }

}
