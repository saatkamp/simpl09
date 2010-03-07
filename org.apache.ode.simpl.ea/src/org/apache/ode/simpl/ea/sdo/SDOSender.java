package org.apache.ode.simpl.ea.sdo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.ode.simpl.ea.DataManagementActivity;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;

public class SDOSender {

	private static SDOSender instance;

	public static SDOSender getInstance() {
		if (instance == null) {
			instance = new SDOSender();
		}
		return instance;
	}

	public void sendSDO(DataObject dataObject, String dataObjectName) {
		OutputStream stream;

		try {
			stream = new FileOutputStream("C:\\"
					+ dataObjectName + "_" +
					+ dataObject.getLong("ProcessInstance/processID") + ".xml");
			try {
				XMLDocument doc = XMLHelper.INSTANCE.createDocument(dataObject, DataManagementActivity
						.getSimplSdoNs(), dataObjectName);
				doc.setEncoding("UTF-8");
				
				XMLHelper.INSTANCE.save(doc, stream, null);
				
//				XMLHelper.INSTANCE.save(dataObject, DataManagementActivity
//						.getSimplSdoNs(), dataObjectType, stream);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
