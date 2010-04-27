package org.apache.ode.simpl.ea.util;

import org.simpl.core.services.datasource.DataSource;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
public class DeploymentInfos {

	public DeploymentInfos(String path, String process) {
		DeploymentUtils.getInstance().readDeploymentDescriptor(path, process);
	}
	
	public DataSource getActivityDataSource(String activityName){
		return DeploymentUtils.getInstance().getDataSourceOfActivity(activityName);
	}
}
