package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ActivityFailureEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.DataSourceService;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import commonj.sdo.DataObject;

public class WriteDataBackActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {

//		ScopeEvent DMStarted = new DMStarted();
//		context.getInternalInstance().sendEvent(DMStarted);

		// Load all attribute values from the activity.
		loadSIMPLAttributes(context, element);

		// Load all specific attribute values from the WriteDataBackActivity.
		Attr dataVarAttr = element.getAttributeNode("dataVariable");
		String dataVariableName = dataVarAttr.getValue();

		DataSource ds = getDataSource(getActivityName(), getDsAddress());

		DataSourceService<DataObject, DataObject> datasourceService = SIMPLCore
				.getInstance().dataSourceService();

		DataObject dataFromBPELVariable = null;
		
		try {
		  Node value = null;
      Variable variable = context.getVisibleVariables().get(
          dataVariableName);
      
      if (variable != null) {
        value = context.readVariable(variable);
      }
      
      // TODO: remove output
      System.out.println("HALLO VARIABLE: " + value);
      // TODO: variable in SDO zur�ck verwandeln (XSD muss irgendwo her geholt werden, sollte beim Prozess liegen)
      // TODO: writeData  beim SIMPL Core ausf�hren
			boolean successful =  datasourceService.writeBack(ds, dataFromBPELVariable);

			if (!successful) {
//				ScopeEvent DMFailure = new DMFailure(
//						"The result of the query is null");
//				context.getInternalInstance().sendEvent(DMFailure);
			} 

		} catch (Exception e) {
			ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
			event.setActivityName(context.getActivityName());
			event.setActivityId(context.getOActivity().getId());
			event.setActivityType("WriteDataBackActivity");
			event.setScopeName(context.getOActivity().getParent().name);
			event.setScopeId(0L);
			event.setScopeDeclerationId(context.getOActivity().getParent().getId());
			
			context.getInternalInstance().sendEvent(event);
		}
	}

}
