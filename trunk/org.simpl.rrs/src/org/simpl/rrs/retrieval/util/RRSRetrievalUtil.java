package org.simpl.rrs.retrieval.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.simpl.rrs.webservices.Column;
import org.simpl.rrs.webservices.RDBSet;
import org.simpl.rrs.webservices.Table;

import commonj.sdo.DataObject;

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
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static RDBSet getRDBDataFormatObject(DataObject dataObject) {
		RDBSet data = new RDBSet();

		List<DataObject> tables = dataObject.getList("table");
		List<DataObject> columns = null;

		for (DataObject table : tables) {
			columns = table.getList("column");
			Table tab = new Table();

			for (DataObject column : columns) {
				Column col = new Column();
				col.setName(column.getString("name"));
				col.setType(column.getString("type"));
				col.setValue(column.getString("value"));

				tab.addColumn(col);
			}
			tab.setName(table.getString("name"));
			tab.setSchema(table.getString("schema"));
			data.addTable(tab);
		}

		return data;
	}

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
