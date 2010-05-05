package org.eclipse.simpl.rrs.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RRSUIPlugIn extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.references";

	// The shared instance
	private static RRSUIPlugIn plugin;
	
	/**
	 * The constructor
	 */
	public RRSUIPlugIn() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		//TODO: Noch anpassen.
		plugin.getPreferenceStore().setDefault("RRS_RET_ADDRESS", "http://localhost:8080/axis2/services/RRSRetrievalService.RRSRetrievalServicePort?wsdl");
		plugin.getPreferenceStore().setDefault("RRS_MG_ADDRESS", "http://localhost:8080/axis2/services/RRSManagementService.RRSManagementServicePort?wsdl");
		plugin.getPreferenceStore().setDefault("RRS_MD_ADDRESS", "http://localhost:8080/axis2/services/RRSMetaDataService.RRSMetaDataServicePort?wsdl");
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
	public static RRSUIPlugIn getDefault() {
		return plugin;
	}

}
