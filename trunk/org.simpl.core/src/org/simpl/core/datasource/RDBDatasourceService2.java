package org.simpl.core.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import commonj.sdo.DataObject;

public class RDBDatasourceService2 implements IDatasourceService {

	static Logger logger = Logger.getLogger(RDBDatasourceService2.class);
	
	public RDBDatasourceService2(){
		// Set up a simple configuration that logs on the console.
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@Override
	public Connection openConnection(String dsAddress) throws ConnectionException {
		// TODO Umändern in DataSource Connection
		// Testweise wird hier nur eine embedded Derby Datenbank verwendet
		if (logger.isDebugEnabled()){
			logger.debug("Connection openConnection("+dsAddress+") executed.");
		}
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
	            logger.fatal("exception during establishing connection to: " + uri.toString(), e);
			}
			
			logger.info("Connection opened on "+dsAddress+".");
			return connect;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
            logger.fatal("exception during loading the JDBC driver", e);
		}
		return connect;
	}
	
	@Override
	public boolean closeConnection(Connection connection) throws ConnectionException {
		// TODO Auto-generated method stub
		boolean success = false;
		
		try {
			connection.close();
			success = true;
			if (logger.isDebugEnabled()){
				logger.debug("boolean closeConnection() executed successfully.");
			}
			logger.info("Connection closed.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (logger.isDebugEnabled()){
				logger.error("boolean closeConnection() executed with failures.", e);
			}
		}
		return success;
	}


	@Override
	public boolean defineData(String dsAddress, String statement) throws ConnectionException {
		if (logger.isDebugEnabled()){
			logger.debug("boolean sendStatement("+ dsAddress+", "+ statement+") executed.");
		}
		boolean success = false;
		Connection conn = openConnection(dsAddress);
		try {
			Statement stat = conn.createStatement();
			stat.execute(statement);
			conn.commit();
			success = true;
		} catch (Throwable e) {
            logger.error("exception executing the statement: " + statement, e);
		}
		
		logger.info("Statement '"+statement+"' send to "+dsAddress+".");
		return success;
	}


	@Override
	public boolean manipulateData(String dsAddress, String statement,
			DataObject data) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public DataObject queryData(String dsAddress, String statement) throws ConnectionException {
		if (logger.isDebugEnabled()){
			logger.debug("DataObject executeStatement("+ dsAddress +", "+statement+") executed.");
		}
		DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
		Command readCustomers = das.createCommand(statement);
		DataObject root = readCustomers.executeQuery();
		
		logger.info("Statement '"+statement+"' executed on "+dsAddress+".");
		return root;
	}
}
