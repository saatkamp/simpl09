package org.simpl.rrs.dsadapter.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;
import org.simpl.rrs.retrieval.util.RRSRetrievalUtil;
import org.simpl.rrs.webservices.RDBSet;

public class MySQLRDBAdapter extends DSAdapterPlugin {

	public MySQLRDBAdapter() {
		this.setType("RDB");
		this.addSubtype("MySQL");
		this.addLanguage("MySQL", "SQL");
	}

	@SuppressWarnings("unchecked")
	public Connection openConnection(String dsAddress) {

		Connection connect = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			StringBuilder uri = new StringBuilder();
			uri.append("jdbc:mysql://");
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

	public RDBSet retrieveData(String dsAddress, String statement) {
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
