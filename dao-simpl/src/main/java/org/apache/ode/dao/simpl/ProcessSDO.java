package org.apache.ode.dao.simpl;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class ProcessSDO extends AbstractSDO {

	@Override
	public DataObject createDataObject(Long id) {
		DataObject object = DataFactory.INSTANCE.create(ProcessSDO.class);
		object.setLong("id", id);
		
		return object;
	}

}
