package org.simpl.rrs.dsadapter;

import java.sql.ResultSet;



public interface DSAdapter {

	public <T> T openConnection(String dsAddress);

	public <T> boolean closeConnection(T connection);
	  
	public ResultSet retrieveData (String dsAddress, String Statement);
	  
}
