package org.simpl.rrs.webservices.helper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.jdom.Document;
import org.jdom.Element;

public class JDBCUtil {

	/*
	 * Create document using JDOM api
	 */
	public static Document toDocument(ResultSet rs) {
		Document doc = new Document();

		try {

			Element results = new Element("Results");
			doc.addContent(results);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();

			while (rs.next()) {
				Element row = new Element("Row");
				results.addContent(row);

				for (int i = 1; i <= colCount; i++) {
					String columnName = rsmd.getColumnName(i);
					Object value = rs.getObject(i);

					Element node = new Element(columnName);
					node.addContent(value.toString());
					row.addContent(node);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doc;
	}
}
