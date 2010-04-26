package org.simpl.rrs.management;

import java.io.File;
import java.sql.SQLException;

public interface ManagementService {

	public boolean Delete(String uri, String adapterType, String referenceName, String statement);
	
	public boolean Update(String uri, String adapterType, String referenceName, String statement) throws SQLException;
	
	public File Insert(String uri, String adapterType, String referenceName, String statement) throws SQLException;
	
	
	
}
