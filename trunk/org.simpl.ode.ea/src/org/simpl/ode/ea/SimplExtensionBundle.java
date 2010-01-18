package org.simpl.ode.ea;

import org.apache.ode.bpel.rtrep.common.extension.AbstractExtensionBundle;

public class SimplExtensionBundle extends AbstractExtensionBundle {

	public static final String NAMESPACE = "http://org.simpl.ode.ea";
	
	@Override
	public String getNamespaceURI() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}

	@Override
	public void registerExtensionActivities() {
		// TODO Auto-generated method stub
		
		super.registerExtensionOperation("callActivity", CallActivity.class);
		super.registerExtensionOperation("createActivity", CreateActivity.class);
		super.registerExtensionOperation("deleteActivity", DeleteActivity.class);
		super.registerExtensionOperation("dropActivity", DropActivity.class);
		super.registerExtensionOperation("insertActivity", InsertActivity.class);
		super.registerExtensionOperation("queryActivity", QueryActivity.class);
		super.registerExtensionOperation("retrieveDataActivity", RetrieveDataActivity.class);
		super.registerExtensionOperation("updateActivity", UpdateActivity.class);
		
	}

}
