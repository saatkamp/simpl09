package org.apache.ode.dao.simpl.sdo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import commonj.sdo.helper.XSDHelper;

public class XsdTypeLoader {

	private static Logger logger = Logger.getLogger(XsdTypeLoader.class);

	private static final String SIMPL_DAO_XSD = System.getProperty("user.dir")
			+ "/webapps/ode/WEB-INF/conf/simpl-dao.xsd";

	public static void defineSIMPLTypes() {
		PropertyConfigurator.configure("log4j.properties");

		FileInputStream file;
		try {
			file = new FileInputStream(new File(SIMPL_DAO_XSD));
			InputStream is = file;

			if (is == null){
				logger.error("InputStream is null");
			}else {
				XSDHelper.INSTANCE.define(is, null);

				is.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("SIMPL-XSD-File not found", e1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Exception while opening the stream", e);
		}
	}
}
