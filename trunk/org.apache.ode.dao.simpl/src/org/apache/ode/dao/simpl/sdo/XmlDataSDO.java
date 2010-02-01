package org.apache.ode.dao.simpl.sdo;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class XmlDataSDO extends AbstractSDO {

	@Override
	public DataObject createDataObject(Long id) {
		DataObject object = DataFactory.INSTANCE.create("http://www.example.org/simpl-dao","XmlDataSDO");
		object.set("id", id);
		
		return object;
	}

}
