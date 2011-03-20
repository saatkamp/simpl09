package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ActivityFailureEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.SIMPLCore;
import org.simpl.core.connector.Connector;
import org.simpl.resource.management.client.DataSource;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

public class IssueCommandActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {

//		ScopeEvent DMStarted = new DMStarted();
//		context.getInternalInstance().sendEvent(DMStarted);

		// Load all attribute values from the activity.
		loadSIMPLAttributes(context, element);

		DataSource ds = getDataSource(getActivityName(), getDsAddress());

		Connector<DataObject, DataObject> datasourceService = SIMPLCore.getInstance()
				.dataSourceService();
		
		try {
			this.successfulExecution = datasourceService.executeStatement(ds,
					getDsStatement(context));

			if (!this.successfulExecution) {
				ActivityFailureEvent event = new ActivityFailureEvent();
				event.setActivityName(context.getActivityName());
				event.setActivityId(context.getOActivity().getId());
				event.setActivityType("IssueCommandActivity");
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
			event.setActivityType("IssueCommandActivity");
			event.setScopeName(context.getOActivity().getParent().name);
			event.setScopeId(0L);
			event.setScopeDeclerationId(context.getOActivity().getParent().getId());
			
			context.getInternalInstance().sendEvent(event);
		}

	}

}
