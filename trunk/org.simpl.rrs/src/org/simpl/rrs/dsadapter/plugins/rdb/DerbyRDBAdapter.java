package org.simpl.rrs.dsadapter.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;
import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;

import commonj.sdo.DataObject;

public class DerbyRDBAdapter extends DSAdapterPlugin {

	public DerbyRDBAdapter() {
		this.setType("RDB");
		this.addSubtype("Derby");
		this.addLanguage("Derby", "SQL");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Connection openConnection(String dsAddress)
			throws ConnectionException {

		Connection connect = null;

		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			StringBuilder uri = new StringBuilder();
			// jdbc:derby:sampleDB", "dba", "password");
			uri.append("jdbc:derby:");
			uri.append(dsAddress);

			try {
				connect = DriverManager.getConnection(uri.toString(), "test",
						"test");
				connect.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}

			return connect;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}

		return connect;
	}

	@SuppressWarnings("hiding")
	@Override
	public <Connection> boolean closeConnection(Connection connection)
			throws ConnectionException {
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

	@Override
	public Object retrieveData(String dsAddress, String statement)
			throws ConnectionException {

		DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
	    Command read = das.createCommand(statement);
	    DataObject root = read.executeQuery();
	    
	    return root;

	}

}
