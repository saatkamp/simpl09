package org.apache.ode.dao.simpl.sdo;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class ActivityRecoverySDO extends AbstractSDO {

	@Override
	public DataObject createDataObject(Long id) {
		DataObject object = DataFactory.INSTANCE.create("http://www.example.org/simpl-dao", "ActivityRecoverySDO");
		object.setLong("id", id);
		
		return object;
	}

}
