package org.simpl.ode.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ScopeEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.SIMPLCore;
import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.exceptions.ConnectionException;
import org.simpl.ode.evt.DMEnd;
import org.simpl.ode.evt.DMFailure;
import org.simpl.ode.evt.DMStarted;
import org.w3c.dom.Element;

public class QueryActivity extends SimplActivity {

	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

		ScopeEvent DMStarted = new DMStarted();
		context.getInternalInstance().sendEvent(DMStarted);

		// Laden alle Attributwerte aus der Aktivität.
		loadSIMPLAttributes(context, element);

		// Laden das Query-spezifische Attribut "queryTarget"
		String queryTarget = element.getAttribute("queryTarget").toString();

		DatasourceService datasourceService = SIMPLCore.getInstance()
				.datasourceService(getDsType(), getDsSubType());

		try {
			boolean success = datasourceService.depositData(getDsAddress(),
					getDsStatement(), queryTarget);
			
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
