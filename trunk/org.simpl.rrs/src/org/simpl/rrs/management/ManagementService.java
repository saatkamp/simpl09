package org.simpl.rrs.management;

import de.uni_stuttgart.simpl.rrs.EPR;


public interface ManagementService {

	public boolean Delete(EPR epr);
	
	public boolean Update(EPR epr);
	
	public boolean Insert(EPR epr);
	
	
	
}
