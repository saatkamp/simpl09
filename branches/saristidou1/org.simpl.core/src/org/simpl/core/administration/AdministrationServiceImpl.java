package org.simpl.core.administration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import org.apache.ode.simpl.events.listener.AuditingParameters;
import org.simpl.core.clients.RMClient;
import org.simpl.core.connector.ConnectorProvider;
import org.simpl.core.dataconverter.DataConverterProvider;
import org.simpl.core.datatransformation.DataTransformationServiceProvider;

/**
 * <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class AdministrationServiceImpl implements AdministrationServiceInterface {
  private static final String DATABASE_NAME = "simplDB";

  public boolean saveSettings(String schema, String table, String settingName,
      LinkedHashMap<String, String> settings) {
    boolean success = false;
    String schem = schema.replaceAll(" ", "");
    String tabl = table.replaceAll(" ", "");
    try {
      Connection conn = getConnection();
      // Erzeugen das Schema, falls es nicht schon exisitiert
      createSchemaIfItDoesntExistYet(conn, schem);
      // Erzeugen die Tabelle im Schema, falls sie noch nicht existiert, mit einem
      // aus der settings liste generierten Create statement
      createTableIfItDoesntExistYet(conn, schem, tabl, getCreateTableStatement(schem,
          tabl, settings));

      Statement statement = conn.createStatement();
      // Speichern der Einstellungen in der Tabelle des angegebenen Schemas
      if (settingsAlreadyExist(conn, schem, tabl, settingName)) {
        // Updaten die Werte in der Tabelle
        statement
            .executeUpdate(getStatement("UPDATE", schem, tabl, settingName, settings));
      } else {
        // Fügen die Werte in die Tabelle ein
        statement
            .executeUpdate(getStatement("INSERT", schem, tabl, settingName, settings));
      }
      // Send the settings to the SIMPL BPEL Event Listener in ODE.
      AuditingParameters.getInstance().setSettings(settings);
      statement.close();
      conn.close();
      success = true;

      // reload the data from resource management, because it may have changed
      if (schema.equals("ResourceManagement")) {
        System.out.println("REALOAD RESOURCE MANAGEMENT DATA");
        RMClient.getInstance().reload();
        
        // update plugins
        ConnectorProvider.loadPlugins();
        DataConverterProvider.loadPlugins();
        DataTransformationServiceProvider.loadServices();
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }

    return success;
  }

  // Load müsste so funktionieren
  public LinkedHashMap<String, String> loadSettings(String schema, String table,
      String settingName) {
    LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();
    String schem = schema.replaceAll(" ", "");
    String tabl = table.replaceAll(" ", "");
    try {
      Connection conn = getConnection();
      Statement stat = conn.createStatement();
      ResultSet resultSet = stat.executeQuery("SELECT * FROM " + schem + "." + tabl
          + " WHERE id='" + settingName + "'");
      ResultSetMetaData rsMetaData = resultSet.getMetaData();

      // Erste Zeile der Ergebnismenge auswählen (es gibt nur eine durch die id)
      resultSet.next();

      if (rsMetaData.getColumnCount() > 1) {
        // Da wir wissen unsere id ist eindeutig, kann es nur eine Zeile im ResultSet
        // geben und diese lesen wir aus.
        for (int i = 2; i <= rsMetaData.getColumnCount(); i++) {
          // Wir starten in der 2.Spalte, da wir die id nicht
          // in den Einstellungen haben wollen.
          settings.put(rsMetaData.getColumnName(i), resultSet.getString(i));
        }
      }
      resultSet.close();
      stat.close();
      conn.close();
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return settings;
  }

  // Erzeugt aus der Liste der Einstellungen einen "Create Table"-Befehl, der
  // eine passende Tabelle im Schema [schema] mit dem Namen [table] erzeugt.
  private String getCreateTableStatement(String schema, String table,
      LinkedHashMap<String, String> settings) {
    StringBuilder createTableStatement = new StringBuilder();

    // Erstellen den standard Teil des Befehls
    createTableStatement.append("CREATE TABLE");
    createTableStatement.append(" ");
    // Schema und Name der Tabelle
    createTableStatement.append(schema);
    createTableStatement.append(".");
    createTableStatement.append(table);
    createTableStatement.append(" ( ");

    // Spalten festlegen
    // id einfügen
    createTableStatement.append("ID VARCHAR (20) NOT NULL, ");

    // Einfügen der Einstellungen als Spalten
    for (String name : settings.keySet()) {
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
    uri.append(AdministrationServiceImpl.DATABASE_NAME);
    uri.append(";create=true");
    try {
      connect = DriverManager.getConnection(uri.toString());
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return connect;
  }

  private void createSchemaIfItDoesntExistYet(Connection connection, String schema)
      throws Exception {
    ResultSet resultSet = connection.getMetaData().getSchemas();
    // int columnCnt = resultSet.getMetaData().getColumnCount();
    boolean shouldCreateSchema = true;
    while (resultSet.next() && shouldCreateSchema) {
      if (resultSet.getString("TABLE_SCHEM").equalsIgnoreCase(schema)) {
        shouldCreateSchema = false;
      }
    }
    resultSet.close();
    if (shouldCreateSchema) {
      System.out.println("Creating schema " + schema + " ...");
      Statement statement = connection.createStatement();
      String statem = "CREATE SCHEMA " + schema;

      // TODO Testausgabe entfernen
      System.out.println("Schema Create: " + statem);

      statement.execute(statem);
      statement.close();
    }
  }

  // TODO Noch das Schema integrieren/abgleichen !
  private void createTableIfItDoesntExistYet(Connection connection, String schema,
      String table, String cStatement) throws Exception {
    ResultSet resultSet = connection.getMetaData().getTables("%", "%", "%",
        new String[] { "TABLE" });
    // int columnCnt = resultSet.getMetaData().getColumnCount();
    boolean shouldCreateTable = true;
    while (resultSet.next() && shouldCreateTable) {
      if (resultSet.getString("TABLE_SCHEM").equalsIgnoreCase(schema)
          && resultSet.getString("TABLE_NAME").equalsIgnoreCase(table)) {
        shouldCreateTable = false;
      }
    }
    resultSet.close();
    if (shouldCreateTable) {
      System.out.println("Creating Table " + schema + "." + table + " ...");
      Statement statement = connection.createStatement();
      String statem = cStatement;
      // TODO Testausgabe entfernen
      System.out.println("Create Table: " + cStatement);
      statement.execute(statem);
      statement.close();
    }
  }

  private boolean settingsAlreadyExist(Connection connection, String schema,
      String table, String settingName) {
    boolean exists = false;
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM " + schema + "."
          + table + " WHERE ID='" + settingName + "'");
      if (resultSet.next()) {
        exists = true;
      }
      // TODO Testausgabe entfernen
      System.out.println("Exists Test: " + "SELECT * FROM " + schema + "." + table
          + " WHERE ID='" + settingName + "'");

      resultSet.close();
      statement.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return exists;
  }

  private String getStatement(String type, String schema, String table,
      String settingName, LinkedHashMap<String, String> settings) {
    StringBuilder manipulationStatement = new StringBuilder();

    if (type.equalsIgnoreCase("update")) {
      // Update
      // Erstellen den standard Teil des Befehls
      manipulationStatement.append("UPDATE");
      manipulationStatement.append(" ");
      // Schema und Name der Tabelle
      manipulationStatement.append(schema);
      manipulationStatement.append(".");
      manipulationStatement.append(table);
      manipulationStatement.append(" ");
      manipulationStatement.append("SET");
      manipulationStatement.append(" ");

      // Werte aus HashMap lesen
      for (String name : settings.keySet()) {
        manipulationStatement.append(name);
        manipulationStatement.append(" = ");
        manipulationStatement.append("'");
        manipulationStatement.append(settings.get(name));
        manipulationStatement.append("'");
        manipulationStatement.append(", ");
      }
      // Das überflüssige Komma weglöschen
      manipulationStatement.deleteCharAt(manipulationStatement.length() - 2);

      // Id abgleichen
      manipulationStatement.append("WHERE ID = ");
      manipulationStatement.append("'");
      manipulationStatement.append(settingName);
      manipulationStatement.append("'");

      // TODO Testausgabe entfernen
      System.out.println("UPDATE: " + manipulationStatement.toString());

      // Beispiel Endergebnis:
      // UPDATE GlobalSettings.AuthentificationData SET
      // USER = 'asd',
      // PASSWORD = 'aasd',
      // WHERE ID = 'lastSaved'

    } else {
      // Insert

      // Erstellen den standard Teil des Befehls
      manipulationStatement.append("INSERT INTO");
      manipulationStatement.append(" ");
      // Schema und Name der Tabelle
      manipulationStatement.append(schema);
      manipulationStatement.append(".");
      manipulationStatement.append(table);

      manipulationStatement.append(" (");

      // id einfügen
      manipulationStatement.append("id, ");

      // Namen aus HashMap lesen
      for (String name : settings.keySet()) {
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
      manipulationStatement.append(settingName);
      manipulationStatement.append("', ");

      // Werte aus HashMap lesen
      for (String name : settings.keySet()) {
        manipulationStatement.append("'");
        manipulationStatement.append(settings.get(name));
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
    }

    return manipulationStatement.toString();
  }
}