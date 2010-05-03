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
	
	private static EPR CreateEPR(String uri,
			String adapterType, String referenceName, String statement) {

		EPR epr = new EPR();
		epr.setAddress(uri);
		epr.setAdapterType(adapterType);
		epr.setReferenceName(referenceName);
		epr.setStatement(statement);

		return epr;

	}
	
	public static void main(String argv[]) throws SQLException {

		
		
		
		//try {
		//	System.out.println(RRS.getInstance().managementService().Insert("http://localhost:MyDB/", "RDB:Derby:MySQL", "test", "SELECT * FROM tabelle"));
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	
		EPR EPR1 = CreateEPR("http://localhost:MyDB2/", "RDB:Derby:MySQL", "test2", "SELECT * FROM tabelle");
		EPR EPR2 = CreateEPR("http://localhost:MyDB/", "RDB:Derby:MySQL", "test", "SELECT * FROM tabelle");
		EPR EPR3 = CreateEPR("http://localhost:MyDB22/", "RDB:Derby:MySQL", "test2", "SELECT * FROM tabelle");
		EPR EPR4 = CreateEPR("C:/eclipse/workspace/org.simpl.rrs/rrsDB", "RDB:Derby:MySQL", "test2", "SELECT * FROM ReferenceTable");
		

		RRS.getInstance().managementService().Insert(EPR1);
		RRS.getInstance().managementService().Insert(EPR2);
		RRS.getInstance().managementService().Update(EPR3);

		
		
		//RRS.getInstance().retrievalService().get(EPR4);
		RRS.getInstance().metadataService().getAllEPR();
		RRS.getInstance().metadataService().getEPR("test2");

		
		}
}

