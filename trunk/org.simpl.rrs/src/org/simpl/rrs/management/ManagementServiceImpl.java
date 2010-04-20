package org.simpl.rrs.management;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

public class ManagementServiceImpl implements ManagementService {
	private static final String DATABASE_NAME = "rrsDB";

	
	
	private String getStatement(String type, String table, String EPRName, LinkedHashMap<String, String> EPR) {
		    StringBuilder manipulationStatement = new StringBuilder();

		    if (type.equalsIgnoreCase("update")) {
		      // Update
		      // Erstellen den standard Teil des Befehls
		      manipulationStatement.append("UPDATE");
		      manipulationStatement.append(" ");
		      // Schema und Name der Tabelle

		      manipulationStatement.append(table);
		      manipulationStatement.append(" ");
		      manipulationStatement.append("SET");
		      manipulationStatement.append(" ");

		      // Werte aus HashMap lesen
		      for (String name : EPR.keySet()) {
		        manipulationStatement.append(name);
		        manipulationStatement.append(" = ");
		        manipulationStatement.append("'");
		        manipulationStatement.append(EPR.get(name));
		        manipulationStatement.append("'");
		        manipulationStatement.append(", ");
		      }
		      // Das überflüssige Komma weglöschen
		      manipulationStatement.deleteCharAt(manipulationStatement.length() - 2);

		      // Id abgleichen
		      manipulationStatement.append("WHERE ID = ");
		      manipulationStatement.append("'");
		      manipulationStatement.append(EPRName);
		      manipulationStatement.append("'");

		      // TODO Testausgabe entfernen
		      System.out.println("UPDATE: " + manipulationStatement.toString());

		      // Beispiel Endergebnis:
		      // UPDATE GlobalSettings.AuthentificationData SET
		      // USER = 'asd',
		      // PASSWORD = 'aasd',
		      // WHERE ID = 'lastSaved'

		    } else if (type.equalsIgnoreCase("Insert")) {
		      // Insert

		      // Erstellen den standard Teil des Befehls
		      manipulationStatement.append("INSERT INTO");
		      manipulationStatement.append(" ");
		      // Schema und Name der Tabelle
		      manipulationStatement.append(table);

		      manipulationStatement.append(" (");

		      // id einfügen
		      manipulationStatement.append("id, ");

		      // Namen aus HashMap lesen
		      for (String name : EPR.keySet()) {
		        manipulationStatement.append(name);
		        manipulationStatement.append(", ");
		      }
		      // Das überflüssige Komma weglöschen
		      manipulationStatement.deleteCharAt(manipulationStatement.length() - 2);

		      manipulationStatement.append(" )");
		      manipulationStatement.append(" VALUES ");
		      manipulationStatement.append("(");

		      // id wert einfügen
		      manipulationStatement.append("'");
		      manipulationStatement.append(EPRName);
		      manipulationStatement.append("', ");

		      // Werte aus HashMap lesen
		      for (String name : EPR.keySet()) {
		        manipulationStatement.append("'");
		        manipulationStatement.append(EPR.get(name));
		        manipulationStatement.append("', ");
		      }
		      // Das überflüssige Komma weglöschen
		      manipulationStatement.deleteCharAt(manipulationStatement.length() - 2);

		      manipulationStatement.append(" )");

		      // TODO Testausgabe entfernen
		      System.out.println("INSERT: " + manipulationStatement.toString());

		      // Beispiel Endergebnis:
		      // INSERT INTO GlobalSettings.AuthentificationData
		      // ('id', 'user', 'password') VALUES
		      // ('lastSaved', 'asd', 'aasd')
		    } else if (type.equalsIgnoreCase("Delete")) {
		    	manipulationStatement.append("DELETE FROM");
		    	manipulationStatement.append(" ");
		    	manipulationStatement.append(table);
		    	manipulationStatement.append(" ");
		    	manipulationStatement.append("WHERE ID = ");
		    	manipulationStatement.append(EPRName);
		    	
		    }

		    return manipulationStatement.toString();
		
	}

	private void createTableIfItDoesntExistYet(Connection connection,
			String table, String cStatement) throws SQLException {
		
		ResultSet resultSet = connection.getMetaData().getTables("%", "%", "%",
		        new String[] { "TABLE" });
		
		boolean shouldCreateTable = true;
	    while (resultSet.next() && shouldCreateTable) {
	      if (resultSet.getString("TABLE_NAME").equalsIgnoreCase(table)) {
	        shouldCreateTable = false;
	      }
	    }
	    resultSet.close();
	    if (shouldCreateTable) {
	      System.out.println("Creating Table " + table + " ...");
	      Statement statement = connection.createStatement();
	      String statem = cStatement;
	      // TODO Testausgabe entfernen
	      System.out.println("Create Table: " + cStatement);
	      statement.execute(statem);
	      statement.close();
	    }
	  }
		// TODO Auto-generated method stub
		

	private String getCreateTableStatement(String table, LinkedHashMap<String, String> EPR) {
		// TODO Auto-generated method stub
		
		StringBuilder createTableStatement = new StringBuilder();

	    // Erstellen den standard Teil des Befehls
	    createTableStatement.append("CREATE TABLE");
	    createTableStatement.append(" ");
	    //Name der Tabelle
	    createTableStatement.append(table);
	    createTableStatement.append(" ( ");
		
	 // Spalten festlegen
	    // id einfügen
	    createTableStatement.append("ID VARCHAR (20) NOT NULL, ");

	    // Einfügen der Einstellungen als Spalten
	    for (String name : EPR.keySet()) {
	      createTableStatement.append(name);
	      createTableStatement.append(" VARCHAR(255), ");
	    }

	    // ID als primaryKey setzen
	    createTableStatement.append("PRIMARY KEY ( ID)");
	    createTableStatement.append(" )");

	    // Beispiel Endergebnis:
	    // CREATE TABLE GlobalSettings.AuthentificationData (
	    // ID VARCHAR (10) NOT NULL,
	    // USER VARCHAR (20),
	    // PASSWORD VARCHAR (20),
	    // PRIMARY KEY ( ID) ) ;

	    return createTableStatement.toString();
	  }

	private Connection getConnection() {
		Connection connect = null;
	    StringBuilder uri = new StringBuilder();
	    uri.append("jdbc:derby:");
	    uri.append(DATABASE_NAME);
	    uri.append(";create=true");
	    try {
	      connect = DriverManager.getConnection(uri.toString());
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    return connect;
	}

	public String Delete(String table, LinkedHashMap<String, String> EPR, String EPRName) {
		
		boolean succes = false;
		String tabl = table.replace(" ", "");
		
		Connection conn = getConnection();
		
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(getStatement("DELETE", table, EPRName, EPR));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;

	}

	public File Insert(String table, LinkedHashMap<String, String> EPR, String EPRName) throws SQLException {
		boolean success = false;
		String tabl = table.replace(" ", "");
		
		Connection conn = getConnection();
		
		createTableIfItDoesntExistYet(conn, tabl, getCreateTableStatement(tabl, EPR));
		
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(getStatement("INSERT",  tabl, EPRName, EPR));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	public boolean Update(String table, LinkedHashMap<String, String> EPR, String EPRName) throws SQLException {
		boolean success = false;
		String tabl = table.replaceAll(" ", "");
		
		Connection conn= getConnection();
		
		//Kontrollieren ob die Tabelle 'References' bereits existiert, wenn nicht, erstellen.
		
		createTableIfItDoesntExistYet(conn, tabl, getCreateTableStatement(
		          tabl, EPR));

		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(getStatement("UPDATE",  tabl, EPRName, EPR));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
		
	}

}
