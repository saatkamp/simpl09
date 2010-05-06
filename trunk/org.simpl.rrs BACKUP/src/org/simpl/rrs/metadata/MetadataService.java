package org.simpl.rrs.metadata;

import org.simpl.rrs.webservices.client.EPR;

public interface MetadataService {
	
	EPR[] getAllEPR();
	
	EPR getEPR(String name);
	
	String[] getAvailableAdapters();
}
