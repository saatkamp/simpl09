package org.simpl.core.datasource.test;


import org.simpl.core.datasource.ConnectionException;
import org.simpl.core.datasource.DataException;
import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.DatasourceServiceFactory;

public class TestRDBDatasourceService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String statement = 
			"CREATE TABLE ADMINISTRATOR.TEST ( COLUMN1 BIGINT  NOT NULL , COLUMN2 VARCHAR (10)  NOT NULL  , CONSTRAINT CC1259163587812 PRIMARY KEY ( COLUMN1)  )";
		
//		String statement =
//			"DROP TABLE ADMINISTRATOR.TEST";
		
		
		DatasourceService service = DatasourceServiceFactory.getInstance("RDB").getDatasourceProvider();
		try {
			service.sendStatement(statement);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
