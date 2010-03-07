package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.rtrep.common.extension.AbstractExtensionBundle;
import org.apache.ode.simpl.ea.sdo.XsdTypeLoader;

public class SimplExtensionBundle extends AbstractExtensionBundle {

	public static final String NAMESPACE = "http://www.example.org/simpl";
	
	@Override
	public String getNamespaceURI() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}

	@Override
	public void registerExtensionActivities() {
		// TODO Auto-generated method stub
		super.registerExtensionOperation("queryActivity", QueryActivity.class);
		super.registerExtensionOperation("callActivity", CallActivity.class);
		super.registerExtensionOperation("deleteActivity", DeleteActivity.class);
		super.registerExtensionOperation("dropActivity", DropActivity.class);
		super.registerExtensionOperation("insertActivity", InsertActivity.class);
		super.registerExtensionOperation("createActivity", CreateActivity.class);
		super.registerExtensionOperation("updateActivity", UpdateActivity.class);
		
		//Initiales Definieren der SIMPL-DM-SDO-Types
		XsdTypeLoader.defineSIMPLTypes();
	}

}
