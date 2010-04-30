package org.simpl.rrs.management;

import java.io.File;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public interface ManagementService {

	public boolean Delete(LinkedHashMap<String, String> EPR);
	
	public boolean Update(LinkedHashMap<String, String> EPR) throws SQLException;
	
	public File Insert(LinkedHashMap<String, String> EPR) throws SQLException;
	
	
	
}
