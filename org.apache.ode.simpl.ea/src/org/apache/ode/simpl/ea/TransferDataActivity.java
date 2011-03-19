package org.apache.ode.simpl.ea;

import java.util.Map;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ActivityFailureEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.apache.ode.simpl.ea.util.StatementUtils;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.resource.management.client.DataSource;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

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
public class TransferDataActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {

//		ScopeEvent DMStarted = new DMStarted();
//		context.getInternalInstance().sendEvent(DMStarted);

		// Load all attribute values from the activity.
		loadSIMPLAttributes(context, element);

		// Load all specific attribute values from the TransferActivity.
		String targetDsAddress = element.getAttribute("targetDsAddress");
		String targetDsContainer = element.getAttribute("targetDsContainer");

		if (targetDsContainer.contains("[") || targetDsContainer.contains("#")){
			//targetDsContainer enth�lt eine BPEL-Variable als Referenz
			Map<String, Variable> variables = null;
			try {
				variables = context.getVisibleVariables();
			} catch (FaultException e) {
				ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
				event.setActivityName(context.getActivityName());
				event.setActivityId(context.getOActivity().getId());
				event.setActivityType("TransferDataActivity");
				event.setScopeName(context.getOActivity().getParent().name);
				event.setScopeId(0L);
				event.setScopeDeclerationId(context.getOActivity().getParent().getId());
				
				context.getInternalInstance().sendEvent(event);
			}
			
			targetDsContainer = String.valueOf(StatementUtils.resolveVariable(context, variables, targetDsContainer));
		}

		DataSource dsFrom = getDataSource(getActivityName(), getDsAddress());
		DataSource dsTo = getDataSource(getActivityName(), targetDsAddress);
		
		DataSourceService<DataObject, DataObject> datasourceService = SIMPLCore
				.getInstance().dataSourceService();

		try {
  	    if (!targetDsAddress.equals("") || !targetDsContainer.equals("")) {
  			DataObject dataObject = datasourceService.retrieveData(dsFrom,
  					getDsStatement(context));
  
  			if (dataObject == null) {
  			  this.successfulExecution = false;
  			} else {
  				this.successfulExecution = datasourceService.writeData(dsTo, dataObject, targetDsContainer);
  			}
  	  } 
  	    
      if (!this.successfulExecution) {
        ActivityFailureEvent event = new ActivityFailureEvent();
        event.setActivityName(context.getActivityName());
        event.setActivityId(context.getOActivity().getId());
        event.setActivityType("TransferDataActivity");
        event.setScopeName(context.getOActivity().getParent().name);
        event.setScopeId(0L);
        event.setScopeDeclerationId(context.getOActivity().getParent()
            .getId());
        context.getInternalInstance().sendEvent(event);
        context.completeWithFault(new Throwable("SIMPL Exception"));
      }
		} catch (Exception e) {
			ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
			event.setActivityName(context.getActivityName());
			event.setActivityId(context.getOActivity().getId());
			event.setActivityType("TransferDataActivity");
			event.setScopeName(context.getOActivity().getParent().name);
			event.setScopeId(0L);
			event.setScopeDeclerationId(context.getOActivity().getParent().getId());
			
			context.getInternalInstance().sendEvent(event);
		}
	}
}