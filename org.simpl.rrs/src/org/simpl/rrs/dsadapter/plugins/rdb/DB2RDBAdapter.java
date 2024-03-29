package org.simpl.rrs.dsadapter.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;
import org.simpl.rrs.retrieval.util.RRSRetrievalUtil;
import org.simpl.rrs.webservices.RDBSet;

public class DB2RDBAdapter extends DSAdapterPlugin {

	public DB2RDBAdapter() {
		this.setType("RDB");
		this.addSubtype("DB2");
		this.addLanguage("DB2", "SQL");
	}

	@SuppressWarnings("unchecked")
	public Connection openConnection(String dsAddress, String userName, String password) {

		Connection connect = null;

		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			StringBuilder uri = new StringBuilder();
			uri.append("jdbc:db2://");
			uri.append((dsAddress));

			try {
				connect = (java.sql.Connection) DriverManager.getConnection(uri
						.toString(), userName, password);
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
	public <Connection> boolean closeConnection(Connection connection) {

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

	public RDBSet retrieveData(String dsAddress, String statement, String userName, String password) {
		Statement connStatement = null;
		ResultSet resultSet = null;
		RDBSet data = new RDBSet();
		
		Connection connection = openConnection(dsAddress, userName, password);
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
