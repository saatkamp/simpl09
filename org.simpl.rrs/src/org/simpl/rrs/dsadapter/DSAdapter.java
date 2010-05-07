package org.simpl.rrs.dsadapter;

import java.sql.ResultSet;

import commonj.sdo.DataObject;


public interface DSAdapter {

	public <T> T openConnection(String dsAddress);

	public <T> boolean closeConnection(T connection);
	  
	public DataObject retrieveData (String dsAddress, String Statement);
	  
}
