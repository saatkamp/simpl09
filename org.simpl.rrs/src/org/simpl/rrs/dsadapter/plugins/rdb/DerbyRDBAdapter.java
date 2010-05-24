package org.simpl.rrs.dsadapter.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;
import org.simpl.rrs.retrieval.util.RRSRetrievalUtil;
import org.simpl.rrs.webservices.RDBSet;

public class DerbyRDBAdapter extends DSAdapterPlugin {

	public DerbyRDBAdapter() {
		this.setType("RDB");
		this.addSubtype("Derby");
		this.addLanguage("Derby", "SQL");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Connection openConnection(String dsAddress)
			{

		Connection connect = null;

		try {
			
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			StringBuilder uri = new StringBuilder();
			// jdbc:derby:sampleDB", "dba", "password");
			uri.append("jdbc:derby:");
			uri.append(dsAddress);
			uri.append(";create=true");

			try {
				connect = DriverManager.getConnection(uri.toString(), "", "");
				connect.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
			}

			return connect;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		}

		return connect;
	}

	@SuppressWarnings("hiding")
	@Override
	public <Connection> boolean closeConnection(Connection connection)
			{
		// TODO Auto-generated method stub
		boolean success = false;

		try {
			((java.sql.Connection) connection).close();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		}

		return success;
	}

	@Override
	public RDBSet retrieveData (String dsAddress, String statement){
		
		Statement connStatement = null;
		ResultSet resultSet = null;
		RDBSet data = new RDBSet();
		
		Connection connection = openConnection(dsAddress);
		try {
			connStatement = connection.createStatement();
			resultSet = connStatement.executeQuery(statement);
			
			data = RRSRetrievalUtil.getRDBDataFormatObject(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

}
