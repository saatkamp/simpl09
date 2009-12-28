package org.simpl.core.test;

import java.util.List;

import org.simpl.core.datasource.ConnectionException;
import org.simpl.core.datasource.RDBDatasourceService2;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

public class DatasourceServiceTest2 {

	/**
	 * TODO DataObjects haben Probleme mit Schema, das brauchen wir aber!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RDBDatasourceService2 rdbService = new RDBDatasourceService2();
		DataObject root = null;
		DataObject data = null;
		DataObject data2 = null;
		try {
			// Query Test
			root = rdbService.queryData("simplDB", "SELECT * FROM TAB");

			printDataObject(root, 0);

			// Drop Test
			System.out.println("Tabelle Tab gelöscht: "
					+ rdbService.manipulateData("simplDB", "DROP TABLE TAB",
							null));
			// System.out.println("SCHEMA Test gelöscht: " +
			// rdbService.manipulateData("simplDB", "DROP SCHEMA TEST RESTRICT",
			// null));

			// Create Test
			// System.out.println("Neues SCHEMA Test erstellt: " +
			// rdbService.defineData("simplDB", "CREATE SCHEMA TEST"));
			System.out
					.println("Neue Tabelle Tab erstellt: "
							+ rdbService
									.defineData("simplDB",
											"CREATE TABLE TAB (ID VARCHAR(20) NOT NULL, MODE VARCHAR(50))"));

			// Tuple-Insert Test
			rdbService.manipulateData("simplDB",
					"INSERT INTO TAB VALUES ('test','adsasdasd')", null);
			rdbService.manipulateData("simplDB",
					"INSERT INTO TAB VALUES ('lastSaved','on')", null);

			// Set-Insert Test
			rdbService.manipulateData("simplDB",
					"INSERT INTO TAB SELECT * FROM TAB2", null);

			// Direct Update Test
			rdbService.manipulateData("simplDB",
					"UPDATE TAB SET MODE='off' WHERE ID='lastSaved'", null);

			// DataObject Update Test
			// 1.Aktualisieren das Root-DataObject nach dem direkten Update
			root = rdbService.queryData("simplDB", "SELECT * FROM TAB");
			printDataObject(root, 0);
			data2=root.getDataObject("TAB[2]");
			// 2.Ändern Werte des root-SDO
			if (data2.getString("MODE").equals("an")) {
				data2.setString("MODE", "aus");
				System.out.println("New ID: " + data2.getString("ID"));
				System.out.println("New MODE: " + data2.getString("MODE"));
			} else {
				data2.setString("MODE", "an");
				System.out.println("New ID: " + data2.getString("ID"));
				System.out.println("New MODE: " + data2.getString("MODE"));
			}
			// 3.Pushen das root-SDO wieder nach unten zum DAS, der die
			// Änderungen
			// in der Datenquelle festschreibt
			rdbService.manipulateDataWithSDO("simplDB", root);

			// 4.Endresultat, aus der Datenquelle
			root = rdbService.queryData("simplDB", "SELECT * FROM TAB");
			printDataObject(root, 0);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printDataObject(DataObject dataObject, int indent) {
		// For each Property
		List properties = dataObject.getInstanceProperties();
		for (int p = 0, size = properties.size(); p < size; p++) {
			if (dataObject.isSet(p)) {
				Property property = (Property) properties.get(p);
				if (property.isMany()) {
					// For many-valued properties, process a list of values
					List values = dataObject.getList(p);
					for (int v = 0, count = values.size(); v < count; v++) {
						printValue(values.get(v), property, indent);
					}
				} else {
					// For single-valued properties, print out the value
					printValue(dataObject.get(p), property, indent);
				}
			}
		}
	}

	public static void printValue(Object value, Property property, int indent) {
		// Get the name of the property
		String propertyName = property.getName();
		// Construct a string for the proper indentation
		String margin = "";
		for (int i = 0; i < indent; i++)
			margin += "\t";
		if (value != null && property.isContainment()) {
			// For containment properties, display the value
			// with printDataObject
			Type type = property.getType();
			String typeName = type.getName();
			System.out.println(margin + propertyName + " (" + typeName + "):");
			printDataObject((DataObject) value, indent + 1);
		} else {
			// For non-containment properties, just print the value
			System.out.println(margin + propertyName + ": " + value);
		}
	}
}
