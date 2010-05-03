package org.simpl.rrs.management;

import java.io.File;
import java.sql.SQLException;

import org.simpl.rrs.model.EPR;

public interface ManagementService {

	public boolean Delete(EPR epr);
	
	public boolean Update(EPR epr) throws SQLException;
	
	public File Insert(EPR epr) throws SQLException;
	
	
	
}
