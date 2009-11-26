package org.simpl.core.datasource.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * <p>
 * A test implementation of the RDBDatasourceService.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 * 
 */
public class RDBTest {

	public static boolean sendStatement(String statement){
		Connection connect = openConnection();
		boolean success = false;
		if (connect != null){
			try {
				Statement stmt = connect.createStatement();
				// Can execute DDL statements, such as CREATE, ALTER, DROP, ...
				// and it can execute DML statements like INSERT, UPDATE and DELETE, 
				// that do not contain parameter markers 
				stmt.executeUpdate(statement);
				stmt.close();
				success = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return success;
	}

	private static Connection openConnection(){
		String url = "jdbc:db2:testdb";
		String user = "Test";
		String password = "test";
		Connection connect = null;
		try {
			// Load the DB2 JDBC Type 2 Driver with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			connect = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

}
