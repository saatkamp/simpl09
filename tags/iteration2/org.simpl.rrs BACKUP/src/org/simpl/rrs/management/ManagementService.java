package org.simpl.rrs.management;

import org.simpl.rrs.webservices.client.EPR;



public interface ManagementService {

	public boolean Delete(EPR epr);
	
	public boolean Update(EPR epr);
	
	public boolean Insert(EPR epr);
	
	
	
}
