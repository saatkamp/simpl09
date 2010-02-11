package org.simpl.core.datasource.plugins.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.simpl.core.datasource.DatasourceServicePlugin;
import org.simpl.core.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <p>
 * Implements all methods of the {@link IDatasourceService} interface for
 * supporting the IBM DB2 relational database.
 * </p>
 * 
 * dsAddress = //server:port/database or //server/database, for example //localhost:50000/testdb.  
 * 
 * @author hahnml
 */
public class DB2RDBDatasourceService extends DatasourceServicePlugin {
	static Logger logger = Logger.getLogger(DB2RDBDatasourceService.class);

	public DB2RDBDatasourceService() {
    this.setDatasourceType("database");
    this.addDatasourceSubtype("DB2");
    this.addDatasourceLanguage("DB2", "SQL");

		// Set up a simple configuration that logs on the console.
		PropertyConfigurator.configure("log4j.properties");
	}

	@Override
	public Connection openConnection(String dsAddress)
			throws ConnectionException {
		// TODO Umändern in DataSource Connection
		// Testweise wird hier nur eine embedded Derby Datenbank verwendet
		if (logger.isDebugEnabled()) {
			logger.debug("Connection openConnection(" + dsAddress
					+ ") executed.");
		}
		Connection connect = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			StringBuilder uri = new StringBuilder();
			uri.append("jdbc:db2:");
			uri.append(dsAddress);
			try {
				//TODO Hier müssen noch die im SIMPL Core hinterlegten Authentification-Informationen geladen werden
				connect = DriverManager.getConnection(uri.toString(), "test", "test");
				connect.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.fatal("exception during establishing connection to: "
						+ uri.toString(), e);
			}

			logger.info("Connection opened on " + dsAddress + ".");
			return connect;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.fatal("exception during loading the JDBC driver", e);
		}
		return connect;
	}

	@Override
	public boolean closeConnection(Connection connection)
			throws ConnectionException {
		// TODO Auto-generated method stub
		boolean success = false;

		try {
			connection.close();
			success = true;
			if (logger.isDebugEnabled()) {
				logger
						.debug("boolean closeConnection() executed successfully.");
			}
			logger.info("Connection closed.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (logger.isDebugEnabled()) {
				logger.error(
						"boolean closeConnection() executed with failures.", e);
			}
		}
		return success;
	}

	@Override
	public DataObject queryData(String dsAddress, String statement)
			throws ConnectionException {
		if (logger.isDebugEnabled()) {
			logger.debug("DataObject queryData(" + dsAddress + ", " + statement
					+ ") executed.");
		}
		DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
		Command read = das.createCommand(statement);
		DataObject root = read.executeQuery();

		logger.info("Statement '" + statement + "' executed on " + dsAddress
				+ ".");
		return root;
	}

	@Override
	public boolean defineData(String dsAddress, String statement)
			throws ConnectionException {
		if (logger.isDebugEnabled()) {
			logger.debug("boolean defineData(" + dsAddress + ", " + statement
					+ ") executed.");
		}
		boolean success = false;
		Connection conn = openConnection(dsAddress);
		try {
			Statement stat = conn.createStatement();
			stat.execute(statement);
			conn.commit();
			success = true;
			stat.close();
		} catch (Throwable e) {
			logger.error("exception executing the statement: " + statement, e);
		}

		logger.info("Statement '" + statement + "' send to " + dsAddress + ".");
		closeConnection(conn);
		return success;
	}

	@Override
	public boolean manipulateData(String dsAddress, String statement,
			DataObject data) throws ConnectionException {
		if (logger.isDebugEnabled()) {
			logger.debug("boolean manipulateData(" + dsAddress + ", "
					+ statement + ") executed.");
		}

		// TODO Hier muss noch der Fall mit einem DataObject abgedeckt werden.
		boolean success = false;
		Connection conn = openConnection(dsAddress);
		try {
			Statement stat = conn.createStatement();
			stat.execute(statement);
			conn.commit();
			stat.close();
			success = true;
		} catch (Throwable e) {
			logger.error("exception executing the statement: " + statement, e);
		}

		logger.info("Statement '" + statement + "' send to " + dsAddress + ".");
		closeConnection(conn);
		return success;
	}

	// Test
	public boolean manipulateDataWithSDO(String dsAddress, DataObject data)
			throws ConnectionException {
		if (logger.isDebugEnabled()) {
			logger.debug("boolean manipulateDataWithSDO(" + dsAddress + ", "
					+ data + ") executed.");
		}
		DAS das = DAS.FACTORY.createDAS(openConnection(dsAddress));
		das.applyChanges(data);
		logger.info("DataObject " + data + "was send back to datasource "
				+ dsAddress);
		return false;
	}

	@Override
	public boolean depositData(String dsAddress, String statement, String target)
			throws ConnectionException {
		boolean success = false;
		
		if (logger.isDebugEnabled()) {
			logger.debug("DataObject depositData(" + dsAddress + ", " + statement
					+ ") executed.");
		}
		
		//Beispiel: CREATE TABLE TAB AS (SELECT * FROM T1 WITH NO DATA);
		//Dies erzeugt aus den Query-Daten eine Neue Tabelle TAB mit den gequerieten Daten.
		StringBuilder createTableStatement = new StringBuilder();
		createTableStatement.append("CREATE TABLE");
		createTableStatement.append(" ");
		createTableStatement.append(target);
		createTableStatement.append(" AS (");
		createTableStatement.append(statement);
		createTableStatement.append(" ");
		createTableStatement.append(") WITH NO DATA");
		
		StringBuilder insertStatement = new StringBuilder();
		insertStatement.append("INSERT INTO");
		insertStatement.append(" ");
		insertStatement.append(target);
		insertStatement.append(" ");
		insertStatement.append(statement);
		
		Connection conn = openConnection(dsAddress);
		try {
			Statement createState = conn.createStatement();
			Statement insertState = conn.createStatement();

			//Neue Tabelle aus dem Query erzeugen
			createState.execute(createTableStatement.toString());
			
			//Query-Daten in die neue Tabelle einfügen
			insertState.execute(insertStatement.toString());
			
			conn.commit();
			createState.close();
			insertState.close();
			closeConnection(conn);
			
			success = true;
		} catch (Throwable e) {
			logger.error("exception executing the statement: " + createTableStatement.toString(), e);
		}
		
		logger.info("Statement '" + createTableStatement.toString() + "' " + "& '" + insertStatement.toString() + "'" + "executed on " + dsAddress);
		
		return success;
	}

  /* (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#getMetaData(java.lang.String)
   */
  @Override
  public DataObject getMetaData(String dsAddress) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }
}