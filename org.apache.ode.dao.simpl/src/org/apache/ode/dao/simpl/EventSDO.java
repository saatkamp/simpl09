package org.apache.ode.dao.simpl;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class EventSDO extends AbstractSDO {

	@Override
	public DataObject createDataObject(Long id) {
		DataObject object = DataFactory.INSTANCE.create("http://www.example.org/simpl-dao", "EventSDO");
		object.setLong("id", id);
		
		return object;
	}

}
