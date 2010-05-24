package org.simpl.rrs.dsadapter;

import org.simpl.rrs.webservices.RDBSet;




public interface DSAdapter {

	public <T> T openConnection(String dsAddress);

	public <T> boolean closeConnection(T connection);
	  
	public RDBSet retrieveData (String dsAddress, String Statement);
	  
}
