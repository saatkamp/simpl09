package org.simpl.rrs.dsadapter.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;

import commonj.sdo.DataObject;

public class DB2RDBAdapter extends DSAdapterPlugin{
	
	public DB2RDBAdapter() {
		this.setType("Database");
		this.addSubtype("DB2");
		this.addLanguage("DB2", "SQL");
	}
	
	@SuppressWarnings("unchecked")
	public Connection openConnection(String dsAddress) throws ConnectionException {
		
		Connection connect = null;
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			StringBuilder uri = new StringBuilder();
			uri.append("jdbc:db2:");
			uri.append((dsAddress));
			
			try {
				connect = (java.sql.Connection) DriverManager.getConnection(uri.toString(),
				        "test", "test");
				connect.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return connect;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connect;
		
	}
	
	@SuppressWarnings("hiding")
	public <Connection> boolean closeConnection(Connection connection) throws ConnectionException{
		
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
	
	public Object retrieveData (String dsAddress, String statement) throws ConnectionException {
		
		DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
	    Command read = das.createCommand(statement);
	    DataObject root = read.executeQuery();
	    
	    return root;
	}

}