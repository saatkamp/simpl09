package org.simpl.ode.ea;

import org.apache.ode.bpel.rtrep.common.extension.AbstractExtensionBundle;

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
		
	}

}
