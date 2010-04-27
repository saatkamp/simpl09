package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ScopeEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.simpl.events.DMEnd;
import org.apache.ode.simpl.events.DMFailure;
import org.apache.ode.simpl.events.DMStarted;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.w3c.dom.Element;

public class CreateActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

		ScopeEvent DMStarted = new DMStarted();
		context.getInternalInstance().sendEvent(DMStarted);

		// Laden alle Attributwerte aus der Aktivität.
		loadSIMPLAttributes(context, element);

		// Laden alle Informationen aus dem Deployment-Deskriptor.
		loadDeployInformation(context, element);
		
		DataSource ds = getDataSource(getActivityName());

		DataSourceService datasourceService = SIMPLCore.getInstance()
				.dataSourceService(ds.getType(), ds.getSubType());

		try {
			this.successfullExecution = datasourceService.executeStatement(ds,
					getDsStatement());

			if (this.successfullExecution) {
				ScopeEvent DMEnd = new DMEnd();
				context.getInternalInstance().sendEvent(DMEnd);
			} else {
				// Hier werden alle vorhandenen "wichtigen" Daten dem Event
				// übergeben
				//TODO: Sinnvolle Fehlermeldungen einfügen
				ScopeEvent DMFailure = new DMFailure(getActivityName(),
						getDsAddress(), getDsStatement(), "UNKNOWN");
				context.getInternalInstance().sendEvent(DMFailure);
			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
