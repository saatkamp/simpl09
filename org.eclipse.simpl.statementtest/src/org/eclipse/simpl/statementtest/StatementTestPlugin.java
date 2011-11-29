package org.eclipse.simpl.statementtest;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * <b>Purpose:</b>Plug-in for statement tests.<br>
 * <b>Description:</b>The plug-in comes with a eclipse wizard that guides through the
 * creation of a statement test and a eclipse view to present the test results.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: StatementTestPlugin.java 51 2010-06-29 18:21:35Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StatementTestPlugin extends AbstractUIPlugin {
  // The plug-in ID
  public static final String PLUGIN_ID = "org.eclipse.simpl.statementtest";

  // The plug-in instance
  public static StatementTestPlugin INSTANCE;

  /**
   * The constructor.
   */
  public StatementTestPlugin() {
    super();
    StatementTestPlugin.INSTANCE = this;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    StatementTestPlugin.INSTANCE = this;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    StatementTestPlugin.INSTANCE = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static StatementTestPlugin getDefault() {
    return StatementTestPlugin.INSTANCE;
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in relative path
   * 
   * @param path
   *          the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String path) {
    return AbstractUIPlugin
        .imageDescriptorFromPlugin(StatementTestPlugin.PLUGIN_ID, path);
  }
}