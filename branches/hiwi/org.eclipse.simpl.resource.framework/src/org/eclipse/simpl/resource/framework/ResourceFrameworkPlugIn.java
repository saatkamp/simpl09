package org.eclipse.simpl.resource.framework;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ResourceFrameworkPlugIn extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.simpl.resource.framework";

	// The shared instance
	private static ResourceFrameworkPlugIn plugin;
	
	/**
	 * The constructor
	 */
	public ResourceFrameworkPlugIn() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		plugin.getPreferenceStore().setDefault("RESOURCE_FRAMEWORK_ADDRESS", "http://localhost:8080/ode/processes/ResourceFrameworkService.ResourceFrameworkPort?wsdl");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	public static ResourceFrameworkPlugIn getDefault() {
		return plugin;
	}

}
