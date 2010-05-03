package org.simpl.rrs.metadata;

import java.io.File;

import org.simpl.rrs.EPR;

public interface MetadataService {
	
	EPR[] getAllEPR();
	
	File getEPR(String name);
	
}
