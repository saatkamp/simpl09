package org.apache.ode.simpl.ea;

import java.util.Map;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ActivityFailureEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.apache.ode.simpl.ea.util.DataSourceUtils;
import org.apache.ode.simpl.ea.util.StatementUtils;
import org.apache.ode.simpl.ea.util.VariableUtils;
import org.simpl.core.SIMPLCoreInterface;
import org.simpl.core.services.SIMPLCoreService;
import org.simpl.resource.management.data.LateBinding;
import org.w3c.dom.Element;

public class QueryDataActivity extends DataManagementActivity {

  @Override
  protected void runSync(ExtensionContext context, Element element) throws FaultException {
    // ScopeEvent DMStarted = new DMStarted();
    // context.getInternalInstance().sendEvent(DMStarted);

    // Load all attribute values from the activity.
    loadSIMPLAttributes(context, element);

    // Load all specific attribute values from the QueryActivity.
    String targetContainer = element.getAttribute("targetContainer").toString();

    if (targetContainer.contains("[") || targetContainer.contains("#")) {
      // queryTarget enthält eine BPEL-Variable als Referenz
      Map<String, Variable> variables = null;
      try {
        variables = context.getVisibleVariables();
      } catch (FaultException e) {
        ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
        event.setActivityName(context.getActivityName());
        event.setActivityId(context.getOActivity().getId());
        event.setActivityType("QueryDataActivity");
        event.setScopeName(context.getOActivity().getParent().name);
        event.setScopeId(0L);
        event.setScopeDeclerationId(context.getOActivity().getParent().getId());

        context.getInternalInstance().sendEvent(event);
      }

      targetContainer = String.valueOf(StatementUtils.resolveVariable(context, variables,
          targetContainer));
    }

    String ds = VariableUtils.getDataSourceReferenceValue(context, getDataResource(), "name");
    LateBinding lb = DataSourceUtils.getLateBinding(context, getDataResource());

    SIMPLCoreInterface simplCoreService = SIMPLCoreService.getInstance().getService();

    try {
      if (!targetContainer.equals("")) {
        this.successfulExecution = simplCoreService.queryData(ds,
            getDmCommand(context), targetContainer, lb);
      }

      if (!this.successfulExecution) {
        ActivityFailureEvent event = new ActivityFailureEvent();
        event.setActivityName(context.getActivityName());
        event.setActivityId(context.getOActivity().getId());
        event.setActivityType("QueryDataActivity");
        event.setScopeName(context.getOActivity().getParent().name);
        event.setScopeId(0L);
        event.setScopeDeclerationId(context.getOActivity().getParent().getId());
        context.getInternalInstance().sendEvent(event);
        context.completeWithFault(new Throwable("SIMPL Exception"));
      }
    } catch (Exception e) {
      ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
      event.setActivityName(context.getActivityName());
      event.setActivityId(context.getOActivity().getId());
      event.setActivityType("QueryDataActivity");
      event.setScopeName(context.getOActivity().getParent().name);
      event.setScopeId(0L);
      event.setScopeDeclerationId(context.getOActivity().getParent().getId());

      context.getInternalInstance().sendEvent(event);
    }
  }
}
