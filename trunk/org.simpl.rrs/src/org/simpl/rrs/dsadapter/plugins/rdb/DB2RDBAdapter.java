package org.simpl.rrs.dsadapter.plugins.rdb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;

public class DB2RDBAdapter extends DSAdapterPlugin{
	
	public DB2RDBAdapter() {
		this.setType("RDB");
		this.addSubtype("DB2");
		this.addLanguage("DB2", "SQL");
	}
	
	@SuppressWarnings("unchecked")
	public Connection openConnection(String dsAddress){
		
		Connection connect = null;
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			StringBuilder uri = new StringBuilder();
			uri.append("jdbc:db2://");
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
	public <Connection> boolean closeConnection(Connection connection){
		
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
	
	public Object retrieveData (String dsAddress, String statement){
		
//		Connection con = openConnection(dsAddress);
//		Statement state;
//		ResultSet rs = null;
//		try {
//			state = con.createStatement();
//
//			rs = state.executeQuery(statement);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return rs;
		
		DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
	    Command read = das.createCommand(statement);
	    DataObject root = read.executeQuery();
	    
ByteArrayOutputStream byteOuputStream =new ByteArrayOutputStream();
	    
	    try {
			XMLHelper.INSTANCE.save(root, "commonj.sdo", "dataObject", byteOuputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return new String(byteOuputStream.toByteArray());
	    
	}

}
