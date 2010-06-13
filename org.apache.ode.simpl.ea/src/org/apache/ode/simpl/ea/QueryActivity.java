package org.apache.ode.simpl.ea;

import java.util.Map;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.apache.ode.simpl.ea.util.StatementUtils;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

public class QueryActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

//		ScopeEvent DMStarted = new DMStarted();
//		context.getInternalInstance().sendEvent(DMStarted);

		// Load all attribute values from the activity.
		loadSIMPLAttributes(context, element);

		// Load all specific attribute values from the QueryActivity.
		String queryTarget = element.getAttribute("queryTarget").toString();

		if (queryTarget.contains("[") || queryTarget.contains("#")){
			//queryTarget enthält eine BPEL-Variable als Referenz
			Map<String, Variable> variables = null;
			try {
				variables = context.getVisibleVariables();
			} catch (FaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			queryTarget = String.valueOf(StatementUtils.resolveVariable(context, variables, queryTarget));
		}
		
		DataSource ds = getDataSource(getActivityName(), getDsAddress());

		DataSourceService<DataObject, DataObject> datasourceService = SIMPLCore.getInstance()
				.dataSourceService();

		try {
			this.successfullExecution = datasourceService.depositData(
					ds, getDsStatement(context), queryTarget);

//			if (this.successfullExecution) {
//				ScopeEvent DMEnd = new DMEnd();
//				context.getInternalInstance().sendEvent(DMEnd);
//			} else {
//				// Hier werden alle vorhandenen "wichtigen" Daten dem Event
//				// übergeben
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
