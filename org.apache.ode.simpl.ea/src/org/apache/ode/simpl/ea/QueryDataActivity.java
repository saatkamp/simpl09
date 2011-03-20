package org.apache.ode.simpl.ea;

import java.util.Map;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ActivityFailureEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.apache.ode.simpl.ea.util.StatementUtils;
import org.simpl.core.SIMPLCore;
import org.simpl.core.connector.Connector;
import org.simpl.resource.management.client.DataSource;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

public class QueryDataActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {

//		ScopeEvent DMStarted = new DMStarted();
//		context.getInternalInstance().sendEvent(DMStarted);

		// Load all attribute values from the activity.
		loadSIMPLAttributes(context, element);

		// Load all specific attribute values from the QueryActivity.
		String queryTarget = element.getAttribute("queryTarget").toString();
		
		if (queryTarget.contains("[") || queryTarget.contains("#")){
			//queryTarget enthält eine BPEL-Variable als Referenz
			Map<String, Variable> variables = null;
			try {
				variables = context.getVisibleVariables();
			} catch (FaultException e) {
				ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
				event.setActivityName(context.getActivityName());
				event.setActivityId(context.getOActivity().getId());
				event.setActivityType("QueryDataActivity");
				event.setScopeName(context.getOActivity().getParent().name);
				event.setScopeId(0L);
				event.setScopeDeclerationId(context.getOActivity().getParent().getId());
				
				context.getInternalInstance().sendEvent(event);
			}
			
			queryTarget = String.valueOf(StatementUtils.resolveVariable(context, variables, queryTarget));
		}
		
		DataSource ds = getDataSource(getActivityName(), getDsAddress());

		Connector<DataObject, DataObject> datasourceService = SIMPLCore.getInstance()
				.dataSourceService();

		try {
		  if (!queryTarget.equals("")) {
  			this.successfulExecution = datasourceService.depositData(
  					ds, getDsStatement(context), queryTarget);
		  }
		  
			if (!this.successfulExecution) {
				ActivityFailureEvent event = new ActivityFailureEvent();
				event.setActivityName(context.getActivityName());
				event.setActivityId(context.getOActivity().getId());
				event.setActivityType("QueryDataActivity");
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
			event.setActivityType("QueryDataActivity");
			event.setScopeName(context.getOActivity().getParent().name);
			event.setScopeId(0L);
			event.setScopeDeclerationId(context.getOActivity().getParent().getId());
			
			context.getInternalInstance().sendEvent(event);
		}
	}
}
