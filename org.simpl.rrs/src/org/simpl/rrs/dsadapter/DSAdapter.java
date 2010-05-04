package org.simpl.rrs.dsadapter;


public interface DSAdapter {

	public <T> T openConnection(String dsAddress);

	public <T> boolean closeConnection(T connection);
	  
	public Object retrieveData (String dsAddress, String Statement);
	  
}
