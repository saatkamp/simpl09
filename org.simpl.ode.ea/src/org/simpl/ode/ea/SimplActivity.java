package org.simpl.ode.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.rtrep.common.extension.AbstractSyncExtensionOperation;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.datasource.ConnectionException;
import org.simpl.core.datasource.RDBDatasourceService2;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

public class SimplActivity extends AbstractSyncExtensionOperation {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

	}

	protected String getStatement(ExtensionContext context, Element element)
			throws FaultException {
		String Statement = null;
		Statement = element.getAttribute("dsStatement").toString();
		return Statement;
	}

	protected String getdsAddress(ExtensionContext context, Element element)
			throws FaultException {
		String dsAddress = null;
		dsAddress = element.getAttribute("dsAddress").toString();
		return dsAddress;
	}

	protected DataObject getDataObject(String dsAddress, String Statement) throws ConnectionException {
		DataObject data = null;
		RDBDatasourceService2 rdbService = new RDBDatasourceService2();
		data = rdbService.queryData(dsAddress, Statement);
		return data;
	}
	
	protected RDBDatasourceService2 rdbService = new RDBDatasourceService2();
	protected String Statement;
	protected String dsAddress;

}
