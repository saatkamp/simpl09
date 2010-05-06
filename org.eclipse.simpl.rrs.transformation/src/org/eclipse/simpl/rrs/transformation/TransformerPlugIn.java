package org.eclipse.simpl.rrs.transformation;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class TransformerPlugIn extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.simpl.rrs.transformation";

	// The shared instance
	private static TransformerPlugIn plugin;
	
	public final String BPEL_EXTENSION = ".bpel";
	
	/**
	 * The constructor
	 */
	public TransformerPlugIn() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		plugin.getPreferenceStore().setDefault("TRANSFORMER_ADDRESS", "http://localhost:8080/axis2/services/TransformationService?wsdl");
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
	public static TransformerPlugIn getDefault() {
		return plugin;
	}

}
