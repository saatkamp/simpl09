package org.apache.ode.simpl.events.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ode.bpel.pmapi.TEventInfo;
import org.simpl.core.SIMPLCore;
import org.simpl.core.dataformat.DataFormatProvider;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

public class EventWriter {
	protected static final Log logger = LogFactory.getLog(EventWriter.class);

	DataObject dataObject = null;
	List<String> primaryKeys = new ArrayList<String>();

	public EventWriter() {
		primaryKeys.add("event_id");
	}

	public void write(TEventInfo eventInfo, long counter) {
		//We will send every event as a single row data object.
		dataObject = DataFormatProvider.getInstance(
				AuditingParameters.getInstance().getDSFormat()).getSDO();
		DataObject tableObject = null;
		DataObject columnObject = null;

		tableObject = dataObject.createDataObject("table");
		tableObject.set("name", "auditing_table");
		tableObject.set("catalog", "");
		//TODO: Das Schema sollte von Außen angegeben werden können...
		//Weiterhin kann bei der DB2 nicht einfach irgendein nicht existierendes Schema als default genutzt werden
		tableObject.set("schema", "SIMPL_AUDITING");
		
		tableObject.set("primaryKey", primaryKeys);
		
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "event_id");
		columnObject.set("type", "VARCHAR(255)");
		columnObject.set("value", String.valueOf(eventInfo.getInstanceId())+"_"+String.valueOf(counter));
		
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "event_type");
		columnObject.set("type", AuditingParameters.getInstance().getVarchar_type());
		columnObject.set("value", eventInfo.getType());
		
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "process_id");
		columnObject.set("type", AuditingParameters.getInstance().getVarchar_type());
		columnObject.set("value", eventInfo.getProcessId().getLocalPart());
		
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "instance_id");
		columnObject.set("type", AuditingParameters.getInstance().getInt_type());
		columnObject.set("value", eventInfo.getInstanceId());

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "scope_id");
		columnObject.set("type", AuditingParameters.getInstance().getInt_type());
		columnObject.set("value", eventInfo.getScopeId());

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "tstamp");
		columnObject.set("type", AuditingParameters.getInstance().getVarchar_type());
		columnObject.set("value", eventInfo.getTimestamp().toString().getBytes());

		// serialisiertes Objekt
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "data");
		columnObject.set("type", AuditingParameters.getInstance().getVarchar_type());
		columnObject.set("value", eventInfo.toString());

//		// Detail if für verschiedene Details
//		columnObject = tableObject.createDataObject("column");
//		columnObject.set("name", "detail");
//		columnObject.set("type", AuditingParameters.getInstance().getVarchar_type());
//		columnObject.set("value", eventInfo.toString());

		logger.debug("Auditing data source: " + AuditingParameters.getInstance().getDataSource().getAddress());
		logger.debug("Event SDO: " + dataObject.toString());
		logger.debug("Event SDO table list size: " + dataObject.getList("table").size());
		
		try {
			SIMPLCore.getInstance().dataSourceService().writeData(AuditingParameters.getInstance().getDataSource(),
					dataObject, "AUDITING_TABLE");
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}
}
