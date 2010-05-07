package org.apache.ode.simpl.ea;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.ode.bpel.rtrep.common.extension.AbstractExtensionBundle;

public class SimplExtensionBundle extends AbstractExtensionBundle {

	static Logger logger = Logger.getLogger(SimplExtensionBundle.class);
	
	public static final String NAMESPACE = "http://www.example.org/simpl";
	
	@Override
	public String getNamespaceURI() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}

	@Override
	public void registerExtensionActivities() {
		// Set up a simple configuration that logs on the console.
	    PropertyConfigurator.configure("log4j.properties");
		
		// TODO Auto-generated method stub
		super.registerExtensionOperation("queryActivity", QueryActivity.class);
		super.registerExtensionOperation("callActivity", CallActivity.class);
		super.registerExtensionOperation("deleteActivity", DeleteActivity.class);
		super.registerExtensionOperation("dropActivity", DropActivity.class);
		super.registerExtensionOperation("insertActivity", InsertActivity.class);
		super.registerExtensionOperation("createActivity", CreateActivity.class);
		super.registerExtensionOperation("updateActivity", UpdateActivity.class);
		super.registerExtensionOperation("retrieveDataActivity", RetrieveDataActivity.class);
		
		logger.debug("SIMPL Extension Bundle loaded successfully.");
	}

}
