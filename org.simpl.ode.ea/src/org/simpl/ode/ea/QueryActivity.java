package org.simpl.ode.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ScopeEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.SIMPLCore;
import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.exceptions.ConnectionException;
import org.simpl.ode.evt.DMEnd;
import org.simpl.ode.evt.DMFailure;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

public class QueryActivity extends SimplActivity {

	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

		DatasourceService datasourceService = SIMPLCore.datasourceServiceProvider
				.getInstance("RDB");

		Statement = getStatement(context, element);
		dsAddress = getdsAddress(context, element);
		DataObject data = null;
		boolean success = false;

		try {
			
			
			success = datasourceService.defineData(dsAddress, "CREATE TABLE TAB (ID VARCHAR(20) NOT NULL, MODE VARCHAR(50))");

			if (success == false) {
				ScopeEvent DMFailure = new DMFailure("Wollo is doff");
				context.getInternalInstance().sendEvent(DMFailure);
			}

			success = datasourceService.manipulateData(dsAddress, "INSERT INTO TAB VALUES ('20', 'Wollo ist doff')", null);
			
			data = datasourceService.queryData(dsAddress, Statement);
			
			printDataObject(context, data, 0);

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
