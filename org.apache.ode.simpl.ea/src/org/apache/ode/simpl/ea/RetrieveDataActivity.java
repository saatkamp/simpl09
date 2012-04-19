package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ActivityFailureEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.apache.ode.simpl.ea.util.DataSourceUtils;
import org.apache.ode.simpl.ea.util.SDOUtils;
import org.apache.ode.simpl.ea.util.VariableUtils;
import org.simpl.core.SIMPLCoreInterface;
import org.simpl.core.services.SIMPLCoreService;
import org.simpl.resource.management.data.LateBinding;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import commonj.sdo.DataObject;

public class RetrieveDataActivity extends DataManagementActivity {

  @Override
  protected void runSync(ExtensionContext context, Element element) throws FaultException {
    // ScopeEvent DMStarted = new DMStarted();
    // context.getInternalInstance().sendEvent(DMStarted);

    // Load all attribute values from the activity.
    loadSIMPLAttributes(context, element);

    // Load all specific attribute values from the RetrieveDataActivity.
    Attr targetVarAttr = element.getAttributeNode("targetVariable");
    String targetVariableName = targetVarAttr.getValue();

    String ds = VariableUtils.getDataSourceReferenceValue(context, getDataResource(), "name");
    LateBinding lb = DataSourceUtils.getLateBinding(context, getDataResource());

    SIMPLCoreInterface simplCoreService = SIMPLCoreService.getInstance().getService();

    try {
      DataObject dataObject = simplCoreService.retrieveData(ds, getDmCommand(context),
          lb);

      if (dataObject == null) {
        // ScopeEvent DMFailure = new DMFailure(
        // "The result of the query is null");
        // context.getInternalInstance().sendEvent(DMFailure);
      } else {
        Node value = SDOUtils.createNodeOfSDO(dataObject);
        Variable variable = context.getVisibleVariables().get(targetVariableName);
        
        if (variable != null) {
          context.writeVariable(variable, value);
        }

        // ScopeEvent DMEnd = new DMEnd();
        // context.getInternalInstance().sendEvent(DMEnd);
      }

    } catch (Exception e) {
      ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
      event.setActivityName(context.getActivityName());
      event.setActivityId(context.getOActivity().getId());
      event.setActivityType("RetrieveDataActivity");
      event.setScopeName(context.getOActivity().getParent().name);
      event.setScopeId(0L);
      event.setScopeDeclerationId(context.getOActivity().getParent().getId());

      context.getInternalInstance().sendEvent(event);
    }
  }
}