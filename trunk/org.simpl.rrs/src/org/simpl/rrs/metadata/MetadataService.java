package org.simpl.rrs.metadata;

import org.simpl.rrs.model.EPR;
import org.w3c.dom.Node;

public interface MetadataService {
	
	EPR[] getAllEPR();
	
	Node getEPR(String name);
	
}
