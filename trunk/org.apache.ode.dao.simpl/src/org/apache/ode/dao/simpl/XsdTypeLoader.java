package org.apache.ode.dao.simpl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import commonj.sdo.helper.XSDHelper;

public class XsdTypeLoader {
	
	private static Logger logger = Logger.getLogger(XsdTypeLoader.class);
	
	private static final String SIMPL_DAO_XSD = "simpl-dao.xsd";
	
	public static void defineSIMPLTypes() {
		PropertyConfigurator.configure("log4j.properties");

		InputStream is = XsdTypeLoader.class.getResourceAsStream(SIMPL_DAO_XSD);
		//logger.info("SIMPL XSD FILE: " + ClassLoader.getSystemResource("schema/"+SIMPL_DAO_XSD).getPath());
		logger.info("SIMPL XSD FILE: " + XsdTypeLoader.class.getResource("/"+SIMPL_DAO_XSD).getPath());
//		if (is == null) {
//			System.out.println("InputStream is null");
//		} else {
//			System.out.println("Obtained Input Stream from resoruce");
//		}
		XSDHelper.INSTANCE.define(is, null);
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("InputStream Exception", e);
		}
	}
}
