package org.simpl.rrs.metadata;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.simpl.rrs.EPR;

public class MetadataServiceImpl implements MetadataService {

	private static final String DATABASE_NAME = "rrsDB";
	private static final String Reference_TABLE_NAME = "ReferenceTable";

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

	@Override
	public EPR[] getAllEPR() {

		List<EPR> allEPRs = new ArrayList<EPR>();
		ResultSet rs = null;

		Connection conn = getConnection();

		try {
			Statement statm = conn.createStatement();
			rs = statm.executeQuery("SELECT * FROM " + Reference_TABLE_NAME);
			statm.close();
			conn.close();
			
			while (rs.next()) {
				EPR rsEPR = new EPR();
				rsEPR.setAddress(rs.getString(1));
				rsEPR.setAdapterType(rs.getString(2));
				rsEPR.setReferenceName(rs.getString(3));
				rsEPR.setStatement(rs.getString(4));
				// parameter füllen
				allEPRs.add(rsEPR);

				HashMap<String, String> EPR = new HashMap<String, String>();
				EPR.put("Address", rs.getString(1));
				EPR.put("adapterType", rs.getString(2));
				EPR.put("referenceName", rs.getString(3));
				EPR.put("Statement", rs.getString(4));
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allEPRs.toArray(new EPR[allEPRs.size()]);
	}

	@Override
	public File getEPR(String name) {

		Connection conn = getConnection();
		ResultSet rs = null;
		try {
			Statement statm = conn.createStatement();
			System.out.println("SELECT * FROM " + Reference_TABLE_NAME
					+ " WHERE REFERENCENAME = '" + name + "'");
			rs = statm.executeQuery("SELECT * FROM " + Reference_TABLE_NAME
					+ " WHERE REFERENCENAME = '" + name + "'");
			statm.close();
			conn.close();

			HashMap<String, String> EPR = new HashMap<String, String>();

			while (rs.next()) {

				EPR.put("Address", rs.getString(1));
				EPR.put("adapterType", rs.getString(2));
				EPR.put("referenceName", rs.getString(3));
				EPR.put("Statement", rs.getString(4));

			}
			System.out.println(EPR.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
