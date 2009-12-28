package org.simpl.core.test;

import org.simpl.core.datasource.ConnectionException;
import org.simpl.core.datasource.RDBDatasourceService2;

import commonj.sdo.DataObject;

public class DatasourceServiceTest2 {

	/**
	 * TODO DataObjects haben Probleme mit Schema, das brauchen wir aber!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RDBDatasourceService2 rdbService = new RDBDatasourceService2();
		try {
			//Query Test
			DataObject root = rdbService.queryData("simplDB", "SELECT * FROM TAB");
			DataObject data = root.getDataObject("TAB[1]");
			System.out.println("ID: " + data.getString("ID"));
			System.out.println("MODE: " + data.getString("MODE"));
			
			DataObject data2 = root.getDataObject("TAB[2]");
			System.out.println("ID: " + data2.getString("ID"));
			System.out.println("MODE: " + data2.getString("MODE"));
			
			//Drop Test
			System.out.println("Tabelle Tab gelöscht: " + rdbService.manipulateData("simplDB", "DROP TABLE TAB", null));
//			System.out.println("SCHEMA Test gelöscht: " + rdbService.manipulateData("simplDB", "DROP SCHEMA TEST RESTRICT", null));
			
			//Create Test
//			System.out.println("Neues SCHEMA Test erstellt: " + rdbService.defineData("simplDB", "CREATE SCHEMA TEST"));
			System.out.println("Neue Tabelle Tab erstellt: " + rdbService.defineData("simplDB", "CREATE TABLE TAB (ID VARCHAR(20) NOT NULL, MODE VARCHAR(50))"));
			
			//Insert Test
			rdbService.manipulateData("simplDB", "INSERT INTO TAB VALUES ('test','adsasdasd')", null);
			rdbService.manipulateData("simplDB", "INSERT INTO TAB VALUES ('lastSaved','on')", null);
			
			//Update Test
			rdbService.manipulateData("simplDB", "UPDATE TAB SET MODE='off' WHERE ID='lastSaved'", null);
			
			//Alternatives Update
			//1.Ändern Werte des root-DO
			if (data2.getString("MODE").equals("an")) {
				data2.setString("MODE", "aus");
				System.out.println("ID: " + data2.getString("ID"));
				System.out.println("MODE: " + data2.getString("MODE"));
			} else {
				data2.setString("MODE", "an");
				System.out.println("ID: " + data2.getString("ID"));
				System.out.println("MODE: " + data2.getString("MODE"));
			}
			//2.Pushen das root-DO wieder nach unten zum DAS, der die Änderungen
			// in der Datenquelle festschreibt
			rdbService.manipulateDataWithSDO("simplDB", root);
			
			//Endresultat, aus der Datenquelle
			root = rdbService.queryData("simplDB", "SELECT * FROM TAB");
			data = root.getDataObject("TAB[1]");
			System.out.println("ID: " + data.getString("ID"));
			System.out.println("MODE: " + data.getString("MODE"));
			
			data2 = root.getDataObject("TAB[2]");
			System.out.println("ID: " + data2.getString("ID"));
			System.out.println("MODE: " + data2.getString("MODE"));
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
