package org.simpl.rrs.dsadapter.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;

import commonj.sdo.DataObject;

public class MySQLRDBAdapter extends DSAdapterPlugin {

	public MySQLRDBAdapter() {
		this.setType("Database");
		this.addSubtype("MySQL");
		this.addLanguage("MySQL", "SQL");
	}

	@SuppressWarnings("unchecked")
	public Connection openConnection(String dsAddress)
			throws ConnectionException {

		Connection connect = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			StringBuilder uri = new StringBuilder();
			uri.append("jdbc:mysql:");
			uri.append(dsAddress);

			try {
				connect = DriverManager.getConnection(uri.toString(), "test",
						"test");
				connect.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;

	}

	@SuppressWarnings("hiding")
	public <Connection> boolean closeConnection(Connection connection) {
		// TODO Auto-generated method stub
		boolean success = false;

		try {
			((java.sql.Connection) connection).close();
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
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
