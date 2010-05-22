package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evar.ExternalVariableModuleException;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.apache.ode.simpl.ea.util.SDOUtils;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import commonj.sdo.DataObject;

public class RetrieveDataActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

//		ScopeEvent DMStarted = new DMStarted();
//		context.getInternalInstance().sendEvent(DMStarted);

		// Laden alle Attributwerte aus der Aktivität.
		loadSIMPLAttributes(context, element);

		// Laden das RetrieveData-spezifische Attribut "dataVariable"
		Attr dataVarAttr = element.getAttributeNode("dataVariable");
		String dataVariableName = dataVarAttr.getValue();

		DataSource ds = getDataSource(getActivityName(), getDsAddress());

		DataSourceService<DataObject, DataObject> datasourceService = SIMPLCore
				.getInstance().dataSourceService();

		try {
			DataObject dataObject = datasourceService.retrieveData(ds,
					getDsStatement(context));

			if (dataObject == null) {
//				ScopeEvent DMFailure = new DMFailure(
//						"The result of the query is null");
//				context.getInternalInstance().sendEvent(DMFailure);
			} else {
				Node value = SDOUtils.createNodeOfSDO(dataObject, element.getNamespaceURI());
				Variable variable = context.getVisibleVariables().get(
						dataVariableName);
				if (variable != null) {

					context.writeVariable(variable, value);

				}

//				ScopeEvent DMEnd = new DMEnd();
//				context.getInternalInstance().sendEvent(DMEnd);
			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExternalVariableModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
