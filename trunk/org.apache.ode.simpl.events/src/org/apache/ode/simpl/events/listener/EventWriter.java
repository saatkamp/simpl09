package org.apache.ode.simpl.events.listener;

import java.util.HashMap;

import org.apache.ode.bpel.pmapi.TEventInfo;
import org.simpl.core.SIMPLCore;
import org.simpl.core.helpers.Printer;
import org.simpl.core.services.administration.AdministrationService;
import org.simpl.core.services.dataformat.DataFormatProvider;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

public class EventWriter {

	DataObject dataObject = null;

	public EventWriter() {
		dataObject = DataFormatProvider.getInstance("RDB").getSDO();
	}

	public void write(TEventInfo eventInfo) {
		DataObject tableObject = null;
		DataObject columnObject = null;

		tableObject = dataObject.createDataObject("table");
		tableObject.set("name", "auditing_table");
		tableObject.set("catalog", "");
		tableObject.set("schema", "SimplAuditing");

		// Primary Key (Auto Increment)
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "event_id");
		columnObject.set("type", AuditingParameters.getInstance().getInt_type());
		columnObject.set("value", null);

		// Detail if für verschiedene Details
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "detail");
		columnObject.set("type", AuditingParameters.getInstance().getVarchar_type());
		columnObject.set("value", setDetails(eventInfo));

		// serialisiertes Objekt
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "data");
		columnObject.set("type", "BLOB");
		columnObject.set("value", "");

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "scope_id");
		columnObject.set("type", AuditingParameters.getInstance().getInt_type());
		columnObject.set("value", eventInfo.getScopeId());

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "tstamp");
		columnObject.set("type", "TIMESTAMP");
		columnObject.set("value", eventInfo.getTimestamp());

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "event_type");
		columnObject.set("type", AuditingParameters.getInstance().getVarchar_type());
		columnObject.set("value", eventInfo.getType());

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "instance_id");
		columnObject.set("type", AuditingParameters.getInstance().getInt_type());
		columnObject.set("value", eventInfo.getInstanceId());

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "process_id");
		columnObject.set("type", AuditingParameters.getInstance().getInt_type());
		columnObject.set("value", eventInfo.getProcessId());

		try {
			SIMPLCore.getInstance().dataSourceService().writeData(AuditingParameters.getInstance().getDataSource(),
					tableObject, "auditing_table");
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String setDetails(TEventInfo eventInfo) {
		String detail = "";

		if (!Long.toString(eventInfo.getActivityId()).equals(null)) {
			detail = detail + "ActivityId: "
					+ Long.toString(eventInfo.getActivityId()) + "\\n";
		}

		if (!Long.toString(eventInfo.getActivityDefinitionId()).equals(null)) {
			detail = detail + "ActivityDefinitionId: "
					+ Long.toString(eventInfo.getActivityDefinitionId())
					+ "\\n";
		}

		if (!eventInfo.getActivityName().equals(null)) {
			detail = detail + "ActivityName: " + eventInfo.getActivityName()
					+ "\\n";
		}

		if (!eventInfo.getActivityType().equals(null)) {
			detail = detail + "ActivityType: " + eventInfo.getActivityType()
					+ "\\n";
		}

		if (!eventInfo.getActivityFailureReason().equals(null)) {
			detail = detail + "ActivityFailureReason: "
					+ eventInfo.getActivityFailureReason() + "\\n";
		}

		if (!eventInfo.getActivityRecoveryAction().equals(null)) {
			detail = detail + "ActivityRecoveryActionu: "
					+ eventInfo.getActivityRecoveryAction() + "\\n";
		}

		if (!eventInfo.getCorrelationKey().equals(null)) {
			detail = detail + "CorrelationKey: "
					+ eventInfo.getCorrelationKey() + "\\n";
		}

		if (!eventInfo.getCorrelationSet().equals(null)) {
			detail = detail + "CorrelationSet: "
					+ eventInfo.getCorrelationSet() + "\\n";
		}

		if (!eventInfo.getExplanation().equals(null)) {
			detail = detail + "Explanation: " + eventInfo.getExplanation()
					+ "\\n";
		}

		if (!eventInfo.getExpression().equals(null)) {
			detail = detail + "Expression: " + eventInfo.getExpression()
					+ "\\n";
		}

		if (!eventInfo.getFault().equals(null)) {
			detail = detail + "Fault: " + eventInfo.getFault() + "\\n";
		}

		if (!Integer.toString(eventInfo.getFaultLineNumber()).equals(null)) {
			detail = detail + "FaultLineNumber: "
					+ Integer.toString(eventInfo.getFaultLineNumber()) + "\\n";
		}

		if (!Long.toString(eventInfo.getInstanceId()).equals(null)) {
			detail = detail + "InstanceId: "
					+ Long.toString(eventInfo.getInstanceId()) + "\\n";
		}

		if (!Integer.toString(eventInfo.getLineNumber()).equals(null)) {
			detail = detail + "LineNumber: "
					+ Integer.toString(eventInfo.getLineNumber()) + "\\n";
		}

		if (!eventInfo.getMexId().equals(null)) {
			detail = detail + "MexId: " + eventInfo.getMexId() + "\\n";
		}

		if (!eventInfo.getName().equals(null)) {
			detail = detail + "Name: " + eventInfo.getName() + "\\n";
		}

		if (!Integer.toString(eventInfo.getOldState()).equals(null)) {
			detail = detail + "OldState: "
					+ Integer.toString(eventInfo.getOldState()) + "\\n";
		}

		if (!Integer.toString(eventInfo.getNewState()).equals(null)) {
			detail = detail + "NewState: "
					+ Integer.toString(eventInfo.getNewState()) + "\\n";
		}

		if (!eventInfo.getOperation().equals(null)) {
			detail = detail + "Operation: " + eventInfo.getOperation() + "\\n";
		}

		if (!eventInfo.getPartnerLinkName().equals(null)) {
			detail = detail + "PartnerLinkName(): "
					+ eventInfo.getPartnerLinkName() + "\\n";
		}

		if (!Long.toString(eventInfo.getParentScopeId()).equals(null)) {
			detail = detail + "ParentScopeId "
					+ Long.toString(eventInfo.getParentScopeId()) + "\\n";
		}

		if (!eventInfo.getPortType().equals(null)) {
			detail = detail + "PortType: " + eventInfo.getPortType() + "\\n";
		}

		if (!eventInfo.getResult().equals(null)) {
			detail = detail + "Result: " + eventInfo.getResult() + "\\n";
		}

		if (!Integer.toString(eventInfo.getRootScopeDeclarationId()).equals(
				null)) {
			detail = detail + "RootScopeDeclarationId: "
					+ Integer.toString(eventInfo.getRootScopeDeclarationId())
					+ "\\n";
		}

		if (!Long.toString(eventInfo.getScopeId()).equals(null)) {
			detail = detail + "ScopeId "
					+ Long.toString(eventInfo.getScopeId()) + "\\n";
		}

		if (!Integer.toString(eventInfo.getScopeDefinitionId()).equals(null)) {
			detail = detail + "ScopeDefinitionId: "
					+ Integer.toString(eventInfo.getScopeDefinitionId())
					+ "\\n";
		}

		if (!Long.toString(eventInfo.getRootScopeId()).equals(null)) {
			detail = detail + "RootScopeId "
					+ Long.toString(eventInfo.getRootScopeId()) + "\\n";
		}

		if (!eventInfo.getScopeName().equals(null)) {
			detail = detail + "ScopeName: " + eventInfo.getScopeName() + "\\n";
		}

		if (!eventInfo.getType().equals(null)) {
			detail = detail + "Type: " + eventInfo.getType() + "\\n";
		}

		if (!eventInfo.getVariableName().equals(null)) {
			detail = detail + "VariableName: " + eventInfo.getVariableName()
					+ "\\n";
		}

		if (!eventInfo.getTimestamp().equals(null)) {
			detail = detail + "Timestamp: " + eventInfo.getTimestamp() + "\\n";
		}

		return detail;
	}

}
