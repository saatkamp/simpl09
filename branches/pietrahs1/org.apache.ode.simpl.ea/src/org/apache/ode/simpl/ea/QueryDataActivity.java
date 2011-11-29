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
    String queryTarget = element.getAttribute("queryTarget").toString();

    if (queryTarget.contains("[") || queryTarget.contains("#")) {
      // queryTarget enth�lt eine BPEL-Variable als Referenz
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

      queryTarget = String.valueOf(StatementUtils.resolveVariable(context, variables,
          queryTarget));
    }

    String ds = VariableUtils.getDataSourceReferenceValue(context, getDsIdentifier(), "name");
    LateBinding lb = DataSourceUtils.getLateBinding(context, getDsIdentifier());

    SIMPLCoreInterface simplCoreService = SIMPLCoreService.getInstance().getService();

    try {
      if (!queryTarget.equals("")) {
        this.successfulExecution = simplCoreService.queryData(ds,
            getDsStatement(context), queryTarget, lb);
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