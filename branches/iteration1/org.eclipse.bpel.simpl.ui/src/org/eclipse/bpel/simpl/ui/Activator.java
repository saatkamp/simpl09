package org.eclipse.bpel.simpl.ui;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.ui.DataManagementUIConstants;
import org.eclipse.bpel.simpl.ui.factories.DataManagementUIAdapterFactory;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.bpel.simpl.ui";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		BPELUtil.registerAdapterFactory(ModelPackage.eINSTANCE, new DataManagementUIAdapterFactory());
		Application.getInstance().initApplication();
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
	public static Activator getDefault() {
		return plugin;
	}

	
	/**
	 * Initializes the table of images used in this plugin.
	 */
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		URL baseURL = getBundle().getEntry("/");

		// A little reflection magic ... so that we don't
		// have to add the createImageDescriptor every time
		// we add it to the IBPELUIConstants ..
		Field fields[] = DataManagementUIConstants.class.getFields();	
		for(int i=0; i < fields.length; i++) {
			Field f = fields[i];
			if (f.getType() != String.class) { 
				continue;
			}
			String name = f.getName();
			if (name.startsWith("ICON_")) {
				try {
					String value = (String) f.get(null);
					createImageDescriptor(value, baseURL);
				} catch (Exception e) {
					log(e);
				}
			}			
		}
	}

	private void createImageDescriptor(String id, URL baseURL) {
		URL url = null;
		try {
			url = new URL(baseURL, DataManagementUIConstants.ICON_PATH + id);
			ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
			getImageRegistry().put(id, descriptor);
		} catch (MalformedURLException e) {
			getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getLocalizedMessage()));
		}
		
	}

	public ImageDescriptor getImageDescriptor(String key) {
		return getImageRegistry().getDescriptor(key);
	}
	
	public Image getImage(String id) {
		return getImageRegistry().get(id);
	}

	/**
	 * Utility methods for logging exceptions.
	 */
	public static void log(Throwable e, int severity) {
		IStatus status = null;
		if (e instanceof CoreException) {
			status = ((CoreException)e).getStatus();
		} else {
			String m = e.getMessage();
			status = new Status(severity, PLUGIN_ID, 0, m==null? "<no message>" : m, e); //$NON-NLS-1$
		}
		System.out.println(e.getClass().getName()+": "+status);
		plugin.getLog().log(status);
	}
	
	public static void log(Throwable throwable) { 
		log(throwable, IStatus.ERROR); }

}
