package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

public class InsertActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

//		ScopeEvent DMStarted = new DMStarted();
//		context.getInternalInstance().sendEvent(DMStarted);

		// Laden alle Attributwerte aus der Aktivit�t.
		loadSIMPLAttributes(context, element);

		DataSource ds = getDataSource(getActivityName(), getDsAddress());

		DataSourceService<DataObject, DataObject> datasourceService = SIMPLCore.getInstance()
				.dataSourceService();

		try {
			this.successfullExecution = datasourceService.executeStatement(ds,
					getDsStatement(context));

//			if (this.successfullExecution) {
//				ScopeEvent DMEnd = new DMEnd();
//				context.getInternalInstance().sendEvent(DMEnd);
//			} else {
//				// Hier werden alle vorhandenen "wichtigen" Daten dem Event
//				// �bergeben
//				//TODO: Sinnvolle Fehlermeldungen einf�gen
//				ScopeEvent DMFailure = new DMFailure(getActivityName(),
//						getDsAddress(), getDsStatement(context), "UNKNOWN");
//				context.getInternalInstance().sendEvent(DMFailure);
//			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}