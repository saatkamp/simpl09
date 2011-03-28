package org.apache.ode.simpl.ea.util;

import org.apache.log4j.Logger;
import org.simpl.resource.management.client.DataSource;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class DeploymentInfos {
	static Logger logger = Logger.getLogger(DeploymentInfos.class);
	
	/**
	 * Returns the data source of an activity from resource management and/or
	 * the deployment descriptor.
	 * 
	 * @param activityName of the activity.
	 * @param dataSourceName of the data source.
	 * @return The data source with the given name of the specified activity.
	 */
	public static DataSource getActivityDataSource(String activityName,
			String dataSourceName) {
		DataSource data = null;

		if (dataSourceName.contains("rm:")) {
			// Query the data source from resource management (on demand)
			data = DeploymentUtils.getInstance().getResourceManagementDataSourceByName(
					dataSourceName);
		} else {
			// Query the data source from the deployment-descriptor
      data = DeploymentUtils.getInstance().getDataSourceByName(dataSourceName);      
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Name of ds: " + data.getName());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Address of ds: " + data.getAddress());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Type of ds: " + data.getType());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Subtype of ds: " + data.getSubType());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("UserName of ds: "
					+ data.getAuthentication().getUser());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Password of ds: "
					+ data.getAuthentication().getPassword());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Format of ds: " + data.getConnector().getConverterDataFormat().getName());
		}

		return data;
	}
}