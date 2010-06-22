package org.simpl.uddi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.simpl.uddi.utils.DatasourceService;
import org.simpl.uddi.utils.DatasourceService_Service;
import org.simpl.uddi.utils.Parameter;

public class UddiDataTypeReader {

	ArrayList<String> types = null;
	ArrayList<String> subtypes = null;
	ArrayList<String> languages = null;
	ArrayList<String> dataformats = null;

	DatasourceService datasourceService = null;
	
	static UddiDataTypeReader dataTypeReader = null;

	private UddiDataTypeReader() {
		DatasourceService_Service.refresh();
		datasourceService = new DatasourceService_Service().getDatasourceServicePort();
	}
	
	public static UddiDataTypeReader getInstance() {
		if (UddiDataTypeReader.dataTypeReader == null){
			UddiDataTypeReader.dataTypeReader = new UddiDataTypeReader();
		}
		return UddiDataTypeReader.dataTypeReader;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getTypes() {
		if (this.types == null) {
			this.types = (ArrayList<String>) Parameter.deserialize(datasourceService.getDataSourceTypes());
//			HashMap<String, String> dstypes = (HashMap<String, String>) Parameter
//					.deserialize(datasourceService.getDataSourceTypes());
//			Set<String> keys = dstypes.keySet();
//
//			String[] types = keys.toArray(new String[0]);
//			for (String item : types) {
//				this.types.add(item);
//			}
			
		}

		return this.types;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getSubtypes() {
		if (subtypes == null) {
			this.subtypes = new ArrayList<String>();
			for (String type : getTypes()) {
				for (String item: (ArrayList<String>) Parameter.deserialize(datasourceService.getDataSourceSubTypes(type))) {
					if (!this.subtypes.contains(item)) {
						this.subtypes.add(item);
					}
				}

//				HashMap<String, String> dssubtypes = (HashMap<String, String>) Parameter
//						.deserialize(datasourceService
//								.getDataSourceSubTypes(type));
//				Set<String> keys = dssubtypes.keySet();
//
//				String[] types = keys.toArray(new String[0]);
//				for (String item : types) {
//					this.subtypes.add(item);
//				}
			}
		}

		return this.subtypes;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getLanguages() {
		if (this.languages == null) {
			this.languages = new ArrayList<String>();

			for (String subtype : getSubtypes()) {
				for(String item: (ArrayList<String>) Parameter.deserialize(datasourceService.getDataSourceLanguages(subtype)))
				
				if (!this.languages.contains(item)) {
					this.languages.add(item);
				}
				
				

//				HashMap<String, String> dssubtypes = (HashMap<String, String>) Parameter
//						.deserialize(datasourceService
//								.getDataSourceLanguages(subtype));
//				Set<String> keys = dssubtypes.keySet();
//
//				String[] types = keys.toArray(new String[0]);
//				for (String item : types) {
//					this.languages.add(item);
//				}
			}
		}
		
		

		return this.languages;
	}
	
	public String getTypesDropDown(String selected) {
		String select = "<select name=\"type\"> ";
		select += "<option value=\"\"></option>";
		
		for (String item: this.getTypes()) {
			
			if (!item.equals(selected)) {
				select += "<option value=\""+item+"\">"+ item + "</option>";
			} else {
				select += "<option selected value=\""+item+"\">"+ item + "</option>";
			}
		}
		select += "</select>";
		return select;
	}
	
	public String getSubTypesDropDown(String selected) {
		String select = "<select name=\"subtype\"> ";
		select += "<option value=\"\"></option>";
		for (String item: getSubtypes()) {
			if (!item.equals(selected)) {
				select += "<option value=\""+item+"\">"+ item + "</option>";
			} else {
				select += "<option selected value=\""+item+"\">"+ item + "</option>";
			}
		}
		select += "</select>";
		return select;
	}
	
	public String getLanguagesDropDown(String selected) {
		String select = "<select name=\"language\">";
		select += "<option value=\"\"></option>";
		
		for (String item: getLanguages()) {
			if (!item.equals(selected)) {
				select += "<option value=\""+item+"\">"+ item + "</option>";
			} else {
				select += "<option selected value=\""+item+"\">"+ item + "</option>";
			}
		}
		select += "</select>";
		return select;
	}
	
	public static void refresh() {
		UddiDataTypeReader.dataTypeReader = null;
	}
	
	
	
	
}
