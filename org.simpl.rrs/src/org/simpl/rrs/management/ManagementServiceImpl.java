package org.simpl.rrs.management;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import org.simpl.rrs.EPR;

public class ManagementServiceImpl implements ManagementService {
	private static final String DATABASE_NAME = "rrsDB";
	private static final String Reference_TABLE_NAME = "References";

	private String getStatement(String type, String table,
			LinkedHashMap<String, String> EPR) {
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
			// Das �berfl�ssige Komma wegl�schen
			manipulationStatement
					.deleteCharAt(manipulationStatement.length() - 2);

			// Id abgleichen
			manipulationStatement.append("WHERE ID = REFERENCENAME");
			manipulationStatement.append("'");
			// manipulationStatement.append(EPRName);
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

			// Namen aus HashMap lesen
			for (String name : EPR.keySet()) {
				manipulationStatement.append(name);
				manipulationStatement.append(", ");
			}
			// Das �berfl�ssige Komma wegl�schen
			manipulationStatement
					.deleteCharAt(manipulationStatement.length() - 2);

			manipulationStatement.append(" )");
			manipulationStatement.append(" VALUES ");
			manipulationStatement.append("(");

			// Werte aus HashMap lesen
			for (String name : EPR.keySet()) {
				manipulationStatement.append("'");
				manipulationStatement.append(EPR.get(name));
				manipulationStatement.append("', ");
			}
			// Das �berfl�ssige Komma wegl�schen
			manipulationStatement
					.deleteCharAt(manipulationStatement.length() - 2);

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
			manipulationStatement.append("WHERE REFERENCENAME = ");
			manipulationStatement.append(EPR.get("ReferenceName"));

		}

		return manipulationStatement.toString();

	}

	private boolean DoesTableExist(Connection connection) throws SQLException {

		boolean tableExists = false;
		ResultSet resultSet = connection.getMetaData().getTables("%", "%", "%",
				new String[] { "TABLE" });

		if (resultSet.getString("TABLE_NAME") == Reference_TABLE_NAME) {
			tableExists = true;
		}

		return tableExists;
	}

	private void createTable(Connection connection, String table,
			String cStatement) throws SQLException {

		System.out.println("Creating Table " + table + " ...");
		Statement statement = connection.createStatement();
		String statem = cStatement;
		// TODO Testausgabe entfernen
		System.out.println("Create Table: " + cStatement);
		statement.execute(statem);
		statement.close();
	}

	// TODO Auto-generated method stub

	private String getCreateTableStatement(String table,
			LinkedHashMap<String, String> EPR) {
		// TODO Auto-generated method stub

		StringBuilder createTableStatement = new StringBuilder();

		// Erstellen den standard Teil des Befehls
		createTableStatement.append("CREATE TABLE");
		createTableStatement.append(" ");
		// Name der Tabelle
		createTableStatement.append(table);
		createTableStatement.append(" ( ");

		// Spalten festlegen
		// Einf�gen der Einstellungen als Spalten
		for (String name : EPR.keySet()) {
			createTableStatement.append(name);
			createTableStatement.append(" VARCHAR(255), ");
		}

		// ReferenceName als primaryKey setzen
		createTableStatement.append("PRIMARY KEY ( REFERENCENAME)");
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

	public boolean Delete(String uri, String adapterType, String referenceName,
			String statement) {

		LinkedHashMap<String, String> EPR = CreateEPR(uri, adapterType,
				referenceName, statement);
		
		boolean success = false;

		Connection conn = getConnection();

		Statement statm;
		try {
			statm = conn.createStatement();
			success = statm.execute(getStatement("DELETE", Reference_TABLE_NAME, EPR));
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;

	}


	public File Insert(String uri, String adapterType, String referenceName,
			String statement) throws SQLException {

		LinkedHashMap<String, String> EPR = CreateEPR(uri, adapterType,
				referenceName, statement);
		Connection conn = getConnection();

		if (DoesTableExist(conn) == true) {
			createTable(conn, Reference_TABLE_NAME, getCreateTableStatement(
					Reference_TABLE_NAME, EPR));
		}

		if (EPRAllreadyExists(Reference_TABLE_NAME, EPR)) {
			try {
				Statement statm = conn.createStatement();
				statm.executeUpdate(getStatement("INSERT",
						Reference_TABLE_NAME, EPR));

				// EPR xml Datei erstellen

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	public boolean Update(String uri, String adapterType, String referenceName,
			String statement) throws SQLException {
		boolean success = false;
		LinkedHashMap<String, String> EPR = CreateEPR(uri, adapterType,
				referenceName, statement);

		Connection conn = getConnection();

		try {
			Statement statm = conn.createStatement();
			statm.executeUpdate(getStatement("UPDATE", Reference_TABLE_NAME,
					EPR));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;

	}

	public boolean EPRAllreadyExists(String table,
			LinkedHashMap<String, String> EPR) {

		boolean EPRInDB = false;
		String tabl = table.replace(" ", "");

		Connection conn = getConnection();

		StringBuilder SearchStatement = new StringBuilder();
		SearchStatement.append("SELECT");
		SearchStatement.append(" ");
		SearchStatement.append("ID");
		SearchStatement.append(" ");
		SearchStatement.append("FROM");
		SearchStatement.append(tabl);
		SearchStatement.append(" ");
		SearchStatement.append("WHERE ID = ");
		SearchStatement.append(EPR.get("ReferenceName"));

		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet results = statement.executeQuery(SearchStatement
					.toString());
			if (results != null) {
				EPRInDB = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EPRInDB;

	}
	
	private LinkedHashMap<String, String> CreateEPR(String uri,
			String adapterType, String referenceName, String statement) {

		LinkedHashMap<String, String> EPR = new LinkedHashMap<String, String>();
		EPR.put("Address", uri);
		EPR.put("adapterType", adapterType);
		EPR.put("referenceName", referenceName);
		EPR.put("Statement", statement);

		return EPR;

	}

}