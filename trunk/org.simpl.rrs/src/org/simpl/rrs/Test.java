package org.simpl.rrs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Test {

	public static void main(String argv[]) {

		System.out.println("blub");
		
		//try {
		//	System.out.println(RRS.getInstance().managementService().Insert("http://localhost:MyDB/", "RDB:Derby:MySQL", "test", "SELECT * FROM tabelle"));
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		try {
			RRS.getInstance().managementService().Insert("http://localhost:MyDB2/", "RDB:Derby:MySQL", "test2", "SELECT * FROM tabelle");
			RRS.getInstance().managementService().Insert("http://localhost:MyDB/", "RDB:Derby:MySQL", "test", "SELECT * FROM tabelle");
			RRS.getInstance().managementService().Update("http://localhost:MyDB/", "RDB:Derby:MySQL", "test2", "SELECT * FROM tabelle", "updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//RRSConfig conf = new RRSConfig();
		}
}

