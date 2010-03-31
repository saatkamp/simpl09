package org.apache.ode.dao.simpl;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class ScopeSDO extends AbstractSDO {

	@Override
	public DataObject createDataObject(Long id) {
		DataObject object = DataFactory.INSTANCE.create("http://www.example.org/simpl-dao","ScopeSDO");
		object.set("id", id);
		
		return object;
	}

}