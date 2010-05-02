package org.simpl.rrs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Test {
	
	private static LinkedHashMap<String, String> CreateEPR(String uri,
			String adapterType, String referenceName, String statement) {

		LinkedHashMap<String, String> EPR = new LinkedHashMap<String, String>();
		EPR.put("Address", uri);
		EPR.put("adapterType", adapterType);
		EPR.put("referenceName", referenceName);
		EPR.put("Statement", statement);

		return EPR;

	}
	
	public static void main(String argv[]) {

		
		
		
		//try {
		//	System.out.println(RRS.getInstance().managementService().Insert("http://localhost:MyDB/", "RDB:Derby:MySQL", "test", "SELECT * FROM tabelle"));
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	
		LinkedHashMap<String, String> EPR1 = CreateEPR("http://localhost:MyDB2/", "RDB:Derby:MySQL", "test2", "SELECT * FROM tabelle");
		LinkedHashMap<String, String> EPR2 = CreateEPR("http://localhost:MyDB/", "RDB:Derby:MySQL", "test", "SELECT * FROM tabelle");
		LinkedHashMap<String, String> EPR3 = CreateEPR("http://localhost:MyDB22/", "RDB:Derby:MySQL", "test2", "SELECT * FROM tabelle");
		LinkedHashMap<String, String> EPR4 = CreateEPR("C:/eclipse/workspace/org.simpl.rrs/rrsDB", "RDB:Derby:MySQL", "test2", "SELECT * FROM ReferenceTable");
		
//		try {
//			System.out.println(Class.forName("org.apache.derby.jdbc.ClientDriver")
//			.newInstance());
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		RRS.getInstance().retrievalService().get(EPR4);
		RRS.getInstance().metadataService().getAllEPR();
		RRS.getInstance().metadataService().getEPR("test2");
//		try {
//			RRS.getInstance().managementService().Insert(EPR1);
//			RRS.getInstance().managementService().Insert(EPR2);
//			RRS.getInstance().managementService().Update(EPR3);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//RRSConfig conf = new RRSConfig();
		}
}

