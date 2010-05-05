package org.simpl.rrs;

import java.sql.SQLException;

import org.simpl.rrs.webservices.client.EPR;
import org.simpl.rrs.webservices.client.ReferenceParameters;
import org.simpl.rrs.webservices.client.ReferenceProperties;

public class Test {
	
	private static EPR CreateEPR(String uri,
			String adapterType, String referenceName, String statement) {

		EPR epr = new EPR();
		ReferenceParameters param = new ReferenceParameters();
		ReferenceProperties prop = new ReferenceProperties();
		param.setDsAddress(uri);
		prop.setResolutionSystem(adapterType);
		param.setReferenceName(referenceName);
		param.setStatement(statement);
		
		epr.setReferenceParameters(param);
		epr.setReferenceProperties(prop);

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

