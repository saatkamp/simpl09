package org.apache.ode.simpl.ea.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.services.datasource.DataSource;

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

	public DeploymentInfos(String path, String process) {
		DeploymentUtils.getInstance().init(path, process);
		// Set up a simple configuration that logs on the console.
		PropertyConfigurator.configure("log4j.properties");
	}

	public DataSource getActivityDataSource(String activityName,
			String dataSourceName) {
		DataSource data = null;
		// If we have a activity-policy mapping, we have a
		// late binding data source with policy.
		// If no such mapping exists, we use the logical name
		// which is specified in the activity address field
		if (DeploymentUtils.getInstance().getDataSourceOfActivity(activityName) != null) {
			data = DeploymentUtils.getInstance().getDataSourceOfActivity(
					activityName);
			DataSource backupDs = DeploymentUtils.getInstance()
					.getDataSourceByName(dataSourceName);
			if (backupDs != null) {
				data = DeploymentUtils.getInstance().merge(data, backupDs);
			}
		} else {
			data = DeploymentUtils.getInstance().getDataSourceByName(
					dataSourceName);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Queried DataSource object: " + data);
		}

		return data;
	}
}
