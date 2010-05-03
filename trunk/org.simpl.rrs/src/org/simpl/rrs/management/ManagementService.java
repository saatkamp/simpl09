package org.simpl.rrs.management;

import java.io.File;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.simpl.rrs.EPR;

public interface ManagementService {

	public boolean Delete(EPR epr);
	
	public boolean Update(EPR epr) throws SQLException;
	
	public File Insert(EPR epr) throws SQLException;
	
	
	
}
