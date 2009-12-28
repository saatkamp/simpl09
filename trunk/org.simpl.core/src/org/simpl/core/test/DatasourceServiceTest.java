package org.simpl.core.test;

import org.simpl.core.datasource.ConnectionException;
import org.simpl.core.datasource.DataException;
import org.simpl.core.datasource.RDBDatasourceService;

import commonj.sdo.DataObject;

public class DatasourceServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RDBDatasourceService rdbService = new RDBDatasourceService();
		try {
			
			//Create Test
//			System.out.println("Neues SCHEMA Test erstellt: " + rdbService.sendStatement("simplDB", "CREATE SCHEMA TEST"));
//			System.out.println("Neue Tabelle Tab erstellt: " + rdbService.sendStatement("simplDB", "CREATE TABLE TEST.TAB (ID VARCHAR(20) NOT NULL, MODE VARCHAR(50))"));
			
			//Insert Test
//			rdbService.sendStatement("simplDB", "INSERT INTO TEST.TAB VALUES ('test','adsasdasd')");
			DataObject root = rdbService.executeStatement("simplDB", "SELECT * FROM TEST.TAB");
			
			//Query Test
			DataObject data = root.getDataObject("TAB[1]");
			System.out.println("ID: " + data.getString("ID"));
			System.out.println("MODE: " + data.getString("MODE"));
			
			DataObject data2 = root.getDataObject("TAB[2]");
			System.out.println("ID: " + data2.getString("ID"));
			System.out.println("MODE: " + data2.getString("MODE"));
			
			//Update Test
			if (data2.getString("MODE").equals("an")){
				data2.setString("MODE", "aus");
				System.out.println("ID: " + data2.getString("ID"));
				System.out.println("MODE: " + data2.getString("MODE"));
			}else{
				data2.setString("MODE", "an");
				System.out.println("ID: " + data2.getString("ID"));
				System.out.println("MODE: " + data2.getString("MODE"));
			}
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
