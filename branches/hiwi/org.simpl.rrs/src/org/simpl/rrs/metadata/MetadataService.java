package org.simpl.rrs.metadata;

import org.simpl.rrs.webservices.EPR;
import org.simpl.rrs.webservices.EPRArray;

public interface MetadataService {
	
	EPRArray getAllEPR();
	
	EPR getEPR(String name);
	
	String[] getAvailableAdapters();
}
