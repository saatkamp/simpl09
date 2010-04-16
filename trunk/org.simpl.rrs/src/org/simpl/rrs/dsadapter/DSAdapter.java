package org.simpl.rrs.dsadapter;

import org.simpl.rrs.dsadapter.exceptions.ConnectionException;

public interface DSAdapter {

	public <T> T openConnection(String dsAddress) throws ConnectionException;

	public <T> boolean closeConnection(T connection) throws ConnectionException;
	  
	public Object retrieveData (String dsAddress, String Statement) throws ConnectionException;
	  
}
