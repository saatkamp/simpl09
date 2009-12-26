package org.simpl.core.datasource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;

import commonj.sdo.DataObject;

public class RDBDatasourceService implements DatasourceService {

	@Override
	public boolean closeConnection() throws ConnectionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataObject executeStatement(String dsAddress, String statement)
			throws ConnectionException, DataException {
		// TODO Auto-generated method stub
		DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
		Command readCustomers = das.createCommand(statement);
		DataObject root = readCustomers.executeQuery();
		return root;
	}

	@Override
	public DataObject executeStatement(String dsAddress, String statement, DataObject data)
			throws ConnectionException, DataException {
		
		return null;
	}

	@Override
	public Connection openConnection(String dsAddress) throws ConnectionException {
		// TODO Umändern in DataSource Connection
		// Testweise wird hier nur eine embedded Derby Datenbank verwendet
		Connection connect = null;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			StringBuilder uri = new StringBuilder();
			uri.append("jdbc:derby:");
			uri.append(dsAddress);
			uri.append(";create=true");
			try {
				connect = DriverManager.getConnection(uri.toString());
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

	@Override
	public boolean sendStatement(String dsAddress, String statement) throws ConnectionException,
			DataException {
		// TODO Auto-generated method stub
		boolean success = false;
		Connection conn = openConnection(dsAddress);
		try {
			Statement stat = conn.createStatement();
			stat.execute(statement);
			conn.commit();
			success = true;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean sendStatement(String dsAddress, String statement, DataObject data)
			throws ConnectionException, DataException {
		// TODO Auto-generated method stub
		return false;
	}
}
