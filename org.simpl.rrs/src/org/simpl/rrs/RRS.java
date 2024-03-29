package org.simpl.rrs;

import java.util.ArrayList;
import java.util.List;

import org.simpl.rrs.dsadapter.DSAdapter;
import org.simpl.rrs.dsadapter.DSAdapterProvider;
import org.simpl.rrs.management.ManagementService;
import org.simpl.rrs.management.ManagementServiceImpl;
import org.simpl.rrs.metadata.MetadataService;
import org.simpl.rrs.metadata.MetadataServiceImpl;
import org.simpl.rrs.retrieval.RetrievalService;
import org.simpl.rrs.retrieval.RetrievalServiceImpl;

public class RRS {

	private static RRS instance = null;
	private static RRSConfig config = null;
	private static RetrievalService retrievalService = new RetrievalServiceImpl();
	private static ManagementService managementService = new ManagementServiceImpl();
	private static MetadataService metadataService = new MetadataServiceImpl();
	private static DSAdapterProvider dsAdapterProvider = new DSAdapterProvider();
	
	
	public static RRS getInstance() {
		RRS.instance = new RRS();
		return RRS.instance;
	}
	
	public RRSConfig config() {
	    config = new RRSConfig();
		return config;
	}
	
	public RetrievalService retrievalService() {
		return retrievalService;
	}
	
	public ManagementService managementService() {
		return managementService;
	}
	
	public MetadataService metadataService() {
		return metadataService;
	}
	
	public DSAdapter dsAdapter(String dsType, String dsSubtype) {
		return dsAdapterProvider.getInstance(dsType, dsSubtype);
	}

	public List<String> getDSAdapterType() {
		return dsAdapterProvider.getTypes();
	}
	
	public List<String> getDSAdapterSubtypes(String dsType) {
		return dsAdapterProvider.getSubtypes(dsType);
	}
	
	public List<String> getDSAdapterLanguages(String dsSubtype) {
		return dsAdapterProvider.getLanguages(dsSubtype);
	}
	
	public List<String> getAvailableAdapters(){
		List<String> adapters = new ArrayList<String>();
		
		for (String type : dsAdapterProvider.getTypes()){
			for (String subtype : dsAdapterProvider.getSubtypes(type)){
				for (String language : dsAdapterProvider.getLanguages(subtype)){
					adapters.add(type+":"+subtype+":"+language);
				}
			}
		}
		
		return adapters;
	}
}
