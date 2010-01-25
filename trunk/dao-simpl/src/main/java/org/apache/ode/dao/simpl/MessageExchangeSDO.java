package org.apache.ode.dao.simpl;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class MessageExchangeSDO {

private DataObject dataObject = null;
	
	private String id = null;
	
	public DataObject getSDO (String id) {
		DataObject object = null;
		if (id.equals(this.id)) {
			object = this.dataObject;
		} else
		{
			 object = createDataObject(id);
			 this.dataObject = object;
			 this.id = id;
		}
		
		return object;
	}

	
	public DataObject createDataObject(String id) {
		DataObject object = DataFactory.INSTANCE.create("http://www.example.org/simpl-dao", "MessageExchangeSDO");
		object.setString("id", id);
		
		return object;
	}

}
