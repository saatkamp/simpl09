package org.simpl.rrs.retrieval.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.simpl.rrs.webservices.Column;
import org.simpl.rrs.webservices.RDBSet;
import org.simpl.rrs.webservices.Table;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class RRSRetrievalUtil {

	/**
	 * @param resultSet
	 * @param metaData
	 * @return
	 */
	public static RDBSet getRDBDataFormatObject(ResultSet resultSet) {
		
		RDBSet result = new RDBSet();
		Table tableObject = null;
		Column columnObject = null;

		try {
			while (resultSet.next()) {
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

				for (int i = 1; i < resultSetMetaData.getColumnCount() + 1; i++) {
					// add table
					if (tableObject == null) {
						tableObject = new Table();
						tableObject.setName(resultSetMetaData.getTableName(i));
						tableObject.setSchema(resultSetMetaData
								.getSchemaName(i));
					}

					// add columns
					columnObject = new Column();
					columnObject.setName(resultSetMetaData.getColumnName(i));

					columnObject
							.setType(resultSetMetaData.getColumnTypeName(i));
					columnObject.setValue(resultSet.getObject(resultSetMetaData
							.getColumnName(i)));

					tableObject.addColumn(columnObject);
				}
				
				result.addTable(tableObject);
				tableObject = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
