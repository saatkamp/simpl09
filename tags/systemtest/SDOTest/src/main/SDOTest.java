package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XMLHelper;
import commonj.sdo.helper.XSDHelper;

public class SDOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SDOTest test = new SDOTest();
		DataObject data = null;

		try {
			test.definePOTypes();
			data = test.createSDO();

			data.setLong("id", 123);
			data.setString("dateTime", "Jetzt");
			
			System.out.println("ID: " + data.getLong("id"));
			System.out.println("DateTime: " + data.getString("dateTime"));

			OutputStream stream;

			stream = new FileOutputStream("C:\\temporaryPoGenerated.xml");

			try {
				XMLHelper.INSTANCE.save(data,
						"http://www.example.org/simpl-dao", "abstractSDO",
						stream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out
					.println("Created file " + "C:\\temporaryPoGenerated.xml");

		} catch (Exception e) {
			System.out.println("Sorry an error occured " + e.toString());
			e.printStackTrace();
		}

	}

	public DataObject createSDO() {
		DataObject purchaseOrder = null;
		purchaseOrder = DataFactory.INSTANCE.create(
				"http://www.example.org/simpl-dao", "AbstractSDO");

		return purchaseOrder;
	}

	/**
	 * Defines purchase order types using
	 * {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#PO_XSD_RESOURCE}
	 * 
	 * @throws Exception
	 */
	private void definePOTypes() throws Exception {

		InputStream is = getClass().getResourceAsStream("simpl-dao.xsd");
		if (is == null) {
			System.out.println("InputStream is null");
		} else {
			System.out.println("Obtained Input Stream from resoruce");
		}
		XSDHelper.INSTANCE.define(is, null);
		is.close();
	}
}
