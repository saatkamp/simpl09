package org.apache.ode.simpl.ea.sdo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import commonj.sdo.helper.XSDHelper;

public class XsdTypeLoader {
	
	private static final String SIMPL_SDO_XSD = System.getProperty("user.dir")
		 	+ "/webapps/ode/WEB-INF/conf/simpl-activity-sdo.xsd";

	public static void defineSIMPLTypes() {

		FileInputStream file;
		try {
			file = new FileInputStream(new File(SIMPL_SDO_XSD));
			InputStream is = file;

			if (is != null){
				XSDHelper.INSTANCE.define(is, null);

				is.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
