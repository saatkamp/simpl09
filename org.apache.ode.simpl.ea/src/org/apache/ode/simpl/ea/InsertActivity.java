package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ScopeEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.simpl.events.DMEnd;
import org.apache.ode.simpl.events.DMFailure;
import org.apache.ode.simpl.events.DMStarted;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.w3c.dom.Element;

public class InsertActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

		ScopeEvent DMStarted = new DMStarted();
		context.getInternalInstance().sendEvent(DMStarted);

		// Laden alle Attributwerte aus der Aktivität.
		loadSIMPLAttributes(context, element);

		DataSourceService datasourceService = SIMPLCore.getInstance()
				.dataSourceService(getDsType(), getDsSubType());

		boolean success = false;

		try {
			success = datasourceService.executeStatement(getDsAddress(),
					getDsStatement());

			if (success == false) {
				ScopeEvent DMFailure = new DMFailure("Wollo is doff");
				context.getInternalInstance().sendEvent(DMFailure);
			} else {
				ScopeEvent DMEnd = new DMEnd();
				context.getInternalInstance().sendEvent(DMEnd);
			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
