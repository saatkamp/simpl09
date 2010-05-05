package org.simpl.rrs.metadata;

import de.uni_stuttgart.simpl.rrs.EPR;

public interface MetadataService {
	
	EPR[] getAllEPR();
	
	EPR getEPR(String name);
	
	String[] getAvailableAdapters();
}
