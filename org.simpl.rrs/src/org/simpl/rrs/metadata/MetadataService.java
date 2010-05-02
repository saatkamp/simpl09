package org.simpl.rrs.metadata;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.derby.iapi.types.XML;

public interface MetadataService {
	
	List<HashMap<String, String>> getAllEPR();
	
	File getEPR(String name);
	
}
