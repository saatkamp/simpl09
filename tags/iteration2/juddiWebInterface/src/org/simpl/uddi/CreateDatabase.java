package org.simpl.uddi;


import java.io.FileNotFoundException;

import org.simpl.uddi.UddiWebConfig;
import org.simpl.uddi.client.IUddiConfig;
import org.simpl.uddi.client.UddiBusiness;
import org.simpl.uddi.client.UddiDataWriter;

public class CreateDatabase implements IUddiConfig {
	
	public static void create() {
		UddiBusiness business = new UddiBusiness();
		business.addName("SimplBusiness", "ger");
		business.addDescription("Business fuer SIMPL Datasources", "ger");
		business.setKey(KEYPREFIX + "simpl");
		UddiWebConfig webconfig = null;
		
		try {
			webconfig = UddiWebConfig.getInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UddiDataWriter dataWriter = UddiDataWriter.getInstance(webconfig.getAddress(),webconfig.getUsername(),webconfig.getPassword());
		
		dataWriter.writeBusiness(business);

		


	}
}
