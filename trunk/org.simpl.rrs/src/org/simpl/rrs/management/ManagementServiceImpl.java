package org.simpl.rrs.management;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import org.simpl.rrs.model.EPR;

public class ManagementServiceImpl implements ManagementService {
	private static final String DATABASE_NAME = "rrsDB";
	private static final String Reference_TABLE_NAME = "ReferenceTable";

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
			manipulationStatement.append("SET ");
			manipulationStatement.append(" ");

			String referenceName = null;
			referenceName = EPR.get("referenceName");
			EPR.remove("referenceName");

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
			manipulationStatement
					.deleteCharAt(manipulationStatement.length() - 2);

			// Id abgleichen
			manipulationStatement.append("WHERE REFERENCENAME =");
			manipulationStatement.append("'");
			// manipulationStatement.append(EPR.get("referenceName"));
			manipulationStatement.append(referenceName);
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
			// Das überflüssige Komma weglöschen
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
			// Das überflüssige Komma weglöschen
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
			manipulationStatement.append("'");
			manipulationStatement.append(EPR.get("referenceName"));
			manipulationStatement.append("'");

			System.out.println("DELETE: " + manipulationStatement.toString());

		}

		return manipulationStatement.toString();

	}

	private boolean DoesTableExist(Connection connection) throws SQLException {

		boolean tableExists = true;
		ResultSet resultSet = connection.getMetaData().getTables("%", "%", "%",
				new String[] { "TABLE" });
		if (resultSet.next() == false) {
			tableExists = false;
		}

		// if (resultSet.getString("TABLE_NAME") == Reference_TABLE_NAME) {
		// tableExists = true;
		// }

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
		// Einfügen der Einstellungen als Spalten
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
			try {
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connect = DriverManager.getConnection(uri.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

	public boolean Delete(EPR epr) {

		LinkedHashMap<String, String> EPRHM = EPRtoHM(epr);
		boolean success = false;
		Connection conn = getConnection();

		Statement statm;
		try {
			statm = conn.createStatement();
			success = statm.execute(getStatement("DELETE",
					Reference_TABLE_NAME, EPRHM));
			success = true;
			statm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;

	}

	public File Insert(EPR epr) throws SQLException {

		Connection conn = getConnection();
		LinkedHashMap<String, String> EPRHM = EPRtoHM(epr);

		if (DoesTableExist(conn) == false) {
			createTable(conn, Reference_TABLE_NAME, getCreateTableStatement(
					Reference_TABLE_NAME, EPRHM));
		}

		if (EPRAllreadyExists(Reference_TABLE_NAME, EPRHM, conn) == false) {
			try {
				Statement statm = conn.createStatement();
				statm.executeUpdate(getStatement("INSERT",
						Reference_TABLE_NAME, EPRHM));
				statm.close();
				conn.close();

				// TODO: EPR xml Datei erstellen

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	public boolean Update(EPR epr) throws SQLException {
		boolean success = false;

		LinkedHashMap<String, String> EPRHM = EPRtoHM(epr);
		Connection conn = getConnection();

		if (EPRAllreadyExists(Reference_TABLE_NAME, EPRHM, conn) == true) {
			try {
				Statement statm = conn.createStatement();
				statm.execute(getStatement("UPDATE", Reference_TABLE_NAME,
						EPRHM));
				statm.close();
				conn.close();

				// EPR xml Datei erstellen

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return success;

	}

	public boolean EPRAllreadyExists(String table,
			LinkedHashMap<String, String> EPR, Connection conn) {

		boolean EPRInDB = false;
		String tabl = table.replace(" ", "");

		StringBuilder SearchStatement = new StringBuilder();
		SearchStatement.append("SELECT");
		SearchStatement.append(" ");
		SearchStatement.append("*");
		SearchStatement.append(" ");
		SearchStatement.append("FROM ");
		SearchStatement.append(tabl);
		SearchStatement.append(" ");
		SearchStatement.append("WHERE referenceName = '");
		SearchStatement.append(EPR.get("referenceName"));
		SearchStatement.append("'");

		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet results = statement.executeQuery(SearchStatement
					.toString());
			if (results.next()) {
				EPRInDB = true;
				return EPRInDB;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EPRInDB;

	}

	public LinkedHashMap<String, String> EPRtoHM(EPR epr) {
		LinkedHashMap<String, String> HM = new LinkedHashMap<String, String>();

		HM.put("referenceName",
				epr.getParameters().getReferenceName() == null ? "" : epr
						.getParameters().getReferenceName());
		HM.put("rrsAddress", epr.getRrsAddress() == null ? "" : epr
				.getRrsAddress());
		HM.put("adapterType", epr.getProperties().getRrsAdapter() == null ? ""
				: epr.getProperties().getRrsAdapter());
		HM.put("statement", epr.getParameters().getStatement() == null ? ""
				: epr.getParameters().getStatement());
		HM.put("dsAddress", epr.getParameters().getDsAddress() == null ? ""
				: epr.getParameters().getDsAddress());
		HM.put("portType", epr.getPortType() == null ? "" : epr.getPortType());
		if (epr.getService() != null) {
			HM.put("serviceName",
					epr.getService().getServiceName() == null ? "" : epr
							.getService().getServiceName());
			HM.put("portName", epr.getService().getPortName() == null ? ""
					: epr.getService().getPortName());
		} else {
			HM.put("serviceName", "");
			HM.put("portName", "");
		}
		HM.put("rrsPolicy", epr.getRrsPolicy() == null ? "" : epr
				.getRrsPolicy());

		return HM;
	}
}
