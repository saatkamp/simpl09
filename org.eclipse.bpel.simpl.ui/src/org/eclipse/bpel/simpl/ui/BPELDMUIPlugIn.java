/**
 * <b>Purpose:</b> The activator class controls the plug-in life cycle.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: BPELDMUIPlugIn.java 1678 2010-10-12 16:08:13Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.bpel.simpl.model.ModelPackage;
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

public class BPELDMUIPlugIn extends AbstractUIPlugin {

	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "org.eclipse.bpel.simpl.ui";

	/** The INSTANCE. */
	public static BPELDMUIPlugIn INSTANCE;

	/**
	 * The constructor.
	 */
	public BPELDMUIPlugIn() {
		super();
		INSTANCE = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
		BPELUtil.registerAdapterFactory(ModelPackage.eINSTANCE,
				new DataManagementUIAdapterFactory());

		Application.getInstance().initApplication();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		INSTANCE = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static BPELDMUIPlugIn getDefault() {
		return INSTANCE;
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
		for (int i = 0; i < fields.length; i++) {
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
			getLog().log(
					new Status(IStatus.ERROR, PLUGIN_ID, e
							.getLocalizedMessage()));
		}

	}

	/**
	 * Gets the image descriptor.
	 * 
	 * @param key
	 *            the key
	 * @return the image descriptor
	 */
	public ImageDescriptor getImageDescriptor(String key) {
		return getImageRegistry().getDescriptor(key);
	}

	/**
	 * Gets the image.
	 * 
	 * @param id
	 *            the id
	 * @return the image
	 */
	public Image getImage(String id) {
		return getImageRegistry().get(id);
	}

	/**
	 * Utility methods for logging exceptions.
	 * 
	 * @param e
	 *            the e
	 * @param severity
	 *            the severity
	 */
	public static void log(Throwable e, int severity) {
		IStatus status = null;
		if (e instanceof CoreException) {
			status = ((CoreException) e).getStatus();
		} else {
			String m = e.getMessage();
			status = new Status(severity, PLUGIN_ID, 0,
					m == null ? "<no message>" : m, e); //$NON-NLS-1$
		}
		//System.out.println(e.getClass().getName() + ": " + status);
		INSTANCE.getLog().log(status);
	}

	/**
	 * Log.
	 * 
	 * @param throwable
	 *            the throwable
	 */
	public static void log(Throwable throwable) {
		log(throwable, IStatus.ERROR);
	}

}
