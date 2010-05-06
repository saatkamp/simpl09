package org.simpl.rrs.dsadapter.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;

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

	public Object retrieveData(String dsAddress, String statement) {

		Connection con = openConnection(dsAddress);
		Statement state;
		ResultSet rs;
		Object obj = null;
		try {
			state = con.createStatement();

			rs = state.executeQuery(statement);

			while (rs.next()) {
				obj = rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
	}
}
