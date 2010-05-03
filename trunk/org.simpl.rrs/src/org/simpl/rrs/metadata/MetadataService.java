package org.simpl.rrs.metadata;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface MetadataService {
	
	List<HashMap<String, String>> getAllEPR();
	
	File getEPR(String name);
	
}
