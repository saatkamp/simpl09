package org.simpl.ode.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evar.ExternalVariableModuleException;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.datasource.ConnectionException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import commonj.sdo.DataObject;

public class RetrieveDataActivity extends SimplActivity{



	protected void runSync(ExtensionContext context, Element element)
	throws FaultException {
// TODO Auto-generated method stub

Statement = getStatement(context, element);
dsAddress = getdsAddress(context, element);
DataObject data = null;

try {
	data = getDataObject(dsAddress, Statement);
	
	context.writeVariable("DataVar", (Node)data);
} catch (ConnectionException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (ExternalVariableModuleException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
}
