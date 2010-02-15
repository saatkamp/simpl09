package org.apache.ode.dao.simpl.sdo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;



public abstract class AbstractSDO {

	private DataObject dataObject = null;
	
	private Long id = null;
	
	public DataObject getSDO (Long id) {
		DataObject object = null;
		if ((id != null) && id.equals(this.id)) {
			object = this.dataObject;
		} else
		{
			 object = createDataObject(id);
			 this.dataObject = object;
			 this.id = id;
		}
		
		return object;
	}
	
	public void send() {
		OutputStream stream;
		String sdo = this.getClass().getSimpleName();

		try {
			stream = new FileOutputStream("C:\\"+sdo+id+".xml");
			try {
				XMLHelper.INSTANCE.save(dataObject,
						"http://www.example.org/simpl-dao", sdo,
						stream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		

	}

	


	public abstract DataObject createDataObject(Long id);

}
