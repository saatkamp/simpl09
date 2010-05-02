package org.simpl.rrs.dsadapter.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;

import commonj.sdo.DataObject;

public class EmbDerbyRDBAdapter extends DSAdapterPlugin{

	public EmbDerbyRDBAdapter() {
	    this.setType("RDB");
	    this.addSubtype("EmbeddedDerby");
	    this.addLanguage("EmbeddedDerby", "SQL");

	  }
	
	@SuppressWarnings("unchecked")
	public Connection openConnection(String dsAddress) throws ConnectionException {

	    Connection connect = null;
   
	    StringBuilder uri = new StringBuilder();
	      uri.append("jdbc:derby:");
	      uri.append(dsAddress);
	      uri.append(";create=true");

	      try {
	        connect = DriverManager.getConnection("jdbc:jdc:jdcpool");
	        connect.setAutoCommit(false);
	      } catch (SQLException e) {
	      }

	      return connect;
	  }
	
	@SuppressWarnings("hiding")
	public <Connection> boolean closeConnection(Connection connection) {
		    // TODO Auto-generated method stub
		    boolean success = false;
		    
		      try {
				((java.sql.Connection) connection).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      success = true;

		    return success;
		  }
	
	public Object retrieveData(String dsAddress, String statement)
    throws ConnectionException {
		
		DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
	    Command read = das.createCommand(statement);
	    DataObject root = read.executeQuery();
	    
	    return root;
	}
	
}

