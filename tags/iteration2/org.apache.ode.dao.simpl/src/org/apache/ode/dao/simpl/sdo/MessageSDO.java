package org.apache.ode.dao.simpl.sdo;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class MessageSDO extends AbstractSDO {

	@Override
	public DataObject createDataObject(Long id) {
		DataObject object = DataFactory.INSTANCE.create("http://www.example.org/simpl-dao", "MessageSDO");
		object.setLong("id", id);
		
		return object;
	}

}
