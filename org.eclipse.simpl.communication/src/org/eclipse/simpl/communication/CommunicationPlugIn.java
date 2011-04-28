package org.eclipse.simpl.communication;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class CommunicationPlugIn extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.simpl.communication";

	// The shared instance
	private static CommunicationPlugIn plugin;
	
	/**
	 * The constructor
	 */
	public CommunicationPlugIn() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		plugin.getPreferenceStore().setDefault("SIMPL_CORE_DSS_ADDRESS", "http://localhost:8080/ode/processes/SIMPLCoreService.SIMPLCoreServicePort?wsdl");
		plugin.getPreferenceStore().setDefault("SIMPL_CORE_AS_ADDRESS", "http://localhost:8080/ode/processes/SIMPLCoreAdministrationService.SIMPLCoreAdministrationServicePort?wsdl");
		plugin.getPreferenceStore().setDefault("SIMPL_RESOURCE_MANAGEMENT_ADDRESS", "http://localhost:8080/axis2/services/ResourceManagementService.ResourceManagementPort?wsdl");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static CommunicationPlugIn getDefault() {
		return plugin;
	}

}
