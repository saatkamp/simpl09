package org.apache.ode.simpl.events.listener;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ode.bpel.pmapi.TEventInfo;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.dataformat.DataFormatProvider;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

public class EventWriter {
	protected static final Log logger = LogFactory.getLog(EventWriter.class);

	DataObject dataObject = null;

	public EventWriter() {
		dataObject = DataFormatProvider.getInstance(
				AuditingParameters.getInstance().getDSFormat()).getSDO();
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
		columnObject.set("value", eventInfo.toString());
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		ObjectOutputStream objOut;
		try {
			objOut = new ObjectOutputStream(byteArrayOutputStream);
			objOut.writeObject(eventInfo);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String blob = new String(byteArrayOutputStream.toByteArray());
		
		// serialisiertes Objekt
		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "data");
		columnObject.set("type", "BLOB");
		columnObject.set("value", blob);

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "scope_id");
		columnObject.set("type", AuditingParameters.getInstance().getInt_type());
		columnObject.set("value", eventInfo.getScopeId());

		columnObject = tableObject.createDataObject("column");
		columnObject.set("name", "tstamp");
		columnObject.set("type", "TIMESTAMP");
		columnObject.set("value", eventInfo.getTimestamp().toString());

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

		logger.debug("Auditing data source: " + AuditingParameters.getInstance().getDataSource().getAddress());
		
		try {
			SIMPLCore.getInstance().dataSourceService().writeData(AuditingParameters.getInstance().getDataSource(),
					dataObject, "auditing_table");
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}
}
