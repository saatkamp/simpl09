package org.simpl.ode.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ScopeEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.datasource.ConnectionException;
import org.simpl.core.datasource.RDBDatasourceService2;
import org.simpl.ode.evt.DMEnd;
import org.simpl.ode.evt.DMFailure;
import org.w3c.dom.Element;

public class InsertActivity extends SimplActivity{

	protected void runSync(ExtensionContext context, Element element)
	throws FaultException {
// TODO Auto-generated method stub

RDBDatasourceService2 rdbService = new RDBDatasourceService2();

Statement = getStatement(context, element);
dsAddress = getdsAddress(context, element);
boolean success = false;

try {
	success = rdbService.manipulateData(dsAddress, Statement, null);
	
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
