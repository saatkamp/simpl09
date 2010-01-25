package org.apache.ode.dao.simpl;

import java.sql.Date;

import commonj.sdo.DataObject;



public abstract class AbstractSDO {

	private DataObject dataObject = null;
	
	private Long id = null;
	
	public DataObject getSDO (Long id) {
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


	public abstract DataObject createDataObject(Long id);

}
