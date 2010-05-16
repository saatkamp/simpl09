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

public class EmbDerbyRDBAdapter extends DSAdapterPlugin {

	public EmbDerbyRDBAdapter() {
		this.setType("RDB");
		this.addSubtype("EmbeddedDerby");
		this.addLanguage("EmbeddedDerby", "SQL");

	}

	@SuppressWarnings("unchecked")
	public Connection openConnection(String dsAddress) {

		Connection connect = null;

		StringBuilder uri = new StringBuilder();
		uri.append("jdbc:derby://");
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
