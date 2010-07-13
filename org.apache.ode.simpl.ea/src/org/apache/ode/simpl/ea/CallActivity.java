package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ActivityFailureEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.DataSourceService;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

public class CallActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {

		// ScopeEvent DMStarted = new DMStarted();
		// context.getInternalInstance().sendEvent(DMStarted);

		// Load all attribute values from the activity.
		loadSIMPLAttributes(context, element);

		DataSource ds = getDataSource(getActivityName(), getDsAddress());

		DataSourceService<DataObject, DataObject> datasourceService = SIMPLCore
				.getInstance().dataSourceService();

		try {
			this.successfullExecution = datasourceService.executeStatement(ds,
					getDsStatement(context));

			// if (this.successfullExecution) {
			// ScopeEvent DMEnd = new DMEnd();
			// context.getInternalInstance().sendEvent(DMEnd);
			// } else {
			// // Hier werden alle vorhandenen "wichtigen" Daten dem Event
			// // übergeben
			// ScopeEvent DMFailure = new DMFailure(getActivityName(),
			// getDsAddress(), getDsStatement(context), "UNKNOWN");
			// context.getInternalInstance().sendEvent(DMFailure);
			// }

		} catch (Exception e) {
			ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
			event.setActivityName(context.getActivityName());
			event.setActivityId(context.getOActivity().getId());
			event.setActivityType("CallActivity");
			event.setScopeName(context.getOActivity().getParent().name);
			event.setScopeId(0L);
			event.setScopeDeclerationId(context.getOActivity().getParent().getId());
			
			context.getInternalInstance().sendEvent(event);
		}

	}
}
