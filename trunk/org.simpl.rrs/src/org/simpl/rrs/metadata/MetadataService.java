package org.simpl.rrs.metadata;

import org.simpl.rrs.model.EPR;
import org.w3c.dom.Node;

public interface MetadataService {
	
	EPR[] getAllEPR();
	
	EPR getEPR(String name);
	
}
