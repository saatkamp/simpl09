package org.simpl.rrs;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.simpl.rrs.webservices.EPR;
import org.simpl.rrs.webservices.ReferenceParameters;
import org.simpl.rrs.webservices.ReferenceProperties;

public class Test {
	
	private static EPR CreateEPR(String uri,
			String adapterType, String referenceName, String statement, String user, String password) {

		EPR epr = new EPR();
		ReferenceParameters param = new ReferenceParameters();
		ReferenceProperties prop = new ReferenceProperties();
		param.setDsAddress(uri);
		param.setUserName(user);
		param.setPassword(password);
		prop.setResolutionSystem(adapterType);
		param.setReferenceName(referenceName);
		param.setStatement(statement);
		
		epr.setReferenceParameters(param);
		epr.setReferenceProperties(prop);

		return epr;

	}
	
	public static LinkedHashMap<String, String> EPRtoHM(EPR epr) {
		LinkedHashMap<String, String> HM = new LinkedHashMap<String, String>();

		HM.put("referenceName",
				epr.getReferenceParameters().getReferenceName() == null ? ""
						: epr.getReferenceParameters().getReferenceName());
		HM.put("address", epr.getAddress() == null ? "" : epr.getAddress().toString());
		HM.put("adapterType", epr.getReferenceProperties()
				.getResolutionSystem() == null ? "" : epr
				.getReferenceProperties().getResolutionSystem());
		HM.put("statement",
				epr.getReferenceParameters().getStatement() == null ? "" : epr
						.getReferenceParameters().getStatement());
		HM.put("dsAddress",
				epr.getReferenceParameters().getDsAddress() == null ? "" : epr
						.getReferenceParameters().getDsAddress());
		HM.put("userName",
				epr.getReferenceParameters().getUserName() == null ? "" : epr
						.getReferenceParameters().getUserName());
		HM.put("password",
				epr.getReferenceParameters().getPassword() == null ? "" : epr
						.getReferenceParameters().getPassword());
		HM.put("portType", epr.getPortType() == null ? "" : epr.getPortType()
				.toString());
		if (epr.getServiceName() != null) {
			HM.put("serviceName", epr.getServiceName().getQName() == null ? ""
					: epr.getServiceName().getQName().toString());
			HM.put("portName", epr.getServiceName().getPortName() == null ? ""
					: epr.getServiceName().getPortName().toString());
		} else {
			HM.put("serviceName", "");
			HM.put("portName", "");
		}
		HM.put("policy", epr.getPolicy() == null ? "" : epr.getPolicy());

		return HM;
	}
	
	public static void main(String argv[]) throws SQLException {

		
		
		
		//try {
		//	System.out.println(RRS.getInstance().managementService().Insert("http://localhost:MyDB/", "RDB:Derby:MySQL", "test", "SELECT * FROM tabelle"));
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	
		EPR EPR1 = CreateEPR("http://localhost:MyDB2/", "RDB:Derby:MySQL", "test2", "SELECT * FROM tabelle", "test", "test");
		EPR EPR2 = CreateEPR("http://localhost:MyDB/", "RDB:Derby:MySQL", "test", "SELECT * FROM tabelle", "test", "test");
		EPR EPR3 = CreateEPR("localhost:3306/test", "RDB:MySQL:SQL", "test2", "SELECT * FROM test", "test", "test");
		@SuppressWarnings("unused")
		EPR EPR4 = CreateEPR("C:/eclipse/workspace/org.simpl.rrs/rrsDB", "RDB:Derby:MySQL", "test2", "SELECT * FROM ReferenceTable", "test", "test");
		
//		RRS.getInstance().metadataService().getAllEPR();
//		RRS.getInstance().managementService().Insert(EPR1);
//		RRS.getInstance().managementService().Insert(EPR2);
//		RRS.getInstance().managementService().Update(EPR3);
//		System.out.println(RRS.getInstance().metadataService().getAllEPR());
				
		System.out.println(RRS.getInstance().retrievalService().get(EPR3));

//		RRS.getInstance().metadataService().getEPR("test2");
		
		}
}

