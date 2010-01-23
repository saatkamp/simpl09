package org.apache.ode.dao.simpl;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class ActivityRecoverySDO extends AbstractSDO {

	@Override
	public DataObject createDataObject(Long id) {
		DataObject object = DataFactory.INSTANCE.create(ActivityRecoverySDO.class);
		object.setLong("id", id);
		
		return object;
	}

}